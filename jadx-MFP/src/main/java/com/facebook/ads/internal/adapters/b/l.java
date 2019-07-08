package com.facebook.ads.internal.adapters.b;

import com.facebook.ads.internal.adapters.b.e.b;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.io.Serializable;
import org.json.JSONObject;

public class l implements Serializable {
    private static final long serialVersionUID = 85021702336014823L;
    private final e a;
    private final i b;
    private final d c;
    private final boolean d;

    private l(e eVar, i iVar, d dVar, boolean z) {
        this.a = eVar;
        this.b = iVar;
        this.c = dVar;
        this.d = z;
    }

    static l a(JSONObject jSONObject) {
        e a2 = new b().a(jSONObject.optString("title")).b(jSONObject.optString("subtitle")).c(jSONObject.optString("body")).a();
        i iVar = new i(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        boolean optBoolean = jSONObject.optBoolean("video_autoplay_enabled");
        boolean optBoolean2 = jSONObject.optBoolean("is_watch_and_browse");
        a b2 = new a().a(jSONObject.optString(BaseVideoPlayerActivity.VIDEO_URL)).a(optBoolean).b(jSONObject.optBoolean("is_audio_muted", true));
        int i = 0;
        if (optBoolean) {
            i = jSONObject.optInt("unskippable_seconds", 0);
        }
        a a3 = b2.a(i);
        JSONObject optJSONObject = jSONObject.optJSONObject("image");
        if (optJSONObject != null) {
            a3.b(optJSONObject.optString("url")).c(optJSONObject.optInt("width")).d(optJSONObject.optInt("height"));
        }
        a3.a(n.a(jSONObject));
        return new l(a2, iVar, a3.a(), optBoolean2);
    }

    public e a() {
        return this.a;
    }

    public i b() {
        return this.b;
    }

    public d c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }
}
