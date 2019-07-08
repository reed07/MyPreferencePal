package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.zad;
import com.myfitnesspal.shared.constants.Constants.Analytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zaak implements zabd {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final AbstractClientBuilder<? extends zad, SignInOptions> zacd;
    /* access modifiers changed from: private */
    public final Lock zaen;
    private final ClientSettings zaes;
    private final Map<Api<?>, Boolean> zaev;
    /* access modifiers changed from: private */
    public final GoogleApiAvailabilityLight zaex;
    private ConnectionResult zafg;
    /* access modifiers changed from: private */
    public final zabe zafs;
    private int zafv;
    private int zafw = 0;
    private int zafx;
    private final Bundle zafy = new Bundle();
    private final Set<AnyClientKey> zafz = new HashSet();
    /* access modifiers changed from: private */
    public zad zaga;
    private boolean zagb;
    /* access modifiers changed from: private */
    public boolean zagc;
    private boolean zagd;
    /* access modifiers changed from: private */
    public IAccountAccessor zage;
    private boolean zagf;
    private boolean zagg;
    private ArrayList<Future<?>> zagh = new ArrayList<>();

    public zaak(zabe zabe, ClientSettings clientSettings, Map<Api<?>, Boolean> map, GoogleApiAvailabilityLight googleApiAvailabilityLight, AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Lock lock, Context context) {
        this.zafs = zabe;
        this.zaes = clientSettings;
        this.zaev = map;
        this.zaex = googleApiAvailabilityLight;
        this.zacd = abstractClientBuilder;
        this.zaen = lock;
        this.mContext = context;
    }

    private static String zad(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return Analytics.UNKNOWN;
        }
    }

    public final void connect() {
    }

    public final void begin() {
        this.zafs.zaho.clear();
        this.zagc = false;
        this.zafg = null;
        this.zafw = 0;
        this.zagb = true;
        this.zagd = false;
        this.zagf = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api api : this.zaev.keySet()) {
            Client client = (Client) this.zafs.zagy.get(api.getClientKey());
            z |= api.zah().getPriority() == 1;
            boolean booleanValue = ((Boolean) this.zaev.get(api)).booleanValue();
            if (client.requiresSignIn()) {
                this.zagc = true;
                if (booleanValue) {
                    this.zafz.add(api.getClientKey());
                } else {
                    this.zagb = false;
                }
            }
            hashMap.put(client, new zaam(this, api, booleanValue));
        }
        if (z) {
            this.zagc = false;
        }
        if (this.zagc) {
            this.zaes.setClientSessionId(Integer.valueOf(System.identityHashCode(this.zafs.zaed)));
            zaat zaat = new zaat(this, null);
            AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder = this.zacd;
            Context context = this.mContext;
            Looper looper = this.zafs.zaed.getLooper();
            ClientSettings clientSettings = this.zaes;
            this.zaga = (zad) abstractClientBuilder.buildClient(context, looper, clientSettings, clientSettings.getSignInOptions(), zaat, zaat);
        }
        this.zafx = this.zafs.zagy.size();
        this.zagh.add(zabh.zabb().submit(new zaan(this, hashMap)));
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zaao() {
        this.zafx--;
        int i = this.zafx;
        if (i > 0) {
            return false;
        }
        if (i < 0) {
            Log.w("GoogleApiClientConnecting", this.zafs.zaed.zaay());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zae(new ConnectionResult(8, null));
            return false;
        }
        ConnectionResult connectionResult = this.zafg;
        if (connectionResult == null) {
            return true;
        }
        this.zafs.zahr = this.zafv;
        zae(connectionResult);
        return false;
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaa(zaj zaj) {
        if (zac(0)) {
            ConnectionResult connectionResult = zaj.getConnectionResult();
            if (connectionResult.isSuccess()) {
                ResolveAccountResponse zacw = zaj.zacw();
                ConnectionResult connectionResult2 = zacw.getConnectionResult();
                if (!connectionResult2.isSuccess()) {
                    String valueOf = String.valueOf(connectionResult2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48);
                    sb.append("Sign-in succeeded with resolve account failure: ");
                    sb.append(valueOf);
                    Log.wtf("GoogleApiClientConnecting", sb.toString(), new Exception());
                    zae(connectionResult2);
                    return;
                }
                this.zagd = true;
                this.zage = zacw.getAccountAccessor();
                this.zagf = zacw.getSaveDefaultAccount();
                this.zagg = zacw.isFromCrossClientAuth();
                zaap();
            } else if (zad(connectionResult)) {
                zaar();
                zaap();
            } else {
                zae(connectionResult);
            }
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaap() {
        if (this.zafx == 0) {
            if (!this.zagc || this.zagd) {
                ArrayList arrayList = new ArrayList();
                this.zafw = 1;
                this.zafx = this.zafs.zagy.size();
                for (AnyClientKey anyClientKey : this.zafs.zagy.keySet()) {
                    if (!this.zafs.zaho.containsKey(anyClientKey)) {
                        arrayList.add((Client) this.zafs.zagy.get(anyClientKey));
                    } else if (zaao()) {
                        zaaq();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.zagh.add(zabh.zabb().submit(new zaaq(this, arrayList)));
                }
            }
        }
    }

    @GuardedBy("mLock")
    public final void onConnected(Bundle bundle) {
        if (zac(1)) {
            if (bundle != null) {
                this.zafy.putAll(bundle);
            }
            if (zaao()) {
                zaaq();
            }
        }
    }

    @GuardedBy("mLock")
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zac(1)) {
            zab(connectionResult, api, z);
            if (zaao()) {
                zaaq();
            }
        }
    }

    @GuardedBy("mLock")
    private final void zaaq() {
        this.zafs.zaba();
        zabh.zabb().execute(new zaal(this));
        zad zad = this.zaga;
        if (zad != null) {
            if (this.zagf) {
                zad.zaa(this.zage, this.zagg);
            }
            zab(false);
        }
        for (AnyClientKey anyClientKey : this.zafs.zaho.keySet()) {
            ((Client) this.zafs.zagy.get(anyClientKey)).disconnect();
        }
        this.zafs.zahs.zab(this.zafy.isEmpty() ? null : this.zafy);
    }

    public final <A extends AnyClient, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(T t) {
        this.zafs.zaed.zafb.add(t);
        return t;
    }

    public final <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public final boolean disconnect() {
        zaas();
        zab(true);
        this.zafs.zaf(null);
        return true;
    }

    @GuardedBy("mLock")
    public final void onConnectionSuspended(int i) {
        zae(new ConnectionResult(8, null));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (r7 != false) goto L_0x0025;
     */
    @javax.annotation.concurrent.GuardedBy("mLock")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zab(com.google.android.gms.common.ConnectionResult r5, com.google.android.gms.common.api.Api<?> r6, boolean r7) {
        /*
            r4 = this;
            com.google.android.gms.common.api.Api$BaseClientBuilder r0 = r6.zah()
            int r0 = r0.getPriority()
            r1 = 0
            r2 = 1
            if (r7 == 0) goto L_0x0025
            boolean r7 = r5.hasResolution()
            if (r7 == 0) goto L_0x0014
            r7 = 1
            goto L_0x0023
        L_0x0014:
            com.google.android.gms.common.GoogleApiAvailabilityLight r7 = r4.zaex
            int r3 = r5.getErrorCode()
            android.content.Intent r7 = r7.getErrorResolutionIntent(r3)
            if (r7 == 0) goto L_0x0022
            r7 = 1
            goto L_0x0023
        L_0x0022:
            r7 = 0
        L_0x0023:
            if (r7 == 0) goto L_0x002e
        L_0x0025:
            com.google.android.gms.common.ConnectionResult r7 = r4.zafg
            if (r7 == 0) goto L_0x002d
            int r7 = r4.zafv
            if (r0 >= r7) goto L_0x002e
        L_0x002d:
            r1 = 1
        L_0x002e:
            if (r1 == 0) goto L_0x0034
            r4.zafg = r5
            r4.zafv = r0
        L_0x0034:
            com.google.android.gms.common.api.internal.zabe r7 = r4.zafs
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.ConnectionResult> r7 = r7.zaho
            com.google.android.gms.common.api.Api$AnyClientKey r6 = r6.getClientKey()
            r7.put(r6, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaak.zab(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaar() {
        this.zagc = false;
        this.zafs.zaed.zagz = Collections.emptySet();
        for (AnyClientKey anyClientKey : this.zafz) {
            if (!this.zafs.zaho.containsKey(anyClientKey)) {
                this.zafs.zaho.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zad(ConnectionResult connectionResult) {
        return this.zagb && !connectionResult.hasResolution();
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zae(ConnectionResult connectionResult) {
        zaas();
        zab(!connectionResult.hasResolution());
        this.zafs.zaf(connectionResult);
        this.zafs.zahs.zac(connectionResult);
    }

    private final void zab(boolean z) {
        zad zad = this.zaga;
        if (zad != null) {
            if (zad.isConnected() && z) {
                this.zaga.zacv();
            }
            this.zaga.disconnect();
            this.zage = null;
        }
    }

    private final void zaas() {
        ArrayList arrayList = this.zagh;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Future) obj).cancel(true);
        }
        this.zagh.clear();
    }

    /* access modifiers changed from: private */
    public final Set<Scope> zaat() {
        ClientSettings clientSettings = this.zaes;
        if (clientSettings == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(clientSettings.getRequiredScopes());
        Map optionalApiSettings = this.zaes.getOptionalApiSettings();
        for (Api api : optionalApiSettings.keySet()) {
            if (!this.zafs.zaho.containsKey(api.getClientKey())) {
                hashSet.addAll(((OptionalApiSettings) optionalApiSettings.get(api)).mScopes);
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zac(int i) {
        if (this.zafw == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zafs.zaed.zaay());
        String valueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
        sb.append("Unexpected callback in ");
        sb.append(valueOf);
        Log.w("GoogleApiClientConnecting", sb.toString());
        int i2 = this.zafx;
        StringBuilder sb2 = new StringBuilder(33);
        sb2.append("mRemainingConnections=");
        sb2.append(i2);
        Log.w("GoogleApiClientConnecting", sb2.toString());
        String zad = zad(this.zafw);
        String zad2 = zad(i);
        StringBuilder sb3 = new StringBuilder(String.valueOf(zad).length() + 70 + String.valueOf(zad2).length());
        sb3.append("GoogleApiClient connecting is in step ");
        sb3.append(zad);
        sb3.append(" but received callback for step ");
        sb3.append(zad2);
        Log.wtf("GoogleApiClientConnecting", sb3.toString(), new Exception());
        zae(new ConnectionResult(8, null));
        return false;
    }
}
