package com.myfitnesspal.feature.registration.service;

import android.os.Handler;
import android.os.Looper;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.registration.service.PrefetchService.OnCompletedListener;
import com.myfitnesspal.feature.registration.task.PrefetchTask.Result;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryListContainer;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.sync.engine.UacfScheduleProgressInfo;
import com.uacf.sync.engine.UacfSchedulerEngine.Callbacks;
import dagger.Lazy;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PrefetchServiceImpl implements PrefetchService {
    private static final int COMPLETE = 2;
    private static final int NOT_STARTED = 0;
    private static final int RUNNING = 1;
    private static final int SERVICE_FRIENDS = 1;
    private static final int SERVICE_NEWSFEED = 0;
    private static final int SERVICE_PRODUCT = 2;
    private static final int SERVICE_SYNC_INCREMENTAL = 3;
    private static final int SERVICE_SYNC_UACF_USER = 5;
    private static final int SERVICE_SYNC_USER = 4;
    private final Lazy<AppGalleryService> appGalleryService;
    private final Lazy<FriendService> friendService;
    private final Lazy<GeoLocationService> geoLocationService;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<NewsFeedService> newsFeedService;
    private OnCompletedListener onCompletedListener;
    private final Lazy<ProductService> productService;
    private int state = 0;
    private final Lazy<SubscriptionService> subscriptionService;
    private final Lazy<SyncService> syncService;
    /* access modifiers changed from: private */
    public final Lazy<SyncUtil> syncUtil;
    private Result taskResult;
    private final Lazy<UserApplicationSettingsService> userAppSettings;
    private final Set<Integer> waitingSet = new HashSet();

    public PrefetchServiceImpl(Lazy<LocalSettingsService> lazy, Lazy<NewsFeedService> lazy2, Lazy<FriendService> lazy3, Lazy<ProductService> lazy4, Lazy<GeoLocationService> lazy5, Lazy<SyncUtil> lazy6, Lazy<SyncService> lazy7, Lazy<SubscriptionService> lazy8, Lazy<AppGalleryService> lazy9, Lazy<UserApplicationSettingsService> lazy10) {
        this.localSettingsService = lazy;
        this.newsFeedService = lazy2;
        this.friendService = lazy3;
        this.productService = lazy4;
        this.geoLocationService = lazy5;
        this.syncUtil = lazy6;
        this.syncService = lazy7;
        this.subscriptionService = lazy8;
        this.appGalleryService = lazy9;
        this.userAppSettings = lazy10;
    }

    public boolean isRunning() {
        return this.state == 1;
    }

    public boolean isComplete() {
        return this.state == 2;
    }

    public void setTaskResult(Result result) {
        this.taskResult = result;
    }

    public Result getTaskResult() {
        return this.taskResult;
    }

    public void setOnCompletedListener(OnCompletedListener onCompletedListener2) {
        this.onCompletedListener = onCompletedListener2;
    }

    public void prefetch() {
        if (this.state != 1) {
            buildWaitingSet();
            this.state = 1;
            resetNewUserStatus();
            startWaitingServices();
            startNonWaitingServices();
            ((UserApplicationSettingsService) this.userAppSettings.get()).preCacheValues();
        }
    }

    private void buildWaitingSet() {
        this.waitingSet.add(Integer.valueOf(0));
        this.waitingSet.add(Integer.valueOf(1));
        this.waitingSet.add(Integer.valueOf(2));
        SyncUtil syncUtil2 = (SyncUtil) this.syncUtil.get();
        boolean hasInitialSyncV2Completed = syncUtil2.hasInitialSyncV2Completed();
        boolean userRefreshRequired = syncUtil2.userRefreshRequired();
        if (!hasInitialSyncV2Completed || userRefreshRequired) {
            this.waitingSet.add(Integer.valueOf(hasInitialSyncV2Completed ? 4 : 3));
        }
        this.waitingSet.add(Integer.valueOf(5));
    }

    private void startWaitingServices() {
        if (this.waitingSet.contains(Integer.valueOf(0))) {
            loadNewsFeed();
        }
        if (this.waitingSet.contains(Integer.valueOf(1))) {
            loadFriends();
        }
        if (this.waitingSet.contains(Integer.valueOf(2))) {
            loadPremium();
        }
        if (this.waitingSet.contains(Integer.valueOf(3))) {
            loadSyncIncremental();
        }
        if (this.waitingSet.contains(Integer.valueOf(4))) {
            loadSyncUser();
        }
        if (this.waitingSet.contains(Integer.valueOf(5))) {
            loadSyncUacfUser();
        }
    }

    private void startNonWaitingServices() {
        loadSubscriptions();
        loadPartnerGallery();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        com.uacf.core.util.Ln.d("onServiceReturned. waiting for %d more", java.lang.Integer.valueOf(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        if (r5 != 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        notifyCompletedSuccessfully();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServiceReturned(int r5) {
        /*
            r4 = this;
            int r0 = r4.state
            r1 = 1
            if (r0 == r1) goto L_0x0006
            return
        L_0x0006:
            java.util.Set<java.lang.Integer> r0 = r4.waitingSet
            monitor-enter(r0)
            java.util.Set<java.lang.Integer> r2 = r4.waitingSet     // Catch:{ all -> 0x003b }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x003b }
            boolean r2 = r2.contains(r3)     // Catch:{ all -> 0x003b }
            if (r2 != 0) goto L_0x0017
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x0017:
            java.util.Set<java.lang.Integer> r2 = r4.waitingSet     // Catch:{ all -> 0x003b }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x003b }
            r2.remove(r5)     // Catch:{ all -> 0x003b }
            java.util.Set<java.lang.Integer> r5 = r4.waitingSet     // Catch:{ all -> 0x003b }
            int r5 = r5.size()     // Catch:{ all -> 0x003b }
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            java.lang.String r0 = "onServiceReturned. waiting for %d more"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r1[r2] = r3
            com.uacf.core.util.Ln.d(r0, r1)
            if (r5 != 0) goto L_0x003a
            r4.notifyCompletedSuccessfully()
        L_0x003a:
            return
        L_0x003b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.registration.service.PrefetchServiceImpl.onServiceReturned(int):void");
    }

    private void resetNewUserStatus() {
        ((LocalSettingsService) this.localSettingsService.get()).setIsNewUser(false);
    }

    private void loadNewsFeed() {
        Ln.d("prefetching newsfeed", new Object[0]);
        MfpNewsFeedActivityEntryListContainer cachedFeed = ((NewsFeedService) this.newsFeedService.get()).getCachedFeed(Uri.ACTIVITY_TIMELINE);
        if (cachedFeed == null || !CollectionUtils.notEmpty((Collection<?>) cachedFeed.getEntries())) {
            ((NewsFeedService) this.newsFeedService.get()).fetchFeedV2Async(Uri.ACTIVITY_TIMELINE, 20, new Function1() {
                public final void execute(Object obj) {
                    PrefetchServiceImpl.lambda$loadNewsFeed$0(PrefetchServiceImpl.this, (MfpNewsFeedActivityEntryListContainer) obj);
                }
            }, $$Lambda$PrefetchServiceImpl$OfceVUljDpcDBs8sepoMEzuCHM.INSTANCE);
        } else {
            Ln.d("newsfeed success", new Object[0]);
        }
        onServiceReturned(0);
    }

    public static /* synthetic */ void lambda$loadNewsFeed$0(PrefetchServiceImpl prefetchServiceImpl, MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer) throws RuntimeException {
        Ln.d("newsfeed success", new Object[0]);
        ((NewsFeedService) prefetchServiceImpl.newsFeedService.get()).putCachedFeed(Uri.ACTIVITY_TIMELINE, mfpNewsFeedActivityEntryListContainer);
    }

    private void loadFriends() {
        Ln.d("prefetching friends", new Object[0]);
        ((FriendService) this.friendService.get()).fetchFriends($$Lambda$PrefetchServiceImpl$UpbCZ9VWiWgPOIhh08yWauMpYwM.INSTANCE, $$Lambda$PrefetchServiceImpl$V7LyqcSpVsnb_kAWw22j0RpuKFo.INSTANCE);
        onServiceReturned(1);
    }

    private void loadPremium() {
        ((GeoLocationService) this.geoLocationService.get()).refresh(new Function0() {
            public final void execute() {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.feature.registration.service.PrefetchServiceImpl
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.registration.service.PrefetchServiceImpl) = (r1v0 'this' com.myfitnesspal.feature.registration.service.-$$Lambda$PrefetchServiceImpl$aZE_KjUU_UlEkMCdMEV2CS9irDg A[THIS]) com.myfitnesspal.feature.registration.service.-$$Lambda$PrefetchServiceImpl$aZE_KjUU_UlEkMCdMEV2CS9irDg.f$0 com.myfitnesspal.feature.registration.service.PrefetchServiceImpl) com.myfitnesspal.feature.registration.service.PrefetchServiceImpl.lambda$loadPremium$5(com.myfitnesspal.feature.registration.service.PrefetchServiceImpl):void type: STATIC in method: com.myfitnesspal.feature.registration.service.-$$Lambda$PrefetchServiceImpl$aZE_KjUU_UlEkMCdMEV2CS9irDg.execute():void, dex: classes3.dex
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
                    com.myfitnesspal.feature.registration.service.PrefetchServiceImpl r0 = com.myfitnesspal.feature.registration.service.PrefetchServiceImpl.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.feature.registration.service.PrefetchServiceImpl) com.myfitnesspal.feature.registration.service.PrefetchServiceImpl.lambda$loadPremium$5(com.myfitnesspal.feature.registration.service.PrefetchServiceImpl):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.registration.service.$$Lambda$PrefetchServiceImpl$aZE_KjUU_UlEkMCdMEV2CS9irDg.execute():void");
            }
        });
    }

    private void loadPartnerGallery() {
        ((AppGalleryService) this.appGalleryService.get()).getAppListAsync("all", null, null);
    }

    private void loadSyncIncremental() {
        ((SyncService) this.syncService.get()).enqueue(SyncType.Incremental, new Callbacks<SyncType>() {
            public void onProgress(UacfScheduleProgressInfo<SyncType> uacfScheduleProgressInfo) {
            }

            public void onCompleted(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
                if (uacfScheduleFinishedInfo.isSuccessful() || PrefetchServiceImpl.this.failedBecauseOfASyncNoNetworkError(uacfScheduleFinishedInfo)) {
                    PrefetchServiceImpl.this.onServiceReturned(3);
                } else {
                    PrefetchServiceImpl.this.notifyRequiredOperationFailed();
                }
            }
        });
    }

    private void loadSyncUser() {
        ((SyncService) this.syncService.get()).enqueue(SyncType.User, new Callbacks<SyncType>() {
            public void onProgress(UacfScheduleProgressInfo<SyncType> uacfScheduleProgressInfo) {
            }

            public void onCompleted(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
                if (uacfScheduleFinishedInfo.isSuccessful() || PrefetchServiceImpl.this.failedBecauseOfASyncNoNetworkError(uacfScheduleFinishedInfo)) {
                    if (uacfScheduleFinishedInfo.isSuccessful()) {
                        ((SyncUtil) PrefetchServiceImpl.this.syncUtil.get()).setUserRefreshed();
                    }
                    PrefetchServiceImpl.this.onServiceReturned(4);
                    return;
                }
                PrefetchServiceImpl.this.notifyRequiredOperationFailed();
            }
        });
    }

    private void loadSyncUacfUser() {
        ((SyncService) this.syncService.get()).enqueue(SyncType.UacfUser, new Callbacks<SyncType>() {
            public void onProgress(UacfScheduleProgressInfo<SyncType> uacfScheduleProgressInfo) {
            }

            public void onCompleted(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
                if (uacfScheduleFinishedInfo.isSuccessful() || PrefetchServiceImpl.this.failedBecauseOfASyncNoNetworkError(uacfScheduleFinishedInfo)) {
                    PrefetchServiceImpl.this.onServiceReturned(5);
                } else {
                    PrefetchServiceImpl.this.notifyRequiredOperationFailed();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean failedBecauseOfASyncNoNetworkError(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
        return !uacfScheduleFinishedInfo.isSuccessful() && uacfScheduleFinishedInfo.getLastError() != null && uacfScheduleFinishedInfo.getLastError().getCause() != null && (uacfScheduleFinishedInfo.getLastError().getCause().getCause() instanceof UnknownHostException);
    }

    private void loadSubscriptions() {
        ((SubscriptionService) this.subscriptionService.get()).pullSubscriptionsFromBackend();
    }

    private void notifyCompletedSuccessfully() {
        this.handler.post(new Runnable() {
            public final void run() {
                PrefetchServiceImpl.lambda$notifyCompletedSuccessfully$6(PrefetchServiceImpl.this);
            }
        });
    }

    public static /* synthetic */ void lambda$notifyCompletedSuccessfully$6(PrefetchServiceImpl prefetchServiceImpl) {
        prefetchServiceImpl.state = 2;
        OnCompletedListener onCompletedListener2 = prefetchServiceImpl.onCompletedListener;
        if (onCompletedListener2 != null) {
            onCompletedListener2.onCompletedSuccessfully();
        }
    }

    /* access modifiers changed from: private */
    public void notifyRequiredOperationFailed() {
        this.handler.post(new Runnable() {
            public final void run() {
                PrefetchServiceImpl.lambda$notifyRequiredOperationFailed$7(PrefetchServiceImpl.this);
            }
        });
    }

    public static /* synthetic */ void lambda$notifyRequiredOperationFailed$7(PrefetchServiceImpl prefetchServiceImpl) {
        prefetchServiceImpl.state = 2;
        OnCompletedListener onCompletedListener2 = prefetchServiceImpl.onCompletedListener;
        if (onCompletedListener2 != null) {
            onCompletedListener2.onRequiredOperationFailed();
        }
    }
}
