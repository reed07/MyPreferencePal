package com.myfitnesspal.feature.premium.util;

import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import java.util.Comparator;

/* renamed from: com.myfitnesspal.feature.premium.util.-$$Lambda$ProductUtils$9BPgOwTWy_ZrUxMpbSuKaVeCYeo reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ProductUtils$9BPgOwTWy_ZrUxMpbSuKaVeCYeo implements Comparator {
    public static final /* synthetic */ $$Lambda$ProductUtils$9BPgOwTWy_ZrUxMpbSuKaVeCYeo INSTANCE = new $$Lambda$ProductUtils$9BPgOwTWy_ZrUxMpbSuKaVeCYeo();

    private /* synthetic */ $$Lambda$ProductUtils$9BPgOwTWy_ZrUxMpbSuKaVeCYeo() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((MfpPaidSubscription) obj2).getSubscriptionEndDate().compareTo(((MfpPaidSubscription) obj).getSubscriptionEndDate());
    }
}
