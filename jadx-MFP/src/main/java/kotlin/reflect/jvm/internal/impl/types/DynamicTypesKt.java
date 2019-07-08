package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: dynamicTypes.kt */
public final class DynamicTypesKt {
    public static final boolean isDynamic(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return kotlinType.unwrap() instanceof DynamicType;
    }
}
