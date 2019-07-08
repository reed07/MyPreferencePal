package com.facebook.ads;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.g;
import com.facebook.ads.internal.w.b.j;
import com.facebook.ads.internal.w.b.x;

@Deprecated
public class AdChoicesView extends RelativeLayout {
    /* access modifiers changed from: private */
    public final NativeAdBase a;
    private final float b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public TextView d;
    private String e;

    @Deprecated
    public AdChoicesView(Context context, NativeAdBase nativeAdBase) {
        this(context, nativeAdBase, false);
    }

    @Deprecated
    public AdChoicesView(Context context, NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout) {
        this(context, nativeAdBase, false, nativeAdLayout);
    }

    @Deprecated
    public AdChoicesView(Context context, NativeAdBase nativeAdBase, boolean z) {
        this(context, nativeAdBase, z, null);
    }

    @Deprecated
    public AdChoicesView(Context context, NativeAdBase nativeAdBase, boolean z, @Nullable NativeAdLayout nativeAdLayout) {
        super(context);
        boolean z2 = false;
        this.c = false;
        this.a = nativeAdBase;
        this.b = x.b;
        this.a.f().a(nativeAdLayout);
        if (!this.a.isAdLoaded() || this.a.g().g()) {
            this.e = this.a.getAdChoicesText();
            if (TextUtils.isEmpty(this.e)) {
                this.e = "AdChoices";
            }
            g o = this.a.f().o();
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (AdChoicesView.this.c) {
                        AdChoicesView.this.a.f().y();
                    } else {
                        AdChoicesView.c(AdChoicesView.this);
                    }
                    return true;
                }
            });
            this.d = new TextView(getContext());
            addView(this.d);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            if (!z || o == null) {
                z2 = true;
            } else {
                ImageView imageView = new ImageView(getContext());
                addView(imageView);
                LayoutParams layoutParams3 = new LayoutParams(Math.round(((float) o.b()) * this.b), Math.round(((float) o.c()) * this.b));
                layoutParams3.addRule(9);
                layoutParams3.addRule(15, -1);
                layoutParams3.setMargins(Math.round(this.b * 4.0f), Math.round(this.b * 2.0f), Math.round(this.b * 2.0f), Math.round(this.b * 2.0f));
                imageView.setLayoutParams(layoutParams3);
                e.a(o, imageView);
                layoutParams2.addRule(11, imageView.getId());
                layoutParams2.width = 0;
                layoutParams.width = Math.round(((float) (o.b() + 4)) * this.b);
                layoutParams.height = Math.round(((float) (o.c() + 2)) * this.b);
            }
            this.c = z2;
            setLayoutParams(layoutParams);
            layoutParams2.addRule(15, -1);
            this.d.setLayoutParams(layoutParams2);
            this.d.setSingleLine();
            this.d.setText(this.e);
            this.d.setTextSize(10.0f);
            this.d.setTextColor(-4341303);
            j.a(this, j.INTERNAL_AD_CHOICES_ICON);
            j.a(this.d, j.INTERNAL_AD_CHOICES_ICON);
            return;
        }
        setVisibility(8);
    }

    static /* synthetic */ void c(AdChoicesView adChoicesView) {
        Paint paint = new Paint();
        paint.setTextSize(adChoicesView.d.getTextSize());
        int round = Math.round(paint.measureText(adChoicesView.e) + (adChoicesView.b * 4.0f));
        final int width = adChoicesView.getWidth();
        final int i = round + width;
        adChoicesView.c = true;
        AnonymousClass2 r3 = new Animation() {
            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                int i = width;
                int i2 = (int) (((float) i) + (((float) (i - i)) * f));
                AdChoicesView.this.getLayoutParams().width = i2;
                AdChoicesView.this.requestLayout();
                AdChoicesView.this.d.getLayoutParams().width = i2 - width;
                AdChoicesView.this.d.requestLayout();
            }

            public boolean willChangeBounds() {
                return true;
            }
        };
        r3.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (AdChoicesView.this.c) {
                            AdChoicesView.e(AdChoicesView.this);
                        }
                    }
                }, 3000);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        r3.setDuration(300);
        r3.setFillAfter(true);
        adChoicesView.startAnimation(r3);
    }

    static /* synthetic */ void e(AdChoicesView adChoicesView) {
        Paint paint = new Paint();
        paint.setTextSize(adChoicesView.d.getTextSize());
        int round = Math.round(paint.measureText(adChoicesView.e) + (adChoicesView.b * 4.0f));
        final int width = adChoicesView.getWidth();
        final int i = width - round;
        AnonymousClass4 r2 = new Animation() {
            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                int i = width;
                int i2 = (int) (((float) i) + (((float) (i - i)) * f));
                AdChoicesView.this.getLayoutParams().width = i2;
                AdChoicesView.this.requestLayout();
                AdChoicesView.this.d.getLayoutParams().width = i2 - i;
                AdChoicesView.this.d.requestLayout();
            }

            public boolean willChangeBounds() {
                return true;
            }
        };
        r2.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                AdChoicesView.this.c = false;
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        r2.setDuration(300);
        r2.setFillAfter(true);
        adChoicesView.startAnimation(r2);
    }
}
