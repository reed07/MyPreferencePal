package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a.c;
import com.facebook.ads.internal.view.i.b.m;

public class k extends c {
    private final f<m> a;

    public k(Context context) {
        this(context, null);
    }

    public k(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public k(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new f<m>() {
            public Class<m> a() {
                return m.class;
            }

            public void a(m mVar) {
                k.this.setVisibility(8);
            }
        };
        int applyDimension = (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics());
        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(-1, Mode.SRC_IN);
        LayoutParams layoutParams = new LayoutParams(applyDimension, applyDimension);
        layoutParams.addRule(13);
        addView(progressBar, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        setVisibility(0);
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.a);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.a);
        }
        setVisibility(8);
        super.b();
    }
}
