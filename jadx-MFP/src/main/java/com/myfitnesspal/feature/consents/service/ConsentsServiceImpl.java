package com.myfitnesspal.feature.consents.service;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.myfitnesspal.feature.consents.model.ConsentData;
import com.myfitnesspal.feature.consents.util.ConsentUtilsKt;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants.ABTest.AdConsentsInterstitial;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v2.MfpAccount;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import com.uacf.core.util.Ln;
import com.uacf.identity.sdk.UacfIdentitySdkFactory;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.uacf.consentservices.sdk.UacfConsent;
import io.uacf.consentservices.sdk.UacfConsentResponseStatus;
import io.uacf.consentservices.sdk.UacfConsentServiceSdk;
import io.uacf.consentservices.sdk.UacfConsentServiceSdkFactory;
import io.uacf.consentservices.sdk.UacfConsentStatus;
import io.uacf.core.consents.UacfUserConsentStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\b\u0012\u0004\u0012\u00020-0,2\b\u00101\u001a\u0004\u0018\u00010/H\u0016J\u0010\u00102\u001a\u0002032\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u00104\u001a\u0002052\u0006\u00106\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\u0016\u00104\u001a\b\u0012\u0004\u0012\u0002050,2\u0006\u0010.\u001a\u00020/H\u0016J\b\u00107\u001a\u000205H\u0016J(\u00108\u001a\u0002052\u0006\u00109\u001a\u00020-2\u0006\u0010:\u001a\u0002052\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<H\u0002J\u0018\u0010>\u001a\u00020?2\u0006\u00106\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u00020<H\u0016J \u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u00020<2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u0010B\u001a\u00020?2\u0006\u00109\u001a\u00020-H\u0016J\u0010\u0010C\u001a\u00020?2\u0006\u00106\u001a\u00020-H\u0016J\u001b\u0010D\u001a\u00020?2\b\u0010E\u001a\u0004\u0018\u00010FH@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u0012\u0010H\u001a\u00020I2\b\u0010E\u001a\u0004\u0018\u00010FH\u0016J\u0012\u0010J\u001a\u00020?2\b\u0010E\u001a\u0004\u0018\u00010FH\u0002R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8BX\u0002¢\u0006\f\n\u0004\b$\u0010\u0016\u001a\u0004\b\"\u0010#R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*\u0002\u0004\n\u0002\b\u0019¨\u0006K"}, d2 = {"Lcom/myfitnesspal/feature/consents/service/ConsentsServiceImpl;", "Lcom/myfitnesspal/feature/consents/service/ConsentsService;", "context", "Landroid/content/Context;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "session", "Lcom/myfitnesspal/shared/service/session/Session;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "premiumService", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "configService", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "(Landroid/content/Context;Lcom/myfitnesspal/shared/service/install/CountryService;Lcom/myfitnesspal/shared/service/session/Session;Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;Lcom/myfitnesspal/feature/premium/service/PremiumService;Lcom/myfitnesspal/shared/service/config/ConfigService;)V", "getConfigService", "()Lcom/myfitnesspal/shared/service/config/ConfigService;", "consentFactory", "Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;", "getConsentFactory", "()Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;", "consentFactory$delegate", "Lkotlin/Lazy;", "consentServiceSdk", "Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;", "getConsentServiceSdk", "()Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;", "consentServiceSdk$delegate", "getContext", "()Landroid/content/Context;", "getCountryService", "()Lcom/myfitnesspal/shared/service/install/CountryService;", "identityFactory", "Lcom/uacf/identity/sdk/UacfIdentitySdkFactory;", "getIdentityFactory", "()Lcom/uacf/identity/sdk/UacfIdentitySdkFactory;", "identityFactory$delegate", "getLocalSettingsService", "()Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "getPremiumService", "()Lcom/myfitnesspal/feature/premium/service/PremiumService;", "getSession", "()Lcom/myfitnesspal/shared/service/session/Session;", "getConsentStatus", "Lio/reactivex/Single;", "Lio/uacf/consentservices/sdk/UacfConsentStatus;", "iso", "", "getConsentStatusFromCountryName", "countryName", "getUserConsentStatus", "Lio/uacf/consentservices/sdk/UacfConsentResponseStatus;", "isConsentsRequired", "", "uacfConsentStatus", "isUserAcceptedConsentsMatrix", "shouldInterruptUserForAdConsents", "consentStatus", "isPremiumUser", "minAgeInDays", "", "minDaysBetweenRequest", "storeConsentData", "", "matrixVersion", "acceptedCount", "storeIsSubjectToAndAcceptedPersonalizedAd", "storeShouldInterruptUserForAdConsents", "updateAdConsentsForInterruption", "uacfUserconsentStatus", "Lio/uacf/core/consents/UacfUserConsentStatus;", "(Lio/uacf/core/consents/UacfUserConsentStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateConsentStatus", "Lio/reactivex/Completable;", "updateConsents", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentsServiceImpl.kt */
public final class ConsentsServiceImpl implements ConsentsService {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ConsentsServiceImpl.class), "identityFactory", "getIdentityFactory()Lcom/uacf/identity/sdk/UacfIdentitySdkFactory;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ConsentsServiceImpl.class), "consentFactory", "getConsentFactory()Lio/uacf/consentservices/sdk/UacfConsentServiceSdkFactory;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ConsentsServiceImpl.class), "consentServiceSdk", "getConsentServiceSdk()Lio/uacf/consentservices/sdk/UacfConsentServiceSdk;"))};
    @NotNull
    private final ConfigService configService;
    private final Lazy consentFactory$delegate = LazyKt.lazy(ConsentsServiceImpl$consentFactory$2.INSTANCE);
    private final Lazy consentServiceSdk$delegate = LazyKt.lazy(new ConsentsServiceImpl$consentServiceSdk$2(this));
    @NotNull
    private final Context context;
    @NotNull
    private final CountryService countryService;
    private final Lazy identityFactory$delegate = LazyKt.lazy(ConsentsServiceImpl$identityFactory$2.INSTANCE);
    @NotNull
    private final LocalSettingsService localSettingsService;
    @NotNull
    private final PremiumService premiumService;
    @NotNull
    private final Session session;

    /* access modifiers changed from: private */
    public final UacfConsentServiceSdkFactory getConsentFactory() {
        Lazy lazy = this.consentFactory$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (UacfConsentServiceSdkFactory) lazy.getValue();
    }

    /* access modifiers changed from: private */
    public final UacfConsentServiceSdk getConsentServiceSdk() {
        Lazy lazy = this.consentServiceSdk$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (UacfConsentServiceSdk) lazy.getValue();
    }

    private final UacfIdentitySdkFactory getIdentityFactory() {
        Lazy lazy = this.identityFactory$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (UacfIdentitySdkFactory) lazy.getValue();
    }

    public ConsentsServiceImpl(@NotNull Context context2, @NotNull CountryService countryService2, @NotNull Session session2, @NotNull LocalSettingsService localSettingsService2, @NotNull PremiumService premiumService2, @NotNull ConfigService configService2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(countryService2, "countryService");
        Intrinsics.checkParameterIsNotNull(session2, "session");
        Intrinsics.checkParameterIsNotNull(localSettingsService2, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(premiumService2, "premiumService");
        Intrinsics.checkParameterIsNotNull(configService2, "configService");
        this.context = context2;
        this.countryService = countryService2;
        this.session = session2;
        this.localSettingsService = localSettingsService2;
        this.premiumService = premiumService2;
        this.configService = configService2;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final CountryService getCountryService() {
        return this.countryService;
    }

    @NotNull
    public final Session getSession() {
        return this.session;
    }

    @NotNull
    public final LocalSettingsService getLocalSettingsService() {
        return this.localSettingsService;
    }

    @NotNull
    public final PremiumService getPremiumService() {
        return this.premiumService;
    }

    @NotNull
    public final ConfigService getConfigService() {
        return this.configService;
    }

    @NotNull
    public Single<UacfConsentStatus> getConsentStatusFromCountryName(@Nullable String str) {
        CharSequence charSequence = str;
        String shortName = charSequence == null || charSequence.length() == 0 ? this.countryService.getCurrentCountry().getShortName() : this.countryService.getShortNameFromLongName(str);
        if (shortName == null) {
            shortName = Country.UNITED_STATES_SHORT;
        }
        return getConsentStatus(shortName);
    }

    @NotNull
    public Single<UacfConsentStatus> getConsentStatus(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "iso");
        Single<UacfConsentStatus> cache = Single.fromCallable(new ConsentsServiceImpl$getConsentStatus$1(this, str)).cache();
        Intrinsics.checkExpressionValueIsNotNull(cache, "Single.fromCallable { co…tStatus { iso } }.cache()");
        return cache;
    }

    @NotNull
    public UacfConsentResponseStatus getUserConsentStatus(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "iso");
        UacfConsentResponseStatus userConsentStatus = getConsentServiceSdk().getUserConsentStatus(new ConsentsServiceImpl$getUserConsentStatus$1(str));
        Intrinsics.checkExpressionValueIsNotNull(userConsentStatus, "consentServiceSdk.getUserConsentStatus { iso }");
        return userConsentStatus;
    }

    @NotNull
    public Completable updateConsentStatus(@Nullable UacfUserConsentStatus uacfUserConsentStatus) {
        Completable fromCallable = Completable.fromCallable(new ConsentsServiceImpl$updateConsentStatus$1(this, uacfUserConsentStatus));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Completable.fromCallable…rconsentStatus)\n        }");
        return fromCallable;
    }

    public void storeConsentData(@NotNull UacfConsentStatus uacfConsentStatus, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(uacfConsentStatus, "uacfConsentStatus");
        Intrinsics.checkParameterIsNotNull(str, "iso");
        String consentMatrixVersion = uacfConsentStatus.getConsentMatrixVersion();
        Intrinsics.checkExpressionValueIsNotNull(consentMatrixVersion, "it.consentMatrixVersion");
        List consents = uacfConsentStatus.getConsents();
        int i = 0;
        if (consents != null) {
            Iterable iterable = consents;
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                UacfConsent uacfConsent = (UacfConsent) next;
                Intrinsics.checkExpressionValueIsNotNull(uacfConsent, "it");
                if (uacfConsent.isRequired() && uacfConsent.isAccepted()) {
                    arrayList.add(next);
                }
            }
            i = ((List) arrayList).size();
        }
        storeConsentData(consentMatrixVersion, i, str);
    }

    public void storeConsentData(@NotNull String str, int i, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "matrixVersion");
        Intrinsics.checkParameterIsNotNull(str2, "iso");
        this.localSettingsService.storeUserAcceptedConsentsMatrix(new ConsentData(str, i), str2);
    }

    public void storeConsentData(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "matrixVersion");
        LocalSettingsService localSettingsService2 = this.localSettingsService;
        ConsentData consentData = new ConsentData(str, i);
        CountryService countryService2 = this.countryService;
        UserProfile profile = this.session.getUser().getProfile();
        Intrinsics.checkExpressionValueIsNotNull(profile, "session.user.profile");
        localSettingsService2.storeUserAcceptedConsentsMatrix(consentData, countryService2.getShortNameFromLongName(profile.getCountryName()));
    }

    public boolean isUserAcceptedConsentsMatrix() {
        LocalSettingsService localSettingsService2 = this.localSettingsService;
        CountryService countryService2 = this.countryService;
        UserProfile profile = this.session.getUser().getProfile();
        Intrinsics.checkExpressionValueIsNotNull(profile, "session.user.profile");
        ConsentData isUserAcceptedConsentsMatrix = localSettingsService2.isUserAcceptedConsentsMatrix(countryService2.getShortNameFromLongName(profile.getCountryName()));
        if (isUserAcceptedConsentsMatrix == null || isUserAcceptedConsentsMatrix.getAcceptedCount() <= 0) {
            return false;
        }
        return true;
    }

    @NotNull
    public Single<Boolean> isConsentsRequired(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "iso");
        Single<Boolean> map = getConsentStatus(str).map(new ConsentsServiceImpl$isConsentsRequired$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(map, "getConsentStatus(iso).ma…nsentsRequired(it, iso) }");
        return map;
    }

    public boolean isConsentsRequired(@NotNull UacfConsentStatus uacfConsentStatus, @NotNull String str) {
        int i;
        Intrinsics.checkParameterIsNotNull(uacfConsentStatus, "uacfConsentStatus");
        Intrinsics.checkParameterIsNotNull(str, "iso");
        UacfConsentResponseStatus uacfConsentResponseStatus = uacfConsentStatus.getUacfConsentResponseStatus();
        if (uacfConsentResponseStatus != null) {
            switch (uacfConsentResponseStatus) {
                case MISSING_REQUIRED:
                case NEVER_CONSENTED:
                    if (!StringsKt.equals(Country.UNITED_STATES_SHORT, str, true) || !uacfConsentStatus.getConsents().isEmpty()) {
                        return true;
                    }
                    return false;
                case OK:
                    String consentMatrixVersion = uacfConsentStatus.getConsentMatrixVersion();
                    Intrinsics.checkExpressionValueIsNotNull(consentMatrixVersion, "it.consentMatrixVersion");
                    List consents = uacfConsentStatus.getConsents();
                    if (consents != null) {
                        Iterable iterable = consents;
                        Collection arrayList = new ArrayList();
                        for (Object next : iterable) {
                            UacfConsent uacfConsent = (UacfConsent) next;
                            Intrinsics.checkExpressionValueIsNotNull(uacfConsent, "it");
                            if (uacfConsent.isAccepted() && uacfConsent.isRequired()) {
                                arrayList.add(next);
                            }
                        }
                        i = ((List) arrayList).size();
                    } else {
                        i = 0;
                    }
                    storeConsentData(consentMatrixVersion, i, str);
                    storeShouldInterruptUserForAdConsents(uacfConsentStatus);
                    return false;
            }
        }
        storeShouldInterruptUserForAdConsents(uacfConsentStatus);
        return false;
    }

    @WorkerThread
    @Nullable
    public Object updateAdConsentsForInterruption(@Nullable UacfUserConsentStatus uacfUserConsentStatus, @NotNull Continuation<? super Unit> continuation) {
        updateConsents(uacfUserConsentStatus);
        return Unit.INSTANCE;
    }

    public void storeShouldInterruptUserForAdConsents(@NotNull UacfConsentStatus uacfConsentStatus) {
        Intrinsics.checkParameterIsNotNull(uacfConsentStatus, "uacfConsentStatus");
        if (this.configService.isVariantEnabled(AdConsentsInterstitial.NAME)) {
            try {
                String aBTestPropertyValueIfVariantEnabled = this.configService.getABTestPropertyValueIfVariantEnabled(AdConsentsInterstitial.NAME, AdConsentsInterstitial.PROPERTY_MIN_AGE);
                int parseInt = aBTestPropertyValueIfVariantEnabled != null ? Integer.parseInt(aBTestPropertyValueIfVariantEnabled) : 7;
                String aBTestPropertyValueIfVariantEnabled2 = this.configService.getABTestPropertyValueIfVariantEnabled(AdConsentsInterstitial.NAME, AdConsentsInterstitial.PROPERTY_REQUEST_INTERVAL);
                this.localSettingsService.setShouldInterruptUserForAdConsents(shouldInterruptUserForAdConsents(uacfConsentStatus, this.premiumService.isPremiumSubscribed(), parseInt, aBTestPropertyValueIfVariantEnabled2 != null ? Integer.parseInt(aBTestPropertyValueIfVariantEnabled2) : 60));
            } catch (NumberFormatException unused) {
                Ln.e("Unable to convert the personalized ad consent properties to int, using the default values", new Object[0]);
                this.localSettingsService.setShouldInterruptUserForAdConsents(shouldInterruptUserForAdConsents(uacfConsentStatus, this.premiumService.isPremiumSubscribed(), 7, 60));
            }
        }
        storeIsSubjectToAndAcceptedPersonalizedAd(uacfConsentStatus);
    }

    public void storeIsSubjectToAndAcceptedPersonalizedAd(@NotNull UacfConsentStatus uacfConsentStatus) {
        Intrinsics.checkParameterIsNotNull(uacfConsentStatus, "consentStatus");
        this.localSettingsService.setIsUserSubjectToPersonalizedAds(ConsentUtilsKt.getIsUserSubjectToPersonalizedAds(uacfConsentStatus.getConsents()));
        this.localSettingsService.setIsPersonalizedAdConsentAccepted(ConsentUtilsKt.getIsPersonalizedConsentAccepted(uacfConsentStatus.getConsents()));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateConsents(io.uacf.core.consents.UacfUserConsentStatus r8) {
        /*
            r7 = this;
            com.uacf.identity.sdk.UacfIdentitySdkFactory r0 = r7.getIdentityFactory()
            com.uacf.identity.sdk.UacfIdentitySdk r0 = r0.newSdkInstance()
            if (r0 == 0) goto L_0x0062
            com.uacf.identity.sdk.model.UacfVerticalAccountInfo r1 = r0.getCurrentUserAccount()
            r2 = 0
            if (r1 == 0) goto L_0x0042
            java.util.List r3 = r1.getAccountLinks()
            if (r3 == 0) goto L_0x0042
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x001d:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x003d
            java.lang.Object r4 = r3.next()
            r5 = r4
            com.uacf.identity.sdk.model.UacfAccountLink r5 = (com.uacf.identity.sdk.model.UacfAccountLink) r5
            java.lang.String r6 = "link"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            io.uacf.core.app.UacfUserAccountDomain r5 = r5.getDomain()
            io.uacf.core.app.UacfUserAccountDomain r6 = io.uacf.core.app.UacfUserAccountDomain.MFP
            if (r5 != r6) goto L_0x0039
            r5 = 1
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            if (r5 == 0) goto L_0x001d
            goto L_0x003e
        L_0x003d:
            r4 = r2
        L_0x003e:
            r3 = r4
            com.uacf.identity.sdk.model.UacfAccountLink r3 = (com.uacf.identity.sdk.model.UacfAccountLink) r3
            goto L_0x0043
        L_0x0042:
            r3 = r2
        L_0x0043:
            if (r1 == 0) goto L_0x0049
            java.lang.String r2 = r1.getUacfUserId()
        L_0x0049:
            if (r3 == 0) goto L_0x0058
            if (r8 == 0) goto L_0x0058
            if (r2 == 0) goto L_0x0058
            io.uacf.core.consents.UacfUserConsentStatusProvider r8 = (io.uacf.core.consents.UacfUserConsentStatusProvider) r8
            r3.setUacfUserConsentStatusProvider(r8)
            r0.updateAccount(r2, r3)
            return
        L_0x0058:
            io.uacf.core.api.UacfApiException r8 = new io.uacf.core.api.UacfApiException
            java.lang.String r0 = "No account link"
            r8.<init>(r0)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        L_0x0062:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type com.uacf.identity.sdk.UacfIdentitySdk"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.consents.service.ConsentsServiceImpl.updateConsents(io.uacf.core.consents.UacfUserConsentStatus):void");
    }

    private final boolean shouldInterruptUserForAdConsents(UacfConsentStatus uacfConsentStatus, boolean z, int i, int i2) {
        List unAcceptedAdConsents = ConsentUtilsKt.getUnAcceptedAdConsents(uacfConsentStatus.getConsents(), z);
        boolean z2 = true;
        boolean z3 = false;
        if (uacfConsentStatus.getAdConsentsLastSeenDate() != null) {
            Calendar instance = Calendar.getInstance();
            instance.add(6, -i2);
            GsonMappableIso8601Date adConsentsLastSeenDate = uacfConsentStatus.getAdConsentsLastSeenDate();
            Intrinsics.checkExpressionValueIsNotNull(instance, "dateMinusMinDaysBetweenRequest");
            if (adConsentsLastSeenDate.compareTo(instance.getTime()) >= 0 || !(!unAcceptedAdConsents.isEmpty())) {
                z2 = false;
            }
            return z2;
        }
        Calendar instance2 = Calendar.getInstance();
        instance2.add(6, -i);
        try {
            SimpleDateFormat newIso8601DateTimeFormat = Format.newIso8601DateTimeFormat();
            MfpAccount account = this.session.getUser().getAccount();
            Intrinsics.checkExpressionValueIsNotNull(account, "session.user.account");
            Date parse = newIso8601DateTimeFormat.parse(account.getCreatedAt());
            Intrinsics.checkExpressionValueIsNotNull(instance2, "dateMinusMinAge");
            if (parse.compareTo(instance2.getTime()) < 0 && (!unAcceptedAdConsents.isEmpty())) {
                z3 = true;
            }
        } catch (ParseException unused) {
            Ln.e("Unable to parse the Account creation date, So Unable to set if personalized ad consents be shown", new Object[0]);
        }
        return z3;
    }
}
