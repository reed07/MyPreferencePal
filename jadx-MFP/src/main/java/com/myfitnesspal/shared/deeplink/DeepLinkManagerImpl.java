package com.myfitnesspal.shared.deeplink;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import javax.inject.Inject;

public class DeepLinkManagerImpl implements DeepLinkManager {
    private AppSettings appSettings;
    private NavigationHelper navigationHelper;
    private HashMap<String, String> utmParams = new HashMap<>();

    @Inject
    public DeepLinkManagerImpl(Context context, AppSettings appSettings2, NavigationHelper navigationHelper2) {
        this.appSettings = appSettings2;
        this.navigationHelper = navigationHelper2;
        this.navigationHelper.withContext(context);
    }

    public boolean hasDeepLinkDestination() {
        return this.appSettings.hasDeepLinkDestination();
    }

    public void setDestinationDeepLink(String str) {
        Ln.d("DEEPLINK: Setting destination deep link to: %s", str);
        this.appSettings.setDestinationDeepLink(str);
    }

    public void clearDestinationDeepLink() {
        Ln.d("DEEPLINK: Clearing destination deep link", new Object[0]);
        this.appSettings.clearDestinationDeepLink();
        this.utmParams.clear();
    }

    public Uri getDestinationUri() {
        return Uri.parse(this.appSettings.getDestinationDeepLink());
    }

    public void navigateToDeepLink() {
        if (hasDeepLinkDestination()) {
            Ln.d("DEEPLINK: Navigating to deep link uri.", new Object[0]);
            this.navigationHelper.withIntent(SharedIntents.newUriIntent(getDestinationUri().buildUpon().appendQueryParameter(Extras.DEEP_LINK_IS_LOCAL, "true").build().toString())).startActivity();
        }
    }

    public void visit(Intent intent, String str) {
        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(Extras.DEEP_LINK_DESTINATION) && Strings.equals(intent.getExtras().getString(Extras.DEEP_LINK_DESTINATION), str)) {
            Ln.d("DEEPLINK: Arrived at destination screen. Clearing deep link state.", new Object[0]);
            clearDestinationDeepLink();
        }
    }
}
