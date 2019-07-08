package com.google.android.gms.internal.icing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.appindexing.AppIndexApi.ActionResult;
import com.google.android.gms.appindexing.AppIndexApi.AppIndexingLink;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.mopub.common.Constants;
import java.util.List;

public final class zzai implements AppIndexApi {
    private static final String TAG = "zzai";

    @Deprecated
    static final class zza implements ActionResult {
        private zzai zzau;
        private PendingResult<Status> zzav;
        private Action zzaw;

        zza(zzai zzai, PendingResult<Status> pendingResult, Action action) {
            this.zzau = zzai;
            this.zzav = pendingResult;
            this.zzaw = action;
        }

        public final PendingResult<Status> end(GoogleApiClient googleApiClient) {
            String packageName = googleApiClient.getContext().getPackageName();
            zzx zza = zzah.zza(this.zzaw, System.currentTimeMillis(), packageName, 2);
            return this.zzau.zza(googleApiClient, zza);
        }

        public final PendingResult<Status> getPendingResult() {
            return this.zzav;
        }
    }

    static abstract class zzb<T extends Result> extends ApiMethodImpl<T, zzag> {
        public zzb(GoogleApiClient googleApiClient) {
            super(zze.zzg, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzab zzab) throws RemoteException;

        /* access modifiers changed from: protected */
        public /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
            zza((zzab) ((zzag) anyClient).getService());
        }
    }

    public static abstract class zzc<T extends Result> extends zzb<Status> {
        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    public static final class zzd extends zzaf<Status> {
        public zzd(ResultHolder<Status> resultHolder) {
            super(resultHolder);
        }

        public final void zza(Status status) {
            this.zzas.setResult(status);
        }
    }

    public final PendingResult<Status> zza(GoogleApiClient googleApiClient, zzx... zzxArr) {
        return googleApiClient.enqueue(new zzaj(this, googleApiClient, zzxArr));
    }

    public final PendingResult<Status> view(GoogleApiClient googleApiClient, Activity activity, Intent intent, String str, Uri uri, List<AppIndexingLink> list) {
        String packageName = googleApiClient.getContext().getPackageName();
        if (list != null) {
            for (AppIndexingLink appIndexingLink : list) {
                zzb(null, appIndexingLink.appIndexingUrl);
            }
        }
        zzx zzx = new zzx(packageName, intent, str, uri, null, list, 1);
        zzx[] zzxArr = {zzx};
        GoogleApiClient googleApiClient2 = googleApiClient;
        return zza(googleApiClient, zzxArr);
    }

    public final PendingResult<Status> viewEnd(GoogleApiClient googleApiClient, Activity activity, Intent intent) {
        return zza(googleApiClient, new zzy().zza(zzx.zza(googleApiClient.getContext().getPackageName(), intent)).zza(System.currentTimeMillis()).zzb(0).zzc(2).zzd());
    }

    public static Intent zza(String str, Uri uri) {
        zzb(str, uri);
        if (zza(uri)) {
            return new Intent("android.intent.action.VIEW", uri);
        }
        if (zzb(uri)) {
            String str2 = "android.intent.action.VIEW";
            List pathSegments = uri.getPathSegments();
            String str3 = (String) pathSegments.get(0);
            Builder builder = new Builder();
            builder.scheme(str3);
            if (pathSegments.size() > 1) {
                builder.authority((String) pathSegments.get(1));
                for (int i = 2; i < pathSegments.size(); i++) {
                    builder.appendPath((String) pathSegments.get(i));
                }
            } else {
                String str4 = TAG;
                String valueOf = String.valueOf(uri);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 88);
                sb.append("The app URI must have the format: android-app://<package_name>/<scheme>/<path>. But got ");
                sb.append(valueOf);
                Log.e(str4, sb.toString());
            }
            builder.encodedQuery(uri.getEncodedQuery());
            builder.encodedFragment(uri.getEncodedFragment());
            return new Intent(str2, builder.build());
        }
        String valueOf2 = String.valueOf(uri);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 70);
        sb2.append("appIndexingUri is neither an HTTP(S) URL nor an \"android-app://\" URL: ");
        sb2.append(valueOf2);
        throw new RuntimeException(sb2.toString());
    }

    private static void zzb(String str, Uri uri) {
        if (zza(uri)) {
            if (uri.getHost().isEmpty()) {
                String valueOf = String.valueOf(uri);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 98);
                sb.append("AppIndex: The web URL must have a host (follow the format http(s)://<host>/<path>). Provided URI: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        } else if (!zzb(uri)) {
            String valueOf2 = String.valueOf(uri);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 176);
            sb2.append("AppIndex: The URI scheme must either be 'http(s)' or 'android-app'. If the latter, it must follow the format 'android-app://<package_name>/<scheme>/<host_path>'. Provided URI: ");
            sb2.append(valueOf2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (str == null || str.equals(uri.getHost())) {
            List pathSegments = uri.getPathSegments();
            if (pathSegments.isEmpty() || ((String) pathSegments.get(0)).isEmpty()) {
                String valueOf3 = String.valueOf(uri);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 128);
                sb3.append("AppIndex: The app URI scheme must exist and follow the format android-app://<package_name>/<scheme>/<host_path>). Provided URI: ");
                sb3.append(valueOf3);
                throw new IllegalArgumentException(sb3.toString());
            }
        } else {
            String valueOf4 = String.valueOf(uri);
            StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf4).length() + 150);
            sb4.append("AppIndex: The android-app URI host must match the package name and follow the format android-app://<package_name>/<scheme>/<host_path>. Provided URI: ");
            sb4.append(valueOf4);
            throw new IllegalArgumentException(sb4.toString());
        }
    }

    private static boolean zza(Uri uri) {
        String scheme = uri.getScheme();
        return Constants.HTTP.equals(scheme) || Constants.HTTPS.equals(scheme);
    }

    private static boolean zzb(Uri uri) {
        return "android-app".equals(uri.getScheme());
    }

    public final PendingResult<Status> view(GoogleApiClient googleApiClient, Activity activity, Uri uri, String str, Uri uri2, List<AppIndexingLink> list) {
        String packageName = googleApiClient.getContext().getPackageName();
        zzb(packageName, uri);
        return view(googleApiClient, activity, zza(packageName, uri), str, uri2, list);
    }

    public final PendingResult<Status> viewEnd(GoogleApiClient googleApiClient, Activity activity, Uri uri) {
        return viewEnd(googleApiClient, activity, zza(googleApiClient.getContext().getPackageName(), uri));
    }

    public final ActionResult action(GoogleApiClient googleApiClient, Action action) {
        return new zza(this, zza(googleApiClient, action, 1), action);
    }

    public final PendingResult<Status> start(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 1);
    }

    public final PendingResult<Status> end(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 2);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, Action action, int i) {
        return zza(googleApiClient, zzah.zza(action, System.currentTimeMillis(), googleApiClient.getContext().getPackageName(), i));
    }
}
