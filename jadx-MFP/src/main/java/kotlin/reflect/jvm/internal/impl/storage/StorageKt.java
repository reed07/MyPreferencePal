package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: storage.kt */
public final class StorageKt {
    @NotNull
    public static final <T> T getValue(@NotNull NotNullLazyValue<? extends T> notNullLazyValue, @Nullable Object obj, @NotNull KProperty<?> kProperty) {
        Intrinsics.checkParameterIsNotNull(notNullLazyValue, "receiver$0");
        Intrinsics.checkParameterIsNotNull(kProperty, "p");
        return notNullLazyValue.invoke();
    }

    @Nullable
    public static final <T> T getValue(@NotNull NullableLazyValue<? extends T> nullableLazyValue, @Nullable Object obj, @NotNull KProperty<?> kProperty) {
        Intrinsics.checkParameterIsNotNull(nullableLazyValue, "receiver$0");
        Intrinsics.checkParameterIsNotNull(kProperty, "p");
        return nullableLazyValue.invoke();
    }
}
