package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

public class ContentGroup implements DrawingContent, PathContent, AnimationListener, KeyPathElement {
    private final List<Content> contents;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix;
    private final String name;
    private final Path path;
    @Nullable
    private List<PathContent> pathContents;
    private final RectF rect;
    @Nullable
    private TransformKeyframeAnimation transformAnimation;

    private static List<Content> contentsFromModels(LottieDrawable lottieDrawable2, BaseLayer baseLayer, List<ContentModel> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            Content content = ((ContentModel) list.get(i)).toContent(lottieDrawable2, baseLayer);
            if (content != null) {
                arrayList.add(content);
            }
        }
        return arrayList;
    }

    @Nullable
    static AnimatableTransform findTransform(List<ContentModel> list) {
        for (int i = 0; i < list.size(); i++) {
            ContentModel contentModel = (ContentModel) list.get(i);
            if (contentModel instanceof AnimatableTransform) {
                return (AnimatableTransform) contentModel;
            }
        }
        return null;
    }

    public ContentGroup(LottieDrawable lottieDrawable2, BaseLayer baseLayer, ShapeGroup shapeGroup) {
        this(lottieDrawable2, baseLayer, shapeGroup.getName(), contentsFromModels(lottieDrawable2, baseLayer, shapeGroup.getItems()), findTransform(shapeGroup.getItems()));
    }

    ContentGroup(LottieDrawable lottieDrawable2, BaseLayer baseLayer, String str, List<Content> list, @Nullable AnimatableTransform animatableTransform) {
        this.matrix = new Matrix();
        this.path = new Path();
        this.rect = new RectF();
        this.name = str;
        this.lottieDrawable = lottieDrawable2;
        this.contents = list;
        if (animatableTransform != null) {
            this.transformAnimation = animatableTransform.createAnimation();
            this.transformAnimation.addAnimationsToLayer(baseLayer);
            this.transformAnimation.addListener(this);
        }
        ArrayList arrayList = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            Content content = (Content) list.get(size);
            if (content instanceof GreedyContent) {
                arrayList.add((GreedyContent) content);
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            ((GreedyContent) arrayList.get(size2)).absorbContent(list.listIterator(list.size()));
        }
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public String getName() {
        return this.name;
    }

    public void setContents(List<Content> list, List<Content> list2) {
        ArrayList arrayList = new ArrayList(list.size() + this.contents.size());
        arrayList.addAll(list);
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            Content content = (Content) this.contents.get(size);
            content.setContents(arrayList, this.contents.subList(0, size));
            arrayList.add(content);
        }
    }

    /* access modifiers changed from: 0000 */
    public List<PathContent> getPathList() {
        if (this.pathContents == null) {
            this.pathContents = new ArrayList();
            for (int i = 0; i < this.contents.size(); i++) {
                Content content = (Content) this.contents.get(i);
                if (content instanceof PathContent) {
                    this.pathContents.add((PathContent) content);
                }
            }
        }
        return this.pathContents;
    }

    /* access modifiers changed from: 0000 */
    public Matrix getTransformationMatrix() {
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            return transformKeyframeAnimation.getMatrix();
        }
        this.matrix.reset();
        return this.matrix;
    }

    public Path getPath() {
        this.matrix.reset();
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.set(transformKeyframeAnimation.getMatrix());
        }
        this.path.reset();
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            Content content = (Content) this.contents.get(size);
            if (content instanceof PathContent) {
                this.path.addPath(((PathContent) content).getPath(), this.matrix);
            }
        }
        return this.path;
    }

    public void draw(Canvas canvas, Matrix matrix2, int i) {
        this.matrix.set(matrix2);
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.preConcat(transformKeyframeAnimation.getMatrix());
            i = (int) ((((((float) ((Integer) this.transformAnimation.getOpacity().getValue()).intValue()) / 100.0f) * ((float) i)) / 255.0f) * 255.0f);
        }
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            Object obj = this.contents.get(size);
            if (obj instanceof DrawingContent) {
                ((DrawingContent) obj).draw(canvas, this.matrix, i);
            }
        }
    }

    public void getBounds(RectF rectF, Matrix matrix2) {
        this.matrix.set(matrix2);
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.preConcat(transformKeyframeAnimation.getMatrix());
        }
        this.rect.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            Content content = (Content) this.contents.get(size);
            if (content instanceof DrawingContent) {
                ((DrawingContent) content).getBounds(this.rect, this.matrix);
                if (rectF.isEmpty()) {
                    rectF.set(this.rect);
                } else {
                    rectF.set(Math.min(rectF.left, this.rect.left), Math.min(rectF.top, this.rect.top), Math.max(rectF.right, this.rect.right), Math.max(rectF.bottom, this.rect.bottom));
                }
            }
        }
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
                int incrementDepthBy = i + keyPath.incrementDepthBy(getName(), i);
                for (int i2 = 0; i2 < this.contents.size(); i2++) {
                    Content content = (Content) this.contents.get(i2);
                    if (content instanceof KeyPathElement) {
                        ((KeyPathElement) content).resolveKeyPath(keyPath, incrementDepthBy, list, keyPath2);
                    }
                }
            }
        }
    }

    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            transformKeyframeAnimation.applyValueCallback(t, lottieValueCallback);
        }
    }
}
