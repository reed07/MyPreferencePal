package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.OnCompleteListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zax implements zabs {
    private final Looper zabj;
    private final GoogleApiManager zabm;
    /* access modifiers changed from: private */
    public final Lock zaen;
    private final ClientSettings zaes;
    /* access modifiers changed from: private */
    public final Map<AnyClientKey<?>, zaw<?>> zaet = new HashMap();
    /* access modifiers changed from: private */
    public final Map<AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
    private final Map<Api<?>, Boolean> zaev;
    /* access modifiers changed from: private */
    public final zaaw zaew;
    private final GoogleApiAvailabilityLight zaex;
    /* access modifiers changed from: private */
    public final Condition zaey;
    private final boolean zaez;
    /* access modifiers changed from: private */
    public final boolean zafa;
    private final Queue<ApiMethodImpl<?, ?>> zafb = new LinkedList();
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zafc;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public Map<zai<?>, ConnectionResult> zafd;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public Map<zai<?>, ConnectionResult> zafe;
    @GuardedBy("mLock")
    private zaaa zaff;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public ConnectionResult zafg;

    public zax(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<AnyClientKey<?>, Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList, zaaw zaaw, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zaen = lock;
        this.zabj = looper;
        this.zaey = lock.newCondition();
        this.zaex = googleApiAvailabilityLight;
        this.zaew = zaaw;
        this.zaev = map2;
        this.zaes = clientSettings;
        this.zaez = z;
        HashMap hashMap = new HashMap();
        for (Api api : map2.keySet()) {
            hashMap.put(api.getClientKey(), api);
        }
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zaq zaq = (zaq) obj;
            hashMap2.put(zaq.mApi, zaq);
        }
        boolean z5 = true;
        boolean z6 = false;
        boolean z7 = true;
        boolean z8 = false;
        for (Entry entry : map.entrySet()) {
            Api api2 = (Api) hashMap.get(entry.getKey());
            Client client = (Client) entry.getValue();
            if (!client.requiresGooglePlayServices()) {
                z2 = z6;
                z4 = z8;
                z3 = false;
            } else if (!((Boolean) this.zaev.get(api2)).booleanValue()) {
                z3 = z7;
                z4 = true;
                z2 = true;
            } else {
                z3 = z7;
                z4 = z8;
                z2 = true;
            }
            zaw zaw = r1;
            zaw zaw2 = new zaw(context, api2, looper, client, (zaq) hashMap2.get(api2), clientSettings, abstractClientBuilder);
            this.zaet.put((AnyClientKey) entry.getKey(), zaw);
            if (client.requiresSignIn()) {
                this.zaeu.put((AnyClientKey) entry.getKey(), zaw);
            }
            z8 = z4;
            z7 = z3;
            z6 = z2;
        }
        if (!z6 || z7 || z8) {
            z5 = false;
        }
        this.zafa = z5;
        this.zabm = GoogleApiManager.zabc();
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public final void zaw() {
    }

    public final <A extends AnyClient, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        if (this.zaez && zab(t)) {
            return t;
        }
        if (!isConnected()) {
            this.zafb.add(t);
            return t;
        }
        this.zaew.zahe.zab(t);
        return ((zaw) this.zaet.get(t.getClientKey())).doRead(t);
    }

    public final <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        AnyClientKey clientKey = t.getClientKey();
        if (this.zaez && zab(t)) {
            return t;
        }
        this.zaew.zahe.zab(t);
        return ((zaw) this.zaet.get(clientKey)).doWrite(t);
    }

    private final <T extends ApiMethodImpl<? extends Result, ? extends AnyClient>> boolean zab(@NonNull T t) {
        AnyClientKey clientKey = t.getClientKey();
        ConnectionResult zaa = zaa(clientKey);
        if (zaa == null || zaa.getErrorCode() != 4) {
            return false;
        }
        t.setFailedResult(new Status(4, null, this.zabm.zaa(((zaw) this.zaet.get(clientKey)).zak(), System.identityHashCode(this.zaew))));
        return true;
    }

    public final void connect() {
        this.zaen.lock();
        try {
            if (!this.zafc) {
                this.zafc = true;
                this.zafd = null;
                this.zafe = null;
                this.zaff = null;
                this.zafg = null;
                this.zabm.zao();
                this.zabm.zaa((Iterable<? extends GoogleApi<?>>) this.zaet.values()).addOnCompleteListener((Executor) new HandlerExecutor(this.zabj), (OnCompleteListener<TResult>) new zaz<TResult>(this));
                this.zaen.unlock();
            }
        } finally {
            this.zaen.unlock();
        }
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zaey.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafg;
        if (connectionResult != null) {
            return connectionResult;
        }
        return new ConnectionResult(13, null);
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            } else {
                nanos = this.zaey.awaitNanos(nanos);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafg;
        if (connectionResult != null) {
            return connectionResult;
        }
        return new ConnectionResult(13, null);
    }

    public final void disconnect() {
        this.zaen.lock();
        try {
            this.zafc = false;
            this.zafd = null;
            this.zafe = null;
            if (this.zaff != null) {
                this.zaff.cancel();
                this.zaff = null;
            }
            this.zafg = null;
            while (!this.zafb.isEmpty()) {
                ApiMethodImpl apiMethodImpl = (ApiMethodImpl) this.zafb.remove();
                apiMethodImpl.zaa((zacs) null);
                apiMethodImpl.cancel();
            }
            this.zaey.signalAll();
        } finally {
            this.zaen.unlock();
        }
    }

    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zaa(api.getClientKey());
    }

    @Nullable
    private final ConnectionResult zaa(@NonNull AnyClientKey<?> anyClientKey) {
        this.zaen.lock();
        try {
            zaw zaw = (zaw) this.zaet.get(anyClientKey);
            if (this.zafd != null && zaw != null) {
                return (ConnectionResult) this.zafd.get(zaw.zak());
            }
            this.zaen.unlock();
            return null;
        } finally {
            this.zaen.unlock();
        }
    }

    public final boolean isConnected() {
        this.zaen.lock();
        try {
            return this.zafd != null && this.zafg == null;
        } finally {
            this.zaen.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zaen.lock();
        try {
            return this.zafd == null && this.zafc;
        } finally {
            this.zaen.unlock();
        }
    }

    private final boolean zaac() {
        this.zaen.lock();
        try {
            if (this.zafc) {
                if (this.zaez) {
                    for (AnyClientKey zaa : this.zaeu.keySet()) {
                        ConnectionResult zaa2 = zaa(zaa);
                        if (zaa2 != null) {
                            if (!zaa2.isSuccess()) {
                            }
                        }
                        this.zaen.unlock();
                        return false;
                    }
                    this.zaen.unlock();
                    return true;
                }
            }
            return false;
        } finally {
            this.zaen.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaen.lock();
        try {
            if (!this.zafc || zaac()) {
                this.zaen.unlock();
                return false;
            }
            this.zabm.zao();
            this.zaff = new zaaa(this, signInConnectionListener);
            this.zabm.zaa((Iterable<? extends GoogleApi<?>>) this.zaeu.values()).addOnCompleteListener((Executor) new HandlerExecutor(this.zabj), (OnCompleteListener<TResult>) this.zaff);
            this.zaen.unlock();
            return true;
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    public final void maybeSignOut() {
        this.zaen.lock();
        try {
            this.zabm.maybeSignOut();
            if (this.zaff != null) {
                this.zaff.cancel();
                this.zaff = null;
            }
            if (this.zafe == null) {
                this.zafe = new ArrayMap(this.zaeu.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zaw zak : this.zaeu.values()) {
                this.zafe.put(zak.zak(), connectionResult);
            }
            if (this.zafd != null) {
                this.zafd.putAll(this.zafe);
            }
        } finally {
            this.zaen.unlock();
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaad() {
        ClientSettings clientSettings = this.zaes;
        if (clientSettings == null) {
            this.zaew.zagz = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(clientSettings.getRequiredScopes());
        Map optionalApiSettings = this.zaes.getOptionalApiSettings();
        for (Api api : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(((OptionalApiSettings) optionalApiSettings.get(api)).mScopes);
            }
        }
        this.zaew.zagz = hashSet;
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaae() {
        while (!this.zafb.isEmpty()) {
            execute((ApiMethodImpl) this.zafb.remove());
        }
        this.zaew.zab((Bundle) null);
    }

    /* access modifiers changed from: private */
    public final boolean zaa(zaw<?> zaw, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && ((Boolean) this.zaev.get(zaw.getApi())).booleanValue() && zaw.zaab().requiresGooglePlayServices() && this.zaex.isUserResolvableError(connectionResult.getErrorCode());
    }

    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("mLock")
    public final ConnectionResult zaaf() {
        ConnectionResult connectionResult = null;
        ConnectionResult connectionResult2 = null;
        int i = 0;
        int i2 = 0;
        for (zaw zaw : this.zaet.values()) {
            Api api = zaw.getApi();
            ConnectionResult connectionResult3 = (ConnectionResult) this.zafd.get(zaw.zak());
            if (!connectionResult3.isSuccess() && (!((Boolean) this.zaev.get(api)).booleanValue() || connectionResult3.hasResolution() || this.zaex.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() != 4 || !this.zaez) {
                    int priority = api.zah().getPriority();
                    if (connectionResult == null || i > priority) {
                        connectionResult = connectionResult3;
                        i = priority;
                    }
                } else {
                    int priority2 = api.zah().getPriority();
                    if (connectionResult2 == null || i2 > priority2) {
                        connectionResult2 = connectionResult3;
                        i2 = priority2;
                    }
                }
            }
        }
        return (connectionResult == null || connectionResult2 == null || i <= i2) ? connectionResult : connectionResult2;
    }
}
