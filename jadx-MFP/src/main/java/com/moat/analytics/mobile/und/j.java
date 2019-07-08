package com.moat.analytics.mobile.und;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.facebook.appevents.AppEventsConstants;
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
    boolean a = false;
    /* access modifiers changed from: private */
    public int b = 0;
    private boolean c = false;
    private boolean d = false;
    private final WeakReference<WebView> e;
    private final Map<b, String> f;
    private final LinkedList<String> g;
    private final AtomicBoolean h = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final long i;
    private final a j;
    private final List<String> k;
    private final a l;
    private final BroadcastReceiver m = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.c();
            } catch (Exception e) {
                m.a(e);
            }
            if (System.currentTimeMillis() - j.this.i > 10000) {
                j.this.e();
            }
        }
    };
    private final BroadcastReceiver n = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.d();
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
        this.e = new WeakReference<>(webView);
        this.l = aVar;
        this.j = new a();
        this.g = new LinkedList<>();
        this.k = new ArrayList();
        this.f = new WeakHashMap();
        this.i = System.currentTimeMillis();
        IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
        IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
        LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.m, intentFilter);
        LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.n, intentFilter2);
        try {
            p.a(3, "JavaScriptBridge", (Object) this, b() ? "bridge installed" : "bridge not installed");
        } catch (Exception e2) {
            m.a(e2);
        }
    }

    private boolean a(WebView webView, String str) {
        String str2;
        StringBuilder sb;
        String str3;
        if (webView == null) {
            str2 = "JavaScriptBridge";
            sb = new StringBuilder();
            str3 = "WebView is null. Can't ";
        } else if (webView.getSettings().getJavaScriptEnabled()) {
            return true;
        } else {
            str2 = "JavaScriptBridge";
            sb = new StringBuilder();
            str3 = "JavaScript is not enabled in the given WebView. Can't ";
        }
        sb.append(str3);
        sb.append(str);
        p.a(6, str2, (Object) this, sb.toString());
        return false;
    }

    private boolean b() {
        if (h() != null && !a(h(), "installBridge")) {
            return false;
        }
        this.a = true;
        i.a().a(s.c(), this);
        return true;
    }

    /* access modifiers changed from: private */
    public void c() {
        try {
            if (w.a().a != d.OFF) {
                if (!this.d) {
                    p.a(3, "JavaScriptBridge", (Object) this, "Ready for communication (setting environment variables).");
                    this.d = true;
                }
                String format = String.format("javascript:(function(e,k){function l(){function f(a){var b=a.c,c=a.a,f=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var h=d[g];if('function'===typeof h)try{f?h(f):h()}catch(k){}a&&delete b[c]}}function d(a,b,c){'function'===typeof a&&(b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]})}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};csif={};this.g=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId};this.nvsj=function(a){briz||(d(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea;c!==kuea&&bjmk[c]||d(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||d(a,c,uqaj)};this.lgpr=function(a,b){d(a,b||kuea,yhgt)};this.hgen=function(a,b){d(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={c:iymv,b:a,a:ewat};briz?f(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={c:bjmk,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.egpw=function(a){var b=a||kuea;ryup[b]=!0;var c={c:uqaj,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.sglu=function(a){var b={c:yhgt,b:a.event||a,f:!1};(a.adKey||kuea)!==kuea&&(b.a=a.adKey);f(b);return 0<Object.keys(yhgt).length};this.ucbx=function(a){f({a:a.adKey||kuea,c:csif,b:a.event,f:!1})}}'undefined'===typeof e.MoatMAK&&(e.MoatMAK=new l,e.MoatMAK.g(k),e.__zMoatInit__=!0)})(window,%s);", new Object[]{i()});
                if (g()) {
                    h().loadUrl(format);
                }
            }
        } catch (Exception e2) {
            p.a("JavaScriptBridge", (Object) this, "Failed to initialize communication (did not set environment variables).", (Throwable) e2);
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    public void d() {
        String str;
        String sb;
        try {
            if (w.a().a != d.OFF) {
                if (this.e != null && g()) {
                    if (!this.c || h().getUrl() != null) {
                        if (h().getUrl() != null) {
                            this.c = true;
                        }
                        for (Entry key : this.f.entrySet()) {
                            b bVar = (b) key.getKey();
                            if (bVar == null || bVar.d() == null) {
                                p.a(3, "JavaScriptBridge", (Object) this, "Tracker has no subject");
                                if (bVar != null) {
                                    if (!bVar.c) {
                                    }
                                }
                                c(bVar);
                            }
                            if (bVar.d) {
                                f(String.format("javascript: MoatMAK.mqjh(\"%s\")", new Object[]{bVar.b}));
                                String format = String.format("javascript: MoatMAK.sglu(%s)", new Object[]{bVar.f()});
                                if (VERSION.SDK_INT >= 19) {
                                    h().evaluateJavascript(format, new ValueCallback<String>() {
                                        /* renamed from: a */
                                        public void onReceiveValue(String str) {
                                            String str2;
                                            if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("false")) {
                                                String str3 = "JavaScriptBridge";
                                                j jVar = j.this;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append("Received value is:");
                                                if (str == null) {
                                                    str2 = "null";
                                                } else {
                                                    StringBuilder sb2 = new StringBuilder();
                                                    sb2.append("(String)");
                                                    sb2.append(str);
                                                    str2 = sb2.toString();
                                                }
                                                sb.append(str2);
                                                p.a(3, str3, (Object) jVar, sb.toString());
                                                if (j.this.b >= 50) {
                                                    j.this.f();
                                                }
                                                
                                                /*  JADX ERROR: Method code generation error
                                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0091: INVOKE  (wrap: com.moat.analytics.mobile.und.j
                                                      0x008f: IGET  (r7v5 com.moat.analytics.mobile.und.j) = (r6v0 'this' com.moat.analytics.mobile.und.j$1 A[THIS]) com.moat.analytics.mobile.und.j.1.a com.moat.analytics.mobile.und.j) com.moat.analytics.mobile.und.j.c(com.moat.analytics.mobile.und.j):int type: STATIC in method: com.moat.analytics.mobile.und.j.1.a(java.lang.String):void, dex: classes3.dex
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
                                                    	... 82 more
                                                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                                                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                                                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                                                    	at java.base/java.lang.Class.forName0(Native Method)
                                                    	at java.base/java.lang.Class.forName(Unknown Source)
                                                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                                                    	... 100 more
                                                    */
                                                /*
                                                    this = this;
                                                    r0 = 3
                                                    if (r7 == 0) goto L_0x0052
                                                    java.lang.String r1 = "null"
                                                    boolean r1 = r7.equalsIgnoreCase(r1)
                                                    if (r1 != 0) goto L_0x0052
                                                    java.lang.String r1 = "false"
                                                    boolean r1 = r7.equalsIgnoreCase(r1)
                                                    if (r1 == 0) goto L_0x0014
                                                    goto L_0x0052
                                                L_0x0014:
                                                    java.lang.String r1 = "true"
                                                    boolean r1 = r7.equalsIgnoreCase(r1)
                                                    if (r1 == 0) goto L_0x0039
                                                    com.moat.analytics.mobile.und.j r7 = com.moat.analytics.mobile.und.j.this
                                                    int r7 = r7.b
                                                    if (r7 == 0) goto L_0x0032
                                                    java.lang.String r7 = "JavaScriptBridge"
                                                    com.moat.analytics.mobile.und.j r1 = com.moat.analytics.mobile.und.j.this
                                                    java.lang.String r2 = "Javascript has found ad"
                                                    com.moat.analytics.mobile.und.p.a(r0, r7, r1, r2)
                                                    com.moat.analytics.mobile.und.j r7 = com.moat.analytics.mobile.und.j.this
                                                    r7.e()
                                                L_0x0032:
                                                    com.moat.analytics.mobile.und.j r7 = com.moat.analytics.mobile.und.j.this
                                                    r0 = 0
                                                    r7.b = r0
                                                    goto L_0x0094
                                                L_0x0039:
                                                    java.lang.String r1 = "JavaScriptBridge"
                                                    com.moat.analytics.mobile.und.j r2 = com.moat.analytics.mobile.und.j.this
                                                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                                                    r3.<init>()
                                                    java.lang.String r4 = "Received unusual value from Javascript:"
                                                    r3.append(r4)
                                                    r3.append(r7)
                                                    java.lang.String r7 = r3.toString()
                                                    com.moat.analytics.mobile.und.p.a(r0, r1, r2, r7)
                                                    goto L_0x0094
                                                L_0x0052:
                                                    java.lang.String r1 = "JavaScriptBridge"
                                                    com.moat.analytics.mobile.und.j r2 = com.moat.analytics.mobile.und.j.this
                                                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                                                    r3.<init>()
                                                    java.lang.String r4 = "Received value is:"
                                                    r3.append(r4)
                                                    if (r7 != 0) goto L_0x0065
                                                    java.lang.String r7 = "null"
                                                    goto L_0x0076
                                                L_0x0065:
                                                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                                                    r4.<init>()
                                                    java.lang.String r5 = "(String)"
                                                    r4.append(r5)
                                                    r4.append(r7)
                                                    java.lang.String r7 = r4.toString()
                                                L_0x0076:
                                                    r3.append(r7)
                                                    java.lang.String r7 = r3.toString()
                                                    com.moat.analytics.mobile.und.p.a(r0, r1, r2, r7)
                                                    com.moat.analytics.mobile.und.j r7 = com.moat.analytics.mobile.und.j.this
                                                    int r7 = r7.b
                                                    r0 = 50
                                                    if (r7 < r0) goto L_0x008f
                                                    com.moat.analytics.mobile.und.j r7 = com.moat.analytics.mobile.und.j.this
                                                    r7.f()
                                                L_0x008f:
                                                    com.moat.analytics.mobile.und.j r7 = com.moat.analytics.mobile.und.j.this
                                                    
                                                    // error: 0x0091: INVOKE  (r7 I:com.moat.analytics.mobile.und.j) com.moat.analytics.mobile.und.j.c(com.moat.analytics.mobile.und.j):int type: STATIC
                                                L_0x0094:
                                                    return
                                                */
                                                throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.und.j.AnonymousClass1.onReceiveValue(java.lang.String):void");
                                            }
                                        });
                                    } else {
                                        h().loadUrl(format);
                                    }
                                }
                            }
                            return;
                        }
                    }
                    if (this.e == null) {
                        str = "JavaScriptBridge";
                        sb = "WebView ref became null, stopping tracking loop";
                    } else {
                        str = "JavaScriptBridge";
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("WebView became null");
                        sb2.append(h() == null ? "" : "based on null url");
                        sb2.append(", stopping tracking loop");
                        sb = sb2.toString();
                    }
                    p.a(3, str, (Object) this, sb);
                    f();
                }
            } catch (Exception e2) {
                m.a(e2);
                f();
            }
        }

        private void d(b bVar) {
            p.a(3, "JavaScriptBridge", (Object) this, "Stopping view update loop");
            if (bVar != null) {
                i.a().a(bVar);
            }
        }

        private void d(String str) {
            if (this.k.size() >= 50) {
                this.k.subList(0, 25).clear();
            }
            this.k.add(str);
        }

        /* access modifiers changed from: private */
        public void e() {
            p.a(3, "JavaScriptBridge", (Object) this, "Stopping metadata reporting loop");
            i.a().a(this);
            LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.m);
        }

        private boolean e(String str) {
            if (this.a) {
                return true;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Bridge is not installed in the given WebView. Can't ");
            sb.append(str);
            p.a(6, "JavaScriptBridge", (Object) this, sb.toString());
            return false;
        }

        /* access modifiers changed from: private */
        public void f() {
            p.a(3, "JavaScriptBridge", (Object) this, "Cleaning up");
            e();
            for (Entry key : this.f.entrySet()) {
                d((b) key.getKey());
            }
            this.f.clear();
            LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.n);
        }

        private void f(String str) {
            if (g()) {
                h().loadUrl(str);
            }
        }

        private boolean g() {
            return h() != null;
        }

        private WebView h() {
            return (WebView) this.e.get();
        }

        private String i() {
            try {
                HashMap hashMap = new HashMap();
                String a2 = this.j.a();
                String b2 = this.j.b();
                String num = Integer.toString(VERSION.SDK_INT);
                String b3 = s.b();
                String str = this.l == a.WEBVIEW ? "0" : AppEventsConstants.EVENT_PARAM_VALUE_YES;
                hashMap.put("versionHash", "3f2ae9c1894282b5e0222f0d06bbf457191f816f");
                hashMap.put("appName", a2);
                hashMap.put("namespace", "UND");
                hashMap.put("version", "2.2.0");
                hashMap.put("deviceOS", num);
                hashMap.put("isNative", str);
                hashMap.put("appId", b2);
                if (b3 != null) {
                    hashMap.put("aqzx", b3);
                }
                return new JSONObject(hashMap).toString();
            } catch (Exception unused) {
                return "{}";
            }
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            p.a(3, "JavaScriptBridge", (Object) this, "webViewReady");
            this.h.compareAndSet(false, true);
            e();
            for (String f2 : this.k) {
                f(f2);
            }
            this.k.clear();
        }

        /* access modifiers changed from: 0000 */
        public void a(b bVar) {
            if (bVar != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("adding tracker");
                sb.append(bVar.b);
                p.a(3, "JavaScriptBridge", (Object) this, sb.toString());
                this.f.put(bVar, "");
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(String str) {
            String format = String.format("javascript: MoatMAK.crts(%s)", new Object[]{str});
            if (this.h.get()) {
                f(format);
            } else {
                d(format);
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(String str, JSONObject jSONObject) {
            String jSONObject2 = jSONObject.toString();
            if (!this.h.get() || !g()) {
                this.g.add(jSONObject2);
                return;
            }
            f(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{str, jSONObject2}));
        }

        /* access modifiers changed from: 0000 */
        public void b(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("markUserInteractionEvent:");
            sb.append(str);
            p.a(3, "JavaScriptBridge", (Object) this, sb.toString());
            String format = String.format("javascript: MoatMAK.ucbx(%s)", new Object[]{str});
            if (this.h.get()) {
                f(format);
            } else {
                d(format);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean b(b bVar) {
            try {
                if (g() && a(h(), "startTracking")) {
                    if (e("startTracking")) {
                        if (bVar.d() == null) {
                            if (!bVar.c) {
                                p.a(3, "JavaScriptBridge", (Object) this, "Tracker subject is null, won't start tracking");
                                return false;
                            }
                            p.a(3, "JavaScriptBridge", (Object) this, "Tracker subject is null at start");
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("Starting tracking on tracker");
                        sb.append(bVar.b);
                        p.a(3, "JavaScriptBridge", (Object) this, sb.toString());
                        f(String.format("javascript: MoatMAK.mqjh(\"%s\")", new Object[]{bVar.b}));
                        i.a().a(s.c(), bVar);
                        return true;
                    }
                }
                return false;
            } catch (Exception e2) {
                p.a("JavaScriptBridge", (Object) this, "Failed to initialize impression start.", (Throwable) e2);
                return false;
            }
        }

        /* access modifiers changed from: 0000 */
        public void c(String str) {
            p.a(3, "JavaScriptBridge", (Object) this, "flushDispatchQueue");
            this.h.compareAndSet(false, true);
            if (this.g.size() >= 200) {
                LinkedList linkedList = new LinkedList();
                for (int i2 = 0; i2 < 10; i2++) {
                    linkedList.addFirst((String) this.g.removeFirst());
                }
                int min = Math.min(Math.min(this.g.size() / 200, 10) + 200, this.g.size());
                for (int i3 = 0; i3 < min; i3++) {
                    this.g.removeFirst();
                }
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    this.g.addFirst((String) it.next());
                }
            }
            int i4 = 0;
            while (!this.g.isEmpty() && i4 < 200) {
                i4++;
                String str2 = "javascript:%s.dispatchMany([%s])";
                StringBuilder sb = new StringBuilder();
                boolean z = true;
                while (!this.g.isEmpty() && i4 < 200) {
                    i4++;
                    String str3 = (String) this.g.getFirst();
                    if (sb.length() + str3.length() > 2000) {
                        break;
                    }
                    this.g.removeFirst();
                    if (z) {
                        z = false;
                    } else {
                        sb.append(",");
                    }
                    sb.append(str3);
                }
                f(String.format(str2, new Object[]{str, sb.toString()}));
            }
            this.g.clear();
        }

        /* access modifiers changed from: 0000 */
        public boolean c(b bVar) {
            boolean z = false;
            if (g() && a(h(), "stopTracking") && e("stopTracking")) {
                String str = "JavaScriptBridge";
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Ending tracking on tracker");
                    sb.append(bVar.b);
                    p.a(3, str, (Object) this, sb.toString());
                    f(String.format("javascript: MoatMAK.egpw(\"%s\")", new Object[]{bVar.b}));
                } catch (Exception e2) {
                    p.a("JavaScriptBridge", (Object) this, "Failed to end impression.", (Throwable) e2);
                }
                z = true;
            }
            if (this.l == a.NATIVE_DISPLAY) {
                d(bVar);
            } else {
                f();
            }
            this.f.remove(bVar);
            return z;
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            try {
                super.finalize();
                p.a(3, "JavaScriptBridge", (Object) this, "finalize");
                f();
            } catch (Exception e2) {
                m.a(e2);
            }
        }
    }
