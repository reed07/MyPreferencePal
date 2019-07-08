package com.myfitnesspal.feature.challenges.util;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;
import java.util.Map;

public class ChallengesAnalyticsHelper {
    public static final String ACHIEVEMENT_LIST_SOURCE = "achievement_list";
    public static final String LIST_JOINED = "joined_challenge";
    public static final String LIST_NEW = "new_challenge";
    public static final String LIST_PAST = "past_challenge";
    public static final String SCREEN_CONTINUE_CLICKED = "continue_clicked";
    public static final String SCREEN_DISMISSED = "dismissed";
    public static final String SCREEN_DONE_CLICKED = "done_clicked";
    public static final String SCREEN_INVITE_METHOD_SELECTED = "invite_method_selected";
    public static final String SCREEN_VIEWED = "viewed";
    public static final String SOURCE_CHALLENGES_HUB = "challenges_hub";
    public static final String SOURCE_MY_INFO = "my_info";
    public static final String SOURCE_OTHER = "other";
    private final String APOSTROPHE = "'";
    private Lazy<AnalyticsService> analyticsService;

    private interface Events {
        public static final String CHALLENGE_ACHIEVEMENT_SHARE = "screen_challenge_achievement_share";
        public static final String CHALLENGE_ACHIEVEMENT_VIEWS = "challenge_achievement_views";
        public static final String CHALLENGE_DETAILS_FRIENDS_TAB_FRIEND_SELECTED = "challenge_details_friends_tab_friend_selected";
        public static final String CHALLENGE_DETAILS_FRIENDS_TAB_INVITE_FRIENDS = "challenge_details_friends_tab_invite_friends";
        public static final String CHALLENGE_DETAILS_LEAVE_CHALLENGE = "challenge_details_leave_challenge";
        public static final String CHALLENGE_DETAILS_TAB_SELECTED = "challenge_details_tab_selected";
        public static final String CHALLENGE_INVITE_FRIENDS_SCREEN_INVITE_FRIEND = "challenge_invite_friends_screen_invite_friend";
        public static final String CHALLENGE_INVITE_FRIENDS_SCREEN_VIEWED = "challenge_invite_friends_screen_viewed";
        public static final String CHALLENGE_JOINED = "challenge_joined";
        public static final String CHALLENGE_JOIN_BUTTON_CLICKED = "challenge_join_button_clicked";
        public static final String CHALLENGE_JOIN_EMAIL_PREF_BACK_BUTTON = "challenge_join_email_preference_back_button";
        public static final String CHALLENGE_JOIN_EMAIL_PREF_SCREEN = "challenge_join_email_preference_screen";
        public static final String CHALLENGE_JOIN_INVITE_FRIENDS_SCREEN = "challenge_join_invite_friends_screen";
        public static final String CHALLENGE_SCREEN_SHARE_BUTTON_CLICKED = "challenge_screen_share_button_clicked";
        public static final String CHALLENGE_SCREEN_VIEWED = "challenge_screen_viewed";
        public static final String CHALLENGE_SELECTED = "challenge_selected";
        public static final String CHALLENGE_TAB_SELECTED = "challenge_tab_selected";
        public static final String CHALLENGE_VIEW_ALL_FRIENDS = "challenge_view_all_friends";
        public static final String SCREEN_CHALLENGES = "SCREEN_Challenges";
        public static final String SPONSOR_LINK_CLICKED = "sponsor_link_clicked";
        public static final String USER_PROFILE_VIEW_ACHIEVEMENTS = "user_profile_view_achievements";
    }

    private String getChallengeStatusForAnalytics(boolean z, boolean z2) {
        return z2 ? LIST_PAST : z ? "joined_challenge" : "new_challenge";
    }

    private String getDetailsUrlPresentForAnalytics(boolean z) {
        return z ? "yes" : "no";
    }

