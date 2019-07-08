package com.myfitnesspal.feature.search.repository;

import android.content.Context;
import com.myfitnesspal.feature.search.model.OnlineSearchResult;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.uacf.core.util.Tuple3;
import io.reactivex.Single;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eJ\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b2\u0006\u0010\u0014\u001a\u00020\u000eJN\u0010\u0015\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u00160\u000b2\u0006\u0010\u0014\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/myfitnesspal/feature/search/repository/OnlineFoodSearchRepository;", "", "context", "Landroid/content/Context;", "searchService", "Lcom/myfitnesspal/feature/search/service/SearchService;", "(Landroid/content/Context;Lcom/myfitnesspal/feature/search/service/SearchService;)V", "foodMapper", "Lkotlin/Lazy;", "Lcom/myfitnesspal/shared/model/mapper/ApiJsonMapper;", "fetchFoodsByLink", "Lio/reactivex/Single;", "Lcom/myfitnesspal/feature/search/model/OnlineSearchResult;", "link", "", "flowId", "fetchSearchAd", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "category", "fetchSponsoredCategoryForQuery", "query", "queryFoods", "Lcom/uacf/core/util/Tuple3;", "Lcom/myfitnesspal/shared/api/ApiResponse;", "Lcom/myfitnesspal/shared/model/v2/MfpFoodSearchResult;", "shouldShowVenues", "", "isAdPresent", "verifiedOnly", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchRepository.kt */
public final class OnlineFoodSearchRepository {
    public static final Companion Companion = new Companion(null);
    private static final String DFP_ATTR_FOOD_OBJECT = "FoodObject";
    private static final String DFP_CUSTOM_KEY_FOOD_CATEGORY = "food_cat";
    private static final String DFP_PATH_SPONSORED_FOOD = "/17729925/UACF_M/MFP/FoodSearch/FS_Results_DRD";
    private static final String DFP_TEMPLATE_ID_SPONSORED_FOOD = "11760166";
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final Lazy<ApiJsonMapper> foodMapper = LazyKt.lazy(OnlineFoodSearchRepository$foodMapper$1.INSTANCE);
    /* access modifiers changed from: private */
    public final SearchService searchService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/search/repository/OnlineFoodSearchRepository$Companion;", "", "()V", "DFP_ATTR_FOOD_OBJECT", "", "DFP_CUSTOM_KEY_FOOD_CATEGORY", "DFP_PATH_SPONSORED_FOOD", "DFP_TEMPLATE_ID_SPONSORED_FOOD", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnlineFoodSearchRepository.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public OnlineFoodSearchRepository(@NotNull Context context2, @NotNull SearchService searchService2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(searchService2, "searchService");
        this.context = context2;
        this.searchService = searchService2;
    }

    @NotNull
    public final Single<Tuple3<ApiResponse<MfpFoodSearchResult>, String, String>> queryFoods(@NotNull String str, @Nullable String str2, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkParameterIsNotNull(str, "query");
        Single<Tuple3<ApiResponse<MfpFoodSearchResult>, String, String>> searchForFoodV2 = this.searchService.searchForFoodV2(str, str2, z, z2, z3);
        Intrinsics.checkExpressionValueIsNotNull(searchForFoodV2, "searchService.searchForF…sAdPresent, verifiedOnly)");
        return searchForFoodV2;
    }

    @NotNull
    public final Single<OnlineSearchResult> fetchFoodsByLink(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(str, "link");
        Single<OnlineSearchResult> fromCallable = Single.fromCallable(new OnlineFoodSearchRepository$fetchFoodsByLink$1(this, str, str2));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Single.fromCallable {\n  … = tuple.item2)\n        }");
        return fromCallable;
    }

    @NotNull
    public final Single<String> fetchSponsoredCategoryForQuery(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "query");
        Single<String> mapQueryToAdCategory = this.searchService.mapQueryToAdCategory(str);
        Intrinsics.checkExpressionValueIsNotNull(mapQueryToAdCategory, "searchService.mapQueryToAdCategory(query)");
        return mapQueryToAdCategory;
    }

    @NotNull
    public final Single<SponsoredFood> fetchSearchAd(@Nullable String str) {
        Single<SponsoredFood> create = Single.create(new OnlineFoodSearchRepository$fetchSearchAd$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(create, "Single.create { emitter …      .build())\n        }");
        return create;
    }
}
