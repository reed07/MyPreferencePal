package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import java.util.List;

/* compiled from: IMASDK */
public final class adm extends ImageView implements OnClickListener {
    /* access modifiers changed from: private */
    public final CompanionData a;
    private final aeb b;
    private final String c;
    private final List<ClickListener> d;

    public adm(Context context, aeb aeb, CompanionData companionData, String str, List<ClickListener> list) {
        super(context);
        this.b = aeb;
        this.a = companionData;
        this.c = str;
        this.d = list;
        setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public final void a() {
        this.b.a(this.a.companionId(), this.c);
    }

    public final void onClick(View view) {
        for (ClickListener onCompanionAdClick : this.d) {
            onCompanionAdClick.onCompanionAdClick();
        }
        this.b.c(this.a.clickThroughUrl());
    }
}
