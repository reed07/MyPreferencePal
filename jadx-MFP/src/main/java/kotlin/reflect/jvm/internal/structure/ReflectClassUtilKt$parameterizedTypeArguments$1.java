package kotlin.reflect.jvm.internal.structure;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/lang/reflect/ParameterizedType;", "it", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: reflectClassUtil.kt */
final class ReflectClassUtilKt$parameterizedTypeArguments$1 extends Lambda implements Function1<ParameterizedType, ParameterizedType> {
    public static final ReflectClassUtilKt$parameterizedTypeArguments$1 INSTANCE = new ReflectClassUtilKt$parameterizedTypeArguments$1();

    ReflectClassUtilKt$parameterizedTypeArguments$1() {
        super(1);
    }

    @Nullable
    public final ParameterizedType invoke(@NotNull ParameterizedType parameterizedType) {
        Intrinsics.checkParameterIsNotNull(parameterizedType, "it");
        Type ownerType = parameterizedType.getOwnerType();
        if (!(ownerType instanceof ParameterizedType)) {
            ownerType = null;
        }
        return (ParameterizedType) ownerType;
    }
}
