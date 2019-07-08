package butterknife;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ButterKnife {
    @VisibleForTesting
    static final Map<Class<?>, Constructor<? extends Unbinder>> BINDINGS = new LinkedHashMap();
    private static final String TAG = "ButterKnife";
    private static boolean debug = false;

    public interface Action<T extends View> {
        @UiThread
        void apply(@NonNull T t, int i);
    }

    public interface Setter<T extends View, V> {
        @UiThread
        void set(@NonNull T t, V v, int i);
    }

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }

    public static void setDebug(boolean z) {
        debug = z;
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Activity activity) {
        return createBinding(activity, activity.getWindow().getDecorView());
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull View view) {
        return createBinding(view, view);
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Dialog dialog) {
        return createBinding(dialog, dialog.getWindow().getDecorView());
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Object obj, @NonNull Activity activity) {
        return createBinding(obj, activity.getWindow().getDecorView());
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Object obj, @NonNull View view) {
        return createBinding(obj, view);
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Object obj, @NonNull Dialog dialog) {
        return createBinding(obj, dialog.getWindow().getDecorView());
    }

    private static Unbinder createBinding(@NonNull Object obj, @NonNull View view) {
        Class cls = obj.getClass();
        if (debug) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Looking up binding for ");
            sb.append(cls.getName());
            Log.d(str, sb.toString());
        }
        Constructor findBindingConstructorForClass = findBindingConstructorForClass(cls);
        if (findBindingConstructorForClass == null) {
            return Unbinder.EMPTY;
        }
        try {
            return (Unbinder) findBindingConstructorForClass.newInstance(new Object[]{obj, view});
        } catch (IllegalAccessException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to invoke ");
            sb2.append(findBindingConstructorForClass);
            throw new RuntimeException(sb2.toString(), e);
        } catch (InstantiationException e2) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unable to invoke ");
            sb3.append(findBindingConstructorForClass);
            throw new RuntimeException(sb3.toString(), e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unable to create binding instance.", cause);
            }
        }
    }

    @UiThread
    @CheckResult
    @Nullable
    private static Constructor<? extends Unbinder> findBindingConstructorForClass(Class<?> cls) {
        Constructor<? extends Unbinder> constructor;
        Constructor<? extends Unbinder> constructor2 = (Constructor) BINDINGS.get(cls);
        if (constructor2 != null) {
            if (debug) {
                Log.d(TAG, "HIT: Cached in binding map.");
            }
            return constructor2;
        }
        String name = cls.getName();
        if (name.startsWith("android.") || name.startsWith("java.")) {
            if (debug) {
                Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
            }
            return null;
        }
        try {
            ClassLoader classLoader = cls.getClassLoader();
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append("_ViewBinding");
            constructor = classLoader.loadClass(sb.toString()).getConstructor(new Class[]{cls, View.class});
            if (debug) {
                Log.d(TAG, "HIT: Loaded binding class and constructor.");
            }
        } catch (ClassNotFoundException unused) {
            if (debug) {
                String str = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Not found. Trying superclass ");
                sb2.append(cls.getSuperclass().getName());
                Log.d(str, sb2.toString());
            }
            constructor = findBindingConstructorForClass(cls.getSuperclass());
        } catch (NoSuchMethodException e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unable to find binding constructor for ");
            sb3.append(name);
            throw new RuntimeException(sb3.toString(), e);
        }
        BINDINGS.put(cls, constructor);
        return constructor;
    }

    @UiThread
    @SafeVarargs
    public static <T extends View> void apply(@NonNull List<T> list, @NonNull Action<? super T>... actionArr) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (Action<? super T> apply : actionArr) {
                apply.apply((View) list.get(i), i);
            }
        }
    }

    @UiThread
    @SafeVarargs
    public static <T extends View> void apply(@NonNull T[] tArr, @NonNull Action<? super T>... actionArr) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            for (Action<? super T> apply : actionArr) {
                apply.apply(tArr[i], i);
            }
        }
    }

    @UiThread
    public static <T extends View> void apply(@NonNull List<T> list, @NonNull Action<? super T> action) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            action.apply((View) list.get(i), i);
        }
    }

    @UiThread
    public static <T extends View> void apply(@NonNull T[] tArr, @NonNull Action<? super T> action) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            action.apply(tArr[i], i);
        }
    }

    @UiThread
    @SafeVarargs
    public static <T extends View> void apply(@NonNull T t, @NonNull Action<? super T>... actionArr) {
        for (Action<? super T> apply : actionArr) {
            apply.apply(t, 0);
        }
    }

    @UiThread
    public static <T extends View> void apply(@NonNull T t, @NonNull Action<? super T> action) {
        action.apply(t, 0);
    }

    @UiThread
    public static <T extends View, V> void apply(@NonNull List<T> list, @NonNull Setter<? super T, V> setter, V v) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            setter.set((View) list.get(i), v, i);
        }
    }

    @UiThread
    public static <T extends View, V> void apply(@NonNull T[] tArr, @NonNull Setter<? super T, V> setter, V v) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            setter.set(tArr[i], v, i);
        }
    }

    @UiThread
    public static <T extends View, V> void apply(@NonNull T t, @NonNull Setter<? super T, V> setter, V v) {
        setter.set(t, v, 0);
    }

    @UiThread
    @RequiresApi
    @TargetApi(14)
    public static <T extends View, V> void apply(@NonNull List<T> list, @NonNull Property<? super T, V> property, V v) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            property.set(list.get(i), v);
        }
    }

    @UiThread
    @RequiresApi
    @TargetApi(14)
    public static <T extends View, V> void apply(@NonNull T[] tArr, @NonNull Property<? super T, V> property, V v) {
        for (T t : tArr) {
            property.set(t, v);
        }
    }

    @UiThread
    @RequiresApi
    @TargetApi(14)
    public static <T extends View, V> void apply(@NonNull T t, @NonNull Property<? super T, V> property, V v) {
        property.set(t, v);
    }

    @Deprecated
    @CheckResult
    public static <T extends View> T findById(@NonNull View view, @IdRes int i) {
        return view.findViewById(i);
    }

    @Deprecated
    @CheckResult
    public static <T extends View> T findById(@NonNull Activity activity, @IdRes int i) {
        return activity.findViewById(i);
    }

    @Deprecated
    @CheckResult
    public static <T extends View> T findById(@NonNull Dialog dialog, @IdRes int i) {
        return dialog.findViewById(i);
    }
}
