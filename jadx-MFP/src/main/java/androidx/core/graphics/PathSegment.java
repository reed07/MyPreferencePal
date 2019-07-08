package androidx.core.graphics;

import android.graphics.PointF;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001b"}, d2 = {"Landroidx/core/graphics/PathSegment;", "", "start", "Landroid/graphics/PointF;", "startFraction", "", "end", "endFraction", "(Landroid/graphics/PointF;FLandroid/graphics/PointF;F)V", "getEnd", "()Landroid/graphics/PointF;", "getEndFraction", "()F", "getStart", "getStartFraction", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core-ktx_release"}, k = 1, mv = {1, 1, 9})
/* compiled from: Path.kt */
public final class PathSegment {
    @NotNull
    private final PointF end;
    private final float endFraction;
    @NotNull
    private final PointF start;
    private final float startFraction;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (java.lang.Float.compare(r2.endFraction, r3.endFraction) == 0) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0033
            boolean r0 = r3 instanceof androidx.core.graphics.PathSegment
            if (r0 == 0) goto L_0x0031
            androidx.core.graphics.PathSegment r3 = (androidx.core.graphics.PathSegment) r3
            android.graphics.PointF r0 = r2.start
            android.graphics.PointF r1 = r3.start
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            float r0 = r2.startFraction
            float r1 = r3.startFraction
            int r0 = java.lang.Float.compare(r0, r1)
            if (r0 != 0) goto L_0x0031
            android.graphics.PointF r0 = r2.end
            android.graphics.PointF r1 = r3.end
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            float r0 = r2.endFraction
            float r3 = r3.endFraction
            int r3 = java.lang.Float.compare(r0, r3)
            if (r3 != 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r3 = 0
            return r3
        L_0x0033:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathSegment.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        PointF pointF = this.start;
        int i = 0;
        int hashCode = (((pointF != null ? pointF.hashCode() : 0) * 31) + Float.floatToIntBits(this.startFraction)) * 31;
        PointF pointF2 = this.end;
        if (pointF2 != null) {
            i = pointF2.hashCode();
        }
        return ((hashCode + i) * 31) + Float.floatToIntBits(this.endFraction);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PathSegment(start=");
        sb.append(this.start);
        sb.append(", startFraction=");
        sb.append(this.startFraction);
        sb.append(", end=");
        sb.append(this.end);
        sb.append(", endFraction=");
        sb.append(this.endFraction);
        sb.append(")");
        return sb.toString();
    }
}
