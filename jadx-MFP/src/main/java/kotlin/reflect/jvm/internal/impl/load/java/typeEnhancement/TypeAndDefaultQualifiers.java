package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: signatureEnhancement.kt */
final class TypeAndDefaultQualifiers {
    @Nullable
    private final JavaTypeQualifiers defaultQualifiers;
    @NotNull
    private final KotlinType type;

    @NotNull
    public final KotlinType component1() {
        return this.type;
    }

    @Nullable
    public final JavaTypeQualifiers component2() {
        return this.defaultQualifiers;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.defaultQualifiers, (java.lang.Object) r3.defaultQualifiers) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers
            if (r0 == 0) goto L_0x001d
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers r3 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers) r3
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r2.type
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r3.type
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r0 = r2.defaultQualifiers
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r3 = r3.defaultQualifiers
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        KotlinType kotlinType = this.type;
        int i = 0;
        int hashCode = (kotlinType != null ? kotlinType.hashCode() : 0) * 31;
        JavaTypeQualifiers javaTypeQualifiers = this.defaultQualifiers;
        if (javaTypeQualifiers != null) {
            i = javaTypeQualifiers.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TypeAndDefaultQualifiers(type=");
        sb.append(this.type);
        sb.append(", defaultQualifiers=");
        sb.append(this.defaultQualifiers);
        sb.append(")");
        return sb.toString();
    }

    public TypeAndDefaultQualifiers(@NotNull KotlinType kotlinType, @Nullable JavaTypeQualifiers javaTypeQualifiers) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        this.type = kotlinType;
        this.defaultQualifiers = javaTypeQualifiers;
    }

    @NotNull
    public final KotlinType getType() {
        return this.type;
    }
}
