package com.facebook.appevents.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Validate;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.math.BigDecimal;
import java.util.Currency;
import org.json.JSONException;
import org.json.JSONObject;

public class AutomaticAnalyticsLogger {
    private static final String TAG = AutomaticAnalyticsLogger.class.getCanonicalName();
    private static final InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(FacebookSdk.getApplicationContext());

    private static class PurchaseLoggingParameters {
        Currency currency;
        Bundle param;
        BigDecimal purchaseAmount;

        PurchaseLoggingParameters(BigDecimal bigDecimal, Currency currency2, Bundle bundle) {
            this.purchaseAmount = bigDecimal;
            this.currency = currency2;
            this.param = bundle;
        }
    }

    public static void logActivateAppEvent() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        boolean autoLogAppEventsEnabled = FacebookSdk.getAutoLogAppEventsEnabled();
        Validate.notNull(applicationContext, "context");
        if (!autoLogAppEventsEnabled) {
            return;
        }
        if (applicationContext instanceof Application) {
            AppEventsLogger.activateApp((Application) applicationContext, applicationId);
        } else {
            Log.w(TAG, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
        }
    }

    public static void logActivityTimeSpentEvent(String str, long j) {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        Validate.notNull(applicationContext, "context");
        FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(applicationId, false);
        if (queryAppSettings != null && queryAppSettings.getAutomaticLoggingEnabled() && j > 0) {
            AppEventsLogger newLogger = AppEventsLogger.newLogger(applicationContext);
            Bundle bundle = new Bundle(1);
            bundle.putCharSequence(Constants.AA_TIME_SPENT_SCREEN_PARAMETER_NAME, str);
            newLogger.logEvent(Constants.AA_TIME_SPENT_EVENT_NAME, (double) j, bundle);
        }
    }

    public static void logPurchaseInapp(String str, String str2) {
        if (isImplicitPurchaseLoggingEnabled()) {
            PurchaseLoggingParameters purchaseLoggingParameters = getPurchaseLoggingParameters(str, str2);
            if (purchaseLoggingParameters != null) {
                internalAppEventsLogger.logPurchaseImplicitlyInternal(purchaseLoggingParameters.purchaseAmount, purchaseLoggingParameters.currency, purchaseLoggingParameters.param);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        r3 = getPurchaseLoggingParameters(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        if (r3 == null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        internalAppEventsLogger.logEventImplicitly(r2, r3.purchaseAmount, r3.currency, r3.param);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void logPurchaseSubs(com.facebook.appevents.internal.SubscriptionType r2, java.lang.String r3, java.lang.String r4) {
        /*
            boolean r0 = isImplicitPurchaseLoggingEnabled()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            com.facebook.FacebookSdk.getApplicationId()
            int[] r0 = com.facebook.appevents.internal.AutomaticAnalyticsLogger.AnonymousClass1.$SwitchMap$com$facebook$appevents$internal$SubscriptionType
            int r2 = r2.ordinal()
            r2 = r0[r2]
            switch(r2) {
                case 1: goto L_0x0023;
                case 2: goto L_0x0020;
                case 3: goto L_0x001d;
                case 4: goto L_0x001a;
                case 5: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0037
        L_0x0016:
            logPurchaseInapp(r3, r4)
            goto L_0x0037
        L_0x001a:
            java.lang.String r2 = "SubscriptionExpire"
            goto L_0x0025
        L_0x001d:
            java.lang.String r2 = "SubscriptionHeartbeat"
            goto L_0x0025
        L_0x0020:
            java.lang.String r2 = "SubscriptionCancel"
            goto L_0x0025
        L_0x0023:
            java.lang.String r2 = "SubscriptionRestore"
        L_0x0025:
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r3 = getPurchaseLoggingParameters(r3, r4)
            if (r3 == 0) goto L_0x0036
            com.facebook.appevents.internal.InternalAppEventsLogger r4 = internalAppEventsLogger
            java.math.BigDecimal r0 = r3.purchaseAmount
            java.util.Currency r1 = r3.currency
            android.os.Bundle r3 = r3.param
            r4.logEventImplicitly(r2, r0, r1, r3)
        L_0x0036:
            return
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.internal.AutomaticAnalyticsLogger.logPurchaseSubs(com.facebook.appevents.internal.SubscriptionType, java.lang.String, java.lang.String):void");
    }

    public static boolean isImplicitPurchaseLoggingEnabled() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        return appSettingsWithoutQuery != null && FacebookSdk.getAutoLogAppEventsEnabled() && appSettingsWithoutQuery.getIAPAutomaticLoggingEnabled();
    }

    @Nullable
    private static PurchaseLoggingParameters getPurchaseLoggingParameters(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(str2);
            Bundle bundle = new Bundle(1);
            bundle.putCharSequence(Constants.IAP_PRODUCT_ID, jSONObject.getString(GooglePlayConstants.BILLING_JSON_FIELD_PRODUCT_ID));
            bundle.putCharSequence(Constants.IAP_PURCHASE_TIME, jSONObject.getString("purchaseTime"));
            bundle.putCharSequence(Constants.IAP_PURCHASE_TOKEN, jSONObject.getString(GooglePlayConstants.BILLING_JSON_FIELD_PURCHASE_TOKEN));
            bundle.putCharSequence(Constants.IAP_PACKAGE_NAME, jSONObject.optString("packageName"));
            bundle.putCharSequence(Constants.IAP_PRODUCT_TITLE, jSONObject2.optString("title"));
            bundle.putCharSequence(Constants.IAP_PRODUCT_DESCRIPTION, jSONObject2.optString("description"));
            String optString = jSONObject2.optString("type");
            bundle.putCharSequence(Constants.IAP_PRODUCT_TYPE, optString);
            if (optString.equals("subs")) {
                bundle.putCharSequence(Constants.IAP_SUBSCRIPTION_AUTORENEWING, Boolean.toString(jSONObject.optBoolean("autoRenewing", false)));
                bundle.putCharSequence(Constants.IAP_SUBSCRIPTION_PERIOD, jSONObject2.optString("subscriptionPeriod"));
                bundle.putCharSequence(Constants.IAP_FREE_TRIAL_PERIOD, jSONObject2.optString("freeTrialPeriod"));
                String optString2 = jSONObject2.optString("introductoryPriceCycles");
                if (!optString2.isEmpty()) {
                    bundle.putCharSequence(Constants.IAP_INTRO_PRICE_AMOUNT_MICROS, jSONObject2.optString("introductoryPriceAmountMicros"));
                    bundle.putCharSequence(Constants.IAP_INTRO_PRICE_CYCLES, optString2);
                }
            }
            return new PurchaseLoggingParameters(new BigDecimal(((double) jSONObject2.getLong(GooglePlayConstants.BILLING_JSON_FIELD_PRICE_AMOUNT_MICROS)) / 1000000.0d), Currency.getInstance(jSONObject2.getString(GooglePlayConstants.BILLING_JSON_FIELD_PRICE_CURRENCY_CODE)), bundle);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing in-app subscription data.", e);
            return null;
        }
    }
}
