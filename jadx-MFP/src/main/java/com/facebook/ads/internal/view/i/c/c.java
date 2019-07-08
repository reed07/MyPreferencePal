package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.widget.TextView;
import com.brightcove.player.util.StringUtil;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.b.o;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class c extends com.facebook.ads.internal.view.i.a.c {
    /* access modifiers changed from: private */
    public final TextView a;
    private final String b;
    private final f<o> c = new f<o>() {
        public Class<o> a() {
            return o.class;
        }

        public void a(o oVar) {
            if (c.this.getVideoView() != null) {
                TextView d = c.this.a;
                c cVar = c.this;
                d.setText(c.a(cVar, (long) (cVar.getVideoView().getDuration() - c.this.getVideoView().getCurrentPositionInMillis())));
            }
        }
    };

    public c(Context context, String str) {
        super(context);
        this.a = new TextView(context);
        this.b = str;
        addView(this.a);
    }

    static /* synthetic */ String a(c cVar, long j) {
        if (j <= 0) {
            return "00:00";
        }
        long minutes = TimeUnit.MILLISECONDS.toMinutes(j);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(j % DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
        if (cVar.b.isEmpty()) {
            return String.format(Locale.US, StringUtil.SHORT_TIME_FORMAT, new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)});
        }
        return cVar.b.replace("{{REMAINING_TIME}}", String.format(Locale.US, StringUtil.SHORT_TIME_FORMAT, new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)}));
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.c);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.c);
        }
        super.b();
    }

    public void setCountdownTextColor(int i) {
        this.a.setTextColor(i);
    }
}
