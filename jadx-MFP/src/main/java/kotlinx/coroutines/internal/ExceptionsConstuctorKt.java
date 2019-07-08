package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00052\u0006\u0010\t\u001a\u0002H\bH\u0000¢\u0006\u0002\u0010\n\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"0\u0010\u0002\u001a$\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00060\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "exceptionConstructors", "Ljava/util/WeakHashMap;", "Ljava/lang/Class;", "", "Lkotlin/Function1;", "tryCopyException", "E", "exception", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: ExceptionsConstuctor.kt */
public final class ExceptionsConstuctorKt {
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> exceptionConstructors = new WeakHashMap<>();

    @Nullable
    public static final <E extends Throwable> E tryCopyException(@NotNull E e) {
        int i;
        Intrinsics.checkParameterIsNotNull(e, "exception");
        ReadLock readLock = cacheLock.readLock();
        readLock.lock();
        try {
            Function1 function1 = (Function1) exceptionConstructors.get(e.getClass());
            if (function1 != null) {
                return (Throwable) function1.invoke(e);
            }
            E e2 = null;
            Function1 function12 = null;
            Constructor[] constructors = e.getClass().getConstructors();
            Intrinsics.checkExpressionValueIsNotNull(constructors, "exception.javaClass.constructors");
            Iterator it = ArraysKt.sortedWith((T[]) constructors, (Comparator<? super T>) new ExceptionsConstuctorKt$tryCopyException$$inlined$sortedByDescending$1<Object>()).iterator();
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                Constructor constructor = (Constructor) it.next();
                Intrinsics.checkExpressionValueIsNotNull(constructor, "constructor");
                Class[] parameterTypes = constructor.getParameterTypes();
                boolean z = true;
                if (parameterTypes.length != 2 || !Intrinsics.areEqual((Object) parameterTypes[i], (Object) String.class) || !Intrinsics.areEqual((Object) parameterTypes[1], (Object) Throwable.class)) {
                    if (parameterTypes.length == 1 && Intrinsics.areEqual((Object) parameterTypes[i], (Object) Throwable.class)) {
                        function12 = new ExceptionsConstuctorKt$tryCopyException$2(constructor);
                        break;
                    }
                    Intrinsics.checkExpressionValueIsNotNull(parameterTypes, "parameters");
                    if (parameterTypes.length != 0) {
                        z = false;
                        continue;
                    }
                    if (z) {
                        function12 = new ExceptionsConstuctorKt$tryCopyException$3(constructor);
                        break;
                    }
                } else {
                    function12 = new ExceptionsConstuctorKt$tryCopyException$1(constructor);
                    break;
                }
            }
            ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
            ReadLock readLock2 = reentrantReadWriteLock.readLock();
            int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
            for (int i2 = 0; i2 < readHoldCount; i2++) {
                readLock2.unlock();
            }
            WriteLock writeLock = reentrantReadWriteLock.writeLock();
            writeLock.lock();
            try {
                exceptionConstructors.put(e.getClass(), function12 != null ? function12 : ExceptionsConstuctorKt$tryCopyException$4$1.INSTANCE);
                Unit unit = Unit.INSTANCE;
                writeLock.unlock();
                if (function12 != null) {
                    e2 = (Throwable) function12.invoke(e);
                }
                return e2;
            } finally {
                while (i < readHoldCount) {
                    readLock2.lock();
                    i++;
                }
                writeLock.unlock();
            }
        } finally {
            readLock.unlock();
        }
    }
}
