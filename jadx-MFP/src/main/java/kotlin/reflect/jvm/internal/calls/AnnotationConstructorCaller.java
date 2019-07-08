package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.calls.Caller.DefaultImpls;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0002 !B?\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u001c\u001a\u0004\u0018\u00010\u00102\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0016¢\u0006\u0002\u0010\u001fR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0006X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00168VX\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\""}, d2 = {"Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller;", "Lkotlin/reflect/jvm/internal/calls/Caller;", "", "jClass", "Ljava/lang/Class;", "parameterNames", "", "", "callMode", "Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$CallMode;", "origin", "Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$Origin;", "methods", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/List;Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$CallMode;Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$Origin;Ljava/util/List;)V", "defaultValues", "", "erasedParameterTypes", "member", "getMember", "()Ljava/lang/Void;", "parameterTypes", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "CallMode", "Origin", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
/* compiled from: AnnotationConstructorCaller.kt */
public final class AnnotationConstructorCaller implements Caller {
    private final CallMode callMode;
    private final List<Object> defaultValues;
    private final List<Class<?>> erasedParameterTypes;
    private final Class<?> jClass;
    private final List<Method> methods;
    private final List<String> parameterNames;
    @NotNull
    private final List<Type> parameterTypes;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$CallMode;", "", "(Ljava/lang/String;I)V", "CALL_BY_NAME", "POSITIONAL_CALL", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AnnotationConstructorCaller.kt */
    public enum CallMode {
        CALL_BY_NAME,
        POSITIONAL_CALL
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$Origin;", "", "(Ljava/lang/String;I)V", "JAVA", "KOTLIN", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AnnotationConstructorCaller.kt */
    public enum Origin {
        JAVA,
        KOTLIN
    }

    @Nullable
    public Void getMember() {
        return null;
    }

    public AnnotationConstructorCaller(@NotNull Class<?> cls, @NotNull List<String> list, @NotNull CallMode callMode2, @NotNull Origin origin, @NotNull List<Method> list2) {
        Intrinsics.checkParameterIsNotNull(cls, "jClass");
        Intrinsics.checkParameterIsNotNull(list, "parameterNames");
        Intrinsics.checkParameterIsNotNull(callMode2, "callMode");
        Intrinsics.checkParameterIsNotNull(origin, "origin");
        Intrinsics.checkParameterIsNotNull(list2, "methods");
        this.jClass = cls;
        this.parameterNames = list;
        this.callMode = callMode2;
        this.methods = list2;
        Iterable<Method> iterable = this.methods;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Method genericReturnType : iterable) {
            arrayList.add(genericReturnType.getGenericReturnType());
        }
        this.parameterTypes = (List) arrayList;
        Iterable<Method> iterable2 = this.methods;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (Method returnType : iterable2) {
            Class returnType2 = returnType.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType2, "it");
            Class wrapperByPrimitive = ReflectClassUtilKt.getWrapperByPrimitive(returnType2);
            if (wrapperByPrimitive != null) {
                returnType2 = wrapperByPrimitive;
            }
            arrayList2.add(returnType2);
        }
        this.erasedParameterTypes = (List) arrayList2;
        Iterable<Method> iterable3 = this.methods;
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
        for (Method defaultValue : iterable3) {
            arrayList3.add(defaultValue.getDefaultValue());
        }
        this.defaultValues = (List) arrayList3;
        if (this.callMode == CallMode.POSITIONAL_CALL && origin == Origin.JAVA && (!CollectionsKt.minus((Iterable<? extends T>) this.parameterNames, "value").isEmpty())) {
            throw new UnsupportedOperationException("Positional call of a Java annotation constructor is allowed only if there are no parameters or one parameter named \"value\". This restriction exists because Java annotations (in contrast to Kotlin)do not impose any order on their arguments. Use KCallable#callBy instead.");
        }
    }

    public void checkArguments(@NotNull Object[] objArr) {
        Intrinsics.checkParameterIsNotNull(objArr, "args");
        DefaultImpls.checkArguments(this, objArr);
    }

    public /* synthetic */ AnnotationConstructorCaller(Class cls, List list, CallMode callMode2, Origin origin, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List list3;
        if ((i & 16) != 0) {
            Iterable<String> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (String declaredMethod : iterable) {
                arrayList.add(cls.getDeclaredMethod(declaredMethod, new Class[0]));
            }
            list3 = (List) arrayList;
        } else {
            list3 = list2;
        }
        this(cls, list, callMode2, origin, list3);
    }

    @NotNull
    public Type getReturnType() {
        return this.jClass;
    }

    @NotNull
    public List<Type> getParameterTypes() {
        return this.parameterTypes;
    }

    @Nullable
    public Object call(@NotNull Object[] objArr) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(objArr, "args");
        checkArguments(objArr);
        Collection arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Object obj2 = objArr[i];
            int i3 = i2 + 1;
            if (obj2 == null && this.callMode == CallMode.CALL_BY_NAME) {
                obj = this.defaultValues.get(i2);
            } else {
                obj = AnnotationConstructorCallerKt.transformKotlinToJvm(obj2, (Class) this.erasedParameterTypes.get(i2));
            }
            if (obj != null) {
                arrayList.add(obj);
                i++;
                i2 = i3;
            } else {
                AnnotationConstructorCallerKt.throwIllegalArgumentType(i2, (String) this.parameterNames.get(i2), (Class) this.erasedParameterTypes.get(i2));
                throw null;
            }
        }
        return AnnotationConstructorCallerKt.createAnnotationInstance(this.jClass, MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) CollectionsKt.zip((Iterable<? extends T>) this.parameterNames, (Iterable<? extends R>) (List) arrayList)), this.methods);
    }
}
