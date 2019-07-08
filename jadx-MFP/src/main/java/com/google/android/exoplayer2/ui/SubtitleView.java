package com.google.android.exoplayer2.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import com.brightcove.player.model.VideoFields;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

public final class SubtitleView extends View implements TextOutput {
    public static final float DEFAULT_BOTTOM_PADDING_FRACTION = 0.08f;
    public static final float DEFAULT_TEXT_SIZE_FRACTION = 0.0533f;
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private float bottomPaddingFraction;
    private List<Cue> cues;
    private final List<SubtitlePainter> painters;
    private CaptionStyleCompat style;
    private float textSize;
    private int textSizeType;

    private float resolveTextSize(int i, float f, int i2, int i3) {
        switch (i) {
            case 0:
                return f * ((float) i3);
            case 1:
                return f * ((float) i2);
            case 2:
                return f;
            default:
                return Float.MIN_VALUE;
        }
    }

    public SubtitleView(Context context) {
        this(context, null);
    }

    public SubtitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.painters = new ArrayList();
        this.textSizeType = 0;
        this.textSize = 0.0533f;
        this.applyEmbeddedStyles = true;
        this.applyEmbeddedFontSizes = true;
        this.style = CaptionStyleCompat.DEFAULT;
        this.bottomPaddingFraction = 0.08f;
    }

    public void onCues(List<Cue> list) {
        setCues(list);
    }

    public void setCues(@Nullable List<Cue> list) {
        int i;
        if (this.cues != list) {
            this.cues = list;
            if (list == null) {
                i = 0;
            } else {
                i = list.size();
            }
            while (this.painters.size() < i) {
                this.painters.add(new SubtitlePainter(getContext()));
            }
            invalidate();
        }
    }

    public void setFixedTextSize(int i, float f) {
        Resources resources;
        Context context = getContext();
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        setTextSize(2, TypedValue.applyDimension(i, f, resources.getDisplayMetrics()));
    }

    public void setUserDefaultTextSize() {
        setFractionalTextSize(((Util.SDK_INT < 19 || isInEditMode()) ? 1.0f : getUserCaptionFontScaleV19()) * 0.0533f);
    }

    public void setFractionalTextSize(float f) {
        setFractionalTextSize(f, false);
    }

    public void setFractionalTextSize(float f, boolean z) {
        setTextSize(z ? 1 : 0, f);
    }

    private void setTextSize(int i, float f) {
        if (this.textSizeType != i || this.textSize != f) {
            this.textSizeType = i;
            this.textSize = f;
            invalidate();
        }
    }

    public void setApplyEmbeddedStyles(boolean z) {
        if (this.applyEmbeddedStyles != z || this.applyEmbeddedFontSizes != z) {
            this.applyEmbeddedStyles = z;
            this.applyEmbeddedFontSizes = z;
            invalidate();
        }
    }

    public void setApplyEmbeddedFontSizes(boolean z) {
        if (this.applyEmbeddedFontSizes != z) {
            this.applyEmbeddedFontSizes = z;
            invalidate();
        }
    }

    public void setUserDefaultStyle() {
        setStyle((Util.SDK_INT < 19 || !isCaptionManagerEnabled() || isInEditMode()) ? CaptionStyleCompat.DEFAULT : getUserCaptionStyleV19());
    }

    public void setStyle(CaptionStyleCompat captionStyleCompat) {
        if (this.style != captionStyleCompat) {
            this.style = captionStyleCompat;
            invalidate();
        }
    }

    public void setBottomPaddingFraction(float f) {
        if (this.bottomPaddingFraction != f) {
            this.bottomPaddingFraction = f;
            invalidate();
        }
    }

    public void dispatchDraw(Canvas canvas) {
        List<Cue> list = this.cues;
        int i = 0;
        int size = list == null ? 0 : list.size();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int paddingBottom = height - getPaddingBottom();
        if (paddingBottom > paddingTop && width > paddingLeft) {
            int i2 = paddingBottom - paddingTop;
            float resolveTextSize = resolveTextSize(this.textSizeType, this.textSize, height, i2);
            if (resolveTextSize > BitmapDescriptorFactory.HUE_RED) {
                while (i < size) {
                    Cue cue = (Cue) this.cues.get(i);
                    int i3 = i2;
                    int i4 = paddingBottom;
                    int i5 = width;
                    int i6 = paddingTop;
                    int i7 = paddingLeft;
                    ((SubtitlePainter) this.painters.get(i)).draw(cue, this.applyEmbeddedStyles, this.applyEmbeddedFontSizes, this.style, resolveTextSize, resolveCueTextSize(cue, height, i2), this.bottomPaddingFraction, canvas, paddingLeft, paddingTop, i5, i4);
                    i++;
                    i2 = i3;
                    paddingBottom = i4;
                    width = i5;
                    paddingLeft = i7;
                }
            }
        }
    }

    private float resolveCueTextSize(Cue cue, int i, int i2) {
        if (cue.textSizeType == Integer.MIN_VALUE || cue.textSize == Float.MIN_VALUE) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return Math.max(resolveTextSize(cue.textSizeType, cue.textSize, i, i2), BitmapDescriptorFactory.HUE_RED);
    }

    @TargetApi(19)
    private boolean isCaptionManagerEnabled() {
        return ((CaptioningManager) getContext().getSystemService(VideoFields.CAPTIONING)).isEnabled();
    }

    @TargetApi(19)
    private float getUserCaptionFontScaleV19() {
        return ((CaptioningManager) getContext().getSystemService(VideoFields.CAPTIONING)).getFontScale();
    }

    @TargetApi(19)
    private CaptionStyleCompat getUserCaptionStyleV19() {
        return CaptionStyleCompat.createFromCaptionStyle(((CaptioningManager) getContext().getSystemService(VideoFields.CAPTIONING)).getUserStyle());
    }
}
