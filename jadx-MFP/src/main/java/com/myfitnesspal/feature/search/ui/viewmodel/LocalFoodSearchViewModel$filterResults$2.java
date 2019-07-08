package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import io.reactivex.functions.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "item", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "test"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchViewModel.kt */
final class LocalFoodSearchViewModel$filterResults$2<T> implements Predicate<DiaryEntryCellModel> {
    final /* synthetic */ String $lowerCaseQuery;
    final /* synthetic */ LocalFoodSearchViewModel this$0;

    LocalFoodSearchViewModel$filterResults$2(LocalFoodSearchViewModel localFoodSearchViewModel, String str) {
        this.this$0 = localFoodSearchViewModel;
        this.$lowerCaseQuery = str;
    }

    public final boolean test(@NotNull DiaryEntryCellModel diaryEntryCellModel) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, Attributes.ITEM);
        return this.this$0.itemMatchesQuery(diaryEntryCellModel, this.$lowerCaseQuery);
    }
}
