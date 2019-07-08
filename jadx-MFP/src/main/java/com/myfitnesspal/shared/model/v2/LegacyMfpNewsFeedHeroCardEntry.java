package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.home.model.MfpNewsFeedLinkDesc;
import java.util.List;

public class LegacyMfpNewsFeedHeroCardEntry implements MfpNewsFeedActivityEntryData {
    @Expose
    private MfpNewsFeedAssetDesc asset;
    @Expose
    private List<MfpNewsFeedLinkDesc> buttons;
    @Expose
    private String cardType;
    @Expose
    private String description;
    @Expose
    private boolean isDismissibleByUser;
    @Expose
    private String title;

    public static final class CardTypes {
        public static final String ADD_FRIEND = "add_friend";
        public static final String CONNECTED_PARTNER = "connected_partner";
        public static final String EMAIL_VERIFICATION = "email_verification";
        public static final String ENGAGE_COMMUNITY = "engage_community";
        public static final String LOG_WEIGHT = "log_weight";
        public static final String NON_PREM_FILE_EXPORT = "non_premium_file_export";
        public static final String NON_PREM_MEAL_MACROS = "non_premium_meal_macros";
        public static final String PREM_FILE_EXPORT = "premium_file_export";
        public static final String PREM_MEAL_MACROS = "premium_meal_macros";
        public static final String SETUP_LOG_WEIGHT_REMINDER = "setup_log_weight_reminder";
        public static final String SET_REMINDERS = "set_reminders";
        public static final String STEP_TRACKING = "step_tracking";
        public static final String X_PROMO = "uacf_apps";
    }

    public String getText() {
        return getDescription();
    }

    public String getAnalyticsTag() {
        return this.cardType;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String str) {
        this.cardType = str;
    }

    public boolean isDismissibleByUser() {
        return this.isDismissibleByUser;
    }

    public void setIsDismissibleByUser(boolean z) {
        this.isDismissibleByUser = z;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public List<MfpNewsFeedLinkDesc> getButtons() {
        return this.buttons;
    }

    public void setButtons(List<MfpNewsFeedLinkDesc> list) {
        this.buttons = list;
    }

    public MfpNewsFeedAssetDesc getAsset() {
        return this.asset;
    }

    public void setAsset(MfpNewsFeedAssetDesc mfpNewsFeedAssetDesc) {
        this.asset = mfpNewsFeedAssetDesc;
    }
}
