package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Primitive;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Contract;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaIncompatibilityRulesOverridabilityCondition.kt */
public final class JavaIncompatibilityRulesOverridabilityCondition implements ExternalOverridabilityCondition {
    public static final Companion Companion = new Companion(null);

    /* compiled from: JavaIncompatibilityRulesOverridabilityCondition.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean doesJavaOverrideHaveIncompatibleValueParameterKinds(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2) {
            Intrinsics.checkParameterIsNotNull(callableDescriptor, "superDescriptor");
            Intrinsics.checkParameterIsNotNull(callableDescriptor2, "subDescriptor");
            if (!(callableDescriptor2 instanceof JavaMethodDescriptor) || !(callableDescriptor instanceof FunctionDescriptor)) {
                return false;
            }
            JavaMethodDescriptor javaMethodDescriptor = (JavaMethodDescriptor) callableDescriptor2;
            FunctionDescriptor functionDescriptor = (FunctionDescriptor) callableDescriptor;
            boolean z = javaMethodDescriptor.getValueParameters().size() == functionDescriptor.getValueParameters().size();
            if (!_Assertions.ENABLED || z) {
                SimpleFunctionDescriptor original = javaMethodDescriptor.getOriginal();
                Intrinsics.checkExpressionValueIsNotNull(original, "subDescriptor.original");
                List valueParameters = original.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "subDescriptor.original.valueParameters");
                Iterable iterable = valueParameters;
                FunctionDescriptor original2 = functionDescriptor.getOriginal();
                Intrinsics.checkExpressionValueIsNotNull(original2, "superDescriptor.original");
                List valueParameters2 = original2.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters2, "superDescriptor.original.valueParameters");
                for (Pair pair : CollectionsKt.zip(iterable, (Iterable<? extends R>) valueParameters2)) {
                    ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) pair.component1();
                    ValueParameterDescriptor valueParameterDescriptor2 = (ValueParameterDescriptor) pair.component2();
                    Companion companion = this;
                    FunctionDescriptor functionDescriptor2 = (FunctionDescriptor) callableDescriptor2;
                    Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "subParameter");
                    boolean z2 = companion.mapValueParameterType(functionDescriptor2, valueParameterDescriptor) instanceof Primitive;
                    Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor2, "superParameter");
                    if (z2 != (companion.mapValueParameterType(functionDescriptor, valueParameterDescriptor2) instanceof Primitive)) {
                        return true;
                    }
                }
                return false;
            }
            throw new AssertionError("External overridability condition with CONFLICTS_ONLY should not be run with different value parameters size");
        }

        private final JvmType mapValueParameterType(FunctionDescriptor functionDescriptor, ValueParameterDescriptor valueParameterDescriptor) {
            if (MethodSignatureMappingKt.forceSingleValueParameterBoxing(functionDescriptor) || isPrimitiveCompareTo(functionDescriptor)) {
                KotlinType type = valueParameterDescriptor.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "valueParameterDescriptor.type");
                return MethodSignatureMappingKt.mapToJvmType(TypeUtilsKt.makeNullable(type));
            }
            KotlinType type2 = valueParameterDescriptor.getType();
            Intrinsics.checkExpressionValueIsNotNull(type2, "valueParameterDescriptor.type");
            return MethodSignatureMappingKt.mapToJvmType(type2);
        }

        private final boolean isPrimitiveCompareTo(FunctionDescriptor functionDescriptor) {
            boolean z = true;
            if (functionDescriptor.getValueParameters().size() != 1) {
                return false;
            }
            DeclarationDescriptor containingDeclaration = functionDescriptor.getContainingDeclaration();
            if (!(containingDeclaration instanceof ClassDescriptor)) {
                containingDeclaration = null;
            }
            ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
            if (classDescriptor == null) {
                return false;
            }
            List valueParameters = functionDescriptor.getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters, "f.valueParameters");
            Object single = CollectionsKt.single(valueParameters);
            Intrinsics.checkExpressionValueIsNotNull(single, "f.valueParameters.single()");
            ClassifierDescriptor declarationDescriptor = ((ValueParameterDescriptor) single).getType().getConstructor().getDeclarationDescriptor();
            if (!(declarationDescriptor instanceof ClassDescriptor)) {
                declarationDescriptor = null;
            }
            ClassDescriptor classDescriptor2 = (ClassDescriptor) declarationDescriptor;
            if (classDescriptor2 == null) {
                return false;
            }
            if (!KotlinBuiltIns.isPrimitiveClass(classDescriptor) || !Intrinsics.areEqual((Object) DescriptorUtilsKt.getFqNameSafe(classDescriptor), (Object) DescriptorUtilsKt.getFqNameSafe(classDescriptor2))) {
                z = false;
            }
            return z;
        }
    }

    @NotNull
    public Result isOverridable(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2, @Nullable ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "superDescriptor");
        Intrinsics.checkParameterIsNotNull(callableDescriptor2, "subDescriptor");
        if (isIncompatibleInAccordanceWithBuiltInOverridabilityRules(callableDescriptor, callableDescriptor2, classDescriptor)) {
            return Result.INCOMPATIBLE;
        }
        if (Companion.doesJavaOverrideHaveIncompatibleValueParameterKinds(callableDescriptor, callableDescriptor2)) {
            return Result.INCOMPATIBLE;
        }
        return Result.UNKNOWN;
    }

    private final boolean isIncompatibleInAccordanceWithBuiltInOverridabilityRules(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, ClassDescriptor classDescriptor) {
        if (!(callableDescriptor instanceof CallableMemberDescriptor) || !(callableDescriptor2 instanceof FunctionDescriptor) || KotlinBuiltIns.isBuiltIn(callableDescriptor2)) {
            return false;
        }
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) callableDescriptor2;
        Name name = functionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "subDescriptor.name");
        if (!builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            BuiltinMethodsWithDifferentJvmName builtinMethodsWithDifferentJvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE;
            Name name2 = functionDescriptor.getName();
            Intrinsics.checkExpressionValueIsNotNull(name2, "subDescriptor.name");
            if (!builtinMethodsWithDifferentJvmName.getSameAsRenamedInJvmBuiltin(name2)) {
                return false;
            }
        }
        CallableMemberDescriptor overriddenSpecialBuiltin = SpecialBuiltinMembers.getOverriddenSpecialBuiltin((CallableMemberDescriptor) callableDescriptor);
        boolean z = callableDescriptor instanceof FunctionDescriptor;
        FunctionDescriptor functionDescriptor2 = (FunctionDescriptor) (!z ? null : callableDescriptor);
        if ((functionDescriptor2 == null || functionDescriptor.isHiddenToOvercomeSignatureClash() != functionDescriptor2.isHiddenToOvercomeSignatureClash()) && (overriddenSpecialBuiltin == null || !functionDescriptor.isHiddenToOvercomeSignatureClash())) {
            return true;
        }
        if (!(classDescriptor instanceof JavaClassDescriptor) || functionDescriptor.getInitialSignatureDescriptor() != null || overriddenSpecialBuiltin == null || SpecialBuiltinMembers.hasRealKotlinSuperClassWithOverrideOf(classDescriptor, overriddenSpecialBuiltin)) {
            return false;
        }
        if ((overriddenSpecialBuiltin instanceof FunctionDescriptor) && z && BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava((FunctionDescriptor) overriddenSpecialBuiltin) != null) {
            String computeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 2, null);
            FunctionDescriptor original = ((FunctionDescriptor) callableDescriptor).getOriginal();
            Intrinsics.checkExpressionValueIsNotNull(original, "superDescriptor.original");
            if (Intrinsics.areEqual((Object) computeJvmDescriptor$default, (Object) MethodSignatureMappingKt.computeJvmDescriptor$default(original, false, false, 2, null))) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public Contract getContract() {
        return Contract.CONFLICTS_ONLY;
    }
}
