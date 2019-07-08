package com.inmobi.ads;

import android.media.MediaMetadataRetriever;

/* compiled from: NativeTimer */
public final class ba {
    a a;
    a b;

    /* compiled from: NativeTimer */
    public static class a {
        private long a;
        private long b;
        private String c;
        private ao d;

        public a(long j, long j2, String str, ao aoVar) {
            this.a = j;
            this.b = j2;
            this.c = str;
            this.d = aoVar;
        }

        public final long a() {
            long j = this.a;
            ak b2 = this.d.b(this.c);
            if (b2 == null || !(b2 instanceof be)) {
                return j;
            }
            be beVar = (be) b2;
            if (beVar == null) {
                return j;
            }
            String b3 = beVar.b().b();
            if (b3 == null) {
                return j;
            }
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(b3);
            long intValue = (long) (((double) j) + (((((double) this.b) * 1.0d) / 100.0d) * ((double) (((long) Integer.valueOf(mediaMetadataRetriever.extractMetadata(9)).intValue()) / 1000))));
            mediaMetadataRetriever.release();
            return intValue;
        }
    }

    public ba(a aVar, a aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }
}
