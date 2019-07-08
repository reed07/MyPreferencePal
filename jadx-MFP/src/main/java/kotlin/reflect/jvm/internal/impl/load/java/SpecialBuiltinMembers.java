package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.TypeCastException;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JvmName(name = "SpecialBuiltinMembers")
/* compiled from: specialBuiltinMembers.kt */
public final class SpecialBuiltinMembers {
    /* access modifiers changed from: private */
    public static final FqName child(@NotNull FqName fqName, String str) {
        FqName child = fqName.child(Name.identifier(str));
        Intrinsics.checkExpressionValueIsNotNull(child, "child(Name.identifier(name))");
        return child;
    }

    /* access modifiers changed from: private */
    public static final FqName childSafe(@NotNull FqNameUnsafe fqNameUnsafe, String str) {
        FqName safe = fqNameUnsafe.child(Name.identifier(str)).toSafe();
        Intrinsics.checkExpressionValueIsNotNull(safe, "child(Name.identifier(name)).toSafe()");
        return safe;
    }

    /* access modifiers changed from: private */
    public static final NameAndSignature method(@NotNull String str, String str2, String str3, String str4) {
        Name identifier = Name.identifier(str2);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(name)");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append('(');
        sb.append(str3);
        sb.append(')');
        sb.append(str4);
        return new NameAndSignature(identifier, signatureBuildingComponents.signature(str, sb.toString()));
    }

    @Nullable
    public static final <T extends CallableMemberDescriptor> T getOverriddenBuiltinWithDifferentJvmName(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        T t2 = null;
        if (!BuiltinMethodsWithDifferentJvmName.INSTANCE.getORIGINAL_SHORT_NAMES().contains(t.getName()) && !BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES$descriptors_jvm().contains(DescriptorUtilsKt.getPropertyIfAccessor(t).getName())) {
            return null;
        }
        if ((t instanceof PropertyDescriptor) || (t instanceof PropertyAccessorDescriptor)) {
            t2 = DescriptorUtilsKt.firstOverridden$default(t, false, SpecialBuiltinMembers$getOverriddenBuiltinWithDifferentJvmName$1.INSTANCE, 1, null);
        } else if (t instanceof SimpleFunctionDescriptor) {
            t2 = DescriptorUtilsKt.firstOverridden$default(t, false, SpecialBuiltinMembers$getOverriddenBuiltinWithDifferentJvmName$2.INSTANCE, 1, null);
        }
        return t2;
    }

    public static final boolean doesOverrideBuiltinWithDifferentJvmName(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "receiver$0");
        return getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor) != null;
    }

    @Nullable
    public static final <T extends CallableMemberDescriptor> T getOverriddenSpecialBuiltin(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        T overriddenBuiltinWithDifferentJvmName = getOverriddenBuiltinWithDifferentJvmName(t);
        if (overriddenBuiltinWithDifferentJvmName != null) {
            return overriddenBuiltinWithDifferentJvmName;
        }
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = t.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        if (!builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return null;
        }
        return DescriptorUtilsKt.firstOverridden$default(t, false, SpecialBuiltinMembers$getOverriddenSpecialBuiltin$2.INSTANCE, 1, null);
    }

    @Nullable
    public static final String getJvmMethodNameIfSpecial(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "callableMemberDescriptor");
        CallableMemberDescriptor overriddenBuiltinThatAffectsJvmName = getOverriddenBuiltinThatAffectsJvmName(callableMemberDescriptor);
        String str = null;
        if (overriddenBuiltinThatAffectsJvmName != null) {
            CallableMemberDescriptor propertyIfAccessor = DescriptorUtilsKt.getPropertyIfAccessor(overriddenBuiltinThatAffectsJvmName);
            if (propertyIfAccessor != null) {
                if (propertyIfAccessor instanceof PropertyDescriptor) {
                    str = BuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(propertyIfAccessor);
                } else if (propertyIfAccessor instanceof SimpleFunctionDescriptor) {
                    Name jvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE.getJvmName((SimpleFunctionDescriptor) propertyIfAccessor);
                    if (jvmName != null) {
                        str = jvmName.asString();
                    }
                }
                return str;
            }
        }
        return null;
    }

    private static final CallableMemberDescriptor getOverriddenBuiltinThatAffectsJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        if (KotlinBuiltIns.isBuiltIn(callableMemberDescriptor)) {
            return getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor);
        }
        return null;
    }

    public static final boolean hasRealKotlinSuperClassWithOverrideOf(@NotNull ClassDescriptor classDescriptor, @NotNull CallableDescriptor callableDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "receiver$0");
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "specialCallableDescriptor");
        DeclarationDescriptor containingDeclaration = callableDescriptor.getContainingDeclaration();
        if (containingDeclaration != null) {
            SimpleType defaultType = ((ClassDescriptor) containingDeclaration).getDefaultType();
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "(specialCallableDescript…ssDescriptor).defaultType");
            ClassDescriptor superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(classDescriptor);
            while (true) {
                boolean z = false;
                if (superClassDescriptor == null) {
                    return false;
                }
                if (!(superClassDescriptor instanceof JavaClassDescriptor)) {
                    if (TypeCheckingProcedure.findCorrespondingSupertype(superClassDescriptor.getDefaultType(), defaultType) != null) {
                        z = true;
                    }
                    if (z) {
                        return !KotlinBuiltIns.isBuiltIn(superClassDescriptor);
                    }
                }
                superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(superClassDescriptor);
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
    }

    public static final boolean isFromJava(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "receiver$0");
        return DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getContainingDeclaration() instanceof JavaClassDescriptor;
    }

    public static final boolean isFromJavaOrBuiltins(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "receiver$0");
        return isFromJava(callableMemberDescriptor) || KotlinBuiltIns.isBuiltIn(callableMemberDescriptor);
    }
}
