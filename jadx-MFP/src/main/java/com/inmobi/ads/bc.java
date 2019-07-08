package com.inmobi.ads;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.a.a;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeVideoAd */
class bc extends a {
    private static final String o = "bc";
    final String l;
    final String m;
    final String n;
    private final String p;
    private final String q;

    bc(JSONObject jSONObject, String str, long j, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, MonetizationContext monetizationContext, boolean z, long j2, float f, @Nullable String str11) {
        super(jSONObject, str, j, str2, str3, str4, str5, monetizationContext, z, j2, f, str11);
        this.l = str6;
        this.m = str7;
        this.n = str8;
        this.p = str9;
        this.q = str10;
    }

    bc(ContentValues contentValues) {
        super(contentValues);
        this.l = contentValues.getAsString(BaseVideoPlayerActivity.VIDEO_URL);
        this.m = contentValues.getAsString("video_track_duration");
        this.n = contentValues.getAsString("click_url");
        this.p = contentValues.getAsString("video_trackers");
        this.q = contentValues.getAsString("companion_ads");
    }

    public final ContentValues a() {
        ContentValues a = super.a();
        a.put(BaseVideoPlayerActivity.VIDEO_URL, this.l);
        a.put("video_track_duration", this.m);
        a.put("click_url", this.n);
        a.put("video_trackers", this.p);
        a.put("companion_ads", this.q);
        return a;
    }

    @NonNull
    public final List<NativeTracker> h() {
        ArrayList arrayList = new ArrayList();
        String str = this.p;
        if (str == null || str.length() == 0) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(this.p);
            if (jSONArray.length() == 0) {
                return arrayList;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                NativeTracker a = NativeTracker.a(new JSONObject(jSONArray.getString(i)));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            a.a().a(new com.inmobi.commons.core.e.a(e));
            return arrayList;
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final List<bt> i() {
        ArrayList arrayList = new ArrayList();
        String str = this.q;
        if (str == null || str.length() == 0) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(this.q);
            if (jSONArray.length() == 0) {
                return arrayList;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                bt a = bt.a(new JSONObject(jSONArray.getString(i)));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            a.a().a(new com.inmobi.commons.core.e.a(e));
            return arrayList;
        }
    }
}
