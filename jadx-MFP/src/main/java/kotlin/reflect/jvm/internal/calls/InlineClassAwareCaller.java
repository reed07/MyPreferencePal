package kotlin.reflect.jvm.internal.calls;

import com.myfitnesspal.shared.constants.Constants.Extras;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001'B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001fH\u0016¢\u0006\u0002\u0010 J\u0010\u0010!\u001a\u00020\"*\u0006\u0012\u0002\b\u00030#H\u0002J\u0010\u0010$\u001a\u00020\"*\u0006\u0012\u0002\b\u00030#H\u0002J\u0012\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010#*\u00020&H\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00028\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158VX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00168VX\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller;", "M", "Ljava/lang/reflect/Member;", "Lkotlin/reflect/jvm/internal/calls/Caller;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "caller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl;", "isDefault", "", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;Lkotlin/reflect/jvm/internal/calls/CallerImpl;Z)V", "data", "Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "getData", "()Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "data$delegate", "Lkotlin/Lazy;", "member", "getMember", "()Ljava/lang/reflect/Member;", "parameterTypes", "", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "getBoxMethod", "Ljava/lang/reflect/Method;", "Ljava/lang/Class;", "getUnboxMethod", "toInlineClass", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "BoxUnboxData", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
/* compiled from: InlineClassAwareCaller.kt */
public final class InlineClassAwareCaller<M extends Member> implements Caller<M> {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(InlineClassAwareCaller.class), "data", "getData()Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;"))};
    /* access modifiers changed from: private */
    public final CallerImpl<M> caller;
    private final Lazy data$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<? extends T>) new InlineClassAwareCaller$data$2<Object>(this));
    /* access modifiers changed from: private */
    public final CallableMemberDescriptor descriptor;
    /* access modifiers changed from: private */
    public final boolean isDefault;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003H\u0002J\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u0002¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "", "argumentRange", "Lkotlin/ranges/IntRange;", "unbox", "", "Ljava/lang/reflect/Method;", "box", "(Lkotlin/ranges/IntRange;[Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getArgumentRange", "()Lkotlin/ranges/IntRange;", "getBox", "()Ljava/lang/reflect/Method;", "getUnbox", "()[Ljava/lang/reflect/Method;", "[Ljava/lang/reflect/Method;", "component1", "component2", "component3", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
    /* compiled from: InlineClassAwareCaller.kt */
    private static final class BoxUnboxData {
        @NotNull
        private final IntRange argumentRange;
        @Nullable
        private final Method box;
        @NotNull
        private final Method[] unbox;

        public BoxUnboxData(@NotNull IntRange intRange, @NotNull Method[] methodArr, @Nullable Method method) {
            Intrinsics.checkParameterIsNotNull(intRange, "argumentRange");
            Intrinsics.checkParameterIsNotNull(methodArr, "unbox");
            this.argumentRange = intRange;
            this.unbox = methodArr;
            this.box = method;
        }

        @NotNull
        public final IntRange component1() {
            return this.argumentRange;
        }

        @NotNull
        public final Method[] component2() {
            return this.unbox;
        }

        @Nullable
        public final Method component3() {
            return this.box;
        }
    }

    private final BoxUnboxData getData() {
        Lazy lazy = this.data$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (BoxUnboxData) lazy.getValue();
    }

    public InlineClassAwareCaller(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull CallerImpl<? extends M> callerImpl, boolean z) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(callerImpl, Extras.CALLER);
        this.descriptor = callableMemberDescriptor;
        this.caller = callerImpl;
        this.isDefault = z;
    }

    @NotNull
    public M getMember() {
        return this.caller.getMember();
    }

    @NotNull
    public Type getReturnType() {
        return this.caller.getReturnType();
    }

    @NotNull
    public List<Type> getParameterTypes() {
        return this.caller.getParameterTypes();
    }

    @Nullable
    public Object call(@NotNull Object[] objArr) {
        Intrinsics.checkParameterIsNotNull(objArr, "args");
        BoxUnboxData data = getData();
        IntRange component1 = data.component1();
        Method[] component2 = data.component2();
        Method component3 = data.component3();
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        if (copyOf != null) {
            int first = component1.getFirst();
            int last = component1.getLast();
            if (first <= last) {
                while (true) {
                    Method method = component2[first];
                    Object obj = objArr[first];
                    if (!(method == null || obj == null)) {
                        obj = method.invoke(obj, new Object[0]);
                    }
                    copyOf[first] = obj;
                    if (first == last) {
                        break;
                    }
                    first++;
                }
            }
            Object call = this.caller.call(copyOf);
            if (component3 == null) {
                return call;
            }
            Object invoke = component3.invoke(null, new Object[]{call});
            return invoke != null ? invoke : call;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
    }

    /* access modifiers changed from: private */
    public final Method getBoxMethod(@NotNull Class<?> cls) {
        try {
            Method declaredMethod = cls.getDeclaredMethod("box-impl", new Class[]{getUnboxMethod(cls).getReturnType()});
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "getDeclaredMethod(\"box\" …UnboxMethod().returnType)");
            return declaredMethod;
        } catch (NoSuchMethodException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("No box method found in inline class: ");
            sb.append(cls);
            sb.append(" (calling ");
            sb.append(this.descriptor);
            sb.append(')');
            throw new KotlinReflectionInternalError(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public final Method getUnboxMethod(@NotNull Class<?> cls) {
        try {
            Method declaredMethod = cls.getDeclaredMethod("unbox-impl", new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "getDeclaredMethod(\"unbox…FOR_INLINE_CLASS_MEMBERS)");
            return declaredMethod;
        } catch (NoSuchMethodException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("No unbox method found in inline class: ");
            sb.append(cls);
            sb.append(" (calling ");
            sb.append(this.descriptor);
            sb.append(')');
            throw new KotlinReflectionInternalError(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public final Class<?> toInlineClass(@NotNull KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor instanceof ClassDescriptor) {
            ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
            if (classDescriptor.isInline()) {
                Class<?> javaClass = UtilKt.toJavaClass(classDescriptor);
                if (javaClass != null) {
                    return javaClass;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Class object for the class ");
                sb.append(classDescriptor.getName());
                sb.append(" cannot be found (classId=");
                sb.append(DescriptorUtilsKt.getClassId(declarationDescriptor));
                sb.append(')');
                throw new KotlinReflectionInternalError(sb.toString());
            }
        }
        return null;
    }
}
