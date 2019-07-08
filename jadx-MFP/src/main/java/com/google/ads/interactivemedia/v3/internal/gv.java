package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: IMASDK */
final class gv extends gu {
    public final long be;
    public final List<gw> bf = new ArrayList();
    public final List<gv> bg = new ArrayList();

    public gv(int i, long j) {
        super(i);
        this.be = j;
    }

    public final void a(gw gwVar) {
        this.bf.add(gwVar);
    }

    public final void a(gv gvVar) {
        this.bg.add(gvVar);
    }

    public final gw d(int i) {
        int size = this.bf.size();
        for (int i2 = 0; i2 < size; i2++) {
            gw gwVar = (gw) this.bf.get(i2);
            if (gwVar.bd == i) {
                return gwVar;
            }
        }
        return null;
    }

    public final gv e(int i) {
        int size = this.bg.size();
        for (int i2 = 0; i2 < size; i2++) {
            gv gvVar = (gv) this.bg.get(i2);
            if (gvVar.bd == i) {
                return gvVar;
            }
        }
        return null;
    }

    public final String toString() {
        String c = c(this.bd);
        String arrays = Arrays.toString(this.bf.toArray());
        String arrays2 = Arrays.toString(this.bg.toArray());
        StringBuilder sb = new StringBuilder(String.valueOf(c).length() + 22 + String.valueOf(arrays).length() + String.valueOf(arrays2).length());
        sb.append(c);
        sb.append(" leaves: ");
        sb.append(arrays);
        sb.append(" containers: ");
        sb.append(arrays2);
        return sb.toString();
    }
}
