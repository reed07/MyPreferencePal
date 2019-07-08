package kotlin.reflect.jvm.internal.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005R\u0018\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\f¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/jvm/internal/structure/ReflectJavaConstructor;", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaMember;", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaConstructor;", "member", "Ljava/lang/reflect/Constructor;", "(Ljava/lang/reflect/Constructor;)V", "getMember", "()Ljava/lang/reflect/Constructor;", "typeParameters", "", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaTypeParameter;", "getTypeParameters", "()Ljava/util/List;", "valueParameters", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaValueParameter;", "getValueParameters", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: ReflectJavaConstructor.kt */
public final class ReflectJavaConstructor extends ReflectJavaMember implements JavaConstructor {
    @NotNull
    private final Constructor<?> member;

    public ReflectJavaConstructor(@NotNull Constructor<?> constructor) {
        Intrinsics.checkParameterIsNotNull(constructor, "member");
        this.member = constructor;
    }

    @NotNull
    public Constructor<?> getMember() {
        return this.member;
    }

    @NotNull
    public List<JavaValueParameter> getValueParameters() {
        Type[] genericParameterTypes = getMember().getGenericParameterTypes();
        Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "types");
        if (genericParameterTypes.length == 0) {
            return CollectionsKt.emptyList();
        }
        Class declaringClass = getMember().getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "klass");
        if (declaringClass.getDeclaringClass() != null && !Modifier.isStatic(declaringClass.getModifiers())) {
            genericParameterTypes = (Type[]) ArraysKt.copyOfRange((T[]) genericParameterTypes, 1, genericParameterTypes.length);
        }
        Annotation[][] parameterAnnotations = getMember().getParameterAnnotations();
        Object[] objArr = (Object[]) parameterAnnotations;
        if (objArr.length >= genericParameterTypes.length) {
            if (objArr.length > genericParameterTypes.length) {
                Intrinsics.checkExpressionValueIsNotNull(parameterAnnotations, "annotations");
                parameterAnnotations = (Annotation[][]) ArraysKt.copyOfRange((T[]) objArr, objArr.length - genericParameterTypes.length, objArr.length);
            }
            Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "realTypes");
            Intrinsics.checkExpressionValueIsNotNull(parameterAnnotations, "realAnnotations");
            return getValueParameters(genericParameterTypes, parameterAnnotations, getMember().isVarArgs());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Illegal generic signature: ");
        sb.append(getMember());
        throw new IllegalStateException(sb.toString());
    }

    @NotNull
    public List<ReflectJavaTypeParameter> getTypeParameters() {
        TypeVariable[] typeParameters = getMember().getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "member.typeParameters");
        Collection arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable reflectJavaTypeParameter : typeParameters) {
            arrayList.add(new ReflectJavaTypeParameter(reflectJavaTypeParameter));
        }
        return (List) arrayList;
    }
}
