package com.facebook.ads.internal.view.d;

import android.util.SparseArray;

public class a {
    private final SparseArray<int[]> a = new SparseArray<>();

    public void a(int i, int[] iArr) {
        this.a.put(i, iArr);
    }

    public int[] a(int i) {
        return (int[]) this.a.get(i);
    }

    public boolean b(int i) {
        return this.a.indexOfKey(i) >= 0;
    }
}