    public ChallengesAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportChallengeScreenAnalytics(int i, int i2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.SCREEN_CHALLENGES, MapUtil.createMap(Attributes.NEW_CHALLENGE_COUNT, Integer.toString(i), Attributes.ACTIVE_CHALLENGE_COUNT, Integer.toString(i2)));
    }

    public void reportChallengeScreenViewed(String str, String str2, String str3, boolean z, boolean z2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_SCREEN_VIEWED, MapUtil.createMap("source", str, "type", getChallengeStatusForAnalytics(z, z2), "challenge_name", sanitize(str2), "challenge_id", str3));
    }

    public void reportChallengeSelectedEvent(String str, String str2, String str3) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_SELECTED, MapUtil.createMap("type", str, "challenge_name", sanitize(str2), "challenge_id", str3));
    }

    public void reportTabSelectedEvent(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_TAB_SELECTED, MapUtil.createMap(Attributes.TAB_NAME, ChallengesUtil.getChallengesListTabName(i)));
    }

    public void reportDetailsTabSelectedEvent(String str, List<String> list, int i, boolean z, boolean z2, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_DETAILS_TAB_SELECTED, MapUtil.createMap("challenge_name", str, "status", getChallengeStatusForAnalytics(z, z2), Attributes.TAB_NAME, ChallengesUtil.getChallengeDetailsTabName(list, i, z), "challenge_id", str2));
    }

    public void reportChallengeJoinEvent(String str, String str2, List<String> list, int i, String str3, boolean z, boolean z2) {
        Map createMap = MapUtil.createMap("source", str, "challenge_name", sanitize(str2), Attributes.TAB_NAME, ChallengesUtil.getChallengeDetailsTabNameForUnjoined(list, i), "challenge_id", str3);
        if (z) {
            createMap.put(Attributes.SHARE_MY_EMAIL, ChallengesUtil.getEmailSharedString(z2));
        }
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_JOIN_BUTTON_CLICKED, createMap);
    }

    public void reportViewAllFriendsEvent(String str, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_VIEW_ALL_FRIENDS, MapUtil.createMap("challenge_name", sanitize(str), "challenge_id", str2));
    }

    public void reportJoinEmailPrefScreenEvent(String str, String str2, String str3) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_JOIN_EMAIL_PREF_SCREEN, MapUtil.createMap("event", str, "challenge_name", sanitize(str2), "challenge_id", str3));
    }

    public void reportJoinEmailPrefScreenEmailSharedEvent(String str, String str2, String str3, boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_JOIN_EMAIL_PREF_SCREEN, MapUtil.createMap("event", str, "challenge_name", sanitize(str2), "challenge_id", str3, Attributes.SHARE_MY_EMAIL, ChallengesUtil.getEmailSharedString(z)));
    }

    public void reportJoinInviteFriendsScreenEvent(String str, String str2, String str3) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_JOIN_INVITE_FRIENDS_SCREEN, MapUtil.createMap("challenge_name", sanitize(str), "challenge_id", str2, "event", str3));
    }

    public void reportJoinInviteFriendsScreenInvitationClickedEvent(String str, String str2, String str3, String str4) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_JOIN_INVITE_FRIENDS_SCREEN, MapUtil.createMap("challenge_name", sanitize(str), "challenge_id", str2, "event", str4, Attributes.INVITATION_METHOD, str3));
    }

    public void reportInviteFriendsScreenViewedEvent(String str, int i, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_INVITE_FRIENDS_SCREEN_VIEWED, MapUtil.createMap("challenge_name", sanitize(str), Attributes.NUMBER_OF_FRIENDS, Strings.toString(Integer.valueOf(i)), "challenge_id", str2));
    }

    public void reportInviteFriendsScreenInviteEvent(String str, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_INVITE_FRIENDS_SCREEN_INVITE_FRIEND, MapUtil.createMap("challenge_name", sanitize(str), "challenge_id", str2));
    }

    public void reportChallengeLeaveEvent(String str, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_DETAILS_LEAVE_CHALLENGE, MapUtil.createMap("challenge_name", sanitize(str), "challenge_id", str2));
    }

    public void reportDetailsFriendsTabInviteFriendsEvent(String str, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_DETAILS_FRIENDS_TAB_INVITE_FRIENDS, MapUtil.createMap("challenge_name", sanitize(str), "challenge_id", str2));
    }

    public void reportDetailsFriendsTabFriendSelectedEvent(String str, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_DETAILS_FRIENDS_TAB_FRIEND_SELECTED, MapUtil.createMap("challenge_name", sanitize(str), "challenge_id", str2));
    }

    public void reportUserProfileViewAchievementsEvent(boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.USER_PROFILE_VIEW_ACHIEVEMENTS, MapUtil.createMap("friend_profile", Strings.toString(Boolean.valueOf(z))));
    }

    public void reportChallengeAchievementViewsEvent(String str, String str2, String str3, String str4) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_ACHIEVEMENT_VIEWS, MapUtil.createMap("source", str, "challenge_name", str3, "challenge_id", str2, Attributes.ACHIEVEMENT_TITLE, str4));
    }

    public void reportChallengeAchievementShareClicked(String str, String str2, boolean z, String str3) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_ACHIEVEMENT_SHARE, MapUtil.createMap("challenge_name", str, Attributes.ACHIEVEMENT_TITLE, str2, Attributes.DETAILS_URL, getDetailsUrlPresentForAnalytics(z), "challenge_id", str3));
    }

    public void reportSponsorLinkClicked(String str, String str2, int i, String str3) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.SPONSOR_LINK_CLICKED, MapUtil.createMap("challenge_name", sanitize(str), Attributes.LINK_NAME, str3, Attributes.LINK_INDEX, Integer.toString(i), "challenge_id", str2));
    }

    public void reportChallengeJoined(String str, String str2, String str3, String str4, boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_JOINED, MapUtil.createMap("source", str4, "challenge_name", sanitize(str), Attributes.TAB_NAME, str3, "challenge_id", str2, Attributes.SHARE_MY_EMAIL, ChallengesUtil.getEmailSharedString(z)));
    }

    public void reportChallengeShared(String str, String str2, List<String> list, int i, boolean z, boolean z2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.CHALLENGE_SCREEN_SHARE_BUTTON_CLICKED, MapUtil.createMap("challenge_id", str, "type", getChallengeStatusForAnalytics(z, z2), "challenge_name", sanitize(str2), Attributes.TAB_NAME, ChallengesUtil.getChallengeDetailsTabName(list, i, z)));
    }

    private String sanitize(String str) {
        return (!Strings.notEmpty(str) || !str.contains("'")) ? str : str.replace("'", "");
    }
}
