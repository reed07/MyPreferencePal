package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.ChannelLogger;
import io.grpc.InternalChannelz.ChannelTrace.Event;
import io.grpc.InternalChannelz.ChannelTrace.Event.Builder;
import io.grpc.InternalChannelz.ChannelTrace.Event.Severity;
import io.grpc.InternalLogId;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

final class ChannelTracer {
    static final Logger logger = Logger.getLogger(ChannelLogger.class.getName());
    private final long channelCreationTimeNanos;
    @GuardedBy("lock")
    @Nullable
    private final Collection<Event> events;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public int eventsLogged;
    private final Object lock = new Object();
    private final InternalLogId logId;

    ChannelTracer(InternalLogId internalLogId, final int i, long j, String str) {
        Preconditions.checkNotNull(str, "description");
        this.logId = (InternalLogId) Preconditions.checkNotNull(internalLogId, "logId");
        if (i > 0) {
            this.events = new ArrayDeque<Event>() {
                @GuardedBy("lock")
                public boolean add(Event event) {
                    if (size() == i) {
                        removeFirst();
                    }
                    
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: INVOKE  (wrap: io.grpc.internal.ChannelTracer
                          0x000b: IGET  (r0v1 io.grpc.internal.ChannelTracer) = (r2v0 'this' io.grpc.internal.ChannelTracer$1 A[THIS]) io.grpc.internal.ChannelTracer.1.this$0 io.grpc.internal.ChannelTracer) io.grpc.internal.ChannelTracer.access$008(io.grpc.internal.ChannelTracer):int type: STATIC in method: io.grpc.internal.ChannelTracer.1.add(io.grpc.InternalChannelz$ChannelTrace$Event):boolean, dex: classes4.dex
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
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
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
                        	... 41 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 59 more
                        */
                    /*
                        this = this;
                        int r0 = r2.size()
                        int r1 = r3
                        if (r0 != r1) goto L_0x000b
                        r2.removeFirst()
                    L_0x000b:
                        io.grpc.internal.ChannelTracer r0 = io.grpc.internal.ChannelTracer.this
                        
                        // error: 0x000d: INVOKE  (r0 I:io.grpc.internal.ChannelTracer) io.grpc.internal.ChannelTracer.access$008(io.grpc.internal.ChannelTracer):int type: STATIC
                        boolean r3 = super.add(r3)
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ChannelTracer.AnonymousClass1.add(io.grpc.InternalChannelz$ChannelTrace$Event):boolean");
                }
            };
        } else {
            this.events = null;
        }
        this.channelCreationTimeNanos = j;
        Builder builder = new Builder();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" created");
        reportEvent(builder.setDescription(sb.toString()).setSeverity(Severity.CT_INFO).setTimestampNanos(j).build());
    }

    /* access modifiers changed from: 0000 */
    public void reportEvent(Event event) {
        Level level;
        switch (event.severity) {
            case CT_ERROR:
                level = Level.FINE;
                break;
            case CT_WARNING:
                level = Level.FINER;
                break;
            default:
                level = Level.FINEST;
                break;
        }
        traceOnly(event);
        logOnly(level, event.description);
    }

    /* access modifiers changed from: 0000 */
    public boolean isTraceEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.events != null;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void traceOnly(Event event) {
        synchronized (this.lock) {
            if (this.events != null) {
                this.events.add(event);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void logOnly(Level level, String str) {
        if (logger.isLoggable(level)) {
            Logger logger2 = logger;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(this.logId);
            sb.append("] ");
            sb.append(str);
            logger2.log(level, sb.toString());
        }
    }
}
