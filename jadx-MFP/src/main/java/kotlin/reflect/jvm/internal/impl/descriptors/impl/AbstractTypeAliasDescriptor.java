package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptorImpl.Companion;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractTypeAliasDescriptor.kt */
public abstract class AbstractTypeAliasDescriptor extends DeclarationDescriptorNonRootImpl implements TypeAliasDescriptor {
    private List<? extends TypeParameterDescriptor> declaredTypeParametersImpl;
    private final AbstractTypeAliasDescriptor$typeConstructor$1 typeConstructor = new AbstractTypeAliasDescriptor$typeConstructor$1(this);
    private final Visibility visibilityImpl;

    /* access modifiers changed from: protected */
    @NotNull
    public abstract StorageManager getStorageManager();

    /* access modifiers changed from: protected */
    @NotNull
    public abstract List<TypeParameterDescriptor> getTypeConstructorTypeParameters();

    public boolean isActual() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isExternal() {
        return false;
    }

    public AbstractTypeAliasDescriptor(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull Name name, @NotNull SourceElement sourceElement, @NotNull Visibility visibility) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(sourceElement, "sourceElement");
        Intrinsics.checkParameterIsNotNull(visibility, "visibilityImpl");
        super(declarationDescriptor, annotations, name, sourceElement);
        this.visibilityImpl = visibility;
    }

    public final void initialize(@NotNull List<? extends TypeParameterDescriptor> list) {
        Intrinsics.checkParameterIsNotNull(list, "declaredTypeParameters");
        this.declaredTypeParametersImpl = list;
    }

    public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptorVisitor, "visitor");
        return declarationDescriptorVisitor.visitTypeAliasDescriptor(this, d);
    }

    public boolean isInner() {
        return TypeUtils.contains(getUnderlyingType(), new AbstractTypeAliasDescriptor$isInner$1(this));
    }

    @NotNull
    public final Collection<TypeAliasConstructorDescriptor> getTypeAliasConstructors() {
        ClassDescriptor classDescriptor = getClassDescriptor();
        if (classDescriptor == null) {
            return CollectionsKt.emptyList();
        }
        Collection constructors = classDescriptor.getConstructors();
        Intrinsics.checkExpressionValueIsNotNull(constructors, "classDescriptor.constructors");
        Iterable<ClassConstructorDescriptor> iterable = constructors;
        Collection arrayList = new ArrayList();
        for (ClassConstructorDescriptor classConstructorDescriptor : iterable) {
            Companion companion = TypeAliasConstructorDescriptorImpl.Companion;
            StorageManager storageManager = getStorageManager();
            TypeAliasDescriptor typeAliasDescriptor = this;
            Intrinsics.checkExpressionValueIsNotNull(classConstructorDescriptor, "it");
            TypeAliasConstructorDescriptor createIfAvailable = companion.createIfAvailable(storageManager, typeAliasDescriptor, classConstructorDescriptor);
            if (createIfAvailable != null) {
                arrayList.add(createIfAvailable);
            }
        }
        return (List) arrayList;
    }

    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        List<? extends TypeParameterDescriptor> list = this.declaredTypeParametersImpl;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("declaredTypeParametersImpl");
        }
        return list;
    }

    @NotNull
    public Modality getModality() {
        return Modality.FINAL;
    }

    @NotNull
    public Visibility getVisibility() {
        return this.visibilityImpl;
    }

    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("typealias ");
        sb.append(getName().asString());
        return sb.toString();
    }

    @NotNull
    public TypeAliasDescriptor getOriginal() {
        DeclarationDescriptorWithSource original = super.getOriginal();
        if (original != null) {
            return (TypeAliasDescriptor) original;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeAliasDescriptor");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r1 != null) goto L_0x0014;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.types.SimpleType computeDefaultType() {
        /*
            r2 = this;
            r0 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor) r0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r1 = r2.getClassDescriptor()
            if (r1 == 0) goto L_0x0010
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r1 = r1.getUnsubstitutedMemberScope()
            if (r1 == 0) goto L_0x0010
            goto L_0x0014
        L_0x0010:
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope$Empty r1 = kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty.INSTANCE
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r1 = (kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope) r1
        L_0x0014:
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = kotlin.reflect.jvm.internal.impl.types.TypeUtils.makeUnsubstitutedType(r0, r1)
            java.lang.String r1 = "TypeUtils.makeUnsubstitu…ope ?: MemberScope.Empty)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor.computeDefaultType():kotlin.reflect.jvm.internal.impl.types.SimpleType");
    }
}
