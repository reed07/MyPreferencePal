package com.myfitnesspal.feature.search.ui.adapter.holder;

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
        emptyItemViewHolder.noResultFound = (TextView) Utils.findRequiredViewAsType(view, R.id.primary_empty_text, "field 'noResultFound'", TextView.class);
        emptyItemViewHolder.searchAllFoods = (TextView) Utils.findRequiredViewAsType(view, R.id.secondary_empty_text, "field 'searchAllFoods'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        EmptyItemViewHolder emptyItemViewHolder = this.target;
        if (emptyItemViewHolder != null) {
            this.target = null;
            emptyItemViewHolder.noResultFound = null;
            emptyItemViewHolder.searchAllFoods = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
