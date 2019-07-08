package com.myfitnesspal.feature.recipes.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.ui.dialog.SortFilterDialogBase;
import com.myfitnesspal.shared.model.CheckableListItem;
import java.util.ArrayList;
import java.util.List;

public class RecipeFoodMealSortDialogFragment extends SortFilterDialogBase<CheckableListItem> {
    private static final String EXTRA_SELECTED_ORDER = "extra_selected_order";
    private OnSortOrderSelectedListener listener;

    public interface OnSortOrderSelectedListener {
        void onSortOrderSelected(MyItemsSortOrder myItemsSortOrder);
    }

    /* access modifiers changed from: protected */
    public int getTitleResId() {
        return R.string.sort_order;
    }

    public static RecipeFoodMealSortDialogFragment newInstance(MyItemsSortOrder myItemsSortOrder) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SELECTED_ORDER, myItemsSortOrder.typeId);
        RecipeFoodMealSortDialogFragment recipeFoodMealSortDialogFragment = new RecipeFoodMealSortDialogFragment();
        recipeFoodMealSortDialogFragment.setArguments(bundle);
        return recipeFoodMealSortDialogFragment;
    }

    public void setListener(OnSortOrderSelectedListener onSortOrderSelectedListener) {
        this.listener = onSortOrderSelectedListener;
    }

    /* access modifiers changed from: protected */
    public List<CheckableListItem> getItemsList(int i) {
        MyItemsSortOrder fromTypeId = MyItemsSortOrder.fromTypeId(getArguments().getInt(EXTRA_SELECTED_ORDER, MyItemsSortOrder.Default.typeId));
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        arrayList.add(new CheckableListItem(getString(MyItemsSortOrder.Default.titleId), MyItemsSortOrder.Default == fromTypeId, MyItemsSortOrder.Default));
        arrayList.add(new CheckableListItem(getString(MyItemsSortOrder.Ascending.titleId), MyItemsSortOrder.Ascending == fromTypeId, MyItemsSortOrder.Ascending));
        String string = getString(MyItemsSortOrder.Descending.titleId);
        if (MyItemsSortOrder.Descending != fromTypeId) {
            z = false;
        }
        arrayList.add(new CheckableListItem(string, z, MyItemsSortOrder.Descending));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.listener != null) {
            this.listener.onSortOrderSelected((MyItemsSortOrder) ((CheckableListItem) view.getTag()).getTag());
        }
    }
}
