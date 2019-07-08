package com.airbnb.lottie.model.layer;

import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
import java.util.Locale;

public class Layer {
    private final LottieComposition composition;
    private final List<Keyframe<Float>> inOutKeyframes;
    private final long layerId;
    private final String layerName;
    private final LayerType layerType;
    private final List<Mask> masks;
    private final MatteType matteType;
    private final long parentId;
    private final int preCompHeight;
    private final int preCompWidth;
    @Nullable
    private final String refId;
    private final List<ContentModel> shapes;
    private final int solidColor;
    private final int solidHeight;
    private final int solidWidth;
    private final float startFrame;
    @Nullable
    private final AnimatableTextFrame text;
    @Nullable
    private final AnimatableTextProperties textProperties;
    @Nullable
    private final AnimatableFloatValue timeRemapping;
    private final float timeStretch;
    private final AnimatableTransform transform;

    public enum LayerType {
        PreComp,
        Solid,
        Image,
        Null,
        Shape,
        Text,
        Unknown
    }

    public enum MatteType {
        None,
        Add,
        Invert,
        Unknown
    }

    public Layer(List<ContentModel> list, LottieComposition lottieComposition, String str, long j, LayerType layerType2, long j2, @Nullable String str2, List<Mask> list2, AnimatableTransform animatableTransform, int i, int i2, int i3, float f, float f2, int i4, int i5, @Nullable AnimatableTextFrame animatableTextFrame, @Nullable AnimatableTextProperties animatableTextProperties, List<Keyframe<Float>> list3, MatteType matteType2, @Nullable AnimatableFloatValue animatableFloatValue) {
        this.shapes = list;
        this.composition = lottieComposition;
        this.layerName = str;
        this.layerId = j;
        this.layerType = layerType2;
        this.parentId = j2;
        this.refId = str2;
        this.masks = list2;
        this.transform = animatableTransform;
        this.solidWidth = i;
        this.solidHeight = i2;
        this.solidColor = i3;
        this.timeStretch = f;
        this.startFrame = f2;
        this.preCompWidth = i4;
        this.preCompHeight = i5;
        this.text = animatableTextFrame;
        this.textProperties = animatableTextProperties;
        this.inOutKeyframes = list3;
        this.matteType = matteType2;
        this.timeRemapping = animatableFloatValue;
    }

    /* access modifiers changed from: 0000 */
    public LottieComposition getComposition() {
        return this.composition;
    }

    /* access modifiers changed from: 0000 */
    public float getTimeStretch() {
        return this.timeStretch;
    }

    /* access modifiers changed from: 0000 */
    public float getStartProgress() {
        return this.startFrame / this.composition.getDurationFrames();
    }

    /* access modifiers changed from: 0000 */
    public List<Keyframe<Float>> getInOutKeyframes() {
        return this.inOutKeyframes;
    }

    public long getId() {
        return this.layerId;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.layerName;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getRefId() {
        return this.refId;
    }

    /* access modifiers changed from: 0000 */
    public int getPreCompWidth() {
        return this.preCompWidth;
    }

    /* access modifiers changed from: 0000 */
    public int getPreCompHeight() {
        return this.preCompHeight;
    }

    /* access modifiers changed from: 0000 */
    public List<Mask> getMasks() {
        return this.masks;
    }

    public LayerType getLayerType() {
        return this.layerType;
    }

    /* access modifiers changed from: 0000 */
    public MatteType getMatteType() {
        return this.matteType;
    }

    /* access modifiers changed from: 0000 */
    public long getParentId() {
        return this.parentId;
    }

    /* access modifiers changed from: 0000 */
    public List<ContentModel> getShapes() {
        return this.shapes;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableTransform getTransform() {
        return this.transform;
    }

    /* access modifiers changed from: 0000 */
    public int getSolidColor() {
        return this.solidColor;
    }

    /* access modifiers changed from: 0000 */
    public int getSolidHeight() {
        return this.solidHeight;
    }

    /* access modifiers changed from: 0000 */
    public int getSolidWidth() {
        return this.solidWidth;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public AnimatableTextFrame getText() {
        return this.text;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public AnimatableTextProperties getTextProperties() {
        return this.textProperties;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public AnimatableFloatValue getTimeRemapping() {
        return this.timeRemapping;
    }

    public String toString() {
        return toString("");
    }

    public String toString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(getName());
        sb.append("\n");
        Layer layerModelForId = this.composition.layerModelForId(getParentId());
        if (layerModelForId != null) {
            sb.append("\t\tParents: ");
            sb.append(layerModelForId.getName());
            Layer layerModelForId2 = this.composition.layerModelForId(layerModelForId.getParentId());
            while (layerModelForId2 != null) {
                sb.append("->");
                sb.append(layerModelForId2.getName());
                layerModelForId2 = this.composition.layerModelForId(layerModelForId2.getParentId());
            }
            sb.append(str);
            sb.append("\n");
        }
        if (!getMasks().isEmpty()) {
            sb.append(str);
            sb.append("\tMasks: ");
            sb.append(getMasks().size());
            sb.append("\n");
        }
        if (!(getSolidWidth() == 0 || getSolidHeight() == 0)) {
            sb.append(str);
            sb.append("\tBackground: ");
            sb.append(String.format(Locale.US, "%dx%d %X\n", new Object[]{Integer.valueOf(getSolidWidth()), Integer.valueOf(getSolidHeight()), Integer.valueOf(getSolidColor())}));
        }
        if (!this.shapes.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (Object next : this.shapes) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(next);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
