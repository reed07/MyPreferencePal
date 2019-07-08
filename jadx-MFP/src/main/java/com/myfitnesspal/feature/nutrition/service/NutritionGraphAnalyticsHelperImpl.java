package com.myfitnesspal.feature.nutrition.service;

import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.constants.Constants.Goals.Nutrient;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NutritionGraphAnalyticsHelperImpl implements NutritionGraphAnalyticsHelper {
    private final Lazy<AnalyticsService> analyticsService;
    private final Map<String, Map<String, Integer>> eventAttributeCountMap = new HashMap();

    public static class Attributes {
        private static final String DAILY_FOOD_LIST = "daily_food_list";
        private static final String DAILY_FOOD_LIST_MACRO = "daily_food_list_%s";
        private static final String DAILY_TOTAL = "daily_total";
        private static final String WEEKLY_FOOD_LIST = "weekly_food_list";
        private static final String WEEKLY_FOOD_LIST_MACRO = "weekly_food_list_%s";
        private static final String WEEKLY_NET = "weekly_net";
        private static final String WEEKLY_TOTAL = "weekly_total";

        public static String getChartAttribute(boolean z, int i) {
            if (!z) {
                return DAILY_TOTAL;
            }
            return i == 2 ? WEEKLY_NET : WEEKLY_TOTAL;
        }

        public static String getFoodListAttribute(boolean z, int i) {
            if (i != -1) {
                String str = null;
                switch (i) {
                    case 0:
                        str = "carbs";
                        break;
                    case 1:
                        str = "fat";
                        break;
                    case 2:
                        str = "protein";
                        break;
                }
                return String.format(z ? WEEKLY_FOOD_LIST_MACRO : DAILY_FOOD_LIST_MACRO, new Object[]{str});
            }
            return z ? WEEKLY_FOOD_LIST : DAILY_FOOD_LIST;
        }

        public static String getChartAttribute(boolean z) {
            return getChartAttribute(z, 1);
        }
    }

    public static class EventId {
        public static final String GRAPH_CALORIES = "graph_calories";
        public static final String GRAPH_MACROS = "graph_macros";
        private static final String GRAPH_NUTRIENTS = "graph_nutrients_%s";
        private static final Map<Integer, String> GRAPH_NUTRIENTS_EVENT_ID_MAP = new HashMap(17);
        public static final String GRAPH_NUTRIENTS_SUMMARY = getNutrientGraphEventId(Challenges.CHALLENGE_TAB_SUMMARY);

        static {
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(0), "calories");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(9), Nutrient.CARBOHYDRATES);
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(12), "protein");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(1), "fat");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(2), "saturated_fat");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(4), Nutrient.MONOUNSATURATED_FAT);
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(5), "trans_fat");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(6), "cholesterol");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(7), "sodium");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(8), "potassium");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(11), "sugar");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(13), "vitamin_a");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(14), "vitamin_c");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(16), "iron");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(15), "calcium");
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(10), Nutrient.FIBER);
            GRAPH_NUTRIENTS_EVENT_ID_MAP.put(Integer.valueOf(3), Nutrient.POLYUNSATURATED_FAT);
        }

        private static String getNutrientGraphEventId(String str) {
            return String.format(GRAPH_NUTRIENTS, new Object[]{str});
        }

        public static String getNutrientGraphEventId(int i) {
            return getNutrientGraphEventId((String) GRAPH_NUTRIENTS_EVENT_ID_MAP.get(Integer.valueOf(i)));
        }
    }

    public NutritionGraphAnalyticsHelperImpl(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void incrementAttribute(String str, String str2) {
        if (Strings.isEmpty(str) || Strings.isEmpty(str2)) {
            throw new IllegalArgumentException("Event ID or attribute cannot be empty.");
        }
        Map map = (Map) this.eventAttributeCountMap.get(str);
        if (map == null) {
            map = new HashMap();
            this.eventAttributeCountMap.put(str, map);
        }
        Integer num = (Integer) map.get(str2);
        if (num == null) {
            num = Integer.valueOf(0);
        }
        map.put(str2, Integer.valueOf(num.intValue() + 1));
    }

    public void reportEvents() {
        for (Entry entry : this.eventAttributeCountMap.entrySet()) {
            String str = (String) entry.getKey();
            HashMap hashMap = new HashMap(CollectionUtils.size(this.eventAttributeCountMap));
            for (Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                hashMap.put(entry2.getKey(), Integer.toString(((Integer) entry2.getValue()).intValue()));
            }
            ((AnalyticsService) this.analyticsService.get()).reportEvent(str, (Map<String, String>) hashMap);
        }
        this.eventAttributeCountMap.clear();
    }
}
