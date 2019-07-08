package com.myfitnesspal.shared.model.v15;

import android.support.annotation.NonNull;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.FoodAndExerciseFactory;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import dagger.Lazy;

public class ExerciseSearchResult extends SearchResult<Exercise> {
    final Lazy<DbConnectionManager> dbConnectionManager;

    public ExerciseSearchResult(@NonNull Lazy<DbConnectionManager> lazy) {
        this.dbConnectionManager = lazy;
    }

    public ExerciseSearchResult(Exercise exercise, @NonNull Lazy<DbConnectionManager> lazy) {
        this(lazy);
        this.data = exercise;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.data = FoodAndExerciseFactory.createExerciseFrom(binaryDecoder, (DbConnectionManager) this.dbConnectionManager.get());
    }
}
