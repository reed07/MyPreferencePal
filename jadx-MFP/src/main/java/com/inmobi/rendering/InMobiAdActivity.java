package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.ads.AudienceNetworkActivity;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.NativeVideoView;
import com.inmobi.ads.NativeVideoWrapper;
import com.inmobi.ads.ah;
import com.inmobi.ads.ao;
import com.inmobi.ads.bd;
import com.inmobi.ads.be;
import com.inmobi.ads.c;
import com.inmobi.ads.c.l;
import com.inmobi.ads.ca;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mopub.common.AdType;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"ClickableViewAccessibility"})
@TargetApi(15)
public class InMobiAdActivity extends Activity {
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, a> b = new HashMap();
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, Intent> c = new HashMap();
    public static Integer d = Integer.valueOf(0);
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, b> e = new HashMap();
    public static Integer f = Integer.valueOf(0);
    /* access modifiers changed from: private */
    public static final String g = "InMobiAdActivity";
    private static SparseArray<AdContainer> h = new SparseArray<>();
    private static RenderView i;
    private static com.inmobi.rendering.RenderView.a j;
    public boolean a = false;
    /* access modifiers changed from: private */
    public AdContainer k;
    /* access modifiers changed from: private */
    public RenderView l;
    private CustomView m;
    private CustomView n;
    /* access modifiers changed from: private */
    public NativeVideoView o;
    private int p;
    private int q;
    private boolean r = false;
    private boolean s = false;

    public interface a {
        void a();
    }

    public interface b {
        void a(int[] iArr);
    }

    public static int a(AdContainer adContainer) {
        int hashCode = adContainer.hashCode();
        h.put(hashCode, adContainer);
        return hashCode;
    }

    public static void a(@NonNull Object obj) {
        h.remove(obj.hashCode());
    }

    public static void a(RenderView renderView) {
        i = renderView;
    }

    public static void a(com.inmobi.rendering.RenderView.a aVar) {
        j = aVar;
    }

    public static int a(Intent intent, a aVar) {
        d = Integer.valueOf(d.intValue() + 1);
        b.put(d, aVar);
        c.put(d, intent);
        return d.intValue();
    }

