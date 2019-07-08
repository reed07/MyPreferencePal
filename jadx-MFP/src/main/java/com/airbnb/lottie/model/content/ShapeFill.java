package com.airbnb.lottie.model.content;

import android.graphics.Path.FillType;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.FillContent;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class ShapeFill implements ContentModel {
    @Nullable
    private final AnimatableColorValue color;
    private final boolean fillEnabled;
    private final FillType fillType;
    private final String name;
    @Nullable
    private final AnimatableIntegerValue opacity;

    public ShapeFill(String str, boolean z, FillType fillType2, @Nullable AnimatableColorValue animatableColorValue, @Nullable AnimatableIntegerValue animatableIntegerValue) {
        this.name = str;
        this.fillEnabled = z;
        this.fillType = fillType2;
        this.color = animatableColorValue;
        this.opacity = animatableIntegerValue;
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public AnimatableColorValue getColor() {
        return this.color;
    }

    @Nullable
    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public FillType getFillType() {
        return this.fillType;
    }

    public Content toContent(LottieDrawable lottieDrawable, BaseLayer baseLayer) {
        return new FillContent(lottieDrawable, baseLayer, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShapeFill{color=, fillEnabled=");
        sb.append(this.fillEnabled);
        sb.append('}');
        return sb.toString();
    }
}
