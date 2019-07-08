package com.myfitnesspal.feature.profile.analytics;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import javax.inject.Inject;

public class MeAnalytics {
    private static final String ATTR_ACTION = "action";
    private static final String ATTR_APP_NAME = "app_name";
    private static final String ATTR_CARD_TYPE = "card_type";
    private static final String ATTR_CHALLENGE_ID = "challenge_id";
    private static final String ATTR_CHALLENGE_NAME = "challenge_name";
    private static final String ATTR_NUM_NEW_CHALLENGES = "num_new_challenges";
    private static final String ATTR_ROW_TYPE = "row_type";
    private static final String EVENT_ME_ACTION_CARD_TAPPED = "me_card_action_tapped";
    private static final String EVENT_ME_CARD_ACTIVE_CHALLENGE_TAPPED = "me_card_active_challenge_tapped";
    private static final String EVENT_ME_CARD_APPS_DEVICES_TAPPED = "me_card_apps_devices_tapped";
    private static final String EVENT_ME_CARD_FRIEND_TAPPED = "me_card_friend_tapped";
    private static final String EVENT_ME_CARD_NEW_CHALLENGES_TAPPED = "me_card_new_challenges_tapped";
    private static final String EVENT_ME_CARD_TAPPED = "me_card_tapped";
    private static final String EVENT_ME_CARD_VIEWED = "me_card_viewed";
    private static final String EVENT_MY_ITEM_ROW_CREATE_TAPPED = "my_item_row_create_tapped";
    private static final String EVENT_MY_ITEM_ROW_TAPPED = "my_item_row_tapped";
    @Inject
    AnalyticsService analytics;

    public interface Action {
        public static final String ADD_FRIEND = "add_friend";
        public static final String ADD_WEIGHT = "add_weight";
        public static final String CONNECT_APPS = "connect_apps_devices";
        public static final String UPDATE_GOALS = "update_goals";
        public static final String VIEW_CHALLENGES = "view_challenges";
    }

    public interface CardType {
        public static final String APPS_DEVICES = "apps_devices";
        public static final String CHALLENGES = "challenges";
        public static final String FRIENDS = "friends";
        public static final String GOALS = "goals";
        public static final String PROGRESS = "progress";
    }

    public interface RowType {
        public static final String CARDIO = "cardio";
        public static final String FOODS = "foods";
        public static final String MEALS = "meals";
        public static final String RECIPES = "recipes";
        public static final String STRENGTH = "strength";
    }

    public MeAnalytics() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void reportCardViewed(String str) {
        this.analytics.reportEvent(EVENT_ME_CARD_VIEWED, MapUtil.createMap("card_type", str));
    }

    public void reportCardTapped(String str) {
        this.analytics.reportEvent(EVENT_ME_CARD_TAPPED, MapUtil.createMap("card_type", str));
    }

    public void reportCardActionTapped(String str, String str2) {
        this.analytics.reportEvent(EVENT_ME_ACTION_CARD_TAPPED, MapUtil.createMap("card_type", str, "action", str2));
    }

    public void reportNewChallengesTapped(int i) {
        this.analytics.reportEvent(EVENT_ME_CARD_NEW_CHALLENGES_TAPPED, MapUtil.createMap(ATTR_NUM_NEW_CHALLENGES, String.valueOf(i)));
    }

    public void reportActiveChallengeTapped(String str, String str2) {
        this.analytics.reportEvent(EVENT_ME_CARD_ACTIVE_CHALLENGE_TAPPED, MapUtil.createMap("challenge_name", str, "challenge_id", str2));
    }

    public void reportFriendTapped() {
        this.analytics.reportEvent(EVENT_ME_CARD_FRIEND_TAPPED);
    }

    public void reportAppTapped(String str) {
        this.analytics.reportEvent(EVENT_ME_CARD_APPS_DEVICES_TAPPED, MapUtil.createMap("app_name", str));
    }

    public void reportItemRowTapped(String str) {
        this.analytics.reportEvent(EVENT_MY_ITEM_ROW_TAPPED, MapUtil.createMap(ATTR_ROW_TYPE, str));
    }

    public void reportItemRowCreateTapped(String str) {
        this.analytics.reportEvent(EVENT_MY_ITEM_ROW_CREATE_TAPPED, MapUtil.createMap(ATTR_ROW_TYPE, str));
    }
}
