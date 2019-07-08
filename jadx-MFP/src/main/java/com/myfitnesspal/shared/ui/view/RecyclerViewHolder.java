package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.uacf.core.util.ViewUtils;

public abstract class RecyclerViewHolder<T> extends ViewHolder {
    /* access modifiers changed from: protected */
    public final Context context = this.itemView.getContext();

    public abstract void setData(T t, int i);

    public RecyclerViewHolder(int i, ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
        ButterKnife.bind((Object) this, this.itemView);
    }

    /* access modifiers changed from: protected */
    public <U extends View> U findById(int i) {
        return ViewUtils.findById(this.itemView, i);
    }

    /* access modifiers changed from: protected */
    public View getParent() {
        return this.itemView;
    }

    public Context getContext() {
        return this.context;
    }
}
