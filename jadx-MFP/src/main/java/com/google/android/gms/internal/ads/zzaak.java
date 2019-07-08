package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzaak implements OnSharedPreferenceChangeListener {
    private final Object lock = new Object();
    private final ConditionVariable zzcnn = new ConditionVariable();
    @VisibleForTesting
    private volatile boolean zzcno = false;
    /* access modifiers changed from: private */
    @Nullable
    public SharedPreferences zzcnp = null;
    private Context zzcnq;
    private JSONObject zzcnr = new JSONObject();
    private volatile boolean zztd = false;

    public final void initialize(Context context) {
        if (!this.zztd) {
            synchronized (this.lock) {
                if (!this.zztd) {
                    if (!this.zzcno) {
                        this.zzcno = true;
                    }
                    this.zzcnq = context.getApplicationContext() == null ? context : context.getApplicationContext();
                    try {
                        Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                        if (remoteContext == null && context != null) {
                            remoteContext = context.getApplicationContext();
                            if (remoteContext == null) {
                                remoteContext = context;
                            }
                        }
                        if (remoteContext != null) {
                            zzwu.zzpx();
                            this.zzcnp = remoteContext.getSharedPreferences("google_ads_flags", 0);
                            if (this.zzcnp != null) {
                                this.zzcnp.registerOnSharedPreferenceChangeListener(this);
                            }
                            zzqy();
                            this.zztd = true;
                            this.zzcno = false;
                            this.zzcnn.open();
                        }
                    } finally {
                        this.zzcno = false;
                        this.zzcnn.open();
                    }
                }
            }
        }
    }

    public final <T> T zzd(zzaac<T> zzaac) {
        if (!this.zzcnn.block(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS)) {
            synchronized (this.lock) {
                if (!this.zzcno) {
                    throw new IllegalStateException("Flags.initialize() was not called!");
                }
            }
        }
        if (!this.zztd || this.zzcnp == null) {
            synchronized (this.lock) {
                if (this.zztd) {
                    if (this.zzcnp == null) {
                    }
                }
                T zzqv = zzaac.zzqv();
                return zzqv;
            }
        }
        if (zzaac.getSource() != 1 || !this.zzcnr.has(zzaac.getKey())) {
            return zzbak.zza(this.zzcnq, new zzaam(this, zzaac));
        }
        return zzaac.zzb(this.zzcnr);
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("flag_configuration".equals(str)) {
            zzqy();
        }
    }

    private final void zzqy() {
        if (this.zzcnp != null) {
            try {
                this.zzcnr = new JSONObject((String) zzbak.zza(this.zzcnq, new zzaal(this)));
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String zzqz() throws Exception {
        return this.zzcnp.getString("flag_configuration", "{}");
    }
}
