package com.myfitnesspal.shared.uacf;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.service.analytics.AmplitudeService;
import com.uacf.core.util.Ln;
import com.uacf.core.util.VersionUtils;
import com.uacf.identity.sdk.UacfIdentitySdkFactory;
import com.uacf.sync.provider.sdk.UacfSyncFactory;
import io.uacf.clientevents.sdk.UacfClientEventsSdkFactory;
import io.uacf.configuration.sdk.UacfConfigurationSdkFactory;
import io.uacf.consentservices.sdk.UacfConsentServiceSdkFactory.Builder;
import io.uacf.consentservices.sdk.UacfConsentsAppDomain;
import io.uacf.core.api.UacfApiEnvironment;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfUserAccountDomain;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.core.interfaces.UacfDeviceIdProvider;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;
import io.uacf.rollouts.sdk.UacfVariantSdkFactory;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkInitParams;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkManager;
import java.util.Date;
import java.util.Map;
import javax.inject.Inject;

public class UacfSharedLibrary {
    private static final UacfClientEventsCallback clientEventsCallback = new UacfClientEventsCallback() {
        public void reportEvent(String str, Object obj) {
            reportEvent(str, obj, null);
        }

        public void reportEvent(String str, Object obj, Date date) {
            new UacfClientEventsSdkFactory().newSdkInstance().reportEvent(str, obj, date);
        }
    };

    public static final class UacfThumbprintAnalyticsCallback implements UacfClientEventsCallback {
        @Inject
        AmplitudeService amplitudeService;

        public void reportEvent(String str, Object obj) {
            reportEvent(str, obj, null);
        }

        public void reportEvent(String str, Object obj, Date date) {
            Map map;
            if (this.amplitudeService == null) {
                MyFitnessPalApp.getInstance().component().inject(this);
            }
            new UacfClientEventsSdkFactory().newSdkInstance().reportEvent(str, obj, date);
            try {
                Gson gson = new Gson();
                map = (Map) gson.fromJson(gson.toJsonTree(obj), new TypeToken<Map<String, String>>() {
                }.getType());
            } catch (Exception e) {
                Ln.d(e, "Failed to parse client events attribute object into single level Map<String, String>.", new Object[0]);
                map = null;
            }
            this.amplitudeService.reportEvent(str, map);
        }
    }

