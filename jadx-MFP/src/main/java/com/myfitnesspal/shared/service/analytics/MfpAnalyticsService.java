package com.myfitnesspal.shared.service.analytics;

import android.content.Context;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.v2.MfpAnalyticsEvent;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MfpAnalyticsService extends AnalyticsServiceBase {
    private final Lazy<AuthTokenProvider> authTokens;
    private final String clientId;
    private final UUID deviceId;
    private final Lazy<MfpAnalyticsTaskQueue> queue;

    public void reportRequiredConsents(String str, int i, String[] strArr) {
    }

    public MfpAnalyticsService(Context context, Lazy<AppSettings> lazy, String str, String str2, Lazy<MfpAnalyticsTaskQueue> lazy2, UUID uuid, String str3, Lazy<AuthTokenProvider> lazy3, Lazy<Session> lazy4, Lazy<SubscriptionService> lazy5) {
        super(context, lazy, str, str2, lazy4, lazy5);
        this.queue = lazy2;
        this.deviceId = uuid;
        this.clientId = str3;
        this.authTokens = lazy3;
    }

    public void reportRegistration() {
        reportEvent("sign_up", getBuilderWithUtmInfo().build(), null);
    }

    public void reportEvent(String str, Map<String, String> map, String str2, int i) {
        super.reportEvent(str, map, str2, i);
        if (isEnabled() && !Strings.isEmpty(str)) {
            HashMap hashMap = new HashMap();
            addNonEmptyValuesTo(hashMap, map);
            if (Strings.notEmpty(str2)) {
                hashMap.put("tag", str2);
            }
            try {
                ((MfpAnalyticsTaskQueue) this.queue.get()).add(new MfpAnalyticsTask(this.deviceId, this.clientId, ((AuthTokenProvider) this.authTokens.get()).getDomainUserId()).add(new MfpAnalyticsEvent().setTimestamp(Calendar.getInstance().getTime()).setSampleRate(i).setType(str).addAllAttributes(hashMap)));
            } catch (IllegalStateException e) {
                Ln.e(e);
            }
        }
    }

    public void reportFoodLookup(Map<String, String> map) {
        reportEvent(Events.FOOD_LOOKUP, map);
    }
}
