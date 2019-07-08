package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzwo extends zzwt<zzxg> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzcla;
    private final /* synthetic */ zzalg zzclb;
    private final /* synthetic */ zzwj zzclc;

    zzwo(zzwj zzwj, Context context, String str, zzalg zzalg) {
        this.zzclc = zzwj;
        this.val$context = context;
        this.zzcla = str;
        this.zzclb = zzalg;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpq() {
        
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: INVOKE  (wrap: android.content.Context
              0x0000: IGET  (r0v0 android.content.Context) = (r2v0 'this' com.google.android.gms.internal.ads.zzwo A[THIS]) com.google.android.gms.internal.ads.zzwo.val$context android.content.Context), (wrap: java.lang.String
              0x0002: CONST_STR  (r1v0 java.lang.String) =  "native_ad") com.google.android.gms.internal.ads.zzwj.zzb(android.content.Context, java.lang.String):void type: STATIC in method: com.google.android.gms.internal.ads.zzwo.zzpq():java.lang.Object, dex: classes2.dex
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
            Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: length -2 is negative
            	at java.base/java.util.ArrayList.shiftTailOverGap(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at java.base/java.util.ArrayList.removeIf(Unknown Source)
            	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:86)
            	at jadx.core.utils.InsnRemover.unbindArgUsage(InsnRemover.java:90)
            	at jadx.core.dex.nodes.InsnNode.replaceArg(InsnNode.java:130)
            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:892)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
            	... 19 more
            */
        /*
            this = this;
            android.content.Context r0 = r2.val$context
            java.lang.String r1 = "native_ad"
            com.google.android.gms.internal.ads.zzwj.zza(r0, r1)
            com.google.android.gms.internal.ads.zzzh r0 = new com.google.android.gms.internal.ads.zzzh
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzwo.zzpq():java.lang.Object");
    }

    public final /* synthetic */ Object zzpr() throws RemoteException {
        return this.zzclc.zzcks.zza(this.val$context, this.zzcla, this.zzclb);
    }

    public final /* synthetic */ Object zza(zzxw zzxw) throws RemoteException {
        return zzxw.createAdLoaderBuilder(ObjectWrapper.wrap(this.val$context), this.zzcla, this.zzclb, 14300000);
    }
}
