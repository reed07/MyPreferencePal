package com.myfitnesspal.feature.search.ui.fragment;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "item", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "position", "", "isChecked", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initListeners$2 extends Lambda implements Function3<DiaryEntryCellModel, Integer, Boolean, Unit> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initListeners$2(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
        super(3);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((DiaryEntryCellModel) obj, ((Number) obj2).intValue(), ((Boolean) obj3).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DiaryEntryCellModel diaryEntryCellModel, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, Attributes.ITEM);
        if (z) {
            LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).addItemToMultiAdd(diaryEntryCellModel, i);
        } else {
            LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).removeItemFromMultiAdd(diaryEntryCellModel);
        }
    }
}
