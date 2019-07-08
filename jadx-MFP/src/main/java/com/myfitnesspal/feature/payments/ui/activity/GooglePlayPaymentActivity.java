package com.myfitnesspal.feature.payments.ui.activity;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.model.MfpAvailabilityDetails;
import com.myfitnesspal.feature.payments.model.MfpPaymentResult;
import com.myfitnesspal.feature.payments.model.MfpProduct.ProductType;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants.BillingResponse;
import com.myfitnesspal.feature.payments.util.GooglePlayUtil;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.DisableReceiptRetrieval;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.EnableGooglePlayPurchaseRecovery;
import com.myfitnesspal.shared.constants.Constants.Payments;
import com.myfitnesspal.shared.constants.Constants.Payments.GenericError;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.premium.PaymentsLogger;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Base64;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlayPaymentActivity extends PaymentActivityBase {
    private static final int BIND_SERVICE_TIMEOUT_MILLIS = 3500;
    private static final boolean ENABLE_TEST_PURCHASING = false;
    private static final String EXTRA_AVAILABILITY_DETAILS = "GooglePlayPaymentActivity.availability_details";
    private static final String EXTRA_GOOGLE_COMPLETED = "GooglePlayPaymentActivity.google_completed";
    private static final String EXTRA_GOOLGE_UI_VISIBLE = "GooglePlayPaymentActivity.google_ui_visible";
    private static final String EXTRA_PRODUCT_ID = "GooglePlayPaymentActivity.product_id";
    private static final String EXTRA_PRODUCT_TYPE = "GooglePlayPaymentActivity.product_type";
    private static final String EXTRA_SESSION_ID = "GooglePlayPaymentActivity.session_id";
    /* access modifiers changed from: private */
    public static final HashMap<String, String> PRODUCT_TYPE_MAP = new HashMap<>();
    private static final String TAG = "GooglePlayPaymentActivity";
    private static final String TEST_SKU = "android.test.purchased";
    private static final String TEST_SKU_PURCHASE_TOKEN = "inapp:com.myfitnesspal.android:android.test.purchased";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    /* access modifiers changed from: private */
    public BillingServiceWrapper asyncBillingWrapper = new BillingServiceWrapper();
    /* access modifiers changed from: private */
    public MfpAvailabilityDetails availabilityDetails;
    /* access modifiers changed from: private */
    public IInAppBillingService billing;
    @Inject
    Lazy<ConfigService> configService;
    private ServiceConnection connection;
    /* access modifiers changed from: private */
    public boolean destroyed;
    /* access modifiers changed from: private */
    public boolean googleCompleted;
    /* access modifiers changed from: private */
    public boolean googleUiVisible;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    /* access modifiers changed from: private */
    public String productId;
    /* access modifiers changed from: private */
    public String productType;
    /* access modifiers changed from: private */
    public Runnable serviceConnectionTimeout = new Runnable() {
        public void run() {
            PaymentsLogger.d("[%s] service bind timed out, setting error...", GooglePlayPaymentActivity.TAG);
            GooglePlayPaymentActivity.this.destroyed = true;
            GooglePlayPaymentActivity.this.setPaymentFailed(GenericError.EXTERNAL_INVOCATION_ERROR);
        }
    };
    /* access modifiers changed from: private */
    public String sessionId = randomString();

    private class BillingServiceWrapper extends SimpleAsyncServiceBase {
        private static final String INNER_TAG = "BillingServiceWrapper";

        /* access modifiers changed from: protected */
        public int getMaxThreads() {
            return 1;
        }

        private BillingServiceWrapper() {
        }

        public void processPayment() {
            PaymentsLogger.d("[%s.%s] processPayment entered, starting work in background...", GooglePlayPaymentActivity.TAG, INNER_TAG);
            async(new Runnable() {
                public void run() {
                    PaymentsLogger.d("[%s.%s] starting Google play interaction", GooglePlayPaymentActivity.TAG, BillingServiceWrapper.INNER_TAG);
                    BillingServiceWrapper.this.executeOnCurrentThread();
                }
            });
        }

        /* access modifiers changed from: 0000 */
        public void queryPurchases() {
            PaymentsLogger.d("[%s.%s] queryPurchases entered, starting work in background...", GooglePlayPaymentActivity.TAG, INNER_TAG);
            async(new Runnable() {
                public void run() {
                    PaymentsLogger.d("[%s.%s] starting Google play interaction for purchases query ", GooglePlayPaymentActivity.TAG, BillingServiceWrapper.INNER_TAG);
                    BillingServiceWrapper.this.executePurchasesQueryOnCurrentThread();
                }
            });
        }

        public void destroy() {
            shutdown();
        }

        /* access modifiers changed from: private */
        public void executeOnCurrentThread() {
            IInAppBillingService access$700 = GooglePlayPaymentActivity.this.billing;
            if (access$700 != null) {
                PaymentsLogger.d("[%s.%s] executeOnCurrentThread has valid billing service. proceeding.", GooglePlayPaymentActivity.TAG, INNER_TAG);
                try {
                    GooglePlayPaymentActivity.this.productType = (String) GooglePlayPaymentActivity.PRODUCT_TYPE_MAP.get(GooglePlayPaymentActivity.this.getProduct().getProductType());
                    int isBillingSupported = access$700.isBillingSupported(3, GooglePlayPaymentActivity.this.getPackageName(), GooglePlayPaymentActivity.this.productType);
                    BillingResponse fromResponseCode = BillingResponse.fromResponseCode(isBillingSupported);
                    PaymentsLogger.d("[%s.%s] isBillingSupported says %d.", GooglePlayPaymentActivity.TAG, INNER_TAG, Integer.valueOf(isBillingSupported));
                    if (fromResponseCode != BillingResponse.BILLING_RESPONSE_RESULT_OK) {
                        failWithGoogleErrorOnMainThread(fromResponseCode);
                        return;
                    }
                    GooglePlayPaymentActivity.this.productId = GooglePlayPaymentActivity.this.getProduct().getProductId();
                    Bundle bundle = new Bundle();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(GooglePlayPaymentActivity.this.productId);
                    bundle.putStringArrayList(GooglePlayConstants.BILLING_EXTRA_ITEM_ID_LIST, arrayList);
                    Bundle skuDetails = access$700.getSkuDetails(3, GooglePlayPaymentActivity.this.getPackageName(), GooglePlayPaymentActivity.this.productType, bundle);
                    int i = skuDetails.getInt(GooglePlayConstants.BILLING_EXTRA_RESPONSE_CODE);
                    BillingResponse fromResponseCode2 = BillingResponse.fromResponseCode(i);
                    PaymentsLogger.d("[%s.%s] getSkuDetails(%s, %s) says %d", GooglePlayPaymentActivity.TAG, INNER_TAG, GooglePlayPaymentActivity.this.productType, GooglePlayPaymentActivity.this.productId, Integer.valueOf(i));
                    if (i != 0) {
                        failWithGoogleErrorOnMainThread(fromResponseCode2);
                        return;
                    }
                    final Tuple2 availabilityDetailsForSku = GooglePlayUtil.getAvailabilityDetailsForSku((PaymentService) GooglePlayPaymentActivity.this.paymentService.get(), GooglePlayPaymentActivity.this.getGeoLocationService(), skuDetails, GooglePlayPaymentActivity.this.getProduct());
                    int intValue = ((Integer) availabilityDetailsForSku.getItem1()).intValue();
                    GenericError fromErrorCode = GenericError.fromErrorCode(intValue);
                    PaymentsLogger.d("[%s.%s] product+price validation says says %d", GooglePlayPaymentActivity.TAG, INNER_TAG, Integer.valueOf(intValue));
                    if (fromErrorCode != GenericError.NO_ERROR) {
                        failWithErrorOnMainThread(fromErrorCode);
                        return;
                    }
                    Bundle buyIntent = access$700.getBuyIntent(3, GooglePlayPaymentActivity.this.getPackageName(), GooglePlayPaymentActivity.this.productId, GooglePlayPaymentActivity.this.productType, GooglePlayPaymentActivity.this.sessionId);
                    int i2 = buyIntent.getInt(GooglePlayConstants.BILLING_EXTRA_RESPONSE_CODE);
                    BillingResponse fromResponseCode3 = BillingResponse.fromResponseCode(i2);
                    final PendingIntent pendingIntent = (PendingIntent) BundleUtils.getParcelable(buyIntent, GooglePlayConstants.BILLING_EXTRA_BUY_INTENT, PendingIntent.class.getClassLoader());
                    PaymentsLogger.d("[%s.%s] getBuyIntent(%s, %s, %s) says %d", GooglePlayPaymentActivity.TAG, INNER_TAG, GooglePlayPaymentActivity.this.productType, GooglePlayPaymentActivity.this.productId, GooglePlayPaymentActivity.this.sessionId, Integer.valueOf(i2));
                    if (fromResponseCode3 != BillingResponse.BILLING_RESPONSE_RESULT_OK) {
                        failWithGoogleErrorOnMainThread(fromResponseCode3);
                    } else if (pendingIntent == null) {
                        failWithErrorOnMainThread(GenericError.USER_INTERFACE_ERROR);
                    } else {
                        PaymentsLogger.d("[%s.%s] all validation succeeded, throwing over to Google", GooglePlayPaymentActivity.TAG, INNER_TAG);
                        postToMainThread(new Function0() {
                            public void execute() throws RuntimeException {
                                GooglePlayPaymentActivity.this.availabilityDetails = (MfpAvailabilityDetails) availabilityDetailsForSku.getItem2();
                                BillingServiceWrapper.this.startGoogleUi(pendingIntent);
                            }
                        });
                    }
                } catch (RemoteException unused) {
                    PaymentsLogger.e("[%s.%s] RemoteException in executeOnCurrentThread", GooglePlayPaymentActivity.TAG, INNER_TAG);
                    failWithRemoteExceptionOnMainThread();
                }
            } else {
                PaymentsLogger.d("[%s.%s] executeOnCurrentThread has null billing service. not proceeding.", GooglePlayPaymentActivity.TAG, INNER_TAG);
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0241, code lost:
            r2 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x0243, code lost:
            r2 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x024c, code lost:
            com.myfitnesspal.shared.service.premium.PaymentsLogger.e("[%s.%s] JSONException in executePurchasesQueryOnCurrentThread", com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.TAG, INNER_TAG);
            failWithRemoteExceptionOnMainThread();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:67:? A[ExcHandler: JSONException (unused org.json.JSONException), SYNTHETIC, Splitter:B:3:0x000f] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void executePurchasesQueryOnCurrentThread() {
            /*
                r20 = this;
                r0 = r20
                com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity r1 = com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.this
                com.android.vending.billing.IInAppBillingService r1 = r1.billing
                r2 = 2
                r3 = 1
                r4 = 0
                if (r1 == 0) goto L_0x0276
                java.lang.String r5 = "[%s.%s] Querying owned items"
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r7 = "GooglePlayPaymentActivity"
                r6[r4] = r7     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r7 = "BillingServiceWrapper"
                r6[r3] = r7     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r5, r6)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r5 = 0
                java.util.HashMap r6 = com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.PRODUCT_TYPE_MAP     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity r7 = com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.this     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.feature.payments.model.MfpProduct r7 = r7.getProduct()     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r7 = r7.getProductType()     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.Object r6 = r6.get(r7)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r6 = (java.lang.String) r6     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r7 = 0
            L_0x0032:
                java.lang.String r8 = "[%s.%s] Calling getPurchases with continuation token: (%s)"
                r9 = 3
                java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r11 = "GooglePlayPaymentActivity"
                r10[r4] = r11     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r11 = "BillingServiceWrapper"
                r10[r3] = r11     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r11 = com.uacf.core.util.Strings.toString(r5)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r10[r2] = r11     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r8, r10)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity r8 = com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.this     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r8 = r8.getPackageName()     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                android.os.Bundle r5 = r1.getPurchases(r9, r8, r6, r5)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                if (r5 != 0) goto L_0x006a
                java.lang.String r1 = "[%s.%s] ownedItems is null"
                java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r6 = "GooglePlayPaymentActivity"
                r5[r4] = r6     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r6 = "BillingServiceWrapper"
                r5[r3] = r6     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r1, r5)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r20.failWithRemoteExceptionOnMainThread()     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r12 = r7
                r7 = 2
                goto L_0x0215
            L_0x006a:
                com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity r8 = com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.this     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                int r8 = r8.getResponseCodeFromBundle(r5)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r10 = "[%s.%s] Owned items response: %d"
                java.lang.Object[] r11 = new java.lang.Object[r9]     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r12 = "GooglePlayPaymentActivity"
                r11[r4] = r12     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r12 = "BillingServiceWrapper"
                r11[r3] = r12     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.Integer r12 = java.lang.Integer.valueOf(r8)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r11[r2] = r12     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r10, r11)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.feature.payments.util.GooglePlayConstants$BillingResponse r10 = com.myfitnesspal.feature.payments.util.GooglePlayConstants.BillingResponse.fromResponseCode(r8)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.feature.payments.util.GooglePlayConstants$BillingResponse r11 = com.myfitnesspal.feature.payments.util.GooglePlayConstants.BillingResponse.BILLING_RESPONSE_RESULT_OK     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                if (r10 == r11) goto L_0x00a5
                java.lang.String r10 = "[%s.%s] getPurchases() failed: (%s)"
                java.lang.Object[] r11 = new java.lang.Object[r9]     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r12 = "GooglePlayPaymentActivity"
                r11[r4] = r12     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r12 = "BillingServiceWrapper"
                r11[r3] = r12     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r8 = com.myfitnesspal.feature.payments.util.GooglePlayUtil.getResponseDesc(r8)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r11[r2] = r8     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r10, r11)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r20.failWithRemoteExceptionOnMainThread()     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
            L_0x00a5:
                java.lang.String r8 = "INAPP_PURCHASE_ITEM_LIST"
                boolean r8 = r5.containsKey(r8)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                if (r8 == 0) goto L_0x00bd
                java.lang.String r8 = "INAPP_PURCHASE_DATA_LIST"
                boolean r8 = r5.containsKey(r8)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                if (r8 == 0) goto L_0x00bd
                java.lang.String r8 = "INAPP_DATA_SIGNATURE_LIST"
                boolean r8 = r5.containsKey(r8)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                if (r8 != 0) goto L_0x00cf
            L_0x00bd:
                java.lang.String r8 = "[%s.%s] Bundle returned from getPurchases() doesn't contain required fields."
                java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r11 = "GooglePlayPaymentActivity"
                r10[r4] = r11     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r11 = "BillingServiceWrapper"
                r10[r3] = r11     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r8, r10)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r20.failWithRemoteExceptionOnMainThread()     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
            L_0x00cf:
                java.lang.String r8 = "INAPP_PURCHASE_ITEM_LIST"
                java.util.ArrayList r8 = r5.getStringArrayList(r8)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r10 = "INAPP_PURCHASE_DATA_LIST"
                java.util.ArrayList r10 = r5.getStringArrayList(r10)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r11 = "INAPP_DATA_SIGNATURE_LIST"
                java.util.ArrayList r11 = r5.getStringArrayList(r11)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                r12 = r7
                r7 = 0
            L_0x00e3:
                int r13 = r10.size()     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                if (r7 >= r13) goto L_0x01f0
                java.lang.Object r13 = r10.get(r7)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r13 = (java.lang.String) r13     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.Object r14 = r11.get(r7)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r14 = (java.lang.String) r14     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.Object r15 = r8.get(r7)     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                java.lang.String r15 = (java.lang.String) r15     // Catch:{ RemoteException -> 0x0262, JSONException -> 0x024c }
                com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity r2 = com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.this     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                boolean r2 = r2.verifySignature(r13, r14)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                if (r2 == 0) goto L_0x01a3
                java.lang.String r2 = "[%s.%s] Sku is owned: (%s)"
                java.lang.Object[] r3 = new java.lang.Object[r9]     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r18 = "GooglePlayPaymentActivity"
                r3[r4] = r18     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r18 = "BillingServiceWrapper"
                r17 = 1
                r3[r17] = r18     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r15 = com.uacf.core.util.Strings.toString(r15)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                r16 = 2
                r3[r16] = r15     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r2, r3)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r2 = "[%s.%s] Purchase data: (%s)"
                java.lang.Object[] r3 = new java.lang.Object[r9]     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r15 = "GooglePlayPaymentActivity"
                r3[r4] = r15     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r15 = "BillingServiceWrapper"
                r17 = 1
                r3[r17] = r15     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r15 = com.uacf.core.util.Strings.toString(r13)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                r16 = 2
                r3[r16] = r15     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r2, r3)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                r2.<init>(r13)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r3 = "token"
                java.lang.String r15 = "purchaseToken"
                java.lang.String r15 = r2.optString(r15)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r3 = r2.optString(r3, r15)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r15 = "productId"
                java.lang.String r15 = r2.optString(r15)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r9 = "orderId"
                java.lang.String r2 = r2.optString(r9)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                boolean r3 = com.uacf.core.util.Strings.isEmpty(r3)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                if (r3 == 0) goto L_0x016b
                java.lang.String r2 = "[%s.%s] BUG: empty/null token!"
                r3 = 2
                java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r3 = "GooglePlayPaymentActivity"
                r9[r4] = r3     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r3 = "BillingServiceWrapper"
                r13 = 1
                r9[r13] = r3     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r2, r9)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                goto L_0x01e8
            L_0x016b:
                com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity r3 = com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.this     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r3 = r3.productId     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                boolean r3 = com.uacf.core.util.Strings.equals(r15, r3)     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                if (r3 == 0) goto L_0x01e8
                java.lang.String r3 = "[%s.%s] Found matching product with product id: %s"
                r9 = 3
                java.lang.Object[] r4 = new java.lang.Object[r9]     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r9 = "GooglePlayPaymentActivity"
                r19 = 0
                r4[r19] = r9     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r9 = "BillingServiceWrapper"
                r17 = 1
                r4[r17] = r9     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r9 = com.uacf.core.util.Strings.toString(r15)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                r15 = 2
                r4[r15] = r9     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r3, r4)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                boolean r3 = com.uacf.core.util.Strings.isEmpty(r2)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                if (r3 == 0) goto L_0x019a
                java.lang.String r2 = "MISSING_ORDER_ID"
            L_0x019a:
                com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity$BillingServiceWrapper$4 r3 = new com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity$BillingServiceWrapper$4     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                r3.<init>(r13, r14, r2)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                r0.postToMainThread(r3)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                goto L_0x01e8
            L_0x01a3:
                java.lang.String r2 = "[%s.%s] Purchase signature verification **FAILED**. Not adding item."
                r3 = 2
                java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = "GooglePlayPaymentActivity"
                r9 = 0
                r4[r9] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = "BillingServiceWrapper"
                r9 = 1
                r4[r9] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r2, r4)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r2 = "[%s.%s] Purchase data: (%s)"
                r3 = 3
                java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = "GooglePlayPaymentActivity"
                r9 = 0
                r4[r9] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = "BillingServiceWrapper"
                r9 = 1
                r4[r9] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = com.uacf.core.util.Strings.toString(r13)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                r9 = 2
                r4[r9] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r2, r4)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r2 = "[%s.%s] Signature: (%s)"
                r3 = 3
                java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = "GooglePlayPaymentActivity"
                r9 = 0
                r4[r9] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = "BillingServiceWrapper"
                r9 = 1
                r4[r9] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = com.uacf.core.util.Strings.toString(r14)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                r9 = 2
                r4[r9] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r2, r4)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                r12 = 1
            L_0x01e8:
                int r7 = r7 + 1
                r2 = 2
                r3 = 1
                r4 = 0
                r9 = 3
                goto L_0x00e3
            L_0x01f0:
                java.lang.String r2 = "INAPP_CONTINUATION_TOKEN"
                java.lang.String r5 = r5.getString(r2)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r2 = "[%s.%s] Continuation token: (%s)"
                r3 = 3
                java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r4 = "GooglePlayPaymentActivity"
                r7 = 0
                r3[r7] = r4     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r4 = "BillingServiceWrapper"
                r7 = 1
                r3[r7] = r4     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r4 = com.uacf.core.util.Strings.toString(r5)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                r7 = 2
                r3[r7] = r4     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r2, r3)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                boolean r2 = com.uacf.core.util.Strings.notEmpty(r5)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                if (r2 != 0) goto L_0x0246
            L_0x0215:
                if (r12 == 0) goto L_0x022e
                java.lang.String r1 = "[%s.%s] verification failed"
                java.lang.Object[] r2 = new java.lang.Object[r7]     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                java.lang.String r3 = "GooglePlayPaymentActivity"
                r4 = 0
                r2[r4] = r3     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r3 = "BillingServiceWrapper"
                r4 = 1
                r2[r4] = r3     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.e(r1, r2)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                com.myfitnesspal.shared.constants.Constants$Payments$GenericError r1 = com.myfitnesspal.shared.constants.Constants.Payments.GenericError.VERIFICATION_FAILED     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                r0.failWithErrorOnMainThread(r1)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                goto L_0x0289
            L_0x022e:
                java.lang.String r1 = "[%s.%s] purchase query successful"
                r2 = 2
                java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ RemoteException -> 0x0244, JSONException -> 0x024c }
                java.lang.String r2 = "GooglePlayPaymentActivity"
                r4 = 0
                r3[r4] = r2     // Catch:{ RemoteException -> 0x0241, JSONException -> 0x024c }
                java.lang.String r2 = "BillingServiceWrapper"
                r4 = 1
                r3[r4] = r2     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                com.myfitnesspal.shared.service.premium.PaymentsLogger.e(r1, r3)     // Catch:{ RemoteException -> 0x0243, JSONException -> 0x024c }
                goto L_0x0289
            L_0x0241:
                r2 = 2
                goto L_0x0262
            L_0x0243:
                r2 = 2
            L_0x0244:
                r4 = 0
                goto L_0x0262
            L_0x0246:
                r7 = r12
                r2 = 2
                r3 = 1
                r4 = 0
                goto L_0x0032
            L_0x024c:
                java.lang.String r1 = "[%s.%s] JSONException in executePurchasesQueryOnCurrentThread"
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.String r3 = "GooglePlayPaymentActivity"
                r4 = 0
                r2[r4] = r3
                java.lang.String r3 = "BillingServiceWrapper"
                r5 = 1
                r2[r5] = r3
                com.myfitnesspal.shared.service.premium.PaymentsLogger.e(r1, r2)
                r20.failWithRemoteExceptionOnMainThread()
                goto L_0x0289
            L_0x0262:
                r5 = 1
                java.lang.String r1 = "[%s.%s] RemoteException in executePurchasesQueryOnCurrentThread"
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.String r3 = "GooglePlayPaymentActivity"
                r2[r4] = r3
                java.lang.String r3 = "BillingServiceWrapper"
                r2[r5] = r3
                com.myfitnesspal.shared.service.premium.PaymentsLogger.e(r1, r2)
                r20.failWithRemoteExceptionOnMainThread()
                goto L_0x0289
            L_0x0276:
                r5 = 1
                java.lang.String r1 = "[%s.%s] executePurchasesQueryOnCurrentThread has null billing service. not proceeding."
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.String r3 = "GooglePlayPaymentActivity"
                r2[r4] = r3
                java.lang.String r3 = "BillingServiceWrapper"
                r2[r5] = r3
                com.myfitnesspal.shared.service.premium.PaymentsLogger.d(r1, r2)
                r20.failWithRemoteExceptionOnMainThread()
            L_0x0289:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.ui.activity.GooglePlayPaymentActivity.BillingServiceWrapper.executePurchasesQueryOnCurrentThread():void");
        }

        private void failWithRemoteExceptionOnMainThread() {
            PaymentsLogger.e("[%s.%s] failWithRemoteExceptionOnMainThread", GooglePlayPaymentActivity.TAG, INNER_TAG);
            postToMainThread(new Function0() {
                public void execute() throws RuntimeException {
                    GooglePlayPaymentActivity.this.failWithRemoteException();
                }
            });
        }

        private void failWithGoogleErrorOnMainThread(final BillingResponse billingResponse) {
            postToMainThread(new Function0() {
                public void execute() throws RuntimeException {
                    GenericError genericError = GenericError.UNKNOWN_ERROR;
                    if (billingResponse != BillingResponse.BILLING_RESPONSE_RESULT_UNKNOWN) {
                        genericError = billingResponse.getGenericError();
                    }
                    PaymentsLogger.e("[%s.%s] failWithGoogleErrorOnMainThread. googleError=%d, mfpError=%d", GooglePlayPaymentActivity.TAG, BillingServiceWrapper.INNER_TAG, Integer.valueOf(billingResponse.getGoogleResponseCode()), Integer.valueOf(genericError.getErrorCode()));
                    GooglePlayPaymentActivity.this.setPaymentFailed(genericError);
                }
            });
        }

        private void failWithErrorOnMainThread(final GenericError genericError) {
            PaymentsLogger.d("[%s.%s] failWithErrorOnMainThread. mfpError=%d", GooglePlayPaymentActivity.TAG, INNER_TAG, Integer.valueOf(genericError.getErrorCode()));
            postToMainThread(new Function0() {
                public void execute() throws RuntimeException {
                    GooglePlayPaymentActivity.this.setPaymentFailed(genericError);
                }
            });
        }

        /* access modifiers changed from: private */
        public void startGoogleUi(PendingIntent pendingIntent) {
            PaymentsLogger.d("[%s.%s] startGoogleUi", GooglePlayPaymentActivity.TAG, INNER_TAG);
            if (GooglePlayPaymentActivity.this.destroyed) {
                PaymentsLogger.d("[%s.%s] not showing google UI because the Activity has been destroyed", GooglePlayPaymentActivity.TAG, INNER_TAG);
                return;
            }
            try {
                GooglePlayPaymentActivity.this.startIntentSenderForResult(pendingIntent.getIntentSender(), 141, new Intent(), 0, 0, 0);
                GooglePlayPaymentActivity.this.googleUiVisible = true;
            } catch (SendIntentException unused) {
                PaymentsLogger.d("[%s.%s] startGoogleUi FAILED with SendIntentException", GooglePlayPaymentActivity.TAG, INNER_TAG);
                GooglePlayPaymentActivity.this.setPaymentFailed(GenericError.EXTERNAL_INVOCATION_ERROR);
            }
        }

        /* access modifiers changed from: protected */
        public String getThreadName() {
            StringBuilder sb = new StringBuilder();
            sb.append("GooglePlayPaymentActivity-");
            sb.append(String.valueOf(hashCode()));
            return sb.toString();
        }
    }

    public boolean showToolbar() {
        return false;
    }

    static {
        PRODUCT_TYPE_MAP.put(ProductType.SUBSCRIPTION, "subs");
        PRODUCT_TYPE_MAP.put(ProductType.PHYSICAL_GOOD, "inapp");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        PaymentsLogger.d("[%s] onCreate entered", TAG);
        super.onCreate(bundle);
        setContentView((int) R.layout.progress_overlay_activity);
        if (((PaymentService) this.paymentService.get()).isPaymentProviderAvailable("google")) {
            if (bundle != null) {
                this.googleUiVisible = bundle.getBoolean(EXTRA_GOOLGE_UI_VISIBLE, false);
                this.googleCompleted = bundle.getBoolean(EXTRA_GOOGLE_COMPLETED, false);
                this.sessionId = bundle.getString(EXTRA_SESSION_ID, this.sessionId);
                this.productId = bundle.getString(EXTRA_PRODUCT_ID);
                this.productType = bundle.getString(EXTRA_PRODUCT_TYPE);
                this.availabilityDetails = (MfpAvailabilityDetails) BundleUtils.getParcelable(bundle, EXTRA_AVAILABILITY_DETAILS, null, MfpAvailabilityDetails.class.getClassLoader());
            }
            connectToBillingService();
            return;
        }
        throw new RuntimeException("Google payments aren't available.");
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_GOOLGE_UI_VISIBLE, this.googleUiVisible);
        bundle.putBoolean(EXTRA_GOOGLE_COMPLETED, this.googleCompleted);
        bundle.putString(EXTRA_SESSION_ID, this.sessionId);
        bundle.putParcelable(EXTRA_AVAILABILITY_DETAILS, this.availabilityDetails);
        bundle.putString(EXTRA_PRODUCT_ID, this.productId);
        bundle.putString(EXTRA_PRODUCT_TYPE, this.productType);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        PaymentsLogger.d("[%s] destroying", TAG);
        this.destroyed = true;
        super.onDestroy();
        this.asyncBillingWrapper.destroy();
        disconnect("onDestroy");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        PaymentsLogger.d("[%s] onActivityResult entered", TAG);
        if (i == 141) {
            if (i2 == -1 && intent != null) {
                int intExtra = intent.getIntExtra(GooglePlayConstants.BILLING_EXTRA_RESPONSE_CODE, -1);
                String extractOrderId = extractOrderId(intent);
                BillingResponse fromResponseCode = BillingResponse.fromResponseCode(intExtra);
                HashMap hashMap = new HashMap();
                hashMap.put(Columns.ORDER_ID, Strings.toString(extractOrderId));
                hashMap.put("product_id", Strings.toString(this.productId));
                if (fromResponseCode != BillingResponse.BILLING_RESPONSE_RESULT_OK) {
                    if (fromResponseCode == BillingResponse.BILLING_RESPONSE_RESULT_ERROR || fromResponseCode == BillingResponse.BILLING_RESPONSE_RESULT_DEVELOPER_ERROR) {
                        PaymentsLogger.e("RESULT_OK, but responseCode says failure=%d", Integer.valueOf(intExtra));
                        finalizePaymentAsIncomplete(extractOrderId);
                    } else {
                        setPaymentFailed(fromResponseCode.getGenericError());
                    }
                    hashMap.put("billing_result", fromResponseCode.getGenericError().name());
                } else if (((ConfigService) this.configService.get()).isVariantEnabled(DisableReceiptRetrieval.NAME)) {
                    finalizePaymentAsIncomplete(extractOrderId);
                    Toast.makeText(this, "SIMULATED GOOGLE PLAY FAILURE FOR TESTING!", 1).show();
                    hashMap.put("billing_result", GenericError.SIMULATED_FAILURE_TESTING.name());
                } else {
                    PaymentsLogger.d("[%s] onActivityResult for Google payments was successful, finalizing %s", TAG, Strings.toString(extractOrderId));
                    finalizePaymentAsSuccess(intent.getStringExtra(GooglePlayConstants.BILLING_EXTRA_INAPP_PURCHASE_DATA), intent.getStringExtra(GooglePlayConstants.BILLING_EXTRA_SIGNATURE), extractOrderId);
                    hashMap.put("billing_result", fromResponseCode.getGenericError().name());
                }
                ((AnalyticsService) this.analyticsService.get()).reportEvent("google_play_receipt_received", (Map<String, String>) hashMap);
            } else if ((!(i2 == -1 && intent == null) && (i2 != 0 || intent == null)) || !((ConfigService) this.configService.get()).isVariantEnabled(EnableGooglePlayPurchaseRecovery.NAME)) {
                PaymentsLogger.d("[%s] onActivityResult for Google payments was unsuccessful, canceling with resultCode = (%s) and data empty = (%s) ", TAG, Integer.toString(i2), intent == null ? "true" : "false");
                setPaymentFailed(GenericError.USER_CANCELED);
            } else {
                ((AnalyticsService) this.analyticsService.get()).reportEvent("missing_google_play_receipt");
                if (intent != null) {
                    BillingResponse fromResponseCode2 = BillingResponse.fromResponseCode(intent.getIntExtra(GooglePlayConstants.BILLING_EXTRA_RESPONSE_CODE, -1));
                    if (fromResponseCode2 == BillingResponse.BILLING_RESPONSE_RESULT_USER_CANCELED || fromResponseCode2 == BillingResponse.BILLING_RESPONSE_RESULT_DEVELOPER_ERROR) {
                        finish();
                    }
                }
                this.asyncBillingWrapper.queryPurchases();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void setPaymentFailed(GenericError genericError) {
        this.googleCompleted = true;
        super.setPaymentFailed(genericError);
    }

    /* access modifiers changed from: protected */
    public void setPaymentSuccess(MfpPaymentResult mfpPaymentResult) {
        this.googleCompleted = true;
        super.setPaymentSuccess(mfpPaymentResult);
    }

    private void connectToBillingService() {
        this.connection = new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                PaymentsLogger.d("[%s] service bound, removing callbacks...", GooglePlayPaymentActivity.TAG);
                GooglePlayPaymentActivity.this.handler.removeCallbacks(GooglePlayPaymentActivity.this.serviceConnectionTimeout);
                if (GooglePlayPaymentActivity.this.destroyed) {
                    PaymentsLogger.d("[%s] service bound, but Activity destroyed. cleaning up", GooglePlayPaymentActivity.TAG);
                    GooglePlayPaymentActivity.this.disconnect("onServiceConnected");
                } else if (GooglePlayPaymentActivity.this.googleUiVisible || GooglePlayPaymentActivity.this.googleCompleted) {
                    PaymentsLogger.d("[%s] onCreate: google ui already visible or finished, not restarting checks; we're done", GooglePlayPaymentActivity.TAG);
                } else {
                    PaymentsLogger.d("[%s] service bound, starting payment asynchronously", GooglePlayPaymentActivity.TAG);
                    GooglePlayPaymentActivity.this.billing = Stub.asInterface(iBinder);
                    GooglePlayPaymentActivity.this.asyncBillingWrapper.processPayment();
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                GooglePlayPaymentActivity.this.disconnect("onServiceDisconnected");
            }
        };
        PaymentsLogger.d("[%s] binding to service and setting timeout", TAG);
        Intent intent = new Intent(GooglePlayConstants.GOOGLE_BILLING_BIND_INTENT);
        intent.setPackage("com.android.vending");
        bindService(intent, this.connection, 1);
        this.handler.postDelayed(this.serviceConnectionTimeout, 3500);
    }

    private void finalizePaymentAsIncomplete(String str) {
        PaymentsLogger.e("payment marked as incomplete", new Object[0]);
        MfpPaymentResult mfpPaymentResult = new MfpPaymentResult(getProduct(), this.sessionId, this.availabilityDetails.getPricePoint().getAmount(), "google", getGeoLocationService().getCountryCode(), null, str);
        ((SubscriptionService) this.subscriptionService.get()).addIncompleteReceipt(mfpPaymentResult, new Function0() {
            public void execute() throws RuntimeException {
                GooglePlayPaymentActivity.this.setPaymentFailed(GenericError.POTENTIALLY_CHARGED);
            }
        });
    }

    /* access modifiers changed from: private */
    public void finalizePaymentAsSuccess(String str, String str2, String str3) {
        String str4 = str;
        PaymentsLogger.d("[%s] finalizePayment entered", TAG);
        if (!verifySignature(str, str2)) {
            PaymentsLogger.d("[%s] result signature NOT VERIFIED! finishing with error", TAG);
            setPaymentFailed(GenericError.VERIFICATION_FAILED);
            return;
        }
        PaymentsLogger.d("[%s] signature successfully verified, continuing...", TAG);
        try {
            JSONObject jSONObject = new JSONObject(str4);
            PaymentsLogger.d("[%s] finalizePayment: parsed JSON response %s", TAG, str4);
            if (jSONObject.getInt(GooglePlayConstants.BILLING_JSON_FIELD_PURCHASE_STATE) == 1) {
                PaymentsLogger.d("[%s] JSON response says user canceled, aborting", TAG);
                setPaymentFailed(GenericError.USER_CANCELED);
            } else {
                PaymentsLogger.d("[%s] finalizePayment: JSON response has a successful status code, proceeding", TAG);
                String string = jSONObject.getString(GooglePlayConstants.BILLING_JSON_FIELD_PURCHASE_TOKEN);
                if (Strings.isEmptyOrWhitespace(string)) {
                    PaymentsLogger.d("[%s] finalizePayment JSON has an invalid/empty receipt payload", TAG);
                    finalizePaymentAsIncomplete(str3);
                } else {
                    String str5 = str3;
                    String string2 = jSONObject.getString(GooglePlayConstants.BILLING_JSON_FIELD_DEVELOPER_PAYLOAD);
                    if (!this.sessionId.equals(string2)) {
                        PaymentsLogger.d("[%s] finalizePayment JSON sessionId mismatch. expected=%s actual=%s", TAG, this.sessionId, string2);
                    }
                    PaymentsLogger.d("[%s] finalizePayment JSON looks good! delegating final behavior to base class", TAG);
                    MfpPaymentResult mfpPaymentResult = new MfpPaymentResult(getProduct(), this.sessionId, this.availabilityDetails.getPricePoint().getAmount(), "google", getGeoLocationService().getCountryCode(), string, str3);
                    setPaymentSuccess(mfpPaymentResult);
                }
            }
        } catch (JSONException unused) {
            PaymentsLogger.d("[%s] JSONException in finalizePayment()", TAG);
            setPaymentFailed(GenericError.INVALID_RESPONSE);
        }
    }

    /* access modifiers changed from: private */
    public void disconnect(@NonNull String str) {
        String str2 = "[%s] unbinding from IInAppBillingService service from (%s), connection is (%s)";
        Object[] objArr = new Object[3];
        objArr[0] = TAG;
        objArr[1] = str;
        objArr[2] = this.connection == null ? "true" : "false";
        PaymentsLogger.d(str2, objArr);
        ServiceConnection serviceConnection = this.connection;
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            this.connection = null;
            this.billing = null;
        }
    }

    /* access modifiers changed from: private */
    public void failWithRemoteException() {
        PaymentsLogger.d("[%s] failWithRemoteException called", TAG);
        setPaymentFailed(GenericError.EXTERNAL_INVOCATION_ERROR);
    }

    private static String randomString() {
        return new BigInteger(150, new SecureRandom()).toString(32);
    }

    private static String extractOrderId(@NonNull Intent intent) {
        try {
            return new JSONObject(intent.getStringExtra(GooglePlayConstants.BILLING_EXTRA_INAPP_PURCHASE_DATA)).optString(GooglePlayConstants.BILLING_JSON_FIELD_ORDER_ID, Payments.MISSING_ORDER_ID);
        } catch (JSONException unused) {
            return Payments.MISSING_ORDER_ID;
        }
    }

    /* access modifiers changed from: private */
    public boolean verifySignature(String str, String str2) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance(GooglePlayConstants.BILLING_VERIFICATION_KEY_FACTORY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj+5RkarDwdWGXXruMjNrnpt09B40EA7wKi4DLrGmPK2W2mDzRe/yjJZFv+Kzd11jRHRBra+FVlSFUs+RGJyVUOjGKY06va2FSeVW8IrwxFV2yNQUEDHjEen/ZvlJtB6RjC8Dc2uAh2/f7FdAe4i1+lOjLGABeedj7iZHeuxLzhhPgerqHUADzFEGeBy1leGBei57emyndXJVYboeMT8Oe6UrnEqqESzI7DYiel8weS163Hya1Jca8fffTyDKdAL1plYxXjYDDBPb5Kq7f5OinzgRwl734UhvxjdA1TSDqy1U2qIV8mh7fsPVJ77I+ct1I2GcJO1ODz+v7H10/I3gYQIDAQAB", 0)));
            try {
                Signature instance = Signature.getInstance(GooglePlayConstants.BILLING_VERIFICATION_SIGNATURE_ALGORITHM);
                instance.initVerify(generatePublic);
                instance.update(str.getBytes());
                if (instance.verify(Base64.decode(str2, 0))) {
                    PaymentsLogger.d("[%s] purchase signature verified", TAG);
                    return true;
                }
            } catch (Exception e) {
                PaymentsLogger.d("[%s] exception during signature validation (%s)", TAG, e.getClass().getSimpleName());
            }
            PaymentsLogger.d("[%s] purchase signature verification FAILED!", TAG);
            return false;
        } catch (NoSuchAlgorithmException e2) {
            PaymentsLogger.d("[%s] algorithm required to verify signature not present", TAG);
            throw new RuntimeException(e2);
        } catch (InvalidKeySpecException e3) {
            PaymentsLogger.d("[%s] failed to generate public key", TAG);
            throw new RuntimeException(e3);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getResponseCodeFromBundle(@NonNull Bundle bundle) {
        Object obj = bundle.get(GooglePlayConstants.BILLING_EXTRA_RESPONSE_CODE);
        if (obj == null) {
            PaymentsLogger.d("[%s.%s] Bundle with null response code, assuming OK (known issue)", TAG, "BillingServiceWrapper");
            return BillingResponse.BILLING_RESPONSE_RESULT_OK.getGoogleResponseCode();
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            PaymentsLogger.d("[%s.%s] Unexpected type for bundle response code.", TAG, "BillingServiceWrapper");
            PaymentsLogger.d("[%s.%s] Unexpected type for bundle response code: (%s)", TAG, "BillingServiceWrapper", obj.getClass().getName());
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected type for bundle response code: ");
            sb.append(obj.getClass().getName());
            throw new RuntimeException(sb.toString());
        }
    }
}
