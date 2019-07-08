package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.home.model.HeroCardLayoutBase;
import com.myfitnesspal.feature.home.model.HeroCardSingleButtonAndImageLayout;
import com.myfitnesspal.feature.home.model.HeroCardTwoButtonLayout;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import java.util.HashMap;
import java.util.Map;

public class MfpNewsFeedHeroCardEntry implements MfpNewsFeedActivityEntryData {
    private static final Map<Integer, Class<? extends HeroCardLayoutBase>> LAYOUT_ID_TO_CLASS_MAP = new HashMap();
    private ApiJsonMapper apiJsonMapper;
    private HeroCardLayoutBase cardLayout;
    @Expose
    private boolean dismissibleByUser = true;
    @Expose
    private int heroCardId;
    @Expose
    private String heroCardName;
    @Expose
    private Map<String, Object> layoutData;
    @Expose
    private int layoutId;

    private static final class LayoutIds {
        private static final int LAYOUT_DOUBLE_BUTTON = 2;
        private static final int LAYOUT_SINGLE_BUTTON_WITH_IMAGE = 1;

        private LayoutIds() {
        }
    }

    public String getText() {
        return null;
    }

    static {
        LAYOUT_ID_TO_CLASS_MAP.put(Integer.valueOf(1), HeroCardSingleButtonAndImageLayout.class);
        LAYOUT_ID_TO_CLASS_MAP.put(Integer.valueOf(2), HeroCardTwoButtonLayout.class);
    }

    public int getHeroCardId() {
        return this.heroCardId;
    }

    public String getAnalyticsTag() {
        return this.heroCardName;
    }

    public String getHeroCardName() {
        return this.heroCardName;
    }

    public boolean isDismissibleByUser() {
        return this.dismissibleByUser;
    }

    public HeroCardLayoutBase getCardLayout() {
        if (this.cardLayout == null && this.layoutData != null) {
            Class cls = (Class) LAYOUT_ID_TO_CLASS_MAP.get(Integer.valueOf(this.layoutId));
            if (cls != null) {
                ApiJsonMapper apiJsonMapper2 = getApiJsonMapper();
                String reverseMap = apiJsonMapper2.reverseMap((Object) this.layoutData);
                apiJsonMapper2.withType(cls);
                this.cardLayout = (HeroCardLayoutBase) apiJsonMapper2.tryMapFrom(reverseMap);
            }
        }
        return this.cardLayout;
    }

    private ApiJsonMapper getApiJsonMapper() {
        if (this.apiJsonMapper == null) {
            this.apiJsonMapper = new ApiJsonMapper();
        }
        return this.apiJsonMapper;
    }
}
