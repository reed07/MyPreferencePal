package com.myfitnesspal.shared.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v2.MfpEnergy;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.NumberUtils;
import javax.inject.Inject;

public abstract class NutritionFactsFragmentBase extends MfpFragment {
    private static final String DASH = "-";
    public static final int DISPLAY_MODE_DETAILED = 1;
    public static final int DISPLAY_MODE_SIMPLE = 0;
    public static final int ENERGY_MODE_CALCULATED = 0;
    public static final int ENERGY_MODE_PER_SERVING = 1;
    public static final int ENERGY_MODE_SCALED = 2;
    private static final String EXTRA_DISPLAY_MODE = "display_mode";
    private static final String EXTRA_ENERGY_MODE = "energy_mode";
    private static final String EXTRA_IS_FOR_MEALS = "is_for_meals";
    private static final String EXTRA_SCALE = "scale";
    private static final String UNIT_PERCENTAGE = "%";
    /* access modifiers changed from: protected */
    @BindView(2131363196)
    public View detailedView;
    /* access modifiers changed from: private */
    public int displayMode = 0;
    private int energyMode = 1;
    private boolean isForMeals;
    private MfpNutritionalContents nutritionalContents;
    private double scale = 1.0d;
    @BindView(2131363654)
    protected View showMore;
    @BindView(2131363198)
    protected View simpleView;
    @Inject
    protected UserEnergyService userEnergyService;

    /* access modifiers changed from: protected */
    public abstract int getLayoutResId();

    /* access modifiers changed from: protected */
    public abstract String getUnitGmDetailed();

    /* access modifiers changed from: protected */
    public abstract String getUnitGmSimple();

    /* access modifiers changed from: protected */
    public abstract String getUnitMg();

    /* access modifiers changed from: protected */
    public abstract void onDisplayModeChanged(int i);

    /* access modifiers changed from: protected */
    public abstract void onShowMoreClicked(int i);

    protected static void setArgumentsToFragment(NutritionFactsFragmentBase nutritionFactsFragmentBase, MfpNutritionalContents mfpNutritionalContents, int i, int i2, double d, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("nutritional_values", mfpNutritionalContents);
        bundle.putInt(EXTRA_ENERGY_MODE, i);
        bundle.putInt("display_mode", i2);
        bundle.putDouble(EXTRA_SCALE, d);
        bundle.putBoolean(EXTRA_IS_FOR_MEALS, z);
        nutritionFactsFragmentBase.setArguments(bundle);
    }

    public void setMultiplier(double d) {
        if (d != this.scale) {
            this.scale = d;
            redrawValues();
        }
    }

    public void setNutritionalContents(MfpNutritionalContents mfpNutritionalContents) {
        this.nutritionalContents = mfpNutritionalContents;
        redrawValues();
    }

    public void setNutritionalContentsAndMultiplier(MfpNutritionalContents mfpNutritionalContents, double d) {
        MfpNutritionalContents mfpNutritionalContents2 = this.nutritionalContents;
        if (mfpNutritionalContents2 == null || !mfpNutritionalContents2.equals(mfpNutritionalContents) || this.scale != d) {
            this.nutritionalContents = mfpNutritionalContents;
            this.scale = d;
            redrawValues();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.nutritionalContents = (MfpNutritionalContents) BundleUtils.getParcelable("nutritional_values", null, MfpNutritionalContents.class.getClassLoader(), bundle, getArguments());
        View inflate = layoutInflater.inflate(getLayoutResId(), viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        Bundle arguments = getArguments();
        this.displayMode = BundleUtils.getInt(bundle, "display_mode", BundleUtils.getInt(arguments, "display_mode", 0));
        this.energyMode = BundleUtils.getInt(bundle, EXTRA_ENERGY_MODE, BundleUtils.getInt(arguments, EXTRA_ENERGY_MODE, 1));
        this.scale = BundleUtils.getDouble(bundle, EXTRA_SCALE, BundleUtils.getDouble(arguments, EXTRA_SCALE, 1.0d));
        this.isForMeals = BundleUtils.getBoolean(arguments, EXTRA_IS_FOR_MEALS);
        setupClickListeners();
        setDisplayMode(this.displayMode);
        redrawValues();
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void setupClickListeners() {
        this.showMore.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NutritionFactsFragmentBase nutritionFactsFragmentBase = NutritionFactsFragmentBase.this;
                nutritionFactsFragmentBase.onShowMoreClicked(nutritionFactsFragmentBase.displayMode);
            }
        });
        this.simpleView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NutritionFactsFragmentBase nutritionFactsFragmentBase = NutritionFactsFragmentBase.this;
                nutritionFactsFragmentBase.onShowMoreClicked(nutritionFactsFragmentBase.displayMode);
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("display_mode", this.displayMode);
        bundle.putDouble(EXTRA_SCALE, this.scale);
        bundle.putInt(EXTRA_ENERGY_MODE, this.energyMode);
        bundle.putParcelable("nutritional_values", this.nutritionalContents);
    }

    /* access modifiers changed from: protected */
    public void setDisplayMode(int i) {
        this.displayMode = i;
        onDisplayModeChanged(i);
    }

    private void setValueLabel(View view, int i, double d, String str) {
        String str2 = DASH;
        if (d >= 0.0d) {
            str2 = String.format("%s %s", new Object[]{NumberUtils.localeStringFromFloatWithMaxFractionDigits((float) (d * getScale()), 1), str});
        }
        TextView textView = (TextView) view.findViewById(i);
        if (textView != null) {
            textView.setText(str2);
        }
    }

