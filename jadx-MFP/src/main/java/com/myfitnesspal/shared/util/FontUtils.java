package com.myfitnesspal.shared.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public final class FontUtils {
    private static final Map<String, Typeface> typefaceCache = new HashMap();

    public static Typeface getTypeface(Context context, String str) {
        if (!Strings.notEmpty(str)) {
            return null;
        }
        Typeface typeface = (Typeface) typefaceCache.get(str);
        if (typeface != null) {
            return typeface;
        }
        try {
            AssetManager assets = context.getAssets();
            StringBuilder sb = new StringBuilder();
            sb.append("fonts/");
            sb.append(str);
            typeface = Typeface.createFromAsset(assets, sb.toString());
            typefaceCache.put(str, typeface);
            return typeface;
        } catch (RuntimeException e) {
            Ln.e(e);
            return typeface;
        }
    }
}
