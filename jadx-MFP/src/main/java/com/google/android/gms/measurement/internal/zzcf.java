package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzcf implements Callable<List<zzfw>> {
    private final /* synthetic */ String zzads;
    private final /* synthetic */ String zzadz;
    private final /* synthetic */ String zzagj;
    private final /* synthetic */ zzby zzaqo;

    zzcf(zzby zzby, String str, String str2, String str3) {
        this.zzaqo = zzby;
        this.zzagj = str;
        this.zzads = str2;
        this.zzadz = str3;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzaqo.zzamx.zzme();
        return 
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: RETURN  (wrap: java.util.List
              0x0019: INVOKE  (r0v5 java.util.List) = (wrap: com.google.android.gms.measurement.internal.zzt
              0x000f: INVOKE  (r0v4 com.google.android.gms.measurement.internal.zzt) = (wrap: com.google.android.gms.measurement.internal.zzfn
              0x000b: INVOKE  (r0v3 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
              0x0009: IGET  (r0v2 com.google.android.gms.measurement.internal.zzby) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC) com.google.android.gms.measurement.internal.zzfn.zzjt():com.google.android.gms.measurement.internal.zzt type: VIRTUAL), (wrap: java.lang.String
              0x0013: IGET  (r1v0 java.lang.String) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzagj java.lang.String), (wrap: java.lang.String
              0x0015: IGET  (r2v0 java.lang.String) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzads java.lang.String), (wrap: java.lang.String
              0x0017: IGET  (r3v0 java.lang.String) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzadz java.lang.String) com.google.android.gms.measurement.internal.zzt.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List type: VIRTUAL) in method: com.google.android.gms.measurement.internal.zzcf.call():java.lang.Object, dex: classes2.dex
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
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: INVOKE  (r0v5 java.util.List) = (wrap: com.google.android.gms.measurement.internal.zzt
              0x000f: INVOKE  (r0v4 com.google.android.gms.measurement.internal.zzt) = (wrap: com.google.android.gms.measurement.internal.zzfn
              0x000b: INVOKE  (r0v3 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
              0x0009: IGET  (r0v2 com.google.android.gms.measurement.internal.zzby) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC) com.google.android.gms.measurement.internal.zzfn.zzjt():com.google.android.gms.measurement.internal.zzt type: VIRTUAL), (wrap: java.lang.String
              0x0013: IGET  (r1v0 java.lang.String) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzagj java.lang.String), (wrap: java.lang.String
              0x0015: IGET  (r2v0 java.lang.String) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzads java.lang.String), (wrap: java.lang.String
              0x0017: IGET  (r3v0 java.lang.String) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzadz java.lang.String) com.google.android.gms.measurement.internal.zzt.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List type: VIRTUAL in method: com.google.android.gms.measurement.internal.zzcf.call():java.lang.Object, dex: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: INVOKE  (r0v4 com.google.android.gms.measurement.internal.zzt) = (wrap: com.google.android.gms.measurement.internal.zzfn
              0x000b: INVOKE  (r0v3 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
              0x0009: IGET  (r0v2 com.google.android.gms.measurement.internal.zzby) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC) com.google.android.gms.measurement.internal.zzfn.zzjt():com.google.android.gms.measurement.internal.zzt type: VIRTUAL in method: com.google.android.gms.measurement.internal.zzcf.call():java.lang.Object, dex: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
            	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
            	... 22 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  (r0v3 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
              0x0009: IGET  (r0v2 com.google.android.gms.measurement.internal.zzby) = (r4v0 'this' com.google.android.gms.measurement.internal.zzcf A[THIS]) com.google.android.gms.measurement.internal.zzcf.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC in method: com.google.android.gms.measurement.internal.zzcf.call():java.lang.Object, dex: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
            	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
            	... 27 more
            Caused by: java.lang.ArrayIndexOutOfBoundsException: Index -2 out of bounds for length 2
            	at java.base/java.util.ArrayList.add(Unknown Source)
            	at java.base/java.util.ArrayList.add(Unknown Source)
            	at jadx.core.dex.instructions.args.SSAVar.use(SSAVar.java:82)
            	at jadx.core.dex.nodes.InsnNode.attachArg(InsnNode.java:87)
            	at jadx.core.dex.nodes.InsnNode.addArg(InsnNode.java:73)
            	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:335)
            	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
            	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
            	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
            	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:350)
            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
            	... 32 more
            */
        /*
            this = this;
            com.google.android.gms.measurement.internal.zzby r0 = r4.zzaqo
            com.google.android.gms.measurement.internal.zzfn r0 = 
            // error: 0x0002: INVOKE  (r0 I:com.google.android.gms.measurement.internal.zzfn) = (r0 I:com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC
            r0.zzme()
            com.google.android.gms.measurement.internal.zzby r0 = r4.zzaqo
            com.google.android.gms.measurement.internal.zzfn r0 = 
            // error: 0x000b: INVOKE  (r0 I:com.google.android.gms.measurement.internal.zzfn) = (r0 I:com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC
            com.google.android.gms.measurement.internal.zzt r0 = r0.zzjt()
            java.lang.String r1 = r4.zzagj
            java.lang.String r2 = r4.zzads
            java.lang.String r3 = r4.zzadz
            java.util.List r0 = r0.zzb(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzcf.call():java.lang.Object");
    }
}
