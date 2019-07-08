package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.RectangleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.List;

public class RectangleContent implements KeyPathElementContent, PathContent, AnimationListener {
    private final BaseKeyframeAnimation<?, Float> cornerRadiusAnimation;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path = new Path();
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final RectF rect = new RectF();
    private final BaseKeyframeAnimation<?, PointF> sizeAnimation;
    @Nullable
    private TrimPathContent trimPath;

    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
    }

    public RectangleContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, RectangleShape rectangleShape) {
        this.name = rectangleShape.getName();
        this.lottieDrawable = lottieDrawable2;
        this.positionAnimation = rectangleShape.getPosition().createAnimation();
        this.sizeAnimation = rectangleShape.getSize().createAnimation();
        this.cornerRadiusAnimation = rectangleShape.getCornerRadius().createAnimation();
        baseLayer.addAnimation(this.positionAnimation);
        baseLayer.addAnimation(this.sizeAnimation);
        baseLayer.addAnimation(this.cornerRadiusAnimation);
        this.positionAnimation.addUpdateListener(this);
        this.sizeAnimation.addUpdateListener(this);
        this.cornerRadiusAnimation.addUpdateListener(this);
    }

    public String getName() {
        return this.name;
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> list, List<Content> list2) {
        for (int i = 0; i < list.size(); i++) {
            Content content = (Content) list.get(i);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.getType() == Type.Simultaneously) {
                    this.trimPath = trimPathContent;
                    this.trimPath.addListener(this);
                }
            }
        }
    }

    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        PointF pointF = (PointF) this.sizeAnimation.getValue();
        float f = pointF.x / 2.0f;
        float f2 = pointF.y / 2.0f;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation = this.cornerRadiusAnimation;
        float floatValue = baseKeyframeAnimation == null ? BitmapDescriptorFactory.HUE_RED : ((Float) baseKeyframeAnimation.getValue()).floatValue();
        float min = Math.min(f, f2);
        if (floatValue > min) {
            floatValue = min;
        }
        PointF pointF2 = (PointF) this.positionAnimation.getValue();
        this.path.moveTo(pointF2.x + f, (pointF2.y - f2) + floatValue);
        this.path.lineTo(pointF2.x + f, (pointF2.y + f2) - floatValue);
        int i = (floatValue > BitmapDescriptorFactory.HUE_RED ? 1 : (floatValue == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
        if (i > 0) {
            float f3 = floatValue * 2.0f;
            this.rect.set((pointF2.x + f) - f3, (pointF2.y + f2) - f3, pointF2.x + f, pointF2.y + f2);
            this.path.arcTo(this.rect, BitmapDescriptorFactory.HUE_RED, 90.0f, false);
        }
        this.path.lineTo((pointF2.x - f) + floatValue, pointF2.y + f2);
        if (i > 0) {
            float f4 = floatValue * 2.0f;
            this.rect.set(pointF2.x - f, (pointF2.y + f2) - f4, (pointF2.x - f) + f4, pointF2.y + f2);
            this.path.arcTo(this.rect, 90.0f, 90.0f, false);
        }
        this.path.lineTo(pointF2.x - f, (pointF2.y - f2) + floatValue);
        if (i > 0) {
            float f5 = floatValue * 2.0f;
            this.rect.set(pointF2.x - f, pointF2.y - f2, (pointF2.x - f) + f5, (pointF2.y - f2) + f5);
            this.path.arcTo(this.rect, 180.0f, 90.0f, false);
        }
        this.path.lineTo((pointF2.x + f) - floatValue, pointF2.y - f2);
        if (i > 0) {
            float f6 = floatValue * 2.0f;
            this.rect.set((pointF2.x + f) - f6, pointF2.y - f2, pointF2.x + f, (pointF2.y - f2) + f6);
            this.path.arcTo(this.rect, 270.0f, 90.0f, false);
        }
        this.path.close();
        Utils.applyTrimPathIfNeeded(this.path, this.trimPath);
        this.isPathValid = true;
        return this.path;
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }
}
