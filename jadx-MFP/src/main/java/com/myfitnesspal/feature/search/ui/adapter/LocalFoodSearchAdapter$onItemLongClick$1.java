package com.myfitnesspal.feature.search.ui.adapter;

import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "<anonymous parameter 1>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchAdapter.kt */
final class LocalFoodSearchAdapter$onItemLongClick$1 extends Lambda implements Function2<DiaryEntryCellModel, Integer, Unit> {
    public static final LocalFoodSearchAdapter$onItemLongClick$1 INSTANCE = new LocalFoodSearchAdapter$onItemLongClick$1();

    LocalFoodSearchAdapter$onItemLongClick$1() {
        super(2);
    }

    public final void invoke(@NotNull DiaryEntryCellModel diaryEntryCellModel, int i) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, "<anonymous parameter 0>");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((DiaryEntryCellModel) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
