package com.myfitnesspal.feature.diary.ui.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.model.AppsHomeViewModel.Property;
import com.myfitnesspal.feature.diary.event.CopyEntriesToDateEvent;
import com.myfitnesspal.feature.diary.event.PasswordCanceledEvent;
import com.myfitnesspal.feature.diary.event.PasswordEnteredEvent;
import com.myfitnesspal.feature.diary.model.DiaryViewModelBase;
import com.myfitnesspal.feature.diary.model.FriendDiaryBundleInfo;
import com.myfitnesspal.feature.diary.model.FriendDiaryRequestData;
import com.myfitnesspal.feature.diary.model.FriendDiaryViewModel;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.diary.ui.dialog.DiaryQuickToolsDialogFragment;
import com.myfitnesspal.feature.diary.ui.dialog.FriendDiaryPasswordDialogFragment;
import com.myfitnesspal.feature.diary.ui.dialog.MealNamesForCopyingItemsDialogFragment;
import com.myfitnesspal.feature.diary.ui.dialog.MealNamesForCopyingItemsDialogFragment.OnMealSelectedListener;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Report;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

public class FriendDiaryFragment extends DiaryFragmentBase implements OnMealSelectedListener {
    private static final String FRIEND_DIARY_BUNDLE_INFO = "friend_diary_bundle_info";
    private static final String MEAL_NAMES_FOR_COPY_MEAL_DIALOG_TAG = "meal_names_for_copy_meal_dialog";
    private FriendDiaryBundleInfo friendDiaryBundleInfo;
    private MealNames mealNames;
    @Inject
    Lazy<UserSummaryService> userSummaryService;

