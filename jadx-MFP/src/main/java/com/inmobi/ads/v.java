package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.inmobi.ads.c.l;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import java.lang.ref.WeakReference;

/* compiled from: IasTrackedHtmlAd */
public class v extends bz {
    private static final String d = "v";
    @NonNull
    private final WeakReference<Activity> e;
    @NonNull
    private final ca f;
    @NonNull
    private final AbstractAvidAdSession<WebView> g;
    private final boolean h;

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004b  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.integralads.avid.library.inmobi.session.AbstractAvidAdSession<android.webkit.WebView> a(@android.support.annotation.Nullable android.content.Context r2, boolean r3, @android.support.annotation.NonNull java.lang.String r4, @android.support.annotation.Nullable com.inmobi.rendering.RenderView r5) {
        /*
            com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext r0 = new com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext
            java.lang.String r1 = "7.2.6"
            r0.<init>(r1, r3)
            int r3 = r4.hashCode()
            r1 = -284840886(0xffffffffef05ac4a, float:-4.136979E28)
            if (r3 == r1) goto L_0x002f
            r1 = 112202875(0x6b0147b, float:6.6233935E-35)
            if (r3 == r1) goto L_0x0025
            r1 = 1425678798(0x54fa21ce, float:8.5944718E12)
            if (r3 == r1) goto L_0x001b
            goto L_0x0039
        L_0x001b:
            java.lang.String r3 = "nonvideo"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0039
            r3 = 2
            goto L_0x003a
        L_0x0025:
            java.lang.String r3 = "video"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0039
            r3 = 3
            goto L_0x003a
        L_0x002f:
            java.lang.String r3 = "unknown"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0039
            r3 = 1
            goto L_0x003a
        L_0x0039:
            r3 = -1
        L_0x003a:
            r4 = 0
            switch(r3) {
                case 2: goto L_0x0045;
                case 3: goto L_0x0040;
                default: goto L_0x003e;
            }
        L_0x003e:
            r3 = r4
            goto L_0x0049
        L_0x0040:
            com.integralads.avid.library.inmobi.session.AvidVideoAdSession r3 = com.integralads.avid.library.inmobi.session.AvidAdSessionManager.startAvidVideoAdSession(r2, r0)
            goto L_0x0049
        L_0x0045:
            com.integralads.avid.library.inmobi.session.AvidDisplayAdSession r3 = com.integralads.avid.library.inmobi.session.AvidAdSessionManager.startAvidDisplayAdSession(r2, r0)
        L_0x0049:
            if (r3 == 0) goto L_0x0058
            boolean r0 = r2 instanceof android.app.Activity
            if (r0 == 0) goto L_0x0055
            android.app.Activity r2 = (android.app.Activity) r2
            r3.registerAdView(r5, r2)
            goto L_0x0058
        L_0x0055:
            r3.registerAdView(r5, r4)
        L_0x0058:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.v.a(android.content.Context, boolean, java.lang.String, com.inmobi.rendering.RenderView):com.integralads.avid.library.inmobi.session.AbstractAvidAdSession");
    }

    public v(@NonNull AdContainer adContainer, @NonNull Activity activity, @NonNull ca caVar, @NonNull AbstractAvidAdSession<WebView> abstractAvidAdSession, boolean z) {
        super(adContainer);
        this.e = new WeakReference<>(activity);
        this.f = caVar;
        this.g = abstractAvidAdSession;
        this.h = z;
    }

    @Nullable
    public final View a() {
        return this.f.a();
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.f.a(view, viewGroup, z);
    }

    @Nullable
    public final View b() {
        return this.f.b();
    }

    @NonNull
    public final c c() {
        return this.f.c();
    }

    public final void a(@Nullable View... viewArr) {
        try {
            Activity activity = (Activity) this.e.get();
            l lVar = this.f.c().k;
            if (activity != null && lVar.j) {
                if (this.a instanceof ah) {
                    ah ahVar = (ah) this.a;
                    if (ahVar.s() != null) {
                        a(activity, (WebView) ahVar.s(), viewArr);
                    }
                } else {
                    View b = this.f.b();
                    if (b != null) {
                        a(activity, (WebView) b, viewArr);
                    }
                }
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in startTrackingForImpression with message : ").append(e2.getMessage());
        } catch (Throwable th) {
            this.f.a(viewArr);
            throw th;
        }
        this.f.a(viewArr);
    }

    private void a(Activity activity, WebView webView, @Nullable View[] viewArr) {
        if (viewArr != null) {
            for (View registerFriendlyObstruction : viewArr) {
                this.g.registerFriendlyObstruction(registerFriendlyObstruction);
            }
        }
        this.g.registerAdView(webView, activity);
        if (this.h && this.g.getAvidDeferredAdSessionListener() != null) {
            this.g.getAvidDeferredAdSessionListener().recordReadyEvent();
        }
    }

    public final void a(int i) {
        this.f.a(i);
    }

    public final void a(Context context, int i) {
        this.f.a(context, i);
    }

    public final void e() {
        super.e();
        try {
            this.e.clear();
        } catch (Exception e2) {
            new StringBuilder("Exception in destroy with message : ").append(e2.getMessage());
        } catch (Throwable th) {
            this.f.e();
            throw th;
        }
        this.f.e();
    }

    public final void d() {
        View view;
        try {
            if (this.a instanceof ah) {
                view = ((ah) this.a).s();
            } else {
                view = (WebView) this.f.b();
            }
            this.g.unregisterAdView(view);
            this.g.endSession();
        } catch (Exception e2) {
            new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e2.getMessage());
        } catch (Throwable th) {
            this.f.d();
            throw th;
        }
        this.f.d();
    }
}
