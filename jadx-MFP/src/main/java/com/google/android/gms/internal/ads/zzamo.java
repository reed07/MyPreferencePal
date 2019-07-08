package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest.ErrorCode;

final class zzamo implements Runnable {
    private final /* synthetic */ zzamj zzdoi;
    private final /* synthetic */ ErrorCode zzdoj;

    zzamo(zzamj zzamj, ErrorCode errorCode) {
        this.zzdoi = zzamj;
        this.zzdoj = errorCode;
    }

    public final void run() {
        try {
            
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000c: INVOKE  (wrap: com.google.android.gms.internal.ads.zzalm
                  0x0002: INVOKE  (r0v2 com.google.android.gms.internal.ads.zzalm) = (wrap: com.google.android.gms.internal.ads.zzamj
                  0x0000: IGET  (r0v1 com.google.android.gms.internal.ads.zzamj) = (r2v0 'this' com.google.android.gms.internal.ads.zzamo A[THIS]) com.google.android.gms.internal.ads.zzamo.zzdoi com.google.android.gms.internal.ads.zzamj) com.google.android.gms.internal.ads.zzamj.zza(com.google.android.gms.internal.ads.zzamj):com.google.android.gms.internal.ads.zzalm type: STATIC), (wrap: int
                  0x0008: INVOKE  (r1v2 int) = (wrap: com.google.ads.AdRequest$ErrorCode
                  0x0006: IGET  (r1v1 com.google.ads.AdRequest$ErrorCode) = (r2v0 'this' com.google.android.gms.internal.ads.zzamo A[THIS]) com.google.android.gms.internal.ads.zzamo.zzdoj com.google.ads.AdRequest$ErrorCode) com.google.android.gms.internal.ads.zzamv.zza(com.google.ads.AdRequest$ErrorCode):int type: STATIC) com.google.android.gms.internal.ads.zzalm.onAdFailedToLoad(int):void type: INTERFACE in method: com.google.android.gms.internal.ads.zzamo.run():void, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:299)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
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
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (r0v2 com.google.android.gms.internal.ads.zzalm) = (wrap: com.google.android.gms.internal.ads.zzamj
                  0x0000: IGET  (r0v1 com.google.android.gms.internal.ads.zzamj) = (r2v0 'this' com.google.android.gms.internal.ads.zzamo A[THIS]) com.google.android.gms.internal.ads.zzamo.zzdoi com.google.android.gms.internal.ads.zzamj) com.google.android.gms.internal.ads.zzamj.zza(com.google.android.gms.internal.ads.zzamj):com.google.android.gms.internal.ads.zzalm type: STATIC in method: com.google.android.gms.internal.ads.zzamo.run():void, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                	... 24 more
                Caused by: java.util.ConcurrentModificationException
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
                	... 29 more
                */
            /*
                this = this;
                com.google.android.gms.internal.ads.zzamj r0 = r2.zzdoi     // Catch:{ RemoteException -> 0x0010 }
                com.google.android.gms.internal.ads.zzalm r0 = r0.zzdnz     // Catch:{ RemoteException -> 0x0010 }
                com.google.ads.AdRequest$ErrorCode r1 = r2.zzdoj     // Catch:{ RemoteException -> 0x0010 }
                int r1 = com.google.android.gms.internal.ads.zzamv.zza(r1)     // Catch:{ RemoteException -> 0x0010 }
                r0.onAdFailedToLoad(r1)     // Catch:{ RemoteException -> 0x0010 }
                return
            L_0x0010:
                r0 = move-exception
                java.lang.String r1 = "#007 Could not call remote method."
                com.google.android.gms.internal.ads.zzbbd.zzd(r1, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzamo.run():void");
        }
    }
