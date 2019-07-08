package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class MFPEditTextWithPreImeBackListener extends AppCompatEditText {
    private MfpImeBackListener mOnImeBack;

    public MFPEditTextWithPreImeBackListener(Context context) {
        super(context);
    }

    public MFPEditTextWithPreImeBackListener(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MFPEditTextWithPreImeBackListener(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            MfpImeBackListener mfpImeBackListener = this.mOnImeBack;
            if (mfpImeBackListener != null) {
                mfpImeBackListener.onImeBack(this, getText().toString());
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void setOnKeyPreImeListener(MfpImeBackListener mfpImeBackListener) {
        this.mOnImeBack = mfpImeBackListener;
    }
}
