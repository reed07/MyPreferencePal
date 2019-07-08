package com.facebook.ads.internal.x;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.facebook.ads.internal.w.b.v;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.b.y;
import com.facebook.ads.internal.w.b.z;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

public class a {
    private static final String a = "a";
    /* access modifiers changed from: private */
    public final View b;
    /* access modifiers changed from: private */
    public final int c;
    /* access modifiers changed from: private */
    public final int d;
    /* access modifiers changed from: private */
    public final WeakReference<C0023a> e;
    /* access modifiers changed from: private */
    public final Handler f;
    /* access modifiers changed from: private */
    public final boolean g;
    /* access modifiers changed from: private */
    public Runnable h;
    private int i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public b l;
    /* access modifiers changed from: private */
    public Map<String, Integer> m;
    /* access modifiers changed from: private */
    public long n;
    /* access modifiers changed from: private */
    public int o;

    /* renamed from: com.facebook.ads.internal.x.a$a reason: collision with other inner class name */
    public static abstract class C0023a {
        public abstract void a();

        public void b() {
        }
    }

    private static final class b extends y<a> {
        b(a aVar) {
            super(aVar);
        }

        public void run() {
            a aVar = (a) a();
            if (aVar != null) {
                View a = aVar.b;
                C0023a aVar2 = (C0023a) aVar.e.get();
                if (a != null && aVar2 != null) {
                    b a2 = a.a(a, aVar.c);
                    int i = 0;
                    if (a2.a()) {
                        
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002c: INVOKE  (r0v1 'aVar' com.facebook.ads.internal.x.a) com.facebook.ads.internal.x.a.d(com.facebook.ads.internal.x.a):int type: STATIC in method: com.facebook.ads.internal.x.a.b.run():void, dex: classes.dex
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
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                            	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:237)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:224)
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
                            java.lang.Object r0 = r8.a()
                            com.facebook.ads.internal.x.a r0 = (com.facebook.ads.internal.x.a) r0
                            if (r0 != 0) goto L_0x0009
                            return
                        L_0x0009:
                            android.view.View r1 = r0.b
                            java.lang.ref.WeakReference r2 = r0.e
                            java.lang.Object r2 = r2.get()
                            com.facebook.ads.internal.x.a$a r2 = (com.facebook.ads.internal.x.a.C0023a) r2
                            if (r1 == 0) goto L_0x00c9
                            if (r2 != 0) goto L_0x001d
                            goto L_0x00c9
                        L_0x001d:
                            int r3 = r0.c
                            com.facebook.ads.internal.x.b r1 = com.facebook.ads.internal.x.a.a(r1, r3)
                            boolean r3 = r1.a()
                            r4 = 0
                            if (r3 == 0) goto L_0x0030
                            
                            // error: 0x002c: INVOKE  (r0 I:com.facebook.ads.internal.x.a) com.facebook.ads.internal.x.a.d(com.facebook.ads.internal.x.a):int type: STATIC
                            goto L_0x0033
                        L_0x0030:
                            r0.o = r4
                        L_0x0033:
                            int r3 = r0.o
                            int r5 = r0.d
                            r6 = 1
                            if (r3 <= r5) goto L_0x0040
                            r3 = 1
                            goto L_0x0041
                        L_0x0040:
                            r3 = 0
                        L_0x0041:
                            com.facebook.ads.internal.x.b r5 = r0.l
                            if (r5 == 0) goto L_0x0053
                            com.facebook.ads.internal.x.b r5 = r0.l
                            boolean r5 = r5.a()
                            if (r5 == 0) goto L_0x0053
                            r5 = 1
                            goto L_0x0054
                        L_0x0053:
                            r5 = 0
                        L_0x0054:
                            if (r3 != 0) goto L_0x005c
                            boolean r7 = r1.a()
                            if (r7 != 0) goto L_0x005f
                        L_0x005c:
                            r0.l = r1
                        L_0x005f:
                            int r1 = r1.b()
                            java.lang.String r1 = java.lang.String.valueOf(r1)
                            monitor-enter(r0)
                            java.util.Map r7 = r0.m     // Catch:{ all -> 0x00c6 }
                            boolean r7 = r7.containsKey(r1)     // Catch:{ all -> 0x00c6 }
                            if (r7 == 0) goto L_0x0080     // Catch:{ all -> 0x00c6 }
                            java.util.Map r4 = r0.m     // Catch:{ all -> 0x00c6 }
                            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x00c6 }
                            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ all -> 0x00c6 }
                            int r4 = r4.intValue()     // Catch:{ all -> 0x00c6 }
                        L_0x0080:
                            java.util.Map r7 = r0.m     // Catch:{ all -> 0x00c6 }
                            int r4 = r4 + r6     // Catch:{ all -> 0x00c6 }
                            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00c6 }
                            r7.put(r1, r4)     // Catch:{ all -> 0x00c6 }
                            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                            if (r3 == 0) goto L_0x00a2
                            if (r5 != 0) goto L_0x00a2
                            long r3 = java.lang.System.currentTimeMillis()
                            r0.n = r3
                            r2.a()
                            boolean r1 = r0.g
                            if (r1 != 0) goto L_0x00a9
                            return
                        L_0x00a2:
                            if (r3 != 0) goto L_0x00a9
                            if (r5 == 0) goto L_0x00a9
                            r2.b()
                        L_0x00a9:
                            boolean r1 = r0.k
                            if (r1 != 0) goto L_0x00c5
                            java.lang.Runnable r1 = r0.h
                            if (r1 == 0) goto L_0x00c5
                            android.os.Handler r1 = r0.f
                            java.lang.Runnable r2 = r0.h
                            int r0 = r0.j
                            long r3 = (long) r0
                            r1.postDelayed(r2, r3)
                        L_0x00c5:
                            return
                        L_0x00c6:
                            r1 = move-exception
                            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
                            throw r1
                        L_0x00c9:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.x.a.b.run():void");
                    }
                }

                public a(View view, int i2, int i3, boolean z, C0023a aVar) {
                    this.f = new Handler();
                    this.i = 0;
                    this.j = 1000;
                    this.k = true;
                    this.l = new b(c.UNKNOWN);
                    this.m = new HashMap();
                    this.n = 0;
                    this.o = 0;
                    this.b = view;
                    if (this.b.getId() == -1) {
                        x.a(this.b);
                    }
                    this.c = i2;
                    this.e = new WeakReference<>(aVar);
                    this.g = z;
                    if (i3 < 0) {
                        i3 = 0;
                    }
                    this.d = i3;
                }

                public a(View view, int i2, C0023a aVar) {
                    this(view, i2, 0, false, aVar);
                }

                public a(View view, int i2, boolean z, C0023a aVar) {
                    this(view, i2, 0, z, aVar);
                }

                @VisibleForTesting
                static float a(View view) {
                    float alpha = view.getAlpha();
                    while (view.getParent() instanceof ViewGroup) {
                        view = (View) view.getParent();
                        float alpha2 = view.getAlpha();
                        if (alpha2 < BitmapDescriptorFactory.HUE_RED) {
                            alpha2 = BitmapDescriptorFactory.HUE_RED;
                        }
                        if (alpha2 > 1.0f) {
                            alpha2 = 1.0f;
                        }
                        alpha *= alpha2;
                    }
                    return alpha;
                }

                private static int a(Vector<Rect> vector) {
                    int size = vector.size();
                    int i2 = size * 2;
                    int[] iArr = new int[i2];
                    int[] iArr2 = new int[i2];
                    boolean[][] zArr = (boolean[][]) Array.newInstance(boolean.class, new int[]{i2, i2});
                    int i3 = 0;
                    int i4 = 0;
                    int i5 = 0;
                    while (i3 < size) {
                        Rect rect = (Rect) vector.elementAt(i3);
                        int i6 = i4 + 1;
                        iArr[i4] = rect.left;
                        int i7 = i5 + 1;
                        iArr2[i5] = rect.bottom;
                        int i8 = i6 + 1;
                        iArr[i6] = rect.right;
                        int i9 = i7 + 1;
                        iArr2[i7] = rect.top;
                        i3++;
                        i4 = i8;
                        i5 = i9;
                    }
                    Arrays.sort(iArr);
                    Arrays.sort(iArr2);
                    for (int i10 = 0; i10 < size; i10++) {
                        Rect rect2 = (Rect) vector.elementAt(i10);
                        int a2 = a(iArr, rect2.left);
                        int a3 = a(iArr, rect2.right);
                        int a4 = a(iArr2, rect2.top);
                        int a5 = a(iArr2, rect2.bottom);
                        for (int i11 = a2 + 1; i11 <= a3; i11++) {
                            for (int i12 = a4 + 1; i12 <= a5; i12++) {
                                zArr[i11][i12] = true;
                            }
                        }
                    }
                    int i13 = 0;
                    int i14 = 0;
                    while (i13 < i2) {
                        int i15 = i14;
                        for (int i16 = 0; i16 < i2; i16++) {
                            i15 += zArr[i13][i16] ? (iArr[i13] - iArr[i13 - 1]) * (iArr2[i16] - iArr2[i16 - 1]) : 0;
                        }
                        i13++;
                        i14 = i15;
                    }
                    return i14;
                }

                private static int a(int[] iArr, int i2) {
                    int length = iArr.length;
                    int i3 = 0;
                    while (i3 < length) {
                        int i4 = ((length - i3) / 2) + i3;
                        if (iArr[i4] == i2) {
                            return i4;
                        }
                        if (iArr[i4] > i2) {
                            length = i4;
                        } else {
                            i3 = i4 + 1;
                        }
                    }
                    return -1;
                }

                public static b a(View view, int i2) {
                    DisplayMetrics displayMetrics;
                    float f2;
                    int i3;
                    View view2 = view;
                    if (view2 == null) {
                        return new b(c.AD_IS_NULL);
                    }
                    if (view.getParent() == null) {
                        return new b(c.INVALID_PARENT);
                    }
                    if (!view.isShown()) {
                        return new b(c.INVALID_PARENT);
                    }
                    if (view.getWindowVisibility() != 0) {
                        return new b(c.INVALID_WINDOW);
                    }
                    if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("mAdView has invisible dimensions (w=");
                        sb.append(view.getMeasuredWidth());
                        sb.append(", h=");
                        sb.append(view.getMeasuredHeight());
                        sb.toString();
                        return new b(c.INVALID_DIMENSIONS);
                    } else if (a(view) < 0.9f) {
                        return new b(c.AD_IS_TRANSPARENT);
                    } else {
                        int width = view.getWidth();
                        int height = view.getHeight();
                        int[] iArr = new int[2];
                        try {
                            view2.getLocationOnScreen(iArr);
                            Rect rect = new Rect();
                            if (!view2.getGlobalVisibleRect(rect)) {
                                return new b(c.AD_IS_NOT_VISIBLE);
                            }
                            Context context = view.getContext();
                            if (VERSION.SDK_INT >= 17) {
                                Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                                displayMetrics = new DisplayMetrics();
                                defaultDisplay.getRealMetrics(displayMetrics);
                            } else {
                                displayMetrics = context.getResources().getDisplayMetrics();
                            }
                            Vector b2 = b(view);
                            int a2 = a(b2);
                            b2.add(rect);
                            float a3 = (((float) (a(b2) - a2)) * 1.0f) / ((float) (view.getMeasuredHeight() * view.getMeasuredWidth()));
                            boolean L = com.facebook.ads.internal.r.a.L(context);
                            int width2 = view.getWidth() * view.getHeight();
                            if (width2 > 0) {
                                f2 = 100.0f / ((float) width2);
                                i3 = i2;
                            } else {
                                i3 = i2;
                                f2 = 100.0f;
                            }
                            int[] iArr2 = iArr;
                            int max = (int) Math.max((double) i3, Math.ceil((double) f2));
                            float f3 = ((float) max) / 100.0f;
                            if (L) {
                                if (a3 < f3) {
                                    String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{Float.valueOf(a3), Float.valueOf(f3)});
                                    return new b(c.AD_INSUFFICIENT_VISIBLE_AREA, a3);
                                }
                            } else if (iArr2[0] < 0 || displayMetrics.widthPixels - iArr2[0] < width) {
                                return new b(c.AD_OFFSCREEN_HORIZONTALLY, a3);
                            } else {
                                int i4 = (int) ((((double) height) * (100.0d - ((double) max))) / 100.0d);
                                if (rect.top - iArr2[1] > i4) {
                                    return new b(c.AD_OFFSCREEN_TOP, a3);
                                }
                                if ((iArr2[1] + height) - rect.bottom > i4) {
                                    return new b(c.AD_OFFSCREEN_BOTTOM, a3);
                                }
                            }
                            if (!com.facebook.ads.internal.w.i.a.b(context)) {
                                return new b(c.SCREEN_NOT_INTERACTIVE, a3);
                            }
                            Map a4 = com.facebook.ads.internal.w.i.b.a(context);
                            if (z.b(a4)) {
                                return new b(c.AD_IS_OBSTRUCTED_BY_KEYGUARD, a3);
                            }
                            if (com.facebook.ads.internal.r.a.j(context) && z.a(a4)) {
                                return new b(c.AD_IN_LOCKSCREEN, a3, a4);
                            }
                            Float f4 = null;
                            if (com.facebook.ads.internal.r.a.H(context)) {
                                Activity a5 = com.facebook.ads.internal.w.a.b.a();
                                if (a5 != null) {
                                    View findViewById = a5.findViewById(16908290);
                                    if (findViewById == null) {
                                        findViewById = a5.getWindow().getDecorView().findViewById(16908290);
                                    }
                                    if (!(findViewById == null || view2 == null || view.getId() == -1)) {
                                        f4 = findViewById.findViewById(view.getId()) == null ? Float.valueOf(-1.0f) : d.a(findViewById, view2);
                                    }
                                }
                            }
                            if (f4 != null) {
                                if (f4.floatValue() == -1.0f) {
                                    return new b(c.AD_IS_NOT_IN_ACTIVITY);
                                }
                                if (f4.floatValue() == BitmapDescriptorFactory.HUE_RED) {
                                    return new b(c.AD_IS_NOT_VISIBLE);
                                }
                            }
                            if (!com.facebook.ads.internal.r.a.I(context) || f4 == null || f4.floatValue() >= f3) {
                                return new b(c.IS_VIEWABLE, a3, a4);
                            }
                            String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{f4, Float.valueOf(f3)});
                            return new b(c.AD_INSUFFICIENT_VISIBLE_AREA, a3, a4);
                        } catch (NullPointerException unused) {
                            return new b(c.INVALID_DIMENSIONS);
                        }
                    }
                }

                private static Vector<Rect> b(View view) {
                    Vector<Rect> vector = new Vector<>();
                    if (!(view.getParent() instanceof ViewGroup)) {
                        return vector;
                    }
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    int indexOfChild = viewGroup.indexOfChild(view);
                    while (true) {
                        indexOfChild++;
                        if (indexOfChild < viewGroup.getChildCount()) {
                            vector.addAll(c(viewGroup.getChildAt(indexOfChild)));
                        } else {
                            vector.addAll(b((View) viewGroup));
                            return vector;
                        }
                    }
                }

                private static Vector<Rect> c(View view) {
                    Vector<Rect> vector = new Vector<>();
                    if (view.isShown() && (VERSION.SDK_INT < 11 || view.getAlpha() > BitmapDescriptorFactory.HUE_RED)) {
                        if (view instanceof ViewGroup) {
                            if (view.getBackground() == null || (VERSION.SDK_INT >= 19 && view.getBackground().getAlpha() <= 0)) {
                                ViewGroup viewGroup = (ViewGroup) view;
                                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                                    vector.addAll(c(viewGroup.getChildAt(i2)));
                                }
                                return vector;
                            }
                        }
                        Rect rect = new Rect();
                        if (view.getGlobalVisibleRect(rect)) {
                            vector.add(rect);
                        }
                    }
                    return vector;
                }

                public synchronized void a() {
                    if (this.h != null) {
                        c();
                    }
                    this.h = new b(this);
                    this.f.postDelayed(this.h, (long) this.i);
                    this.k = false;
                    this.o = 0;
                    this.l = new b(c.UNKNOWN);
                    this.m = new HashMap();
                }

                public void a(int i2) {
                    this.i = i2;
                }

                public synchronized void a(Map<String, String> map) {
                    map.put("vrc", String.valueOf(this.l.b()));
                    map.put("vp", String.valueOf(this.l.c()));
                    map.put("vh", new JSONObject(this.m).toString());
                    map.put("vt", v.b(this.n));
                    map.putAll(this.l.d());
                }

                public void b(int i2) {
                    this.j = i2;
                }

                public synchronized boolean b() {
                    return this.k;
                }

                public synchronized void c() {
                    this.f.removeCallbacks(this.h);
                    this.h = null;
                    this.k = true;
                    this.o = 0;
                }

                public synchronized String d() {
                    StringBuilder sb;
                    c cVar = c.values()[this.l.b()];
                    sb = new StringBuilder();
                    sb.append(cVar.toString());
                    sb.append(String.format(Locale.US, " (%.1f%%)", new Object[]{Float.valueOf(this.l.c() * 100.0f)}));
                    return sb.toString();
                }
            }
