package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.graphics.Rect;
import android.location.Location;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant.ProfileVisibility;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class z {
    String a = "{}";
    private c b = new c();
    private JSONObject c;
    private Rect d;
    private Rect e;
    private JSONObject f;
    private JSONObject g;
    private Location h;
    private Map<String, Object> i = new HashMap();

    static class a {
        int a = 0;
        final Set<Rect> b = new HashSet();
        boolean c = false;

        a() {
        }
    }

    private static class b {
        final View a;
        final Rect b;

        b(View view, b bVar) {
            this.a = view;
            this.b = bVar != null ? z.b(view, bVar.b.left, bVar.b.top) : z.k(view);
        }
    }

    private static class c {
        Rect a = new Rect(0, 0, 0, 0);
        double b = 0.0d;
        double c = 0.0d;

        c() {
        }
    }

    z() {
    }

    @VisibleForTesting
    static int a(Rect rect, Set<Rect> set) {
        int i2 = 0;
        if (set.isEmpty()) {
            return 0;
        }
        ArrayList<Rect> arrayList = new ArrayList<>();
        arrayList.addAll(set);
        Collections.sort(arrayList, new Comparator<Rect>() {
            /* renamed from: a */
            public final int compare(Rect rect, Rect rect2) {
                return Integer.valueOf(rect.top).compareTo(Integer.valueOf(rect2.top));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Rect rect2 : arrayList) {
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int i3 = 0;
        while (i2 < arrayList2.size() - 1) {
            int i4 = i2 + 1;
            if (!((Integer) arrayList2.get(i2)).equals(arrayList2.get(i4))) {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i2)).intValue(), rect.top, ((Integer) arrayList2.get(i4)).intValue(), rect.bottom);
                int i5 = rect.top;
                for (Rect rect4 : arrayList) {
                    if (Rect.intersects(rect4, rect3)) {
                        if (rect4.bottom > i5) {
                            i3 += rect3.width() * (rect4.bottom - Math.max(i5, rect4.top));
                            i5 = rect4.bottom;
                        }
                        if (rect4.bottom == rect3.bottom) {
                            break;
                        }
                    }
                }
            }
            i2 = i4;
        }
        return i3;
    }

    private static Rect a(DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    static Rect a(View view) {
        return view != null ? k(view) : new Rect(0, 0, 0, 0);
    }

    @VisibleForTesting
    static a a(Rect rect, @NonNull View view) {
        a aVar = new a();
        try {
            ArrayDeque i2 = i(view);
            if (i2 != null) {
                if (!i2.isEmpty()) {
                    p.b(2, "VisibilityInfo", view, "starting covering rect search");
                    b bVar = null;
                    loop0:
                    while (true) {
                        if (i2.isEmpty()) {
                            break;
                        }
                        View view2 = (View) i2.pollLast();
                        b bVar2 = new b(view2, bVar);
                        if (view2.getParent() != null && (view2.getParent() instanceof ViewGroup)) {
                            ViewGroup viewGroup = (ViewGroup) view2.getParent();
                            int childCount = viewGroup.getChildCount();
                            boolean z = false;
                            for (int i3 = 0; i3 < childCount; i3++) {
                                if (aVar.a >= 500) {
                                    p.a(3, "VisibilityInfo", (Object) null, "Short-circuiting cover retrieval, reached max");
                                    break loop0;
                                }
                                View childAt = viewGroup.getChildAt(i3);
                                if (childAt == view2) {
                                    z = true;
                                } else {
                                    aVar.a++;
                                    if (a(childAt, view2, z)) {
                                        b(new b(childAt, bVar), rect, aVar);
                                        if (aVar.c) {
                                            return aVar;
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            continue;
                        }
                        bVar = bVar2;
                    }
                    return aVar;
                }
            }
            return aVar;
        } catch (Exception e2) {
            m.a(e2);
        }
    }

    private static c a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        c cVar = new c();
        int b2 = b(rect);
        if (view != null && z && z2 && !z3 && b2 > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (a(view, rect2)) {
                int b3 = b(rect2);
                if (b3 < b2) {
                    p.b(2, "VisibilityInfo", null, "Ad is clipped");
                }
                if (view.getRootView() instanceof ViewGroup) {
                    cVar.a = rect2;
                    a a2 = a(rect2, view);
                    if (a2.c) {
                        cVar.c = 1.0d;
                    } else {
                        int a3 = a(rect2, a2.b);
                        if (a3 > 0) {
                            cVar.c = ((double) a3) / (((double) b3) * 1.0d);
                        }
                        cVar.b = ((double) (b3 - a3)) / (((double) b2) * 1.0d);
                    }
                }
            }
        }
        return cVar;
    }

    private static Map<String, String> a(Rect rect) {
        HashMap hashMap = new HashMap();
        hashMap.put(AvidJSONUtil.KEY_X, String.valueOf(rect.left));
        hashMap.put("y", String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    private static Map<String, String> a(Rect rect, DisplayMetrics displayMetrics) {
        return a(b(rect, displayMetrics));
    }

    private static JSONObject a(Location location) {
        Map b2 = b(location);
        if (b2 == null) {
            return null;
        }
        return new JSONObject(b2);
    }

    private static void a(b bVar, Rect rect, a aVar) {
        Rect rect2 = bVar.b;
        if (rect2.setIntersect(rect, rect2)) {
            if (VERSION.SDK_INT >= 22) {
                Rect rect3 = new Rect(0, 0, 0, 0);
                if (a(bVar.a, rect3)) {
                    Rect rect4 = bVar.b;
                    if (rect4.setIntersect(rect3, rect4)) {
                        rect2 = rect4;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            if (w.a().c) {
                p.b(2, "VisibilityInfo", bVar.a, String.format(Locale.ROOT, "Covered by %s-%s alpha=%f", new Object[]{bVar.a.getClass().getName(), rect2.toString(), Float.valueOf(bVar.a.getAlpha())}));
            }
            aVar.b.add(rect2);
            if (rect2.contains(rect)) {
                aVar.c = true;
            }
        }
    }

    private static boolean a(View view, Rect rect) {
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int[] iArr2 = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr2);
        rect.offset(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
        return true;
    }

    private static boolean a(View view, View view2, boolean z) {
        return z ? VERSION.SDK_INT < 21 || view.getZ() >= view2.getZ() : VERSION.SDK_INT >= 21 && view.getZ() > view2.getZ();
    }

    private static int b(Rect rect) {
        return rect.width() * rect.height();
    }

    private static Rect b(Rect rect, DisplayMetrics displayMetrics) {
        float f2 = displayMetrics.density;
        if (f2 == BitmapDescriptorFactory.HUE_RED) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f2), Math.round(((float) rect.top) / f2), Math.round(((float) rect.right) / f2), Math.round(((float) rect.bottom) / f2));
    }

    /* access modifiers changed from: private */
    public static Rect b(View view, int i2, int i3) {
        int left = i2 + view.getLeft();
        int top = i3 + view.getTop();
        return new Rect(left, top, view.getWidth() + left, view.getHeight() + top);
    }

    private static Map<String, String> b(Location location) {
        if (location == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("latitude", Double.toString(location.getLatitude()));
        hashMap.put("longitude", Double.toString(location.getLongitude()));
        hashMap.put("timestamp", Long.toString(location.getTime()));
        hashMap.put("horizontalAccuracy", Float.toString(location.getAccuracy()));
        return hashMap;
    }

    private static void b(b bVar, Rect rect, a aVar) {
        boolean z;
        if (h(bVar.a)) {
            if (bVar.a instanceof ViewGroup) {
                int i2 = 0;
                z = !ViewGroup.class.equals(bVar.a.getClass().getSuperclass()) || !j(bVar.a);
                ViewGroup viewGroup = (ViewGroup) bVar.a;
                int childCount = viewGroup.getChildCount();
                while (i2 < childCount) {
                    int i3 = aVar.a + 1;
                    aVar.a = i3;
                    if (i3 <= 500) {
                        b(new b(viewGroup.getChildAt(i2), bVar), rect, aVar);
                        if (!aVar.c) {
                            i2++;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                a(bVar, rect, aVar);
            }
        }
    }

    private static boolean c(View view) {
        return VERSION.SDK_INT >= 19 ? view != null && view.isAttachedToWindow() : (view == null || view.getWindowToken() == null) ? false : true;
    }

    private static boolean d(View view) {
        return view != null && view.hasWindowFocus();
    }

    private static boolean e(View view) {
        return view == null || !view.isShown();
    }

    private static float f(View view) {
        return view == null ? BitmapDescriptorFactory.HUE_RED : g(view);
    }

    private static float g(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && ((double) alpha) != 0.0d && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    private static boolean h(View view) {
        return view.isShown() && ((double) view.getAlpha()) > 0.0d;
    }

    private static ArrayDeque<View> i(@NonNull View view) {
        ArrayDeque<View> arrayDeque = new ArrayDeque<>();
        int i2 = 0;
        View view2 = view;
        while (true) {
            if (view2.getParent() == null && view2 != view.getRootView()) {
                break;
            }
            i2++;
            if (i2 <= 50) {
                arrayDeque.add(view2);
                if (!(view2.getParent() instanceof View)) {
                    break;
                }
                view2 = (View) view2.getParent();
            } else {
                p.a(3, "VisibilityInfo", (Object) null, "Short-circuiting chain retrieval, reached max");
                break;
            }
        }
        return arrayDeque;
    }

    private static boolean j(View view) {
        return VERSION.SDK_INT < 19 || view.getBackground() == null || view.getBackground().getAlpha() == 0;
    }

    /* access modifiers changed from: private */
    public static Rect k(View view) {
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new Rect(i2, i3, view.getWidth() + i2, view.getHeight() + i3);
    }

    private static DisplayMetrics l(View view) {
        if (VERSION.SDK_INT >= 17 && a.a != null) {
            Activity activity = (Activity) a.a.get();
            if (activity != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                return displayMetrics;
            }
        }
        return view.getContext().getResources().getDisplayMetrics();
    }

    /* access modifiers changed from: 0000 */
    public void a(String str, View view) {
        HashMap hashMap = new HashMap();
        String str2 = "{}";
        if (view != null) {
            try {
                DisplayMetrics l = l(view);
                boolean c2 = c(view);
                boolean d2 = d(view);
                boolean e2 = e(view);
                float f2 = f(view);
                hashMap.put("dr", Float.valueOf(l.density));
                hashMap.put("dv", Double.valueOf(s.a()));
                hashMap.put("adKey", str);
                boolean z = false;
                hashMap.put("isAttached", Integer.valueOf(c2 ? 1 : 0));
                hashMap.put("inFocus", Integer.valueOf(d2 ? 1 : 0));
                hashMap.put("isHidden", Integer.valueOf(e2 ? 1 : 0));
                hashMap.put("opacity", Float.valueOf(f2));
                Rect a2 = a(l);
                Rect a3 = a(view);
                c a4 = a(view, a3, c2, d2, e2);
                if (this.c == null || a4.b != this.b.b || !a4.a.equals(this.b.a) || a4.c != this.b.c) {
                    this.b = a4;
                    this.c = new JSONObject(a(this.b.a, l));
                    z = true;
                }
                hashMap.put("coveredPercent", Double.valueOf(a4.c));
                if (this.g == null || !a2.equals(this.e)) {
                    this.e = a2;
                    this.g = new JSONObject(a(a2, l));
                    z = true;
                }
                if (this.f == null || !a3.equals(this.d)) {
                    this.d = a3;
                    this.f = new JSONObject(a(a3, l));
                    z = true;
                }
                if (this.i == null || !hashMap.equals(this.i)) {
                    this.i = hashMap;
                    z = true;
                }
                Location b2 = o.a().b();
                if (!o.a(b2, this.h)) {
                    this.h = b2;
                    z = true;
                }
                if (z) {
                    JSONObject jSONObject = new JSONObject(this.i);
                    jSONObject.accumulate("screen", this.g);
                    jSONObject.accumulate(Promotion.ACTION_VIEW, this.f);
                    jSONObject.accumulate(ProfileVisibility.VISIBLE, this.c);
                    jSONObject.accumulate("maybe", this.c);
                    jSONObject.accumulate("visiblePercent", Double.valueOf(this.b.b));
                    if (b2 != null) {
                        jSONObject.accumulate("location", a(b2));
                    }
                    this.a = jSONObject.toString();
                }
            } catch (Exception e3) {
                m.a(e3);
                this.a = str2;
            }
        }
    }
}
