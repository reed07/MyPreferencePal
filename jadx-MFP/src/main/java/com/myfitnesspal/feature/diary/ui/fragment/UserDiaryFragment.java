package com.myfitnesspal.feature.diary.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.UnitDialogDismissedEvent;
import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity;
import com.myfitnesspal.feature.diary.event.CopyEntriesToDateEvent;
import com.myfitnesspal.feature.diary.event.DeleteEntryEvent;
import com.myfitnesspal.feature.diary.event.DiaryItemCheckedEvent;
import com.myfitnesspal.feature.diary.event.DiaryUpdatedExternallyEvent;
import com.myfitnesspal.feature.diary.event.ExerciseTypeEvent;
import com.myfitnesspal.feature.diary.event.MoreItemEventSelected;
import com.myfitnesspal.feature.diary.event.NoteTypeEvent;
import com.myfitnesspal.feature.diary.event.ShowMealsDialogEvent;
import com.myfitnesspal.feature.diary.event.ShowQuickAddCaloriesDialogEvent;
import com.myfitnesspal.feature.diary.model.DiaryViewModelBase;
import com.myfitnesspal.feature.diary.model.UserDiaryViewModel;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryCompletedTask;
import com.myfitnesspal.feature.diary.service.DiaryCompletedTask.CompletedEvent;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.diary.ui.activity.CompleteDiaryActivity;
import com.myfitnesspal.feature.diary.ui.activity.DiarySettingsActivity;
import com.myfitnesspal.feature.diary.ui.activity.EditDiaryNoteView;
import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter;
import com.myfitnesspal.feature.diary.ui.dialog.DiaryLongPressDialogFragment;
import com.myfitnesspal.feature.diary.ui.dialog.DiaryMoreActionsDialog;
import com.myfitnesspal.feature.diary.ui.dialog.DiaryQuickToolsDialogFragment;
import com.myfitnesspal.feature.diary.ui.dialog.NoteTypeDialogFragment;
import com.myfitnesspal.feature.diary.ui.item.SectionHeaderItem;
import com.myfitnesspal.feature.drawer.event.ClearDrawerMenuBadgeEvent;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.exercise.ui.activity.ExerciseSearchActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.nutrition.ui.activity.Nutrition;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.settings.ui.activity.RemindersActivity;
import com.myfitnesspal.feature.settings.ui.dialog.UnitsDialogFragment;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin.OnTimestampChangedListener;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl.WalkthroughType;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.event.DialogCalendarEvent;
import com.myfitnesspal.shared.event.DiaryPromoItemDismissedEvent;
import com.myfitnesspal.shared.event.InsightQuestionAnsweredEvent;
import com.myfitnesspal.shared.event.InsightsChangedEvent;
import com.myfitnesspal.shared.event.QuickAddCalorieAddedEvent;
import com.myfitnesspal.shared.event.WaterAddedEvent;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.WaterEntry;
import com.myfitnesspal.shared.model.v15.CompleteDiaryDayResultObject;
import com.myfitnesspal.shared.notification.MfpNotificationHandler;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.CalorieAddErrorDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.CustomSwipeableViewPager;
import com.myfitnesspal.shared.util.AdsHelper.DfpAdsListener;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.DateUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.myfitnesspal.shared.util.Toaster;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UserDiaryFragment extends DiaryFragmentBase implements DfpAdsListener {
    private static final int ACTION_COPY = 2000;
    private static final int ACTION_DELETE = 2002;
    private static final int ACTION_SAVE = 2001;
    private static final String ANALYTIC_ATTRIBUTE_ADD_NOTE = "add_note";
    private static final String ANALYTIC_ATTRIBUTE_ADD_WATER = "add_water";
    private static final String ANALYTIC_ATTRIBUTE_COMPLETE_ENTRY = "complete_entry";
    private static final String ANALYTIC_ATTRIBUTE_COPY_TO_DATE = "copy_to_date";
    private static final String ANALYTIC_ATTRIBUTE_CREATE_MEAL = "create_meal";
    private static final int COMPLETE_ENTRY_ACTION_ITEM = 12994;
    private static final String DATE = "date";
    private static final int EDIT_ACTION_ITEM = 12995;
    private static final String EXTRA_SELECTED_SECTION_NAME = "selected_section_name";
    private static final String EXTRA_SELECTED_SECTION_ORIGINAL_TIME = "selected_section_original_time";
    private static final int NOTES_ACTION_ITEM = 12993;
    private static final String NOTIFICATION_ID = "notification_id";
    private static final int NUTRITION_ACTION_ITEM = 12991;
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog_tag";
    private static final String REFERRER = "referrer";
    private static final int SETTINGS_ACTION_ITEM = 12996;
    private static final String SHOW_WALKTHROUGH = "show_walkthrough";
    private static final int WATER_ACTION_ITEM = 12992;
    @Inject
    Lazy<AdsAnalyticsHelper> adsAnalyticsHelper;
    private OnPageChangeListener diaryPagerOnPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (UserDiaryFragment.this.showWalkThrough) {
                UserDiaryFragment.this.setBehaviorForWalkthrough(false);
            }
        }
    };
    private ActionMode editActionMode;
    @Inject
    Lazy<ExerciseAnalyticsHelper> exerciseAnalyticsHelper;
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    private Object longPressEntryToDelete = null;
    @Inject
    Lazy<MealAnalyticsHelper> mealAnalyticsHelper;
    @Inject
    Lazy<MfpNotificationHandler> mfpNotificationHandler;
    private String mostRecentlyAddedMealName;
    private Date mostRecentlyAddedMealNameDate;
    final Function2<View, WalkthroughType> newSkipEventCallback = new Function2<View, WalkthroughType>() {
        public void execute(View view, WalkthroughType walkthroughType) throws RuntimeException {
            UserDiaryFragment.this.skipWalkthrough(view);
            UserDiaryFragment.this.setBehaviorForWalkthrough(false);
            ((BottomBarMixin) ((MfpActivity) UserDiaryFragment.this.getActivity()).mixin(BottomBarMixin.class)).setEnabled(true);
        }
    };
    private Date originalTime;
    private String sectionName;
    @Inject
    Lazy<Session> session;
    /* access modifiers changed from: private */
    public boolean showWalkThrough;
    @Inject
    Lazy<SyncService> syncService;
    private TimestampPickerMixin timestampPickerMixin;
    @Inject
    Lazy<WalkthroughUtil> walkthroughUtil;
    private View walkthroughView;
    @Inject
    Lazy<WaterLoggingAnalyticsHelper> waterLoggingAnalyticsHelper;

    class EditActionMode implements Callback {
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        EditActionMode() {
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            UserDiaryFragment.this.clearDiaryItemsChecked();
            actionMode.setTitle(R.string.select_item);
            MenuItemCompat.setShowAsAction(menu.add(0, 2000, 1, R.string.copy_to_date).setIcon(R.drawable.ic_content_copy_white_24dp), 0);
            MenuItemCompat.setShowAsAction(menu.add(0, 2001, 2, R.string.save_meal).setIcon(R.drawable.ic_save_meal_white_24dp), 0);
            MenuItemCompat.setShowAsAction(menu.add(0, 2002, 3, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp), 2);
            UserDiaryFragment.this.toggleEditingAndProperties(true);
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0065, code lost:
            if (com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.access$700(r6, r6.diaryDelegate.getDiaryItemsChecked(), r5) != false) goto L_0x0067;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onActionItemClicked(final android.view.ActionMode r5, android.view.MenuItem r6) {
            /*
                r4 = this;
                int r0 = r6.getItemId()
                r1 = 0
                r2 = 1
                r3 = 2002(0x7d2, float:2.805E-42)
                if (r0 != r3) goto L_0x0046
                boolean r6 = r4.showToastIfNothingSelected()
                if (r6 == 0) goto L_0x0011
                return r2
            L_0x0011:
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment r6 = com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.this
                dagger.Lazy r6 = r6.configService
                java.lang.Object r6 = r6.get()
                com.myfitnesspal.shared.service.config.ConfigService r6 = (com.myfitnesspal.shared.service.config.ConfigService) r6
                java.lang.String r0 = "material-accidental-delete-android-2016-03"
                boolean r6 = r6.isVariantEnabled(r0)
                if (r6 == 0) goto L_0x0039
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment r6 = com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.this
                dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r6 = r6.localSettingsService
                java.lang.Object r6 = r6.get()
                com.myfitnesspal.shared.service.localsettings.LocalSettingsService r6 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r6
                boolean r6 = r6.shouldShowDeleteDiaryEntriesConfirm()
                if (r6 == 0) goto L_0x0039
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment r6 = com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.this
                r6.showDeleteConfirmDialog(r5)
                goto L_0x003e
            L_0x0039:
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment r6 = com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.this
                r6.deleteSelectedItems(r5)
            L_0x003e:
                java.lang.String r5 = "Action Item Clicked."
                java.lang.Object[] r6 = new java.lang.Object[r1]
                com.uacf.core.util.Ln.d(r5, r6)
                return r2
            L_0x0046:
                int r0 = r6.getItemId()
                r3 = 2000(0x7d0, float:2.803E-42)
                if (r0 != r3) goto L_0x0069
                java.lang.String r6 = "copy_to_date"
                r4.reportOverflowItemClicked(r6)
                boolean r6 = r4.showToastIfNothingSelected()
                if (r6 != 0) goto L_0x0067
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment r6 = com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.this
                com.myfitnesspal.feature.diary.util.DiaryDelegate r0 = r6.diaryDelegate
                java.util.ArrayList r0 = r0.getDiaryItemsChecked()
                boolean r5 = r6.copyItems(r0, r5)
                if (r5 == 0) goto L_0x0068
            L_0x0067:
                r1 = 1
            L_0x0068:
                return r1
            L_0x0069:
                int r6 = r6.getItemId()
                r0 = 2001(0x7d1, float:2.804E-42)
                if (r6 != r0) goto L_0x00b4
                java.lang.String r6 = "create_meal"
                r4.reportOverflowItemClicked(r6)
                boolean r6 = r4.showToastIfNothingSelected()
                if (r6 == 0) goto L_0x007d
                return r2
            L_0x007d:
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment r6 = com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.this
                com.myfitnesspal.shared.model.v1.DiaryDay r6 = r6.getCurrentDiaryDay()
                if (r6 != 0) goto L_0x0086
                return r2
            L_0x0086:
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment r0 = com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.this
                com.myfitnesspal.feature.diary.util.DiaryDelegate r0 = r0.diaryDelegate
                java.util.ArrayList r0 = r0.getDiaryItemsChecked()
                java.util.Iterator r0 = r0.iterator()
            L_0x0092:
                boolean r3 = r0.hasNext()
                if (r3 == 0) goto L_0x00b4
                java.lang.Object r3 = r0.next()
                java.lang.Long r3 = (java.lang.Long) r3
                com.myfitnesspal.shared.model.v1.FoodEntry r3 = r6.getFoodEntry(r3)
                if (r3 == 0) goto L_0x0092
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment r6 = com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.this
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment$EditActionMode$1 r0 = new com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment$EditActionMode$1
                r0.<init>(r5)
                com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment$EditActionMode$2 r5 = new com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment$EditActionMode$2
                r5.<init>()
                r6.saveAllCheckedItems(r0, r5)
                return r2
            L_0x00b4:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.EditActionMode.onActionItemClicked(android.view.ActionMode, android.view.MenuItem):boolean");
        }

        private void reportOverflowItemClicked(String str) {
            ((DiaryAnalyticsHelper) UserDiaryFragment.this.diaryAnalyticsHelper.get()).reportOverflowItemClickedEvent(str);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            ((DiaryAnalyticsHelper) UserDiaryFragment.this.diaryAnalyticsHelper.get()).reportDiaryEditCloseButtonEvent();
            UserDiaryFragment.this.toggleEditingAndProperties(false);
        }

        private boolean showToastIfNothingSelected() {
            if (CollectionUtils.notEmpty((Collection<?>) UserDiaryFragment.this.diaryDelegate.getDiaryItemsChecked())) {
                return false;
            }
            Toaster.showShort((Activity) UserDiaryFragment.this.getActivity(), (int) R.string.nothing_selected);
            return true;
        }
    }

    private class TimeSelectedListener implements OnTimestampChangedListener {
        private final Date originalTime;
        private final String sectionName;

        private TimeSelectedListener(Date date, String str) {
            this.originalTime = date;
            this.sectionName = str;
        }

        public void onTimestampChange(@Nullable Date date, @NotNull TimestampOption timestampOption) {
            boolean z = (date == null && this.originalTime != null) || (date != null && this.originalTime == null) || (date != null && !date.equals(this.originalTime));
            ((DiaryAnalyticsHelper) UserDiaryFragment.this.diaryAnalyticsHelper.get()).reportTimeHeaderSaved(timestampOption, z);
            if (z) {
                for (FoodEntry foodEntry : UserDiaryFragment.this.getFoodEntriesForMeal(this.sectionName)) {
                    if ((foodEntry.getEntryTime() == null && this.originalTime == null) || (foodEntry.getEntryTime() != null && foodEntry.getEntryTime().equals(this.originalTime))) {
                        foodEntry.setEntryTimeAndUpdateLoggedAt(date);
                        UserDiaryFragment.this.getCurrentDiaryDay().updateFoodEntry(foodEntry);
                    }
                }
                UserDiaryFragment.this.markCurrentDiaryDayInCacheAsStaleAndRefresh(true);
            }
        }
    }

    public void onAdFailedToLoad() {
    }

    public void onAdLoaded() {
    }

    public void onBannerAdLoaded() {
    }

    public void onBottomRowSaveMealActionsClick(String str) {
    }

    public static UserDiaryFragment newInstance(String str, int i, boolean z, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("date", str);
        bundle.putInt("notification_id", i);
        bundle.putBoolean(SHOW_WALKTHROUGH, z);
        bundle.putString("referrer", str2);
        UserDiaryFragment userDiaryFragment = new UserDiaryFragment();
        userDiaryFragment.setArguments(bundle);
        return userDiaryFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        this.timestampPickerMixin = new TimestampPickerMixin((MfpActivity) getActivity());
        registerMixin(this.timestampPickerMixin);
        if (bundle != null) {
            this.sectionName = bundle.getString(EXTRA_SELECTED_SECTION_NAME);
            this.originalTime = (Date) bundle.getSerializable(EXTRA_SELECTED_SECTION_ORIGINAL_TIME);
            this.timestampPickerMixin.setTimestampChangeListener(new TimeSelectedListener(this.originalTime, this.sectionName));
        }
        postEvent(new ClearDrawerMenuBadgeEvent());
        Bundle arguments = getArguments();
        int i = BundleUtils.getInt(arguments, "notification_id", -1);
        if (i != -1) {
            ((MfpNotificationHandler) this.mfpNotificationHandler.get()).removeNotificationWithId(i);
        }
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportReferrer(BundleUtils.getString(arguments, "referrer", "unknown"));
    }

    public void onActivityCreated(Bundle bundle) {
        useDateFromExtrasIfItExists();
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        this.showWalkThrough = BundleUtils.getBoolean(getArguments(), SHOW_WALKTHROUGH, false);
        this.diaryDelegate.setWalkthroughVisible(this.showWalkThrough);
    }

    /* access modifiers changed from: protected */
    public DiaryViewModelBase getViewModelInstance() {
        return new UserDiaryViewModel(getRunner(), this.diaryService, this.nutrientGoalService, this);
    }

    public void onResume() {
        super.onResume();
        showCreateMealDialog(getActivity().getIntent());
        scrollToJustAddedFoodOrExercise();
        showToastIfNecessary();
        markDiaryCacheStaleRefreshAndDebounceSync();
        ActionMode actionMode = this.editActionMode;
        if (actionMode != null) {
            actionMode.setTitle(R.string.select_item);
        }
        clearDiaryItemsChecked();
        forEachDiaryAdapter(new Function1<DiaryAdapter>() {
            public void execute(DiaryAdapter diaryAdapter) {
                diaryAdapter.resume();
            }
        });
    }

    public void onPause() {
        super.onPause();
        hideCopyToDateDialog();
        ViewUtils.setVisible(false, this.walkthroughView);
        ((BottomBarMixin) ((MfpActivity) getActivity()).mixin(BottomBarMixin.class)).setEnabled(true);
        forEachDiaryAdapter(new Function1<DiaryAdapter>() {
            public void execute(DiaryAdapter diaryAdapter) {
                diaryAdapter.pause();
            }
        });
    }

    public void onDestroy() {
        destroyCurrentDFPAdViews();
        super.onDestroy();
    }

    public void onTimestampClick(String str, Date date) {
        this.sectionName = str;
        this.originalTime = date;
        Date latestEntryTimeForMealName = getCurrentDiaryDay().getLatestEntryTimeForMealName(str);
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportTimeHeaderTapped(date == null ? DiaryAnalyticsHelper.ATTR_VALUE_NO_TIME : DiaryAnalyticsHelper.ATTR_VALUE_HAS_TIME);
        this.timestampPickerMixin.setLatestEntryTimestamp(latestEntryTimeForMealName);
        this.timestampPickerMixin.setTimestampChangeListener(new TimeSelectedListener(date, str));
        this.timestampPickerMixin.showTimestampOptions();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_SELECTED_SECTION_NAME, this.sectionName);
        bundle.putSerializable(EXTRA_SELECTED_SECTION_ORIGINAL_TIME, this.originalTime);
    }

    private void destroyCurrentDFPAdViews() {
        forEachDiaryAdapter(new Function1<DiaryAdapter>() {
            public void execute(DiaryAdapter diaryAdapter) {
                diaryAdapter.destroy();
            }
        });
    }

    private void forEachDiaryAdapter(Function1<DiaryAdapter> function1) {
        for (Calendar diaryAdapterForCalendarDate : this.dateList) {
            DiaryAdapter diaryAdapterForCalendarDate2 = getDiaryAdapterForCalendarDate(diaryAdapterForCalendarDate);
            if (diaryAdapterForCalendarDate2 != null) {
                function1.execute(diaryAdapterForCalendarDate2);
            }
        }
    }

    private void scrollToJustAddedFoodOrExercise() {
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
        if (diaryDayForActiveDateSync != null) {
            checkForJustAddedExercise(diaryDayForActiveDateSync);
            checkForJustAddedFoods(diaryDayForActiveDateSync);
        }
    }

    private void checkForJustAddedFoods(DiaryDay diaryDay) {
        if (diaryDay.getJustAddedFoodEntry() != null || !Strings.isEmpty(diaryDay.getJustAddedMultipleItemsMealName())) {
            FoodEntry justAddedFoodEntry = diaryDay.getJustAddedFoodEntry();
            this.mostRecentlyAddedMealName = justAddedFoodEntry != null ? justAddedFoodEntry.getMealName() : diaryDay.getJustAddedMultipleItemsMealName();
            this.mostRecentlyAddedMealNameDate = diaryDay.getDate();
            this.diaryDelegate.addMostRecentlyAddedEntry(justAddedFoodEntry);
        }
    }

    private void checkForJustAddedExercise(DiaryDay diaryDay) {
        ExerciseEntry justAddedExerciseEntry = diaryDay.getJustAddedExerciseEntry();
        if (justAddedExerciseEntry != null || !Strings.isEmpty(diaryDay.getJustAddedMultipleItemsMealName())) {
            this.diaryDelegate.addMostRecentlyAddedEntry(justAddedExerciseEntry);
            switchToNewDate(diaryDay.getDate());
        }
    }

    private void showToastIfNecessary() {
        this.diaryDelegate.showToastIfNecessary();
    }

    private void resetCurrentAdapterListAndMap(DiaryDay diaryDay) {
        DiaryAdapter currentRecyclerViewAdapter = getCurrentRecyclerViewAdapter();
        if (currentRecyclerViewAdapter != null) {
            this.diaryDelegate.resetDiaryAdapterListAndMap(diaryDay, getNutrientGoalForDate(diaryDay.getDate()), currentRecyclerViewAdapter);
        }
    }

    private void refreshCurrentNutrientDashboard(DiaryDay diaryDay) {
        Calendar selectedDate = getSelectedDate();
        ViewGroup nutrientDashboardContainerForDate = getNutrientDashboardContainerForDate(selectedDate);
        if (nutrientDashboardContainerForDate != null) {
            refreshNutrientDashboard(nutrientDashboardContainerForDate, selectedDate, diaryDay);
        }
    }

    private void refreshDiaryData() {
        setupViewForDiaryDay(getCurrentDiaryDay());
    }

    private void useDateFromExtrasIfItExists() {
        String string = BundleUtils.getString(getArguments(), "date", (String) null);
        if (string != null) {
            Date parse = DateTimeUtils.parse("yyyy-MM-dd", string);
            if (parse != null) {
                ((Session) this.session.get()).getUser().setActiveDate(parse);
            }
        }
    }

    public void onContentPagerCreated() {
        if (this.contentPager != null) {
            this.contentPager.removeOnPageChangeListener(this.diaryPagerOnPageChangeListener);
            this.contentPager.addOnPageChangeListener(this.diaryPagerOnPageChangeListener);
        }
        super.onContentPagerCreated();
    }

    /* access modifiers changed from: protected */
    public void fetchDiaryDay(Calendar calendar) {
        ((UserDiaryViewModel) this.viewModel).load(calendar.getTime());
    }

    /* access modifiers changed from: protected */
    public View renderPageView(ViewGroup viewGroup, int i) {
        View renderPageView = super.renderPageView(viewGroup, i);
        if (this.showWalkThrough) {
            showWalkthrough(renderPageView, i);
        }
        return renderPageView;
    }

    /* access modifiers changed from: protected */
    public void destroyPageItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyPageItem(viewGroup, i, obj);
        if (i < this.dateList.size()) {
            DiaryAdapter diaryAdapterForCalendarDate = getDiaryAdapterForCalendarDate((Calendar) this.dateList.get(i));
            if (diaryAdapterForCalendarDate != null) {
                diaryAdapterForCalendarDate.destroy();
            }
        }
    }

    /* access modifiers changed from: protected */
    public Date currentDate() {
        Date activeDate = ((Session) this.session.get()).getUser().getActiveDate();
        return activeDate != null ? activeDate : getSelectedDate().getTime();
    }

    /* access modifiers changed from: private */
    public void showDeleteConfirmDialog(ActionMode actionMode) {
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.delete_entry_confirm, null, false);
        CheckBox checkBox = (CheckBox) ViewUtils.findById(inflate, R.id.dont_ask);
        ((TextView) ViewUtils.findById(inflate, R.id.message)).setText(this.diaryDelegate.getItemsSelectedCount() > 1 ? R.string.are_you_sure_you_want_to_delete_item : R.string.are_you_sure_you_want_to_delete_item_single);
        mfpAlertDialogBuilder.setPositiveButton((int) R.string.delete, (OnClickListener) new OnClickListener(checkBox, actionMode) {
            private final /* synthetic */ CheckBox f$1;
            private final /* synthetic */ ActionMode f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                UserDiaryFragment.lambda$showDeleteConfirmDialog$0(UserDiaryFragment.this, this.f$1, this.f$2, dialogInterface, i);
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener(checkBox) {
            private final /* synthetic */ CheckBox f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                UserDiaryFragment.lambda$showDeleteConfirmDialog$1(UserDiaryFragment.this, this.f$1, dialogInterface, i);
            }
        }).setOnCancelListener($$Lambda$aOHe9tZ4zgJyFvnk2RdcLuAcuE.INSTANCE).setTitle((int) R.string.delete).setView(inflate).show();
    }

    public static /* synthetic */ void lambda$showDeleteConfirmDialog$0(UserDiaryFragment userDiaryFragment, CheckBox checkBox, ActionMode actionMode, DialogInterface dialogInterface, int i) {
        if (checkBox.isChecked()) {
            ((LocalSettingsService) userDiaryFragment.localSettingsService.get()).setShowDeleteDiaryEntriesConfirm(false);
        }
        userDiaryFragment.deleteSelectedItems(actionMode);
        userDiaryFragment.reportDeleteConfirm("diary_edit", checkBox.isChecked(), true);
    }

    public static /* synthetic */ void lambda$showDeleteConfirmDialog$1(UserDiaryFragment userDiaryFragment, CheckBox checkBox, DialogInterface dialogInterface, int i) {
        userDiaryFragment.reportDeleteConfirm("diary_edit", checkBox.isChecked(), false);
        dialogInterface.cancel();
    }

    /* access modifiers changed from: private */
    public void deleteSelectedItems(final ActionMode actionMode) {
        setBusy(1, true);
        deleteAllCheckedItems(new Function0() {
            public void execute() throws RuntimeException {
                actionMode.finish();
            }
        }, new Function0() {
            public void execute() throws RuntimeException {
                UserDiaryFragment.this.setBusy(1, false);
            }
        });
    }

    public void deleteEntry(DatabaseObject databaseObject) {
        deleteEntry(databaseObject, null, false);
    }

    private void showProgressDialogFragment() {
        DialogFragment currentProgressDialogFragment = getCurrentProgressDialogFragment();
        if (currentProgressDialogFragment == null) {
            currentProgressDialogFragment = ProgressDialogFragment.newInstance(R.string.please_wait);
        }
        if (!currentProgressDialogFragment.isAdded()) {
            currentProgressDialogFragment.setCancelable(false);
            currentProgressDialogFragment.show(getActivity().getSupportFragmentManager(), PROGRESS_DIALOG_TAG);
        }
    }

    private void dismissProgressDialogFragment() {
        DialogFragment currentProgressDialogFragment = getCurrentProgressDialogFragment();
        if (currentProgressDialogFragment != null) {
            currentProgressDialogFragment.dismiss();
        }
    }

    private DialogFragment getCurrentProgressDialogFragment() {
        return (DialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag(PROGRESS_DIALOG_TAG);
    }

    @Subscribe
    public void onShowMealsDialogEvent(ShowMealsDialogEvent showMealsDialogEvent) {
        List names = ((Session) this.session.get()).getUser().getMealNames().getNames();
        new MfpAlertDialogBuilder(getActivity()).setItems(Enumerable.select((Collection<T>) names, (ReturningFunction1<U, T>) new ReturningFunction1() {
            public final Object execute(Object obj) {
                return UserDiaryFragment.lambda$onShowMealsDialogEvent$2(UserDiaryFragment.this, (String) obj);
            }
        }), new OnItemClickListener(names) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                UserDiaryFragment.lambda$onShowMealsDialogEvent$3(UserDiaryFragment.this, this.f$1, adapterView, view, i, j);
            }
        }).setTitle((int) R.string.move_to).show();
    }

    public static /* synthetic */ DialogListTextItem lambda$onShowMealsDialogEvent$2(UserDiaryFragment userDiaryFragment, String str) throws RuntimeException {
        return new DialogListTextItem(((LocalizedStringsUtil) userDiaryFragment.localizedStringsUtil.get()).getMealNameString(str, (UserEnergyService) userDiaryFragment.userEnergyService.get()));
    }

    public static /* synthetic */ void lambda$onShowMealsDialogEvent$3(UserDiaryFragment userDiaryFragment, List list, AdapterView adapterView, View view, int i, long j) {
        DiaryDay currentDiaryDay = userDiaryFragment.getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            Ln.d(Integer.valueOf(i), new Object[0]);
            String str = (String) list.get(i);
            FoodEntry foodEntry = (FoodEntry) userDiaryFragment.longPressEntryToDelete;
            if (!str.equals(foodEntry.getMealName())) {
                foodEntry.setMealName(str);
                currentDiaryDay.updateFoodEntry(foodEntry);
                userDiaryFragment.markCurrentDiaryDayInCacheAsStaleAndRefresh(true);
                ((DiaryAnalyticsHelper) userDiaryFragment.diaryAnalyticsHelper.get()).reportFoodEntryMoved();
            }
        }
    }

    @Subscribe
    public void onDiaryPromoItemDismissedEvent(DiaryPromoItemDismissedEvent diaryPromoItemDismissedEvent) {
        refreshDiaryData();
    }

    @Subscribe
    public void onDeleteEntryEvent(DeleteEntryEvent deleteEntryEvent) {
        deleteEntry((DatabaseObject) this.longPressEntryToDelete, new Function0() {
            public void execute() throws RuntimeException {
                UserDiaryFragment.this.dismissDialog(Fragments.LONG_PRESS_DIALOG);
            }
        }, true);
    }

    @Subscribe
    public void onExerciseTypeEvent(ExerciseTypeEvent exerciseTypeEvent) {
        int exerciseType = exerciseTypeEvent.getExerciseType();
        ((ExerciseAnalyticsHelper) this.exerciseAnalyticsHelper.get()).reportAddExerciseScreenDisplayed("diary", exerciseType);
        NavigationHelper navigationHelper = getNavigationHelper();
        navigationHelper.withExtra(Extras.ACTIVE_BUTTON, 6005).withExtra(Extras.IS_VIEWING_MULTI_ADD_ITEMS, false);
        navigationHelper.withIntent(ExerciseSearchActivity.newStartIntentForExerciseType(getActivity(), exerciseType)).startActivity(exerciseType == 0 ? 47 : 48);
    }

    @Subscribe
    public void onDiaryUpdatedExternally(DiaryUpdatedExternallyEvent diaryUpdatedExternallyEvent) {
        markDiaryCacheStaleAndRefresh();
    }

    public void deleteEntry(DatabaseObject databaseObject, Function0 function0, boolean z) {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            boolean deleteItem = this.diaryDelegate.deleteItem(databaseObject, currentDiaryDay);
            resetCurrentAdapterListAndMap(currentDiaryDay);
            if (z) {
                markCurrentDiaryDayInCacheAsStaleAndRefresh(true);
            }
            if (deleteItem) {
                refreshCurrentNutrientDashboard(currentDiaryDay);
            }
            FunctionUtils.invokeIfValid(function0);
            invalidateOptionsMenu();
        }
    }

    @Subscribe
    public void onDiaryCompletedTaskCompletedEvent(CompletedEvent completedEvent) {
        dismissProgressDialogFragment();
        setBusy(1, false);
        if (!completedEvent.successful() || !isEnabled()) {
            handleDiaryCompleteError();
            return;
        }
        CompleteDiaryDayResultObject completeDiaryDayResultObject = (CompleteDiaryDayResultObject) completedEvent.getResult();
        if (completeDiaryDayResultObject != null) {
            getNavigationHelper().withIntent(CompleteDiaryActivity.newStartIntent(getActivity(), completeDiaryDayResultObject.getProjectedWeightInPounds())).startActivity();
        } else {
            handleDiaryCompleteError();
        }
    }

    /* access modifiers changed from: private */
    public void markCurrentDiaryDayInCacheAsStaleAndRefresh(boolean z) {
        ((DiaryService) this.diaryService.get()).markDiaryDayCacheEntryStaleForDate(getSelectedDate().getTime());
        fetchDiaryDay(getSelectedDate());
        if (z) {
            debounceSync();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 51) {
            if (i != 54) {
                if (i != 87) {
                    if (i != 159) {
                        switch (i) {
                            case 100:
                                if (i2 == -1) {
                                    markCurrentDiaryDayInCacheAsStaleAndRefresh(false);
                                    return;
                                }
                                return;
                            case 101:
                                if (i2 == -1) {
                                    markCurrentDiaryDayInCacheAsStaleAndRefresh(false);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    } else if (i2 == -1) {
                        getImmHelper().hideSoftInput();
                        updateQuickAddEntry(this.diaryDelegate.getMealNameForQuickAdd(), (QuickAddFood) ExtrasUtils.getParcelable(intent, QuickAddActivity.EXTRA_QUICK_ADD_OBJECT, QuickAddFood.class.getClassLoader()));
                    }
                } else if (i2 == -1 && intent != null) {
                    showCreateMealDialog(intent);
                    markCurrentDiaryDayInCacheAsStaleAndRefresh(false);
                }
            } else if (i2 == -1) {
                markCurrentDiaryDayInCacheAsStaleAndRefresh(true);
            }
        } else if (i2 == -1) {
            markCurrentDiaryDayInCacheAsStaleAndRefresh(false);
        }
    }

    private void debounceSync() {
        ((UacfScheduler) this.syncScheduler.get()).debounceDefaultSync();
    }

    private void toggleEditMode() {
        this.editActionMode = ((AppCompatActivity) getActivity()).startActionMode(new EditActionMode());
    }

    /* access modifiers changed from: private */
    public void toggleEditingAndProperties(boolean z) {
        this.diaryDelegate.setEditing(z);
        ((CustomSwipeableViewPager) this.contentPager).setSwipeEnabled(!z);
        ViewUtils.setVisible(!z, this.next);
        ViewUtils.setVisible(!z, this.previous);
        ViewUtils.setVisible(z, getCurrentSelectAllContainer());
        this.date.setEnabled(!z);
        if (z) {
            selectAllDiaryCategoryHeaders(false);
            updateSelectAllButton(getCurrentSelectAllButton());
        } else {
            CompoundButton currentSelectAllButton = getCurrentSelectAllButton();
            if (currentSelectAllButton != null) {
                currentSelectAllButton.setOnCheckedChangeListener(null);
            }
        }
        onContentPagerCreated();
    }

    public void switchToCreateMealScreen() {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            ArrayList<Long> diaryItemsChecked = this.diaryDelegate.getDiaryItemsChecked();
            ArrayList arrayList = new ArrayList(CollectionUtils.size((Collection<?>) diaryItemsChecked));
            for (Long foodEntry : diaryItemsChecked) {
                FoodEntry foodEntry2 = currentDiaryDay.getFoodEntry(foodEntry);
                if (foodEntry2 != null) {
                    arrayList.add(foodEntry2);
                }
            }
            getNavigationHelper().withExtra(MealEditorMixin.EXTRA_FOOD_ENTRIES, (Serializable) arrayList).withIntent(FoodEditorActivity.newMealItemEditorIntent(getActivity(), null, null, null, "diary_edit")).startActivity(RequestCodes.FOOD_EDITOR);
        }
    }

    @Subscribe
    public void onWaterAddedEvent(WaterAddedEvent waterAddedEvent) {
        DiaryAdapter currentRecyclerViewAdapter = getCurrentRecyclerViewAdapter();
        showToastIfNecessary();
        if (currentRecyclerViewAdapter != null) {
            currentRecyclerViewAdapter.getCurrentDiaryDay().setWaterCups((float) waterAddedEvent.getCups());
            notifyDataSetChangedToDiaryAdapter(currentRecyclerViewAdapter);
        }
        refreshDiaryData();
    }

    @Subscribe
    public void onInsightsChanged(InsightsChangedEvent insightsChangedEvent) {
        notifyDataSetChangedToDiaryAdapter(getCurrentRecyclerViewAdapter());
    }

    @Subscribe
    public void onInsightsQuestionAnswered(InsightQuestionAnsweredEvent insightQuestionAnsweredEvent) {
        if (this.diaryDelegate.onInsightsQuestionAnswered(insightQuestionAnsweredEvent.getId()) >= 0) {
            notifyDataSetChangedToDiaryAdapter(getCurrentRecyclerViewAdapter());
        }
    }

    /* access modifiers changed from: private */
    public ArrayList<FoodEntry> getFoodEntriesForMeal(String str) {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay == null) {
            return new ArrayList<>();
        }
        return (ArrayList) currentDiaryDay.getFoodEntriesByMealName().get(str);
    }

    @Subscribe
    public void handleMoreItemEventSelected(MoreItemEventSelected moreItemEventSelected) {
        if (moreItemEventSelected != null) {
            String mealName = moreItemEventSelected.getMealName();
            String str = "";
            switch (moreItemEventSelected.getEventId()) {
                case Dialogs.SAVE_MEAL /*7618*/:
                    str = DiaryAnalyticsHelper.SAVE_MEAL;
                    navigateToCreateMealForMealName(mealName, MealAnalyticsHelper.VALUE_DIARY_MORE);
                    break;
                case Dialogs.COPY_MEAL /*7619*/:
                    str = DiaryAnalyticsHelper.COPY_MEAL;
                    copyAllItemsInMeal(mealName);
                    break;
                case Dialogs.QUICK_ADD /*7620*/:
                    str = DiaryAnalyticsHelper.QUICK_ADD;
                    this.diaryDelegate.setQuickFoodAndNavigateToQuickAdd(null, mealName);
                    break;
                case Dialogs.CHANGE_UNIT /*7624*/:
                    str = DiaryAnalyticsHelper.CHANGE_UNIT;
                    UnitsDialogFragment.newInstance().show(getFragmentManager(), Fragments.UNITS_DIALOG);
                    ((WaterLoggingAnalyticsHelper) this.waterLoggingAnalyticsHelper.get()).reportWaterUnitChangeClick(WaterLoggingAnalyticsHelper.DIARY_FOOTER);
                    break;
                case Dialogs.REMINDERS /*7625*/:
                    str = "reminders";
                    getNavigationHelper().withIntent(RemindersActivity.newStartIntent(getActivity())).startActivity();
                    break;
            }
            ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportMealMoreOptionTappedEvent(mealName, str);
        }
    }

    private void copyAllItemsInMeal(String str) {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            this.diaryDelegate.recreateCheckedItemsList(Enumerable.select((Collection<T>) (ArrayList) currentDiaryDay.getFoodEntriesByMealName().get(str), (ReturningFunction1<U, T>) new ReturningFunction1<Long, FoodEntry>() {
                public Long execute(FoodEntry foodEntry) throws RuntimeException {
                    return Long.valueOf(foodEntry.localId);
                }
            }));
            copyAllCheckedItems(null, new Function0() {
                public void execute() throws RuntimeException {
                    UserDiaryFragment.this.setBusy(1, false);
                }
            });
        }
    }

    @Subscribe
    public void onDiaryItemCheckedEvent(DiaryItemCheckedEvent diaryItemCheckedEvent) {
        DatabaseObject checkedDiaryItem = diaryItemCheckedEvent.getCheckedDiaryItem();
        boolean diaryItemCheckedEvent2 = this.diaryDelegate.diaryItemCheckedEvent(((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync(), checkedDiaryItem, diaryItemCheckedEvent.isChecked());
        refreshActionMode();
        selectHeaderSection(this.diaryDelegate.getSectionNameForListItem(checkedDiaryItem), diaryItemCheckedEvent2);
    }

    private void selectHeaderSection(String str, boolean z) {
        DiaryAdapter currentRecyclerViewAdapter = getCurrentRecyclerViewAdapter();
        if (currentRecyclerViewAdapter != null) {
            currentRecyclerViewAdapter.selectHeaderSection(str, z);
        }
    }

    @Subscribe
    public void onSyncFinished(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
        if (uacfScheduleFinishedInfo.getScheduleGroup() == SyncType.Incremental) {
            markDiaryCacheStaleAndRefresh();
        }
    }

    private void markDiaryCacheStaleAndRefresh() {
        ((DiaryService) this.diaryService.get()).markDiaryDayCacheStale();
        if (isAdded()) {
            int currentItemIndex = getCurrentItemIndex();
            int min = Math.min(CollectionUtils.size((Collection<?>) this.dateList) - 1, currentItemIndex + 1);
            for (int max = Math.max(0, currentItemIndex - 1); max <= min; max++) {
                fetchDiaryDay((Calendar) this.dateList.get(max));
            }
        }
    }

    private void markDiaryCacheStaleRefreshAndDebounceSync() {
        markDiaryCacheStaleAndRefresh();
        debounceSync();
    }

    @Subscribe
    public void onQuickAddCalorieAddedEvent(QuickAddCalorieAddedEvent quickAddCalorieAddedEvent) {
        updateQuickCalorieEntry(quickAddCalorieAddedEvent.getMealName(), quickAddCalorieAddedEvent.getCalories());
    }

    public void updateQuickCalorieEntry(String str, float f) {
        updateQuickAddEntry(str, new QuickAddFood(f));
    }

    private void updateQuickAddEntry(String str, QuickAddFood quickAddFood) {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            if (!NutritionUtils.areFoodValuesInRange(quickAddFood.getCalories(), quickAddFood.getCarbohydrate(), quickAddFood.getFat(), quickAddFood.getProtein())) {
                showServingErrorOrOutOfRangeDialog(Dialogs.OUT_OF_RANGE_ERROR_DIALOG);
                return;
            }
            this.diaryDelegate.updateQuickAddEntry(currentDiaryDay, str, quickAddFood, getSession().getUser());
            refreshDiaryData();
        }
    }

    @Subscribe
    public void onCopyEntriesToDateEvent(CopyEntriesToDateEvent copyEntriesToDateEvent) {
        Date targetDate = copyEntriesToDateEvent.getTargetDate();
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        List checkedFoodEntryItemsFromDiaryDay = this.diaryDelegate.getCheckedFoodEntryItemsFromDiaryDay(currentDiaryDay);
        this.diaryDelegate.copyEntriesToDate(currentDiaryDay, targetDate);
        switchToNewDate(targetDate);
        showSaveMealSnackBar(checkedFoodEntryItemsFromDiaryDay);
    }

    private void switchToNewDate(Date date) {
        setDate(date);
        Calendar instance = Calendar.getInstance();
        instance.setTime(currentDate());
        clearAndResetToNewDate(instance);
    }

    private void clearAndResetToNewDate(Calendar calendar) {
        refreshDatesList(calendar);
        if (this.contentPagerAdapter != null) {
            this.contentPagerAdapter.notifyDataSetChanged();
            this.contentPager.setCurrentItem(5, false);
        }
    }

    private void completeThisEntryLocally() {
        if (((Session) this.session.get()).getUser().hasMasterDatabaseId() && !((GlobalSettingsService) this.globalSettingsService.get()).getDontShowOfflineNotificationForCompleteDiaryDay()) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(getString(R.string.complete_your_acount), getString(R.string.complete_diary_offline_msg), getString(R.string.dont_show_again), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((GlobalSettingsService) UserDiaryFragment.this.globalSettingsService.get()).setDontShowOfflineNotificationForCompleteDiaryDay(true);
                    dialogInterface.cancel();
                }
            }, getString(R.string.dismiss), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
        }
    }

    private void reallyCompleteThisEntry(String str) {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            setBusy(1, true);
            showProgressDialogFragment();
            ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportCompleteButtonTapEvent(str);
            new DiaryCompletedTask(this.syncService, this.diaryService, currentDiaryDay).run(getRunner());
        }
    }

    private boolean shouldCompleteThisEntryLocally() {
        if (!((Session) this.session.get()).getUser().hasMasterDatabaseId()) {
            return true;
        }
        return ConnectivityUtil.isOffline().booleanValue();
    }

    @Subscribe
    public void onNoteTypeEvent(NoteTypeEvent noteTypeEvent) {
        DiaryNote diaryNote;
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            int noteType = noteTypeEvent.getNoteType();
            switch (noteType) {
                case 0:
                    diaryNote = currentDiaryDay.getFoodNote();
                    break;
                case 1:
                    diaryNote = currentDiaryDay.getExerciseNote();
                    break;
                default:
                    diaryNote = null;
                    break;
            }
            if (diaryNote == null) {
                diaryNote = new DiaryNote();
                diaryNote.setNoteType(noteType);
                diaryNote.setEntryDate(currentDiaryDay.getDate());
                diaryNote.setBody("");
            }
            EditDiaryNoteView.setDiaryNote(diaryNote);
            getNavigationHelper().withExtra(Extras.TITLE_FOR_NOTE, getString(diaryNote.getNoteTitle())).withIntent(EditDiaryNoteView.newStartIntent(getActivity())).startActivity(51);
        }
    }

    @Subscribe
    public void onDateSetFromDatePicker(DialogCalendarEvent dialogCalendarEvent) {
        if (dialogCalendarEvent != null) {
            Calendar calendar = dialogCalendarEvent.getCalendar();
            clearAndResetToNewDate(calendar);
            setDate(calendar.getTime());
        }
    }

    @Subscribe
    public void onUnitDialogDismissedEvent(UnitDialogDismissedEvent unitDialogDismissedEvent) {
        if (!unitDialogDismissedEvent.isCancelled()) {
            ((WaterLoggingAnalyticsHelper) this.waterLoggingAnalyticsHelper.get()).reportWaterUnitUpdated(WaterLoggingAnalyticsHelper.DIARY_FOOTER, LocalizedFluid.getUserCurrentWaterUnit((Session) this.session.get()));
            refreshDiaryData();
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            if (!currentDiaryDay.isEmpty()) {
                MenuItemCompat.setShowAsAction(menu.add(0, EDIT_ACTION_ITEM, 0, R.string.edit).setIcon(R.drawable.ic_edit_white_24dp), 2);
            }
            MenuItemCompat.setShowAsAction(menu.add(0, NUTRITION_ACTION_ITEM, 0, R.string.nutrition).setIcon(R.drawable.ic_nutrition_black_24_dp), 2);
            MenuItemCompat.setShowAsAction(menu.add(0, WATER_ACTION_ITEM, 0, R.string.add_water), 0);
            MenuItemCompat.setShowAsAction(menu.add(0, NOTES_ACTION_ITEM, 0, R.string.add_note), 0);
            MenuItemCompat.setShowAsAction(menu.add(0, COMPLETE_ENTRY_ACTION_ITEM, 0, R.string.complete_diary), 0);
            if (ConfigUtils.isAppNavUpdateDiaryEnabled(getConfigService())) {
                MenuItemCompat.setShowAsAction(menu.add(0, SETTINGS_ACTION_ITEM, 0, R.string.settings_diary_settings), 0);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String str;
        switch (menuItem.getItemId()) {
            case NUTRITION_ACTION_ITEM /*12991*/:
                onNutritionClick(DiaryAnalyticsHelper.LOCATION_HEADER);
                break;
            case WATER_ACTION_ITEM /*12992*/:
                str = ANALYTIC_ATTRIBUTE_ADD_WATER;
                this.diaryDelegate.showWaterEntryDialog();
                break;
            case NOTES_ACTION_ITEM /*12993*/:
                str = ANALYTIC_ATTRIBUTE_ADD_NOTE;
                onNotesClick();
                break;
            case COMPLETE_ENTRY_ACTION_ITEM /*12994*/:
                str = ANALYTIC_ATTRIBUTE_COMPLETE_ENTRY;
                onCompleteEntryClick(Attributes.TOP_RIGHT);
                break;
            case EDIT_ACTION_ITEM /*12995*/:
                ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportToolbarEditClicked();
                View view = this.walkthroughView;
                if (view != null && view.getVisibility() == 0) {
                    ((BottomBarMixin) ((MfpActivity) getActivity()).mixin(BottomBarMixin.class)).setEnabled(true);
                }
                toggleEditMode();
                break;
            case SETTINGS_ACTION_ITEM /*12996*/:
                getNavigationHelper().withIntent(DiarySettingsActivity.newStartIntent(getActivity(), "diary")).startActivity();
                break;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
        str = null;
        if (str != null) {
            ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportOverFlowTapItem(str);
        }
        return true;
    }

    private void reportDeleteConfirm(String str, boolean z, boolean z2) {
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportDeleteConfirmationEvent(str, z, z2);
    }

    /* access modifiers changed from: private */
    public boolean copyItems(List<Long> list, final ActionMode actionMode) {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay == null) {
            return false;
        }
        for (Long l : list) {
            if (currentDiaryDay.getFoodEntry(l) == null) {
                if (currentDiaryDay.getExerciseEntry(l) != null) {
                }
            }
            copyAllCheckedItems(new Function0() {
                public void execute() throws RuntimeException {
                    ActionMode actionMode = actionMode;
                    if (actionMode != null) {
                        actionMode.finish();
                    }
                }
            }, new Function0() {
                public void execute() throws RuntimeException {
                    UserDiaryFragment.this.setBusy(1, false);
                }
            });
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onSelectAllClicked() {
        boolean areAllItemsSelected = areAllItemsSelected();
        selectAllDiaryCategoryHeaders(false);
        this.diaryDelegate.onSelectAllClicked(getCurrentDiaryDay());
        if (!areAllItemsSelected) {
            selectAllDiaryCategoryHeaders(true);
        }
        refreshActionMode();
    }

    private void selectAllDiaryCategoryHeaders(boolean z) {
        DiaryDay currentDiaryDay = getCurrentDiaryDay();
        if (currentDiaryDay != null) {
            this.diaryDelegate.clearSelectAllList();
            selectHeaderSection("Exercise", z);
            Map foodEntriesByMealName = currentDiaryDay.getFoodEntriesByMealName();
            if (foodEntriesByMealName != null) {
                for (Entry key : foodEntriesByMealName.entrySet()) {
                    selectHeaderSection((String) key.getKey(), z);
                }
            }
        }
    }

    private void refreshActionMode() {
        if (isEditing()) {
            notifyDataSetChangedToDiaryAdapter(getCurrentRecyclerViewAdapter());
            invalidateOptionsMenu();
            int itemsSelectedCount = this.diaryDelegate.getItemsSelectedCount();
            this.editActionMode.setTitle(getString(itemsSelectedCount > 0 ? R.string.number_selected : R.string.select_item, Integer.valueOf(itemsSelectedCount)));
            updateSelectAllButton(getCurrentSelectAllButton());
        }
    }

    /* access modifiers changed from: protected */
    public void updateSelectAllButton(CompoundButton compoundButton) {
        if (compoundButton != null) {
            compoundButton.setOnCheckedChangeListener(null);
            compoundButton.setChecked(areAllItemsSelected());
            compoundButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ((DiaryAnalyticsHelper) UserDiaryFragment.this.diaryAnalyticsHelper.get()).reportEditSelectAllEvent(z);
                    UserDiaryFragment.this.onSelectAllClicked();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void clearDiaryItemsChecked() {
        this.diaryDelegate.clearDiaryItemsChecked();
        refreshActionMode();
    }

    private void deleteAllCheckedItems(Function0 function0, Function0 function02) {
        try {
            int itemsSelectedCount = this.diaryDelegate.getItemsSelectedCount();
            if (itemsSelectedCount > 0) {
                ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportItemsDeletedEvent(itemsSelectedCount);
                for (Entry value : this.diaryDelegate.getItemsSelected().entrySet()) {
                    deleteEntry((DatabaseObject) value.getValue());
                }
                markCurrentDiaryDayInCacheAsStaleAndRefresh(true);
                FunctionUtils.invokeIfValid(function0);
            }
        } finally {
            invalidateOptionsMenu();
            FunctionUtils.invokeIfValid(function02);
        }
    }

    private void copyAllCheckedItems(Function0 function0, Function0 function02) {
        try {
            showCopyToDateDialog();
            FunctionUtils.invokeIfValid(function0);
        } finally {
            invalidateOptionsMenu();
            FunctionUtils.invokeIfValid(function02);
        }
    }

    /* access modifiers changed from: private */
    public void saveAllCheckedItems(Function0 function0, Function0 function02) {
        try {
            switchToCreateMealScreen();
            FunctionUtils.invokeIfValid(function0);
        } finally {
            invalidateOptionsMenu();
            FunctionUtils.invokeIfValid(function02);
        }
    }

    private boolean areAllItemsSelected() {
        return this.diaryDelegate.areAllItemsSelected(getCurrentDiaryDay());
    }

    private void showServingErrorOrOutOfRangeDialog(int i) {
        showDialogFragment(CalorieAddErrorDialogFragment.newInstance(i), Fragments.CALORIES_ADD_ERROR_DIALOG);
    }

    private void showCopyToDateDialog() {
        showDialogFragment(DiaryQuickToolsDialogFragment.newInstance(), Fragments.DIARY_QUICK_TOOL_DIALOG);
    }

    private void hideCopyToDateDialog() {
        Fragment findFragmentByTag = getActivity().getSupportFragmentManager().findFragmentByTag(Fragments.DIARY_QUICK_TOOL_DIALOG);
        if (findFragmentByTag != null) {
            ((DialogFragment) findFragmentByTag).dismiss();
        }
    }

    private void showLongPressDialog(boolean z) {
        showDialogFragment(DiaryLongPressDialogFragment.newInstance(z, this.diaryDelegate.isLandscape()), Fragments.LONG_PRESS_DIALOG);
    }

    @Subscribe
    public void onShowQuickAddCaloriesDialogEvent(ShowQuickAddCaloriesDialogEvent showQuickAddCaloriesDialogEvent) {
        this.diaryDelegate.showQuickAddCaloriesDialog();
    }

    public void dismissDialog(String str) {
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag(str);
        if (findFragmentByTag != null) {
            ((DialogFragment) findFragmentByTag).dismiss();
        }
    }

    private void showWalkthrough(View view, int i) {
        if (i == getCurrentItemIndex()) {
            this.walkthroughView = findWalkthroughViewAndReportEvent(view, R.id.walkthrough_container);
            ((WalkthroughUtil) this.walkthroughUtil.get()).showDiaryWalkthrough(this.walkthroughView, this.newSkipEventCallback);
            ((BottomBarMixin) ((MfpActivity) getActivity()).mixin(BottomBarMixin.class)).setEnabled(false);
            this.showWalkThrough = false;
            getArguments().putBoolean(SHOW_WALKTHROUGH, this.showWalkThrough);
            setBehaviorForWalkthrough(true);
        }
    }

    /* access modifiers changed from: private */
    public void setBehaviorForWalkthrough(boolean z) {
        if (!ConfigUtils.isAppNavBottomBarEnabled((ConfigService) this.configService.get())) {
            MaterialUtils.setFixedFooterScrollingBehavior(getActivity(), z);
        }
    }

    private <T extends View> T findWalkthroughViewAndReportEvent(View view, int i) {
        reportWalkthroughEvent();
        return ViewUtils.findById(view, i);
    }

    /* access modifiers changed from: private */
    public void skipWalkthrough(View view) {
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportSkippedWalkthrough();
        ((WalkthroughUtil) this.walkthroughUtil.get()).hide(view);
    }

    private void reportWalkthroughEvent() {
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportShownWalkthrough();
    }

    private ViewGroup getNutrientDashboardContainerForDate(Calendar calendar) {
        return (ViewGroup) getViewForCalendarDate(calendar, R.id.diary_summary_container);
    }

    private String getTagForCurrentItem() {
        return getTagForPage(getSelectedDate());
    }

    private DiaryAdapter getCurrentRecyclerViewAdapter() {
        return getDiaryAdapterFromRecyclerView(getCurrentRecyclerView());
    }

    private View getCurrentSelectAllContainer() {
        return ViewUtils.findById(this.contentPager.findViewWithTag(getTagForCurrentItem()), R.id.select_all_include);
    }

    private CompoundButton getCurrentSelectAllButton() {
        return (CompoundButton) ViewUtils.findById(this.contentPager.findViewWithTag(getTagForCurrentItem()), R.id.select_all);
    }

    private void showSaveMealSnackBar(final List<FoodEntry> list) {
        if (!CollectionUtils.isEmpty((Collection<?>) list) && ConfigUtils.isDiaryCopyUpdateOn(getConfigService())) {
            new SnackbarBuilder(getView()).setMessage((int) R.string.save_items_for_later).setDuration(0).setAction((int) R.string.save_caps, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    ((DiaryAnalyticsHelper) UserDiaryFragment.this.diaryAnalyticsHelper.get()).reportDiaryCopySaveTappedEvent();
                    UserDiaryFragment.this.getNavigationHelper().withExtra(MealEditorMixin.EXTRA_FOOD_ENTRIES, (Serializable) (ArrayList) list).withIntent(FoodEditorActivity.newMealItemEditorIntent(UserDiaryFragment.this.getActivity(), null, null, null, MealAnalyticsHelper.VALUE_DIARY_MORE)).startActivity(RequestCodes.FOOD_EDITOR);
                }
            }).setActionTextColorResId(R.color.diary_green).showWithDelay();
            ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportDiaryCopySaveShownEvent();
        }
    }

    private void handleDiaryCompleteError() {
        ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getString(R.string.failed_to_complete_diary));
    }

    /* access modifiers changed from: protected */
    public boolean scrollToMostRecentlyAddedFood(DiaryDay diaryDay, DiaryAdapter diaryAdapter, RecyclerView recyclerView) {
        if (Strings.notEmpty(this.mostRecentlyAddedMealName) && DateUtil.areDatesEqualIgnoreTime(diaryDay.getDate(), this.mostRecentlyAddedMealNameDate)) {
            int lastItemIndexForSection = diaryAdapter.getLastItemIndexForSection(this.mostRecentlyAddedMealName);
            this.mostRecentlyAddedMealName = null;
            this.mostRecentlyAddedMealNameDate = null;
            if (lastItemIndexForSection >= 0) {
                recyclerView.scrollToPosition(Math.max(lastItemIndexForSection - 1, 0));
                return true;
            }
        }
        return false;
    }

    public void onSectionHeaderCheckChanged(DiaryDay diaryDay, String str, boolean z) {
        this.diaryDelegate.selectAllFoodEntriesInMeal(diaryDay, str, z);
        refreshActionMode();
    }

    public void onEntryItemCheckChanged(DatabaseObject databaseObject, boolean z) {
        this.diaryDelegate.handleItemClickWhileEditing(databaseObject, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBottomRowAddFoodClick(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = 324362706(0x135561d2, float:2.6932609E-27)
            if (r0 == r1) goto L_0x0019
            r1 = 2120967672(0x7e6b65f8, float:7.822451E37)
            if (r0 == r1) goto L_0x000f
            goto L_0x0023
        L_0x000f:
            java.lang.String r0 = "Exercise"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0023
            r0 = 0
            goto L_0x0024
        L_0x0019:
            java.lang.String r0 = "Water Consumption"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0023
            r0 = 1
            goto L_0x0024
        L_0x0023:
            r0 = -1
        L_0x0024:
            switch(r0) {
                case 0: goto L_0x0075;
                case 1: goto L_0x0040;
                default: goto L_0x0027;
            }
        L_0x0027:
            dagger.Lazy r0 = r5.premiumService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.feature.premium.service.PremiumService r0 = (com.myfitnesspal.feature.premium.service.PremiumService) r0
            com.myfitnesspal.feature.premium.service.PremiumFeature r1 = com.myfitnesspal.feature.premium.service.PremiumFeature.FoodTimestamps
            boolean r0 = r0.isFeatureSubscribed(r1)
            if (r0 == 0) goto L_0x0091
            com.myfitnesspal.shared.model.v1.DiaryDay r0 = r5.getCurrentDiaryDay()
            java.util.Date r0 = r0.getLatestEntryTimeForMealName(r6)
            goto L_0x0092
        L_0x0040:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r6 = r5.getNavigationHelper()
            android.support.v4.app.FragmentActivity r0 = r5.getActivity()
            com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity$Mode r1 = com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity.Mode.Add
            android.content.Intent r0 = com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity.newStartIntent(r0, r1)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r6 = r6.withIntent(r0)
            r0 = 50
            r6.startActivity(r0)
            dagger.Lazy r6 = r5.configService
            java.lang.Object r6 = r6.get()
            com.myfitnesspal.shared.service.config.ConfigService r6 = (com.myfitnesspal.shared.service.config.ConfigService) r6
            boolean r6 = com.myfitnesspal.shared.util.ConfigUtils.isSponsoredWaterAnalyticsEnabled(r6)
            if (r6 == 0) goto L_0x00c1
            dagger.Lazy<com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper> r6 = r5.waterLoggingAnalyticsHelper
            java.lang.Object r6 = r6.get()
            com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper r6 = (com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper) r6
            java.lang.String r0 = "diary"
            java.lang.String r1 = "add"
            r6.reportWaterScreenViewed(r0, r1)
            goto L_0x00c1
        L_0x0075:
            dagger.Lazy<com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper> r6 = r5.exerciseAnalyticsHelper
            java.lang.Object r6 = r6.get()
            com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper r6 = (com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper) r6
            java.lang.String r0 = "diary"
            r6.reportAddExerciseTapped(r0)
            com.myfitnesspal.feature.diary.ui.dialog.ExerciseTypeDialogFragment r6 = new com.myfitnesspal.feature.diary.ui.dialog.ExerciseTypeDialogFragment
            r6.<init>()
            android.support.v4.app.FragmentManager r0 = r5.getFragmentManager()
            java.lang.String r1 = "exercise_type_dialog"
            r6.show(r0, r1)
            goto L_0x00c1
        L_0x0091:
            r0 = 0
        L_0x0092:
            com.myfitnesspal.feature.diary.util.DiaryDelegate r1 = r5.diaryDelegate
            r1.startLoggingAddFoodFlow()
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r5.getNavigationHelper()
            dagger.Lazy<com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory> r2 = r5.foodSearchRouter
            java.lang.Object r2 = r2.get()
            com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory r2 = (com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory) r2
            android.support.v4.app.FragmentActivity r3 = r5.getActivity()
            com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity$Extras r4 = new com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity$Extras
            r4.<init>()
            com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity$Extras r6 = r4.setMealName(r6)
            com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity$Extras r6 = r6.setLatestMealEntryTime(r0)
            android.content.Intent r6 = r2.getFoodSearchActivityIntent(r3, r6)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r6 = r1.withIntent(r6)
            r0 = 49
            r6.startActivity(r0)
        L_0x00c1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment.onBottomRowAddFoodClick(java.lang.String):void");
    }

    public void onBottomRowShowMoreActionsClick(String str) {
        if (((str.hashCode() == 2120967672 && str.equals("Exercise")) ? (char) 0 : 65535) != 0) {
            DiaryMoreActionsDialog newInstance = DiaryMoreActionsDialog.newInstance(str, getFoodEntriesForMeal(str));
            if (newInstance != null) {
                ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportMealMoreTappedEvent(str);
                showDialogFragment(newInstance, Fragments.MORE_DIALOG);
            }
        } else {
            DiaryAdapter currentRecyclerViewAdapter = getCurrentRecyclerViewAdapter();
            if (currentRecyclerViewAdapter != null) {
                List<DatabaseObject> dataForHeaderType = currentRecyclerViewAdapter.getDataForHeaderType("Exercise");
                ArrayList diaryItemsChecked = this.diaryDelegate.getDiaryItemsChecked();
                for (DatabaseObject localId : dataForHeaderType) {
                    diaryItemsChecked.add(Long.valueOf(localId.getLocalId()));
                }
                copyItems(diaryItemsChecked, null);
            }
        }
    }

    public void onEntryClick(DiaryDay diaryDay, DatabaseObject databaseObject, View view) {
        if (isEditing()) {
            this.diaryDelegate.handleItemClickWhileEditing(databaseObject, view);
        } else {
            this.diaryDelegate.handleDiaryEntryClick(databaseObject, diaryDay);
        }
    }

    public boolean onEntryLongClick(DatabaseObject databaseObject) {
        this.longPressEntryToDelete = databaseObject;
        Object obj = this.longPressEntryToDelete;
        if (obj instanceof ExerciseEntry) {
            if (((ExerciseEntry) obj).getExercise().isCalorieAdjustmentExercise()) {
                return true;
            }
        } else if ((obj instanceof WaterEntry) && ((WaterEntry) obj).getMilliliters() == BitmapDescriptorFactory.HUE_RED) {
            return true;
        }
        showLongPressDialog(this.longPressEntryToDelete instanceof FoodEntry);
        return true;
    }

    public void onMealCaloriesClick(SectionHeaderItem sectionHeaderItem) {
        if (sectionHeaderItem != null) {
            String headerName = sectionHeaderItem.getHeaderName();
            this.diaryDelegate.navigateToMealGoal(sectionHeaderItem.getDayOfWeek(), "diary");
            ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportMealGoalHeaderTappedEvent(headerName, ((Session) this.session.get()).getUser().getMealNames().mealIndexForName(headerName));
        }
    }

    public void onPromoCallActionClick(PremiumFeature premiumFeature) {
        ((UserApplicationSettingsService) this.userApplicationSettingsService.get()).setMealGoalsBuyUpsellDismissed(true);
        getMessageBus().post(new DiaryPromoItemDismissedEvent());
        getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) getActivity(), premiumFeature)).startActivity();
    }

    public void onCompleteEntryClick(String str) {
        if (shouldCompleteThisEntryLocally()) {
            completeThisEntryLocally();
        } else {
            reallyCompleteThisEntry(str);
        }
        ((UacfScheduler) this.syncScheduler.get()).debounceDefaultSync();
    }

    public void onNutritionClick(String str) {
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportDiaryTapNutrition(str);
        getNavigationHelper().withIntent(Nutrition.newStartIntent(getActivity())).startActivity();
    }

    public void onNotesClick() {
        showDialogFragment(new NoteTypeDialogFragment(), Fragments.NOTE_TYPE_DIALOG);
    }
}
