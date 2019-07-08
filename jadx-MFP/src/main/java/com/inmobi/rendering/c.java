package com.inmobi.rendering;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.exoplayer2.util.MimeTypes;
import com.inmobi.a.n;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.ah;
import com.inmobi.ads.ak;
import com.inmobi.ads.br;
import com.inmobi.commons.core.configs.h;
import com.inmobi.commons.core.network.a.C0047a;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.mraid.MediaRenderView;
import com.inmobi.rendering.mraid.MraidMediaProcessor;
import com.inmobi.rendering.mraid.MraidMediaProcessor.HeadphonesPluggedChangeReceiver;
import com.inmobi.rendering.mraid.MraidMediaProcessor.RingerModeChangeReceiver;
import com.inmobi.rendering.mraid.b;
import com.inmobi.rendering.mraid.g;
import com.mopub.common.Constants;
import com.myfitnesspal.shared.constants.Constants.BusyStates;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.samsung.android.sdk.healthdata.HealthConstants.FoodIntake;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JavaScriptBridge */
public class c {
    static final String[] a = {"tel", "sms", "calendar", "inlineVideo"};
    /* access modifiers changed from: private */
    public static final String b = "c";
    /* access modifiers changed from: private */
    public RenderView c;
    private RenderingProperties d;
    private g e;

    @TargetApi(16)
    /* compiled from: JavaScriptBridge */
    private static class a implements OnGlobalLayoutListener {
        /* access modifiers changed from: private */
        public int a;
        /* access modifiers changed from: private */
        public int b;
        private View c;
        /* access modifiers changed from: private */
        public final Boolean d = Boolean.valueOf(false);

        a(View view) {
            this.c = view;
        }

