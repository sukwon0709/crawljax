package com.crawljax.util;

import org.junit.Test;

/**
 * Created by soh on 13/04/15.
 */
public class MySQLSnapshotRestoreTest {

    @Test
    public void snapshot() {
        System.out.println("taking a snapshot...");
        MySQLSnapshotRestore snapshotRestore = new MySQLSnapshotRestore("root", "12345", "wordpress");
        snapshotRestore.takeSnapshot();
    }

    @Test
    public void restore() {
        System.out.println("restoring a snapshot...");
        MySQLSnapshotRestore snapshotRestore = new MySQLSnapshotRestore("root", "12345", "wordpress");
        snapshotRestore.restore();
    }
}
