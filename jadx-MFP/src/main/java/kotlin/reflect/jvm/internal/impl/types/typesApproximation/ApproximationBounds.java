package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeApproximation.kt */
public final class ApproximationBounds<T> {
    private final T lower;
    private final T upper;

    public final T component1() {
        return this.lower;
    }

    public final T component2() {
        return this.upper;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.upper, (java.lang.Object) r3.upper) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.types.typesApproximation.ApproximationBounds
            if (r0 == 0) goto L_0x001d
            kotlin.reflect.jvm.internal.impl.types.typesApproximation.ApproximationBounds r3 = (kotlin.reflect.jvm.internal.impl.types.typesApproximation.ApproximationBounds) r3
            T r0 = r2.lower
            T r1 = r3.lower
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            T r0 = r2.upper
            T r3 = r3.upper
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
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.typesApproximation.ApproximationBounds.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        T t = this.lower;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        T t2 = this.upper;
        if (t2 != null) {
            i = t2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApproximationBounds(lower=");
        sb.append(this.lower);
        sb.append(", upper=");
        sb.append(this.upper);
        sb.append(")");
        return sb.toString();
    }

    public ApproximationBounds(T t, T t2) {
        this.lower = t;
        this.upper = t2;
    }

    public final T getLower() {
        return this.lower;
    }

    public final T getUpper() {
        return this.upper;
    }
}
