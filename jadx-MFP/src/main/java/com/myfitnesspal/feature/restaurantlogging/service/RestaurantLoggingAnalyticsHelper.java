package com.myfitnesspal.feature.restaurantlogging.service;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myfitnesspal.feature.restaurantlogging.model.MenuItemEditorBundleData;
import com.myfitnesspal.feature.restaurantlogging.model.MfpLocation;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenu;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatch;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatchData;
import com.myfitnesspal.feature.restaurantlogging.model.RequestMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.uacf.core.util.CollectionUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantLoggingAnalyticsHelper {
    public static final String FLOW_END = "flow_end";
    public static final String FLOW_START = "flow_start";
    public static final String RESTAURANT_LOGGING_CHOOSE_ALTERNATIVE_SCREEN = "restaurant_logging_choose_alternative_screen";
    public static final String RESTAURANT_LOGGING_ENTRY_POINT = "restaurant_logging_entry_point";
    public static final String RESTAURANT_LOGGING_FLOW_TYPE = "restaurant_logging";
    public static final String RESTAURANT_LOGGING_MENU_ITEM_CHANGE_NUMBER_OF_SERVINGS = "restaurant_logging_menu_item_change_number_of_servings";
    public static final String RESTAURANT_LOGGING_MENU_ITEM_CHOOSE_ALTERNATIVE = "restaurant_logging_menu_item_choose_alternative";
    public static final String RESTAURANT_LOGGING_MENU_ITEM_DETAIL_VIEW = "restaurant_logging_menu_item_detail_view";
    public static final String RESTAURANT_LOGGING_MENU_ITEM_LOGGED = "restaurant_logging_menu_item_logged";
    public static final String RESTAURANT_LOGGING_MENU_SEARCH_BUTTON_TAPPED = "restaurant_logging_menu_search_button_tapped";
    public static final String RESTAURANT_LOGGING_MENU_SEARCH_RESULT_SELECTED = "restaurant_logging_menu_search_result_selected";
    public static final String RESTAURANT_LOGGING_MENU_VIEW = "restaurant_logging_menu_view";
    public static final String RESTAURANT_LOGGING_VENUE_LIST = "restaurant_logging_venue_list";
    public static final String RESTAURANT_LOGGING_VENUE_SEARCH_BUTTON_TAPPED = "restaurant_logging_venue_search_button_tapped";
    public static final String RESTAURANT_LOGGING_VENUE_SEARCH_PERFORMED = "restaurant_logging_venue_search_performed";
    public static final String RL_ALTERNATIVE_MATCH_SELECTED = "rl_alternative_match_selected";
    public static final String RL_ALTERNATIVE_SEARCH = "rl_alternative_search";
    public static final String RL_FOOD_LOGGED = "rl_food_logged";
    public static final String RL_MENU_ACCESSED = "rl_menu_accessed";
    public static final String RL_MENU_ITEM_ACCESSED = "rl_menu_item_accessed";
    public static final String RL_MENU_ITEM_SEARCH_START = "rl_menu_item_search_start";
    public static final String RL_MENU_ITEM_VIEWED_DETAIL = "rl_menu_item_viewed_detail";
    public static final String RL_USER_REQUESTED_MENU = "rl_user_requested_menu";
    public static final String RL_VENUE_EXPLORE_WITHOUT_MENUS = "rl_venue_explore_without_menus";
    /* access modifiers changed from: private */
    public final Lazy<MfpAnalyticsService> kafkaAnalyticsService;
    /* access modifiers changed from: private */
    public final Lazy<AnalyticsService> multiAnalyticsService;

    private static class Builder {
        private static final String BASED_ON_OBJECT = "based_on_object";
        private static final double DEFAULT_DOUBLE_VALUE = Double.MIN_VALUE;
        private static final int DEFAULT_INT_VALUE = Integer.MIN_VALUE;
        private static final String FLOW_ID = "flow_id";
        private static final String FLOW_TYPE = "flow_type";
        private static final String ITEM_ID = "item_id";
        private static final String LATITUDE = "latitude";
        private static final String LOG_OBJECT = "log_object";
        private static final String LONGITUDE = "longitude";
        private static final String MEAL_NAME = "meal_name";
        private static final String MENU_ID = "menu_id";
        private static final String MENU_ITEM_COUNT = "menu_item_count";
        private static final String MENU_ITEM_NAME = "menu_item_name";
        private static final String MENU_NAME = "menu_name";
        private static final String MENU_SECTION_COUNT = "menu_section_count";
        private static final String NOTIFICATION_PREFERENCE = "notificationPreference";
        private static final String NUMBER_OF_MENUS = "number_of_menus";
        private static final String POSITION = "position";
        private static final String SEARCH_TERM = "search_term";
        private static final String SECTION_ID = "section_id";
        private static final String SECTION_NAME = "section_name";
        private static final String SELECTED_OBJECT = "selected_object";
        private static final String SOURCE = "source";
        private static final String TYPE = "type";
        private static final String VENUES_WITHOUT_MENU_COUNT = "venues_without_menu_count";
        private static final String VENUES_WITH_MENU_COUNT = "venues_with_menu_count";
        private static final String VENUE_ID = "venue_id";
        private static final String VENUE_NAME = "venue_name";
        private static final String VENUE_VERIFIED = "venue_verified";
        private final Map<String, String> attributesMap;
        private RestaurantLoggingAnalyticsFoodObject basedOnObject;
        private EventType eventType;
        private String flowId;
        private String flowType;
        private final RestaurantLoggingAnalyticsHelper helper;
        private Boolean isVenueVerified;
        private String itemId;
        private double latitude = DEFAULT_DOUBLE_VALUE;
        private RestaurantLoggingAnalyticsFoodObject logObject;
        private double longitude = DEFAULT_DOUBLE_VALUE;
        private ApiJsonMapper mapper;
        private String mealName;
        private String menuId;
        private int menuItemCount = Integer.MIN_VALUE;
        private String menuItemName;
        private String menuName;
        private int menuSectionCount = Integer.MIN_VALUE;
        private String notificationPreference;
        private int numberOfMenus = Integer.MIN_VALUE;
        private int searchMatchSelectedIndex = Integer.MIN_VALUE;
        private String searchTerm;
        private String sectionId;
        private String sectionName;
        private RestaurantLoggingAnalyticsFoodObject selectedObject;
        private String source;
        private String type;
        private String venueId;
        private String venueName;
        private int venueWithMenuCount = Integer.MIN_VALUE;
        private int venueWithoutMenuCount = Integer.MIN_VALUE;

        public Builder(RestaurantLoggingAnalyticsHelper restaurantLoggingAnalyticsHelper) {
            this.helper = restaurantLoggingAnalyticsHelper;
            this.attributesMap = new HashMap();
            this.eventType = EventType.All;
        }

        public Builder setBasedOnObject(String str, MfpMenuItemMatchData mfpMenuItemMatchData) {
            this.basedOnObject = RestaurantLoggingAnalyticsFoodObject.newInstance(str, mfpMenuItemMatchData);
            return this;
        }

        public Builder setLogObject(String str, MfpMenuItemMatchData mfpMenuItemMatchData) {
            this.logObject = RestaurantLoggingAnalyticsFoodObject.newInstance(str, mfpMenuItemMatchData);
            return this;
        }

        public Builder setSelectedObject(String str, MfpMenuItemMatchData mfpMenuItemMatchData) {
            this.selectedObject = RestaurantLoggingAnalyticsFoodObject.newInstance(str, mfpMenuItemMatchData);
            return this;
        }

        public Builder setFlowId(String str) {
            this.flowId = str;
            return this;
        }

        public Builder setFlowType(String str) {
            this.flowType = str;
            return this;
        }

        public Builder setItemId(String str) {
            this.itemId = str;
            return this;
        }

        public Builder setMealName(String str) {
            this.mealName = str;
            return this;
        }

        public Builder setMenuId(String str) {
            this.menuId = str;
            return this;
        }

        public Builder setMenuItemName(String str) {
            this.menuItemName = str;
            return this;
        }

        public Builder setMenuName(String str) {
            this.menuName = str;
            return this;
        }

        public Builder setNotificationPreference(String str) {
            this.notificationPreference = str;
            return this;
        }

        public Builder setSearchTerm(String str) {
            this.searchTerm = str;
            return this;
        }

        public Builder setSectionId(String str) {
            this.sectionId = str;
            return this;
        }

        public Builder setSectionName(String str) {
            this.sectionName = str;
            return this;
        }

        public Builder setType(String str) {
            this.type = str;
            return this;
        }

        public Builder setVenueId(String str) {
            this.venueId = str;
            return this;
        }

        public Builder setVenueName(String str) {
            this.venueName = str;
            return this;
        }

        public Builder setSource(String str) {
            this.source = str;
            return this;
        }

        public Builder setMenuItemCount(int i) {
            this.menuItemCount = i;
            return this;
        }

        public Builder setMenuSectionCount(int i) {
            this.menuSectionCount = i;
            return this;
        }

        public Builder setNumberOfMenus(int i) {
            this.numberOfMenus = i;
            return this;
        }

        public Builder setVenueWithMenuCount(int i) {
            this.venueWithMenuCount = i;
            return this;
        }

        public Builder setVenueWithoutMenuCount(int i) {
            this.venueWithoutMenuCount = i;
            return this;
        }

        public Builder setLatitude(double d) {
            this.latitude = d;
            return this;
        }

        public Builder setLongitude(double d) {
            this.longitude = d;
            return this;
        }

        public Builder setIsVenueVerified(Boolean bool) {
            this.isVenueVerified = bool;
            return this;
        }

        public Builder setSearchMatchSelectedIndex(int i) {
            this.searchMatchSelectedIndex = i;
            return this;
        }

        public Builder setEventType(EventType eventType2) {
            this.eventType = eventType2;
            return this;
        }

        public void reportEvent(String str) {
            AnalyticsService analyticsService;
            if (this.eventType != null) {
                setupAttributes();
                switch (this.eventType) {
                    case All:
                        analyticsService = (AnalyticsService) this.helper.multiAnalyticsService.get();
                        break;
                    case Kafka:
                        analyticsService = (AnalyticsService) this.helper.kafkaAnalyticsService.get();
                        break;
                    default:
                        throw new IllegalStateException("Unsupported event type");
                }
                analyticsService.reportEvent(str, this.attributesMap);
                return;
            }
            throw new IllegalStateException("You need to set an event type to report an event");
        }

        private void setupAttributes() {
            addObjectParamIfValid(BASED_ON_OBJECT, this.basedOnObject);
            addObjectParamIfValid(LOG_OBJECT, this.logObject);
            addObjectParamIfValid(SELECTED_OBJECT, this.selectedObject);
            addStringParamIfValid("flow_id", this.flowId);
            addStringParamIfValid(FLOW_TYPE, this.flowType);
            addStringParamIfValid("item_id", this.itemId);
            addStringParamIfValid("meal_name", this.mealName);
            addStringParamIfValid(MENU_ID, this.menuId);
            addStringParamIfValid(MENU_ITEM_NAME, this.menuItemName);
            addStringParamIfValid(MENU_NAME, this.menuName);
            addStringParamIfValid(NOTIFICATION_PREFERENCE, this.notificationPreference);
            addStringParamIfValid("search_term", this.searchTerm);
            addStringParamIfValid(SECTION_ID, this.sectionId);
            addStringParamIfValid(SECTION_NAME, this.sectionName);
            addStringParamIfValid("source", this.source);
            addStringParamIfValid("type", this.type);
            addStringParamIfValid(VENUE_ID, this.venueId);
            addStringParamIfValid(VENUE_NAME, this.venueName);
            addIntParamIfValid(VENUES_WITH_MENU_COUNT, this.venueWithMenuCount);
            addIntParamIfValid(VENUES_WITHOUT_MENU_COUNT, this.venueWithoutMenuCount);
            addIntParamIfValid(MENU_ITEM_COUNT, this.menuItemCount);
            addIntParamIfValid(MENU_SECTION_COUNT, this.menuSectionCount);
            addIntParamIfValid(NUMBER_OF_MENUS, this.numberOfMenus);
            addIntParamIfValid("position", this.searchMatchSelectedIndex);
            addDoubleParamIfValid("latitude", this.latitude);
            addDoubleParamIfValid("longitude", this.longitude);
            addBooleanParamIfValid(VENUE_VERIFIED, this.isVenueVerified);
        }

        private void addObjectParamIfValid(String str, RestaurantLoggingAnalyticsFoodObject restaurantLoggingAnalyticsFoodObject) {
            if (restaurantLoggingAnalyticsFoodObject != null) {
                if (this.mapper == null) {
                    this.mapper = new ApiJsonMapper();
                }
                this.attributesMap.put(str, this.mapper.reverseMap((Object) restaurantLoggingAnalyticsFoodObject));
            }
        }

        private void addStringParamIfValid(String str, String str2) {
            if (str2 != null) {
                this.attributesMap.put(str, str2);
            }
        }

        private void addIntParamIfValid(String str, int i) {
            if (i != Integer.MIN_VALUE) {
                this.attributesMap.put(str, Integer.toString(i));
            }
        }

        private void addDoubleParamIfValid(String str, double d) {
            if (d != DEFAULT_DOUBLE_VALUE) {
                this.attributesMap.put(str, Double.toString(d));
            }
        }

        private void addBooleanParamIfValid(String str, Boolean bool) {
            if (bool != null) {
                this.attributesMap.put(str, Boolean.toString(bool.booleanValue()));
            }
        }
    }

    public enum EventType {
        All,
        Kafka
    }

    private static class RestaurantLoggingAnalyticsFoodObject {
        @SerializedName("item")
        @Expose
        private final JsonElement itemJsonElement;
        @Expose
        private final String type;

        private RestaurantLoggingAnalyticsFoodObject(String str, MfpMenuItemMatchData mfpMenuItemMatchData) {
            this.type = str;
            this.itemJsonElement = getJsonElementFromObject(mfpMenuItemMatchData);
        }

        public static RestaurantLoggingAnalyticsFoodObject newInstance(String str, MfpMenuItemMatchData mfpMenuItemMatchData) {
            return new RestaurantLoggingAnalyticsFoodObject(str, mfpMenuItemMatchData);
        }

        private JsonElement getJsonElementFromObject(MfpMenuItemMatchData mfpMenuItemMatchData) {
            ApiJsonMapper apiJsonMapper = new ApiJsonMapper();
            String reverseMap = apiJsonMapper.reverseMap((Object) mfpMenuItemMatchData);
            apiJsonMapper.withType(JsonElement.class);
            return (JsonElement) apiJsonMapper.tryMapFrom(reverseMap);
        }

        public String getType() {
            return this.type;
        }
    }

    public RestaurantLoggingAnalyticsHelper(Lazy<AnalyticsService> lazy, Lazy<MfpAnalyticsService> lazy2) {
        this.multiAnalyticsService = lazy;
        this.kafkaAnalyticsService = lazy2;
    }

    public void reportEntryPoint(String str) {
        new Builder(this).setMealName(str).reportEvent(RESTAURANT_LOGGING_ENTRY_POINT);
    }

    public void reportAlternativeScreenLoaded(MenuItemEditorBundleData menuItemEditorBundleData) {
        getBuilderWithNameAttributesFilledIn(menuItemEditorBundleData, menuItemEditorBundleData.getMenuItem()).reportEvent(RESTAURANT_LOGGING_CHOOSE_ALTERNATIVE_SCREEN);
    }

    public void reportMenuItemClicked(String str, String str2, String str3) {
        getBuilderWithNameAttributesFilledIn(str, str2, str3, null).reportEvent(RESTAURANT_LOGGING_MENU_SEARCH_RESULT_SELECTED);
    }

    public void reportMenuItemViewed(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem) {
        getBuilderWithNameAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).reportEvent(RESTAURANT_LOGGING_MENU_ITEM_DETAIL_VIEW);
    }

    public void reportRequestedMenu(RequestMenuItem requestMenuItem, Venue venue) {
        new Builder(this).setVenueName(venue.getName()).setVenueId(venue.getId()).setNotificationPreference(requestMenuItem.getKey()).reportEvent(RL_USER_REQUESTED_MENU);
    }

    public void reportServingsChange(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem) {
        getBuilderWithNameAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).reportEvent(RESTAURANT_LOGGING_MENU_ITEM_CHANGE_NUMBER_OF_SERVINGS);
    }

    public void reportChooseAlternateMatch(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem) {
        getBuilderWithNameAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).reportEvent(RESTAURANT_LOGGING_MENU_ITEM_CHOOSE_ALTERNATIVE);
    }

    public void reportVenueSearchClicked(String str) {
        getBuilderWithNameAttributesFilledIn(str).reportEvent(RESTAURANT_LOGGING_VENUE_SEARCH_BUTTON_TAPPED);
    }

    public void reportVenueSearchPerformed(String str) {
        getBuilderWithNameAttributesFilledIn(str).reportEvent(RESTAURANT_LOGGING_VENUE_SEARCH_PERFORMED);
    }

    public void reportVenueList(List<Venue> list, String str) {
        int i = 0;
        int i2 = 0;
        for (Venue hasMenu : list) {
            if (hasMenu.hasMenu()) {
                i++;
            } else {
                i2++;
            }
        }
        getBuilderWithNameAttributesFilledIn(str).setVenueWithMenuCount(i).setVenueWithoutMenuCount(i2).reportEvent(RESTAURANT_LOGGING_VENUE_LIST);
    }

    public void reportItemLogged(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem, MfpMenuItemMatchData mfpMenuItemMatchData, MfpMenuItemMatch mfpMenuItemMatch) {
        reportItemLogged(menuItemEditorBundleData, mfpMenuItem);
        reportItemLoggedDES(menuItemEditorBundleData, mfpMenuItem, mfpMenuItemMatchData, mfpMenuItemMatch);
    }

    private void reportItemLogged(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem) {
        getBuilderWithNameAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).reportEvent(RESTAURANT_LOGGING_MENU_ITEM_LOGGED);
    }

    public void reportSearchClicked(Venue venue, String str, String str2) {
        reportSearchClicked(venue.getName(), str2);
        reportSearchClickedDES(venue, str);
    }

    private void reportSearchClicked(String str, String str2) {
        getBuilderWithNameAttributesFilledIn(str, str2).reportEvent(RESTAURANT_LOGGING_MENU_SEARCH_BUTTON_TAPPED);
    }

    public void reportMenuViewed(MfpMenu mfpMenu, Venue venue, String str, String str2, String str3, int i, int i2, int i3) {
        reportMenuViewed(venue.getName(), str, mfpMenu.getName(), str3, i, i2, i3);
        reportMenuViewedDES(mfpMenu, venue, str, str2, str3);
    }

    private void reportMenuViewed(String str, String str2, String str3, String str4, int i, int i2, int i3) {
        getBuilderWithNameAttributesFilledIn(str, str2, str3).setSource(str4).setMenuItemCount(i2).setMenuSectionCount(i3).setNumberOfMenus(i).reportEvent(RESTAURANT_LOGGING_MENU_VIEW);
    }

    private void reportMenuViewedDES(MfpMenu mfpMenu, Venue venue, String str, String str2, String str3) {
        getBuilderWithNameAttributesFilledIn(venue.getName(), str, mfpMenu.getName()).setFlowId(str2).setVenueId(venue.getId()).setSource(str3).setIsVenueVerified(Boolean.valueOf(venue.isVerified())).setMenuId(mfpMenu.getId()).setEventType(EventType.Kafka).reportEvent(RL_MENU_ACCESSED);
    }

    private void reportItemLoggedDES(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem, MfpMenuItemMatchData mfpMenuItemMatchData, MfpMenuItemMatch mfpMenuItemMatch) {
        String type = mfpMenuItemMatch.getType();
        getBuilderWithMenuItemDESAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).setEventType(EventType.All).setBasedOnObject(type, mfpMenuItemMatch.getBasedOnMatchData()).setLogObject(type, mfpMenuItemMatchData).reportEvent(RL_FOOD_LOGGED);
    }

    private void reportSearchClickedDES(Venue venue, String str) {
        getBuilderWithVenueDESAttributesFilledIn(venue, str).reportEvent(RL_MENU_ITEM_SEARCH_START);
    }

    public void reportNoMenuDES(Venue venue, String str) {
        MfpLocation location = venue.getLocation();
        getBuilderWithVenueDESAttributesFilledIn(venue, str).setLatitude(location.getLatitude()).setLongitude(location.getLongitude()).reportEvent(RL_VENUE_EXPLORE_WITHOUT_MENUS);
    }

    public void reportAlternativeSearchDES(MenuItemEditorBundleData menuItemEditorBundleData, String str) {
        MfpMenuItem menuItem = menuItemEditorBundleData.getMenuItem();
        List matches = menuItem.getMatches();
        MfpMenuItemMatch mfpMenuItemMatch = CollectionUtils.notEmpty((Collection<?>) matches) ? (MfpMenuItemMatch) matches.get(0) : null;
        Builder searchTerm = getBuilderWithMenuItemDESAttributesFilledIn(menuItemEditorBundleData, menuItem).setSearchTerm(str);
        if (mfpMenuItemMatch != null) {
            searchTerm.setBasedOnObject(mfpMenuItemMatch.getType(), mfpMenuItemMatch.getBasedOnMatchData());
        }
        searchTerm.reportEvent(RL_ALTERNATIVE_SEARCH);
    }

    public void reportFlowStartDES(String str) {
        getBuilderForReportingFlow(str).setFlowType("restaurant_logging").reportEvent(FLOW_START);
    }

    public void reportFlowEndDES(String str) {
        getBuilderForReportingFlow(str).reportEvent(FLOW_END);
    }

    public void reportMenuItemViewedDES(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem, MfpMenuItemMatch mfpMenuItemMatch) {
        reportMenuItemAccessedDES(menuItemEditorBundleData, mfpMenuItem);
        reportMenuItemViewedDetailDES(menuItemEditorBundleData, mfpMenuItem, mfpMenuItemMatch);
    }

    private void reportMenuItemAccessedDES(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem) {
        getBuilderWithMenuItemDESAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).reportEvent(RL_MENU_ITEM_ACCESSED);
    }

    private void reportMenuItemViewedDetailDES(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem, MfpMenuItemMatch mfpMenuItemMatch) {
        getBuilderWithMenuItemDESAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).setBasedOnObject(mfpMenuItemMatch.getType(), mfpMenuItemMatch.getBasedOnMatchData()).reportEvent(RL_MENU_ITEM_VIEWED_DETAIL);
    }

    public void reportAlternateMatchSelectedDES(MfpMenuItem mfpMenuItem, MfpMenuItemMatch mfpMenuItemMatch, MfpMenuItemMatch mfpMenuItemMatch2, MenuItemEditorBundleData menuItemEditorBundleData, String str, int i, String str2) {
        Builder type = getBuilderWithMenuItemDESAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).setSearchTerm(str).setSearchMatchSelectedIndex(i).setType(str2);
        if (mfpMenuItemMatch2 != null) {
            type.setBasedOnObject(mfpMenuItemMatch2.getType(), mfpMenuItemMatch2.getBasedOnMatchData());
        }
        if (mfpMenuItemMatch != null) {
            type.setSelectedObject(mfpMenuItemMatch.getType(), mfpMenuItemMatch.getBasedOnMatchData());
        }
        type.reportEvent(RL_ALTERNATIVE_MATCH_SELECTED);
    }

    private Builder getBuilderForReportingFlow(String str) {
        return new Builder(this).setFlowId(str).setEventType(EventType.Kafka);
    }

    private Builder getBuilderWithNameAttributesFilledIn(String str) {
        return getBuilderWithNameAttributesFilledIn(null, str, null, null);
    }

    private Builder getBuilderWithNameAttributesFilledIn(String str, String str2) {
        return getBuilderWithNameAttributesFilledIn(str, str2, null, null);
    }

    private Builder getBuilderWithNameAttributesFilledIn(String str, String str2, String str3) {
        return getBuilderWithNameAttributesFilledIn(str, str2, str3, null);
    }

    private Builder getBuilderWithNameAttributesFilledIn(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem) {
        return new Builder(this).setVenueName(menuItemEditorBundleData.getVenueName()).setMealName(menuItemEditorBundleData.getMealName()).setMenuName(menuItemEditorBundleData.getMenuName()).setMenuItemName(mfpMenuItem.getName());
    }

    private Builder getBuilderWithNameAttributesFilledIn(String str, String str2, String str3, String str4) {
        return new Builder(this).setVenueName(str).setMealName(str2).setMenuName(str3).setMenuItemName(str4);
    }

    private Builder getBuilderWithMenuItemDESAttributesFilledIn(MenuItemEditorBundleData menuItemEditorBundleData, MfpMenuItem mfpMenuItem) {
        return getBuilderWithNameAttributesFilledIn(menuItemEditorBundleData, mfpMenuItem).setFlowId(menuItemEditorBundleData.getFlowId()).setVenueId(menuItemEditorBundleData.getVenueId()).setMenuId(mfpMenuItem.getMenuId()).setSectionId(mfpMenuItem.getSectionId()).setSectionName(menuItemEditorBundleData.getSectionName()).setItemId(mfpMenuItem.getId()).setIsVenueVerified(menuItemEditorBundleData.isVenueVerified()).setEventType(EventType.Kafka);
    }

    private Builder getBuilderWithVenueDESAttributesFilledIn(Venue venue, String str) {
        return getBuilderWithNameAttributesFilledIn(venue.getName(), (String) null).setFlowId(str).setVenueId(venue.getId()).setIsVenueVerified(Boolean.valueOf(venue.isVerified())).setEventType(EventType.Kafka);
    }
}
