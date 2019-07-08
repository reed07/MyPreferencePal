package com.myfitnesspal.feature.consents.model;

import com.myfitnesspal.feature.consents.model.AdConsentsViewModel.Mode;
import com.myfitnesspal.feature.consents.util.ConsentUtilsKt;
import com.uacf.core.util.Ln;
import io.reactivex.functions.Consumer;
import io.uacf.consentservices.sdk.UacfConsent;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import io.uacf.core.consents.UacfUserConsentStatus;
import io.uacf.core.consents.UacfUserConsentStatusProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "consentsStatus", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "kotlin.jvm.PlatformType", "accept", "com/myfitnesspal/feature/consents/model/AdConsentsViewModel$fetchConsents$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsViewModel.kt */
final class AdConsentsViewModel$fetchConsents$$inlined$let$lambda$1<T> implements Consumer<UacfConsentStatus> {
    final /* synthetic */ AdConsentsViewModel this$0;

    AdConsentsViewModel$fetchConsents$$inlined$let$lambda$1(AdConsentsViewModel adConsentsViewModel) {
        this.this$0 = adConsentsViewModel;
    }

    public final void accept(UacfConsentStatus uacfConsentStatus) {
        List list;
        if (uacfConsentStatus != null) {
            this.this$0.localSettingsService.setIsUserSubjectToPersonalizedAds(ConsentUtilsKt.getIsUserSubjectToPersonalizedAds(uacfConsentStatus.getConsents()));
            AdConsentsViewModel adConsentsViewModel = this.this$0;
            UacfUserConsentStatusProvider uacfUserConsentStatus = adConsentsViewModel.getConsentServiceSdk().getUacfUserConsentStatus(uacfConsentStatus);
            if (uacfUserConsentStatus != null) {
                adConsentsViewModel.uacfUserConsentStatus = (UacfUserConsentStatus) uacfUserConsentStatus;
                if (this.this$0.getMode() == Mode.INTERRUPTION) {
                    list = ConsentUtilsKt.getUnAcceptedAdConsents(uacfConsentStatus.getConsents(), this.this$0.isPremiumUser);
                } else {
                    list = ConsentUtilsKt.getAllAdConsents(uacfConsentStatus.getConsents(), this.this$0.isPremiumUser);
                }
                AdConsentsViewModel adConsentsViewModel2 = this.this$0;
                Iterable<UacfConsent> iterable = list;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (UacfConsent uacfConsent : iterable) {
                    arrayList.add(new Pair(uacfConsent, Boolean.valueOf(uacfConsent.isAccepted())));
                }
                adConsentsViewModel2.setConsentsList((List) arrayList);
                if (!this.this$0.getConsentsList().isEmpty()) {
                    this.this$0.updateAdConsentsLastSeen();
                }
                this.this$0.updateConsentsToShowAndAcceptButtonMode();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type io.uacf.core.consents.UacfUserConsentStatus");
        }
        Ln.e("Consent Status is empty", new Object[0]);
    }
}
