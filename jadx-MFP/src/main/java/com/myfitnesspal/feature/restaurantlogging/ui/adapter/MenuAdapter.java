package com.myfitnesspal.feature.restaurantlogging.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.listener.OnMenuItemActionListener;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuListItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuSection;
import com.myfitnesspal.feature.restaurantlogging.util.MultiAddMenuItemHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.view.ViewHolder;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MfpMenuListItem> {
    private final LayoutInflater inflater;
    private OnMenuItemActionListener itemActionListener;
    private final List<MfpMenuListItem> mfpMenuListItems = new ArrayList();
    private final Lazy<MultiAddMenuItemHelper> multiAddMenuItemHelper;
    private final Lazy<UserEnergyService> userEnergyService;

    private static class HeaderViewHolder extends ViewHolder<MfpMenuListItem> {
        private final TextView headerTextView;

        private HeaderViewHolder(View view) {
            super(view);
            this.headerTextView = (TextView) findById(R.id.txtFoodName);
        }

        public void setData(MfpMenuListItem mfpMenuListItem, int i) {
            this.headerTextView.setText(mfpMenuListItem.getName());
        }
    }

    private static class ItemViewHolder extends ViewHolder<MfpMenuItem> {
        private final TextView headerTextView;
        private final TextView infoTextView;
        /* access modifiers changed from: private */
        public final OnMenuItemActionListener itemActionListener;
        private final CheckBox multiAddCheckBox;
        private final Lazy<MultiAddMenuItemHelper> multiAddMenuItemHelper;
        private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ItemViewHolder.this.itemActionListener.onItemCheckToggled(compoundButton, (MfpMenuItem) compoundButton.getTag(), z);
            }
        };
        private final Lazy<UserEnergyService> userEnergyService;

        protected ItemViewHolder(View view, Lazy<UserEnergyService> lazy, Lazy<MultiAddMenuItemHelper> lazy2, OnMenuItemActionListener onMenuItemActionListener) {
            super(view);
            this.userEnergyService = lazy;
            this.multiAddMenuItemHelper = lazy2;
            this.itemActionListener = onMenuItemActionListener;
            this.headerTextView = (TextView) findById(R.id.text_primary);
            this.infoTextView = (TextView) findById(R.id.text_secondary);
            this.multiAddCheckBox = (CheckBox) findById(R.id.multiSelectCheckBox);
        }

        public void setData(MfpMenuItem mfpMenuItem, int i) {
            this.headerTextView.setText(mfpMenuItem.getName());
            List matches = mfpMenuItem.getMatches();
            boolean z = matches == null;
            if (z) {
                this.infoTextView.setText(R.string.fetch_nutrition_info);
            } else if (matches.isEmpty()) {
                this.infoTextView.setText(R.string.find_estimate);
            } else {
                this.infoTextView.setText(mfpMenuItem.getDisplayableEnergyString((UserEnergyService) this.userEnergyService.get()));
            }
            ViewUtils.setVisible(!z && ((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).isEnabled(), this.multiAddCheckBox);
            this.multiAddCheckBox.setTag(mfpMenuItem);
            this.multiAddCheckBox.setOnCheckedChangeListener(null);
            this.multiAddCheckBox.setChecked(((MultiAddMenuItemHelper) this.multiAddMenuItemHelper.get()).containsItem(mfpMenuItem));
            this.multiAddCheckBox.setOnCheckedChangeListener(this.onCheckedChangeListener);
        }
    }

    private enum ViewType {
        Header,
        Item
    }

    public MenuAdapter(Context context, Lazy<UserEnergyService> lazy, Lazy<MultiAddMenuItemHelper> lazy2) {
        super(context, 0);
        this.userEnergyService = lazy;
        this.multiAddMenuItemHelper = lazy2;
        this.inflater = LayoutInflater.from(context);
    }

    public void setItems(List<MfpMenuListItem> list) {
        this.mfpMenuListItems.clear();
        this.mfpMenuListItems.addAll(list);
        setNotifyOnChange(false);
        clear();
        addAll(list);
        notifyDataSetChanged();
    }

    public void setItemActionListener(OnMenuItemActionListener onMenuItemActionListener) {
        this.itemActionListener = onMenuItemActionListener;
    }

    public List<MfpMenuListItem> getItems() {
        return this.mfpMenuListItems;
    }

    public int getViewTypeCount() {
        return ViewType.values().length;
    }

    public int getItemViewType(int i) {
        ViewType viewType;
        if (((MfpMenuListItem) getItem(i)) instanceof MfpMenuSection) {
            viewType = ViewType.Header;
        } else {
            viewType = ViewType.Item;
        }
        return viewType.ordinal();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        MfpMenuListItem mfpMenuListItem = (MfpMenuListItem) getItem(i);
        ViewType viewType = ViewType.values()[getItemViewType(i)];
        if (view == null) {
            Object obj = null;
            switch (viewType) {
                case Header:
                    view = this.inflater.inflate(R.layout.menu_header, viewGroup, false);
                    obj = new HeaderViewHolder(view);
                    break;
                case Item:
                    view = this.inflater.inflate(R.layout.generic_list_item_with_checkbox, viewGroup, false);
                    obj = new ItemViewHolder(view, this.userEnergyService, this.multiAddMenuItemHelper, this.itemActionListener);
                    break;
            }
            view.setTag(obj);
        }
        ((ViewHolder) view.getTag()).setData(mfpMenuListItem, i);
        return view;
    }

    public boolean isEnabled(int i) {
        return ViewType.values()[getItemViewType(i)] == ViewType.Item;
    }
}
