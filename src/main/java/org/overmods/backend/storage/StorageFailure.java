package org.overmods.backend.storage;

import org.overmods.backend.error.Failed;

public class StorageFailure extends Failed {
    public StorageFailure(Throwable cause) {
        super();
        initCause(cause);
    }
}
