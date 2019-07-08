package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SignaturePropagator {
    public static final SignaturePropagator DO_NOTHING = new SignaturePropagator() {
        @NotNull
        public PropagatedSignature resolvePropagatedSignature(@NotNull JavaMethod javaMethod, @NotNull ClassDescriptor classDescriptor, @NotNull KotlinType kotlinType, @Nullable KotlinType kotlinType2, @NotNull List<ValueParameterDescriptor> list, @NotNull List<TypeParameterDescriptor> list2) {
            PropagatedSignature propagatedSignature = new PropagatedSignature(kotlinType, kotlinType2, list, list2, Collections.emptyList(), false);
            return propagatedSignature;
        }

        public void reportSignatureErrors(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull List<String> list) {
            throw new UnsupportedOperationException("Should not be called");
        }
    };

    public static class PropagatedSignature {
        private final boolean hasStableParameterNames;
        private final KotlinType receiverType;
        private final KotlinType returnType;
        private final List<String> signatureErrors;
        private final List<TypeParameterDescriptor> typeParameters;
        private final List<ValueParameterDescriptor> valueParameters;

        public PropagatedSignature(@NotNull KotlinType kotlinType, @Nullable KotlinType kotlinType2, @NotNull List<ValueParameterDescriptor> list, @NotNull List<TypeParameterDescriptor> list2, @NotNull List<String> list3, boolean z) {
            this.returnType = kotlinType;
            this.receiverType = kotlinType2;
            this.valueParameters = list;
            this.typeParameters = list2;
            this.signatureErrors = list3;
            this.hasStableParameterNames = z;
        }

        @NotNull
        public KotlinType getReturnType() {
            return this.returnType;
        }

        @Nullable
        public KotlinType getReceiverType() {
            return this.receiverType;
        }

        @NotNull
        public List<ValueParameterDescriptor> getValueParameters() {
            return this.valueParameters;
        }

        @NotNull
        public List<TypeParameterDescriptor> getTypeParameters() {
            return this.typeParameters;
        }

        public boolean hasStableParameterNames() {
            return this.hasStableParameterNames;
        }

        @NotNull
        public List<String> getErrors() {
            return this.signatureErrors;
        }
    }

    void reportSignatureErrors(@NotNull CallableMemberDescriptor callableMemberDescriptor, @NotNull List<String> list);

    @NotNull
    PropagatedSignature resolvePropagatedSignature(@NotNull JavaMethod javaMethod, @NotNull ClassDescriptor classDescriptor, @NotNull KotlinType kotlinType, @Nullable KotlinType kotlinType2, @NotNull List<ValueParameterDescriptor> list, @NotNull List<TypeParameterDescriptor> list2);
}
