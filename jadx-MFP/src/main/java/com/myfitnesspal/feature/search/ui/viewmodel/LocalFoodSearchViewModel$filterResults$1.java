package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u00012\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "kotlin.jvm.PlatformType", "items", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchViewModel.kt */
final class LocalFoodSearchViewModel$filterResults$1<T, R> implements Function<T, Iterable<? extends U>> {
    public static final LocalFoodSearchViewModel$filterResults$1 INSTANCE = new LocalFoodSearchViewModel$filterResults$1();

    LocalFoodSearchViewModel$filterResults$1() {
    }

    public final List<DiaryEntryCellModel> apply(List<? extends DiaryEntryCellModel> list) {
        return list;
    }
}
