package com.myfitnesspal.feature.registration.service;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;

public class UpdatedTermsAnalyticsHelper {
    private static final String CLICK_ATTRIBUTE = "click";
    private static final String DISPLAYED_ATTRIBUTE = "displayed";
    private static final String LINK_ATTRIBUTE = "link";
    private static final String MODAL_DISPLAY = "modal_display";
    private static final String PP_SEE = "privacy_policy_see";
    private static final String SUCCESSFULLY = "successfully";
    private static final String TIER_ATTRIBUTE = "tier";
    private static final String TOS_OUTDATED_VALUE_RECEIVED = "tos_outdated_value_received";
    private static final String TOS_SEE = "terms_of_service_see";
    private static final String TOS_UPDATE_MODAL_ACCEPT = "tos_update_modal_accept";
    private static final String TOS_UPDATE_MODAL_BACK_BUTTON_EU = "tos_update_modal_back_button_EU";
    private static final String TOS_UPDATE_MODAL_LINK_CLICKED = "tos_update_modal_link_clicked";
    public static final String TOS_UPDATE_MODAL_VIEWED = "tos_update_modal_viewed";
    private static final String UNSUCCESSFULLY = "unsuccessfully";
    private static final String VALUE_CURRENT = "value_current";
    private static final String VALUE_RECEIVED = "value_received";
    private final Lazy<AnalyticsService> analyticsService;

    public enum ButtonName {
        Continue,
        Dismiss,
        Accept,
        Back
    }

    public enum CountryTier {
        US,
        EU
    }

    public enum LinkType {
        ToS,
        PP,
        ContactUs
    }

    public enum ModalState {
        Successful,
        Error
    }

    public UpdatedTermsAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportModalViewedEvent(CountryTier countryTier, boolean z) {
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str = TOS_UPDATE_MODAL_VIEWED;
        String[] strArr = new String[4];
        strArr[0] = TIER_ATTRIBUTE;
        strArr[1] = countryTier.toString();
        strArr[2] = DISPLAYED_ATTRIBUTE;
        strArr[3] = z ? SUCCESSFULLY : UNSUCCESSFULLY;
        analyticsService2.reportEvent(str, MapUtil.createMap(strArr));
    }

    public void reportModalLinkClicked(CountryTier countryTier, LinkType linkType) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(TOS_UPDATE_MODAL_LINK_CLICKED, MapUtil.createMap(TIER_ATTRIBUTE, countryTier.toString(), "link", linkType.toString()));
    }

    public void reportModalButtonClicked(CountryTier countryTier, ButtonName buttonName) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(TOS_UPDATE_MODAL_ACCEPT, MapUtil.createMap(TIER_ATTRIBUTE, countryTier.toString(), "click", buttonName.toString().toLowerCase()));
    }

    public void reportModalBackClicked(ModalState modalState) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(TOS_UPDATE_MODAL_BACK_BUTTON_EU, MapUtil.createMap(MODAL_DISPLAY, modalState.toString().toLowerCase()));
    }

    public void reportOutdatedValueReceived(int i, int i2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(TOS_OUTDATED_VALUE_RECEIVED, MapUtil.createMap(VALUE_RECEIVED, Integer.toString(i), VALUE_CURRENT, Integer.toString(i2)));
    }

    public void reportTOSSee(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(TOS_SEE, MapUtil.createMap("source", str));
    }

    public void reportPPSee(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(PP_SEE, MapUtil.createMap("source", str));
    }
}
