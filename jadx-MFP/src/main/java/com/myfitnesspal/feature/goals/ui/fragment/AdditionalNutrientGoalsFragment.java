package com.myfitnesspal.feature.goals.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.GoalUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.adapter.GoalListItem;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Goals.Nutrient;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.ui.adapter.ListItem;
import com.myfitnesspal.shared.ui.adapter.SimpleSectionedAdapter;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

public class AdditionalNutrientGoalsFragment extends MfpFragment {
    private static final int ACTION_BAR_ACCEPT = 1000;
    private static final String EXTRA_DIRTY_STATE = "EXTRA_DIRTY_STATE";
    private static final int UNIT_NUTRIENT_GOAL = 0;
    /* access modifiers changed from: private */
    public MfpDailyGoal defaultDailyGoal;
    private Handler handler = new Handler();
    private ListView listView;
    /* access modifiers changed from: private */
    public MfpNutrientGoal nutrientGoal;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @Inject
    Lazy<PremiumApiErrorUtil> premiumApiErrorUtil;
    /* access modifiers changed from: private */
    public State state = State.Clean;

    private enum State {
        Clean,
        Dirty,
        Saving
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.nutrientGoal = (MfpNutrientGoal) BundleUtils.getParcelable(bundle, "nutrient_goal", MfpNutrientGoal.class.getClassLoader());
        this.defaultDailyGoal = (MfpDailyGoal) BundleUtils.getParcelable(bundle, Extras.DAILY_GOAL, MfpDailyGoal.class.getClassLoader());
        this.state = (State) BundleUtils.getSerializable(bundle, EXTRA_DIRTY_STATE, State.Clean, State.class.getClassLoader());
        setupList();
    }

