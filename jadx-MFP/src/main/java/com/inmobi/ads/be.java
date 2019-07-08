package com.inmobi.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.EventType;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant.ProfileVisibility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: NativeVideoAsset */
public final class be extends ak {
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    int E;
    int F;
    public Map<String, Object> G;
    private boolean H;
    List<ak> z;

    /* compiled from: NativeVideoAsset */
    static class a extends al {
        public a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @Nullable ba baVar) {
            super(i, i2, i3, i4, i5, i6, i7, i8, "none", "straight", "#ff000000", "#00000000", baVar);
        }
    }

    public be(String str, String str2, al alVar, by byVar, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, List<NativeTracker> list, JSONObject jSONObject, boolean z7) {
        super(str, str2, ShareConstants.VIDEO_URL, alVar);
        this.e = byVar;
        this.i = 2;
        this.A = z2;
        this.B = z3;
        this.C = z4;
        this.D = z5;
        this.z = new ArrayList();
        this.H = z7;
        if (byVar != null) {
            this.r = byVar.a();
            List<NativeTracker> d = byVar.d();
            Map<String, String> map = null;
            if (list != null) {
                for (NativeTracker nativeTracker : list) {
                    if (TrackerEventType.TRACKER_EVENT_TYPE_IAS == nativeTracker.b) {
                        map = nativeTracker.c;
                        if (!TextUtils.isEmpty(nativeTracker.a)) {
                            d.add(nativeTracker);
                        }
                    } else {
                        d.add(nativeTracker);
                    }
                }
            }
            for (NativeTracker nativeTracker2 : d) {
                if (TrackerEventType.TRACKER_EVENT_TYPE_IAS == nativeTracker2.b) {
                    nativeTracker2.c = map;
                }
            }
            if (!d.isEmpty()) {
                a(d);
            }
        }
        if (jSONObject != null) {
            this.f = jSONObject;
        }
        this.v.put("placementType", PlacementType.PLACEMENT_TYPE_INLINE);
        this.v.put("lastVisibleTimestamp", Integer.valueOf(Integer.MIN_VALUE));
        this.v.put(ProfileVisibility.VISIBLE, Boolean.valueOf(false));
        this.v.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(0));
        this.v.put("didStartPlaying", Boolean.valueOf(false));
        this.v.put(EventType.DID_PAUSE, Boolean.valueOf(false));
        this.v.put("didCompleteQ1", Boolean.valueOf(false));
        this.v.put("didCompleteQ2", Boolean.valueOf(false));
        this.v.put("didCompleteQ3", Boolean.valueOf(false));
        this.v.put("didCompleteQ4", Boolean.valueOf(false));
        this.v.put("didRequestFullScreen", Boolean.valueOf(false));
        this.v.put("isFullScreen", Boolean.valueOf(false));
        this.v.put("didImpressionFire", Boolean.valueOf(false));
        this.v.put("mapViewabilityParams", new HashMap());
        this.v.put("didSignalVideoCompleted", Boolean.valueOf(false));
        this.v.put("shouldAutoPlay", Boolean.valueOf(z6));
        this.v.put("lastMediaVolume", Integer.valueOf(0));
        this.v.put("currentMediaVolume", Integer.valueOf(0));
        this.v.put("didQ4Fire", Boolean.valueOf(false));
    }

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        if (this.H) {
            return this.A && !com.inmobi.commons.a.a.d();
        }
        return this.A;
    }

    public final void a(be beVar) {
        this.v.putAll(beVar.v);
        this.G.putAll(beVar.G);
        this.u = beVar.u;
    }

    public final by b() {
        if (this.e == null) {
            return null;
        }
        return (by) this.e;
    }
}
