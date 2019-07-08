package com.myfitnesspal.feature.video.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;

public class VideoAnalyticsHelper {
    private static final String ATTRIBUTE_DURATION = "duration";
    private static final String ATTRIBUTE_FLOW_ID = "flow_id";
    private static final String ATTRIBUTE_POSITION_IN_GALLERY_LIST = "position_in_gallery_list";
    private static final String ATTRIBUTE_SPONSOR_NAME = "sponsor_name";
    private static final String ATTRIBUTE_VIDEO_CLICKS = "video_clicks";
    private static final String ATTRIBUTE_VIDEO_FRANCHISE = "video_franchise";
    private static final String ATTRIBUTE_VIDEO_TITLE = "video_title";
    private static final String EVENT_FRANCHISE_SPONSOR_LINK_CLICKED = "franchise_sponsor_link_clicked";
    private static final String EVENT_FRANCHISE_SPONSOR_LINK_VIEWED = "franchise_sponsor_link_viewed";
    private static final String EVENT_VIDEO_LIST_ITEM_AUTOPLAYED = "video_list_item_autoplayed";
    private static final String EVENT_VIDEO_LIST_ITEM_CLICKED = "video_list_item_clicked";
    private static final String EVENT_VIDEO_PLAYER_EXIT = "video_player_exit";
    private final Lazy<AnalyticsService> analyticsService;

    public VideoAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportFranchiseSponsorLinkViewed(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FRANCHISE_SPONSOR_LINK_VIEWED, MapUtil.createMap("flow_id", Strings.toString(str), ATTRIBUTE_VIDEO_FRANCHISE, Strings.toString(str2), ATTRIBUTE_SPONSOR_NAME, Strings.toString(str3)));
    }

    public void reportFranchiseSponsorLinkClicked(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FRANCHISE_SPONSOR_LINK_CLICKED, MapUtil.createMap("flow_id", Strings.toString(str), ATTRIBUTE_VIDEO_FRANCHISE, Strings.toString(str2), ATTRIBUTE_SPONSOR_NAME, Strings.toString(str3)));
    }

    public void reportListItemAutoPlayed(@Nullable String str, @NonNull String str2, @Nullable String str3, @Nullable String str4) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_VIDEO_LIST_ITEM_AUTOPLAYED, MapUtil.createMap("flow_id", Strings.toString(str), ATTRIBUTE_VIDEO_TITLE, Strings.toString(str2), ATTRIBUTE_VIDEO_FRANCHISE, Strings.toString(str3), ATTRIBUTE_SPONSOR_NAME, Strings.toString(str4)));
    }

    public void reportListItemClicked(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_VIDEO_LIST_ITEM_CLICKED, MapUtil.createMap("flow_id", Strings.toString(str), ATTRIBUTE_VIDEO_TITLE, Strings.toString(str2), ATTRIBUTE_VIDEO_FRANCHISE, Strings.toString(str3), ATTRIBUTE_SPONSOR_NAME, Strings.toString(str4), ATTRIBUTE_POSITION_IN_GALLERY_LIST, Strings.toString(Integer.valueOf(i))));
    }

    public void reportVideoPlayerExit(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, double d, int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_VIDEO_PLAYER_EXIT, MapUtil.createMap("flow_id", Strings.toString(str), ATTRIBUTE_VIDEO_TITLE, Strings.toString(str2), ATTRIBUTE_VIDEO_FRANCHISE, Strings.toString(str3), ATTRIBUTE_SPONSOR_NAME, Strings.toString(str4), "duration", Strings.toString(Double.valueOf(d)), ATTRIBUTE_VIDEO_CLICKS, Strings.toString(Integer.valueOf(i))));
    }
}
