package com.myfitnesspal.feature.appgallery.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.ui.view.CheckableItem;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ViewUtils;
import java.util.Map;

public class GoogleFitPermissionsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutId = R.layout.permission_item;
    private final Map<Integer, CheckableItem> permissionItems;

    private static class ViewHolder {
        final TextView permissionName;
        final CheckBox state;

        public ViewHolder(View view) {
            this.permissionName = (TextView) ViewUtils.findById(view, R.id.permission_name);
            this.state = (CheckBox) ViewUtils.findById(view, R.id.state);
        }

        /* access modifiers changed from: 0000 */
        public void bind(CheckableItem checkableItem) {
            this.permissionName.setText(checkableItem.getDescription());
            this.state.setChecked(checkableItem.getState());
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public GoogleFitPermissionsAdapter(LayoutInflater layoutInflater, Map<Integer, CheckableItem> map) {
        this.inflater = layoutInflater;
        this.permissionItems = map;
    }

    public int getCount() {
        return CollectionUtils.size(this.permissionItems);
    }

    public CheckableItem getItem(int i) {
        return (CheckableItem) this.permissionItems.get(Integer.valueOf(i));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(this.layoutId, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        ((ViewHolder) view.getTag()).bind((CheckableItem) this.permissionItems.get(Integer.valueOf(i)));
        return view;
    }
}
