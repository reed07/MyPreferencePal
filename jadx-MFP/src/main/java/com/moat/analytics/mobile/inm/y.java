package com.moat.analytics.mobile.inm;

import android.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class y extends c implements ReactiveVideoTracker {
    private Integer l;

    public y(String str) {
        super(str);
        p.a(3, "ReactiveVideoTracker", (Object) this, "Initializing.");
        StringBuilder sb = new StringBuilder();
        sb.append(a());
        sb.append(" created");
        p.a("[SUCCESS] ", sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        return "ReactiveVideoTracker";
    }

    /* access modifiers changed from: 0000 */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.b.equals(MoatAdEvent.a) && !a(moatAdEvent.b, this.l)) {
            moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }

    /* access modifiers changed from: 0000 */
    public void a(List<String> list) {
        if (this.l.intValue() >= 1000) {
            super.a(list);
            return;
        }
        throw new m(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{this.l}));
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Object> i() {
        HashMap hashMap = new HashMap();
        View view = (View) this.k.get();
        Integer valueOf = Integer.valueOf(0);
        Integer valueOf2 = Integer.valueOf(0);
        if (view != null) {
            valueOf = Integer.valueOf(view.getWidth());
            valueOf2 = Integer.valueOf(view.getHeight());
        }
        hashMap.put("duration", this.l);
        hashMap.put("width", valueOf);
        hashMap.put("height", valueOf2);
        return hashMap;
    }

    public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            c();
            d();
            this.l = num;
            return super.a(map, view);
        } catch (Exception e) {
            a("trackVideoAd", e);
            return false;
        }
    }
}
