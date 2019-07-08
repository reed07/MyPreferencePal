package kotlin.reflect.jvm.internal;

import com.myfitnesspal.shared.constants.Constants.Extras;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.FunctionWithAllInvokes.DefaultImpls;
import kotlin.reflect.jvm.internal.ReflectProperties.LazySoftVal;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.CallerImpl.BoundConstructor;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundInstance;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundJvmStaticInObject;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundStatic;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.Instance;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.JvmStaticInObject;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.Static;
import kotlin.reflect.jvm.internal.calls.CallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00032\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00042\u00020\u0005B)\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\fB\u0017\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fB5\b\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\u0011J\u001e\u0010.\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003000/2\n\u00101\u001a\u0006\u0012\u0002\b\u000300H\u0002J\u0010\u00102\u001a\u0002032\u0006\u00101\u001a\u000204H\u0002J\u0010\u00105\u001a\u0002032\u0006\u00101\u001a\u000204H\u0002J\u0010\u00106\u001a\u0002032\u0006\u00101\u001a\u000204H\u0002J\u0013\u00107\u001a\u00020%2\b\u00108\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u00109\u001a\u00020\u0013H\u0016J\b\u0010:\u001a\u00020\tH\u0016R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00178VX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR!\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00178VX\u0002¢\u0006\f\n\u0004\b \u0010\u001b\u001a\u0004\b\u001f\u0010\u0019R\u001b\u0010\r\u001a\u00020\u000e8VX\u0002¢\u0006\f\n\u0004\b#\u0010\u001b\u001a\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020%8VX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010&R\u0014\u0010'\u001a\u00020%8VX\u0004¢\u0006\u0006\u001a\u0004\b'\u0010&R\u0014\u0010(\u001a\u00020%8VX\u0004¢\u0006\u0006\u001a\u0004\b(\u0010&R\u0014\u0010)\u001a\u00020%8VX\u0004¢\u0006\u0006\u001a\u0004\b)\u0010&R\u0014\u0010*\u001a\u00020%8VX\u0004¢\u0006\u0006\u001a\u0004\b*\u0010&R\u0014\u0010+\u001a\u00020%8VX\u0004¢\u0006\u0006\u001a\u0004\b+\u0010&R\u0014\u0010\b\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lkotlin/reflect/jvm/internal/KFunctionImpl;", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "", "Lkotlin/reflect/KFunction;", "Lkotlin/jvm/internal/FunctionBase;", "Lkotlin/reflect/jvm/internal/FunctionWithAllInvokes;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "boundReceiver", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;)V", "descriptorInitialValue", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;Ljava/lang/Object;)V", "arity", "", "getArity", "()I", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "defaultCaller", "getDefaultCaller", "defaultCaller$delegate", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "descriptor$delegate", "isBound", "", "()Z", "isExternal", "isInfix", "isInline", "isOperator", "isSuspend", "getName", "()Ljava/lang/String;", "createConstructorCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl;", "Ljava/lang/reflect/Constructor;", "member", "createInstanceMethodCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl$Method;", "Ljava/lang/reflect/Method;", "createJvmStaticInObjectCaller", "createStaticMethodCaller", "equals", "other", "hashCode", "toString", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
/* compiled from: KFunctionImpl.kt */
public final class KFunctionImpl extends KCallableImpl<Object> implements FunctionBase<Object>, KFunction<Object>, FunctionWithAllInvokes {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KFunctionImpl.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KFunctionImpl.class), Extras.CALLER, "getCaller()Lkotlin/reflect/jvm/internal/calls/Caller;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KFunctionImpl.class), "defaultCaller", "getDefaultCaller()Lkotlin/reflect/jvm/internal/calls/Caller;"))};
    private final Object boundReceiver;
    @NotNull
    private final LazySoftVal caller$delegate;
    @NotNull
    private final KDeclarationContainerImpl container;
    @Nullable
    private final LazySoftVal defaultCaller$delegate;
    @NotNull
    private final LazySoftVal descriptor$delegate;
    /* access modifiers changed from: private */
    public final String signature;

    @NotNull
    public Caller<?> getCaller() {
        return (Caller) this.caller$delegate.getValue(this, $$delegatedProperties[1]);
    }

    @Nullable
    public Caller<?> getDefaultCaller() {
        return (Caller) this.defaultCaller$delegate.getValue(this, $$delegatedProperties[2]);
    }

    @NotNull
    public FunctionDescriptor getDescriptor() {
        return (FunctionDescriptor) this.descriptor$delegate.getValue(this, $$delegatedProperties[0]);
    }

    @Nullable
    public Object invoke() {
        return DefaultImpls.invoke(this);
    }

    @Nullable
    public Object invoke(@Nullable Object obj) {
        return DefaultImpls.invoke(this, obj);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2) {
        return DefaultImpls.invoke(this, obj, obj2);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        return DefaultImpls.invoke(this, obj, obj2, obj3);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14, @Nullable Object obj15) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14, @Nullable Object obj15, @Nullable Object obj16) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14, @Nullable Object obj15, @Nullable Object obj16, @Nullable Object obj17) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14, @Nullable Object obj15, @Nullable Object obj16, @Nullable Object obj17, @Nullable Object obj18) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14, @Nullable Object obj15, @Nullable Object obj16, @Nullable Object obj17, @Nullable Object obj18, @Nullable Object obj19) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14, @Nullable Object obj15, @Nullable Object obj16, @Nullable Object obj17, @Nullable Object obj18, @Nullable Object obj19, @Nullable Object obj20) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14, @Nullable Object obj15, @Nullable Object obj16, @Nullable Object obj17, @Nullable Object obj18, @Nullable Object obj19, @Nullable Object obj20, @Nullable Object obj21) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21);
    }

    @Nullable
    public Object invoke(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6, @Nullable Object obj7, @Nullable Object obj8, @Nullable Object obj9, @Nullable Object obj10, @Nullable Object obj11, @Nullable Object obj12, @Nullable Object obj13, @Nullable Object obj14, @Nullable Object obj15, @Nullable Object obj16, @Nullable Object obj17, @Nullable Object obj18, @Nullable Object obj19, @Nullable Object obj20, @Nullable Object obj21, @Nullable Object obj22) {
        return DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21, obj22);
    }

    @NotNull
    public KDeclarationContainerImpl getContainer() {
        return this.container;
    }

    /* synthetic */ KFunctionImpl(KDeclarationContainerImpl kDeclarationContainerImpl, String str, String str2, FunctionDescriptor functionDescriptor, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kDeclarationContainerImpl, str, str2, functionDescriptor, (i & 16) != 0 ? CallableReference.NO_RECEIVER : obj);
    }

    private KFunctionImpl(KDeclarationContainerImpl kDeclarationContainerImpl, String str, String str2, FunctionDescriptor functionDescriptor, Object obj) {
        this.container = kDeclarationContainerImpl;
        this.signature = str2;
        this.boundReceiver = obj;
        this.descriptor$delegate = ReflectProperties.lazySoft(functionDescriptor, new KFunctionImpl$descriptor$2(this, str));
        this.caller$delegate = ReflectProperties.lazySoft(new KFunctionImpl$caller$2(this));
        this.defaultCaller$delegate = ReflectProperties.lazySoft(new KFunctionImpl$defaultCaller$2(this));
    }

    public KFunctionImpl(@NotNull KDeclarationContainerImpl kDeclarationContainerImpl, @NotNull String str, @NotNull String str2, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(kDeclarationContainerImpl, "container");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "signature");
        this(kDeclarationContainerImpl, str, str2, null, obj);
    }

    public KFunctionImpl(@NotNull KDeclarationContainerImpl kDeclarationContainerImpl, @NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(kDeclarationContainerImpl, "container");
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "descriptor");
        String asString = functionDescriptor.getName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "descriptor.name.asString()");
        this(kDeclarationContainerImpl, asString, RuntimeTypeMapper.INSTANCE.mapSignature(functionDescriptor).asString(), functionDescriptor, null, 16, null);
    }

    public boolean isBound() {
        return !Intrinsics.areEqual(this.boundReceiver, CallableReference.NO_RECEIVER);
    }

    @NotNull
    public String getName() {
        String asString = getDescriptor().getName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "descriptor.name.asString()");
        return asString;
    }

    /* access modifiers changed from: private */
    public final Method createStaticMethodCaller(java.lang.reflect.Method method) {
        return isBound() ? new BoundStatic(method, this.boundReceiver) : new Static(method);
    }

    /* access modifiers changed from: private */
    public final Method createJvmStaticInObjectCaller(java.lang.reflect.Method method) {
        return isBound() ? new BoundJvmStaticInObject(method) : new JvmStaticInObject(method);
    }

    /* access modifiers changed from: private */
    public final Method createInstanceMethodCaller(java.lang.reflect.Method method) {
        return isBound() ? new BoundInstance(method, this.boundReceiver) : new Instance(method);
    }

    /* access modifiers changed from: private */
    public final CallerImpl<Constructor<?>> createConstructorCaller(Constructor<?> constructor) {
        return isBound() ? new BoundConstructor<>(constructor, this.boundReceiver) : new CallerImpl.Constructor<>(constructor);
    }

    public int getArity() {
        return CallerKt.getArity(getCaller());
    }

    public boolean isInline() {
        return getDescriptor().isInline();
    }

    public boolean isExternal() {
        return getDescriptor().isExternal();
    }

    public boolean isOperator() {
        return getDescriptor().isOperator();
    }

    public boolean isInfix() {
        return getDescriptor().isInfix();
    }

    public boolean isSuspend() {
        return getDescriptor().isSuspend();
    }

    public boolean equals(@Nullable Object obj) {
        KFunctionImpl asKFunctionImpl = UtilKt.asKFunctionImpl(obj);
        boolean z = false;
        if (asKFunctionImpl == null) {
            return false;
        }
        if (Intrinsics.areEqual((Object) getContainer(), (Object) asKFunctionImpl.getContainer()) && Intrinsics.areEqual((Object) getName(), (Object) asKFunctionImpl.getName()) && Intrinsics.areEqual((Object) this.signature, (Object) asKFunctionImpl.signature) && Intrinsics.areEqual(this.boundReceiver, asKFunctionImpl.boundReceiver)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (((getContainer().hashCode() * 31) + getName().hashCode()) * 31) + this.signature.hashCode();
    }

    @NotNull
    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderFunction(getDescriptor());
    }
}
