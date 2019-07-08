package com.myfitnesspal.feature.goals.ui.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.model.MealGoalsFragmentViewModel;
import com.myfitnesspal.feature.goals.model.MealGoalsInputMode;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v2.MealGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;

public class MealGoalsAdapter extends Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public final MealGoalsInputMode inputMode;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    /* access modifiers changed from: private */
    public final MealGoalsFragmentViewModel mealGoalsFragmentViewModel;
    /* access modifiers changed from: private */
    public final Lazy<UserEnergyService> userEnergyService;

    interface MealGoalChangedListener {
        void onMealGoalChanged(double d, int i);

        void onMealGoalsFocusChanged();
    }

    private static class MealGoalEditTextListener implements TextWatcher, OnFocusChangeListener {
        private MealGoalChangedListener listener;
        private int position;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private MealGoalEditTextListener() {
        }

        public void setMealGoalStateListener(MealGoalChangedListener mealGoalChangedListener) {
            this.listener = mealGoalChangedListener;
        }

        public void updatePosition(int i) {
            this.position = i;
        }

        public void afterTextChanged(Editable editable) {
            double tryParseDouble = NumberUtils.tryParseDouble(Strings.toString(editable));
            MealGoalChangedListener mealGoalChangedListener = this.listener;
            if (mealGoalChangedListener != null) {
                mealGoalChangedListener.onMealGoalChanged(tryParseDouble, this.position);
            }
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                this.listener.onMealGoalsFocusChanged();
            }
        }
    }

    public static class ViewHolder extends RecyclerViewHolder<MealGoal> {
        @BindView(2131363017)
        View container;
        private boolean isEnergyInputMode;
        private Lazy<LocalizedStringsUtil> localizedStringsUtil;
        private MealGoalChangedListener mealGoalChangedListener;
        private MealGoalEditTextListener mealGoalEditTextListener;
        @BindView(2131363016)
        EditText mealGoalEditView;
        @BindView(2131363018)
        View mealGoalPercent;
        @BindView(2131363019)
        TextView mealGoalTxtView;
        @BindView(2131363031)
        TextView mealNameView;
        private MealNames mealNames;
        private MfpMeasuredValue total;
        private Lazy<UserEnergyService> userEnergyService;

        public ViewHolder(ViewGroup viewGroup, MealNames mealNames2, MfpMeasuredValue mfpMeasuredValue, boolean z, Lazy<UserEnergyService> lazy, Lazy<LocalizedStringsUtil> lazy2) {
            super(R.layout.meal_goal_item, viewGroup);
            this.mealNames = mealNames2;
            this.total = mfpMeasuredValue;
            this.isEnergyInputMode = z;
            this.userEnergyService = lazy;
            this.localizedStringsUtil = lazy2;
            setListeners();
        }

        public void setData(MealGoal mealGoal, int i) {
            this.mealGoalEditView.removeTextChangedListener(this.mealGoalEditTextListener);
            this.mealNameView.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(this.mealNames.nameForIndex(mealGoal.getMealIndex()), (UserEnergyService) this.userEnergyService.get()));
            MfpMeasuredValue mfpMeasuredValue = this.total;
            double value = mfpMeasuredValue != null ? (double) mfpMeasuredValue.getValue() : 0.0d;
            MfpMeasuredValue energy = mealGoal.getEnergy();
            setRowValues(LocalizedEnergy.fromMeasuredValue(energy), value > 0.0d ? NumberUtils.localeStringFromDouble(((energy != null ? (double) energy.getValue() : 0.0d) / value) * 100.0d, 0) : "0");
            this.mealGoalEditTextListener = new MealGoalEditTextListener();
            this.mealGoalEditTextListener.setMealGoalStateListener(this.mealGoalChangedListener);
            this.mealGoalEditView.addTextChangedListener(this.mealGoalEditTextListener);
            this.mealGoalEditView.setOnFocusChangeListener(this.mealGoalEditTextListener);
            this.mealGoalEditTextListener.updatePosition(i);
        }

        public void setMealGoalChangedListener(MealGoalChangedListener mealGoalChangedListener2) {
            this.mealGoalChangedListener = mealGoalChangedListener2;
        }

        private void setEditView(String str) {
            this.mealGoalEditView.setText(str);
        }

        /* access modifiers changed from: private */
        public void setTextView(String str) {
            this.mealGoalTxtView.setText(str);
        }

        private void setRowValues(LocalizedEnergy localizedEnergy, String str) {
            Energy userCurrentEnergyUnit = ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit();
            if (this.isEnergyInputMode) {
                setTextView(String.format(this.context.getString(R.string.percent_format), new Object[]{str}));
                setEditView(LocalizedEnergy.getRoundedDisplayStringWithoutUnit(this.context, localizedEnergy, userCurrentEnergyUnit));
                ViewUtils.setVisible(false, this.mealGoalPercent);
                return;
            }
            setTextView(LocalizedEnergy.getRoundedDisplayString(this.context, localizedEnergy, userCurrentEnergyUnit));
            setEditView(str);
            ViewUtils.setVisible(true, this.mealGoalPercent);
        }

        private void setListeners() {
            this.container.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ViewHolder.this.mealGoalEditView.requestFocus();
                    Context context = ViewHolder.this.getContext();
                    ViewHolder.this.getContext();
                    ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(ViewHolder.this.mealGoalEditView, 1);
                    if (ViewHolder.this.mealGoalEditView.getText() != null) {
                        ViewHolder.this.mealGoalEditView.setSelection(ViewHolder.this.mealGoalEditView.getText().length());
                    }
                }
            });
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.container = Utils.findRequiredView(view, R.id.meal_goal_item_container, "field 'container'");
            viewHolder.mealNameView = (TextView) Utils.findRequiredViewAsType(view, R.id.meal_name, "field 'mealNameView'", TextView.class);
            viewHolder.mealGoalTxtView = (TextView) Utils.findRequiredViewAsType(view, R.id.meal_goal_text_view, "field 'mealGoalTxtView'", TextView.class);
            viewHolder.mealGoalEditView = (EditText) Utils.findRequiredViewAsType(view, R.id.meal_goal_edit_view, "field 'mealGoalEditView'", EditText.class);
            viewHolder.mealGoalPercent = Utils.findRequiredView(view, R.id.meal_goal_percent, "field 'mealGoalPercent'");
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.container = null;
                viewHolder.mealNameView = null;
                viewHolder.mealGoalTxtView = null;
                viewHolder.mealGoalEditView = null;
                viewHolder.mealGoalPercent = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MealGoalsAdapter(MealGoalsFragmentViewModel mealGoalsFragmentViewModel2, Lazy<UserEnergyService> lazy, Lazy<LocalizedStringsUtil> lazy2, MealGoalsInputMode mealGoalsInputMode) {
        this.mealGoalsFragmentViewModel = mealGoalsFragmentViewModel2;
        this.userEnergyService = lazy;
        this.localizedStringsUtil = lazy2;
        this.inputMode = mealGoalsInputMode;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = new ViewHolder(viewGroup, this.mealGoalsFragmentViewModel.getMealNames(), this.mealGoalsFragmentViewModel.getTotalMeasuredValue(), isEnergyInputMode(), this.userEnergyService, this.localizedStringsUtil);
        return viewHolder;
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        MealGoal mealGoal = (MealGoal) this.mealGoalsFragmentViewModel.getMealGoals().get(i);
        viewHolder.setMealGoalChangedListener(new MealGoalChangedListener() {
            public void onMealGoalChanged(double d, int i) {
                MealGoalsAdapter.this.mealGoalsFragmentViewModel.mealGoalsChanged(d, i, MealGoalsAdapter.this.inputMode);
                if (MealGoalsAdapter.this.isEnergyInputMode()) {
                    ViewHolder viewHolder = viewHolder;
                    viewHolder.setTextView(String.format(viewHolder.getContext().getString(R.string.percent_format), new Object[]{MealGoalsAdapter.this.mealGoalsFragmentViewModel.getPercentStringForIndex(i)}));
                    return;
                }
                ViewHolder viewHolder2 = viewHolder;
                viewHolder2.setTextView(LocalizedEnergy.getRoundedDisplayString(viewHolder2.getContext(), MealGoalsAdapter.this.mealGoalsFragmentViewModel.getEnergyStringForIndex(i), ((UserEnergyService) MealGoalsAdapter.this.userEnergyService.get()).getUserCurrentEnergyUnit()));
            }

            public void onMealGoalsFocusChanged() {
                MealGoalsAdapter.this.mealGoalsFragmentViewModel.mealGoalRequestedFocus();
            }
        });
        viewHolder.setData(mealGoal, i);
    }

    public int getItemCount() {
        return CollectionUtils.size((Collection<?>) this.mealGoalsFragmentViewModel.getMealGoals());
    }

    /* access modifiers changed from: private */
    public boolean isEnergyInputMode() {
        return this.inputMode == MealGoalsInputMode.Energy;
    }
}
