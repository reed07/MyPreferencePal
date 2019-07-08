package com.myfitnesspal.feature.appgallery.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.model.AppItemModel;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ViewUtils;
import java.util.Collection;
import java.util.List;

public class AppItemListAdapter<T extends AppItemModel> extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutId;
    private List<T> list;

    private static class ViewHolder {
        TextView description;
        MfpImageView imageView;
        TextView name;

        public ViewHolder(View view) {
            this.name = (TextView) ViewUtils.findById(view, R.id.title);
            this.description = (TextView) ViewUtils.findById(view, R.id.text);
            this.imageView = (MfpImageView) ViewUtils.findById(view, R.id.image);
        }

        /* access modifiers changed from: 0000 */
        public void bind(AppItemModel appItemModel) {
            this.imageView.setUrl(appItemModel.getImageUrl());
            this.name.setText(appItemModel.getName());
            this.description.setText(appItemModel.getDescription());
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public AppItemListAdapter(LayoutInflater layoutInflater, List<T> list2, int i) {
        this.inflater = layoutInflater;
        this.list = list2;
        this.layoutId = i;
    }

    public int getCount() {
        return CollectionUtils.size((Collection<?>) this.list);
    }

    public T getItem(int i) {
        return (AppItemModel) this.list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(this.layoutId, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        ((ViewHolder) view.getTag()).bind((AppItemModel) this.list.get(i));
        return view;
    }
}
