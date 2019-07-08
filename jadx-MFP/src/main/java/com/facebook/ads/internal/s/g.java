package com.facebook.ads.internal.s;

import com.brightcove.player.event.EventType;

public enum g {
    TEST(EventType.TEST),
    BROWSER_SESSION("browser_session"),
    CLOSE("close"),
    SHOW_AD_CALLED("show_ad_called"),
    IMPRESSION("impression"),
    INVALIDATION("invalidation"),
    STORE("store"),
    OFF_TARGET_CLICK("off_target_click"),
    OPEN_LINK("open_link"),
    NATIVE_VIEW("native_view"),
    VIDEO("video"),
    USER_RETURN("user_return"),
    AD_REPORTING("x_out"),
    PREVIEW_IMPRESSION("preview_impression"),
    AD_SELECTION("ad_selection"),
    CLICK_GUARD("click_guard"),
    TWO_STEP("two_step_dialog"),
    TWO_STEP_CANCEL("two_step_cancel"),
    SWIPE_TO_CLICK("swipe_click"),
    CLOSE_BROWSE_VIEW("browse_view_closed"),
    WATCH_AND_X_MINIMIZED("watch_and_x_minimized");
    
    private String v;

    private g(String str) {
        this.v = str;
    }

    public static g a(String str) {
        g[] values;
        for (g gVar : values()) {
            if (gVar.v.equalsIgnoreCase(str)) {
                return gVar;
            }
        }
        return null;
    }

    public String toString() {
        return this.v;
    }
}
