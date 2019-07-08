package com.myfitnesspal.feature.exercise.ui.fragment;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.exercise.constants.ExerciseSearchTab;
import com.myfitnesspal.feature.exercise.model.ExerciseSearchAdapterItem;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseSearchAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.exercise.ui.activity.AddExerciseEntry;
import com.myfitnesspal.feature.exercise.ui.adapter.ExerciseSearchAdapter;
import com.myfitnesspal.feature.exercise.ui.adapter.ExerciseSearchAdapter.ExerciseAdapterItemActionListener;
import com.myfitnesspal.feature.exercise.viewmodel.ExerciseSearchViewModel;
import com.myfitnesspal.feature.exercise.viewmodel.ExerciseSearchViewModel.Property;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExercise.ExerciseTypes;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.MultiAddExerciseSelection;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class ExerciseSearchFragment extends MfpFragment {
    private static final String EXTRA_CURRENT_QUERY = "current_query";
    private static final String EXTRA_EXERCISE_SEARCH_TAB_ORDINAL = "exercise_search_tab_ordinal";
    private static final String EXTRA_EXERCISE_TYPE = "exercise_type";
    private static final int RESULTS_PER_PAGE = 25;
    private ExerciseAdapterItemActionListener adapterItemActionListener = new ExerciseAdapterItemActionListener() {
        public void onExerciseItemClick(MfpExerciseEntry mfpExerciseEntry, int i) {
            if (ExerciseSearchFragment.this.exerciseSearchViewModel.wereLastResultsFromOnlineSearch()) {
                ((ExerciseSearchAnalyticsHelper) ExerciseSearchFragment.this.exerciseSearchAnalyticsHelper.get()).appendPropertiesToOnlineSearchSummary(i);
                ((ExerciseAnalyticsHelper) ExerciseSearchFragment.this.exerciseAnalyticsHelper.get()).reportExerciseSearchResultSelected(i);
            }
            ExerciseSearchFragment.this.navigateToExerciseEntry(mfpExerciseEntry, i);
        }

        public void onExerciseItemCheckChange(MfpExerciseEntry mfpExerciseEntry, CompoundButton compoundButton, boolean z) {
            if (z) {
                MfpExercise exercise = mfpExerciseEntry.getExercise();
                boolean z2 = exercise != null && Strings.equals(exercise.getType(), "cardio");
                if ((!z2 || mfpExerciseEntry.getDuration() != 0) && (z2 || mfpExerciseEntry.getSets() != 0)) {
                    MultiAddExerciseSelection.current().selectOrUpdateExerciseEntry(mfpExerciseEntry);
                } else {
                    ExerciseSearchFragment.this.getNavigationHelper().withExtra("exercise_entry", (Parcelable) mfpExerciseEntry).fromFragment(ExerciseSearchFragment.this).withIntent(AddExerciseEntry.newStartIntent((Context) ExerciseSearchFragment.this.getActivity(), ExerciseTypes.toValue(mfpExerciseEntry.getExercise().getType()))).startActivity(RequestCodes.MULTI_ADD_EXERCISE);
                }
            } else {
                MultiAddExerciseSelection.current().deselectExerciseEntry(mfpExerciseEntry);
            }
            ExerciseSearchFragment.this.exerciseSearchActionListener.onMultiAddSelectionChanged(MultiAddExerciseSelection.current().selectedItemCount());
        }

        public void onEmptyItemTextClick() {
            ExerciseSearchFragment exerciseSearchFragment = ExerciseSearchFragment.this;
            exerciseSearchFragment.performOnlineSearch(exerciseSearchFragment.exerciseSearchViewModel.getFilterQuery());
        }
    };
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<ExerciseAnalyticsHelper> exerciseAnalyticsHelper;
    @Inject
    Lazy<ExerciseEntryMapper> exerciseEntryMapper;
    @Inject
    Lazy<ExerciseMapper> exerciseMapper;
    @BindView(2131362507)
    RecyclerView exerciseRecyclerView;
    /* access modifiers changed from: private */
    public ExerciseSearchActionListener exerciseSearchActionListener;
    private ExerciseSearchAdapter exerciseSearchAdapter;
    @Inject
    Lazy<ExerciseSearchAnalyticsHelper> exerciseSearchAnalyticsHelper;
    private ExerciseSearchTab exerciseSearchTab;
    /* access modifiers changed from: private */
    public ExerciseSearchViewModel exerciseSearchViewModel;
    @Inject
    Lazy<ExerciseService> exerciseService;
    @Inject
    Lazy<ExerciseStringService> exerciseStringService;
    private int exerciseType;
    private OnScrollListener recyclerViewOnScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i != 0) {
                ExerciseSearchFragment.this.getImmHelper().hideSoftInput();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ExerciseSearchFragment.this.exerciseRecyclerView.getLayoutManager();
            int childCount = linearLayoutManager.getChildCount();
            int itemCount = linearLayoutManager.getItemCount();
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (ExerciseSearchFragment.this.exerciseSearchViewModel.getState() != State.Loading && !ExerciseSearchFragment.this.exerciseSearchViewModel.hasReachedEnd() && childCount + findFirstVisibleItemPosition >= itemCount && findFirstVisibleItemPosition >= 0 && itemCount >= 25) {
                ExerciseSearchFragment.this.exerciseSearchViewModel.loadNextPage();
            }
        }
    };
    @Inject
    Lazy<SearchService> searchService;
    @Inject
    Lazy<SortOrderHelper> sortOrderHelper;

    public interface ExerciseSearchActionListener {
        void onClearSearchFocus();

        void onMultiAddSelectionChanged(int i);
    }

    public static ExerciseSearchFragment newInstance(int i, int i2, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_EXERCISE_SEARCH_TAB_ORDINAL, i);
        bundle.putInt("exercise_type", i2);
        bundle.putString(EXTRA_CURRENT_QUERY, str);
        ExerciseSearchFragment exerciseSearchFragment = new ExerciseSearchFragment();
        exerciseSearchFragment.setArguments(bundle);
        return exerciseSearchFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        Bundle arguments = getArguments();
        this.exerciseSearchTab = ExerciseSearchTab.values()[BundleUtils.getInt(arguments, EXTRA_EXERCISE_SEARCH_TAB_ORDINAL)];
        this.exerciseType = BundleUtils.getInt(arguments, "exercise_type");
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.exercises_page, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setupViewModelAndLoad();
        setupListeners();
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.LOCAL_LIST_FETCHED_AND_FILTERED) {
            createOrUpdateAdapter(this.exerciseSearchViewModel.getExerciseSearchAdapterItems());
        } else if (i == Property.ONLINE_LIST_FETCHED) {
            List onlineSearchAdapterItems = this.exerciseSearchViewModel.getOnlineSearchAdapterItems();
            ((ExerciseAnalyticsHelper) this.exerciseAnalyticsHelper.get()).reportExericseSearch(this.exerciseSearchViewModel.getFilterQuery(), onlineSearchAdapterItems.size());
            createOrUpdateAdapter(onlineSearchAdapterItems);
        } else if (i == Property.ONLINE_LIST_FETCH_FAILED) {
            ((ExerciseAnalyticsHelper) this.exerciseAnalyticsHelper.get()).reportExericseSearchFailed(this.exerciseSearchViewModel.getFilterQuery());
            new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.alert).setMessage((int) R.string.network_problem_searching).setPositiveButton((int) R.string.dismiss, (OnClickListener) null).show();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 195) {
            ExerciseSearchAdapter exerciseSearchAdapter2 = this.exerciseSearchAdapter;
            if (exerciseSearchAdapter2 != null) {
                exerciseSearchAdapter2.notifyDataSetChanged();
            }
        } else if (i == 61 && i2 == -1) {
            getNavigationHelper().withIntent(Diary.newStartIntentWithReferrerAndForceHomeOnBack(getActivity(), "navigation")).withFlags(67108864, 0).finishActivityAfterNavigation().startActivity();
        }
    }

    public void reDrawExercises() {
        ExerciseSearchAdapter exerciseSearchAdapter2 = this.exerciseSearchAdapter;
        if (exerciseSearchAdapter2 != null) {
            exerciseSearchAdapter2.notifyDataSetChanged();
        }
    }

    public void reloadLocalExercises(String str) {
        if (!this.exerciseSearchViewModel.wereLastResultsFromOnlineSearch()) {
            this.exerciseSearchViewModel.setFilterQuery(str);
            ExerciseSearchAdapter exerciseSearchAdapter2 = this.exerciseSearchAdapter;
            if (exerciseSearchAdapter2 != null) {
                exerciseSearchAdapter2.setQueryString(str);
            }
            this.exerciseSearchViewModel.load(new Void[0]);
        }
    }

    private void setupViewModelAndLoad() {
        this.exerciseSearchViewModel = (ExerciseSearchViewModel) getViewModel();
        if (this.exerciseSearchViewModel == null) {
            ExerciseSearchViewModel exerciseSearchViewModel2 = new ExerciseSearchViewModel(getRunner(), this.exerciseService, this.diaryService, this.sortOrderHelper, this.exerciseEntryMapper, this.searchService, this.exerciseMapper, this.exerciseStringService, this.exerciseSearchAnalyticsHelper, getSession(), this.exerciseSearchTab, this.exerciseType, BundleUtils.getString(getArguments(), EXTRA_CURRENT_QUERY));
            this.exerciseSearchViewModel = (ExerciseSearchViewModel) setViewModel(exerciseSearchViewModel2);
        }
        this.exerciseSearchViewModel.load(new Void[0]);
    }

    public void setExerciseSearchActionListener(ExerciseSearchActionListener exerciseSearchActionListener2) {
        this.exerciseSearchActionListener = exerciseSearchActionListener2;
    }

    public void updateQueryAndFilter(String str) {
        this.exerciseSearchViewModel.setFilterQueryAndFilterLocalResults(str);
        ExerciseSearchAdapter exerciseSearchAdapter2 = this.exerciseSearchAdapter;
        if (exerciseSearchAdapter2 != null) {
            exerciseSearchAdapter2.setQueryString(str);
        }
    }

    public void performOnlineSearch(String str) {
        if (this.exerciseSearchViewModel.wereLastResultsFromOnlineSearch()) {
            ((ExerciseSearchAnalyticsHelper) this.exerciseSearchAnalyticsHelper.get()).logEventsAndReportToAnalytics();
        }
        getImmHelper().hideSoftInput();
        this.exerciseSearchActionListener.onClearSearchFocus();
        ((ExerciseSearchAnalyticsHelper) this.exerciseSearchAnalyticsHelper.get()).initOnlineSearchSummaryForAnalytics(this.exerciseType);
        this.exerciseSearchViewModel.fetchOnlineExercises(str);
    }

    private void setupListeners() {
        this.exerciseRecyclerView.addOnScrollListener(this.recyclerViewOnScrollListener);
    }

    private void createOrUpdateAdapter(List<ExerciseSearchAdapterItem> list) {
        ExerciseSearchAdapter exerciseSearchAdapter2 = this.exerciseSearchAdapter;
        if (exerciseSearchAdapter2 != null) {
            exerciseSearchAdapter2.setItems(list);
            return;
        }
        ExerciseSearchAdapter exerciseSearchAdapter3 = new ExerciseSearchAdapter(list, this.exerciseStringService, this.adapterItemActionListener, this.exerciseSearchTab, this.exerciseSearchViewModel.getFilterQuery());
        this.exerciseSearchAdapter = exerciseSearchAdapter3;
        this.exerciseRecyclerView.setAdapter(this.exerciseSearchAdapter);
        this.exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /* access modifiers changed from: private */
    public void navigateToExerciseEntry(MfpExerciseEntry mfpExerciseEntry, int i) {
        ((ExerciseSearchAnalyticsHelper) this.exerciseSearchAnalyticsHelper.get()).appendLastSearchType(this.exerciseSearchViewModel.wereLastResultsFromOnlineSearch());
        ((ExerciseSearchAnalyticsHelper) this.exerciseSearchAnalyticsHelper.get()).updateExerciseSearchBreadcrumb(Attributes.ADD_VIEW);
        ((ExerciseAnalyticsHelper) this.exerciseAnalyticsHelper.get()).reportAddExerciseEntryDisplayed(mfpExerciseEntry.getExercise().getType(), this.exerciseSearchViewModel.wereLastResultsFromOnlineSearch());
        getNavigationHelper().withExtra("exercise_entry", (Parcelable) mfpExerciseEntry).withExtra(Extras.EXERCISE_TYPE, ExerciseTypes.toValue(mfpExerciseEntry.getExercise().getType())).withExtra("index", i).withExtra("list_type", this.exerciseSearchTab.getAnalyticsTabName()).withExtra(Attributes.ITEM_COUNT, 1).fromFragment(this).withIntent(AddExerciseEntry.newStartIntent((Context) getActivity(), this.exerciseType)).startActivity(MultiAddExerciseSelection.isActive() ? RequestCodes.MULTI_ADD_EXERCISE : 61);
    }
}
