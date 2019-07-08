package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/internal/MainDispatcherLoader;", "", "()V", "dispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "loadMainDispatcher", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: MainDispatchers.kt */
public final class MainDispatcherLoader {
    public static final MainDispatcherLoader INSTANCE;
    @NotNull
    @JvmField
    public static final MainCoroutineDispatcher dispatcher;

    static {
        MainDispatcherLoader mainDispatcherLoader = new MainDispatcherLoader();
        INSTANCE = mainDispatcherLoader;
        dispatcher = mainDispatcherLoader.loadMainDispatcher();
    }

    private MainDispatcherLoader() {
    }

    private final MainCoroutineDispatcher loadMainDispatcher() {
        Object obj;
        Class<MainDispatcherFactory> cls = MainDispatcherFactory.class;
        try {
            ServiceLoader load = ServiceLoader.load(cls, cls.getClassLoader());
            Intrinsics.checkExpressionValueIsNotNull(load, "ServiceLoader.load(clz, clz.classLoader)");
            List list = CollectionsKt.toList(load);
            Iterator it = list.iterator();
            if (!it.hasNext()) {
                obj = null;
            } else {
                obj = it.next();
                int loadPriority = ((MainDispatcherFactory) obj).getLoadPriority();
                while (it.hasNext()) {
                    Object next = it.next();
                    int loadPriority2 = ((MainDispatcherFactory) next).getLoadPriority();
                    if (loadPriority < loadPriority2) {
                        obj = next;
                        loadPriority = loadPriority2;
                    }
                }
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) obj;
            if (mainDispatcherFactory != null) {
                MainCoroutineDispatcher tryCreateDispatcher = MainDispatchersKt.tryCreateDispatcher(mainDispatcherFactory, list);
                if (tryCreateDispatcher != null) {
                    return tryCreateDispatcher;
                }
            }
            return new MissingMainCoroutineDispatcher(null, null, 2, null);
        } catch (Throwable th) {
            return new MissingMainCoroutineDispatcher(th, null, 2, null);
        }
    }
}
