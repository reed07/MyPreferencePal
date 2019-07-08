package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.support.v4.util.LongSparseArray;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;

public class GradientStrokeContent extends BaseStrokeContent {
    private final RectF boundsRect = new RectF();
    private final int cacheSteps;
    private final BaseKeyframeAnimation<GradientColor, GradientColor> colorAnimation;
    private final BaseKeyframeAnimation<PointF, PointF> endPointAnimation;
    private final LongSparseArray<LinearGradient> linearGradientCache = new LongSparseArray<>();
    private final String name;
    private final LongSparseArray<RadialGradient> radialGradientCache = new LongSparseArray<>();
    private final BaseKeyframeAnimation<PointF, PointF> startPointAnimation;
    private final GradientType type;

    public GradientStrokeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, GradientStroke gradientStroke) {
        super(lottieDrawable, baseLayer, gradientStroke.getCapType().toPaintCap(), gradientStroke.getJoinType().toPaintJoin(), gradientStroke.getMiterLimit(), gradientStroke.getOpacity(), gradientStroke.getWidth(), gradientStroke.getLineDashPattern(), gradientStroke.getDashOffset());
        this.name = gradientStroke.getName();
        this.type = gradientStroke.getGradientType();
        this.cacheSteps = (int) (lottieDrawable.getComposition().getDuration() / 32.0f);
        this.colorAnimation = gradientStroke.getGradientColor().createAnimation();
        this.colorAnimation.addUpdateListener(this);
        baseLayer.addAnimation(this.colorAnimation);
        this.startPointAnimation = gradientStroke.getStartPoint().createAnimation();
        this.startPointAnimation.addUpdateListener(this);
        baseLayer.addAnimation(this.startPointAnimation);
        this.endPointAnimation = gradientStroke.getEndPoint().createAnimation();
        this.endPointAnimation.addUpdateListener(this);
        baseLayer.addAnimation(this.endPointAnimation);
    }

    public void draw(Canvas canvas, Matrix matrix, int i) {
        getBounds(this.boundsRect, matrix);
        if (this.type == GradientType.Linear) {
            this.paint.setShader(getLinearGradient());
        } else {
            this.paint.setShader(getRadialGradient());
        }
        super.draw(canvas, matrix, i);
    }

    public String getName() {
        return this.name;
    }

    private LinearGradient getLinearGradient() {
        long gradientHash = (long) getGradientHash();
        LinearGradient linearGradient = (LinearGradient) this.linearGradientCache.get(gradientHash);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointF = (PointF) this.startPointAnimation.getValue();
        PointF pointF2 = (PointF) this.endPointAnimation.getValue();
        GradientColor gradientColor = (GradientColor) this.colorAnimation.getValue();
        LinearGradient linearGradient2 = new LinearGradient((float) ((int) (this.boundsRect.left + (this.boundsRect.width() / 2.0f) + pointF.x)), (float) ((int) (this.boundsRect.top + (this.boundsRect.height() / 2.0f) + pointF.y)), (float) ((int) (this.boundsRect.left + (this.boundsRect.width() / 2.0f) + pointF2.x)), (float) ((int) (this.boundsRect.top + (this.boundsRect.height() / 2.0f) + pointF2.y)), gradientColor.getColors(), gradientColor.getPositions(), TileMode.CLAMP);
        this.linearGradientCache.put(gradientHash, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient getRadialGradient() {
        long gradientHash = (long) getGradientHash();
        RadialGradient radialGradient = (RadialGradient) this.radialGradientCache.get(gradientHash);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointF = (PointF) this.startPointAnimation.getValue();
        PointF pointF2 = (PointF) this.endPointAnimation.getValue();
        GradientColor gradientColor = (GradientColor) this.colorAnimation.getValue();
        int[] colors = gradientColor.getColors();
        float[] positions = gradientColor.getPositions();
        int width = (int) (this.boundsRect.left + (this.boundsRect.width() / 2.0f) + pointF.x);
        int height = (int) (this.boundsRect.top + (this.boundsRect.height() / 2.0f) + pointF.y);
        float f = (float) width;
        float f2 = (float) height;
        RadialGradient radialGradient2 = new RadialGradient(f, f2, (float) Math.hypot((double) (((int) ((this.boundsRect.left + (this.boundsRect.width() / 2.0f)) + pointF2.x)) - width), (double) (((int) ((this.boundsRect.top + (this.boundsRect.height() / 2.0f)) + pointF2.y)) - height)), colors, positions, TileMode.CLAMP);
        this.radialGradientCache.put(gradientHash, radialGradient2);
        return radialGradient2;
    }

    private int getGradientHash() {
        int round = Math.round(this.startPointAnimation.getProgress() * ((float) this.cacheSteps));
        int round2 = Math.round(this.endPointAnimation.getProgress() * ((float) this.cacheSteps));
        int round3 = Math.round(this.colorAnimation.getProgress() * ((float) this.cacheSteps));
        int i = round != 0 ? 527 * round : 17;
        if (round2 != 0) {
            i = i * 31 * round2;
        }
        return round3 != 0 ? i * 31 * round3 : i;
    }
}
