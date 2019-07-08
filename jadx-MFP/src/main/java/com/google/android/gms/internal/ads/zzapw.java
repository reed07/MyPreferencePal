package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.myfitnesspal.shared.db.table.ImagesTable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzapw implements Callable<zzaxf> {
    @VisibleForTesting
    private static long zzdtd = 10;
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock = new Object();
    private final zzaba zzbln;
    private final zzaqp zzbqa;
    private final zzcu zzdcf;
    private final zzaxg zzdsk;
    private final zzazs zzdte;
    /* access modifiers changed from: private */
    public final zzbb zzdtf;
    private boolean zzdtg;
    private List<String> zzdth;
    private JSONObject zzdti;
    private String zzdtj;
    @Nullable
    private String zzdtk;

    public zzapw(Context context, zzbb zzbb, zzazs zzazs, zzcu zzcu, zzaxg zzaxg, zzaba zzaba) {
        this.mContext = context;
        this.zzdtf = zzbb;
        this.zzdte = zzazs;
        this.zzdsk = zzaxg;
        this.zzdcf = zzcu;
        this.zzbln = zzaba;
        this.zzbqa = zzbb.zzkn();
        this.zzdtg = false;
        this.mErrorCode = -2;
        this.zzdth = null;
        this.zzdtj = null;
        this.zzdtk = null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        if (r3.length() != 0) goto L_0x0044;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0071 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0078 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007b A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0081 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b7 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x017d A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x017f A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01f3 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x022e  */
    /* renamed from: zzwd */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzaxf call() {
        /*
            r15 = this;
            r0 = 0
            r1 = 0
            com.google.android.gms.ads.internal.zzbb r2 = r15.zzdtf     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r11 = r2.getUuid()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            boolean r2 = r15.zzwe()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r2 != 0) goto L_0x006e
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaxg r3 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzasm r3 = r3.zzehy     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r3 = r3.zzdyb     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r2.<init>(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaxg r4 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzasm r4 = r4.zzehy     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r4 = r4.zzdyb     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r3.<init>(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            int r4 = r3.length()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r4 == 0) goto L_0x0040
            java.lang.String r4 = "ads"
            org.json.JSONArray r3 = r3.optJSONArray(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r3 == 0) goto L_0x0037
            org.json.JSONObject r3 = r3.optJSONObject(r0)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x0038
        L_0x0037:
            r3 = r1
        L_0x0038:
            if (r3 == 0) goto L_0x0040
            int r3 = r3.length()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r3 != 0) goto L_0x0044
        L_0x0040:
            r3 = 3
            r15.zzcs(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
        L_0x0044:
            com.google.android.gms.internal.ads.zzaqp r3 = r15.zzbqa     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzbcb r2 = r3.zzh(r2)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            long r3 = zzdtd     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.Object r2 = r2.get(r3, r5)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r3 = "success"
            boolean r3 = r2.optBoolean(r3, r0)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r3 == 0) goto L_0x006e
            java.lang.String r3 = "json"
            org.json.JSONObject r2 = r2.getJSONObject(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r3 = "ads"
            org.json.JSONArray r2 = r2.optJSONArray(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            org.json.JSONObject r2 = r2.getJSONObject(r0)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r8 = r2
            goto L_0x006f
        L_0x006e:
            r8 = r1
        L_0x006f:
            if (r8 == 0) goto L_0x0078
            java.lang.String r2 = "enable_omid"
            boolean r2 = r8.optBoolean(r2)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x0079
        L_0x0078:
            r2 = 0
        L_0x0079:
            if (r2 != 0) goto L_0x0081
            com.google.android.gms.internal.ads.zzbca r3 = com.google.android.gms.internal.ads.zzbbq.zzm(r1)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r12 = r3
            goto L_0x00b1
        L_0x0081:
            java.lang.String r3 = "omid_settings"
            org.json.JSONObject r3 = r8.optJSONObject(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r3 != 0) goto L_0x008f
            com.google.android.gms.internal.ads.zzbca r3 = com.google.android.gms.internal.ads.zzbbq.zzm(r1)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r12 = r3
            goto L_0x00b1
        L_0x008f:
            java.lang.String r4 = "omid_html"
            java.lang.String r3 = r3.optString(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r4 == 0) goto L_0x00a1
            com.google.android.gms.internal.ads.zzbca r3 = com.google.android.gms.internal.ads.zzbbq.zzm(r1)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r12 = r3
            goto L_0x00b1
        L_0x00a1:
            com.google.android.gms.internal.ads.zzbca r4 = com.google.android.gms.internal.ads.zzbbq.zzm(r1)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzapx r5 = new com.google.android.gms.internal.ads.zzapx     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r5.<init>(r15, r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.util.concurrent.Executor r3 = com.google.android.gms.internal.ads.zzbcg.zzepo     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzbcb r3 = com.google.android.gms.internal.ads.zzbbq.zza(r4, r5, r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r12 = r3
        L_0x00b1:
            boolean r3 = r15.zzwe()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r3 != 0) goto L_0x0172
            if (r8 != 0) goto L_0x00bb
            goto L_0x0172
        L_0x00bb:
            java.lang.String r3 = "template_id"
            java.lang.String r3 = r8.getString(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r4 = "instream"
            java.lang.String r5 = "type"
            java.lang.String r5 = r8.optString(r5)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            boolean r4 = r4.equals(r5)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaxg r5 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzasi r5 = r5.zzeag     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzacp r5 = r5.zzbti     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r5 == 0) goto L_0x00df
            com.google.android.gms.internal.ads.zzaxg r5 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzasi r5 = r5.zzeag     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzacp r5 = r5.zzbti     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            boolean r5 = r5.zzdcs     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x00e0
        L_0x00df:
            r5 = 0
        L_0x00e0:
            com.google.android.gms.internal.ads.zzaxg r6 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzasi r6 = r6.zzeag     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzacp r6 = r6.zzbti     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r6 == 0) goto L_0x00f1
            com.google.android.gms.internal.ads.zzaxg r6 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzasi r6 = r6.zzeag     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzacp r6 = r6.zzbti     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            boolean r6 = r6.zzdcu     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x00f2
        L_0x00f1:
            r6 = 0
        L_0x00f2:
            if (r4 == 0) goto L_0x00fb
            com.google.android.gms.internal.ads.zzapv r3 = new com.google.android.gms.internal.ads.zzapv     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r3.<init>()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x0173
        L_0x00fb:
            java.lang.String r4 = "2"
            boolean r4 = r4.equals(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r4 == 0) goto L_0x010d
            com.google.android.gms.internal.ads.zzaqq r3 = new com.google.android.gms.internal.ads.zzaqq     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaxg r4 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            boolean r4 = r4.zzehx     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r3.<init>(r5, r6, r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x0173
        L_0x010d:
            java.lang.String r4 = "1"
            boolean r4 = r4.equals(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r4 == 0) goto L_0x011f
            com.google.android.gms.internal.ads.zzaqr r3 = new com.google.android.gms.internal.ads.zzaqr     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaxg r4 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            boolean r4 = r4.zzehx     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r3.<init>(r5, r6, r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x0173
        L_0x011f:
            java.lang.String r4 = "3"
            boolean r3 = r4.equals(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r3 == 0) goto L_0x016d
            java.lang.String r3 = "custom_template_id"
            java.lang.String r3 = r8.getString(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzbcl r4 = new com.google.android.gms.internal.ads.zzbcl     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r4.<init>()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            android.os.Handler r6 = com.google.android.gms.internal.ads.zzayh.zzelc     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzapz r7 = new com.google.android.gms.internal.ads.zzapz     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r7.<init>(r15, r4, r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r6.post(r7)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            long r6 = zzdtd     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.Object r3 = r4.get(r6, r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r3 == 0) goto L_0x014c
            com.google.android.gms.internal.ads.zzaqs r3 = new com.google.android.gms.internal.ads.zzaqs     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r3.<init>(r5)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x0173
        L_0x014c:
            java.lang.String r3 = "No handler for custom template: "
            java.lang.String r4 = "custom_template_id"
            java.lang.String r4 = r8.getString(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            int r5 = r4.length()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r5 == 0) goto L_0x0163
            java.lang.String r3 = r3.concat(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x0169
        L_0x0163:
            java.lang.String r4 = new java.lang.String     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r4.<init>(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r3 = r4
        L_0x0169:
            com.google.android.gms.internal.ads.zzaxz.e(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x0170
        L_0x016d:
            r15.zzcs(r0)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
        L_0x0170:
            r3 = r1
            goto L_0x0173
        L_0x0172:
            r3 = r1
        L_0x0173:
            boolean r4 = r15.zzwe()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r4 != 0) goto L_0x01ee
            if (r3 == 0) goto L_0x01ee
            if (r8 != 0) goto L_0x017f
            goto L_0x01ee
        L_0x017f:
            java.lang.String r4 = "tracking_urls_and_actions"
            org.json.JSONObject r4 = r8.getJSONObject(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r5 = "impression_tracking_urls"
            org.json.JSONArray r5 = r4.optJSONArray(r5)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r5 != 0) goto L_0x0190
            r6 = r1
            goto L_0x01a6
        L_0x0190:
            int r6 = r5.length()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r7 = 0
        L_0x0197:
            int r9 = r5.length()     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r7 >= r9) goto L_0x01a6
            java.lang.String r9 = r5.getString(r7)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r6[r7] = r9     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            int r7 = r7 + 1
            goto L_0x0197
        L_0x01a6:
            if (r6 != 0) goto L_0x01aa
            r5 = r1
            goto L_0x01ae
        L_0x01aa:
            java.util.List r5 = java.util.Arrays.asList(r6)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
        L_0x01ae:
            r15.zzdth = r5     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r5 = "active_view"
            org.json.JSONObject r4 = r4.optJSONObject(r5)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r15.zzdti = r4     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r4 = "debug_signals"
            java.lang.String r4 = r8.optString(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r15.zzdtj = r4     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r4 = "omid_settings"
            java.lang.String r4 = r8.optString(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r15.zzdtk = r4     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzacf r13 = r3.zza(r15, r8)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r13 != 0) goto L_0x01d5
            java.lang.String r3 = "Failed to retrieve ad assets."
            com.google.android.gms.internal.ads.zzaxz.e(r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r13 = r1
            goto L_0x01ef
        L_0x01d5:
            com.google.android.gms.internal.ads.zzach r14 = new com.google.android.gms.internal.ads.zzach     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            android.content.Context r4 = r15.mContext     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.ads.internal.zzbb r5 = r15.zzdtf     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaqp r6 = r15.zzbqa     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzcu r7 = r15.zzdcf     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaxg r3 = r15.zzdsk     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzasi r3 = r3.zzeag     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzbbi r10 = r3.zzbsp     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r3 = r14
            r9 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r13.zzb(r14)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            goto L_0x01ef
        L_0x01ee:
            r13 = r1
        L_0x01ef:
            boolean r3 = r13 instanceof com.google.android.gms.internal.ads.zzabw     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            if (r3 == 0) goto L_0x0202
            r3 = r13
            com.google.android.gms.internal.ads.zzabw r3 = (com.google.android.gms.internal.ads.zzabw) r3     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaqa r4 = new com.google.android.gms.internal.ads.zzaqa     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r4.<init>(r15, r3)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzaqp r3 = r15.zzbqa     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            java.lang.String r5 = "/nativeAdCustomClick"
            r3.zza(r5, r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
        L_0x0202:
            com.google.android.gms.internal.ads.zzaxf r2 = r15.zza(r13, r2)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.ads.internal.zzbb r3 = r15.zzdtf     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            com.google.android.gms.internal.ads.zzbgg r4 = zzb(r12)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            r3.zzg(r4)     // Catch:{ CancellationException -> 0x0224, ExecutionException -> 0x0222, InterruptedException -> 0x0220, JSONException -> 0x021e, TimeoutException -> 0x0217, Exception -> 0x0210 }
            return r2
        L_0x0210:
            r2 = move-exception
            java.lang.String r3 = "Error occured while doing native ads initialization."
            com.google.android.gms.internal.ads.zzaxz.zzc(r3, r2)
            goto L_0x022a
        L_0x0217:
            r2 = move-exception
            java.lang.String r3 = "Timeout when loading native ad."
            com.google.android.gms.internal.ads.zzaxz.zzc(r3, r2)
            goto L_0x022a
        L_0x021e:
            r2 = move-exception
            goto L_0x0225
        L_0x0220:
            r2 = move-exception
            goto L_0x0225
        L_0x0222:
            r2 = move-exception
            goto L_0x0225
        L_0x0224:
            r2 = move-exception
        L_0x0225:
            java.lang.String r3 = "Malformed native JSON response."
            com.google.android.gms.internal.ads.zzaxz.zzc(r3, r2)
        L_0x022a:
            boolean r2 = r15.zzdtg
            if (r2 != 0) goto L_0x0231
            r15.zzcs(r0)
        L_0x0231:
            com.google.android.gms.internal.ads.zzaxf r0 = r15.zza(r1, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapw.call():com.google.android.gms.internal.ads.zzaxf");
    }

    private static zzbgg zzb(zzbcb<zzbgg> zzbcb) {
        try {
            return (zzbgg) zzbcb.get((long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcui)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzbbd.zzc("", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzbbd.zzc("", e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final void zzc(zzadx zzadx, String str) {
        try {
            zzaeh zzar = this.zzdtf.zzar(zzadx.getCustomTemplateId());
            if (zzar != null) {
                zzar.zzb(zzadx, str);
            }
        } catch (RemoteException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40);
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            zzaxz.zzc(sb.toString(), e);
        }
    }

    private final zzaxf zza(zzacf zzacf, boolean z) {
        int i;
        synchronized (this.mLock) {
            i = (zzacf == null && this.mErrorCode == -2) ? 0 : this.mErrorCode;
        }
        zzaxf zzaxf = new zzaxf(this.zzdsk.zzeag.zzdwg, null, this.zzdsk.zzehy.zzdlq, i, this.zzdsk.zzehy.zzdlr, this.zzdth, this.zzdsk.zzehy.orientation, this.zzdsk.zzehy.zzdlx, this.zzdsk.zzeag.zzdwj, false, null, null, null, null, null, 0, this.zzdsk.zzbst, this.zzdsk.zzehy.zzdyc, this.zzdsk.zzehn, this.zzdsk.zzeho, this.zzdsk.zzehy.zzdyi, this.zzdti, i != -2 ? null : zzacf, null, null, null, this.zzdsk.zzehy.zzdyu, this.zzdsk.zzehy.zzdyv, null, this.zzdsk.zzehy.zzdlu, this.zzdtj, this.zzdsk.zzehw, this.zzdsk.zzehy.zzbph, this.zzdsk.zzehx, z, this.zzdsk.zzehy.zzdls, this.zzdsk.zzehy.zzbpi, this.zzdtk, this.zzdsk.zzehy.zzdzf);
        return zzaxf;
    }

    public final zzbcb<zzabm> zzg(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return zzbbq.zzm(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int i = (this.zzdsk.zzeag.zzbti == null || this.zzdsk.zzeag.zzbti.versionCode < 2) ? 1 : this.zzdsk.zzeag.zzbti.zzdcv;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List<zzbcb> arrayList = new ArrayList<>();
        if (optJSONObject.optJSONArray(ImagesTable.TABLE_NAME) != null) {
            arrayList = zza(optJSONObject, ImagesTable.TABLE_NAME, false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        zzbcl zzbcl = new zzbcl();
        int size = arrayList.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzbcb zza : arrayList) {
            List list = arrayList;
            zza.zza(new zzaqd(atomicInteger, size, zzbcl, arrayList), zzayf.zzeky);
            arrayList = list;
        }
        zzbcl zzbcl2 = zzbcl;
        zzaqb zzaqb = new zzaqb(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i, optBoolean);
        return zzbbq.zza((zzbcb<A>) zzbcl2, (zzbbm<A, B>) zzaqb, (Executor) zzayf.zzeky);
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    public final Future<zzabr> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    public final zzbcb<zzabr> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public final List<zzbcb<zzabr>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            zzh(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    private final zzbcb<zzabr> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String str;
        if (z) {
            str = jSONObject.getString("url");
        } else {
            str = jSONObject.optString("url");
        }
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (TextUtils.isEmpty(str)) {
            zzh(0, z);
            return zzbbq.zzm(null);
        } else if (z2) {
            return zzbbq.zzm(new zzabr(null, Uri.parse(str), optDouble));
        } else {
            zzazs zzazs = this.zzdte;
            zzaqc zzaqc = new zzaqc(this, z, optDouble, optBoolean, str);
            return zzazs.zza(str, zzaqc);
        }
    }

    public final zzbcb<zzbgg> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return zzbbq.zzm(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzaxz.zzeo("Required field 'vast_xml' is missing");
            return zzbbq.zzm(null);
        }
        zzaqf zza = zza(this.mContext, this.zzdcf, this.zzdsk, this.zzbln, this.zzdtf);
        boolean equals = "instream".equals(jSONObject.optString("type"));
        zzbcl zzbcl = new zzbcl();
        zzbcg.zzepo.execute(new zzaqg(zza, equals, optJSONObject, zzbcl));
        return zzbcl;
    }

    public final zzbcb<zzbgg> zza(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return zzbbq.zzm(null);
        }
        zzaqf zza = zza(this.mContext, this.zzdcf, this.zzdsk, this.zzbln, this.zzdtf);
        zzbcl zzbcl = new zzbcl();
        Executor executor = zzbcg.zzepo;
        zzaqh zzaqh = new zzaqh(zza, true, zzbcl, str, str2);
        executor.execute(zzaqh);
        return zzbcl;
    }

    private final boolean zzwe() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdtg;
        }
        return z;
    }

    private final void zzcs(int i) {
        synchronized (this.mLock) {
            this.zzdtg = true;
            this.mErrorCode = i;
        }
    }

    public final void zzh(int i, boolean z) {
        if (z) {
            zzcs(i);
        }
    }

    static zzbgg zzc(zzbcb<zzbgg> zzbcb) {
        try {
            return (zzbgg) zzbcb.get((long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcuh)).intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzaxz.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzaxz.zzc("Exception occurred while waiting for video to load", e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static <V> List<V> zzk(List<zzbcb<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (zzbcb zzbcb : list) {
            Object obj = zzbcb.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    private static zzaqf zza(Context context, zzcu zzcu, zzaxg zzaxg, zzaba zzaba, zzbb zzbb) {
        zzaqf zzaqf = new zzaqf(context, zzcu, zzaxg, zzaba, zzbb);
        return zzaqf;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ zzbcb zza(String str, Object obj) throws Exception {
        zzbv.zzlg();
        zzbgg zza = zzbgm.zza(this.mContext, zzbht.zzaey(), "native-omid", false, false, this.zzdcf, this.zzdsk.zzeag.zzbsp, this.zzbln, null, this.zzdtf.zzid(), this.zzdsk.zzehw);
        zzbck zzn = zzbck.zzn(zza);
        zza.zzadl().zza((zzbho) new zzapy(zzn));
        zza.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
        return zzn;
    }
}
