package com.myfitnesspal.framework.mvvm;

import android.databinding.ObservableList;
import android.databinding.ObservableList.OnListChangedCallback;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import com.uacf.core.util.CollectionUtils;
import java.util.Collection;

public abstract class BindableListAdapter<T> extends BaseAdapter {
    private LayoutInflater inflater;
    private ObservableList<T> list;
    private OnListChangedCallback listChangedCallback = new OnListChangedCallback() {
        public void onChanged(ObservableList observableList) {
            BindableListAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            BindableListAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            BindableListAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            BindableListAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            BindableListAdapter.this.notifyDataSetChanged();
        }
    };

    public long getItemId(int i) {
        return (long) i;
    }

    public BindableListAdapter(LayoutInflater layoutInflater, ObservableList<T> observableList) {
        this.inflater = layoutInflater;
        this.list = observableList;
        this.list.addOnListChangedCallback(this.listChangedCallback);
    }

    public void destroy() {
        ObservableList<T> observableList = this.list;
        if (observableList != null) {
            observableList.removeOnListChangedCallback(this.listChangedCallback);
            this.list = null;
        }
        this.inflater = null;
    }

    public void setItems(ObservableList<T> observableList) {
        ObservableList<T> observableList2 = this.list;
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

    public Object getItem(int i) {
        ObservableList<T> observableList = this.list;
        if (observableList != null) {
            return observableList.get(i);
        }
        return null;
    }

    public int getCount() {
        return CollectionUtils.size((Collection<?>) this.list);
    }

    /* access modifiers changed from: protected */
    public final LayoutInflater getInflater() {
        return this.inflater;
    }
}
