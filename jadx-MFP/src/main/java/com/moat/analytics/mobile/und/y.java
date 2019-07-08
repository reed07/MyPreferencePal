package com.moat.analytics.mobile.und;

import android.app.Activity;
import android.graphics.Rect;
import android.location.Location;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class y {
    String a = "{}";
    private a b = new a();
    private JSONObject c;
    private Rect d;
    private Rect e;
    private JSONObject f;
    private JSONObject g;
    private Location h;
    private Map<String, Object> i = new HashMap();

    private static class a {
        Rect a = new Rect(0, 0, 0, 0);
        double b = 0.0d;
        double c = 0.0d;

        a() {
        }
    }

    y() {
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
            public int compare(Rect rect, Rect rect2) {
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
        return view != null ? h(view) : new Rect(0, 0, 0, 0);
    }

    private static a a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        a aVar = new a();
        int b2 = b(rect);
        if (view != null && z && z2 && !z3 && b2 > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (view.getGlobalVisibleRect(rect2)) {
                int b3 = b(rect2);
                if (b3 < b2) {
                    p.b(2, "VisibilityInfo", null, "Ad is clipped");
                }
                HashSet hashSet = new HashSet();
                if (view.getRootView() instanceof ViewGroup) {
                    aVar.a = rect2;
                    boolean a2 = a(rect2, view, hashSet);
                    if (a2) {
                        aVar.c = 1.0d;
                    }
                    if (!a2) {
                        int a3 = a(rect2, (Set<Rect>) hashSet);
                        if (a3 > 0) {
                            aVar.c = ((double) a3) / (((double) b3) * 1.0d);
                        }
                        aVar.b = ((double) (b3 - a3)) / (((double) b2) * 1.0d);
                    }
                }
            }
        }
        return aVar;
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

    private static boolean a(Rect rect, View view, Set<Rect> set) {
        View rootView = view.getRootView();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(rootView);
        p.b(2, "VisibilityInfo", view, "starting covering rect search");
        int i2 = 0;
        boolean z = false;
        while (!arrayDeque.isEmpty() && i2 < 250) {
            i2++;
            View view2 = (View) arrayDeque.pollLast();
            if (view2.equals(view)) {
                p.b(2, "VisibilityInfo", rect, "found target");
                z = true;
            } else if (g(view2)) {
                if ((view2 instanceof ViewGroup) && !(view2 instanceof ListView)) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        arrayDeque.add(viewGroup.getChildAt(childCount));
                    }
                }
                if (VERSION.SDK_INT < 21 ? z : !(view2.getElevation() <= view.getElevation() && (!z || view2.getElevation() != view.getElevation()))) {
                    Rect h2 = h(view2);
                    if (h2.setIntersect(rect, h2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Covered by ");
                        sb.append(view2.getClass().getSimpleName());
                        sb.append("-");
                        sb.append(h2.toString());
                        p.b(2, "VisibilityInfo", view2, sb.toString());
                        set.add(h2);
                        if (h2.contains(rect)) {
                            return true;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return false;
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

    private static boolean b(View view) {
        boolean z = true;
        if (VERSION.SDK_INT >= 19) {
            if (view == null || !view.isAttachedToWindow()) {
                z = false;
            }
            return z;
        }
        if (view == null || view.getWindowToken() == null) {
            z = false;
        }
        return z;
    }

    private static boolean c(View view) {
        return view != null && view.hasWindowFocus();
    }

    private static boolean d(View view) {
        return view == null || !view.isShown();
    }

    private static float e(View view) {
        return view == null ? BitmapDescriptorFactory.HUE_RED : f(view);
    }

    private static float f(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && ((double) alpha) != 0.0d && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    private static boolean g(View view) {
        return view.isShown() && ((double) view.getAlpha()) > 0.0d;
    }

    private static Rect h(View view) {
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new Rect(i2, i3, view.getWidth() + i2, view.getHeight() + i3);
    }

    private static DisplayMetrics i(View view) {
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
                DisplayMetrics i2 = i(view);
                boolean b2 = b(view);
                boolean c2 = c(view);
                boolean d2 = d(view);
                float e2 = e(view);
                hashMap.put("dr", Float.valueOf(i2.density));
                hashMap.put("dv", Double.valueOf(s.a()));
                hashMap.put("adKey", str);
                boolean z = false;
                hashMap.put("isAttached", Integer.valueOf(b2 ? 1 : 0));
                hashMap.put("inFocus", Integer.valueOf(c2 ? 1 : 0));
                hashMap.put("isHidden", Integer.valueOf(d2 ? 1 : 0));
                hashMap.put("opacity", Float.valueOf(e2));
                Rect a2 = a(i2);
                Rect a3 = a(view);
                a a4 = a(view, a3, b2, c2, d2);
                if (this.c == null || a4.b != this.b.b || !a4.a.equals(this.b.a) || a4.c != this.b.c) {
                    this.b = a4;
                    this.c = new JSONObject(a(this.b.a, i2));
                    z = true;
                }
                hashMap.put("coveredPercent", Double.valueOf(a4.c));
                if (this.g == null || !a2.equals(this.e)) {
                    this.e = a2;
                    this.g = new JSONObject(a(a2, i2));
                    z = true;
                }
                if (this.f == null || !a3.equals(this.d)) {
                    this.d = a3;
                    this.f = new JSONObject(a(a3, i2));
                    z = true;
                }
                if (this.i == null || !hashMap.equals(this.i)) {
                    this.i = hashMap;
                    z = true;
                }
                Location b3 = o.a().b();
                if (!o.a(b3, this.h)) {
                    this.h = b3;
                    z = true;
                }
                if (z) {
                    JSONObject jSONObject = new JSONObject(this.i);
                    jSONObject.accumulate("screen", this.g);
                    jSONObject.accumulate(Promotion.ACTION_VIEW, this.f);
                    jSONObject.accumulate(ProfileVisibility.VISIBLE, this.c);
                    jSONObject.accumulate("maybe", this.c);
                    jSONObject.accumulate("visiblePercent", Double.valueOf(this.b.b));
                    if (b3 != null) {
                        jSONObject.accumulate("location", a(b3));
                    }
                    this.a = jSONObject.toString();
                    return;
                }
                String str3 = this.a;
            } catch (Exception e3) {
                m.a(e3);
                this.a = str2;
            }
        }
    }
}
