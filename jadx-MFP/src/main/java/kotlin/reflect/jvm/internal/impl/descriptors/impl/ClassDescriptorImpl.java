package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClassDescriptorImpl extends ClassDescriptorBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Set<ClassConstructorDescriptor> constructors;
    private final ClassKind kind;
    private final Modality modality;
    private ClassConstructorDescriptor primaryConstructor;
    private final TypeConstructor typeConstructor;
    private MemberScope unsubstitutedMemberScope;

    @Nullable
    public ClassDescriptor getCompanionObjectDescriptor() {
        return null;
    }

    public boolean isActual() {
        return false;
    }

    public boolean isCompanionObject() {
        return false;
    }

    public boolean isData() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isInner() {
        return false;
    }

    public ClassDescriptorImpl(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Name name, @NotNull Modality modality2, @NotNull ClassKind classKind, @NotNull Collection<KotlinType> collection, @NotNull SourceElement sourceElement, boolean z, @NotNull StorageManager storageManager) {
        super(storageManager, declarationDescriptor, name, sourceElement, z);
        this.modality = modality2;
        this.kind = classKind;
        this.typeConstructor = new ClassTypeConstructorImpl(this, Collections.emptyList(), collection, storageManager);
    }

    public final void initialize(@NotNull MemberScope memberScope, @NotNull Set<ClassConstructorDescriptor> set, @Nullable ClassConstructorDescriptor classConstructorDescriptor) {
        this.unsubstitutedMemberScope = memberScope;
        this.constructors = set;
        this.primaryConstructor = classConstructorDescriptor;
    }

    @NotNull
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @NotNull
    public Collection<ClassConstructorDescriptor> getConstructors() {
        return this.constructors;
    }

    @NotNull
    public MemberScope getUnsubstitutedMemberScope() {
        return this.unsubstitutedMemberScope;
    }

    @NotNull
    public MemberScope getStaticScope() {
        return Empty.INSTANCE;
    }

    @NotNull
    public ClassKind getKind() {
        return this.kind;
    }

    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return this.primaryConstructor;
    }

    @NotNull
    public Modality getModality() {
        return this.modality;
    }

    @NotNull
    public Visibility getVisibility() {
        return Visibilities.PUBLIC;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ");
        sb.append(getName());
        return sb.toString();
    }

    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return Collections.emptyList();
    }

    @NotNull
    public Collection<ClassDescriptor> getSealedSubclasses() {
        return Collections.emptyList();
    }
}
