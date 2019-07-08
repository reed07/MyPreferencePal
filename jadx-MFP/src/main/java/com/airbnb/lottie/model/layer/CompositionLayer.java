package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.value.LottieValueCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

public class CompositionLayer extends BaseLayer {
    private final List<BaseLayer> layers = new ArrayList();
    private final RectF newClipRect = new RectF();
    private final RectF rect = new RectF();
    @Nullable
    private BaseKeyframeAnimation<Float, Float> timeRemapping;

    public CompositionLayer(LottieDrawable lottieDrawable, Layer layer, List<Layer> list, LottieComposition lottieComposition) {
        Layer layer2;
        super(lottieDrawable, layer);
        AnimatableFloatValue timeRemapping2 = layer.getTimeRemapping();
        if (timeRemapping2 != null) {
            this.timeRemapping = timeRemapping2.createAnimation();
            addAnimation(this.timeRemapping);
            this.timeRemapping.addUpdateListener(this);
        } else {
            this.timeRemapping = null;
        }
        LongSparseArray longSparseArray = new LongSparseArray(lottieComposition.getLayers().size());
        int size = list.size() - 1;
        BaseLayer baseLayer = null;
        while (true) {
            if (size >= 0) {
                BaseLayer forModel = BaseLayer.forModel((Layer) list.get(size), lottieDrawable, lottieComposition);
                if (forModel != null) {
                    longSparseArray.put(forModel.getLayerModel().getId(), forModel);
                    if (baseLayer == null) {
                        this.layers.add(0, forModel);
                        switch (layer2.getMatteType()) {
                            case Add:
                            case Invert:
                                baseLayer = forModel;
                                break;
                        }
                    } else {
                        baseLayer.setMatteLayer(forModel);
                        baseLayer = null;
                    }
                }
                size--;
            } else {
                for (int i = 0; i < longSparseArray.size(); i++) {
                    BaseLayer baseLayer2 = (BaseLayer) longSparseArray.get(longSparseArray.keyAt(i));
                    if (baseLayer2 != null) {
                        BaseLayer baseLayer3 = (BaseLayer) longSparseArray.get(baseLayer2.getLayerModel().getParentId());
                        if (baseLayer3 != null) {
                            baseLayer2.setParentLayer(baseLayer3);
                        }
                    }
                }
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void drawLayer(Canvas canvas, Matrix matrix, int i) {
        L.beginSection("CompositionLayer#draw");
        canvas.save();
        this.newClipRect.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) this.layerModel.getPreCompWidth(), (float) this.layerModel.getPreCompHeight());
        matrix.mapRect(this.newClipRect);
        for (int size = this.layers.size() - 1; size >= 0; size--) {
            if (!this.newClipRect.isEmpty() ? canvas.clipRect(this.newClipRect) : true) {
                ((BaseLayer) this.layers.get(size)).draw(canvas, matrix, i);
            }
        }
        canvas.restore();
        L.endSection("CompositionLayer#draw");
    }

    public void getBounds(RectF rectF, Matrix matrix) {
        super.getBounds(rectF, matrix);
        this.rect.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        for (int size = this.layers.size() - 1; size >= 0; size--) {
            ((BaseLayer) this.layers.get(size)).getBounds(this.rect, this.boundsMatrix);
            if (rectF.isEmpty()) {
                rectF.set(this.rect);
            } else {
                rectF.set(Math.min(rectF.left, this.rect.left), Math.min(rectF.top, this.rect.top), Math.max(rectF.right, this.rect.right), Math.max(rectF.bottom, this.rect.bottom));
            }
        }
    }

    public void setProgress(@FloatRange float f) {
        super.setProgress(f);
        if (this.timeRemapping != null) {
            f = ((float) ((long) (((Float) this.timeRemapping.getValue()).floatValue() * 1000.0f))) / this.lottieDrawable.getComposition().getDuration();
        }
        if (this.layerModel.getTimeStretch() != BitmapDescriptorFactory.HUE_RED) {
            f /= this.layerModel.getTimeStretch();
        }
        float startProgress = f - this.layerModel.getStartProgress();
        for (int size = this.layers.size() - 1; size >= 0; size--) {
            ((BaseLayer) this.layers.get(size)).setProgress(startProgress);
        }
    }

    /* access modifiers changed from: protected */
    public void resolveChildKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        for (int i2 = 0; i2 < this.layers.size(); i2++) {
            ((BaseLayer) this.layers.get(i2)).resolveKeyPath(keyPath, i, list, keyPath2);
        }
    }

    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t, lottieValueCallback);
        if (t != LottieProperty.TIME_REMAP) {
            return;
        }
        if (lottieValueCallback == null) {
            this.timeRemapping = null;
            return;
        }
        this.timeRemapping = new ValueCallbackKeyframeAnimation(lottieValueCallback);
        addAnimation(this.timeRemapping);
    }
}
