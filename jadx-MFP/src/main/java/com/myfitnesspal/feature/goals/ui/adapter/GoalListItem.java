package com.myfitnesspal.feature.goals.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.adapter.ListItem;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public class GoalListItem implements ListItem {
    private static final int ITEM_VIEW_TYPE = GoalListItem.class.getCanonicalName().hashCode();
    private String goalId;
    private String goalItemDisplayValue;
    private int goalType;
    private String goalUnitAbbreviation;
    private float goalValue;
    private int iconResId;
    private boolean isChecked;
    private boolean showCheckBox;
    private boolean showIconOnRight;
    private String sideTitle;
    private String subtitle;
    private String title;

    private GoalListItem() {
    }

    public static GoalListItem newInstance(int i, String str) {
        return new GoalListItem().setGoalType(i).setTitle(str);
    }

    public static GoalListItem newInstance(int i, String str, String str2) {
        return newInstance(i, str).setGoalItemDisplayValue(str2);
    }

    public static GoalListItem newInstance(int i, String str, String str2, String str3, float f, String str4) {
        return newInstance(i, str2, str3).setGoalId(str).setGoalUnitAbbreviation(str4).setGoalValue(f);
    }

    private GoalListItem setGoalType(int i) {
        this.goalType = i;
        return this;
    }

    private GoalListItem setGoalId(String str) {
        this.goalId = str;
        return this;
    }

    private GoalListItem setGoalItemDisplayValue(String str) {
        this.goalItemDisplayValue = str;
        return this;
    }

    private GoalListItem setTitle(String str) {
        this.title = str;
        return this;
    }

    public GoalListItem setSideTitle(String str) {
        this.sideTitle = str;
        return this;
    }

    public GoalListItem setSubtitle(String str) {
        this.subtitle = str;
        return this;
    }

    public GoalListItem setGoalValue(float f) {
        this.goalValue = f;
        return this;
    }

    public GoalListItem setGoalUnitAbbreviation(String str) {
        this.goalUnitAbbreviation = str;
        return this;
    }

    public GoalListItem setIconResId(int i) {
        this.iconResId = i;
        return this;
    }

    public GoalListItem setShowIconOnRight(boolean z) {
        this.showIconOnRight = z;
        return this;
    }

    public GoalListItem setShowCheckBox(boolean z, boolean z2) {
        this.showCheckBox = z;
        this.isChecked = z2;
        return this;
    }

    public int getViewType() {
        return ITEM_VIEW_TYPE;
    }

    public View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.goal_row, viewGroup, false);
        }
        TextView textView = (TextView) ViewUtils.findById(view, R.id.title);
        textView.setText(this.title);
        if (!this.showIconOnRight) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.iconResId, 0);
        }
        TextView textView2 = (TextView) ViewUtils.findById(view, R.id.side_title);
        ViewUtils.setVisible(Strings.notEmpty(this.sideTitle), textView2);
        textView2.setText(this.sideTitle);
        TextView textView3 = (TextView) ViewUtils.findById(view, R.id.subtitle);
        ViewUtils.setVisible(Strings.notEmpty(this.subtitle), textView3);
        textView3.setText(this.subtitle);
        setupRightSide(view);
        return view;
    }

    private void setupRightSide(View view) {
        TextView textView = (TextView) ViewUtils.findById(view, R.id.text);
        textView.setText(this.goalItemDisplayValue);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.showIconOnRight ? this.iconResId : 0, 0);
        CompoundButton compoundButton = (CompoundButton) ViewUtils.findById(view, R.id.checkbox);
        ViewUtils.setVisible(this.showCheckBox, compoundButton);
        compoundButton.setChecked(this.isChecked);
    }

    public String getTitle() {
        return this.title;
    }

    public float getGoalValue() {
        return this.goalValue;
    }

    public String getGoalId() {
        return this.goalId;
    }

    public int getGoalType() {
        return this.goalType;
    }

    public String getGoalUnitAbbreviation() {
        return this.goalUnitAbbreviation;
    }
}
