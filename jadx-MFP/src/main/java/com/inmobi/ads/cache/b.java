package com.inmobi.ads.cache;

import android.support.annotation.Nullable;
import com.inmobi.ads.bm;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: AdAssetBatch */
public final class b {
    public List<a> a = new ArrayList();
    Set<bm> b;
    Set<String> c = new HashSet();
    int d;
    int e;
    public String f;
    @Nullable
    public String g;
    public String h;
    private String i;
    private final WeakReference<f> j;

    public b(String str, String str2, Set<bm> set, f fVar) {
        this.i = str;
        this.f = str2;
        this.b = set;
        this.j = new WeakReference<>(fVar);
    }

    public b(String str, Set<bm> set, f fVar, String str2) {
        this.i = str;
        this.h = str2;
        this.b = set;
        this.j = new WeakReference<>(fVar);
    }

    @Nullable
    public final f a() {
        return (f) this.j.get();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AdAssetBatch{mRawAssets=");
        sb.append(this.b);
        sb.append(", mBatchDownloadSuccessCount=");
        sb.append(this.d);
        sb.append(", mBatchDownloadFailureCount=");
        sb.append(this.e);
        sb.append('}');
        return sb.toString();
    }
}
