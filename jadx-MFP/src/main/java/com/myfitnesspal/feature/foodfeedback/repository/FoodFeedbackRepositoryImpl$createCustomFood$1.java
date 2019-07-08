package com.myfitnesspal.feature.foodfeedback.repository;

import com.facebook.ads.internal.j.e;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter.DBTaskResult;
import com.myfitnesspal.shared.model.v1.Food;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "e", "Lio/reactivex/SingleEmitter;", "Lcom/myfitnesspal/shared/model/v1/Food;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackRepositoryImpl.kt */
final class FoodFeedbackRepositoryImpl$createCustomFood$1<T> implements SingleOnSubscribe<T> {
    final /* synthetic */ Food $newFood;
    final /* synthetic */ FoodFeedbackRepositoryImpl this$0;

    FoodFeedbackRepositoryImpl$createCustomFood$1(FoodFeedbackRepositoryImpl foodFeedbackRepositoryImpl, Food food) {
        this.this$0 = foodFeedbackRepositoryImpl;
        this.$newFood = food;
    }

    public final void subscribe(@NotNull SingleEmitter<Food> singleEmitter) {
        Intrinsics.checkParameterIsNotNull(singleEmitter, e.a);
        if (((DbConnectionManager) this.this$0.getDbConnectionManager().get()).foodDbAdapter().insertFood(this.$newFood, null, (DbConnectionManager) this.this$0.getDbConnectionManager().get()) == DBTaskResult.SUCCESS) {
            singleEmitter.onSuccess(this.$newFood);
        } else {
            singleEmitter.onError(new Exception());
        }
    }
}