    public static void initialize(MyFitnessPalApp myFitnessPalApp) {
        String appVersionName = VersionUtils.getAppVersionName(myFitnessPalApp);
        $$Lambda$UacfSharedLibrary$Fh014XELi3GU1ik4qqQ8gCVCJ4 r7 = new UacfDeviceIdProvider() {
            public final String get() {
                return 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: java.lang.String
                      0x0002: INVOKE  (r0v1 java.lang.String) = (wrap: com.myfitnesspal.app.MyFitnessPalApp
                      0x0000: IGET  (r0v0 com.myfitnesspal.app.MyFitnessPalApp) = (r1v0 'this' com.myfitnesspal.shared.uacf.-$$Lambda$UacfSharedLibrary$Fh014XELi3GU1ik4q-qQ8gCVCJ4 A[THIS]) com.myfitnesspal.shared.uacf.-$$Lambda$UacfSharedLibrary$Fh014XELi3GU1ik4q-qQ8gCVCJ4.f$0 com.myfitnesspal.app.MyFitnessPalApp) com.myfitnesspal.shared.uacf.UacfSharedLibrary.lambda$initialize$0(com.myfitnesspal.app.MyFitnessPalApp):java.lang.String type: STATIC) in method: com.myfitnesspal.shared.uacf.-$$Lambda$UacfSharedLibrary$Fh014XELi3GU1ik4q-qQ8gCVCJ4.get():java.lang.String, dex: classes4.dex
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
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r0v1 java.lang.String) = (wrap: com.myfitnesspal.app.MyFitnessPalApp
                      0x0000: IGET  (r0v0 com.myfitnesspal.app.MyFitnessPalApp) = (r1v0 'this' com.myfitnesspal.shared.uacf.-$$Lambda$UacfSharedLibrary$Fh014XELi3GU1ik4q-qQ8gCVCJ4 A[THIS]) com.myfitnesspal.shared.uacf.-$$Lambda$UacfSharedLibrary$Fh014XELi3GU1ik4q-qQ8gCVCJ4.f$0 com.myfitnesspal.app.MyFitnessPalApp) com.myfitnesspal.shared.uacf.UacfSharedLibrary.lambda$initialize$0(com.myfitnesspal.app.MyFitnessPalApp):java.lang.String type: STATIC in method: com.myfitnesspal.shared.uacf.-$$Lambda$UacfSharedLibrary$Fh014XELi3GU1ik4q-qQ8gCVCJ4.get():java.lang.String, dex: classes4.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 33 more
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
                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:350)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	... 36 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 57 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.app.MyFitnessPalApp r0 = com.myfitnesspal.app.MyFitnessPalApp.this
                    java.lang.String r0 = 
                    // error: 0x0002: INVOKE  (r0 I:java.lang.String) = (r0 I:com.myfitnesspal.app.MyFitnessPalApp) com.myfitnesspal.shared.uacf.UacfSharedLibrary.lambda$initialize$0(com.myfitnesspal.app.MyFitnessPalApp):java.lang.String type: STATIC
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.uacf.$$Lambda$UacfSharedLibrary$Fh014XELi3GU1ik4qqQ8gCVCJ4.get():java.lang.String");
            }
        };
        UacfIdentitySdkFactory.configure(myFitnessPalApp, UacfAppId.MYFITNESSPAL, appVersionName, new UacfApiEnvironmentProvider() {
            public final UacfApiEnvironment get() {
                return SSO.getEnvironment(MyFitnessPalApp.this.component().mfpApiSettings());
            }
        }, clientEventsCallback);
        UacfAuthProvider uacfAuthProvider = getUacfAuthProvider(myFitnessPalApp);
        MyFitnessPalApp myFitnessPalApp2 = myFitnessPalApp;
        String str = appVersionName;
        UacfNotificationSdkFactory.configure(myFitnessPalApp2, UacfAppId.MYFITNESSPAL, str, uacfAuthProvider, new UacfApiEnvironmentProvider() {
            public final UacfApiEnvironment get() {
                return UacfSharedLibEnvironment.getNISEnvironment(MyFitnessPalApp.this.component().mfpApiSettings());
            }
        }, clientEventsCallback);
        UacfClientEventsSdkFactory.configure(myFitnessPalApp2, UacfAppId.MYFITNESSPAL, str, r7, uacfAuthProvider, new UacfApiEnvironmentProvider() {
            public final UacfApiEnvironment get() {
                return SSO.getEnvironment(MyFitnessPalApp.this.component().mfpApiSettings());
            }
        });
        UacfSyncFactory.configure(myFitnessPalApp, UacfAppId.MYFITNESSPAL, appVersionName, uacfAuthProvider, new UacfApiEnvironmentProvider() {
            public final UacfApiEnvironment get() {
                return UacfSharedLibEnvironment.getSyncV2Environment(MyFitnessPalApp.this.component().mfpApiSettings());
            }
        });
        new Builder(myFitnessPalApp).withAppId(UacfAppId.MYFITNESSPAL).withAppVersion(appVersionName).withConsentsAppDomain(UacfConsentsAppDomain.MFP).withAuthProvider(uacfAuthProvider).withDeviceIdProvider(r7).withDomain(UacfUserAccountDomain.MFP).withEnvironmentProvider(new UacfApiEnvironmentProvider() {
            public final UacfApiEnvironment get() {
                return UacfSharedLibEnvironment.getConsentsEnvironment(MyFitnessPalApp.this.component().mfpApiSettings());
            }
        }).build();
        MyFitnessPalApp myFitnessPalApp3 = myFitnessPalApp;
        UacfConfigurationSdkFactory.configure(myFitnessPalApp3, UacfAppId.MYFITNESSPAL, str, uacfAuthProvider, new UacfApiEnvironmentProvider() {
            public final UacfApiEnvironment get() {
                return UacfSharedLibEnvironment.getConfigurationEnvironment(MyFitnessPalApp.this.component().mfpApiSettings());
            }
        }, clientEventsCallback);
        UacfVariantSdkFactory.configure(myFitnessPalApp3, UacfAppId.MYFITNESSPAL, str, r7, uacfAuthProvider, UacfUserAccountDomain.MFP, new UacfApiEnvironmentProvider() {
            public final UacfApiEnvironment get() {
                return UacfSharedLibEnvironment.getRolloutsEnvironment(MyFitnessPalApp.this.component().mfpApiSettings());
            }
        }, clientEventsCallback, null);
        UacfThumbprintUiSdkManager.initializeSdk(new UacfThumbprintUiSdkInitParams.Builder().setIdentitySdk(SSO.getSdk()).setClientEventsCallback(new UacfThumbprintAnalyticsCallback()).build());
    }

    private static UacfAuthProvider getUacfAuthProvider(final MyFitnessPalApp myFitnessPalApp) {
        return new UacfAuthProvider() {
            private AuthTokenProvider authTokenProvider;

            public boolean isValidLoginSession() {
                return getAuthTokenProvider().isValidLoginSession();
            }

            public String getClientToken() {
                return getAuthTokenProvider().getClientToken();
            }

            public String getUacfUserId() {
                return getAuthTokenProvider().getUacfUserId();
            }

            public Long getLongUacfUserId() {
                return getAuthTokenProvider().getLongUacfUserId();
            }

            public String getDomainUserId() {
                return getAuthTokenProvider().getDomainUserId();
            }

            public UacfUserAccountDomain getDomain() {
                return getAuthTokenProvider().getDomain();
            }

            public String getUserLocale() {
                return getAuthTokenProvider().getUserLocale();
            }

            public String getAccessToken() {
                return getAuthTokenProvider().getAccessToken();
            }

            public String getRefreshToken() {
                return getAuthTokenProvider().getRefreshToken();
            }

            private AuthTokenProvider getAuthTokenProvider() {
                if (this.authTokenProvider == null) {
                    this.authTokenProvider = myFitnessPalApp.component().authTokenProvider();
                }
                return this.authTokenProvider;
            }
        };
    }
}
