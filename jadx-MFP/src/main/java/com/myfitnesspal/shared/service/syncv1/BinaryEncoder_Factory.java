package com.myfitnesspal.shared.service.syncv1;

import dagger.internal.Factory;

public final class BinaryEncoder_Factory implements Factory<BinaryEncoder> {
    private static final BinaryEncoder_Factory INSTANCE = new BinaryEncoder_Factory();

    public BinaryEncoder get() {
        return provideInstance();
    }

    public static BinaryEncoder provideInstance() {
        return new BinaryEncoder();
    }

    public static BinaryEncoder_Factory create() {
        return INSTANCE;
    }

    public static BinaryEncoder newBinaryEncoder() {
        return new BinaryEncoder();
    }
}
