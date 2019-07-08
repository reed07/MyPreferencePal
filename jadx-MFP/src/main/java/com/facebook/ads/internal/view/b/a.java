package com.facebook.ads.internal.view.b;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;
import com.facebook.ads.internal.w.c.c;
import com.google.android.exoplayer2.C;
import java.util.List;

@TargetApi(19)
public class a extends LinearLayout {
    private static final int a = Color.rgb(224, 224, 224);
    private static final Uri b = Uri.parse("http://www.facebook.com");
    private static final OnTouchListener c = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    x.a(view, a.d);
                    break;
                case 1:
                    x.a(view, 0);
                    break;
            }
            return false;
        }
    };
    /* access modifiers changed from: private */
    public static final int d = Color.argb(34, 0, 0, 0);
    private ImageView e;
    private e f;
    private ImageView g;
    /* access modifiers changed from: private */
    public C0014a h;
    /* access modifiers changed from: private */
    public String i;

    /* renamed from: com.facebook.ads.internal.view.b.a$a reason: collision with other inner class name */
    public interface C0014a {
        void a();
    }

    public a(Context context) {
        super(context);
        float f2 = getResources().getDisplayMetrics().density;
        int i2 = (int) (50.0f * f2);
        int i3 = (int) (f2 * 4.0f);
        x.a((View) this, -1);
        setGravity(16);
        this.e = new ImageView(context);
        this.e.setContentDescription("Close");
        LayoutParams layoutParams = new LayoutParams(i2, i2);
        this.e.setScaleType(ScaleType.CENTER);
        this.e.setImageBitmap(c.a(b.BROWSER_CLOSE));
        this.e.setOnTouchListener(c);
        this.e.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (a.this.h != null) {
                    a.this.h.a();
                }
            }
        });
        addView(this.e, layoutParams);
        this.f = new e(context);
        LayoutParams layoutParams2 = new LayoutParams(0, -2);
        layoutParams2.weight = 1.0f;
        this.f.setPadding(0, i3, 0, i3);
        addView(this.f, layoutParams2);
        this.g = new ImageView(context);
        LayoutParams layoutParams3 = new LayoutParams(i2, i2);
        this.g.setContentDescription("Open native browser");
        this.g.setScaleType(ScaleType.CENTER);
        this.g.setOnTouchListener(c);
        this.g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!TextUtils.isEmpty(a.this.i) && !"about:blank".equals(a.this.i)) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(a.this.i));
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    a.this.getContext().startActivity(intent);
                }
            }
        });
        addView(this.g, layoutParams3);
        setupDefaultNativeBrowser(context);
    }

    private void setupDefaultNativeBrowser(Context context) {
        Bitmap bitmap;
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", b), 65536);
        if (queryIntentActivities.size() == 0) {
            this.g.setVisibility(8);
            bitmap = null;
        } else {
            bitmap = c.a((queryIntentActivities.size() != 1 || !"com.android.chrome".equals(((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName)) ? b.BROWSER_LAUNCH_NATIVE : b.BROWSER_LAUNCH_CHROME);
        }
        this.g.setImageBitmap(bitmap);
    }

    public void setListener(C0014a aVar) {
        this.h = aVar;
    }

    public void setTitle(String str) {
        this.f.setTitle(str);
    }

    public void setUrl(String str) {
        this.i = str;
        if (TextUtils.isEmpty(str) || "about:blank".equals(str)) {
            this.f.setSubtitle(null);
            this.g.setEnabled(false);
            this.g.setColorFilter(new PorterDuffColorFilter(a, Mode.SRC_IN));
            return;
        }
        this.f.setSubtitle(str);
        this.g.setEnabled(true);
        this.g.setColorFilter(null);
    }
}
