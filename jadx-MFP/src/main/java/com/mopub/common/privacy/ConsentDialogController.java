package com.mopub.common.privacy;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.privacy.ConsentDialogRequest.Listener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.VolleyError;

public class ConsentDialogController implements Listener {
    @NonNull
    private final Context mAppContext;
    @Nullable
    private ConsentDialogListener mExtListener;
    private final Handler mHandler = new Handler();
    @Nullable
    private String mHtmlBody;
    volatile boolean mReady;
    volatile boolean mRequestInFlight;

    /* renamed from: com.mopub.common.privacy.ConsentDialogController$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$network$MoPubNetworkError$Reason = new int[Reason.values().length];

        static {
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.BAD_BODY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    ConsentDialogController(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        this.mAppContext = context.getApplicationContext();
    }

    public void onSuccess(ConsentDialogResponse consentDialogResponse) {
        this.mRequestInFlight = false;
        this.mHtmlBody = consentDialogResponse.getHtml();
        if (TextUtils.isEmpty(this.mHtmlBody)) {
            this.mReady = false;
            ConsentDialogListener consentDialogListener = this.mExtListener;
            if (consentDialogListener != null) {
                consentDialogListener.onConsentDialogLoadFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
            return;
        }
        this.mReady = true;
        ConsentDialogListener consentDialogListener2 = this.mExtListener;
        if (consentDialogListener2 != null) {
            consentDialogListener2.onConsentDialogLoaded();
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        ConsentDialogListener consentDialogListener = this.mExtListener;
        resetState();
        if (consentDialogListener != null) {
            if (!(volleyError instanceof MoPubNetworkError) || AnonymousClass2.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()] != 1) {
                consentDialogListener.onConsentDialogLoadFailed(MoPubErrorCode.UNSPECIFIED);
            } else {
                consentDialogListener.onConsentDialogLoadFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void loadConsentDialog(@android.support.annotation.Nullable final com.mopub.common.privacy.ConsentDialogListener r6, @android.support.annotation.Nullable java.lang.Boolean r7, @android.support.annotation.NonNull com.mopub.common.privacy.PersonalInfoData r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.mopub.common.Preconditions.checkNotNull(r8)     // Catch:{ all -> 0x006d }
            boolean r0 = r5.mReady     // Catch:{ all -> 0x006d }
            if (r0 == 0) goto L_0x0016
            if (r6 == 0) goto L_0x0014
            android.os.Handler r7 = r5.mHandler     // Catch:{ all -> 0x006d }
            com.mopub.common.privacy.ConsentDialogController$1 r8 = new com.mopub.common.privacy.ConsentDialogController$1     // Catch:{ all -> 0x006d }
            r8.<init>(r6)     // Catch:{ all -> 0x006d }
            r7.post(r8)     // Catch:{ all -> 0x006d }
        L_0x0014:
            monitor-exit(r5)
            return
        L_0x0016:
            boolean r0 = r5.mRequestInFlight     // Catch:{ all -> 0x006d }
            if (r0 == 0) goto L_0x0021
            java.lang.String r6 = "Already making a consent dialog load request."
            com.mopub.common.logging.MoPubLog.d(r6)     // Catch:{ all -> 0x006d }
            monitor-exit(r5)
            return
        L_0x0021:
            r5.mExtListener = r6     // Catch:{ all -> 0x006d }
            r6 = 1
            r5.mRequestInFlight = r6     // Catch:{ all -> 0x006d }
            com.mopub.common.privacy.ConsentDialogRequest r6 = new com.mopub.common.privacy.ConsentDialogRequest     // Catch:{ all -> 0x006d }
            android.content.Context r0 = r5.mAppContext     // Catch:{ all -> 0x006d }
            com.mopub.common.privacy.ConsentDialogUrlGenerator r1 = new com.mopub.common.privacy.ConsentDialogUrlGenerator     // Catch:{ all -> 0x006d }
            android.content.Context r2 = r5.mAppContext     // Catch:{ all -> 0x006d }
            java.lang.String r3 = r8.getAdUnitId()     // Catch:{ all -> 0x006d }
            com.mopub.common.privacy.ConsentStatus r4 = r8.getConsentStatus()     // Catch:{ all -> 0x006d }
            java.lang.String r4 = r4.getValue()     // Catch:{ all -> 0x006d }
            r1.<init>(r2, r3, r4)     // Catch:{ all -> 0x006d }
            com.mopub.common.privacy.ConsentDialogUrlGenerator r7 = r1.withGdprApplies(r7)     // Catch:{ all -> 0x006d }
            java.lang.String r1 = r8.getConsentedPrivacyPolicyVersion()     // Catch:{ all -> 0x006d }
            com.mopub.common.privacy.ConsentDialogUrlGenerator r7 = r7.withConsentedPrivacyPolicyVersion(r1)     // Catch:{ all -> 0x006d }
            java.lang.String r1 = r8.getConsentedVendorListVersion()     // Catch:{ all -> 0x006d }
            com.mopub.common.privacy.ConsentDialogUrlGenerator r7 = r7.withConsentedVendorListVersion(r1)     // Catch:{ all -> 0x006d }
            boolean r8 = r8.isForceGdprApplies()     // Catch:{ all -> 0x006d }
            com.mopub.common.privacy.ConsentDialogUrlGenerator r7 = r7.withForceGdprApplies(r8)     // Catch:{ all -> 0x006d }
            java.lang.String r8 = "ads.mopub.com"
            java.lang.String r7 = r7.generateUrlString(r8)     // Catch:{ all -> 0x006d }
            r6.<init>(r0, r7, r5)     // Catch:{ all -> 0x006d }
            android.content.Context r7 = r5.mAppContext     // Catch:{ all -> 0x006d }
            com.mopub.network.MoPubRequestQueue r7 = com.mopub.network.Networking.getRequestQueue(r7)     // Catch:{ all -> 0x006d }
            r7.add(r6)     // Catch:{ all -> 0x006d }
            monitor-exit(r5)
            return
        L_0x006d:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.privacy.ConsentDialogController.loadConsentDialog(com.mopub.common.privacy.ConsentDialogListener, java.lang.Boolean, com.mopub.common.privacy.PersonalInfoData):void");
    }

    /* access modifiers changed from: 0000 */
    public boolean showConsentDialog() {
        if (!this.mReady || TextUtils.isEmpty(this.mHtmlBody)) {
            return false;
        }
        ConsentDialogActivity.start(this.mAppContext, this.mHtmlBody);
        resetState();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean isReady() {
        return this.mReady;
    }

    private void resetState() {
        this.mRequestInFlight = false;
        this.mReady = false;
        this.mExtListener = null;
        this.mHtmlBody = null;
    }
}
