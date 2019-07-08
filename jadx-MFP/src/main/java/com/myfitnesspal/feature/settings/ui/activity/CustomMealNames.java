package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.ui.activity.MealGoalsActivity;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.feature.home.util.InternalURLSpan;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.ui.adapter.CustomMealNamesAdapter;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.DividerItemDecorator;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;

public class CustomMealNames extends MfpActivity {
    private static final String ATTRIBUTE_NAMES_CHANGED = "name_changed";
    private static final String ATTRIBUTE_NO = "no";
    private static final String ATTRIBUTE_NUMBER_OF_MEALS = "number_of_meals";
    private static final String ATTRIBUTE_YES = "yes";
    public static final String CUSTOM_MEAL_NAMES = "custom_meal_names";
    private static final String EVENT_CUSTOM_MEAL_NAMES_CHANGED = "custom_meal_names_changed";
    private static final String EXIT_CONFIRM_DIALOG_TAG = "exit_confirm_dialog_tag";
    private static final String EXIT_UPDATE_MEAL_GOALS_DIALOG_TAG = "exit_update_meal_goals_dialog_tag";
    private static final String EXTRA_MODIFIED_MEAL_NAMES = "modified_meal_names";
    private static final int MAX_MEAL_NAME_COUNT = 6;
    private static final int MENU_SAVE_MEAL_NAMES = 1001;
    private CustomMealNamesAdapter adapter;
    @BindView(2131361847)
    TextView mealNamesEduHint;
    @BindView(2131363028)
    RecyclerView mealsRecyclerView;
    private OnClickListener navigateToFAQClickListener = new OnClickListener() {
        public void onClick(View view) {
            NavigationHelper navigationHelper = CustomMealNames.this.getNavigationHelper();
            CustomMealNames customMealNames = CustomMealNames.this;
            navigationHelper.withIntent(Faq.newStartIntent(customMealNames, 109, customMealNames.getString(R.string.custom_meal_names_settings))).startActivity();
        }
    };
    private DialogNegativeListener onNegativeButtonClickListener = new DialogNegativeListener() {
        public void onClick() {
            CustomMealNames.this.finish();
        }
    };
    private DialogPositiveListener onPositiveButtonClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            CustomMealNames.this.validateAndSave();
        }
    };
    private DialogPositiveListener onUpdateGoalsPositiveButtonClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            CustomMealNames.this.getNavigationHelper().withIntent(MealGoalsActivity.newStartIntent(CustomMealNames.this, CustomMealNames.CUSTOM_MEAL_NAMES, (ArrayList) ((CustomMealNamesAdapter) CustomMealNames.this.mealsRecyclerView.getAdapter()).getMealNames())).finishActivityAfterNavigation().startActivity();
        }
    };
    private List<String> originalMealNames;
    @Inject
    Lazy<PremiumService> premiumService;

    public String getAnalyticsScreenTag() {
        return Screens.CUSTOM_MEAL_NAMES;
    }

    public static Intent createNewIntent(Context context) {
        return new Intent(context, CustomMealNames.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.custom_meal_names);
        setLearnMoreLink();
        setAdapter(BundleUtils.getStringArrayList(bundle, EXTRA_MODIFIED_MEAL_NAMES));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList(EXTRA_MODIFIED_MEAL_NAMES, (ArrayList) this.adapter.getMealNames());
    }

    private void setAdapter(ArrayList<String> arrayList) {
        MealNames mealNames = getSession().getUser().getMealNames();
        this.originalMealNames = mealNames != null ? new ArrayList(mealNames.getNames()) : new ArrayList();
        while (CollectionUtils.size((Collection<?>) this.originalMealNames) < 6) {
            this.originalMealNames.add("");
        }
        if (arrayList == null) {
            arrayList = new ArrayList<>(this.originalMealNames);
        }
        this.adapter = new CustomMealNamesAdapter(arrayList);
        this.mealsRecyclerView.addItemDecoration(new DividerItemDecorator(this));
        this.mealsRecyclerView.setAdapter(this.adapter);
        this.mealsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mealsRecyclerView.setHasFixedSize(true);
    }

    public void onUpPressed() {
        onBackOrUpPressed();
    }

    public void onBackPressed() {
        onBackOrUpPressed();
    }

    private void onBackOrUpPressed() {
        if (isNewListDifferentFromOriginalList()) {
            showExitConfirmDialog();
        } else {
            finish();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.save_meal_names).setIcon(R.drawable.ic_check_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        validateAndSave();
        return true;
    }

    private boolean isNewListDifferentFromOriginalList() {
        return isNewListDifferentFromOriginalList(this.adapter.getMealNames());
    }

    private boolean isNewListDifferentFromOriginalList(List<String> list) {
        if (getValidMealNamesCount(this.originalMealNames) != getValidMealNamesCount(list)) {
            return true;
        }
        return areNewNamesDifferentFromOriginalNames(list);
    }

    private boolean areNewNamesDifferentFromOriginalNames(List<String> list) {
        int validMealNamesCount = getValidMealNamesCount(this.originalMealNames);
        int validMealNamesCount2 = getValidMealNamesCount(list);
        int i = 0;
        while (i < validMealNamesCount && i < validMealNamesCount2) {
            if (!Strings.equals((String) this.originalMealNames.get(i), (String) list.get(i))) {
                return true;
            }
            i++;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void validateAndSave() {
        List mealNames = ((CustomMealNamesAdapter) this.mealsRecyclerView.getAdapter()).getMealNames();
        if (checkMealNamesValidity(mealNames)) {
            saveChanges(mealNames);
        }
    }

    /* access modifiers changed from: private */
    public void saveChanges(List<String> list) {
        int isMealNamesSizeChanged = isMealNamesSizeChanged(list);
        if (!isMealGoalsAvailable() || isMealNamesSizeChanged == 0 || !getSession().getUser().isMealGoalsEnabled()) {
            persistMealNames(list);
            reportMealNamesChangedEvent(list, getAnalyticsService(), isNewListDifferentFromOriginalList(list));
            finish();
            return;
        }
        showUpdateGoalDialog(isMealNamesSizeChanged < 0 ? R.string.meal_goals_meal_names_deleted_warning_text : R.string.meal_goals_meal_names_added_warning_text);
    }

    private void showUpdateGoalDialog(@StringRes int i) {
        showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.meal_goals_changed_dialog_title)).setMessage(i)).setPositiveText(R.string.update_goals, this.onUpdateGoalsPositiveButtonClickListener), EXIT_UPDATE_MEAL_GOALS_DIALOG_TAG);
    }

    private void showExitConfirmDialog() {
        showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.unsaved_changes)).setMessage((int) R.string.unsaved_changes_body)).setPositiveText(R.string.save, this.onPositiveButtonClickListener)).setNegativeText(R.string.dont_save, this.onNegativeButtonClickListener), EXIT_CONFIRM_DIALOG_TAG);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(EXIT_CONFIRM_DIALOG_TAG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onPositiveButtonClickListener)).setNegativeListener(this.onNegativeButtonClickListener);
        return true;
    }

    private boolean checkMealNamesValidity(List<String> list) {
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            HashSet hashSet = new HashSet();
            boolean z = false;
            for (String str : list) {
                boolean isEmpty = Strings.isEmpty(str);
                if (!z) {
                    z = isEmpty;
                } else if (!isEmpty) {
                    showMissingMealNamesDialog();
                    return false;
                }
                if (!isEmpty && !hashSet.add(str)) {
                    showUniqueMealNamesDialog();
                    return false;
                }
            }
            if (!isAnyMealBeingDeleted(list)) {
                return true;
            }
            showDeleteConfirmationDialog(list);
            return false;
        }
        showEmptyMealNamesDialog();
        return false;
    }

    private boolean isAnyMealBeingDeleted(List<String> list) {
        return getValidMealNamesCount(list) < getValidMealNamesCount(this.originalMealNames);
    }

    private int isMealNamesSizeChanged(List<String> list) {
        return getValidMealNamesCount(list) - getValidMealNamesCount(this.originalMealNames);
    }

    public static int getValidMealNamesCount(List<String> list) {
        int i = 0;
        for (String isEmpty : list) {
            if (!Strings.isEmpty(isEmpty)) {
                i++;
            }
        }
        return i;
    }

    private void showDeleteConfirmationDialog(final List<String> list) {
        new MfpAlertDialogBuilder(this).setTitle((int) R.string.heads_up).setMessage((int) R.string.meal_name_hidden).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CustomMealNames.this.saveChanges(list);
            }
        }).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    private void persistMealNames(List<String> list) {
        User user = getSession().getUser();
        user.setProperty("meal_names", Strings.arrayOfStringsToCSV((String[]) list.toArray(new String[list.size()])));
        user.updatePropertyNamed("meal_names");
    }

    private void showEmptyMealNamesDialog() {
        showErrorDialog(R.string.try_again, R.string.minimum_1_meal);
    }

    private void showMissingMealNamesDialog() {
        showErrorDialog(R.string.try_again, R.string.missing_meal_name_text);
    }

    private void showUniqueMealNamesDialog() {
        showErrorDialog(R.string.try_again, R.string.meal_names_unique_text);
    }

    private void showErrorDialog(int i, int i2) {
        new MfpAlertDialogBuilder(this).setTitle(i).setMessage(i2).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    public static void reportMealNamesChangedEvent(List<String> list, AnalyticsService analyticsService, boolean z) {
        analyticsService.reportEvent(EVENT_CUSTOM_MEAL_NAMES_CHANGED, new Builder().put(ATTRIBUTE_NAMES_CHANGED, z ? "yes" : "no").put(ATTRIBUTE_NUMBER_OF_MEALS, Strings.toString(Integer.valueOf(getValidMealNamesCount(list)))).build());
    }

    private void setLearnMoreLink() {
        String string = getString(R.string.learn_more);
        String string2 = getString(R.string.custom_meal_names_edu_hint, new Object[]{string});
        int indexOf = string2.indexOf(string);
        SpannableString spannableString = new SpannableString(string2);
        if (indexOf != -1) {
            spannableString.setSpan(new InternalURLSpan(this.navigateToFAQClickListener, getResources().getColor(R.color.blue)), indexOf, string.length() + indexOf, 33);
        }
        this.mealNamesEduHint.setText(spannableString);
        this.mealNamesEduHint.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private boolean isMealGoalsAvailable() {
        return ((PremiumService) this.premiumService.get()).isFeatureAvailable(PremiumFeature.MealGoals);
    }
}
