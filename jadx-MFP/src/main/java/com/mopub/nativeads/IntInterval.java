package com.mopub.nativeads;

import android.support.annotation.Nullable;

public class IntInterval implements Comparable<IntInterval> {
    private int length;
    private int start;

    public IntInterval(int i, int i2) {
        this.start = i;
        this.length = i2;
    }

    public int getStart() {
        return this.start;
    }

    public int getLength() {
        return this.length;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public boolean equals(int i, int i2) {
        return this.start == i && this.length == i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{start : ");
        sb.append(this.start);
        sb.append(", length : ");
        sb.append(this.length);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntInterval)) {
            return false;
        }
        IntInterval intInterval = (IntInterval) obj;
        if (!(this.start == intInterval.start && this.length == intInterval.length)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((899 + this.start) * 31) + this.length;
    }

    public int compareTo(@Nullable IntInterval intInterval) {
        int i = this.start;
        int i2 = intInterval.start;
        return i == i2 ? this.length - intInterval.length : i - i2;
    }
}
