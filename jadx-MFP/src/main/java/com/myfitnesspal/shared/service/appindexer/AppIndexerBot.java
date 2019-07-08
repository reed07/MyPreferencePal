package com.myfitnesspal.shared.service.appindexer;

import android.content.Intent;

public interface AppIndexerBot {
    String getAuthToken();

    String getClientId();

    boolean isActive();

    void onNewIntent(Intent intent);
}
