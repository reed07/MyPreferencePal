package kotlin.reflect.jvm.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Type;", "invoke", "kotlin/reflect/jvm/internal/KTypeImpl$arguments$2$1$type$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: KTypeImpl.kt */
final class KTypeImpl$arguments$2$$special$$inlined$mapIndexed$lambda$1 extends Lambda implements Function0<Type> {
    final /* synthetic */ int $i;
    final /* synthetic */ Lazy $parameterizedTypeArguments$inlined;
    final /* synthetic */ KProperty $parameterizedTypeArguments$metadata$inlined;
    final /* synthetic */ KTypeImpl$arguments$2 this$0;

    KTypeImpl$arguments$2$$special$$inlined$mapIndexed$lambda$1(int i, KTypeImpl$arguments$2 kTypeImpl$arguments$2, Lazy lazy, KProperty kProperty) {
        this.$i = i;
        this.this$0 = kTypeImpl$arguments$2;
        this.$parameterizedTypeArguments$inlined = lazy;
        this.$parameterizedTypeArguments$metadata$inlined = kProperty;
        super(0);
    }

    @NotNull
    public final Type invoke() {
        Type javaType$kotlin_reflect_api = this.this$0.this$0.getJavaType$kotlin_reflect_api();
        if (javaType$kotlin_reflect_api instanceof Class) {
            Class cls = (Class) javaType$kotlin_reflect_api;
            Class<Object> componentType = cls.isArray() ? cls.getComponentType() : Object.class;
            Intrinsics.checkExpressionValueIsNotNull(componentType, "if (javaType.isArray) ja…Type else Any::class.java");
            return componentType;
        } else if (javaType$kotlin_reflect_api instanceof GenericArrayType) {
            if (this.$i == 0) {
                Type genericComponentType = ((GenericArrayType) javaType$kotlin_reflect_api).getGenericComponentType();
                Intrinsics.checkExpressionValueIsNotNull(genericComponentType, "javaType.genericComponentType");
                return genericComponentType;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Array type has been queried for a non-0th argument: ");
            sb.append(this.this$0.this$0);
            throw new KotlinReflectionInternalError(sb.toString());
        } else if (javaType$kotlin_reflect_api instanceof ParameterizedType) {
            Lazy lazy = this.$parameterizedTypeArguments$inlined;
            KProperty kProperty = this.$parameterizedTypeArguments$metadata$inlined;
            Type type = (Type) ((List) lazy.getValue()).get(this.$i);
            if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                Type[] lowerBounds = wildcardType.getLowerBounds();
                Intrinsics.checkExpressionValueIsNotNull(lowerBounds, "argument.lowerBounds");
                Type type2 = (Type) ArraysKt.firstOrNull((T[]) lowerBounds);
                if (type2 != null) {
                    type = type2;
                } else {
                    Type[] upperBounds = wildcardType.getUpperBounds();
                    Intrinsics.checkExpressionValueIsNotNull(upperBounds, "argument.upperBounds");
                    type = (Type) ArraysKt.first((T[]) upperBounds);
                }
            }
            Intrinsics.checkExpressionValueIsNotNull(type, "if (argument !is Wildcar…ument.upperBounds.first()");
            return type;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Non-generic type has been queried for arguments: ");
            sb2.append(this.this$0.this$0);
            throw new KotlinReflectionInternalError(sb2.toString());
        }
    }
}
