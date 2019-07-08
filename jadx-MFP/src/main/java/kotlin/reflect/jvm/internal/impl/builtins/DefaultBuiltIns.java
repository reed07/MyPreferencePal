package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: DefaultBuiltIns.kt */
public final class DefaultBuiltIns extends KotlinBuiltIns {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final BuiltInsInitializer<DefaultBuiltIns> initializer = new BuiltInsInitializer<>(DefaultBuiltIns$Companion$initializer$1.INSTANCE);

    /* compiled from: DefaultBuiltIns.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final DefaultBuiltIns getInstance() {
            return (DefaultBuiltIns) DefaultBuiltIns.initializer.get();
        }
    }

    @NotNull
    public static final DefaultBuiltIns getInstance() {
        return Companion.getInstance();
    }

    private DefaultBuiltIns() {
        super(new LockBasedStorageManager());
        createBuiltInsModule();
    }

    public /* synthetic */ DefaultBuiltIns(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
