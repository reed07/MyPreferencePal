package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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
import com.facebook.ads.internal.view.i.a.c;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;
import com.facebook.ads.internal.w.e.g;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;

public class a extends c {
    private final C0020a a;

    /* renamed from: com.facebook.ads.internal.view.i.c.a$a reason: collision with other inner class name */
    public static class C0020a extends RelativeLayout {
        private final String a;
        /* access modifiers changed from: private */
        public final String b;
        /* access modifiers changed from: private */
        public final String c;
        private final DisplayMetrics d;
        private ImageView e;
        /* access modifiers changed from: private */
        public TextView f;
        /* access modifiers changed from: private */
        public boolean g = false;

        public C0020a(Context context, String str, String str2, float[] fArr, String str3) {
            super(context);
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = context.getResources().getDisplayMetrics();
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-16777216);
            gradientDrawable.setAlpha(RequestCodes.PROGRESS_PHOTOS_GALLERY);
            gradientDrawable.setCornerRadii(new float[]{fArr[0] * this.d.density, fArr[0] * this.d.density, fArr[1] * this.d.density, fArr[1] * this.d.density, fArr[2] * this.d.density, fArr[2] * this.d.density, fArr[3] * this.d.density, fArr[3] * this.d.density});
            x.a((View) this, (Drawable) gradientDrawable);
            setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (!C0020a.this.g) {
                        C0020a.d(C0020a.this);
                    } else if (!TextUtils.isEmpty(C0020a.this.b)) {
                        g.a(new g(), C0020a.this.getContext(), Uri.parse(C0020a.this.b), C0020a.this.c);
                    }
                    return true;
                }
            });
            this.e = new ImageView(getContext());
            this.e.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b.IC_AD_CHOICES));
            addView(this.e);
            LayoutParams layoutParams = new LayoutParams(Math.round(this.d.density * 16.0f), Math.round(this.d.density * 16.0f));
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            layoutParams.setMargins(Math.round(this.d.density * 4.0f), Math.round(this.d.density * 2.0f), Math.round(this.d.density * 2.0f), Math.round(this.d.density * 2.0f));
            this.e.setLayoutParams(layoutParams);
            this.f = new TextView(getContext());
            addView(this.f);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.width = 0;
            layoutParams2.leftMargin = (int) (this.d.density * 20.0f);
            layoutParams2.addRule(9);
            layoutParams2.addRule(15, -1);
            this.f.setLayoutParams(layoutParams2);
            this.f.setSingleLine();
            this.f.setText(this.a);
            this.f.setTextSize(10.0f);
            this.f.setTextColor(-4341303);
            setMinimumWidth(Math.round(this.d.density * 20.0f));
            setMinimumHeight(Math.round(this.d.density * 18.0f));
        }

        static /* synthetic */ void d(C0020a aVar) {
            Paint paint = new Paint();
            paint.setTextSize(aVar.f.getTextSize());
            int round = Math.round(paint.measureText(aVar.a) + (aVar.d.density * 4.0f));
            final int width = aVar.getWidth();
            final int i = round + width;
            aVar.g = true;
            AnonymousClass2 r3 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    int i = width;
                    int i2 = (int) (((float) i) + (((float) (i - i)) * f));
                    C0020a.this.getLayoutParams().width = i2;
                    C0020a.this.requestLayout();
                    C0020a.this.f.getLayoutParams().width = i2 - width;
                    C0020a.this.f.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            r3.setAnimationListener(new AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (C0020a.this.g) {
                                C0020a.f(C0020a.this);
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
            aVar.startAnimation(r3);
        }

        static /* synthetic */ void f(C0020a aVar) {
            Paint paint = new Paint();
            paint.setTextSize(aVar.f.getTextSize());
            int round = Math.round(paint.measureText(aVar.a) + (aVar.d.density * 4.0f));
            final int width = aVar.getWidth();
            final int i = width - round;
            AnonymousClass4 r2 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    int i = width;
                    int i2 = (int) (((float) i) + (((float) (i - i)) * f));
                    C0020a.this.getLayoutParams().width = i2;
                    C0020a.this.requestLayout();
                    C0020a.this.f.getLayoutParams().width = i2 - i;
                    C0020a.this.f.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            r2.setAnimationListener(new AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    C0020a.this.g = false;
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            r2.setDuration(300);
            r2.setFillAfter(true);
            aVar.startAnimation(r2);
        }
    }

    public a(Context context, String str, String str2, float[] fArr) {
        super(context);
        C0020a aVar = new C0020a(context, "AdChoices", str, fArr, str2);
        this.a = aVar;
        addView(this.a);
    }
}
