package com.myfitnesspal.shared.deeplink;

import android.content.Intent;
import android.net.Uri;

public interface DeepLinkManager {
    void clearDestinationDeepLink();

    Uri getDestinationUri();

    boolean hasDeepLinkDestination();

    void navigateToDeepLink();

    void setDestinationDeepLink(String str);

    void visit(Intent intent, String str);
}
