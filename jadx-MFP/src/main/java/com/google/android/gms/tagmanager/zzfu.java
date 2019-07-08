package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

final class zzfu implements zzbe {
    private final String zzabl;
    private final zzfx zzbgn;
    private final zzfw zzbgo;
    private final Context zzri;

    @VisibleForTesting
    private zzfu(zzfx zzfx, Context context, zzfw zzfw) {
        this.zzbgn = zzfx;
        this.zzri = context.getApplicationContext();
        this.zzbgo = zzfw;
        String str = "GoogleTagManager";
        String str2 = "4.00";
        String str3 = VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        String str4 = null;
        if (!(locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
                sb.append("-");
                sb.append(locale.getCountry().toLowerCase());
            }
            str4 = sb.toString();
        }
        this.zzabl = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, Build.MODEL, Build.ID});
    }

    @VisibleForTesting
    zzfu(Context context, zzfw zzfw) {
        this(new zzfv(), context, zzfw);
    }

    public final boolean zzom() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzri.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdi.v("...no network connectivity");
        return false;
    }

    public final void zzf(List<zzbw> list) {
        HttpURLConnection zzc;
        int min = Math.min(list.size(), 40);
        boolean z = true;
        for (int i = 0; i < min; i++) {
            zzbw zzbw = (zzbw) list.get(i);
            URL zzd = zzd(zzbw);
            if (zzd == null) {
                zzdi.zzab("No destination: discarding hit.");
                this.zzbgo.zzb(zzbw);
            } else {
                InputStream inputStream = null;
                try {
                    zzc = this.zzbgn.zzc(zzd);
                    if (z) {
                        zzdn.zzw(this.zzri);
                        z = false;
                    }
                    zzc.setRequestProperty("User-Agent", this.zzabl);
                    int responseCode = zzc.getResponseCode();
                    InputStream inputStream2 = zzc.getInputStream();
                    if (responseCode != 200) {
                        StringBuilder sb = new StringBuilder(25);
                        sb.append("Bad response: ");
                        sb.append(responseCode);
                        zzdi.zzab(sb.toString());
                        this.zzbgo.zzc(zzbw);
                    } else {
                        this.zzbgo.zza(zzbw);
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    zzc.disconnect();
                } catch (IOException e) {
                    String str = "Exception sending hit: ";
                    String valueOf = String.valueOf(e.getClass().getSimpleName());
                    zzdi.zzab(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    zzdi.zzab(e.getMessage());
                    this.zzbgo.zzc(zzbw);
                } catch (Throwable th) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    zzc.disconnect();
                    throw th;
                }
            }
        }
    }

    @VisibleForTesting
    private static URL zzd(zzbw zzbw) {
        try {
            return new URL(zzbw.zzox());
        } catch (MalformedURLException unused) {
            zzdi.e("Error trying to parse the GTM url.");
            return null;
        }
    }
}
