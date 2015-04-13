package com.crawljax.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

/**
 * Uses mysqlhotcopy internally to snapshot and restore MySQL db.
 *
 * This class depends on the user running the application to have sudoer access
 * and does not need to provide password. This can be done by adding,
 *  username ALL=(ALL) NOPASSWD: ALL
 * to sudoers file by running visudo.
 */
public class MySQLSnapshotRestore implements DBSnapshotRestore {

    public static MySQLSnapshotRestore initialize(String username, String password, String dbname) {
        return new MySQLSnapshotRestore(username, password, dbname);
    }

    private String username;
    private String password;
    private String dbname;

    private MySQLSnapshotRestore(String username, String password, String dbname) {
        this.username = username;
        this.password = password;
        this.dbname = dbname;
    }

    @Override
    public void takeSnapshot() {
        String line = null;
        try {
            Process p = new ProcessBuilder(
                    "sudo", "/usr/bin/mysqlhotcopy", "-u", username, "-p", password, dbname, "/tmp")
                    .start();
            p.waitFor();

            // prints any error messages
            BufferedReader outputReader = getProcessErrOutput(p);
            while ((line = outputReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restore() {
        /**
         * Clearly there are some race conditions between original data removal
         *  and backed up data is fully moved.
         *  One way to solve this issue is to drop all AJAX events once DB restore starts.
         */
        try {
            // Renames original data directory so that backed up data can be moved.
            Process p = new ProcessBuilder(
                    "sudo", "mv", "/var/lib/mysql/" + dbname, "/var/lib/mysql/" + dbname + "-old")
                    .start();
            p.waitFor();

            // Moves backed up data.
            p = new ProcessBuilder(
                    "sudo", "mv", "/tmp/" + dbname, "/var/lib/mysql/" + dbname)
                    .start();
            p.waitFor();

            // Deletes original data.
            p = new ProcessBuilder(
                    "sudo", "rm", "-rf", "/var/lib/mysql/" + dbname + "-old")
                    .start();
            p.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader getProcessOutput(Process p) {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    private BufferedReader getProcessErrOutput(Process p) {
        return new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }
}
