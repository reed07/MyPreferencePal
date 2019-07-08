package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.SubstitutingScope;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LazySubstitutingClassDescriptor implements ClassDescriptor {
    private List<TypeParameterDescriptor> declaredTypeParameters;
    private TypeSubstitutor newSubstitutor;
    private final ClassDescriptor original;
    private final TypeSubstitutor originalSubstitutor;
    private TypeConstructor typeConstructor;
    private List<TypeParameterDescriptor> typeConstructorParameters;

    public LazySubstitutingClassDescriptor(ClassDescriptor classDescriptor, TypeSubstitutor typeSubstitutor) {
        this.original = classDescriptor;
        this.originalSubstitutor = typeSubstitutor;
    }

    private TypeSubstitutor getSubstitutor() {
        if (this.newSubstitutor == null) {
            if (this.originalSubstitutor.isEmpty()) {
                this.newSubstitutor = this.originalSubstitutor;
            } else {
                List parameters = this.original.getTypeConstructor().getParameters();
                this.typeConstructorParameters = new ArrayList(parameters.size());
                this.newSubstitutor = DescriptorSubstitutor.substituteTypeParameters(parameters, this.originalSubstitutor.getSubstitution(), this, this.typeConstructorParameters);
                this.declaredTypeParameters = CollectionsKt.filter(this.typeConstructorParameters, new Function1<TypeParameterDescriptor, Boolean>() {
                    public Boolean invoke(TypeParameterDescriptor typeParameterDescriptor) {
                        return Boolean.valueOf(!typeParameterDescriptor.isCapturedFromOuterDeclaration());
                    }
                });
            }
        }
        return this.newSubstitutor;
    }

    @NotNull
    public TypeConstructor getTypeConstructor() {
        TypeConstructor typeConstructor2 = this.original.getTypeConstructor();
        if (this.originalSubstitutor.isEmpty()) {
            return typeConstructor2;
        }
        if (this.typeConstructor == null) {
            TypeSubstitutor substitutor = getSubstitutor();
            Collection<KotlinType> supertypes = typeConstructor2.getSupertypes();
            ArrayList arrayList = new ArrayList(supertypes.size());
            for (KotlinType substitute : supertypes) {
                arrayList.add(substitutor.substitute(substitute, Variance.INVARIANT));
            }
            this.typeConstructor = new ClassTypeConstructorImpl(this, this.typeConstructorParameters, arrayList, LockBasedStorageManager.NO_LOCKS);
        }
        return this.typeConstructor;
    }

    @NotNull
    public MemberScope getMemberScope(@NotNull TypeSubstitution typeSubstitution) {
        MemberScope memberScope = this.original.getMemberScope(typeSubstitution);
        if (this.originalSubstitutor.isEmpty()) {
            return memberScope;
        }
        return new SubstitutingScope(memberScope, getSubstitutor());
    }

    @NotNull
    public MemberScope getUnsubstitutedMemberScope() {
        MemberScope unsubstitutedMemberScope = this.original.getUnsubstitutedMemberScope();
        if (this.originalSubstitutor.isEmpty()) {
            return unsubstitutedMemberScope;
        }
        return new SubstitutingScope(unsubstitutedMemberScope, getSubstitutor());
    }

    @NotNull
    public MemberScope getStaticScope() {
        return this.original.getStaticScope();
    }

    @NotNull
    public SimpleType getDefaultType() {
        return KotlinTypeFactory.simpleNotNullType(getAnnotations(), this, TypeUtils.getDefaultTypeProjections(getTypeConstructor().getParameters()));
    }

    @NotNull
    public ReceiverParameterDescriptor getThisAsReceiverParameter() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    public Collection<ClassConstructorDescriptor> getConstructors() {
        Collection<ClassConstructorDescriptor> constructors = this.original.getConstructors();
        ArrayList arrayList = new ArrayList(constructors.size());
        for (ClassConstructorDescriptor classConstructorDescriptor : constructors) {
            arrayList.add(classConstructorDescriptor.copy(this, classConstructorDescriptor.getModality(), classConstructorDescriptor.getVisibility(), classConstructorDescriptor.getKind(), false).substitute(getSubstitutor()));
        }
        return arrayList;
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.original.getAnnotations();
    }

    @NotNull
    public Name getName() {
        return this.original.getName();
    }

    @NotNull
    public ClassDescriptor getOriginal() {
        return this.original.getOriginal();
    }

    @NotNull
    public DeclarationDescriptor getContainingDeclaration() {
        return this.original.getContainingDeclaration();
    }

    @NotNull
    public ClassDescriptor substitute(@NotNull TypeSubstitutor typeSubstitutor) {
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        return new LazySubstitutingClassDescriptor(this, TypeSubstitutor.createChainedSubstitutor(typeSubstitutor.getSubstitution(), getSubstitutor().getSubstitution()));
    }

    public ClassDescriptor getCompanionObjectDescriptor() {
        return this.original.getCompanionObjectDescriptor();
    }

    @NotNull
    public ClassKind getKind() {
        return this.original.getKind();
    }

    @NotNull
    public Modality getModality() {
        return this.original.getModality();
    }

    @NotNull
    public Visibility getVisibility() {
        return this.original.getVisibility();
    }

    public boolean isInner() {
        return this.original.isInner();
    }

    public boolean isData() {
        return this.original.isData();
    }

    public boolean isInline() {
        return this.original.isInline();
    }

    public boolean isExternal() {
        return this.original.isExternal();
    }

    public boolean isCompanionObject() {
        return this.original.isCompanionObject();
    }

    public boolean isExpect() {
        return this.original.isExpect();
    }

    public boolean isActual() {
        return this.original.isActual();
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitClassDescriptor(this, d);
    }

    @NotNull
    public MemberScope getUnsubstitutedInnerClassesScope() {
        return this.original.getUnsubstitutedInnerClassesScope();
    }

    @Nullable
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return this.original.getUnsubstitutedPrimaryConstructor();
    }

    @NotNull
    public SourceElement getSource() {
        return SourceElement.NO_SOURCE;
    }

    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        getSubstitutor();
        return this.declaredTypeParameters;
    }

    @NotNull
    public Collection<ClassDescriptor> getSealedSubclasses() {
        return this.original.getSealedSubclasses();
    }
}
