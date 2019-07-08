package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzayc implements zzayb {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private boolean zzebp = true;
    @GuardedBy("mLock")
    private boolean zzebq = true;
    @GuardedBy("mLock")
    private boolean zzebr = true;
    @GuardedBy("mLock")
    private boolean zzeby = false;
    @GuardedBy("mLock")
    private String zzeis = "";
    @GuardedBy("mLock")
    private int zzejp = -1;
    private boolean zzekh;
    private final List<Runnable> zzeki = new ArrayList();
    private zzbcb<?> zzekj;
    @GuardedBy("mLock")
    @Nullable
    private zzsx zzekk = null;
    @GuardedBy("mLock")
    @Nullable
    private SharedPreferences zzekl;
    @GuardedBy("mLock")
    @Nullable
    private Editor zzekm;
    @GuardedBy("mLock")
    private boolean zzekn = false;
    @GuardedBy("mLock")
    @Nullable
    private String zzeko;
    @GuardedBy("mLock")
    @Nullable
    private String zzekp;
    @GuardedBy("mLock")
    private long zzekq = 0;
    @GuardedBy("mLock")
    private long zzekr = 0;
    @GuardedBy("mLock")
    private long zzeks = 0;
    @GuardedBy("mLock")
    private int zzekt = 0;
    @GuardedBy("mLock")
    private Set<String> zzeku = Collections.emptySet();
    @GuardedBy("mLock")
    private JSONObject zzekv = new JSONObject();

    public final void zza(Context context, String str, boolean z) {
        this.zzekj = zzayf.zzc(new zzayd(this, context, "admob"));
        this.zzekh = true;
    }

    private final void zzzp() {
        zzbcb<?> zzbcb = this.zzekj;
        if (zzbcb != null && !zzbcb.isDone()) {
            try {
                this.zzekj.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                zzaxz.zzc("Interrupted while waiting for preferences loaded.", e);
            } catch (CancellationException | ExecutionException | TimeoutException e2) {
                zzaxz.zzb("Fail to initialize AdSharedPreferenceManager.", e2);
            }
        }
    }

    private final Bundle zzzq() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.mLock) {
            bundle.putBoolean("use_https", this.zzebp);
            bundle.putBoolean("content_url_opted_out", this.zzebq);
            bundle.putBoolean("content_vertical_opted_out", this.zzebr);
            bundle.putBoolean("auto_collect_location", this.zzeby);
            bundle.putInt("version_code", this.zzekt);
            bundle.putStringArray("never_pool_slots", (String[]) this.zzeku.toArray(new String[this.zzeku.size()]));
            bundle.putString("app_settings_json", this.zzeis);
            bundle.putLong("app_settings_last_update_ms", this.zzekq);
            bundle.putLong("app_last_background_time_ms", this.zzekr);
            bundle.putInt("request_in_session_count", this.zzejp);
            bundle.putLong("first_ad_req_time_ms", this.zzeks);
            bundle.putString("native_advanced_settings", this.zzekv.toString());
            if (this.zzeko != null) {
                bundle.putString("content_url_hashes", this.zzeko);
            }
            if (this.zzekp != null) {
                bundle.putString("content_vertical_hashes", this.zzekp);
            }
        }
        return bundle;
    }

    private final void zzd(Bundle bundle) {
        zzayf.zzeky.execute(new zzaye(this));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzam(boolean r4) {
        /*
            r3 = this;
            r3.zzzp()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzebp     // Catch:{ all -> 0x0031 }
            if (r1 != r4) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x000c:
            r3.zzebp = r4     // Catch:{ all -> 0x0031 }
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x001e
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "use_https"
            r1.putBoolean(r2, r4)     // Catch:{ all -> 0x0031 }
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0031 }
            r1.apply()     // Catch:{ all -> 0x0031 }
        L_0x001e:
            boolean r1 = r3.zzekn     // Catch:{ all -> 0x0031 }
            if (r1 != 0) goto L_0x002f
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0031 }
            r1.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "use_https"
            r1.putBoolean(r2, r4)     // Catch:{ all -> 0x0031 }
            r3.zzd(r1)     // Catch:{ all -> 0x0031 }
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayc.zzam(boolean):void");
    }

    public final boolean zzzb() {
        boolean z;
        zzzp();
        synchronized (this.mLock) {
            if (!this.zzebp) {
                if (!this.zzekn) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public final void zzan(boolean z) {
        zzzp();
        synchronized (this.mLock) {
            if (this.zzebq != z) {
                this.zzebq = z;
                if (this.zzekm != null) {
                    this.zzekm.putBoolean("content_url_opted_out", z);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzebq);
                bundle.putBoolean("content_vertical_opted_out", this.zzebr);
                zzd(bundle);
            }
        }
    }

    public final boolean zzzc() {
        boolean z;
        zzzp();
        synchronized (this.mLock) {
            z = this.zzebq;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdq(@javax.annotation.Nullable java.lang.String r4) {
        /*
            r3 = this;
            r3.zzzp()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0032
            java.lang.String r1 = r3.zzeko     // Catch:{ all -> 0x0034 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            r3.zzeko = r4     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0023
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0034 }
            r1.apply()     // Catch:{ all -> 0x0034 }
        L_0x0023:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            r3.zzd(r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayc.zzdq(java.lang.String):void");
    }

    @Nullable
    public final String zzzd() {
        String str;
        zzzp();
        synchronized (this.mLock) {
            str = this.zzeko;
        }
        return str;
    }

    public final void zzao(boolean z) {
        zzzp();
        synchronized (this.mLock) {
            if (this.zzebr != z) {
                this.zzebr = z;
                if (this.zzekm != null) {
                    this.zzekm.putBoolean("content_vertical_opted_out", z);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzebq);
                bundle.putBoolean("content_vertical_opted_out", this.zzebr);
                zzd(bundle);
            }
        }
    }

    public final boolean zzze() {
        boolean z;
        zzzp();
        synchronized (this.mLock) {
            z = this.zzebr;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdr(@javax.annotation.Nullable java.lang.String r4) {
        /*
            r3 = this;
            r3.zzzp()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0032
            java.lang.String r1 = r3.zzekp     // Catch:{ all -> 0x0034 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            r3.zzekp = r4     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0023
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzekm     // Catch:{ all -> 0x0034 }
            r1.apply()     // Catch:{ all -> 0x0034 }
        L_0x0023:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            r3.zzd(r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayc.zzdr(java.lang.String):void");
    }

    @Nullable
    public final String zzzf() {
        String str;
        zzzp();
        synchronized (this.mLock) {
            str = this.zzekp;
        }
        return str;
    }

    public final void zzap(boolean z) {
        zzzp();
        synchronized (this.mLock) {
            if (this.zzeby != z) {
                this.zzeby = z;
                if (this.zzekm != null) {
                    this.zzekm.putBoolean("auto_collect_location", z);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("auto_collect_location", z);
                zzd(bundle);
            }
        }
    }

    public final boolean zzzg() {
        boolean z;
        zzzp();
        synchronized (this.mLock) {
            z = this.zzeby;
        }
        return z;
    }

    public final void zzcv(int i) {
        zzzp();
        synchronized (this.mLock) {
            if (this.zzekt != i) {
                this.zzekt = i;
                if (this.zzekm != null) {
                    this.zzekm.putInt("version_code", i);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("version_code", i);
                zzd(bundle);
            }
        }
    }

    public final int zzzh() {
        int i;
        zzzp();
        synchronized (this.mLock) {
            i = this.zzekt;
        }
        return i;
    }

    public final void zzds(String str) {
        zzzp();
        synchronized (this.mLock) {
            if (!this.zzeku.contains(str)) {
                this.zzeku.add(str);
                if (this.zzekm != null) {
                    this.zzekm.putStringSet("never_pool_slots", this.zzeku);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.zzeku.toArray(new String[this.zzeku.size()]));
                zzd(bundle);
            }
        }
    }

    public final void zzdt(String str) {
        zzzp();
        synchronized (this.mLock) {
            if (this.zzeku.contains(str)) {
                this.zzeku.remove(str);
                if (this.zzekm != null) {
                    this.zzekm.putStringSet("never_pool_slots", this.zzeku);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.zzeku.toArray(new String[this.zzeku.size()]));
                zzd(bundle);
            }
        }
    }

    public final boolean zzdu(String str) {
        boolean contains;
        zzzp();
        synchronized (this.mLock) {
            contains = this.zzeku.contains(str);
        }
        return contains;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdv(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzzp()
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzbv.zzlm()     // Catch:{ all -> 0x0060 }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x0060 }
            r5.zzekq = r1     // Catch:{ all -> 0x0060 }
            if (r6 == 0) goto L_0x005e
            java.lang.String r3 = r5.zzeis     // Catch:{ all -> 0x0060 }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x0060 }
            if (r3 == 0) goto L_0x001b
            goto L_0x005e
        L_0x001b:
            r5.zzeis = r6     // Catch:{ all -> 0x0060 }
            android.content.SharedPreferences$Editor r3 = r5.zzekm     // Catch:{ all -> 0x0060 }
            if (r3 == 0) goto L_0x0034
            android.content.SharedPreferences$Editor r3 = r5.zzekm     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x0060 }
            android.content.SharedPreferences$Editor r3 = r5.zzekm     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = "app_settings_last_update_ms"
            r3.putLong(r4, r1)     // Catch:{ all -> 0x0060 }
            android.content.SharedPreferences$Editor r3 = r5.zzekm     // Catch:{ all -> 0x0060 }
            r3.apply()     // Catch:{ all -> 0x0060 }
        L_0x0034:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x0060 }
            r3.<init>()     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x0060 }
            java.lang.String r6 = "app_settings_last_update_ms"
            r3.putLong(r6, r1)     // Catch:{ all -> 0x0060 }
            r5.zzd(r3)     // Catch:{ all -> 0x0060 }
            java.util.List<java.lang.Runnable> r6 = r5.zzeki     // Catch:{ all -> 0x0060 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x0060 }
        L_0x004c:
            boolean r1 = r6.hasNext()     // Catch:{ all -> 0x0060 }
            if (r1 == 0) goto L_0x005c
            java.lang.Object r1 = r6.next()     // Catch:{ all -> 0x0060 }
            java.lang.Runnable r1 = (java.lang.Runnable) r1     // Catch:{ all -> 0x0060 }
            r1.run()     // Catch:{ all -> 0x0060 }
            goto L_0x004c
        L_0x005c:
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x005e:
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x0060:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayc.zzdv(java.lang.String):void");
    }

    public final zzaxj zzzi() {
        zzaxj zzaxj;
        zzzp();
        synchronized (this.mLock) {
            zzaxj = new zzaxj(this.zzeis, this.zzekq);
        }
        return zzaxj;
    }

    public final void zzau(long j) {
        zzzp();
        synchronized (this.mLock) {
            if (this.zzekr != j) {
                this.zzekr = j;
                if (this.zzekm != null) {
                    this.zzekm.putLong("app_last_background_time_ms", j);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("app_last_background_time_ms", j);
                zzd(bundle);
            }
        }
    }

    public final long zzzj() {
        long j;
        zzzp();
        synchronized (this.mLock) {
            j = this.zzekr;
        }
        return j;
    }

    public final void zzcw(int i) {
        zzzp();
        synchronized (this.mLock) {
            if (this.zzejp != i) {
                this.zzejp = i;
                if (this.zzekm != null) {
                    this.zzekm.putInt("request_in_session_count", i);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("request_in_session_count", i);
                zzd(bundle);
            }
        }
    }

    public final int zzzk() {
        int i;
        zzzp();
        synchronized (this.mLock) {
            i = this.zzejp;
        }
        return i;
    }

    public final void zzav(long j) {
        zzzp();
        synchronized (this.mLock) {
            if (this.zzeks != j) {
                this.zzeks = j;
                if (this.zzekm != null) {
                    this.zzekm.putLong("first_ad_req_time_ms", j);
                    this.zzekm.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("first_ad_req_time_ms", j);
                zzd(bundle);
            }
        }
    }

    public final long zzzl() {
        long j;
        zzzp();
        synchronized (this.mLock) {
            j = this.zzeks;
        }
        return j;
    }

    public final void zzb(String str, String str2, boolean z) {
        zzzp();
        synchronized (this.mLock) {
            JSONArray optJSONArray = this.zzekv.optJSONArray(str);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
            }
            int length = optJSONArray.length();
            int i = 0;
            while (true) {
                if (i < optJSONArray.length()) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        if (!str2.equals(optJSONObject.optString("template_id"))) {
                            i++;
                        } else if (!z || optJSONObject.optBoolean("uses_media_view", false) != z) {
                            length = i;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("template_id", str2);
                jSONObject.put("uses_media_view", z);
                jSONObject.put("timestamp_ms", zzbv.zzlm().currentTimeMillis());
                optJSONArray.put(length, jSONObject);
                this.zzekv.put(str, optJSONArray);
            } catch (JSONException e) {
                zzaxz.zzc("Could not update native advanced settings", e);
            }
            if (this.zzekm != null) {
                this.zzekm.putString("native_advanced_settings", this.zzekv.toString());
                this.zzekm.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", this.zzekv.toString());
            zzd(bundle);
        }
    }

    public final JSONObject zzzm() {
        JSONObject jSONObject;
        zzzp();
        synchronized (this.mLock) {
            jSONObject = this.zzekv;
        }
        return jSONObject;
    }

    public final void zzzn() {
        zzzp();
        synchronized (this.mLock) {
            this.zzekv = new JSONObject();
            if (this.zzekm != null) {
                this.zzekm.remove("native_advanced_settings");
                this.zzekm.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            zzd(bundle);
        }
    }

    @Nullable
    public final zzsx zzzo() {
        if (!this.zzekh || !PlatformVersion.isAtLeastIceCreamSandwich()) {
            return null;
        }
        if (zzzc() && zzze()) {
            return null;
        }
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcpz)).booleanValue()) {
            return null;
        }
        synchronized (this.mLock) {
            if (Looper.getMainLooper() == null) {
                return null;
            }
            if (this.zzekk == null) {
                this.zzekk = new zzsx();
            }
            this.zzekk.zzns();
            zzaxz.zzen("start fetching content...");
            zzsx zzsx = this.zzekk;
            return zzsx;
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzm(Context context, String str) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        Editor edit = sharedPreferences.edit();
        synchronized (this.mLock) {
            this.zzekl = sharedPreferences;
            this.zzekm = edit;
            if (PlatformVersion.isAtLeastM() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                z = true;
            }
            this.zzekn = z;
            this.zzebp = this.zzekl.getBoolean("use_https", this.zzebp);
            this.zzebq = this.zzekl.getBoolean("content_url_opted_out", this.zzebq);
            this.zzeko = this.zzekl.getString("content_url_hashes", this.zzeko);
            this.zzeby = this.zzekl.getBoolean("auto_collect_location", this.zzeby);
            this.zzebr = this.zzekl.getBoolean("content_vertical_opted_out", this.zzebr);
            this.zzekp = this.zzekl.getString("content_vertical_hashes", this.zzekp);
            this.zzekt = this.zzekl.getInt("version_code", this.zzekt);
            this.zzeis = this.zzekl.getString("app_settings_json", this.zzeis);
            this.zzekq = this.zzekl.getLong("app_settings_last_update_ms", this.zzekq);
            this.zzekr = this.zzekl.getLong("app_last_background_time_ms", this.zzekr);
            this.zzejp = this.zzekl.getInt("request_in_session_count", this.zzejp);
            this.zzeks = this.zzekl.getLong("first_ad_req_time_ms", this.zzeks);
            this.zzeku = this.zzekl.getStringSet("never_pool_slots", this.zzeku);
            try {
                this.zzekv = new JSONObject(this.zzekl.getString("native_advanced_settings", "{}"));
            } catch (JSONException e) {
                zzaxz.zzc("Could not convert native advanced settings to json object", e);
            }
            zzd(zzzq());
        }
    }
}
