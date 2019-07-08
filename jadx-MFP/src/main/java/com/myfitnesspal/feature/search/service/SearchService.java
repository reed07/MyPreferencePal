package com.myfitnesspal.feature.search.service;

import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v15.SearchResult;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.Tuple3;
import io.reactivex.Single;
import java.util.List;

public interface SearchService {
    void fetchPairedFoods(long j, int i, String str, Function1<List<DiaryEntryCellModel>> function1);

    @Deprecated
    List<DiaryEntryCellModel> fetchResultsFromDBSync(String str, int i, int i2);

    @Deprecated
    List<DiaryEntryCellModel> fetchResultsFromDBSync(String str, SortOrder sortOrder, int i, int i2);

    MfpFood findByIdAndVersion(String str, String str2) throws ApiException;

    Tuple2<ApiResponse<MfpFoodSearchResult>, String> getMoreResultsV2(String str, String str2) throws ApiException;

    Single<String> mapQueryToAdCategory(String str);

    List<SearchResult> searchForExercise(int i, String str, int i2);

    Tuple2<ApiResponse<MfpFoodSearchResult>, String> searchForFoodV2(String str, String str2) throws ApiException;

    Single<Tuple3<ApiResponse<MfpFoodSearchResult>, String, String>> searchForFoodV2(String str, String str2, boolean z, boolean z2, boolean z3);
}
