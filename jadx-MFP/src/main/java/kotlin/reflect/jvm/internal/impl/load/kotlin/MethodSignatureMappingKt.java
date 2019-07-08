package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Primitive;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: methodSignatureMapping.kt */
public final class MethodSignatureMappingKt {
    @NotNull
    public static final String computeJvmDescriptor(@NotNull FunctionDescriptor functionDescriptor, boolean z, boolean z2) {
        String str;
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "receiver$0");
        StringBuilder sb = new StringBuilder();
        if (z2) {
            if (functionDescriptor instanceof ConstructorDescriptor) {
                str = "<init>";
            } else {
                str = functionDescriptor.getName().asString();
                Intrinsics.checkExpressionValueIsNotNull(str, "name.asString()");
            }
            sb.append(str);
        }
        sb.append("(");
        for (ValueParameterDescriptor valueParameterDescriptor : functionDescriptor.getValueParameters()) {
            Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);
            KotlinType type = valueParameterDescriptor.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "parameter.type");
            appendErasedType(sb, type);
        }
        sb.append(")");
        if (z) {
            if (TypeSignatureMappingKt.hasVoidReturnType(functionDescriptor)) {
                sb.append("V");
            } else {
                KotlinType returnType = functionDescriptor.getReturnType();
                if (returnType == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(returnType, "returnType!!");
                appendErasedType(sb, returnType);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static /* synthetic */ String computeJvmDescriptor$default(FunctionDescriptor functionDescriptor, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return computeJvmDescriptor(functionDescriptor, z, z2);
    }

    public static final boolean forceSingleValueParameterBoxing(@NotNull CallableDescriptor callableDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "f");
        boolean z = false;
        if (!(callableDescriptor instanceof FunctionDescriptor)) {
            return false;
        }
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) callableDescriptor;
        if (functionDescriptor.getValueParameters().size() != 1 || SpecialBuiltinMembers.isFromJavaOrBuiltins((CallableMemberDescriptor) callableDescriptor) || (!Intrinsics.areEqual((Object) functionDescriptor.getName().asString(), (Object) ProductAction.ACTION_REMOVE))) {
            return false;
        }
        FunctionDescriptor original = functionDescriptor.getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(original, "f.original");
        List valueParameters = original.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "f.original.valueParameters");
        Object single = CollectionsKt.single(valueParameters);
        Intrinsics.checkExpressionValueIsNotNull(single, "f.original.valueParameters.single()");
        KotlinType type = ((ValueParameterDescriptor) single).getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "f.original.valueParameters.single().type");
        JvmType mapToJvmType = mapToJvmType(type);
        JvmPrimitiveType jvmPrimitiveType = null;
        if (!(mapToJvmType instanceof Primitive)) {
            mapToJvmType = null;
        }
        Primitive primitive = (Primitive) mapToJvmType;
        if (primitive != null) {
            jvmPrimitiveType = primitive.getJvmPrimitiveType();
        }
        if (jvmPrimitiveType != JvmPrimitiveType.INT) {
            return false;
        }
        FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(functionDescriptor);
        if (overriddenBuiltinFunctionWithErasedValueParametersInJava == null) {
            return false;
        }
        FunctionDescriptor original2 = overriddenBuiltinFunctionWithErasedValueParametersInJava.getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(original2, "overridden.original");
        List valueParameters2 = original2.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters2, "overridden.original.valueParameters");
        Object single2 = CollectionsKt.single(valueParameters2);
        Intrinsics.checkExpressionValueIsNotNull(single2, "overridden.original.valueParameters.single()");
        KotlinType type2 = ((ValueParameterDescriptor) single2).getType();
        Intrinsics.checkExpressionValueIsNotNull(type2, "overridden.original.valueParameters.single().type");
        JvmType mapToJvmType2 = mapToJvmType(type2);
        DeclarationDescriptor containingDeclaration = overriddenBuiltinFunctionWithErasedValueParametersInJava.getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "overridden.containingDeclaration");
        if (Intrinsics.areEqual((Object) DescriptorUtilsKt.getFqNameUnsafe(containingDeclaration), (Object) KotlinBuiltIns.FQ_NAMES.mutableCollection.toUnsafe()) && (mapToJvmType2 instanceof Object) && Intrinsics.areEqual((Object) ((Object) mapToJvmType2).getInternalName(), (Object) "java/lang/Object")) {
            z = true;
        }
        return z;
    }

    @NotNull
    public static final String getInternalName(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "receiver$0");
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqNameUnsafe unsafe = DescriptorUtilsKt.getFqNameSafe(classDescriptor).toUnsafe();
        Intrinsics.checkExpressionValueIsNotNull(unsafe, "fqNameSafe.toUnsafe()");
        ClassId mapKotlinToJava = javaToKotlinClassMap.mapKotlinToJava(unsafe);
        if (mapKotlinToJava == null) {
            return TypeSignatureMappingKt.computeInternalName$default(classDescriptor, null, false, 2, null);
        }
        JvmClassName byClassId = JvmClassName.byClassId(mapKotlinToJava);
        Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(it)");
        String internalName = byClassId.getInternalName();
        Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(it).internalName");
        return internalName;
    }

    private static final void appendErasedType(@NotNull StringBuilder sb, KotlinType kotlinType) {
        sb.append(mapToJvmType(kotlinType));
    }

    @NotNull
    public static final JvmType mapToJvmType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return (JvmType) TypeSignatureMappingKt.mapType$default(kotlinType, JvmTypeFactoryImpl.INSTANCE, TypeMappingMode.DEFAULT, TypeMappingConfigurationImpl.INSTANCE, null, null, false, 32, null);
    }

    @Nullable
    public static final String computeJvmSignature(@NotNull CallableDescriptor callableDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "receiver$0");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        if (DescriptorUtils.isLocal(callableDescriptor)) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = callableDescriptor.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        if (classDescriptor == null) {
            return null;
        }
        Name name = classDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "classDescriptor.name");
        if (name.isSpecial()) {
            return null;
        }
        CallableDescriptor original = callableDescriptor.getOriginal();
        if (!(original instanceof SimpleFunctionDescriptor)) {
            original = null;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) original;
        if (simpleFunctionDescriptor != null) {
            return signatureBuildingComponents.signature(classDescriptor, computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null));
        }
        return null;
    }
}
