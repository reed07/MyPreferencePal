package com.myfitnesspal.feature.home.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.myfitnesspal.feature.home.model.BlogEntry;
import com.myfitnesspal.feature.home.model.VideoEntry;
import com.myfitnesspal.feature.home.ui.view.NewsFeedNativeAdState;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.video.util.VideoUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry.DataTypes;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NewsFeedAnalyticsHelper {
    private static final String ACTIVITY_COMMENT_ADDED = "activity_comment_added";
    private static final String ACTIVITY_ENTRY_DETAIL_VIEWED = "activity_entry_detail_viewed";
    private static final String ACTIVITY_ENTRY_VIEWED = "activity_entry_viewed";
    private static final String ADD_COMMENTS_PRESSED = "add_comments_pressed";
    private static final String AD_POSITION = "position";
    private static final String AD_SOURCE = "ad_source";
    private static final String AD_TIME_TO_FAIL = "time_to_fail";
    private static final String AD_TIME_TO_SERVE = "time_to_serve";
    private static final String AD_TYPE = "ad_type";
    private static final String ANLT_ATTR_VIDEO_TYPE = "video_type";
    private static final String ANLT_VALUE_AUTOPLAY = "auto-play";
    private static final String ANLT_VALUE_CLICK_TO_PLAY = "click-to-play";
    private static final String ATTRIBUTE_DURATION = "duration";
    private static final String ATTRIBUTE_FLOW_ID = "flow_id";
    private static final String ATTRIBUTE_HAS_PHOTO = "has_photo";
    private static final String ATTRIBUTE_POSITION_IN_NEWSFEED = "position_in_newsfeed";
    private static final String ATTRIBUTE_VIDEO_TITLE = "video_title";
    private static final String BLOG_CLICK_SOURCE = "blog_click_source";
    private static final String BLOG_ENTRY_NUMBER = "blog_entry_number";
    private static final String BLOG_POST_URL = "blog_post_url";
    private static final String CARD_TYPE = "card_type";
    private static final String COMMENTS_BUTTON_PRESSED = "comments_button_pressed";
    private static final String CONTEXT = "context";
    private static final String ENTRY_NUMBER = "entry_number";
    private static final String HERO_CARD_CLOSED = "Hero Card Closed";
    private static final String HERO_CARD_DISPLAYED = "Hero Card Displayed";
    private static final String HERO_CARD_TAPPED = "Hero Card Tapped";
    private static final String INDEX = "index";
    private static final String INDEX_IN_ADAPTER = "index_in_adapter";
    private static final double NANOS_PER_MILSECOND = 1000000.0d;
    private static final String NATIVE_AD_CLICKED = "native_ad_clicked";
    private static final String NATIVE_AD_FAILED = "native_ad_failed";
    private static final String NATIVE_AD_REQUESTED = "native_ad_requested";
    private static final String NATIVE_AD_VIEWED = "native_ad_rendered";
    private static final String NEWSFEED_ADD_STATUS_CLICKED = "newsfeed_add_status_clicked";
    private static final String NEWSFEED_PAGE_EXIT = "newsfeed_page_exit";
    public static final String NON_PREMIUM_MEAL_GOAL_CARD_TYPE = "non_premium_meal_goal";
    public static final String PREMIUM_MEAL_GOAL_CARD_TYPE = "premium_meal_goal";
    private static final String USER_TAPPED_NEWSFEED_BLOG_ENTRY = "user_tapped_newsfeed_blog_entry";
    private final Lazy<AnalyticsService> analyticsService;
    private final Lazy<ConfigService> configService;
    private final Set<String> heroCardDisplayedEventSentForType = new HashSet();
    private final Set<String> newsFeedEntryViewedEventSentForId = new HashSet();
    private final Lazy<UserApplicationSettingsService> userApplicationSettingsService;

    public NewsFeedAnalyticsHelper(Lazy<AnalyticsService> lazy, Lazy<ConfigService> lazy2, Lazy<UserApplicationSettingsService> lazy3) {
        this.analyticsService = lazy;
        this.configService = lazy2;
        this.userApplicationSettingsService = lazy3;
    }

    public void reportHeroCardDisplayed(String str) {
        if (!this.heroCardDisplayedEventSentForType.contains(str) || Strings.equals(str, PREMIUM_MEAL_GOAL_CARD_TYPE) || Strings.equals(str, NON_PREMIUM_MEAL_GOAL_CARD_TYPE)) {
            this.heroCardDisplayedEventSentForType.add(str);
            ((AnalyticsService) this.analyticsService.get()).reportEvent(HERO_CARD_DISPLAYED, getHeroCardAttributesMap(str));
        }
    }

    public void reportHeroCardClosed(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(HERO_CARD_CLOSED, getHeroCardAttributesMap(str));
    }

    public void reportHeroCardTapped(String str, int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(HERO_CARD_TAPPED, getHeroCardAttributesMap(str, i));
    }

    public void reportResendEmailVerification() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.RESEND_EMAIL_CONFIRM);
    }

    public void reportLikeClicked(String str, boolean z) {
        reportLikeClicked(str, z, null);
    }

    public void reportLikeClicked(String str, boolean z, String str2) {
        Map build = new Builder().put("item_type", str).put("card_type", str).put("has_photo", entryHasImage(str)).build();
        if (Strings.notEmpty(str2)) {
            build.put("newsfeed", str2);
        }
        ((AnalyticsService) this.analyticsService.get()).reportEvent(z ? Events.USER_LIKED_ITEM : Events.USER_UNLIKED_ITEM, build);
    }

    public void reportSingleBlogItemClicked(BlogEntry blogEntry, String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ACTIVITY_ENTRY_DETAIL_VIEWED, MapUtil.createMap(BLOG_ENTRY_NUMBER, Integer.toString(blogEntry.getBlogNumber()), BLOG_POST_URL, blogEntry.getPost().getLink().getWebUrl(), BLOG_CLICK_SOURCE, str, "card_type", DataTypes.BLOG_INDIVIDUAL_SUMMARY));
    }

    public void reportSingleVideoBlogItemClicked(@NonNull String str, int i, @Nullable String str2, @NonNull String str3) {
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str4 = ACTIVITY_ENTRY_DETAIL_VIEWED;
        String[] strArr = new String[12];
        strArr[0] = "flow_id";
        strArr[1] = str;
        strArr[2] = ATTRIBUTE_POSITION_IN_NEWSFEED;
        strArr[3] = Strings.toString(Integer.valueOf(i + 1));
        strArr[4] = ATTRIBUTE_VIDEO_TITLE;
        strArr[5] = Strings.toString(str2);
        strArr[6] = BLOG_CLICK_SOURCE;
        strArr[7] = str3;
        strArr[8] = "card_type";
        strArr[9] = DataTypes.VIDEO;
        strArr[10] = ANLT_ATTR_VIDEO_TYPE;
        strArr[11] = VideoUtil.isVideoAutoPlayOn(this.userApplicationSettingsService, this.configService) ? "auto-play" : "click-to-play";
        analyticsService2.reportEvent(str4, MapUtil.createMap(strArr));
    }

    public void reportMultiBlogItemClicked(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(USER_TAPPED_NEWSFEED_BLOG_ENTRY, new Builder().put(ENTRY_NUMBER, str).build());
    }

    public void reportNewsFeedEntryViewed(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, @Nullable String str, int i) {
        String id = mfpNewsFeedActivityEntry.getId();
        if (!this.newsFeedEntryViewedEventSentForId.contains(id)) {
            this.newsFeedEntryViewedEventSentForId.add(id);
            Builder put = new Builder().put("newsfeed", "newsfeed").put("card_type", mfpNewsFeedActivityEntry.getType()).put("flow_id", Strings.toString(str)).put("has_photo", entryHasImage(mfpNewsFeedActivityEntry.getType())).put(ATTRIBUTE_POSITION_IN_NEWSFEED, Integer.toString(i + 1));
            if (Strings.equals(mfpNewsFeedActivityEntry.getType(), DataTypes.VIDEO)) {
                VideoEntry videoEntry = (VideoEntry) mfpNewsFeedActivityEntry.getEntryData();
                put.put(ANLT_ATTR_VIDEO_TYPE, VideoUtil.isVideoAutoPlayOn(this.userApplicationSettingsService, this.configService) ? "auto-play" : "click-to-play");
                if (videoEntry != null) {
                    put.put(ATTRIBUTE_VIDEO_TITLE, videoEntry.getTitle());
                }
            }
            Map build = put.build();
            appendBlogEntryAttributes(build, mfpNewsFeedActivityEntry, i);
            ((AnalyticsService) this.analyticsService.get()).reportEvent(ACTIVITY_ENTRY_VIEWED, build);
        }
    }

    private String entryHasImage(String str) {
        return String.valueOf(Strings.equals(str, "image_status_update") || Strings.equals(str, DataTypes.MEAL_STATUS_UPDATE));
    }

    public Set<String> getHeroCardDisplayedEventSentForType() {
        return new HashSet(this.heroCardDisplayedEventSentForType);
    }

    public Set<String> getNewsFeedEntryViewedEventSentForId() {
        return new HashSet(this.newsFeedEntryViewedEventSentForId);
    }

    public void addHeroCardDisplayedEventSentForTypes(List<String> list) {
        if (list != null) {
            this.heroCardDisplayedEventSentForType.addAll(list);
        }
    }

    public void addNewsFeedEntryViewedEventSentForId(List<String> list) {
        if (list != null) {
            this.newsFeedEntryViewedEventSentForId.addAll(list);
        }
    }

    public void reportActivityEntryDetailViewed(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ACTIVITY_ENTRY_DETAIL_VIEWED, MapUtil.createMap("card_type", str));
    }

    public void reportNewsfeedPageExit(@NonNull String str, long j) {
        double nanoTime = ((double) (System.nanoTime() - j)) / NANOS_PER_MILSECOND;
        ((AnalyticsService) this.analyticsService.get()).reportEvent(NEWSFEED_PAGE_EXIT, MapUtil.createMap("flow_id", str, "duration", Strings.toString(Double.valueOf(nanoTime))));
    }

    private void appendBlogEntryAttributes(Map<String, String> map, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, int i) {
        if (mfpNewsFeedActivityEntry != null && ConfigUtils.showBlogsV2((ConfigService) this.configService.get())) {
            MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
            if (entryData instanceof BlogEntry) {
                BlogEntry blogEntry = (BlogEntry) entryData;
                map.put(BLOG_ENTRY_NUMBER, Integer.toString(blogEntry.getBlogNumber()));
                map.put(BLOG_POST_URL, blogEntry.getPost().getLink().getWebUrl());
                map.put(INDEX_IN_ADAPTER, Integer.toString(i));
            }
        }
    }

    private Map<String, String> getHeroCardAttributesMap(String str) {
        return getHeroCardAttributesMap(str, -1);
    }

    private Map<String, String> getHeroCardAttributesMap(String str, int i) {
        HashMap hashMap = new HashMap();
        if (Strings.notEmpty(str)) {
            hashMap.put("card_type", str);
        }
        if (i >= 0) {
            hashMap.put("index", Integer.toString(i));
        }
        return hashMap;
    }

    public void reportCommentsButtonPressed(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(COMMENTS_BUTTON_PRESSED, MapUtil.createMap("card_type", String.valueOf(str), "has_photo", entryHasImage(str)));
    }

    public void reportAddCommentsPressed(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ADD_COMMENTS_PRESSED, MapUtil.createMap("card_type", String.valueOf(str), "has_photo", entryHasImage(str)));
    }

    public void reportActivityCommentAdded(String str, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ACTIVITY_COMMENT_ADDED, MapUtil.createMap("card_type", String.valueOf(str), "has_photo", entryHasImage(str), CONTEXT, str2));
    }

    public void reportNewsfeedAddStatusClicked() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(NEWSFEED_ADD_STATUS_CLICKED);
    }

    public void reportNativeAdRequested(NewsFeedNativeAdState newsFeedNativeAdState, String str, @NonNull String str2) {
        Map nativeAdAttributesMap = getNativeAdAttributesMap(newsFeedNativeAdState, str);
        nativeAdAttributesMap.put("position", str2);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(NATIVE_AD_REQUESTED, nativeAdAttributesMap);
    }

    public void reportNativeAdClicked(NewsFeedNativeAdState newsFeedNativeAdState, String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(NATIVE_AD_CLICKED, getNativeAdAttributesMap(newsFeedNativeAdState, str));
    }

    public void reportNativeAdViewed(NewsFeedNativeAdState newsFeedNativeAdState, String str, long j) {
        Map nativeAdAttributesMap = getNativeAdAttributesMap(newsFeedNativeAdState, str);
        nativeAdAttributesMap.put(AD_TIME_TO_SERVE, Strings.toString(Long.valueOf(j)));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(NATIVE_AD_VIEWED, nativeAdAttributesMap);
    }

    public void reportNativeAdFailed(NewsFeedNativeAdState newsFeedNativeAdState, String str, long j) {
        Map nativeAdAttributesMap = getNativeAdAttributesMap(newsFeedNativeAdState, str);
        nativeAdAttributesMap.put(AD_TIME_TO_FAIL, Strings.toString(Long.valueOf(j)));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(NATIVE_AD_FAILED, nativeAdAttributesMap);
    }

    private Map<String, String> getNativeAdAttributesMap(NewsFeedNativeAdState newsFeedNativeAdState, String str) {
        Map<String, String> createMap = MapUtil.createMap("ad_type", newsFeedNativeAdState.getStateName());
        createMap.put(AD_SOURCE, str);
        return createMap;
    }
}
