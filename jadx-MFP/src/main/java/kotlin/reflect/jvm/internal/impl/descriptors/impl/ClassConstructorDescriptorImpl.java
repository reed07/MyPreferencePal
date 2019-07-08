package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClassConstructorDescriptorImpl extends FunctionDescriptorImpl implements ClassConstructorDescriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Name NAME = Name.special("<init>");
    protected final boolean isPrimary;

    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
    }

    protected ClassConstructorDescriptorImpl(@NotNull ClassDescriptor classDescriptor, @Nullable ConstructorDescriptor constructorDescriptor, @NotNull Annotations annotations, boolean z, @NotNull Kind kind, @NotNull SourceElement sourceElement) {
        super(classDescriptor, constructorDescriptor, annotations, NAME, kind, sourceElement);
        this.isPrimary = z;
    }

    @NotNull
    public static ClassConstructorDescriptorImpl create(@NotNull ClassDescriptor classDescriptor, @NotNull Annotations annotations, boolean z, @NotNull SourceElement sourceElement) {
        ClassConstructorDescriptorImpl classConstructorDescriptorImpl = new ClassConstructorDescriptorImpl(classDescriptor, null, annotations, z, Kind.DECLARATION, sourceElement);
        return classConstructorDescriptorImpl;
    }

    public ClassConstructorDescriptorImpl initialize(@NotNull List<ValueParameterDescriptor> list, @NotNull Visibility visibility, @NotNull List<TypeParameterDescriptor> list2) {
        super.initialize(null, calculateDispatchReceiverParameter(), list2, list, null, Modality.FINAL, visibility);
        return this;
    }

    public ClassConstructorDescriptorImpl initialize(@NotNull List<ValueParameterDescriptor> list, @NotNull Visibility visibility) {
        initialize(list, visibility, getContainingDeclaration().getDeclaredTypeParameters());
        return this;
    }

    @Nullable
    public ReceiverParameterDescriptor calculateDispatchReceiverParameter() {
        ClassDescriptor containingDeclaration = getContainingDeclaration();
        if (containingDeclaration.isInner()) {
            DeclarationDescriptor containingDeclaration2 = containingDeclaration.getContainingDeclaration();
            if (containingDeclaration2 instanceof ClassDescriptor) {
                return ((ClassDescriptor) containingDeclaration2).getThisAsReceiverParameter();
            }
        }
        return null;
    }

    @NotNull
    public ClassDescriptor getContainingDeclaration() {
        return (ClassDescriptor) super.getContainingDeclaration();
    }

    @NotNull
    public ClassConstructorDescriptor getOriginal() {
        return (ClassConstructorDescriptor) super.getOriginal();
    }

    @Nullable
    public ClassConstructorDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        return (ClassConstructorDescriptor) super.substitute(typeSubstitutor);
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitConstructorDescriptor(this, d);
    }

    public boolean isPrimary() {
        return this.isPrimary;
    }

    @NotNull
    public Collection<? extends FunctionDescriptor> getOverriddenDescriptors() {
        return Collections.emptySet();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ClassConstructorDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement) {
        if (kind == Kind.DECLARATION || kind == Kind.SYNTHESIZED) {
            ClassConstructorDescriptorImpl classConstructorDescriptorImpl = new ClassConstructorDescriptorImpl((ClassDescriptor) declarationDescriptor, this, annotations, this.isPrimary, Kind.DECLARATION, sourceElement);
            return classConstructorDescriptorImpl;
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

    @NotNull
    public ClassConstructorDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality, Visibility visibility, Kind kind, boolean z) {
        return (ClassConstructorDescriptor) super.copy(declarationDescriptor, modality, visibility, kind, z);
    }
}
