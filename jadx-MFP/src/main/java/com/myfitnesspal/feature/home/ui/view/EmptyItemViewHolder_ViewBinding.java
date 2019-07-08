package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EmptyItemViewHolder_ViewBinding implements Unbinder {
    private EmptyItemViewHolder target;

    @UiThread
    public EmptyItemViewHolder_ViewBinding(EmptyItemViewHolder emptyItemViewHolder, View view) {
        this.target = emptyItemViewHolder;
        emptyItemViewHolder.messageTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.message_text, "field 'messageTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        EmptyItemViewHolder emptyItemViewHolder = this.target;
        if (emptyItemViewHolder != null) {
            this.target = null;
            emptyItemViewHolder.messageTextView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
