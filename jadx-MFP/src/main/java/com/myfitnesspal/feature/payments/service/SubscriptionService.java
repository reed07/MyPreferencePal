package com.myfitnesspal.feature.payments.service;

import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.model.MfpPaymentResult;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import java.util.List;

public interface SubscriptionService extends SyncItemHandler<MfpPaidSubscription> {
    void addIncompleteReceipt(MfpPaymentResult mfpPaymentResult, Function0 function0);

    void findByProductId(String str, Function1<List<MfpPaidSubscription>> function1);

    String getActiveSku();

    List<MfpPaidSubscription> getAllSubscriptions();

    MfpPaidSubscription getMostRecentActiveSubscription();

    boolean isUserSubscribed();

    boolean isUserSubscribedToFeature(String str);

    void processReceipt(MfpPaymentResult mfpPaymentResult, Function1<MfpPaymentResult> function1, Function2<MfpPaymentResult, List<ApiException>> function2);

    void pullSubscriptionsFromBackend();

    void pushPendingReceiptsOnCurrentThread();

    void syncWithBackend(Function1<List<MfpPaidSubscription>> function1, Function1<List<ApiException>> function12);
}
