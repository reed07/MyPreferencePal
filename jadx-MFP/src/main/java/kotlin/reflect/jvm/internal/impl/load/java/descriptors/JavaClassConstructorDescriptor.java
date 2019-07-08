package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaClassConstructorDescriptor extends ClassConstructorDescriptorImpl implements JavaCallableMemberDescriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Boolean hasStableParameterNames = null;
    private Boolean hasSynthesizedParameterNames = null;

    protected JavaClassConstructorDescriptor(@NotNull ClassDescriptor classDescriptor, @Nullable JavaClassConstructorDescriptor javaClassConstructorDescriptor, @NotNull Annotations annotations, boolean z, @NotNull Kind kind, @NotNull SourceElement sourceElement) {
        super(classDescriptor, javaClassConstructorDescriptor, annotations, z, kind, sourceElement);
    }

    @NotNull
    public static JavaClassConstructorDescriptor createJavaConstructor(@NotNull ClassDescriptor classDescriptor, @NotNull Annotations annotations, boolean z, @NotNull SourceElement sourceElement) {
        JavaClassConstructorDescriptor javaClassConstructorDescriptor = new JavaClassConstructorDescriptor(classDescriptor, null, annotations, z, Kind.DECLARATION, sourceElement);
        return javaClassConstructorDescriptor;
    }

    public boolean hasStableParameterNames() {
        return this.hasStableParameterNames.booleanValue();
    }

    public void setHasStableParameterNames(boolean z) {
        this.hasStableParameterNames = Boolean.valueOf(z);
    }

    public boolean hasSynthesizedParameterNames() {
        return this.hasSynthesizedParameterNames.booleanValue();
    }

    public void setHasSynthesizedParameterNames(boolean z) {
        this.hasSynthesizedParameterNames = Boolean.valueOf(z);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public JavaClassConstructorDescriptor createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement) {
        if (kind == Kind.DECLARATION || kind == Kind.SYNTHESIZED) {
            JavaClassConstructorDescriptor createDescriptor = createDescriptor((ClassDescriptor) declarationDescriptor, (JavaClassConstructorDescriptor) functionDescriptor, kind, sourceElement, annotations);
            createDescriptor.setHasStableParameterNames(hasStableParameterNames());
            createDescriptor.setHasSynthesizedParameterNames(hasSynthesizedParameterNames());
            return createDescriptor;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt at creating a constructor that is not a declaration: \ncopy from: ");
        sb.append(this);
        sb.append("\n");
        sb.append("newOwner: ");
        sb.append(declarationDescriptor);
        sb.append("\n");
        sb.append("kind: ");
        sb.append(kind);
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: protected */
    @NotNull
    public JavaClassConstructorDescriptor createDescriptor(@NotNull ClassDescriptor classDescriptor, @Nullable JavaClassConstructorDescriptor javaClassConstructorDescriptor, @NotNull Kind kind, @NotNull SourceElement sourceElement, @NotNull Annotations annotations) {
        JavaClassConstructorDescriptor javaClassConstructorDescriptor2 = new JavaClassConstructorDescriptor(classDescriptor, javaClassConstructorDescriptor, annotations, this.isPrimary, kind, sourceElement);
        return javaClassConstructorDescriptor2;
    }

    @NotNull
    public JavaClassConstructorDescriptor enhance(@Nullable KotlinType kotlinType, @NotNull List<ValueParameterData> list, @NotNull KotlinType kotlinType2, @Nullable Pair<UserDataKey<?>, ?> pair) {
        ReceiverParameterDescriptor receiverParameterDescriptor;
        KotlinType kotlinType3 = kotlinType;
        JavaClassConstructorDescriptor createSubstitutedCopy = createSubstitutedCopy((DeclarationDescriptor) getContainingDeclaration(), (FunctionDescriptor) null, getKind(), (Name) null, getAnnotations(), getSource());
        if (kotlinType3 == null) {
            receiverParameterDescriptor = null;
        } else {
            receiverParameterDescriptor = DescriptorFactory.createExtensionReceiverParameterForCallable(createSubstitutedCopy, kotlinType3, Annotations.Companion.getEMPTY());
        }
        JavaClassConstructorDescriptor javaClassConstructorDescriptor = createSubstitutedCopy;
        KotlinType kotlinType4 = kotlinType2;
        javaClassConstructorDescriptor.initialize(receiverParameterDescriptor, getDispatchReceiverParameter(), getTypeParameters(), UtilKt.copyValueParameters(list, getValueParameters(), createSubstitutedCopy), kotlinType4, getModality(), getVisibility());
        if (pair != null) {
            createSubstitutedCopy.putInUserDataMap((UserDataKey) pair.getFirst(), pair.getSecond());
        }
        return createSubstitutedCopy;
    }
}
