package io.uacf.consentservices.sdk;

import io.uacf.consentservices.internal.model.ConsentApiParams;
import io.uacf.consentservices.internal.model.ConsentStatus;
import io.uacf.consentservices.internal.service.ConsentService;
import io.uacf.consentservices.internal.service.ConsentServiceImpl;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.consents.UacfUserConsentStatus;
import io.uacf.core.consents.UacfUserConsentStatusProvider;

public class UacfConsentServiceSdkImpl implements UacfConsentServiceSdk {
    private ConsentService consentService;

    UacfConsentServiceSdkImpl(ConsentServiceImpl consentServiceImpl) {
        this.consentService = consentServiceImpl;
    }

    public UacfConsentStatus getConsentStatus(UacfConsentIsoCodeProvider uacfConsentIsoCodeProvider) throws UacfApiException {
        ConsentStatus consentStatus = this.consentService.getConsentStatus(new ConsentApiParams(true, true, uacfConsentIsoCodeProvider.getIsoCode()));
        return new Builder().setConsentMatrixVersion(consentStatus.getConsentMatrixVersion()).setGdprIsoCode(consentStatus.getGdprIsoCode()).setHasAccepted(consentStatus.hasAccepted()).setTos(consentStatus.getTos()).setAdConsentsLastSeen(consentStatus.getAdConsentsLastSeen()).setConsents(consentStatus.getConsents()).setUacfConsentResponseStatus(UacfConsentResponseStatus.valueOf(consentStatus.getStatus().name())).setConsentStandard(consentStatus.getConsentStandard()).build();
    }

    public UacfConsentResponseStatus getUserConsentStatus(UacfConsentIsoCodeProvider uacfConsentIsoCodeProvider) throws UacfApiException {
        return UacfConsentResponseStatus.valueOf(this.consentService.getConsentStatus(new ConsentApiParams(false, false, uacfConsentIsoCodeProvider.getIsoCode())).getStatus().name());
    }

    public UacfUserConsentStatusProvider getUacfUserConsentStatus(UacfConsentsProvider uacfConsentsProvider) {
        if (uacfConsentsProvider == null) {
            return null;
        }
        UacfUserConsentStatus uacfUserConsentStatus = new UacfUserConsentStatus();
        if (uacfConsentsProvider.getTos() != null) {
            uacfUserConsentStatus.setConsentStatus(uacfConsentsProvider.getTos().getId(), uacfConsentsProvider.getTos().isAccepted());
        }
        if (uacfConsentsProvider.getConsents() != null && !uacfConsentsProvider.getConsents().isEmpty()) {
            for (UacfConsent uacfConsent : uacfConsentsProvider.getConsents()) {
                uacfUserConsentStatus.setConsentStatus(uacfConsent.getId(), uacfConsent.isAccepted());
            }
        }
        uacfUserConsentStatus.setIsoCode(uacfConsentsProvider.getGdprIsoCode());
        uacfUserConsentStatus.setConsentMatrixVersion(uacfConsentsProvider.getConsentMatrixVersion());
        uacfUserConsentStatus.setAdConsentsLastSeenDate(uacfConsentsProvider.getAdConsentsLastSeenDate());
        return uacfUserConsentStatus;
    }
}
