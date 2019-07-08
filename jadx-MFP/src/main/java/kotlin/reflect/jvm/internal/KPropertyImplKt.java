package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.KotlinFunction;
import kotlin.reflect.jvm.internal.JvmPropertySignature.JavaField;
import kotlin.reflect.jvm.internal.JvmPropertySignature.JavaMethodProperty;
import kotlin.reflect.jvm.internal.JvmPropertySignature.KotlinProperty;
import kotlin.reflect.jvm.internal.JvmPropertySignature.MappedKotlinProperty;
import kotlin.reflect.jvm.internal.KPropertyImpl.Accessor;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundInstance;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundJvmStaticInObject;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundStatic;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.Instance;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.JvmStaticInObject;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.Static;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import kotlin.reflect.jvm.internal.calls.ThrowingCaller;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\f\u0010\u0005\u001a\u00020\u0004*\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/calls/Caller;", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "isGetter", "", "isJvmFieldPropertyInCompanionObject", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* compiled from: KPropertyImpl.kt */
public final class KPropertyImplKt {
    /* access modifiers changed from: private */
    public static final Caller<?> computeCallerForAccessor(@NotNull Accessor<?, ?> accessor, boolean z) {
        CallerImpl callerImpl;
        KotlinFunction kotlinFunction;
        Caller<?> caller;
        Method method;
        CallerImpl.Method method2;
        CallerImpl.Method method3;
        CallerImpl.Method method4;
        CallerImpl.Method method5;
        if (KDeclarationContainerImpl.Companion.getLOCAL_PROPERTY_SIGNATURE$kotlin_reflect_api().matches(accessor.getProperty().getSignature())) {
            return ThrowingCaller.INSTANCE;
        }
        KPropertyImplKt$computeCallerForAccessor$1 kPropertyImplKt$computeCallerForAccessor$1 = new KPropertyImplKt$computeCallerForAccessor$1(accessor);
        KPropertyImplKt$computeCallerForAccessor$3 kPropertyImplKt$computeCallerForAccessor$3 = new KPropertyImplKt$computeCallerForAccessor$3(accessor, z, new KPropertyImplKt$computeCallerForAccessor$2(accessor), kPropertyImplKt$computeCallerForAccessor$1);
        JvmPropertySignature mapPropertySignature = RuntimeTypeMapper.INSTANCE.mapPropertySignature(accessor.getProperty().getDescriptor());
        if (mapPropertySignature instanceof KotlinProperty) {
            KotlinProperty kotlinProperty = (KotlinProperty) mapPropertySignature;
            JvmPropertySignature signature = kotlinProperty.getSignature();
            JvmMethodSignature jvmMethodSignature = z ? signature.hasGetter() ? signature.getGetter() : null : signature.hasSetter() ? signature.getSetter() : null;
            Method findMethodBySignature = jvmMethodSignature != null ? accessor.getProperty().getContainer().findMethodBySignature(kotlinProperty.getNameResolver().getString(jvmMethodSignature.getName()), kotlinProperty.getNameResolver().getString(jvmMethodSignature.getDesc()), UtilKt.isPublicInBytecode(accessor.getDescriptor())) : null;
            if (findMethodBySignature == null) {
                Field javaField = accessor.getProperty().getJavaField();
                if (javaField != null) {
                    callerImpl = kPropertyImplKt$computeCallerForAccessor$3.invoke(javaField);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("No accessors or field is found for property ");
                    sb.append(accessor.getProperty());
                    throw new KotlinReflectionInternalError(sb.toString());
                }
            } else if (!Modifier.isStatic(findMethodBySignature.getModifiers())) {
                if (accessor.isBound()) {
                    method5 = new BoundInstance(findMethodBySignature, accessor.getProperty().getBoundReceiver());
                } else {
                    method5 = new Instance(findMethodBySignature);
                }
                callerImpl = method5;
            } else if (kPropertyImplKt$computeCallerForAccessor$1.invoke()) {
                if (accessor.isBound()) {
                    method4 = new BoundJvmStaticInObject(findMethodBySignature);
                } else {
                    method4 = new JvmStaticInObject(findMethodBySignature);
                }
                callerImpl = method4;
            } else {
                if (accessor.isBound()) {
                    method3 = new BoundStatic(findMethodBySignature, accessor.getProperty().getBoundReceiver());
                } else {
                    method3 = new Static(findMethodBySignature);
                }
                callerImpl = method3;
            }
        } else if (mapPropertySignature instanceof JavaField) {
            callerImpl = kPropertyImplKt$computeCallerForAccessor$3.invoke(((JavaField) mapPropertySignature).getField());
        } else if (mapPropertySignature instanceof JavaMethodProperty) {
            if (z) {
                method = ((JavaMethodProperty) mapPropertySignature).getGetterMethod();
            } else {
                JavaMethodProperty javaMethodProperty = (JavaMethodProperty) mapPropertySignature;
                method = javaMethodProperty.getSetterMethod();
                if (method == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("No source found for setter of Java method property: ");
                    sb2.append(javaMethodProperty.getGetterMethod());
                    throw new KotlinReflectionInternalError(sb2.toString());
                }
            }
            if (accessor.isBound()) {
                method2 = new BoundInstance(method, accessor.getProperty().getBoundReceiver());
            } else {
                method2 = new Instance(method);
            }
            callerImpl = method2;
        } else if (mapPropertySignature instanceof MappedKotlinProperty) {
            if (z) {
                kotlinFunction = ((MappedKotlinProperty) mapPropertySignature).getGetterSignature();
            } else {
                kotlinFunction = ((MappedKotlinProperty) mapPropertySignature).getSetterSignature();
                if (kotlinFunction == null) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("No setter found for property ");
                    sb3.append(accessor.getProperty());
                    throw new KotlinReflectionInternalError(sb3.toString());
                }
            }
            Method findMethodBySignature2 = accessor.getProperty().getContainer().findMethodBySignature(kotlinFunction.getMethodName(), kotlinFunction.getMethodDesc(), UtilKt.isPublicInBytecode(accessor.getDescriptor()));
            if (findMethodBySignature2 != null) {
                boolean z2 = !Modifier.isStatic(findMethodBySignature2.getModifiers());
                if (!_Assertions.ENABLED || z2) {
                    if (accessor.isBound()) {
                        caller = new BoundInstance<>(findMethodBySignature2, accessor.getProperty().getBoundReceiver());
                    } else {
                        caller = new Instance<>(findMethodBySignature2);
                    }
                    return caller;
                }
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Mapped property cannot have a static accessor: ");
                sb4.append(accessor.getProperty());
                throw new AssertionError(sb4.toString());
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("No accessor found for property ");
            sb5.append(accessor.getProperty());
            throw new KotlinReflectionInternalError(sb5.toString());
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(callerImpl, accessor.getDescriptor(), false, 2, null);
    }

    /* access modifiers changed from: private */
    public static final boolean isJvmFieldPropertyInCompanionObject(@NotNull PropertyDescriptor propertyDescriptor) {
        DeclarationDescriptor containingDeclaration = propertyDescriptor.getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "containingDeclaration");
        if (!DescriptorUtils.isCompanionObject(containingDeclaration)) {
            return false;
        }
        DeclarationDescriptor containingDeclaration2 = containingDeclaration.getContainingDeclaration();
        boolean z = true;
        if ((DescriptorUtils.isInterface(containingDeclaration2) || DescriptorUtils.isAnnotationClass(containingDeclaration2)) && (!(propertyDescriptor instanceof DeserializedPropertyDescriptor) || !JvmProtoBufUtil.isMovedFromInterfaceCompanion(((DeserializedPropertyDescriptor) propertyDescriptor).getProto()))) {
            z = false;
        }
        return z;
    }
}
