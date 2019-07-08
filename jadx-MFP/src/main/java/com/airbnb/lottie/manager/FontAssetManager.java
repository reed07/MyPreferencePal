package com.airbnb.lottie.manager;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.airbnb.lottie.FontAssetDelegate;
import com.airbnb.lottie.model.MutablePair;
import java.util.HashMap;
import java.util.Map;

public class FontAssetManager {
    private final AssetManager assetManager;
    private String defaultFontFileExtension = ".ttf";
    @Nullable
    private FontAssetDelegate delegate;
    private final Map<String, Typeface> fontFamilies = new HashMap();
    private final Map<MutablePair<String>, Typeface> fontMap = new HashMap();
    private final MutablePair<String> tempPair = new MutablePair<>();

    public FontAssetManager(Callback callback, @Nullable FontAssetDelegate fontAssetDelegate) {
        this.delegate = fontAssetDelegate;
        if (!(callback instanceof View)) {
            Log.w("LOTTIE", "LottieDrawable must be inside of a view for images to work.");
            this.assetManager = null;
            return;
        }
        this.assetManager = ((View) callback).getContext().getAssets();
    }

    public void setDelegate(@Nullable FontAssetDelegate fontAssetDelegate) {
        this.delegate = fontAssetDelegate;
    }

    public Typeface getTypeface(String str, String str2) {
        this.tempPair.set(str, str2);
        Typeface typeface = (Typeface) this.fontMap.get(this.tempPair);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceForStyle = typefaceForStyle(getFontFamily(str), str2);
        this.fontMap.put(this.tempPair, typefaceForStyle);
        return typefaceForStyle;
    }

    private Typeface getFontFamily(String str) {
        Typeface typeface = (Typeface) this.fontFamilies.get(str);
        if (typeface != null) {
            return typeface;
        }
        Typeface typeface2 = null;
        FontAssetDelegate fontAssetDelegate = this.delegate;
        if (fontAssetDelegate != null) {
            typeface2 = fontAssetDelegate.fetchFont(str);
        }
        FontAssetDelegate fontAssetDelegate2 = this.delegate;
        if (fontAssetDelegate2 != null && typeface2 == null) {
            String fontPath = fontAssetDelegate2.getFontPath(str);
            if (fontPath != null) {
                typeface2 = Typeface.createFromAsset(this.assetManager, fontPath);
            }
        }
        if (typeface2 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("fonts/");
            sb.append(str);
            sb.append(this.defaultFontFileExtension);
            typeface2 = Typeface.createFromAsset(this.assetManager, sb.toString());
        }
        this.fontFamilies.put(str, typeface2);
        return typeface2;
    }

    private Typeface typefaceForStyle(Typeface typeface, String str) {
        boolean contains = str.contains("Italic");
        boolean contains2 = str.contains("Bold");
        int i = (!contains || !contains2) ? contains ? 2 : contains2 ? 1 : 0 : 3;
        if (typeface.getStyle() == i) {
            return typeface;
        }
        return Typeface.create(typeface, i);
    }
}
