package com.myfitnesspal.shared.ui.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.btothefifth.patched.R;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.ui.view.MacroDetails;

import java.text.DecimalFormat;

import lanchon.dexpatcher.annotation.DexAction;
import lanchon.dexpatcher.annotation.DexEdit;
import lanchon.dexpatcher.annotation.DexIgnore;
import lanchon.dexpatcher.annotation.DexWrap;

import static android.content.Context.MODE_PRIVATE;

@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public class NutritionFactsFragmentBase extends Fragment {
    @DexIgnore
    public View detailedView;

    @DexIgnore
    protected View simpleView;

    @DexWrap
    private void redrawValues(){
        redrawValues();
        if (getNutritionalContents()!=null)
        {
            //getNutritionalContents().calculateNetCarbs();
            //((TextView)this.getActivity().findViewById(R.id.netCarbs)).setText(new DecimalFormat("#0.0").format(getNutritionalContents().calculateNetCarbs().doubleValue())+" g");

            //Utils.findRequiredView(view, R.id.nutritionFactsDetailed, "field 'detailedView'")

            setValueLabel(detailedView, R.id.txtNetCarbs, getNutritionalContents().calculateNetCarbs(), "g");
        }
    }

    @DexIgnore
    public MfpNutritionalContents getNutritionalContents() {
        return null;
    }

    @DexIgnore
    private void setValueLabel(View view, int i, double d, String str) {

    }

    @DexIgnore
    public double getScale() {
        return 0.0;
    }
}
