package io.uacf.thumbprint.ui.internal.analytics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class ThumbprintAnalyticsAttributes {

    public static final class LinkClicked {
        @SerializedName("link_name")
        @Expose
        private LinkName linkName;
        @SerializedName("screen_name")
        @Expose
        private ScreenName screenName;

        public enum LinkName {
            EDIT,
            RESEND
        }

        public LinkClicked(ScreenName screenName2, LinkName linkName2) {
            this.screenName = screenName2;
            this.linkName = linkName2;
        }
    }

    public static final class PasswordChangeAttempted {
        @SerializedName("result")
        @Expose
        private boolean result;

        public PasswordChangeAttempted(boolean z) {
            this.result = z;
        }
    }

    public static final class ScreenClosed {
        @SerializedName("action")
        @Expose
        private Action action;
        @SerializedName("screen_name")
        @Expose
        private ScreenName screenName;

        public enum Action {
            VERIFIED,
            NOT_NOW,
            BACK
        }

        public ScreenClosed(ScreenName screenName2, Action action2) {
            this.screenName = screenName2;
            this.action = action2;
        }
    }

    public enum ScreenName {
        VERIFY_EMAIL,
        PASSWORD_CHANGE
    }

    public static final class ScreenViewed {
        @SerializedName("screen_name")
        @Expose
        private ScreenName screenName;
        @SerializedName("source")
        @Expose
        private Source source;
        @SerializedName("time_on_screen")
        @Expose
        private long timeOnScreen;

        public enum Source {
            APP_LAUNCH,
            EXPORT_DIARY,
            EXPORT_PROGRESS
        }

        public ScreenViewed(ScreenName screenName2, Source source2) {
            this.screenName = screenName2;
            this.source = source2;
        }

        public ScreenViewed(ScreenName screenName2, long j) {
            this.screenName = screenName2;
            this.timeOnScreen = j;
        }
    }
}
