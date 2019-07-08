package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import com.myfitnesspal.shared.ui.view.TextWatcherAdapter.TextWatcherListener;
import com.uacf.core.util.Strings;

public class ClearableEditText extends MFPEditTextWithPreImeBackListener implements OnFocusChangeListener, OnTouchListener, TextWatcherListener {
    private static final int DRAWABLE_BOTTOM = 3;
    private static final int DRAWABLE_LEFT = 0;
    private static final int DRAWABLE_RIGHT = 2;
    private static final int DRAWABLE_TOP = 1;
    private Drawable clearButton;
    private OnClearTextListener clearTextListener;
    private OnFocusChangeListener onFocusChangeListener;
    private OnTouchListener onTouchListener;
    private TextWatcher textWatcherListener;

    public interface OnClearTextListener {
        void onTextCleared();
    }

    public ClearableEditText(Context context) {
        super(context);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setOnTouchListener(OnTouchListener onTouchListener2) {
        this.onTouchListener = onTouchListener2;
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener2) {
        this.onFocusChangeListener = onFocusChangeListener2;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (getCompoundDrawables()[2] != null) {
            if (motionEvent.getX() > ((float) ((getWidth() - getPaddingRight()) - this.clearButton.getIntrinsicWidth()))) {
                if (motionEvent.getAction() == 1) {
                    setText("");
                    OnClearTextListener onClearTextListener = this.clearTextListener;
                    if (onClearTextListener != null) {
                        onClearTextListener.onTextCleared();
                    }
                }
                return true;
            }
        }
        OnTouchListener onTouchListener2 = this.onTouchListener;
        if (onTouchListener2 != null) {
            return onTouchListener2.onTouch(view, motionEvent);
        }
        return false;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            setClearIconVisible(Strings.notEmpty((Object) getText()));
        } else {
            setClearIconVisible(false);
        }
        OnFocusChangeListener onFocusChangeListener2 = this.onFocusChangeListener;
        if (onFocusChangeListener2 != null) {
            onFocusChangeListener2.onFocusChange(view, z);
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (isFocused()) {
            setClearIconVisible(Strings.notEmpty((Object) charSequence));
        }
        TextWatcher textWatcher = this.textWatcherListener;
        if (textWatcher != null) {
            textWatcher.onTextChanged(charSequence, i, i2, i3);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        TextWatcher textWatcher = this.textWatcherListener;
        if (textWatcher != null) {
            textWatcher.beforeTextChanged(charSequence, i, i2, i3);
        }
    }

    public void afterTextChanged(Editable editable) {
        TextWatcher textWatcher = this.textWatcherListener;
        if (textWatcher != null) {
            textWatcher.afterTextChanged(editable);
        }
    }

    private void init() {
        this.clearButton = getCompoundDrawables()[2];
        if (this.clearButton == null) {
            this.clearButton = getResources().getDrawable(17301610);
        }
        Drawable drawable = this.clearButton;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.clearButton.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this));
    }

    /* access modifiers changed from: protected */
    public void setClearIconVisible(boolean z) {
        if (z != (getCompoundDrawables()[2] != null)) {
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], z ? this.clearButton : null, getCompoundDrawables()[3]);
        }
    }

    public void setOnClearTextListener(OnClearTextListener onClearTextListener) {
        this.clearTextListener = onClearTextListener;
    }

    public void setTextWatcherListener(TextWatcher textWatcher) {
        this.textWatcherListener = textWatcher;
    }
}
