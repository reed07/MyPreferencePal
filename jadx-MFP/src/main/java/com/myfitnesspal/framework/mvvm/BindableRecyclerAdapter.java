package com.myfitnesspal.framework.mvvm;

import android.databinding.ObservableList;
import android.databinding.ObservableList.OnListChangedCallback;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import com.uacf.core.util.CollectionUtils;
import java.util.Collection;

public abstract class BindableRecyclerAdapter<HolderT extends ViewHolder, ItemT> extends Adapter<HolderT> {
    private LayoutInflater inflater;
    private ObservableList<ItemT> list;
    private OnListChangedCallback listChangedCallback = new OnListChangedCallback() {
        public void onChanged(ObservableList observableList) {
            BindableRecyclerAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            BindableRecyclerAdapter.this.notifyItemRangeChanged(i, i2);
        }

        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            BindableRecyclerAdapter.this.notifyItemRangeInserted(i, i2);
        }

        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            BindableRecyclerAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            BindableRecyclerAdapter.this.notifyItemRangeRemoved(i, i2);
        }
    };

    public BindableRecyclerAdapter(LayoutInflater layoutInflater, ObservableList<ItemT> observableList) {
        this.list = observableList;
        this.inflater = layoutInflater;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        ObservableList<ItemT> observableList = this.list;
        if (observableList != null) {
            observableList.addOnListChangedCallback(this.listChangedCallback);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        ObservableList<ItemT> observableList = this.list;
        if (observableList != null) {
            observableList.removeOnListChangedCallback(this.listChangedCallback);
        }
    }

    public void setItems(ObservableList<ItemT> observableList) {
        ObservableList<ItemT> observableList2 = this.list;
        if (observableList2 != observableList) {
            if (observableList2 != null) {
                observableList2.removeOnListChangedCallback(this.listChangedCallback);
            }
            this.list = observableList;
            if (observableList != null) {
                this.list.addOnListChangedCallback(this.listChangedCallback);
            }
            notifyDataSetChanged();
        }
    }

    public int getItemCount() {
        return CollectionUtils.size((Collection<?>) this.list);
    }

    /* access modifiers changed from: protected */
    public ObservableList<ItemT> getItems() {
        return this.list;
    }

    /* access modifiers changed from: protected */
    public LayoutInflater getInflater() {
        return this.inflater;
    }
}
