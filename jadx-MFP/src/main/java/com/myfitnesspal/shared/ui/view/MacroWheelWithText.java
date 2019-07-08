package com.myfitnesspal.shared.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.ui.view.MacroWheel;

public class MacroWheelWithText extends FrameLayout {
    @BindView(2131362475)
    TextView energyUnit;
    @BindView(2131362477)
    TextView energyValue;
    @BindView(2131362978)
    MacroWheel macroWheel;

    public MacroWheelWithText(Context context) {
        super(context);
        init(context);
    }

    public MacroWheelWithText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    @TargetApi(21)
    public MacroWheelWithText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.macro_wheel_with_text, this, true);
        ButterKnife.bind((View) this);
    }

    public void setMacroWheelValues(int i, int i2, int i3) {
        this.macroWheel.setValues(i, i2, i3);
    }

    public void setEnergyValue(String str) {
        this.energyValue.setText(str);
    }

    public void setEnergyUnit(String str) {
        this.energyUnit.setText(str);
    }
}
