package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.PolystarShape.Type;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.List;

public class PolystarContent implements KeyPathElementContent, PathContent, AnimationListener {
    @Nullable
    private final BaseKeyframeAnimation<?, Float> innerRadiusAnimation;
    @Nullable
    private final BaseKeyframeAnimation<?, Float> innerRoundednessAnimation;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<?, Float> outerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> outerRoundednessAnimation;
    private final Path path = new Path();
    private final BaseKeyframeAnimation<?, Float> pointsAnimation;
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final BaseKeyframeAnimation<?, Float> rotationAnimation;
    @Nullable
    private TrimPathContent trimPath;
    private final Type type;

    public PolystarContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable2;
        this.name = polystarShape.getName();
        this.type = polystarShape.getType();
        this.pointsAnimation = polystarShape.getPoints().createAnimation();
        this.positionAnimation = polystarShape.getPosition().createAnimation();
        this.rotationAnimation = polystarShape.getRotation().createAnimation();
        this.outerRadiusAnimation = polystarShape.getOuterRadius().createAnimation();
        this.outerRoundednessAnimation = polystarShape.getOuterRoundedness().createAnimation();
        if (this.type == Type.Star) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        baseLayer.addAnimation(this.pointsAnimation);
        baseLayer.addAnimation(this.positionAnimation);
        baseLayer.addAnimation(this.rotationAnimation);
        baseLayer.addAnimation(this.outerRadiusAnimation);
        baseLayer.addAnimation(this.outerRoundednessAnimation);
        if (this.type == Type.Star) {
            baseLayer.addAnimation(this.innerRadiusAnimation);
            baseLayer.addAnimation(this.innerRoundednessAnimation);
        }
        this.pointsAnimation.addUpdateListener(this);
        this.positionAnimation.addUpdateListener(this);
        this.rotationAnimation.addUpdateListener(this);
        this.outerRadiusAnimation.addUpdateListener(this);
        this.outerRoundednessAnimation.addUpdateListener(this);
        if (this.type == Type.Star) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
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
                if (trimPathContent.getType() == ShapeTrimPath.Type.Simultaneously) {
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
        switch (this.type) {
            case Star:
                createStarPath();
                break;
            case Polygon:
                createPolygonPath();
                break;
        }
        this.path.close();
        Utils.applyTrimPathIfNeeded(this.path, this.trimPath);
        this.isPathValid = true;
        return this.path;
    }

    public String getName() {
        return this.name;
    }

