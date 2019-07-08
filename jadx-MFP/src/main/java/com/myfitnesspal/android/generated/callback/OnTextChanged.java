package com.myfitnesspal.android.generated.callback;

public final class OnTextChanged implements android.databinding.adapters.TextViewBindingAdapter.OnTextChanged {
    final Listener mListener;
    final int mSourceId;

    public interface Listener {
        void _internalCallbackOnTextChanged(int i, CharSequence charSequence, int i2, int i3, int i4);
    }

    public OnTextChanged(Listener listener, int i) {
        this.mListener = listener;
        this.mSourceId = i;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mListener._internalCallbackOnTextChanged(this.mSourceId, charSequence, i, i2, i3);
    }
}
