package com.myfitnesspal.shared.service.syncv2;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.shared.constants.SyncResourceName;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.ExerciseEntryPropertiesTable;
import com.myfitnesspal.shared.db.table.StepsTable;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.sync.provider.sdk.model.SyncMode;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class SyncUtilImpl implements SyncUtil {
    private static final String ALWAYS_ENABLED = "always_enabled";
    private static final String FINAL_SYNC_STATE_PREFIX = "final_sync_state_";
    private static final String LAST_REFRESHED_VERSION = "last_refreshed_version";
    private static final String ROLLOUT_PREFIX = "sync-v2-android-";
    private static final String SHARED_PREFS_EXERCISE_DATA_MIGRATION_OCCURRED = "sync-v2-exercise-data-migration-occurred";
    private static final String SHARED_PREFS_SYNC_V2_COMPLETED = "sync-v2-completed";
    private static final Map<String, String> mapOfResourceNamesForExclusionToRolloutName = new Builder().put("exercise", "sync-v2-android-2015-11-exercises").put("exercise_entry", "sync-v2-android-2015-11-exercises").put("image", ALWAYS_ENABLED).put(SyncResourceName.IMAGE_ASSOCIATION, ALWAYS_ENABLED).build();
    private final Lazy<ConfigService> configService;
    private final Context context;
    private final Lazy<SQLiteDatabaseWrapper> lazyDatabase;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<ExerciseEntryMapper> mapper;
    private final KeyedSharedPreferences prefs;
    private final Lazy<Session> session;
    private final Lazy<SQLiteDatabaseWrapper> stockDatabase;

    public SyncUtilImpl(Context context2, Lazy<ConfigService> lazy, KeyedSharedPreferences keyedSharedPreferences, Lazy<SQLiteDatabaseWrapper> lazy2, Lazy<SQLiteDatabaseWrapper> lazy3, Lazy<ExerciseEntryMapper> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<Session> lazy6) {
        this.stockDatabase = lazy3;
        this.context = context2.getApplicationContext();
        this.configService = lazy;
        this.prefs = keyedSharedPreferences;
        this.lazyDatabase = lazy2;
        this.mapper = lazy4;
        this.localSettingsService = lazy5;
        this.session = lazy6;
    }

    public boolean hasInitialSyncV2Completed() {
        return this.prefs.getBoolean(SHARED_PREFS_SYNC_V2_COMPLETED, false);
    }

    public void setInitialSyncV2Completed(boolean z) {
        this.prefs.edit().putBoolean(SHARED_PREFS_SYNC_V2_COMPLETED, z).apply();
    }

    public String getRolloutForResourceName(String str) {
        return (String) mapOfResourceNamesForExclusionToRolloutName.get(str);
    }

    public Collection<String> getAllSyncV2EnabledResources() {
        HashSet hashSet = new HashSet();
        hashSet.add("nutrient_goal");
        hashSet.add(SyncResourceName.PAID_SUBSCRIPTION);
        hashSet.add("user");
        hashSet.add("user_application_settings");
        Collection syncV2ResourcesWhoseRolloutsAreOn = getSyncV2ResourcesWhoseRolloutsAreOn();
        Collection<String> resourcesWithFinalState = getResourcesWithFinalState(3);
        Ln.d("SYNCV2a: rollouts on = (%s), pendingOrJustCompleted = (%s)", syncV2ResourcesWhoseRolloutsAreOn, resourcesWithFinalState);
        for (String str : resourcesWithFinalState) {
            if (syncV2ResourcesWhoseRolloutsAreOn.contains(str)) {
                Ln.d("SYNCV2a: rollouts contains %s, remove it", str);
                syncV2ResourcesWhoseRolloutsAreOn.remove(str);
            } else {
                Ln.d("SYNCV2a: rollouts does not contain %s, add it", str);
                syncV2ResourcesWhoseRolloutsAreOn.add(str);
            }
        }
        Ln.d("SYNCV2a: removed pendingOrJustCompleted from rollouts --> (%s)", syncV2ResourcesWhoseRolloutsAreOn);
        hashSet.addAll(syncV2ResourcesWhoseRolloutsAreOn);
        Ln.d("SYNCV2a: final sync v2 set --> (%s)", hashSet);
        return hashSet;
    }

    public Collection<String> getSyncV2ResourcesWhoseRolloutsAreOn() {
        HashSet hashSet = new HashSet();
        for (Entry entry : mapOfResourceNamesForExclusionToRolloutName.entrySet()) {
            String str = (String) entry.getValue();
            if (ALWAYS_ENABLED.equals(str) || ((ConfigService) this.configService.get()).isVariantEnabled(str)) {
                hashSet.add(entry.getKey());
            }
        }
        hashSet.add("notification");
        return hashSet;
    }

    public void purgeStateForV2ImportMode(SyncMode syncMode) {
        if (syncMode.isImport()) {
            this.prefs.edit().putBoolean(syncMode.getFinishedPrefsKey(), false).putString(syncMode.getTokenPrefsKey(), null).apply();
        } else {
            throw new IllegalArgumentException(String.format("ImageSyncMode %s is not an IMPORT mode", new Object[]{syncMode}));
        }
    }

    public String getResourceNameForRollout(String str) {
        for (Entry entry : mapOfResourceNamesForExclusionToRolloutName.entrySet()) {
            if (Strings.equals((String) entry.getValue(), str)) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    public boolean userRefreshRequired() {
        if ((BuildConfiguration.getBuildConfiguration().isDebug() ? 1 : 8273) > this.prefs.getInt(LAST_REFRESHED_VERSION, 0)) {
            return true;
        }
        return false;
    }

    public void setUserRefreshed() {
        this.prefs.edit().putInt(LAST_REFRESHED_VERSION, getVersionCode()).apply();
    }

    public void migrateRemindersData() {
        SQLiteDatabaseWrapper sQLiteDatabaseWrapper = (SQLiteDatabaseWrapper) this.lazyDatabase.get();
        DatabaseUtil.ensureDatabaseTransaction(sQLiteDatabaseWrapper, new Function0(sQLiteDatabaseWrapper) {
            private final /* synthetic */ SQLiteDatabaseWrapper f$1;

            {
                this.f$1 = r2;
            }

            public final void execute() {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: INVOKE  (wrap: com.myfitnesspal.shared.service.syncv2.SyncUtilImpl
                      0x0000: IGET  (r0v0 com.myfitnesspal.shared.service.syncv2.SyncUtilImpl) = (r2v0 'this' com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$NjUh8memnVoAWhflr6s4OcE88GE A[THIS]) com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$NjUh8memnVoAWhflr6s4OcE88GE.f$0 com.myfitnesspal.shared.service.syncv2.SyncUtilImpl), (wrap: com.uacf.core.database.SQLiteDatabaseWrapper
                      0x0002: IGET  (r1v0 com.uacf.core.database.SQLiteDatabaseWrapper) = (r2v0 'this' com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$NjUh8memnVoAWhflr6s4OcE88GE A[THIS]) com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$NjUh8memnVoAWhflr6s4OcE88GE.f$1 com.uacf.core.database.SQLiteDatabaseWrapper) com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.lambda$migrateRemindersData$0(com.myfitnesspal.shared.service.syncv2.SyncUtilImpl, com.uacf.core.database.SQLiteDatabaseWrapper):void type: STATIC in method: com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$NjUh8memnVoAWhflr6s4OcE88GE.execute():void, dex: classes4.dex
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
                    com.myfitnesspal.shared.service.syncv2.SyncUtilImpl r0 = com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.this
                    com.uacf.core.database.SQLiteDatabaseWrapper r1 = r2.f$1
                    
                    // error: 0x0004: INVOKE  (r0 I:com.myfitnesspal.shared.service.syncv2.SyncUtilImpl), (r1 I:com.uacf.core.database.SQLiteDatabaseWrapper) com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.lambda$migrateRemindersData$0(com.myfitnesspal.shared.service.syncv2.SyncUtilImpl, com.uacf.core.database.SQLiteDatabaseWrapper):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.syncv2.$$Lambda$SyncUtilImpl$NjUh8memnVoAWhflr6s4OcE88GE.execute():void");
            }
        });
    }

    public void migrateDataForSyncV2() {
        SQLiteDatabaseWrapper sQLiteDatabaseWrapper = (SQLiteDatabaseWrapper) this.lazyDatabase.get();
        DatabaseUtil.ensureDatabaseTransaction(sQLiteDatabaseWrapper, new Function0(sQLiteDatabaseWrapper) {
            private final /* synthetic */ SQLiteDatabaseWrapper f$1;

            {
                this.f$1 = r2;
            }

            public final void execute() {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: INVOKE  (wrap: com.myfitnesspal.shared.service.syncv2.SyncUtilImpl
                      0x0000: IGET  (r0v0 com.myfitnesspal.shared.service.syncv2.SyncUtilImpl) = (r2v0 'this' com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$ugzlY709BOHhDnSKLb1_F1n6u7k A[THIS]) com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$ugzlY709BOHhDnSKLb1_F1n6u7k.f$0 com.myfitnesspal.shared.service.syncv2.SyncUtilImpl), (wrap: com.uacf.core.database.SQLiteDatabaseWrapper
                      0x0002: IGET  (r1v0 com.uacf.core.database.SQLiteDatabaseWrapper) = (r2v0 'this' com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$ugzlY709BOHhDnSKLb1_F1n6u7k A[THIS]) com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$ugzlY709BOHhDnSKLb1_F1n6u7k.f$1 com.uacf.core.database.SQLiteDatabaseWrapper) com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.lambda$migrateDataForSyncV2$1(com.myfitnesspal.shared.service.syncv2.SyncUtilImpl, com.uacf.core.database.SQLiteDatabaseWrapper):void type: STATIC in method: com.myfitnesspal.shared.service.syncv2.-$$Lambda$SyncUtilImpl$ugzlY709BOHhDnSKLb1_F1n6u7k.execute():void, dex: classes4.dex
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
                    com.myfitnesspal.shared.service.syncv2.SyncUtilImpl r0 = com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.this
                    com.uacf.core.database.SQLiteDatabaseWrapper r1 = r2.f$1
                    
                    // error: 0x0004: INVOKE  (r0 I:com.myfitnesspal.shared.service.syncv2.SyncUtilImpl), (r1 I:com.uacf.core.database.SQLiteDatabaseWrapper) com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.lambda$migrateDataForSyncV2$1(com.myfitnesspal.shared.service.syncv2.SyncUtilImpl, com.uacf.core.database.SQLiteDatabaseWrapper):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.syncv2.$$Lambda$SyncUtilImpl$ugzlY709BOHhDnSKLb1_F1n6u7k.execute():void");
            }
        });
    }

    public void migrateStepsData() {
        SQLiteDatabaseWrapper sQLiteDatabaseWrapper = (SQLiteDatabaseWrapper) this.lazyDatabase.get();
        DatabaseUtil.ensureDatabaseTransaction(sQLiteDatabaseWrapper, new Function0() {
            public final void execute() {
                SyncUtilImpl.lambda$migrateStepsData$2(SQLiteDatabaseWrapper.this);
            }
        });
    }

    static /* synthetic */ void lambda$migrateStepsData$2(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) throws RuntimeException {
        ExerciseEntriesTable exerciseEntriesTable = new ExerciseEntriesTable(sQLiteDatabaseWrapper);
        StepsTable stepsTable = new StepsTable(sQLiteDatabaseWrapper);
        exerciseEntriesTable.execSQL("UPDATE exercise_entries SET steps = (SELECT steps.steps FROM steps WHERE exercise_entries.master_id = steps.exercise_entry_master_id),client_id = (SELECT steps.client_id FROM steps WHERE exercise_entries.master_id = steps.exercise_entry_master_id),device_id = (SELECT steps.device_id FROM steps WHERE exercise_entries.master_id = steps.exercise_entry_master_id) WHERE EXISTS (SELECT 1 FROM steps WHERE steps.exercise_entry_master_id = exercise_entries.master_id)", new Object[0]);
        stepsTable.deleteData("EXISTS (SELECT 1 from exercise_entries WHERE master_id = steps.exercise_entry_master_id)");
    }

    public void setFinalSyncStateForResources(Collection<String> collection, int i) {
        if (!CollectionUtils.isEmpty(collection)) {
            Editor edit = this.prefs.edit();
            for (String finalSyncStateKey : collection) {
                edit.putInt(getFinalSyncStateKey(finalSyncStateKey), i);
            }
            edit.apply();
        }
    }

    public void removeFinalSyncStateForResources(Collection<String> collection) {
        if (!CollectionUtils.isEmpty(collection)) {
            Editor edit = this.prefs.edit();
            for (String finalSyncStateKey : collection) {
                edit.remove(getFinalSyncStateKey(finalSyncStateKey));
            }
            edit.apply();
        }
    }

    public Collection<String> getResourcesWithFinalState(int i) {
        return Enumerable.where((Collection<T>) SyncResourceName.ALL, (ReturningFunction1<Boolean, T>) new ReturningFunction1(i) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final Object execute(Object obj) {
                return SyncUtilImpl.lambda$getResourcesWithFinalState$3(SyncUtilImpl.this, this.f$1, (String) obj);
            }
        });
    }

    public static /* synthetic */ Boolean lambda$getResourcesWithFinalState$3(SyncUtilImpl syncUtilImpl, int i, String str) throws RuntimeException {
        boolean z = false;
        if ((i & syncUtilImpl.prefs.getInt(syncUtilImpl.getFinalSyncStateKey(str), 0)) > 0) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public void migrateResourceState(int i, int i2) {
        Collection resourcesWithFinalState = getResourcesWithFinalState(i);
        Ln.d("SYNCV2a: migrateResourceState: from = %s, to = %s, resources = (%s)", Integer.valueOf(i), Integer.valueOf(i2), Strings.join(",", resourcesWithFinalState));
        setFinalSyncStateForResources(resourcesWithFinalState, i2);
    }

    private String getFinalSyncStateKey(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(FINAL_SYNC_STATE_PREFIX);
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.String[], android.database.Cursor] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v1, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 54
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void migrateRemindersData(com.myfitnesspal.shared.db.table.RemindersTable r13) {
        /*
            r12 = this;
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r0 = r12.localSettingsService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r0 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r0
            boolean r0 = r0.wasFoodDBMigrated()
            if (r0 == 0) goto L_0x000f
            return
        L_0x000f:
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r0 = r12.session
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.shared.service.session.Session r0 = (com.myfitnesspal.shared.service.session.Session) r0
            com.myfitnesspal.shared.model.User r0 = r0.getUser()
            com.myfitnesspal.shared.model.MealNames r0 = r0.getMealNames()
            java.lang.String r1 = "id"
            java.lang.String r2 = "reminder_type"
            java.lang.String r3 = "meal_name"
            java.lang.String[] r6 = new java.lang.String[]{r1, r2, r3}
            r4 = 0
            java.lang.String r5 = "reminders"
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            java.lang.String r1 = android.database.sqlite.SQLiteQueryBuilder.buildQueryString(r4, r5, r6, r7, r8, r9, r10, r11)
            r2 = 0
            android.database.Cursor r2 = r13.rawQuery(r1, r2)     // Catch:{ all -> 0x009b }
        L_0x003a:
            boolean r1 = r2.moveToNext()     // Catch:{ all -> 0x009b }
            r3 = 1
            if (r1 == 0) goto L_0x008a
            java.lang.String r1 = "id"
            int r1 = r2.getColumnIndex(r1)     // Catch:{ all -> 0x009b }
            long r4 = r2.getLong(r1)     // Catch:{ all -> 0x009b }
            java.lang.String r1 = "reminder_type"
            int r1 = r2.getColumnIndex(r1)     // Catch:{ all -> 0x009b }
            int r1 = r2.getInt(r1)     // Catch:{ all -> 0x009b }
            java.lang.String r6 = "meal_name"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ all -> 0x009b }
            java.lang.String r6 = r2.getString(r6)     // Catch:{ all -> 0x009b }
            java.lang.String r6 = com.uacf.core.util.Strings.toString(r6)     // Catch:{ all -> 0x009b }
            if (r1 != r3) goto L_0x003a
            int r1 = r0.mealIdForName(r6)     // Catch:{ all -> 0x009b }
            android.content.ContentValues r6 = new android.content.ContentValues     // Catch:{ all -> 0x009b }
            r6.<init>()     // Catch:{ all -> 0x009b }
            java.lang.String r7 = "meal_id"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x009b }
            r6.put(r7, r1)     // Catch:{ all -> 0x009b }
            java.lang.String r1 = "id=?"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x009b }
            r7 = 0
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x009b }
            java.lang.String r4 = com.uacf.core.util.Strings.toString(r4)     // Catch:{ all -> 0x009b }
            r3[r7] = r4     // Catch:{ all -> 0x009b }
            r13.updateData(r6, r1, r3)     // Catch:{ all -> 0x009b }
            goto L_0x003a
        L_0x008a:
            if (r2 == 0) goto L_0x008f
            r2.close()
        L_0x008f:
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r13 = r12.localSettingsService
            java.lang.Object r13 = r13.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r13 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r13
            r13.setFoodDBMigrated(r3)
            return
        L_0x009b:
            r13 = move-exception
            if (r2 == 0) goto L_0x00a1
            r2.close()
        L_0x00a1:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.migrateRemindersData(com.myfitnesspal.shared.db.table.RemindersTable):void");
    }

    /* access modifiers changed from: private */
    public void migrateExerciseData(ExerciseEntriesTable exerciseEntriesTable) {
        if (!this.prefs.getBoolean(SHARED_PREFS_EXERCISE_DATA_MIGRATION_OCCURRED, false)) {
            exerciseEntriesTable.execSQL("UPDATE exercise_entries SET duration_in_seconds = (IFNULL(quantity,0) * 60) WHERE exercise_type = 0 AND duration_in_seconds IS NULL", new Object[0]);
            exerciseEntriesTable.execSQL("UPDATE exercise_entries SET repetitions = IFNULL(quantity,0) WHERE exercise_type = 1 AND repetitions IS NULL", new Object[0]);
            exerciseEntriesTable.execSQL("UPDATE exercise_entries SET sync_flags = 1 WHERE master_id = 0", new Object[0]);
            exerciseEntriesTable.execSQL("UPDATE exercises SET sync_flags = 3 WHERE deleted = 1", new Object[0]);
            migrateStepsData();
            migrateExerciseEntryProperties(exerciseEntriesTable);
            migrateExercisesFromExercisesTableToExerciseEntriesTable(exerciseEntriesTable);
            this.prefs.edit().putBoolean(SHARED_PREFS_EXERCISE_DATA_MIGRATION_OCCURRED, true).apply();
        }
    }

    /* JADX INFO: finally extract failed */
    private void migrateExerciseEntryProperties(ExerciseEntriesTable exerciseEntriesTable) {
        Cursor cursor = null;
        try {
            Cursor queryData = exerciseEntriesTable.queryData(true, new String[]{"id"}, "", new String[0]);
            while (queryData.moveToNext()) {
                long j = queryData.getLong(0);
                ContentValues v2ContentValuesFromV1ExtraProperties = ((ExerciseEntryMapper) this.mapper.get()).getV2ContentValuesFromV1ExtraProperties(loadExtraExerciseEntryProperties(j));
                if (v2ContentValuesFromV1ExtraProperties.size() > 0) {
                    exerciseEntriesTable.updateData(v2ContentValuesFromV1ExtraProperties, "id = ?", Long.valueOf(j));
                }
                deleteExerciseEntryProperties(j);
            }
            if (queryData != null) {
                queryData.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void migrateExercisesFromExercisesTableToExerciseEntriesTable(com.myfitnesspal.shared.db.table.ExerciseEntriesTable r22) {
        /*
            r21 = this;
            r1 = r21
            r0 = r22
            java.lang.String r3 = "SELECT EE.id as EE_id, * FROM exercise_entries as EE JOIN exercises as E on E.id = EE.exercise_id"
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x01c4 }
            android.database.Cursor r3 = r0.rawQuery(r3, r5)     // Catch:{ all -> 0x01c4 }
            java.lang.String r5 = "EE_id"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ all -> 0x01c2 }
            java.lang.String r6 = "id"
            int r6 = r3.getColumnIndex(r6)     // Catch:{ all -> 0x01c2 }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ all -> 0x01c2 }
            r7.<init>()     // Catch:{ all -> 0x01c2 }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ all -> 0x01c2 }
            r8.<init>()     // Catch:{ all -> 0x01c2 }
            com.uacf.core.database.CursorMapper r9 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x01c2 }
            r9.<init>(r3)     // Catch:{ all -> 0x01c2 }
            com.myfitnesspal.shared.model.mapper.ApiJsonMapper r10 = new com.myfitnesspal.shared.model.mapper.ApiJsonMapper     // Catch:{ all -> 0x01c2 }
            r10.<init>()     // Catch:{ all -> 0x01c2 }
        L_0x002d:
            boolean r11 = r3.moveToNext()     // Catch:{ all -> 0x01c2 }
            r12 = 3
            r13 = 2
            r14 = 1
            if (r11 == 0) goto L_0x00bc
            long r15 = r3.getLong(r5)     // Catch:{ all -> 0x01c2 }
            long r17 = r3.getLong(r6)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = "MIGRATE: found entry id %s has exercise id %s"
            java.lang.Object[] r2 = new java.lang.Object[r13]     // Catch:{ all -> 0x01c2 }
            java.lang.Long r19 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x01c2 }
            r2[r4] = r19     // Catch:{ all -> 0x01c2 }
            java.lang.Long r19 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x01c2 }
            r2[r14] = r19     // Catch:{ all -> 0x01c2 }
            com.uacf.core.util.Ln.d(r11, r2)     // Catch:{ all -> 0x01c2 }
            java.lang.Long r2 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x01c2 }
            java.lang.Long r11 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x01c2 }
            r8.put(r2, r11)     // Catch:{ all -> 0x01c2 }
            java.lang.Long r2 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x01c2 }
            java.lang.Object r2 = r7.get(r2)     // Catch:{ all -> 0x01c2 }
            com.uacf.core.util.Tuple2 r2 = (com.uacf.core.util.Tuple2) r2     // Catch:{ all -> 0x01c2 }
            if (r2 == 0) goto L_0x006f
            java.lang.Object r2 = r2.getItem2()     // Catch:{ all -> 0x01c2 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x01c2 }
            goto L_0x0070
        L_0x006f:
            r2 = 0
        L_0x0070:
            java.lang.String r11 = "MIGRATE: looked up JSON for exercise %d, found = %s"
            java.lang.Object[] r15 = new java.lang.Object[r13]     // Catch:{ all -> 0x01c2 }
            java.lang.Long r16 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x01c2 }
            r15[r4] = r16     // Catch:{ all -> 0x01c2 }
            boolean r16 = com.uacf.core.util.Strings.notEmpty(r2)     // Catch:{ all -> 0x01c2 }
            java.lang.Boolean r16 = java.lang.Boolean.valueOf(r16)     // Catch:{ all -> 0x01c2 }
            r15[r14] = r16     // Catch:{ all -> 0x01c2 }
            com.uacf.core.util.Ln.d(r11, r15)     // Catch:{ all -> 0x01c2 }
            boolean r2 = com.uacf.core.util.Strings.isEmpty(r2)     // Catch:{ all -> 0x01c2 }
            if (r2 == 0) goto L_0x002d
            com.myfitnesspal.shared.model.v2.MfpExercise r2 = new com.myfitnesspal.shared.model.v2.MfpExercise     // Catch:{ all -> 0x01c2 }
            r2.<init>(r9)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = r10.reverseMap(r2)     // Catch:{ all -> 0x01c2 }
            java.lang.String r15 = "MIGRATE: writing JSON for exercise local id = %s: master = %s, json = %s"
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch:{ all -> 0x01c2 }
            java.lang.Long r16 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x01c2 }
            r12[r4] = r16     // Catch:{ all -> 0x01c2 }
            long r19 = r2.getMasterId()     // Catch:{ all -> 0x01c2 }
            java.lang.Long r16 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x01c2 }
            r12[r14] = r16     // Catch:{ all -> 0x01c2 }
            r12[r13] = r11     // Catch:{ all -> 0x01c2 }
            com.uacf.core.util.Ln.d(r15, r12)     // Catch:{ all -> 0x01c2 }
            java.lang.Long r12 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x01c2 }
            com.uacf.core.util.Tuple2 r2 = com.uacf.core.util.Tuple.create(r2, r11)     // Catch:{ all -> 0x01c2 }
            r7.put(r12, r2)     // Catch:{ all -> 0x01c2 }
            goto L_0x002d
        L_0x00bc:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ all -> 0x01c2 }
            r2.<init>()     // Catch:{ all -> 0x01c2 }
            java.lang.String r5 = "MIGRATE: update database now for %s records"
            java.lang.Object[] r6 = new java.lang.Object[r14]     // Catch:{ all -> 0x01c2 }
            int r9 = r8.size()     // Catch:{ all -> 0x01c2 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x01c2 }
            r6[r4] = r9     // Catch:{ all -> 0x01c2 }
            com.uacf.core.util.Ln.d(r5, r6)     // Catch:{ all -> 0x01c2 }
            java.util.Set r5 = r8.keySet()     // Catch:{ all -> 0x01c2 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x01c2 }
        L_0x00da:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x01c2 }
            if (r6 == 0) goto L_0x014e
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x01c2 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x01c2 }
            java.lang.Object r9 = r8.get(r6)     // Catch:{ all -> 0x01c2 }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x01c2 }
            java.lang.Object r10 = r7.get(r9)     // Catch:{ all -> 0x01c2 }
            com.uacf.core.util.Tuple2 r10 = (com.uacf.core.util.Tuple2) r10     // Catch:{ all -> 0x01c2 }
            java.lang.Object r11 = r10.getItem1()     // Catch:{ all -> 0x01c2 }
            com.myfitnesspal.shared.model.v2.MfpExercise r11 = (com.myfitnesspal.shared.model.v2.MfpExercise) r11     // Catch:{ all -> 0x01c2 }
            java.lang.Object r10 = r10.getItem2()     // Catch:{ all -> 0x01c2 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x01c2 }
            long r15 = r11.getMasterId()     // Catch:{ all -> 0x01c2 }
            java.lang.String r12 = r11.getVersion()     // Catch:{ all -> 0x01c2 }
            java.lang.String r13 = "exercise_master_id"
            java.lang.Long r14 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x01c2 }
            r2.put(r13, r14)     // Catch:{ all -> 0x01c2 }
            java.lang.String r13 = "exercise_version"
            r2.put(r13, r12)     // Catch:{ all -> 0x01c2 }
            java.lang.String r13 = "is_calorie_adjustment"
            boolean r11 = r11.isCalorieAdjustment()     // Catch:{ all -> 0x01c2 }
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)     // Catch:{ all -> 0x01c2 }
            r2.put(r13, r11)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = "exercise"
            r2.put(r11, r10)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = "MIGRATE: exercise entry %s gets exercise %s with master %s, version %s and JSON %s"
            r13 = 5
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ all -> 0x01c2 }
            r13[r4] = r6     // Catch:{ all -> 0x01c2 }
            r14 = 1
            r13[r14] = r9     // Catch:{ all -> 0x01c2 }
            java.lang.Long r9 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x01c2 }
            r14 = 2
            r13[r14] = r9     // Catch:{ all -> 0x01c2 }
            r9 = 3
            r13[r9] = r12     // Catch:{ all -> 0x01c2 }
            r12 = 4
            r13[r12] = r10     // Catch:{ all -> 0x01c2 }
            com.uacf.core.util.Ln.d(r11, r13)     // Catch:{ all -> 0x01c2 }
            java.lang.String r10 = "id = ?"
            r11 = 1
            java.lang.Object[] r12 = new java.lang.Object[r11]     // Catch:{ all -> 0x01c2 }
            r12[r4] = r6     // Catch:{ all -> 0x01c2 }
            r0.updateData(r2, r10, r12)     // Catch:{ all -> 0x01c2 }
            r12 = 3
            r13 = 2
            r14 = 1
            goto L_0x00da
        L_0x014e:
            if (r3 == 0) goto L_0x0153
            r3.close()
        L_0x0153:
            dagger.Lazy<com.uacf.core.database.SQLiteDatabaseWrapper> r2 = r1.stockDatabase
            java.lang.Object r2 = r2.get()
            com.uacf.core.database.SQLiteDatabaseWrapper r2 = (com.uacf.core.database.SQLiteDatabaseWrapper) r2
            if (r2 == 0) goto L_0x01c1
            android.content.Context r5 = r1.context
            boolean r5 = com.myfitnesspal.shared.db.StockDbSQLiteOpenHelper.doesStockDatabaseFileExist(r5)
            if (r5 == 0) goto L_0x01c1
            java.lang.String r5 = "SELECT original_uid FROM exercises WHERE is_public = 1"
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ all -> 0x01ba }
            android.database.Cursor r3 = r0.rawQuery(r5, r6)     // Catch:{ all -> 0x01ba }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x01ba }
            r5.<init>()     // Catch:{ all -> 0x01ba }
            com.myfitnesspal.shared.db.table.StockExercisesTable r6 = new com.myfitnesspal.shared.db.table.StockExercisesTable     // Catch:{ all -> 0x01ba }
            r6.<init>(r2)     // Catch:{ all -> 0x01ba }
        L_0x0177:
            boolean r2 = r3.moveToNext()     // Catch:{ all -> 0x01ba }
            if (r2 == 0) goto L_0x0192
            java.lang.String r2 = r3.getString(r4)     // Catch:{ all -> 0x01ba }
            java.lang.String r7 = "uid= ?"
            r8 = 1
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch:{ all -> 0x01ba }
            r9[r4] = r2     // Catch:{ all -> 0x01ba }
            boolean r7 = r6.any(r7, r9)     // Catch:{ all -> 0x01ba }
            if (r7 == 0) goto L_0x0177
            r5.add(r2)     // Catch:{ all -> 0x01ba }
            goto L_0x0177
        L_0x0192:
            boolean r2 = com.uacf.core.util.CollectionUtils.notEmpty(r5)     // Catch:{ all -> 0x01ba }
            if (r2 == 0) goto L_0x01b4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ba }
            r2.<init>()     // Catch:{ all -> 0x01ba }
            java.lang.String r4 = "DELETE FROM exercises WHERE original_uid IN "
            r2.append(r4)     // Catch:{ all -> 0x01ba }
            java.lang.String r4 = com.uacf.core.database.DatabaseUtil.getArgsForList(r5)     // Catch:{ all -> 0x01ba }
            r2.append(r4)     // Catch:{ all -> 0x01ba }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01ba }
            com.uacf.core.database.SQLiteDatabaseWrapper r0 = r22.getDatabase()     // Catch:{ all -> 0x01ba }
            r0.execSQL(r2)     // Catch:{ all -> 0x01ba }
        L_0x01b4:
            if (r3 == 0) goto L_0x01c1
            r3.close()
            goto L_0x01c1
        L_0x01ba:
            r0 = move-exception
            if (r3 == 0) goto L_0x01c0
            r3.close()
        L_0x01c0:
            throw r0
        L_0x01c1:
            return
        L_0x01c2:
            r0 = move-exception
            goto L_0x01c6
        L_0x01c4:
            r0 = move-exception
            r3 = 0
        L_0x01c6:
            if (r3 == 0) goto L_0x01cb
            r3.close()
        L_0x01cb:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.migrateExercisesFromExercisesTableToExerciseEntriesTable(com.myfitnesspal.shared.db.table.ExerciseEntriesTable):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
        if (r1 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003f, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        return r0.build();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0034, code lost:
        if (r1 != null) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.String, java.lang.String> loadExtraExerciseEntryProperties(long r7) {
        /*
            r6 = this;
            com.uacf.core.util.MapUtil$Builder r0 = new com.uacf.core.util.MapUtil$Builder
            r0.<init>()
            r1 = 0
            dagger.Lazy<com.uacf.core.database.SQLiteDatabaseWrapper> r2 = r6.lazyDatabase     // Catch:{ Exception -> 0x0039 }
            java.lang.Object r2 = r2.get()     // Catch:{ Exception -> 0x0039 }
            com.uacf.core.database.SQLiteDatabaseWrapper r2 = (com.uacf.core.database.SQLiteDatabaseWrapper) r2     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "select property_name, property_value from exercise_entry_properties where exercise_entry_id = ?"
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch:{ Exception -> 0x0039 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r7 = com.uacf.core.util.Strings.toString(r7)     // Catch:{ Exception -> 0x0039 }
            r8 = 0
            r5[r8] = r7     // Catch:{ Exception -> 0x0039 }
            android.database.Cursor r1 = r2.rawQuery(r3, r5)     // Catch:{ Exception -> 0x0039 }
        L_0x0022:
            boolean r7 = r1.moveToNext()     // Catch:{ Exception -> 0x0039 }
            if (r7 == 0) goto L_0x0034
            java.lang.String r7 = r1.getString(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r2 = r1.getString(r4)     // Catch:{ Exception -> 0x0039 }
            r0.put(r7, r2)     // Catch:{ Exception -> 0x0039 }
            goto L_0x0022
        L_0x0034:
            if (r1 == 0) goto L_0x0042
            goto L_0x003f
        L_0x0037:
            r7 = move-exception
            goto L_0x0047
        L_0x0039:
            r7 = move-exception
            com.uacf.core.util.Ln.e(r7)     // Catch:{ all -> 0x0037 }
            if (r1 == 0) goto L_0x0042
        L_0x003f:
            r1.close()
        L_0x0042:
            java.util.Map r7 = r0.build()
            return r7
        L_0x0047:
            if (r1 == 0) goto L_0x004c
            r1.close()
        L_0x004c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.syncv2.SyncUtilImpl.loadExtraExerciseEntryProperties(long):java.util.Map");
    }

    private void deleteExerciseEntryProperties(long j) {
        new ExerciseEntryPropertiesTable((SQLiteDatabaseWrapper) this.lazyDatabase.get()).deleteData("exercise_entry_id = ?", Long.valueOf(j));
    }

    private int getVersionCode() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
