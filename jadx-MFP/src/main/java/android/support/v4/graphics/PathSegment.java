package android.support.v4.graphics;

import android.graphics.PointF;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class PathSegment {
    private final PointF mEnd;
    private final float mEndFraction;
    private final PointF mStart;
    private final float mStartFraction;

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegment)) {
            return false;
        }
        PathSegment pathSegment = (PathSegment) obj;
        if (Float.compare(this.mStartFraction, pathSegment.mStartFraction) != 0 || Float.compare(this.mEndFraction, pathSegment.mEndFraction) != 0 || !this.mStart.equals(pathSegment.mStart) || !this.mEnd.equals(pathSegment.mEnd)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = this.mStart.hashCode() * 31;
        float f = this.mStartFraction;
        int i = 0;
        int floatToIntBits = (((hashCode + (f != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(f) : 0)) * 31) + this.mEnd.hashCode()) * 31;
        float f2 = this.mEndFraction;
        if (f2 != BitmapDescriptorFactory.HUE_RED) {
            i = Float.floatToIntBits(f2);
        }
        return floatToIntBits + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PathSegment{start=");
        sb.append(this.mStart);
        sb.append(", startFraction=");
        sb.append(this.mStartFraction);
        sb.append(", end=");
        sb.append(this.mEnd);
        sb.append(", endFraction=");
        sb.append(this.mEndFraction);
        sb.append('}');
        return sb.toString();
    }
}
