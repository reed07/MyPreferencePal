package com.myfitnesspal.feature.settings.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import butterknife.BindView;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.settings.util.DiarySharingSettingsManager;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Facebook.Login;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Facebook;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.facebook.FacebookService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.impl.DisconnectFacebook;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.OrientationUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class FacebookSettings extends MfpActivity {
    private static final int CONNECT = 1;
    private static final int DISCONNECT = 0;
    private CallbackManager callbackManager;
    @BindView(2131362140)
    CheckedTextView chkFindMe;
    @BindView(2131362199)
    View connectFacebookButton;
    @Inject
    DiarySharingSettingsManager diarySharingSettingsManager;
    @BindView(2131362336)
    View disconnectFacebookButton;
    @Inject
    Lazy<FacebookService> facebookService;
    @Inject
    Lazy<LoginModel> loginModel;
    @BindView(2131363314)
    View progress;

    public String getAnalyticsScreenTag() {
        return Screens.FACEBOOK_SETTINGS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, FacebookSettings.class);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        this.callbackManager = Factory.create();
        setContentView((int) R.layout.facebook_settings);
        setTitle(R.string.facebook_settings);
        initializeUI();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.callbackManager.onActivityResult(i, i2, intent);
        if (i == 20) {
            refresh();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        scheduleSync();
    }

    private void initializeUI() {
        refresh();
        this.chkFindMe.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FacebookSettings.this.updateUserProperty(Facebook.FIND_BY_FACEBOOK_ENABLED, FacebookSettings.this.toggleCheckedTextView(view));
            }
        });
        this.disconnectFacebookButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.settings.ui.activity.FacebookSettings
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.settings.ui.activity.FacebookSettings) = (r1v0 'this' com.myfitnesspal.feature.settings.ui.activity.-$$Lambda$FacebookSettings$giktTM5vKZcJ2VzKRcLcE4kZLCY A[THIS]) com.myfitnesspal.feature.settings.ui.activity.-$$Lambda$FacebookSettings$giktTM5vKZcJ2VzKRcLcE4kZLCY.f$0 com.myfitnesspal.feature.settings.ui.activity.FacebookSettings), (r2v0 'view' android.view.View) com.myfitnesspal.feature.settings.ui.activity.FacebookSettings.lambda$initializeUI$2(com.myfitnesspal.feature.settings.ui.activity.FacebookSettings, android.view.View):void type: STATIC in method: com.myfitnesspal.feature.settings.ui.activity.-$$Lambda$FacebookSettings$giktTM5vKZcJ2VzKRcLcE4kZLCY.onClick(android.view.View):void, dex: classes4.dex
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
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 55 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.settings.ui.activity.FacebookSettings r0 = com.myfitnesspal.feature.settings.ui.activity.FacebookSettings.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.settings.ui.activity.FacebookSettings), (r2 I:android.view.View) com.myfitnesspal.feature.settings.ui.activity.FacebookSettings.lambda$initializeUI$2(com.myfitnesspal.feature.settings.ui.activity.FacebookSettings, android.view.View):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.ui.activity.$$Lambda$FacebookSettings$giktTM5vKZcJ2VzKRcLcE4kZLCY.onClick(android.view.View):void");
            }
        });
        this.connectFacebookButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FacebookSettings.this.invokeFacebookLogin();
            }
        });
    }

    /* access modifiers changed from: private */
    public void refresh() {
        User user = getSession().getUser();
        boolean isValid = ((LoginModel) this.loginModel.get()).getFacebookData().isValid();
        boolean requiresReconnect = ((FacebookService) this.facebookService.get()).requiresReconnect();
        boolean z = false;
        boolean z2 = isValid && requiresReconnect;
        boolean z3 = isValid && !requiresReconnect;
        setDisplayedButton((!z2 && isValid) ? 0 : 1);
        this.chkFindMe.setEnabled(z3);
        CheckedTextView checkedTextView = this.chkFindMe;
        if (z3 && user.allowFacebookFriendsToFindMe()) {
            z = true;
        }
        checkedTextView.setChecked(z);
    }

    private void setDisplayedButton(int i) {
        ViewUtils.setVisible(i == 0, this.disconnectFacebookButton);
        ViewUtils.setVisible(i == 1, this.connectFacebookButton);
    }

    /* access modifiers changed from: private */
    public void updateUserProperty(String str, boolean z) {
        Ln.d("Updating user property %s to %s", str, Strings.toString(Boolean.valueOf(z)));
        User user = getSession().getUser();
        user.setProperty(str, Strings.toString(Boolean.valueOf(z)));
        user.updatePropertyNamed(str);
    }

    private boolean toggleCheckedTextView(View view) {
        CheckedTextView checkedTextView = (CheckedTextView) view;
        checkedTextView.toggle();
        return checkedTextView.isChecked();
    }

    /* access modifiers changed from: private */
    public void connectFacebook() {
        ViewUtils.setVisible(true, this.progress);
        ((FacebookService) this.facebookService.get()).connect(this, new Function1<String>() {
            public void execute(String str) {
                FacebookSettings.this.refresh();
                ViewUtils.setVisible(false, FacebookSettings.this.progress);
            }
        }, new Function2() {
            public final void execute(Object obj, Object obj2) {
                FacebookSettings.lambda$connectFacebook$4(FacebookSettings.this, (Integer) obj, (String) obj2);
            }
        });
    }

    public static /* synthetic */ void lambda$connectFacebook$4(FacebookSettings facebookSettings, Integer num, String str) throws RuntimeException {
        String str2;
        if (num.intValue() != 2000) {
            if (!Strings.notEmpty(str)) {
                str = facebookSettings.getString(R.string.failAssociateFacebookUser);
            }
            str2 = facebookSettings.getString(R.string.dismiss);
        } else {
            str = facebookSettings.getString(R.string.underage_facebook_connect);
            str2 = facebookSettings.getString(R.string.ok);
        }
        ((LegacyAlertMixin) facebookSettings.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(facebookSettings.getString(R.string.error), str, str2);
        facebookSettings.refresh();
        ViewUtils.setVisible(false, facebookSettings.progress);
    }

    /* access modifiers changed from: private */
    public void disconnectFromFacebook() {
        ViewUtils.setVisible(true, this.progress);
        ((FacebookService) this.facebookService.get()).disconnect(new Function0() {
            public final void execute() {
                FacebookSettings.lambda$disconnectFromFacebook$5(FacebookSettings.this);
            }
        }, new Function2() {
            public final void execute(Object obj, Object obj2) {
                FacebookSettings.lambda$disconnectFromFacebook$6(FacebookSettings.this, (Integer) obj, (String) obj2);
            }
        });
    }

    public static /* synthetic */ void lambda$disconnectFromFacebook$5(FacebookSettings facebookSettings) throws RuntimeException {
        facebookSettings.refresh();
        ViewUtils.setVisible(false, facebookSettings.progress);
    }

    public static /* synthetic */ void lambda$disconnectFromFacebook$6(FacebookSettings facebookSettings, Integer num, String str) throws RuntimeException {
        if (num.intValue() == 256) {
            facebookSettings.getNavigationHelper().withContext(facebookSettings).withIntent(DisconnectFacebook.newStartIntent(facebookSettings)).startActivity(20);
        } else {
            Ln.d("FACEBOOK: NOT dissociated, code = %s, message = %s", num, str);
            if (!Strings.notEmpty(str)) {
                str = facebookSettings.getString(R.string.failDissociateFacebookUser);
            }
            ((LegacyAlertMixin) facebookSettings.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(facebookSettings.getString(R.string.request_failed), str, facebookSettings.getString(R.string.dismiss));
        }
        facebookSettings.refresh();
        ViewUtils.setVisible(false, facebookSettings.progress);
    }

    /* access modifiers changed from: private */
    public void invokeFacebookLogin() {
        LoginManager.getInstance().registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                Ln.v("invokeFacebookLogin success", new Object[0]);
                OrientationUtils.unlockOrientation(FacebookSettings.this.getActivity());
                FacebookSettings.this.connectFacebook();
            }

            public void onCancel() {
                Ln.v("invokeFacebookLogin cancelled", new Object[0]);
                OrientationUtils.unlockOrientation(FacebookSettings.this.getActivity());
                FacebookSettings.this.refresh();
                ViewUtils.setVisible(false, FacebookSettings.this.progress);
            }

            public void onError(FacebookException facebookException) {
                Ln.v("invokeFacebookLogin error", facebookException);
                OrientationUtils.unlockOrientation(FacebookSettings.this.getActivity());
                FacebookSettings.this.refresh();
                ViewUtils.setVisible(false, FacebookSettings.this.progress);
            }
        });
        OrientationUtils.lockOrientation(getActivity());
        LoginManager.getInstance().logInWithReadPermissions((Activity) this, Login.PERMISSIONS);
    }
}
