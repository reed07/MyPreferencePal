package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* compiled from: IMASDK */
public class afj extends afi {
    private static Method c = null;
    private static Method d = null;
    private static Method e = null;
    private static Method f = null;
    private static Method g = null;
    private static Method h = null;
    private static Method i = null;
    private static Method j = null;
    private static Method k = null;
    private static Method l = null;
    private static Method m = null;
    private static Method n = null;
    private static Method o = null;
    private static String p = null;
    private static String q = null;
    private static String r = null;
    private static long s = 0;
    private static afq t = null;
    private static boolean u = false;

    protected static synchronized void a(String str, Context context, afo afo) {
        File file;
        Class loadClass;
        synchronized (afj.class) {
            if (!u) {
                try {
                    t = new afq(afo, null);
                    p = str;
                    byte[] a = t.a(qi.f());
                    byte[] a2 = t.a(a, qi.g());
                    File cacheDir = context.getCacheDir();
                    if (cacheDir == null) {
                        cacheDir = context.getDir("dex", 0);
                        if (cacheDir == null) {
                            throw new afk();
                        }
                    } else {
                        Context context2 = context;
                    }
                    File createTempFile = File.createTempFile("ads", ".jar", cacheDir);
                    FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                    fileOutputStream.write(a2, 0, a2.length);
                    fileOutputStream.close();
                    try {
                        DexClassLoader dexClassLoader = new DexClassLoader(createTempFile.getAbsolutePath(), cacheDir.getAbsolutePath(), null, context.getClassLoader());
                        Class loadClass2 = dexClassLoader.loadClass(b(a, qi.p()));
                        Class loadClass3 = dexClassLoader.loadClass(b(a, qi.D()));
                        Class loadClass4 = dexClassLoader.loadClass(b(a, qi.x()));
                        Class loadClass5 = dexClassLoader.loadClass(b(a, qi.t()));
                        Class loadClass6 = dexClassLoader.loadClass(b(a, qi.F()));
                        Class loadClass7 = dexClassLoader.loadClass(b(a, qi.r()));
                        Class loadClass8 = dexClassLoader.loadClass(b(a, qi.B()));
                        Class loadClass9 = dexClassLoader.loadClass(b(a, qi.z()));
                        Class loadClass10 = dexClassLoader.loadClass(b(a, qi.n()));
                        Class loadClass11 = dexClassLoader.loadClass(b(a, qi.l()));
                        Class loadClass12 = dexClassLoader.loadClass(b(a, qi.j()));
                        File file2 = cacheDir;
                        try {
                            loadClass = dexClassLoader.loadClass(b(a, qi.v()));
                            file = createTempFile;
                        } catch (Throwable th) {
                            th = th;
                            cacheDir = file2;
                            file = createTempFile;
                            String name = file.getName();
                            file.delete();
                            new File(cacheDir, name.replace(".jar", ".dex")).delete();
                            throw th;
                        }
                        try {
                            Class loadClass13 = dexClassLoader.loadClass(b(a, qi.h()));
                            Class cls = loadClass;
                            c = loadClass2.getMethod(b(a, qi.q()), new Class[0]);
                            d = loadClass3.getMethod(b(a, qi.E()), new Class[0]);
                            e = loadClass4.getMethod(b(a, qi.y()), new Class[0]);
                            f = loadClass5.getMethod(b(a, qi.u()), new Class[]{Context.class});
                            g = loadClass6.getMethod(b(a, qi.G()), new Class[]{MotionEvent.class, DisplayMetrics.class});
                            h = loadClass7.getMethod(b(a, qi.s()), new Class[]{Context.class});
                            i = loadClass8.getMethod(b(a, qi.C()), new Class[]{Context.class});
                            j = loadClass9.getMethod(b(a, qi.A()), new Class[]{Context.class});
                            k = loadClass10.getMethod(b(a, qi.o()), new Class[]{Context.class});
                            l = loadClass11.getMethod(b(a, qi.m()), new Class[]{Context.class});
                            m = loadClass12.getMethod(b(a, qi.k()), new Class[]{Context.class});
                            n = cls.getMethod(b(a, qi.w()), new Class[]{Context.class});
                            o = loadClass13.getMethod(b(a, qi.i()), new Class[]{Context.class});
                            String name2 = file.getName();
                            file.delete();
                            new File(file2, name2.replace(".jar", ".dex")).delete();
                            s = b().longValue();
                            u = true;
                        } catch (Throwable th2) {
                            th = th2;
                            cacheDir = file2;
                            String name3 = file.getName();
                            file.delete();
                            new File(cacheDir, name3.replace(".jar", ".dex")).delete();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        file = createTempFile;
                        String name32 = file.getName();
                        file.delete();
                        new File(cacheDir, name32.replace(".jar", ".dex")).delete();
                        throw th;
                    }
                } catch (FileNotFoundException e2) {
                    throw new afk(e2);
                } catch (IOException e3) {
                    throw new afk(e3);
                } catch (ClassNotFoundException e4) {
                    throw new afk(e4);
                } catch (afr e5) {
                    throw new afk(e5);
                } catch (NoSuchMethodException e6) {
                    throw new afk(e6);
                } catch (NullPointerException e7) {
                    throw new afk(e7);
                } catch (afk unused) {
                } catch (UnsupportedOperationException unused2) {
                }
            }
        }
    }

    protected afj(Context context, afo afo, afp afp) {
        super(context, afo, afp);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b6, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x008c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0096 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0010 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025 A[Catch:{ afk -> 0x0034, IOException -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[ExcHandler: IOException (unused java.io.IOException), SYNTHETIC, Splitter:B:7:0x0010] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r9) {
        /*
            r8 = this;
            r0 = 1
            java.lang.String r1 = c()     // Catch:{ afk -> 0x0008, IOException -> 0x00b5 }
            r8.a(r0, r1)     // Catch:{ afk -> 0x0008, IOException -> 0x00b5 }
        L_0x0008:
            r1 = 2
            java.lang.String r2 = a()     // Catch:{ afk -> 0x0010, IOException -> 0x00b5 }
            r8.a(r1, r2)     // Catch:{ afk -> 0x0010, IOException -> 0x00b5 }
        L_0x0010:
            java.lang.Long r1 = b()     // Catch:{ afk -> 0x0034, IOException -> 0x00b5 }
            long r1 = r1.longValue()     // Catch:{ afk -> 0x0034, IOException -> 0x00b5 }
            r3 = 25
            r8.a(r3, r1)     // Catch:{ afk -> 0x0034, IOException -> 0x00b5 }
            long r3 = s     // Catch:{ afk -> 0x0034, IOException -> 0x00b5 }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0034
            r3 = 17
            long r4 = s     // Catch:{ afk -> 0x0034, IOException -> 0x00b5 }
            long r1 = r1 - r4
            r8.a(r3, r1)     // Catch:{ afk -> 0x0034, IOException -> 0x00b5 }
            r1 = 23
            long r2 = s     // Catch:{ afk -> 0x0034, IOException -> 0x00b5 }
            r8.a(r1, r2)     // Catch:{ afk -> 0x0034, IOException -> 0x00b5 }
        L_0x0034:
            r1 = 0
            java.util.ArrayList r2 = g(r9)     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
            r3 = 31
            java.lang.Object r4 = r2.get(r1)     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
            long r4 = r4.longValue()     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
            r8.a(r3, r4)     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
            r3 = 32
            java.lang.Object r2 = r2.get(r0)     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
            long r4 = r2.longValue()     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
            r8.a(r3, r4)     // Catch:{ afk -> 0x0057, IOException -> 0x00b5 }
        L_0x0057:
            r2 = 33
            java.lang.Long r3 = d()     // Catch:{ afk -> 0x0064, IOException -> 0x00b5 }
            long r3 = r3.longValue()     // Catch:{ afk -> 0x0064, IOException -> 0x00b5 }
            r8.a(r2, r3)     // Catch:{ afk -> 0x0064, IOException -> 0x00b5 }
        L_0x0064:
            r2 = 27
            com.google.ads.interactivemedia.v3.internal.afo r3 = r8.b     // Catch:{ afk -> 0x006f, IOException -> 0x00b5 }
            java.lang.String r3 = a(r9, r3)     // Catch:{ afk -> 0x006f, IOException -> 0x00b5 }
            r8.a(r2, r3)     // Catch:{ afk -> 0x006f, IOException -> 0x00b5 }
        L_0x006f:
            r2 = 29
            com.google.ads.interactivemedia.v3.internal.afo r3 = r8.b     // Catch:{ afk -> 0x007a, IOException -> 0x00b5 }
            java.lang.String r3 = b(r9, r3)     // Catch:{ afk -> 0x007a, IOException -> 0x00b5 }
            r8.a(r2, r3)     // Catch:{ afk -> 0x007a, IOException -> 0x00b5 }
        L_0x007a:
            int[] r2 = h(r9)     // Catch:{ afk -> 0x008c, IOException -> 0x00b5 }
            r3 = 5
            r1 = r2[r1]     // Catch:{ afk -> 0x008c, IOException -> 0x00b5 }
            long r4 = (long) r1     // Catch:{ afk -> 0x008c, IOException -> 0x00b5 }
            r8.a(r3, r4)     // Catch:{ afk -> 0x008c, IOException -> 0x00b5 }
            r1 = 6
            r0 = r2[r0]     // Catch:{ afk -> 0x008c, IOException -> 0x00b5 }
            long r2 = (long) r0     // Catch:{ afk -> 0x008c, IOException -> 0x00b5 }
            r8.a(r1, r2)     // Catch:{ afk -> 0x008c, IOException -> 0x00b5 }
        L_0x008c:
            int r0 = i(r9)     // Catch:{ afk -> 0x0096, IOException -> 0x00b5 }
            r1 = 12
            long r2 = (long) r0     // Catch:{ afk -> 0x0096, IOException -> 0x00b5 }
            r8.a(r1, r2)     // Catch:{ afk -> 0x0096, IOException -> 0x00b5 }
        L_0x0096:
            int r0 = j(r9)     // Catch:{ afk -> 0x009f, IOException -> 0x00b5 }
            r1 = 3
            long r2 = (long) r0     // Catch:{ afk -> 0x009f, IOException -> 0x00b5 }
            r8.a(r1, r2)     // Catch:{ afk -> 0x009f, IOException -> 0x00b5 }
        L_0x009f:
            r0 = 34
            java.lang.String r1 = e(r9)     // Catch:{ afk -> 0x00a8, IOException -> 0x00b5 }
            r8.a(r0, r1)     // Catch:{ afk -> 0x00a8, IOException -> 0x00b5 }
        L_0x00a8:
            r0 = 35
            java.lang.Long r9 = f(r9)     // Catch:{ afk -> 0x00b6, IOException -> 0x00b5 }
            long r1 = r9.longValue()     // Catch:{ afk -> 0x00b6, IOException -> 0x00b5 }
            r8.a(r0, r1)     // Catch:{ afk -> 0x00b6, IOException -> 0x00b5 }
        L_0x00b5:
            return
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.afj.b(android.content.Context):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0070, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[ExcHandler: IOException (unused java.io.IOException), SYNTHETIC, Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 2
            java.lang.String r1 = a()     // Catch:{ afk -> 0x0008, IOException -> 0x006f }
            r6.a(r0, r1)     // Catch:{ afk -> 0x0008, IOException -> 0x006f }
        L_0x0008:
            r1 = 1
            java.lang.String r2 = c()     // Catch:{ afk -> 0x0010, IOException -> 0x006f }
            r6.a(r1, r2)     // Catch:{ afk -> 0x0010, IOException -> 0x006f }
        L_0x0010:
            r2 = 25
            java.lang.Long r3 = b()     // Catch:{ afk -> 0x001d, IOException -> 0x006f }
            long r3 = r3.longValue()     // Catch:{ afk -> 0x001d, IOException -> 0x006f }
            r6.a(r2, r3)     // Catch:{ afk -> 0x001d, IOException -> 0x006f }
        L_0x001d:
            r2 = 0
            android.util.DisplayMetrics r3 = r6.a     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            java.util.ArrayList r2 = a(r2, r3)     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            r3 = 14
            r4 = 0
            java.lang.Object r4 = r2.get(r4)     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            long r4 = r4.longValue()     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            r6.a(r3, r4)     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            r3 = 15
            java.lang.Object r1 = r2.get(r1)     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            long r4 = r1.longValue()     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            r6.a(r3, r4)     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            int r1 = r2.size()     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            r3 = 3
            if (r1 < r3) goto L_0x0059
            r1 = 16
            java.lang.Object r0 = r2.get(r0)     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            long r2 = r0.longValue()     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
            r6.a(r1, r2)     // Catch:{ afk -> 0x0059, IOException -> 0x006f }
        L_0x0059:
            r0 = 34
            java.lang.String r1 = e(r7)     // Catch:{ afk -> 0x0062, IOException -> 0x006f }
            r6.a(r0, r1)     // Catch:{ afk -> 0x0062, IOException -> 0x006f }
        L_0x0062:
            r0 = 35
            java.lang.Long r7 = f(r7)     // Catch:{ afk -> 0x0070, IOException -> 0x006f }
            long r1 = r7.longValue()     // Catch:{ afk -> 0x0070, IOException -> 0x006f }
            r6.a(r0, r1)     // Catch:{ afk -> 0x0070, IOException -> 0x006f }
        L_0x006f:
            return
        L_0x0070:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.afj.c(android.content.Context):void");
    }

    private static String a() throws afk {
        String str = p;
        if (str != null) {
            return str;
        }
        throw new afk();
    }

    private static Long b() throws afk {
        Method method = c;
        if (method != null) {
            try {
                return (Long) method.invoke(null, new Object[0]);
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    static String d(Context context) throws afk {
        Method method = h;
        if (method != null) {
            try {
                String str = (String) method.invoke(null, new Object[]{context});
                if (str != null) {
                    return str;
                }
                throw new afk();
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static String c() throws afk {
        Method method = e;
        if (method != null) {
            try {
                return (String) method.invoke(null, new Object[0]);
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static ArrayList<Long> a(MotionEvent motionEvent, DisplayMetrics displayMetrics) throws afk {
        Method method = g;
        if (method == null || motionEvent == null) {
            throw new afk();
        }
        try {
            return (ArrayList) method.invoke(null, new Object[]{motionEvent, displayMetrics});
        } catch (IllegalAccessException e2) {
            throw new afk(e2);
        } catch (InvocationTargetException e3) {
            throw new afk(e3);
        }
    }

    private static String e(Context context) throws afk {
        Method method = l;
        if (method != null) {
            try {
                return (String) method.invoke(null, new Object[]{context});
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static Long f(Context context) throws afk {
        Method method = m;
        if (method != null) {
            try {
                return (Long) method.invoke(null, new Object[]{context});
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static String a(Context context, afo afo) throws afk {
        String str = q;
        if (str != null) {
            return str;
        }
        Method method = f;
        if (method != null) {
            try {
                ByteBuffer byteBuffer = (ByteBuffer) method.invoke(null, new Object[]{context});
                if (byteBuffer != null) {
                    String a = afo.a(byteBuffer.array(), true);
                    q = a;
                    return a;
                }
                throw new afk();
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static ArrayList<Long> g(Context context) throws afk {
        Method method = j;
        if (method != null) {
            try {
                ArrayList<Long> arrayList = (ArrayList) method.invoke(null, new Object[]{context});
                if (arrayList != null && arrayList.size() == 2) {
                    return arrayList;
                }
                throw new afk();
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static String b(Context context, afo afo) throws afk {
        String str = r;
        if (str != null) {
            return str;
        }
        Method method = i;
        if (method != null) {
            try {
                ByteBuffer byteBuffer = (ByteBuffer) method.invoke(null, new Object[]{context});
                if (byteBuffer != null) {
                    String a = afo.a(byteBuffer.array(), true);
                    r = a;
                    return a;
                }
                throw new afk();
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static Long d() throws afk {
        Method method = d;
        if (method != null) {
            try {
                return (Long) method.invoke(null, new Object[0]);
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static int[] h(Context context) throws afk {
        Method method = k;
        if (method != null) {
            try {
                return (int[]) method.invoke(null, new Object[]{context});
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static int i(Context context) throws afk {
        Method method = n;
        if (method != null) {
            try {
                return ((Integer) method.invoke(null, new Object[]{context})).intValue();
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static int j(Context context) throws afk {
        Method method = o;
        if (method != null) {
            try {
                return ((Integer) method.invoke(null, new Object[]{context})).intValue();
            } catch (IllegalAccessException e2) {
                throw new afk(e2);
            } catch (InvocationTargetException e3) {
                throw new afk(e3);
            }
        } else {
            throw new afk();
        }
    }

    private static String b(byte[] bArr, String str) throws afk {
        try {
            return new String(t.a(bArr, str), "UTF-8");
        } catch (afr e2) {
            throw new afk(e2);
        } catch (UnsupportedEncodingException e3) {
            throw new afk(e3);
        }
    }
}
