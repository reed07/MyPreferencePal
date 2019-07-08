package com.myfitnesspal.shared.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.uacf.core.util.ViewUtils;

public class CustomMacroProgressBar extends LinearLayout {
    @BindView(2131363960)
    TextView goalPercent;
    @BindView(2131363961)
    TextView goalValue;
    @BindView(2131362982)
    TextView macroName;
    @BindView(2131362983)
    ProgressBar macroProgress;
    @BindView(2131362871)
    View premiumLock;

    public CustomMacroProgressBar(Context context) {
        super(context);
        init(context);
    }

    public CustomMacroProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    @TargetApi(21)
    public CustomMacroProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    public void setMacroName(String str, int i, boolean z) {
        this.macroName.setText(str);
        if (z) {
            setMacroNameColor(i);
        }
    }

    public void setMacroNameColor(int i) {
        this.macroName.setTextColor(i);
    }

    public void setGoalPercent(String str) {
        this.goalPercent.setText(str);
    }

    public void setGoalValue(String str) {
        this.goalValue.setText(str);
    }

    public void setProgress(int i, int i2) {
        this.macroProgress.setProgress(i);
        this.macroProgress.setMax(100);
        if (VERSION.SDK_INT < 21) {
            this.macroProgress.getProgressDrawable().setColorFilter(ContextCompat.getColor(getContext(), i2), Mode.SRC_IN);
        } else {
            this.macroProgress.setProgressTintList(ColorStateList.valueOf(getResources().getColor(i2)));
        }
    }

    public void showLock() {
        ViewUtils.setVisible(this.premiumLock);
        ViewUtils.setGone(this.goalPercent);
        ViewUtils.setGone(this.goalValue);
        if (VERSION.SDK_INT < 21) {
            this.macroProgress.getProgressDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.black), Mode.SRC_IN);
        }
    }

    public void hideLock() {
        ViewUtils.setGone(this.premiumLock);
        ViewUtils.setVisible(this.goalPercent);
        ViewUtils.setVisible(this.goalValue);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_macro_progress, this, true);
        ButterKnife.bind((View) this);
        setOrientation(1);
        hideLock();
    }
}
