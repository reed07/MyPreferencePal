package com.myfitnesspal.feature.dashboard.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.BindViews;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.Nutrient;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.NextButtonEvent;
import com.myfitnesspal.shared.model.v2.MfpGoalDisplay;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.VersionUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;

public class CustomNutrientDashboardSelectionFragment extends MfpFragment {
    public static final String CUSTOM_GOALS_DISPLAY_TYPE = "nutrients_remaining";
    private static final int REQUIRED_SELECTION_COUNT = 3;
    private static final int SELECTED_CHECKBOXES_REQUIRED_FOR_TOOLBAR_NEXT = 3;
    private static final Map<Integer, Nutrient> VIEW_ID_TO_NUTRIENT = new HashMap();
    private Handler handler = new Handler();
    @BindViews({2131362101, 2131362095, 2131362102, 2131362099, 2131362098, 2131362105, 2131362094, 2131362103, 2131362100, 2131362093, 2131362096, 2131362104, 2131362106, 2131362107, 2131362097, 2131362092})
    List<CheckBox> nutrientCheckBoxes;
    final OnCheckedChangeListener onCheckboxChangeListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            CustomNutrientDashboardSelectionFragment.this.setCountBasedOnButtonState(z);
        }
    };
    @Inject
    Lazy<PremiumApiErrorUtil> premiumApiErrorUtil;
    private int selectedCount;
    @BindView(2131363924)
    TextView tvNutrientSelected;

    static {
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbProtein), Nutrient.Protein);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbFat), Nutrient.Fat);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbSatFat), Nutrient.SaturatedFat);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbPolyFat), Nutrient.PolyUnsaturatedFat);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbMonoFat), Nutrient.MonoUnsaturatedFat);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbTransFat), Nutrient.TransFat);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbCholesterol), Nutrient.Cholesterol);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbSodium), Nutrient.Sodium);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbPotassium), Nutrient.Potassium);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbCarbs), Nutrient.Carbohydrates);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbFiber), Nutrient.Fiber);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbSugar), Nutrient.Sugar);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbVitaminA), Nutrient.VitaminA);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbVitaminC), Nutrient.VitaminC);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbIron), Nutrient.Iron);
        VIEW_ID_TO_NUTRIENT.put(Integer.valueOf(R.id.cbCalcium), Nutrient.Calcium);
    }

    public static CustomNutrientDashboardSelectionFragment newInstance(Bundle bundle) {
        CustomNutrientDashboardSelectionFragment customNutrientDashboardSelectionFragment = new CustomNutrientDashboardSelectionFragment();
        customNutrientDashboardSelectionFragment.setArguments(bundle);
        return customNutrientDashboardSelectionFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        return layoutInflater.inflate(R.layout.fragment_custom_summary, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setTitle(R.string.fragment_custom_summary_title, new Object[0]);
        setupViewState();
        setPaddingAndEventListeners();
    }

    public void onResume() {
        super.onResume();
        this.handler.post(new Runnable() {
            public void run() {
                CustomNutrientDashboardSelectionFragment.this.getActivity().invalidateOptionsMenu();
            }
        });
    }

    public void save(final Function0 function0, final Function0 function02) {
        ArrayList selectedNutrientApiKeys = getSelectedNutrientApiKeys();
        if (CollectionUtils.size((Collection<?>) selectedNutrientApiKeys) == 3) {
            selectedNutrientApiKeys.add(Nutrient.Energy.getApiKey());
            MfpGoalDisplay mfpGoalDisplay = new MfpGoalDisplay();
            mfpGoalDisplay.setDisplayType(CUSTOM_GOALS_DISPLAY_TYPE);
            mfpGoalDisplay.setNutrients(selectedNutrientApiKeys);
            getSession().getUser().updateGoalDisplays(new Function1<List<MfpGoalDisplay>>() {
                public void execute(List<MfpGoalDisplay> list) {
                    function0.execute();
                }
            }, new Function1<List<Exception>>() {
                public void execute(List<Exception> list) {
                    function02.execute();
                    ((PremiumApiErrorUtil) CustomNutrientDashboardSelectionFragment.this.premiumApiErrorUtil.get()).showAlertForApiError(list, CustomNutrientDashboardSelectionFragment.this.getString(R.string.error_could_not_set_nutrient_dashboard));
                }
            }, mfpGoalDisplay);
            return;
        }
        function02.execute();
    }

    private void setupViewState() {
        List fromApiKeys = Nutrient.fromApiKeys(BundleUtils.getStringList(getArguments(), Extras.NUTRIENTS_KEY));
        if (fromApiKeys != null) {
            this.selectedCount = fromApiKeys.size();
            selectNutrients(fromApiKeys);
            setSelectedNutrientText();
            checkEnableAcceptButton();
        }
    }

    private void selectNutrients(List<Nutrient> list) {
        for (Nutrient checkBoxSelected : list) {
            setCheckBoxSelected(checkBoxSelected);
        }
    }

    private void setPaddingAndEventListeners() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.custom_summary_padding_top_bottom);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(VersionUtils.isOsVersionLessThan(17) ? R.dimen.custom_summary_padding_left_ics_or_lower : R.dimen.custom_summary_padding_left);
        for (CheckBox checkBox : this.nutrientCheckBoxes) {
            checkBox.setPadding(dimensionPixelSize2, dimensionPixelSize, 0, dimensionPixelSize);
            checkBox.setOnCheckedChangeListener(this.onCheckboxChangeListener);
        }
    }

    /* access modifiers changed from: private */
    public void setCountBasedOnButtonState(boolean z) {
        this.selectedCount += z ? 1 : -1;
        setSelectedNutrientText();
        checkEnableAcceptButton();
    }

    private void checkEnableAcceptButton() {
        postEvent(new NextButtonEvent().setEnabled(this.selectedCount == 3));
    }

    private void setSelectedNutrientText() {
        this.tvNutrientSelected.setText(getString(this.selectedCount > 3 ? R.string.nutrients_too_many_selected : R.string.nutrients_x_of_3_selected, Integer.valueOf(this.selectedCount)));
    }

    private void setCheckBoxSelected(final Nutrient nutrient) {
        List list = (List) Enumerable.where((Collection<T>) VIEW_ID_TO_NUTRIENT.entrySet(), (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, Entry<Integer, Nutrient>>() {
            public Boolean execute(Entry<Integer, Nutrient> entry) {
                return Boolean.valueOf(entry.getValue() == nutrient);
            }
        });
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            final int intValue = ((Integer) ((Entry) list.get(0)).getKey()).intValue();
            CheckBox checkBox = (CheckBox) Enumerable.firstOrDefault(this.nutrientCheckBoxes, new ReturningFunction1<Boolean, CheckBox>() {
                public Boolean execute(CheckBox checkBox) {
                    return Boolean.valueOf(checkBox.getId() == intValue);
                }
            });
            if (checkBox != null) {
                checkBox.setChecked(true);
            }
        }
    }

    private ArrayList<String> getSelectedNutrientApiKeys() {
        ArrayList<String> arrayList = new ArrayList<>(3);
        for (CheckBox checkBox : this.nutrientCheckBoxes) {
            if (checkBox.isChecked()) {
                arrayList.add(((Nutrient) VIEW_ID_TO_NUTRIENT.get(Integer.valueOf(checkBox.getId()))).getApiKey());
            }
        }
        return arrayList;
    }
}
