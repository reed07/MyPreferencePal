package com.myfitnesspal.shared.task;

import android.content.Context;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.progress.model.ImageStatusMetadata;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import dagger.Lazy;

public class SyncAndUploadImagePostStatusTask extends UploadImagePostStatusTask {
    private final MealFood mealFood;
    private final Lazy<MealService> mealService;

    public static SyncAndUploadImagePostStatusTask newInstanceForMealFood(String str, String str2, String str3, Lazy<ImageService> lazy, Lazy<ImageUploadService> lazy2, Lazy<NewsFeedService> lazy3, MealFood mealFood2, String str4, Lazy<MealService> lazy4) {
        SyncAndUploadImagePostStatusTask syncAndUploadImagePostStatusTask = new SyncAndUploadImagePostStatusTask(str, str2, str3, null, null, lazy, lazy2, lazy3, mealFood2, str4, lazy4);
        return syncAndUploadImagePostStatusTask;
    }

    private SyncAndUploadImagePostStatusTask(String str, String str2, String str3, ImageStatusMetadata imageStatusMetadata, User user, Lazy<ImageService> lazy, Lazy<ImageUploadService> lazy2, Lazy<NewsFeedService> lazy3, MealFood mealFood2, String str4, Lazy<MealService> lazy4) {
        super(str, str2, str3, imageStatusMetadata, user, lazy, lazy2, lazy3, mealFood2, str4, Type.MealFood);
        this.mealFood = mealFood2;
        this.mealService = lazy4;
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) throws Exception {
        ((MealService) this.mealService.get()).syncAndUpdateIdsIfNeeded(this.mealFood);
        return super.exec(context);
    }
}
