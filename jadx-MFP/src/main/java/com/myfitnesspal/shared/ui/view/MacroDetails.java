package com.myfitnesspal.shared.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;

public class MacroDetails extends LinearLayout {
    @BindView(2131363962)
    TextView macroName;
    @BindView(2131363963)
    TextView macroPercent;
    @BindView(2131363964)
    TextView macroValue;

    public MacroDetails(Context context) {
        super(context);
        init(context);
    }

    public MacroDetails(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    @TargetApi(21)
    public MacroDetails(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.new_nutrition_macro_details, this, true);
        ButterKnife.bind((View) this);
        setOrientation(1);
        setGravity(1);
        setPadding(0, getResources().getDimensionPixelSize(R.dimen.material_padding_4), 0, 0);
    }

    public void setMacroName(String str, int i) {
        this.macroName.setText(str);
        this.macroName.setTextColor(i);
    }

    public void setMacroValue(String str) {
        this.macroValue.setText(str);
    }

    public void setMacroPercent(String str) {
        this.macroPercent.setText(str);
    }
}
