package com.facebook.ads.internal.view.component;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.i;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.w.b.g;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import java.util.Map;

public class a extends c {
    /* access modifiers changed from: private */
    public final String b;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.x.a c;
    /* access modifiers changed from: private */
    public final w d;
    /* access modifiers changed from: private */
    @Nullable
    public final c e;
    /* access modifiers changed from: private */
    @Nullable
    public final C0012a f;

    /* renamed from: com.facebook.ads.internal.view.component.a$a reason: collision with other inner class name */
    public interface C0016a {
        void a();
    }

    public a(Context context, boolean z, boolean z2, String str, h hVar, c cVar, C0012a aVar, com.facebook.ads.internal.x.a aVar2, w wVar) {
        super(context, z, z2, hVar);
        this.e = cVar;
        this.f = aVar;
        this.b = str;
        this.c = aVar2;
        this.d = wVar;
    }

    /* access modifiers changed from: private */
    @Nullable
    public b a(Uri uri, String str, Map<String, String> map, boolean z) {
        return com.facebook.ads.internal.a.c.a(getContext(), this.e, str, uri, map, z, false);
    }

    private void a(String str, String str2, String str3, Map<String, String> map, boolean z, @Nullable C0016a aVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.e == null) {
            setVisibility(8);
            return;
        }
        setText(str);
        final String str4 = str3;
        final Map<String, String> map2 = map;
        final String str5 = str2;
        final boolean z2 = z;
        final C0016a aVar2 = aVar;
        AnonymousClass1 r1 = new OnClickListener() {
            /* access modifiers changed from: private */
            public void a() {
                String str;
                String str2;
                if (!com.facebook.ads.internal.r.a.k(a.this.getContext()) || a.this.d.b()) {
                    try {
                        Uri parse = Uri.parse(str5);
                        a.this.c.a(map2);
                        map2.put("touch", k.a(a.this.d.e()));
                        b a2 = a.this.a(parse, str4, map2, z2);
                        if (a2 != null && aVar2 == null) {
                            a2.a();
                        } else if (aVar2 != null) {
                            aVar2.a();
                        }
                        if (a.this.f != null) {
                            a.this.f.a(a.this.b);
                        }
                    } catch (ActivityNotFoundException e2) {
                        e = e2;
                        str2 = String.valueOf(a.class);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Error while opening ");
                        sb.append(str5);
                        str = sb.toString();
                        Log.e(str2, str, e);
                    } catch (Exception e3) {
                        e = e3;
                        str2 = String.valueOf(a.class);
                        str = "Error executing action";
                        Log.e(str2, str, e);
                    }
                }
            }

            public void onClick(View view) {
                if (a.this.d.a(a.this.getContext())) {
                    if (a.this.e != null) {
                        a.this.e.i(str4, map2);
                    }
                } else if (com.facebook.ads.internal.r.a.e(a.this.getContext())) {
                    if (a.this.e != null) {
                        a.this.e.j(str4, map2);
                    }
                    g.a(new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            map2.put("is_two_step", "true");
                            AnonymousClass1.this.a();
                        }
                    }, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (a.this.e != null) {
                                a.this.e.k(str4, map2);
                            }
                        }
                    }, com.facebook.ads.internal.w.a.b.a());
                } else {
                    a();
                }
            }
        };
        setOnClickListener(r1);
    }

    public void a(i iVar, String str, Map<String, String> map) {
        a(iVar.b(), iVar.a(), str, map, false, null);
    }

    public void a(i iVar, String str, Map<String, String> map, @Nullable C0016a aVar) {
        a(iVar.b(), iVar.a(), str, map, false, aVar);
    }

    public void a(i iVar, String str, Map<String, String> map, boolean z) {
        a(iVar.b(), iVar.a(), str, map, z, null);
    }

    public void b(i iVar, String str, Map<String, String> map) {
        Uri parse = Uri.parse(iVar.a());
        this.c.a(map);
        map.put("touch", k.a(this.d.e()));
        b a = a(parse, str, map, false);
        if (a != null) {
            a.b();
        }
    }
}
