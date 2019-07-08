package com.inmobi.commons.core.e;

import android.support.annotation.Nullable;
import com.inmobi.commons.core.b.d;
import com.inmobi.commons.core.b.e;
import com.inmobi.commons.core.configs.a;
import com.inmobi.commons.core.configs.b.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TelemetryComponent */
public class b implements e, c {
    public static AtomicBoolean b = new AtomicBoolean(false);
    private static final String c = "b";
    private static final Object d = new Object();
    private static volatile b e;
    private static Map<String, c> f;
    public ExecutorService a;
    private d g = new d();
    /* access modifiers changed from: private */
    public e h;
    private String i;
    /* access modifiers changed from: private */
    public d j;

    public static b a() {
        b bVar = e;
        if (bVar == null) {
            synchronized (d) {
                bVar = e;
                if (bVar == null) {
                    bVar = new b();
                    e = bVar;
                }
            }
        }
        return bVar;
    }

    public final void b() {
        b.set(false);
        com.inmobi.commons.core.configs.b.a().a((a) this.g, (c) this);
        a("telemetry", this.g.a);
        this.i = this.g.b;
        this.a.execute(new Runnable() {
            public final void run() {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.inmobi.commons.core.e.b
                      0x0000: IGET  (r0v0 com.inmobi.commons.core.e.b) = (r1v0 'this' com.inmobi.commons.core.e.b$1 A[THIS]) com.inmobi.commons.core.e.b.1.a com.inmobi.commons.core.e.b) com.inmobi.commons.core.e.b.a(com.inmobi.commons.core.e.b):void type: STATIC in method: com.inmobi.commons.core.e.b.1.run():void, dex: classes3.dex
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
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
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
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 55 more
                    */
                /*
                    this = this;
                    com.inmobi.commons.core.e.b r0 = com.inmobi.commons.core.e.b.this
                    
                    // error: 0x0002: INVOKE  (r0 I:com.inmobi.commons.core.e.b) com.inmobi.commons.core.e.b.a(com.inmobi.commons.core.e.b):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.e.b.AnonymousClass1.run():void");
            }
        });
    }

    private b() {
        f = new HashMap();
        a("telemetry", this.g.a);
        this.i = this.g.b;
        this.h = new e();
        this.a = Executors.newSingleThreadExecutor();
    }

    public final void a(String str, JSONObject jSONObject) {
        a(str, new c(str, jSONObject, this.g.a));
    }

    private void a(String str, c cVar) {
        if (str != null && !str.trim().equals("")) {
            if (cVar != null) {
                f.put(str, cVar);
            } else {
                f.put(str, new c(str, null, this.g.a));
            }
        }
    }

    @Nullable
    private static c c(f fVar) {
        a();
        String str = fVar.d;
        if (str == null || str.trim().equals("")) {
            return null;
        }
        return (c) f.get(str);
    }

    public static void a(String str, String str2, Map<String, Object> map) {
        try {
            f fVar = new f(str, str2);
            if (map != null && !map.isEmpty()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (Entry entry : map.entrySet()) {
                        jSONObject.put((String) entry.getKey(), entry.getValue());
                    }
                    fVar.f = jSONObject.toString();
                } catch (JSONException e2) {
                    StringBuilder sb = new StringBuilder("Error forming JSON payload for ");
                    sb.append(str2);
                    sb.append(" Error: ");
                    sb.append(e2);
                }
            }
            a().a(fVar);
        } catch (Exception e3) {
            StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
            sb2.append(e3.getMessage());
            sb2.append(")");
        }
    }

    public final void a(final f fVar) {
        c c2 = c(fVar);
        if (c2 == null || !c2.b || !this.g.a.b) {
            StringBuilder sb = new StringBuilder("Telemetry service is not enabled or registered for component: ");
            sb.append(fVar.d);
            sb.append("|| type = ");
            sb.append(fVar.c);
            sb.append(" Config :");
            sb.append(c2);
            return;
        }
        this.a.execute(new Runnable() {
            public final void run() {
                b.this.b(fVar);
                b.d(b.this);
            }
        });
    }

    public final void b(f fVar) {
        c c2 = c(fVar);
        if (c2 == null || !c2.b || !this.g.a.b) {
            StringBuilder sb = new StringBuilder("Telemetry service is not enabled or registered for component: ");
            sb.append(fVar.d);
            sb.append("|| type = ");
            sb.append(fVar.c);
            sb.append(" Config :");
            sb.append(c2);
            return;
        }
        this.h.b(this.g.f, "default");
        if ((this.h.a("default") + 1) - this.g.e >= 0) {
            e.a();
        }
        e.a(fVar);
    }

    public final com.inmobi.commons.core.b.c a(String str) {
        List<f> list;
        if (com.inmobi.commons.core.utilities.b.b.a() != 1) {
            list = e.a(this.g.h.b);
        } else {
            list = e.a(this.g.i.b);
        }
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (f fVar : list) {
                arrayList.add(Integer.valueOf(fVar.a));
            }
            String a2 = a(list);
            if (a2 != null) {
                return new com.inmobi.commons.core.b.c(arrayList, a2, true);
            }
        }
        return null;
    }

    @Nullable
    private static String a(List<f> list) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            hashMap.put("version", "2.0.0");
            hashMap.put("component", "telemetry");
            hashMap.put("mk-version", com.inmobi.commons.a.b.a());
            hashMap.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(hashMap);
            JSONArray jSONArray = new JSONArray();
            for (f fVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("eventId", fVar.b);
                jSONObject2.put("eventType", fVar.c);
                if (!fVar.a().trim().isEmpty()) {
                    jSONObject2.put("payload", fVar.a());
                }
                jSONObject2.put("componentType", fVar.d);
                jSONObject2.put("ts", fVar.e);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("telemetry", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public final void a(a aVar) {
        this.g = (d) aVar;
        this.i = this.g.b;
    }

    static /* synthetic */ void d(b bVar) {
        b bVar2 = bVar;
        if (!b.get()) {
            d dVar = bVar2.g;
            int i2 = dVar.d;
            long j2 = dVar.f;
            long j3 = dVar.c;
            long j4 = dVar.g;
            int i3 = dVar.i.b;
            int i4 = dVar.h.b;
            long j5 = dVar.i.a;
            long j6 = dVar.h.a;
            com.inmobi.commons.core.b.a aVar = r2;
            com.inmobi.commons.core.b.a aVar2 = new com.inmobi.commons.core.b.a(i2, j2, j3, j4, i3, i4, j5, j6);
            aVar.e = bVar2.i;
            aVar.b = "default";
            d dVar2 = bVar2.j;
            if (dVar2 == null) {
                bVar2.j = new d(bVar2.h, bVar2, aVar);
            } else {
                dVar2.a(aVar);
            }
            bVar2.j.a("default");
        }
    }
}
