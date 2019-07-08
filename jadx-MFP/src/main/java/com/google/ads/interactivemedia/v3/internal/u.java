package com.google.ads.interactivemedia.v3.internal;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: IMASDK */
public final class u implements q {
    private final int[] a = new int[2];

    public final JSONObject a(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.a);
        int[] iArr = this.a;
        return z.a(iArr[0], iArr[1], width, height);
    }

    public final void a(View view, JSONObject jSONObject, r rVar, boolean z) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!z || VERSION.SDK_INT < 21) {
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    rVar.a(viewGroup.getChildAt(i), this, jSONObject);
                }
                return;
            }
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                ArrayList arrayList = (ArrayList) hashMap.get(Float.valueOf(childAt.getZ()));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    hashMap.put(Float.valueOf(childAt.getZ()), arrayList);
                }
                arrayList.add(childAt);
            }
            ArrayList arrayList2 = new ArrayList(hashMap.keySet());
            Collections.sort(arrayList2);
            ArrayList arrayList3 = arrayList2;
            int size = arrayList3.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList3.get(i3);
                i3++;
                ArrayList arrayList4 = (ArrayList) hashMap.get((Float) obj);
                int size2 = arrayList4.size();
                int i4 = 0;
                while (i4 < size2) {
                    Object obj2 = arrayList4.get(i4);
                    i4++;
                    rVar.a((View) obj2, this, jSONObject);
                }
            }
        }
    }
}
