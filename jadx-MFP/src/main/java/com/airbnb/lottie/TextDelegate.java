package com.airbnb.lottie;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

public class TextDelegate {
    @Nullable
    private final LottieAnimationView animationView = null;
    private boolean cacheText = true;
    @Nullable
    private final LottieDrawable drawable = null;
    private final Map<String, String> stringMap = new HashMap();

    private String getText(String str) {
        return str;
    }

    @VisibleForTesting
    TextDelegate() {
    }

    public final String getTextInternal(String str) {
        if (this.cacheText && this.stringMap.containsKey(str)) {
            return (String) this.stringMap.get(str);
        }
        String text = getText(str);
        if (this.cacheText) {
            this.stringMap.put(str, text);
        }
        return text;
    }
}
