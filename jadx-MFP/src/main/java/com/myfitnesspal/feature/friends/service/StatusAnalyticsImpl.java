package com.myfitnesspal.feature.friends.service;

import com.myfitnesspal.feature.friends.service.StatusAnalytics.Source;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;

public class StatusAnalyticsImpl implements StatusAnalytics {
    private static final String ATTRIBUTE_IMAGE_ADDED = "image_added";
    private static final String ATTRIBUTE_IMAGE_TYPE = "image_type";
    private static final String ATTRIBUTE_SOURCE = "source";
    private static final String EVENT_KEY_DESCRIPTION = "description";
    private static final String EVENT_KEY_SOURCE = "source";
    private static final String EVENT_POST_STATUS_UPDATE_COMPLETED = "status_update_screen_share_completed";
    private static final String EVENT_POST_STATUS_UPDATE_FAILED = "status_update_screen_share_failed";
    private static final String EVENT_POST_STATUS_UPDATE_STARTED = "status_update_screen_share_started";
    private static final String EVENT_UPDATE_STATUS_CLICKED = "status_update_CTA";
    private static final String EVENT_UPDATE_STATUS_SCREEN_VIEW = "status_update_screen_view";
    private static final String STATUS_UPDATE_ADD_PHOTO_CLICKED = "status_update_add_photo_clicked";
    private static final String STATUS_UPDATE_ADD_PHOTO_OPTION_CLICKED = "status_update_add_photo_option_clicked";
    private Lazy<AnalyticsService> analytics;

    public StatusAnalyticsImpl(Lazy<AnalyticsService> lazy) {
        this.analytics = lazy;
    }

    public void reportPostStatusUpdateCompleted(String str) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_POST_STATUS_UPDATE_COMPLETED, MapUtil.createMap(ATTRIBUTE_IMAGE_ADDED, String.valueOf(Strings.notEmpty(str)), "image_type", str));
    }

    public void reportPostStatusUpdateStarted() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_POST_STATUS_UPDATE_STARTED);
    }

    public void reportStatusUpdateViewed() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_UPDATE_STATUS_SCREEN_VIEW);
    }

    public void reportUpdateStatusClicked(Source source) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_UPDATE_STATUS_CLICKED, MapUtil.createMap("source", source.getAnalyticsValue()));
    }

    public void reportPostStatusUpdateFailed(String str) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_POST_STATUS_UPDATE_FAILED, MapUtil.createMap("source", Strings.toString(str)));
    }

    public void reportStatusUpdateAddPhotoOptionClicked(String str) {
        ((AnalyticsService) this.analytics.get()).reportEvent(STATUS_UPDATE_ADD_PHOTO_OPTION_CLICKED, MapUtil.createMap("source", str));
    }

    public void reportStatusUpdateAddPhotoClicked() {
        ((AnalyticsService) this.analytics.get()).reportEvent(STATUS_UPDATE_ADD_PHOTO_CLICKED);
    }
}
