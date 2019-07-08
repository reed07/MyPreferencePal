package com.bumptech.glide.load.engine.bitmap_recycle;

public final class IntegerArrayAdapter implements ArrayAdapterInterface<int[]> {
    public int getElementSizeInBytes() {
        return 4;
    }

    public String getTag() {
        return "IntegerArrayPool";
    }

    public int getArrayLength(int[] iArr) {
        return iArr.length;
    }

    public int[] newArray(int i) {
        return new int[i];
    }
}
