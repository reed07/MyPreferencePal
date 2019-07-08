package com.myfitnesspal.feature.goals.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.adapter.ListItem;
import com.uacf.core.util.ViewUtils;

public class CustomDailyGoalListItem implements ListItem {
    public static final int ITEM_VIEW_TYPE = CustomDailyGoalListItem.class.getCanonicalName().hashCode();
    private String subtitle;
    private String title;

    private static class Holder {
        TextView subtitleView;
        TextView titleView;

        public Holder(View view) {
            this.titleView = (TextView) ViewUtils.findById(view, R.id.title);
            this.subtitleView = (TextView) ViewUtils.findById(view, R.id.subtitle);
        }
    }

    public CustomDailyGoalListItem(String str, String str2) {
        this.title = str;
        this.subtitle = str2;
    }

    public String getTitle() {
        return this.title;
    }

    public View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.custom_daily_goal_row, viewGroup, false);
            view.setTag(new Holder(view));
        }
        Holder holder = (Holder) view.getTag();
        holder.titleView.setText(this.title);
        holder.subtitleView.setText(this.subtitle);
        return view;
    }

    public int getViewType() {
        return ITEM_VIEW_TYPE;
    }
}
