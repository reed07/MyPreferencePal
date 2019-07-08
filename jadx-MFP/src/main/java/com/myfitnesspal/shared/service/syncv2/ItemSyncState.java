package com.myfitnesspal.shared.service.syncv2;

public enum ItemSyncState {
    Synchronized(0),
    Created(1),
    Updated(2),
    Deleted(3);
    
    int id;

    private ItemSyncState(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }
}
