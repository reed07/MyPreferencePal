package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzcl implements Callable<byte[]> {
    private final /* synthetic */ zzag zzagi;
    private final /* synthetic */ String zzagj;
    private final /* synthetic */ zzby zzaqo;

    zzcl(zzby zzby, zzag zzag, String str) {
        this.zzaqo = zzby;
        this.zzagi = zzag;
        this.zzagj = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: INVOKE  (wrap: com.google.android.gms.measurement.internal.zzfn
              0x0002: INVOKE  (r0v1 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
              0x0000: IGET  (r0v0 com.google.android.gms.measurement.internal.zzby) = (r3v0 'this' com.google.android.gms.measurement.internal.zzcl A[THIS]) com.google.android.gms.measurement.internal.zzcl.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC) com.google.android.gms.measurement.internal.zzfn.zzme():void type: VIRTUAL in method: com.google.android.gms.measurement.internal.zzcl.call():java.lang.Object, dex: classes2.dex
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
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r0v1 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
              0x0000: IGET  (r0v0 com.google.android.gms.measurement.internal.zzby) = (r3v0 'this' com.google.android.gms.measurement.internal.zzcl A[THIS]) com.google.android.gms.measurement.internal.zzcl.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC in method: com.google.android.gms.measurement.internal.zzcl.call():java.lang.Object, dex: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
            	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            Caused by: java.lang.ArrayIndexOutOfBoundsException
            */
        /*
            this = this;
            com.google.android.gms.measurement.internal.zzby r0 = r3.zzaqo
            com.google.android.gms.measurement.internal.zzfn r0 = 
            // error: 0x0002: INVOKE  (r0 I:com.google.android.gms.measurement.internal.zzfn) = (r0 I:com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC
            r0.zzme()
            com.google.android.gms.measurement.internal.zzby r0 = r3.zzaqo
            com.google.android.gms.measurement.internal.zzfn r0 = 
            // error: 0x000b: INVOKE  (r0 I:com.google.android.gms.measurement.internal.zzfn) = (r0 I:com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC
            com.google.android.gms.measurement.internal.zzdv r0 = r0.zzlw()
            com.google.android.gms.measurement.internal.zzag r1 = r3.zzagi
            java.lang.String r2 = r3.zzagj
            byte[] r0 = r0.zzb(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzcl.call():java.lang.Object");
    }
}
