package com.myfitnesspal.shared.deeplink;

import android.content.Context;
import android.net.Uri;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.ui.activity.LoginActivity;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.framework.deeplink.DeepLinkRouter;
import com.myfitnesspal.framework.deeplink.Dispatcher;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.notification.service.NotificationInvokerService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.UtmInformation;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

public class MfpDeepLinkRouter extends DeepLinkRouter {
    private Lazy<AnalyticsService> analyticsService;
    private Lazy<AppSettings> appSettings;
    private DeepLinkManager deepLinkManager;
    private NavigationHelper navigationHelper;
    private Lazy<Session> session;

    protected MfpDeepLinkRouter(Context context, Class cls, Dispatcher dispatcher, Uri uri, DeepLinkManager deepLinkManager2, NavigationHelper navigationHelper2, Lazy<AppSettings> lazy, Lazy<Session> lazy2, Lazy<AnalyticsService> lazy3) {
        super(context, cls, dispatcher, uri);
        this.deepLinkManager = deepLinkManager2;
        this.navigationHelper = navigationHelper2;
        this.appSettings = lazy;
        this.session = lazy2;
        this.analyticsService = lazy3;
    }

    public static DeepLinkRouter getInstance(Context context, Class cls, Dispatcher dispatcher, Uri uri, DeepLinkManager deepLinkManager2, NavigationHelper navigationHelper2, Lazy<AppSettings> lazy, Lazy<Session> lazy2, Lazy<AnalyticsService> lazy3) {
        if (router == null) {
            MfpDeepLinkRouter mfpDeepLinkRouter = new MfpDeepLinkRouter(context, cls, dispatcher, uri, deepLinkManager2, navigationHelper2, lazy, lazy2, lazy3);
            router = mfpDeepLinkRouter;
        } else {
            Class cls2 = cls;
            router.setRoutes(cls);
            Uri uri2 = uri;
            router.setDeepLink(uri);
            Dispatcher dispatcher2 = dispatcher;
            router.setDispatcher(dispatcher);
        }
        return router;
    }

    /* access modifiers changed from: protected */
    public boolean isUserAuthenticated() {
        return ((Session) this.session.get()).getUser().isLoggedIn();
    }

    /* access modifiers changed from: protected */
    public void onUserNotAuthenticated() {
        this.deepLinkManager.setDestinationDeepLink(getDeepLink().toString());
        this.navigationHelper.withContext(this.context).withClearTopAndNewTask().finishActivityAfterNavigation().withExtra(Extras.WELCOME_NOTIFICATION, (int) R.string.login_to_continue).withIntent(LoginActivity.newStartIntent(this.context)).startActivity();
    }

    /* access modifiers changed from: protected */
    public void onPreRoute(Uri uri) {
        extractAndSaveUtmInformation(uri);
        reportPushNotificationClicked(uri);
    }

    private void extractAndSaveUtmInformation(Uri uri) {
        if (uri != null) {
            HashMap hashMap = new HashMap();
            for (String str : UriUtils.getQueryParameterNames(uri)) {
                if (Strings.startsWith(str, "utm_")) {
                    hashMap.put(str, uri.getQueryParameter(str));
                }
            }
            if (CollectionUtils.notEmpty((Map<?, ?>) hashMap)) {
                UtmInformation fromMap = UtmInformation.fromMap(hashMap);
                if (fromMap.isValid()) {
                    ((AppSettings) this.appSettings.get()).setDeepLinkUtmInformation(fromMap);
                }
            }
        }
    }

    private void reportPushNotificationClicked(Uri uri) {
        if (uri != null) {
            for (String equals : UriUtils.getQueryParameterNames(uri)) {
                if (Strings.equals(equals, NotificationInvokerService.QUERY_PARAM_GCM_MFP_NOTIFICATION)) {
                    Builder builder = new Builder();
                    UtmInformation mostRecentUtmInformation = ((AppSettings) this.appSettings.get()).getMostRecentUtmInformation();
                    if (mostRecentUtmInformation != null) {
                        builder.put("utm_campaign", Strings.toString(mostRecentUtmInformation.getCampaign()));
                    }
                    ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_OPENED, builder.build());
                }
            }
        }
    }
}
