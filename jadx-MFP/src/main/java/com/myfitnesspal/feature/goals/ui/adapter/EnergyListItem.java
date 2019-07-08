package com.myfitnesspal.feature.goals.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.adapter.ListItem;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public final class EnergyListItem implements ListItem {
    public static final int ITEM_VIEW_TYPE = EnergyListItem.class.getCanonicalName().hashCode();
    private Display display;
    private String subtitle;
    private String title;
    private String value;

    public enum Display {
        Percent,
        Grams
    }

    private static class Holder {
        TextView subtitleView;
        TextView titleView;
        TextView valueView;

        public Holder(View view) {
            this.titleView = (TextView) ViewUtils.findById(view, R.id.title);
            this.subtitleView = (TextView) ViewUtils.findById(view, R.id.subtitle);
            this.valueView = (TextView) ViewUtils.findById(view, R.id.value);
        }
    }

    public EnergyListItem(String str, String str2, String str3, Display display2) {
        this.title = str;
        this.subtitle = str2;
        this.value = str3;
        this.display = display2;
    }

    public View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.energy_list_row, viewGroup, false);
            view.setTag(new Holder(view));
        }
        Holder holder = (Holder) view.getTag();
        holder.titleView.setText(this.title);
        ViewUtils.setVisible(Strings.notEmpty(this.subtitle), holder.subtitleView);
        holder.subtitleView.setText(this.subtitle);
        holder.valueView.setText(this.value);
        holder.valueView.setTextColor(view.getResources().getColor(this.display == Display.Grams ? R.color.light_grey_text : R.color.profile_preference_value));
        return view;
    }

    public int getViewType() {
        return ITEM_VIEW_TYPE;
    }
}
