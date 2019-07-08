package kotlin.reflect.jvm.internal.components;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/components/SignatureSerializer;", "", "()V", "constructorDesc", "", "constructor", "Ljava/lang/reflect/Constructor;", "fieldDesc", "field", "Ljava/lang/reflect/Field;", "methodDesc", "method", "Ljava/lang/reflect/Method;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: ReflectKotlinClass.kt */
final class SignatureSerializer {
    public static final SignatureSerializer INSTANCE = new SignatureSerializer();

    private SignatureSerializer() {
    }

    @NotNull
    public final String methodDesc(@NotNull Method method) {
        Class[] parameterTypes;
        Intrinsics.checkParameterIsNotNull(method, Param.METHOD);
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Class cls : method.getParameterTypes()) {
            Intrinsics.checkExpressionValueIsNotNull(cls, "parameterType");
            sb.append(ReflectClassUtilKt.getDesc(cls));
        }
        sb.append(")");
        Class returnType = method.getReturnType();
        Intrinsics.checkExpressionValueIsNotNull(returnType, "method.returnType");
        sb.append(ReflectClassUtilKt.getDesc(returnType));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    @NotNull
    public final String constructorDesc(@NotNull Constructor<?> constructor) {
        Class[] parameterTypes;
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Class cls : constructor.getParameterTypes()) {
            Intrinsics.checkExpressionValueIsNotNull(cls, "parameterType");
            sb.append(ReflectClassUtilKt.getDesc(cls));
        }
        sb.append(")V");
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    @NotNull
    public final String fieldDesc(@NotNull Field field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Class type = field.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "field.type");
        return ReflectClassUtilKt.getDesc(type);
    }
}