    private void setEnergy(View view, MfpEnergy mfpEnergy) {
        String energyType = getEnergyType();
        TextView textView = (TextView) view.findViewById(R.id.calories);
        if (this.energyMode == 1) {
            textView.setText(getActivity().getString(R.string.caloriesPerServing, new Object[]{energyType}));
        } else {
            textView.setText(energyType);
        }
        ((TextView) view.findViewById(R.id.txtCalories)).setText(getEnergyValue(mfpEnergy.getCaloriesValue()));
    }

    /* access modifiers changed from: protected */
    public String getEnergyValue(float f) {
        double d = (double) f;
        if (d <= MfpNutritionalContents.DEFAULT_VALUE.doubleValue()) {
            return DASH;
        }
        return this.userEnergyService.getDisplayableEnergy(d * getScale());
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String getEnergyType() {
        return getActivity().getString(this.userEnergyService.getCurrentEnergyStringId());
    }

    private void redrawValues() {
        View view = this.detailedView;
        View view2 = this.simpleView;
        MfpNutritionalContents mfpNutritionalContents = this.nutritionalContents;
        if (mfpNutritionalContents != null && view != null && view2 != null) {
            MfpEnergy energy = mfpNutritionalContents.getEnergy();
            MfpNutritionalContents mfpNutritionalContents2 = this.nutritionalContents;
            String unitGmDetailed = getUnitGmDetailed();
            String unitMg = getUnitMg();
            setEnergy(view, energy);
            View view3 = view;
            String str = unitGmDetailed;
            setValueLabel(view3, R.id.txtTotalFat, mfpNutritionalContents2.getFat().doubleValue(), str);
            setValueLabel(view3, R.id.txtSaturated, mfpNutritionalContents2.getSaturatedFat().doubleValue(), str);
            setValueLabel(view3, R.id.txtPolyunsaturated, mfpNutritionalContents2.getPolyunsaturatedFat().doubleValue(), str);
            setValueLabel(view3, R.id.txtMonosaturated, mfpNutritionalContents2.getMonounsaturatedFat().doubleValue(), str);
            setValueLabel(view3, R.id.txtTrans, mfpNutritionalContents2.getTransFat().doubleValue(), str);
            String str2 = unitMg;
            setValueLabel(view3, R.id.txtCholesterol, mfpNutritionalContents2.getCholesterol().doubleValue(), str2);
            setValueLabel(view3, R.id.txtSodium, mfpNutritionalContents2.getSodium().doubleValue(), str2);
            setValueLabel(view3, R.id.txtPotassium, mfpNutritionalContents2.getPotassium().doubleValue(), str2);
            String str3 = unitGmDetailed;
            setValueLabel(view3, R.id.txtTotalCarbs, mfpNutritionalContents2.getCarbohydrates().doubleValue(), str3);
            setValueLabel(view3, R.id.txtDietaryFiber, mfpNutritionalContents2.getFiber().doubleValue(), str3);
            setValueLabel(view3, R.id.txtSugars, mfpNutritionalContents2.getSugar().doubleValue(), str3);
            setValueLabel(view3, R.id.txtProtein, mfpNutritionalContents2.getProtein().doubleValue(), str3);
            setValueLabel(view3, R.id.txtVitaminA, mfpNutritionalContents2.getVitaminA().doubleValue(), UNIT_PERCENTAGE);
            setValueLabel(view3, R.id.txtVitaminC, mfpNutritionalContents2.getVitaminC().doubleValue(), UNIT_PERCENTAGE);
            setValueLabel(view3, R.id.txtCalcium, mfpNutritionalContents2.getCalcium().doubleValue(), UNIT_PERCENTAGE);
            setValueLabel(view3, R.id.txtIron, mfpNutritionalContents2.getIron().doubleValue(), UNIT_PERCENTAGE);
            if (ConfigUtils.isNewNutrientsForUsEnabled(getConfigService())) {
                View view4 = view;
                setValueLabel(view4, R.id.txtVitaminD, mfpNutritionalContents2.getVitaminD().doubleValue(), UNIT_PERCENTAGE);
                String str4 = unitGmDetailed;
                setValueLabel(view4, R.id.txtAddedSugars, mfpNutritionalContents2.getAddedSugars().doubleValue(), str4);
                setValueLabel(view4, R.id.txtSugarAlcohols, mfpNutritionalContents2.getSugarAlcohols().doubleValue(), str4);
            }
            redrawSimpleValues(view2, mfpNutritionalContents2);
        }
    }

    /* access modifiers changed from: protected */
    public void redrawSimpleValues(View view, MfpNutritionalContents mfpNutritionalContents) {
        setEnergy(view, mfpNutritionalContents.getEnergy());
        String unitGmSimple = getUnitGmSimple();
        View view2 = view;
        String str = unitGmSimple;
        setValueLabel(view2, R.id.txtTotalFat, mfpNutritionalContents.getFat().doubleValue(), str);
        setValueLabel(view2, R.id.txtTotalCarbs, mfpNutritionalContents.getCarbohydrates().doubleValue(), str);
        setValueLabel(view2, R.id.txtProtein, mfpNutritionalContents.getProtein().doubleValue(), str);
    }

    /* access modifiers changed from: protected */
    public MfpNutritionalContents getNutritionalContents() {
        return this.nutritionalContents;
    }

    /* access modifiers changed from: protected */
    public boolean isForMeals() {
        return this.isForMeals;
    }

    /* access modifiers changed from: protected */
    public double getScale() {
        switch (this.energyMode) {
            case 0:
                return this.scale;
            case 1:
                return 1.0d;
            case 2:
                double d = this.scale;
                double d2 = 0.0d;
                if (d != 0.0d) {
                    d2 = 1.0d / d;
                }
                return d2;
            default:
                return 1.0d;
        }
    }
}
