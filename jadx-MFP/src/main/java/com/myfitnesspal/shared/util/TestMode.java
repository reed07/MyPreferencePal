package com.myfitnesspal.shared.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/shared/util/TestMode;", "", "()V", "TEST_MODE_PROPERTY", "", "enable", "", "enabled", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TestMode.kt */
public final class TestMode {
    public static final TestMode INSTANCE = new TestMode();
    private static final String TEST_MODE_PROPERTY = TEST_MODE_PROPERTY;

    private TestMode() {
    }

    public final boolean enabled() {
        return Intrinsics.areEqual((Object) "true", (Object) System.getProperty(TEST_MODE_PROPERTY));
    }

    public final void enable(boolean z) {
        System.setProperty(TEST_MODE_PROPERTY, String.valueOf(z));
    }
}
