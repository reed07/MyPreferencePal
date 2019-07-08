package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;

public class LinearLayoutListAdapterView extends LinearLayoutAdapterView {
    private ListAdapter adapter;
    private DataSetObserver observer = new DataSetObserver() {
        public void onChanged() {
            super.onChanged();
            LinearLayoutListAdapterView.this.recreateChildren();
        }

        public void onInvalidated() {
            super.onInvalidated();
            LinearLayoutListAdapterView.this.recreateChildren();
        }
    };

    public LinearLayoutListAdapterView(Context context) {
        super(context);
    }

    public LinearLayoutListAdapterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LinearLayoutListAdapterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAdapter(ListAdapter listAdapter) {
        ListAdapter listAdapter2 = this.adapter;
        if (listAdapter2 != listAdapter) {
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(this.observer);
            }
            this.adapter = listAdapter;
            ListAdapter listAdapter3 = this.adapter;
            if (listAdapter3 != null) {
                listAdapter3.registerDataSetObserver(this.observer);
            }
        }
        recreateChildren();
    }

    /* access modifiers changed from: protected */
    public View createView(int i) {
        return this.adapter.getView(i, null, this);
    }

    /* access modifiers changed from: protected */
    public int getCount() {
        ListAdapter listAdapter = this.adapter;
        if (listAdapter == null) {
            return 0;
        }
        return listAdapter.getCount();
    }

    /* access modifiers changed from: protected */
    public void recreateChildren() {
        super.recreateChildren();
    }

    public ListAdapter getAdapter() {
        return this.adapter;
    }
}
