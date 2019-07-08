package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.Arrays;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBytecodeBinaryVersion.kt */
public final class JvmBytecodeBinaryVersion extends BinaryVersion {
    public static final Companion Companion = new Companion(null);
    @NotNull
    @JvmField
    public static final JvmBytecodeBinaryVersion INSTANCE = new JvmBytecodeBinaryVersion(1, 0, 3);
    @NotNull
    @JvmField
    public static final JvmBytecodeBinaryVersion INVALID_VERSION = new JvmBytecodeBinaryVersion(new int[0]);

    /* compiled from: JvmBytecodeBinaryVersion.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public JvmBytecodeBinaryVersion(@NotNull int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "numbers");
        super(Arrays.copyOf(iArr, iArr.length));
    }
}
