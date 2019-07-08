package io.uacf.core.app;

import com.myfitnesspal.shared.constants.Constants.Challenges;

public enum UacfSocialNetworkProvider {
    FACEBOOK,
    GOOGLE,
    WECHAT,
    TWITTER;

    public String toString() {
        switch (this) {
            case FACEBOOK:
                return "facebook";
            case GOOGLE:
                return "google";
            case WECHAT:
                return "wechat";
            case TWITTER:
                return Challenges.CHALLENGE_INVITE_TWITTER;
            default:
                throw new IllegalStateException("Unknown enum value");
        }
    }
}
