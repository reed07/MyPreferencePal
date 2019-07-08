package com.facebook.ads.internal.x;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

class d {
    @Nullable
    @VisibleForTesting
    static Float a(View view, View view2) {
        float f;
        if (view == null || view2 == null) {
            return null;
        }
        if (view2.getVisibility() != 0) {
            return Float.valueOf(BitmapDescriptorFactory.HUE_RED);
        }
        LinkedList<View> linkedList = new LinkedList<>();
        Stack stack = new Stack();
        stack.push(view);
        int i = 0;
        boolean z = false;
        while (!stack.empty()) {
            View view3 = (View) stack.pop();
            if (view3.getVisibility() == 0) {
                if (view3 == view2) {
                    z = true;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        stack.push(viewGroup.getChildAt(childCount));
                    }
                } else if (z || (VERSION.SDK_INT >= 21 && view3.getZ() > view2.getZ())) {
                    linkedList.add(view3);
                }
            }
        }
        if (linkedList.isEmpty()) {
            f = 1.0f;
        } else {
            Rect rect = new Rect();
            if (!view2.getGlobalVisibleRect(rect)) {
                return Float.valueOf(BitmapDescriptorFactory.HUE_RED);
            }
            int width = rect.width() * rect.height();
            HashSet<Rect> hashSet = new HashSet<>();
            hashSet.add(rect);
            for (View view4 : linkedList) {
                Rect rect2 = new Rect();
                if (view4.getGlobalVisibleRect(rect2)) {
                    HashSet hashSet2 = new HashSet();
                    for (Rect a : hashSet) {
                        hashSet2.addAll(a(a, rect2));
                    }
                    hashSet = hashSet2;
                }
            }
            for (Rect rect3 : hashSet) {
                i += rect3.width() * rect3.height();
            }
            f = ((float) i) / ((float) width);
        }
        return Float.valueOf(f);
    }

    @VisibleForTesting
    static Set<Rect> a(Rect rect, Rect rect2) {
        HashSet<Rect> hashSet = new HashSet<>();
        if (!rect2.intersect(rect)) {
            hashSet.add(rect);
            return hashSet;
        }
        hashSet.add(new Rect(rect.left, rect.top, rect2.left, rect.bottom));
        hashSet.add(new Rect(rect2.left, rect.top, rect2.right, rect2.top));
        hashSet.add(new Rect(rect2.right, rect.top, rect.right, rect.bottom));
        hashSet.add(new Rect(rect2.left, rect2.bottom, rect2.right, rect.bottom));
        HashSet hashSet2 = new HashSet();
        for (Rect rect3 : hashSet) {
            if (rect3.width() > 0 && rect3.height() > 0) {
                hashSet2.add(rect3);
            }
        }
        return hashSet2;
    }
}
