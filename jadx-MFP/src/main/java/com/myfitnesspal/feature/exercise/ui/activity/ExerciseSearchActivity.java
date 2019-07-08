package com.myfitnesspal.feature.exercise.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.exercise.constants.ExerciseSearchTab;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseSearchAnalyticsHelper;
import com.myfitnesspal.feature.exercise.ui.dialog.ExerciseSortOrderDialogFragment;
import com.myfitnesspal.feature.exercise.ui.dialog.ExerciseSortOrderDialogFragment.OnSortOrderSelectedListener;
import com.myfitnesspal.feature.exercise.ui.fragment.ExerciseSearchFragment;
import com.myfitnesspal.feature.exercise.ui.fragment.ExerciseSearchFragment.ExerciseSearchActionListener;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.MultiAddExerciseSelection;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.lang.ref.WeakReference;
import javax.inject.Inject;

public class ExerciseSearchActivity extends MfpActivity {
    private static final int ENABLE_MULTI_ADD_DELAY_MS = 250;
    /* access modifiers changed from: private */
    public static final ExerciseSearchTab[] EXERCISE_SEARCH_TABS = {ExerciseSearchTab.RECENT, ExerciseSearchTab.MY_EXERCISES, ExerciseSearchTab.BROWSE_ALL};
    private static final String EXTRA_MULTI_MODE_ON = "multi_mode_on";
    private static final int MULTI_ADD_ACTION_ITEM = 900;
    private static final int SORT_ORDER_ACTION_ITEM = 901;
    private static final String TAG_EXERCISE_SORT_ORDER_DIALOG = "exercise_sort_order_dialog";
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    private OnPageChangeListener contentPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            ExerciseSearchActivity.this.supportInvalidateOptionsMenu();
            ExerciseSearchActivity.this.invalidateActionMode();
            ExerciseSearchActivity.this.reloadTabAtPosition(i);
        }
    };
    @BindView(2131364158)
    ViewPager contentPager;
    private ContentPagerAdapter contentPagerAdapter;
    @BindView(2131362226)
    Button createNewExerciseButton;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<ExerciseAnalyticsHelper> exerciseAnalyticsHelper;
    /* access modifiers changed from: private */
    public ExerciseSearchActionListener exerciseSearchActionListener = new ExerciseSearchActionListener() {
        public void onMultiAddSelectionChanged(int i) {
            if (ExerciseSearchActivity.this.multiAddActionMode != null) {
                ExerciseSearchActivity exerciseSearchActivity = ExerciseSearchActivity.this;
                exerciseSearchActivity.setActionModeTitle(exerciseSearchActivity.multiAddActionMode, i);
                ExerciseSearchActivity.this.multiAddActionMode.invalidate();
            }
        }

        public void onClearSearchFocus() {
            ExerciseSearchActivity.this.searchEditText.clearFocus();
        }
    };
    @Inject
    Lazy<ExerciseSearchAnalyticsHelper> exerciseSearchAnalyticsHelper;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    /* access modifiers changed from: private */
    public ActionMode multiAddActionMode;
    @BindView(2131363140)
    TabLayout newTabContainer;
    @BindView(2131362415)
    EditText searchEditText;
    @Inject
    Lazy<SearchService> searchService;
    @Inject
    Lazy<Session> session;
    private final OnSortOrderSelectedListener sortOrderSelectedListener = new OnSortOrderSelectedListener() {
        public final void onSortOrderSelected(SortOrder sortOrder) {
            ExerciseSearchActivity.this.reloadCurrentTab();
        }
    };
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;

    private class ContentPagerAdapter extends FragmentStatePagerAdapter {
        private SparseArray<WeakReference<ExerciseSearchFragment>> instantiatedFragments;

        private ContentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.instantiatedFragments = new SparseArray<>(ExerciseSearchActivity.this.getTabCount());
        }

        public Fragment getItem(int i) {
            return ExerciseSearchFragment.newInstance(ExerciseSearchActivity.EXERCISE_SEARCH_TABS[i].ordinal(), ExerciseSearchActivity.this.getExerciseType(), ExerciseSearchActivity.this.getQueryString());
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ExerciseSearchFragment exerciseSearchFragment = (ExerciseSearchFragment) super.instantiateItem(viewGroup, i);
            this.instantiatedFragments.put(i, new WeakReference(exerciseSearchFragment));
            exerciseSearchFragment.setExerciseSearchActionListener(ExerciseSearchActivity.this.exerciseSearchActionListener);
            return exerciseSearchFragment;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            this.instantiatedFragments.remove(i);
            super.destroyItem(viewGroup, i, obj);
        }

        public int getCount() {
            return ExerciseSearchActivity.this.getTabCount();
        }

        public CharSequence getPageTitle(int i) {
            return ExerciseSearchActivity.this.getString(ExerciseSearchActivity.EXERCISE_SEARCH_TABS[i].getLabelResId());
        }

        /* access modifiers changed from: private */
        public ExerciseSearchFragment getFragmentAtPosition(int i) {
            WeakReference weakReference = (WeakReference) this.instantiatedFragments.get(i);
            if (weakReference == null) {
                return null;
            }
            return (ExerciseSearchFragment) weakReference.get();
        }
    }

    class MultipleChoiceActionMode implements Callback {
        private static final int ACTION_ADD_ALL = 101;
        private final Activity activity;

        private MultipleChoiceActionMode(Activity activity2) {
            this.activity = activity2;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            ExerciseSearchActivity.this.showMultiAddView(true);
            ExerciseSearchActivity.this.setActionModeTitle(actionMode, MultiAddExerciseSelection.current().selectedItemCount());
            ExerciseSearchActivity.this.exerciseSearchActionListener.onMultiAddSelectionChanged(MultiAddExerciseSelection.current().selectedItemCount());
            MenuItemCompat.setShowAsAction(menu.add(0, 101, 0, R.string.add_selected), 2);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            MenuItem findItem = menu.findItem(101);
            MultiAddExerciseSelection current = MultiAddExerciseSelection.current();
            if (!(findItem == null || current == null)) {
                findItem.setVisible(!current.isEmpty());
            }
            return true;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() == 101) {
                MultiAddExerciseSelection current = MultiAddExerciseSelection.current();
                if (current != null) {
                    if (!current.isEmpty()) {
                        current.addAllExerciseEntriesToDiary((DiaryService) ExerciseSearchActivity.this.diaryService.get());
                        ExerciseSearchActivity.this.setResult(-1);
                        ExerciseSearchActivity.this.showMultiAddView(false);
                        ((UacfScheduler) ExerciseSearchActivity.this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
                        ExerciseSearchActivity.this.getNavigationHelper().finishActivityAfterNavigation().withClearTopAndSingleTop().withIntent(Diary.newStartIntentWithReferrer(this.activity, Extras.REFERRER_DIARY_JUST_LOGGED)).startActivity();
                    } else {
                        ((LegacyAlertMixin) ExerciseSearchActivity.this.mixin(LegacyAlertMixin.class)).showAlertDialog(ExerciseSearchActivity.this.getString(R.string.no_entry_selected));
                    }
                }
            }
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            ExerciseSearchActivity.this.showMultiAddView(false);
            ExerciseSearchActivity.this.multiAddActionMode = null;
        }
    }

    public static Intent newStartIntentForExerciseType(Context context, int i) {
        return new Intent(context, ExerciseSearchActivity.class).putExtra(Extras.EXERCISE_TYPE, i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.new_exercises);
        component().inject(this);
        MaterialUtils.removeDefaultToolbarElevation(this);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(this, true);
        setupPager();
        setListeners();
        setTitle(getExerciseType() == 0 ? R.string.cardio : R.string.strength);
        if (BundleUtils.getBoolean(bundle, EXTRA_MULTI_MODE_ON, ((LocalSettingsService) this.localSettingsService.get()).getMultiAddToggleOnByDefault())) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ExerciseSearchActivity.this.showMultiAddMode();
                }
            }, 250);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        MenuItemCompat.setShowAsAction(menu.add(0, MULTI_ADD_ACTION_ITEM, 0, R.string.multi_add), 2);
        if (!getCountryService().isCurrentCountryCJK()) {
            MenuItemCompat.setShowAsAction(menu.add(0, SORT_ORDER_ACTION_ITEM, 0, R.string.sort_order), 0);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (MultiAddExerciseSelection.isActive()) {
            this.exerciseSearchActionListener.onMultiAddSelectionChanged(MultiAddExerciseSelection.current().selectedItemCount());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case MULTI_ADD_ACTION_ITEM /*900*/:
                showMultiAddMode();
                return true;
            case SORT_ORDER_ACTION_ITEM /*901*/:
                displaySortOptions();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_MULTI_MODE_ON, MultiAddExerciseSelection.isActive());
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(TAG_EXERCISE_SORT_ORDER_DIALOG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((ExerciseSortOrderDialogFragment) dialogFragment).setSortOrderSelectedListener(this.sortOrderSelectedListener);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if ((i == 45 || i == 67) && i2 == -1) {
            getNavigationHelper().withIntent(Diary.newStartIntentWithReferrerAndForceHomeOnBack(getActivity(), "navigation")).withFlags(67108864, 0).finishActivityAfterNavigation().startActivity();
        }
    }

    /* access modifiers changed from: private */
    public void showMultiAddMode() {
        if (this.multiAddActionMode == null) {
            this.multiAddActionMode = startActionMode(new MultipleChoiceActionMode(this));
        }
    }

    private void setListeners() {
        this.searchEditText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                ExerciseSearchActivity.this.getCurrentExerciseSearchFragment().performOnlineSearch(Strings.toString(ExerciseSearchActivity.this.searchEditText.getText()));
                return true;
            }
        });
        this.searchEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (ExerciseSearchActivity.this.hasResumed()) {
                    ExerciseSearchActivity.this.updateSearchQuery(Strings.trimmed((Object) editable));
                }
            }
        });
        this.searchEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                ExerciseSearchActivity.lambda$setListeners$0(ExerciseSearchActivity.this, view, z);
            }
        });
        this.createNewExerciseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent;
                int i;
                ((ExerciseSearchActivity) ExerciseSearchActivity.this.getActivity()).finishActionMode();
                if (ExerciseSearchActivity.this.getExerciseType() == 0) {
                    intent = NewCardio.newStartIntent(ExerciseSearchActivity.this.getActivity(), true);
                    i = 45;
                } else {
                    intent = NewStrength.newStartIntent(ExerciseSearchActivity.this.getActivity(), true);
                    i = 67;
                }
                ((ExerciseAnalyticsHelper) ExerciseSearchActivity.this.exerciseAnalyticsHelper.get()).reportNewExerciseScreenDisplayed(ExerciseSearchActivity.this.getExerciseType(), ExerciseSearchActivity.this.getCurrentExerciseSearchTab().getAnalyticsTabName());
                ExerciseSearchActivity.this.getNavigationHelper().withIntent(intent).startActivity(i);
            }
        });
    }

    public static /* synthetic */ void lambda$setListeners$0(ExerciseSearchActivity exerciseSearchActivity, View view, boolean z) {
        if (z) {
            ((ExerciseAnalyticsHelper) exerciseSearchActivity.exerciseAnalyticsHelper.get()).reportExerciseSearchTapped(exerciseSearchActivity.getCurrentExerciseSearchTab());
        }
    }

    private void displaySortOptions() {
        ExerciseSortOrderDialogFragment newInstance = ExerciseSortOrderDialogFragment.newInstance(getCurrentExerciseSearchTab().getTabId(), getExerciseType());
        newInstance.setSortOrderSelectedListener(this.sortOrderSelectedListener);
        showDialogFragment(newInstance, TAG_EXERCISE_SORT_ORDER_DIALOG);
    }

    /* access modifiers changed from: private */
    public void updateSearchQuery(String str) {
        ExerciseSearchFragment currentExerciseSearchFragment = getCurrentExerciseSearchFragment();
        if (currentExerciseSearchFragment != null) {
            currentExerciseSearchFragment.updateQueryAndFilter(str);
        }
    }

    private void setupPager() {
        this.contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        this.contentPager.setAdapter(this.contentPagerAdapter);
        this.contentPager.addOnPageChangeListener(this.contentPageChangeListener);
        TabLayout tabLayout = this.newTabContainer;
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(this.contentPager);
        }
    }

    private void redrawExerciseOnCurrentTab() {
        reloadOrRedrawExercisesForTabAtPosition(this.contentPager.getCurrentItem(), false);
    }

    /* access modifiers changed from: private */
    public void reloadCurrentTab() {
        reloadOrRedrawExercisesForTabAtPosition(this.contentPager.getCurrentItem(), true);
    }

    /* access modifiers changed from: private */
    public void reloadTabAtPosition(int i) {
        reloadOrRedrawExercisesForTabAtPosition(i, true);
    }

    private void reloadOrRedrawExercisesForTabAtPosition(int i, boolean z) {
        ExerciseSearchFragment access$700 = this.contentPagerAdapter.getFragmentAtPosition(i);
        if (access$700 == null) {
            return;
        }
        if (z) {
            access$700.reloadLocalExercises(getQueryString());
        } else {
            access$700.reDrawExercises();
        }
    }

    /* access modifiers changed from: private */
    public void invalidateActionMode() {
        ActionMode actionMode = this.multiAddActionMode;
        if (actionMode != null) {
            actionMode.invalidate();
        }
    }

    public void finishActionMode() {
        ActionMode actionMode = this.multiAddActionMode;
        if (actionMode != null) {
            actionMode.finish();
            this.multiAddActionMode = null;
        }
    }

    /* access modifiers changed from: private */
    public int getTabCount() {
        return EXERCISE_SEARCH_TABS.length;
    }

    /* access modifiers changed from: private */
    public int getExerciseType() {
        return ExtrasUtils.getInt(getIntent(), Extras.EXERCISE_TYPE);
    }

    /* access modifiers changed from: private */
    public void showMultiAddView(boolean z) {
        if (!z) {
            MultiAddExerciseSelection.reset();
        } else {
            MultiAddExerciseSelection.activate();
        }
        redrawExerciseOnCurrentTab();
    }

    /* access modifiers changed from: private */
    public ExerciseSearchFragment getCurrentExerciseSearchFragment() {
        return this.contentPagerAdapter.getFragmentAtPosition(this.contentPager.getCurrentItem());
    }

    /* access modifiers changed from: private */
    public ExerciseSearchTab getCurrentExerciseSearchTab() {
        return EXERCISE_SEARCH_TABS[this.contentPager.getCurrentItem()];
    }

    /* access modifiers changed from: private */
    public String getQueryString() {
        return Strings.toString(this.searchEditText.getText());
    }

    /* access modifiers changed from: private */
    public void setActionModeTitle(ActionMode actionMode, int i) {
        actionMode.setTitle(getString(i > 0 ? R.string.number_selected : R.string.select_item, new Object[]{Integer.valueOf(i)}));
    }
}