        public final void onGlobalLayout() {
            try {
                this.a = com.inmobi.commons.core.utilities.b.c.b(this.c.getWidth());
                this.b = com.inmobi.commons.core.utilities.b.c.b(this.c.getHeight());
                if (VERSION.SDK_INT >= 16) {
                    this.c.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    this.c.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                synchronized (this.d) {
                    this.d.notify();
                }
            } catch (Exception e) {
                c.b;
                new StringBuilder("SDK encountered unexpected error in JavaScriptBridge$1.onGlobalLayout(); ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public String getPlatform(String str) {
        return "android";
    }

    @JavascriptInterface
    public String getSdkVersion(String str) {
        return "7.2.6";
    }

    @JavascriptInterface
    public String getVersion(String str) {
        return "2.0";
    }

    @JavascriptInterface
    public void log(String str, String str2) {
    }

    @JavascriptInterface
    public void onOrientationChange(String str) {
    }

    @JavascriptInterface
    public void showAlert(String str, String str2) {
    }

    @JavascriptInterface
    public void storePicture(String str, String str2) {
    }

    c(RenderView renderView, RenderingProperties renderingProperties) {
        this.c = renderView;
        this.d = renderingProperties;
    }

    @JavascriptInterface
    public void open(final String str, final String str2) {
        RenderView renderView = this.c;
        if (renderView != null) {
            if (!renderView.e()) {
                this.c.c("open");
            } else {
                new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                    public final void run() {
                        try {
                            c.this.c.c("open", str, str2);
                        } catch (Exception e) {
                            c.this.c.b(str, "Unexpected error", "open");
                            Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to open URL; SDK encountered unexpected error");
                            c.b;
                            new StringBuilder("SDK encountered unexpected error in handling open() request from creative; ").append(e.getMessage());
                        }
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void openEmbedded(final String str, final String str2) {
        RenderView renderView = this.c;
        if (renderView != null) {
            if (!renderView.e()) {
                this.c.c("openEmbedded");
            } else {
                new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                    public final void run() {
                        try {
                            c.this.c.c("openEmbedded", str, str2);
                        } catch (Exception e) {
                            c.this.c.b(str, "Unexpected error", "openEmbedded");
                            Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to open URL; SDK encountered unexpected error");
                            c.b;
                            new StringBuilder("SDK encountered unexpected error in handling openEmbedded() request from creative; ").append(e.getMessage());
                        }
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void ping(String str, String str2, boolean z) {
        if (this.c != null) {
            if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
                RenderView renderView = this.c;
                StringBuilder sb = new StringBuilder("Invalid URL:");
                sb.append(str2);
                renderView.b(str, sb.toString(), "ping");
                return;
            }
            StringBuilder sb2 = new StringBuilder("JavaScript called ping() URL: >>> ");
            sb2.append(str2);
            sb2.append(" <<<");
            try {
                new Thread(str2, z) {
                    final /* synthetic */ String a;
                    final /* synthetic */ boolean b;

                    {
                        this.a = r2;
                        this.b = r3;
                    }

                    public final void run() {
                        try {
                            h hVar = new h();
                            com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) hVar, (com.inmobi.commons.core.configs.b.c) null);
                            if (!hVar.g) {
                                a aVar = new a(this.a, this.b, false, c.j.a + 1);
                                c.a;
                                StringBuilder sb = new StringBuilder("Received click (");
                                sb.append(aVar.b);
                                sb.append(") for pinging over HTTP");
                                c.a(c.this, aVar);
                            }
                        } catch (Exception e) {
                            c.a;
                            new StringBuilder("SDK encountered unexpected error in pinging click; ").append(e.getMessage());
                        }
                    }
                }.start();
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "ping");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to fire ping; SDK encountered unexpected error");
                new StringBuilder("SDK encountered unexpected error in handling ping() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void pingInWebView(String str, String str2, boolean z) {
        if (this.c != null) {
            if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
                RenderView renderView = this.c;
                StringBuilder sb = new StringBuilder("Invalid URL:");
                sb.append(str2);
                renderView.b(str, sb.toString(), "pingInWebView");
                return;
            }
            StringBuilder sb2 = new StringBuilder("JavaScript called pingInWebView() URL: >>> ");
            sb2.append(str2);
            sb2.append(" <<<");
            try {
                new Thread(str2, z) {
                    final /* synthetic */ String a;
                    final /* synthetic */ boolean b;

                    {
                        this.a = r2;
                        this.b = r3;
                    }

                    public final void run() {
                        try {
                            h hVar = new h();
                            com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) hVar, (com.inmobi.commons.core.configs.b.c) null);
                            if (!hVar.g) {
                                a aVar = new a(this.a, this.b, true, c.j.a + 1);
                                c.a;
                                StringBuilder sb = new StringBuilder("Received click (");
                                sb.append(aVar.b);
                                sb.append(") for pinging in WebView");
                                c.a(c.this, aVar);
                            }
                        } catch (Exception e) {
                            c.a;
                            new StringBuilder("SDK encountered unexpected error in pinging click over WebView; ").append(e.getMessage());
                        }
                    }
                }.start();
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "pingInWebView");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to fire ping; SDK encountered unexpected error");
                new StringBuilder("SDK encountered unexpected error in handling pingInWebView() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public String getPlatformVersion(String str) {
        return Integer.toString(VERSION.SDK_INT);
    }

    @JavascriptInterface
    public void fireAdReady(String str) {
        try {
            this.c.getListener().w();
        } catch (Exception e2) {
            this.c.b(str, "Unexpected error", "fireAdReady");
            new StringBuilder("SDK encountered unexpected error in handling fireAdReady() signal from creative; ").append(e2.getMessage());
        }
    }

    @JavascriptInterface
    public void fireAdFailed(String str) {
        try {
            this.c.getListener().y();
        } catch (Exception e2) {
            this.c.b(str, "Unexpected error", "fireAdFailed");
            new StringBuilder("SDK encountered unexpected error in handling fireAdFailed() signal from creative; ").append(e2.getMessage());
        }
    }

    @JavascriptInterface
    public String getDefaultPosition(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return new JSONObject().toString();
        }
        synchronized (renderView.getDefaultPositionMonitor()) {
            this.c.setDefaultPositionLock();
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        c.this.c.setDefaultPosition();
                    } catch (Exception e) {
                        c.b;
                        new StringBuilder("SDK encountered unexpected error in getting/setting default position; ").append(e.getMessage());
                    }
                }
            });
            while (this.c.k) {
                try {
                    this.c.getDefaultPositionMonitor().wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        return this.c.getDefaultPosition();
    }

    @JavascriptInterface
    public String getCurrentPosition(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return "";
        }
        synchronized (renderView.getCurrentPositionMonitor()) {
            this.c.setCurrentPositionLock();
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        c.this.c.setCurrentPosition();
                    } catch (Exception e) {
                        c.b;
                        new StringBuilder("SDK encountered unexpected error in getting/setting current position; ").append(e.getMessage());
                    }
                }
            });
            while (this.c.l) {
                try {
                    this.c.getCurrentPositionMonitor().wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        return this.c.getCurrentPosition();
    }

    @JavascriptInterface
    public void setExpandProperties(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView != null && !"Expanded".equals(renderView.getState())) {
            try {
                this.c.setExpandProperties(b.a(str2));
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "setExpandProperties");
                new StringBuilder("SDK encountered unexpected error in setExpandProperties(); ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public String getExpandProperties(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return "";
        }
        return renderView.getExpandProperties().c;
    }

    @JavascriptInterface
    public void expand(final String str, final String str2) {
        if (this.d.a != PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
            RenderView renderView = this.c;
            if (renderView != null) {
                if (!renderView.e()) {
                    this.c.c("expand");
                } else if (!this.c.o) {
                    this.c.b(str, "Creative is not visible. Ignoring request.", "expand");
                } else if (str2 == null || str2.length() == 0 || str2.startsWith(Constants.HTTP)) {
                    new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                        public final void run() {
                            int i;
                            try {
                                RenderView a2 = c.this.c;
                                String str = str;
                                String str2 = str2;
                                if ("Default".equals(a2.d) || "Resized".equals(a2.d)) {
                                    a2.t = true;
                                    com.inmobi.rendering.mraid.c cVar = a2.f;
                                    if (cVar.c == null) {
                                        cVar.c = (ViewGroup) cVar.a.getParent();
                                        cVar.d = cVar.c.indexOfChild(cVar.a);
                                    }
                                    if (cVar.a != null) {
                                        b expandProperties = cVar.a.getExpandProperties();
                                        cVar.b = URLUtil.isValidUrl(str2);
                                        if (cVar.b) {
                                            RenderView renderView = new RenderView(cVar.a.getContainerContext(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), null, cVar.a.getImpressionId());
                                            renderView.a(cVar.a.getListener(), cVar.a.getAdConfig());
                                            renderView.setOriginalRenderView(cVar.a);
                                            renderView.loadUrl(str2);
                                            renderView.setPlacementId(cVar.a.getPlacementId());
                                            renderView.setAllowAutoRedirection(cVar.a.getAllowAutoRedirection());
                                            renderView.setCreativeId(cVar.a.getCreativeId());
                                            i = InMobiAdActivity.a((AdContainer) renderView);
                                            if (expandProperties != null) {
                                                renderView.setUseCustomClose(cVar.a.m);
                                            }
                                        } else {
                                            FrameLayout frameLayout = new FrameLayout(cVar.a.getContainerContext());
                                            LayoutParams layoutParams = new LayoutParams(cVar.a.getWidth(), cVar.a.getHeight());
                                            frameLayout.setId(BusyStates.ALL);
                                            cVar.c.addView(frameLayout, cVar.d, layoutParams);
                                            cVar.c.removeView(cVar.a);
                                            i = InMobiAdActivity.a((AdContainer) cVar.a);
                                        }
                                        cVar.a.getListener().G();
                                        Intent intent = new Intent(cVar.a.getContainerContext(), InMobiAdActivity.class);
                                        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                                        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", i);
                                        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 200);
                                        com.inmobi.commons.a.a.a(cVar.a.getContainerContext(), intent);
                                    }
                                    a2.requestLayout();
                                    a2.invalidate();
                                    a2.n = true;
                                    a2.setFocusable(true);
                                    a2.setFocusableInTouchMode(true);
                                    a2.requestFocus();
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("command", "expand");
                                    hashMap.put("scheme", br.a(str));
                                    a2.c.b("CreativeInvokedAction", hashMap);
                                    return;
                                }
                                new StringBuilder("Render view state must be either DEFAULT or RESIZED to admit the expand request. Current state:").append(a2.d);
                            } catch (Exception e) {
                                c.this.c.b(str, "Unexpected error", "expand");
                                Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to expand ad; SDK encountered an unexpected error");
                                c.b;
                                new StringBuilder("SDK encountered unexpected error in handling expand() request; ").append(e.getMessage());
                            }
                        }
                    });
                } else {
                    this.c.b(str, "Invalid URL", "expand");
                }
            }
        }
    }

    @JavascriptInterface
    public void setResizeProperties(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView != null) {
            com.inmobi.rendering.mraid.h a2 = com.inmobi.rendering.mraid.h.a(str2, renderView.getResizeProperties());
            if (a2 == null) {
                this.c.b(str, "setResizeProperties", "All mandatory fields are not present");
            }
            this.c.setResizeProperties(a2);
        }
    }

    @JavascriptInterface
    public String getResizeProperties(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return "";
        }
        com.inmobi.rendering.mraid.h resizeProperties = renderView.getResizeProperties();
        if (resizeProperties == null) {
            return "";
        }
        return resizeProperties.a();
    }

