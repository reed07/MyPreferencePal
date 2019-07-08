package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter;
import com.myfitnesspal.shared.model.FoodImages;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.MealEntries;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0002\u0010\rJ\u0001\u0010\u000e\u001a^\u0012(\u0012&\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0012\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00130\u0010 \u0012*.\u0012(\u0012&\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0012\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00130\u0010\u0018\u00010\u000f0\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0017H\u0002J.\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000f2\u0006\u0010\u001f\u001a\u00020\u001aJ.\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J$\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00130\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ.\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00130\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ$\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00130\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u0014\u0010,\u001a\u0004\u0018\u00010-2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u0016\u0010.\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J \u0010/\u001a\u00020*2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/myfitnesspal/feature/search/repository/LocalFoodSearchRepository;", "", "dbConnectionManager", "Lcom/myfitnesspal/shared/db/DbConnectionManager;", "localSettingsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "session", "Lcom/myfitnesspal/shared/service/session/Session;", "mealUtil", "Lcom/myfitnesspal/feature/meals/util/MealUtil;", "(Lcom/myfitnesspal/shared/db/DbConnectionManager;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;)V", "constructSingleForData", "Lio/reactivex/Single;", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "kotlin.jvm.PlatformType", "", "sortOrder", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "fetchData", "Lkotlin/Function0;", "fetchHistoryItems", "limit", "", "mealName", "", "fetchImagesForFoodOfType", "Lcom/myfitnesspal/shared/model/FoodImages;", "type", "fetchRecentFoods", "dbAdapter", "Lcom/myfitnesspal/shared/db/adapter/FoodEntriesDBAdapter;", "userId", "", "mealId", "fetchUserFoods", "fetchUserMeals", "fetchUserRecipes", "filterDuplicates", "", "results", "latestPreviousFoodEntriesForMealName", "Lcom/myfitnesspal/shared/model/v1/MealEntries;", "removeRecentlyDeletedItems", "sortResultsList", "list", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchRepository.kt */
public final class LocalFoodSearchRepository {
    private final Lazy<CountryService> countryService;
    /* access modifiers changed from: private */
    public final DbConnectionManager dbConnectionManager;
    /* access modifiers changed from: private */
    public final Lazy<LocalSettingsService> localSettingsService;
    /* access modifiers changed from: private */
    public final Lazy<MealUtil> mealUtil;
    /* access modifiers changed from: private */
    public final Lazy<Session> session;

    @Inject
    public LocalFoodSearchRepository(@NotNull DbConnectionManager dbConnectionManager2, @NotNull Lazy<LocalSettingsService> lazy, @NotNull Lazy<CountryService> lazy2, @NotNull Lazy<Session> lazy3, @NotNull Lazy<MealUtil> lazy4) {
        Intrinsics.checkParameterIsNotNull(dbConnectionManager2, "dbConnectionManager");
        Intrinsics.checkParameterIsNotNull(lazy, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(lazy2, "countryService");
        Intrinsics.checkParameterIsNotNull(lazy3, "session");
        Intrinsics.checkParameterIsNotNull(lazy4, "mealUtil");
        this.dbConnectionManager = dbConnectionManager2;
        this.localSettingsService = lazy;
        this.countryService = lazy2;
        this.session = lazy3;
        this.mealUtil = lazy4;
    }

    @NotNull
    public final Single<List<DiaryEntryCellModel>> fetchUserFoods(@Nullable SortOrder sortOrder, int i) {
        Single<List<DiaryEntryCellModel>> constructSingleForData = constructSingleForData(sortOrder, new LocalFoodSearchRepository$fetchUserFoods$1(this, sortOrder, i));
        Intrinsics.checkExpressionValueIsNotNull(constructSingleForData, "constructSingleForData(s…mutableListOf()\n        }");
        return constructSingleForData;
    }

    @NotNull
    public final Single<List<DiaryEntryCellModel>> fetchUserMeals(@Nullable SortOrder sortOrder, int i, @Nullable String str) {
        Single<List<DiaryEntryCellModel>> constructSingleForData = constructSingleForData(null, new LocalFoodSearchRepository$fetchUserMeals$1(this, sortOrder, i, str));
        Intrinsics.checkExpressionValueIsNotNull(constructSingleForData, "constructSingleForData(n…}\n            }\n        }");
        return constructSingleForData;
    }

    @NotNull
    public final Single<List<DiaryEntryCellModel>> fetchUserRecipes(@Nullable SortOrder sortOrder, int i) {
        Single<List<DiaryEntryCellModel>> constructSingleForData = constructSingleForData(sortOrder, new LocalFoodSearchRepository$fetchUserRecipes$1(this, sortOrder, i));
        Intrinsics.checkExpressionValueIsNotNull(constructSingleForData, "constructSingleForData(s…toMutableList()\n        }");
        return constructSingleForData;
    }

    @NotNull
    public final Single<List<DiaryEntryCellModel>> fetchHistoryItems(@Nullable SortOrder sortOrder, int i, @Nullable String str) {
        Single<List<DiaryEntryCellModel>> constructSingleForData = constructSingleForData(sortOrder, new LocalFoodSearchRepository$fetchHistoryItems$1(this, str, sortOrder, i));
        Intrinsics.checkExpressionValueIsNotNull(constructSingleForData, "constructSingleForData(s…        results\n        }");
        return constructSingleForData;
    }

    @NotNull
    public final Single<FoodImages> fetchImagesForFoodOfType(int i) {
        Single<FoodImages> fromCallable = Single.fromCallable(new LocalFoodSearchRepository$fetchImagesForFoodOfType$1(this, i));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Single.fromCallable {\n  …fType(type)\n            }");
        return fromCallable;
    }

    private final Single<List<DiaryEntryCellModel>> constructSingleForData(SortOrder sortOrder, Function0<? extends List<DiaryEntryCellModel>> function0) {
        return Single.fromCallable(new LocalFoodSearchRepository$constructSingleForData$1(this, function0, sortOrder));
    }

    /* access modifiers changed from: private */
    public final void sortResultsList(List<DiaryEntryCellModel> list, SortOrder sortOrder) {
        if (sortOrder == SortOrder.ALPHABETICAL_ASCENDING) {
            if (list.size() > 1) {
                CollectionsKt.sortWith(list, new LocalFoodSearchRepository$sortResultsList$$inlined$sortBy$1());
            }
        } else if (sortOrder == SortOrder.ALPHABETICAL_DESCENDING && list.size() > 1) {
            CollectionsKt.sortWith(list, new LocalFoodSearchRepository$sortResultsList$$inlined$sortByDescending$1());
        }
    }

    /* access modifiers changed from: private */
    public final MealEntries latestPreviousFoodEntriesForMealName(String str) {
        CharSequence charSequence = str;
        MealEntries mealEntries = null;
        if (charSequence == null || charSequence.length() == 0) {
            return null;
        }
        int mealIdForName = ((Session) this.session.get()).getUser().getMealNames().mealIdForName(str);
        ArrayList fetchLatestPreviousFoodEntriesForMealId = this.dbConnectionManager.foodEntriesDbAdapter().fetchLatestPreviousFoodEntriesForMealId(mealIdForName);
        if (fetchLatestPreviousFoodEntriesForMealId != null) {
            Object obj = fetchLatestPreviousFoodEntriesForMealId.get(fetchLatestPreviousFoodEntriesForMealId.size() - 1);
            if (obj != null) {
                mealEntries = new MealEntries(((FoodEntry) obj).getDate(), mealIdForName, fetchLatestPreviousFoodEntriesForMealId, this.session);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.model.v1.FoodEntry");
            }
        }
        return mealEntries;
    }

    /* access modifiers changed from: private */
    public final List<DiaryEntryCellModel> fetchRecentFoods(FoodEntriesDBAdapter foodEntriesDBAdapter, long j, int i, int i2) {
        if (!((CountryService) this.countryService.get()).isEnglishCurrentDialect()) {
            List<DiaryEntryCellModel> fetchRecentFrequentAndOwnedFoodsForUserId = foodEntriesDBAdapter.fetchRecentFrequentAndOwnedFoodsForUserId(1, j, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(fetchRecentFrequentAndOwnedFoodsForUserId, "dbAdapter.fetchRecentFre…                   limit)");
            return fetchRecentFrequentAndOwnedFoodsForUserId;
        }
        List<DiaryEntryCellModel> fetchRecentlyUsedFoodsForUserId = foodEntriesDBAdapter.fetchRecentlyUsedFoodsForUserId(j, (long) i, i2);
        Intrinsics.checkExpressionValueIsNotNull(fetchRecentlyUsedFoodsForUserId, "dbAdapter.fetchRecentlyU…                   limit)");
        return fetchRecentlyUsedFoodsForUserId;
    }

    /* access modifiers changed from: private */
    public final void removeRecentlyDeletedItems(List<DiaryEntryCellModel> list) {
        Food food;
        Object obj = this.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "localSettingsService.get()");
        Set recentsDeletedFoodOriginalUids = ((LocalSettingsService) obj).getRecentsDeletedFoodOriginalUids();
        List arrayList = new ArrayList();
        for (DiaryEntryCellModel diaryEntryCellModel : list) {
            if (diaryEntryCellModel.isFood()) {
                if (diaryEntryCellModel != null) {
                    food = (Food) diaryEntryCellModel;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.model.v1.Food");
                }
            } else if (diaryEntryCellModel != null) {
                food = ((FoodEntry) diaryEntryCellModel).getFood();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.model.v1.FoodEntry");
            }
            Intrinsics.checkExpressionValueIsNotNull(food, "food");
            if (recentsDeletedFoodOriginalUids.contains(food.getOriginalUid())) {
                arrayList.add(diaryEntryCellModel);
            }
        }
        list.removeAll(arrayList);
    }

    /* access modifiers changed from: private */
    public final void filterDuplicates(List<DiaryEntryCellModel> list) {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        long localId = ((Session) this.session.get()).getUser().getLocalId();
        while (it.hasNext()) {
            DiaryEntryCellModel diaryEntryCellModel = (DiaryEntryCellModel) it.next();
            if (diaryEntryCellModel instanceof Food) {
                Food food = (Food) diaryEntryCellModel;
                if (food.isDeleted && !food.isPublic && localId == food.ownerUserId) {
                    it.remove();
                } else if (food.hasOriginalUid()) {
                    String originalUid = food.getOriginalUid();
                    if (hashSet.contains(originalUid)) {
                        it.remove();
                    } else {
                        hashSet.add(originalUid);
                    }
                }
            }
        }
    }
}