    public void onResume() {
        super.onResume();
        refillListAdapter();
        this.handler.post(new Runnable() {
            public void run() {
                AdditionalNutrientGoalsFragment.this.setHasOptionsMenu(true);
                AdditionalNutrientGoalsFragment.this.invalidateOptionsMenu();
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("nutrient_goal", this.nutrientGoal);
        bundle.putParcelable(Extras.DAILY_GOAL, this.defaultDailyGoal);
        bundle.putSerializable(EXTRA_DIRTY_STATE, this.state);
    }

    @Subscribe
    public void onGoalsUpdatedEvent(GoalUpdatedEvent goalUpdatedEvent) {
        float f;
        if (goalUpdatedEvent.containsValues()) {
            NutrientGoalsUtil nutrientGoalsUtil2 = (NutrientGoalsUtil) this.nutrientGoalsUtil.get();
            MfpDailyGoal mfpDailyGoal = this.defaultDailyGoal;
            String goalId = goalUpdatedEvent.getGoalId();
            if (goalUpdatedEvent.isDouble()) {
                f = (float) goalUpdatedEvent.getDoubleGoalValue();
            } else {
                f = (float) goalUpdatedEvent.getGoalValue();
            }
            nutrientGoalsUtil2.updateDailyGoal(mfpDailyGoal, goalId, f);
            this.state = State.Dirty;
            refillListAdapter();
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
        if (this.state != State.Saving) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1000, 0, R.string.done).setIcon(R.drawable.ic_check_white_24dp), 2);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1000) {
            switch (this.state) {
                case Dirty:
                    this.state = State.Saving;
                    save();
                    invalidateOptionsMenu();
                    return true;
                case Clean:
                    getActivity().finish();
                    return true;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setupList() {
        this.listView.setAdapter(createAdapter());
        if (this.defaultDailyGoal == null || this.nutrientGoal == null) {
            fetchDefaultDailyGoal();
        }
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ListItem listItem = (ListItem) adapterView.getItemAtPosition(i);
                if (listItem instanceof GoalListItem) {
                    AdditionalNutrientGoalsFragment.this.showDialog((GoalListItem) listItem);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void refillListAdapter() {
        if (isEnabled()) {
            SimpleSectionedAdapter simpleSectionedAdapter = (SimpleSectionedAdapter) this.listView.getAdapter();
            simpleSectionedAdapter.clear();
            simpleSectionedAdapter.addAll(getItems());
            ListViewUtils.notifyDataSetChanged(this.listView);
        }
    }

    private ListAdapter createAdapter() {
        return new SimpleSectionedAdapter(getActivity(), getItems());
    }

    private void fetchDefaultDailyGoal() {
        setBusy(true);
        ((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(new Function1<MfpNutrientGoal>() {
            public void execute(MfpNutrientGoal mfpNutrientGoal) {
                AdditionalNutrientGoalsFragment.this.nutrientGoal = mfpNutrientGoal;
                if (AdditionalNutrientGoalsFragment.this.defaultDailyGoal == null && AdditionalNutrientGoalsFragment.this.nutrientGoal != null) {
                    AdditionalNutrientGoalsFragment additionalNutrientGoalsFragment = AdditionalNutrientGoalsFragment.this;
                    additionalNutrientGoalsFragment.defaultDailyGoal = additionalNutrientGoalsFragment.nutrientGoal.getDefaultGoal();
                }
                AdditionalNutrientGoalsFragment.this.refillListAdapter();
                AdditionalNutrientGoalsFragment.this.setBusy(false);
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
                AdditionalNutrientGoalsFragment.this.setBusy(false);
            }
        });
    }

    private List<ListItem> getItems() {
        if (this.defaultDailyGoal == null) {
            return new ArrayList();
        }
        String string = getString(R.string.gram_abbreviation);
        String string2 = getString(R.string.milligram_abbreviation);
        String str = string;
        String str2 = string2;
        String str3 = string;
        String string3 = getString(R.string.percent_daily_value_abbreviation);
        return new ArrayList(Arrays.asList(new ListItem[]{GoalListItem.newInstance(0, "saturated_fat", getString(R.string.saturated_fat), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatGrams(this.defaultDailyGoal.getSaturatedFat()), this.defaultDailyGoal.getSaturatedFat(), str), GoalListItem.newInstance(0, Nutrient.POLYUNSATURATED_FAT, getString(R.string.polyunsaturated_fat), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatGrams(this.defaultDailyGoal.getPolyunsaturatedFat()), this.defaultDailyGoal.getPolyunsaturatedFat(), str), GoalListItem.newInstance(0, Nutrient.MONOUNSATURATED_FAT, getString(R.string.monounsaturated_fat), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatGrams(this.defaultDailyGoal.getMonounsaturatedFat()), this.defaultDailyGoal.getMonounsaturatedFat(), str), GoalListItem.newInstance(0, "trans_fat", getString(R.string.trans_fat), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatGrams(this.defaultDailyGoal.getTransFat()), this.defaultDailyGoal.getTransFat(), str), GoalListItem.newInstance(0, "cholesterol", getString(R.string.cholesterol), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatMilligrams(this.defaultDailyGoal.getCholesterol()), this.defaultDailyGoal.getCholesterol(), str2), GoalListItem.newInstance(0, "sodium", getString(R.string.sodium), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatMilligrams(this.defaultDailyGoal.getSodium()), this.defaultDailyGoal.getSodium(), str2), GoalListItem.newInstance(0, "potassium", getString(R.string.potassium), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatMilligrams(this.defaultDailyGoal.getPotassium()), this.defaultDailyGoal.getPotassium(), str2), GoalListItem.newInstance(0, Nutrient.FIBER, getString(R.string.fiber), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatGrams(this.defaultDailyGoal.getFiber()), this.defaultDailyGoal.getFiber(), str3), GoalListItem.newInstance(0, "sugar", getString(R.string.sugars), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatGrams(this.defaultDailyGoal.getSugar()), this.defaultDailyGoal.getSugar(), str3), GoalListItem.newInstance(0, "vitamin_a", getString(R.string.vitamin_a), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatDailyValuePercent((float) this.defaultDailyGoal.getVitaminA()), (float) this.defaultDailyGoal.getVitaminA(), string3), GoalListItem.newInstance(0, "vitamin_c", getString(R.string.vitamin_c), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatDailyValuePercent((float) this.defaultDailyGoal.getVitaminC()), (float) this.defaultDailyGoal.getVitaminC(), string3), GoalListItem.newInstance(0, "calcium", getString(R.string.calcium), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatDailyValuePercent((float) this.defaultDailyGoal.getCalcium()), (float) this.defaultDailyGoal.getCalcium(), string3), GoalListItem.newInstance(0, "iron", getString(R.string.iron), ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).formatDailyValuePercent((float) this.defaultDailyGoal.getIron()), (float) this.defaultDailyGoal.getIron(), string3)}));
    }

    /* access modifiers changed from: private */
    public void showDialog(GoalListItem goalListItem) {
        if (goalListItem.getGoalType() == 0) {
            showUserGoalDialogFragment(goalListItem);
        }
    }

    private void showUserGoalDialogFragment(GoalListItem goalListItem) {
        UserGoalDialogFragment.newInstance(goalListItem.getTitle(), goalListItem.getGoalUnitAbbreviation(), goalListItem.getGoalId(), goalListItem.getGoalValue()).show(getChildFragmentManager(), String.format("%s_dialog", new Object[]{Integer.valueOf(goalListItem.getGoalType())}));
    }

    private void save() {
        setBusy(true);
        ((NutrientGoalService) this.nutrientGoalService.get()).setNutrientGoalAsync(new Function1<Boolean>() {
            public void execute(Boolean bool) {
                AdditionalNutrientGoalsFragment.this.setBusy(false);
                AdditionalNutrientGoalsFragment.this.state = State.Clean;
                FragmentActivity activity = AdditionalNutrientGoalsFragment.this.getActivity();
                if (activity != null) {
                    activity.finish();
                }
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
                Ln.e(list, new Object[0]);
                AdditionalNutrientGoalsFragment.this.setBusy(false);
                AdditionalNutrientGoalsFragment.this.state = State.Dirty;
                if (AdditionalNutrientGoalsFragment.this.isEnabled()) {
                    AdditionalNutrientGoalsFragment.this.invalidateOptionsMenu();
                    ((PremiumApiErrorUtil) AdditionalNutrientGoalsFragment.this.premiumApiErrorUtil.get()).showAlertForApiError(list, AdditionalNutrientGoalsFragment.this.getResources().getString(R.string.error_could_not_set_goals));
                }
            }
        }, ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).updateAllDailyGoalsWithNewNonMacroValues(this.nutrientGoal));
    }
}
