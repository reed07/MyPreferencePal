package com.myfitnesspal.feature.externalsync.impl.googlefit.model;

public final class SyncScope {
    String scope;
    private int version;

    public SyncScope(int i, String str) {
        if (str != null) {
            this.version = i;
            this.scope = str;
            return;
        }
        throw new IllegalArgumentException("SyncScope must not be null.");
    }

    public SyncScope(String str) {
        this(1, str);
    }

    public String getScope() {
        return this.scope;
    }
}
