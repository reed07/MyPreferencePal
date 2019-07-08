package com.moat.analytics.mobile.inm;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

class j {
    /* access modifiers changed from: private */
    public int a = 0;
    private boolean b = false;
    private boolean c = false;
    private final AtomicBoolean d = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public boolean f = false;
    private boolean g = false;
    @NonNull
    private final WeakReference<WebView> h;
    private final Map<b, String> i;
    private final LinkedList<String> j;
    /* access modifiers changed from: private */
    public final long k;
    private final String l;
    private final List<String> m;
    private final a n;
    private final BroadcastReceiver o = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.d();
            } catch (Exception e) {
                m.a(e);
            }
            if (System.currentTimeMillis() - j.this.k > 30000) {
                j.this.i();
            }
        }
    };
    private final BroadcastReceiver p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.e();
            } catch (Exception e) {
                m.a(e);
            }
        }
    };

    enum a {
        WEBVIEW,
        NATIVE_DISPLAY,
        NATIVE_VIDEO
    }

    j(WebView webView, a aVar) {
        this.h = new WeakReference<>(webView);
        this.n = aVar;
        this.j = new LinkedList<>();
        this.m = new ArrayList();
        this.i = new WeakHashMap();
        this.k = System.currentTimeMillis();
        this.l = String.format("javascript:(function(d,k){function l(){function d(a,b){var c=ipkn[b]||ipkn[kuea];if(c){var h=function(b){var c=b.b;c.ts=b.i;c.ticks=b.g;c.buffered=!0;a(c)};h(c.first);c.a.forEach(function(a){h(a)})}}function e(a){var b=a.a,c=a.c,h=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var f=d[g];if('function'===typeof f)try{h?f(h):f()}catch(k){}a&&delete b[c]}}function f(a,b,c){'function'===typeof a&& (b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]},c===yhgt&&d(a,b))}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};ipkn={};csif={};this.h=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId;this.metadata=a};this.nvsj=function(a){briz||(f(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea; c!==kuea&&bjmk[c]||f(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||f(a,c,uqaj)};this.lgpr=function(a,b){f(a,b||kuea,yhgt)};this.hgen=function(a,b){f(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={a:iymv,b:a,c:ewat};briz?e(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={a:bjmk,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.egpw= function(a){var b=a||kuea;ryup[b]=!0;var c={a:uqaj,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.sglu=function(a){var b=a.adKey||kuea,c={a:yhgt,b:a.event||a,g:1,i:+new Date,f:!1};b!==kuea&&(c.c=a.adKey);a=0<Object.keys(yhgt).length;if(!a||!this.isNative)if(ipkn[b]){var d=ipkn[b].a.slice(-1)[0]||ipkn[b].first;JSON.stringify(c.b)==JSON.stringify(d.b)?d.g+=1:(5<=ipkn[b].a.length&&ipkn[b].a.shift(),ipkn[b].a.push(c))}else ipkn[b]={first:c,a:[]};a&&e(c);return a};this.ucbx=function(a){e({c:a.adKey||kuea,a:csif, b:a.event,f:!1})}}'undefined'===typeof d.MoatMAK&&(d.MoatMAK=new l,d.MoatMAK.h(k),d.__zMoatInit__=!0)})(window,%s);", new Object[]{h()});
        if (d("Initialize")) {
            IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
            IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
            LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.o, intentFilter);
            LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.p, intentFilter2);
            d();
            i.a().a(s.c(), this);
            p.a(3, "JavaScriptBridge", (Object) this, "bridge initialization succeeded");
        }
    }

    private boolean a(WebView webView) {
        return webView.getSettings().getJavaScriptEnabled();
    }

    private void c() {
        for (Entry key : this.i.entrySet()) {
            b bVar = (b) key.getKey();
            if (bVar.e()) {
                g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.e}));
            }
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        try {
            if (w.a().a != d.OFF) {
                if (!this.c) {
                    p.a(3, "JavaScriptBridge", (Object) this, "Attempting to establish communication (setting environment variables).");
                    this.c = true;
                }
                g(this.l);
            }
        } catch (Exception e2) {
            p.a("JavaScriptBridge", (Object) this, "Attempt failed to establish communication (did not set environment variables).", (Throwable) e2);
        }
    }

    private void d(b bVar) {
        p.a(3, "JavaScriptBridge", (Object) this, "Stopping view update loop");
        if (bVar != null) {
            i.a().a(bVar);
        }
    }

    private boolean d(String str) {
        WebView g2 = g();
        if (g2 == null) {
            StringBuilder sb = new StringBuilder("WebView is null. Can't ");
            sb.append(str);
            p.a(6, "JavaScriptBridge", (Object) this, sb.toString());
            throw new m("WebView is null");
        } else if (a(g2)) {
            return true;
        } else {
            StringBuilder sb2 = new StringBuilder("JavaScript is not enabled in the given WebView. Can't ");
            sb2.append(str);
            p.a(6, "JavaScriptBridge", (Object) this, sb2.toString());
            throw new m("JavaScript is not enabled in the WebView");
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void e() {
        try {
            if (w.a().a != d.OFF) {
                if (this.g) {
                    p.a(3, "JavaScriptBridge", (Object) this, "Can't send info, already cleaned up");
                    return;
                }
                if (f()) {
                    if (!this.b || g().getUrl() != null) {
                        if (g().getUrl() != null) {
                            this.b = true;
                        }
                        for (Entry key : this.i.entrySet()) {
                            b bVar = (b) key.getKey();
                            if (bVar == null || bVar.f() == null) {
                                p.a(3, "JavaScriptBridge", (Object) this, "Tracker has no subject");
                                if (bVar != null) {
                                    if (!bVar.f) {
                                    }
                                }
                                c(bVar);
                            }
                            if (bVar.e()) {
                                if (!this.d.get()) {
                                    g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.e}));
                                }
                                String format = String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.sglu(%s);}", new Object[]{bVar.h()});
                                if (VERSION.SDK_INT >= 19) {
                                    g().evaluateJavascript(format, new ValueCallback<String>() {
                                        /* renamed from: a */
                                        public void onReceiveValue(String str) {
                                            String str2;
                                            if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("false")) {
                                                String str3 = "JavaScriptBridge";
                                                j jVar = j.this;
                                                StringBuilder sb = new StringBuilder("Received value is:");
                                                if (str == null) {
                                                    str2 = "null";
                                                } else {
                                                    StringBuilder sb2 = new StringBuilder("(String)");
                                                    sb2.append(str);
                                                    str2 = sb2.toString();
                                                }
                                                sb.append(str2);
                                                p.a(3, str3, (Object) jVar, sb.toString());
                                                if (j.this.a >= 150) {
                                                    p.a(3, "JavaScriptBridge", (Object) j.this, "Giving up on finding ad");
                                                    j.this.b();
                                                }
                                                
                                                /*  JADX ERROR: Method code generation error
                                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0097: INVOKE  (wrap: com.moat.analytics.mobile.inm.j
                                                      0x0095: IGET  (r2v3 com.moat.analytics.mobile.inm.j) = (r7v0 'this' com.moat.analytics.mobile.inm.j$1 A[THIS]) com.moat.analytics.mobile.inm.j.1.a com.moat.analytics.mobile.inm.j) com.moat.analytics.mobile.inm.j.b(com.moat.analytics.mobile.inm.j):int type: STATIC in method: com.moat.analytics.mobile.inm.j.1.a(java.lang.String):void, dex: classes3.dex
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:220)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:299)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                                                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                                                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                                                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                                                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                                                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                                                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                                                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                                                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                                                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                                                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                                                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                                                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                                                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                                                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                                                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                                                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                                                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                                                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                                                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                                                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                                                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                                    	... 86 more
                                                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                                                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                                                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                                                    	at java.base/java.lang.Class.forName0(Native Method)
                                                    	at java.base/java.lang.Class.forName(Unknown Source)
                                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                                                    	... 104 more
                                                    */
                                                /*
                                                    this = this;
                                                    r0 = 1
                                                    r1 = 3
                                                    if (r8 == 0) goto L_0x0055
                                                    java.lang.String r2 = "null"
                                                    boolean r2 = r8.equalsIgnoreCase(r2)
                                                    if (r2 != 0) goto L_0x0055
                                                    java.lang.String r2 = "false"
                                                    boolean r2 = r8.equalsIgnoreCase(r2)
                                                    if (r2 == 0) goto L_0x0015
                                                    goto L_0x0055
                                                L_0x0015:
                                                    java.lang.String r2 = "true"
                                                    boolean r2 = r8.equalsIgnoreCase(r2)
                                                    if (r2 == 0) goto L_0x003f
                                                    com.moat.analytics.mobile.inm.j r8 = com.moat.analytics.mobile.inm.j.this
                                                    boolean r8 = r8.f
                                                    if (r8 != 0) goto L_0x0038
                                                    com.moat.analytics.mobile.inm.j r8 = com.moat.analytics.mobile.inm.j.this
                                                    r8.f = r0
                                                    java.lang.String r8 = "JavaScriptBridge"
                                                    com.moat.analytics.mobile.inm.j r0 = com.moat.analytics.mobile.inm.j.this
                                                    java.lang.String r2 = "Javascript has found ad"
                                                    com.moat.analytics.mobile.inm.p.a(r1, r8, r0, r2)
                                                    com.moat.analytics.mobile.inm.j r8 = com.moat.analytics.mobile.inm.j.this
                                                    r8.a()
                                                L_0x0038:
                                                    com.moat.analytics.mobile.inm.j r8 = com.moat.analytics.mobile.inm.j.this
                                                    r0 = 0
                                                    r8.a = r0
                                                    return
                                                L_0x003f:
                                                    java.lang.String r0 = "JavaScriptBridge"
                                                    com.moat.analytics.mobile.inm.j r2 = com.moat.analytics.mobile.inm.j.this
                                                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                                                    java.lang.String r4 = "Received unusual value from Javascript:"
                                                    r3.<init>(r4)
                                                    r3.append(r8)
                                                    java.lang.String r8 = r3.toString()
                                                    com.moat.analytics.mobile.inm.p.a(r1, r0, r2, r8)
                                                    goto L_0x00ba
                                                L_0x0055:
                                                    java.lang.String r2 = "JavaScriptBridge"
                                                    com.moat.analytics.mobile.inm.j r3 = com.moat.analytics.mobile.inm.j.this
                                                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                                                    java.lang.String r5 = "Received value is:"
                                                    r4.<init>(r5)
                                                    if (r8 != 0) goto L_0x0065
                                                    java.lang.String r5 = "null"
                                                    goto L_0x0073
                                                L_0x0065:
                                                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                                                    java.lang.String r6 = "(String)"
                                                    r5.<init>(r6)
                                                    r5.append(r8)
                                                    java.lang.String r5 = r5.toString()
                                                L_0x0073:
                                                    r4.append(r5)
                                                    java.lang.String r4 = r4.toString()
                                                    com.moat.analytics.mobile.inm.p.a(r1, r2, r3, r4)
                                                    com.moat.analytics.mobile.inm.j r2 = com.moat.analytics.mobile.inm.j.this
                                                    int r2 = r2.a
                                                    r3 = 150(0x96, float:2.1E-43)
                                                    if (r2 < r3) goto L_0x0095
                                                    java.lang.String r2 = "JavaScriptBridge"
                                                    com.moat.analytics.mobile.inm.j r3 = com.moat.analytics.mobile.inm.j.this
                                                    java.lang.String r4 = "Giving up on finding ad"
                                                    com.moat.analytics.mobile.inm.p.a(r1, r2, r3, r4)
                                                    com.moat.analytics.mobile.inm.j r2 = com.moat.analytics.mobile.inm.j.this
                                                    r2.b()
                                                L_0x0095:
                                                    com.moat.analytics.mobile.inm.j r2 = com.moat.analytics.mobile.inm.j.this
                                                    
                                                    // error: 0x0097: INVOKE  (r2 I:com.moat.analytics.mobile.inm.j) com.moat.analytics.mobile.inm.j.b(com.moat.analytics.mobile.inm.j):int type: STATIC
                                                    if (r8 == 0) goto L_0x00ba
                                                    java.lang.String r2 = "false"
                                                    boolean r8 = r8.equalsIgnoreCase(r2)
                                                    if (r8 == 0) goto L_0x00ba
                                                    com.moat.analytics.mobile.inm.j r8 = com.moat.analytics.mobile.inm.j.this
                                                    boolean r8 = r8.e
                                                    if (r8 != 0) goto L_0x00ba
                                                    com.moat.analytics.mobile.inm.j r8 = com.moat.analytics.mobile.inm.j.this
                                                    r8.e = r0
                                                    java.lang.String r8 = "JavaScriptBridge"
                                                    com.moat.analytics.mobile.inm.j r0 = com.moat.analytics.mobile.inm.j.this
                                                    java.lang.String r2 = "Bridge connection established"
                                                    com.moat.analytics.mobile.inm.p.a(r1, r8, r0, r2)
                                                L_0x00ba:
                                                    return
                                                */
                                                throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.inm.j.AnonymousClass1.onReceiveValue(java.lang.String):void");
                                            }
                                        });
                                    } else {
                                        g().loadUrl(format);
                                    }
                                }
                            }
                            return;
                        }
                    }
                    String str = "JavaScriptBridge";
                    StringBuilder sb = new StringBuilder("WebView became null");
                    sb.append(g() == null ? "" : "based on null url");
                    sb.append(", stopping tracking loop");
                    p.a(3, str, (Object) this, sb.toString());
                    b();
                }
            } catch (Exception e2) {
                m.a(e2);
                b();
            }
        }

        private void e(String str) {
            if (this.m.size() >= 50) {
                this.m.subList(0, 25).clear();
            }
            this.m.add(str);
        }

        private void f(String str) {
            if (this.d.get()) {
                g(str);
            } else {
                e(str);
            }
        }

        private boolean f() {
            return g() != null;
        }

        private WebView g() {
            return (WebView) this.h.get();
        }

        @UiThread
        private void g(String str) {
            if (this.g) {
                p.a(3, "JavaScriptBridge", (Object) this, "Can't send, already cleaned up");
                return;
            }
            if (f()) {
                p.b(2, "JavaScriptBridge", this, str);
                if (VERSION.SDK_INT >= 19) {
                    g().evaluateJavascript(str, null);
                    return;
                }
                g().loadUrl(str);
            }
        }

        private String h() {
            try {
                a d2 = s.d();
                b e2 = s.e();
                HashMap hashMap = new HashMap();
                String a2 = d2.a();
                String b2 = d2.b();
                String c2 = d2.c();
                String num = Integer.toString(VERSION.SDK_INT);
                String b3 = s.b();
                String str = this.n == a.WEBVIEW ? "0" : AppEventsConstants.EVENT_PARAM_VALUE_YES;
                String str2 = e2.e ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0";
                String str3 = e2.d ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0";
                String str4 = ((k) MoatAnalytics.getInstance()).b() ? "0" : AppEventsConstants.EVENT_PARAM_VALUE_YES;
                hashMap.put("versionHash", "c334ae83accfebb8da23104450c896463c9cfab7");
                hashMap.put("appName", a2);
                hashMap.put("namespace", "INM");
                hashMap.put("version", "2.5.0");
                hashMap.put("deviceOS", num);
                hashMap.put("isNative", str);
                hashMap.put("appId", b2);
                hashMap.put("source", c2);
                hashMap.put(Attributes.CARRIER, e2.b);
                hashMap.put("sim", e2.a);
                hashMap.put("phone", String.valueOf(e2.c));
                hashMap.put("buildFp", Build.FINGERPRINT);
                hashMap.put("buildModel", Build.MODEL);
                hashMap.put("buildMfg", Build.MANUFACTURER);
                hashMap.put("buildBrand", Build.BRAND);
                hashMap.put("buildProduct", Build.PRODUCT);
                hashMap.put("buildTags", Build.TAGS);
                hashMap.put("f1", str3);
                hashMap.put("f2", str2);
                hashMap.put("locationEnabled", str4);
                if (b3 != null) {
                    hashMap.put("aqzx", b3);
                }
                return new JSONObject(hashMap).toString();
            } catch (Exception unused) {
                return "{}";
            }
        }

        /* access modifiers changed from: private */
        public void i() {
            p.a(3, "JavaScriptBridge", (Object) this, "Stopping metadata reporting loop");
            i.a().a(this);
            LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.o);
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            p.a(3, "JavaScriptBridge", (Object) this, "webViewReady");
            if (this.d.compareAndSet(false, true)) {
                p.a(3, "JavaScriptBridge", (Object) this, "webViewReady first time");
                i();
                for (String g2 : this.m) {
                    g(g2);
                }
                this.m.clear();
            }
            c();
        }

        /* access modifiers changed from: 0000 */
        public void a(b bVar) {
            if (bVar != null) {
                StringBuilder sb = new StringBuilder("adding tracker");
                sb.append(bVar.e);
                p.a(3, "JavaScriptBridge", (Object) this, sb.toString());
                this.i.put(bVar, "");
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(String str) {
            f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.crts(%s);}", new Object[]{str}));
        }

        /* access modifiers changed from: 0000 */
        @UiThread
        public void a(String str, JSONObject jSONObject) {
            if (this.g) {
                p.a(3, "JavaScriptBridge", (Object) this, "Can't dispatch, already cleaned up");
                return;
            }
            String jSONObject2 = jSONObject.toString();
            if (!this.d.get() || !f()) {
                this.j.add(jSONObject2);
                return;
            }
            g(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{str, jSONObject2}));
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            p.a(3, "JavaScriptBridge", (Object) this, "Cleaning up");
            this.g = true;
            i();
            for (Entry key : this.i.entrySet()) {
                d((b) key.getKey());
            }
            this.i.clear();
            LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.p);
        }

        /* access modifiers changed from: 0000 */
        public void b(b bVar) {
            if (d("startTracking")) {
                StringBuilder sb = new StringBuilder("Starting tracking on tracker");
                sb.append(bVar.e);
                p.a(3, "JavaScriptBridge", (Object) this, sb.toString());
                g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.e}));
                i.a().a(s.c(), bVar);
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(String str) {
            StringBuilder sb = new StringBuilder("markUserInteractionEvent:");
            sb.append(str);
            p.a(3, "JavaScriptBridge", (Object) this, sb.toString());
            f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.ucbx(%s);}", new Object[]{str}));
        }

        /* access modifiers changed from: 0000 */
        public void c(b bVar) {
            Throwable th = null;
            if (!this.g) {
                try {
                    if (d("stopTracking")) {
                        String str = "JavaScriptBridge";
                        try {
                            StringBuilder sb = new StringBuilder("Ending tracking on tracker");
                            sb.append(bVar.e);
                            p.a(3, str, (Object) this, sb.toString());
                            g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.egpw(\"%s\");}", new Object[]{bVar.e}));
                        } catch (Exception e2) {
                            p.a("JavaScriptBridge", (Object) this, "Failed to end impression.", (Throwable) e2);
                        }
                    }
                } catch (m e3) {
                    th = e3;
                }
                if (this.n == a.NATIVE_DISPLAY) {
                    d(bVar);
                } else {
                    b();
                }
                this.i.remove(bVar);
            }
            if (th != null) {
                throw th;
            }
        }

        /* access modifiers changed from: 0000 */
        public void c(String str) {
            p.a(3, "JavaScriptBridge", (Object) this, "flushDispatchQueue");
            if (this.j.size() >= 200) {
                LinkedList linkedList = new LinkedList();
                for (int i2 = 0; i2 < 10; i2++) {
                    linkedList.addFirst((String) this.j.removeFirst());
                }
                int min = Math.min(Math.min(this.j.size() / 200, 10) + 200, this.j.size());
                for (int i3 = 0; i3 < min; i3++) {
                    this.j.removeFirst();
                }
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    this.j.addFirst((String) it.next());
                }
            }
            if (!this.j.isEmpty()) {
                String str2 = "javascript:%s.dispatchMany([%s])";
                StringBuilder sb = new StringBuilder();
                String str3 = "";
                int i4 = 1;
                while (!this.j.isEmpty() && i4 < 200) {
                    i4++;
                    String str4 = (String) this.j.removeFirst();
                    if (sb.length() + str4.length() > 2000) {
                        break;
                    }
                    sb.append(str3);
                    sb.append(str4);
                    str3 = ",";
                }
                g(String.format(str2, new Object[]{str, sb.toString()}));
            }
            this.j.clear();
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            try {
                super.finalize();
                p.a(3, "JavaScriptBridge", (Object) this, "finalize");
                b();
            } catch (Exception e2) {
                m.a(e2);
            }
        }
    }
