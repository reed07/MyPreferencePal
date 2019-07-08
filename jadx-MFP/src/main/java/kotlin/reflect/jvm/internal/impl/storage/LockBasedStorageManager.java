package kotlin.reflect.jvm.internal.impl.storage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.WrappedValues;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LockBasedStorageManager implements StorageManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final StorageManager NO_LOCKS = new LockBasedStorageManager("NO_LOCKS", ExceptionHandlingStrategy.THROW, NoLock.INSTANCE) {
        /* access modifiers changed from: protected */
        @NotNull
        public <T> RecursionDetectedResult<T> recursionDetectedDefault() {
            return RecursionDetectedResult.fallThrough();
        }
    };
    private static final String PACKAGE_NAME = StringsKt.substringBeforeLast(LockBasedStorageManager.class.getCanonicalName(), ".", "");
    private final String debugText;
    /* access modifiers changed from: private */
    public final ExceptionHandlingStrategy exceptionHandlingStrategy;
    protected final Lock lock;

    private static class CacheWithNotNullValuesBasedOnMemoizedFunction<K, V> extends CacheWithNullableValuesBasedOnMemoizedFunction<K, V> implements CacheWithNotNullValues<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<LockBasedStorageManager> cls = LockBasedStorageManager.class;
        }

        private CacheWithNotNullValuesBasedOnMemoizedFunction(@NotNull LockBasedStorageManager lockBasedStorageManager, @NotNull ConcurrentMap<KeyWithComputation<K, V>, Object> concurrentMap) {
            super(concurrentMap);
        }

        @NotNull
        public V computeIfAbsent(K k, @NotNull Function0<? extends V> function0) {
            return super.computeIfAbsent(k, function0);
        }
    }

    private static class CacheWithNullableValuesBasedOnMemoizedFunction<K, V> extends MapBasedMemoizedFunction<KeyWithComputation<K, V>, V> {
        private CacheWithNullableValuesBasedOnMemoizedFunction(@NotNull LockBasedStorageManager lockBasedStorageManager, @NotNull ConcurrentMap<KeyWithComputation<K, V>, Object> concurrentMap) {
            super(lockBasedStorageManager, concurrentMap, new Function1<KeyWithComputation<K, V>, V>() {
                public V invoke(KeyWithComputation<K, V> keyWithComputation) {
                    return keyWithComputation.computation.invoke();
                }
            });
        }

        @Nullable
        public V computeIfAbsent(K k, @NotNull Function0<? extends V> function0) {
            return invoke(new KeyWithComputation(k, function0));
        }
    }

    public interface ExceptionHandlingStrategy {
        public static final ExceptionHandlingStrategy THROW = new ExceptionHandlingStrategy() {
            @NotNull
            public RuntimeException handleException(@NotNull Throwable th) {
                throw ExceptionUtilsKt.rethrow(th);
            }
        };

        @NotNull
        RuntimeException handleException(@NotNull Throwable th);
    }

    private static class KeyWithComputation<K, V> {
        /* access modifiers changed from: private */
        public final Function0<? extends V> computation;
        private final K key;

        public KeyWithComputation(K k, Function0<? extends V> function0) {
            this.key = k;
            this.computation = function0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.key.equals(((KeyWithComputation) obj).key);
        }

        public int hashCode() {
            return this.key.hashCode();
        }
    }

    private static class LockBasedLazyValue<T> implements NullableLazyValue<T> {
        private final Function0<? extends T> computable;
        private final LockBasedStorageManager storageManager;
        @Nullable
        private volatile Object value = NotValue.NOT_COMPUTED;

        /* access modifiers changed from: protected */
        public void postCompute(T t) {
        }

        public LockBasedLazyValue(@NotNull LockBasedStorageManager lockBasedStorageManager, @NotNull Function0<? extends T> function0) {
            this.storageManager = lockBasedStorageManager;
            this.computable = function0;
        }

        public boolean isComputed() {
            return (this.value == NotValue.NOT_COMPUTED || this.value == NotValue.COMPUTING) ? false : true;
        }

        public T invoke() {
            T invoke;
            Object obj = this.value;
            if (!(obj instanceof NotValue)) {
                return WrappedValues.unescapeThrowable(obj);
            }
            this.storageManager.lock.lock();
            try {
                Object obj2 = this.value;
                if (!(obj2 instanceof NotValue)) {
                    invoke = WrappedValues.unescapeThrowable(obj2);
                } else {
                    if (obj2 == NotValue.COMPUTING) {
                        this.value = NotValue.RECURSION_WAS_DETECTED;
                        RecursionDetectedResult recursionDetected = recursionDetected(true);
                        if (!recursionDetected.isFallThrough()) {
                            invoke = recursionDetected.getValue();
                        }
                    }
                    if (obj2 == NotValue.RECURSION_WAS_DETECTED) {
                        RecursionDetectedResult recursionDetected2 = recursionDetected(false);
                        if (!recursionDetected2.isFallThrough()) {
                            invoke = recursionDetected2.getValue();
                        }
                    }
                    this.value = NotValue.COMPUTING;
                    invoke = this.computable.invoke();
                    this.value = invoke;
                    postCompute(invoke);
                }
                this.storageManager.lock.unlock();
                return invoke;
            } catch (Throwable th) {
                this.storageManager.lock.unlock();
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        @NotNull
        public RecursionDetectedResult<T> recursionDetected(boolean z) {
            return this.storageManager.recursionDetectedDefault();
        }
    }

    private static class LockBasedNotNullLazyValue<T> extends LockBasedLazyValue<T> implements NotNullLazyValue<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<LockBasedStorageManager> cls = LockBasedStorageManager.class;
        }

        public LockBasedNotNullLazyValue(@NotNull LockBasedStorageManager lockBasedStorageManager, @NotNull Function0<? extends T> function0) {
            super(lockBasedStorageManager, function0);
        }

        @NotNull
        public T invoke() {
            return super.invoke();
        }
    }

    private static class MapBasedMemoizedFunction<K, V> implements MemoizedFunctionToNullable<K, V> {
        private final ConcurrentMap<K, Object> cache;
        private final Function1<? super K, ? extends V> compute;
        private final LockBasedStorageManager storageManager;

        public MapBasedMemoizedFunction(@NotNull LockBasedStorageManager lockBasedStorageManager, @NotNull ConcurrentMap<K, Object> concurrentMap, @NotNull Function1<? super K, ? extends V> function1) {
            this.storageManager = lockBasedStorageManager;
            this.cache = concurrentMap;
            this.compute = function1;
        }

        @Nullable
        public V invoke(K k) {
            Object obj = this.cache.get(k);
            if (obj != null && obj != NotValue.COMPUTING) {
                return WrappedValues.unescapeExceptionOrNull(obj);
            }
            this.storageManager.lock.lock();
            try {
                Object obj2 = this.cache.get(k);
                if (obj2 == NotValue.COMPUTING) {
                    throw recursionDetected(k);
                } else if (obj2 != null) {
                    V unescapeExceptionOrNull = WrappedValues.unescapeExceptionOrNull(obj2);
                    this.storageManager.lock.unlock();
                    return unescapeExceptionOrNull;
                } else {
                    this.cache.put(k, NotValue.COMPUTING);
                    V invoke = this.compute.invoke(k);
                    Object put = this.cache.put(k, WrappedValues.escapeNull(invoke));
                    if (put == NotValue.COMPUTING) {
                        this.storageManager.lock.unlock();
                        return invoke;
                    }
                    throw raceCondition(k, put);
                }
            } catch (Throwable th) {
                this.storageManager.lock.unlock();
                throw th;
            }
        }

        @NotNull
        private AssertionError recursionDetected(K k) {
            StringBuilder sb = new StringBuilder();
            sb.append("Recursion detected on input: ");
            sb.append(k);
            sb.append(" under ");
            sb.append(this.storageManager);
            return (AssertionError) LockBasedStorageManager.sanitizeStackTrace(new AssertionError(sb.toString()));
        }

        @NotNull
        private AssertionError raceCondition(K k, Object obj) {
            StringBuilder sb = new StringBuilder();
            sb.append("Race condition detected on input ");
            sb.append(k);
            sb.append(". Old value is ");
            sb.append(obj);
            sb.append(" under ");
            sb.append(this.storageManager);
            return (AssertionError) LockBasedStorageManager.sanitizeStackTrace(new AssertionError(sb.toString()));
        }

        /* access modifiers changed from: protected */
        public LockBasedStorageManager getStorageManager() {
            return this.storageManager;
        }
    }

    private static class MapBasedMemoizedFunctionToNotNull<K, V> extends MapBasedMemoizedFunction<K, V> implements MemoizedFunctionToNotNull<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<LockBasedStorageManager> cls = LockBasedStorageManager.class;
        }

        public MapBasedMemoizedFunctionToNotNull(@NotNull LockBasedStorageManager lockBasedStorageManager, @NotNull ConcurrentMap<K, Object> concurrentMap, @NotNull Function1<? super K, ? extends V> function1) {
            super(lockBasedStorageManager, concurrentMap, function1);
        }

        @NotNull
        public V invoke(K k) {
            return super.invoke(k);
        }
    }

    private enum NotValue {
        NOT_COMPUTED,
        COMPUTING,
        RECURSION_WAS_DETECTED
    }

    private static class RecursionDetectedResult<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean fallThrough;
        private final T value;

        static {
            Class<LockBasedStorageManager> cls = LockBasedStorageManager.class;
        }

        @NotNull
        public static <T> RecursionDetectedResult<T> value(T t) {
            return new RecursionDetectedResult<>(t, false);
        }

        @NotNull
        public static <T> RecursionDetectedResult<T> fallThrough() {
            return new RecursionDetectedResult<>(null, true);
        }

        private RecursionDetectedResult(T t, boolean z) {
            this.value = t;
            this.fallThrough = z;
        }

        public T getValue() {
            return this.value;
        }

        public boolean isFallThrough() {
            return this.fallThrough;
        }

        public String toString() {
            return isFallThrough() ? "FALL_THROUGH" : String.valueOf(this.value);
        }
    }

    private static String defaultDebugName() {
        return "<unknown creating class>";
    }

    private LockBasedStorageManager(@NotNull String str, @NotNull ExceptionHandlingStrategy exceptionHandlingStrategy2, @NotNull Lock lock2) {
        this.lock = lock2;
        this.exceptionHandlingStrategy = exceptionHandlingStrategy2;
        this.debugText = str;
    }

    public LockBasedStorageManager() {
        this(defaultDebugName(), ExceptionHandlingStrategy.THROW, new ReentrantLock());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(" (");
        sb.append(this.debugText);
        sb.append(")");
        return sb.toString();
    }

    @NotNull
    public <K, V> MemoizedFunctionToNotNull<K, V> createMemoizedFunction(@NotNull Function1<? super K, ? extends V> function1) {
        return createMemoizedFunction(function1, createConcurrentHashMap());
    }

    @NotNull
    public <K, V> MemoizedFunctionToNotNull<K, V> createMemoizedFunction(@NotNull Function1<? super K, ? extends V> function1, @NotNull ConcurrentMap<K, Object> concurrentMap) {
        return new MapBasedMemoizedFunctionToNotNull(this, concurrentMap, function1);
    }

    @NotNull
    public <K, V> MemoizedFunctionToNullable<K, V> createMemoizedFunctionWithNullableValues(@NotNull Function1<? super K, ? extends V> function1) {
        return createMemoizedFunctionWithNullableValues(function1, createConcurrentHashMap());
    }

    @NotNull
    public <K, V> MemoizedFunctionToNullable<K, V> createMemoizedFunctionWithNullableValues(@NotNull Function1<? super K, ? extends V> function1, @NotNull ConcurrentMap<K, Object> concurrentMap) {
        return new MapBasedMemoizedFunction(this, concurrentMap, function1);
    }

    @NotNull
    public <T> NotNullLazyValue<T> createLazyValue(@NotNull Function0<? extends T> function0) {
        return new LockBasedNotNullLazyValue(this, function0);
    }

    @NotNull
    public <T> NotNullLazyValue<T> createRecursionTolerantLazyValue(@NotNull Function0<? extends T> function0, @NotNull final T t) {
        return new LockBasedNotNullLazyValue<T>(this, function0) {
            /* access modifiers changed from: protected */
            @NotNull
            public RecursionDetectedResult<T> recursionDetected(boolean z) {
                return RecursionDetectedResult.value(t);
            }
        };
    }

    @NotNull
    public <T> NotNullLazyValue<T> createLazyValueWithPostCompute(@NotNull Function0<? extends T> function0, Function1<? super Boolean, ? extends T> function1, @NotNull Function1<? super T, Unit> function12) {
        final Function1<? super Boolean, ? extends T> function13 = function1;
        final Function1<? super T, Unit> function14 = function12;
        AnonymousClass3 r0 = new LockBasedNotNullLazyValue<T>(this, function0) {
            /* access modifiers changed from: protected */
            @NotNull
            public RecursionDetectedResult<T> recursionDetected(boolean z) {
                Function1 function1 = function13;
                if (function1 == null) {
                    return super.recursionDetected(z);
                }
                return RecursionDetectedResult.value(function1.invoke(Boolean.valueOf(z)));
            }

            /* access modifiers changed from: protected */
            public void postCompute(@NotNull T t) {
                function14.invoke(t);
            }
        };
        return r0;
    }

    @NotNull
    public <T> NullableLazyValue<T> createNullableLazyValue(@NotNull Function0<? extends T> function0) {
        return new LockBasedLazyValue(this, function0);
    }

    @NotNull
    private static <K> ConcurrentMap<K, Object> createConcurrentHashMap() {
        return new ConcurrentHashMap(3, 1.0f, 2);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public <T> RecursionDetectedResult<T> recursionDetectedDefault() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recursive call in a lazy value under ");
        sb.append(this);
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException(sb.toString())));
    }

    /* access modifiers changed from: private */
    @NotNull
    public static <T extends Throwable> T sanitizeStackTrace(@NotNull T t) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (!stackTrace[i].getClassName().startsWith(PACKAGE_NAME)) {
                break;
            } else {
                i++;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i, length);
        t.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
        return t;
    }

    @NotNull
    public <K, V> CacheWithNotNullValues<K, V> createCacheWithNotNullValues() {
        return new CacheWithNotNullValuesBasedOnMemoizedFunction(createConcurrentHashMap());
    }
}
