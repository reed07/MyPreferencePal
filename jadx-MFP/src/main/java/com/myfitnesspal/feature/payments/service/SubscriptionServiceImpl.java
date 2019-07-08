package com.myfitnesspal.feature.payments.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.Parcelable;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myfitnesspal.feature.payments.api.CreatePaidSubscriptionRequest;
import com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter;
import com.myfitnesspal.feature.payments.model.MfpBillingDetails;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription.SubscriptionStatus;
import com.myfitnesspal.feature.payments.model.MfpPaymentDetails;
import com.myfitnesspal.feature.payments.model.MfpPaymentResult;
import com.myfitnesspal.feature.payments.model.MfpProductFeature;
import com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails;
import com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit;
import com.myfitnesspal.feature.payments.model.PaymentReceipt;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import com.myfitnesspal.feature.premium.event.PremiumEvents.SubscriptionsRefreshed;
import com.myfitnesspal.feature.premium.util.ProductUtils;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Payments.APIErrorCode;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.constants.SyncResourceName;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncV2ServiceBase;
import com.squareup.otto.Bus;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.provider.sdk.model.SyncItem;
import com.uacf.sync.provider.sdk.model.SyncItem.Action;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

public class SubscriptionServiceImpl extends SyncV2ServiceBase<MfpPaidSubscription> implements SubscriptionService {
    private static final String ANALYTICS_KEY_MONTHLY = "monthly";
    private static final String ANALYTICS_KEY_TRIAL = "trial";
    private static final String ANALYTICS_KEY_YEARLY = "yearly";
    private static final String ANALYTICS_NOT_SUBSCRIBED = "not_premium";
    private static final Gson MAPPER = new Gson();
    private static final int MAX_THREADS = 1;
    private static final String PREFS_KEY_SUBSCRIPTION_EXPIRATIONS_REPORTED = "subscription_expirations_reported";
    private static final int[] RETRY_TIMEOUTS = {0, 1000, 2000};
    private static final String SUBSCRIPTION_STATUS_KEY = "subscription_status";
    private static final String TAG = SubscriptionServiceImpl.class.getCanonicalName();
    private static final int VALIDATION_HTTP_STATUS_CODE = 400;
    private final Lazy<AnalyticsService> analytics;
    private final Provider<MfpV2Api> api;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final Lazy<SubscriptionServiceDbAdapter> dbAdapter;
    private final Lazy<Bus> messageBus;
    private MfpPaidSubscription mostRecentActiveSubscription;
    private Map<String, MfpProductFeature> paidFeatureCache;
    private String paidFeatureCacheUserId = "";
    private final SharedPreferences prefs;
    private final Lazy<Session> session;

