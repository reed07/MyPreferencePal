package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.f.c;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;

public class j extends LinearLayout {
    private static final int a = ((int) (x.b * 40.0f));
    private static final int b = ((int) (x.b * 20.0f));
    private static final int c = ((int) (x.b * 10.0f));
    private final c d;
    /* access modifiers changed from: private */
    public final e e;

    j(Context context, c cVar, e eVar, b bVar) {
        this(context, cVar, eVar, null, bVar);
    }

    j(Context context, c cVar, e eVar, @Nullable String str, b bVar) {
        super(context);
        this.d = cVar;
        this.e = eVar;
        setOrientation(1);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        if (!TextUtils.isEmpty(str)) {
            ImageView imageView = new ImageView(getContext());
            imageView.setColorFilter(-10459280);
            imageView.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b.BACK_ARROW));
            int i = c;
            imageView.setPadding(0, i, i * 2, i);
            int i2 = a;
            LayoutParams layoutParams2 = new LayoutParams(i2, i2);
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    j.this.e.a();
                }
            });
            TextView textView = new TextView(getContext());
            textView.setGravity(17);
            textView.setText(str);
            x.a(textView, true, 16);
            textView.setTextColor(-14934495);
            LayoutParams layoutParams3 = new LayoutParams(-1, -2);
            layoutParams3.setMargins(0, 0, a, 0);
            layoutParams3.gravity = 17;
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            linearLayout.addView(imageView, layoutParams2);
            linearLayout.addView(textView, layoutParams3);
            linearLayout.setPadding(0, 0, 0, 0);
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(-1, 1));
            x.a(view, -10459280);
            addView(linearLayout, layoutParams);
            addView(view);
        }
        if (!TextUtils.isEmpty(this.d.c())) {
            String c2 = this.d.c();
            ImageView imageView2 = new ImageView(getContext());
            imageView2.setColorFilter(-10459280);
            int i3 = b;
            LayoutParams layoutParams4 = new LayoutParams(i3, i3);
            layoutParams4.gravity = 16;
            imageView2.setImageBitmap(com.facebook.ads.internal.w.c.c.a(bVar));
            TextView textView2 = new TextView(getContext());
            x.a(textView2, true, 14);
            textView2.setTextColor(-10459280);
            LayoutParams layoutParams5 = new LayoutParams(-1, -2);
            textView2.setText(c2);
            textView2.setPadding(c, 0, 0, 0);
            LinearLayout linearLayout2 = new LinearLayout(getContext());
            linearLayout2.setOrientation(0);
            linearLayout2.addView(imageView2, layoutParams4);
            linearLayout2.addView(textView2, layoutParams5);
            int i4 = c;
            linearLayout2.setPadding(0, i4, 0, i4);
            addView(linearLayout2, layoutParams);
        }
        View a2 = a();
        a2.setPadding(0, c, 0, 0);
        addView(a2, layoutParams);
    }

    private View a() {
        l lVar = new l(getContext());
        for (final c cVar : this.d.d()) {
            final f fVar = new f(getContext());
            fVar.a(cVar.b(), null);
            fVar.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fVar.a();
                    j.this.e.a(cVar);
                }
            });
            lVar.addView(fVar);
        }
        return lVar;
    }
}
