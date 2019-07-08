package kotlin.reflect.jvm.internal;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KParameter;
import kotlin.reflect.KParameter.Kind;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.IllegalCallableAccessException;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.reflect.jvm.internal.ReflectProperties.LazySoftVal;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J%\u00105\u001a\u00028\u00002\u0016\u00106\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010807\"\u0004\u0018\u000108H\u0016¢\u0006\u0002\u00109J#\u0010:\u001a\u00028\u00002\u0014\u00106\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u0001080;H\u0002¢\u0006\u0002\u0010<J#\u0010=\u001a\u00028\u00002\u0014\u00106\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u0001080;H\u0016¢\u0006\u0002\u0010<J3\u0010>\u001a\u00028\u00002\u0014\u00106\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u0001080;2\f\u0010?\u001a\b\u0012\u0002\b\u0003\u0018\u00010@H\u0000¢\u0006\u0004\bA\u0010BJ\u0012\u0010C\u001a\u0004\u0018\u0001082\u0006\u0010D\u001a\u00020EH\u0002J\n\u0010F\u001a\u0004\u0018\u00010EH\u0002R(\u0010\u0004\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0007 \b*\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00060\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u000b \b*\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\n0\u0005X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\r0\r0\u0005X\u0004¢\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u000f \b*\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00060\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0016R\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\"8VX\u0004¢\u0006\u0006\u001a\u0004\b!\u0010#R\u0014\u0010$\u001a\u00020\"8DX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010#R\u0012\u0010%\u001a\u00020\"X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010#R\u0014\u0010&\u001a\u00020\"8VX\u0004¢\u0006\u0006\u001a\u0004\b&\u0010#R\u0014\u0010'\u001a\u00020\"8VX\u0004¢\u0006\u0006\u001a\u0004\b'\u0010#R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u0012R\u0014\u0010*\u001a\u00020+8VX\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u0012R\u0016\u00101\u001a\u0004\u0018\u0001028VX\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u0006G"}, d2 = {"Lkotlin/reflect/jvm/internal/KCallableImpl;", "R", "Lkotlin/reflect/KCallable;", "()V", "_annotations", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "", "", "kotlin.jvm.PlatformType", "_parameters", "Ljava/util/ArrayList;", "Lkotlin/reflect/KParameter;", "_returnType", "Lkotlin/reflect/jvm/internal/KTypeImpl;", "_typeParameters", "Lkotlin/reflect/jvm/internal/KTypeParameterImpl;", "annotations", "getAnnotations", "()Ljava/util/List;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "defaultCaller", "getDefaultCaller", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "isAbstract", "", "()Z", "isAnnotationConstructor", "isBound", "isFinal", "isOpen", "parameters", "getParameters", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "call", "args", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "callAnnotationConstructor", "", "(Ljava/util/Map;)Ljava/lang/Object;", "callBy", "callDefaultMethod", "continuationArgument", "Lkotlin/coroutines/Continuation;", "callDefaultMethod$kotlin_reflect_api", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "defaultPrimitiveValue", "type", "Ljava/lang/reflect/Type;", "extractContinuationArgument", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
/* compiled from: KCallableImpl.kt */
public abstract class KCallableImpl<R> implements KCallable<R> {
    private final LazySoftVal<List<Annotation>> _annotations;
    private final LazySoftVal<ArrayList<KParameter>> _parameters;
    private final LazySoftVal<KTypeImpl> _returnType;
    private final LazySoftVal<List<KTypeParameterImpl>> _typeParameters;

    @NotNull
    public abstract Caller<?> getCaller();

    @NotNull
    public abstract KDeclarationContainerImpl getContainer();

    @Nullable
    public abstract Caller<?> getDefaultCaller();

    @NotNull
    public abstract CallableMemberDescriptor getDescriptor();

    public abstract boolean isBound();

    public KCallableImpl() {
        LazySoftVal<List<Annotation>> lazySoft = ReflectProperties.lazySoft(new KCallableImpl$_annotations$1(this));
        Intrinsics.checkExpressionValueIsNotNull(lazySoft, "ReflectProperties.lazySo…or.computeAnnotations() }");
        this._annotations = lazySoft;
        LazySoftVal<ArrayList<KParameter>> lazySoft2 = ReflectProperties.lazySoft(new KCallableImpl$_parameters$1(this));
        Intrinsics.checkExpressionValueIsNotNull(lazySoft2, "ReflectProperties.lazySo…ze()\n        result\n    }");
        this._parameters = lazySoft2;
        LazySoftVal<KTypeImpl> lazySoft3 = ReflectProperties.lazySoft(new KCallableImpl$_returnType$1(this));
        Intrinsics.checkExpressionValueIsNotNull(lazySoft3, "ReflectProperties.lazySo…eturnType\n        }\n    }");
        this._returnType = lazySoft3;
        LazySoftVal<List<KTypeParameterImpl>> lazySoft4 = ReflectProperties.lazySoft(new KCallableImpl$_typeParameters$1(this));
        Intrinsics.checkExpressionValueIsNotNull(lazySoft4, "ReflectProperties.lazySo…KTypeParameterImpl)\n    }");
        this._typeParameters = lazySoft4;
    }

    @NotNull
    public List<Annotation> getAnnotations() {
        Object invoke = this._annotations.invoke();
        Intrinsics.checkExpressionValueIsNotNull(invoke, "_annotations()");
        return (List) invoke;
    }

    @NotNull
    public List<KParameter> getParameters() {
        Object invoke = this._parameters.invoke();
        Intrinsics.checkExpressionValueIsNotNull(invoke, "_parameters()");
        return (List) invoke;
    }

    @NotNull
    public KType getReturnType() {
        Object invoke = this._returnType.invoke();
        Intrinsics.checkExpressionValueIsNotNull(invoke, "_returnType()");
        return (KType) invoke;
    }

    @NotNull
    public List<KTypeParameter> getTypeParameters() {
        Object invoke = this._typeParameters.invoke();
        Intrinsics.checkExpressionValueIsNotNull(invoke, "_typeParameters()");
        return (List) invoke;
    }

    @Nullable
    public KVisibility getVisibility() {
        Visibility visibility = getDescriptor().getVisibility();
        Intrinsics.checkExpressionValueIsNotNull(visibility, "descriptor.visibility");
        return UtilKt.toKVisibility(visibility);
    }

    public boolean isFinal() {
        return getDescriptor().getModality() == Modality.FINAL;
    }

    public boolean isOpen() {
        return getDescriptor().getModality() == Modality.OPEN;
    }

    public boolean isAbstract() {
        return getDescriptor().getModality() == Modality.ABSTRACT;
    }

    /* access modifiers changed from: protected */
    public final boolean isAnnotationConstructor() {
        return Intrinsics.areEqual((Object) getName(), (Object) "<init>") && getContainer().getJClass().isAnnotation();
    }

    public R call(@NotNull Object... objArr) {
        Intrinsics.checkParameterIsNotNull(objArr, "args");
        try {
            return getCaller().call(objArr);
        } catch (IllegalAccessException e) {
            throw new IllegalCallableAccessException(e);
        }
    }

    public R callBy(@NotNull Map<KParameter, ? extends Object> map) {
        Intrinsics.checkParameterIsNotNull(map, "args");
        return isAnnotationConstructor() ? callAnnotationConstructor(map) : callDefaultMethod$kotlin_reflect_api(map, null);
    }

    public final R callDefaultMethod$kotlin_reflect_api(@NotNull Map<KParameter, ? extends Object> map, @Nullable Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(map, "args");
        List<KParameter> parameters = getParameters();
        ArrayList arrayList = new ArrayList(parameters.size());
        ArrayList arrayList2 = new ArrayList(1);
        int i = 0;
        boolean z = false;
        int i2 = 0;
        for (KParameter kParameter : parameters) {
            if (i != 0 && i % 32 == 0) {
                arrayList2.add(Integer.valueOf(i2));
                i2 = 0;
            }
            if (map.containsKey(kParameter)) {
                arrayList.add(map.get(kParameter));
            } else if (kParameter.isOptional()) {
                arrayList.add(defaultPrimitiveValue(ReflectJvmMapping.getJavaType(kParameter.getType())));
                i2 = (1 << (i % 32)) | i2;
                z = true;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("No argument provided for a required parameter: ");
                sb.append(kParameter);
                throw new IllegalArgumentException(sb.toString());
            }
            if (kParameter.getKind() == Kind.VALUE) {
                i++;
            }
        }
        if (continuation != null) {
            arrayList.add(continuation);
        }
        if (!z) {
            Object[] array = arrayList.toArray(new Object[0]);
            if (array != null) {
                return call(Arrays.copyOf(array, array.length));
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        arrayList2.add(Integer.valueOf(i2));
        Caller defaultCaller = getDefaultCaller();
        if (defaultCaller != null) {
            arrayList.addAll(arrayList2);
            arrayList.add(null);
            try {
                Object[] array2 = arrayList.toArray(new Object[0]);
                if (array2 != null) {
                    return defaultCaller.call(array2);
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            } catch (IllegalAccessException e) {
                throw new IllegalCallableAccessException(e);
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("This callable does not support a default call: ");
            sb2.append(getDescriptor());
            throw new KotlinReflectionInternalError(sb2.toString());
        }
    }

    private final R callAnnotationConstructor(Map<KParameter, ? extends Object> map) {
        Object obj;
        Iterable<KParameter> parameters = getParameters();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        for (KParameter kParameter : parameters) {
            if (map.containsKey(kParameter)) {
                obj = map.get(kParameter);
                if (obj == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Annotation argument value cannot be null (");
                    sb.append(kParameter);
                    sb.append(')');
                    throw new IllegalArgumentException(sb.toString());
                }
            } else if (kParameter.isOptional()) {
                obj = null;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("No argument provided for a required parameter: ");
                sb2.append(kParameter);
                throw new IllegalArgumentException(sb2.toString());
            }
            arrayList.add(obj);
        }
        List list = (List) arrayList;
        Caller defaultCaller = getDefaultCaller();
        if (defaultCaller != null) {
            try {
                Object[] array = list.toArray(new Object[0]);
                if (array != null) {
                    return defaultCaller.call(array);
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            } catch (IllegalAccessException e) {
                throw new IllegalCallableAccessException(e);
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("This callable does not support a default call: ");
            sb3.append(getDescriptor());
            throw new KotlinReflectionInternalError(sb3.toString());
        }
    }

    private final Object defaultPrimitiveValue(Type type) {
        if (!(type instanceof Class) || !((Class) type).isPrimitive()) {
            return null;
        }
        if (Intrinsics.areEqual((Object) type, (Object) Boolean.TYPE)) {
            return Boolean.valueOf(false);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Character.TYPE)) {
            return Character.valueOf((char) 0);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Byte.TYPE)) {
            return Byte.valueOf((byte) 0);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Short.TYPE)) {
            return Short.valueOf((short) 0);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Integer.TYPE)) {
            return Integer.valueOf(0);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Float.TYPE)) {
            return Float.valueOf(BitmapDescriptorFactory.HUE_RED);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Long.TYPE)) {
            return Long.valueOf(0);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Double.TYPE)) {
            return Double.valueOf(0.0d);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Void.TYPE)) {
            throw new IllegalStateException("Parameter with void type is illegal");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown primitive: ");
        sb.append(type);
        throw new UnsupportedOperationException(sb.toString());
    }

    /* access modifiers changed from: private */
    public final Type extractContinuationArgument() {
        CallableMemberDescriptor descriptor = getDescriptor();
        Type type = null;
        if (!(descriptor instanceof FunctionDescriptor)) {
            descriptor = null;
        }
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) descriptor;
        if (functionDescriptor != null && functionDescriptor.isSuspend()) {
            Object lastOrNull = CollectionsKt.lastOrNull(getCaller().getParameterTypes());
            if (!(lastOrNull instanceof ParameterizedType)) {
                lastOrNull = null;
            }
            ParameterizedType parameterizedType = (ParameterizedType) lastOrNull;
            if (Intrinsics.areEqual(parameterizedType != null ? parameterizedType.getRawType() : null, (Object) Continuation.class)) {
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                Intrinsics.checkExpressionValueIsNotNull(actualTypeArguments, "continuationType.actualTypeArguments");
                Object single = ArraysKt.single((T[]) actualTypeArguments);
                if (!(single instanceof WildcardType)) {
                    single = null;
                }
                WildcardType wildcardType = (WildcardType) single;
                if (wildcardType != null) {
                    Type[] lowerBounds = wildcardType.getLowerBounds();
                    if (lowerBounds != null) {
                        type = (Type) ArraysKt.first((T[]) lowerBounds);
                    }
                }
                return type;
            }
        }
        return null;
    }
}
