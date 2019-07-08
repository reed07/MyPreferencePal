package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class h extends v {
    private final int height;
    private final int left;
    private final int top;
    private final int width;

    private h(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.height = i3;
        this.width = i4;
    }

    public final int left() {
        return this.left;
    }

    public final int top() {
        return this.top;
    }

    public final int height() {
        return this.height;
    }

    public final int width() {
        return this.width;
    }

    public final String toString() {
        int i = this.left;
        int i2 = this.top;
        int i3 = this.height;
        int i4 = this.width;
        StringBuilder sb = new StringBuilder(90);
        sb.append("BoundingRectData{left=");
        sb.append(i);
        sb.append(", top=");
        sb.append(i2);
        sb.append(", height=");
        sb.append(i3);
        sb.append(", width=");
        sb.append(i4);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return this.left == vVar.left() && this.top == vVar.top() && this.height == vVar.height() && this.width == vVar.width();
    }

    public final int hashCode() {
        return ((((((this.left ^ 1000003) * 1000003) ^ this.top) * 1000003) ^ this.height) * 1000003) ^ this.width;
    }

    /* synthetic */ h(int i, int i2, int i3, int i4, f fVar) {
        this(i, i2, i3, i4);
    }
}
