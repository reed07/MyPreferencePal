package com.google.firebase.iid;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.brightcove.player.event.EventType;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.io.IOException;
import java.util.concurrent.Executor;

final class zzr implements MessagingChannel {
    private final FirebaseApp zzam;
    private final zzan zzan;
    private final zzat zzbi;
    private final Executor zzbj;

    zzr(FirebaseApp firebaseApp, zzan zzan2, Executor executor) {
        this(firebaseApp, zzan2, executor, new zzat(firebaseApp.getApplicationContext(), zzan2));
    }

    public final Task<Void> ackMessage(String str) {
        return null;
    }

    public final boolean isChannelBuilt() {
        return true;
    }

    @VisibleForTesting
    private zzr(FirebaseApp firebaseApp, zzan zzan2, Executor executor, zzat zzat) {
        this.zzam = firebaseApp;
        this.zzan = zzan2;
        this.zzbi = zzat;
        this.zzbj = executor;
    }

    public final boolean isAvailable() {
        return this.zzan.zzac() != 0;
    }

    public final Task<Void> buildChannel(String str, String str2) {
        return Tasks.forResult(null);
    }

    public final Task<String> getToken(String str, String str2, String str3, String str4) {
        return zzc(zza(str, str3, str4, new Bundle()));
    }

    public final Task<Void> deleteToken(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString(Attributes.DELETE, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        return zzb(zzc(zza(str, str3, str4, bundle)));
    }

    public final Task<Void> deleteInstanceId(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("iid-operation", Attributes.DELETE);
        bundle.putString(Attributes.DELETE, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        return zzb(zzc(zza(str, EventType.ANY, EventType.ANY, bundle)));
    }

    public final Task<Void> subscribeToTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String str4 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString(str4, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str3);
        return zzb(zzc(zza(str, str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle)));
    }

    public final Task<Void> unsubscribeFromTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String str4 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString(str4, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        bundle.putString(Attributes.DELETE, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str3);
        return zzb(zzc(zza(str, str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle)));
    }

    private final Task<Bundle> zza(String str, String str2, String str3, Bundle bundle) {
        bundle.putString("scope", str3);
        bundle.putString("sender", str2);
        bundle.putString("subtype", str2);
        bundle.putString("appid", str);
        bundle.putString("gmp_app_id", this.zzam.getOptions().getApplicationId());
        bundle.putString("gmsv", Integer.toString(this.zzan.zzaf()));
        bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
        bundle.putString("app_ver", this.zzan.zzad());
        bundle.putString("app_ver_name", this.zzan.zzae());
        bundle.putString("cliv", "fiid-12451000");
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzbj.execute(new zzs(this, bundle, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: private */
    public static String zza(Bundle bundle) throws IOException {
        if (bundle != null) {
            String string = bundle.getString("registration_id");
            if (string != null) {
                return string;
            }
            String string2 = bundle.getString("unregistered");
            if (string2 != null) {
                return string2;
            }
            String string3 = bundle.getString("error");
            if ("RST".equals(string3)) {
                throw new IOException("INSTANCE_ID_RESET");
            } else if (string3 != null) {
                throw new IOException(string3);
            } else {
                String valueOf = String.valueOf(bundle);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
                sb.append("Unexpected response: ");
                sb.append(valueOf);
                Log.w("FirebaseInstanceId", sb.toString(), new Throwable());
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        } else {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    private final <T> Task<Void> zzb(Task<T> task) {
        return task.continueWith(zzi.zze(), new zzt(this));
    }

    private final Task<String> zzc(Task<Bundle> task) {
        return task.continueWith(this.zzbj, new zzu(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Bundle bundle, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(this.zzbi.zzc(bundle));
        } catch (IOException e) {
            taskCompletionSource.setException(e);
        }
    }
}
