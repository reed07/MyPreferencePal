package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.Arrays;
import java.util.List;

public class ShapeGroup implements ContentModel {
    private final List<ContentModel> items;
    private final String name;

    public ShapeGroup(String str, List<ContentModel> list) {
        this.name = str;
        this.items = list;
    }

    public String getName() {
        return this.name;
    }

    public List<ContentModel> getItems() {
        return this.items;
    }

    public Content toContent(LottieDrawable lottieDrawable, BaseLayer baseLayer) {
        return new ContentGroup(lottieDrawable, baseLayer, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShapeGroup{name='");
        sb.append(this.name);
        sb.append("' Shapes: ");
        sb.append(Arrays.toString(this.items.toArray()));
        sb.append('}');
        return sb.toString();
    }
}
