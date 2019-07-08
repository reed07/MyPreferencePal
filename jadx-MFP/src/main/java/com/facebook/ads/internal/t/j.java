package com.facebook.ads.internal.t;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.b.x;
import org.json.JSONObject;

public class j {
    private Typeface a = Typeface.create(Typeface.SANS_SERIF, 0);
    private int b = -1;
    private int c = -16777216;
    private int d = -11643291;
    private int e = 0;
    private int f = -12420889;
    private int g = -12420889;
    private boolean h = AdInternalSettings.isVideoAutoplay();
    private boolean i = AdInternalSettings.isVideoAutoplayOnMobile();

    public j() {
    }

    public j(JSONObject jSONObject) {
        this.b = jSONObject.getBoolean("background_transparent") ? 0 : Color.parseColor(jSONObject.getString("background_color"));
        this.c = Color.parseColor(jSONObject.getString("title_text_color"));
        this.d = Color.parseColor(jSONObject.getString("description_text_color"));
        this.e = jSONObject.getBoolean("button_transparent") ? 0 : Color.parseColor(jSONObject.getString("button_color"));
        this.g = jSONObject.getBoolean("button_border_transparent") ? 0 : Color.parseColor(jSONObject.getString("button_border_color"));
        this.f = Color.parseColor(jSONObject.getString("button_text_color"));
        this.a = Typeface.create(jSONObject.getString("android_typeface"), 0);
    }

    public Typeface a() {
        return this.a;
    }

    public void a(int i2) {
        this.b = i2;
    }

    public void a(Typeface typeface) {
        this.a = typeface;
    }

    public void a(TextView textView) {
        textView.setTextColor(this.c);
        textView.setTextSize(16.0f);
        textView.setTypeface(this.a, 1);
    }

    public void a(boolean z) {
        this.i = z;
    }

    public int b() {
        return this.b;
    }

    public void b(int i2) {
        this.c = i2;
    }

    public void b(TextView textView) {
        textView.setTextColor(this.d);
        textView.setTextSize(10.0f);
        textView.setTypeface(this.a);
    }

    public void b(boolean z) {
        this.h = z;
    }

    public int c() {
        return this.c;
    }

    public void c(int i2) {
        this.d = i2;
    }

    public void c(TextView textView) {
        x.a((View) textView, this.e);
        textView.setTextColor(this.f);
        textView.setTextSize(2, 14.0f);
        textView.setTypeface(this.a, 1);
    }

    public int d() {
        return this.d;
    }

    public void d(int i2) {
        this.e = i2;
    }

    public int e() {
        return this.e;
    }

    public void e(int i2) {
        this.f = i2;
    }

    public int f() {
        return this.f;
    }

    public void f(int i2) {
        this.g = i2;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return 16;
    }

    public int i() {
        return 10;
    }

    public boolean j() {
        return this.h;
    }

    public boolean k() {
        return this.i;
    }
}
