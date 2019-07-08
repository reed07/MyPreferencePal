package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.lang.ref.WeakReference;

final class zzaqn implements OnGlobalLayoutListener {
    private final /* synthetic */ zzaqf zzdur;
    private final /* synthetic */ WeakReference zzdus;

    zzaqn(zzaqf zzaqf, WeakReference weakReference) {
        this.zzdur = zzaqf;
        this.zzdus = weakReference;
    }

    public final void onGlobalLayout() {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0005: INVOKE  (wrap: com.google.android.gms.internal.ads.zzaqf
              0x0000: IGET  (r0v0 com.google.android.gms.internal.ads.zzaqf) = (r3v0 'this' com.google.android.gms.internal.ads.zzaqn A[THIS]) com.google.android.gms.internal.ads.zzaqn.zzdur com.google.android.gms.internal.ads.zzaqf), (wrap: java.lang.ref.WeakReference
              0x0002: IGET  (r1v0 java.lang.ref.WeakReference) = (r3v0 'this' com.google.android.gms.internal.ads.zzaqn A[THIS]) com.google.android.gms.internal.ads.zzaqn.zzdus java.lang.ref.WeakReference), false com.google.android.gms.internal.ads.zzaqf.zza(com.google.android.gms.internal.ads.zzaqf, java.lang.ref.WeakReference, boolean):void type: STATIC in method: com.google.android.gms.internal.ads.zzaqn.onGlobalLayout():void, dex: classes2.dex
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
            Caused by: java.util.ConcurrentModificationException
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:86)
            	at jadx.core.dex.instructions.args.SSAVar.use(SSAVar.java:79)
            	at jadx.core.dex.nodes.InsnNode.attachArg(InsnNode.java:87)
            	at jadx.core.dex.nodes.InsnNode.addArg(InsnNode.java:73)
            	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:335)
            	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            */
        /*
            this = this;
            com.google.android.gms.internal.ads.zzaqf r0 = r3.zzdur
            java.lang.ref.WeakReference r1 = r3.zzdus
            r2 = 0
            r0.zza(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqn.onGlobalLayout():void");
    }
}
