package com.moat.analytics.mobile.inm;

import android.media.MediaPlayer;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class u extends h implements NativeVideoTracker {
    private WeakReference<MediaPlayer> m;

    u(String str) {
        super(str);
        p.a(3, "NativeVideoTracker", (Object) this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            StringBuilder sb = new StringBuilder("PartnerCode is ");
            sb.append(str == null ? "null" : "empty");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder("NativeDisplayTracker creation problem, ");
            sb3.append(sb2);
            p.a("[ERROR] ", 3, "NativeVideoTracker", this, sb3.toString());
            this.a = new m(sb2);
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append(a());
        sb4.append(" created");
        p.a("[SUCCESS] ", sb4.toString());
    }

    private void a(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.getCurrentPosition();
            } catch (Exception unused) {
                throw new m("Playback has already completed");
            }
        } else {
            throw new m("Null player instance");
        }
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        return "NativeVideoTracker";
    }

    /* access modifiers changed from: 0000 */
    public void a(List<String> list) {
        if (!n()) {
            list.add("Player is null");
        }
        super.a(list);
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Object> i() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.m.get();
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    public boolean n() {
        WeakReference<MediaPlayer> weakReference = this.m;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public Integer o() {
        return Integer.valueOf(((MediaPlayer) this.m.get()).getCurrentPosition());
    }

    /* access modifiers changed from: 0000 */
    public boolean q() {
        return ((MediaPlayer) this.m.get()).isPlaying();
    }

    /* access modifiers changed from: 0000 */
    public Integer r() {
        return Integer.valueOf(((MediaPlayer) this.m.get()).getDuration());
    }

    public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            c();
            d();
            a(mediaPlayer);
            this.m = new WeakReference<>(mediaPlayer);
            return super.a(map, view);
        } catch (Exception e) {
            m.a(e);
            String a = m.a("trackVideoAd", e);
            if (this.d != null) {
                this.d.onTrackingFailedToStart(a);
            }
            p.a(3, "NativeVideoTracker", (Object) this, a);
            StringBuilder sb = new StringBuilder();
            sb.append(a());
            sb.append(" ");
            sb.append(a);
            p.a("[ERROR] ", sb.toString());
            return false;
        }
    }
}
