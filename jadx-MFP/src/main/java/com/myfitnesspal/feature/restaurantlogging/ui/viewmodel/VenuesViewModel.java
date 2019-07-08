package com.myfitnesspal.feature.restaurantlogging.ui.viewmodel;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.model.VenuesRequestData;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.feature.restaurantlogging.task.GetVenuesTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.uacf.core.util.Ln;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import java.util.List;

public class VenuesViewModel extends RunnerViewModel<VenuesRequestData> {
    private long currentTaskId = -1;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private GoogleMap googleMap;
    private final Lazy<VenueService> venueService;
    private List<Venue> venues;

    public interface Property extends com.myfitnesspal.feature.appgallery.model.ViewModelWithCtaButton.Property {
        public static final int MAP_ENABLED = ViewModelPropertyId.next();
        public static final int VENUES_LIST_FETCHED = ViewModelPropertyId.next();
        public static final int VENUES_LIST_FETCH_FAILED = ViewModelPropertyId.next();
    }

    public VenuesViewModel(Runner runner, Lazy<VenueService> lazy) {
        super(runner);
        this.venueService = lazy;
    }

    public void load(VenuesRequestData... venuesRequestDataArr) {
        if (this.currentTaskId != -1) {
            getRunner().cancel(this.currentTaskId);
        }
        this.currentTaskId = new GetVenuesTask(this.venueService, venuesRequestDataArr[0]).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.matches(getRunner(), GetVenuesTask.class)) {
            return;
        }
        if (!taskDetails.successful()) {
            setError(taskDetails.getFailure());
            notifyPropertyChanged(Property.VENUES_LIST_FETCH_FAILED);
            return;
        }
        this.venues = (List) taskDetails.getResult();
        setState(State.Loaded);
        notifyPropertyChanged(Property.VENUES_LIST_FETCHED);
    }

    private Observable<GoogleMap> getMapObservable(MapView mapView) {
        return Observable.create(new ObservableOnSubscribe() {
            public final void subscribe(ObservableEmitter observableEmitter) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.google.android.gms.maps.MapView
                      0x0000: IGET  (r0v0 com.google.android.gms.maps.MapView) = (r1v0 'this' com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.-$$Lambda$VenuesViewModel$2eAFzcXzsqFOqxN-IXxVWfetZBQ A[THIS]) com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.-$$Lambda$VenuesViewModel$2eAFzcXzsqFOqxN-IXxVWfetZBQ.f$0 com.google.android.gms.maps.MapView), (r2v0 'observableEmitter' io.reactivex.ObservableEmitter) com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.VenuesViewModel.lambda$getMapObservable$1(com.google.android.gms.maps.MapView, io.reactivex.ObservableEmitter):void type: STATIC in method: com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.-$$Lambda$VenuesViewModel$2eAFzcXzsqFOqxN-IXxVWfetZBQ.subscribe(io.reactivex.ObservableEmitter):void, dex: classes2.dex
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
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
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
                    com.google.android.gms.maps.MapView r0 = com.google.android.gms.maps.MapView.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.google.android.gms.maps.MapView), (r2 I:io.reactivex.ObservableEmitter) com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.VenuesViewModel.lambda$getMapObservable$1(com.google.android.gms.maps.MapView, io.reactivex.ObservableEmitter):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.restaurantlogging.ui.viewmodel.$$Lambda$VenuesViewModel$2eAFzcXzsqFOqxNIXxVWfetZBQ.subscribe(io.reactivex.ObservableEmitter):void");
            }
        });
    }

    static /* synthetic */ void lambda$null$0(ObservableEmitter observableEmitter, GoogleMap googleMap2) {
        if (!observableEmitter.isDisposed()) {
            observableEmitter.onNext(googleMap2);
        }
    }

    public void loadMap(MapView mapView) {
        this.disposables.add(getMapObservable(mapView).subscribe(new Consumer() {
            public final void accept(Object obj) {
                VenuesViewModel.lambda$loadMap$2(VenuesViewModel.this, (GoogleMap) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                VenuesViewModel.lambda$loadMap$3(VenuesViewModel.this, (Throwable) obj);
            }
        }));
    }

    public static /* synthetic */ void lambda$loadMap$2(VenuesViewModel venuesViewModel, GoogleMap googleMap2) throws Exception {
        venuesViewModel.googleMap = googleMap2;
        venuesViewModel.notifyPropertyChanged(Property.MAP_ENABLED);
    }

    public static /* synthetic */ void lambda$loadMap$3(VenuesViewModel venuesViewModel, Throwable th) throws Exception {
        Ln.e(th);
        venuesViewModel.notifyPropertyChanged(Property.MAP_ENABLED);
    }

    /* access modifiers changed from: protected */
    public void onDetach() {
        this.disposables.clear();
        super.onDetach();
    }

    public List<Venue> getVenues() {
        return this.venues;
    }

    public GoogleMap googleMap() {
        return this.googleMap;
    }
}
