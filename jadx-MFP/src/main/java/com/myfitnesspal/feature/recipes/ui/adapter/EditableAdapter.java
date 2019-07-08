package com.myfitnesspal.feature.recipes.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase.EditListAdapter;

public abstract class EditableAdapter<T> extends EditListAdapter<T> {
    /* access modifiers changed from: protected */
    public abstract void onItemClicked(AdapterView<?> adapterView, View view, int i, long j);

    public EditableAdapter(ListView listView, MfpEditableFragmentBase mfpEditableFragmentBase, Context context) {
        super(mfpEditableFragmentBase, context);
        onListViewRecreated(listView);
    }

    public void onListViewRecreated(ListView listView) {
        if (listView != null) {
            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    EditableAdapter.this.onItemClicked(adapterView, view, i, j);
                }
            });
        }
    }

    public int getCount() {
        if (super.getCount() == 0) {
            return 0;
        }
        return super.getCount();
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        return super.getView(i, view, viewGroup);
    }
}
