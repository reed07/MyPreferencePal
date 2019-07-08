package com.myfitnesspal.shared.ui.view;

import android.view.View;
import com.uacf.core.util.ViewUtils;

public abstract class ViewHolder<T> {
    protected final View parent;

    public abstract void setData(T t, int i);

    protected ViewHolder(View view) {
        this.parent = view;
    }

    /* access modifiers changed from: protected */
    public <U extends View> U findById(int i) {
        return ViewUtils.findById(this.parent, i);
    }

    /* access modifiers changed from: protected */
    public View getParent() {
        return this.parent;
    }
}
