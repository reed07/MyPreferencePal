package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Contract;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ErasedOverridabilityCondition.kt */
public final class ErasedOverridabilityCondition implements ExternalOverridabilityCondition {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Result.values().length];

        static {
            $EnumSwitchMapping$0[Result.OVERRIDABLE.ordinal()] = 1;
        }
    }

    @NotNull
    public ExternalOverridabilityCondition.Result isOverridable(@NotNull CallableDescriptor callableDescriptor, @NotNull CallableDescriptor callableDescriptor2, @Nullable ClassDescriptor classDescriptor) {
        boolean z;
        ExternalOverridabilityCondition.Result result;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "superDescriptor");
        Intrinsics.checkParameterIsNotNull(callableDescriptor2, "subDescriptor");
        if (callableDescriptor2 instanceof JavaMethodDescriptor) {
            JavaMethodDescriptor javaMethodDescriptor = (JavaMethodDescriptor) callableDescriptor2;
            List typeParameters = javaMethodDescriptor.getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters, "subDescriptor.typeParameters");
            if (!(!typeParameters.isEmpty())) {
                OverrideCompatibilityInfo basicOverridabilityProblem = OverridingUtil.getBasicOverridabilityProblem(callableDescriptor, callableDescriptor2);
                KotlinType kotlinType = null;
                if ((basicOverridabilityProblem != null ? basicOverridabilityProblem.getResult() : null) != null) {
                    return ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                List valueParameters = javaMethodDescriptor.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "subDescriptor.valueParameters");
                Sequence map = SequencesKt.map(CollectionsKt.asSequence(valueParameters), ErasedOverridabilityCondition$isOverridable$signatureTypes$1.INSTANCE);
                KotlinType returnType = javaMethodDescriptor.getReturnType();
                if (returnType == null) {
                    Intrinsics.throwNpe();
                }
                Sequence plus = SequencesKt.plus(map, (Object) returnType);
                ReceiverParameterDescriptor extensionReceiverParameter = javaMethodDescriptor.getExtensionReceiverParameter();
                if (extensionReceiverParameter != null) {
                    kotlinType = extensionReceiverParameter.getType();
                }
                Iterator it = SequencesKt.plus(plus, (Iterable) CollectionsKt.listOfNotNull(kotlinType)).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    KotlinType kotlinType2 = (KotlinType) it.next();
                    if (!(!kotlinType2.getArguments().isEmpty()) || (kotlinType2.unwrap() instanceof RawTypeImpl)) {
                        z2 = false;
                        continue;
                    } else {
                        z2 = true;
                        continue;
                    }
                    if (z2) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    return ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                CallableDescriptor callableDescriptor3 = (CallableDescriptor) callableDescriptor.substitute(RawSubstitution.INSTANCE.buildSubstitutor());
                if (callableDescriptor3 == null) {
                    return ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                if (callableDescriptor3 instanceof SimpleFunctionDescriptor) {
                    SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) callableDescriptor3;
                    List typeParameters2 = simpleFunctionDescriptor.getTypeParameters();
                    Intrinsics.checkExpressionValueIsNotNull(typeParameters2, "erasedSuper.typeParameters");
                    if (!typeParameters2.isEmpty()) {
                        FunctionDescriptor build = simpleFunctionDescriptor.newCopyBuilder().setTypeParameters(CollectionsKt.emptyList()).build();
                        if (build == null) {
                            Intrinsics.throwNpe();
                        }
                        callableDescriptor3 = build;
                    }
                }
                OverrideCompatibilityInfo isOverridableByWithoutExternalConditions = OverridingUtil.DEFAULT.isOverridableByWithoutExternalConditions(callableDescriptor3, callableDescriptor2, false);
                Intrinsics.checkExpressionValueIsNotNull(isOverridableByWithoutExternalConditions, "OverridingUtil.DEFAULT.i…er, subDescriptor, false)");
                Result result2 = isOverridableByWithoutExternalConditions.getResult();
                Intrinsics.checkExpressionValueIsNotNull(result2, "OverridingUtil.DEFAULT.i…Descriptor, false).result");
                if (WhenMappings.$EnumSwitchMapping$0[result2.ordinal()] != 1) {
                    result = ExternalOverridabilityCondition.Result.UNKNOWN;
                } else {
                    result = ExternalOverridabilityCondition.Result.OVERRIDABLE;
                }
                return result;
            }
        }
        return ExternalOverridabilityCondition.Result.UNKNOWN;
    }

    @NotNull
    public Contract getContract() {
        return Contract.SUCCESS_ONLY;
    }
}
