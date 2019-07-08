package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class os implements gc {
    final /* synthetic */ op a;
    private final mq b;
    private final bu c = new bu();
    private final ex d = new ex();

    os(op opVar, mq mqVar) {
        this.a = opVar;
        this.b = mqVar;
    }

    public final void a(bs bsVar) {
        this.b.a(bsVar);
    }

    public final int a(fr frVar, int i, boolean z) throws IOException, InterruptedException {
        return this.b.a(frVar, i, z);
    }

    public final void a(ut utVar, int i) {
        this.b.a(utVar, i);
    }

    public final void a(long j, int i, int i2, int i3, gd gdVar) {
        ex exVar;
        this.b.a(j, i, i2, i3, gdVar);
        while (this.b.d()) {
            this.d.a();
            if (this.b.a(this.c, this.d, false, false, 0) == -4) {
                this.d.g();
                exVar = this.d;
            } else {
                exVar = null;
            }
            if (exVar != null) {
                long j2 = exVar.c;
                ByteBuffer byteBuffer = exVar.b;
                byte[] array = byteBuffer.array();
                int limit = byteBuffer.limit();
                ut utVar = new ut(array, limit);
                String str = (String) qi.a(utVar.r());
                String str2 = (String) qi.a(utVar.r());
                long j3 = utVar.j();
                long j4 = utVar.j();
                if (j4 != 0) {
                    StringBuilder sb = new StringBuilder(63);
                    sb.append("Ignoring non-zero presentation_time_delta: ");
                    sb.append(j4);
                    Log.w("EventMessageDecoder", sb.toString());
                }
                ju juVar = new ju(str, str2, vf.c(utVar.j(), 1000, j3), utVar.j(), Arrays.copyOfRange(array, utVar.d(), limit));
                boolean z = false;
                ju juVar2 = (ju) new js(juVar).a(0);
                String str3 = juVar2.a;
                String str4 = juVar2.b;
                if ("urn:mpeg:dash:event:2012".equals(str3) && (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(str4) || InternalAvidAdSessionContext.AVID_API_LEVEL.equals(str4) || "3".equals(str4))) {
                    z = true;
                }
                if (z) {
                    long a2 = op.b(juVar2);
                    if (a2 != -9223372036854775807L) {
                        this.a.d.sendMessage(this.a.d.obtainMessage(1, new oq(j2, a2)));
                    }
                }
            }
        }
        this.b.m();
    }

    public final void a() {
        this.b.a();
    }
}
