package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.j2objc.annotations.ReflectionSupport;
import com.google.j2objc.annotations.ReflectionSupport.Level;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Logger;

@GwtCompatible(emulated = true)
@ReflectionSupport(Level.FULL)
abstract class AggregateFutureState {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Logger log = Logger.getLogger(AggregateFutureState.class.getName());
    /* access modifiers changed from: private */
    public volatile int remaining;
    /* access modifiers changed from: private */
    public volatile Set<Throwable> seenExceptions = null;

    private static abstract class AtomicHelper {
        /* access modifiers changed from: 0000 */
        public abstract void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2);

        /* access modifiers changed from: 0000 */
        public abstract int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState);

        private AtomicHelper() {
        }
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicIntegerFieldUpdater<AggregateFutureState> remainingCountUpdater;
        final AtomicReferenceFieldUpdater<AggregateFutureState, Set<Throwable>> seenExceptionsUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super();
            this.seenExceptionsUpdater = atomicReferenceFieldUpdater;
            this.remainingCountUpdater = atomicIntegerFieldUpdater;
        }

        /* access modifiers changed from: 0000 */
        public void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2) {
            this.seenExceptionsUpdater.compareAndSet(aggregateFutureState, set, set2);
        }

        /* access modifiers changed from: 0000 */
        public int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState) {
            return this.remainingCountUpdater.decrementAndGet(aggregateFutureState);
        }
    }

    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        /* access modifiers changed from: 0000 */
        public void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2) {
            synchronized (aggregateFutureState) {
                if (aggregateFutureState.seenExceptions == set) {
                    aggregateFutureState.seenExceptions = set2;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState) {
            int access$300;
            synchronized (aggregateFutureState) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0001: INVOKE  (r2v0 'aggregateFutureState' com.google.common.util.concurrent.AggregateFutureState) com.google.common.util.concurrent.AggregateFutureState.access$310(com.google.common.util.concurrent.AggregateFutureState):int type: STATIC in method: com.google.common.util.concurrent.AggregateFutureState.SynchronizedAtomicHelper.decrementAndGetRemainingCount(com.google.common.util.concurrent.AggregateFutureState):int, dex: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:248)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:70)
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
                    	... 29 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 47 more
                    */
                /*
                    this = this;
                    monitor-enter(r2)
                    
                    // error: 0x0001: INVOKE  (r2 I:com.google.common.util.concurrent.AggregateFutureState) com.google.common.util.concurrent.AggregateFutureState.access$310(com.google.common.util.concurrent.AggregateFutureState):int type: STATIC
                    int r0 = r2.remaining     // Catch:{ all -> 0x000a }
                    monitor-exit(r2)     // Catch:{ all -> 0x000a }
                    return r0     // Catch:{ all -> 0x000a }
                L_0x000a:
                    r0 = move-exception     // Catch:{ all -> 0x000a }
                    monitor-exit(r2)     // Catch:{ all -> 0x000a }
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AggregateFutureState.SynchronizedAtomicHelper.decrementAndGetRemainingCount(com.google.common.util.concurrent.AggregateFutureState):int");
            }
        }

        /* access modifiers changed from: 0000 */
        public abstract void addInitialException(Set<Throwable> set);

        static {
            AtomicHelper atomicHelper;
            Throwable th = null;
            try {
                atomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, Key.Remaining));
            } catch (Throwable th2) {
                th = th2;
                atomicHelper = new SynchronizedAtomicHelper();
            }
            ATOMIC_HELPER = atomicHelper;
            if (th != null) {
                log.log(java.util.logging.Level.SEVERE, "SafeAtomicHelper is broken!", th);
            }
        }

        AggregateFutureState(int i) {
            this.remaining = i;
        }

        /* access modifiers changed from: 0000 */
        public final Set<Throwable> getOrInitSeenExceptions() {
            Set<Throwable> set = this.seenExceptions;
            if (set != null) {
                return set;
            }
            Set newConcurrentHashSet = Sets.newConcurrentHashSet();
            addInitialException(newConcurrentHashSet);
            ATOMIC_HELPER.compareAndSetSeenExceptions(this, null, newConcurrentHashSet);
            return this.seenExceptions;
        }

        /* access modifiers changed from: 0000 */
        public final int decrementRemainingAndGet() {
            return ATOMIC_HELPER.decrementAndGetRemainingCount(this);
        }
    }
