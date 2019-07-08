package com.moat.analytics.mobile.inm;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.a.b.a;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

class ab {
    private static final LinkedHashSet<String> a = new LinkedHashSet<>();

    ab() {
    }

    @NonNull
    static a<WebView> a(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            try {
                return a.a();
            } catch (Exception unused) {
                return a.a();
            }
        } else if (viewGroup instanceof WebView) {
            return a.a((WebView) viewGroup);
        } else {
            LinkedList linkedList = new LinkedList();
            linkedList.add(viewGroup);
            Object obj = null;
            int i = 0;
            while (!linkedList.isEmpty() && i < 100) {
                i++;
                ViewGroup viewGroup2 = (ViewGroup) linkedList.poll();
                int childCount = viewGroup2.getChildCount();
                Object obj2 = obj;
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        obj = obj2;
                        break;
                    }
                    View childAt = viewGroup2.getChildAt(i2);
                    if (childAt instanceof WebView) {
                        p.a(3, "WebViewHound", (Object) childAt, "Found WebView");
                        if (z || a(String.valueOf(childAt.hashCode()))) {
                            if (obj2 != null) {
                                p.a(3, "WebViewHound", (Object) childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                p.a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                                obj = null;
                                break;
                            }
                            obj2 = (WebView) childAt;
                        }
                    }
                    if (childAt instanceof ViewGroup) {
                        linkedList.add((ViewGroup) childAt);
                    }
                    i2++;
                }
            }
            return a.b(obj);
        }
    }

    private static boolean a(String str) {
        try {
            boolean add = a.add(str);
            if (a.size() > 50) {
                Iterator it = a.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            p.a(3, "WebViewHound", (Object) null, add ? "Newly Found WebView" : "Already Found WebView");
            return add;
        } catch (Exception e) {
            m.a(e);
            return false;
        }
    }
}
