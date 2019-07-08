package com.mopub.common.privacy;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.MoPubIdentifier.AdvertisingIdChangeListener;
import com.mopub.common.privacy.SyncRequest.Listener;
import com.mopub.common.util.ManifestUtils;
import com.mopub.mobileads.MoPubConversionTracker;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MultiAdResponse;
import com.mopub.network.MultiAdResponse.ServerOverrideListener;
import com.mopub.network.Networking;
import com.mopub.volley.VolleyError;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PersonalInfoManager {
    /* access modifiers changed from: private */
    @NonNull
    public final Context mAppContext;
    @NonNull
    private final ConsentDialogController mConsentDialogController;
    @NonNull
    private final Set<ConsentStatusChangeListener> mConsentStatusChangeListeners;
    @NonNull
    private final MoPubConversionTracker mConversionTracker;
    /* access modifiers changed from: private */
    public boolean mForceGdprAppliesChanged;
    /* access modifiers changed from: private */
    public boolean mForceGdprAppliesChangedSending;
    /* access modifiers changed from: private */
    @Nullable
    public Long mLastSyncRequestTimeUptimeMs;
    /* access modifiers changed from: private */
    @NonNull
    public final PersonalInfoData mPersonalInfoData;
    /* access modifiers changed from: private */
    @Nullable
    public SdkInitializationListener mSdkInitializationListener;
    /* access modifiers changed from: private */
    @NonNull
    public ServerOverrideListener mServerOverrideListener;
    /* access modifiers changed from: private */
    public long mSyncDelayMs = 300000;
    /* access modifiers changed from: private */
    @Nullable
    public ConsentStatus mSyncRequestConsentStatus;
    /* access modifiers changed from: private */
    public long mSyncRequestEpochTime;
    /* access modifiers changed from: private */
    public boolean mSyncRequestInFlight;
    @NonNull
    private final Listener mSyncRequestListener;

    private class PersonalInfoServerOverrideListener implements ServerOverrideListener {
        private PersonalInfoServerOverrideListener() {
        }

        public void onForceExplicitNo(@Nullable String str) {
            if (TextUtils.isEmpty(str)) {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.EXPLICIT_NO, ConsentChangeReason.REVOKED_BY_SERVER);
            } else {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.EXPLICIT_NO, str);
            }
        }

        public void onInvalidateConsent(@Nullable String str) {
            if (TextUtils.isEmpty(str)) {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.UNKNOWN, ConsentChangeReason.REACQUIRE_BY_SERVER);
            } else {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.UNKNOWN, str);
            }
        }

        public void onReacquireConsent(@Nullable String str) {
            if (!TextUtils.isEmpty(str)) {
                PersonalInfoManager.this.mPersonalInfoData.setConsentChangeReason(str);
            }
            PersonalInfoManager.this.mPersonalInfoData.setShouldReacquireConsent(true);
            PersonalInfoManager.this.mPersonalInfoData.writeToDisk();
        }

        public void onForceGdprApplies() {
            PersonalInfoManager.this.forceGdprApplies();
        }
    }

    private class PersonalInfoSyncRequestListener implements Listener {
        private PersonalInfoSyncRequestListener() {
        }

        public void onSuccess(SyncResponse syncResponse) {
            boolean canCollectPersonalInformation = PersonalInfoManager.this.canCollectPersonalInformation();
            if (PersonalInfoManager.this.mPersonalInfoData.getGdprApplies() == null) {
                PersonalInfoManager.this.mPersonalInfoData.setGdprApplies(Boolean.valueOf(syncResponse.isGdprRegion()));
            }
            if (syncResponse.isForceGdprApplies()) {
                PersonalInfoManager.this.mForceGdprAppliesChanged = true;
                PersonalInfoManager.this.mPersonalInfoData.setForceGdprApplies(true);
                boolean canCollectPersonalInformation2 = PersonalInfoManager.this.canCollectPersonalInformation();
                if (canCollectPersonalInformation != canCollectPersonalInformation2) {
                    PersonalInfoManager personalInfoManager = PersonalInfoManager.this;
                    personalInfoManager.fireOnConsentStateChangeListeners(personalInfoManager.mPersonalInfoData.getConsentStatus(), PersonalInfoManager.this.mPersonalInfoData.getConsentStatus(), canCollectPersonalInformation2);
                }
            }
            PersonalInfoData access$300 = PersonalInfoManager.this.mPersonalInfoData;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(PersonalInfoManager.this.mSyncRequestEpochTime);
            access$300.setLastChangedMs(sb.toString());
            PersonalInfoManager.this.mPersonalInfoData.setLastSuccessfullySyncedConsentStatus(PersonalInfoManager.this.mSyncRequestConsentStatus);
            PersonalInfoManager.this.mPersonalInfoData.setWhitelisted(syncResponse.isWhitelisted());
            PersonalInfoManager.this.mPersonalInfoData.setCurrentVendorListVersion(syncResponse.getCurrentVendorListVersion());
            PersonalInfoManager.this.mPersonalInfoData.setCurrentVendorListLink(syncResponse.getCurrentVendorListLink());
            PersonalInfoManager.this.mPersonalInfoData.setCurrentPrivacyPolicyVersion(syncResponse.getCurrentPrivacyPolicyVersion());
            PersonalInfoManager.this.mPersonalInfoData.setCurrentPrivacyPolicyLink(syncResponse.getCurrentPrivacyPolicyLink());
            String currentVendorListIabHash = syncResponse.getCurrentVendorListIabHash();
            String currentVendorListIabFormat = syncResponse.getCurrentVendorListIabFormat();
            if (!TextUtils.isEmpty(currentVendorListIabHash) && !currentVendorListIabHash.equals(PersonalInfoManager.this.mPersonalInfoData.getCurrentVendorListIabHash()) && !TextUtils.isEmpty(currentVendorListIabFormat)) {
                PersonalInfoManager.this.mPersonalInfoData.setCurrentVendorListIabFormat(currentVendorListIabFormat);
                PersonalInfoManager.this.mPersonalInfoData.setCurrentVendorListIabHash(currentVendorListIabHash);
            }
            String extras = syncResponse.getExtras();
            if (!TextUtils.isEmpty(extras)) {
                PersonalInfoManager.this.mPersonalInfoData.setExtras(extras);
            }
            String consentChangeReason = syncResponse.getConsentChangeReason();
            if (syncResponse.isForceExplicitNo()) {
                PersonalInfoManager.this.mServerOverrideListener.onForceExplicitNo(consentChangeReason);
            } else if (syncResponse.isInvalidateConsent()) {
                PersonalInfoManager.this.mServerOverrideListener.onInvalidateConsent(consentChangeReason);
            } else if (syncResponse.isReacquireConsent()) {
                PersonalInfoManager.this.mServerOverrideListener.onReacquireConsent(consentChangeReason);
            }
            String callAgainAfterSecs = syncResponse.getCallAgainAfterSecs();
            if (!TextUtils.isEmpty(callAgainAfterSecs)) {
                try {
                    long parseLong = Long.parseLong(callAgainAfterSecs);
                    if (parseLong > 0) {
                        PersonalInfoManager.this.mSyncDelayMs = parseLong * 1000;
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("callAgainAfterSecs is not positive: ");
                        sb2.append(callAgainAfterSecs);
                        MoPubLog.d(sb2.toString());
                    }
                } catch (NumberFormatException unused) {
                    MoPubLog.d("Unable to parse callAgainAfterSecs. Ignoring value");
                }
            }
            if (!ConsentStatus.EXPLICIT_YES.equals(PersonalInfoManager.this.mSyncRequestConsentStatus)) {
                PersonalInfoManager.this.mPersonalInfoData.setUdid(null);
            }
            if (PersonalInfoManager.this.mForceGdprAppliesChangedSending) {
                PersonalInfoManager.this.mForceGdprAppliesChanged = false;
                PersonalInfoManager.this.mForceGdprAppliesChangedSending = false;
            }
            PersonalInfoManager.this.mPersonalInfoData.writeToDisk();
            PersonalInfoManager.this.mSyncRequestInFlight = false;
            if (ConsentStatus.POTENTIAL_WHITELIST.equals(PersonalInfoManager.this.mSyncRequestConsentStatus) && PersonalInfoManager.this.mPersonalInfoData.isWhitelisted()) {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.EXPLICIT_YES, ConsentChangeReason.GRANTED_BY_WHITELISTED_PUB);
                PersonalInfoManager.this.requestSync(true);
            }
            if (PersonalInfoManager.this.mSdkInitializationListener != null) {
                PersonalInfoManager.this.mSdkInitializationListener.onInitializationFinished();
                PersonalInfoManager.this.mSdkInitializationListener = null;
            }
        }

        public void onErrorResponse(VolleyError volleyError) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed sync request because of ");
            sb.append(volleyError instanceof MoPubNetworkError ? ((MoPubNetworkError) volleyError).getReason() : volleyError.getMessage());
            MoPubLog.d(sb.toString());
            PersonalInfoManager.this.mSyncRequestInFlight = false;
            if (PersonalInfoManager.this.mSdkInitializationListener != null) {
                MoPubLog.d("Personal Info Manager initialization finished but ran into errors.");
                PersonalInfoManager.this.mSdkInitializationListener.onInitializationFinished();
                PersonalInfoManager.this.mSdkInitializationListener = null;
            }
        }
    }

    public PersonalInfoManager(@NonNull Context context, @NonNull String str, @Nullable SdkInitializationListener sdkInitializationListener) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        this.mAppContext = context.getApplicationContext();
        this.mConsentStatusChangeListeners = Collections.synchronizedSet(new HashSet());
        this.mSyncRequestListener = new PersonalInfoSyncRequestListener();
        this.mServerOverrideListener = new PersonalInfoServerOverrideListener();
        MultiAdResponse.setServerOverrideListener(this.mServerOverrideListener);
        this.mConsentDialogController = new ConsentDialogController(this.mAppContext);
        this.mPersonalInfoData = new PersonalInfoData(this.mAppContext, str);
        this.mConversionTracker = new MoPubConversionTracker(this.mAppContext);
        AnonymousClass1 r3 = new AdvertisingIdChangeListener() {
            public void onIdChanged(@NonNull AdvertisingId advertisingId, @NonNull AdvertisingId advertisingId2) {
                Preconditions.checkNotNull(advertisingId);
                Preconditions.checkNotNull(advertisingId2);
                if (advertisingId.isDoNotTrack() && advertisingId2.isDoNotTrack()) {
                    return;
                }
                if (!advertisingId.isDoNotTrack() && advertisingId2.isDoNotTrack()) {
                    PersonalInfoManager.this.attemptStateTransition(ConsentStatus.DNT, ConsentChangeReason.DENIED_BY_DNT_ON);
                    PersonalInfoManager.this.requestSync(true);
                } else if (!advertisingId.isDoNotTrack() || advertisingId2.isDoNotTrack()) {
                    if (!TextUtils.isEmpty(advertisingId2.mAdvertisingId) && !advertisingId2.getIfaWithPrefix().equals(PersonalInfoManager.this.mPersonalInfoData.getUdid()) && ConsentStatus.EXPLICIT_YES.equals(PersonalInfoManager.this.mPersonalInfoData.getConsentStatus())) {
                        PersonalInfoManager.this.mPersonalInfoData.setLastSuccessfullySyncedConsentStatus(null);
                        PersonalInfoManager.this.mPersonalInfoData.setLastChangedMs(null);
                        PersonalInfoManager.this.attemptStateTransition(ConsentStatus.UNKNOWN, ConsentChangeReason.IFA_CHANGED);
                    }
                } else if (ConsentStatus.EXPLICIT_NO.equals(PersonalInfoManager.this.mPersonalInfoData.getConsentStatusBeforeDnt())) {
                    PersonalInfoManager.this.attemptStateTransition(ConsentStatus.EXPLICIT_NO, ConsentChangeReason.DNT_OFF);
                } else {
                    PersonalInfoManager.this.attemptStateTransition(ConsentStatus.UNKNOWN, ConsentChangeReason.DNT_OFF);
                }
            }
        };
        this.mSdkInitializationListener = sdkInitializationListener;
        MoPubIdentifier moPubIdentifier = ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier();
        moPubIdentifier.setIdChangeListener(r3);
        moPubIdentifier.setInitializationListener(createInitializationListener());
    }

    public boolean shouldShowConsentDialog() {
        if (gdprApplies() == null || !gdprApplies().booleanValue() || ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            return false;
        }
        if (!this.mPersonalInfoData.shouldReacquireConsent() || !this.mPersonalInfoData.getConsentStatus().equals(ConsentStatus.EXPLICIT_YES)) {
            return this.mPersonalInfoData.getConsentStatus().equals(ConsentStatus.UNKNOWN);
        }
        return true;
    }

    public boolean isConsentDialogReady() {
        return this.mConsentDialogController.isReady();
    }

    public void loadConsentDialog(@Nullable final ConsentDialogListener consentDialogListener) {
        ManifestUtils.checkGdprActivitiesDeclared(this.mAppContext);
        if (ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            if (consentDialogListener != null) {
                new Handler().post(new Runnable() {
                    public void run() {
                        consentDialogListener.onConsentDialogLoadFailed(MoPubErrorCode.DO_NOT_TRACK);
                    }
                });
            }
            return;
        }
        Boolean gdprApplies = gdprApplies();
        if (gdprApplies == null || gdprApplies.booleanValue()) {
            this.mConsentDialogController.loadConsentDialog(consentDialogListener, gdprApplies, this.mPersonalInfoData);
            return;
        }
        if (consentDialogListener != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    consentDialogListener.onConsentDialogLoadFailed(MoPubErrorCode.GDPR_DOES_NOT_APPLY);
                }
            });
        }
    }

    public boolean showConsentDialog() {
        return this.mConsentDialogController.showConsentDialog();
    }

    public boolean canCollectPersonalInformation() {
        Boolean gdprApplies = gdprApplies();
        boolean z = false;
        if (gdprApplies == null) {
            return false;
        }
        if (!gdprApplies.booleanValue()) {
            return true;
        }
        if (getPersonalInfoConsentStatus().equals(ConsentStatus.EXPLICIT_YES) && !ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            z = true;
        }
        return z;
    }

    @Nullable
    public Boolean gdprApplies() {
        if (this.mPersonalInfoData.isForceGdprApplies()) {
            return Boolean.valueOf(true);
        }
        return this.mPersonalInfoData.getGdprApplies();
    }

    public void forceGdprApplies() {
        if (!this.mPersonalInfoData.isForceGdprApplies()) {
            boolean canCollectPersonalInformation = canCollectPersonalInformation();
            this.mPersonalInfoData.setForceGdprApplies(true);
            this.mForceGdprAppliesChanged = true;
            this.mPersonalInfoData.writeToDisk();
            boolean canCollectPersonalInformation2 = canCollectPersonalInformation();
            if (canCollectPersonalInformation != canCollectPersonalInformation2) {
                fireOnConsentStateChangeListeners(this.mPersonalInfoData.getConsentStatus(), this.mPersonalInfoData.getConsentStatus(), canCollectPersonalInformation2);
            }
            requestSync(true);
        }
    }

    @NonNull
    public ConsentStatus getPersonalInfoConsentStatus() {
        return this.mPersonalInfoData.getConsentStatus();
    }

    public void grantConsent() {
        if (ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            MoPubLog.e("Cannot grant consent because Do Not Track is on.");
            return;
        }
        if (this.mPersonalInfoData.isWhitelisted()) {
            attemptStateTransition(ConsentStatus.EXPLICIT_YES, ConsentChangeReason.GRANTED_BY_WHITELISTED_PUB);
        } else {
            MoPubLog.w("You do not have approval to use the grantConsent API. Please reach out to your account teams or support@mopub.com for more information.");
            attemptStateTransition(ConsentStatus.POTENTIAL_WHITELIST, ConsentChangeReason.GRANTED_BY_NOT_WHITELISTED_PUB);
        }
        requestSync(true);
    }

    public void revokeConsent() {
        if (ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            MoPubLog.e("Cannot revoke consent because Do Not Track is on.");
            return;
        }
        attemptStateTransition(ConsentStatus.EXPLICIT_NO, ConsentChangeReason.DENIED_BY_PUB);
        requestSync(true);
    }

    /* access modifiers changed from: 0000 */
    public void changeConsentStateFromDialog(@NonNull ConsentStatus consentStatus) {
        Preconditions.checkNotNull(consentStatus);
        switch (consentStatus) {
            case EXPLICIT_YES:
                attemptStateTransition(consentStatus, ConsentChangeReason.GRANTED_BY_USER);
                requestSync(true);
                return;
            case EXPLICIT_NO:
                attemptStateTransition(consentStatus, ConsentChangeReason.DENIED_BY_USER);
                requestSync(true);
                return;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid consent status: ");
                sb.append(consentStatus);
                sb.append(". This is a bug with the use of changeConsentStateFromDialog.");
                MoPubLog.d(sb.toString());
                return;
        }
    }

    public void subscribeConsentStatusChangeListener(@Nullable ConsentStatusChangeListener consentStatusChangeListener) {
        if (consentStatusChangeListener != null) {
            this.mConsentStatusChangeListeners.add(consentStatusChangeListener);
        }
    }

    public void unsubscribeConsentStatusChangeListener(@Nullable ConsentStatusChangeListener consentStatusChangeListener) {
        this.mConsentStatusChangeListeners.remove(consentStatusChangeListener);
    }

    @VisibleForTesting
    static boolean shouldMakeSyncRequest(boolean z, @Nullable Boolean bool, boolean z2, @Nullable Long l, long j, @Nullable String str, boolean z3) {
        if (z) {
            return false;
        }
        boolean z4 = true;
        if (bool == null) {
            return true;
        }
        if (!bool.booleanValue()) {
            return false;
        }
        if (z2) {
            return true;
        }
        if (z3 && TextUtils.isEmpty(str)) {
            return false;
        }
        if (l == null) {
            return true;
        }
        if (SystemClock.uptimeMillis() - l.longValue() <= j) {
            z4 = false;
        }
        return z4;
    }

    public void requestSync(boolean z) {
        if (MoPub.isSdkInitialized()) {
            if (shouldMakeSyncRequest(this.mSyncRequestInFlight, gdprApplies(), z, this.mLastSyncRequestTimeUptimeMs, this.mSyncDelayMs, this.mPersonalInfoData.getUdid(), ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack())) {
                requestSync();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void requestSync() {
        this.mSyncRequestConsentStatus = this.mPersonalInfoData.getConsentStatus();
        this.mSyncRequestEpochTime = Calendar.getInstance().getTimeInMillis();
        this.mSyncRequestInFlight = true;
        this.mLastSyncRequestTimeUptimeMs = Long.valueOf(SystemClock.uptimeMillis());
        SyncUrlGenerator syncUrlGenerator = new SyncUrlGenerator(this.mAppContext, this.mSyncRequestConsentStatus.getValue());
        syncUrlGenerator.withAdUnitId(this.mPersonalInfoData.getAdUnitId()).withUdid(this.mPersonalInfoData.getUdid()).withLastChangedMs(this.mPersonalInfoData.getLastChangedMs()).withLastConsentStatus(this.mPersonalInfoData.getLastSuccessfullySyncedConsentStatus()).withConsentChangeReason(this.mPersonalInfoData.getConsentChangeReason()).withConsentedVendorListVersion(this.mPersonalInfoData.getConsentedVendorListVersion()).withConsentedPrivacyPolicyVersion(this.mPersonalInfoData.getConsentedPrivacyPolicyVersion()).withCachedVendorListIabHash(this.mPersonalInfoData.getCurrentVendorListIabHash()).withExtras(this.mPersonalInfoData.getExtras()).withGdprApplies(gdprApplies()).withForceGdprApplies(this.mPersonalInfoData.isForceGdprApplies());
        if (this.mForceGdprAppliesChanged) {
            this.mForceGdprAppliesChangedSending = true;
            syncUrlGenerator.withForceGdprAppliesChanged(Boolean.valueOf(true));
        }
        Networking.getRequestQueue(this.mAppContext).add(new SyncRequest(this.mAppContext, syncUrlGenerator.generateUrlString(Constants.HOST), this.mSyncRequestListener));
    }

    public ConsentData getConsentData() {
        return new PersonalInfoData(this.mAppContext, this.mPersonalInfoData.getAdUnitId());
    }

    /* access modifiers changed from: private */
    public void attemptStateTransition(@NonNull ConsentStatus consentStatus, @NonNull ConsentChangeReason consentChangeReason) {
        attemptStateTransition(consentStatus, consentChangeReason.getReason());
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void attemptStateTransition(@NonNull ConsentStatus consentStatus, @NonNull String str) {
        Preconditions.checkNotNull(consentStatus);
        Preconditions.checkNotNull(str);
        ConsentStatus consentStatus2 = this.mPersonalInfoData.getConsentStatus();
        if (consentStatus2.equals(consentStatus)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Consent status is already ");
            sb.append(consentStatus2);
            sb.append(". Not doing a state transition.");
            MoPubLog.d(sb.toString());
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Changing consent status from ");
        sb2.append(consentStatus2);
        sb2.append("to ");
        sb2.append(consentStatus);
        sb2.append(" because ");
        sb2.append(str);
        MoPubLog.d(sb2.toString());
        this.mPersonalInfoData.setConsentChangeReason(str);
        this.mPersonalInfoData.setConsentStatus(consentStatus);
        if (ConsentStatus.POTENTIAL_WHITELIST.equals(consentStatus) || (!ConsentStatus.POTENTIAL_WHITELIST.equals(consentStatus2) && ConsentStatus.EXPLICIT_YES.equals(consentStatus))) {
            PersonalInfoData personalInfoData = this.mPersonalInfoData;
            personalInfoData.setConsentedPrivacyPolicyVersion(personalInfoData.getCurrentPrivacyPolicyVersion());
            PersonalInfoData personalInfoData2 = this.mPersonalInfoData;
            personalInfoData2.setConsentedVendorListVersion(personalInfoData2.getCurrentVendorListVersion());
            PersonalInfoData personalInfoData3 = this.mPersonalInfoData;
            personalInfoData3.setConsentedVendorListIabFormat(personalInfoData3.getCurrentVendorListIabFormat());
        }
        if (ConsentStatus.DNT.equals(consentStatus) || ConsentStatus.EXPLICIT_NO.equals(consentStatus) || ConsentStatus.UNKNOWN.equals(consentStatus)) {
            this.mPersonalInfoData.setConsentedPrivacyPolicyVersion(null);
            this.mPersonalInfoData.setConsentedVendorListVersion(null);
            this.mPersonalInfoData.setConsentedVendorListIabFormat(null);
        }
        if (ConsentStatus.EXPLICIT_YES.equals(consentStatus)) {
            this.mPersonalInfoData.setUdid(ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().getIfaWithPrefix());
        }
        if (ConsentStatus.DNT.equals(consentStatus)) {
            this.mPersonalInfoData.setConsentStatusBeforeDnt(consentStatus2);
        }
        this.mPersonalInfoData.setShouldReacquireConsent(false);
        this.mPersonalInfoData.writeToDisk();
        boolean canCollectPersonalInformation = canCollectPersonalInformation();
        if (canCollectPersonalInformation) {
            ClientMetadata.getInstance(this.mAppContext).repopulateCountryData();
            if (this.mConversionTracker.shouldTrack()) {
                this.mConversionTracker.reportAppOpen(false);
            }
        }
        fireOnConsentStateChangeListeners(consentStatus2, consentStatus, canCollectPersonalInformation);
    }

    /* access modifiers changed from: private */
    public void fireOnConsentStateChangeListeners(@NonNull ConsentStatus consentStatus, @NonNull ConsentStatus consentStatus2, boolean z) {
        synchronized (this.mConsentStatusChangeListeners) {
            for (final ConsentStatusChangeListener consentStatusChangeListener : this.mConsentStatusChangeListeners) {
                Handler handler = new Handler(Looper.getMainLooper());
                final ConsentStatus consentStatus3 = consentStatus;
                final ConsentStatus consentStatus4 = consentStatus2;
                final boolean z2 = z;
                AnonymousClass4 r3 = new Runnable() {
                    public void run() {
                        consentStatusChangeListener.onConsentStateChange(consentStatus3, consentStatus4, z2);
                    }
                };
                handler.post(r3);
            }
        }
    }

    private SdkInitializationListener createInitializationListener() {
        return new SdkInitializationListener() {
            public void onInitializationFinished() {
                MoPubLog.d("MoPubIdentifier initialized.");
                if (PersonalInfoManager.shouldMakeSyncRequest(PersonalInfoManager.this.mSyncRequestInFlight, PersonalInfoManager.this.gdprApplies(), false, PersonalInfoManager.this.mLastSyncRequestTimeUptimeMs, PersonalInfoManager.this.mSyncDelayMs, PersonalInfoManager.this.mPersonalInfoData.getUdid(), ClientMetadata.getInstance(PersonalInfoManager.this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack())) {
                    PersonalInfoManager.this.requestSync();
                } else if (PersonalInfoManager.this.mSdkInitializationListener != null) {
                    PersonalInfoManager.this.mSdkInitializationListener.onInitializationFinished();
                    PersonalInfoManager.this.mSdkInitializationListener = null;
                }
                new MoPubConversionTracker(PersonalInfoManager.this.mAppContext).reportAppOpen(true);
            }
        };
    }
}
