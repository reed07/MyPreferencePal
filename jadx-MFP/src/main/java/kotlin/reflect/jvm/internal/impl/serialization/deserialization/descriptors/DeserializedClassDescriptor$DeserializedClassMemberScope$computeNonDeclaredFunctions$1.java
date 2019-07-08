package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedClassDescriptor.kt */
final class DeserializedClassDescriptor$DeserializedClassMemberScope$computeNonDeclaredFunctions$1 extends Lambda implements Function1<SimpleFunctionDescriptor, Boolean> {
    final /* synthetic */ DeserializedClassMemberScope this$0;

    DeserializedClassDescriptor$DeserializedClassMemberScope$computeNonDeclaredFunctions$1(DeserializedClassMemberScope deserializedClassMemberScope) {
        this.this$0 = deserializedClassMemberScope;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((SimpleFunctionDescriptor) obj));
    }

    public final boolean invoke(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "it");
        return this.this$0.getC().getComponents().getPlatformDependentDeclarationFilter().isFunctionAvailable(DeserializedClassDescriptor.this, simpleFunctionDescriptor);
    }
}
