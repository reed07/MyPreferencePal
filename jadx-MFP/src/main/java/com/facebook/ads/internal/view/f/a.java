package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.w.b.x;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.lang.ref.WeakReference;

public class a extends RelativeLayout {
    public static final int a = ((int) (x.b * 72.0f));
    private static final int b = ((int) (x.b * 16.0f));
    private static final int c = ((int) (x.b * 16.0f));
    private static final LayoutParams d = new LayoutParams(-1, -1);
    private final o e;
    private f f = new f(getContext());
    private j g;
    private LinearLayout h;

    /* renamed from: com.facebook.ads.internal.view.f.a$a reason: collision with other inner class name */
    private static class C0018a implements e {
        final WeakReference<ImageView> a;

        private C0018a(ImageView imageView) {
            this.a = new WeakReference<>(imageView);
        }

        public void a(boolean z) {
            if (!z && this.a.get() != null) {
                ((ImageView) this.a.get()).setVisibility(8);
            }
        }
    }

    public a(Context context, o oVar) {
        super(context);
        this.e = oVar;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        x.a((View) this.f, 0);
        this.f.setRadius(50);
        new d((ImageView) this.f).a().a(this.e.b().b());
        int i = a;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        j jVar = new j(getContext(), this.e.e().a(), true, false, true);
        this.g = jVar;
        this.g.a(this.e.c().a(), this.e.c().b(), null, false, true);
        this.g.getDescriptionTextView().setAlpha(0.8f);
        this.g.setAlignment(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        int i2 = c;
        layoutParams2.setMargins(0, i2, 0, i2 / 2);
        this.h = new LinearLayout(getContext());
        this.h.setGravity(17);
        LinearLayout linearLayout2 = this.h;
        int i3 = c;
        linearLayout2.setPadding(i3, i3 / 2, i3, i3 / 2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(0, c / 2, 0, 0);
        n j = this.e.f().j();
        TextView textView = new TextView(getContext());
        textView.setTextColor(-1);
        x.a(textView, false, 16);
        textView.setText(j.d());
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        ImageView imageView = new ImageView(getContext());
        new d(imageView).a().a((e) new C0018a(imageView)).a(j.b());
        int i4 = b;
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(i4, i4);
        layoutParams5.setMargins(0, 0, c / 2, 0);
        this.h.addView(imageView, layoutParams5);
        this.h.addView(textView, layoutParams4);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(100.0f);
        gradientDrawable.setColor(469762047);
        x.a((View) this.h, (Drawable) gradientDrawable);
        linearLayout.addView(this.f, layoutParams);
        linearLayout.addView(this.g, layoutParams2);
        linearLayout.addView(this.h, layoutParams3);
        x.a((View) this, -14473425);
        addView(linearLayout, d);
        a(this.f, 150);
        a(this.g, RequestCodes.CHALLENGES);
        a(this.h, 190);
    }

    private void a(View view, int i) {
        view.setTranslationY((float) i);
        view.setScaleY(0.75f);
        view.setScaleX(0.75f);
        view.animate().translationYBy((float) (-i)).scaleX(1.0f).scaleY(1.0f).setDuration(200).setInterpolator(new DecelerateInterpolator(2.0f));
    }
}
