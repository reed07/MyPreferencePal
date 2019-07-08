package com.myfitnesspal.shared.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btothefifth.patched.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanchon.dexpatcher.annotation.DexAction;
import lanchon.dexpatcher.annotation.DexEdit;
import lanchon.dexpatcher.annotation.DexIgnore;

@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public class MacroDetails extends LinearLayout {
    TextView macroName;
    TextView macroPercent;
    TextView macroValue;

    @DexIgnore
    public MacroDetails(Context context) {
        super(context);
        init(context);
    }

    @DexIgnore
    public MacroDetails(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    @DexIgnore
    public MacroDetails(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    @DexIgnore
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.new_nutrition_macro_details, this, true);
        ButterKnife.bind((View) this);
        setOrientation(1);
        setGravity(1);
        setPadding(0, getResources().getDimensionPixelSize(R.dimen.material_padding_4), 0, 0);
    }

    @DexIgnore
    public void setMacroName(String str, int i) {
        this.macroName.setText(str);
        this.macroName.setTextColor(i);
    }

    @DexIgnore
    public void setMacroValue(String str) {
        this.macroValue.setText(str);
    }

    @DexIgnore
    public void setMacroPercent(String str) {
        this.macroPercent.setText(str);
    }
}
