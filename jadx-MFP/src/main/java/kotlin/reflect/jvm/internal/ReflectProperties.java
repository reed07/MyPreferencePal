package kotlin.reflect.jvm.internal;

import java.lang.ref.SoftReference;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReflectProperties {

    public static class LazySoftVal<T> extends Val<T> {
        private final Function0<T> initializer;
        private SoftReference<Object> value = null;

        public LazySoftVal(@Nullable T t, @NotNull Function0<T> function0) {
            this.initializer = function0;
            if (t != null) {
                this.value = new SoftReference<>(escape(t));
            }
        }

        public T invoke() {
            SoftReference<Object> softReference = this.value;
            if (softReference != null) {
                Object obj = softReference.get();
                if (obj != null) {
                    return unescape(obj);
                }
            }
            T invoke = this.initializer.invoke();
            this.value = new SoftReference<>(escape(invoke));
            return invoke;
        }
    }

    public static class LazyVal<T> extends Val<T> {
        private final Function0<T> initializer;
        private Object value = null;

        public LazyVal(@NotNull Function0<T> function0) {
            this.initializer = function0;
        }

        public T invoke() {
            Object obj = this.value;
            if (obj != null) {
                return unescape(obj);
            }
            T invoke = this.initializer.invoke();
            this.value = escape(invoke);
            return invoke;
        }
    }

    public static abstract class Val<T> {
        private static final Object NULL_VALUE = new Object() {
        };

        public abstract T invoke();

        public final T getValue(Object obj, Object obj2) {
            return invoke();
        }

        /* access modifiers changed from: protected */
        public Object escape(T t) {
            return t == null ? NULL_VALUE : t;
        }

        /* access modifiers changed from: protected */
        public T unescape(Object obj) {
            if (obj == NULL_VALUE) {
                return null;
            }
            return obj;
        }
    }

    @NotNull
    public static <T> LazyVal<T> lazy(@NotNull Function0<T> function0) {
        return new LazyVal<>(function0);
    }

    @NotNull
    public static <T> LazySoftVal<T> lazySoft(@Nullable T t, @NotNull Function0<T> function0) {
        return new LazySoftVal<>(t, function0);
    }

    @NotNull
    public static <T> LazySoftVal<T> lazySoft(@NotNull Function0<T> function0) {
        return lazySoft(null, function0);
    }
}