    @JavascriptInterface
    public void resize(final String str) {
        if (this.d.a != PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
            RenderView renderView = this.c;
            if (renderView != null) {
                if (renderView.o) {
                    new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                        public final void run() {
                            try {
                                RenderView a2 = c.this.c;
                                String str = str;
                                if (("Default".equals(a2.d) || "Resized".equals(a2.d)) && a2.getResizeProperties() != null) {
                                    a2.t = true;
                                    a2.g.a();
                                    a2.requestLayout();
                                    a2.invalidate();
                                    a2.n = true;
                                    a2.setFocusable(true);
                                    a2.setFocusableInTouchMode(true);
                                    a2.requestFocus();
                                    a2.setAndUpdateViewState("Resized");
                                    a2.c.c(a2);
                                    a2.t = false;
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("command", "resize");
                                    hashMap.put("scheme", br.a(str));
                                    a2.c.b("CreativeInvokedAction", hashMap);
                                }
                            } catch (Exception e) {
                                c.this.c.b(str, "Unexpected error", "resize");
                                Logger.a(InternalLogLevel.ERROR, c.b, "Could not resize ad; SDK encountered an unexpected error");
                                c.b;
                                new StringBuilder("SDK encountered an unexpected error in handling resize() request; ").append(e.getMessage());
                            }
                        }
                    });
                    return;
                }
                this.c.b(str, "Creative is not visible. Ignoring request.", "resize");
            }
        }
    }

    @JavascriptInterface
    public void setOrientationProperties(String str, String str2) {
        this.e = g.a(str2, this.c.getOrientationProperties());
        this.c.setOrientationProperties(this.e);
    }

    @JavascriptInterface
    public String getOrientationProperties(String str) {
        return this.e.d;
    }

    @JavascriptInterface
    public boolean isViewable(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return false;
        }
        return renderView.o;
    }

    @JavascriptInterface
    public void useCustomClose(final String str, final boolean z) {
        new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
            public final void run() {
                try {
                    c.this.c.b(z);
                } catch (Exception e) {
                    c.this.c.b(str, "Unexpected error", "useCustomClose");
                    c.b;
                    new StringBuilder("SDK encountered internal error in handling useCustomClose() request from creative; ").append(e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public void playVideo(final String str, final String str2) {
        if (this.c != null) {
            if (str2 == null || str2.trim().length() == 0 || !str2.startsWith(Constants.HTTP) || (!str2.endsWith("mp4") && !str2.endsWith("avi") && !str2.endsWith("m4v"))) {
                this.c.b(str, "Null or empty or invalid media playback URL supplied", "playVideo");
                return;
            }
            StringBuilder sb = new StringBuilder("JavaScript called: playVideo (");
            sb.append(str2);
            sb.append(")");
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        RenderView a2 = c.this.c;
                        String str = str;
                        String trim = str2.trim();
                        if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == a2.e.a || "Expanded".equals(a2.getViewState())) {
                            if (a2.b != null) {
                                if (a2.b.get() != null) {
                                    a2.setAdActiveFlag(true);
                                    MraidMediaProcessor mraidMediaProcessor = a2.h;
                                    Activity activity = (Activity) a2.b.get();
                                    mraidMediaProcessor.b = new MediaRenderView(activity);
                                    MediaRenderView mediaRenderView = mraidMediaProcessor.b;
                                    mediaRenderView.h = MediaRenderView.a(trim);
                                    mediaRenderView.g = "anonymous";
                                    if (mediaRenderView.b == null) {
                                        mediaRenderView.b = Bitmap.createBitmap(24, 24, Config.ARGB_8888);
                                        mediaRenderView.b = MediaRenderView.b(mediaRenderView.h);
                                    }
                                    ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
                                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                                    layoutParams.addRule(13);
                                    mraidMediaProcessor.b.setLayoutParams(layoutParams);
                                    RelativeLayout relativeLayout = new RelativeLayout(activity);
                                    relativeLayout.setOnTouchListener(new OnTouchListener() {
                                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                                            return true;
                                        }
                                    });
                                    relativeLayout.setBackgroundColor(-16777216);
                                    relativeLayout.addView(mraidMediaProcessor.b);
                                    viewGroup.addView(relativeLayout, new LayoutParams(-1, -1));
                                    mraidMediaProcessor.b.c = relativeLayout;
                                    mraidMediaProcessor.b.requestFocus();
                                    mraidMediaProcessor.b.setOnKeyListener(new OnKeyListener() {
                                        public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                                            if (4 != i || keyEvent.getAction() != 0) {
                                                return false;
                                            }
                                            MraidMediaProcessor.this.b.a();
                                            return true;
                                        }
                                    });
                                    mraidMediaProcessor.b.d = new a() {
                                        public final void a(MediaRenderView mediaRenderView) {
                                            MraidMediaProcessor.f;
                                            MraidMediaProcessor.this.a.setAdActiveFlag(false);
                                            ViewGroup viewGroup = mediaRenderView.c;
                                            if (viewGroup != null) {
                                                ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
                                            }
                                            mediaRenderView.c = null;
                                        }

                                        public final void a() {
                                            MraidMediaProcessor.f;
                                        }
                                    };
                                    MediaRenderView mediaRenderView2 = mraidMediaProcessor.b;
                                    mediaRenderView2.setVideoPath(mediaRenderView2.h);
                                    mediaRenderView2.setOnCompletionListener(mediaRenderView2);
                                    mediaRenderView2.setOnPreparedListener(mediaRenderView2);
                                    mediaRenderView2.setOnErrorListener(mediaRenderView2);
                                    if (mediaRenderView2.a == null && VERSION.SDK_INT >= 19) {
                                        mediaRenderView2.a = new CustomMediaController(mediaRenderView2.getContext());
                                        mediaRenderView2.a.setAnchorView(mediaRenderView2);
                                        mediaRenderView2.setMediaController(mediaRenderView2.a);
                                    }
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("command", "playVideo");
                                    hashMap.put("scheme", br.a(str));
                                    a2.c.b("CreativeInvokedAction", hashMap);
                                }
                            }
                            a2.b(str, "Media playback is  not allowed before it is visible! Ignoring request ...", "playVideo");
                        }
                    } catch (Exception e) {
                        c.this.c.b(str, "Unexpected error", "playVideo");
                        Logger.a(InternalLogLevel.ERROR, "InMobi", "Error playing video; SDK encountered an unexpected error");
                        c.b;
                        new StringBuilder("SDK encountered unexpected error in handling playVideo() request from creative; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public String getState(String str) {
        return this.c.getState().toLowerCase(Locale.ENGLISH);
    }

    @JavascriptInterface
    public String getScreenSize(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", com.inmobi.commons.core.utilities.b.c.a().a);
            jSONObject.put("height", com.inmobi.commons.core.utilities.b.c.a().b);
        } catch (JSONException unused) {
        } catch (Exception e2) {
            this.c.b(str, "Unexpected error", "getScreenSize");
            new StringBuilder("SDK encountered unexpected error while getting screen dimensions; ").append(e2.getMessage());
        }
        return jSONObject.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:1|2|(2:4|(2:6|7)(1:8))|9|(3:13|5b|21)|25|26|27|28|29) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0066 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x007f */
    @android.webkit.JavascriptInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getMaxSize(java.lang.String r7) {
        /*
            r6 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            com.inmobi.rendering.RenderView r1 = r6.c     // Catch:{ Exception -> 0x008e }
            android.app.Activity r1 = r1.getFullScreenActivity()     // Catch:{ Exception -> 0x008e }
            if (r1 != 0) goto L_0x0024
            com.inmobi.rendering.RenderView r1 = r6.c     // Catch:{ Exception -> 0x008e }
            android.content.Context r1 = r1.getContainerContext()     // Catch:{ Exception -> 0x008e }
            boolean r1 = r1 instanceof android.app.Activity     // Catch:{ Exception -> 0x008e }
            if (r1 != 0) goto L_0x001c
            java.lang.String r7 = r6.getScreenSize(r7)     // Catch:{ Exception -> 0x008e }
            return r7
        L_0x001c:
            com.inmobi.rendering.RenderView r1 = r6.c     // Catch:{ Exception -> 0x008e }
            android.content.Context r1 = r1.getContainerContext()     // Catch:{ Exception -> 0x008e }
            android.app.Activity r1 = (android.app.Activity) r1     // Catch:{ Exception -> 0x008e }
        L_0x0024:
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r1 = r1.findViewById(r2)     // Catch:{ Exception -> 0x008e }
            android.widget.FrameLayout r1 = (android.widget.FrameLayout) r1     // Catch:{ Exception -> 0x008e }
            android.widget.FrameLayout r1 = (android.widget.FrameLayout) r1     // Catch:{ Exception -> 0x008e }
            int r2 = r1.getWidth()     // Catch:{ Exception -> 0x008e }
            int r2 = com.inmobi.commons.core.utilities.b.c.b(r2)     // Catch:{ Exception -> 0x008e }
            int r3 = r1.getHeight()     // Catch:{ Exception -> 0x008e }
            int r3 = com.inmobi.commons.core.utilities.b.c.b(r3)     // Catch:{ Exception -> 0x008e }
            com.inmobi.rendering.RenderView r4 = r6.c     // Catch:{ Exception -> 0x008e }
            android.app.Activity r4 = r4.getFullScreenActivity()     // Catch:{ Exception -> 0x008e }
            if (r4 == 0) goto L_0x0075
            if (r2 == 0) goto L_0x004b
            if (r3 != 0) goto L_0x0075
        L_0x004b:
            com.inmobi.rendering.c$a r2 = new com.inmobi.rendering.c$a     // Catch:{ Exception -> 0x008e }
            r2.<init>(r1)     // Catch:{ Exception -> 0x008e }
            android.view.ViewTreeObserver r1 = r1.getViewTreeObserver()     // Catch:{ Exception -> 0x008e }
            r1.addOnGlobalLayoutListener(r2)     // Catch:{ Exception -> 0x008e }
            java.lang.Boolean r1 = r2.d     // Catch:{ Exception -> 0x008e }
            monitor-enter(r1)     // Catch:{ Exception -> 0x008e }
            java.lang.Boolean r3 = r2.d     // Catch:{ InterruptedException -> 0x0066 }
            r3.wait()     // Catch:{ InterruptedException -> 0x0066 }
            goto L_0x0066
        L_0x0064:
            r2 = move-exception
            goto L_0x0073
        L_0x0066:
            int r3 = r2.a     // Catch:{ all -> 0x0064 }
            int r2 = r2.b     // Catch:{ all -> 0x0064 }
            monitor-exit(r1)     // Catch:{ all -> 0x0064 }
            r5 = r3
            r3 = r2
            r2 = r5
            goto L_0x0075
        L_0x0073:
            monitor-exit(r1)     // Catch:{ all -> 0x0064 }
            throw r2     // Catch:{ Exception -> 0x008e }
        L_0x0075:
            java.lang.String r1 = "width"
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x007f }
            java.lang.String r1 = "height"
            r0.put(r1, r3)     // Catch:{ JSONException -> 0x007f }
        L_0x007f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008e }
            java.lang.String r2 = "getMaxSize called:"
            r1.<init>(r2)     // Catch:{ Exception -> 0x008e }
            java.lang.String r2 = r0.toString()     // Catch:{ Exception -> 0x008e }
            r1.append(r2)     // Catch:{ Exception -> 0x008e }
            goto L_0x00a6
        L_0x008e:
            r1 = move-exception
            com.inmobi.rendering.RenderView r2 = r6.c
            java.lang.String r3 = "Unexpected error"
            java.lang.String r4 = "getMaxSize"
            r2.b(r7, r3, r4)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r2 = "SDK encountered unexpected error in handling getMaxSize() request from creative; "
            r7.<init>(r2)
            java.lang.String r1 = r1.getMessage()
            r7.append(r1)
        L_0x00a6:
            java.lang.String r7 = r0.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.getMaxSize(java.lang.String):java.lang.String");
    }

    @JavascriptInterface
    public void close(final String str) {
        new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
            public final void run() {
                try {
                    c.this.c.getReferenceContainer().b();
                } catch (Exception e) {
                    c.this.c.b(str, "Unexpected error", "close");
                    Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to close ad; SDK encountered an unexpected error");
                    c.b;
                    new StringBuilder("SDK encountered an expected error in handling the close() request from creative; ").append(e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public String getPlacementType(String str) {
        return PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.d.a ? "interstitial" : "inline";
    }

    @JavascriptInterface
    @TargetApi(23)
    public void createCalendarEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        String str12 = str;
        String str13 = str3;
        String str14 = str4;
        RenderView renderView = this.c;
        if (renderView != null) {
            if (!renderView.e()) {
                this.c.c("createCalendarEvent");
            } else if (this.c.e("calendar")) {
                if (str13 == null || str3.trim().length() == 0 || str14 == null || str4.trim().length() == 0) {
                    this.c.b(str12, "Mandatory parameter(s) start and/or end date not supplied", "createCalendarEvent");
                    return;
                }
                StringBuilder sb = new StringBuilder("createCalendarEvent called with parameters: \nevent ID: ");
                sb.append(str2);
                sb.append("; startDate: ");
                sb.append(str13);
                sb.append("; endDate: ");
                sb.append(str14);
                sb.append("; location: ");
                sb.append(str5);
                sb.append("; description: ");
                sb.append(str6);
                sb.append("; summary: ");
                sb.append(str7);
                sb.append("; status: ");
                sb.append(str8);
                sb.append("; transparency: ");
                sb.append(str9);
                sb.append("; recurrence: ");
                sb.append(str10);
                sb.append("; reminder: ");
                sb.append(str11);
                Context b2 = com.inmobi.commons.a.a.b();
                if (b2 != null) {
                    if (VERSION.SDK_INT < 23 || (b2.checkSelfPermission("android.permission.WRITE_CALENDAR") == 0 && b2.checkSelfPermission("android.permission.READ_CALENDAR") == 0)) {
                        try {
                            this.c.a(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
                        } catch (Exception e2) {
                            this.c.b(str12, "Unexpected error", "createCalendarEvent");
                            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not create calendar event; SDK encountered unexpected error");
                            new StringBuilder("SDK encountered unexpected error in handling createCalendarEvent() request from creative; ").append(e2.getMessage());
                        }
                    } else {
                        AnonymousClass2 r0 = r1;
                        final String str15 = str;
                        final String str16 = str2;
                        String[] strArr = {"android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR"};
                        final String str17 = str3;
                        final String str18 = str4;
                        final String str19 = str5;
                        final String str20 = str6;
                        final String str21 = str7;
                        final String str22 = str8;
                        final String str23 = str9;
                        final String str24 = str10;
                        String[] strArr2 = strArr;
                        final String str25 = str11;
                        AnonymousClass2 r1 = new InMobiAdActivity.b() {
                            public final void a(int[] iArr) {
                                if (iArr.length == 2 && iArr[0] == 0 && iArr[1] == 0) {
                                    try {
                                        c.this.c.a(str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, str25);
                                    } catch (Exception e2) {
                                        c.this.c.b(str15, "Unexpected error", "createCalendarEvent");
                                        Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not create calendar event; SDK encountered unexpected error");
                                        c.b;
                                        new StringBuilder("SDK encountered unexpected error in handling createCalendarEvent() request from creative; ").append(e2.getMessage());
                                    }
                                } else {
                                    c.this.c.b(str15, "Permission denied by user.", "createCalendarEvent");
                                }
                            }
                        };
                        InMobiAdActivity.a(strArr2, (InMobiAdActivity.b) r0);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:28|29|(3:38|39|40)|41|42) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00fc */
    @android.webkit.JavascriptInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void postToSocial(java.lang.String r6, int r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r5 = this;
            com.inmobi.rendering.RenderView r0 = r5.c
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r0.e()
            if (r0 != 0) goto L_0x0013
            com.inmobi.rendering.RenderView r6 = r5.c
            java.lang.String r7 = "postToSocial"
            r6.c(r7)
            return
        L_0x0013:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "postToSocial called with parameters: socialType: "
            r0.<init>(r1)
            r0.append(r7)
            java.lang.String r1 = "; text: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = "; link: "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r1 = "; image URL: "
            r0.append(r1)
            r0.append(r10)
            com.inmobi.rendering.RenderView r0 = r5.c     // Catch:{ Exception -> 0x0113 }
            java.lang.String r1 = "postToSocial"
            boolean r1 = r0.e(r1)     // Catch:{ Exception -> 0x0113 }
            if (r1 == 0) goto L_0x0112
            com.inmobi.rendering.mraid.i r1 = r0.i     // Catch:{ Exception -> 0x0113 }
            android.content.Context r0 = r0.getContainerContext()     // Catch:{ Exception -> 0x0113 }
            if (r8 == 0) goto L_0x0108
            int r2 = r8.length()     // Catch:{ Exception -> 0x0113 }
            if (r2 == 0) goto L_0x0108
            if (r9 == 0) goto L_0x0108
            int r2 = r9.length()     // Catch:{ Exception -> 0x0113 }
            if (r2 == 0) goto L_0x0108
            java.lang.String r2 = "http"
            boolean r2 = r9.startsWith(r2)     // Catch:{ Exception -> 0x0113 }
            if (r2 == 0) goto L_0x0108
            if (r10 == 0) goto L_0x0108
            int r2 = r10.length()     // Catch:{ Exception -> 0x0113 }
            if (r2 == 0) goto L_0x0108
            java.lang.String r2 = "http"
            boolean r2 = r10.startsWith(r2)     // Catch:{ Exception -> 0x0113 }
            if (r2 == 0) goto L_0x0108
            java.lang.String r2 = ".jpg"
            boolean r2 = r10.endsWith(r2)     // Catch:{ Exception -> 0x0113 }
            if (r2 != 0) goto L_0x0077
            goto L_0x0108
        L_0x0077:
            r2 = 0
            switch(r7) {
                case 1: goto L_0x00f6;
                case 2: goto L_0x00b1;
                case 3: goto L_0x007f;
                default: goto L_0x007b;
            }     // Catch:{ Exception -> 0x0113 }
        L_0x007b:
            com.inmobi.rendering.RenderView r7 = r1.a     // Catch:{ Exception -> 0x0113 }
            goto L_0x0100
        L_0x007f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0113 }
            r1.<init>()     // Catch:{ Exception -> 0x0113 }
            r1.append(r8)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r2 = " "
            r1.append(r2)     // Catch:{ Exception -> 0x0113 }
            r1.append(r9)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r2 = " "
            r1.append(r2)     // Catch:{ Exception -> 0x0113 }
            r1.append(r10)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0113 }
            java.lang.String r2 = "com.twitter.android"
            android.content.Intent r3 = new android.content.Intent     // Catch:{ Exception -> 0x0113 }
            r3.<init>()     // Catch:{ Exception -> 0x0113 }
            java.lang.String r4 = "text/plain"
            r3.setType(r4)     // Catch:{ Exception -> 0x0113 }
            r3.setPackage(r2)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r2 = "android.intent.extra.TEXT"
            r3.putExtra(r2, r1)     // Catch:{ Exception -> 0x0113 }
            r2 = r3
            goto L_0x00f6
        L_0x00b1:
            java.lang.String r1 = "ads"
            boolean r1 = com.inmobi.commons.core.utilities.f.a(r1)     // Catch:{ Exception -> 0x0113 }
            if (r1 == 0) goto L_0x00f6
            boolean r1 = com.inmobi.rendering.mraid.i.a()     // Catch:{ Exception -> 0x0113 }
            if (r1 == 0) goto L_0x00f6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0113 }
            r1.<init>()     // Catch:{ Exception -> 0x0113 }
            r1.append(r8)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r2 = " "
            r1.append(r2)     // Catch:{ Exception -> 0x0113 }
            r1.append(r9)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r2 = " "
            r1.append(r2)     // Catch:{ Exception -> 0x0113 }
            r1.append(r10)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0113 }
            com.google.android.gms.plus.PlusShare$Builder r2 = new com.google.android.gms.plus.PlusShare$Builder     // Catch:{ Exception -> 0x0113 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r3 = "text/plain"
            com.google.android.gms.plus.PlusShare$Builder r2 = r2.setType(r3)     // Catch:{ Exception -> 0x0113 }
            com.google.android.gms.plus.PlusShare$Builder r1 = r2.setText(r1)     // Catch:{ Exception -> 0x0113 }
            android.net.Uri r2 = android.net.Uri.parse(r10)     // Catch:{ Exception -> 0x0113 }
            com.google.android.gms.plus.PlusShare$Builder r1 = r1.setContentUrl(r2)     // Catch:{ Exception -> 0x0113 }
            android.content.Intent r2 = r1.getIntent()     // Catch:{ Exception -> 0x0113 }
        L_0x00f6:
            if (r2 == 0) goto L_0x00fc
            com.inmobi.commons.a.a.a(r0, r2)     // Catch:{ ActivityNotFoundException -> 0x00fc }
            return
        L_0x00fc:
            com.inmobi.rendering.mraid.i.a(r0, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0113 }
            goto L_0x0112
        L_0x0100:
            java.lang.String r8 = "Unsupported type of social network"
            java.lang.String r9 = "postToSocial"
            r7.b(r6, r8, r9)     // Catch:{ Exception -> 0x0113 }
            return
        L_0x0108:
            com.inmobi.rendering.RenderView r7 = r1.a     // Catch:{ Exception -> 0x0113 }
            java.lang.String r8 = "Attempting to share with null/empty/invalid parameters"
            java.lang.String r9 = "postToSocial"
            r7.b(r6, r8, r9)     // Catch:{ Exception -> 0x0113 }
            return
        L_0x0112:
            return
        L_0x0113:
            r7 = move-exception
            com.inmobi.rendering.RenderView r8 = r5.c
            java.lang.String r9 = "Unexpected error"
            java.lang.String r10 = "postToSocial"
            r8.b(r6, r9, r10)
            com.inmobi.commons.core.utilities.Logger$InternalLogLevel r6 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR
            java.lang.String r8 = "InMobi"
            java.lang.String r9 = "Could not post to social network; SDK encountered an unexpected error"
            com.inmobi.commons.core.utilities.Logger.a(r6, r8, r9)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "SDK encountered an unexpected error in handling the postToSocial() request from creative; "
            r6.<init>(r8)
            java.lang.String r7 = r7.getMessage()
            r6.append(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.postToSocial(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    @JavascriptInterface
    public String supports(String str, String str2) {
        if (Arrays.asList(a).contains(str2) || this.c.e(str2)) {
            return String.valueOf(this.c.e(str2));
        }
        return "false";
    }

    @JavascriptInterface
    public void openExternal(String str, String str2, @Nullable String str3) {
        RenderView renderView = this.c;
        if (renderView != null) {
            if (!renderView.e()) {
                this.c.c("openExternal");
            } else {
                this.c.a("openExternal", str, str2, str3);
            }
        }
    }

    @JavascriptInterface
    public void asyncPing(String str, String str2) {
        if (!URLUtil.isValidUrl(str2)) {
            this.c.b(str, "Invalid url", "asyncPing");
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("command", "ping");
            hashMap.put("scheme", br.a(str));
            this.c.a("CreativeInvokedAction", (Map<String, Object>) hashMap);
            final com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpConstants.METHOD_GET, str2);
            cVar.u = false;
            cVar.A = false;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            new com.inmobi.commons.core.network.a(cVar, new C0047a() {
                public final void a(d dVar) {
                    c.b;
                    try {
                        n.a().a(cVar.g());
                        n.a().b(dVar.c());
                        n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
                    } catch (Exception e) {
                        c.b;
                        new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                    }
                }

                public final void b(d dVar) {
                    c.b;
                }
            }).a();
        } catch (Exception e2) {
            this.c.b(str, "Unexpected error", "asyncPing");
            new StringBuilder("SDK encountered internal error in handling asyncPing() request from creative; ").append(e2.getMessage());
        }
    }

    @JavascriptInterface
    public void disableCloseRegion(final String str, final boolean z) {
        RenderView renderView = this.c;
        if (renderView != null) {
            new Handler(renderView.getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        c.this.c.a(z);
                    } catch (Exception e) {
                        c.this.c.b(str, "Unexpected error", "disableCloseRegion");
                        c.b;
                        new StringBuilder("SDK encountered unexpected error in handling disableCloseRegion() request from creative; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:25|26|27) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r4.c.getListener().b(new java.util.HashMap<>());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0090, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0091, code lost:
        r4.c.b(r5, "Unexpected error", "onUserInteraction");
        new java.lang.StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(r6.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a8, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0081 */
    @android.webkit.JavascriptInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUserInteraction(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            com.inmobi.rendering.RenderView r0 = r4.c
            if (r0 == 0) goto L_0x0012
            boolean r0 = r0.e()
            if (r0 != 0) goto L_0x0012
            com.inmobi.rendering.RenderView r5 = r4.c
            java.lang.String r6 = "onUserInteraction"
            r5.c(r6)
            return
        L_0x0012:
            if (r6 != 0) goto L_0x003c
            com.inmobi.rendering.RenderView r6 = r4.c     // Catch:{ Exception -> 0x0023 }
            com.inmobi.rendering.RenderView$a r6 = r6.getListener()     // Catch:{ Exception -> 0x0023 }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x0023 }
            r0.<init>()     // Catch:{ Exception -> 0x0023 }
            r6.b(r0)     // Catch:{ Exception -> 0x0023 }
            return
        L_0x0023:
            r6 = move-exception
            com.inmobi.rendering.RenderView r0 = r4.c
            java.lang.String r1 = "Unexpected error"
            java.lang.String r2 = "onUserInteraction"
            r0.b(r5, r1, r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; "
            r5.<init>(r0)
            java.lang.String r6 = r6.getMessage()
            r5.append(r6)
            return
        L_0x003c:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0081 }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x0081 }
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ JSONException -> 0x0081 }
            r6.<init>()     // Catch:{ JSONException -> 0x0081 }
            java.util.Iterator r1 = r0.keys()     // Catch:{ JSONException -> 0x0081 }
        L_0x004a:
            boolean r2 = r1.hasNext()     // Catch:{ JSONException -> 0x0081 }
            if (r2 == 0) goto L_0x005e
            java.lang.Object r2 = r1.next()     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ JSONException -> 0x0081 }
            java.lang.Object r3 = r0.get(r2)     // Catch:{ JSONException -> 0x0081 }
            r6.put(r2, r3)     // Catch:{ JSONException -> 0x0081 }
            goto L_0x004a
        L_0x005e:
            com.inmobi.rendering.RenderView r0 = r4.c     // Catch:{ Exception -> 0x0068 }
            com.inmobi.rendering.RenderView$a r0 = r0.getListener()     // Catch:{ Exception -> 0x0068 }
            r0.b(r6)     // Catch:{ Exception -> 0x0068 }
            return
        L_0x0068:
            r6 = move-exception
            com.inmobi.rendering.RenderView r0 = r4.c     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r1 = "Unexpected error"
            java.lang.String r2 = "onUserInteraction"
            r0.b(r5, r1, r2)     // Catch:{ JSONException -> 0x0081 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r1 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; "
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0081 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ JSONException -> 0x0081 }
            r0.append(r6)     // Catch:{ JSONException -> 0x0081 }
            return
        L_0x0081:
            com.inmobi.rendering.RenderView r6 = r4.c     // Catch:{ Exception -> 0x0090 }
            com.inmobi.rendering.RenderView$a r6 = r6.getListener()     // Catch:{ Exception -> 0x0090 }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x0090 }
            r0.<init>()     // Catch:{ Exception -> 0x0090 }
            r6.b(r0)     // Catch:{ Exception -> 0x0090 }
            return
        L_0x0090:
            r6 = move-exception
            com.inmobi.rendering.RenderView r0 = r4.c
            java.lang.String r1 = "Unexpected error"
            java.lang.String r2 = "onUserInteraction"
            r0.b(r5, r1, r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; "
            r5.<init>(r0)
            java.lang.String r6 = r6.getMessage()
            r5.append(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.onUserInteraction(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:19|20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r4.c.getListener().a(new java.util.HashMap<>());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007e, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007f, code lost:
        r4.c.b(r5, "Unexpected error", "incentCompleted");
        new java.lang.StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(r6.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0096, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006f */
    @android.webkit.JavascriptInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void incentCompleted(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            if (r6 != 0) goto L_0x002a
            com.inmobi.rendering.RenderView r6 = r4.c     // Catch:{ Exception -> 0x0011 }
            com.inmobi.rendering.RenderView$a r6 = r6.getListener()     // Catch:{ Exception -> 0x0011 }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x0011 }
            r0.<init>()     // Catch:{ Exception -> 0x0011 }
            r6.a(r0)     // Catch:{ Exception -> 0x0011 }
            return
        L_0x0011:
            r6 = move-exception
            com.inmobi.rendering.RenderView r0 = r4.c
            java.lang.String r1 = "Unexpected error"
            java.lang.String r2 = "incentCompleted"
            r0.b(r5, r1, r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; "
            r5.<init>(r0)
            java.lang.String r6 = r6.getMessage()
            r5.append(r6)
            return
        L_0x002a:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x006f }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x006f }
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ JSONException -> 0x006f }
            r6.<init>()     // Catch:{ JSONException -> 0x006f }
            java.util.Iterator r1 = r0.keys()     // Catch:{ JSONException -> 0x006f }
        L_0x0038:
            boolean r2 = r1.hasNext()     // Catch:{ JSONException -> 0x006f }
            if (r2 == 0) goto L_0x004c
            java.lang.Object r2 = r1.next()     // Catch:{ JSONException -> 0x006f }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ JSONException -> 0x006f }
            java.lang.Object r3 = r0.get(r2)     // Catch:{ JSONException -> 0x006f }
            r6.put(r2, r3)     // Catch:{ JSONException -> 0x006f }
            goto L_0x0038
        L_0x004c:
            com.inmobi.rendering.RenderView r0 = r4.c     // Catch:{ Exception -> 0x0056 }
            com.inmobi.rendering.RenderView$a r0 = r0.getListener()     // Catch:{ Exception -> 0x0056 }
            r0.a(r6)     // Catch:{ Exception -> 0x0056 }
            return
        L_0x0056:
            r6 = move-exception
            com.inmobi.rendering.RenderView r0 = r4.c     // Catch:{ JSONException -> 0x006f }
            java.lang.String r1 = "Unexpected error"
            java.lang.String r2 = "incentCompleted"
            r0.b(r5, r1, r2)     // Catch:{ JSONException -> 0x006f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x006f }
            java.lang.String r1 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; "
            r0.<init>(r1)     // Catch:{ JSONException -> 0x006f }
            java.lang.String r6 = r6.getMessage()     // Catch:{ JSONException -> 0x006f }
            r0.append(r6)     // Catch:{ JSONException -> 0x006f }
            return
        L_0x006f:
            com.inmobi.rendering.RenderView r6 = r4.c     // Catch:{ Exception -> 0x007e }
            com.inmobi.rendering.RenderView$a r6 = r6.getListener()     // Catch:{ Exception -> 0x007e }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x007e }
            r0.<init>()     // Catch:{ Exception -> 0x007e }
            r6.a(r0)     // Catch:{ Exception -> 0x007e }
            return
        L_0x007e:
            r6 = move-exception
            com.inmobi.rendering.RenderView r0 = r4.c
            java.lang.String r1 = "Unexpected error"
            java.lang.String r2 = "incentCompleted"
            r0.b(r5, r1, r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; "
            r5.<init>(r0)
            java.lang.String r6 = r6.getMessage()
            r5.append(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.incentCompleted(java.lang.String, java.lang.String):void");
    }

    @JavascriptInterface
    public String getOrientation(String str) {
        int b2 = com.inmobi.commons.core.utilities.b.c.b();
        if (b2 == 1) {
            return "0";
        }
        if (b2 == 3) {
            return "90";
        }
        if (b2 == 2) {
            return "180";
        }
        return b2 == 4 ? "270" : FoodIntake.UNIT_TYPE_NOT_DEFINED;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:9|10|11|12|13|14|15) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003e */
    @android.webkit.JavascriptInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveContent(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            r4 = this;
            if (r6 == 0) goto L_0x00b0
            int r0 = r6.length()
            if (r0 == 0) goto L_0x00b0
            if (r7 == 0) goto L_0x00b0
            int r0 = r7.length()
            if (r0 != 0) goto L_0x0012
            goto L_0x00b0
        L_0x0012:
            com.inmobi.rendering.RenderView r0 = r4.c     // Catch:{ Exception -> 0x0097 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0097 }
            java.lang.String r2 = "saveContent called: content ID: "
            r1.<init>(r2)     // Catch:{ Exception -> 0x0097 }
            r1.append(r6)     // Catch:{ Exception -> 0x0097 }
            java.lang.String r2 = "; URL: "
            r1.append(r2)     // Catch:{ Exception -> 0x0097 }
            r1.append(r7)     // Catch:{ Exception -> 0x0097 }
            java.lang.String r1 = "saveContent"
            boolean r1 = r0.e(r1)     // Catch:{ Exception -> 0x0097 }
            if (r1 != 0) goto L_0x0069
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0097 }
            r1.<init>()     // Catch:{ Exception -> 0x0097 }
            java.lang.String r2 = "url"
            r1.put(r2, r7)     // Catch:{ JSONException -> 0x003e }
            java.lang.String r7 = "reason"
            r2 = 5
            r1.put(r7, r2)     // Catch:{ JSONException -> 0x003e }
        L_0x003e:
            java.lang.String r7 = r1.toString()     // Catch:{ Exception -> 0x0097 }
            java.lang.String r1 = "\""
            java.lang.String r2 = "\\\""
            java.lang.String r7 = r7.replace(r1, r2)     // Catch:{ Exception -> 0x0097 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0097 }
            java.lang.String r2 = "sendSaveContentResult(\"saveContent_"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0097 }
            r1.append(r6)     // Catch:{ Exception -> 0x0097 }
            java.lang.String r6 = "\", 'failed', \""
            r1.append(r6)     // Catch:{ Exception -> 0x0097 }
            r1.append(r7)     // Catch:{ Exception -> 0x0097 }
            java.lang.String r6 = "\");"
            r1.append(r6)     // Catch:{ Exception -> 0x0097 }
            java.lang.String r6 = r1.toString()     // Catch:{ Exception -> 0x0097 }
            r0.a(r5, r6)     // Catch:{ Exception -> 0x0097 }
            return
        L_0x0069:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ Exception -> 0x0097 }
            r1.<init>()     // Catch:{ Exception -> 0x0097 }
            com.inmobi.ads.bm r2 = new com.inmobi.ads.bm     // Catch:{ Exception -> 0x0097 }
            r3 = -1
            r2.<init>(r3, r7)     // Catch:{ Exception -> 0x0097 }
            r1.add(r2)     // Catch:{ Exception -> 0x0097 }
            com.inmobi.ads.cache.b r7 = new com.inmobi.ads.cache.b     // Catch:{ Exception -> 0x0097 }
            java.util.UUID r2 = java.util.UUID.randomUUID()     // Catch:{ Exception -> 0x0097 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0097 }
            com.inmobi.ads.cache.f r0 = r0.w     // Catch:{ Exception -> 0x0097 }
            r7.<init>(r2, r1, r0, r6)     // Catch:{ Exception -> 0x0097 }
            r7.g = r5     // Catch:{ Exception -> 0x0097 }
            com.inmobi.ads.cache.AssetStore r6 = com.inmobi.ads.cache.AssetStore.a()     // Catch:{ Exception -> 0x0097 }
            java.util.concurrent.ExecutorService r0 = r6.c     // Catch:{ Exception -> 0x0097 }
            com.inmobi.ads.cache.AssetStore$4 r1 = new com.inmobi.ads.cache.AssetStore$4     // Catch:{ Exception -> 0x0097 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0097 }
            r0.execute(r1)     // Catch:{ Exception -> 0x0097 }
            return
        L_0x0097:
            r6 = move-exception
            com.inmobi.rendering.RenderView r7 = r4.c
            java.lang.String r0 = "Unexpected error"
            java.lang.String r1 = "saveContent"
            r7.b(r5, r0, r1)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "SDK encountered unexpected error in handling saveContent() request from creative; "
            r5.<init>(r7)
            java.lang.String r6 = r6.getMessage()
            r5.append(r6)
            return
        L_0x00b0:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "url"
            if (r7 != 0) goto L_0x00bb
            java.lang.String r7 = ""
        L_0x00bb:
            r0.put(r1, r7)     // Catch:{ JSONException -> 0x00c4 }
            java.lang.String r7 = "reason"
            r1 = 1
            r0.put(r7, r1)     // Catch:{ JSONException -> 0x00c4 }
        L_0x00c4:
            java.lang.String r7 = r0.toString()
            java.lang.String r0 = "\""
            java.lang.String r1 = "\\\""
            java.lang.String r7 = r7.replace(r0, r1)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "sendSaveContentResult(\"saveContent_"
            r0.<init>(r1)
            if (r6 != 0) goto L_0x00db
            java.lang.String r6 = ""
        L_0x00db:
            r0.append(r6)
            java.lang.String r6 = "\", 'failed', \""
            r0.append(r6)
            r0.append(r7)
            java.lang.String r6 = "\");"
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            com.inmobi.rendering.RenderView r7 = r4.c
            r7.a(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.saveContent(java.lang.String, java.lang.String, java.lang.String):void");
    }

    @JavascriptInterface
    public void cancelSaveContent(String str, String str2) {
        try {
            RenderView.d();
        } catch (Exception e2) {
            this.c.b(str, "Unexpected error", "cancelSaveContent");
            new StringBuilder("SDK encountered unexpected error in handling cancelSaveContent() request from creative; ").append(e2.getMessage());
        }
    }

    @JavascriptInterface
    public String isDeviceMuted(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return "false";
        }
        boolean z = false;
        try {
            renderView.getMediaProcessor();
            z = MraidMediaProcessor.a();
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in checking if device is muted; ").append(e2.getMessage());
        }
        return String.valueOf(z);
    }

    @JavascriptInterface
    public String isHeadphonePlugged(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return "false";
        }
        boolean z = false;
        try {
            renderView.getMediaProcessor();
            z = MraidMediaProcessor.d();
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in checking if headphones are plugged-in; ").append(e2.getMessage());
        }
        return String.valueOf(z);
    }

    @JavascriptInterface
    public void registerDeviceMuteEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                MraidMediaProcessor mediaProcessor = renderView.getMediaProcessor();
                Context b2 = com.inmobi.commons.a.a.b();
                if (b2 != null && mediaProcessor.c == null) {
                    mediaProcessor.c = new RingerModeChangeReceiver(str);
                    b2.registerReceiver(mediaProcessor.c, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
                }
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "registerDeviceMuteEventListener");
                new StringBuilder("SDK encountered unexpected error in handling registerDeviceMuteEventListener() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterDeviceMuteEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                renderView.getMediaProcessor().b();
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "unRegisterDeviceMuteEventListener");
                new StringBuilder("SDK encountered unexpected error in handling unregisterDeviceMuteEventListener() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void registerDeviceVolumeChangeEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                MraidMediaProcessor mediaProcessor = renderView.getMediaProcessor();
                Context b2 = com.inmobi.commons.a.a.b();
                if (b2 != null && mediaProcessor.d == null) {
                    mediaProcessor.d = new com.inmobi.rendering.mraid.MraidMediaProcessor.a(str, b2, new Handler());
                    b2.getContentResolver().registerContentObserver(System.CONTENT_URI, true, mediaProcessor.d);
                }
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "registerDeviceVolumeChangeEventListener");
                new StringBuilder("SDK encountered unexpected error in handling registerDeviceVolumeChangeEventListener() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterDeviceVolumeChangeEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                renderView.getMediaProcessor().c();
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "unregisterDeviceVolumeChangeEventListener");
                new StringBuilder("SDK encountered unexpected error in handling unregisterDeviceVolumeChangeEventListener() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public int getDeviceVolume(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return -1;
        }
        try {
            MraidMediaProcessor mediaProcessor = renderView.getMediaProcessor();
            Context b2 = com.inmobi.commons.a.a.b();
            if (b2 == null) {
                return -1;
            }
            if (!mediaProcessor.a.getRenderingConfig().m || !com.inmobi.commons.a.a.d()) {
                return ((AudioManager) b2.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamVolume(3);
            }
            return 0;
        } catch (Exception e2) {
            this.c.b(str, "Unexpected error", "getDeviceVolume");
            new StringBuilder("SDK encountered unexpected error in handling getDeviceVolume() request from creative; ").append(e2.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public void registerHeadphonePluggedEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                MraidMediaProcessor mediaProcessor = renderView.getMediaProcessor();
                Context b2 = com.inmobi.commons.a.a.b();
                if (b2 != null && mediaProcessor.e == null) {
                    mediaProcessor.e = new HeadphonesPluggedChangeReceiver(str);
                    b2.registerReceiver(mediaProcessor.e, new IntentFilter("android.intent.action.HEADSET_PLUG"));
                }
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "registerHeadphonePluggedEventListener");
                new StringBuilder("SDK encountered unexpected error in handling registerHeadphonePluggedEventListener() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterHeadphonePluggedEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                renderView.getMediaProcessor().e();
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "unregisterHeadphonePluggedEventListener");
                new StringBuilder("SDK encountered unexpected error in handling unregisterHeadphonePluggedEventListener() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void disableBackButton(String str, boolean z) {
        RenderView renderView = this.c;
        if (renderView != null) {
            renderView.setDisableBackButton(z);
        }
    }

    @JavascriptInterface
    public boolean isBackButtonDisabled(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return false;
        }
        return renderView.q;
    }

    @JavascriptInterface
    public void registerBackButtonPressedEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                renderView.r = str;
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "registerBackButtonPressedEventListener");
                new StringBuilder("SDK encountered unexpected error in handling registerBackButtonPressedEventListener() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterBackButtonPressedEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                renderView.r = null;
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "unregisterBackButtonPressedEventListener");
                new StringBuilder("SDK encountered unexpected error in handling unregisterBackButtonPressedEventListener() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void startDownloader(String str, String str2, String str3, String str4) {
        RenderView renderView = this.c;
        if (renderView != null) {
            if (!renderView.e()) {
                this.c.c("startDownloader");
                return;
            }
            try {
                if (TextUtils.isEmpty(str2)) {
                    this.c.b(str, "Invalid URL", "startDownloader");
                    return;
                }
                RenderView renderView2 = this.c;
                AdContainer referenceContainer = renderView2.getReferenceContainer();
                if (referenceContainer instanceof ah) {
                    ah ahVar = (ah) referenceContainer;
                    ak.a(str2, str3, str4);
                    ahVar.a(renderView2);
                    return;
                }
                if (referenceContainer instanceof RenderView) {
                    ak.a(str2, str3, str4);
                }
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "startDownloader");
                new StringBuilder("SDK encountered unexpected error in handling startDownloader() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void registerDownloaderCallbacks(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                AdContainer referenceContainer = renderView.getReferenceContainer();
                if (referenceContainer instanceof ah) {
                    ((ah) referenceContainer).a(renderView);
                }
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "registerDownloaderCallbacks");
                new StringBuilder("SDK encountered unexpected error in handling registerDownloaderCallbacks() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterDownloaderCallbacks(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                AdContainer referenceContainer = renderView.getReferenceContainer();
                if (referenceContainer instanceof ah) {
                    ah ahVar = (ah) referenceContainer;
                    if (ahVar.z != null) {
                        ahVar.z.remove(renderView);
                    }
                }
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "unregisterDownloaderCallbacks");
                new StringBuilder("SDK encountered unexpected error in handling unregisterDownloaderCallbacks() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public int getDownloadProgress(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return -1;
        }
        try {
            return renderView.getDownloadProgress();
        } catch (Exception e2) {
            this.c.b(str, "Unexpected error", "getDownloadProgress");
            new StringBuilder("SDK encountered unexpected error in handling getDownloadProgress() request from creative; ").append(e2.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public int getDownloadStatus(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return -1;
        }
        try {
            return renderView.getDownloadStatus();
        } catch (Exception e2) {
            this.c.b(str, "Unexpected error", "getDownloadStatus");
            new StringBuilder("SDK encountered unexpected error in handling getDownloadStatus() request from creative; ").append(e2.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public void setCloseEndCardTracker(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView != null) {
            try {
                renderView.setCloseEndCardTracker(str2);
            } catch (Exception e2) {
                this.c.b(str, "Unexpected error", "getDownloadStatus");
                new StringBuilder("SDK encountered unexpected error in handling getDownloadStatus() request from creative; ").append(e2.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void fireSkip(String str) {
        RenderView.f();
    }

    @JavascriptInterface
    public void fireComplete(String str) {
        if (this.c != null) {
            RenderView.g();
        }
    }

    @JavascriptInterface
    public void showEndCard(String str) {
        RenderView renderView = this.c;
        if (renderView != null) {
            AdContainer referenceContainer = renderView.getReferenceContainer();
            if (referenceContainer instanceof ah) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        ah ahVar = ah.this;
                        ahVar.r = true;
                        ahVar.c((ak) null);
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void saveBlob(String str, String str2) {
        RenderView renderView = this.c;
        if (!(renderView == null || renderView.u == null)) {
            renderView.u.e(str2);
        }
    }

    @JavascriptInterface
    public void getBlob(String str, String str2) {
        RenderView renderView = this.c;
        if (!(renderView == null || renderView.u == null)) {
            renderView.u.a(str, str2, renderView);
        }
    }
}
