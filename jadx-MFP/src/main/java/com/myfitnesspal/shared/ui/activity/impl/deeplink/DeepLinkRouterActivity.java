package com.myfitnesspal.shared.ui.activity.impl.deeplink;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.framework.deeplink.DeepLinkRouter;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.AnalyticsUtil;
import com.uacf.core.util.IntentUtil;
import com.uacf.core.util.Ln;
import com.uacf.core.util.UriUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class DeepLinkRouterActivity extends Activity {
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<AppIndexerBot> appIndexerBot;
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    @Inject
    Lazy<DeepLinkRouter> router;
    @Inject
    Lazy<Session> session;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.progress_overlay_activity);
        ((MyFitnessPalApp) getApplication()).component().inject(this);
        Intent intent = getIntent();
        ((AppIndexerBot) this.appIndexerBot.get()).onNewIntent(intent);
        if (IntentUtil.hasData(intent)) {
            Uri data = getIntent().getData();
            try {
                data = Uri.parse(data.toString());
            } catch (Exception unused) {
                Ln.e("deep link parse error! reverting to original!", new Object[0]);
            }
            logAppOpened(data);
            ((DeepLinkRouter) this.router.get()).setDeepLink(data);
            ((DeepLinkRouter) this.router.get()).setExtras(intent.getExtras());
            ((DeepLinkRouter) this.router.get()).route(this);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ((AppIndexerBot) this.appIndexerBot.get()).onNewIntent(intent);
    }

    private void logAppOpened(Uri uri) {
        if (!isLocalRedirect(uri)) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(AnalyticsUtil.getAppOpenedEventType(((Session) this.session.get()).getUser()), AnalyticsUtil.getUtmParamsFrom(uri));
        }
    }

    private boolean isLocalRedirect(Uri uri) {
        return UriUtils.getQueryParameterNames(uri).contains(Extras.DEEP_LINK_IS_LOCAL) && uri.getQueryParameter(Extras.DEEP_LINK_IS_LOCAL).equalsIgnoreCase("true");
    }

    public FoodSearchActivityFactory getFoodSearchRouter() {
        return (FoodSearchActivityFactory) this.foodSearchRouter.get();
    }
}
