package com.myfitnesspal.shared.ui.activity;

import android.content.Context;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity;
import com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity.Mode;
import com.myfitnesspal.feature.diary.event.ExerciseTypeEvent;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.dialog.ExerciseTypeDialogFragment;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.exercise.ui.activity.ExerciseSearchActivity;
import com.myfitnesspal.feature.friends.service.StatusAnalytics.Source;
import com.myfitnesspal.feature.friends.ui.activity.NewStatusOrCommentActivity;
import com.myfitnesspal.feature.home.util.HomeAnalyticsHelper;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.ui.activity.AddWeightActivity;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivity;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;
import com.myfitnesspal.shared.event.MealNameEvent;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu.OnItemClickListener;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu.OnStateChangeListener;
import com.uacf.floatingbuttonmenu.animation.FloatingButtonAnimationHandlerBase;
import com.uacf.floatingbuttonmenu.animation.TranslateAlphaFloatingButtonAnimationHandler.Builder;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

public final class FloatingButtonMixin extends RunnerLifecycleMixin {
    private MfpUiComponentInterface component;
    @Inject
    Lazy<ConfigService> configService;
    /* access modifiers changed from: private */
    public Context context;
    private OnItemClickListener defaultOnItemClickListener = new OnItemClickListener() {
        public void onItemClick(View view, int i) {
            if (FloatingButtonMixin.this.isEnabled()) {
                ((HomeAnalyticsHelper) FloatingButtonMixin.this.homeAnalyticsHelper.get()).reportFABOptionSelected(i);
                switch (i) {
                    case 0:
                        FloatingButtonMixin.this.navigationHelper.withNoAnimations().withIntent(AddWeightActivity.newStartIntent(FloatingButtonMixin.this.context, ProgressEntryPoint.Fab, ImportDevice.None)).fromFragment(FloatingButtonMixin.this.fragment).startActivity(25);
                        break;
                    case 1:
                        FloatingButtonMixin.this.onAddExerciseOptionClicked();
                        break;
                    case 2:
                        FloatingButtonMixin.this.onAddFoodOptionClicked();
                        break;
                    case 3:
                        FloatingButtonMixin.this.navigationHelper.withIntent(WaterEntryActivity.newStartIntent(FloatingButtonMixin.this.context, Mode.Add)).fromFragment(FloatingButtonMixin.this.fragment).startActivity(50);
                        ((WaterLoggingAnalyticsHelper) FloatingButtonMixin.this.waterLoggingAnalyticsHelper.get()).reportWaterScreenViewed("fab", "add");
                        break;
                    case 4:
                        FloatingButtonMixin.this.navigationHelper.withExtra(Extras.ITEM_TYPE, 17).withIntent(NewStatusOrCommentActivity.newStartIntent(FloatingButtonMixin.this.context, Source.Fab)).fromFragment(FloatingButtonMixin.this.fragment).startActivity(34);
                        break;
                }
            }
        }
    };
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<ExerciseAnalyticsHelper> exerciseAnalyticsHelper;
    private FloatingButtonMenu floatingButtonMenu;
    @Inject
    Lazy<FoodSearchActivityFactory> foodSearchRouter;
    /* access modifiers changed from: private */
    public MfpFragment fragment;
    private Handler handler = new Handler();
    private boolean hasCompletedFABSetup = false;
    @Inject
    Lazy<HomeAnalyticsHelper> homeAnalyticsHelper;
    private MfpFloatingButtonHost host;
    private Set<OnStateChangeListener> listeners = new HashSet();
    /* access modifiers changed from: private */
    public NavigationHelper navigationHelper;
    @Inject
    Lazy<UserWeightService> userWeightService;
    @Inject
    Lazy<WaterLoggingAnalyticsHelper> waterLoggingAnalyticsHelper;

    private static class BusEventHelper implements BusEventHandler {
        private final FloatingButtonMixin parent;

        BusEventHelper(FloatingButtonMixin floatingButtonMixin) {
            this.parent = floatingButtonMixin;
        }

        @Subscribe
        public void onMealNameEvent(MealNameEvent mealNameEvent) {
            this.parent.mealNameEvent(mealNameEvent);
        }

        @Subscribe
        public void onExerciseTypeEvent(ExerciseTypeEvent exerciseTypeEvent) {
            this.parent.exerciseTypeEvent(exerciseTypeEvent);
        }
    }

