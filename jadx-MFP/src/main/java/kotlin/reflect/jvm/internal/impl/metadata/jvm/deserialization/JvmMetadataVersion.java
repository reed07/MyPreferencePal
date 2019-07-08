package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.Arrays;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmMetadataVersion.kt */
public final class JvmMetadataVersion extends BinaryVersion {
    public static final Companion Companion = new Companion(null);
    @NotNull
    @JvmField
    public static final JvmMetadataVersion INSTANCE = new JvmMetadataVersion(1, 1, 13);
    @NotNull
    @JvmField
    public static final JvmMetadataVersion INVALID_VERSION = new JvmMetadataVersion(new int[0]);
    private final boolean isStrictSemantics;

    /* compiled from: JvmMetadataVersion.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public JvmMetadataVersion(@NotNull int[] iArr, boolean z) {
        Intrinsics.checkParameterIsNotNull(iArr, "versionArray");
        super(Arrays.copyOf(iArr, iArr.length));
        this.isStrictSemantics = z;
    }

    public JvmMetadataVersion(@NotNull int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "numbers");
        this(iArr, false);
    }

    public boolean isCompatible() {
        if (getMajor() == 1 && getMinor() == 0) {
            return false;
        }
        boolean z = this.isStrictSemantics ? isCompatibleTo(INSTANCE) : getMajor() == 1 && getMinor() <= 4;
        if (z) {
            return true;
        }
        return false;
    }
}
