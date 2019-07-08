package com.myfitnesspal.feature.payments.ui.activity;

import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import java.util.Comparator;

/* renamed from: com.myfitnesspal.feature.payments.ui.activity.-$$Lambda$PaymentConfirmation$e7oUx6z-AbEdg8QWo11oY1vnaZw reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$PaymentConfirmation$e7oUx6zAbEdg8QWo11oY1vnaZw implements Comparator {
    public static final /* synthetic */ $$Lambda$PaymentConfirmation$e7oUx6zAbEdg8QWo11oY1vnaZw INSTANCE = new $$Lambda$PaymentConfirmation$e7oUx6zAbEdg8QWo11oY1vnaZw();

    private /* synthetic */ $$Lambda$PaymentConfirmation$e7oUx6zAbEdg8QWo11oY1vnaZw() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((MfpPaidSubscription) obj2).getSubscriptionStartDate().compareTo(((MfpPaidSubscription) obj).getSubscriptionStartDate());
    }
}
