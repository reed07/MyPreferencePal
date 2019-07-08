package com.myfitnesspal.shared.service.ads;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.constants.Constants.ConfigParam;
import com.myfitnesspal.shared.model.AdType;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import dagger.Lazy;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class AdUnitServiceImpl implements AdUnitService {
    private AdsSettings adSettings;
    private Context context;
    private final Lazy<UacfConfigurationUtil> uacfConfigurationUtil;

    @Inject
    public AdUnitServiceImpl(Context context2, AdsSettings adsSettings, Lazy<UacfConfigurationUtil> lazy) {
        this.context = context2;
        this.adSettings = adsSettings;
        this.uacfConfigurationUtil = lazy;
    }

    @NotNull
    public String getNativeDfpTemplateAdCampaingsId() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getInteger(ConfigParam.NATIVE_DFP_TEMPLATE_AD_COMPAINGS_ID, Integer.valueOf(Integer.parseInt(this.context.getString(R.string.nativeDfpTemplateAdCampaingsId)))).toString();
    }

    @NotNull
    public AdUnit getDiaryScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_DIARY_SCREEN.getPath(), ConfigParam.DFP_UNIT_DIARY_SCREEN.getPath(), ConfigParam.AMAZON_AD_UUID_DIARY_SCREEN.getPath(), getMoPubAdUnitId(R.string.diaryScreenAdUnitValue, R.string.test_diaryScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getProgressScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_PROGRESS_SCREEN.getPath(), ConfigParam.DFP_UNIT_PROGRESS_SCREEN.getPath(), ConfigParam.AMAZON_AD_UUID_PROGRESS_SCREEN.getPath(), getMoPubAdUnitId(R.string.progressScreenAdUnitValue, R.string.test_progressScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getCompleteEntryScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.MEDIUM, ConfigParam.DFP_UNIT_COMPLETE_ENTRY_SCREEN.getPath(), ConfigParam.DFP_UNIT_COMPLETE_ENTRY_SCREEN.getPath(), ConfigParam.AMAZON_AD_UUID_COMPLETE_ENTRY_SCREEN.getPath(), null, false);
    }

    @NotNull
    public AdUnit getAddEntryScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_ADD_ENTRY.getPath(), ConfigParam.DFP_UNIT_ADD_ENTRY.getPath(), ConfigParam.AMAZON_AD_UUID_ADD_ENTRY.getPath(), getMoPubAdUnitId(R.string.addEntryScreenAdUnitValue, R.string.test_addEntryScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getAddIngredientScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_ADD_INGREDIENT_SCREEN.getPath(), ConfigParam.DFP_UNIT_ADD_INGREDIENT_SCREEN.getPath(), ConfigParam.AMAZON_AD_UUID_ADD_INGREDIENT_SCREEN.getPath(), getMoPubAdUnitId(R.string.addIngredientScreenAdUnitValue, R.string.test_addIngredientScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getAddOrEditFoodEntryScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_ADD_EDIT_ENTRY.getPath(), ConfigParam.DFP_UNIT_ADD_EDIT_ENTRY.getPath(), ConfigParam.AMAZON_AD_UUID_ADD_EDIT_ENTRY.getPath(), getMoPubAdUnitId(R.string.addOrEditFoodEntryScreenAdUnitValue, R.string.test_addOrEditFoodEntryScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getAddOrEditExerciseEntryScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_ADD_EDIT_EXERCISE.getPath(), ConfigParam.DFP_UNIT_ADD_EDIT_EXERCISE.getPath(), ConfigParam.AMAZON_AD_UUID_ADD_EDIT_EXERCISE.getPath(), getMoPubAdUnitId(R.string.addOrEditExerciseEntryScreenAdUnitValue, R.string.test_addOrEditExerciseEntryScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getNutritionScreenDailyAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_DAILY_NUTRITION.getPath(), ConfigParam.DFP_UNIT_DAILY_NUTRITION.getPath(), ConfigParam.AMAZON_AD_UUID_DAILY_NUTRITION.getPath(), getMoPubAdUnitId(R.string.nutritionScreenDailyAdUnitValue, R.string.test_nutritionScreenDailyAdUnitValue), true);
    }

    @NotNull
    public AdUnit getExerciseSearchScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_EXERCISE_SEARCH.getPath(), ConfigParam.DFP_UNIT_EXERCISE_SEARCH.getPath(), ConfigParam.AMAZON_AD_UUID_EXERCISE_SEARCH.getPath(), getMoPubAdUnitId(R.string.exerciseSearchScreenAdUnitValue, R.string.test_exerciseSearchScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getFoodSearchScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_FOOD_SEARCH.getPath(), ConfigParam.DFP_UNIT_FOOD_SEARCH.getPath(), ConfigParam.AMAZON_AD_UUID_FOOD_SEARCH.getPath(), getMoPubAdUnitId(R.string.foodSearchScreenAdUnitValue, R.string.test_foodSearchScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getFriendsTabFriendsScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_FRIENDS_FRIENDS.getPath(), ConfigParam.DFP_UNIT_FRIENDS_FRIENDS.getPath(), ConfigParam.AMAZON_AD_UUID_FRIENDS_FRIENDS.getPath(), getMoPubAdUnitId(R.string.friendsTabFriendsScreenAdUnitValue, R.string.test_friendsTabFriendsScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getFriendsTabMessagesScreenValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_FRIENDS_MESSAGES.getPath(), ConfigParam.DFP_UNIT_FRIENDS_MESSAGES.getPath(), ConfigParam.AMAZON_AD_UUID_FRIENDS_MESSAGES.getPath(), getMoPubAdUnitId(R.string.friendsTabMessagesScreenValue, R.string.test_friendsTabMessagesScreenValue), true);
    }

    @NotNull
    public AdUnit getFriendsTabNewsScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_FRIENDS_NEWS.getPath(), ConfigParam.DFP_UNIT_FRIENDS_NEWS.getPath(), ConfigParam.AMAZON_AD_UUID_FRIENDS_NEWS.getPath(), getMoPubAdUnitId(R.string.friendsTabNewsScreenAdUnitValue, R.string.test_friendsTabNewsScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getFriendsTabProfileScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_FRIENDS_PROFILE.getPath(), ConfigParam.DFP_UNIT_FRIENDS_PROFILE.getPath(), ConfigParam.AMAZON_AD_UUID_FRIENDS_PROFILE.getPath(), getMoPubAdUnitId(R.string.friendsTabProfileScreenAdUnitValue, R.string.test_friendsTabProfileScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getFriendsTabRequestsScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_FRIENDS_REQUEST.getPath(), ConfigParam.DFP_UNIT_FRIENDS_REQUEST.getPath(), ConfigParam.AMAZON_AD_UUID_FRIENDS_REQUEST.getPath(), getMoPubAdUnitId(R.string.friendsTabProfileScreenAdUnitValue, R.string.test_friendsTabProfileScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getHomeScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_HOME.getPath(), ConfigParam.DFP_UNIT_HOME.getPath(), ConfigParam.AMAZON_AD_UUID_HOME.getPath(), getMoPubAdUnitId(R.string.homeScreenAdUnitValue, R.string.test_homeScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getDiaryNoteScreenAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_NOTES.getPath(), ConfigParam.DFP_UNIT_NOTES.getPath(), ConfigParam.AMAZON_AD_UUID_NOTES.getPath(), getMoPubAdUnitId(R.string.addDiaryNoteScreenAdUnitValue, R.string.test_addDiaryNoteScreenAdUnitValue), true);
    }

    @NotNull
    public AdUnit getNutritionScreenWeeklyAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_WEEKLY_NUTRITION.getPath(), ConfigParam.DFP_UNIT_WEEKLY_NUTRITION.getPath(), ConfigParam.AMAZON_AD_UUID_WEEKLY_NUTRITION.getPath(), getMoPubAdUnitId(R.string.nutritionScreenWeeklyAdUnitValue, R.string.test_nutritionScreenWeeklyAdUnitValue), true);
    }

    @NotNull
    public AdUnit getSmartWaterSectionHeaderAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.ONE_PIXEL, ConfigParam.DFP_UNIT_SMART_WATER_SECTION_HEADER.getPath(), null, null, null, false);
    }

    @NotNull
    public AdUnit getSmartWaterEntryDialogAdUnitValue() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.WATER, ConfigParam.DFP_UNIT_SMART_WATER_ENTRY_DIALOG.getPath(), null, null, null, false);
    }

    @NotNull
    public AdUnit getNewsFeedDfpAdUnitValue(boolean z) {
        AdUnit adUnit;
        if (!isTestModeForAds()) {
            return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_NATIVE_WITH_PARTNERS.getPath(), null, null, getMoPubAdUnitId(R.string.newsfeedNativeAdUnitValue, R.string.test_newsfeedNativeAdUnitValue), false);
        }
        if (z) {
            adUnit = ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_NATIVE_FIRST_POSITION.getPath(), null, null, getMoPubAdUnitId(R.string.newsfeedNativeAdUnitValue, R.string.test_newsfeedNativeAdUnitValue), false);
        } else {
            adUnit = ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getAdUnit(AdType.BANNER, ConfigParam.DFP_UNIT_NATIVE_TENTH_POSITION.getPath(), null, null, getMoPubAdUnitId(R.string.newsfeedNativeAdUnitValue, R.string.test_newsfeedNativeAdUnitValue), false);
        }
        return adUnit;
    }

    private boolean isTestModeForAds() {
        return this.adSettings.isTestModeForAds();
    }

    private String getMoPubAdUnitId(int i, int i2) {
        return isTestModeForAds() ? this.context.getString(i2) : this.context.getString(i);
    }
}
