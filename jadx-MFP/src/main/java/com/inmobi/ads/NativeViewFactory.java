package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.squareup.picasso.Callback;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

class NativeViewFactory {
    private static final String a = "NativeViewFactory";
    @NonNull
    private static final Map<Class, Integer> c;
    @Nullable
    private static volatile WeakReference<NativeViewFactory> e = null;
    /* access modifiers changed from: private */
    public static WeakReference<Context> f = null;
    private static int g = 1;
    private static int h = 1;
    /* access modifiers changed from: private */
    public int b;
    @NonNull
    private Map<Integer, c> d = new HashMap();

    static class PicassoCallback implements Callback {
        private WeakReference<Context> a;
        private WeakReference<ImageView> b;
        private ak c;

        public void onSuccess() {
        }

        PicassoCallback(Context context, ImageView imageView, ak akVar) {
            this.a = new WeakReference<>(context);
            this.b = new WeakReference<>(imageView);
            this.c = akVar;
        }

        public void onError() {
            NativeViewFactory.a((Context) this.a.get(), (ImageView) this.b.get(), this.c);
        }
    }

    private static final class a implements Runnable {
        private WeakReference<Context> a;
        private WeakReference<ImageView> b;

        a(Context context, ImageView imageView) {
            this.a = new WeakReference<>(context);
            this.b = new WeakReference<>(imageView);
        }

        public final void run() {
            Context context = (Context) this.a.get();
            ImageView imageView = (ImageView) this.b.get();
            if (context != null && imageView != null) {
                NativeViewFactory.b(context, imageView);
            }
        }
    }

    @SuppressLint({"AppCompatCustomView"})
    private static final class b extends TextView {
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public b(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public final void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            int lineHeight = getLineHeight() > 0 ? i2 / getLineHeight() : 0;
            if (lineHeight > 0) {
                setSingleLine(false);
                setLines(lineHeight);
            }
            if (lineHeight == 1) {
                setSingleLine();
            }
        }
    }

    private abstract class c {
        private int a = 0;
        @NonNull
        LinkedList<View> b = new LinkedList<>();
        private int d = 0;

        /* access modifiers changed from: protected */
        public abstract View a(@NonNull Context context);

        public c() {
        }

        public boolean a(@NonNull View view) {
            NativeViewFactory.b(view);
            view.setOnClickListener(null);
            this.b.addLast(view);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0016: INVOKE  (wrap: com.inmobi.ads.NativeViewFactory
                  0x0014: IGET  (r2v1 com.inmobi.ads.NativeViewFactory) = (r1v0 'this' com.inmobi.ads.NativeViewFactory$c A[THIS]) com.inmobi.ads.NativeViewFactory.c.c com.inmobi.ads.NativeViewFactory) com.inmobi.ads.NativeViewFactory.a(com.inmobi.ads.NativeViewFactory):int type: STATIC in method: com.inmobi.ads.NativeViewFactory.c.a(android.view.View):boolean, dex: classes3.dex
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
                	... 22 more
                Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.Class.forName0(Native Method)
                	at java.base/java.lang.Class.forName(Unknown Source)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                	... 40 more
                */
            /*
                this = this;
                com.inmobi.ads.NativeViewFactory.b(r2)
                r0 = 0
                r2.setOnClickListener(r0)
                java.util.LinkedList<android.view.View> r0 = r1.b
                r0.addLast(r2)
                r0 = 1065353216(0x3f800000, float:1.0)
                r2.setScaleX(r0)
                r2.setScaleY(r0)
                com.inmobi.ads.NativeViewFactory r2 = com.inmobi.ads.NativeViewFactory.this
                
                // error: 0x0016: INVOKE  (r2 I:com.inmobi.ads.NativeViewFactory) com.inmobi.ads.NativeViewFactory.a(com.inmobi.ads.NativeViewFactory):int type: STATIC
                r2 = 1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeViewFactory.c.a(android.view.View):boolean");
        }

        public final View a(@NonNull Context context, ak akVar, c cVar) {
            View view;
            NativeViewFactory.f = new WeakReference(context);
            if (this.b.isEmpty()) {
                this.a++;
                view = a(context);
            } else {
                this.d++;
                view = (View) this.b.removeFirst();
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002b: INVOKE  (wrap: com.inmobi.ads.NativeViewFactory
                      0x0029: IGET  (r0v3 com.inmobi.ads.NativeViewFactory) = (r1v0 'this' com.inmobi.ads.NativeViewFactory$c A[THIS]) com.inmobi.ads.NativeViewFactory.c.c com.inmobi.ads.NativeViewFactory) com.inmobi.ads.NativeViewFactory.b(com.inmobi.ads.NativeViewFactory):int type: STATIC in method: com.inmobi.ads.NativeViewFactory.c.a(android.content.Context, com.inmobi.ads.ak, com.inmobi.ads.c):android.view.View, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:148)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
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
                    	... 27 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 45 more
                    */
                /*
                    this = this;
                    java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
                    r0.<init>(r2)
                    com.inmobi.ads.NativeViewFactory.f = r0
                    java.util.LinkedList<android.view.View> r0 = r1.b
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L_0x001b
                    int r0 = r1.a
                    int r0 = r0 + 1
                    r1.a = r0
                    android.view.View r2 = r1.a(r2)
                    goto L_0x002e
                L_0x001b:
                    int r2 = r1.d
                    int r2 = r2 + 1
                    r1.d = r2
                    java.util.LinkedList<android.view.View> r2 = r1.b
                    java.lang.Object r2 = r2.removeFirst()
                    android.view.View r2 = (android.view.View) r2
                    com.inmobi.ads.NativeViewFactory r0 = com.inmobi.ads.NativeViewFactory.this
                    
                    // error: 0x002b: INVOKE  (r0 I:com.inmobi.ads.NativeViewFactory) com.inmobi.ads.NativeViewFactory.b(com.inmobi.ads.NativeViewFactory):int type: STATIC
                L_0x002e:
                    r1.a(r2, r3, r4)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeViewFactory.c.a(android.content.Context, com.inmobi.ads.ak, com.inmobi.ads.c):android.view.View");
            }

            public String toString() {
                StringBuilder sb = new StringBuilder("Size:");
                sb.append(this.b.size());
                sb.append(" Miss Count:");
                sb.append(this.a);
                sb.append(" Hit Count:");
                sb.append(this.d);
                return sb.toString();
            }

            /* access modifiers changed from: protected */
            public void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                view.setVisibility(akVar.x);
                view.setOnClickListener(null);
            }
        }

