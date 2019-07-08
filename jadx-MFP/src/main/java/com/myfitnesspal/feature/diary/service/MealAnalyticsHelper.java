package com.myfitnesspal.feature.diary.service;

import com.myfitnesspal.feature.images.service.ImageUploadService.ImageType;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

public class MealAnalyticsHelper {
    private static final String ATTRIBUTE_ARTIFACT_DATA_TYPE = "artifact_data_type";
    private static final String ATTRIBUTE_CAPTION = "caption";
    private static final String ATTRIBUTE_CURRENT_MEAL_PERMISSION = "current_meal_permission";
    private static final String ATTRIBUTE_FLOW_ID = "flow_id";
    private static final String ATTRIBUTE_HAS_NOTES = "has_notes";
    private static final String ATTRIBUTE_HAS_PHOTO = "has_photo";
    private static final String ATTRIBUTE_MEAL_ITEMS_COUNT = "meal_item_count";
    private static final String ATTRIBUTE_MEAL_ITEM_COUNT = "meal_item_count";
    private static final String ATTRIBUTE_NEW_MEAL_PERMISSION = "new_meal_permission";
    private static final String ATTRIBUTE_OPERATION = "operation";
    private static final String ATTRIBUTE_OWNER_TYPE = "owner_type";
    private static final String ATTRIBUTE_PERMISSION_LEVEL = "permission_level";
    private static final String ATTRIBUTE_START_SCREEN = "start_screen";
    private static final String ATTRIBUTE_TYPE = "type";
    private static final String ATTRIBUTE_VALUE_NEW = "new";
    private static final String ATTRIBUTE_VALUE_SAVE = "save";
    private static final String EVENT_FRIENDS_MEAL_SAVED = "friends_meal_saved";
    private static final String EVENT_FRIENDS_MEAL_SAVE_FAILED = "friends_meal_save_failed";
    private static final String EVENT_FRIENDS_MEAL_VIEWED = "friends_meal_viewed";
    private static final String EVENT_FRIENDS_MEAL_VIEW_FAILED = "friends_meal_view_failed";
    private static final String EVENT_MEAL_FLOW_COMPLETED = "meal_flow_completed";
    private static final String EVENT_MEAL_FLOW_STARTED = "meal_flow_started";
    private static final String EVENT_MEAL_IMAGE_EDIT_COMPLETED = "image_edited_completed";
    private static final String EVENT_MEAL_IMAGE_EDIT_TAPPED = "image_edited_tapped";
    private static final String EVENT_MEAL_IMAGE_REMOVED = "image_removed";
    private static final String EVENT_MEAL_IMPORT_PHOTO_COMPLETED = "meal_import_photo_completed";
    private static final String EVENT_MEAL_IMPORT_PHOTO_STARTED = "meal_import_photo_started";
    private static final String EVENT_MEAL_PROFILE_TAPPED = "meal_profile_tapped";
    private static final String EVENT_SCREEN_CREATE_MEAL = "SCREEN_create_meal";
    private static final String EVENT_SCREEN_VIEW_MEAL = "SCREEN_view_meal";
    private static final String EVENT_SHARE_MEAL_ARTIFACT_VIEWED = "share_meal_artifact_viewed";
    private static final String EVENT_SHARE_MEAL_PERMISSION_ALERT_ACCEPTED = "share_meal_permission_alert_accepted";
    private static final String EVENT_SHARE_MEAL_PERMISSION_ALERT_CANCELLED = "share_meal_permission_alert_cancelled";
    private static final String EVENT_SHARE_MEAL_PERMISSION_ALERT_SHOWN = "share_meal_permission_alert_shown";
    private static final String EVENT_SHARE_MEAL_SHARE_COMPLETED = "share_meal_share_completed";
    private static final String EVENT_SHARE_MEAL_SHARE_STARTED_NEWSFEED = "share_meal_share_started_newsfeed";
    private static final String EVENT_SHARE_MEAL_STARTED = "share_meal_started";
    private static final String EVENT_SHARE_MEAL_UNABLE_TO_SHARE_ALERT_SHOWN = "share_meal_unable_to_share_alert_shown";
    private static final String EVENT_VIEW_SAVED_MEAL = "view_saved_meals";
    public static final String VALUE_DIARY_EDIT = "diary_edit";
    public static final String VALUE_DIARY_FRIEND = "diary_friend";
    public static final String VALUE_DIARY_MORE = "diary_more";
    public static final String VALUE_FOOD_SEARCH = "food_search";
    private static final String VALUE_FRIEND = "friend";
    private static final String VALUE_GENERATE_ARTIFACT = "generate_artifact";
    private static final String VALUE_MEAL = "meal";
    public static final String VALUE_MEAL_RECIPES_FOODS = "meals_recipes_foods";
    public static final String VALUE_MY_ITEMS = "my_items";
    private static final String VALUE_SELF = "self";
    private static final String VALUE_SYNC_UPLOAD = "sync_upload";
    private final Lazy<AnalyticsService> analyticsService;

