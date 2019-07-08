package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzco implements Callable<List<zzfw>> {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzby zzaqo;

    zzco(zzby zzby, zzk zzk) {
        this.zzaqo = zzby;
        this.zzaqn = zzk;
    }

    public final /* synthetic */ Object call() throws Exception {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: INVOKE  (wrap: com.google.android.gms.measurement.internal.zzfn
              0x0002: INVOKE  (r0v1 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
              0x0000: IGET  (r0v0 com.google.android.gms.measurement.internal.zzby) = (r2v0 'this' com.google.android.gms.measurement.internal.zzco A[THIS]) com.google.android.gms.measurement.internal.zzco.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC) com.google.android.gms.measurement.internal.zzfn.zzme():void type: VIRTUAL in method: com.google.android.gms.measurement.internal.zzco.call():java.lang.Object, dex: classes2.dex
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
              0x0000: IGET  (r0v0 com.google.android.gms.measurement.internal.zzby) = (r2v0 'this' com.google.android.gms.measurement.internal.zzco A[THIS]) com.google.android.gms.measurement.internal.zzco.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC in method: com.google.android.gms.measurement.internal.zzco.call():java.lang.Object, dex: classes2.dex
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
            com.google.android.gms.measurement.internal.zzby r0 = r2.zzaqo
            com.google.android.gms.measurement.internal.zzfn r0 = 
            // error: 0x0002: INVOKE  (r0 I:com.google.android.gms.measurement.internal.zzfn) = (r0 I:com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC
            r0.zzme()
            com.google.android.gms.measurement.internal.zzby r0 = r2.zzaqo
            com.google.android.gms.measurement.internal.zzfn r0 = 
            // error: 0x000b: INVOKE  (r0 I:com.google.android.gms.measurement.internal.zzfn) = (r0 I:com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC
            com.google.android.gms.measurement.internal.zzt r0 = r0.zzjt()
            com.google.android.gms.measurement.internal.zzk r1 = r2.zzaqn
            java.lang.String r1 = r1.packageName
            java.util.List r0 = r0.zzbl(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzco.call():java.lang.Object");
    }
}
