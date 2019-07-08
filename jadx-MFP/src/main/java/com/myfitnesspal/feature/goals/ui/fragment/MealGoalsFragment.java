package com.myfitnesspal.feature.goals.ui.fragment;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.model.MealGoalsFragmentViewModel;
import com.myfitnesspal.feature.goals.model.MealGoalsFragmentViewModel.Property;
import com.myfitnesspal.feature.goals.model.MealGoalsInputMode;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.EditCustomMacroGoalsActivity;
import com.myfitnesspal.feature.goals.ui.activity.MealGoalStateListener;
import com.myfitnesspal.feature.goals.ui.adapter.MealGoalsAdapter;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.DividerItemDecorator;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MealGoalsFragment extends MfpFragment {
    public static final String TOTAL_HUNDRED_PERCENT = "100";
    private MealGoalsAdapter adapter;
    @BindView(2131362050)
    TextView calculationError;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @BindView(2131363021)
    RecyclerView mealGoalsRecyclerView;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @BindView(2131363876)
    TextView totalName;
    @BindView(2131363020)
    View totalPercent;
    @BindView(2131363877)
    TextView totalValue;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    private MealGoalsFragmentViewModel viewModel;

    public static MealGoalsFragment newInstance(MfpDailyGoal mfpDailyGoal, List<String> list, MealGoalsInputMode mealGoalsInputMode) {
        MealGoalsFragment mealGoalsFragment = new MealGoalsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Extras.DAILY_GOAL, mfpDailyGoal);
        bundle.putStringArrayList("meal_names", new ArrayList(list));
        bundle.putSerializable(Extras.INPUT_MODE, mealGoalsInputMode);
        mealGoalsFragment.setArguments(bundle);
        return mealGoalsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.meal_goals_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        MealGoalsInputMode mealGoalsInputMode = (MealGoalsInputMode) BundleUtils.getSerializable(getArguments(), Extras.INPUT_MODE, MealGoalsInputMode.Energy, MealGoalsInputMode.class.getClassLoader());
        initViewModel();
        initAdapter(mealGoalsInputMode);
        initUI(mealGoalsInputMode);
        rebindView();
        this.viewModel.validateUpdateMealGoalValidFlag(mealGoalsInputMode);
        getImmHelper().hideSoftInput();
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        FragmentActivity activity = getActivity();
        if (i == Property.DAILY_GOAL_DATA) {
            boolean isMealGoalsValid = this.viewModel.isMealGoalsValid();
            if (activity instanceof MealGoalStateListener) {
                MealGoalStateListener mealGoalStateListener = (MealGoalStateListener) activity;
                mealGoalStateListener.onMealGoalStateChanged(isMealGoalsValid);
                mealGoalStateListener.persistDailyGoal(this.viewModel.getDailyGoal());
            }
        }
        if (i == Property.MEAL_GOAL_FOCUS_CHANGED && (activity instanceof MealGoalStateListener)) {
            ((MealGoalStateListener) activity).onMealGoalFocusChanged();
        }
        if (i == Property.ERROR_MESSAGE_CHANGED) {
            boolean isMealGoalsValid2 = this.viewModel.isMealGoalsValid();
            if (activity instanceof MealGoalStateListener) {
                ((MealGoalStateListener) activity).onMealGoalStateChanged(isMealGoalsValid2);
            }
            showInvalidMealGoalsError(!this.viewModel.isMealGoalsValid());
        }
    }

    private void initViewModel() {
        MfpDailyGoal mfpDailyGoal = (MfpDailyGoal) BundleUtils.getParcelable(getArguments(), Extras.DAILY_GOAL, MfpDailyGoal.class.getClassLoader());
        MealNames mealNames = new MealNames((List<String>) BundleUtils.getStringArrayList(getArguments(), "meal_names"));
        this.viewModel = (MealGoalsFragmentViewModel) getViewModel();
        MealGoalsFragmentViewModel mealGoalsFragmentViewModel = this.viewModel;
        if (mealGoalsFragmentViewModel == null) {
            this.viewModel = (MealGoalsFragmentViewModel) setViewModel(new MealGoalsFragmentViewModel(mfpDailyGoal, mealNames, this.userEnergyService, this.nutrientGoalsUtil));
        } else {
            mealGoalsFragmentViewModel.updateModel(mfpDailyGoal, mealNames);
        }
    }

    private void initAdapter(MealGoalsInputMode mealGoalsInputMode) {
        this.adapter = new MealGoalsAdapter(this.viewModel, this.userEnergyService, this.localizedStringsUtil, mealGoalsInputMode);
        this.mealGoalsRecyclerView.addItemDecoration(new DividerItemDecorator(getActivity()));
        this.mealGoalsRecyclerView.setAdapter(this.adapter);
        this.mealGoalsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mealGoalsRecyclerView.setHasFixedSize(true);
    }

    private void initUI(MealGoalsInputMode mealGoalsInputMode) {
        boolean z = mealGoalsInputMode == MealGoalsInputMode.Energy;
        this.totalName.setText(R.string.total_daily_meal_goal);
        this.totalValue.setText(z ? this.viewModel.getTotalDisplayString() : TOTAL_HUNDRED_PERCENT);
        ViewUtils.setVisible(!z, this.totalPercent);
        this.totalValue.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MealGoalsFragment.this.getNavigationHelper().withIntent(EditCustomMacroGoalsActivity.newStartIntent(MealGoalsFragment.this.getContext())).startActivity();
            }
        });
    }

    private void showInvalidMealGoalsError(boolean z) {
        this.calculationError.setText(getString(this.viewModel.getErrorMessageId(), this.viewModel.getGoalValueOffBy()));
        ViewUtils.setVisible(z, this.calculationError);
    }
}
