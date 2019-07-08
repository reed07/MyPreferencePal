package com.myfitnesspal.feature.goals.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.CustomGoalByDayActivity;
import com.myfitnesspal.feature.goals.ui.activity.MacroGoalEditorActivity;
import com.myfitnesspal.feature.goals.ui.activity.MealGoalsActivity;
import com.myfitnesspal.feature.goals.ui.adapter.CustomDailyGoalListItem;
import com.myfitnesspal.feature.goals.ui.adapter.EnergyListItem;
import com.myfitnesspal.feature.goals.ui.adapter.EnergyListItem.Display;
import com.myfitnesspal.feature.goals.ui.adapter.GoalListItem;
import com.myfitnesspal.feature.goals.ui.dialog.MacroNutrientEditorDialog;
import com.myfitnesspal.feature.goals.ui.dialog.NetEnergyGoalDialogFragment;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.adapter.CustomListItem;
import com.myfitnesspal.shared.ui.adapter.HeaderListItem;
import com.myfitnesspal.shared.ui.adapter.ListItem;
import com.myfitnesspal.shared.ui.adapter.SimpleSectionedAdapter;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;

public class EditCustomMacroGoalsFragment extends MfpFragment {
    private static final int ADD_GOAL_REPLACEMENT = 2;
    private static final int MACRO_NUTRIENT_GOAL = 1;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private float carb;
    private float fat;
    private final Function1<List<Exception>> genericErrorHandler = new Function1<List<Exception>>() {
        public void execute(List<Exception> list) {
            EditCustomMacroGoalsFragment.this.setBusy(false);
            Ln.e(list, new Object[0]);
            if (EditCustomMacroGoalsFragment.this.isEnabled()) {
                EditCustomMacroGoalsFragment editCustomMacroGoalsFragment = EditCustomMacroGoalsFragment.this;
                editCustomMacroGoalsFragment.postEvent(new AlertEvent(editCustomMacroGoalsFragment.getResources().getString(R.string.error_could_not_retrieve_exercise_goal)));
            }
        }
    };
    private Map<String, ArrayList<String>> goalListMap;
    private ListView listView;
    private float localizedEnergyValue;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutritionalGoalsUtil;
    private final OnItemClickListener onListItemClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ListItem listItem = (ListItem) adapterView.getItemAtPosition(i);
            if (listItem instanceof GoalListItem) {
                EditCustomMacroGoalsFragment.this.onGoalListItemClicked((GoalListItem) listItem);
            } else if (listItem instanceof CustomDailyGoalListItem) {
                EditCustomMacroGoalsFragment.this.onCustomDailyGoalListItemClicked((CustomDailyGoalListItem) listItem);
            } else if (listItem instanceof EnergyListItem) {
                EditCustomMacroGoalsFragment.this.onEnergyListItemClicked((EnergyListItem) listItem);
            }
        }
    };
    @Inject
    Lazy<PremiumApiErrorUtil> premiumApiErrorUtil;
    @Inject
    Lazy<PremiumService> premiumService;
    private float protein;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static EditCustomMacroGoalsFragment newInstance() {
        return new EditCustomMacroGoalsFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public void onResume() {
        super.onResume();
        requery();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        return inflate;
    }

    public void updateEnergyGoalFromDialog(float f) {
        recalculateAndUpdateGoals(f, null);
    }

    /* access modifiers changed from: private */
    public void onGoalListItemClicked(GoalListItem goalListItem) {
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.CustomDailyGoals);
        switch (goalListItem.getGoalType()) {
            case 1:
                showMacroNutrientDialog();
                return;
            case 2:
                if (featureAvailability == Availability.Available) {
                    getNavigationHelper().withIntent(CustomGoalByDayActivity.newStartIntent(getActivity(), null)).withIntent(CustomGoalByDayActivity.newStartIntent(getActivity(), null)).startActivity(192);
                    reportAnalyticsEvent();
                    return;
                } else if (featureAvailability == Availability.Locked) {
                    getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) getActivity(), PremiumFeature.CustomDailyGoals)).startActivity();
                    return;
                } else if (featureAvailability == Availability.Revoked) {
                    getMessageBus().post(new AlertEvent(getString(R.string.premium_feature_revoked)));
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void onCustomDailyGoalListItemClicked(CustomDailyGoalListItem customDailyGoalListItem) {
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.CustomDailyGoals);
        if (featureAvailability == Availability.Available) {
            getNavigationHelper().withIntent(CustomGoalByDayActivity.newStartIntent(getActivity(), (ArrayList) this.goalListMap.get(customDailyGoalListItem.getTitle()))).startActivity(192);
        } else if (featureAvailability == Availability.Locked) {
            getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) getActivity(), PremiumFeature.CustomDailyGoals)).startActivity();
        } else if (featureAvailability == Availability.Revoked) {
            getMessageBus().post(new AlertEvent(getString(R.string.premium_feature_revoked)));
        }
    }

    /* access modifiers changed from: private */
    public void onEnergyListItemClicked(EnergyListItem energyListItem) {
        if (!getSession().getUser().getGoalPreferences().isMacroGoalFormatGrams()) {
            showEnergyGoalEditDialog();
        } else {
            showNoCalorieAdjustmentInGramsDialog();
        }
    }

    private void reportAnalyticsEvent() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.DAILY_GOALS_VIEWED);
    }

    public void recalculateAndUpdateGoals(final float f, final MacroGoalsUpdatedEvent macroGoalsUpdatedEvent) {
        ((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(new Function1<MfpNutrientGoal>() {
            public void execute(MfpNutrientGoal mfpNutrientGoal) {
                MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
                float value = defaultGoal.getEnergy().getValue();
                String hashKey = defaultGoal.toHashKey();
                MacroGoalsUpdatedEvent macroGoalsUpdatedEvent = macroGoalsUpdatedEvent;
                if (macroGoalsUpdatedEvent != null) {
                    defaultGoal = EditCustomMacroGoalsFragment.this.getDailyGoalWithNewMacroValues(defaultGoal, macroGoalsUpdatedEvent);
                } else if (f != BitmapDescriptorFactory.HUE_RED) {
                    defaultGoal = ((NutrientGoalsUtil) EditCustomMacroGoalsFragment.this.nutritionalGoalsUtil.get()).getDailyGoalWithNewEnergyValue(defaultGoal, f);
                }
                int access$100 = EditCustomMacroGoalsFragment.this.updateDailyGoalsThatShouldBeDefault(defaultGoal, mfpNutrientGoal.getDailyGoals(), hashKey, defaultGoal.toHashKey());
                boolean z = true;
                boolean z2 = value != defaultGoal.getEnergy().getValue();
                if (access$100 <= 0) {
                    z = false;
                }
                mfpNutrientGoal.setDefaultGoal(defaultGoal);
                ((NutrientGoalsUtil) EditCustomMacroGoalsFragment.this.nutritionalGoalsUtil.get()).setWeekDailyGoalsToDefaultDailyGoalIfNeeded(mfpNutrientGoal);
                EditCustomMacroGoalsFragment.this.save(mfpNutrientGoal, z, z2);
            }
        }, this.genericErrorHandler);
    }

    /* access modifiers changed from: private */
    public MfpDailyGoal getDailyGoalWithNewMacroValues(MfpDailyGoal mfpDailyGoal, MacroGoalsUpdatedEvent macroGoalsUpdatedEvent) {
        MfpDailyGoal mfpDailyGoal2 = (MfpDailyGoal) ParcelableUtil.clone(mfpDailyGoal, MfpDailyGoal.CREATOR);
        writeValuesToDailyGoal(mfpDailyGoal2, new MfpMeasuredValue(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit(), macroGoalsUpdatedEvent.getLocalizedEnergyValue()), macroGoalsUpdatedEvent.getCarbohydrates(), macroGoalsUpdatedEvent.getProtein(), macroGoalsUpdatedEvent.getFat());
        return mfpDailyGoal2;
    }

    /* access modifiers changed from: private */
    public int updateDailyGoalsThatShouldBeDefault(MfpDailyGoal mfpDailyGoal, List<MfpDailyGoal> list, String str, String str2) {
        int i = 0;
        for (MfpDailyGoal mfpDailyGoal2 : list) {
            String hashKey = mfpDailyGoal2.toHashKey();
            boolean equals = Strings.equals(hashKey, str);
            boolean equals2 = Strings.equals(hashKey, str2);
            if (equals || equals2) {
                i += equals ^ true ? 1 : 0;
                writeValuesToDailyGoal(mfpDailyGoal2, mfpDailyGoal);
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    public void save(MfpNutrientGoal mfpNutrientGoal, final boolean z, final boolean z2) {
        ((NutrientGoalService) this.nutrientGoalService.get()).setNutrientGoalAsync(new Function1<Boolean>() {
            public void execute(Boolean bool) {
                if (EditCustomMacroGoalsFragment.this.isEnabled()) {
                    if (z2 && ((Session) EditCustomMacroGoalsFragment.this.session.get()).getUser().isMealGoalsEnabled() && ((PremiumService) EditCustomMacroGoalsFragment.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealGoals)) {
                        EditCustomMacroGoalsFragment.this.showGoalsChangedDialog(false);
                    } else if (z) {
                        new MfpAlertDialogBuilder(EditCustomMacroGoalsFragment.this.getActivity()).setTitle((int) R.string.removed_daily_goal_title).setMessage((CharSequence) EditCustomMacroGoalsFragment.this.getString(R.string.removed_daily_goals)).setPositiveButton(17039370, (OnClickListener) null).show();
                    }
                    EditCustomMacroGoalsFragment.this.requery();
                }
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
                Ln.e(list, new Object[0]);
                if (EditCustomMacroGoalsFragment.this.isEnabled()) {
                    ((PremiumApiErrorUtil) EditCustomMacroGoalsFragment.this.premiumApiErrorUtil.get()).showAlertForApiError(list, EditCustomMacroGoalsFragment.this.getResources().getString(R.string.error_could_not_set_macros_by_day));
                }
            }
        }, mfpNutrientGoal);
    }

    private List<ListItem> filterCustomGoalsFromPremiumStatus(List<ListItem> list) {
        ArrayList arrayList = new ArrayList();
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.CustomDailyGoals);
        if (featureAvailability != Availability.Hidden) {
            arrayList.add(new CustomListItem(getActivity(), R.layout.set_daily_goals_header_row));
            if (featureAvailability == Availability.Available || featureAvailability == Availability.Revoked) {
                arrayList.addAll(list);
                arrayList.add(GoalListItem.newInstance(2, getString(R.string.add_goal_replacement)));
            } else if (featureAvailability == Availability.Locked) {
                arrayList.add(GoalListItem.newInstance(2, getString(R.string.add_goal_replacement)).setIconResId(R.drawable.ic_premium_lock));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void requery() {
        ((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(new Function1<MfpNutrientGoal>() {
            public void execute(MfpNutrientGoal mfpNutrientGoal) {
                if (EditCustomMacroGoalsFragment.this.isEnabled()) {
                    EditCustomMacroGoalsFragment.this.pullValuesFromDefaultGoal(mfpNutrientGoal);
                    List createDefaultGoalListItems = EditCustomMacroGoalsFragment.this.createDefaultGoalListItems();
                    List access$600 = EditCustomMacroGoalsFragment.this.createCustomGoalListItems(mfpNutrientGoal);
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(createDefaultGoalListItems);
                    arrayList.addAll(access$600);
                    EditCustomMacroGoalsFragment.this.refreshListView(arrayList);
                }
            }
        }, this.genericErrorHandler);
    }

    /* access modifiers changed from: private */
    public void refreshListView(List<ListItem> list) {
        SimpleSectionedAdapter simpleSectionedAdapter = new SimpleSectionedAdapter(getActivity(), list);
        this.listView.setOnItemClickListener(this.onListItemClickListener);
        this.listView.setAdapter(simpleSectionedAdapter);
    }

    /* access modifiers changed from: private */
    public void pullValuesFromDefaultGoal(MfpNutrientGoal mfpNutrientGoal) {
        MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
        this.localizedEnergyValue = ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) defaultGoal.getEnergy().getValue());
        this.carb = defaultGoal.getCarbohydrates();
        this.protein = defaultGoal.getProtein();
        this.fat = defaultGoal.getFat();
    }

    /* access modifiers changed from: protected */
    public List<ListItem> createDefaultGoalListItems() {
        String carbohydratesForDisplay = ((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getCarbohydratesForDisplay(this.carb);
        int round = Math.round(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getMacroCarbohydratesPercentage(this.carb, this.protein, this.fat));
        String formatPercent = formatPercent(round);
        String fatForDisplay = ((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getFatForDisplay(this.fat);
        int round2 = Math.round(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getMacroFatPercentage(this.carb, this.protein, this.fat));
        String formatPercent2 = formatPercent(round2);
        String proteinForDisplay = ((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getProteinForDisplay(this.protein);
        String formatPercent3 = formatPercent(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getMacroProteinPercentage(round, round2));
        boolean isMacroGoalFormatGrams = ((Session) this.session.get()).getUser().getGoalPreferences().isMacroGoalFormatGrams();
        String string = getString(R.string.gram_abbreviation);
        ListItem[] listItemArr = new ListItem[5];
        listItemArr[0] = new HeaderListItem(getString(R.string.default_goal));
        listItemArr[1] = new EnergyListItem(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.CUSTOM_GOAL_ENERGY_TYPE, this.userEnergyService), isMacroGoalFormatGrams ? getString(R.string.calories_auto_calculated) : null, NumberUtils.localeStringFromInt(Math.round(this.localizedEnergyValue)), isMacroGoalFormatGrams ? Display.Grams : Display.Percent);
        GoalListItem newInstance = GoalListItem.newInstance(1, getString(R.string.carbohydrates), isMacroGoalFormatGrams ? carbohydratesForDisplay : formatPercent);
        if (isMacroGoalFormatGrams) {
            carbohydratesForDisplay = formatPercent;
        }
        listItemArr[2] = newInstance.setSideTitle(carbohydratesForDisplay).setGoalValue(this.carb).setGoalUnitAbbreviation(string);
        GoalListItem newInstance2 = GoalListItem.newInstance(1, getString(R.string.protein), isMacroGoalFormatGrams ? proteinForDisplay : formatPercent3);
        if (!isMacroGoalFormatGrams) {
            formatPercent3 = proteinForDisplay;
        }
        listItemArr[3] = newInstance2.setSideTitle(formatPercent3).setGoalValue(this.protein).setGoalUnitAbbreviation(string);
        GoalListItem newInstance3 = GoalListItem.newInstance(1, getString(R.string.fat), isMacroGoalFormatGrams ? fatForDisplay : formatPercent2);
        if (isMacroGoalFormatGrams) {
            fatForDisplay = formatPercent2;
        }
        listItemArr[4] = newInstance3.setSideTitle(fatForDisplay).setGoalValue(this.fat).setGoalUnitAbbreviation(string);
        return new ArrayList(Arrays.asList(listItemArr));
    }

    /* access modifiers changed from: private */
    public List<ListItem> createCustomGoalListItems(MfpNutrientGoal mfpNutrientGoal) {
        Map aggregateCustomGoals = aggregateCustomGoals(mfpNutrientGoal);
        ArrayList arrayList = new ArrayList();
        this.goalListMap = new HashMap();
        for (Entry entry : aggregateCustomGoals.entrySet()) {
            String formatCustomGoalTitle = formatCustomGoalTitle((List) entry.getValue());
            this.goalListMap.put(formatCustomGoalTitle, (ArrayList) entry.getValue());
            arrayList.add(new CustomDailyGoalListItem(formatCustomGoalTitle, formatCustomGoalSubtext(mfpNutrientGoal, (String) ((List) entry.getValue()).get(0))));
        }
        return filterCustomGoalsFromPremiumStatus(arrayList);
    }

    private String formatCustomGoalTitle(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String formattedDayOFWeek : list) {
            arrayList.add(getString(DateTimeUtils.getFormattedDayOFWeek(formattedDayOFWeek)));
        }
        return Strings.join(", ", (Collection<T>) arrayList);
    }

    private String formatCustomGoalSubtext(MfpNutrientGoal mfpNutrientGoal, String str) {
        MfpDailyGoal mfpDailyGoal;
        Iterator it = mfpNutrientGoal.getDailyGoals().iterator();
        while (true) {
            if (!it.hasNext()) {
                mfpDailyGoal = null;
                break;
            }
            mfpDailyGoal = (MfpDailyGoal) it.next();
            if (Strings.equals(mfpDailyGoal.getDayOfWeek(), str)) {
                break;
            }
        }
        if (mfpDailyGoal != null) {
            boolean isMacroGoalFormatGrams = ((Session) this.session.get()).getUser().getGoalPreferences().isMacroGoalFormatGrams();
            String localizedString = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(isMacroGoalFormatGrams ? LocalizedStrings.CUSTOM_GOAL_GRAMS_SUBTEXT : LocalizedStrings.CUSTOM_GOAL_PERCENT_SUBTEXT, this.userEnergyService);
            int round = Math.round(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy(mfpDailyGoal.getEnergy()));
            int round2 = Math.round(mfpDailyGoal.getCarbohydrates());
            int round3 = Math.round(mfpDailyGoal.getProtein());
            int round4 = Math.round(mfpDailyGoal.getFat());
            if (isMacroGoalFormatGrams) {
                return String.format(localizedString, new Object[]{Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(round3), Integer.valueOf(round4)});
            }
            NutrientGoalsUtil nutrientGoalsUtil = (NutrientGoalsUtil) this.nutritionalGoalsUtil.get();
            float f = (float) round2;
            float f2 = (float) round3;
            float f3 = (float) round4;
            int round5 = Math.round(nutrientGoalsUtil.getMacroCarbohydratesPercentage(f, f2, f3));
            int round6 = Math.round(nutrientGoalsUtil.getMacroFatPercentage(f, f2, f3));
            return String.format(localizedString, new Object[]{Integer.valueOf(round), Integer.valueOf(round5), Integer.valueOf(nutrientGoalsUtil.getMacroProteinPercentage(round5, round6)), Integer.valueOf(round6)});
        }
        throw new IllegalArgumentException("specified day is invalid");
    }

    private void showEnergyGoalEditDialog() {
        NetEnergyGoalDialogFragment.newInstance(this.localizedEnergyValue).show(getFragmentManager(), Fragments.NET_ENERGY_DIALOG);
    }

    private void showNoCalorieAdjustmentInGramsDialog() {
        new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.alert).setMessage((CharSequence) ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ENERGY_BY_GRAM_ERROR, this.userEnergyService)).setPositiveButton((int) R.string.ok, (OnClickListener) null).show();
    }

    private void showMacroNutrientDialog() {
        if (((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.TrackMacrosByGram) == Availability.Hidden) {
            MacroNutrientEditorDialog.getInstance(this.localizedEnergyValue).show(getChildFragmentManager(), Fragments.MACRO_NUTRIENT_DIALOG);
        } else {
            getNavigationHelper().withContext(getActivity()).withIntent(MacroGoalEditorActivity.newStartIntent(getActivity(), this.localizedEnergyValue, this.carb, this.protein, this.fat)).startActivity(149);
        }
    }

    public void showCustomGoalsChangeDialog() {
        if (((PremiumService) this.premiumService.get()).isFeatureAvailable(PremiumFeature.MealGoals) && ((Session) this.session.get()).getUser().isMealGoalsEnabled()) {
            showGoalsChangedDialog(true);
        }
    }

    /* access modifiers changed from: private */
    public void showGoalsChangedDialog(boolean z) {
        MfpAlertDialogBuilder title = new MfpAlertDialogBuilder(getActivity()).setTitle(z ? R.string.meal_goals_custom_goals_changed_dialog_title : R.string.meal_goals_changed_dialog_title);
        int i = z ? R.string.meal_goals_custom_goals_changed_dialog_text : ((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.meal_goals_changed_dialog_to_fit_cal_goals_text : R.string.meal_goals_changed_dialog_to_fit_kj_goals_text;
        title.setMessage(i).setPositiveButton((int) R.string.ok, (OnClickListener) null).setNegativeButton((int) R.string.review, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditCustomMacroGoalsFragment.this.getNavigationHelper().withIntent(MealGoalsActivity.newStartIntent(EditCustomMacroGoalsFragment.this.getActivity())).startActivity();
            }
        }).show();
    }

    @Subscribe
    public void onMacroGoalsUpdatedEvent(MacroGoalsUpdatedEvent macroGoalsUpdatedEvent) {
        recalculateAndUpdateGoals(BitmapDescriptorFactory.HUE_RED, macroGoalsUpdatedEvent);
    }

    private void writeValuesToDailyGoal(MfpDailyGoal mfpDailyGoal, MfpDailyGoal mfpDailyGoal2) {
        MfpMeasuredValue energy = mfpDailyGoal2.getEnergy();
        ((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).normalizeMealGoalsForTotal(mfpDailyGoal, mfpDailyGoal.getEnergy(), energy);
        mfpDailyGoal.setEnergy(energy);
        mfpDailyGoal.setCarbohydrates(mfpDailyGoal2.getCarbohydrates());
        mfpDailyGoal.setProtein(mfpDailyGoal2.getProtein());
        mfpDailyGoal.setFat(mfpDailyGoal2.getFat());
    }

    private void writeValuesToDailyGoal(MfpDailyGoal mfpDailyGoal, MfpMeasuredValue mfpMeasuredValue, float f, float f2, float f3) {
        ((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).normalizeMealGoalsForTotal(mfpDailyGoal, mfpDailyGoal.getEnergy(), mfpMeasuredValue);
        mfpDailyGoal.setEnergy(mfpMeasuredValue);
        mfpDailyGoal.setCarbohydrates(f);
        mfpDailyGoal.setProtein(f2);
        mfpDailyGoal.setFat(f3);
    }

    private Map<String, List<String>> aggregateCustomGoals(MfpNutrientGoal mfpNutrientGoal) {
        HashMap hashMap = new HashMap();
        for (MfpDailyGoal mfpDailyGoal : mfpNutrientGoal.getDailyGoals()) {
            if (!dailyGoalEqualsInternalState(mfpDailyGoal)) {
                String hashKey = mfpDailyGoal.toHashKey();
                if (hashMap.containsKey(hashKey)) {
                    ((List) hashMap.get(hashKey)).add(mfpDailyGoal.getDayOfWeek());
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(mfpDailyGoal.getDayOfWeek());
                    hashMap.put(hashKey, arrayList);
                }
            }
        }
        return hashMap;
    }

    private String formatPercent(int i) {
        return getString(R.string.percent_value, Integer.valueOf(i));
    }

    private boolean dailyGoalEqualsInternalState(MfpDailyGoal mfpDailyGoal) {
        return ((int) this.localizedEnergyValue) == ((int) ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) mfpDailyGoal.getEnergy().getValue())) && ((int) this.carb) == ((int) mfpDailyGoal.getCarbohydrates()) && ((int) this.protein) == ((int) mfpDailyGoal.getProtein()) && ((int) this.fat) == ((int) mfpDailyGoal.getFat());
    }
}
