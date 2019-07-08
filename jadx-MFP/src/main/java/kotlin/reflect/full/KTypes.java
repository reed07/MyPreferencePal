package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0001H\u0007¨\u0006\u0007"}, d2 = {"isSubtypeOf", "", "Lkotlin/reflect/KType;", "other", "isSupertypeOf", "withNullability", "nullable", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "KTypes")
/* compiled from: KTypes.kt */
public final class KTypes {
    @NotNull
    @SinceKotlin(version = "1.1")
    public static final KType withNullability(@NotNull KType kType, boolean z) {
        Intrinsics.checkParameterIsNotNull(kType, "receiver$0");
        if (kType.isMarkedNullable()) {
            if (!z) {
                KotlinType makeNotNullable = TypeUtils.makeNotNullable(((KTypeImpl) kType).getType());
                Intrinsics.checkExpressionValueIsNotNull(makeNotNullable, "TypeUtils.makeNotNullabl…(this as KTypeImpl).type)");
                kType = new KTypeImpl(makeNotNullable, new KTypes$withNullability$1(kType));
            }
            return kType;
        }
        KotlinType type = ((KTypeImpl) kType).getType();
        if (FlexibleTypesKt.isFlexible(type)) {
            KotlinType makeNullableAsSpecified = TypeUtils.makeNullableAsSpecified(type, z);
            Intrinsics.checkExpressionValueIsNotNull(makeNullableAsSpecified, "TypeUtils.makeNullableAs…ied(kotlinType, nullable)");
            return new KTypeImpl(makeNullableAsSpecified, new KTypes$withNullability$2(kType));
        }
        if (z) {
            KotlinType makeNullable = TypeUtils.makeNullable(type);
            Intrinsics.checkExpressionValueIsNotNull(makeNullable, "TypeUtils.makeNullable(kotlinType)");
            kType = new KTypeImpl(makeNullable, new KTypes$withNullability$3(kType));
        }
        return kType;
    }

    @SinceKotlin(version = "1.1")
    public static final boolean isSubtypeOf(@NotNull KType kType, @NotNull KType kType2) {
        Intrinsics.checkParameterIsNotNull(kType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(kType2, "other");
        return TypeUtilsKt.isSubtypeOf(((KTypeImpl) kType).getType(), ((KTypeImpl) kType2).getType());
    }

    @SinceKotlin(version = "1.1")
    public static final boolean isSupertypeOf(@NotNull KType kType, @NotNull KType kType2) {
        Intrinsics.checkParameterIsNotNull(kType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(kType2, "other");
        return isSubtypeOf(kType2, kType);
    }
}
