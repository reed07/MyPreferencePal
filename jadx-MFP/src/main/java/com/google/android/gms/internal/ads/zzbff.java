package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.brightcove.player.event.EventType;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzark
public final class zzbff implements zzu<zzbdz> {
    private boolean zzewj;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            zzwu.zzpv();
            return zzbat.zza(context, Integer.parseInt(str2));
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length());
            sb.append("Could not parse ");
            sb.append(str);
            sb.append(" in a video GMSG: ");
            sb.append(str2);
            zzaxz.zzeo(sb.toString());
            return i;
        }
    }

    private static void zza(zzbdk zzbdk, Map<String, String> map) {
        String str = (String) map.get("minBufferMs");
        String str2 = (String) map.get("maxBufferMs");
        String str3 = (String) map.get("bufferForPlaybackMs");
        String str4 = (String) map.get("bufferForPlaybackAfterRebufferMs");
        if (str != null) {
            try {
                zzbdk.zzcz(Integer.parseInt(str));
            } catch (NumberFormatException unused) {
                zzaxz.zzeo(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
                return;
            }
        }
        if (str2 != null) {
            zzbdk.zzda(Integer.parseInt(str2));
        }
        if (str3 != null) {
            zzbdk.zzdb(Integer.parseInt(str3));
        }
        if (str4 != null) {
            zzbdk.zzdc(Integer.parseInt(str4));
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        int i;
        String[] split;
        zzbdz zzbdz = (zzbdz) obj;
        String str = (String) map.get("action");
        if (str == null) {
            zzaxz.zzeo("Action missing from video GMSG.");
            return;
        }
        if (zzaxz.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String jSONObject2 = jSONObject.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(jSONObject2).length());
            sb.append("Video GMSG: ");
            sb.append(str);
            sb.append(" ");
            sb.append(jSONObject2);
            zzaxz.zzdn(sb.toString());
        }
        if ("background".equals(str)) {
            String str2 = (String) map.get("color");
            if (TextUtils.isEmpty(str2)) {
                zzaxz.zzeo("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzbdz.setBackgroundColor(Color.parseColor(str2));
            } catch (IllegalArgumentException unused) {
                zzaxz.zzeo("Invalid color parameter in video GMSG.");
            }
        } else {
            if ("decoderProps".equals(str)) {
                String str3 = (String) map.get("mimeTypes");
                if (str3 == null) {
                    zzaxz.zzeo("No MIME types specified for decoder properties inspection.");
                    zzbdk.zza(zzbdz, "missingMimeTypes");
                } else if (VERSION.SDK_INT < 16) {
                    zzaxz.zzeo("Video decoder properties available on API versions >= 16.");
                    zzbdk.zza(zzbdz, "deficientApiVersion");
                } else {
                    HashMap hashMap = new HashMap();
                    for (String str4 : str3.split(",")) {
                        hashMap.put(str4, zzbar.zzeh(str4.trim()));
                    }
                    zzbdk.zza(zzbdz, (Map<String, List<Map<String, Object>>>) hashMap);
                }
            } else {
                zzbdq zzabt = zzbdz.zzabt();
                if (zzabt == null) {
                    zzaxz.zzeo("Could not get underlay container for a video GMSG.");
                    return;
                }
                boolean equals = "new".equals(str);
                boolean equals2 = "position".equals(str);
                if (equals || equals2) {
                    Context context = zzbdz.getContext();
                    int zza = zza(context, map, AvidJSONUtil.KEY_X, 0);
                    int zza2 = zza(context, map, "y", 0);
                    int zza3 = zza(context, map, "w", -1);
                    int zza4 = zza(context, map, "h", -1);
                    int min = Math.min(zza3, zzbdz.zzacb() - zza);
                    int min2 = Math.min(zza4, zzbdz.zzaca() - zza2);
                    try {
                        i = Integer.parseInt((String) map.get("player"));
                    } catch (NumberFormatException unused2) {
                        i = 0;
                    }
                    boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                    if (!equals || zzabt.zzabp() != null) {
                        zzabt.zze(zza, zza2, min, min2);
                        return;
                    }
                    zzabt.zza(zza, zza2, min, min2, i, parseBoolean, new zzbdy((String) map.get("flags")));
                    zzbdk zzabp = zzabt.zzabp();
                    if (zzabp != null) {
                        zza(zzabp, map);
                    }
                    return;
                }
                zzbgw zzabu = zzbdz.zzabu();
                if (zzabu != null) {
                    if ("timeupdate".equals(str)) {
                        String str5 = (String) map.get("currentTime");
                        if (str5 == null) {
                            zzaxz.zzeo("currentTime parameter missing from timeupdate video GMSG.");
                            return;
                        }
                        try {
                            zzabu.zze(Float.parseFloat(str5));
                        } catch (NumberFormatException unused3) {
                            String str6 = "Could not parse currentTime parameter from timeupdate video GMSG: ";
                            String valueOf = String.valueOf(str5);
                            zzaxz.zzeo(valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
                        }
                    } else if ("skip".equals(str)) {
                        zzabu.zzaew();
                    }
                }
                zzbdk zzabp2 = zzabt.zzabp();
                if (zzabp2 == null) {
                    zzbdk.zzb(zzbdz);
                } else if ("click".equals(str)) {
                    Context context2 = zzbdz.getContext();
                    int zza5 = zza(context2, map, AvidJSONUtil.KEY_X, 0);
                    int zza6 = zza(context2, map, "y", 0);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) zza5, (float) zza6, 0);
                    zzabp2.zzf(obtain);
                    obtain.recycle();
                } else if ("currentTime".equals(str)) {
                    String str7 = (String) map.get(TimestampAnalyticsHelper.ATTR_TIME);
                    if (str7 == null) {
                        zzaxz.zzeo("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        zzabp2.seekTo((int) (Float.parseFloat(str7) * 1000.0f));
                    } catch (NumberFormatException unused4) {
                        String str8 = "Could not parse time parameter from currentTime video GMSG: ";
                        String valueOf2 = String.valueOf(str7);
                        zzaxz.zzeo(valueOf2.length() != 0 ? str8.concat(valueOf2) : new String(str8));
                    }
                } else if (MessengerShareContentUtility.SHARE_BUTTON_HIDE.equals(str)) {
                    zzabp2.setVisibility(4);
                } else if ("load".equals(str)) {
                    zzabp2.zzff();
                } else if ("loadControl".equals(str)) {
                    zza(zzabp2, map);
                } else if ("muted".equals(str)) {
                    if (Boolean.parseBoolean((String) map.get("muted"))) {
                        zzabp2.zzabj();
                    } else {
                        zzabp2.zzabk();
                    }
                } else if (EventType.PAUSE.equals(str)) {
                    zzabp2.pause();
                } else if (EventType.PLAY.equals(str)) {
                    zzabp2.play();
                } else if ("show".equals(str)) {
                    zzabp2.setVisibility(0);
                } else if ("src".equals(str)) {
                    zzabp2.zzer((String) map.get("src"));
                } else if ("touchMove".equals(str)) {
                    Context context3 = zzbdz.getContext();
                    zzabp2.zza((float) zza(context3, map, "dx", 0), (float) zza(context3, map, "dy", 0));
                    if (!this.zzewj) {
                        zzbdz.zzvw();
                        this.zzewj = true;
                    }
                } else if ("volume".equals(str)) {
                    String str9 = (String) map.get("volume");
                    if (str9 == null) {
                        zzaxz.zzeo("Level parameter missing from volume video GMSG.");
                        return;
                    }
                    try {
                        zzabp2.setVolume(Float.parseFloat(str9));
                    } catch (NumberFormatException unused5) {
                        String str10 = "Could not parse volume parameter from volume video GMSG: ";
                        String valueOf3 = String.valueOf(str9);
                        zzaxz.zzeo(valueOf3.length() != 0 ? str10.concat(valueOf3) : new String(str10));
                    }
                } else if ("watermark".equals(str)) {
                    zzabp2.zzabl();
                } else {
                    String str11 = "Unknown video action: ";
                    String valueOf4 = String.valueOf(str);
                    zzaxz.zzeo(valueOf4.length() != 0 ? str11.concat(valueOf4) : new String(str11));
                }
            }
        }
    }
}
