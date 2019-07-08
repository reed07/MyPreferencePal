package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodOrExercise;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import dagger.Lazy;
import javax.annotation.Nonnull;

public final class SearchResultFactory {
    public static SearchResult createSearchResult(int i, BinaryDecoder binaryDecoder, @Nonnull Lazy<DbConnectionManager> lazy) {
        SearchResult searchResult;
        switch (i) {
            case 1:
                searchResult = new FoodSearchResult(lazy);
                break;
            case 2:
                searchResult = new ExerciseSearchResult(lazy);
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid search result type ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
        searchResult.readData(binaryDecoder);
        return searchResult;
    }

    public static SearchResult createSearchResult(FoodOrExercise foodOrExercise, @Nonnull Lazy<DbConnectionManager> lazy) {
        if (foodOrExercise instanceof Food) {
            return new FoodSearchResult((Food) foodOrExercise, lazy);
        }
        if (foodOrExercise instanceof Exercise) {
            return new ExerciseSearchResult((Exercise) foodOrExercise, lazy);
        }
        throw new IllegalArgumentException("Invalid FoodOrExercise type");
    }
}