    public FloatingButtonMixin(MfpFloatingButtonActivity mfpFloatingButtonActivity) {
        super(mfpFloatingButtonActivity);
        this.context = mfpFloatingButtonActivity;
        this.host = mfpFloatingButtonActivity;
        this.component = mfpFloatingButtonActivity;
        this.navigationHelper = mfpFloatingButtonActivity.getNavigationHelper();
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public FloatingButtonMixin(MfpFloatingButtonFragment mfpFloatingButtonFragment) {
        super(mfpFloatingButtonFragment);
        this.context = mfpFloatingButtonFragment.getActivity();
        this.host = mfpFloatingButtonFragment;
        this.component = mfpFloatingButtonFragment;
        this.fragment = mfpFloatingButtonFragment;
        this.navigationHelper = mfpFloatingButtonFragment.getNavigationHelper();
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void onSetContentView() {
        show();
    }

    public void addStateChangedListener(OnStateChangeListener onStateChangeListener) {
        this.listeners.add(onStateChangeListener);
    }

    public void removeStateChangedListener(OnStateChangeListener onStateChangeListener) {
        this.listeners.remove(onStateChangeListener);
    }

    public void show() {
        if (!this.hasCompletedFABSetup) {
            ViewGroup floatingButtonParent = this.host.getFloatingButtonParent();
            if (floatingButtonParent != null) {
                this.floatingButtonMenu = (FloatingButtonMenu) LayoutInflater.from(this.context).inflate(R.layout.floating_button, floatingButtonParent, false);
                floatingButtonParent.addView(this.floatingButtonMenu);
                ArrayList arrayList = new ArrayList(6);
                arrayList.add(new Pair(this.context.getString(R.string.weight), Integer.valueOf(R.drawable.fab_active_btn_weight)));
                arrayList.add(new Pair(this.context.getString(R.string.exercise), Integer.valueOf(R.drawable.fab_active_btn_exercise)));
                arrayList.add(new Pair(this.context.getString(R.string.food), Integer.valueOf(R.drawable.fab_active_btn_food)));
                arrayList.add(new Pair(this.context.getString(R.string.water), Integer.valueOf(R.drawable.fab_active_btn_water)));
                arrayList.add(new Pair(this.context.getString(R.string.status), Integer.valueOf(R.drawable.fab_active_btn_status)));
                this.floatingButtonMenu.setOnStateChangeListener(new OnStateChangeListener() {
                    public final void onMenuStateChanged(int i) {
                        FloatingButtonMixin.lambda$show$0(FloatingButtonMixin.this, i);
                    }
                });
                show(this.floatingButtonMenu, this.defaultOnItemClickListener, arrayList);
                this.hasCompletedFABSetup = true;
            } else {
                return;
            }
        }
        this.floatingButtonMenu.post(new Runnable() {
            public final void run() {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.shared.ui.activity.FloatingButtonMixin
                      0x0000: IGET  (r0v0 com.myfitnesspal.shared.ui.activity.FloatingButtonMixin) = (r1v0 'this' com.myfitnesspal.shared.ui.activity.-$$Lambda$FloatingButtonMixin$UHFMynKQ9Whjv6TSmIxW8NfvC6s A[THIS]) com.myfitnesspal.shared.ui.activity.-$$Lambda$FloatingButtonMixin$UHFMynKQ9Whjv6TSmIxW8NfvC6s.f$0 com.myfitnesspal.shared.ui.activity.FloatingButtonMixin) com.myfitnesspal.shared.ui.activity.FloatingButtonMixin.lambda$show$1(com.myfitnesspal.shared.ui.activity.FloatingButtonMixin):void type: STATIC in method: com.myfitnesspal.shared.ui.activity.-$$Lambda$FloatingButtonMixin$UHFMynKQ9Whjv6TSmIxW8NfvC6s.run():void, dex: classes4.dex
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
                    com.myfitnesspal.shared.ui.activity.FloatingButtonMixin r0 = com.myfitnesspal.shared.ui.activity.FloatingButtonMixin.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.shared.ui.activity.FloatingButtonMixin) com.myfitnesspal.shared.ui.activity.FloatingButtonMixin.lambda$show$1(com.myfitnesspal.shared.ui.activity.FloatingButtonMixin):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.activity.$$Lambda$FloatingButtonMixin$UHFMynKQ9Whjv6TSmIxW8NfvC6s.run():void");
            }
        });
    }

    public static /* synthetic */ void lambda$show$0(FloatingButtonMixin floatingButtonMixin, int i) {
        for (OnStateChangeListener onMenuStateChanged : floatingButtonMixin.listeners) {
            onMenuStateChanged.onMenuStateChanged(i);
        }
    }

    public void hide() {
        ViewUtils.setVisible(false, this.floatingButtonMenu);
    }

    public void open() {
        open(0);
    }

    public void open(int i) {
        if (i == 0) {
            this.floatingButtonMenu.open();
        } else {
            this.handler.postDelayed(new Runnable() {
                public final void run() {
                    FloatingButtonMixin.lambda$open$2(FloatingButtonMixin.this);
                }
            }, (long) i);
        }
    }

