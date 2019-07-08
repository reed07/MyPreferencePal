package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class SubtitlePainter {
    private static final float INNER_PADDING_RATIO = 0.125f;
    private static final String TAG = "SubtitlePainter";
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Alignment cueTextAlignment;
    private float cueTextSizePx;
    private float defaultTextSizePx;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint = new TextPaint();
    private int textTop;
    private int windowColor;

    public SubtitlePainter(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{16843287, 16843288}, 0, 0);
        this.spacingAdd = (float) obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        float round = (float) Math.round((((float) context.getResources().getDisplayMetrics().densityDpi) * 2.0f) / 160.0f);
        this.outlineWidth = round;
        this.shadowRadius = round;
        this.shadowOffset = round;
        this.textPaint.setAntiAlias(true);
        this.textPaint.setSubpixelText(true);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Style.FILL);
    }

    public void draw(Cue cue, boolean z, boolean z2, CaptionStyleCompat captionStyleCompat, float f, float f2, float f3, Canvas canvas, int i, int i2, int i3, int i4) {
        Canvas canvas2;
        Cue cue2 = cue;
        boolean z3 = z;
        boolean z4 = z2;
        CaptionStyleCompat captionStyleCompat2 = captionStyleCompat;
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        Canvas canvas3 = canvas;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        boolean z5 = cue2.bitmap == null;
        int i9 = -16777216;
        if (z5) {
            if (!TextUtils.isEmpty(cue2.text)) {
                i9 = (!cue2.windowColorSet || !z3) ? captionStyleCompat2.windowColor : cue2.windowColor;
            } else {
                return;
            }
        }
        if (!areCharSequencesEqual(this.cueText, cue2.text)) {
            canvas2 = canvas;
        } else if (!Util.areEqual(this.cueTextAlignment, cue2.textAlignment) || this.cueBitmap != cue2.bitmap || this.cueLine != cue2.line || this.cueLineType != cue2.lineType) {
            canvas2 = canvas;
        } else if (!Util.areEqual(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue2.lineAnchor)) || this.cuePosition != cue2.position) {
            canvas2 = canvas;
        } else if (!Util.areEqual(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue2.positionAnchor)) || this.cueSize != cue2.size || this.cueBitmapHeight != cue2.bitmapHeight || this.applyEmbeddedStyles != z3 || this.applyEmbeddedFontSizes != z4 || this.foregroundColor != captionStyleCompat2.foregroundColor || this.backgroundColor != captionStyleCompat2.backgroundColor || this.windowColor != i9 || this.edgeType != captionStyleCompat2.edgeType || this.edgeColor != captionStyleCompat2.edgeColor) {
            canvas2 = canvas;
        } else if (Util.areEqual(this.textPaint.getTypeface(), captionStyleCompat2.typeface) && this.defaultTextSizePx == f4 && this.cueTextSizePx == f5 && this.bottomPaddingFraction == f6 && this.parentLeft == i5 && this.parentTop == i6 && this.parentRight == i7 && this.parentBottom == i8) {
            drawLayout(canvas, z5);
            return;
        } else {
            canvas2 = canvas;
        }
        this.cueText = cue2.text;
        this.cueTextAlignment = cue2.textAlignment;
        this.cueBitmap = cue2.bitmap;
        this.cueLine = cue2.line;
        this.cueLineType = cue2.lineType;
        this.cueLineAnchor = cue2.lineAnchor;
        this.cuePosition = cue2.position;
        this.cuePositionAnchor = cue2.positionAnchor;
        this.cueSize = cue2.size;
        this.cueBitmapHeight = cue2.bitmapHeight;
        this.applyEmbeddedStyles = z3;
        this.applyEmbeddedFontSizes = z4;
        this.foregroundColor = captionStyleCompat2.foregroundColor;
        this.backgroundColor = captionStyleCompat2.backgroundColor;
        this.windowColor = i9;
        this.edgeType = captionStyleCompat2.edgeType;
        this.edgeColor = captionStyleCompat2.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat2.typeface);
        this.defaultTextSizePx = f4;
        this.cueTextSizePx = f5;
        this.bottomPaddingFraction = f6;
        this.parentLeft = i5;
        this.parentTop = i6;
        this.parentRight = i7;
        this.parentBottom = i8;
        if (z5) {
            setupTextLayout();
        } else {
            setupBitmapLayout();
        }
        drawLayout(canvas2, z5);
    }

    private void setupTextLayout() {
        CharSequence charSequence;
        int i;
        int i2;
        int i3;
        int i4 = this.parentRight - this.parentLeft;
        int i5 = this.parentBottom - this.parentTop;
        this.textPaint.setTextSize(this.defaultTextSizePx);
        int i6 = (int) ((this.defaultTextSizePx * INNER_PADDING_RATIO) + 0.5f);
        int i7 = i6 * 2;
        int i8 = i4 - i7;
        float f = this.cueSize;
        if (f != Float.MIN_VALUE) {
            i8 = (int) (((float) i8) * f);
        }
        if (i8 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        CharSequence charSequence2 = this.cueText;
        if (!this.applyEmbeddedStyles) {
            charSequence2 = charSequence2.toString();
        } else if (!this.applyEmbeddedFontSizes) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence2);
            int length = spannableStringBuilder.length();
            AbsoluteSizeSpan[] absoluteSizeSpanArr = (AbsoluteSizeSpan[]) spannableStringBuilder.getSpans(0, length, AbsoluteSizeSpan.class);
            RelativeSizeSpan[] relativeSizeSpanArr = (RelativeSizeSpan[]) spannableStringBuilder.getSpans(0, length, RelativeSizeSpan.class);
            for (AbsoluteSizeSpan removeSpan : absoluteSizeSpanArr) {
                spannableStringBuilder.removeSpan(removeSpan);
            }
            for (RelativeSizeSpan removeSpan2 : relativeSizeSpanArr) {
                spannableStringBuilder.removeSpan(removeSpan2);
            }
            charSequence2 = spannableStringBuilder;
        } else if (this.cueTextSizePx > BitmapDescriptorFactory.HUE_RED) {
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(charSequence2);
            spannableStringBuilder2.setSpan(new AbsoluteSizeSpan((int) this.cueTextSizePx), 0, spannableStringBuilder2.length(), 16711680);
            charSequence2 = spannableStringBuilder2;
        }
        if (Color.alpha(this.backgroundColor) > 0) {
            SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(charSequence2);
            spannableStringBuilder3.setSpan(new BackgroundColorSpan(this.backgroundColor), 0, spannableStringBuilder3.length(), 16711680);
            charSequence = spannableStringBuilder3;
        } else {
            charSequence = charSequence2;
        }
        Alignment alignment = this.cueTextAlignment;
        if (alignment == null) {
            alignment = Alignment.ALIGN_CENTER;
        }
        Alignment alignment2 = alignment;
        StaticLayout staticLayout = new StaticLayout(charSequence, this.textPaint, i8, alignment2, this.spacingMult, this.spacingAdd, true);
        this.textLayout = staticLayout;
        int height = this.textLayout.getHeight();
        int lineCount = this.textLayout.getLineCount();
        int i9 = 0;
        for (int i10 = 0; i10 < lineCount; i10++) {
            i9 = Math.max((int) Math.ceil((double) this.textLayout.getLineWidth(i10)), i9);
        }
        if (this.cueSize == Float.MIN_VALUE || i9 >= i8) {
            i8 = i9;
        }
        int i11 = i8 + i7;
        float f2 = this.cuePosition;
        if (f2 != Float.MIN_VALUE) {
            int round = Math.round(((float) i4) * f2) + this.parentLeft;
            int i12 = this.cuePositionAnchor;
            if (i12 == 2) {
                round -= i11;
            } else if (i12 == 1) {
                round = ((round * 2) - i11) / 2;
            }
            i2 = Math.max(round, this.parentLeft);
            i = Math.min(i11 + i2, this.parentRight);
        } else {
            i2 = ((i4 - i11) / 2) + this.parentLeft;
            i = i2 + i11;
        }
        int i13 = i - i2;
        if (i13 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (invalid horizontal positioning)");
            return;
        }
        float f3 = this.cueLine;
        if (f3 != Float.MIN_VALUE) {
            if (this.cueLineType == 0) {
                i3 = Math.round(((float) i5) * f3) + this.parentTop;
            } else {
                int lineBottom = this.textLayout.getLineBottom(0) - this.textLayout.getLineTop(0);
                float f4 = this.cueLine;
                if (f4 >= BitmapDescriptorFactory.HUE_RED) {
                    i3 = Math.round(f4 * ((float) lineBottom)) + this.parentTop;
                } else {
                    i3 = Math.round((f4 + 1.0f) * ((float) lineBottom)) + this.parentBottom;
                }
            }
            int i14 = this.cueLineAnchor;
            if (i14 == 2) {
                i3 -= height;
            } else if (i14 == 1) {
                i3 = ((i3 * 2) - height) / 2;
            }
            int i15 = i3 + height;
            int i16 = this.parentBottom;
            if (i15 > i16) {
                i3 = i16 - height;
            } else {
                int i17 = this.parentTop;
                if (i3 < i17) {
                    i3 = i17;
                }
            }
        } else {
            i3 = (this.parentBottom - height) - ((int) (((float) i5) * this.bottomPaddingFraction));
        }
        StaticLayout staticLayout2 = new StaticLayout(charSequence, this.textPaint, i13, alignment2, this.spacingMult, this.spacingAdd, true);
        this.textLayout = staticLayout2;
        this.textLeft = i2;
        this.textTop = i3;
        this.textPaddingX = i6;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupBitmapLayout() {
        /*
            r7 = this;
            int r0 = r7.parentRight
            int r1 = r7.parentLeft
            int r0 = r0 - r1
            int r2 = r7.parentBottom
            int r3 = r7.parentTop
            int r2 = r2 - r3
            float r1 = (float) r1
            float r0 = (float) r0
            float r4 = r7.cuePosition
            float r4 = r4 * r0
            float r1 = r1 + r4
            float r3 = (float) r3
            float r2 = (float) r2
            float r4 = r7.cueLine
            float r4 = r4 * r2
            float r3 = r3 + r4
            float r4 = r7.cueSize
            float r0 = r0 * r4
            int r0 = java.lang.Math.round(r0)
            float r4 = r7.cueBitmapHeight
            r5 = 1
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 == 0) goto L_0x002e
            float r2 = r2 * r4
            int r2 = java.lang.Math.round(r2)
            goto L_0x0044
        L_0x002e:
            float r2 = (float) r0
            android.graphics.Bitmap r4 = r7.cueBitmap
            int r4 = r4.getHeight()
            float r4 = (float) r4
            android.graphics.Bitmap r5 = r7.cueBitmap
            int r5 = r5.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            float r2 = r2 * r4
            int r2 = java.lang.Math.round(r2)
        L_0x0044:
            int r4 = r7.cueLineAnchor
            r5 = 1
            r6 = 2
            if (r4 != r6) goto L_0x004d
            float r4 = (float) r0
        L_0x004b:
            float r1 = r1 - r4
            goto L_0x0053
        L_0x004d:
            if (r4 != r5) goto L_0x0053
            int r4 = r0 / 2
            float r4 = (float) r4
            goto L_0x004b
        L_0x0053:
            int r1 = java.lang.Math.round(r1)
            int r4 = r7.cuePositionAnchor
            if (r4 != r6) goto L_0x005e
            float r4 = (float) r2
        L_0x005c:
            float r3 = r3 - r4
            goto L_0x0064
        L_0x005e:
            if (r4 != r5) goto L_0x0064
            int r4 = r2 / 2
            float r4 = (float) r4
            goto L_0x005c
        L_0x0064:
            int r3 = java.lang.Math.round(r3)
            android.graphics.Rect r4 = new android.graphics.Rect
            int r0 = r0 + r1
            int r2 = r2 + r3
            r4.<init>(r1, r3, r0, r2)
            r7.bitmapRect = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupBitmapLayout():void");
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
        } else {
            drawBitmapLayout(canvas);
        }
    }

    private void drawTextLayout(Canvas canvas) {
        int i;
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout != null) {
            int save = canvas.save();
            canvas.translate((float) this.textLeft, (float) this.textTop);
            if (Color.alpha(this.windowColor) > 0) {
                this.paint.setColor(this.windowColor);
                canvas.drawRect((float) (-this.textPaddingX), BitmapDescriptorFactory.HUE_RED, (float) (staticLayout.getWidth() + this.textPaddingX), (float) staticLayout.getHeight(), this.paint);
            }
            int i2 = this.edgeType;
            boolean z = true;
            if (i2 == 1) {
                this.textPaint.setStrokeJoin(Join.ROUND);
                this.textPaint.setStrokeWidth(this.outlineWidth);
                this.textPaint.setColor(this.edgeColor);
                this.textPaint.setStyle(Style.FILL_AND_STROKE);
                staticLayout.draw(canvas);
            } else if (i2 == 2) {
                TextPaint textPaint2 = this.textPaint;
                float f = this.shadowRadius;
                float f2 = this.shadowOffset;
                textPaint2.setShadowLayer(f, f2, f2, this.edgeColor);
            } else if (i2 == 3 || i2 == 4) {
                if (this.edgeType != 3) {
                    z = false;
                }
                int i3 = -1;
                if (z) {
                    i = -1;
                } else {
                    i = this.edgeColor;
                }
                if (z) {
                    i3 = this.edgeColor;
                }
                float f3 = this.shadowRadius / 2.0f;
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Style.FILL);
                float f4 = -f3;
                this.textPaint.setShadowLayer(this.shadowRadius, f4, f4, i);
                staticLayout.draw(canvas);
                this.textPaint.setShadowLayer(this.shadowRadius, f3, f3, i3);
            }
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Style.FILL);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
            canvas.restoreToCount(save);
        }
    }

    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, null, this.bitmapRect, null);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }
}