    public static void a(String[] strArr, b bVar) {
        try {
            if (com.inmobi.commons.a.a.a()) {
                f = Integer.valueOf(f.intValue() + 1);
                e.put(f, bVar);
                int intValue = f.intValue();
                Intent intent = new Intent(com.inmobi.commons.a.a.b(), InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 104);
                intent.putExtra("id", intValue);
                intent.putExtra("permissions", strArr);
                com.inmobi.commons.a.a.a(com.inmobi.commons.a.a.b(), intent);
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error while requesting permissions; ").append(e2.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.a) {
            int i2 = this.p;
            if (100 == i2) {
                RenderView renderView = this.l;
                if (!(renderView == null || renderView.getFullScreenEventsListener() == null)) {
                    try {
                        if (!this.r) {
                            this.r = true;
                            this.l.getFullScreenEventsListener().a(this.l);
                        }
                    } catch (Exception unused) {
                    }
                }
            } else if (this.q == 200 && 102 == i2) {
                AdContainer adContainer = this.k;
                if (!(adContainer == null || adContainer.getFullScreenEventsListener() == null)) {
                    if (!this.r) {
                        this.r = true;
                        this.k.getFullScreenEventsListener().a(null);
                    }
                }
            } else if (201 == this.q) {
                if (this.k instanceof bd) {
                    NativeVideoView nativeVideoView = this.o;
                    if (nativeVideoView != null) {
                        final be beVar = (be) nativeVideoView.getTag();
                        if (beVar != null && this.s) {
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                public final void run() {
                                    if (InMobiAdActivity.this.k == null) {
                                        return;
                                    }
                                    if (InMobiAdActivity.this.k.getRenderingProperties().a != PlacementType.PLACEMENT_TYPE_FULLSCREEN || !((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                                        InMobiAdActivity.this.o.start();
                                    }
                                }
                            }, 50);
                        }
                        if (this.k.getFullScreenEventsListener() != null) {
                            try {
                                if (!this.r) {
                                    this.r = true;
                                    this.k.getFullScreenEventsListener().a(beVar);
                                }
                            } catch (Exception e2) {
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                            }
                        }
                    }
                }
                AdContainer adContainer2 = this.k;
                if (adContainer2 instanceof ah) {
                    try {
                        if (!this.r) {
                            this.r = true;
                            adContainer2.getFullScreenEventsListener().a(null);
                        }
                    } catch (Exception e3) {
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                    }
                }
            }
            this.s = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (!this.a && 102 == this.p) {
            AdContainer adContainer = this.k;
            if (adContainer != null) {
                ca viewableAd = adContainer.getViewableAd();
                int i2 = this.q;
                if (200 == i2) {
                    if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.k.getRenderingProperties().a) {
                        try {
                            viewableAd.a(this.m, this.n);
                        } catch (Exception e2) {
                            new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e2.getMessage());
                            if (this.k.getFullScreenEventsListener() != null) {
                                this.k.getFullScreenEventsListener().a();
                            }
                        }
                    }
                } else if (201 == i2) {
                    try {
                        c cVar = new c();
                        com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) cVar, (com.inmobi.commons.core.configs.b.c) null);
                        if (viewableAd.b() != null) {
                            if (this.k instanceof bd) {
                                be beVar = (be) this.o.getTag();
                                if (beVar != null) {
                                    l lVar = cVar.k;
                                    int i3 = lVar.g;
                                    if (beVar.G.containsKey(TimestampAnalyticsHelper.ATTR_TIME)) {
                                        i3 = ((Integer) beVar.G.get(TimestampAnalyticsHelper.ATTR_TIME)).intValue();
                                    }
                                    lVar.g = i3;
                                    viewableAd.a(new View[0]);
                                }
                            } else if (this.k instanceof ah) {
                                try {
                                    viewableAd.a(new View[0]);
                                } catch (Exception e3) {
                                    new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e3.getMessage());
                                    if (this.k.getFullScreenEventsListener() != null) {
                                        this.k.getFullScreenEventsListener().a();
                                    }
                                }
                            }
                        }
                    } catch (Exception e4) {
                        new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e4.getMessage());
                        if (this.k.getFullScreenEventsListener() != null) {
                            this.k.getFullScreenEventsListener().a();
                        }
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @TargetApi(23)
    public void onCreate(Bundle bundle) {
        c cVar;
        super.onCreate(bundle);
        if (!com.inmobi.commons.a.a.a()) {
            finish();
            Logger.a(InternalLogLevel.DEBUG, "InMobi", "Session not found, AdActivity will be closed");
            return;
        }
        this.r = false;
        this.p = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
        int i2 = this.p;
        if (i2 == 100) {
            String stringExtra = getIntent().getStringExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL");
            long longExtra = getIntent().getLongExtra(AudienceNetworkActivity.PLACEMENT_ID, Long.MIN_VALUE);
            boolean booleanExtra = getIntent().getBooleanExtra("allowAutoRedirection", false);
            String stringExtra2 = getIntent().getStringExtra("impressionId");
            String stringExtra3 = getIntent().getStringExtra("creativeId");
            this.l = new RenderView(this, new RenderingProperties(PlacementType.PLACEMENT_TYPE_FULLSCREEN), null, stringExtra2);
            this.l.setPlacementId(longExtra);
            this.l.setCreativeId(stringExtra3);
            this.l.setAllowAutoRedirection(booleanExtra);
            com.inmobi.rendering.RenderView.a aVar = RenderView.a;
            RenderView renderView = i;
            if (renderView != null) {
                aVar = renderView.getListener();
                cVar = i.getAdConfig();
            } else {
                cVar = new c();
                com.inmobi.rendering.RenderView.a aVar2 = j;
                if (aVar2 != null) {
                    aVar = aVar2;
                }
            }
            this.l.setIsInAppBrowser(true);
            this.l.a(aVar, cVar);
            RelativeLayout relativeLayout = new RelativeLayout(this);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(10);
            layoutParams.addRule(2, 65533);
            relativeLayout.setBackgroundColor(-1);
            relativeLayout.addView(this.l, layoutParams);
            float f2 = com.inmobi.commons.core.utilities.b.c.a().c;
            LinearLayout linearLayout = new LinearLayout(this);
            LayoutParams layoutParams2 = new LayoutParams(-1, (int) (48.0f * f2));
            linearLayout.setOrientation(0);
            linearLayout.setId(65533);
            linearLayout.setWeightSum(100.0f);
            linearLayout.setBackgroundResource(17301658);
            linearLayout.setBackgroundColor(-7829368);
            layoutParams2.addRule(12);
            relativeLayout.addView(linearLayout, layoutParams2);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
            layoutParams3.weight = 25.0f;
            CustomView customView = new CustomView(this, f2, 2);
            customView.setOnTouchListener(new OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setBackgroundColor(-7829368);
                        InMobiAdActivity.this.a = true;
                        InMobiAdActivity.this.finish();
                        return true;
                    } else if (motionEvent.getAction() != 0) {
                        return true;
                    } else {
                        view.setBackgroundColor(-16711681);
                        return true;
                    }
                }
            });
            linearLayout.addView(customView, layoutParams3);
            CustomView customView2 = new CustomView(this, f2, 3);
            customView2.setOnTouchListener(new OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setBackgroundColor(-7829368);
                        InMobiAdActivity.this.l.reload();
                        return true;
                    } else if (motionEvent.getAction() != 0) {
                        return true;
                    } else {
                        view.setBackgroundColor(-16711681);
                        return true;
                    }
                }
            });
            linearLayout.addView(customView2, layoutParams3);
            CustomView customView3 = new CustomView(this, f2, 4);
            customView3.setOnTouchListener(new OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setBackgroundColor(-7829368);
                        if (InMobiAdActivity.this.l.canGoBack()) {
                            InMobiAdActivity.this.l.goBack();
                        } else {
                            InMobiAdActivity.this.a = true;
                            InMobiAdActivity.this.finish();
                        }
                        return true;
                    } else if (motionEvent.getAction() != 0) {
                        return true;
                    } else {
                        view.setBackgroundColor(-16711681);
                        return true;
                    }
                }
            });
            linearLayout.addView(customView3, layoutParams3);
            CustomView customView4 = new CustomView(this, f2, 6);
            customView4.setOnTouchListener(new OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setBackgroundColor(-7829368);
                        if (InMobiAdActivity.this.l.canGoForward()) {
                            InMobiAdActivity.this.l.goForward();
                        }
                        return true;
                    } else if (motionEvent.getAction() != 0) {
                        return true;
                    } else {
                        view.setBackgroundColor(-16711681);
                        return true;
                    }
                }
            });
            linearLayout.addView(customView4, layoutParams3);
            setContentView(relativeLayout);
            this.l.loadUrl(stringExtra);
            this.l.setFullScreenActivityContext(this);
            return;
        }
        if (i2 == 102) {
            if (getIntent().hasExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX")) {
                this.k = (AdContainer) h.get(getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", -1));
                if (this.k == null) {
                    finish();
                    return;
                }
                this.q = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 0);
                if (this.q == 0) {
                    if (this.k.getFullScreenEventsListener() != null) {
                        this.k.getFullScreenEventsListener().a();
                    }
                    finish();
                    return;
                }
                if (getIntent().getBooleanExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", false)) {
                    requestWindowFeature(1);
                    getWindow().setFlags(1024, 1024);
                }
                if ((200 != this.q || AdType.HTML.equals(this.k.getMarkupType())) && (201 != this.q || "inmobiJson".equals(this.k.getMarkupType()))) {
                    try {
                        this.k.setFullScreenActivityContext(this);
                        FrameLayout frameLayout = (FrameLayout) findViewById(16908290);
                        RelativeLayout relativeLayout2 = new RelativeLayout(getApplicationContext());
                        relativeLayout2.setId(65534);
                        float f3 = com.inmobi.commons.core.utilities.b.c.a().c;
                        if (AdType.HTML.equals(this.k.getMarkupType())) {
                            relativeLayout2.setBackgroundColor(0);
                            LayoutParams layoutParams4 = new LayoutParams(-1, -1);
                            layoutParams4.addRule(10);
                            int i3 = (int) (50.0f * f3);
                            LayoutParams layoutParams5 = new LayoutParams(i3, i3);
                            layoutParams5.addRule(11);
                            this.m = new CustomView(this, f3, 0);
                            this.m.setId(65532);
                            this.m.setOnClickListener(new OnClickListener() {
                                public final void onClick(View view) {
                                    InMobiAdActivity.this.a = true;
                                    try {
                                        InMobiAdActivity.this.k.b();
                                    } catch (Exception e) {
                                        InMobiAdActivity.g;
                                        new StringBuilder("Encountered unexpected error in processing close request: ").append(e.getMessage());
                                        Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                                    }
                                }
                            });
                            this.n = new CustomView(this, f3, 1);
                            this.n.setId(65531);
                            this.n.setOnClickListener(new OnClickListener() {
                                public final void onClick(View view) {
                                    InMobiAdActivity.this.a = true;
                                    try {
                                        InMobiAdActivity.this.k.b();
                                    } catch (Exception e) {
                                        InMobiAdActivity.g;
                                        new StringBuilder("Encountered unexpected error in processing close request: ").append(e.getMessage());
                                        Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                                    }
                                }
                            });
                            View a2 = this.k.getViewableAd().a();
                            if (a2 != null) {
                                ViewGroup viewGroup = (ViewGroup) a2.getParent();
                                if (viewGroup != null) {
                                    viewGroup.removeView(a2);
                                }
                                relativeLayout2.addView(a2, layoutParams4);
                                relativeLayout2.addView(this.m, layoutParams5);
                                relativeLayout2.addView(this.n, layoutParams5);
                                ((RenderView) this.k).a(((RenderView) this.k).p);
                                ((RenderView) this.k).b(((RenderView) this.k).m);
                            }
                        } else if ("inmobiJson".equals(this.k.getMarkupType())) {
                            PlacementType placementType = this.k.getRenderingProperties().a;
                            relativeLayout2.setBackgroundColor(-16777216);
                            ao aoVar = (ao) this.k.getDataModel();
                            Point point = aoVar.d.c.a;
                            com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) new c(), (com.inmobi.commons.core.configs.b.c) null);
                            ca viewableAd = this.k.getViewableAd();
                            View b2 = aoVar.c ? viewableAd.b() : null;
                            if (b2 == null) {
                                b2 = viewableAd.a(null, relativeLayout2, false);
                            }
                            if (this.k instanceof bd) {
                                NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) this.k.getVideoContainerView();
                                if (nativeVideoWrapper != null) {
                                    this.o = nativeVideoWrapper.getVideoView();
                                    this.o.requestFocus();
                                    be beVar = (be) this.o.getTag();
                                    if (beVar.y != null) {
                                        beVar.a((be) beVar.y);
                                    }
                                    if (PlacementType.PLACEMENT_TYPE_INLINE == placementType) {
                                        beVar.v.put("placementType", PlacementType.PLACEMENT_TYPE_INLINE);
                                    } else {
                                        beVar.v.put("placementType", PlacementType.PLACEMENT_TYPE_FULLSCREEN);
                                    }
                                }
                            }
                            if (b2 != null) {
                                relativeLayout2.addView(b2, new LayoutParams(point.x, point.y));
                            }
                            this.k.setRequestedScreenOrientation();
                        } else {
                            if (this.k.getFullScreenEventsListener() != null) {
                                this.k.getFullScreenEventsListener().a();
                            }
                            finish();
                            return;
                        }
                        frameLayout.addView(relativeLayout2, new LayoutParams(-1, -1));
                    } catch (Exception e2) {
                        this.k.setFullScreenActivityContext(null);
                        if (this.k.getFullScreenEventsListener() != null) {
                            this.k.getFullScreenEventsListener().a();
                        }
                        finish();
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                    }
                } else {
                    if (this.k.getFullScreenEventsListener() != null) {
                        this.k.getFullScreenEventsListener().a();
                    }
                    finish();
                }
            }
        } else if (i2 == 103) {
            int intExtra = getIntent().getIntExtra("id", -1);
            if (intExtra != -1) {
                startActivityForResult((Intent) c.get(Integer.valueOf(intExtra)), intExtra);
            }
        } else if (i2 == 104) {
            int intExtra2 = getIntent().getIntExtra("id", -1);
            if (intExtra2 != -1) {
                String[] stringArrayExtra = getIntent().getStringArrayExtra("permissions");
                if (stringArrayExtra != null && stringArrayExtra.length > 0 && VERSION.SDK_INT >= 23) {
                    com.inmobi.commons.core.utilities.a.b();
                    requestPermissions(stringArrayExtra, intExtra2);
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        if (!this.a) {
            this.s = true;
            NativeVideoView nativeVideoView = this.o;
            if (nativeVideoView != null) {
                nativeVideoView.pause();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.a) {
            int i2 = this.p;
            if (100 == i2) {
                RenderView renderView = this.l;
                if (!(renderView == null || renderView.getFullScreenEventsListener() == null)) {
                    try {
                        this.l.getFullScreenEventsListener().b(this.l);
                        this.l.destroy();
                        this.l = null;
                    } catch (Exception unused) {
                    }
                }
            } else if (102 == i2) {
                AdContainer adContainer = this.k;
                if (!(adContainer == null || adContainer.getFullScreenEventsListener() == null)) {
                    int i3 = this.q;
                    if (200 == i3) {
                        try {
                            this.k.getFullScreenEventsListener().b(null);
                        } catch (Exception e2) {
                            new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e2.getMessage());
                            Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                        }
                    } else if (201 == i3 && VERSION.SDK_INT >= 15) {
                        AdContainer adContainer2 = this.k;
                        if (adContainer2 instanceof bd) {
                            NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) ((bd) adContainer2).getVideoContainerView();
                            if (nativeVideoWrapper != null) {
                                try {
                                    this.k.getFullScreenEventsListener().b((be) nativeVideoWrapper.getVideoView().getTag());
                                } catch (Exception e3) {
                                    new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e3.getMessage());
                                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                                }
                            }
                        } else if (adContainer2 instanceof ah) {
                            try {
                                adContainer2.getFullScreenEventsListener().b(null);
                            } catch (Exception e4) {
                                new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e4.getMessage());
                                Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
                            }
                        }
                    }
                }
            }
            AdContainer adContainer3 = this.k;
            if (adContainer3 != null) {
                adContainer3.destroy();
                this.k = null;
            }
        } else {
            int i4 = this.p;
            if (100 != i4 && 102 == i4) {
                AdContainer adContainer4 = this.k;
                if (adContainer4 != null) {
                    int i5 = this.q;
                    if (200 == i5) {
                        RenderView renderView2 = (RenderView) adContainer4;
                        renderView2.setFullScreenActivityContext(null);
                        try {
                            renderView2.b();
                        } catch (Exception e5) {
                            new StringBuilder("Encountered unexpected error in processing close request: ").append(e5.getMessage());
                            Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                        }
                    } else if (201 == i5 && VERSION.SDK_INT >= 15) {
                        AdContainer adContainer5 = this.k;
                        if (adContainer5 instanceof bd) {
                            bd bdVar = (bd) adContainer5;
                            NativeVideoView nativeVideoView = this.o;
                            if (nativeVideoView != null) {
                                be beVar = (be) nativeVideoView.getTag();
                                if (beVar != null) {
                                    if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == bdVar.b.a) {
                                        this.o.a();
                                    }
                                    if (this.k.getFullScreenEventsListener() != null) {
                                        try {
                                            this.k.getFullScreenEventsListener().b(beVar);
                                        } catch (Exception e6) {
                                            new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e6.getMessage());
                                            Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e6));
                                        }
                                    }
                                }
                            }
                        } else if ((adContainer5 instanceof ah) && adContainer5.getFullScreenEventsListener() != null) {
                            try {
                                this.k.getFullScreenEventsListener().b(null);
                            } catch (Exception e7) {
                                new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e7.getMessage());
                                Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e7));
                            }
                        }
                    }
                    a((Object) this.k);
                    this.k.destroy();
                    this.k = null;
                }
            }
        }
        super.onDestroy();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RenderView renderView = this.l;
        if (renderView != null && "Resized".equals(renderView.d) && renderView.getResizeProperties() != null) {
            renderView.g.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        a aVar = (a) b.remove(Integer.valueOf(i2));
        if (aVar != null) {
            c.remove(Integer.valueOf(i2));
            aVar.a();
            this.a = true;
            finish();
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (!z) {
            RenderView renderView = this.l;
            if (renderView != null) {
                renderView.setOrientationProperties(renderView.getOrientationProperties());
            }
            AdContainer adContainer = this.k;
            if (adContainer != null) {
                adContainer.setRequestedScreenOrientation();
            }
        }
    }

    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        onMultiWindowModeChanged(z);
    }

    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        com.inmobi.commons.core.utilities.a.c();
        b bVar = (b) e.remove(Integer.valueOf(i2));
        if (bVar != null) {
            bVar.a(iArr);
        }
        finish();
    }

    public void onBackPressed() {
        int i2 = this.p;
        if (i2 == 102) {
            AdContainer adContainer = this.k;
            if (adContainer != null && !adContainer.c()) {
                boolean z = false;
                if (200 == this.q) {
                    RenderView renderView = (RenderView) this.k;
                    if (renderView != null) {
                        if (renderView.r != null) {
                            z = true;
                        }
                        if (z) {
                            renderView.a(renderView.r, "broadcastEvent('backButtonPressed')");
                        }
                        if (!renderView.q) {
                            this.a = true;
                            try {
                                renderView.b();
                                return;
                            } catch (Exception e2) {
                                new StringBuilder("Encountered unexpected error in processing close request: ").append(e2.getMessage());
                                Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                            }
                        } else {
                            return;
                        }
                    }
                    return;
                }
                AdContainer adContainer2 = this.k;
                if (adContainer2 instanceof bd) {
                    bd bdVar = (bd) adContainer2;
                    if (bdVar != null && !bdVar.h().b) {
                        this.a = true;
                        NativeVideoView nativeVideoView = this.o;
                        if (nativeVideoView != null) {
                            be beVar = (be) nativeVideoView.getTag();
                            if (beVar != null) {
                                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == bdVar.b.a) {
                                    this.o.a();
                                }
                                try {
                                    if (((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                                        beVar.v.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(this.o.getCurrentPosition()));
                                        if (!bdVar.l && ((Boolean) beVar.v.get("didRequestFullScreen")).booleanValue()) {
                                            beVar.v.put("didRequestFullScreen", Boolean.valueOf(false));
                                            if (beVar.y != null) {
                                                beVar.y.v.put("didRequestFullScreen", Boolean.valueOf(false));
                                            }
                                            bdVar.b();
                                            beVar.v.put("isFullScreen", Boolean.valueOf(false));
                                        }
                                    }
                                    return;
                                } catch (Exception e3) {
                                    new StringBuilder("Encountered unexpected error in onVideoClosed handler: ").append(e3.getMessage());
                                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in closing video");
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                                }
                            }
                            return;
                        }
                        finish();
                    }
                } else if (adContainer2 instanceof ah) {
                    ah ahVar = (ah) adContainer2;
                    if (ahVar == null) {
                        finish();
                    } else if (!ahVar.h().b) {
                        ahVar.b();
                    }
                }
            }
        } else if (i2 == 100) {
            this.a = true;
            finish();
        }
    }
}
