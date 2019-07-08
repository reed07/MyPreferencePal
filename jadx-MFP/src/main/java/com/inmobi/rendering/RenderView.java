package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.fitness.data.WorkoutExercises;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.inmobi.a.n;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.NativeTracker;
import com.inmobi.ads.NativeVideoWrapper;
import com.inmobi.ads.ad;
import com.inmobi.ads.bd;
import com.inmobi.ads.be;
import com.inmobi.ads.bq;
import com.inmobi.ads.br;
import com.inmobi.ads.c;
import com.inmobi.ads.ca;
import com.inmobi.ads.cb;
import com.inmobi.ads.o;
import com.inmobi.ads.v;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.mraid.MraidMediaProcessor;
import com.inmobi.rendering.mraid.b;
import com.inmobi.rendering.mraid.d;
import com.inmobi.rendering.mraid.e;
import com.inmobi.rendering.mraid.f;
import com.inmobi.rendering.mraid.g;
import com.inmobi.rendering.mraid.h;
import com.inmobi.rendering.mraid.i;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.myfitnesspal.shared.constants.Constants.BusyStates;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "ViewConstructor", "ClickableViewAccessibility"})
public final class RenderView extends WebView implements AdContainer, b {
    public static final a a = new a() {
        public final void A() {
        }

        public final void B() {
        }

        public final void G() {
        }

        public final void a(RenderView renderView) {
        }

        public final void a(HashMap<Object, Object> hashMap) {
        }

        public final void b(RenderView renderView) {
        }

        public final void b(String str, Map<String, Object> map) {
        }

        public final void b(HashMap<Object, Object> hashMap) {
        }

        public final void c(RenderView renderView) {
        }

        public final void d(RenderView renderView) {
        }

        public final void w() {
        }

        public final void y() {
        }
    };
    /* access modifiers changed from: private */
    public static final String x = RenderView.class.getSimpleName();
    /* access modifiers changed from: private */
    public boolean A = false;
    private WeakReference<ViewGroup> B;
    private c C;
    private c D;
    /* access modifiers changed from: private */
    public List<String> E = new ArrayList();
    private boolean F;
    private b G;
    private h H;
    private g I;
    private JSONObject J;
    private JSONObject K;
    private boolean L = true;
    /* access modifiers changed from: private */
    public boolean M = false;
    private final Object N = new Object();
    private final Object O = new Object();
    private boolean P = true;
    /* access modifiers changed from: private */
    public View Q;
    /* access modifiers changed from: private */
    public CustomViewCallback R;
    private int S = -1;
    private boolean T = false;
    private long U = Long.MIN_VALUE;
    private String V;
    private String W;
    private AdContainer aa;
    private o ab;
    private boolean ac = false;
    private boolean ad;
    @Nullable
    private Set<bq> ae;
    private ca af;
    private final com.inmobi.ads.AdContainer.a ag = new com.inmobi.ads.AdContainer.a() {
        public final void a() {
            RenderView.x;
            if (RenderView.this.c != null) {
                RenderView.this.c.A();
            }
        }

        public final void a(Object obj) {
            RenderView.x;
            if (PlacementType.PLACEMENT_TYPE_INLINE == RenderView.this.e.a) {
                if (RenderView.this.y != null) {
                    RenderView.this.y.setAndUpdateViewState("Expanded");
                } else {
                    RenderView.this.setAndUpdateViewState("Expanded");
                }
                RenderView.this.t = false;
            }
            if (RenderView.this.c != null) {
                RenderView.this.c.c(RenderView.this);
            }
        }

        public final void b(Object obj) {
            RenderView.x;
            if (PlacementType.PLACEMENT_TYPE_INLINE == RenderView.this.e.a) {
                RenderView.this.setAndUpdateViewState("Default");
                if (RenderView.this.y != null) {
                    RenderView.this.y.setAndUpdateViewState("Default");
                }
            } else if ("Default".equals(RenderView.this.d)) {
                RenderView.this.setAndUpdateViewState("Hidden");
            }
            if (RenderView.this.c != null) {
                RenderView.this.c.d(RenderView.this);
            }
        }
    };
    private final WebViewClient ah = new WebViewClient() {
        public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            RenderView.x;
            if (VERSION.SDK_INT < 21) {
                return false;
            }
            String uri = webResourceRequest.getUrl().toString();
            if (RenderView.this.j) {
                webView.loadUrl(uri);
                return true;
            } else if (RenderView.this.e() || RenderView.this.A || "about:blank".equals(uri)) {
                RenderView.x;
                StringBuilder sb = new StringBuilder("Placement type: ");
                sb.append(RenderView.this.e.a);
                sb.append(" url:");
                sb.append(uri);
                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN != RenderView.this.e.a) {
                    RenderView.x;
                    if (com.inmobi.commons.core.utilities.b.a(RenderView.this.getContainerContext(), uri, null) != null) {
                        RenderView.this.getListener().B();
                    }
                    return true;
                } else if (!RenderView.this.A || !com.inmobi.commons.core.utilities.b.a(uri)) {
                    RenderView.x;
                    if (com.inmobi.commons.core.utilities.b.a(RenderView.this.getContainerContext(), uri, null) != null) {
                        RenderView.this.getListener().B();
                    }
                    return true;
                } else {
                    RenderView.x;
                    return false;
                }
            } else {
                RenderView.this.c("redirect");
                return true;
            }
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            RenderView.x;
            if (RenderView.this.j) {
                webView.loadUrl(str);
                return true;
            } else if (RenderView.this.e() || RenderView.this.A || "about:blank".equals(str)) {
                RenderView.x;
                StringBuilder sb = new StringBuilder("Placement type: ");
                sb.append(RenderView.this.e.a);
                sb.append(" url:");
                sb.append(str);
                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN != RenderView.this.e.a) {
                    RenderView.x;
                    if (com.inmobi.commons.core.utilities.b.a(RenderView.this.getContainerContext(), str, null) != null) {
                        RenderView.this.getListener().B();
                    }
                    return true;
                } else if (!RenderView.this.A || !com.inmobi.commons.core.utilities.b.a(str)) {
                    RenderView.x;
                    if (com.inmobi.commons.core.utilities.b.a(RenderView.this.getContainerContext(), str, null) != null) {
                        RenderView.this.getListener().B();
                    }
                    return true;
                } else {
                    RenderView.x;
                    return false;
                }
            } else {
                RenderView.this.c("redirect");
                return true;
            }
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            RenderView.x;
            RenderView.this.M = false;
            RenderView.this.setAndUpdateViewState("Loading");
        }

        public final void onPageFinished(WebView webView, String str) {
            RenderView.x;
            if (RenderView.this.E.contains(str) && !RenderView.this.M) {
                RenderView.this.M = true;
                RenderView.x;
                RenderView renderView = RenderView.this;
                renderView.d(renderView.getMraidJsString());
            }
            if ("Loading".equals(RenderView.this.d)) {
                RenderView.this.c.a(RenderView.this);
                RenderView.k(RenderView.this);
                if (RenderView.this.y != null) {
                    RenderView.this.setAndUpdateViewState("Expanded");
                    return;
                }
                RenderView.this.setAndUpdateViewState("Default");
            }
        }

        public final void onLoadResource(WebView webView, String str) {
            RenderView.x;
            String url = RenderView.this.getUrl();
            if (str != null && url != null && str.contains("/mraid.js") && !url.equals("about:blank") && !url.startsWith("file:")) {
                if (!RenderView.this.E.contains(url)) {
                    RenderView.this.E.add(url);
                }
                if (!RenderView.this.M) {
                    RenderView.this.M = true;
                    RenderView.x;
                    RenderView renderView = RenderView.this;
                    renderView.d(renderView.getMraidJsString());
                }
            }
        }

        @TargetApi(22)
        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            RenderView.x;
            StringBuilder sb = new StringBuilder("Loading error. Error code:");
            sb.append(i);
            sb.append(" Error msg:");
            sb.append(str);
            sb.append(" Failing url:");
            sb.append(str2);
        }

        @TargetApi(23)
        public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            RenderView.x;
            StringBuilder sb = new StringBuilder("Loading error. Error code:");
            sb.append(webResourceError.getErrorCode());
            sb.append(" Error msg:");
            sb.append(webResourceError.getDescription());
            sb.append(" Failing url:");
            sb.append(webResourceRequest.getUrl());
        }
    };
    private final WebChromeClient ai = new WebChromeClient() {
        public final boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            RenderView.x;
            StringBuilder sb = new StringBuilder("jsAlert called with: ");
            sb.append(str2);
            sb.append(str);
            if (RenderView.a(RenderView.this, jsResult)) {
                Activity fullScreenActivity = RenderView.this.getFullScreenActivity();
                if (fullScreenActivity != null) {
                    new Builder(fullScreenActivity).setMessage(str2).setTitle(str).setPositiveButton(17039370, new OnClickListener() {
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            jsResult.confirm();
                        }
                    }).setCancelable(false).create().show();
                } else {
                    jsResult.cancel();
                }
            }
            return true;
        }

        public final boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            RenderView.x;
            StringBuilder sb = new StringBuilder("jsConfirm called with: ");
            sb.append(str2);
            sb.append(str);
            if (RenderView.a(RenderView.this, jsResult)) {
                Activity fullScreenActivity = RenderView.this.getFullScreenActivity();
                if (fullScreenActivity != null) {
                    new Builder(fullScreenActivity).setMessage(str2).setPositiveButton(17039370, new OnClickListener() {
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            jsResult.confirm();
                        }
                    }).setNegativeButton(17039360, new OnClickListener() {
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            jsResult.cancel();
                        }
                    }).create().show();
                } else {
                    jsResult.cancel();
                }
            }
            return true;
        }

        public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            RenderView.x;
            StringBuilder sb = new StringBuilder("jsPrompt called with: ");
            sb.append(str2);
            sb.append(str);
            if (!RenderView.a(RenderView.this, (JsResult) jsPromptResult)) {
                return true;
            }
            if (RenderView.this.getFullScreenActivity() != null) {
                return false;
            }
            jsPromptResult.cancel();
            return true;
        }

        public final void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            if (RenderView.this.b != null && RenderView.this.b.get() != null) {
                RenderView.this.Q = view;
                RenderView.this.R = customViewCallback;
                RenderView.this.Q.setOnTouchListener(new OnTouchListener() {
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
                FrameLayout frameLayout = (FrameLayout) ((Activity) RenderView.this.b.get()).findViewById(16908290);
                RenderView.this.Q.setBackgroundColor(-16777216);
                frameLayout.addView(RenderView.this.Q, new LayoutParams(-1, -1, 0, 0));
                RenderView.this.Q.requestFocus();
                View m = RenderView.this.Q;
                m.setOnKeyListener(new OnKeyListener() {
                    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (4 != keyEvent.getKeyCode() || keyEvent.getAction() != 0) {
                            return false;
                        }
                        RenderView.x;
                        AnonymousClass5.this.a();
                        return true;
                    }
                });
                m.setFocusable(true);
                m.setFocusableInTouchMode(true);
                m.requestFocus();
            }
        }

        public final void onHideCustomView() {
            a();
            super.onHideCustomView();
        }

        /* access modifiers changed from: private */
        public void a() {
            if (RenderView.this.Q != null) {
                if (RenderView.this.R != null) {
                    RenderView.this.R.onCustomViewHidden();
                    RenderView.this.R = null;
                }
                if (!(RenderView.this.Q == null || RenderView.this.Q.getParent() == null)) {
                    ((ViewGroup) RenderView.this.Q.getParent()).removeView(RenderView.this.Q);
                    RenderView.this.Q = null;
                }
            }
        }

        public final void onGeolocationPermissionsShowPrompt(final String str, final Callback callback) {
            if (!(RenderView.this.b == null || RenderView.this.b.get() == null)) {
                new Builder((Context) RenderView.this.b.get()).setTitle("Location Permission").setMessage("Allow location access").setPositiveButton(17039370, new OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        callback.invoke(str, true, false);
                    }
                }).setNegativeButton(17039360, new OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        callback.invoke(str, false, false);
                    }
                }).create().show();
            }
            super.onGeolocationPermissionsShowPrompt(str, callback);
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            StringBuilder sb = new StringBuilder();
            sb.append(consoleMessage.message());
            sb.append(" -- From line ");
            sb.append(consoleMessage.lineNumber());
            sb.append(" of ");
            sb.append(consoleMessage.sourceId());
            RenderView.x;
            return true;
        }
    };
    /* access modifiers changed from: 0000 */
    public WeakReference<Activity> b;
    /* access modifiers changed from: 0000 */
    public a c;
    /* access modifiers changed from: 0000 */
    public String d = "Default";
    /* access modifiers changed from: 0000 */
    public RenderingProperties e;
    com.inmobi.rendering.mraid.c f;
    f g;
    MraidMediaProcessor h;
    i i;
    public boolean j;
    boolean k = true;
    boolean l = true;
    public boolean m = false;
    boolean n = false;
    boolean o = false;
    boolean p = false;
    boolean q = false;
    String r = null;
    public AtomicBoolean s = new AtomicBoolean(false);
    /* access modifiers changed from: 0000 */
    public boolean t;
    a u;
    public boolean v;
    final com.inmobi.ads.cache.f w = new com.inmobi.ads.cache.f() {
        public final void a(com.inmobi.ads.cache.b bVar) {
            if (bVar.g != null && bVar.a.size() > 0) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", ((com.inmobi.ads.cache.a) bVar.a.get(0)).d);
                    jSONObject.put("reason", ((com.inmobi.ads.cache.a) bVar.a.get(0)).l);
                } catch (JSONException unused) {
                }
                String replace = jSONObject.toString().replace("\"", "\\\"");
                StringBuilder sb = new StringBuilder("sendSaveContentResult(\"saveContent_");
                sb.append(bVar.h);
                sb.append("\", 'failed', \"");
                sb.append(replace);
                sb.append("\");");
                String sb2 = sb.toString();
                RenderView.x;
                RenderView.this.a(bVar.g, sb2);
            }
        }

        public final void b(com.inmobi.ads.cache.b bVar) {
            if (bVar.g != null && bVar.a.size() > 0) {
                StringBuilder sb = new StringBuilder("sendSaveContentResult(\"saveContent_");
                sb.append(bVar.h);
                sb.append("\", 'success', \"");
                sb.append(((com.inmobi.ads.cache.a) bVar.a.get(0)).k);
                sb.append("\");");
                String sb2 = sb.toString();
                RenderView.x;
                RenderView.this.a(bVar.g, sb2);
            }
        }
    };
    /* access modifiers changed from: private */
    public RenderView y;
    @Nullable
    private WeakReference<Activity> z;

    public interface a {
        void A();

        void B();

        void G();

        void a(RenderView renderView);

        void a(HashMap<Object, Object> hashMap);

        void b(RenderView renderView);

        void b(String str, Map<String, Object> map);

        void b(HashMap<Object, Object> hashMap);

        void c(RenderView renderView);

        void d(RenderView renderView);

        void w();

        void y();
    }

    public static void d() {
    }

    static void f() {
    }

    static void g() {
    }

    public final Object getDataModel() {
        return null;
    }

    public final String getMarkupType() {
        return AdType.HTML;
    }

    @Nullable
    public final View getVideoContainerView() {
        return null;
    }

    public RenderView(Context context, RenderingProperties renderingProperties, @Nullable Set<bq> set, @Nullable String str) {
        super(context.getApplicationContext());
        if (context instanceof Activity) {
            this.z = new WeakReference<>((Activity) context);
        }
        this.y = null;
        this.e = renderingProperties;
        this.t = false;
        this.ae = set;
        this.W = str;
        setReferenceContainer(this);
        this.aa = this;
        this.ab = new o();
        this.ad = false;
    }

    public final void setIsPreload(boolean z2) {
        this.v = z2;
    }

    public final void setPlacementId(long j2) {
        this.U = j2;
    }

    public final void setImpressionId(String str) {
        this.W = str;
    }

    public final void setCreativeId(String str) {
        this.V = str;
    }

    public final void setAllowAutoRedirection(boolean z2) {
        this.ac = z2;
    }

    public final void setBlobProvider(a aVar) {
        this.u = aVar;
    }

    public final String getImpressionId() {
        return this.W;
    }

    public final String getCreativeId() {
        return this.V;
    }

    public final long getPlacementId() {
        return this.U;
    }

    public final boolean getAllowAutoRedirection() {
        return this.ac;
    }

    public final void setOriginalRenderView(RenderView renderView) {
        this.y = renderView;
    }

    public final RenderView getOriginalRenderView() {
        return this.y;
    }

    public final com.inmobi.ads.AdContainer.a getFullScreenEventsListener() {
        return this.ag;
    }

    public final RenderingProperties getRenderingProperties() {
        return this.e;
    }

    public final String getState() {
        return this.d;
    }

    public final Object getDefaultPositionMonitor() {
        return this.N;
    }

    public final Object getCurrentPositionMonitor() {
        return this.O;
    }

    public final void setDefaultPositionLock() {
        this.k = true;
    }

    public final void setCurrentPositionLock() {
        this.l = true;
    }

    @NonNull
    public final Context getContainerContext() {
        WeakReference<Activity> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            return getContext();
        }
        return (Context) this.b.get();
    }

    public final void setDefaultPosition() {
        int[] iArr = new int[2];
        this.J = new JSONObject();
        if (this.B == null) {
            this.B = new WeakReference<>((ViewGroup) getParent());
        }
        if (this.B.get() != null) {
            ((ViewGroup) this.B.get()).getLocationOnScreen(iArr);
            try {
                this.J.put(AvidJSONUtil.KEY_X, com.inmobi.commons.core.utilities.b.c.b(iArr[0]));
                this.J.put("y", com.inmobi.commons.core.utilities.b.c.b(iArr[1]));
                int b2 = com.inmobi.commons.core.utilities.b.c.b(((ViewGroup) this.B.get()).getWidth());
                int b3 = com.inmobi.commons.core.utilities.b.c.b(((ViewGroup) this.B.get()).getHeight());
                this.J.put("width", b2);
                this.J.put("height", b3);
            } catch (JSONException unused) {
            }
        } else {
            this.J.put(AvidJSONUtil.KEY_X, 0);
            this.J.put("y", 0);
            this.J.put("width", 0);
            this.J.put("height", 0);
        }
        synchronized (this.N) {
            this.k = false;
            this.N.notifyAll();
        }
    }

    public final String getDefaultPosition() {
        JSONObject jSONObject = this.J;
        return jSONObject == null ? "" : jSONObject.toString();
    }

    public final void setCurrentPosition() {
        this.K = new JSONObject();
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        try {
            this.K.put(AvidJSONUtil.KEY_X, com.inmobi.commons.core.utilities.b.c.b(iArr[0]));
            this.K.put("y", com.inmobi.commons.core.utilities.b.c.b(iArr[1]));
            int b2 = com.inmobi.commons.core.utilities.b.c.b(getWidth());
            int b3 = com.inmobi.commons.core.utilities.b.c.b(getHeight());
            this.K.put("width", b2);
            this.K.put("height", b3);
        } catch (JSONException unused) {
        }
        synchronized (this.O) {
            this.l = false;
            this.O.notifyAll();
        }
    }

    public final String getCurrentPosition() {
        JSONObject jSONObject = this.K;
        return jSONObject == null ? "" : jSONObject.toString();
    }

    public final void setFullScreenActivityContext(Activity activity) {
        this.b = new WeakReference<>(activity);
        g gVar = this.I;
        if (gVar != null) {
            setOrientationProperties(gVar);
        }
    }

    public final Activity getFullScreenActivity() {
        WeakReference<Activity> weakReference = this.b;
        if (weakReference == null) {
            return null;
        }
        return (Activity) weakReference.get();
    }

    public final Activity getPubActivity() {
        WeakReference<Activity> weakReference = this.z;
        if (weakReference == null) {
            return null;
        }
        return (Activity) weakReference.get();
    }

    public final c.i getRenderingConfig() {
        return this.D.i;
    }

    public final c.g getMraidConfig() {
        return this.D.j;
    }

    /* access modifiers changed from: protected */
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        StringBuilder sb = new StringBuilder("onSizeChanged (");
        sb.append(i2);
        sb.append(", ");
        sb.append(i3);
        sb.append(")");
        if (i2 != 0 && i3 != 0) {
            int b2 = com.inmobi.commons.core.utilities.b.c.b(i2);
            int b3 = com.inmobi.commons.core.utilities.b.c.b(i3);
            StringBuilder sb2 = new StringBuilder("window.mraidview.broadcastEvent('sizeChange',");
            sb2.append(b2);
            sb2.append(",");
            sb2.append(b3);
            sb2.append(");");
            d(sb2.toString());
        }
    }

    public final void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        boolean z2 = i2 == 0;
        if (this.o != z2) {
            d(z2);
        }
    }

    public final void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        this.T = !z2;
        c(z2);
    }

    public final void onScreenStateChanged(int i2) {
        super.onScreenStateChanged(i2);
        if (i2 == 0) {
            c(false);
            return;
        }
        if (!this.T) {
            c(true);
        }
    }

    private void c(boolean z2) {
        if (this.o != z2) {
            if (VERSION.SDK_INT <= 23 || getFullScreenActivity() == null || !getFullScreenActivity().isInMultiWindowMode()) {
                d(z2);
            }
        }
    }

    private void d(boolean z2) {
        if (!this.t) {
            this.o = z2;
            if (!z2) {
                this.i.a(getContainerContext());
            } else {
                this.c.b(this);
            }
            boolean z3 = this.o;
            StringBuilder sb = new StringBuilder("window.mraidview.broadcastEvent('viewableChange',");
            sb.append(z3);
            sb.append(");");
            d(sb.toString());
        }
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.F = isHardwareAccelerated();
        if (this.B == null) {
            this.B = new WeakReference<>((ViewGroup) getParent());
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        new StringBuilder("Touch event received, action:").append(motionEvent.getAction());
        this.ad = true;
        if (e()) {
            d("window.mraidview.onUserInteraction();");
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public final void onDetachedFromWindow() {
        this.E.clear();
        getMediaProcessor().b();
        getMediaProcessor().c();
        getMediaProcessor().e();
        this.i.a(getContainerContext());
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e2) {
            StringBuilder sb = new StringBuilder("Detaching WebView from window encountered an error (");
            sb.append(e2.getMessage());
            sb.append(")");
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "IllegalArgumentException");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(e2.getMessage());
                hashMap.put("message", sb2.toString());
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("ads", "ExceptionCaught", hashMap);
            } catch (Exception e3) {
                StringBuilder sb3 = new StringBuilder("Error in submitting telemetey event : (");
                sb3.append(e3.getMessage());
                sb3.append(")");
            }
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    @TargetApi(19)
    public final void a(a aVar, @NonNull c cVar) {
        this.D = cVar;
        this.c = aVar;
        this.B = new WeakReference<>((ViewGroup) getParent());
        if (WorkoutExercises.ROW.contains("staging") && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (getRenderingConfig() != null) {
            setBackgroundColor(getRenderingConfig().f);
        }
        if (getMraidConfig() != null) {
            if ((System.currentTimeMillis() / 1000) - new d().a.b("last_updated_ts", 0) > getMraidConfig().a) {
                e eVar = new e(getMraidConfig().d, getMraidConfig().b, getMraidConfig().c);
                if (eVar.a != null) {
                    eVar.b = new com.inmobi.commons.core.network.c(HttpConstants.METHOD_GET, eVar.a);
                    eVar.b.A = false;
                    HashMap hashMap = new HashMap();
                    hashMap.put(HttpHeaders.ACCEPT_ENCODING, "gzip");
                    eVar.b.a((Map<String, String>) hashMap);
                    new Thread(new Runnable() {
                        public final void run() {
                            byte[] bArr;
                            int i = 0;
                            while (i <= e.this.d) {
                                e.c;
                                long elapsedRealtime = SystemClock.elapsedRealtime();
                                com.inmobi.commons.core.network.d a2 = new com.inmobi.commons.core.network.e(e.this.b).a();
                                try {
                                    n.a().a(e.this.b.g());
                                    n.a().b(a2.c());
                                    n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
                                } catch (Exception e) {
                                    e.c;
                                    new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                                }
                                if (a2.a()) {
                                    e.c;
                                    i++;
                                    if (i > e.this.d) {
                                        break;
                                    }
                                    try {
                                        Thread.sleep((long) (e.this.e * 1000));
                                    } catch (InterruptedException unused) {
                                        e.c;
                                    }
                                } else {
                                    d dVar = new d();
                                    List list = (List) a2.d.get(HttpHeaders.CONTENT_ENCODING);
                                    if (list == null || !((String) list.get(0)).equals("gzip")) {
                                        dVar.a(a2.b());
                                        e.c;
                                        try {
                                            HashMap hashMap = new HashMap();
                                            hashMap.put("url", e.this.a);
                                            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                                            hashMap.put("payloadSize", Long.valueOf(e.this.b.g() + a2.c()));
                                            com.inmobi.commons.core.e.b.a();
                                            com.inmobi.commons.core.e.b.a("ads", "MraidFetchLatency", hashMap);
                                            return;
                                        } catch (Exception e2) {
                                            e.c;
                                            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                                            sb.append(e2.getMessage());
                                            sb.append(")");
                                            return;
                                        }
                                    } else {
                                        e.c;
                                        if (a2.a == null || a2.a.length == 0) {
                                            bArr = new byte[0];
                                        } else {
                                            bArr = new byte[a2.a.length];
                                            System.arraycopy(a2.a, 0, bArr, 0, a2.a.length);
                                        }
                                        byte[] a3 = com.inmobi.commons.core.utilities.d.a(bArr);
                                        if (a3 != null) {
                                            try {
                                                dVar.a(new String(a3, "UTF-8"));
                                                e.c;
                                                try {
                                                    HashMap hashMap2 = new HashMap();
                                                    hashMap2.put("url", e.this.a);
                                                    hashMap2.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                                                    hashMap2.put("payloadSize", Long.valueOf(e.this.b.g() + a2.c()));
                                                    com.inmobi.commons.core.e.b.a();
                                                    com.inmobi.commons.core.e.b.a("ads", "MraidFetchLatency", hashMap2);
                                                    return;
                                                } catch (Exception e3) {
                                                    e.c;
                                                    StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                                                    sb2.append(e3.getMessage());
                                                    sb2.append(")");
                                                    return;
                                                }
                                            } catch (UnsupportedEncodingException e4) {
                                                e.c;
                                                e.c;
                                                e4.getMessage();
                                            }
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                    }).start();
                }
            }
        }
        if (VERSION.SDK_INT >= 16) {
            setImportantForAccessibility(2);
        }
        setScrollable(false);
        if (VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setGeolocationEnabled(true);
        setWebViewClient(this.ah);
        setWebChromeClient(this.ai);
        this.C = new c(this, this.e);
        addJavascriptInterface(this.C, "sdkController");
        this.f = new com.inmobi.rendering.mraid.c(this);
        this.g = new f(this);
        this.h = new MraidMediaProcessor(this);
        this.i = new i(this);
        this.G = new b();
        this.H = new h();
        this.I = new g();
    }

    public final void setScrollable(boolean z2) {
        setScrollContainer(z2);
        setVerticalScrollBarEnabled(z2);
        setHorizontalScrollBarEnabled(z2);
    }

    /* access modifiers changed from: 0000 */
    public final void setIsInAppBrowser(boolean z2) {
        this.A = z2;
    }

    public final boolean c() {
        return this.s.get();
    }

    @TargetApi(11)
    public final void destroy() {
        if (!this.s.get()) {
            if (!this.L) {
                this.L = true;
                return;
            }
            this.s.set(true);
            this.t = true;
            this.S = -1;
            removeJavascriptInterface("sdkController");
            WeakReference<Activity> weakReference = this.b;
            if (weakReference != null) {
                weakReference.clear();
            }
            WeakReference<ViewGroup> weakReference2 = this.B;
            if (weakReference2 != null) {
                weakReference2.clear();
            }
            ca caVar = this.af;
            if (caVar != null) {
                caVar.d();
                this.af.e();
            }
            this.aa = null;
            ViewParent parent = getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this);
                removeAllViews();
            }
            super.destroy();
        }
    }

    public final void a(int i2, Map<String, String> map) {
        switch (i2) {
            case 1:
                return;
            case 2:
                d("inmobi.recordEvent(120,null);");
                return;
            case 3:
                return;
            default:
                return;
        }
    }

    public final void setRequestedScreenOrientation() {
        if (getFullScreenActivity() != null) {
            g gVar = this.I;
            if (gVar != null) {
                setOrientationProperties(gVar);
            }
        }
    }

    public final void setReferenceContainer(AdContainer adContainer) {
        this.aa = adContainer;
    }

    public final AdContainer getReferenceContainer() {
        return this.aa;
    }

    @NonNull
    public final c getAdConfig() {
        return this.D;
    }

    public final o getApkDownloader() {
        return this.ab;
    }

    @SuppressLint({"SwitchIntDef"})
    @NonNull
    public final ca getViewableAd() {
        Activity activity;
        if (this.af == null) {
            this.af = new cb(this);
            if (getFullScreenActivity() == null) {
                activity = getPubActivity();
            } else {
                activity = getFullScreenActivity();
            }
            Set<bq> set = this.ae;
            if (set != null) {
                if (activity != null) {
                    try {
                        for (bq bqVar : set) {
                            int i2 = bqVar.a;
                            if (i2 == 1) {
                                this.af = new ad(this, activity, this.af, bqVar.b);
                            } else if (i2 == 3) {
                                AbstractAvidAdSession abstractAvidAdSession = (AbstractAvidAdSession) bqVar.b.get("avidAdSession");
                                boolean z2 = bqVar.b.containsKey("deferred") && ((Boolean) bqVar.b.get("deferred")).booleanValue();
                                if (abstractAvidAdSession != null) {
                                    v vVar = new v(this, activity, this.af, abstractAvidAdSession, z2);
                                    this.af = vVar;
                                }
                            }
                        }
                    } catch (Exception e2) {
                        new StringBuilder("Exception occurred while creating the HTML viewable ad : ").append(e2.getMessage());
                    }
                } else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", getMarkupType());
                    String str = this.W;
                    if (str != null) {
                        hashMap.put("impId", str);
                    }
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "TrackersForService", hashMap);
                }
            }
        }
        return this.af;
    }

    public final void a(String str) {
        this.t = false;
        if (!this.s.get()) {
            loadDataWithBaseURL("", str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
        }
    }

    public final void b(String str) {
        this.t = false;
        if (!this.s.get()) {
            loadUrl(str);
        }
    }

    public final void stopLoading() {
        if (!this.s.get()) {
            super.stopLoading();
        }
    }

    public final void b(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder("broadcastEvent('error',\"");
        sb.append(str2);
        sb.append("\", \"");
        sb.append(str3);
        sb.append("\")");
        a(str, sb.toString());
    }

    public final void a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        d(sb.toString());
    }

    public final void a(String str, Map<String, Object> map) {
        this.c.b(str, map);
    }

    public final void d(final String str) {
        if (getContainerContext() != null) {
            new Handler(getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        if (!RenderView.this.s.get()) {
                            StringBuilder sb = new StringBuilder("javascript:try{");
                            sb.append(str);
                            sb.append("}catch(e){}");
                            String sb2 = sb.toString();
                            RenderView.x;
                            if (VERSION.SDK_INT < 19) {
                                RenderView.this.loadUrl(sb2);
                                return;
                            }
                            RenderView.this.evaluateJavascript(sb2, null);
                        }
                    } catch (Exception e) {
                        RenderView.x;
                        new StringBuilder("SDK encountered an unexpected error injecting JavaScript in the Ad container; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    public final void setUseCustomClose(boolean z2) {
        this.m = z2;
    }

    public final void setCloseRegionDisabled(boolean z2) {
        this.p = z2;
    }

    public final void setDisableBackButton(boolean z2) {
        this.q = z2;
    }

    public final void a(boolean z2) {
        setCloseRegionDisabled(z2);
        View rootView = getRootView();
        if (rootView != null) {
            CustomView customView = (CustomView) rootView.findViewById(65531);
            if (customView != null) {
                customView.setVisibility(this.p ? 8 : 0);
            }
        }
    }

    public final void b(boolean z2) {
        setUseCustomClose(z2);
        if (getRootView() != null) {
            CustomView customView = (CustomView) getRootView().findViewById(65532);
            if (customView != null) {
                customView.setVisibility(this.m ? 8 : 0);
            }
        }
    }

    public final void b() {
        MraidMediaProcessor mraidMediaProcessor = this.h;
        if (mraidMediaProcessor.b != null) {
            mraidMediaProcessor.b.a();
            mraidMediaProcessor.b = null;
        }
        if ("Expanded".equals(this.d)) {
            if (!"Default".equals(this.d)) {
                this.t = true;
                com.inmobi.rendering.mraid.c cVar = this.f;
                if (cVar.a.getOriginalRenderView() == null) {
                    View findViewById = cVar.c.getRootView().findViewById(BusyStates.ALL);
                    ((ViewGroup) cVar.a.getParent()).removeView(cVar.a);
                    ((ViewGroup) findViewById.getParent()).removeView(findViewById);
                    cVar.c.addView(cVar.a, cVar.d, new RelativeLayout.LayoutParams(cVar.c.getWidth(), cVar.c.getHeight()));
                    cVar.a.j();
                }
                i();
                this.t = false;
            }
            this.L = false;
        } else if ("Resized".equals(this.d)) {
            if (!"Default".equals(this.d)) {
                this.t = true;
                f fVar = this.g;
                ViewGroup viewGroup = (ViewGroup) fVar.a.getParent();
                View findViewById2 = viewGroup.getRootView().findViewById(65534);
                View findViewById3 = fVar.b.getRootView().findViewById(BusyStates.ALL);
                ((ViewGroup) findViewById2.getParent()).removeView(findViewById2);
                ((ViewGroup) findViewById3.getParent()).removeView(findViewById3);
                viewGroup.removeView(fVar.a);
                fVar.b.addView(fVar.a, fVar.c, new RelativeLayout.LayoutParams(fVar.b.getWidth(), fVar.b.getHeight()));
                fVar.a.j();
                setAndUpdateViewState("Default");
                this.c.d(this);
                this.t = false;
            }
        } else if ("Default".equals(this.d)) {
            setAndUpdateViewState("Hidden");
            if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.e.a) {
                i();
            } else {
                ((ViewGroup) getParent()).removeAllViews();
            }
        }
        this.E.clear();
        this.n = false;
    }

    private void i() {
        InMobiAdActivity.a((Object) this);
        Activity fullScreenActivity = getFullScreenActivity();
        if (fullScreenActivity != null) {
            ((InMobiAdActivity) fullScreenActivity).a = true;
            fullScreenActivity.finish();
            int i2 = this.S;
            if (i2 != -1) {
                fullScreenActivity.overridePendingTransition(0, i2);
            }
        } else {
            if (PlacementType.PLACEMENT_TYPE_INLINE == this.e.a) {
                setAndUpdateViewState("Default");
                RenderView renderView = this.y;
                if (renderView != null) {
                    renderView.setAndUpdateViewState("Default");
                }
            } else if ("Default".equals(this.d)) {
                setAndUpdateViewState("Hidden");
            }
            a aVar = this.c;
            if (aVar != null) {
                aVar.d(this);
            }
        }
    }

    public final void setExitAnimation(int i2) {
        this.S = i2;
    }

    private static String f(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public final void a(String str, String str2, String str3, @Nullable String str4) {
        if (str3 != null) {
            a(str, str2, str3, str4, false);
        } else if (str4 != null) {
            a(str, str2, str4, null, true);
        } else {
            String str5 = str2;
            b(str2, "Empty url and fallback url", "openExternal");
        }
    }

    private void a(String str, String str2, String str3, @Nullable String str4, boolean z2) {
        while (true) {
            try {
                com.inmobi.commons.core.utilities.b.b(getContainerContext(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put("command", "openExternal");
                hashMap.put("scheme", br.a(str2));
                this.c.b("CreativeInvokedAction", hashMap);
                getListener().B();
                StringBuilder sb = new StringBuilder("broadcastEvent('");
                sb.append(str);
                sb.append("Successful','");
                sb.append(str3);
                sb.append("');");
                a(str2, sb.toString());
                return;
            } catch (URISyntaxException e2) {
                if (z2) {
                    b("DeeplinkFallbackFailed", str3);
                } else {
                    b("DeeplinkFailed", str3);
                }
                new StringBuilder("Error message in processing openExternal: ").append(e2.getMessage());
                StringBuilder sb2 = new StringBuilder("Cannot resolve URI (");
                sb2.append(f(str3));
                sb2.append(")");
                b(str2, sb2.toString(), str);
                if (str4 != null) {
                    str3 = str4;
                    str4 = null;
                    z2 = true;
                } else {
                    return;
                }
            } catch (ActivityNotFoundException e3) {
                if (z2) {
                    b("DeeplinkFallbackFailed", str3);
                } else {
                    b("DeeplinkFailed", str3);
                }
                new StringBuilder("Error message in processing openExternal: ").append(e3.getMessage());
                StringBuilder sb3 = new StringBuilder("Cannot resolve URI (");
                sb3.append(f(str3));
                sb3.append(")");
                b(str2, sb3.toString(), str);
                if (str4 != null) {
                    str3 = str4;
                    str4 = null;
                    z2 = true;
                } else {
                    return;
                }
            } catch (Exception e4) {
                b(str2, "Unexpected error", "openExternal");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not open URL; SDK encountered an unexpected error");
                new StringBuilder("SDK encountered unexpected error in handling openExternal() request from creative; ").append(e4.getMessage());
                return;
            }
        }
    }

    private void b(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str2);
        a(str, (Map<String, Object>) hashMap);
    }

    public final void c(String str, String str2, String str3) {
        if (str3 == null || (str3.startsWith(Constants.HTTP) && !URLUtil.isValidUrl(str3))) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" called with invalid url (");
            sb.append(str3);
            sb.append(")");
            b(str2, "Invalid URL", str);
        } else if (!str3.startsWith(Constants.HTTP) || str3.contains(Uri.GOOGLE_PLAY) || str3.contains("market.android.com") || str3.contains("market%3A%2F%2F")) {
            a(str, str2, str3, null);
        } else {
            InMobiAdActivity.a(this);
            Intent intent = new Intent(getContainerContext(), InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 100);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str3);
            com.inmobi.commons.a.a.a(getContainerContext(), intent);
            StringBuilder sb2 = new StringBuilder("broadcastEvent('");
            sb2.append(str);
            sb2.append("Successful','");
            sb2.append(str3);
            sb2.append("');");
            a(str2, sb2.toString());
            HashMap hashMap = new HashMap();
            hashMap.put("command", "openEmbedded");
            hashMap.put("scheme", br.a(str2));
            this.c.b("CreativeInvokedAction", hashMap);
        }
    }

    public final void setRenderViewEventListener(a aVar) {
        this.c = aVar;
    }

    public final a getListener() {
        a aVar = this.c;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = a;
        this.c = aVar2;
        return aVar2;
    }

    public final String getViewState() {
        return this.d;
    }

    public final MraidMediaProcessor getMediaProcessor() {
        return this.h;
    }

    public final b getExpandProperties() {
        return this.G;
    }

    public final h getResizeProperties() {
        return this.H;
    }

    public final void setResizeProperties(h hVar) {
        this.H = hVar;
    }

    public final void setAndUpdateViewState(String str) {
        this.d = str;
        new StringBuilder("set state:").append(this.d);
        String lowerCase = this.d.toLowerCase(Locale.ENGLISH);
        StringBuilder sb = new StringBuilder("window.mraidview.broadcastEvent('stateChange','");
        sb.append(lowerCase);
        sb.append("');");
        d(sb.toString());
    }

    private void j() {
        setVisibility(0);
        requestLayout();
    }

    public final void setAdActiveFlag(boolean z2) {
        this.n = z2;
    }

    public final g getOrientationProperties() {
        return this.I;
    }

    public final void setOrientationProperties(g gVar) {
        this.I = gVar;
        WeakReference<Activity> weakReference = this.b;
        if (!(weakReference == null || weakReference.get() == null || gVar.a)) {
            String str = gVar.b;
            char c2 = 65535;
            int hashCode = str.hashCode();
            boolean z2 = true;
            if (hashCode != 729267099) {
                if (hashCode == 1430647483 && str.equals(Extras.ORIENTATION_LANDSCAPE)) {
                    c2 = 0;
                }
            } else if (str.equals(Extras.ORIENTATION_PORTRAIT)) {
                c2 = 1;
            }
            switch (c2) {
                case 0:
                    if (!(com.inmobi.commons.core.utilities.b.c.b() == 3 || com.inmobi.commons.core.utilities.b.c.b() == 4)) {
                        z2 = false;
                    }
                    if (z2) {
                        if (3 == com.inmobi.commons.core.utilities.b.c.b()) {
                            ((Activity) this.b.get()).setRequestedOrientation(0);
                            return;
                        } else {
                            ((Activity) this.b.get()).setRequestedOrientation(8);
                            return;
                        }
                    } else if (gVar.c.equals(TtmlNode.LEFT)) {
                        ((Activity) this.b.get()).setRequestedOrientation(8);
                        return;
                    } else if (gVar.c.equals(TtmlNode.RIGHT)) {
                        ((Activity) this.b.get()).setRequestedOrientation(0);
                        return;
                    }
                    break;
                case 1:
                    if (com.inmobi.commons.core.utilities.b.c.b() == 2) {
                        ((Activity) this.b.get()).setRequestedOrientation(9);
                        return;
                    } else {
                        ((Activity) this.b.get()).setRequestedOrientation(1);
                        return;
                    }
                default:
                    if (com.inmobi.commons.core.utilities.b.c.b() != 2) {
                        if (com.inmobi.commons.core.utilities.b.c.b() != 4) {
                            if (com.inmobi.commons.core.utilities.b.c.b() != 3) {
                                ((Activity) this.b.get()).setRequestedOrientation(1);
                                break;
                            } else {
                                ((Activity) this.b.get()).setRequestedOrientation(0);
                                return;
                            }
                        } else {
                            ((Activity) this.b.get()).setRequestedOrientation(8);
                            return;
                        }
                    } else {
                        ((Activity) this.b.get()).setRequestedOrientation(9);
                        return;
                    }
            }
        }
    }

    public final String getMraidJsString() {
        String c2 = new d().a.c("mraid_js_string");
        return c2 == null ? "var imIsObjValid=function(a){return\"undefined\"!=typeof a&&null!=a?!0:!1},EventListeners=function(a){this.event=a;this.count=0;var b=[];this.add=function(a){b.push(a);++this.count};this.remove=function(a){var e=!1,d=this;b=b.filter(function(b){if(b=b===a)--d.count,e=!0;return!b});return e};this.removeAll=function(){b=[];this.count=0};this.broadcast=function(a){b.forEach(function(e){try{e.apply({},a)}catch(d){}})};this.toString=function(){var c=[a,\":\"];b.forEach(function(a){c.push(\"|\",String(a),\"|\")});\nreturn c.join(\"\")}},InmobiObj=function(){this.listeners=[];this.addEventListener=function(a,b){try{if(imIsObjValid(b)&&imIsObjValid(a)){var c=this.listeners;c[a]||(c[a]=new EventListeners);c[a].add(b);\"micIntensityChange\"==a&&window.imraidview.startListeningMicIntensity();\"deviceMuted\"==a&&window.imraidview.startListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&window.imraidview.startListeningDeviceVolumeChange();\"volumeChange\"==a&&window.imraidview.startListeningVolumeChange();\"headphones\"==a&&\nwindow.imraidview.startListeningHeadphonePluggedEvents();\"backButtonPressed\"==a&&window.imraidview.startListeningForBackButtonPressedEvent();\"downloadStatusChanged\"==a&&window.imraidview.registerDownloaderCallbacks()}}catch(e){this.log(e)}};this.removeEventListener=function(a,b){if(imIsObjValid(a)){var c=this.listeners;imIsObjValid(c[a])&&(imIsObjValid(b)?c[a].remove(b):c[a].removeAll());\"micIntensityChange\"==a&&0==c[a].count&&window.imraidview.stopListeningMicIntensity();\"deviceMuted\"==a&&0==c[a].count&&\nwindow.imraidview.stopListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceVolumeChange();\"volumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningVolumeChange();\"headphones\"==a&&0==c[a].count&&window.imraidview.stopListeningHeadphonePluggedEvents();\"backButtonPressed\"==a&&0==c[a].count&&window.imraidview.stopListeningForBackButtonPressedEvent();\"downloadStatusChanged\"==a&&0==c[a].count&&window.imraidview.unregisterDownloaderCallbacks()}};\nthis.broadcastEvent=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)b[c]=arguments[c];c=b.shift();try{this.listeners[c]&&this.listeners[c].broadcast(b)}catch(e){}}};this.sendSaveContentResult=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)if(2==c){var e=arguments[c],e=JSON.parse(e);b[c]=e}else b[c]=arguments[c];e=b[1];\"success\"!=e&&(c=b[0].substring(b[0].indexOf(\"_\")+1),imraid.saveContentIDMap[c]&&delete imraid.saveContentIDMap[c]);\nwindow.imraid.broadcastEvent(b[0],b[1],b[2])}}},__im__iosNativeMessageHandler=void 0;window.webkit&&(window.webkit.messageHandlers&&window.webkit.messageHandlers.nativeMessageHandler)&&(__im__iosNativeMessageHandler=window.webkit.messageHandlers.nativeMessageHandler);\nvar __im__iosNativeCall={nativeCallInFlight:!1,nativeCallQueue:[],executeNativeCall:function(a){this.nativeCallInFlight?this.nativeCallQueue.push(a):(this.nativeCallInFlight=!0,imIsObjValid(__im__iosNativeMessageHandler)?__im__iosNativeMessageHandler.postMessage(a):window.location=a)},nativeCallComplete:function(a){0==this.nativeCallQueue.length?this.nativeCallInFlight=!1:(a=this.nativeCallQueue.shift(),imIsObjValid(__im__iosNativeMessageHandler)?__im__iosNativeMessageHandler.postMessage(a):window.location=\na)}},IOSNativeCall=function(){this.urlScheme=\"\";this.executeNativeCall=function(a){if(imIsObjValid(__im__iosNativeMessageHandler)){e={};e.command=a;e.scheme=this.urlScheme;for(var b={},c=1;c<arguments.length;c+=2)d=arguments[c+1],null!=d&&(b[arguments[c]]=\"\"+d);e.params=b}else for(var e=this.urlScheme+\"://\"+a,d,b=!0,c=1;c<arguments.length;c+=2)d=arguments[c+1],null!=d&&(b?(e+=\"?\",b=!1):e+=\"&\",e+=arguments[c]+\"=\"+escape(d));__im__iosNativeCall.executeNativeCall(e);return\"OK\"};this.nativeCallComplete=\nfunction(a){__im__iosNativeCall.nativeCallComplete(a);return\"OK\"};this.updateKV=function(a,b){this[a]=b;var c=this.broadcastMap[a];c&&this.broadcastEvent(c,b)}};\n(function(){var a=window.mraidview={};a.orientationProperties={allowOrientationChange:!0,forceOrientation:\"none\",direction:\"right\"};var b=[],c=!1;a.detectAndBlockFraud=function(e){a.isPossibleFraud()&&a.fireRedirectFraudBeacon(e);return!1};a.popupBlocked=function(e){a.firePopupBlockedBeacon(e)};a.zeroPad=function(a){var d=\"\";10>a&&(d+=\"0\");return d+a};a.supports=function(a){console.log(\"bridge: supports (MRAID)\");if(\"string\"!=typeof a)window.mraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\n\"supports\");else return\"false\"!=sdkController.supports(\"window.mraidview\",a)};a.useCustomClose=function(a){try{sdkController.useCustomClose(\"window.mraidview\",a)}catch(d){imraidview.showAlert(\"use CustomClose: \"+d)}};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};a.stackCommands=function(a,d){c?b.push(a):(eval(a),d&&(c=!0))};a.expand=function(a){try{\"undefined\"==typeof a&&(a=null),sdkController.expand(\"window.mraidview\",a)}catch(d){imraidview.showAlert(\"executeNativeExpand: \"+\nd+\", URL = \"+a)}};a.setExpandProperties=function(e){try{e?this.props=e:e=null;if(\"undefined\"!=typeof e.lockOrientation&&null!=e.lockOrientation&&\"undefined\"!=typeof e.orientation&&null!=e.orientation){var d={};d.allowOrientationChange=!e.lockOrientation;d.forceOrientation=e.orientation;a.setOrientationProperties(d)}sdkController.setExpandProperties(\"window.mraidview\",a.stringify(e))}catch(b){imraidview.showAlert(\"executeNativesetExpandProperties: \"+b+\", props = \"+e)}};a.getExpandProperties=function(){try{return eval(\"(\"+\nsdkController.getExpandProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getExpandProperties: \"+a)}};a.setOrientationProperties=function(e){try{e?(\"undefined\"!=typeof e.allowOrientationChange&&(a.orientationProperties.allowOrientationChange=e.allowOrientationChange),\"undefined\"!=typeof e.forceOrientation&&(a.orientationProperties.forceOrientation=e.forceOrientation)):e=null,sdkController.setOrientationProperties(\"window.mraidview\",a.stringify(a.orientationProperties))}catch(d){imraidview.showAlert(\"setOrientationProperties: \"+\nd+\", props = \"+e)}};a.getOrientationProperties=function(){return{forceOrientation:a.orientationProperties.forceOrientation,allowOrientationChange:a.orientationProperties.allowOrientationChange}};a.resizeProps=null;a.setResizeProperties=function(e){var d,b;try{d=parseInt(e.width);b=parseInt(e.height);if(isNaN(d)||isNaN(b)||1>d||1>b)throw\"Invalid\";e.width=d;e.height=b;a.resizeProps=e;sdkController.setResizeProperties(\"window.mraidview\",a.stringify(e))}catch(c){window.mraid.broadcastEvent(\"error\",\"Invalid properties.\",\n\"setResizeProperties\")}};a.getResizeProperties=function(){try{return eval(\"(\"+sdkController.getResizeProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getResizeProperties: \"+a)}};a.open=function(a){\"undefined\"==typeof a&&(a=null);try{sdkController.open(\"window.mraidview\",a)}catch(d){imraidview.showAlert(\"open: \"+d)}};a.getScreenSize=function(){try{return eval(\"(\"+sdkController.getScreenSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getScreenSize: \"+a)}};a.getMaxSize=\nfunction(){try{return eval(\"(\"+sdkController.getMaxSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getMaxSize: \"+a)}};a.getCurrentPosition=function(){try{return eval(\"(\"+sdkController.getCurrentPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getCurrentPosition: \"+a)}};a.getDefaultPosition=function(){try{return eval(\"(\"+sdkController.getDefaultPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getDefaultPosition: \"+a)}};a.getState=function(){try{return String(sdkController.getState(\"window.mraidview\"))}catch(a){imraidview.showAlert(\"getState: \"+\na)}};a.isViewable=function(){try{return sdkController.isViewable(\"window.mraidview\")}catch(a){imraidview.showAlert(\"isViewable: \"+a)}};a.getPlacementType=function(){return sdkController.getPlacementType(\"window.mraidview\")};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};\"function\"!=typeof String.prototype.startsWith&&(String.prototype.startsWith=function(a){return 0==this.indexOf(a)});a.playVideo=function(a){var d=\"\";null!=a&&(d=a);try{sdkController.playVideo(\"window.mraidview\",\nd)}catch(b){imraidview.showAlert(\"playVideo: \"+b)}};a.stringify=function(e){if(\"undefined\"===typeof JSON){var d=\"\",b;if(\"undefined\"==typeof e.length)return a.stringifyArg(e);for(b=0;b<e.length;b++)0<b&&(d+=\",\"),d+=a.stringifyArg(e[b]);return d+\"]\"}return JSON.stringify(e)};a.stringifyArg=function(a){var d,b,c;b=typeof a;d=\"\";if(\"number\"===b||\"boolean\"===b)d+=args;else if(a instanceof Array)d=d+\"[\"+a+\"]\";else if(a instanceof Object){b=!0;d+=\"{\";for(c in a)null!==a[c]&&(b||(d+=\",\"),d=d+'\"'+c+'\":',b=\ntypeof a[c],d=\"number\"===b||\"boolean\"===b?d+a[c]:\"function\"===typeof a[c]?d+'\"\"':a[c]instanceof Object?d+this.stringify(args[i][c]):d+'\"'+a[c]+'\"',b=!1);d+=\"}\"}else a=a.replace(/\\\\/g,\"\\\\\\\\\"),a=a.replace(/\"/g,'\\\\\"'),d=d+'\"'+a+'\"';imraidview.showAlert(\"json:\"+d);return d};getPID=function(a){var d=\"\";null!=a&&(\"undefined\"!=typeof a.id&&null!=a.id)&&(d=a.id);return d};a.resize=function(){if(null==a.resizeProps)window.mraid.broadcastEvent(\"error\",\"Valid resize dimensions must be provided before calling resize\",\n\"resize\");else try{sdkController.resize(\"window.mraidview\")}catch(b){imraidview.showAlert(\"resize called in bridge\")}};a.createCalendarEvent=function(a){var d={};\"object\"!=typeof a&&window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects parameter\",\"createCalendarEvent\");if(\"string\"!=typeof a.start||\"string\"!=typeof a.end)window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects string parameters for start and end dates\",\"createCalendarEvent\");else{\"string\"!=typeof a.id&&\n(a.id=\"\");\"string\"!=typeof a.location&&(a.location=\"\");\"string\"!=typeof a.description&&(a.description=\"\");\"string\"!=typeof a.summary&&(a.summary=\"\");\"string\"==typeof a.status&&(\"pending\"==a.status||\"tentative\"==a.status||\"confirmed\"==a.status||\"cancelled\"==a.status)||(a.status=\"\");\"string\"==typeof a.transparency&&(\"opaque\"==a.transparency||\"transparent\"==a.transparency)||(a.transparency=\"\");if(null==a.recurrence||\"\"==a.recurrence)d={};else{\"string\"==typeof a.summary&&(d.frequency=a.recurrence.frequency);\nnull!=a.recurrence.interval&&(d.interval=a.recurrence.interval);\"string\"==typeof a.summary&&(d.expires=a.recurrence.expires);null!=a.recurrence.exceptionDates&&(d.exceptionDates=a.recurrence.exceptionDates);if(null!=a.recurrence.daysInWeek){var b=formatDaysInWeek(a.recurrence.daysInWeek);null!=b?d.daysInWeek=b:imraidview.showAlert(\"daysInWeek invalid format \")}d.daysInMonth=a.recurrence.daysInMonth;d.daysInYear=a.recurrence.daysInYear;d.weeksInMonth=a.recurrence.weeksInMonth;d.monthsInYear=a.recurrence.monthsInYear}\"string\"!=\ntypeof a.reminder&&(a.reminder=\"\");try{sdkController.createCalendarEvent(\"window.mraidview\",a.id,a.start,a.end,a.location,a.description,a.summary,a.status,a.transparency,JSON.stringify(d),a.reminder)}catch(c){sdkController.createCalendarEvent(\"window.mraidview\",a.start,a.end,a.location,a.description)}}};formatDaysInWeek=function(a){try{if(0!=a.length){for(var d=0;d<a.length;d++)switch(a[d]){case 0:a[d]=\"SU\";break;case 1:a[d]=\"MO\";break;case 2:a[d]=\"TU\";break;case 3:a[d]=\"WE\";break;case 4:a[d]=\"TH\";\nbreak;case 5:a[d]=\"FR\";break;case 6:a[d]=\"SA\";break;default:return null}return a}}catch(b){}return null};a.storePicture=function(b){console.log(\"bridge: storePicture\");if(\"string\"!=typeof b)window.mraid.broadcastEvent(\"error\",\"storePicture method expects url as string parameter\",\"storePicture\");else{if(a.supports(\"storePicture\"))return!window.confirm(\"Do you want to download the file?\")?(window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled by user.\",\"storePicture\"),!1):sdkController.storePicture(\"window.mraidview\",\nb);window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled because it is unsupported in this device/app.\",\"storePicture\")}};a.fireMediaTrackingEvent=function(a,d){};a.fireMediaErrorEvent=function(a,d){};a.fireMediaTimeUpdateEvent=function(a,d,b){};a.fireMediaCloseEvent=function(a,d,b){};a.fireMediaVolumeChangeEvent=function(a,d,b){};a.broadcastEvent=function(){window.mraid.broadcastEvent.apply(window.mraid,arguments)}})();\n(function(){var a=window.mraid=new InmobiObj,b=window.mraidview,c=!1;b.isAdShownToUser=!1;b.onUserInteraction=function(){c=!0};b.isPossibleFraud=function(){return a.supports(\"redirectFraudDetection\")&&(!b.isAdShownToUser||!c)};b.fireRedirectFraudBeacon=function(a){if(\"undefined\"!=typeof inmobi&&inmobi.recordEvent){var d={};d.trigger=a;d.isAdShown=b.isAdShownToUser.toString();inmobi.recordEvent(135,d)}};b.firePopupBlockedBeacon=function(a){if(\"undefined\"!=typeof inmobi&&inmobi.recordEvent){var d={};\nd.trigger=a;inmobi.recordEvent(136,d)}};window.onbeforeunload=function(){b.detectAndBlockFraud(\"redirect\")};a.addEventListener(\"viewableChange\",function(a){a&&!b.isAdShownToUser&&(b.isAdShownToUser=!0)});a.useCustomClose=b.useCustomClose;a.close=b.close;a.getExpandProperties=b.getExpandProperties;a.setExpandProperties=function(c){\"undefined\"!=typeof c&&(\"useCustomClose\"in c&&\"undefined\"!=typeof a.getState()&&\"expanded\"!=a.getState())&&a.useCustomClose(c.useCustomClose);b.setExpandProperties(c)};a.getResizeProperties=\nb.getResizeProperties;a.setResizeProperties=b.setResizeProperties;a.getOrientationProperties=b.getOrientationProperties;a.setOrientationProperties=b.setOrientationProperties;a.expand=b.expand;a.getMaxSize=b.getMaxSize;a.getState=b.getState;a.isViewable=b.isViewable;a.createCalendarEvent=function(a){b.detectAndBlockFraud(\"mraid.createCalendarEvent\")||b.createCalendarEvent(a)};a.open=function(c){b.detectAndBlockFraud(\"mraid.open\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"open\"):\nb.open(c))};a.resize=b.resize;a.getVersion=function(){return\"2.0\"};a.getPlacementType=b.getPlacementType;a.playVideo=function(a){b.playVideo(a)};a.getScreenSize=b.getScreenSize;a.getCurrentPosition=b.getCurrentPosition;a.getDefaultPosition=b.getDefaultPosition;a.supports=function(a){return b.supports(a)};a.storePicture=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"storePicture\"):b.storePicture(c)}})();\n(function(){var a=window.imraidview={},b,c=!0;a.setOrientationProperties=function(d){try{d?(\"undefined\"!=typeof d.allowOrientationChange&&(mraidview.orientationProperties.allowOrientationChange=d.allowOrientationChange),\"undefined\"!=typeof d.forceOrientation&&(mraidview.orientationProperties.forceOrientation=d.forceOrientation),\"undefined\"!=typeof d.direction&&(mraidview.orientationProperties.direction=d.direction)):d=null,sdkController.setOrientationProperties(\"window.imraidview\",mraidview.stringify(mraidview.orientationProperties))}catch(b){a.showAlert(\"setOrientationProperties: \"+\nb+\", props = \"+d)}};a.getOrientationProperties=function(){return mraidview.orientationProperties};a.getWindowOrientation=function(){var a=window.orientation;0>a&&(a+=360);window.innerWidth!==this.previousWidth&&0==a&&window.innerWidth>window.innerHeight&&(a=90);return a};var e=function(){window.setTimeout(function(){if(c||a.getWindowOrientation()!==b)c=!1,b=a.getWindowOrientation(),sdkController.onOrientationChange(\"window.imraidview\"),imraid.broadcastEvent(\"orientationChange\",b)},200)};a.registerOrientationListener=\nfunction(){b=a.getWindowOrientation();window.addEventListener(\"resize\",e,!1);window.addEventListener(\"orientationchange\",e,!1)};a.unRegisterOrientationListener=function(){window.removeEventListener(\"resize\",e,!1);window.removeEventListener(\"orientationchange\",e,!1)};window.imraidview.registerOrientationListener();a.firePostStatusEvent=function(a){window.imraid.broadcastEvent(\"postStatus\",a)};a.fireMediaTrackingEvent=function(a,b){var c={};c.name=a;var e=\"inmobi_media_\"+a;\"undefined\"!=typeof b&&(null!=\nb&&\"\"!=b)&&(e=e+\"_\"+b);window.imraid.broadcastEvent(e,c)};a.fireMediaErrorEvent=function(a,b){var c={name:\"error\"};c.code=b;var e=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(e=e+\"_\"+a);window.imraid.broadcastEvent(e,c)};a.fireMediaTimeUpdateEvent=function(a,b,c){var e={name:\"timeupdate\",target:{}};e.target.currentTime=b;e.target.duration=c;b=\"inmobi_media_\"+e.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,e)};a.saveContent=function(a,\nb,c){window.imraid.addEventListener(\"saveContent_\"+a,c);sdkController.saveContent(\"window.imraidview\",a,b)};a.cancelSaveContent=function(a){sdkController.cancelSaveContent(\"window.imraidview\",a)};a.disableCloseRegion=function(a){sdkController.disableCloseRegion(\"window.imraidview\",a)};a.fireGalleryImageSelectedEvent=function(a,b,c){var e=new Image;e.src=\"data:image/jpeg;base64,\"+a;e.width=b;e.height=c;window.imraid.broadcastEvent(\"galleryImageSelected\",e)};a.fireCameraPictureCatpturedEvent=function(a,\nb,c){var e=new Image;e.src=\"data:image/jpeg;base64,\"+a;e.width=b;e.height=c;window.imraid.broadcastEvent(\"cameraPictureCaptured\",e)};a.fireMediaCloseEvent=function(a,b,c){var e={name:\"close\"};e.viaUserInteraction=b;e.target={};e.target.currentTime=c;b=\"inmobi_media_\"+e.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,e)};a.fireMediaVolumeChangeEvent=function(a,b,c){var e={name:\"volumechange\",target:{}};e.target.volume=b;e.target.muted=c;b=\"inmobi_media_\"+e.name;\n\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,e)};a.fireDeviceMuteChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceMuted\",a)};a.fireDeviceVolumeChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceVolumeChange\",a)};a.fireHeadphonePluggedEvent=function(a){window.imraid.broadcastEvent(\"headphones\",a)};a.showAlert=function(a){sdkController.showAlert(\"window.imraidview\",a)};a.openExternal=function(b,c){try{600<=getSdkVersionInt()?sdkController.openExternal(\"window.imraidview\",\nb,c):sdkController.openExternal(\"window.imraidview\",b)}catch(e){a.showAlert(\"openExternal: \"+e)}};a.log=function(b){try{sdkController.log(\"window.imraidview\",b)}catch(c){a.showAlert(\"log: \"+c)}};a.getPlatform=function(){return\"android\"};a.asyncPing=function(b){try{sdkController.asyncPing(\"window.imraidview\",b)}catch(c){a.showAlert(\"asyncPing: \"+c)}};a.startListeningDeviceMuteEvents=function(){sdkController.registerDeviceMuteEventListener(\"window.imraidview\")};a.stopListeningDeviceMuteEvents=function(){sdkController.unregisterDeviceMuteEventListener(\"window.imraidview\")};\na.startListeningDeviceVolumeChange=function(){sdkController.registerDeviceVolumeChangeEventListener(\"window.imraidview\")};a.stopListeningDeviceVolumeChange=function(){sdkController.unregisterDeviceVolumeChangeEventListener(\"window.imraidview\")};a.startListeningHeadphonePluggedEvents=function(){sdkController.registerHeadphonePluggedEventListener(\"window.imraidview\")};a.stopListeningHeadphonePluggedEvents=function(){sdkController.unregisterHeadphonePluggedEventListener(\"window.imraidview\")};getSdkVersionInt=\nfunction(){for(var b=a.getSdkVersion().split(\".\"),c=b.length,e=\"\",f=0;f<c;f++)e+=b[f];return parseInt(e)};a.getSdkVersion=function(){return window._im_imaiview.getSdkVersion()};a.supports=function(a){console.log(\"bridge: supports (IMRAID)\");if(\"string\"!=typeof a)window.imraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.imraidview\",a)};a.postToSocial=function(a,b,c,e){a=parseInt(a);isNaN(a)?window.imraid.broadcastEvent(\"error\",\n\"socialType must be an integer\",\"postToSocial\"):(\"string\"!=typeof b&&(b=\"\"),\"string\"!=typeof c&&(c=\"\"),\"string\"!=typeof e&&(e=\"\"),sdkController.postToSocial(\"window.imraidview\",a,b,c,e))};a.incentCompleted=function(a){if(\"object\"!=typeof a||null==a)sdkController.incentCompleted(\"window.imraidview\",null);else try{sdkController.incentCompleted(\"window.imraidview\",JSON.stringify(a))}catch(b){sdkController.incentCompleted(\"window.imraidview\",null)}};a.getOrientation=function(){try{return String(sdkController.getOrientation(\"window.imraidview\"))}catch(b){a.showAlert(\"getOrientation: \"+\nb)}};a.acceptAction=function(b){try{sdkController.acceptAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"acceptAction: \"+c+\", params = \"+b)}};a.rejectAction=function(b){try{sdkController.rejectAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"rejectAction: \"+c+\", params = \"+b)}};a.updateToPassbook=function(b){window.imraid.broadcastEvent(\"error\",\"Method not supported\",\"updateToPassbook\");a.log(\"Method not supported\")};a.isDeviceMuted=function(){return\"false\"!=\nsdkController.isDeviceMuted(\"window.imraidview\")};a.getDeviceVolume=function(){return 603>=getSdkVersionInt()?-1:sdkController.getDeviceVolume(\"window.imraidview\")};a.isHeadPhonesPlugged=function(){return\"false\"!=sdkController.isHeadphonePlugged(\"window.imraidview\")};a.sendSaveContentResult=function(){window.imraid.sendSaveContentResult.apply(window.imraid,arguments)};a.broadcastEvent=function(){window.imraid.broadcastEvent.apply(window.imraid,arguments)};a.disableBackButton=function(a){void 0==a||\n\"boolean\"!=typeof a?console.log(\"disableBackButton called with invalid params\"):sdkController.disableBackButton(\"window.imraidview\",a)};a.isBackButtonDisabled=function(){return sdkController.isBackButtonDisabled(\"window.imraidview\")};a.startListeningForBackButtonPressedEvent=function(){sdkController.registerBackButtonPressedEventListener(\"window.imraidview\")};a.stopListeningForBackButtonPressedEvent=function(){sdkController.unregisterBackButtonPressedEventListener(\"window.imraidview\")};a.hideStatusBar=\nfunction(){};a.setOpaqueBackground=function(){};a.startDownloader=function(a,b,c){682<=getSdkVersionInt()&&sdkController.startDownloader(\"window.imraidview\",a,b,c)};a.registerDownloaderCallbacks=function(){682<=getSdkVersionInt()&&sdkController.registerDownloaderCallbacks(\"window.imraidview\")};a.unregisterDownloaderCallbacks=function(){682<=getSdkVersionInt()&&sdkController.unregisterDownloaderCallbacks(\"window.imraidview\")};a.getDownloadProgress=function(){return 682<=getSdkVersionInt()?sdkController.getDownloadProgress(\"window.imraidview\"):\n-1};a.getDownloadStatus=function(){return 682<=getSdkVersionInt()?sdkController.getDownloadStatus(\"window.imraidview\"):-1};a.fireEvent=function(a){700<=getSdkVersionInt()&&(\"fireSkip\"===a?sdkController.fireSkip(\"window.imraidview\"):\"fireComplete\"===a?sdkController.fireComplete(\"window.imraidview\"):\"showEndCard\"===a&&sdkController.showEndCard(\"window.imraidview\"))};a.saveBlob=function(a){700<=getSdkVersionInt()&&sdkController.saveBlob(\"window.imraidview\",a)};a.getBlob=function(a,b){700<=getSdkVersionInt()&&\nsdkController.getBlob(a,b)};a.setCloseEndCardTracker=function(a){700<=getSdkVersionInt()&&sdkController.setCloseEndCardTracker(\"window.imraidview\",a)}})();\n(function(){var a=window.imraid=new InmobiObj,b=window.imraidview;a.getOrientation=b.getOrientation;a.setOrientationProperties=b.setOrientationProperties;a.getOrientationProperties=b.getOrientationProperties;a.saveContentIDMap={};a.saveContent=function(c,e,d){var k=arguments.length,h,f=null;if(3>k){if(\"function\"===typeof arguments[k-1])h=arguments[k-1];else return;f={reason:1}}else a.saveContentIDMap[c]&&(h=arguments[2],f={reason:11,url:arguments[1]});\"function\"!==!h&&(f?(window.imraid.addEventListener(\"saveContent_failed_\"+\nc,h),window.imraid.sendSaveContentResult(\"saveContent_failed_\"+c,\"failed\",JSON.stringify(f))):(a.removeEventListener(\"saveContent_\"+c),a.saveContentIDMap[c]=!0,b.saveContent(c,e,d)))};a.cancelSaveContent=function(a){b.cancelSaveContent(a)};a.asyncPing=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"asyncPing\"):b.asyncPing(c)};a.disableCloseRegion=b.disableCloseRegion;a.getSdkVersion=b.getSdkVersion;a.log=function(c){\"undefined\"==typeof c?a.broadcastEvent(\"error\",\"message is required.\",\n\"log\"):\"string\"==typeof c?b.log(c):b.log(JSON.stringify(c))};a.getInMobiAIVersion=function(){return\"2.0\"};a.getVendorName=function(){return\"inmobi\"};a.openExternal=function(a,e){mraidview.detectAndBlockFraud(\"imraid.openExternal\")||b.openExternal(a,e)};a.updateToPassbook=function(c){mraidview.detectAndBlockFraud(\"imraid.updateToPassbook\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"updateToPassbook\"):b.updateToPassbook(c))};a.postToSocial=function(a,e,d,k){mraidview.detectAndBlockFraud(\"imraid.postToSocial\")||\nb.postToSocial(a,e,d,k)};a.getPlatform=b.getPlatform;a.incentCompleted=b.incentCompleted;a.loadSKStore=b.loadSKStore;a.showSKStore=function(a){mraidview.detectAndBlockFraud(\"imraid.showSKStore\")||b.showSKStore(a)};a.supports=function(a){return b.supports(a)};a.isDeviceMuted=function(){return!imIsObjValid(a.listeners.deviceMuted)?-1:b.isDeviceMuted()};a.isHeadPhonesPlugged=function(){return!imIsObjValid(a.listeners.headphones)?!1:b.isHeadPhonesPlugged()};a.getDeviceVolume=function(){return b.getDeviceVolume()};\na.setDeviceVolume=function(a){b.setDeviceVolume(a)};a.hideStatusBar=function(){b.hideStatusBar()};a.setOpaqueBackground=function(){b.setOpaqueBackground()};a.disableBackButton=b.disableBackButton;a.isBackButtonDisabled=b.isBackButtonDisabled;a.startDownloader=b.startDownloader;a.getDownloadProgress=b.getDownloadProgress;a.getDownloadStatus=b.getDownloadStatus;a.fireEvent=b.fireEvent;a.saveBlob=b.saveBlob;a.getBlob=b.getBlob;a.setCloseEndCardTracker=b.setCloseEndCardTracker})();\n(function(){var a=window._im_imaiview={ios:{}};window.imaiview=a;a.broadcastEvent=function(){for(var a=Array(arguments.length),c=0;c<arguments.length;c++)a[c]=arguments[c];c=a.shift();try{window.mraid.broadcastEvent(c,a)}catch(e){}};a.getPlatform=function(){return\"android\"};a.getPlatformVersion=function(){return sdkController.getPlatformVersion(\"window.imaiview\")};a.log=function(a){sdkController.log(\"window.imaiview\",a)};a.openEmbedded=function(a){sdkController.openEmbedded(\"window.imaiview\",a)};\na.openExternal=function(a,c){600<=getSdkVersionInt()?sdkController.openExternal(\"window.imaiview\",a,c):sdkController.openExternal(\"window.imaiview\",a)};a.ping=function(a,c){sdkController.ping(\"window.imaiview\",a,c)};a.pingInWebView=function(a,c){sdkController.pingInWebView(\"window.imaiview\",a,c)};a.getSdkVersion=function(){try{var a=sdkController.getSdkVersion(\"window.imaiview\");if(\"string\"==typeof a&&null!=a)return a}catch(c){return\"3.7.0\"}};a.onUserInteraction=function(a){if(\"object\"!=typeof a||\nnull==a)sdkController.onUserInteraction(\"window.imaiview\",null);else try{sdkController.onUserInteraction(\"window.imaiview\",JSON.stringify(a))}catch(c){sdkController.onUserInteraction(\"window.imaiview\",null)}};a.fireAdReady=function(){sdkController.fireAdReady(\"window.imaiview\")};a.fireAdFailed=function(){sdkController.fireAdFailed(\"window.imaiview\")};a.broadcastEvent=function(){window.imai.broadcastEvent.apply(window.imai,arguments)}})();\n(function(){var a=window._im_imaiview;window._im_imai=new InmobiObj;window._im_imai.ios=new InmobiObj;var b=window._im_imai;window.imai=window._im_imai;b.matchString=function(a,b){if(\"string\"!=typeof a||null==a||null==b)return-1;var d=-1;try{d=a.indexOf(b)}catch(k){}return d};b.isHttpUrl=function(a){return\"string\"!=typeof a||null==a?!1:0==b.matchString(a,\"http://\")?!0:0==b.matchString(a,\"https://\")?!0:!1};b.appendTapParams=function(a,e,d){if(!imIsObjValid(e)||!imIsObjValid(d))return a;b.isHttpUrl(a)&&\n(a=-1==b.matchString(a,\"?\")?a+(\"?u-tap-o=\"+e+\",\"+d):a+(\"&u-tap-o=\"+e+\",\"+d));return a};b.performAdClick=function(a,e){e=e||event;if(imIsObjValid(a)){var d=a.clickConfig,k=a.landingConfig;if(!imIsObjValid(d)&&!imIsObjValid(k))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,f=null,g=null,m=null,n=null,l=null,q=null,p=null;if(imIsObjValid(e))try{m=e.changedTouches[0].pageX,n=e.changedTouches[0].pageY}catch(r){n=\nm=0}imIsObjValid(k)?imIsObjValid(d)?(l=k.url,q=k.fallbackUrl,p=k.urlType,h=d.url,f=d.pingWV,g=d.fr):(l=k.url,p=k.urlType):(l=d.url,p=d.urlType);d=b.getPlatform();try{if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"boolean\"!=typeof f&&\"number\"!=typeof f||null==f)f=!0;if(0>f||1<f)f=!0;if(\"number\"!=typeof p||null==p)p=0;h=b.appendTapParams(h,m,n);imIsObjValid(h)?!0==f?b.pingInWebView(h,g):b.ping(h,g):b.log(\"clickurl provided is null.\");if(imIsObjValid(l))switch(imIsObjValid(h)||\n(l=b.appendTapParams(l,m,n)),p){case 1:b.openEmbedded(l);break;case 2:\"ios\"==d?b.ios.openItunesProductView(l):this.broadcastEvent(\"error\",\"Cannot process openItunesProductView for os\"+d);break;default:b.openExternal(l,q)}else b.log(\"Landing url provided is null.\")}catch(s){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.performActionClick=function(a,e){e=e||event;if(imIsObjValid(a)){var d=a.clickConfig,k=a.landingConfig;\nif(!imIsObjValid(d)&&!imIsObjValid(k))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,f=null,g=null,m=null,n=null;if(imIsObjValid(e))try{m=e.changedTouches[0].pageX,n=e.changedTouches[0].pageY}catch(l){n=m=0}imIsObjValid(d)&&(h=d.url,f=d.pingWV,g=d.fr);try{if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"boolean\"!=typeof f&&\"number\"!=typeof f||null==f)f=\n!0;if(0>f||1<f)f=!0;h=b.appendTapParams(h,m,n);imIsObjValid(h)?!0==f?b.pingInWebView(h,g):b.ping(h,g):b.log(\"clickurl provided is null.\");b.onUserInteraction(k)}catch(q){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.getVersion=function(){return\"1.0\"};b.getPlatform=a.getPlatform;b.getPlatformVersion=a.getPlatformVersion;b.log=a.log;b.openEmbedded=function(b){mraidview.detectAndBlockFraud(\"imai.openEmbedded\")||a.openEmbedded(b)};\nb.openExternal=function(b,e){mraidview.detectAndBlockFraud(\"imai.openExternal\")||a.openExternal(b,e)};b.ping=a.ping;b.pingInWebView=a.pingInWebView;b.onUserInteraction=a.onUserInteraction;b.getSdkVersion=a.getSdkVersion;b.loadSKStore=a.loadSKStore;b.showSKStore=function(b){mraidview.detectAndBlockFraud(\"imai.showSKStore\")||a.showSKStore(b)};b.ios.openItunesProductView=function(b){mraidview.detectAndBlockFraud(\"imai.ios.openItunesProductView\")||a.ios.openItunesProductView(b)};b.fireAdReady=a.fireAdReady;\nb.fireAdFailed=a.fireAdFailed})();" : c2;
    }

    public final void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        String str12 = str9;
        i iVar = this.i;
        Context containerContext = getContainerContext();
        int a2 = com.inmobi.rendering.mraid.a.a(containerContext);
        GregorianCalendar b2 = com.inmobi.rendering.mraid.a.b(str3);
        if (b2 != null) {
            StringBuilder sb = new StringBuilder("Event start: ");
            sb.append(b2.get(1));
            sb.append("-");
            sb.append(b2.get(2));
            sb.append("-");
            sb.append(b2.get(5));
            GregorianCalendar b3 = com.inmobi.rendering.mraid.a.b(str4);
            if (b3 != null) {
                StringBuilder sb2 = new StringBuilder("Event end: ");
                sb2.append(b3.get(1));
                sb2.append("-");
                sb2.append(b3.get(2));
                sb2.append("-");
                sb2.append(b3.get(5));
                String str13 = str2;
                Intent putExtra = new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("calendar_id", str2).putExtra("beginTime", b2.getTimeInMillis()).putExtra(AbstractEvent.END_TIME, b3.getTimeInMillis()).putExtra("allDay", false).putExtra("title", str6).putExtra("eventLocation", str5).putExtra("description", str7);
                if (str12.equals("transparent")) {
                    putExtra.putExtra("availability", 1);
                } else if (str12.equals("opaque")) {
                    putExtra.putExtra("availability", 0);
                }
                String a3 = i.a(str10);
                if (a3.length() != 0) {
                    putExtra.putExtra("rrule", a3);
                }
                AnonymousClass1 r2 = new com.inmobi.rendering.InMobiAdActivity.a(containerContext, a2, str8, str11, str3, str) {
                    final /* synthetic */ Context a;
                    final /* synthetic */ int b;
                    final /* synthetic */ String c;
                    final /* synthetic */ String d;
                    final /* synthetic */ String e;
                    final /* synthetic */ String f;

                    {
                        this.a = r2;
                        this.b = r3;
                        this.c = r4;
                        this.d = r5;
                        this.e = r6;
                        this.f = r7;
                    }

                    public final void a() {
                        int i;
                        i.b;
                        if (this.b == a.a(this.a)) {
                            i.b;
                            return;
                        }
                        ContentValues contentValues = new ContentValues();
                        String str = this.c;
                        char c2 = 65535;
                        int hashCode = str.hashCode();
                        if (hashCode != -1320822226) {
                            if (hashCode != -804109473) {
                                if (hashCode == 476588369 && str.equals(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED)) {
                                    c2 = 2;
                                }
                            } else if (str.equals("confirmed")) {
                                c2 = 1;
                            }
                        } else if (str.equals("tentative")) {
                            c2 = 0;
                        }
                        switch (c2) {
                            case 0:
                                contentValues.put("eventStatus", Integer.valueOf(0));
                                break;
                            case 1:
                                contentValues.put("eventStatus", Integer.valueOf(1));
                                break;
                            case 2:
                                contentValues.put("eventStatus", Integer.valueOf(2));
                                break;
                        }
                        ContentResolver contentResolver = this.a.getContentResolver();
                        contentResolver.update(ContentUris.withAppendedId(Events.CONTENT_URI, (long) a.a(this.a)), contentValues, null, null);
                        String str2 = this.d;
                        if (str2 != null && !"".equals(str2)) {
                            try {
                                if (this.d.startsWith("+")) {
                                    i = Integer.parseInt(this.d.substring(1)) / 60000;
                                } else {
                                    i = Integer.parseInt(this.d) / 60000;
                                }
                            } catch (NumberFormatException unused) {
                                GregorianCalendar b2 = a.b(this.d);
                                if (b2 == null) {
                                    i.b;
                                    new StringBuilder("Invalid reminder date provided. date string: ").append(this.d);
                                    return;
                                }
                                i = ((int) (b2.getTimeInMillis() - a.b(this.e).getTimeInMillis())) / 60000;
                                if (i > 0) {
                                    i.this.a.b(this.f, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                                    return;
                                }
                            }
                            int i2 = -i;
                            StringBuilder sb = new StringBuilder();
                            sb.append(a.a(this.a));
                            contentResolver.delete(Reminders.CONTENT_URI, "event_id=?", new String[]{sb.toString()});
                            if (i2 < 0) {
                                i.this.a.b(this.f, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                                return;
                            }
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put(Extras.EVENT_ID, Integer.valueOf(a.a(this.a)));
                            contentValues2.put(Param.METHOD, Integer.valueOf(1));
                            contentValues2.put("minutes", Integer.valueOf(i2));
                            contentResolver.insert(Reminders.CONTENT_URI, contentValues2);
                        }
                    }
                };
                int a4 = InMobiAdActivity.a(putExtra, (com.inmobi.rendering.InMobiAdActivity.a) r2);
                Intent intent = new Intent(containerContext, InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 103);
                intent.putExtra("id", a4);
                com.inmobi.commons.a.a.a(containerContext, intent);
            }
        }
    }

    public final void a() {
        this.P = false;
        try {
            getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException unused) {
        } catch (InvocationTargetException unused2) {
        }
    }

    @android.annotation.TargetApi(16)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean e(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = 1
            r2 = 0
            switch(r0) {
                case -1886160473: goto L_0x0046;
                case -1647691422: goto L_0x003c;
                case -178324674: goto L_0x0032;
                case 1509574865: goto L_0x0028;
                case 1642189884: goto L_0x001e;
                case 1772979069: goto L_0x0014;
                case 1921345160: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0050
        L_0x000a:
            java.lang.String r0 = "postToSocial"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0050
            r6 = 1
            goto L_0x0051
        L_0x0014:
            java.lang.String r0 = "redirectFraudDetection"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0050
            r6 = 0
            goto L_0x0051
        L_0x001e:
            java.lang.String r0 = "saveContent"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0050
            r6 = 3
            goto L_0x0051
        L_0x0028:
            java.lang.String r0 = "html5video"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0050
            r6 = 5
            goto L_0x0051
        L_0x0032:
            java.lang.String r0 = "calendar"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0050
            r6 = 6
            goto L_0x0051
        L_0x003c:
            java.lang.String r0 = "inlineVideo"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0050
            r6 = 4
            goto L_0x0051
        L_0x0046:
            java.lang.String r0 = "playVideo"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0050
            r6 = 2
            goto L_0x0051
        L_0x0050:
            r6 = -1
        L_0x0051:
            switch(r6) {
                case 0: goto L_0x0096;
                case 1: goto L_0x0095;
                case 2: goto L_0x0095;
                case 3: goto L_0x0095;
                case 4: goto L_0x008b;
                case 5: goto L_0x008b;
                case 6: goto L_0x0055;
                default: goto L_0x0054;
            }
        L_0x0054:
            return r2
        L_0x0055:
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r0 = "android.intent.action.VIEW"
            r6.<init>(r0)
            java.lang.String r0 = "vnd.android.cursor.item/event"
            r6.setType(r0)
            android.content.Context r0 = r5.getContainerContext()
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            r3 = 65536(0x10000, float:9.18355E-41)
            android.content.pm.ResolveInfo r6 = r0.resolveActivity(r6, r3)
            android.content.Context r0 = com.inmobi.commons.a.a.b()
            java.lang.String r3 = "android.permission.WRITE_CALENDAR"
            boolean r0 = com.inmobi.commons.a.a.b(r0, r3)
            android.content.Context r3 = com.inmobi.commons.a.a.b()
            java.lang.String r4 = "android.permission.READ_CALENDAR"
            boolean r3 = com.inmobi.commons.a.a.b(r3, r4)
            if (r6 == 0) goto L_0x008a
            if (r0 == 0) goto L_0x008a
            if (r3 == 0) goto L_0x008a
            return r1
        L_0x008a:
            return r2
        L_0x008b:
            boolean r6 = r5.F
            if (r6 == 0) goto L_0x0094
            boolean r6 = r5.P
            if (r6 == 0) goto L_0x0094
            return r1
        L_0x0094:
            return r2
        L_0x0095:
            return r1
        L_0x0096:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.RenderView.e(java.lang.String):boolean");
    }

    public final boolean e() {
        c.i renderingConfig = getRenderingConfig();
        if (renderingConfig == null) {
            return false;
        }
        if (!renderingConfig.g || this.ac || this.ad) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final int getDownloadProgress() {
        getReferenceContainer().getApkDownloader();
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final int getDownloadStatus() {
        getReferenceContainer().getApkDownloader();
        return -2;
    }

    public final void a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("(");
        sb.append(str3);
        sb.append(");");
        a(str, sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public final void setCloseEndCardTracker(String str) {
        AdContainer referenceContainer = getReferenceContainer();
        if (referenceContainer instanceof bd) {
            NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) ((bd) referenceContainer).getVideoContainerView();
            if (nativeVideoWrapper != null) {
                be beVar = (be) nativeVideoWrapper.getVideoView().getTag();
                if (beVar != null && beVar.b() != null && beVar.b().f() != null) {
                    beVar.b().f().a(new NativeTracker(str, 0, TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE, null));
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void c(String str) {
        StringBuilder sb = new StringBuilder("window.mraidview.fireRedirectFraudBeacon('");
        sb.append(str);
        sb.append("')");
        d(sb.toString());
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("plId", Long.valueOf(this.U));
            hashMap.put("creativeId", this.V);
            hashMap.put("impId", this.W);
            hashMap.put("trigger", str);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "BlockAutoRedirection", hashMap);
        } catch (Exception e2) {
            StringBuilder sb2 = new StringBuilder("Error in submitting telemetey event : (");
            sb2.append(e2.getMessage());
            sb2.append(")");
        }
    }

    public final void setExpandProperties(b bVar) {
        if (bVar.b) {
            setUseCustomClose(bVar.a);
        }
        this.G = bVar;
    }

    static /* synthetic */ void k(RenderView renderView) {
        renderView.d("window.imaiview.broadcastEvent('ready');");
        renderView.d("window.mraidview.broadcastEvent('ready');");
    }

    static /* synthetic */ boolean a(RenderView renderView, JsResult jsResult) {
        c.i renderingConfig = renderView.getRenderingConfig();
        if (renderingConfig != null && renderingConfig.l) {
            return true;
        }
        jsResult.cancel();
        renderView.d("window.mraidview.popupBlocked('popupBlocked')");
        return false;
    }
}
