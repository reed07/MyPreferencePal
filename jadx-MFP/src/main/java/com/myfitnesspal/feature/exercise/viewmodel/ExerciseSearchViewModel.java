package com.myfitnesspal.feature.exercise.viewmodel;

import android.widget.Filter;
import android.widget.Filter.FilterResults;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.constants.ExerciseSearchTab;
import com.myfitnesspal.feature.exercise.model.ExerciseSearchAdapterItem;
import com.myfitnesspal.feature.exercise.model.ExerciseSearchEmptyItem;
import com.myfitnesspal.feature.exercise.model.ExerciseSearchEmptyItem.EmptyItemType;
import com.myfitnesspal.feature.exercise.service.ExerciseSearchAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.exercise.task.ExerciseSearchTask;
import com.myfitnesspal.feature.exercise.task.GetExercisesTask;
import com.myfitnesspal.feature.exercise.task.GetFavoriteExercisesTask;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Task;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExerciseSearchViewModel extends RunnerViewModel<Void> {
    private static final int MAX_ONLINE_SEARCH_RESULTS = 50;
    private static final int RESULTS_PER_PAGE = 25;
    private static final String TASK_GET_EXERCISES_ALL = "get_exercises_all";
    private static final String TASK_GET_EXERCISES_FAVORITE = "get_exercises_task_favorite";
    private static final String TASK_GET_EXERCISES_OWNED = "get_exercises_owned";
    private static final String TASK_SEARCH_ONLINE_EXERCISES = "search_online_exercise";
    private int currentPage = 1;
    private final Lazy<DiaryService> diaryService;
    private final Lazy<ExerciseEntryMapper> exerciseEntryMapper;
    private final Lazy<ExerciseMapper> exerciseMapper;
    /* access modifiers changed from: private */
    public List<ExerciseSearchAdapterItem> exerciseSearchAdapterItems;
    private final Lazy<ExerciseSearchAnalyticsHelper> exerciseSearchAnalyticsHelper;
    private final ExerciseSearchTab exerciseSearchTab;
    private final Lazy<ExerciseService> exerciseService;
    private final Lazy<ExerciseStringService> exerciseStringService;
    private final int exerciseType;
    private String filterQuery;
    private boolean hasReachedEnd;
    private final LocalSearchResultsFilter localSearchResultsFilter;
    private List<ExerciseSearchAdapterItem> onlineSearchAdapterItems;
    /* access modifiers changed from: private */
    public List<MfpExerciseEntry> queriedLocalExerciseEntries;
    private final Lazy<SearchService> searchService;
    private final Session session;
    private final Lazy<SortOrderHelper> sortOrderHelper;
    private boolean wereLastResultsFromOnlineSearch;

    private class LocalSearchResultsFilter extends Filter {
        private LocalSearchResultsFilter() {
        }

        /* access modifiers changed from: protected */
        public FilterResults performFiltering(CharSequence charSequence) {
            if (CollectionUtils.isEmpty((Collection<?>) ExerciseSearchViewModel.this.queriedLocalExerciseEntries)) {
                return null;
            }
            String lowerCase = Strings.trimmed((Object) charSequence).toLowerCase();
            ArrayList arrayList = new ArrayList();
            if (Strings.isEmpty(lowerCase)) {
                arrayList.addAll(ExerciseSearchViewModel.this.queriedLocalExerciseEntries);
            } else {
                for (MfpExerciseEntry mfpExerciseEntry : ExerciseSearchViewModel.this.queriedLocalExerciseEntries) {
                    if (mfpExerciseEntry.getExercise().getDescription().toLowerCase().contains(lowerCase)) {
                        arrayList.add(mfpExerciseEntry);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.count = CollectionUtils.size((Collection<?>) arrayList);
            filterResults.values = arrayList;
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults != null) {
                ExerciseSearchViewModel.this.exerciseSearchAdapterItems = (List) filterResults.values;
                if (ExerciseSearchViewModel.this.exerciseSearchAdapterItems.isEmpty()) {
                    ExerciseSearchViewModel.this.exerciseSearchAdapterItems.add(new ExerciseSearchEmptyItem(EmptyItemType.NoFilteredItems));
                }
                ExerciseSearchViewModel.this.notifyLocalListFiltered();
            }
        }
    }

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int LOCAL_LIST_FETCHED_AND_FILTERED = ViewModelPropertyId.next();
        public static final int ONLINE_LIST_FETCHED = ViewModelPropertyId.next();
        public static final int ONLINE_LIST_FETCH_FAILED = ViewModelPropertyId.next();
    }

    static /* synthetic */ ExerciseSearchAdapterItem lambda$onTaskCompleted$0(MfpExerciseEntry mfpExerciseEntry) throws RuntimeException {
        return mfpExerciseEntry;
    }

    public ExerciseSearchViewModel(Runner runner, Lazy<ExerciseService> lazy, Lazy<DiaryService> lazy2, Lazy<SortOrderHelper> lazy3, Lazy<ExerciseEntryMapper> lazy4, Lazy<SearchService> lazy5, Lazy<ExerciseMapper> lazy6, Lazy<ExerciseStringService> lazy7, Lazy<ExerciseSearchAnalyticsHelper> lazy8, Session session2, ExerciseSearchTab exerciseSearchTab2, int i, String str) {
        super(runner);
        this.exerciseService = lazy;
        this.diaryService = lazy2;
        this.sortOrderHelper = lazy3;
        this.exerciseEntryMapper = lazy4;
        this.searchService = lazy5;
        this.exerciseMapper = lazy6;
        this.exerciseStringService = lazy7;
        this.exerciseSearchAnalyticsHelper = lazy8;
        this.session = session2;
        this.exerciseSearchTab = exerciseSearchTab2;
        this.exerciseType = i;
        this.filterQuery = str;
        this.localSearchResultsFilter = new LocalSearchResultsFilter();
    }

    public void load(Void... voidArr) {
        if (getState() != State.Loading) {
            fetchLocalExercises(this.currentPage);
        }
    }

    public void loadNextPage() {
        int i = this.currentPage + 1;
        this.currentPage = i;
        fetchLocalExercises(i);
    }

    public void fetchOnlineExercises(String str) {
        if (getState() != State.Loading) {
            setState(State.Loading);
            Runner runner = getRunner();
            String str2 = TASK_SEARCH_ONLINE_EXERCISES;
            ExerciseSearchTask exerciseSearchTask = new ExerciseSearchTask(this.searchService, this.exerciseMapper, this.diaryService, this.session, str, this.exerciseType, 50);
            runner.run(str2, exerciseSearchTask);
        }
    }

    public void setFilterQueryAndFilterLocalResults(String str) {
        if (!Strings.equals(this.filterQuery, str)) {
            this.filterQuery = str;
            filterLocalResults();
        }
    }

    public List<ExerciseSearchAdapterItem> getExerciseSearchAdapterItems() {
        return this.exerciseSearchAdapterItems;
    }

    public List<ExerciseSearchAdapterItem> getOnlineSearchAdapterItems() {
        return this.onlineSearchAdapterItems;
    }

    public String getFilterQuery() {
        return this.filterQuery;
    }

    public void setFilterQuery(String str) {
        this.filterQuery = str;
    }

    public boolean wereLastResultsFromOnlineSearch() {
        return this.wereLastResultsFromOnlineSearch;
    }

    public boolean hasReachedEnd() {
        return this.hasReachedEnd;
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner())) {
            setState(State.Loaded);
            String taskName = taskDetails.getTaskName();
            boolean z = true;
            if (Strings.equals(taskName, TASK_SEARCH_ONLINE_EXERCISES)) {
                if (taskDetails.successful()) {
                    this.onlineSearchAdapterItems = Enumerable.select((Collection<T>) (List) taskDetails.getResult(), (ReturningFunction1<U, T>) $$Lambda$ExerciseSearchViewModel$bSYaYgbAEcJxpJsBnm4wUE0ie5k.INSTANCE);
                    int size = this.onlineSearchAdapterItems.size();
                    ((ExerciseSearchAnalyticsHelper) this.exerciseSearchAnalyticsHelper.get()).logSearchResultSize(size);
                    if (size == 0) {
                        this.onlineSearchAdapterItems.add(new ExerciseSearchEmptyItem(EmptyItemType.NoOnlineItems));
                    }
                    notifyPropertyChanged(Property.ONLINE_LIST_FETCHED);
                    this.wereLastResultsFromOnlineSearch = true;
                } else {
                    notifyPropertyChanged(Property.ONLINE_LIST_FETCH_FAILED);
                }
            } else if (Strings.equals(taskName, TASK_GET_EXERCISES_OWNED) || Strings.equals(taskName, TASK_GET_EXERCISES_FAVORITE) || Strings.equals(taskName, TASK_GET_EXERCISES_ALL)) {
                this.queriedLocalExerciseEntries = (List) taskDetails.getResult();
                int size2 = CollectionUtils.size((Collection<?>) this.queriedLocalExerciseEntries);
                if (size2 >= this.currentPage * 25) {
                    z = false;
                }
                this.hasReachedEnd = z;
                if (size2 == 0) {
                    this.exerciseSearchAdapterItems = new ArrayList();
                    this.exerciseSearchAdapterItems.add(new ExerciseSearchEmptyItem(EmptyItemType.NoLocalItems));
                    notifyLocalListFiltered();
                    return;
                }
                sortResults();
                filterLocalResults();
            }
        }
    }

    private void filterLocalResults() {
        this.localSearchResultsFilter.filter(this.filterQuery);
    }

    private void fetchLocalExercises(int i) {
        String str;
        Task task;
        setState(State.Loading);
        switch (this.exerciseSearchTab) {
            case MY_EXERCISES:
                str = TASK_GET_EXERCISES_OWNED;
                task = getGetExercisesTask(GetExercisesTask.Filter.Owned);
                break;
            case RECENT:
                String str2 = TASK_GET_EXERCISES_FAVORITE;
                GetFavoriteExercisesTask getFavoriteExercisesTask = new GetFavoriteExercisesTask(this.diaryService, this.session.getUser().getActiveDate(), this.exerciseType, ((SortOrderHelper) this.sortOrderHelper.get()).getCurrentSortOrderForTab(6005), i * 25);
                str = str2;
                task = getFavoriteExercisesTask;
                break;
            case BROWSE_ALL:
                str = TASK_GET_EXERCISES_ALL;
                task = getGetExercisesTask(GetExercisesTask.Filter.All);
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unhandled Tab: ");
                sb.append(this.exerciseSearchTab);
                throw new IllegalArgumentException(sb.toString());
        }
        getRunner().run(str, task);
    }

    private void sortResults() {
        SortOrder currentSortOrderForTab = ((SortOrderHelper) this.sortOrderHelper.get()).getCurrentSortOrderForTab(this.exerciseSearchTab.getTabId());
        if (currentSortOrderForTab != SortOrder.RECENTLY_USED && CollectionUtils.notEmpty((Collection<?>) this.queriedLocalExerciseEntries)) {
            Collections.sort(this.queriedLocalExerciseEntries, new Comparator(currentSortOrderForTab == SortOrder.ALPHABETICAL_DESCENDING) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final int compare(Object obj, Object obj2) {
                    return ExerciseSearchViewModel.lambda$sortResults$1(ExerciseSearchViewModel.this, this.f$1, (MfpExerciseEntry) obj, (MfpExerciseEntry) obj2);
                }
            });
        }
    }

    public static /* synthetic */ int lambda$sortResults$1(ExerciseSearchViewModel exerciseSearchViewModel, boolean z, MfpExerciseEntry mfpExerciseEntry, MfpExerciseEntry mfpExerciseEntry2) {
        String descriptionName = ((ExerciseStringService) exerciseSearchViewModel.exerciseStringService.get()).getDescriptionName(mfpExerciseEntry.getExercise());
        String descriptionName2 = ((ExerciseStringService) exerciseSearchViewModel.exerciseStringService.get()).getDescriptionName(mfpExerciseEntry2.getExercise());
        return z ? descriptionName2.compareToIgnoreCase(descriptionName) : descriptionName.compareToIgnoreCase(descriptionName2);
    }

    private Task getGetExercisesTask(GetExercisesTask.Filter filter) {
        GetExercisesTask getExercisesTask = new GetExercisesTask(this.exerciseService, this.diaryService, filter, this.exerciseType, ((SortOrderHelper) this.sortOrderHelper.get()).getCurrentSortOrderForTab(this.exerciseSearchTab.getTabId()), this.session, this.exerciseEntryMapper);
        return getExercisesTask;
    }

    /* access modifiers changed from: private */
    public void notifyLocalListFiltered() {
        this.wereLastResultsFromOnlineSearch = false;
        notifyPropertyChanged(Property.LOCAL_LIST_FETCHED_AND_FILTERED);
    }
}
