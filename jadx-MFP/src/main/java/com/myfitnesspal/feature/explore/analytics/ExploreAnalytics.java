package com.myfitnesspal.feature.explore.analytics;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class ExploreAnalytics {
    private static final String ATTR_ACTION = "action";
    private static final String ATTR_BLOG_CATEGORY = "blog_category";
    private static final String ATTR_BLOG_POST_URL = "blog_post_url";
    private static final String ATTR_CARD_TYPE = "card_type";
    private static final String ATTR_CATEGORY = "category";
    private static final String ATTR_CATEGORY_NAME = "category_name";
    private static final String ATTR_CHALLENGE_ID = "challenge_id";
    private static final String ATTR_CHALLENGE_NAME = "challenge_name";
    private static final String ATTR_DESTINATION = "destination";
    private static final String ATTR_MEAL_ID = "meal_id";
    private static final String EVENT_BLOG_ARTICLE_TAPPED = "explore_blog_article_tapped";
    private static final String EVENT_BLOG_CATEGORY_TAPPED = "explore_blog_category_tapped";
    private static final String EVENT_CARD_ACTION_TAPPED = "explore_card_action_tapped";
    private static final String EVENT_CARD_TAPPED = "explore_card_tapped";
    private static final String EVENT_CARD_VIEWED = "explore_card_viewed";
    private static final String EVENT_COMMUNITY_TAPPED = "explore_community_tapped";
    private static final String EVENT_MEAL_CATEGORY_TAPPED = "explore_meal_category_tapped";
    private static final String EVENT_MEAL_TAPPED = "explore_meal_tapped";
    private static final String EVENT_NEW_CHALLENGE_TAPPED = "explore_new_challenge_tapped";
    private static final String EVENT_SHOP_CATEGORY_TAPPED = "explore_shop_category_tapped";
    private static String UNKNOWN = "unknown";
    @Inject
    AnalyticsService analytics;

    public interface Action {
        public static final String VIEW_CHALLENGES = "view_challenges";
        public static final String VIEW_COLLECTIONS = "view_collections";
        public static final String VIEW_MEALS = "view_meals";
        public static final String VIEW_VENUES = "view_restaurants";
    }

    public interface BlogCategory {
        public static final String EAT = "eat";
        public static final String EXERCISE = "exercise";
        public static final String INSPIRATION = "inspiration";
        public static final String NUTRITION = "nutrition";
        public static final String VIDEOS = "videos";
    }

    public interface CardType {
        public static final String BLOG = "blog";
        public static final String CHALLENGES = "challenges";
        public static final String COMMUNITY = "community";
        public static final String DISCOVER_MEALS = "meal_browse_categories";
        public static final String MEAL_COLLECTIONS = "meal_collections";
        public static final String MEAL_IDEAS = "meal_browse_feed";
        public static final String NEARBY_VENUES = "rl";
        public static final String SHOP = "shop";
    }

    public interface CommunityType {
        public static final String FACEBOOK = "fb";
        public static final String FORUM = "forum";
        public static final String INSTAGRAM = "instagram";
        public static final String PINTEREST = "pinterest";
    }

    public interface ShopCategory {
        public static final String BOYS = "boys";
        public static final String GIRLS = "girls";
        public static final String MEN = "men";
        public static final String NEW_ARRIVALS = "new_arrivals";
        public static final String OUTLET = "outlet";
        public static final String SHOES = "shoes";
        public static final String TECHNOLOGY = "technology";
        public static final String WOMEN = "women";
    }

    public ExploreAnalytics() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void reportCardTapped(String str) {
        this.analytics.reportEvent(EVENT_CARD_TAPPED, MapUtil.createMap("card_type", str));
    }

    public void reportChallengeTapped(String str, String str2) {
        this.analytics.reportEvent(EVENT_NEW_CHALLENGE_TAPPED, MapUtil.createMap("challenge_name", str, "challenge_id", str2));
    }

    public void reportCardViewed(String str) {
        this.analytics.reportEvent(EVENT_CARD_VIEWED, MapUtil.createMap("card_type", str));
    }

    public void reportCardActionTapped(String str, String str2) {
        this.analytics.reportEvent(EVENT_CARD_ACTION_TAPPED, MapUtil.createMap("card_type", str, "action", str2));
    }

    public void reportMealTapped(String str) {
        this.analytics.reportEvent(EVENT_MEAL_TAPPED, MapUtil.createMap("meal_id", str));
    }

    public void reportMealCategoryTapped(String str) {
        this.analytics.reportEvent(EVENT_MEAL_CATEGORY_TAPPED, MapUtil.createMap("category", str));
    }

    public void reportBlogArticleTapped(String str) {
        this.analytics.reportEvent(EVENT_BLOG_ARTICLE_TAPPED, MapUtil.createMap(ATTR_BLOG_POST_URL, str));
    }

    public void reportBlogCategoryTapped(String str) {
        if (Strings.isEmpty(str)) {
            str = UNKNOWN;
        }
        this.analytics.reportEvent(EVENT_BLOG_CATEGORY_TAPPED, MapUtil.createMap(ATTR_BLOG_CATEGORY, str));
    }

    public void reportShopCategoryTapped(String str) {
        if (Strings.isEmpty(str)) {
            str = UNKNOWN;
        }
        this.analytics.reportEvent(EVENT_SHOP_CATEGORY_TAPPED, MapUtil.createMap(ATTR_CATEGORY_NAME, str));
    }

    public void reportCommunityTapped(String str) {
        if (Strings.isEmpty(str)) {
            str = UNKNOWN;
        }
        this.analytics.reportEvent(EVENT_COMMUNITY_TAPPED, MapUtil.createMap("destination", str));
    }
}