    public static /* synthetic */ void lambda$open$2(FloatingButtonMixin floatingButtonMixin) {
        if (floatingButtonMixin.isEnabled()) {
            floatingButtonMixin.floatingButtonMenu.open();
        }
    }

    public boolean isOpen() {
        return this.floatingButtonMenu.isExpanded();
    }

    public void close() {
        close(0);
    }

    public void close(int i) {
        if (i == 0) {
            this.floatingButtonMenu.close();
        } else {
            this.handler.postDelayed(new Runnable() {
                public final void run() {
                    FloatingButtonMixin.lambda$close$3(FloatingButtonMixin.this);
                }
            }, (long) i);
        }
    }

    public static /* synthetic */ void lambda$close$3(FloatingButtonMixin floatingButtonMixin) {
        if (floatingButtonMixin.isEnabled()) {
            floatingButtonMixin.floatingButtonMenu.close();
        }
    }

    public void addBusEventHandlers(List<BusEventHandler> list) {
        list.add(new BusEventHelper(this));
    }

    private void show(FloatingButtonMenu floatingButtonMenu2, OnItemClickListener onItemClickListener, List<Pair<String, Integer>> list) {
        this.host.showFloatingButton(floatingButtonMenu2, onItemClickListener, list);
    }

    /* access modifiers changed from: private */
    public boolean isEnabled() {
        return this.component.isEnabled();
    }

    /* access modifiers changed from: private */
    public void onAddFoodOptionClicked() {
        ((DiaryService) this.diaryService.get()).startLoggingFlow("fab");
        this.host.showDialogFragment(MealNamesDialogFragment.newInstance(), Fragments.MEAL_NAMES_DIALOG);
    }

    /* access modifiers changed from: private */
    public void onAddExerciseOptionClicked() {
        ((ExerciseAnalyticsHelper) this.exerciseAnalyticsHelper.get()).reportAddExerciseTapped("fab");
        this.host.showDialogFragment(new ExerciseTypeDialogFragment(), Fragments.EXERCISE_TYPE_DIALOG);
    }

    private void navigateToFoodSearchView(String str) {
        this.navigationHelper.withExtra(Extras.ACTIVE_TAB, (int) SearchTabs.TAB_FREQUENT_FOODS);
        if (!Strings.isEmpty(str)) {
            this.navigationHelper.withExtra(Extras.MEAL_NAME, str);
        }
        this.navigationHelper.withIntent(((FoodSearchActivityFactory) this.foodSearchRouter.get()).getFoodSearchActivityIntent(this.context, new FoodSearchActivity.Extras().setMealName(str))).fromFragment(this.fragment).startActivity(49);
    }

    /* access modifiers changed from: private */
    public void mealNameEvent(MealNameEvent mealNameEvent) {
        navigateToFoodSearchView(mealNameEvent.getMealName());
    }

    /* access modifiers changed from: private */
    public void exerciseTypeEvent(ExerciseTypeEvent exerciseTypeEvent) {
        int exerciseType = exerciseTypeEvent.getExerciseType();
        ((ExerciseAnalyticsHelper) this.exerciseAnalyticsHelper.get()).reportAddExerciseScreenDisplayed("fab", exerciseType);
        this.navigationHelper.withExtra(Extras.ACTIVE_BUTTON, 6005).withExtra(Extras.IS_VIEWING_MULTI_ADD_ITEMS, false).withIntent(ExerciseSearchActivity.newStartIntentForExerciseType(this.context, exerciseType)).fromFragment(this.fragment).startActivity(exerciseType == 0 ? 47 : 48);
    }

    public static FloatingButtonAnimationHandlerBase getDefaultAnimator(FloatingButtonMenu floatingButtonMenu2) {
        return ((Builder) ((Builder) ((Builder) ((Builder) new Builder(floatingButtonMenu2).setDuration(250)).setStartOffsetBetweenEachChild(0)).setOpenInterpolator(new OvershootInterpolator(1.5f))).setCloseInterpolator(new AnticipateInterpolator(1.5f))).build();
    }

    public static void initDefaultMenu(Context context2, FloatingButtonMenu floatingButtonMenu2, List<Pair<String, Integer>> list) {
        LayoutInflater from = LayoutInflater.from(context2);
        for (Pair pair : list) {
            String str = (String) pair.first;
            ImageView imageView = (ImageView) from.inflate(R.layout.floating_button_options, floatingButtonMenu2, false);
            imageView.setImageResource(((Integer) pair.second).intValue());
            floatingButtonMenu2.addItem(imageView, str);
        }
    }
}
