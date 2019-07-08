package com.facebook.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.f;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.adapters.g;
import com.facebook.ads.internal.adapters.h;
import com.facebook.ads.internal.ipc.RemoteANActivity;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.settings.a.C0009a;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.e.a.e;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.view.l;
import com.facebook.ads.internal.view.m;
import com.facebook.ads.internal.view.n;
import com.facebook.ads.internal.view.r;
import com.facebook.ads.internal.view.s;
import com.facebook.ads.internal.view.z;
import com.facebook.ads.internal.w.b.x;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AudienceNetworkActivity extends Activity {
    @Deprecated
    public static final String AD_ICON_URL = "adIconUrl";
    @Deprecated
    public static final String AD_SUBTITLE = "adSubtitle";
    @Deprecated
    public static final String AD_TITLE = "adTitle";
    public static final String AUDIENCE_NETWORK_UNIQUE_ID_EXTRA = "uniqueId";
    public static final String AUTOPLAY = "autoplay";
    public static final String BROWSER_URL = "browserURL";
    public static final String CLIENT_TOKEN = "clientToken";
    @Deprecated
    public static final String CONTEXT_SWITCH_BEHAVIOR = "contextSwitchBehavior";
    @Deprecated
    public static final String END_CARD_ACTIVATION_COMMAND = "facebookRewardedVideoEndCardActivationCommand";
    @Deprecated
    public static final String END_CARD_MARKUP = "facebookRewardedVideoEndCardMarkup";
    public static final String HANDLER_TIME = "handlerTime";
    public static final String PLACEMENT_ID = "placementId";
    public static final String PREDEFINED_ORIENTATION_KEY = "predefinedOrientationKey";
    public static final String REQUEST_TIME = "requestTime";
    public static final String REWARD_SERVER_URL = "rewardServerURL";
    public static final String SKIP_DELAY_SECONDS_KEY = "skipAfterSeconds";
    public static final String USE_CACHE = "useCache";
    public static final String VIDEO_LOGGER = "videoLogger";
    public static final String VIDEO_MPD = "videoMPD";
    public static final String VIDEO_SEEK_TIME = "videoSeekTime";
    public static final String VIDEO_URL = "videoURL";
    public static final String VIEW_TYPE = "viewType";
    @Deprecated
    public static final String WEBVIEW_ENCODING = "utf-8";
    @Deprecated
    public static final String WEBVIEW_MIME_TYPE = "text/html";
    private final List<BackButtonInterceptor> a = new ArrayList();
    /* access modifiers changed from: private */
    public RelativeLayout b;
    private int c = -1;
    private String d;
    private C0009a e;
    private long f;
    private long g;
    private int h;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.a i;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.a.c j;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.c.c k;

    public interface BackButtonInterceptor {
        boolean interceptBackButton();
    }

    private static class a implements C0012a {
        final WeakReference<AudienceNetworkActivity> a;

        private a(AudienceNetworkActivity audienceNetworkActivity) {
            this.a = new WeakReference<>(audienceNetworkActivity);
        }

        public void a(View view) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).b.addView(view);
            }
        }

        public void a(View view, int i) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).b.addView(view, i);
            }
        }

        public void a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str);
            }
        }

        public void a(String str, com.facebook.ads.internal.o.d dVar) {
            if (this.a.get() != null) {
                AudienceNetworkActivity.a((AudienceNetworkActivity) this.a.get(), str, dVar);
            }
        }

        public void a(String str, boolean z, @Nullable com.facebook.ads.internal.view.a.b bVar) {
            if (this.a.get() != null) {
                AudienceNetworkActivity.a((AudienceNetworkActivity) this.a.get(), str, z, bVar);
            }
        }
    }

    private static class b {
        /* access modifiers changed from: private */
        public final AudienceNetworkActivity a;
        private final Intent b;
        private final com.facebook.ads.internal.s.c c;

        private b(AudienceNetworkActivity audienceNetworkActivity, Intent intent, com.facebook.ads.internal.s.c cVar) {
            this.a = audienceNetworkActivity;
            this.b = intent;
            this.c = cVar;
        }

        static /* synthetic */ com.facebook.ads.internal.view.a a(b bVar) {
            q qVar = (q) bVar.b.getSerializableExtra("rewardedVideoAdDataBundle");
            AudienceNetworkActivity audienceNetworkActivity = bVar.a;
            s sVar = new s(audienceNetworkActivity, bVar.c, new com.facebook.ads.internal.view.i.a(audienceNetworkActivity), new d(), qVar);
            return sVar;
        }

        static /* synthetic */ com.facebook.ads.internal.view.a a(b bVar, RelativeLayout relativeLayout) {
            AudienceNetworkActivity audienceNetworkActivity = bVar.a;
            z zVar = new z(audienceNetworkActivity, bVar.c, new a());
            zVar.a((View) relativeLayout);
            zVar.a(bVar.b.getIntExtra("video_time_polling_interval", 200));
            return zVar;
        }

        private boolean a() {
            return this.b.getBooleanExtra(AudienceNetworkActivity.USE_CACHE, false);
        }

        private k b() {
            return (k) this.b.getSerializableExtra("ad_data_bundle");
        }

        static /* synthetic */ com.facebook.ads.internal.view.a b(b bVar) {
            q qVar = (q) bVar.b.getSerializableExtra("rewardedVideoAdDataBundle");
            AudienceNetworkActivity audienceNetworkActivity = bVar.a;
            return new r(audienceNetworkActivity, bVar.c, new d(), qVar);
        }

        static /* synthetic */ com.facebook.ads.internal.view.a c(b bVar) {
            f fVar = (f) bVar.b.getSerializableExtra("rewardedVideoAdDataBundle");
            AudienceNetworkActivity audienceNetworkActivity = bVar.a;
            return new com.facebook.ads.internal.view.f(audienceNetworkActivity, fVar, bVar.c, new d());
        }

        static /* synthetic */ com.facebook.ads.internal.view.a f(b bVar) {
            com.facebook.ads.internal.view.a a2 = g.a(bVar.b.getStringExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA));
            if (a2 == null) {
                return null;
            }
            a2.setListener(new a());
            return a2;
        }

        static /* synthetic */ com.facebook.ads.internal.view.a g(b bVar) {
            n nVar = new n(bVar.a, bVar.c, bVar.b(), bVar.a() ? new com.facebook.ads.internal.h.b(bVar.a) : null, new a());
            return nVar;
        }

        static /* synthetic */ com.facebook.ads.internal.view.a h(b bVar) {
            return new l(bVar.a, bVar.b(), bVar.c, new a());
        }

        static /* synthetic */ com.facebook.ads.internal.view.a i(b bVar) {
            return new e(bVar.a, bVar.c, bVar.a() ? new com.facebook.ads.internal.h.b(bVar.a) : null, new a());
        }

        static /* synthetic */ com.facebook.ads.internal.view.a j(b bVar) {
            return new m(bVar.a, bVar.c, bVar.b(), new a());
        }
    }

    private class c implements OnLongClickListener {
        private c() {
        }

        public boolean onLongClick(View view) {
            if (!(AudienceNetworkActivity.this.k == null || AudienceNetworkActivity.this.b == null)) {
                AudienceNetworkActivity.this.k.setBounds(0, 0, AudienceNetworkActivity.this.b.getWidth(), AudienceNetworkActivity.this.b.getHeight());
                AudienceNetworkActivity.this.k.a(!AudienceNetworkActivity.this.k.a());
            }
            return true;
        }
    }

    private static class d extends a {
        private d(AudienceNetworkActivity audienceNetworkActivity) {
            super();
        }

        public void a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str);
                String a = aa.REWARDED_VIDEO_END_ACTIVITY.a();
                String a2 = aa.REWARDED_VIDEO_ERROR.a();
                if (str.equals(a) || str.equals(a2)) {
                    ((AudienceNetworkActivity) this.a.get()).finish();
                }
            }
        }

        public void a(String str, com.facebook.ads.internal.o.d dVar) {
            super.a(str, dVar);
            if (this.a.get() != null) {
                AudienceNetworkActivity audienceNetworkActivity = (AudienceNetworkActivity) this.a.get();
                if (str.equals(aa.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD.a())) {
                    Intent intent = new Intent();
                    intent.putExtra("rewardedVideoAdDataBundle", ((com.facebook.ads.internal.view.f.a) dVar).a());
                    com.facebook.ads.internal.view.a a = b.a(new b(intent, com.facebook.ads.internal.s.d.a((Context) audienceNetworkActivity)));
                    if (audienceNetworkActivity.j != null) {
                        audienceNetworkActivity.j.b();
                    }
                    audienceNetworkActivity.j = null;
                    x.a((ViewGroup) a);
                    audienceNetworkActivity.i = a;
                    a.a(audienceNetworkActivity.getIntent(), null, audienceNetworkActivity);
                }
            }
        }
    }

    static /* synthetic */ void a(AudienceNetworkActivity audienceNetworkActivity, String str, com.facebook.ads.internal.o.d dVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        sb.append(audienceNetworkActivity.d);
        Intent intent = new Intent(sb.toString());
        intent.putExtra("event", dVar);
        LocalBroadcastManager.getInstance(audienceNetworkActivity).sendBroadcast(intent);
    }

    static /* synthetic */ void a(AudienceNetworkActivity audienceNetworkActivity, String str, boolean z, com.facebook.ads.internal.view.a.b bVar) {
        if (audienceNetworkActivity.j == null) {
            audienceNetworkActivity.j = com.facebook.ads.internal.view.a.d.a(audienceNetworkActivity.getApplicationContext(), com.facebook.ads.internal.s.d.a((Context) audienceNetworkActivity), str, audienceNetworkActivity.i, new a());
            audienceNetworkActivity.j.setLayoutParams(new LayoutParams(-1, -1));
        }
        audienceNetworkActivity.j.a(z);
        audienceNetworkActivity.j.setAdReportingFlowListener(bVar);
        x.b(audienceNetworkActivity.j);
        x.a((ViewGroup) audienceNetworkActivity.b);
        audienceNetworkActivity.b.addView(audienceNetworkActivity.j);
        audienceNetworkActivity.j.a();
    }

    private void a(Exception exc) {
        finish();
        com.facebook.ads.internal.w.h.a.b(this, "an_activity", com.facebook.ads.internal.w.h.b.aa, exc);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if ("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW".equals(str)) {
            finish();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        sb.append(this.d);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(sb.toString()));
    }

    private boolean a() {
        return this.e == C0009a.REWARDED_VIDEO || this.e == C0009a.REWARDED_PLAYABLE || this.e == C0009a.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD;
    }

    public static Class getAdActivity() {
        return AdInternalSettings.d ? RemoteANActivity.class : AudienceNetworkActivity.class;
    }

    public void addBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.a.add(backButtonInterceptor);
    }

    public void finish() {
        if (!isFinishing()) {
            a(a() ? aa.REWARDED_VIDEO_CLOSED.a() : "com.facebook.ads.interstitial.dismissed");
            super.finish();
        }
    }

    public void onBackPressed() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.g += currentTimeMillis - this.f;
            this.f = currentTimeMillis;
            if (this.g > ((long) this.h)) {
                boolean z = false;
                for (BackButtonInterceptor interceptBackButton : this.a) {
                    if (interceptBackButton.interceptBackButton()) {
                        z = true;
                    }
                }
                if (!z) {
                    super.onBackPressed();
                }
            }
        } catch (Exception e2) {
            a(e2);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        try {
            if (this.i instanceof h) {
                ((h) this.i).a(configuration);
            } else if (this.i instanceof s) {
                ((s) this.i).onConfigurationChanged(configuration);
            }
        } catch (Exception e2) {
            a(e2);
        }
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        com.facebook.ads.internal.view.a aVar;
        super.onCreate(bundle);
        try {
            com.facebook.ads.internal.w.b.c.a();
            boolean z = true;
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            this.b = new RelativeLayout(this);
            x.a((View) this.b, -16777216);
            setContentView(this.b, new LayoutParams(-1, -1));
            Intent intent = getIntent();
            if (bundle != null) {
                this.c = bundle.getInt(PREDEFINED_ORIENTATION_KEY, -1);
                this.d = bundle.getString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
                this.e = (C0009a) bundle.getSerializable(VIEW_TYPE);
            } else {
                this.c = intent.getIntExtra(PREDEFINED_ORIENTATION_KEY, -1);
                this.d = intent.getStringExtra(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
                this.e = (C0009a) intent.getSerializableExtra(VIEW_TYPE);
                this.h = intent.getIntExtra(SKIP_DELAY_SECONDS_KEY, 0) * 1000;
            }
            b bVar = new b(getIntent(), com.facebook.ads.internal.s.d.a((Context) this));
            if (this.e != null) {
                switch (this.e) {
                    case FULL_SCREEN_VIDEO:
                        aVar = b.a(bVar, this.b);
                        break;
                    case REWARDED_VIDEO:
                        aVar = b.a(bVar);
                        break;
                    case REWARDED_PLAYABLE:
                        aVar = b.b(bVar);
                        break;
                    case REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD:
                        aVar = b.c(bVar);
                        break;
                    case INTERSTITIAL_WEB_VIEW:
                        aVar = bVar.a;
                        break;
                    case BROWSER:
                        aVar = bVar.a;
                        break;
                    case INTERSTITIAL_OLD_NATIVE_VIDEO:
                        aVar = b.f(bVar);
                        break;
                    case INTERSTITIAL_NATIVE_VIDEO:
                        aVar = b.g(bVar);
                        break;
                    case INTERSTITIAL_NATIVE_IMAGE:
                        aVar = b.h(bVar);
                        break;
                    case INTERSTITIAL_NATIVE_CAROUSEL:
                        aVar = b.i(bVar);
                        break;
                    case INTERSTITIAL_NATIVE_PLAYABLE:
                        aVar = b.j(bVar);
                        break;
                }
            }
            aVar = null;
            this.i = aVar;
            if (this.i == null) {
                com.facebook.ads.internal.o.b.a(com.facebook.ads.internal.o.a.a(null, "Unable to infer viewType from intent or savedInstanceState"));
                a("com.facebook.ads.interstitial.error");
                finish();
                return;
            }
            this.i.a(intent, bundle, this);
            a("com.facebook.ads.interstitial.displayed");
            this.f = System.currentTimeMillis();
            if (this.e != C0009a.INTERSTITIAL_WEB_VIEW) {
                z = false;
            }
            if (com.facebook.ads.internal.r.a.b(this) && this.e != C0009a.BROWSER) {
                this.k = new com.facebook.ads.internal.view.c.c();
                this.k.a(intent.getStringExtra(PLACEMENT_ID));
                this.k.b(getPackageName());
                long longExtra = intent.getLongExtra(REQUEST_TIME, 0);
                if (longExtra != 0) {
                    this.k.a(longExtra);
                }
                TextView textView = new TextView(this);
                textView.setText("Debug");
                textView.setTextColor(-1);
                x.a((View) textView, Color.argb(160, 0, 0, 0));
                textView.setPadding(5, 5, 5, 5);
                LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.addRule(12, -1);
                layoutParams.addRule(11, -1);
                textView.setLayoutParams(layoutParams);
                c cVar = new c();
                textView.setOnLongClickListener(cVar);
                if (z) {
                    this.b.addView(textView);
                } else {
                    this.b.setOnLongClickListener(cVar);
                }
                this.b.getOverlay().add(this.k);
            }
        } catch (Exception e2) {
            a(e2);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            a(a() ? aa.REWARDED_VIDEO_ACTIVITY_DESTROYED.a() : "com.facebook.ads.interstitial.activity_destroyed");
            if (this.b != null) {
                this.b.removeAllViews();
            }
            if (this.i != null) {
                g.a(this.i);
                this.i.onDestroy();
                this.i = null;
            }
            if (this.k != null && com.facebook.ads.internal.r.a.b(this)) {
                this.k.b();
            }
            if (this.j != null) {
                this.j.b();
            }
        } catch (Exception e2) {
            a(e2);
        }
        super.onDestroy();
    }

    public void onPause() {
        try {
            this.g += System.currentTimeMillis() - this.f;
            if (this.i != null) {
                this.i.a_(false);
            }
        } catch (Exception e2) {
            a(e2);
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        try {
            this.f = System.currentTimeMillis();
            if (this.i != null) {
                this.i.b(false);
            }
        } catch (Exception e2) {
            a(e2);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            if (this.i != null) {
                this.i.a(bundle);
            }
            bundle.putInt(PREDEFINED_ORIENTATION_KEY, this.c);
            bundle.putString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.d);
            bundle.putSerializable(VIEW_TYPE, this.e);
        } catch (Exception e2) {
            a(e2);
        }
    }

    public void onStart() {
        super.onStart();
        try {
            if (this.c != -1) {
                try {
                    setRequestedOrientation(this.c);
                } catch (IllegalStateException unused) {
                }
            }
        } catch (Exception e2) {
            a(e2);
        }
    }

    public void removeBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.a.remove(backButtonInterceptor);
    }
}
