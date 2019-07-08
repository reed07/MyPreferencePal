package com.uacf.core.util;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class BundleUtils {
    public static float getFloat(String str, float f, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return getFloat(bundle, str, f);
                }
            }
        }
        return f;
    }

    public static float getFloat(Bundle bundle, String str) {
        if (bundle != null && Strings.notEmpty(str)) {
            try {
                return bundle.getFloat(str);
            } catch (RuntimeException e) {
                Ln.e(e);
            }
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    public static float getFloat(Bundle bundle, String str, float f) {
        return containsKey(bundle, str) ? getFloat(bundle, str) : f;
    }

    public static String getString(String str, String str2, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return getString(bundle, str, str2);
                }
            }
        }
        return str2;
    }

    public static String getString(Bundle bundle, String str) {
        if (bundle != null && Strings.notEmpty(str)) {
            try {
                return bundle.getString(str);
            } catch (RuntimeException e) {
                Ln.e(e);
            }
        }
        return "";
    }

    public static List<String> getStringList(String str, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return getStringList(bundle, str);
                }
            }
        }
        return null;
    }

    public static List<String> getStringList(Bundle bundle, String str) {
        if (bundle != null && Strings.notEmpty(str)) {
            try {
                return bundle.getStringArrayList(str);
            } catch (RuntimeException e) {
                Ln.e(e);
            }
        }
        return null;
    }

    public static String getString(Bundle bundle, String str, String str2) {
        return containsKey(bundle, str) ? getString(bundle, str) : str2;
    }

    public static Integer getInt(String str, Integer num, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return Integer.valueOf(getInt(bundle, str, num.intValue()));
                }
            }
        }
        return num;
    }

    public static int getInt(Bundle bundle, String str) {
        if (bundle != null && Strings.notEmpty(str)) {
            try {
                return bundle.getInt(str);
            } catch (RuntimeException e) {
                Ln.e(e);
            }
        }
        return 0;
    }

    public static int getInt(Bundle bundle, String str, int i) {
        return containsKey(bundle, str) ? getInt(bundle, str) : i;
    }

    public static Double getDouble(String str, Double d, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return Double.valueOf(getDouble(bundle, str, d.doubleValue()));
                }
            }
        }
        return d;
    }

    public static double getDouble(Bundle bundle, String str, double d) {
        if (bundle != null && Strings.notEmpty(str)) {
            try {
                return bundle.getDouble(str, d);
            } catch (RuntimeException e) {
                Ln.e(e);
            }
        }
        return d;
    }

    public static Long getLong(String str, Long l, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return Long.valueOf(getLong(bundle, str, l.longValue()));
                }
            }
        }
        return l;
    }

    public static long getLong(Bundle bundle, String str) {
        if (bundle != null && Strings.notEmpty(str)) {
            try {
                return bundle.getLong(str);
            } catch (RuntimeException e) {
                Ln.e(e);
            }
        }
        return 0;
    }

    public static long getLong(Bundle bundle, String str, long j) {
        return containsKey(bundle, str) ? getLong(bundle, str) : j;
    }

    public static Boolean getBoolean(String str, Boolean bool, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return Boolean.valueOf(getBoolean(bundle, str, bool.booleanValue()));
                }
            }
        }
        return bool;
    }

    public static boolean getBoolean(Bundle bundle, String str) {
        if (bundle != null && Strings.notEmpty(str)) {
            try {
                return bundle.getBoolean(str);
            } catch (RuntimeException e) {
                Ln.e(e);
            }
        }
        return false;
    }

    public static boolean getBoolean(Bundle bundle, String str, boolean z) {
        return containsKey(bundle, str) ? getBoolean(bundle, str) : z;
    }

    public static <T extends Parcelable> T getParcelable(String str, T t, ClassLoader classLoader, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return getParcelable(bundle, str, t, classLoader);
                }
            }
        }
        return t;
    }

    public static <T extends Parcelable> T getParcelable(Bundle bundle, String str, ClassLoader classLoader) {
        return getParcelable(bundle, str, (T) null, classLoader);
    }

    public static <T extends Parcelable> T getParcelable(Bundle bundle, String str, T t, ClassLoader classLoader) {
        if (bundle != null && Strings.notEmpty(str)) {
            ClassLoader classLoader2 = null;
            if (classLoader != null) {
                try {
                    classLoader2 = bundle.getClassLoader();
                    bundle.setClassLoader(classLoader);
                } catch (RuntimeException e) {
                    Ln.e(e);
                    if (classLoader != null) {
                        bundle.setClassLoader(null);
                    }
                } catch (Throwable th) {
                    if (classLoader != null) {
                        bundle.setClassLoader(null);
                    }
                    throw th;
                }
            }
            T parcelable = bundle.getParcelable(str);
            if (classLoader != null) {
                bundle.setClassLoader(classLoader2);
            }
            return parcelable;
        }
        return t;
    }

    public static <T extends Parcelable> ArrayList<T> getParcelableArrayList(String str, ClassLoader classLoader, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return getParcelableArrayList(bundle, str, classLoader);
                }
            }
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [boolean] */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.ClassLoader] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.ClassLoader] */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.lang.ClassLoader] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8, types: [java.lang.ClassLoader] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v5
  assigns: []
  uses: []
  mth insns count: 34
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0032  */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends android.os.Parcelable> java.util.ArrayList<T> getParcelableArrayList(android.os.Bundle r2, java.lang.String r3, java.lang.ClassLoader r4) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0036
            boolean r1 = com.uacf.core.util.Strings.notEmpty(r3)
            if (r1 == 0) goto L_0x0036
            if (r4 == 0) goto L_0x0019
            java.lang.ClassLoader r1 = r2.getClassLoader()     // Catch:{ RuntimeException -> 0x0016, all -> 0x0013 }
            r2.setClassLoader(r4)     // Catch:{ RuntimeException -> 0x0026 }
            goto L_0x001a
        L_0x0013:
            r3 = move-exception
            r1 = r0
            goto L_0x0030
        L_0x0016:
            r3 = move-exception
            r1 = r0
            goto L_0x0027
        L_0x0019:
            r1 = r0
        L_0x001a:
            java.util.ArrayList r3 = r2.getParcelableArrayList(r3)     // Catch:{ RuntimeException -> 0x0026 }
            if (r4 == 0) goto L_0x0023
            r2.setClassLoader(r1)
        L_0x0023:
            return r3
        L_0x0024:
            r3 = move-exception
            goto L_0x0030
        L_0x0026:
            r3 = move-exception
        L_0x0027:
            com.uacf.core.util.Ln.e(r3)     // Catch:{ all -> 0x0024 }
            if (r4 == 0) goto L_0x0036
            r2.setClassLoader(r1)
            goto L_0x0036
        L_0x0030:
            if (r4 == 0) goto L_0x0035
            r2.setClassLoader(r1)
        L_0x0035:
            throw r3
        L_0x0036:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.BundleUtils.getParcelableArrayList(android.os.Bundle, java.lang.String, java.lang.ClassLoader):java.util.ArrayList");
    }

    public static <T extends Parcelable> ArrayList<T> getParcelableArrayAsList(Bundle bundle, String str, ClassLoader classLoader) {
        if (bundle != null && Strings.notEmpty(str)) {
            ClassLoader classLoader2 = null;
            if (classLoader != null) {
                try {
                    classLoader2 = bundle.getClassLoader();
                    bundle.setClassLoader(classLoader);
                } catch (RuntimeException e) {
                    Ln.e(e);
                    if (classLoader != null) {
                        bundle.setClassLoader(null);
                    }
                } catch (Throwable th) {
                    if (classLoader != null) {
                        bundle.setClassLoader(null);
                    }
                    throw th;
                }
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(str);
            ArrayList<T> arrayList = new ArrayList<>();
            if (parcelableArray != null) {
                for (Parcelable add : parcelableArray) {
                    arrayList.add(add);
                }
            }
            if (classLoader != null) {
                bundle.setClassLoader(classLoader2);
            }
            return arrayList;
        }
        return new ArrayList<>();
    }

    public static List<Integer> getIntegerArrayList(Bundle bundle, String str) {
        if (bundle == null || !Strings.notEmpty(str)) {
            return null;
        }
        return bundle.getIntegerArrayList(str);
    }

    public static <T extends Serializable> T getSerializable(Bundle bundle, String str, ClassLoader classLoader) {
        return getSerializable(bundle, str, (T) null, classLoader);
    }

    public static <T extends Serializable> T getSerializable(String str, T t, ClassLoader classLoader, Bundle... bundleArr) {
        if (bundleArr != null) {
            for (Bundle bundle : bundleArr) {
                if (bundle != null && bundle.get(str) != null) {
                    return getSerializable(bundle, str, t, classLoader);
                }
            }
        }
        return t;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r4 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r1.setClassLoader(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        if (r4 == null) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends java.io.Serializable> T getSerializable(android.os.Bundle r1, java.lang.String r2, T r3, java.lang.ClassLoader r4) {
        /*
            if (r1 == 0) goto L_0x0033
            boolean r0 = com.uacf.core.util.Strings.notEmpty(r2)
            if (r0 == 0) goto L_0x0033
            r0 = 0
            if (r4 == 0) goto L_0x0012
            java.lang.ClassLoader r0 = r1.getClassLoader()     // Catch:{ RuntimeException -> 0x0026 }
            r1.setClassLoader(r4)     // Catch:{ RuntimeException -> 0x0026 }
        L_0x0012:
            java.io.Serializable r2 = r1.getSerializable(r2)     // Catch:{ RuntimeException -> 0x0026 }
            if (r2 == 0) goto L_0x001e
            if (r4 == 0) goto L_0x001d
            r1.setClassLoader(r0)
        L_0x001d:
            return r2
        L_0x001e:
            if (r4 == 0) goto L_0x0033
        L_0x0020:
            r1.setClassLoader(r0)
            goto L_0x0033
        L_0x0024:
            r2 = move-exception
            goto L_0x002d
        L_0x0026:
            r2 = move-exception
            com.uacf.core.util.Ln.e(r2)     // Catch:{ all -> 0x0024 }
            if (r4 == 0) goto L_0x0033
            goto L_0x0020
        L_0x002d:
            if (r4 == 0) goto L_0x0032
            r1.setClassLoader(r0)
        L_0x0032:
            throw r2
        L_0x0033:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.BundleUtils.getSerializable(android.os.Bundle, java.lang.String, java.io.Serializable, java.lang.ClassLoader):java.io.Serializable");
    }

    public static boolean containsKey(Bundle bundle, String str) {
        return bundle != null && bundle.containsKey(str);
    }

    public static ArrayList<String> getStringArrayList(Bundle bundle, String str) {
        if (bundle == null || !Strings.notEmpty(str)) {
            return null;
        }
        return bundle.getStringArrayList(str);
    }

    public static void putStringSet(Bundle bundle, String str, Set<String> set) {
        if (bundle != null) {
            bundle.putStringArrayList(str, new ArrayList(set));
        }
    }

    public static Set<String> getStringSet(Bundle bundle, String str) {
        if (bundle == null || !bundle.containsKey(str)) {
            return new HashSet();
        }
        return new HashSet(bundle.getStringArrayList(str));
    }
}
