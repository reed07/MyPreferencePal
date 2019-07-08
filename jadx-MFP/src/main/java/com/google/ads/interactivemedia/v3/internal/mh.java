package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;

/* compiled from: IMASDK */
final class mh {
    private final fq[] a;
    private fq b;

    public mh(fq[] fqVarArr) {
        this.a = fqVarArr;
    }

    public final fq a(fr frVar, fs fsVar, Uri uri) throws IOException, InterruptedException {
        fq fqVar = this.b;
        if (fqVar != null) {
            return fqVar;
        }
        fq[] fqVarArr = this.a;
        int length = fqVarArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            fq fqVar2 = fqVarArr[i];
            try {
                if (fqVar2.a(frVar)) {
                    this.b = fqVar2;
                    frVar.a();
                    break;
                }
                i++;
            } catch (EOFException unused) {
            } finally {
                frVar.a();
            }
        }
        fq fqVar3 = this.b;
        if (fqVar3 != null) {
            fqVar3.a(fsVar);
            return this.b;
        }
        String b2 = vf.b((Object[]) this.a);
        StringBuilder sb = new StringBuilder(String.valueOf(b2).length() + 58);
        sb.append("None of the available extractors (");
        sb.append(b2);
        sb.append(") could read the stream.");
        throw new nb(sb.toString(), uri);
    }

    public final void a() {
        fq fqVar = this.b;
        if (fqVar != null) {
            fqVar.c();
            this.b = null;
        }
    }
}
