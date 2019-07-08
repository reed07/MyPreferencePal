package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;

final class zzfb extends zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zze zzafa;

    zzfb(zze zze, Activity activity) {
        this.zzafa = zze;
        this.val$activity = activity;
        super(zzea.this);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0010: INVOKE  (wrap: com.google.android.gms.internal.measurement.zzdn
              0x0004: INVOKE  (r0v2 com.google.android.gms.internal.measurement.zzdn) = (wrap: com.google.android.gms.internal.measurement.zzea
              0x0002: IGET  (r0v1 com.google.android.gms.internal.measurement.zzea) = (wrap: com.google.android.gms.internal.measurement.zzea$zze
              0x0000: IGET  (r0v0 com.google.android.gms.internal.measurement.zzea$zze) = (r4v0 'this' com.google.android.gms.internal.measurement.zzfb A[THIS]) com.google.android.gms.internal.measurement.zzfb.zzafa com.google.android.gms.internal.measurement.zzea$zze) com.google.android.gms.internal.measurement.zzea.zze.zzadv com.google.android.gms.internal.measurement.zzea) com.google.android.gms.internal.measurement.zzea.zzb(com.google.android.gms.internal.measurement.zzea):com.google.android.gms.internal.measurement.zzdn type: STATIC), (wrap: com.google.android.gms.dynamic.IObjectWrapper
              0x000a: INVOKE  (r1v1 com.google.android.gms.dynamic.IObjectWrapper) = (wrap: android.app.Activity
              0x0008: IGET  (r1v0 android.app.Activity) = (r4v0 'this' com.google.android.gms.internal.measurement.zzfb A[THIS]) com.google.android.gms.internal.measurement.zzfb.val$activity android.app.Activity) com.google.android.gms.dynamic.ObjectWrapper.wrap(java.lang.Object):com.google.android.gms.dynamic.IObjectWrapper type: STATIC), (wrap: long
              0x000e: IGET  (r2v0 long) = (r4v0 'this' com.google.android.gms.internal.measurement.zzfb A[THIS]) com.google.android.gms.internal.measurement.zzfb.zzaev long) com.google.android.gms.internal.measurement.zzdn.onActivityStopped(com.google.android.gms.dynamic.IObjectWrapper, long):void type: INTERFACE in method: com.google.android.gms.internal.measurement.zzfb.zzgd():void, dex: classes2.dex
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
            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
            	at jadx.core.ProcessClass.process(ProcessClass.java:36)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: INVOKE  (r0v2 com.google.android.gms.internal.measurement.zzdn) = (wrap: com.google.android.gms.internal.measurement.zzea
              0x0002: IGET  (r0v1 com.google.android.gms.internal.measurement.zzea) = (wrap: com.google.android.gms.internal.measurement.zzea$zze
              0x0000: IGET  (r0v0 com.google.android.gms.internal.measurement.zzea$zze) = (r4v0 'this' com.google.android.gms.internal.measurement.zzfb A[THIS]) com.google.android.gms.internal.measurement.zzfb.zzafa com.google.android.gms.internal.measurement.zzea$zze) com.google.android.gms.internal.measurement.zzea.zze.zzadv com.google.android.gms.internal.measurement.zzea) com.google.android.gms.internal.measurement.zzea.zzb(com.google.android.gms.internal.measurement.zzea):com.google.android.gms.internal.measurement.zzdn type: STATIC in method: com.google.android.gms.internal.measurement.zzfb.zzgd():void, dex: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
            	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            Caused by: java.lang.ArrayIndexOutOfBoundsException: Index -2 out of bounds for length 2
            	at java.base/java.util.ArrayList.shiftTailOverGap(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:86)
            	at jadx.core.utils.InsnRemover.unbindArgUsage(InsnRemover.java:90)
            	at jadx.core.dex.nodes.InsnNode.replaceArg(InsnNode.java:130)
            	at jadx.core.dex.nodes.InsnNode.replaceArg(InsnNode.java:134)
            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:892)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
            	... 24 more
            */
        /*
            this = this;
            com.google.android.gms.internal.measurement.zzea$zze r0 = r4.zzafa
            com.google.android.gms.internal.measurement.zzea r0 = com.google.android.gms.internal.measurement.zzea.this
            com.google.android.gms.internal.measurement.zzdn r0 = 
            // error: 0x0004: INVOKE  (r0 I:com.google.android.gms.internal.measurement.zzdn) = (r0 I:com.google.android.gms.internal.measurement.zzea) com.google.android.gms.internal.measurement.zzea.zzb(com.google.android.gms.internal.measurement.zzea):com.google.android.gms.internal.measurement.zzdn type: STATIC
            android.app.Activity r1 = r4.val$activity
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r1)
            long r2 = r4.zzaev
            r0.onActivityStopped(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfb.zzgd():void");
    }
}