    public static FriendDiaryFragment newInstance(FriendDiaryBundleInfo friendDiaryBundleInfo2) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRIEND_DIARY_BUNDLE_INFO, friendDiaryBundleInfo2);
        FriendDiaryFragment friendDiaryFragment = new FriendDiaryFragment();
        friendDiaryFragment.setArguments(bundle);
        return friendDiaryFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.mealNames = getSession().getUser().getMealNames();
        this.friendDiaryBundleInfo = (FriendDiaryBundleInfo) BundleUtils.getParcelable(getArguments(), FRIEND_DIARY_BUNDLE_INFO, FriendDiaryBundleInfo.class.getClassLoader());
        this.activeDate = this.friendDiaryBundleInfo.getCurrentDate();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().setTitle(this.friendDiaryBundleInfo.getUsername());
    }

    public void onResume() {
        super.onResume();
        showCreateMealDialog(getActivity().getIntent());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 87) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1 && intent != null) {
            showCreateMealDialog(intent);
            ((UacfScheduler) this.syncScheduler.get()).debounceDefaultSync();
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(MEAL_NAMES_FOR_COPY_MEAL_DIALOG_TAG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((MealNamesForCopyingItemsDialogFragment) dialogFragment).setOnMealSelectedListener(this);
        return true;
    }

    /* access modifiers changed from: protected */
    public DiaryViewModelBase getViewModelInstance() {
        FriendDiaryViewModel friendDiaryViewModel = new FriendDiaryViewModel(getRunner(), this.diaryService, this.nutrientGoalService, this, this.userSummaryService);
        return friendDiaryViewModel;
    }

    /* access modifiers changed from: protected */
    public void fetchDiaryDay(Calendar calendar) {
        ((FriendDiaryViewModel) this.viewModel).load(new FriendDiaryRequestData(this.friendDiaryBundleInfo, calendar.getTime()));
    }

    /* access modifiers changed from: protected */
    public void setEmptyViewText(TextView textView) {
        if (textView != null) {
            textView.setText(getString(R.string.friend_not_logged_anything, this.friendDiaryBundleInfo.getUsername()));
        }
    }

    @Subscribe
    public void onCopyEntriesToDateEvent(CopyEntriesToDateEvent copyEntriesToDateEvent) {
        String sectionName = copyEntriesToDateEvent.getSectionName();
        Date targetDate = copyEntriesToDateEvent.getTargetDate();
        if (Strings.equals("Exercise", sectionName) || Strings.equals(Report.CARDIO_EXERCISE, sectionName) || Strings.equals(Report.STRENGTH_EXERCISE, sectionName)) {
            this.diaryDelegate.copyEntriesFromSectionToSectionOnDate(getCurrentDiaryDay(), targetDate, sectionName, sectionName);
            navigateToDiaryAfterCopying();
            return;
        }
        MealNamesForCopyingItemsDialogFragment newInstance = MealNamesForCopyingItemsDialogFragment.newInstance(sectionName, targetDate);
        newInstance.setOnMealSelectedListener(this);
        showDialogFragment(newInstance, MEAL_NAMES_FOR_COPY_MEAL_DIALOG_TAG);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void reportCopyEvent(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -828903721(0xffffffffce97eed7, float:-1.27450611E9)
            r2 = 0
            if (r0 == r1) goto L_0x0029
            r1 = 1439848066(0x55d25682, float:2.89086238E13)
            if (r0 == r1) goto L_0x001f
            r1 = 2120967672(0x7e6b65f8, float:7.822451E37)
            if (r0 == r1) goto L_0x0015
            goto L_0x0033
        L_0x0015:
            java.lang.String r0 = "Exercise"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 2
            goto L_0x0034
        L_0x001f:
            java.lang.String r0 = "Cardio Exercise"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 0
            goto L_0x0034
        L_0x0029:
            java.lang.String r0 = "Strength Exercise"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 1
            goto L_0x0034
        L_0x0033:
            r0 = -1
        L_0x0034:
            switch(r0) {
                case 0: goto L_0x0038;
                case 1: goto L_0x0038;
                case 2: goto L_0x0038;
                default: goto L_0x0037;
            }
        L_0x0037:
            goto L_0x0044
        L_0x0038:
            dagger.Lazy r4 = r3.diaryAnalyticsHelper
            java.lang.Object r4 = r4.get()
            com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper r4 = (com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper) r4
            r4.reportFriendDiaryCopyExerciseEvent()
            goto L_0x006d
        L_0x0044:
            com.myfitnesspal.shared.model.MealNames r0 = r3.mealNames
            int r0 = r0.size()
            if (r2 >= r0) goto L_0x006d
            com.myfitnesspal.shared.model.MealNames r0 = r3.mealNames
            java.util.List r0 = r0.getNames()
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = com.uacf.core.util.Strings.equals(r0, r4)
            if (r0 == 0) goto L_0x006a
            dagger.Lazy r4 = r3.diaryAnalyticsHelper
            java.lang.Object r4 = r4.get()
            com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper r4 = (com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper) r4
            r4.reportFriendDiaryCopuMealEvent(r2)
            goto L_0x006d
        L_0x006a:
            int r2 = r2 + 1
            goto L_0x0044
        L_0x006d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.ui.fragment.FriendDiaryFragment.reportCopyEvent(java.lang.String):void");
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.LOAD_STATE) {
            setBusy(this.viewModel.isBusy());
            if (this.viewModel.getState() == State.Error) {
                showError((ApiException) this.viewModel.getLastError());
                return;
            }
            return;
        }
        super.onViewModelPropertyChanged(observable, i);
    }

    private void showError(ApiException apiException) {
        switch (apiException.getStatusCode()) {
            case 256:
                showDialogForInvalidUser();
                return;
            case 257:
                showDialogWithMessageAndFinishOnClick(R.string.diary_permission_denied);
                return;
            case 258:
                promptForPassword();
                return;
            default:
                return;
        }
    }

    private void showDialogForInvalidUser() {
        showDialogWithMessageAndFinishOnClick(R.string.non_existent_user);
    }

    private void showDialogWithMessageAndFinishOnClick(int i) {
        final FragmentActivity activity = getActivity();
        new MfpAlertDialogBuilder(activity).setMessage(i).setPositiveButton((int) R.string.ok, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
            }
        }).setCancelable(false).setCanceledOnTouchOutside(false).show();
    }

    private void promptForPassword() {
        showDialogFragment(FriendDiaryPasswordDialogFragment.newInstance(), Fragments.FRIEND_DIARY_PASSWORD_DIALOG);
    }

    @Subscribe
    public void onPasswordEnteredEvent(PasswordEnteredEvent passwordEnteredEvent) {
        this.friendDiaryBundleInfo.setDiaryToken(passwordEnteredEvent.getPassword());
        onContentPagerCreated();
    }

    @Subscribe
    public void onPasswordCanceledEvent(PasswordCanceledEvent passwordCanceledEvent) {
        getActivity().finish();
    }

    public void onBottomRowAddFoodClick(String str) {
        reportCopyEvent(str);
        showDialogFragment(DiaryQuickToolsDialogFragment.newInstance(str), Fragments.DIARY_QUICK_TOOL_DIALOG);
    }

    public void onBottomRowShowMoreActionsClick(String str) {
        navigateToCreateMealForMealName(str, MealAnalyticsHelper.VALUE_DIARY_MORE);
    }

    public void onBottomRowSaveMealActionsClick(String str) {
        navigateToCreateMealForMealName(str, MealAnalyticsHelper.VALUE_DIARY_FRIEND);
    }

    public void onMealSelected(String str, String str2, Date date) {
        this.diaryDelegate.copyEntriesFromSectionToSectionOnDate(getCurrentDiaryDay(), date, str, str2);
        navigateToDiaryAfterCopying();
    }

    private void navigateToDiaryAfterCopying() {
        getNavigationHelper().withIntent(Diary.newStartIntentWithReferrer(getActivity(), Extras.REFERRER_DIARY_JUST_LOGGED)).finishActivityAfterNavigation().startActivity();
    }
}
