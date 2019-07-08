package com.myfitnesspal.feature.progress.service;

import com.myfitnesspal.feature.progress.constants.ArtifactType;
import com.myfitnesspal.feature.progress.constants.GalleryViewMode;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.constants.ShareTarget;
import com.myfitnesspal.feature.progress.constants.SharingPermission;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics.TapTarget;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.MessageType;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.util.AnalyticsUtil;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;

public class ProgressAnalyticsImpl implements ProgressAnalytics {
    private final Lazy<AnalyticsService> analytics;

    interface AttributeKey {
        public static final String ARTIFACT_DATA_TYPE = "artifact_data_type";
        public static final String ARTIFACT_TYPE = "artifact_type";
        public static final String CAPTION = "caption";
        public static final String CARD_TYPE = "card_type";
        public static final String CHANGED_DATE = "changed_date";
        public static final String CHANGED_PERMISSION = "changed_permission";
        public static final String CONTAINS_INTERSTITIAL_IMAGE = "contains_interstitial_image";
        public static final String COUNT = "count";
        public static final String ENTRY_POINT = "entry_point";
        public static final String HAS_PHOTO = "has_photo";
        public static final String IMAGE_ID = "image_id";
        public static final String INTERSTITIAL_ORDER = "interstitial_order";
        public static final String INTERSTITIAL_TYPE = "interstitial_type";
        public static final String IS_TODAY = "is_today";
        public static final String NEWSFEED_STORY_ID = "newsfeed_story_id";
        public static final String REASON = "reason";
        public static final String REPORTEE_USER_ID = "reportee_user_id";
        public static final String REPORTER_USER_ID = "reporter_user_id";
        public static final String SOCIAL_NETWORK = "social_network";
        public static final String TAPPED = "tapped";
        public static final String TODAY_MINUS_DATE = "today_minus_date";
        public static final String WEIGHT_CHANGED = "weight_changed";
        public static final String WEIGHT_EXISTS = "weight_exists";
    }

    interface AttributeValue {
        public static final String OFF = "off";
        public static final String ON = "on";
        public static final String PERMISSION_COMMUNITY = "community";
        public static final String PERMISSION_FRIENDS_ONLY = "friends_only";
        public static final String PROGRESS_PHOTO_CARD_TYPE = "image_status_update";
    }

    interface EventName {
        public static final String ARTIFACT_SCREEN_NEXT_TAPPED = "artifact_screen_next_tapped";
        public static final String ARTIFACT_SCREEN_VIEWED = "artifact_screen_viewed";
        public static final String GALLERY_IMPORT_PHOTO_COMPLETED = "progress_gallery_import_photo_completed";
        public static final String GALLERY_IMPORT_PHOTO_STARTED = "progress_gallery_import_photo_started";
        public static final String GALLERY_SHARE_TAP = "progress_gallery_share_tap";
        public static final String IMAGE_REPORTED = "image_reported";
        public static final String INTRO_PROGRESS_INTERSTITIAL_TAP = "intro_progress_interstitial_tap";
        public static final String INTRO_PROGRESS_INTERSTITIAL_VIEW = "intro_progress_interstitial_view";
        public static final String POST_PRIVACY_PERMISSIONS = "post_privacy_permissions";
        public static final String POST_PRIVACY_PERMISSIONS_DETAILS_VIEWED = "post_privacy_permissions_details_viewed";
        public static final String PROGRESS_IMPORT_PHOTO_COMPLETED = "progress_import_photo_completed";
        public static final String PROGRESS_IMPORT_PHOTO_STARTED = "progress_import_photo_started";
        public static final String PROGRESS_SCREEN_SINCE_START_WEIGHT_GRAPH = "progress_screen_since_start_weight_graph";
        public static final String PROGRESS_SHARE_SCREEN_VIEWED = "progress_share_screen_viewed";
        public static final String PROGRESS_VIEW_PHOTO = "progress_view_photo";
        public static final String SHARE_PROGRESS_ARTIFACT_VIEWED = "share_progress_artifact_viewed";
        public static final String SHARE_PROGRESS_INTERSTITIAL_NOT_NOW = "share_progress_interstitial_not_now";
        public static final String SHARE_PROGRESS_INTERSTITIAL_TAP = "share_progress_interstitial_tap";
        public static final String SHARE_PROGRESS_INTERSTITIAL_VIEW = "share_progress_interstitial_view";
        public static final String SHARE_PROGRESS_MESSAGE_TOGGLE = "share_progress_message_toggle";
        public static final String SHARE_PROGRESS_SHARE_COMPLETED_NEWSFEED = "share_progress_share_completed_newsfeed";
        public static final String SHARE_PROGRESS_SHARE_STARTED = "share_progress_share_started";
        public static final String SHARE_PROGRESS_SHARE_STARTED_NEWSFEED = "share_progress_share_started_newsfeed";
        public static final String WEIGHT_ENTRY_CAMERA_PHOTO_TAKEN = "weight_entry_camera_photo_taken";
        public static final String WEIGHT_ENTRY_CAMERA_VIEW = "weight_entry_camera_view";
        public static final String WEIGHT_ENTRY_IMPORT_PHOTO = "weight_entry_import_photo";
        public static final String WEIGHT_ENTRY_SAVED = "weight_entry_saved";
        public static final String WEIGHT_ENTRY_SCREEN_DISMISS = "weight_entry_screen_dismiss";
        public static final String WEIGHT_ENTRY_SCREEN_VIEW = "weight_entry_screen_view";
    }

