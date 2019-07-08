package com.myfitnesspal.shared.service.imagesync;

public interface ImageSyncFlags {
    public static final int DELETED = 5;
    public static final int PENDING_ASSOCIATE = 2;
    public static final int PENDING_DELETE = 1;
    public static final int PENDING_DISASSOCIATE = 3;
    public static final int PENDING_UPLOAD = 0;
    public static final int PROCESSED = 4;
}
