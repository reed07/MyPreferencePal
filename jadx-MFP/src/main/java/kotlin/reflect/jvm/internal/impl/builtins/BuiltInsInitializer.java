package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInsInitializer.kt */
public final class BuiltInsInitializer<T extends KotlinBuiltIns> {
    private final Function0<T> constructor;
    private Throwable initializationFailed;
    private volatile boolean initializing;
    private volatile T instance;

    public BuiltInsInitializer(@NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "constructor");
        this.constructor = function0;
    }

    private final synchronized void initialize() {
        if (this.instance == null) {
            if (this.initializationFailed != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Built-in library initialization failed previously: ");
                Throwable th = this.initializationFailed;
                if (th == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(th);
                throw new IllegalStateException(sb.toString(), this.initializationFailed);
            } else if (!this.initializing) {
                this.initializing = true;
                try {
                    this.instance = (KotlinBuiltIns) this.constructor.invoke();
                    this.initializing = false;
                } catch (Throwable th2) {
                    this.initializing = false;
                    throw th2;
                }
            } else {
                throw new IllegalStateException("Built-in library initialization loop");
            }
        }
    }

    @NotNull
    public final T get() {
        T t;
        if (this.initializing) {
            synchronized (this) {
                t = this.instance;
                if (t == null) {
                    throw new AssertionError("Built-ins are not initialized (note: We are under the same lock as initializing and instance)");
                }
            }
            return t;
        }
        if (this.instance == null) {
            initialize();
        }
        T t2 = this.instance;
        if (t2 == null) {
            Intrinsics.throwNpe();
        }
        return t2;
    }
}
