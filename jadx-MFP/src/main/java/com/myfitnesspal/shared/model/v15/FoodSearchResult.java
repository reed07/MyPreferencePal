package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodAndExerciseFactory;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import dagger.Lazy;
import javax.annotation.Nonnull;

public class FoodSearchResult extends SearchResult<Food> {
    final Lazy<DbConnectionManager> dbConnectionManager;

    public FoodSearchResult(@Nonnull Lazy<DbConnectionManager> lazy) {
        this.dbConnectionManager = lazy;
    }

    public FoodSearchResult(Food food, @Nonnull Lazy<DbConnectionManager> lazy) {
        this(lazy);
        this.data = food;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.data = FoodAndExerciseFactory.createFoodFrom(binaryDecoder, (DbConnectionManager) this.dbConnectionManager.get());
    }
}
