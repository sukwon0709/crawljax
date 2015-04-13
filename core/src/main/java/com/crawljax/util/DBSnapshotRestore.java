package com.crawljax.util;

/**
 * Interface for DB snapshot and restore mechanism.
 */
public interface DBSnapshotRestore {

    /**
     * Takes a snapshot of a database.
     */
    void takeSnapshot();

    /**
     * Restores to a snapshot of a database.
     */
    void restore();
}
