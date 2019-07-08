package com.inmobi.ads;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* compiled from: AnimationManager */
public class n {
    private static final String c = "n";
    List<a> a = new ArrayList();
    boolean b = false;

    /* compiled from: AnimationManager */
    class a {
        Animator a;
        long b;
        boolean c;

        a(Animator animator) {
            this.a = animator;
        }
    }

    /* access modifiers changed from: 0000 */
    public final List<a> a(final View view, ak akVar) {
        LinkedList linkedList = new LinkedList();
        try {
            float c2 = (float) NativeViewFactory.c(akVar.c.c.x);
            float c3 = (float) NativeViewFactory.c(akVar.c.d.x);
            if (c2 != c3) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) ((int) c2), (float) ((int) c3)});
                final com.inmobi.ads.NativeContainerLayout.a aVar = (com.inmobi.ads.NativeContainerLayout.a) view.getLayoutParams();
                ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        aVar.a = ((Float) valueAnimator.getAnimatedValue()).intValue();
                        view.setLayoutParams(aVar);
                        view.requestLayout();
                    }
                });
                linkedList.add(a((Animator) ofFloat, akVar));
            }
            float c4 = (float) NativeViewFactory.c(akVar.c.c.y);
            float c5 = (float) NativeViewFactory.c(akVar.c.d.y);
            if (c4 != c5) {
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(float) ((int) c4), (float) ((int) c5)});
                final com.inmobi.ads.NativeContainerLayout.a aVar2 = (com.inmobi.ads.NativeContainerLayout.a) view.getLayoutParams();
                ofFloat2.addUpdateListener(new AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        aVar2.b = ((Float) valueAnimator.getAnimatedValue()).intValue();
                        view.setLayoutParams(aVar2);
                        view.requestLayout();
                    }
                });
                linkedList.add(a((Animator) ofFloat2, akVar));
            }
            float c6 = (float) NativeViewFactory.c(akVar.c.a.x);
            float c7 = (float) NativeViewFactory.c(akVar.c.b.x);
            if (c6 != c7) {
                linkedList.add(a(a(view, "scaleX", c6, c7), akVar));
            }
            float c8 = (float) NativeViewFactory.c(akVar.c.a.y);
            float c9 = (float) NativeViewFactory.c(akVar.c.b.y);
            if (c8 != c9) {
                linkedList.add(a(a(view, "scaleY", c8, c9), akVar));
            }
        } catch (Exception unused) {
        }
        if (linkedList.isEmpty()) {
            return null;
        }
        return linkedList;
    }

    private static Animator a(View view, String str, float f, float f2) {
        float f3 = f2 / f;
        view.setPivotX(BitmapDescriptorFactory.HUE_RED);
        view.setPivotY(BitmapDescriptorFactory.HUE_RED);
        return ObjectAnimator.ofFloat(view, str, new float[]{f3});
    }

    /* access modifiers changed from: 0000 */
    public final void a(@Nullable List<a> list) {
        if (list != null) {
            for (a aVar : list) {
                if (!aVar.c) {
                    ValueAnimator valueAnimator = (ValueAnimator) aVar.a;
                    valueAnimator.setCurrentPlayTime(aVar.b);
                    valueAnimator.start();
                }
                if (!this.a.contains(aVar)) {
                    this.a.add(aVar);
                }
            }
        }
    }

    private a a(Animator animator, ak akVar) {
        animator.setDuration(0);
        animator.setStartDelay(0);
        ba g = akVar.c.g();
        if (g != null) {
            com.inmobi.ads.ba.a aVar = g.a;
            com.inmobi.ads.ba.a aVar2 = g.b;
            if (aVar2 != null) {
                animator.setDuration(aVar2.a() * 1000);
            }
            if (aVar != null) {
                animator.setStartDelay(aVar.a() * 1000);
            }
        }
        return new a(animator);
    }
}
