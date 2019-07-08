package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: PackageFragmentDescriptorImpl.kt */
public abstract class PackageFragmentDescriptorImpl extends DeclarationDescriptorNonRootImpl implements PackageFragmentDescriptor {
    @NotNull
    private final FqName fqName;

    @NotNull
    public final FqName getFqName() {
        return this.fqName;
    }

    public PackageFragmentDescriptorImpl(@NotNull ModuleDescriptor moduleDescriptor, @NotNull FqName fqName2) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(fqName2, "fqName");
        super(moduleDescriptor, Annotations.Companion.getEMPTY(), fqName2.shortNameOrSpecial(), SourceElement.NO_SOURCE);
        this.fqName = fqName2;
    }

    public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptorVisitor, "visitor");
        return declarationDescriptorVisitor.visitPackageFragmentDescriptor(this, d);
    }

    @NotNull
    public ModuleDescriptor getContainingDeclaration() {
        DeclarationDescriptor containingDeclaration = super.getContainingDeclaration();
        if (containingDeclaration != null) {
            return (ModuleDescriptor) containingDeclaration;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ModuleDescriptor");
    }

    @NotNull
    public SourceElement getSource() {
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        return sourceElement;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("package ");
        sb.append(this.fqName);
        return sb.toString();
    }
}
