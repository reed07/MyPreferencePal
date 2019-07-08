package com.myfitnesspal.feature.foodfeedback.repository;

import com.myfitnesspal.feature.foodfeedback.model.FoodExistsCheckItem;
import com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackItem;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Food;
import dagger.Lazy;
import io.reactivex.Completable;
import io.reactivex.Single;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0002\u0010\nJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00112\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/repository/FoodFeedbackRepositoryImpl;", "Lcom/myfitnesspal/feature/foodfeedback/repository/FoodFeedbackAction;", "mfpV2Api", "Ljavax/inject/Provider;", "Lcom/myfitnesspal/shared/api/v2/MfpV2Api;", "dbConnectionManager", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/db/DbConnectionManager;", "appSettings", "Lcom/myfitnesspal/feature/settings/model/AppSettings;", "(Ljavax/inject/Provider;Ldagger/Lazy;Ldagger/Lazy;)V", "getAppSettings", "()Ldagger/Lazy;", "getDbConnectionManager", "getMfpV2Api", "()Ljavax/inject/Provider;", "checkIfFoodWithDescriptionExists", "Lio/reactivex/Single;", "", "foodExistsCheckItem", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodExistsCheckItem;", "createCustomFood", "Lcom/myfitnesspal/shared/model/v1/Food;", "newFood", "postFeedback", "Lio/reactivex/Completable;", "foodFeedbackItem", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackItem;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackRepositoryImpl.kt */
public final class FoodFeedbackRepositoryImpl implements FoodFeedbackAction {
    @NotNull
    private final Lazy<AppSettings> appSettings;
    @NotNull
    private final Lazy<DbConnectionManager> dbConnectionManager;
    @NotNull
    private final Provider<MfpV2Api> mfpV2Api;

    public FoodFeedbackRepositoryImpl(@NotNull Provider<MfpV2Api> provider, @NotNull Lazy<DbConnectionManager> lazy, @NotNull Lazy<AppSettings> lazy2) {
        Intrinsics.checkParameterIsNotNull(provider, "mfpV2Api");
        Intrinsics.checkParameterIsNotNull(lazy, "dbConnectionManager");
        Intrinsics.checkParameterIsNotNull(lazy2, "appSettings");
        this.mfpV2Api = provider;
        this.dbConnectionManager = lazy;
        this.appSettings = lazy2;
    }

    @NotNull
    public final Provider<MfpV2Api> getMfpV2Api() {
        return this.mfpV2Api;
    }

    @NotNull
    public final Lazy<DbConnectionManager> getDbConnectionManager() {
        return this.dbConnectionManager;
    }

    @NotNull
    public final Lazy<AppSettings> getAppSettings() {
        return this.appSettings;
    }

    @NotNull
    public Single<Food> createCustomFood(@NotNull Food food) {
        Intrinsics.checkParameterIsNotNull(food, "newFood");
        Single<Food> create = Single.create(new FoodFeedbackRepositoryImpl$createCustomFood$1(this, food));
        Intrinsics.checkExpressionValueIsNotNull(create, "Single.create { e ->\n   …)\n            }\n        }");
        return create;
    }

    @NotNull
    public Completable postFeedback(@NotNull FoodFeedbackItem foodFeedbackItem) {
        Intrinsics.checkParameterIsNotNull(foodFeedbackItem, "foodFeedbackItem");
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setItem(foodFeedbackItem);
        Completable fromCallable = Completable.fromCallable(new FoodFeedbackRepositoryImpl$postFeedback$1(this, apiRequest));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Completable.fromCallable…(FOOD_FEEDBACK)\n        }");
        return fromCallable;
    }

    @NotNull
    public Single<Boolean> checkIfFoodWithDescriptionExists(@NotNull FoodExistsCheckItem foodExistsCheckItem) {
        Intrinsics.checkParameterIsNotNull(foodExistsCheckItem, "foodExistsCheckItem");
        Single<Boolean> create = Single.create(new FoodFeedbackRepositoryImpl$checkIfFoodWithDescriptionExists$1(this, foodExistsCheckItem));
        Intrinsics.checkExpressionValueIsNotNull(create, "Single.create { e ->\n   …Success(result)\n        }");
        return create;
    }
}
