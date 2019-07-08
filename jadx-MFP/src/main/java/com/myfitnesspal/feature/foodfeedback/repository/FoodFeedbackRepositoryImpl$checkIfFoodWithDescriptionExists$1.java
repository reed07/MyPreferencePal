package com.myfitnesspal.feature.foodfeedback.repository;

import com.facebook.ads.internal.j.e;
import com.myfitnesspal.feature.foodfeedback.model.FoodExistsCheckItem;
import com.myfitnesspal.shared.db.DbConnectionManager;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "e", "Lio/reactivex/SingleEmitter;", "", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackRepositoryImpl.kt */
final class FoodFeedbackRepositoryImpl$checkIfFoodWithDescriptionExists$1<T> implements SingleOnSubscribe<T> {
    final /* synthetic */ FoodExistsCheckItem $foodExistsCheckItem;
    final /* synthetic */ FoodFeedbackRepositoryImpl this$0;

    FoodFeedbackRepositoryImpl$checkIfFoodWithDescriptionExists$1(FoodFeedbackRepositoryImpl foodFeedbackRepositoryImpl, FoodExistsCheckItem foodExistsCheckItem) {
        this.this$0 = foodFeedbackRepositoryImpl;
        this.$foodExistsCheckItem = foodExistsCheckItem;
    }

    public final void subscribe(@NotNull SingleEmitter<Boolean> singleEmitter) {
        Intrinsics.checkParameterIsNotNull(singleEmitter, e.a);
        singleEmitter.onSuccess(Boolean.valueOf(((DbConnectionManager) this.this$0.getDbConnectionManager().get()).foodDbAdapter().checkForExistingFoodWithDescription(this.$foodExistsCheckItem.getFoodName(), this.$foodExistsCheckItem.getBrand(), Boolean.valueOf(this.$foodExistsCheckItem.getCheckBrand()), Boolean.valueOf(this.$foodExistsCheckItem.getCheckPublicFood()), this.$foodExistsCheckItem.getItemType(), this.$foodExistsCheckItem.getUserLocalId(), this.this$0.getAppSettings())));
    }
}