    public ProgressAnalyticsImpl(Lazy<AnalyticsService> lazy) {
        this.analytics = lazy;
    }

    public void reportWeightEntryScreenView(ProgressEntryPoint progressEntryPoint) {
        if (progressEntryPoint == null) {
            progressEntryPoint = ProgressEntryPoint.Unknown;
        }
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.WEIGHT_ENTRY_SCREEN_VIEW, MapUtil.createMap("entry_point", progressEntryPoint.getAnalyticsValue()));
    }

    public void reportWeightEntryScreenDismiss() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.WEIGHT_ENTRY_SCREEN_DISMISS);
    }

    public void reportWeightEntryCameraView() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.WEIGHT_ENTRY_CAMERA_VIEW);
    }

    public void reportWeightEntryCameraPhotoTaken() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.WEIGHT_ENTRY_CAMERA_PHOTO_TAKEN);
    }

    public void reportWeightEntrySaved(ProgressEntryPoint progressEntryPoint, boolean z) {
        if (progressEntryPoint == null) {
            progressEntryPoint = ProgressEntryPoint.Unknown;
        }
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.WEIGHT_ENTRY_SAVED, MapUtil.createMap(AttributeKey.HAS_PHOTO, String.valueOf(z), "entry_point", progressEntryPoint.getAnalyticsValue()));
    }

    public void reportWeightEntrySaved(ProgressEntryPoint progressEntryPoint, boolean z, boolean z2, boolean z3) {
        if (progressEntryPoint == null) {
            progressEntryPoint = ProgressEntryPoint.Unknown;
        }
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.WEIGHT_ENTRY_SAVED, MapUtil.createMap(AttributeKey.HAS_PHOTO, String.valueOf(z), "entry_point", progressEntryPoint.getAnalyticsValue(), AttributeKey.IS_TODAY, String.valueOf(z2), "weight_changed", String.valueOf(z3)));
    }

    public void reportLegacyWeightEntrySaved() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.WEIGHT_ENTRY_SAVED, MapUtil.createMap(AttributeKey.HAS_PHOTO, "false"));
    }

    public void reportWeightEntryPhotoImported(boolean z, Date date) {
        long abs = Math.abs(DateTimeUtils.getNumberOfDaysBetween(new Date(), date));
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.WEIGHT_ENTRY_IMPORT_PHOTO, MapUtil.createMap(AttributeKey.CHANGED_DATE, String.valueOf(z), AttributeKey.TODAY_MINUS_DATE, String.valueOf(abs)));
    }

    public void reportProgressPhotoView() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.PROGRESS_VIEW_PHOTO);
    }

    public void reportViewTapped(TapTarget tapTarget) {
        ((AnalyticsService) this.analytics.get()).reportEvent(tapTarget.getEventName());
    }

    public void reportProgressImportPhotoCompleted() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.PROGRESS_IMPORT_PHOTO_COMPLETED);
    }

    public void reportProgressImportPhotoStarted() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.PROGRESS_IMPORT_PHOTO_STARTED);
    }

    public void reportGalleryImportPhotoCompleted() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.GALLERY_IMPORT_PHOTO_COMPLETED);
    }

    public void reportGalleryShareTapped(GalleryViewMode galleryViewMode) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.GALLERY_SHARE_TAP, MapUtil.createMap(AttributeKey.ARTIFACT_TYPE, galleryViewMode.getAnaltyicsValue()));
    }

    public void reportShareProgressMessageToggle(boolean z) {
        AnalyticsService analyticsService = (AnalyticsService) this.analytics.get();
        String str = EventName.SHARE_PROGRESS_MESSAGE_TOGGLE;
        String[] strArr = new String[2];
        strArr[0] = AttributeKey.TAPPED;
        strArr[1] = z ? "on" : "off";
        analyticsService.reportEvent(str, MapUtil.createMap(strArr));
    }

    public void reportGalleryImportPhotoStarted() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.GALLERY_IMPORT_PHOTO_STARTED);
    }

    public void reportShareProgressArtifactView(ArtifactType artifactType) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.SHARE_PROGRESS_ARTIFACT_VIEWED, MapUtil.createMap(AttributeKey.ARTIFACT_DATA_TYPE, String.valueOf(artifactType)));
    }

    public void reportShareProgressShareStarted(ShareTarget shareTarget, ArtifactType artifactType, boolean z, GalleryViewMode galleryViewMode, boolean z2) {
        reportShareProgressShareStarted(shareTarget, artifactType.getAnalyticsValue(), z, galleryViewMode.getAnaltyicsValue(), z2);
    }

    public void reportShareProgressShareStarted(ShareTarget shareTarget, String str, boolean z, String str2, boolean z2) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.SHARE_PROGRESS_SHARE_STARTED, MapUtil.createMap(AttributeKey.SOCIAL_NETWORK, shareTarget.getAnalyticsValue(), AttributeKey.ARTIFACT_DATA_TYPE, str, "caption", String.valueOf(z), AttributeKey.ARTIFACT_TYPE, str2, AttributeKey.CONTAINS_INTERSTITIAL_IMAGE, String.valueOf(z2)));
    }

    public void reportProgressPhotosIntroInterstitialView() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.INTRO_PROGRESS_INTERSTITIAL_VIEW);
    }

    public void reportShareProgressInterstitialNegative(MessageType messageType, int i, int i2) {
        reportShareProgressInterstitialEvent(EventName.SHARE_PROGRESS_INTERSTITIAL_NOT_NOW, messageType, i, i2);
    }

    public void reportShareProgressInterstitialPositive(MessageType messageType, int i, int i2) {
        reportShareProgressInterstitialEvent(EventName.SHARE_PROGRESS_INTERSTITIAL_TAP, messageType, i, i2);
    }

    public void reportShareProgressInterstitialView(MessageType messageType, int i, int i2) {
        reportShareProgressInterstitialEvent(EventName.SHARE_PROGRESS_INTERSTITIAL_VIEW, messageType, i, i2);
    }

    private void reportShareProgressInterstitialEvent(String str, MessageType messageType, int i, int i2) {
        if (messageType != null && Strings.notEmpty(str)) {
            String valueOf = String.valueOf(i);
            if (messageType == MessageType.WeightAbsolute && i > i2) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.valueOf(i2));
                sb.append("+");
                valueOf = sb.toString();
            }
            ((AnalyticsService) this.analytics.get()).reportEvent(str, MapUtil.createMap(AttributeKey.INTERSTITIAL_TYPE, messageType.getAnalyticValue(), AttributeKey.INTERSTITIAL_ORDER, valueOf));
        }
    }

    public void reportProgressPhotosIntroInterstitialPositive() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.INTRO_PROGRESS_INTERSTITIAL_TAP);
    }

    public void reportProgressScreenSinceStartingWeightGraph(boolean z) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.PROGRESS_SCREEN_SINCE_START_WEIGHT_GRAPH, MapUtil.createMap(AttributeKey.WEIGHT_EXISTS, AnalyticsUtil.getAnalyticsAttributeValueForBoolean(z)));
    }

    public void reportProgressPhotosShareStartedNewsfeed(String str, String str2, String str3, boolean z) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.SHARE_PROGRESS_SHARE_STARTED_NEWSFEED, MapUtil.createMap(AttributeKey.ARTIFACT_DATA_TYPE, str, "caption", String.valueOf(str2), AttributeKey.ARTIFACT_TYPE, str3, AttributeKey.CONTAINS_INTERSTITIAL_IMAGE, String.valueOf(z)));
    }

    public void reportProgressPhotosShareCompletedNewsfeed(String str, String str2, String str3, boolean z) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.SHARE_PROGRESS_SHARE_COMPLETED_NEWSFEED, MapUtil.createMap(AttributeKey.ARTIFACT_DATA_TYPE, str, "caption", String.valueOf(str2), AttributeKey.ARTIFACT_TYPE, str3, AttributeKey.CONTAINS_INTERSTITIAL_IMAGE, String.valueOf(z)));
    }

    public void reportImageReportedEvent(String str, String str2, String str3, String str4, String str5) {
        ((AnalyticsService) this.analytics.get()).reportEvent("image_reported", MapUtil.createMap("reason", str, "image_id", str2, AttributeKey.NEWSFEED_STORY_ID, str3, AttributeKey.REPORTER_USER_ID, str4, AttributeKey.REPORTEE_USER_ID, str5));
    }

    public void reportPostPrivacyPermissions(SharingPermission sharingPermission) {
        AnalyticsService analyticsService = (AnalyticsService) this.analytics.get();
        String str = EventName.POST_PRIVACY_PERMISSIONS;
        String[] strArr = new String[4];
        strArr[0] = AttributeKey.CHANGED_PERMISSION;
        strArr[1] = sharingPermission == SharingPermission.Community ? "community" : AttributeValue.PERMISSION_FRIENDS_ONLY;
        strArr[2] = AttributeKey.CARD_TYPE;
        strArr[3] = "image_status_update";
        analyticsService.reportEvent(str, MapUtil.createMap(strArr));
    }

    public void reportPostPrivacyPermissionViewed() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.POST_PRIVACY_PERMISSIONS_DETAILS_VIEWED, MapUtil.createMap(AttributeKey.CARD_TYPE, "image_status_update"));
    }

    public void reportProgressShareScreenViewed() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.PROGRESS_SHARE_SCREEN_VIEWED);
    }

    public void reportArtifactScreenViewed() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.ARTIFACT_SCREEN_VIEWED);
    }

    public void reportArtifactScreenNextTapped(ArtifactType artifactType, GalleryViewMode galleryViewMode, boolean z) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EventName.ARTIFACT_SCREEN_NEXT_TAPPED, MapUtil.createMap(AttributeKey.ARTIFACT_DATA_TYPE, artifactType.getAnalyticsValue(), AttributeKey.ARTIFACT_TYPE, galleryViewMode.getAnaltyicsValue(), AttributeKey.CONTAINS_INTERSTITIAL_IMAGE, String.valueOf(z)));
    }
}
