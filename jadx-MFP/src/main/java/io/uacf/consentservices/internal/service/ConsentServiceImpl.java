package io.uacf.consentservices.internal.service;

import android.content.Context;
import android.text.TextUtils;
import io.uacf.consentservices.internal.model.AgeGateConsentLocation;
import io.uacf.consentservices.internal.model.Consent;
import io.uacf.consentservices.internal.model.ConsentApiParams;
import io.uacf.consentservices.internal.model.ConsentSelection;
import io.uacf.consentservices.internal.model.ConsentStatus;
import io.uacf.consentservices.sdk.UacfConsentsAppDomain;
import io.uacf.core.api.ApiResponse;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfUserAccountDomain;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.net.retrofit.RetrofitBasedServiceImpl;
import io.uacf.net.retrofit.UacfApiRetrofitBuilder;
import io.uacf.net.retrofit.UacfRetrofitHelper;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ConsentServiceImpl extends RetrofitBasedServiceImpl implements ConsentService {
    private UacfConsentsAppDomain appDomain;
    private UacfAppId appId;
    private UacfAuthProvider authProvider;
    private Locale consentLocale;
    private Context context;
    private String deviceId;
    private UacfUserAccountDomain domain;

    private interface ConsentsConsumer {
        @GET("v1/consent/location-mapping/{gdpr_iso_code}")
        Call<ApiResponse<AgeGateConsentLocation>> getAgeGateConsentLocation(@Path("gdpr_iso_code") String str);

        @GET("v2/consent/status")
        Call<ApiResponse<ConsentStatus>> getConsentStatus(@Query("domain") String str, @Query("domain_user_id") String str2, @Query("device_id") String str3, @Query("user_locale") String str4, @Query("gdpr_iso_code") String str5, @Query("app_domain") String str6, @Query("expanded") boolean z, @Query("content") boolean z2);
    }

    public ConsentServiceImpl(Context context2, UacfUserAccountDomain uacfUserAccountDomain, UacfConsentsAppDomain uacfConsentsAppDomain, String str, UacfAppId uacfAppId, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfAuthProvider uacfAuthProvider, OkHttpClient okHttpClient, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer, Locale locale) {
        super(context2, uacfUserAgentProvider, uacfApiEnvironmentProvider, uacfAuthProvider, okHttpClient, uacfOkHttpNetworkTracer);
        this.appId = uacfAppId;
        this.context = context2;
        this.domain = uacfUserAccountDomain;
        this.appDomain = uacfConsentsAppDomain;
        this.deviceId = str;
        this.authProvider = uacfAuthProvider;
        this.consentLocale = locale;
    }

    public ConsentStatus getConsentStatus(ConsentApiParams consentApiParams) throws UacfApiException {
        String str;
        if (!TextUtils.isEmpty(this.authProvider.getAccessToken())) {
            str = this.authProvider.getAccessToken();
        } else {
            str = this.authProvider.getClientToken();
        }
        ConsentStatus consentStatus = (ConsentStatus) ((ApiResponse) UacfRetrofitHelper.execute(((ConsentsConsumer) ((UacfApiRetrofitBuilder) getBuilderWithUnderscores().withBearerAuth(str)).build()).getConsentStatus(this.domain.name(), this.authProvider.getDomainUserId(), this.deviceId, ConsentLocaleProvider.getFormattedLocale(this.context, this.consentLocale), consentApiParams.getIsoCode(), this.appDomain.name(), consentApiParams.isExpanded(), consentApiParams.isContent()))).getItem();
        consentStatus.setConsents(filterContentByStatus(consentStatus));
        return consentStatus;
    }

    private ArrayList<Consent> filterContentByStatus(ConsentStatus consentStatus) throws UacfApiException {
        ArrayList<Consent> arrayList = new ArrayList<>();
        if (!ConsentValidator.isConsentStatusValid(consentStatus)) {
            throw new UacfApiException("Error parsing response");
        } else if (!ConsentValidator.hasConsents(consentStatus)) {
            return arrayList;
        } else {
            ArrayList<Consent> populateConsents = populateConsents(consentStatus, (ConsentSelection) consentStatus.getSelections().get(ConsentStatus.CONSENT_ACCEPTED), true);
            populateConsents.addAll(populateConsents(consentStatus, (ConsentSelection) consentStatus.getSelections().get(ConsentStatus.CONSENT_UNACCEPTED), false));
            return populateConsents;
        }
    }

    private ArrayList<Consent> populateConsents(ConsentStatus consentStatus, ConsentSelection consentSelection, boolean z) throws UacfApiException {
        ArrayList<Consent> arrayList = new ArrayList<>();
        if (consentSelection.getTos() != null) {
            for (String str : consentSelection.getTos()) {
                Consent consent = (Consent) consentStatus.getContentMap().get(str);
                if (consent != null) {
                    consent.setRequired(true);
                    consent.setAccepted(z);
                    consentStatus.setTos(consent);
                } else {
                    throw new UacfApiException("Error parsing response");
                }
            }
        }
        if (consentSelection.getRequired() != null) {
            for (String content : consentSelection.getRequired()) {
                arrayList.addAll(getContent(consentStatus, content, true, z));
            }
        }
        if (consentSelection.getOptional() != null) {
            for (String content2 : consentSelection.getOptional()) {
                arrayList.addAll(getContent(consentStatus, content2, false, z));
            }
        }
        return arrayList;
    }

    private ArrayList<Consent> getContent(ConsentStatus consentStatus, String str, boolean z, boolean z2) {
        ArrayList<Consent> arrayList = new ArrayList<>();
        Consent consent = (Consent) consentStatus.getContentMap().get(str);
        consent.setRequired(z);
        consent.setAccepted(z2);
        arrayList.add(consent);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public Class<?> getConsumerClass() {
        return ConsentsConsumer.class;
    }
}