    /* renamed from: com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl$10 reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action = new int[Action.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.uacf.sync.provider.sdk.model.SyncItem$Action[] r0 = com.uacf.sync.provider.sdk.model.SyncItem.Action.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action = r0
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Delete     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x001f }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Create     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x002a }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Update     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl.AnonymousClass10.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    public String getSyncResourceName() {
        return SyncResourceName.PAID_SUBSCRIPTION;
    }

    public void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException {
    }

    public SubscriptionServiceImpl(Context context2, SharedPreferences sharedPreferences, Lazy<Session> lazy, Lazy<Bus> lazy2, Lazy<SubscriptionServiceDbAdapter> lazy3, Provider<MfpV2Api> provider, Lazy<AnalyticsService> lazy4) {
        this.context = context2.getApplicationContext();
        this.prefs = sharedPreferences;
        this.session = lazy;
        this.api = provider;
        this.dbAdapter = lazy3;
        this.messageBus = lazy2;
        this.analytics = lazy4;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public Class<? extends Parcelable> getSyncItemClass() {
        return MfpPaidSubscription.class;
    }

    public void consumeSyncItems(List<SyncItem<MfpPaidSubscription>> list) {
        Enumerable.forEach((Collection<T>) list, (Function1<T>) new Function1<SyncItem<MfpPaidSubscription>>() {
            public void execute(SyncItem<MfpPaidSubscription> syncItem) {
                if (syncItem.getAction() == null) {
                    Ln.e("SYNCV2: SubscriptionServiceImpl#consumeSyncItems action is null!", new Object[0]);
                    return;
                }
                switch (AnonymousClass10.$SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action[syncItem.getAction().ordinal()]) {
                    case 1:
                        ((SubscriptionServiceDbAdapter) SubscriptionServiceImpl.this.dbAdapter.get()).removeSubscription(syncItem.getId());
                        break;
                    case 2:
                    case 3:
                        MfpPaidSubscription mfpPaidSubscription = (MfpPaidSubscription) syncItem.getItem();
                        ((SubscriptionServiceDbAdapter) SubscriptionServiceImpl.this.dbAdapter.get()).addOrUpdateSubscription(mfpPaidSubscription);
                        SubscriptionServiceImpl.this.reportIfCancelled(mfpPaidSubscription);
                        break;
                }
            }
        });
        updatePaidFeatureCacheAndNotifyRefreshed();
    }

    public MfpPaidSubscription getMostRecentActiveSubscription() {
        ensurePaidFeaturesCache();
        return this.mostRecentActiveSubscription;
    }

    public void addIncompleteReceipt(final MfpPaymentResult mfpPaymentResult, final Function0 function0) {
        auto(new Runnable() {
            public void run() {
                ((SubscriptionServiceDbAdapter) SubscriptionServiceImpl.this.dbAdapter.get()).addReceipt(mfpPaymentResult.getProduct(), mfpPaymentResult.getPaymentSessionId(), mfpPaymentResult.getProvider(), mfpPaymentResult.getCountryCode(), mfpPaymentResult.getReceipt(), mfpPaymentResult.getOrderId());
                SubscriptionServiceImpl.this.updatePaidFeatureCacheAndNotifyRefreshed();
                SubscriptionServiceImpl.this.postToMainThread(function0);
            }
        });
    }

    public void processReceipt(final MfpPaymentResult mfpPaymentResult, final Function1<MfpPaymentResult> function1, final Function2<MfpPaymentResult, List<ApiException>> function2) {
        auto(new Runnable() {
            public void run() {
                mfpPaymentResult.getProduct().getProductId();
                boolean z = !((SubscriptionServiceDbAdapter) SubscriptionServiceImpl.this.dbAdapter.get()).addReceipt(mfpPaymentResult.getProduct(), mfpPaymentResult.getPaymentSessionId(), mfpPaymentResult.getProvider(), mfpPaymentResult.getCountryCode(), mfpPaymentResult.getReceipt(), mfpPaymentResult.getOrderId());
                List access$400 = SubscriptionServiceImpl.this.postReceiptsOnCurrentThread();
                if (z) {
                    throw new RuntimeException("could not add purchase receipt to data store. this is a fatal error");
                } else if (access$400.size() == 0) {
                    SubscriptionServiceImpl.this.postToMainThread(function1, mfpPaymentResult);
                } else {
                    SubscriptionServiceImpl.this.postToMainThread(function2, mfpPaymentResult, access$400);
                }
            }
        });
    }

    public boolean isUserSubscribedToFeature(String str) {
        Map<String, MfpProductFeature> map;
        ensurePaidFeaturesCache();
        synchronized (this) {
            map = this.paidFeatureCache;
        }
        return map != null && map.containsKey(str);
    }

    public synchronized boolean isUserSubscribed() {
        ensurePaidFeaturesCache();
        return CollectionUtils.notEmpty(this.paidFeatureCache);
    }

    public void syncWithBackend(final Function1<List<MfpPaidSubscription>> function1, final Function1<List<ApiException>> function12) {
        auto(new Runnable() {
            public void run() {
                try {
                    SubscriptionServiceImpl.this.reconcileIncompleteGooglePlayPayments();
                    List access$400 = SubscriptionServiceImpl.this.postReceiptsOnCurrentThread();
                    List access$600 = SubscriptionServiceImpl.this.getSubscriptionsOnCurrentThread();
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(access$400);
                    arrayList.addAll(access$600);
                    if (arrayList.size() > 0) {
                        SubscriptionServiceImpl.this.postToMainThread(function12, arrayList);
                    } else {
                        SubscriptionServiceImpl.this.postToMainThread(function1, ((SubscriptionServiceDbAdapter) SubscriptionServiceImpl.this.dbAdapter.get()).getSubscriptions());
                    }
                } catch (Exception e) {
                    Ln.e("unexpected failure while syncing with backend! %s", e);
                    SubscriptionServiceImpl.this.postToMainThread(function12, null);
                }
            }
        });
    }

    public void findByProductId(final String str, final Function1<List<MfpPaidSubscription>> function1) {
        async(new Runnable() {
            public void run() {
                SubscriptionServiceImpl.this.postToMainThread(function1, ((SubscriptionServiceDbAdapter) SubscriptionServiceImpl.this.dbAdapter.get()).getSubscriptions(new ReturningFunction1<Boolean, MfpPaidSubscription>() {
                    public Boolean execute(MfpPaidSubscription mfpPaidSubscription) {
                        MfpPaymentDetails paymentDetails = mfpPaidSubscription.getPaymentDetails();
                        return Boolean.valueOf(paymentDetails != null && str.equals(paymentDetails.getProductId()));
                    }
                }));
            }
        });
    }

    public void pushPendingReceiptsOnCurrentThread() {
        reconcileIncompleteGooglePlayPayments();
        postReceiptsOnCurrentThread();
    }

    public void pullSubscriptionsFromBackend() {
        async(new Runnable() {
            public void run() {
                SubscriptionServiceImpl.this.getSubscriptionsOnCurrentThread();
            }
        });
    }

    public List<MfpPaidSubscription> getAllSubscriptions() {
        return ((SubscriptionServiceDbAdapter) this.dbAdapter.get()).queryAllSubscriptions();
    }

    private String getCurrentUserId() {
        return ((Session) this.session.get()).getUser().getUserId();
    }

    private String getCurrentUsername() {
        return ((Session) this.session.get()).getUser().getUsername();
    }

    /* access modifiers changed from: private */
    public List<ApiException> postReceiptsOnCurrentThread() {
        Ln.d("postReceiptsOnCurrentThread started", new Object[0]);
        HashMap hashMap = new HashMap();
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = RETRY_TIMEOUTS;
            if (i >= iArr.length) {
                break;
            }
            try {
                Thread.sleep((long) iArr[i]);
            } catch (InterruptedException unused) {
            }
            List<PaymentReceipt> pendingReceipts = ((SubscriptionServiceDbAdapter) this.dbAdapter.get()).getPendingReceipts();
            for (PaymentReceipt paymentReceipt : pendingReceipts) {
                i2++;
                MfpBillingDetails mfpBillingDetails = new MfpBillingDetails();
                mfpBillingDetails.setCoupon(paymentReceipt.getCoupon());
                mfpBillingDetails.setPaymentProvider(paymentReceipt.getPaymentProvider());
                mfpBillingDetails.setPaymentReceipt(paymentReceipt.getPayload());
                mfpBillingDetails.setCountryCode(paymentReceipt.getCountryCode());
                mfpBillingDetails.setExternalTransactionId(paymentReceipt.getOrderId());
                CreatePaidSubscriptionRequest createPaidSubscriptionRequest = new CreatePaidSubscriptionRequest();
                createPaidSubscriptionRequest.setBillingDetails(mfpBillingDetails);
                createPaidSubscriptionRequest.setProductId(paymentReceipt.getProductId());
                createPaidSubscriptionRequest.setStartDate(null);
                try {
                    ApiResponse apiResponse = (ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(new ApiRequest(createPaidSubscriptionRequest))).post(Uri.PAID_SUBSCRIPTIONS, new Object[0]);
                    if (!(apiResponse == null || apiResponse.getItem() == null)) {
                        ((SubscriptionServiceDbAdapter) this.dbAdapter.get()).addOrUpdateSubscription((MfpPaidSubscription) apiResponse.getItem());
                        ((SubscriptionServiceDbAdapter) this.dbAdapter.get()).finalizeReceipt(paymentReceipt);
                        updatePaidFeatureCacheAndNotifyRefreshed();
                    }
                    hashMap.remove(Long.valueOf(paymentReceipt.getId()));
                } catch (ApiException e) {
                    finalizeReceiptBasedOnException(e, paymentReceipt);
                    hashMap.put(Long.valueOf(paymentReceipt.getId()), e);
                    Ln.e("failed to POST payload '%s'. will try again later.", paymentReceipt.getProductId());
                }
            }
            if (CollectionUtils.isEmpty((Map<?, ?>) hashMap) || CollectionUtils.isEmpty((Collection<?>) pendingReceipts)) {
                break;
            }
            i++;
        }
        Ln.d("postReceiptsOnCurrentThread finished, processed=%d", Integer.valueOf(i2));
        updatePaidFeatureCacheAndNotifyRefreshed();
        return new ArrayList(hashMap.values());
    }

    private void finalizeReceiptBasedOnException(ApiException apiException, PaymentReceipt paymentReceipt) {
        if (apiException.getStatusCode() == 400 && apiException.getApiError() != null) {
            String error = apiException.getApiError().getError();
            if (Strings.equals(error, APIErrorCode.INVALID_RECEIPT) || Strings.equals(error, APIErrorCode.DUPLICATE_RECEIPT)) {
                ((SubscriptionServiceDbAdapter) this.dbAdapter.get()).finalizeReceipt(paymentReceipt);
                Ln.e("Backend reported %s", error);
            }
        }
    }

    private synchronized void ensurePaidFeaturesCache() {
        if (this.paidFeatureCache == null || !Strings.equals(this.paidFeatureCacheUserId, getCurrentUserId())) {
            updatePaidFeatureCacheAndNotifyRefreshed();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void updatePaidFeatureCacheAndNotifyRefreshed() {
        this.paidFeatureCacheUserId = ((Session) this.session.get()).getUser().getUserId();
        this.paidFeatureCache = ((SubscriptionServiceDbAdapter) this.dbAdapter.get()).getPaidFeatures();
        MfpPaidSubscription mostRecentActiveSubscription2 = ProductUtils.getMostRecentActiveSubscription(((SubscriptionServiceDbAdapter) this.dbAdapter.get()).getSubscriptions());
        if (!MfpPaidSubscription.isEqualTo(this.mostRecentActiveSubscription, mostRecentActiveSubscription2)) {
            this.mostRecentActiveSubscription = mostRecentActiveSubscription2;
            updateUserPremiumStatus();
        }
        notifySubscriptionsRefreshed();
    }

    /* access modifiers changed from: private */
    public void reconcileIncompleteGooglePlayPayments(final ServiceConnection serviceConnection, final IInAppBillingService iInAppBillingService) {
        auto(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:44:0x00e0  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r8 = this;
                    r0 = 1
                    r1 = 0
                    java.lang.String r2 = "reconcileIncompleteGooglePlayPayments started"
                    java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ all -> 0x00d1 }
                    com.uacf.core.util.Ln.d(r2, r3)     // Catch:{ all -> 0x00d1 }
                    com.android.vending.billing.IInAppBillingService r2 = r3     // Catch:{ all -> 0x00d1 }
                    if (r2 == 0) goto L_0x007e
                    com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl r2 = com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl.this     // Catch:{ all -> 0x00d1 }
                    android.content.Context r2 = r2.context     // Catch:{ all -> 0x00d1 }
                    com.android.vending.billing.IInAppBillingService r3 = r3     // Catch:{ all -> 0x00d1 }
                    java.util.List r2 = com.myfitnesspal.feature.payments.util.GooglePlayUtil.extractSessionsAndReceipts(r2, r3)     // Catch:{ all -> 0x00d1 }
                    java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00d1 }
                    r3 = 0
                L_0x001e:
                    boolean r4 = r2.hasNext()     // Catch:{ all -> 0x007c }
                    if (r4 == 0) goto L_0x0074
                    java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x007c }
                    com.uacf.core.util.Tuple2 r4 = (com.uacf.core.util.Tuple2) r4     // Catch:{ all -> 0x007c }
                    java.lang.Object r5 = r4.getItem1()     // Catch:{ all -> 0x007c }
                    java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x007c }
                    java.lang.Object r4 = r4.getItem2()     // Catch:{ all -> 0x007c }
                    java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x007c }
                    com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl r6 = com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl.this     // Catch:{ all -> 0x007c }
                    dagger.Lazy r6 = r6.dbAdapter     // Catch:{ all -> 0x007c }
                    java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x007c }
                    com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter r6 = (com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter) r6     // Catch:{ all -> 0x007c }
                    com.myfitnesspal.feature.payments.model.PaymentReceipt r6 = r6.findReceiptByPaymentSessionId(r5)     // Catch:{ all -> 0x007c }
                    if (r6 == 0) goto L_0x001e
                    java.lang.String r7 = r6.getPaymentSessionId()     // Catch:{ all -> 0x007c }
                    boolean r7 = com.uacf.core.util.Strings.equals(r5, r7)     // Catch:{ all -> 0x007c }
                    if (r7 == 0) goto L_0x001e
                    java.lang.String r6 = r6.getPayload()     // Catch:{ all -> 0x007c }
                    boolean r6 = com.uacf.core.util.Strings.isEmpty(r6)     // Catch:{ all -> 0x007c }
                    if (r6 == 0) goto L_0x001e
                    boolean r6 = com.uacf.core.util.Strings.notEmpty(r4)     // Catch:{ all -> 0x007c }
                    if (r6 == 0) goto L_0x001e
                    com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl r6 = com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl.this     // Catch:{ all -> 0x007c }
                    dagger.Lazy r6 = r6.dbAdapter     // Catch:{ all -> 0x007c }
                    java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x007c }
                    com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter r6 = (com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter) r6     // Catch:{ all -> 0x007c }
                    r6.updateReceiptPayload(r5, r4)     // Catch:{ all -> 0x007c }
                    int r3 = r3 + 1
                    goto L_0x001e
                L_0x0074:
                    if (r3 <= 0) goto L_0x007f
                    com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl r2 = com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl.this     // Catch:{ all -> 0x007c }
                    r2.postReceiptsOnCurrentThread()     // Catch:{ all -> 0x007c }
                    goto L_0x007f
                L_0x007c:
                    r2 = move-exception
                    goto L_0x00d3
                L_0x007e:
                    r3 = 0
                L_0x007f:
                    com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl r2 = com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl.this     // Catch:{ Exception -> 0x00a4 }
                    android.content.Context r2 = r2.context     // Catch:{ Exception -> 0x00a4 }
                    android.content.ServiceConnection r4 = r2     // Catch:{ Exception -> 0x00a4 }
                    r2.unbindService(r4)     // Catch:{ Exception -> 0x00a4 }
                    if (r3 <= 0) goto L_0x009a
                    java.lang.String r2 = "reconciled %d orphaned subscriptions"
                    java.lang.Object[] r0 = new java.lang.Object[r0]
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    r0[r1] = r3
                L_0x0096:
                    com.uacf.core.util.Ln.e(r2, r0)
                    goto L_0x00b8
                L_0x009a:
                    java.lang.String r0 = "reconciled 0 orphaned subscriptions"
                    java.lang.Object[] r1 = new java.lang.Object[r1]
                    com.uacf.core.util.Ln.d(r0, r1)
                    goto L_0x00b8
                L_0x00a2:
                    r2 = move-exception
                    goto L_0x00b9
                L_0x00a4:
                    java.lang.String r2 = "failed to unbind service! connection will leak"
                    java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ all -> 0x00a2 }
                    com.uacf.core.util.Ln.e(r2, r4)     // Catch:{ all -> 0x00a2 }
                    if (r3 <= 0) goto L_0x009a
                    java.lang.String r2 = "reconciled %d orphaned subscriptions"
                    java.lang.Object[] r0 = new java.lang.Object[r0]
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    r0[r1] = r3
                    goto L_0x0096
                L_0x00b8:
                    return
                L_0x00b9:
                    if (r3 <= 0) goto L_0x00c9
                    java.lang.Object[] r0 = new java.lang.Object[r0]
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    r0[r1] = r3
                    java.lang.String r1 = "reconciled %d orphaned subscriptions"
                    com.uacf.core.util.Ln.e(r1, r0)
                    goto L_0x00d0
                L_0x00c9:
                    java.lang.Object[] r0 = new java.lang.Object[r1]
                    java.lang.String r1 = "reconciled 0 orphaned subscriptions"
                    com.uacf.core.util.Ln.d(r1, r0)
                L_0x00d0:
                    throw r2
                L_0x00d1:
                    r2 = move-exception
                    r3 = 0
                L_0x00d3:
                    com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl r4 = com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl.this     // Catch:{ Exception -> 0x00f8 }
                    android.content.Context r4 = r4.context     // Catch:{ Exception -> 0x00f8 }
                    android.content.ServiceConnection r5 = r2     // Catch:{ Exception -> 0x00f8 }
                    r4.unbindService(r5)     // Catch:{ Exception -> 0x00f8 }
                    if (r3 <= 0) goto L_0x00ee
                    java.lang.Object[] r0 = new java.lang.Object[r0]
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    r0[r1] = r3
                L_0x00e8:
                    java.lang.String r1 = "reconciled %d orphaned subscriptions"
                    com.uacf.core.util.Ln.e(r1, r0)
                    goto L_0x010a
                L_0x00ee:
                    java.lang.Object[] r0 = new java.lang.Object[r1]
                    java.lang.String r1 = "reconciled 0 orphaned subscriptions"
                    com.uacf.core.util.Ln.d(r1, r0)
                    goto L_0x010a
                L_0x00f6:
                    r2 = move-exception
                    goto L_0x010b
                L_0x00f8:
                    java.lang.String r4 = "failed to unbind service! connection will leak"
                    java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f6 }
                    com.uacf.core.util.Ln.e(r4, r5)     // Catch:{ all -> 0x00f6 }
                    if (r3 <= 0) goto L_0x00ee
                    java.lang.Object[] r0 = new java.lang.Object[r0]
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    r0[r1] = r3
                    goto L_0x00e8
                L_0x010a:
                    throw r2
                L_0x010b:
                    if (r3 <= 0) goto L_0x011b
                    java.lang.Object[] r0 = new java.lang.Object[r0]
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    r0[r1] = r3
                    java.lang.String r1 = "reconciled %d orphaned subscriptions"
                    com.uacf.core.util.Ln.e(r1, r0)
                    goto L_0x0122
                L_0x011b:
                    java.lang.Object[] r0 = new java.lang.Object[r1]
                    java.lang.String r1 = "reconciled 0 orphaned subscriptions"
                    com.uacf.core.util.Ln.d(r1, r0)
                L_0x0122:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl.AnonymousClass7.run():void");
            }
        });
    }

    /* access modifiers changed from: private */
    public void reconcileIncompleteGooglePlayPayments() {
        AnonymousClass8 r0 = new ServiceConnection() {
            public void onServiceDisconnected(ComponentName componentName) {
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SubscriptionServiceImpl.this.reconcileIncompleteGooglePlayPayments(this, Stub.asInterface(iBinder));
            }
        };
        Intent intent = new Intent(GooglePlayConstants.GOOGLE_BILLING_BIND_INTENT);
        intent.setPackage("com.android.vending");
        this.context.bindService(intent, r0, 1);
    }

    /* access modifiers changed from: private */
    public List<ApiException> getSubscriptionsOnCurrentThread() {
        ArrayList arrayList = new ArrayList();
        Ln.d("getSubscriptionsOnCurrentThread started", new Object[0]);
        try {
            List items = ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.PAID_SUBSCRIPTIONS, "subscription_status", SubscriptionStatus.ACTIVE.value())).getItems();
            if (items != null) {
                ((SubscriptionServiceDbAdapter) this.dbAdapter.get()).setSubscriptions(getCurrentUsername(), items);
                updatePaidFeatureCacheAndNotifyRefreshed();
            }
        } catch (ApiException e) {
            arrayList.add(e);
            Ln.e("failure during GET /paid-subscriptions! %s", e);
        }
        Ln.d("getSubscriptionsOnCurrentThread finished", new Object[0]);
        return arrayList;
    }

    private Map<String, Boolean> loadReportedExpirations() {
        try {
            return (Map) MAPPER.fromJson(this.prefs.getString(PREFS_KEY_SUBSCRIPTION_EXPIRATIONS_REPORTED, "{}"), new TypeToken<Map<String, Boolean>>() {
            }.getType());
        } catch (Exception unused) {
            return new HashMap();
        }
    }

    private boolean expirationReported(String str) {
        Boolean bool = (Boolean) loadReportedExpirations().get(str);
        return bool != null && bool.booleanValue();
    }

    private void addReportedExpiration(String str) {
        Map loadReportedExpirations = loadReportedExpirations();
        loadReportedExpirations.put(str, Boolean.valueOf(true));
        this.prefs.edit().putString(PREFS_KEY_SUBSCRIPTION_EXPIRATIONS_REPORTED, MAPPER.toJson((Object) loadReportedExpirations)).apply();
    }

    private void notifySubscriptionsRefreshed() {
        ((Bus) this.messageBus.get()).post(new SubscriptionsRefreshed());
    }

    /* access modifiers changed from: private */
    public void reportIfCancelled(MfpPaidSubscription mfpPaidSubscription) {
        if (mfpPaidSubscription.getSubscriptionStatus() == SubscriptionStatus.CANCELLED) {
            String subscriptionId = mfpPaidSubscription.getSubscriptionId();
            if (!expirationReported(subscriptionId)) {
                String str = ANALYTICS_KEY_TRIAL;
                if (!mfpPaidSubscription.isTrial()) {
                    MfpSubscriptionDetails subscriptionDetails = mfpPaidSubscription.getSubscriptionDetails();
                    if (subscriptionDetails != null) {
                        FrequencyUnit frequencyUnit = subscriptionDetails.getFrequencyUnit();
                        if (frequencyUnit != null) {
                            str = frequencyUnit == FrequencyUnit.MONTH ? "monthly" : ANALYTICS_KEY_YEARLY;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                ((AnalyticsService) this.analytics.get()).reportEvent(Events.PREMIUM_EXPIRED, MapUtil.createMap(Attributes.SKU, str));
                addReportedExpiration(subscriptionId);
            }
        }
    }

    public String getActiveSku() {
        return isUserSubscribed() ? getMostRecentActiveSubscription().getPaymentDetails().getProductId() : ANALYTICS_NOT_SUBSCRIBED;
    }

    private void updateUserPremiumStatus() {
        MfpPaidSubscription mfpPaidSubscription = this.mostRecentActiveSubscription;
        ((AnalyticsService) this.analytics.get()).updateUserPremiumStatus((mfpPaidSubscription == null || mfpPaidSubscription.getPaymentDetails() == null || this.mostRecentActiveSubscription.getPaymentDetails().getProductId() == null) ? ANALYTICS_NOT_SUBSCRIBED : this.mostRecentActiveSubscription.getPaymentDetails().getProductId());
    }
}
