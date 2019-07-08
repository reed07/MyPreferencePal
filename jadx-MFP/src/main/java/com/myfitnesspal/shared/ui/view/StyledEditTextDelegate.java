package com.myfitnesspal.shared.ui.view;

import android.widget.EditText;
import com.uacf.core.util.Strings;

public class StyledEditTextDelegate extends StyledTextDelegate {
    public StyledEditTextDelegate(EditText editText) {
        super(editText);
    }

    /* access modifiers changed from: protected */
    public CharSequence obtainTextToMeasure() {
        return this.textView.getHint();
    }

    public float onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (Strings.isEmpty((Object) this.textView.getText())) {
            return super.onLayout(z, i, i2, i3, i4);
        }
        resetTextSize();
        return this.actualTextSize;
    }
}
