package com.myfitnesspal.feature.consents.util;

import io.uacf.consentservices.sdk.UacfConsent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u00012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u0010\t\u001a\u00020\b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0001\u001a\u0016\u0010\n\u001a\u00020\b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0001\u001a$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\b\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"AdConsentWhiteListNonPremium", "", "", "AdConsentsWhitelistPremium", "getAllAdConsents", "Lio/uacf/consentservices/sdk/UacfConsent;", "consents", "isPremiumUser", "", "getIsPersonalizedConsentAccepted", "getIsUserSubjectToPersonalizedAds", "getUnAcceptedAdConsents", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
/* compiled from: ConsentUtils.kt */
public final class ConsentUtilsKt {
    @NotNull
    @JvmField
    public static final List<String> AdConsentWhiteListNonPremium = CollectionsKt.listOf(AdConsents.PERSONALIZED_ADS.getId(), AdConsents.LOCALIZED_ADS.getId());
    @NotNull
    @JvmField
    public static final List<String> AdConsentsWhitelistPremium = CollectionsKt.listOf(AdConsents.LOCALIZED_ADS.getId());

    @NotNull
    public static final List<UacfConsent> getUnAcceptedAdConsents(@Nullable List<? extends UacfConsent> list, boolean z) {
        boolean z2;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            UacfConsent uacfConsent = (UacfConsent) next;
            if (!uacfConsent.isRequired() && !uacfConsent.isAccepted()) {
                arrayList.add(next);
            }
        }
        Iterable iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Object next2 : iterable2) {
            UacfConsent uacfConsent2 = (UacfConsent) next2;
            if (z) {
                z2 = AdConsentsWhitelistPremium.contains(uacfConsent2.getId());
            } else {
                z2 = AdConsentWhiteListNonPremium.contains(uacfConsent2.getId());
            }
            if (z2) {
                arrayList2.add(next2);
            }
        }
        return (List) arrayList2;
    }

    @NotNull
    public static final List<UacfConsent> getAllAdConsents(@Nullable List<? extends UacfConsent> list, boolean z) {
        boolean z2;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (!((UacfConsent) next).isRequired()) {
                arrayList.add(next);
            }
        }
        Iterable iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Object next2 : iterable2) {
            UacfConsent uacfConsent = (UacfConsent) next2;
            if (z) {
                z2 = AdConsentsWhitelistPremium.contains(uacfConsent.getId());
            } else {
                z2 = AdConsentWhiteListNonPremium.contains(uacfConsent.getId());
            }
            if (z2) {
                arrayList2.add(next2);
            }
        }
        return (List) arrayList2;
    }

    public static final boolean getIsPersonalizedConsentAccepted(@Nullable List<? extends UacfConsent> list) {
        boolean z;
        if (list == null) {
            return false;
        }
        Iterable<UacfConsent> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (UacfConsent uacfConsent : iterable) {
            if (!Intrinsics.areEqual((Object) uacfConsent.getId(), (Object) AdConsents.PERSONALIZED_ADS.getId()) || !uacfConsent.isAccepted()) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static final boolean getIsUserSubjectToPersonalizedAds(@Nullable List<? extends UacfConsent> list) {
        if (list == null) {
            return false;
        }
        Iterable<UacfConsent> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (UacfConsent id : iterable) {
            if (Intrinsics.areEqual((Object) id.getId(), (Object) AdConsents.PERSONALIZED_ADS.getId())) {
                return true;
            }
        }
        return false;
    }
}
