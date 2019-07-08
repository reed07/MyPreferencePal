package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.google.ads.interactivemedia.v3.internal.la;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* compiled from: IMASDK */
public final class lb<T extends la<T>> implements tv<T> {
    private final tv<? extends T> a;
    private final List<lc> b;

    public lb(tv<? extends T> tvVar, List<lc> list) {
        this.a = tvVar;
        this.b = list;
    }

    public final /* synthetic */ Object a(Uri uri, InputStream inputStream) throws IOException {
        la laVar = (la) this.a.a(uri, inputStream);
        List<lc> list = this.b;
        return (list == null || list.isEmpty()) ? laVar : (la) laVar.a(this.b);
    }
}
