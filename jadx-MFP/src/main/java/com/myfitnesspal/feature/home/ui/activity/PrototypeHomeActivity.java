package com.myfitnesspal.feature.home.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnContextClickListener;
import android.view.View.OnLongClickListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment;
import com.myfitnesspal.feature.explore.ui.fragment.PrototypeExploreFragment;
import com.myfitnesspal.feature.home.ui.fragment.HomeFragment;
import com.myfitnesspal.feature.profile.ui.fragment.ProfileFragment;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.BundleUtils;
import java.util.UUID;

public class PrototypeHomeActivity extends MfpActivity {
    public static final String EXTRA_FLOW_ID = "flow_id";
    private static final String EXTRA_VISIBLE_FRAGMENT_TAG = "extra_visible_fragment_tag";
    @BindView(2131362034)
    View buttonDiary;
    @BindView(2131362035)
    View buttonExplore;
    @BindView(2131362036)
    View buttonHome;
    @BindView(2131362037)
    View buttonMe;
    private int currentTabIndex = 0;
    private UserDiaryFragment diaryFragment;
    private PrototypeExploreFragment exploreFragment;
    private String flowId;
    private HomeFragment homeFragment;
    private ProfileFragment meFragment;
    private Fragment visibleFragment;

    private interface Order {
        public static final int DIARY = 1;
        public static final int EXPLORE = 3;
        public static final int HOME = 0;
        public static final int ME = 4;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, PrototypeHomeActivity.class).addFlags(603979776);
    }

    public static Intent newStartIntentWithFabShowing(Context context) {
        return newStartIntent(context).putExtra(HomeFragment.EXTRA_SHOW_FAB_ON_RESUME, true);
    }

    public static Intent newStartIntentDiaryAddDeeplinkSelectMeal(Context context) {
        return newStartIntent(context).putExtra(HomeFragment.EXTRA_SHOW_DIARY_ADD_DEEPLINK_SELECT_MEAL_ON_RESUME, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.home_activity_prototype);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(getActivity(), true);
        this.flowId = BundleUtils.getString(bundle, "flow_id", UUID.randomUUID().toString());
        init(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_VISIBLE_FRAGMENT_TAG, this.visibleFragment.getClass().getCanonicalName());
        bundle.putString("flow_id", this.flowId);
    }

    public boolean backPressed() {
        if (this.visibleFragment == this.homeFragment) {
            return false;
        }
        moveToHome();
        return true;
    }

    private void restoreFragments(Bundle bundle) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.homeFragment = (HomeFragment) supportFragmentManager.findFragmentByTag(tag(HomeFragment.class));
        this.diaryFragment = (UserDiaryFragment) supportFragmentManager.findFragmentByTag(tag(UserDiaryFragment.class));
        this.exploreFragment = (PrototypeExploreFragment) supportFragmentManager.findFragmentByTag(tag(PrototypeExploreFragment.class));
        this.meFragment = (ProfileFragment) supportFragmentManager.findFragmentByTag(tag(ProfileFragment.class));
        if (bundle != null) {
            this.visibleFragment = supportFragmentManager.findFragmentByTag(bundle.getString(EXTRA_VISIBLE_FRAGMENT_TAG, ""));
        }
    }

    private void init(Bundle bundle) {
        this.buttonHome.setTag(Integer.valueOf(0));
        this.buttonDiary.setTag(Integer.valueOf(1));
        this.buttonExplore.setTag(Integer.valueOf(3));
        this.buttonMe.setTag(Integer.valueOf(4));
        restoreFragments(bundle);
        if (this.visibleFragment == null) {
            moveToHome();
        }
        this.buttonHome.setOnLongClickListener(new OnLongClickListener() {
            public final boolean onLongClick(View view) {
                return 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                      0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity) = (r1v0 'this' com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$G-0DHOVEMKPuu4MLJyMMDdAJO5U A[THIS]) com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$G-0DHOVEMKPuu4MLJyMMDdAJO5U.f$0 com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity), (r2v0 'view' android.view.View) com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity.lambda$init$0(com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity, android.view.View):boolean type: STATIC) in method: com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$G-0DHOVEMKPuu4MLJyMMDdAJO5U.onLongClick(android.view.View):boolean, dex: classes3.dex
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
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity) = (r1v0 'this' com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$G-0DHOVEMKPuu4MLJyMMDdAJO5U A[THIS]) com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$G-0DHOVEMKPuu4MLJyMMDdAJO5U.f$0 com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity), (r2v0 'view' android.view.View) com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity.lambda$init$0(com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity, android.view.View):boolean type: STATIC in method: com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$G-0DHOVEMKPuu4MLJyMMDdAJO5U.onLongClick(android.view.View):boolean, dex: classes3.dex
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
                    	... 60 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity r0 = com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity.this
                    boolean r2 = 
                    // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity), (r2 I:android.view.View) com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity.lambda$init$0(com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity, android.view.View):boolean type: STATIC
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.home.ui.activity.$$Lambda$PrototypeHomeActivity$G0DHOVEMKPuu4MLJyMMDdAJO5U.onLongClick(android.view.View):boolean");
            }
        });
        if (VERSION.SDK_INT >= 23) {
            this.buttonHome.setOnContextClickListener(new OnContextClickListener() {
                public final boolean onContextClick(View view) {
                    return 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  (wrap: boolean
                          0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity) = (r1v0 'this' com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$qlGQNNV0bplPnW2tZmMPB3uemFY A[THIS]) com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$qlGQNNV0bplPnW2tZmMPB3uemFY.f$0 com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity), (r2v0 'view' android.view.View) com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity.lambda$init$1(com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity, android.view.View):boolean type: STATIC) in method: com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$qlGQNNV0bplPnW2tZmMPB3uemFY.onContextClick(android.view.View):boolean, dex: classes3.dex
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r2v1 boolean) = (wrap: com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity
                          0x0000: IGET  (r0v0 com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity) = (r1v0 'this' com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$qlGQNNV0bplPnW2tZmMPB3uemFY A[THIS]) com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$qlGQNNV0bplPnW2tZmMPB3uemFY.f$0 com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity), (r2v0 'view' android.view.View) com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity.lambda$init$1(com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity, android.view.View):boolean type: STATIC in method: com.myfitnesspal.feature.home.ui.activity.-$$Lambda$PrototypeHomeActivity$qlGQNNV0bplPnW2tZmMPB3uemFY.onContextClick(android.view.View):boolean, dex: classes3.dex
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
                        	... 65 more
                        */
                    /*
                        this = this;
                        com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity r0 = com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity.this
                        boolean r2 = 
                        // error: 0x0002: INVOKE  (r2 I:boolean) = (r0 I:com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity), (r2 I:android.view.View) com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity.lambda$init$1(com.myfitnesspal.feature.home.ui.activity.PrototypeHomeActivity, android.view.View):boolean type: STATIC
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.home.ui.activity.$$Lambda$PrototypeHomeActivity$qlGQNNV0bplPnW2tZmMPB3uemFY.onContextClick(android.view.View):boolean");
                }
            });
        }
        this.buttonHome.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                PrototypeHomeActivity.this.moveToHome();
            }
        });
        this.buttonDiary.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                PrototypeHomeActivity.this.moveToDiary();
            }
        });
        this.buttonExplore.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                PrototypeHomeActivity.this.moveToExplore();
            }
        });
        this.buttonMe.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                PrototypeHomeActivity.this.moveToMe();
            }
        });
    }

    /* access modifiers changed from: private */
    public void moveToExplore() {
        if (this.exploreFragment == null) {
            this.exploreFragment = PrototypeExploreFragment.newInstance();
        }
        navigateTo(this.exploreFragment, 3);
    }

    /* access modifiers changed from: private */
    public void moveToHome() {
        if (this.homeFragment == null) {
            this.homeFragment = HomeFragment.newInstance(this.flowId);
        }
        navigateTo(this.homeFragment, 0);
    }

    /* access modifiers changed from: private */
    public void moveToDiary() {
        if (this.diaryFragment == null) {
            this.diaryFragment = UserDiaryFragment.newInstance(null, -1, false, "");
        }
        navigateTo(this.diaryFragment, 1);
    }

    /* access modifiers changed from: private */
    public void moveToMe() {
        if (this.meFragment == null) {
            this.meFragment = ProfileFragment.newInstance(getSession().getUser().getUsername(), getSession().getUser().getUid(), null, false);
        }
        navigateTo(this.meFragment, 4);
    }

    private void navigateTo(MfpFragment mfpFragment, int i) {
        if (mfpFragment != null && this.visibleFragment != mfpFragment) {
            String tag = tag((Fragment) mfpFragment);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            if (this.visibleFragment != null) {
                if (this.currentTabIndex > i) {
                    beginTransaction.setCustomAnimations(R.anim.slide_in_left_100_medium, R.anim.slide_out_right_100_medium);
                } else {
                    beginTransaction.setCustomAnimations(R.anim.slide_in_right_100_short, R.anim.slide_out_left_100_medium);
                }
            }
            if (supportFragmentManager.findFragmentByTag(tag) == null) {
                beginTransaction.add(R.id.fragment_container, mfpFragment, tag);
            } else {
                beginTransaction.attach(mfpFragment);
            }
            Fragment fragment = this.visibleFragment;
            if (fragment != null) {
                beginTransaction.detach(fragment);
            }
            beginTransaction.show(mfpFragment);
            beginTransaction.commitAllowingStateLoss();
            this.currentTabIndex = i;
            this.visibleFragment = mfpFragment;
        }
    }
}
