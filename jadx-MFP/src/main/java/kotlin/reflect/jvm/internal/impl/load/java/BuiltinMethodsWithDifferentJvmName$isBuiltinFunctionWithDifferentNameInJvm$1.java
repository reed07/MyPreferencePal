package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Map;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: specialBuiltinMembers.kt */
final class BuiltinMethodsWithDifferentJvmName$isBuiltinFunctionWithDifferentNameInJvm$1 extends Lambda implements Function1<CallableMemberDescriptor, Boolean> {
    final /* synthetic */ SimpleFunctionDescriptor $functionDescriptor;

    BuiltinMethodsWithDifferentJvmName$isBuiltinFunctionWithDifferentNameInJvm$1(SimpleFunctionDescriptor simpleFunctionDescriptor) {
        this.$functionDescriptor = simpleFunctionDescriptor;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((CallableMemberDescriptor) obj));
    }

    public final boolean invoke(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "it");
        Map access$getSIGNATURE_TO_JVM_REPRESENTATION_NAME$p = BuiltinMethodsWithDifferentJvmName.SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(this.$functionDescriptor);
        if (access$getSIGNATURE_TO_JVM_REPRESENTATION_NAME$p != null) {
            return access$getSIGNATURE_TO_JVM_REPRESENTATION_NAME$p.containsKey(computeJvmSignature);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
    }
}
