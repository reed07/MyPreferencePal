package com.myfitnesspal.shared.ui.view;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.util.FontUtils;
import com.uacf.core.util.Strings;

public class StyledTextDelegate {
    private static final String ELLIPSIS = "…";
    private static final float MIN_TEXT_SIZE = 10.0f;
    private static final Canvas textResizeCanvas = new Canvas();
    protected float actualTextSize;
    private boolean addEllipsis = true;
    private boolean capitalized;
    private boolean mNeedsResize = false;
    private float maxTextSize = BitmapDescriptorFactory.HUE_RED;
    private float minTextSize = MIN_TEXT_SIZE;
    private Runnable postLayoutRunnable = new Runnable() {
        public void run() {
            if (StyledTextDelegate.this.textView != null && StyledTextDelegate.this.textView.getParent() != null) {
                StyledTextDelegate.this.textView.getParent().requestLayout();
                ((View) StyledTextDelegate.this.textView.getParent()).postInvalidate();
            }
        }
    };
    private int resizeMode = 0;
    private float spacingAdd = BitmapDescriptorFactory.HUE_RED;
    private float spacingMult = 1.0f;
    protected float textSize;
    protected final TextView textView;
    private boolean treatTextAsHtml;

    public static final class ResizeModes {
        public static final int HEIGHT = 2;
        public static final int NONE = 0;
        public static final int WIDTH = 1;
    }

    public StyledTextDelegate(TextView textView2) {
        this.textView = textView2;
    }

