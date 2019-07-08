package com.facebook.ads;

import android.graphics.Typeface;
import com.facebook.ads.internal.o.a;
import com.facebook.ads.internal.o.b;
import com.facebook.ads.internal.t.j;
import org.json.JSONObject;

public class NativeAdViewAttributes {
    private final j a;

    public NativeAdViewAttributes() {
        this.a = new j();
    }

    NativeAdViewAttributes(j jVar) {
        this.a = jVar;
    }

    public NativeAdViewAttributes(JSONObject jSONObject) {
        j jVar;
        try {
            jVar = new j(jSONObject);
        } catch (Exception e) {
            jVar = new j();
            b.a(a.a(e, "Error retrieving native ui configuration data"));
        }
        this.a = jVar;
    }

    /* access modifiers changed from: 0000 */
    public j a() {
        return this.a;
    }

    public boolean getAutoplay() {
        return this.a.j();
    }

    public boolean getAutoplayOnMobile() {
        return this.a.k();
    }

    public int getBackgroundColor() {
        return this.a.b();
    }

    public int getButtonBorderColor() {
        return this.a.g();
    }

    public int getButtonColor() {
        return this.a.e();
    }

    public int getButtonTextColor() {
        return this.a.f();
    }

    public int getDescriptionTextColor() {
        return this.a.d();
    }

    public int getDescriptionTextSize() {
        return this.a.i();
    }

    public int getTitleTextColor() {
        return this.a.c();
    }

    public int getTitleTextSize() {
        return this.a.h();
    }

    public Typeface getTypeface() {
        return this.a.a();
    }

    public NativeAdViewAttributes setAutoplay(boolean z) {
        this.a.b(z);
        return this;
    }

    public NativeAdViewAttributes setAutoplayOnMobile(boolean z) {
        this.a.a(z);
        return this;
    }

    public NativeAdViewAttributes setBackgroundColor(int i) {
        this.a.a(i);
        return this;
    }

    public NativeAdViewAttributes setButtonBorderColor(int i) {
        this.a.f(i);
        return this;
    }

    public NativeAdViewAttributes setButtonColor(int i) {
        this.a.d(i);
        return this;
    }

    public NativeAdViewAttributes setButtonTextColor(int i) {
        this.a.e(i);
        return this;
    }

    public NativeAdViewAttributes setDescriptionTextColor(int i) {
        this.a.c(i);
        return this;
    }

    public NativeAdViewAttributes setTitleTextColor(int i) {
        this.a.b(i);
        return this;
    }

    public NativeAdViewAttributes setTypeface(Typeface typeface) {
        this.a.a(typeface);
        return this;
    }
}
