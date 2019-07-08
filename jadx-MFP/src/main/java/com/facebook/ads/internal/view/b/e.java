package com.facebook.ads.internal.view.b;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.w.c.b;
import com.facebook.ads.internal.w.c.c;
import com.mopub.common.Constants;

@TargetApi(19)
public class e extends LinearLayout {
    private TextView a = new TextView(getContext());
    private TextView b;
    private Drawable c;

    public e(Context context) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        setOrientation(1);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        this.a.setTextColor(-16777216);
        this.a.setTextSize(2, 20.0f);
        this.a.setEllipsize(TruncateAt.END);
        this.a.setSingleLine(true);
        this.a.setVisibility(8);
        addView(this.a, layoutParams);
        this.b = new TextView(getContext());
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        this.b.setAlpha(0.5f);
        this.b.setTextColor(-16777216);
        this.b.setTextSize(2, 15.0f);
        this.b.setCompoundDrawablePadding((int) (f * 5.0f));
        this.b.setEllipsize(TruncateAt.END);
        this.b.setSingleLine(true);
        this.b.setVisibility(8);
        addView(this.b, layoutParams2);
    }

    private Drawable getPadlockDrawable() {
        if (this.c == null) {
            this.c = new BitmapDrawable(getContext().getResources(), c.a(b.BROWSER_PADLOCK));
        }
        return this.c;
    }

    public void setSubtitle(String str) {
        TextView textView;
        int i;
        if (TextUtils.isEmpty(str)) {
            this.b.setText(null);
            textView = this.b;
            i = 8;
        } else {
            Uri parse = Uri.parse(str);
            this.b.setText(parse.getHost());
            this.b.setCompoundDrawablesRelativeWithIntrinsicBounds(Constants.HTTPS.equals(parse.getScheme()) ? getPadlockDrawable() : null, null, null, null);
            textView = this.b;
            i = 0;
        }
        textView.setVisibility(i);
    }

    public void setTitle(String str) {
        TextView textView;
        int i;
        if (TextUtils.isEmpty(str)) {
            this.a.setText(null);
            textView = this.a;
            i = 8;
        } else {
            this.a.setText(str);
            textView = this.a;
            i = 0;
        }
        textView.setVisibility(i);
    }
}
