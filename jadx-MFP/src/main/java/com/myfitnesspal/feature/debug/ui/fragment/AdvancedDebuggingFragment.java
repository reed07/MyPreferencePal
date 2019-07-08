package com.myfitnesspal.feature.debug.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.Preference.OnPreferenceClickListener;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.achievementinterstitialad.service.StoredAchievementEvents;
import com.myfitnesspal.feature.consents.ui.activity.AdConsentsActivity;
import com.myfitnesspal.feature.debug.ui.activity.AnalyticsEventsActivity;
import com.myfitnesspal.feature.debug.ui.activity.EndpointActivity;
import com.myfitnesspal.feature.debug.ui.activity.FlagResetActivity;
import com.myfitnesspal.feature.debug.ui.activity.LocaleOverrideActivity;
import com.myfitnesspal.feature.debug.ui.activity.NotificationsDebugActivity;
import com.myfitnesspal.feature.debug.ui.activity.PremiumDebugActivity;
import com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity;
import com.myfitnesspal.feature.debug.util.DebugSettingsService;
import com.myfitnesspal.feature.onboarding.ui.activity.OnboardingActivity;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpCongratsFragment;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.walkthrough.ui.activity.WalkthroughLoggingActivity;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.constants.Constants.Settings.Ads;
import com.myfitnesspal.shared.constants.Constants.Settings.App;
import com.myfitnesspal.shared.constants.Constants.Settings.App.Logging;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.Toaster;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.mock.interceptor.legacy.FileRequestInterceptor;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.reactivex.annotations.NonNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class AdvancedDebuggingFragment extends PreferenceFragmentCompat {
    private static final String MEAL_NAME_TEST = "Test meal";
    @Inject
    protected AdsSettings adsSettings;
    @Inject
    protected ApiUrlProvider apiUrlProvider;
    @Inject
    protected AppSettings appSettings;
    @Inject
    protected AuthTokenProvider authTokenProvider;
    @Inject
    protected DebugSettingsService debugSettingsService;
    @Inject
    protected FoodSearchActivityFactory foodSearchActivityFactory;
    @Inject
    protected GlobalSettingsService globalSettingsService;
    @Inject
    protected LocalSettingsService localSettingsService;
    @Inject
    protected MfpApiSettings mfpApiSettings;
    @Inject
    protected NavigationHelper navigationHelper;
    @Inject
    protected ProductService productService;
    @Inject
    protected RequestInterceptor requestInterceptor;
    @Inject
    protected Session session;
    @Inject
    protected SignUpService signUpService;
    @Inject
    protected StoredAchievementEvents storedAchievementEvents;
    @Inject
    protected UserApplicationSettingsService userApplicationSettingsService;

    public void onCreatePreferences(Bundle bundle, String str) {
    }

    public static AdvancedDebuggingFragment newInstance() {
        return new AdvancedDebuggingFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.advanced_debugging_fragment);
        MyFitnessPalApp.getInstance().component().inject(this);
        this.navigationHelper.setContext(getActivity());
        setupAppPrefs();
        setupABTestOverrides();
        setupAdsPrefs();
        setupMocksPrefs();
        setupPremiumDevMenu();
        setupNewPrivacyFlow();
        setupSignUpCongrats();
        setupPersonalizedConsents();
        setupAuthDevMenu();
        setupLocaleOverrideItem();
        setupUacfPrefs();
    }

    private void setupLocaleOverrideItem() {
        findPreference(App.LOCALE_OVERRIDE).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(LocaleOverrideActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity())).startActivity();
            }
        });
    }

    private void setupNewPrivacyFlow() {
        findPreference(App.NEW_PRIVACY_FLOW_DEBUG).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                      0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z8eTiZyVOQhxoGkjb4TYEgOO6yA A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z8eTiZyVOQhxoGkjb4TYEgOO6yA.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupNewPrivacyFlow$1(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC) in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z8eTiZyVOQhxoGkjb4TYEgOO6yA.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z8eTiZyVOQhxoGkjb4TYEgOO6yA A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z8eTiZyVOQhxoGkjb4TYEgOO6yA.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupNewPrivacyFlow$1(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z8eTiZyVOQhxoGkjb4TYEgOO6yA.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
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
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	... 41 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 62 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment r0 = com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.this
                    boolean r2 = 
                    // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2 I:android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupNewPrivacyFlow$1(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.fragment.$$Lambda$AdvancedDebuggingFragment$z8eTiZyVOQhxoGkjb4TYEgOO6yA.onPreferenceClick(android.support.v7.preference.Preference):boolean");
            }
        });
    }

    private void setupSignUpCongrats() {
        findPreference(App.SIGNUP_CONGRATS_DEBUG).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, SignUpCongratsFragment.newInstance(false)).commit();
            }
        });
    }

    private void setupPersonalizedConsents() {
        findPreference(App.PERSONALIZED_CONSENTS).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(AdConsentsActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity(), null)).startActivity();
            }
        });
    }

    private void setupAuthDevMenu() {
        findPreference(App.AUTH_DEV_MENU).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return EndpointActivity.start(AdvancedDebuggingFragment.this.getActivity());
            }
        });
    }

    private void setupPremiumDevMenu() {
        findPreference(App.PREMIUM_DEV_MENU).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(PremiumDebugActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity())).startActivity();
            }
        });
    }

    private void setupAppPrefs() {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        boolean z = buildConfiguration.isDebug() || buildConfiguration.isQABuild();
        Preference findPreference = findPreference(App.PAYMENT_DEBUG);
        Preference findPreference2 = findPreference(App.PROGRESS_PHOTOS_DEBUG);
        Preference findPreference3 = findPreference(App.RETRIEVE_MEAL_FOOD_DEBUG);
        if (z) {
            findPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                public final boolean onPreferenceClick(Preference preference) {
                    return 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                          0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$m-RiuELchkhNKUGO_idUjKMp_cQ A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$m-RiuELchkhNKUGO_idUjKMp_cQ.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$6(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC) in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$m-RiuELchkhNKUGO_idUjKMp_cQ.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$m-RiuELchkhNKUGO_idUjKMp_cQ A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$m-RiuELchkhNKUGO_idUjKMp_cQ.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$6(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$m-RiuELchkhNKUGO_idUjKMp_cQ.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                        	... 43 more
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
                        	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                        	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                        	... 46 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 63 more
                        */
                    /*
                        this = this;
                        com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment r0 = com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.this
                        boolean r2 = 
                        // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2 I:android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$6(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.fragment.$$Lambda$AdvancedDebuggingFragment$mRiuELchkhNKUGO_idUjKMp_cQ.onPreferenceClick(android.support.v7.preference.Preference):boolean");
                }
            });
            findPreference2.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                public final boolean onPreferenceClick(Preference preference) {
                    return 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                          0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$eJfpskbWo1wg1nelXl7ZRC2GU5g A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$eJfpskbWo1wg1nelXl7ZRC2GU5g.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$7(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC) in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$eJfpskbWo1wg1nelXl7ZRC2GU5g.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$eJfpskbWo1wg1nelXl7ZRC2GU5g A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$eJfpskbWo1wg1nelXl7ZRC2GU5g.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$7(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$eJfpskbWo1wg1nelXl7ZRC2GU5g.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                        	... 43 more
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
                        	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                        	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                        	... 46 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 63 more
                        */
                    /*
                        this = this;
                        com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment r0 = com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.this
                        boolean r2 = 
                        // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2 I:android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$7(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.fragment.$$Lambda$AdvancedDebuggingFragment$eJfpskbWo1wg1nelXl7ZRC2GU5g.onPreferenceClick(android.support.v7.preference.Preference):boolean");
                }
            });
            findPreference3.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                public final boolean onPreferenceClick(Preference preference) {
                    return 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                          0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$IAfWtGjzuSuhJWzsWYboPzsghFY A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$IAfWtGjzuSuhJWzsWYboPzsghFY.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$8(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC) in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$IAfWtGjzuSuhJWzsWYboPzsghFY.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$IAfWtGjzuSuhJWzsWYboPzsghFY A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$IAfWtGjzuSuhJWzsWYboPzsghFY.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$8(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$IAfWtGjzuSuhJWzsWYboPzsghFY.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                        	... 43 more
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
                        	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                        	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                        	... 46 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 63 more
                        */
                    /*
                        this = this;
                        com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment r0 = com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.this
                        boolean r2 = 
                        // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2 I:android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$8(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.fragment.$$Lambda$AdvancedDebuggingFragment$IAfWtGjzuSuhJWzsWYboPzsghFY.onPreferenceClick(android.support.v7.preference.Preference):boolean");
                }
            });
        } else {
            getPreferenceScreen().removePreference(findPreference);
            getPreferenceScreen().removePreference(findPreference2);
            getPreferenceScreen().removePreference(findPreference3);
        }
        findPreference(App.NOTIFICATIONS_DEBUG).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(NotificationsDebugActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity())).startActivity();
            }
        });
        findPreference(App.ANALYTICS).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(AnalyticsEventsActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity())).startActivity();
            }
        });
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(Logging.PRIVATE_FILE_LOGGING);
        checkBoxPreference.setChecked(this.appSettings.isPrivateFileLoggingEnabled());
        checkBoxPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return AdvancedDebuggingFragment.this.appSettings.setPrivateFileLoggingEnabled(((Boolean) obj).booleanValue());
            }
        });
        findPreference(Logging.PRIVATE_LOG_FILE_CLEAR).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.lambda$setupAppPrefs$12(AdvancedDebuggingFragment.this, preference);
            }
        });
        findPreference(App.WALKTHROUGH_LOGGING).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(WalkthroughLoggingActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity())).startActivity();
            }
        });
        findPreference(App.WALKTHROUGH_FOOD_SEARCH).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                      0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$WoQV2kdCZwVe3xVKMv3OCpq4LkY A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$WoQV2kdCZwVe3xVKMv3OCpq4LkY.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$14(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC) in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$WoQV2kdCZwVe3xVKMv3OCpq4LkY.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$WoQV2kdCZwVe3xVKMv3OCpq4LkY A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$WoQV2kdCZwVe3xVKMv3OCpq4LkY.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$14(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$WoQV2kdCZwVe3xVKMv3OCpq4LkY.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
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
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	... 41 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 66 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment r0 = com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.this
                    boolean r2 = 
                    // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2 I:android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupAppPrefs$14(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.fragment.$$Lambda$AdvancedDebuggingFragment$WoQV2kdCZwVe3xVKMv3OCpq4LkY.onPreferenceClick(android.support.v7.preference.Preference):boolean");
            }
        });
        findPreference(App.AUTH_TOKEN).setSummary((CharSequence) this.authTokenProvider.getAccessToken());
        findPreference(App.FORCE_CRASH).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.lambda$setupAppPrefs$16(AdvancedDebuggingFragment.this, preference);
            }
        });
        findPreference(App.EXPIRE_SYNC_TOKEN).setOnPreferenceClickListener($$Lambda$AdvancedDebuggingFragment$QGbmQqYrxz8Ua4RreVekfLo01lw.INSTANCE);
        findPreference(App.REMOVE_SYNC_TOKEN).setOnPreferenceClickListener($$Lambda$AdvancedDebuggingFragment$ciDTiItjQ2FrxDd13rImqMCj05Y.INSTANCE);
        findPreference(App.FLAG_TOGGLE).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(FlagResetActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity())).startActivity();
            }
        });
        findPreference(App.PREMIUM_ONBOARDING).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(OnboardingActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity())).startActivity();
            }
        });
    }

    public static /* synthetic */ boolean lambda$setupAppPrefs$12(AdvancedDebuggingFragment advancedDebuggingFragment, Preference preference) {
        Ln.clear();
        Toaster.showLong((Activity) advancedDebuggingFragment.getActivity(), "Private log file cleared");
        return true;
    }

    public static /* synthetic */ boolean lambda$setupAppPrefs$16(AdvancedDebuggingFragment advancedDebuggingFragment, Preference preference) {
        advancedDebuggingFragment.getActivity().finish();
        new Thread($$Lambda$AdvancedDebuggingFragment$XZmVojJmV9Lu8tQEcgp8VAsxF3A.INSTANCE).start();
        return true;
    }

    static /* synthetic */ void lambda$null$15() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException unused) {
        }
        throw new RuntimeException("forced crash");
    }

    private void setupABTestOverrides() {
        findPreference("app.abtests.overrides").setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.navigationHelper.withIntent(RolloutDebugActivity.newStartIntent(AdvancedDebuggingFragment.this.getActivity())).startActivity();
            }
        });
    }

    private void setupAdsPrefs() {
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(Ads.TEST_MODE);
        checkBoxPreference.setChecked(this.adsSettings.isTestModeForAds());
        checkBoxPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return AdvancedDebuggingFragment.this.adsSettings.setIsTestModeForAds(((Boolean) obj).booleanValue());
            }
        });
        setTestAdsNetworkPrefs();
        findPreference(Ads.LOGIN_STREAK).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.setLoginStreak();
            }
        });
        findPreference(Ads.STREAK_LAST_SHOWN_DATE).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return AdvancedDebuggingFragment.this.setStreakLastShownDate();
            }
        });
        findPreference(Ads.TRACKING_ID).setSummary((CharSequence) this.localSettingsService.getGAID());
    }

    private void restartApp(@NonNull Context context) {
        context.startActivity(Intent.makeRestartActivityTask(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent()));
        Runtime.getRuntime().exit(0);
    }

    private void setupUacfPrefs() {
        findPreference(getString(R.string.key_uacf_rollout_dev_tool)).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                      0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z9RkMYdaGKf6s_e7znlSCWw3Y4U A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z9RkMYdaGKf6s_e7znlSCWw3Y4U.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$25(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC) in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z9RkMYdaGKf6s_e7znlSCWw3Y4U.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z9RkMYdaGKf6s_e7znlSCWw3Y4U A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z9RkMYdaGKf6s_e7znlSCWw3Y4U.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$25(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$z9RkMYdaGKf6s_e7znlSCWw3Y4U.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
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
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	... 41 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 58 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment r0 = com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.this
                    boolean r2 = 
                    // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2 I:android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$25(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.fragment.$$Lambda$AdvancedDebuggingFragment$z9RkMYdaGKf6s_e7znlSCWw3Y4U.onPreferenceClick(android.support.v7.preference.Preference):boolean");
            }
        });
        findPreference(getString(R.string.key_uacf_configuration_dev_tool)).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                      0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$iLwDRpluCXAnn7cGaeaakeXr0qo A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$iLwDRpluCXAnn7cGaeaakeXr0qo.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$26(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC) in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$iLwDRpluCXAnn7cGaeaakeXr0qo.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$iLwDRpluCXAnn7cGaeaakeXr0qo A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$iLwDRpluCXAnn7cGaeaakeXr0qo.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$26(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$iLwDRpluCXAnn7cGaeaakeXr0qo.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
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
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	... 41 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 58 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment r0 = com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.this
                    boolean r2 = 
                    // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2 I:android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$26(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.fragment.$$Lambda$AdvancedDebuggingFragment$iLwDRpluCXAnn7cGaeaakeXr0qo.onPreferenceClick(android.support.v7.preference.Preference):boolean");
            }
        });
        findPreference(getString(R.string.key_uacf_notification_inbox_dev_tool)).setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                      0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$OXqOMwishL692h4UfMtxfMunaY8 A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$OXqOMwishL692h4UfMtxfMunaY8.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$27(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC) in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$OXqOMwishL692h4UfMtxfMunaY8.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment) = (r1v0 'this' com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$OXqOMwishL692h4UfMtxfMunaY8 A[THIS]) com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$OXqOMwishL692h4UfMtxfMunaY8.f$0 com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2v0 'preference' android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$27(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC in method: com.myfitnesspal.feature.debug.ui.fragment.-$$Lambda$AdvancedDebuggingFragment$OXqOMwishL692h4UfMtxfMunaY8.onPreferenceClick(android.support.v7.preference.Preference):boolean, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
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
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	... 41 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 58 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment r0 = com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.this
                    boolean r2 = 
                    // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment), (r2 I:android.support.v7.preference.Preference) com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment.lambda$setupUacfPrefs$27(com.myfitnesspal.feature.debug.ui.fragment.AdvancedDebuggingFragment, android.support.v7.preference.Preference):boolean type: STATIC
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.fragment.$$Lambda$AdvancedDebuggingFragment$OXqOMwishL692h4UfMtxfMunaY8.onPreferenceClick(android.support.v7.preference.Preference):boolean");
            }
        });
    }

    private void setupMocksPrefs() {
        ListPreference listPreference = (ListPreference) findPreference("app.mockdata");
        listPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return AdvancedDebuggingFragment.lambda$setupMocksPrefs$28(AdvancedDebuggingFragment.this, preference, obj);
            }
        });
        FileRequestInterceptor fileRequestInterceptor = (FileRequestInterceptor) this.requestInterceptor;
        List sets = fileRequestInterceptor.getSets();
        sets.add("None");
        boolean z = true;
        int size = sets.size() - 1;
        if (fileRequestInterceptor.getCurrentSet() == -1) {
            z = false;
        }
        int currentSet = z ? fileRequestInterceptor.getCurrentSet() : size;
        CharSequence[] charSequenceArr = new CharSequence[sets.size()];
        sets.toArray(charSequenceArr);
        listPreference.setEntries(charSequenceArr);
        listPreference.setEntryValues(charSequenceArr);
        listPreference.setValue(currentSet != size ? Strings.toString(sets.get(currentSet)) : "None");
    }

    public static /* synthetic */ boolean lambda$setupMocksPrefs$28(AdvancedDebuggingFragment advancedDebuggingFragment, Preference preference, Object obj) {
        advancedDebuggingFragment.mfpApiSettings.setCurrentMock((String) obj);
        advancedDebuggingFragment.requestInterceptor.reset();
        return true;
    }

    private void setTestAdsNetworkPrefs() {
        ListPreference listPreference = (ListPreference) findPreference(Ads.TEST_NETWORK);
        listPreference.setSummary(this.adsSettings.getTestNetworkPathForAds());
        listPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener(listPreference) {
            private final /* synthetic */ ListPreference f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return AdvancedDebuggingFragment.lambda$setTestAdsNetworkPrefs$29(AdvancedDebuggingFragment.this, this.f$1, preference, obj);
            }
        });
    }

    public static /* synthetic */ boolean lambda$setTestAdsNetworkPrefs$29(AdvancedDebuggingFragment advancedDebuggingFragment, ListPreference listPreference, Preference preference, Object obj) {
        String str = (String) obj;
        advancedDebuggingFragment.adsSettings.setTestNetworkPathForAds(str);
        listPreference.setSummary(str);
        return true;
    }

    /* access modifiers changed from: private */
    public void setLoginStreak() {
        Context context = getContext();
        if (context != null) {
            EditText editText = new EditText(context);
            editText.setInputType(2);
            new Builder(context).setTitle((CharSequence) "Enter login streak days").setView((View) editText).setPositiveButton(17039370, (OnClickListener) new OnClickListener(editText, context) {
                private final /* synthetic */ EditText f$1;
                private final /* synthetic */ Context f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    AdvancedDebuggingFragment.lambda$setLoginStreak$30(AdvancedDebuggingFragment.this, this.f$1, this.f$2, dialogInterface, i);
                }
            }).setNegativeButton(17039360, (OnClickListener) null).show();
        }
    }

    public static /* synthetic */ void lambda$setLoginStreak$30(AdvancedDebuggingFragment advancedDebuggingFragment, EditText editText, Context context, DialogInterface dialogInterface, int i) {
        try {
            advancedDebuggingFragment.debugSettingsService.setLoginStreakDays(Integer.valueOf(editText.getText().toString()).intValue());
            advancedDebuggingFragment.restartApp(context);
        } catch (Exception unused) {
            Toast.makeText(context, "Could not set - use pls numeric value. Try again", 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void setStreakLastShownDate() {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(1);
        final EditText editText = new EditText(getActivity());
        editText.setInputType(2);
        editText.setMaxEms(2);
        editText.setMaxLines(1);
        editText.setHint("Enter streak day (3, 7, 14 or 28)");
        linearLayout.addView(editText);
        final EditText editText2 = new EditText(getActivity());
        editText2.setInputType(2);
        editText2.setMaxEms(3);
        editText2.setMaxLines(1);
        editText2.setHint("Days ego (eg 80)");
        linearLayout.addView(editText2);
        Builder builder = new Builder(getContext());
        builder.setTitle((CharSequence) "Override last shown date value");
        builder.setView((View) linearLayout);
        builder.setPositiveButton((CharSequence) "Set", (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    int intValue = Integer.valueOf(editText.getText().toString()).intValue();
                    if (intValue == 3 || intValue == 7 || intValue == 14 || intValue == 28) {
                        int intValue2 = Integer.valueOf(editText2.getText().toString()).intValue();
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(new Date());
                        instance.add(5, -intValue2);
                        AdvancedDebuggingFragment.this.storedAchievementEvents.storeAchievementShowedEvent(intValue, instance.getTime().getTime());
                        Toast.makeText(AdvancedDebuggingFragment.this.getActivity(), "Saved! Restart pls app", 1).show();
                        return;
                    }
                    Toast.makeText(AdvancedDebuggingFragment.this.getActivity(), "Incorrect streak day!!!", 1).show();
                } catch (Exception unused) {
                    Toast.makeText(AdvancedDebuggingFragment.this.getActivity(), "Could not set - use pls numeric value. Try again", 0).show();
                }
            }
        });
        builder.setNegativeButton((CharSequence) "Cancel", (OnClickListener) null);
        builder.show();
    }
}
