package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: SpecialTypes.kt */
public final class LazyWrappedType extends WrappedType {
    private final NotNullLazyValue<KotlinType> lazyValue;

    public LazyWrappedType(@NotNull StorageManager storageManager, @NotNull Function0<? extends KotlinType> function0) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(function0, "computation");
        this.lazyValue = storageManager.createLazyValue(function0);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public KotlinType getDelegate() {
        return (KotlinType) this.lazyValue.invoke();
    }

    public boolean isComputed() {
        return this.lazyValue.isComputed();
    }
}
