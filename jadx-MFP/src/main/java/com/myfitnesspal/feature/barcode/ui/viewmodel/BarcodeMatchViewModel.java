package com.myfitnesspal.feature.barcode.ui.viewmodel;

import android.content.Context;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.myfitnesspal.shared.model.v2.SearchResultItem;
import com.uacf.core.util.ArrayUtil;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BarcodeMatchViewModel extends RunnerViewModel<String> {
    private static final int INVALID_TASK_ID = -1;
    private static final int SEARCH_RESULT_LIMIT = 100;
    private List<MfpFood> matches;
    private String nextLink;
    private List<MfpFood> searchResults;
    private final Lazy<SearchService> searchService;
    private long searchTaskId = -1;

    private static class FoodSearchTask extends EventedTaskBase<Result, ApiException> {
        private static final String FLOW_ID = "new-barcode-scanner";
        private String nextToken;
        private final Lazy<SearchService> searchService;
        private String searchTerm;

        public static class Result {
            public List<MfpFood> foods;
            public String nextLink;
        }

        public static FoodSearchTask createWithTerm(Lazy<SearchService> lazy, String str) {
            FoodSearchTask foodSearchTask = new FoodSearchTask(lazy);
            foodSearchTask.searchTerm = str;
            return foodSearchTask;
        }

        public static FoodSearchTask createWithNextLink(Lazy<SearchService> lazy, String str) {
            FoodSearchTask foodSearchTask = new FoodSearchTask(lazy);
            foodSearchTask.nextToken = str;
            return foodSearchTask;
        }

        private FoodSearchTask(Lazy<SearchService> lazy) {
            this.searchService = lazy;
        }

        /* access modifiers changed from: protected */
        public Result exec(Context context) throws ApiException {
            Tuple2 tuple2;
            if (Strings.notEmpty(this.searchTerm)) {
                tuple2 = ((SearchService) this.searchService.get()).searchForFoodV2(this.searchTerm, FLOW_ID);
            } else {
                tuple2 = ((SearchService) this.searchService.get()).getMoreResultsV2(this.nextToken, FLOW_ID);
            }
            Result result = new Result();
            result.nextLink = (String) tuple2.getItem2();
            result.foods = Enumerable.select((Collection<T>) ((ApiResponse) tuple2.getItem1()).getItems(), (ReturningFunction1<U, T>) new ReturningFunction1<MfpFood, MfpFoodSearchResult>() {
                public MfpFood execute(MfpFoodSearchResult mfpFoodSearchResult) {
                    SearchResultItem searchResultItem = mfpFoodSearchResult.getSearchResultItem();
                    if (searchResultItem instanceof MfpFood) {
                        return (MfpFood) searchResultItem;
                    }
                    return null;
                }
            });
            return result;
        }
    }

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int SEARCH_RESULTS = ViewModelPropertyId.next();
        public static final int SEARCH_RESULTS_RESET = ViewModelPropertyId.next();
    }

    public BarcodeMatchViewModel(Runner runner, Lazy<SearchService> lazy, List<MfpFood> list) {
        super(runner);
        this.searchService = lazy;
        if (list == null) {
            list = new ArrayList<>();
        }
        this.matches = list;
        this.searchResults = new ArrayList();
    }

    public List<MfpFood> getMatches() {
        return this.matches;
    }

    public List<MfpFood> getSearchResults() {
        return this.searchResults;
    }

    public void clearSearchResults() {
        reset();
        notifyPropertyChanged(Property.SEARCH_RESULTS_RESET);
    }

    public void load(String... strArr) {
        if (ArrayUtil.size(strArr) > 0 && Strings.notEmpty(strArr[0])) {
            reset();
            search(FoodSearchTask.createWithTerm(this.searchService, strArr[0]));
            notifyPropertyChanged(Property.SEARCH_RESULTS);
        }
    }

    public void loadNextPage() {
        if (canLoadNextPage()) {
            search(FoodSearchTask.createWithNextLink(this.searchService, this.nextLink));
        }
    }

    private boolean canLoadNextPage() {
        return !isLoading() && this.searchResults.size() < 100 && Strings.notEmpty(this.nextLink);
    }

    private void search(FoodSearchTask foodSearchTask) {
        setState(State.Loading);
        this.searchTaskId = foodSearchTask.setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.CancelExisting).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), FoodSearchTask.class, this.searchTaskId)) {
            if (taskDetails.successful()) {
                Result result = (Result) taskDetails.getResult();
                this.nextLink = result.nextLink;
                this.searchResults.addAll(result.foods);
                setState(State.Loaded);
                notifyPropertyChanged(Property.SEARCH_RESULTS);
            } else {
                setState(State.Error);
            }
            this.searchTaskId = -1;
        }
    }

    private void reset() {
        this.nextLink = null;
        this.searchResults.clear();
        this.searchTaskId = -1;
    }
}
