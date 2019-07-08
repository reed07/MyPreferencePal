package com.facebook.ads.internal.v.b.a;

import android.text.TextUtils;
import com.facebook.ads.internal.v.b.m;

public class f implements c {
    public String a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        String substring = (lastIndexOf == -1 || lastIndexOf <= str.lastIndexOf(47) || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf + 1, str.length());
        String c = m.c(str);
        if (TextUtils.isEmpty(substring)) {
            return c;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(".");
        sb.append(substring);
        return sb.toString();
    }
}
