package com.myfitnesspal.shared.service.ads;

import com.myfitnesspal.shared.model.AdUnit;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u000202H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0012\u0010\u000e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0012\u0010\u0010\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0012\u0010\u0012\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0012\u0010\u0014\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0012\u0010\u0016\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0012\u0010\u0018\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0012\u0010\u001a\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u0012\u0010\u001c\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0005R\u0012\u0010\u001e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005R\u0012\u0010 \u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0005R\u0012\u0010\"\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0012\u0010&\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0005R\u0012\u0010(\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u0005R\u0012\u0010*\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u0005R\u0012\u0010,\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\u0005R\u0012\u0010.\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0005¨\u00063"}, d2 = {"Lcom/myfitnesspal/shared/service/ads/AdUnitService;", "", "addEntryScreenAdUnitValue", "Lcom/myfitnesspal/shared/model/AdUnit;", "getAddEntryScreenAdUnitValue", "()Lcom/myfitnesspal/shared/model/AdUnit;", "addIngredientScreenAdUnitValue", "getAddIngredientScreenAdUnitValue", "addOrEditExerciseEntryScreenAdUnitValue", "getAddOrEditExerciseEntryScreenAdUnitValue", "addOrEditFoodEntryScreenAdUnitValue", "getAddOrEditFoodEntryScreenAdUnitValue", "completeEntryScreenAdUnitValue", "getCompleteEntryScreenAdUnitValue", "diaryNoteScreenAdUnitValue", "getDiaryNoteScreenAdUnitValue", "diaryScreenAdUnitValue", "getDiaryScreenAdUnitValue", "exerciseSearchScreenAdUnitValue", "getExerciseSearchScreenAdUnitValue", "foodSearchScreenAdUnitValue", "getFoodSearchScreenAdUnitValue", "friendsTabFriendsScreenAdUnitValue", "getFriendsTabFriendsScreenAdUnitValue", "friendsTabMessagesScreenValue", "getFriendsTabMessagesScreenValue", "friendsTabNewsScreenAdUnitValue", "getFriendsTabNewsScreenAdUnitValue", "friendsTabProfileScreenAdUnitValue", "getFriendsTabProfileScreenAdUnitValue", "friendsTabRequestsScreenAdUnitValue", "getFriendsTabRequestsScreenAdUnitValue", "homeScreenAdUnitValue", "getHomeScreenAdUnitValue", "nativeDfpTemplateAdCampaingsId", "", "getNativeDfpTemplateAdCampaingsId", "()Ljava/lang/String;", "nutritionScreenDailyAdUnitValue", "getNutritionScreenDailyAdUnitValue", "nutritionScreenWeeklyAdUnitValue", "getNutritionScreenWeeklyAdUnitValue", "progressScreenAdUnitValue", "getProgressScreenAdUnitValue", "smartWaterEntryDialogAdUnitValue", "getSmartWaterEntryDialogAdUnitValue", "smartWaterSectionHeaderAdUnitValue", "getSmartWaterSectionHeaderAdUnitValue", "getNewsFeedDfpAdUnitValue", "isFirstPosition", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdUnitService.kt */
public interface AdUnitService {
    @NotNull
    AdUnit getAddEntryScreenAdUnitValue();

    @NotNull
    AdUnit getAddIngredientScreenAdUnitValue();

    @NotNull
    AdUnit getAddOrEditExerciseEntryScreenAdUnitValue();

    @NotNull
    AdUnit getAddOrEditFoodEntryScreenAdUnitValue();

    @NotNull
    AdUnit getCompleteEntryScreenAdUnitValue();

    @NotNull
    AdUnit getDiaryNoteScreenAdUnitValue();

    @NotNull
    AdUnit getDiaryScreenAdUnitValue();

    @NotNull
    AdUnit getExerciseSearchScreenAdUnitValue();

    @NotNull
    AdUnit getFoodSearchScreenAdUnitValue();

    @NotNull
    AdUnit getFriendsTabFriendsScreenAdUnitValue();

    @NotNull
    AdUnit getFriendsTabMessagesScreenValue();

    @NotNull
    AdUnit getFriendsTabNewsScreenAdUnitValue();

    @NotNull
    AdUnit getFriendsTabProfileScreenAdUnitValue();

    @NotNull
    AdUnit getFriendsTabRequestsScreenAdUnitValue();

    @NotNull
    AdUnit getHomeScreenAdUnitValue();

    @NotNull
    String getNativeDfpTemplateAdCampaingsId();

    @NotNull
    AdUnit getNewsFeedDfpAdUnitValue(boolean z);

    @NotNull
    AdUnit getNutritionScreenDailyAdUnitValue();

    @NotNull
    AdUnit getNutritionScreenWeeklyAdUnitValue();

    @NotNull
    AdUnit getProgressScreenAdUnitValue();

    @NotNull
    AdUnit getSmartWaterEntryDialogAdUnitValue();

    @NotNull
    AdUnit getSmartWaterSectionHeaderAdUnitValue();
}