    public MealAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportMealFlowStartedEvent(String str, String str2, String str3, boolean z, boolean z2, Permission permission) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_FLOW_STARTED, MapUtil.createMap(ATTRIBUTE_START_SCREEN, str, "flow_id", str2, "type", str3, "has_photo", Strings.toString(Boolean.valueOf(z)), ATTRIBUTE_HAS_NOTES, Strings.toString(Boolean.valueOf(z2)), ATTRIBUTE_PERMISSION_LEVEL, getPermissionString(permission)));
    }

    public void reportMealFlowCompletedEvent(String str, String str2, String str3, int i, boolean z, boolean z2, Permission permission) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_FLOW_COMPLETED, MapUtil.createMap(ATTRIBUTE_START_SCREEN, str, "flow_id", str2, "type", str3, "meal_item_count", Strings.toString(Integer.valueOf(i)), "has_photo", Strings.toString(Boolean.valueOf(z)), ATTRIBUTE_HAS_NOTES, Strings.toString(Boolean.valueOf(z2)), ATTRIBUTE_PERMISSION_LEVEL, getPermissionString(permission)));
    }

    public void reportImageImportStarted() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_IMPORT_PHOTO_STARTED);
    }

    public void reportImageImportCompleted() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_IMPORT_PHOTO_COMPLETED);
    }

    public void reportViewSavedMeal() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_VIEW_SAVED_MEAL);
    }

    public void reportScreenViewedForCreateOrSaveAsMode(boolean z, Permission permission) {
        reportScreenViewed(true, z, 0, false, permission);
    }

    public void reportScreenViewedForOtherModes(int i, boolean z, Permission permission) {
        reportScreenViewed(false, false, i, z, permission);
    }

    public void reportImageEditTapped() {
        reportEventWithImageType(EVENT_MEAL_IMAGE_EDIT_TAPPED);
    }

    public void reportImageEditCompleted() {
        reportEventWithImageType(EVENT_MEAL_IMAGE_EDIT_COMPLETED);
    }

    public void reportImageRemoved() {
        reportEventWithImageType(EVENT_MEAL_IMAGE_REMOVED);
    }

    public void reportFriendsMealViewed(boolean z) {
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str = EVENT_FRIENDS_MEAL_VIEWED;
        String[] strArr = new String[2];
        strArr[0] = ATTRIBUTE_OWNER_TYPE;
        strArr[1] = z ? VALUE_SELF : "friend";
        analyticsService2.reportEvent(str, MapUtil.createMap(strArr));
    }

    public void reportFriendsMealViewFailed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FRIENDS_MEAL_VIEW_FAILED);
    }

    public void reportFriendsMealSaved() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FRIENDS_MEAL_SAVED);
    }

    public void reportFriendsMealSaveFailed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FRIENDS_MEAL_SAVE_FAILED);
    }

    public void reportShareMealStarted(boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SHARE_MEAL_STARTED, MapUtil.createMap("has_photo", Strings.toString(Boolean.valueOf(z))));
    }

    public void reportShareMealArtifactViewed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SHARE_MEAL_ARTIFACT_VIEWED);
    }

    public void reportShareMealShareStartedNewsfeed(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SHARE_MEAL_SHARE_STARTED_NEWSFEED, MapUtil.createMap("artifact_data_type", "meal", "caption", str));
    }

    public void reportShareMealShareCompleted(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SHARE_MEAL_SHARE_COMPLETED, MapUtil.createMap("artifact_data_type", "meal", "caption", str));
    }

    public void reportShareMealPermissionAlertShown(Permission permission, Permission permission2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SHARE_MEAL_PERMISSION_ALERT_SHOWN, MapUtil.createMap(ATTRIBUTE_CURRENT_MEAL_PERMISSION, getPermissionString(permission), ATTRIBUTE_NEW_MEAL_PERMISSION, getPermissionString(permission2)));
    }

    public void reportShareMealPermissionAlertAccepted() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SHARE_MEAL_PERMISSION_ALERT_ACCEPTED);
    }

    public void reportShareMealPermissionAlertCancelled() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SHARE_MEAL_PERMISSION_ALERT_CANCELLED);
    }

    public void reportMealProfileTapped() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_PROFILE_TAPPED);
    }

    public void reportShareMealFailedDuringArtifactGeneration() {
        reportShareMealFailed(VALUE_GENERATE_ARTIFACT);
    }

    public void reportShareMealFailedDuringSyncOrUpload() {
        reportShareMealFailed(VALUE_SYNC_UPLOAD);
    }

    private void reportShareMealFailed(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SHARE_MEAL_UNABLE_TO_SHARE_ALERT_SHOWN, MapUtil.createMap("operation", str));
    }

    private void reportScreenViewed(boolean z, boolean z2, int i, boolean z3, Permission permission) {
        HashMap hashMap = new HashMap();
        if (z) {
            hashMap.put("type", z2 ? ATTRIBUTE_VALUE_NEW : ATTRIBUTE_VALUE_SAVE);
        } else {
            hashMap.put("meal_item_count", Strings.toString(Integer.valueOf(i)));
            hashMap.put("has_photo", Strings.toString(Boolean.valueOf(z3)));
        }
        hashMap.put(ATTRIBUTE_PERMISSION_LEVEL, getPermissionString(permission));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(z ? EVENT_SCREEN_CREATE_MEAL : EVENT_SCREEN_VIEW_MEAL, (Map<String, String>) hashMap);
    }

    private void reportEventWithImageType(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(str, MapUtil.createMap("image_type", ImageType.MealPhoto.getAnalyticsValue()));
    }

    private String getPermissionString(Permission permission) {
        return Strings.toString(permission).toLowerCase();
    }
}
