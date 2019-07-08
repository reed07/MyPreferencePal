package com.airbnb.lottie.model.layer;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.Mask.MaskMode;
import com.airbnb.lottie.model.layer.Layer.MatteType;
import com.airbnb.lottie.value.LottieValueCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseLayer implements DrawingContent, AnimationListener, KeyPathElement {
    private final Paint addMaskPaint = new Paint(1);
    private final List<BaseKeyframeAnimation<?, ?>> animations = new ArrayList();
    final Matrix boundsMatrix = new Matrix();
    private final Paint clearPaint = new Paint();
    private final Paint contentPaint = new Paint(1);
    private final String drawTraceName;
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    @Nullable
    private MaskKeyframeAnimation mask;
    private final RectF maskBoundsRect = new RectF();
    private final Matrix matrix = new Matrix();
    private final RectF matteBoundsRect = new RectF();
    @Nullable
    private BaseLayer matteLayer;
    private final Paint mattePaint = new Paint(1);
    @Nullable
    private BaseLayer parentLayer;
    private List<BaseLayer> parentLayers;
    private final Path path = new Path();
    private final RectF rect = new RectF();
    private final Paint subtractMaskPaint = new Paint(1);
    private final RectF tempMaskBoundsRect = new RectF();
    final TransformKeyframeAnimation transform;
    private boolean visible = true;

    /* access modifiers changed from: 0000 */
    public abstract void drawLayer(Canvas canvas, Matrix matrix2, int i);

    /* access modifiers changed from: 0000 */
    public void resolveChildKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
    }

    public void setContents(List<Content> list, List<Content> list2) {
    }

    @Nullable
    static BaseLayer forModel(Layer layer, LottieDrawable lottieDrawable2, LottieComposition lottieComposition) {
        switch (layer.getLayerType()) {
            case Shape:
                return new ShapeLayer(lottieDrawable2, layer);
            case PreComp:
                return new CompositionLayer(lottieDrawable2, layer, lottieComposition.getPrecomps(layer.getRefId()), lottieComposition);
            case Solid:
                return new SolidLayer(lottieDrawable2, layer);
            case Image:
                return new ImageLayer(lottieDrawable2, layer);
            case Null:
                return new NullLayer(lottieDrawable2, layer);
            case Text:
                return new TextLayer(lottieDrawable2, layer);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown layer type ");
                sb.append(layer.getLayerType());
                L.warn(sb.toString());
                return null;
        }
    }

    BaseLayer(LottieDrawable lottieDrawable2, Layer layer) {
        this.lottieDrawable = lottieDrawable2;
        this.layerModel = layer;
        StringBuilder sb = new StringBuilder();
        sb.append(layer.getName());
        sb.append("#draw");
        this.drawTraceName = sb.toString();
        this.clearPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.addMaskPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        this.subtractMaskPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        if (layer.getMatteType() == MatteType.Invert) {
            this.mattePaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        } else {
            this.mattePaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        }
        this.transform = layer.getTransform().createAnimation();
        this.transform.addListener(this);
        if (layer.getMasks() != null && !layer.getMasks().isEmpty()) {
            this.mask = new MaskKeyframeAnimation(layer.getMasks());
            for (BaseKeyframeAnimation addUpdateListener : this.mask.getMaskAnimations()) {
                addUpdateListener.addUpdateListener(this);
            }
            for (BaseKeyframeAnimation baseKeyframeAnimation : this.mask.getOpacityAnimations()) {
                addAnimation(baseKeyframeAnimation);
                baseKeyframeAnimation.addUpdateListener(this);
            }
        }
        setupInOutAnimations();
    }

    public void onValueChanged() {
        invalidateSelf();
    }

    /* access modifiers changed from: 0000 */
    public Layer getLayerModel() {
        return this.layerModel;
    }

    /* access modifiers changed from: 0000 */
    public void setMatteLayer(@Nullable BaseLayer baseLayer) {
        this.matteLayer = baseLayer;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    /* access modifiers changed from: 0000 */
    public void setParentLayer(@Nullable BaseLayer baseLayer) {
        this.parentLayer = baseLayer;
    }

    private void setupInOutAnimations() {
        boolean z = true;
        if (!this.layerModel.getInOutKeyframes().isEmpty()) {
            final FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            floatKeyframeAnimation.setIsDiscrete();
            floatKeyframeAnimation.addUpdateListener(new AnimationListener() {
                public void onValueChanged() {
                    BaseLayer.this.setVisible(((Float) floatKeyframeAnimation.getValue()).floatValue() == 1.0f);
                }
            });
            if (((Float) floatKeyframeAnimation.getValue()).floatValue() != 1.0f) {
                z = false;
            }
            setVisible(z);
            addAnimation(floatKeyframeAnimation);
            return;
        }
        setVisible(true);
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    @SuppressLint({"WrongConstant"})
    private void saveLayerCompat(Canvas canvas, RectF rectF, Paint paint, boolean z) {
        if (VERSION.SDK_INT < 23) {
            canvas.saveLayer(rectF, paint, z ? 31 : 19);
        } else {
            canvas.saveLayer(rectF, paint);
        }
    }

    public void addAnimation(BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.animations.add(baseKeyframeAnimation);
    }

    @CallSuper
    public void getBounds(RectF rectF, Matrix matrix2) {
        this.boundsMatrix.set(matrix2);
        this.boundsMatrix.preConcat(this.transform.getMatrix());
    }

    public void draw(Canvas canvas, Matrix matrix2, int i) {
        L.beginSection(this.drawTraceName);
        if (!this.visible) {
            L.endSection(this.drawTraceName);
            return;
        }
        buildParentLayerListIfNeeded();
        L.beginSection("Layer#parentMatrix");
        this.matrix.reset();
        this.matrix.set(matrix2);
        for (int size = this.parentLayers.size() - 1; size >= 0; size--) {
            this.matrix.preConcat(((BaseLayer) this.parentLayers.get(size)).transform.getMatrix());
        }
        L.endSection("Layer#parentMatrix");
        int intValue = (int) ((((((float) i) / 255.0f) * ((float) ((Integer) this.transform.getOpacity().getValue()).intValue())) / 100.0f) * 255.0f);
        if (hasMatteOnThisLayer() || hasMasksOnThisLayer()) {
            L.beginSection("Layer#computeBounds");
            this.rect.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            getBounds(this.rect, this.matrix);
            intersectBoundsWithMatte(this.rect, this.matrix);
            this.matrix.preConcat(this.transform.getMatrix());
            intersectBoundsWithMask(this.rect, this.matrix);
            this.rect.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) canvas.getWidth(), (float) canvas.getHeight());
            L.endSection("Layer#computeBounds");
            L.beginSection("Layer#saveLayer");
            saveLayerCompat(canvas, this.rect, this.contentPaint, true);
            L.endSection("Layer#saveLayer");
            clearCanvas(canvas);
            L.beginSection("Layer#drawLayer");
            drawLayer(canvas, this.matrix, intValue);
            L.endSection("Layer#drawLayer");
            if (hasMasksOnThisLayer()) {
                applyMasks(canvas, this.matrix);
            }
            if (hasMatteOnThisLayer()) {
                L.beginSection("Layer#drawMatte");
                L.beginSection("Layer#saveLayer");
                saveLayerCompat(canvas, this.rect, this.mattePaint, false);
                L.endSection("Layer#saveLayer");
                clearCanvas(canvas);
                this.matteLayer.draw(canvas, matrix2, intValue);
                L.beginSection("Layer#restoreLayer");
                canvas.restore();
                L.endSection("Layer#restoreLayer");
                L.endSection("Layer#drawMatte");
            }
            L.beginSection("Layer#restoreLayer");
            canvas.restore();
            L.endSection("Layer#restoreLayer");
            recordRenderTime(L.endSection(this.drawTraceName));
            return;
        }
        this.matrix.preConcat(this.transform.getMatrix());
        L.beginSection("Layer#drawLayer");
        drawLayer(canvas, this.matrix, intValue);
        L.endSection("Layer#drawLayer");
        recordRenderTime(L.endSection(this.drawTraceName));
    }

    private void recordRenderTime(float f) {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), f);
    }

    private void clearCanvas(Canvas canvas) {
        L.beginSection("Layer#clearLayer");
        canvas.drawRect(this.rect.left - 1.0f, this.rect.top - 1.0f, this.rect.right + 1.0f, this.rect.bottom + 1.0f, this.clearPaint);
        L.endSection("Layer#clearLayer");
    }

    private void intersectBoundsWithMask(RectF rectF, Matrix matrix2) {
        this.maskBoundsRect.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        if (hasMasksOnThisLayer()) {
            int size = this.mask.getMasks().size();
            int i = 0;
            while (i < size) {
                Mask mask2 = (Mask) this.mask.getMasks().get(i);
                this.path.set((Path) ((BaseKeyframeAnimation) this.mask.getMaskAnimations().get(i)).getValue());
                this.path.transform(matrix2);
                switch (mask2.getMaskMode()) {
                    case MaskModeSubtract:
                        return;
                    case MaskModeIntersect:
                        return;
                    default:
                        this.path.computeBounds(this.tempMaskBoundsRect, false);
                        if (i == 0) {
                            this.maskBoundsRect.set(this.tempMaskBoundsRect);
                        } else {
                            RectF rectF2 = this.maskBoundsRect;
                            rectF2.set(Math.min(rectF2.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                        }
                        i++;
                }
            }
            rectF.set(Math.max(rectF.left, this.maskBoundsRect.left), Math.max(rectF.top, this.maskBoundsRect.top), Math.min(rectF.right, this.maskBoundsRect.right), Math.min(rectF.bottom, this.maskBoundsRect.bottom));
        }
    }

    private void intersectBoundsWithMatte(RectF rectF, Matrix matrix2) {
        if (hasMatteOnThisLayer() && this.layerModel.getMatteType() != MatteType.Invert) {
            this.matteLayer.getBounds(this.matteBoundsRect, matrix2);
            rectF.set(Math.max(rectF.left, this.matteBoundsRect.left), Math.max(rectF.top, this.matteBoundsRect.top), Math.min(rectF.right, this.matteBoundsRect.right), Math.min(rectF.bottom, this.matteBoundsRect.bottom));
        }
    }

    private void applyMasks(Canvas canvas, Matrix matrix2) {
        applyMasks(canvas, matrix2, MaskMode.MaskModeAdd);
        applyMasks(canvas, matrix2, MaskMode.MaskModeIntersect);
        applyMasks(canvas, matrix2, MaskMode.MaskModeSubtract);
    }

    private void applyMasks(Canvas canvas, Matrix matrix2, MaskMode maskMode) {
        Paint paint;
        boolean z = true;
        if (AnonymousClass2.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[maskMode.ordinal()] != 1) {
            paint = this.addMaskPaint;
        } else {
            paint = this.subtractMaskPaint;
        }
        int size = this.mask.getMasks().size();
        int i = 0;
        while (true) {
            if (i >= size) {
                z = false;
                break;
            } else if (((Mask) this.mask.getMasks().get(i)).getMaskMode() == maskMode) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            L.beginSection("Layer#drawMask");
            L.beginSection("Layer#saveLayer");
            saveLayerCompat(canvas, this.rect, paint, false);
            L.endSection("Layer#saveLayer");
            clearCanvas(canvas);
            for (int i2 = 0; i2 < size; i2++) {
                if (((Mask) this.mask.getMasks().get(i2)).getMaskMode() == maskMode) {
                    this.path.set((Path) ((BaseKeyframeAnimation) this.mask.getMaskAnimations().get(i2)).getValue());
                    this.path.transform(matrix2);
                    BaseKeyframeAnimation baseKeyframeAnimation = (BaseKeyframeAnimation) this.mask.getOpacityAnimations().get(i2);
                    int alpha = this.contentPaint.getAlpha();
                    this.contentPaint.setAlpha((int) (((float) ((Integer) baseKeyframeAnimation.getValue()).intValue()) * 2.55f));
                    canvas.drawPath(this.path, this.contentPaint);
                    this.contentPaint.setAlpha(alpha);
                }
            }
            L.beginSection("Layer#restoreLayer");
            canvas.restore();
            L.endSection("Layer#restoreLayer");
            L.endSection("Layer#drawMask");
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean hasMasksOnThisLayer() {
        MaskKeyframeAnimation maskKeyframeAnimation = this.mask;
        return maskKeyframeAnimation != null && !maskKeyframeAnimation.getMaskAnimations().isEmpty();
    }

    /* access modifiers changed from: private */
    public void setVisible(boolean z) {
        if (z != this.visible) {
            this.visible = z;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setProgress(@FloatRange float f) {
        this.transform.setProgress(f);
        if (this.mask != null) {
            for (int i = 0; i < this.mask.getMaskAnimations().size(); i++) {
                ((BaseKeyframeAnimation) this.mask.getMaskAnimations().get(i)).setProgress(f);
            }
        }
        if (this.layerModel.getTimeStretch() != BitmapDescriptorFactory.HUE_RED) {
            f /= this.layerModel.getTimeStretch();
        }
        BaseLayer baseLayer = this.matteLayer;
        if (baseLayer != null) {
            this.matteLayer.setProgress(baseLayer.layerModel.getTimeStretch() * f);
        }
        for (int i2 = 0; i2 < this.animations.size(); i2++) {
            ((BaseKeyframeAnimation) this.animations.get(i2)).setProgress(f);
        }
    }

    private void buildParentLayerListIfNeeded() {
        if (this.parentLayers == null) {
            if (this.parentLayer == null) {
                this.parentLayers = Collections.emptyList();
                return;
            }
            this.parentLayers = new ArrayList();
            for (BaseLayer baseLayer = this.parentLayer; baseLayer != null; baseLayer = baseLayer.parentLayer) {
                this.parentLayers.add(baseLayer);
            }
        }
    }

    public String getName() {
        return this.layerModel.getName();
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        if (keyPath.matches(getName(), i)) {
            if (!"__container".equals(getName())) {
                keyPath2 = keyPath2.addKey(getName());
                if (keyPath.fullyResolvesTo(getName(), i)) {
                    list.add(keyPath2.resolve(this));
                }
            }
            if (keyPath.propagateToChildren(getName(), i)) {
                resolveChildKeyPath(keyPath, i + keyPath.incrementDepthBy(getName(), i), list, keyPath2);
            }
        }
    }

    @CallSuper
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        this.transform.applyValueCallback(t, lottieValueCallback);
    }
}
