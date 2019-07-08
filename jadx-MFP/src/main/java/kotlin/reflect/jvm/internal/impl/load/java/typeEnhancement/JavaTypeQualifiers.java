package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeQualifiers.kt */
public final class JavaTypeQualifiers {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final JavaTypeQualifiers NONE;
    private final boolean isNotNullTypeParameter;
    private final boolean isNullabilityQualifierForWarning;
    @Nullable
    private final MutabilityQualifier mutability;
    @Nullable
    private final NullabilityQualifier nullability;

    /* compiled from: typeQualifiers.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final JavaTypeQualifiers getNONE() {
            return JavaTypeQualifiers.NONE;
        }
    }

    public JavaTypeQualifiers(@Nullable NullabilityQualifier nullabilityQualifier, @Nullable MutabilityQualifier mutabilityQualifier, boolean z, boolean z2) {
        this.nullability = nullabilityQualifier;
        this.mutability = mutabilityQualifier;
        this.isNotNullTypeParameter = z;
        this.isNullabilityQualifierForWarning = z2;
    }

    @Nullable
    public final NullabilityQualifier getNullability() {
        return this.nullability;
    }

    @Nullable
    public final MutabilityQualifier getMutability() {
        return this.mutability;
    }

    public final boolean isNotNullTypeParameter$descriptors_jvm() {
        return this.isNotNullTypeParameter;
    }

    public /* synthetic */ JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 8) != 0) {
            z2 = false;
        }
        this(nullabilityQualifier, mutabilityQualifier, z, z2);
    }

    public final boolean isNullabilityQualifierForWarning$descriptors_jvm() {
        return this.isNullabilityQualifierForWarning;
    }

    static {
        JavaTypeQualifiers javaTypeQualifiers = new JavaTypeQualifiers(null, null, false, false, 8, null);
        NONE = javaTypeQualifiers;
    }
}
