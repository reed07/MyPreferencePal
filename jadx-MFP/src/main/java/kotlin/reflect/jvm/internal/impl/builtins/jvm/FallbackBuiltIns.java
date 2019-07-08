package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsInitializer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
final class FallbackBuiltIns extends KotlinBuiltIns {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final BuiltInsInitializer<FallbackBuiltIns> initializer = new BuiltInsInitializer<>(FallbackBuiltIns$Companion$initializer$1.INSTANCE);

    /* compiled from: JvmBuiltInsSettings.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KotlinBuiltIns getInstance() {
            return FallbackBuiltIns.initializer.get();
        }
    }

    private FallbackBuiltIns() {
        super(new LockBasedStorageManager());
        createBuiltInsModule();
    }

    public /* synthetic */ FallbackBuiltIns(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public All getPlatformDependentDeclarationFilter() {
        return All.INSTANCE;
    }
}
