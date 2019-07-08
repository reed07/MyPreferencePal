package com.myfitnesspal.shared.ui.fragment.impl;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.MfpWebViewClient;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;
import java.util.Map;
import javax.inject.Inject;

public class ExternalWebViewFragment extends MfpFragment {
    private static final double NANOS_PER_SECOND = 1.0E9d;
    public static final String NAVIGATION = "navigation";
    protected static final int REFRESH = 100;
    public static final String SOURCE_STRING = "mfp";
    private static final String VANILLA_URL = "http://www.myfitnesspal.com/vanilla/index";
    @Inject
    protected AnalyticsService analyticsService;
    private Map<String, String> attributes;
    @Inject
    protected ConfigService configService;
    /* access modifiers changed from: private */
    public String lastLoadedHost;
    /* access modifiers changed from: private */
    public int pageVisitCount;
    private long startTime;
    private String utmCampaign;
    protected String utmMedium;
    protected String utmSource;
    private WebView webView;
    private WebViewClient webViewClient;

    private class VanillaForumsWebViewClient extends MfpWebViewClient {
        private VanillaForumsWebViewClient(Activity activity) {
            super(activity);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (super.shouldOverrideUrlLoading(webView, str)) {
                return true;
            }
            String host = Uri.parse(ExternalWebViewFragment.VANILLA_URL).getHost();
            if (host.equals(ExternalWebViewFragment.this.lastLoadedHost) || host.equals(MfpWebViewClient.MFP_HOST)) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0028: INVOKE  (wrap: com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment
                      0x0026: IGET  (r1v6 com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment) = (r0v0 'this' com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment$VanillaForumsWebViewClient A[THIS]) com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment.VanillaForumsWebViewClient.this$0 com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment) com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment.access$208(com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment):int type: STATIC in method: com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment.VanillaForumsWebViewClient.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean, dex: classes4.dex
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
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                    	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:237)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:224)
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
                    	... 31 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 49 more
                    */
                /*
                    this = this;
                    boolean r1 = super.shouldOverrideUrlLoading(r1, r2)
                    if (r1 == 0) goto L_0x0008
                    r1 = 1
                    return r1
                L_0x0008:
                    java.lang.String r1 = "http://www.myfitnesspal.com/vanilla/index"
                    android.net.Uri r1 = android.net.Uri.parse(r1)
                    java.lang.String r1 = r1.getHost()
                    com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment r2 = com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment.this
                    java.lang.String r2 = r2.lastLoadedHost
                    boolean r2 = r1.equals(r2)
                    if (r2 != 0) goto L_0x0026
                    java.lang.String r2 = "www.myfitnesspal.com"
                    boolean r1 = r1.equals(r2)
                    if (r1 == 0) goto L_0x002b
                L_0x0026:
                    com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment r1 = com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment.this
                    
                    // error: 0x0028: INVOKE  (r1 I:com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment) com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment.access$208(com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment):int type: STATIC
                L_0x002b:
                    r1 = 0
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment.VanillaForumsWebViewClient.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
            }
        }

        /* access modifiers changed from: protected */
        public WebView setWebView(WebView webView2) {
            if (this.webViewClient == null) {
                this.webViewClient = new VanillaForumsWebViewClient(getActivity());
            }
            this.webView = webView2;
            webView2.setWebViewClient(this.webViewClient);
            return webView2;
        }

        /* access modifiers changed from: protected */
        public WebView getWebView() {
            return this.webView;
        }

        /* access modifiers changed from: protected */
        public Map<String, String> getMutableAnalyticsAttributes() {
            return this.attributes;
        }

        /* access modifiers changed from: protected */
        public void populateUtmParams(String str) {
            if (Strings.notEmpty(str)) {
                Bundle queryParams = UriUtils.getQueryParams(str);
                this.utmMedium = queryParams.getString("utm_medium", "");
                this.utmSource = queryParams.getString("utm_source", "");
                this.utmCampaign = queryParams.getString("utm_campaign", "");
                return;
            }
            this.utmMedium = "navigation";
            this.utmSource = SOURCE_STRING;
            this.utmCampaign = "";
        }

        public void onStart() {
            super.onStart();
            this.startTime = System.nanoTime();
        }

        public void onStop() {
            super.onStop();
            String valueOf = String.valueOf(((double) (System.nanoTime() - this.startTime)) / NANOS_PER_SECOND);
            String valueOf2 = String.valueOf(this.pageVisitCount);
            String str = "";
            WebView webView2 = this.webView;
            if (webView2 != null) {
                str = webView2.getUrl();
            }
            this.attributes = new Builder().put("source", Strings.toString(this.utmSource)).put("campaign", Strings.toString(this.utmCampaign)).put("medium", Strings.toString(this.utmMedium)).put(Attributes.FRAME_TIME, Strings.toString(valueOf)).put(Attributes.PAGE_VISITS, Strings.toString(valueOf2)).put(Attributes.SOURCE_URL, str).build();
        }

        public boolean backPressed() {
            WebView webView2 = this.webView;
            if (webView2 == null || !webView2.canGoBack()) {
                return false;
            }
            this.webView.goBack();
            return true;
        }

        /* access modifiers changed from: protected */
        public void loadPageInWebView(String str, String str2) {
            this.lastLoadedHost = str;
            this.webView.loadUrl(str2);
        }
    }
