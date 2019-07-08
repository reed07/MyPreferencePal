package com.google.android.gms.measurement.internal;

final class zzcq implements Runnable {
    private final /* synthetic */ String zzaeb;
    private final /* synthetic */ String zzagj;
    private final /* synthetic */ zzby zzaqo;
    private final /* synthetic */ String zzaqr;
    private final /* synthetic */ long zzaqs;

    zzcq(zzby zzby, String str, String str2, String str3, long j) {
        this.zzaqo = zzby;
        this.zzaqr = str;
        this.zzagj = str2;
        this.zzaeb = str3;
        this.zzaqs = j;
    }

    public final void run() {
        String str = this.zzaqr;
        if (str == null) {
            
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: INVOKE  (wrap: com.google.android.gms.measurement.internal.zzdy
                  0x000e: INVOKE  (r0v8 com.google.android.gms.measurement.internal.zzdy) = (wrap: com.google.android.gms.measurement.internal.zzbw
                  0x000a: INVOKE  (r0v7 com.google.android.gms.measurement.internal.zzbw) = (wrap: com.google.android.gms.measurement.internal.zzfn
                  0x0006: INVOKE  (r0v6 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
                  0x0004: IGET  (r0v5 com.google.android.gms.measurement.internal.zzby) = (r5v0 'this' com.google.android.gms.measurement.internal.zzcq A[THIS]) com.google.android.gms.measurement.internal.zzcq.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC) com.google.android.gms.measurement.internal.zzfn.zzmh():com.google.android.gms.measurement.internal.zzbw type: VIRTUAL) com.google.android.gms.measurement.internal.zzbw.zzgm():com.google.android.gms.measurement.internal.zzdy type: VIRTUAL), (wrap: java.lang.String
                  0x0012: IGET  (r1v1 java.lang.String) = (r5v0 'this' com.google.android.gms.measurement.internal.zzcq A[THIS]) com.google.android.gms.measurement.internal.zzcq.zzagj java.lang.String), (null com.google.android.gms.measurement.internal.zzdx) com.google.android.gms.measurement.internal.zzdy.zza(java.lang.String, com.google.android.gms.measurement.internal.zzdx):void type: VIRTUAL in method: com.google.android.gms.measurement.internal.zzcq.run():void, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
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
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000e: INVOKE  (r0v8 com.google.android.gms.measurement.internal.zzdy) = (wrap: com.google.android.gms.measurement.internal.zzbw
                  0x000a: INVOKE  (r0v7 com.google.android.gms.measurement.internal.zzbw) = (wrap: com.google.android.gms.measurement.internal.zzfn
                  0x0006: INVOKE  (r0v6 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
                  0x0004: IGET  (r0v5 com.google.android.gms.measurement.internal.zzby) = (r5v0 'this' com.google.android.gms.measurement.internal.zzcq A[THIS]) com.google.android.gms.measurement.internal.zzcq.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC) com.google.android.gms.measurement.internal.zzfn.zzmh():com.google.android.gms.measurement.internal.zzbw type: VIRTUAL) com.google.android.gms.measurement.internal.zzbw.zzgm():com.google.android.gms.measurement.internal.zzdy type: VIRTUAL in method: com.google.android.gms.measurement.internal.zzcq.run():void, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                	... 26 more
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000a: INVOKE  (r0v7 com.google.android.gms.measurement.internal.zzbw) = (wrap: com.google.android.gms.measurement.internal.zzfn
                  0x0006: INVOKE  (r0v6 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
                  0x0004: IGET  (r0v5 com.google.android.gms.measurement.internal.zzby) = (r5v0 'this' com.google.android.gms.measurement.internal.zzcq A[THIS]) com.google.android.gms.measurement.internal.zzcq.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC) com.google.android.gms.measurement.internal.zzfn.zzmh():com.google.android.gms.measurement.internal.zzbw type: VIRTUAL in method: com.google.android.gms.measurement.internal.zzcq.run():void, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	... 31 more
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: INVOKE  (r0v6 com.google.android.gms.measurement.internal.zzfn) = (wrap: com.google.android.gms.measurement.internal.zzby
                  0x0004: IGET  (r0v5 com.google.android.gms.measurement.internal.zzby) = (r5v0 'this' com.google.android.gms.measurement.internal.zzcq A[THIS]) com.google.android.gms.measurement.internal.zzcq.zzaqo com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC in method: com.google.android.gms.measurement.internal.zzcq.run():void, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	... 36 more
                Caused by: java.lang.ArrayIndexOutOfBoundsException
                */
            /*
                this = this;
                java.lang.String r0 = r5.zzaqr
                if (r0 != 0) goto L_0x0019
                com.google.android.gms.measurement.internal.zzby r0 = r5.zzaqo
                com.google.android.gms.measurement.internal.zzfn r0 = 
                // error: 0x0006: INVOKE  (r0 I:com.google.android.gms.measurement.internal.zzfn) = (r0 I:com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC
                com.google.android.gms.measurement.internal.zzbw r0 = r0.zzmh()
                com.google.android.gms.measurement.internal.zzdy r0 = r0.zzgm()
                java.lang.String r1 = r5.zzagj
                r2 = 0
                r0.zza(r1, r2)
                return
            L_0x0019:
                com.google.android.gms.measurement.internal.zzdx r1 = new com.google.android.gms.measurement.internal.zzdx
                java.lang.String r2 = r5.zzaeb
                long r3 = r5.zzaqs
                r1.<init>(r2, r0, r3)
                com.google.android.gms.measurement.internal.zzby r0 = r5.zzaqo
                com.google.android.gms.measurement.internal.zzfn r0 = 
                // error: 0x0024: INVOKE  (r0 I:com.google.android.gms.measurement.internal.zzfn) = (r0 I:com.google.android.gms.measurement.internal.zzby) com.google.android.gms.measurement.internal.zzby.zza(com.google.android.gms.measurement.internal.zzby):com.google.android.gms.measurement.internal.zzfn type: STATIC
                com.google.android.gms.measurement.internal.zzbw r0 = r0.zzmh()
                com.google.android.gms.measurement.internal.zzdy r0 = r0.zzgm()
                java.lang.String r2 = r5.zzagj
                r0.zza(r2, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzcq.run():void");
        }
    }