    private void createStarPath() {
        double d;
        int i;
        float f;
        float f2;
        float f3;
        double d2;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float floatValue = ((Float) this.pointsAnimation.getValue()).floatValue();
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation = this.rotationAnimation;
        double radians = Math.toRadians((baseKeyframeAnimation == null ? 0.0d : (double) ((Float) baseKeyframeAnimation.getValue()).floatValue()) - 90.0d);
        double d3 = (double) floatValue;
        float f13 = (float) (6.283185307179586d / d3);
        float f14 = f13 / 2.0f;
        float f15 = floatValue - ((float) ((int) floatValue));
        int i2 = (f15 > BitmapDescriptorFactory.HUE_RED ? 1 : (f15 == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
        if (i2 != 0) {
            radians += (double) ((1.0f - f15) * f14);
        }
        float floatValue2 = ((Float) this.outerRadiusAnimation.getValue()).floatValue();
        float floatValue3 = ((Float) this.innerRadiusAnimation.getValue()).floatValue();
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.innerRoundednessAnimation;
        float floatValue4 = baseKeyframeAnimation2 != null ? ((Float) baseKeyframeAnimation2.getValue()).floatValue() / 100.0f : BitmapDescriptorFactory.HUE_RED;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation3 = this.outerRoundednessAnimation;
        float floatValue5 = baseKeyframeAnimation3 != null ? ((Float) baseKeyframeAnimation3.getValue()).floatValue() / 100.0f : BitmapDescriptorFactory.HUE_RED;
        if (i2 != 0) {
            f = ((floatValue2 - floatValue3) * f15) + floatValue3;
            i = i2;
            double d4 = (double) f;
            d = d3;
            f3 = (float) (d4 * Math.cos(radians));
            f2 = (float) (d4 * Math.sin(radians));
            this.path.moveTo(f3, f2);
            d2 = radians + ((double) ((f13 * f15) / 2.0f));
        } else {
            d = d3;
            i = i2;
            double d5 = (double) floatValue2;
            float cos = (float) (Math.cos(radians) * d5);
            float sin = (float) (d5 * Math.sin(radians));
            this.path.moveTo(cos, sin);
            d2 = radians + ((double) f14);
            f3 = cos;
            f2 = sin;
            f = BitmapDescriptorFactory.HUE_RED;
        }
        double ceil = Math.ceil(d) * 2.0d;
        boolean z = false;
        double d6 = d2;
        float f16 = f14;
        int i3 = 0;
        while (true) {
            double d7 = (double) i3;
            if (d7 < ceil) {
                float f17 = z ? floatValue2 : floatValue3;
                int i4 = (f > BitmapDescriptorFactory.HUE_RED ? 1 : (f == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
                if (i4 == 0 || d7 != ceil - 2.0d) {
                    f4 = f16;
                } else {
                    f4 = f16;
                    f16 = (f13 * f15) / 2.0f;
                }
                if (i4 == 0 || d7 != ceil - 1.0d) {
                    f6 = f13;
                    f5 = floatValue3;
                    f8 = f17;
                    f7 = floatValue2;
                } else {
                    f6 = f13;
                    f7 = floatValue2;
                    f5 = floatValue3;
                    f8 = f;
                }
                double d8 = (double) f8;
                float f18 = f16;
                float cos2 = (float) (d8 * Math.cos(d6));
                float sin2 = (float) (d8 * Math.sin(d6));
                if (floatValue4 == BitmapDescriptorFactory.HUE_RED && floatValue5 == BitmapDescriptorFactory.HUE_RED) {
                    this.path.lineTo(cos2, sin2);
                    f9 = sin2;
                    f11 = floatValue4;
                    f10 = floatValue5;
                    f12 = f;
                } else {
                    f11 = floatValue4;
                    f10 = floatValue5;
                    double atan2 = (double) ((float) (Math.atan2((double) f2, (double) f3) - 1.5707963267948966d));
                    float cos3 = (float) Math.cos(atan2);
                    float sin3 = (float) Math.sin(atan2);
                    f12 = f;
                    f9 = sin2;
                    float f19 = f3;
                    double atan22 = (double) ((float) (Math.atan2((double) sin2, (double) cos2) - 1.5707963267948966d));
                    float cos4 = (float) Math.cos(atan22);
                    float sin4 = (float) Math.sin(atan22);
                    float f20 = z ? f11 : f10;
                    float f21 = (z ? f5 : f7) * f20 * 0.47829f;
                    float f22 = cos3 * f21;
                    float f23 = f21 * sin3;
                    float f24 = (z ? f7 : f5) * (z ? f10 : f11) * 0.47829f;
                    float f25 = cos4 * f24;
                    float f26 = f24 * sin4;
                    if (i != 0) {
                        if (i3 == 0) {
                            f22 *= f15;
                            f23 *= f15;
                        } else if (d7 == ceil - 1.0d) {
                            f25 *= f15;
                            f26 *= f15;
                        }
                    }
                    this.path.cubicTo(f19 - f22, f2 - f23, cos2 + f25, f9 + f26, cos2, f9);
                }
                d6 += (double) f18;
                z = !z;
                i3++;
                f3 = cos2;
                f = f12;
                floatValue2 = f7;
                f13 = f6;
                f16 = f4;
                floatValue3 = f5;
                floatValue4 = f11;
                floatValue5 = f10;
                f2 = f9;
            } else {
                PointF pointF = (PointF) this.positionAnimation.getValue();
                this.path.offset(pointF.x, pointF.y);
                this.path.close();
                return;
            }
        }
    }

    private void createPolygonPath() {
        double d;
        double d2;
        double d3;
        int i;
        int floor = (int) Math.floor((double) ((Float) this.pointsAnimation.getValue()).floatValue());
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation = this.rotationAnimation;
        double radians = Math.toRadians((baseKeyframeAnimation == null ? 0.0d : (double) ((Float) baseKeyframeAnimation.getValue()).floatValue()) - 90.0d);
        double d4 = (double) floor;
        float f = (float) (6.283185307179586d / d4);
        float floatValue = ((Float) this.outerRoundednessAnimation.getValue()).floatValue() / 100.0f;
        float floatValue2 = ((Float) this.outerRadiusAnimation.getValue()).floatValue();
        double d5 = (double) floatValue2;
        float cos = (float) (Math.cos(radians) * d5);
        float sin = (float) (Math.sin(radians) * d5);
        this.path.moveTo(cos, sin);
        double d6 = (double) f;
        double d7 = radians + d6;
        double ceil = Math.ceil(d4);
        int i2 = 0;
        while (((double) i2) < ceil) {
            float cos2 = (float) (Math.cos(d7) * d5);
            double d8 = ceil;
            float sin2 = (float) (d5 * Math.sin(d7));
            if (floatValue != BitmapDescriptorFactory.HUE_RED) {
                d3 = d5;
                i = i2;
                d2 = d7;
                double atan2 = (double) ((float) (Math.atan2((double) sin, (double) cos) - 1.5707963267948966d));
                d = d6;
                double atan22 = (double) ((float) (Math.atan2((double) sin2, (double) cos2) - 1.5707963267948966d));
                float f2 = floatValue2 * floatValue * 0.25f;
                this.path.cubicTo(cos - (((float) Math.cos(atan2)) * f2), sin - (((float) Math.sin(atan2)) * f2), cos2 + (((float) Math.cos(atan22)) * f2), sin2 + (f2 * ((float) Math.sin(atan22))), cos2, sin2);
            } else {
                d2 = d7;
                d3 = d5;
                d = d6;
                i = i2;
                this.path.lineTo(cos2, sin2);
            }
            d7 = d2 + d;
            i2 = i + 1;
            sin = sin2;
            cos = cos2;
            ceil = d8;
            d5 = d3;
            d6 = d;
        }
        PointF pointF = (PointF) this.positionAnimation.getValue();
        this.path.offset(pointF.x, pointF.y);
        this.path.close();
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        if (t == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback);
        } else {
            if (t == LottieProperty.POLYSTAR_INNER_RADIUS) {
                BaseKeyframeAnimation<?, Float> baseKeyframeAnimation = this.innerRadiusAnimation;
                if (baseKeyframeAnimation != null) {
                    baseKeyframeAnimation.setValueCallback(lottieValueCallback);
                    return;
                }
            }
            if (t == LottieProperty.POLYSTAR_OUTER_RADIUS) {
                this.outerRadiusAnimation.setValueCallback(lottieValueCallback);
                return;
            }
            if (t == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS) {
                BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.innerRoundednessAnimation;
                if (baseKeyframeAnimation2 != null) {
                    baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
                    return;
                }
            }
            if (t == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
                this.outerRoundednessAnimation.setValueCallback(lottieValueCallback);
            }
        }
    }
}
