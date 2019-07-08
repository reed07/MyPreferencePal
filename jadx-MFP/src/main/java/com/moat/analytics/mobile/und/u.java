package com.moat.analytics.mobile.und;

import android.media.MediaPlayer;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

class u extends h<MediaPlayer> implements NativeVideoTracker {
    u(String str) {
        super(str);
        p.a(3, "NativeVideoTracker", (Object) this, "In initialization method.");
        StringBuilder sb = new StringBuilder();
        sb.append(a());
        sb.append(" created");
        p.a("[SUCCESS] ", sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        return "NativeVideoTracker";
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> g() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.j.get();
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public Integer j() {
        return Integer.valueOf(((MediaPlayer) this.j.get()).getCurrentPosition());
    }

    /* access modifiers changed from: protected */
    public boolean k() {
        return ((MediaPlayer) this.j.get()).isPlaying();
    }

    /* access modifiers changed from: protected */
    public Integer l() {
        return Integer.valueOf(((MediaPlayer) this.j.get()).getDuration());
    }

    /* renamed from: trackVideoAd */
    public boolean a(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        String str;
        StringBuilder sb;
        String str2;
        if (mediaPlayer == null) {
            p.a(3, "NativeVideoTracker", (Object) this, "Null player instance. Not tracking.");
            str = "[ERROR] ";
            sb = new StringBuilder();
            sb.append(a());
            str2 = " tracking didn't start, Null player instance";
        } else {
            try {
                mediaPlayer.getCurrentPosition();
                return super.a(map, mediaPlayer, view);
            } catch (Exception unused) {
                p.a(3, "NativeVideoTracker", (Object) this, "Playback has already completed. Not tracking.");
                str = "[ERROR] ";
                sb = new StringBuilder();
                sb.append(a());
                sb.append(" tracking didn't started for ");
                sb.append(e());
                str2 = ", playback has already completed";
            }
        }
        sb.append(str2);
        p.a(str, sb.toString());
        return false;
    }
}
