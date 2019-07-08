package com.myfitnesspal.shared.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.TextView.BufferType;

public class StyledButton extends AppCompatButton {
    protected StyledTextDelegate delegate;

    public StyledButton(Context context) {
        super(context);
        init(null);
    }

    public StyledButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public StyledButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        this.delegate = new StyledTextDelegate(this);
        this.delegate.updateFromAttrs(attributeSet);
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        StyledTextDelegate styledTextDelegate = this.delegate;
        if (styledTextDelegate != null) {
            charSequence = styledTextDelegate.getStyledText(charSequence);
        }
        super.setText(charSequence, bufferType);
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        StyledTextDelegate styledTextDelegate = this.delegate;
        if (styledTextDelegate != null) {
            styledTextDelegate.onTextChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.delegate.onSizeChanged(i, i2, i3, i4);
    }

    public void setTextSize(float f) {
        super.setTextSize(f);
        this.delegate.setTextSize(f);
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        this.delegate.setTextSize(f);
    }

    public void setLineSpacing(float f, float f2) {
        super.setLineSpacing(f, f2);
        this.delegate.setLineSpacing(f, f2);
    }

    public void setMaxTextSize(float f) {
        this.delegate.setMaxTextSize(f);
    }

    public float getMaxTextSize() {
        return this.delegate.getMaxTextSize();
    }

    public void setMinTextSize(float f) {
        this.delegate.setMinTextSize(f);
    }

    public float getMinTextSize() {
        return this.delegate.getMinTextSize();
    }

    public void setAddEllipsis(boolean z) {
        this.delegate.setShouldAddEllipsis(z);
    }

    public boolean getAddEllipsis() {
        return this.delegate.shouldAddEllipsis();
    }

    public void setResizeMode(int i) {
        this.delegate.setResizeMode(i);
    }

    public int getResizeMode() {
        return this.delegate.getResizeMode();
    }

    public void resetTextSize() {
        if (this.delegate.resetTextSize()) {
            super.setTextSize(0, this.delegate.getTextSize());
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"WrongCall"})
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.setTextSize(0, this.delegate.onLayout(z, i, i2, i3, i4));
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.delegate.postRequestLayout();
        }
    }
}
