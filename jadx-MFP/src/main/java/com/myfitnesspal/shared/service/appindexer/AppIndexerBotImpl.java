package com.myfitnesspal.shared.service.appindexer;

import android.content.Intent;
import com.uacf.core.util.Strings;

public class AppIndexerBotImpl implements AppIndexerBot {
    private static final String AUTH_TOKEN = "28c84862dcfe67a29d1dc7401af68d198752e25f";
    private static final String CLIENT_ID = "mfp-android-googlebot";
    private static final String EXTRA_REFERRER_NAME = "android.intent.extra.REFERRER_NAME";
    private static final String GOOGLEBOT_REFERRER_PREFIX = "android-app://com.google.appcrawler";
    private static boolean isGoogleBot;

    public String getAuthToken() {
        return AUTH_TOKEN;
    }

    public String getClientId() {
        return CLIENT_ID;
    }

    public boolean isActive() {
        return isGoogleBot;
    }

    public void onNewIntent(Intent intent) {
        if (!isGoogleBot && intent != null) {
            String stringExtra = intent.getStringExtra(EXTRA_REFERRER_NAME);
            if (Strings.notEmpty(stringExtra) && stringExtra.startsWith(GOOGLEBOT_REFERRER_PREFIX)) {
                isGoogleBot = true;
            }
        }
    }
}