    public void updateFromAttrs(AttributeSet attributeSet) {
        if (!this.textView.isInEditMode()) {
            this.textSize = this.textView.getTextSize();
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = this.textView.getContext().obtainStyledAttributes(attributeSet, R.styleable.mfp_StyledTextView);
                setFont(obtainStyledAttributes != null ? obtainStyledAttributes.getString(2) : null);
                setCapitalized(obtainStyledAttributes.getBoolean(1, false));
                setTreatTextAsHtml(obtainStyledAttributes.getBoolean(6, false));
                setText(this.textView.getText());
                setMinTextSize(obtainStyledAttributes.getDimension(4, MIN_TEXT_SIZE));
                setMaxTextSize(obtainStyledAttributes.getDimension(3, BitmapDescriptorFactory.HUE_RED));
                setResizeMode(obtainStyledAttributes.getInt(5, 0));
                onTextChanged();
                obtainStyledAttributes.recycle();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setFont(String str) {
        if (Strings.notEmpty(str)) {
            Typeface typeface = FontUtils.getTypeface(this.textView.getContext(), str);
            if (typeface != null) {
                this.textView.setTypeface(typeface);
            }
        }
    }

    public boolean isCapitalized() {
        return this.capitalized;
    }

    public void setCapitalized(boolean z) {
        this.capitalized = z;
    }

    public boolean isTreatTextAsHtml() {
        return this.treatTextAsHtml;
    }

    public void setTreatTextAsHtml(boolean z) {
        this.treatTextAsHtml = z;
        if (z) {
            MovementMethod movementMethod = this.textView.getMovementMethod();
            if (movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) {
                this.textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
    }

    public CharSequence getStyledText(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        if (this.capitalized) {
            charSequence = Strings.toString(charSequence).toUpperCase();
        }
        return charSequence;
    }

    public boolean shouldAddEllipsis() {
        return this.addEllipsis;
    }

    public void setShouldAddEllipsis(boolean z) {
        this.addEllipsis = z;
    }

    public float getMaxTextSize() {
        return this.maxTextSize;
    }

    public void setMaxTextSize(float f) {
        this.maxTextSize = f;
        this.textView.requestLayout();
        this.textView.invalidate();
    }

    public float getMinTextSize() {
        return this.minTextSize;
    }

    public void setMinTextSize(float f) {
        this.minTextSize = f;
        this.textView.requestLayout();
        this.textView.invalidate();
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    public void setResizeMode(int i) {
        this.resizeMode = i;
    }

    public float getSpacingAdd() {
        return this.spacingAdd;
    }

    public void setSpacingMult(float f) {
        this.spacingMult = f;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float f) {
        this.textSize = f;
    }

    public void onTextChanged() {
        this.mNeedsResize = true;
        resetTextSize();
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            this.mNeedsResize = true;
        }
    }

    public void setLineSpacing(float f, float f2) {
        this.spacingMult = f2;
        this.spacingAdd = f;
    }

    public boolean resetTextSize() {
        float f = this.textSize;
        if (f <= BitmapDescriptorFactory.HUE_RED) {
            return false;
        }
        this.maxTextSize = f;
        this.actualTextSize = f;
        return true;
    }

    public float onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z || this.mNeedsResize) {
            return resizeText(((i3 - i) - this.textView.getCompoundPaddingLeft()) - this.textView.getCompoundPaddingRight(), ((i4 - i2) - this.textView.getCompoundPaddingBottom()) - this.textView.getCompoundPaddingTop());
        }
        return this.actualTextSize;
    }

    public void postRequestLayout() {
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.post(this.postLayoutRunnable);
        }
    }

    private float resizeText(int i, int i2) {
        CharSequence obtainTextToMeasure = obtainTextToMeasure();
        if (Strings.isEmpty((Object) obtainTextToMeasure) || i2 <= 0 || i <= 0 || this.textSize == BitmapDescriptorFactory.HUE_RED) {
            return this.actualTextSize;
        }
        TextPaint paint = this.textView.getPaint();
        float f = this.maxTextSize;
        float min = f > BitmapDescriptorFactory.HUE_RED ? Math.min(this.textSize, f) : this.textSize;
        Rect textSize2 = getTextSize(obtainTextToMeasure, paint, min);
        float f2 = min;
        while (isOutOfBounds(textSize2, i, i2)) {
            float f3 = this.minTextSize;
            if (f2 <= f3) {
                break;
            }
            f2 = Math.max(f2 - 2.0f, f3);
            textSize2 = getTextSize(obtainTextToMeasure, paint, f2);
        }
        if (this.addEllipsis && f2 == this.minTextSize && isOutOfBounds(textSize2, i, i2)) {
            TextPaint textPaint = new TextPaint(paint);
            StaticLayout staticLayout = new StaticLayout(obtainTextToMeasure, textPaint, i, Alignment.ALIGN_NORMAL, this.spacingMult, this.spacingAdd, false);
            if (staticLayout.getLineCount() > 0) {
                int lineForVertical = staticLayout.getLineForVertical(i2) - 1;
                if (lineForVertical < 0) {
                    setText("");
                } else {
                    int lineStart = staticLayout.getLineStart(lineForVertical);
                    int lineEnd = staticLayout.getLineEnd(lineForVertical);
                    float lineWidth = staticLayout.getLineWidth(lineForVertical);
                    float measureText = textPaint.measureText(ELLIPSIS);
                    while (lineStart < lineEnd && ((float) i) < lineWidth + measureText) {
                        lineEnd--;
                        lineWidth = textPaint.measureText(obtainTextToMeasure.subSequence(lineStart, lineEnd + 1).toString());
                    }
                    if (lineEnd >= 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(obtainTextToMeasure.subSequence(0, lineEnd));
                        sb.append(ELLIPSIS);
                        setText(sb.toString());
                    }
                }
            }
        }
        setLineSpacing(this.spacingAdd, this.spacingMult);
        this.mNeedsResize = false;
        this.actualTextSize = f2;
        return this.actualTextSize;
    }

    /* access modifiers changed from: protected */
    public CharSequence obtainTextToMeasure() {
        return this.textView.getText();
    }

    private Rect getTextSize(CharSequence charSequence, TextPaint textPaint, float f) {
        TextPaint textPaint2 = new TextPaint(textPaint);
        textPaint2.setTextSize(f);
        StaticLayout staticLayout = new StaticLayout(charSequence, textPaint2, Integer.MAX_VALUE, Alignment.ALIGN_NORMAL, this.spacingMult, this.spacingAdd, true);
        staticLayout.draw(textResizeCanvas);
        return new Rect(0, 0, getLayoutWidth(staticLayout), staticLayout.getHeight());
    }

    private boolean isOutOfBounds(Rect rect, int i, int i2) {
        if ((this.resizeMode & 1) == 1 && rect.width() > i) {
            return true;
        }
        if ((this.resizeMode & 2) != 2 || rect.height() <= i2) {
            return false;
        }
        return true;
    }

    private int getLayoutWidth(StaticLayout staticLayout) {
        int i = 0;
        for (int i2 = 0; i2 < staticLayout.getLineCount(); i2++) {
            i = (int) Math.max((float) i, staticLayout.getLineWidth(i2));
        }
        return i;
    }

    private void setText(CharSequence charSequence) {
        TextView textView2 = this.textView;
        if (this.treatTextAsHtml) {
            charSequence = Html.fromHtml(Strings.toString(charSequence));
        }
        textView2.setText(charSequence);
    }
}
