package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "items", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "kotlin.jvm.PlatformType", "", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchViewModel.kt */
final class LocalFoodSearchViewModel$filterResults$4<T> implements Consumer<List<DiaryEntryCellModel>> {
    final /* synthetic */ LocalFoodSearchViewModel this$0;

    LocalFoodSearchViewModel$filterResults$4(LocalFoodSearchViewModel localFoodSearchViewModel) {
        this.this$0 = localFoodSearchViewModel;
    }

    public final void accept(List<DiaryEntryCellModel> list) {
        this.this$0.getSearchItems().setValue(list);
    }
}