        static {
            HashMap hashMap = new HashMap();
            c = hashMap;
            hashMap.put(aw.class, Integer.valueOf(0));
            c.put(bp.class, Integer.valueOf(1));
            c.put(bo.class, Integer.valueOf(2));
            c.put(NativeContainerLayout.class, Integer.valueOf(3));
            c.put(ImageView.class, Integer.valueOf(6));
            c.put(NativeVideoWrapper.class, Integer.valueOf(7));
            c.put(b.class, Integer.valueOf(4));
            c.put(Button.class, Integer.valueOf(5));
            c.put(NativeTimerView.class, Integer.valueOf(8));
            c.put(RenderView.class, Integer.valueOf(9));
            c.put(GifView.class, Integer.valueOf(10));
        }

        static void a(int i) {
            g = i;
        }

        static void b(int i) {
            h = i;
        }

        static int c(int i) {
            Context context = (Context) f.get();
            if (context != null && (context instanceof InMobiAdActivity)) {
                return i;
            }
            int i2 = g;
            if (i2 == 0) {
                return i;
            }
            return (int) (((double) i) * ((((double) i2) * 1.0d) / ((double) h)));
        }

        @SuppressLint({"UseSparseArrays"})
        private NativeViewFactory(Context context) {
            f = new WeakReference<>(context);
            this.d.put(Integer.valueOf(0), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new aw(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a(view, akVar.c);
                }
            });
            this.d.put(Integer.valueOf(3), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new NativeContainerLayout(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a(view, akVar.c);
                }
            });
            this.d.put(Integer.valueOf(1), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new bp(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a(view, akVar.c);
                }

                public final boolean a(@NonNull View view) {
                    ((bp) view).a = null;
                    return super.a(view);
                }
            });
            this.d.put(Integer.valueOf(2), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new bo(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a(view, akVar.c);
                }
            });
            this.d.put(Integer.valueOf(6), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new ImageView(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a((ImageView) view, akVar);
                }

                public final boolean a(@NonNull View view) {
                    if (!(view instanceof ImageView)) {
                        return false;
                    }
                    ((ImageView) view).setImageDrawable(null);
                    return super.a(view);
                }
            });
            this.d.put(Integer.valueOf(10), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new GifView(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a((GifView) view, akVar);
                }

                public final boolean a(@NonNull View view) {
                    if (!(view instanceof GifView)) {
                        return false;
                    }
                    ((GifView) view).setGif(null);
                    return super.a(view);
                }
            });
            this.d.put(Integer.valueOf(7), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new NativeVideoWrapper(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a((NativeVideoWrapper) view, akVar);
                }

                @TargetApi(15)
                public final boolean a(@NonNull View view) {
                    if (!(view instanceof NativeVideoWrapper)) {
                        return false;
                    }
                    NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) view;
                    nativeVideoWrapper.getProgressBar().setVisibility(8);
                    nativeVideoWrapper.getPoster().setImageBitmap(null);
                    nativeVideoWrapper.getVideoView().a();
                    return super.a(view);
                }
            });
            this.d.put(Integer.valueOf(4), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new b(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a((TextView) view, akVar);
                }

                public final boolean a(@NonNull View view) {
                    if (!(view instanceof TextView)) {
                        return false;
                    }
                    NativeViewFactory.a((TextView) view);
                    return super.a(view);
                }
            });
            this.d.put(Integer.valueOf(5), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new Button(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.b((Button) view, akVar);
                }

                public final boolean a(@NonNull View view) {
                    if (!(view instanceof Button)) {
                        return false;
                    }
                    NativeViewFactory.a((TextView) (Button) view);
                    return super.a(view);
                }
            });
            this.d.put(Integer.valueOf(8), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new NativeTimerView(context.getApplicationContext());
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a(NativeViewFactory.this, (NativeTimerView) view, akVar);
                }

                public final boolean a(@NonNull View view) {
                    return (view instanceof NativeTimerView) && super.a(view);
                }
            });
            this.d.put(Integer.valueOf(9), new c() {
                /* access modifiers changed from: protected */
                public final View a(@NonNull Context context) {
                    return new RenderView(context.getApplicationContext(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), null, null);
                }

                /* access modifiers changed from: protected */
                public final void a(@NonNull View view, @NonNull ak akVar, @NonNull c cVar) {
                    super.a(view, akVar, cVar);
                    NativeViewFactory.a((RenderView) view, akVar, cVar);
                }

                public final boolean a(@NonNull View view) {
                    return (view instanceof RenderView) && !((RenderView) view).v && super.a(view);
                }
            });
        }

        public static NativeViewFactory a(Context context) {
            NativeViewFactory nativeViewFactory;
            NativeViewFactory nativeViewFactory2;
            NativeViewFactory nativeViewFactory3 = null;
            if (e == null) {
                nativeViewFactory = null;
            } else {
                nativeViewFactory = (NativeViewFactory) e.get();
            }
            if (nativeViewFactory == null) {
                synchronized (NativeViewFactory.class) {
                    if (e != null) {
                        nativeViewFactory3 = (NativeViewFactory) e.get();
                    }
                    if (nativeViewFactory3 == null) {
                        nativeViewFactory2 = new NativeViewFactory(context);
                        e = new WeakReference<>(nativeViewFactory2);
                    } else {
                        nativeViewFactory2 = nativeViewFactory3;
                    }
                }
            }
            return nativeViewFactory;
        }

        public final void a(@NonNull View view) {
            if ((view instanceof aw) || (view instanceof NativeContainerLayout)) {
                NativeContainerLayout nativeContainerLayout = (NativeContainerLayout) view;
                if (nativeContainerLayout.getChildCount() != 0) {
                    Stack stack = new Stack();
                    stack.push(nativeContainerLayout);
                    while (!stack.isEmpty()) {
                        NativeContainerLayout nativeContainerLayout2 = (NativeContainerLayout) stack.pop();
                        for (int childCount = nativeContainerLayout2.getChildCount() - 1; childCount >= 0; childCount--) {
                            View childAt = nativeContainerLayout2.getChildAt(childCount);
                            nativeContainerLayout2.removeViewAt(childCount);
                            if (childAt instanceof NativeContainerLayout) {
                                stack.push((NativeContainerLayout) childAt);
                            } else {
                                c(childAt);
                            }
                        }
                        c((View) nativeContainerLayout2);
                    }
                    return;
                }
            }
            c(view);
        }

        private void c(@NonNull View view) {
            int intValue = ((Integer) c.get(view.getClass())).intValue();
            if (-1 == intValue) {
                new StringBuilder("View type unknown, ignoring recycle:").append(view);
                return;
            }
            c cVar = (c) this.d.get(Integer.valueOf(intValue));
            if (cVar == null) {
                StringBuilder sb = new StringBuilder("Unsupported AssetType:");
                sb.append(intValue);
                sb.append(" failed to recycle view");
                return;
            }
            if (this.b >= 300) {
                c b2 = b();
                if (b2 != null && b2.b.size() > 0) {
                    b2.b.removeFirst();
                }
            }
            cVar.a(view);
        }

        @Nullable
        private c b() {
            int i = 0;
            c cVar = null;
            for (Entry entry : this.d.entrySet()) {
                if (((c) entry.getValue()).b.size() > i) {
                    c cVar2 = (c) entry.getValue();
                    cVar = cVar2;
                    i = cVar2.b.size();
                }
            }
            return cVar;
        }

        /* access modifiers changed from: private */
        public static void b(Context context, ImageView imageView) {
            Bitmap bitmap;
            if (imageView.getDrawable() == null) {
                float f2 = com.inmobi.commons.core.utilities.b.c.a().c;
                CustomView customView = new CustomView(context, f2, 0);
                if (VERSION.SDK_INT < 28) {
                    customView.layout(0, 0, (int) (((float) c(40)) * f2), (int) (((float) c(40)) * f2));
                    customView.setDrawingCacheEnabled(true);
                    customView.buildDrawingCache();
                    bitmap = customView.getDrawingCache();
                } else {
                    customView.layout(0, 0, (int) (((float) c(40)) * f2), (int) (((float) c(40)) * f2));
                    bitmap = Bitmap.createBitmap((int) (((float) c(40)) * f2), (int) (((float) c(40)) * f2), Config.ARGB_8888);
                    customView.draw(new Canvas(bitmap));
                }
                imageView.setImageBitmap(bitmap);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x0054  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x005d  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x005f A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void a(@android.support.annotation.NonNull android.widget.TextView r9, java.lang.String[] r10) {
            /*
                int r0 = r9.getPaintFlags()
                int r1 = r10.length
                r2 = 0
                r4 = r0
                r0 = 0
                r3 = 0
            L_0x0009:
                if (r0 >= r1) goto L_0x0062
                r5 = r10[r0]
                r6 = -1
                int r7 = r5.hashCode()
                r8 = -1178781136(0xffffffffb9bd3a30, float:-3.6092242E-4)
                if (r7 == r8) goto L_0x0045
                r8 = -1026963764(0xffffffffc2c9c6cc, float:-100.888275)
                if (r7 == r8) goto L_0x003b
                r8 = -891985998(0xffffffffcad55fb2, float:-6991833.0)
                if (r7 == r8) goto L_0x0031
                r8 = 3029637(0x2e3a85, float:4.245426E-39)
                if (r7 == r8) goto L_0x0027
                goto L_0x004f
            L_0x0027:
                java.lang.String r7 = "bold"
                boolean r5 = r5.equals(r7)
                if (r5 == 0) goto L_0x004f
                r5 = 0
                goto L_0x0050
            L_0x0031:
                java.lang.String r7 = "strike"
                boolean r5 = r5.equals(r7)
                if (r5 == 0) goto L_0x004f
                r5 = 2
                goto L_0x0050
            L_0x003b:
                java.lang.String r7 = "underline"
                boolean r5 = r5.equals(r7)
                if (r5 == 0) goto L_0x004f
                r5 = 3
                goto L_0x0050
            L_0x0045:
                java.lang.String r7 = "italic"
                boolean r5 = r5.equals(r7)
                if (r5 == 0) goto L_0x004f
                r5 = 1
                goto L_0x0050
            L_0x004f:
                r5 = -1
            L_0x0050:
                switch(r5) {
                    case 0: goto L_0x005d;
                    case 1: goto L_0x005a;
                    case 2: goto L_0x0057;
                    case 3: goto L_0x0054;
                    default: goto L_0x0053;
                }
            L_0x0053:
                goto L_0x005f
            L_0x0054:
                r4 = r4 | 8
                goto L_0x005f
            L_0x0057:
                r4 = r4 | 16
                goto L_0x005f
            L_0x005a:
                r3 = r3 | 2
                goto L_0x005f
            L_0x005d:
                r3 = r3 | 1
            L_0x005f:
                int r0 = r0 + 1
                goto L_0x0009
            L_0x0062:
                android.graphics.Typeface r10 = android.graphics.Typeface.DEFAULT
                r9.setTypeface(r10, r3)
                r9.setPaintFlags(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeViewFactory.a(android.widget.TextView, java.lang.String[]):void");
        }

        @TargetApi(21)
        static void a(View view, @NonNull al alVar) {
            int parseColor = Color.parseColor("#00000000");
            try {
                parseColor = Color.parseColor(alVar.e());
            } catch (IllegalArgumentException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
            view.setBackgroundColor(parseColor);
            if (AbstractEvent.LINE.equals(alVar.a())) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(parseColor);
                if ("curved".equals(alVar.b())) {
                    gradientDrawable.setCornerRadius(alVar.c());
                }
                int parseColor2 = Color.parseColor("#ff000000");
                try {
                    parseColor2 = Color.parseColor(alVar.d());
                } catch (IllegalArgumentException e3) {
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                }
                gradientDrawable.setStroke(1, parseColor2);
                if (VERSION.SDK_INT < 16) {
                    view.setBackgroundDrawable(gradientDrawable);
                    return;
                }
                view.setBackground(gradientDrawable);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
            if (r0.equals(com.facebook.share.internal.ShareConstants.VIDEO_URL) != false) goto L_0x008b;
         */
        @android.support.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final android.view.View a(@android.support.annotation.NonNull android.content.Context r12, @android.support.annotation.NonNull com.inmobi.ads.ak r13, @android.support.annotation.NonNull com.inmobi.ads.c r14) {
            /*
                r11 = this;
                boolean r0 = r13 instanceof com.inmobi.ads.am
                r1 = 3
                r2 = 2
                r3 = 5
                r4 = 7
                r5 = 6
                r6 = 4
                r7 = 0
                r8 = 1
                r9 = -1
                if (r0 == 0) goto L_0x0031
                r0 = r13
                com.inmobi.ads.am r0 = (com.inmobi.ads.am) r0
                java.lang.String r3 = "root"
                java.lang.String r4 = r0.d
                boolean r3 = r3.equalsIgnoreCase(r4)
                if (r3 == 0) goto L_0x001d
                r1 = 0
                goto L_0x00a0
            L_0x001d:
                java.lang.String r3 = "card_scrollable"
                java.lang.String r4 = r0.d
                boolean r3 = r3.equalsIgnoreCase(r4)
                if (r3 == 0) goto L_0x00a0
                int r0 = r0.A
                if (r0 == r8) goto L_0x002e
                r1 = 1
                goto L_0x00a0
            L_0x002e:
                r1 = 2
                goto L_0x00a0
            L_0x0031:
                java.lang.String r0 = r13.b
                int r10 = r0.hashCode()
                switch(r10) {
                    case 67056: goto L_0x0080;
                    case 70564: goto L_0x0076;
                    case 2241657: goto L_0x006c;
                    case 2571565: goto L_0x0062;
                    case 69775675: goto L_0x0058;
                    case 79826725: goto L_0x004e;
                    case 81665115: goto L_0x0045;
                    case 1942407129: goto L_0x003b;
                    default: goto L_0x003a;
                }
            L_0x003a:
                goto L_0x008a
            L_0x003b:
                java.lang.String r1 = "WEBVIEW"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x008a
                r1 = 6
                goto L_0x008b
            L_0x0045:
                java.lang.String r2 = "VIDEO"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x008a
                goto L_0x008b
            L_0x004e:
                java.lang.String r1 = "TIMER"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x008a
                r1 = 5
                goto L_0x008b
            L_0x0058:
                java.lang.String r1 = "IMAGE"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x008a
                r1 = 1
                goto L_0x008b
            L_0x0062:
                java.lang.String r1 = "TEXT"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x008a
                r1 = 0
                goto L_0x008b
            L_0x006c:
                java.lang.String r1 = "ICON"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x008a
                r1 = 2
                goto L_0x008b
            L_0x0076:
                java.lang.String r1 = "GIF"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x008a
                r1 = 7
                goto L_0x008b
            L_0x0080:
                java.lang.String r1 = "CTA"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x008a
                r1 = 4
                goto L_0x008b
            L_0x008a:
                r1 = -1
            L_0x008b:
                switch(r1) {
                    case 0: goto L_0x009f;
                    case 1: goto L_0x009d;
                    case 2: goto L_0x009d;
                    case 3: goto L_0x009b;
                    case 4: goto L_0x0099;
                    case 5: goto L_0x0096;
                    case 6: goto L_0x0093;
                    case 7: goto L_0x0090;
                    default: goto L_0x008e;
                }
            L_0x008e:
                r1 = -1
                goto L_0x00a0
            L_0x0090:
                r1 = 10
                goto L_0x00a0
            L_0x0093:
                r1 = 9
                goto L_0x00a0
            L_0x0096:
                r1 = 8
                goto L_0x00a0
            L_0x0099:
                r1 = 5
                goto L_0x00a0
            L_0x009b:
                r1 = 7
                goto L_0x00a0
            L_0x009d:
                r1 = 6
                goto L_0x00a0
            L_0x009f:
                r1 = 4
            L_0x00a0:
                if (r9 != r1) goto L_0x00b5
                java.lang.StringBuilder r12 = new java.lang.StringBuilder
                java.lang.String r14 = "Unsupported AssetType:"
                r12.<init>(r14)
                java.lang.String r13 = r13.b
                r12.append(r13)
                java.lang.String r13 = " failed to instantiate view "
                r12.append(r13)
                r12 = 0
                return r12
            L_0x00b5:
                java.util.Map<java.lang.Integer, com.inmobi.ads.NativeViewFactory$c> r0 = r11.d
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                java.lang.Object r0 = r0.get(r1)
                com.inmobi.ads.NativeViewFactory$c r0 = (com.inmobi.ads.NativeViewFactory.c) r0
                android.view.View r12 = r0.a(r12, r13, r14)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeViewFactory.a(android.content.Context, com.inmobi.ads.ak, com.inmobi.ads.c):android.view.View");
        }

        /* access modifiers changed from: private */
        @TargetApi(17)
        public static Button b(@NonNull Button button, @NonNull ak akVar) {
            a aVar = (a) akVar.c;
            button.setLayoutParams(new LayoutParams(c(aVar.a.x), c(aVar.a.y)));
            button.setText((CharSequence) akVar.e);
            button.setTextSize(1, (float) c(aVar.h()));
            int parseColor = Color.parseColor("#ff000000");
            try {
                parseColor = Color.parseColor(aVar.i());
            } catch (IllegalArgumentException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
            button.setTextColor(parseColor);
            int parseColor2 = Color.parseColor("#00000000");
            try {
                parseColor2 = Color.parseColor(aVar.e());
            } catch (IllegalArgumentException e3) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            }
            button.setBackgroundColor(parseColor2);
            if (VERSION.SDK_INT >= 17) {
                button.setTextAlignment(4);
            }
            button.setGravity(17);
            a((TextView) button, aVar.j());
            a((View) button, (al) aVar);
            return button;
        }

        public static LayoutParams a(@NonNull ak akVar, @NonNull ViewGroup viewGroup) {
            Point point = akVar.c.a;
            Point point2 = akVar.c.c;
            LayoutParams layoutParams = new LayoutParams(c(point.x), c(point.y));
            if (viewGroup instanceof NativeContainerLayout) {
                layoutParams = new com.inmobi.ads.NativeContainerLayout.a(c(point.x), c(point.y));
                com.inmobi.ads.NativeContainerLayout.a aVar = (com.inmobi.ads.NativeContainerLayout.a) layoutParams;
                int c2 = c(point2.x);
                int c3 = c(point2.y);
                aVar.a = c2;
                aVar.b = c3;
            } else if (viewGroup instanceof LinearLayout) {
                layoutParams = new LinearLayout.LayoutParams(c(point.x), c(point.y));
                ((LinearLayout.LayoutParams) layoutParams).setMargins(c(point2.x), c(point2.y), 0, 0);
            } else if (viewGroup instanceof AbsListView) {
                return new AbsListView.LayoutParams(c(point.x), c(point.y));
            } else {
                if (viewGroup instanceof FrameLayout) {
                    layoutParams = new FrameLayout.LayoutParams(c(point.x), c(point.y));
                    ((FrameLayout.LayoutParams) layoutParams).setMargins(c(point2.x), c(point2.y), 0, 0);
                }
            }
            return layoutParams;
        }

        static /* synthetic */ void b(View view) {
            if (VERSION.SDK_INT < 16) {
                view.setBackgroundDrawable(null);
            } else {
                view.setBackground(null);
            }
        }

        static /* synthetic */ void a(ImageView imageView, ak akVar) {
            int i;
            int i2;
            int i3;
            String str = (String) akVar.e;
            if (str != null) {
                int c2 = c(akVar.c.a.x);
                int c3 = c(akVar.c.a.y);
                String f2 = akVar.c.f();
                char c4 = 65535;
                int hashCode = f2.hashCode();
                int i4 = 0;
                if (hashCode != -1362001767) {
                    if (hashCode == 727618043 && f2.equals("aspectFill")) {
                        c4 = 1;
                    }
                } else if (f2.equals("aspectFit")) {
                    c4 = 0;
                }
                switch (c4) {
                    case 0:
                        imageView.setScaleType(ScaleType.FIT_CENTER);
                        break;
                    case 1:
                        imageView.setScaleType(ScaleType.CENTER_CROP);
                        break;
                    default:
                        imageView.setScaleType(ScaleType.FIT_XY);
                        break;
                }
                Context context = (Context) f.get();
                if (context != null && c2 > 0 && c3 > 0 && str.trim().length() != 0) {
                    bh.a(context).load(str).into(imageView, new PicassoCallback(context, imageView, akVar));
                    if ("cross_button".equalsIgnoreCase(akVar.d) && akVar.r.length() == 0) {
                        new Handler().postDelayed(new a(context, imageView), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
                    }
                }
                ak akVar2 = akVar.t;
                if (akVar2 == null || !AbstractEvent.LINE.equals(akVar2.c.a())) {
                    i3 = 0;
                    i2 = 0;
                    i = 0;
                } else {
                    i3 = akVar2.c.c.x == akVar.c.c.x ? 1 : 0;
                    i2 = c(akVar2.c.a.x) == c(akVar.c.a.x) + akVar.c.c.x ? 1 : 0;
                    i = c(akVar2.c.c.y) == c(akVar.c.c.y) ? 1 : 0;
                    if (c(akVar2.c.a.y) == c(akVar.c.a.y) + c(akVar.c.c.y)) {
                        i4 = 1;
                    }
                    if (c(akVar2.c.a.x) == c(akVar.c.a.x)) {
                        i3 = 1;
                        i2 = 1;
                    }
                }
                if (VERSION.SDK_INT < 17) {
                    imageView.setPadding(i3, i, i2, i4);
                } else {
                    imageView.setPaddingRelative(i3, i, i2, i4);
                }
                a((View) imageView, akVar.c);
            }
        }

        static /* synthetic */ void a(GifView gifView, ak akVar) {
            gifView.setLayoutParams(new LayoutParams(c(akVar.c.a.x), c(akVar.c.a.y)));
            gifView.setGif(((aq) akVar).z);
            a((View) gifView, akVar.c);
        }

        static /* synthetic */ void a(NativeVideoWrapper nativeVideoWrapper, ak akVar) {
            if (VERSION.SDK_INT >= 15) {
                a((View) nativeVideoWrapper, akVar.c);
                if (akVar.w != null) {
                    nativeVideoWrapper.setPosterImage((Bitmap) akVar.w);
                }
                nativeVideoWrapper.getProgressBar().setVisibility(0);
            }
        }

        static /* synthetic */ void a(TextView textView, ak akVar) {
            a aVar = (a) akVar.c;
            textView.setLayoutParams(new LayoutParams(c(aVar.a.x), c(aVar.a.y)));
            textView.setText((CharSequence) akVar.e);
            textView.setTypeface(Typeface.DEFAULT);
            switch (aVar.p) {
                case 1:
                    textView.setGravity(8388629);
                    break;
                case 2:
                    textView.setGravity(17);
                    break;
                default:
                    textView.setGravity(8388627);
                    break;
            }
            textView.setTextSize(1, (float) c(aVar.h()));
            int parseColor = Color.parseColor("#ff000000");
            try {
                parseColor = Color.parseColor(aVar.i());
            } catch (IllegalArgumentException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
            textView.setTextColor(parseColor);
            int parseColor2 = Color.parseColor("#00000000");
            try {
                parseColor2 = Color.parseColor(aVar.e());
            } catch (IllegalArgumentException e3) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            }
            textView.setBackgroundColor(parseColor2);
            if (VERSION.SDK_INT >= 17) {
                textView.setTextAlignment(1);
            }
            a(textView, aVar.j());
            textView.setEllipsize(TruncateAt.END);
            textView.setHorizontallyScrolling(true);
            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
            a((View) textView, (al) aVar);
        }

        static /* synthetic */ void a(TextView textView) {
            textView.setTypeface(Typeface.DEFAULT, 0);
            textView.setPaintFlags(textView.getPaintFlags() & -17);
            textView.setPaintFlags(textView.getPaintFlags() & -9);
        }

        static /* synthetic */ void a(NativeViewFactory nativeViewFactory, final NativeTimerView nativeTimerView, ak akVar) {
            long j;
            nativeTimerView.setVisibility(4);
            final bb bbVar = (bb) akVar;
            com.inmobi.ads.ba.a aVar = bbVar.A.a;
            com.inmobi.ads.ba.a aVar2 = bbVar.A.b;
            if (aVar != null) {
                try {
                    j = aVar.a();
                } catch (Exception e2) {
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                    return;
                }
            } else {
                j = 0;
            }
            long a2 = aVar2 != null ? aVar2.a() : 0;
            if (a2 >= 0) {
                nativeTimerView.setTimerValue(a2);
                new Handler().postDelayed(new Runnable() {
                    public final void run() {
                        if (NativeViewFactory.f.get() != null) {
                            if (bbVar.z) {
                                nativeTimerView.setVisibility(0);
                            }
                            nativeTimerView.a();
                        }
                    }
                }, j * 1000);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A[Catch:{ Exception -> 0x005f }] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x005a A[Catch:{ Exception -> 0x005f }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static /* synthetic */ void a(com.inmobi.rendering.RenderView r4, com.inmobi.ads.ak r5, com.inmobi.ads.c r6) {
            /*
                r0 = r5
                com.inmobi.ads.bf r0 = (com.inmobi.ads.bf) r0     // Catch:{ Exception -> 0x005f }
                com.inmobi.rendering.RenderView$a r1 = com.inmobi.rendering.RenderView.a     // Catch:{ Exception -> 0x005f }
                r4.a(r1, r6)     // Catch:{ Exception -> 0x005f }
                r6 = 1
                r4.j = r6     // Catch:{ Exception -> 0x005f }
                java.lang.Object r5 = r5.e     // Catch:{ Exception -> 0x005f }
                java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x005f }
                java.lang.String r0 = r0.z     // Catch:{ Exception -> 0x005f }
                r1 = -1
                int r2 = r0.hashCode()     // Catch:{ Exception -> 0x005f }
                r3 = -1081286672(0xffffffffbf8cdff0, float:-1.100584)
                if (r2 == r3) goto L_0x0048
                r3 = 84303(0x1494f, float:1.18134E-40)
                if (r2 == r3) goto L_0x003e
                r3 = 2228139(0x21ffab, float:3.122288E-39)
                if (r2 == r3) goto L_0x0035
                r6 = 83774455(0x4fe4bf7, float:5.9784876E-36)
                if (r2 == r6) goto L_0x002b
                goto L_0x0052
            L_0x002b:
                java.lang.String r6 = "REF_HTML"
                boolean r6 = r0.equals(r6)     // Catch:{ Exception -> 0x005f }
                if (r6 == 0) goto L_0x0052
                r6 = 0
                goto L_0x0053
            L_0x0035:
                java.lang.String r2 = "HTML"
                boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x005f }
                if (r0 == 0) goto L_0x0052
                goto L_0x0053
            L_0x003e:
                java.lang.String r6 = "URL"
                boolean r6 = r0.equals(r6)     // Catch:{ Exception -> 0x005f }
                if (r6 == 0) goto L_0x0052
                r6 = 3
                goto L_0x0053
            L_0x0048:
                java.lang.String r6 = "REF_IFRAME"
                boolean r6 = r0.equals(r6)     // Catch:{ Exception -> 0x005f }
                if (r6 == 0) goto L_0x0052
                r6 = 2
                goto L_0x0053
            L_0x0052:
                r6 = -1
            L_0x0053:
                switch(r6) {
                    case 0: goto L_0x005a;
                    case 1: goto L_0x005a;
                    default: goto L_0x0056;
                }     // Catch:{ Exception -> 0x005f }
            L_0x0056:
                r4.b(r5)     // Catch:{ Exception -> 0x005f }
                goto L_0x005e
            L_0x005a:
                r4.a(r5)     // Catch:{ Exception -> 0x005f }
                return
            L_0x005e:
                return
            L_0x005f:
                r4 = move-exception
                com.inmobi.commons.core.a.a r5 = com.inmobi.commons.core.a.a.a()
                com.inmobi.commons.core.e.a r6 = new com.inmobi.commons.core.e.a
                r6.<init>(r4)
                r5.a(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeViewFactory.a(com.inmobi.rendering.RenderView, com.inmobi.ads.ak, com.inmobi.ads.c):void");
        }

        static /* synthetic */ void a(Context context, ImageView imageView, ak akVar) {
            if (!(context == null || imageView == null)) {
                String str = akVar.r;
                if ("cross_button".equalsIgnoreCase(akVar.d) && str.trim().length() == 0) {
                    b(context, imageView);
                }
            }
            HashMap hashMap = new HashMap();
            hashMap.put("[ERRORCODE]", "603");
            akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, (Map<String, String>) hashMap);
        }
    }
