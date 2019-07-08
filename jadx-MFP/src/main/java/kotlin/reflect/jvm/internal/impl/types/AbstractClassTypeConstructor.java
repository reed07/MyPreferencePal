package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.Collections;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractClassTypeConstructor extends AbstractTypeConstructor implements TypeConstructor {
    private int hashCode = 0;

    @NotNull
    public abstract ClassDescriptor getDeclarationDescriptor();

    public AbstractClassTypeConstructor(@NotNull StorageManager storageManager) {
        super(storageManager);
    }

    public final int hashCode() {
        int i;
        int i2 = this.hashCode;
        if (i2 != 0) {
            return i2;
        }
        ClassDescriptor declarationDescriptor = getDeclarationDescriptor();
        if (hasMeaningfulFqName(declarationDescriptor)) {
            i = DescriptorUtils.getFqName(declarationDescriptor).hashCode();
        } else {
            i = System.identityHashCode(this);
        }
        this.hashCode = i;
        return i;
    }

    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        return DescriptorUtilsKt.getBuiltIns(getDeclarationDescriptor());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeConstructor) || obj.hashCode() != hashCode()) {
            return false;
        }
        TypeConstructor typeConstructor = (TypeConstructor) obj;
        if (typeConstructor.getParameters().size() != getParameters().size()) {
            return false;
        }
        ClassDescriptor declarationDescriptor = getDeclarationDescriptor();
        ClassifierDescriptor declarationDescriptor2 = typeConstructor.getDeclarationDescriptor();
        if (!hasMeaningfulFqName(declarationDescriptor) || ((declarationDescriptor2 != null && !hasMeaningfulFqName(declarationDescriptor2)) || !(declarationDescriptor2 instanceof ClassDescriptor))) {
            return false;
        }
        return areFqNamesEqual(declarationDescriptor, (ClassDescriptor) declarationDescriptor2);
    }

    private static boolean areFqNamesEqual(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        if (!classDescriptor.getName().equals(classDescriptor2.getName())) {
            return false;
        }
        DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
        DeclarationDescriptor containingDeclaration2 = classDescriptor2.getContainingDeclaration();
        while (true) {
            boolean z = true;
            if (containingDeclaration == null || containingDeclaration2 == null) {
                return true;
            }
            if (containingDeclaration instanceof ModuleDescriptor) {
                return containingDeclaration2 instanceof ModuleDescriptor;
            }
            if (containingDeclaration2 instanceof ModuleDescriptor) {
                return false;
            }
            if (containingDeclaration instanceof PackageFragmentDescriptor) {
                if (!(containingDeclaration2 instanceof PackageFragmentDescriptor) || !((PackageFragmentDescriptor) containingDeclaration).getFqName().equals(((PackageFragmentDescriptor) containingDeclaration2).getFqName())) {
                    z = false;
                }
                return z;
            } else if ((containingDeclaration2 instanceof PackageFragmentDescriptor) || !containingDeclaration.getName().equals(containingDeclaration2.getName())) {
                return false;
            } else {
                containingDeclaration = containingDeclaration.getContainingDeclaration();
                containingDeclaration2 = containingDeclaration2.getContainingDeclaration();
            }
        }
        return true;
    }

    private static boolean hasMeaningfulFqName(@NotNull ClassifierDescriptor classifierDescriptor) {
        return !ErrorUtils.isError(classifierDescriptor) && !DescriptorUtils.isLocal(classifierDescriptor);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean z) {
        DeclarationDescriptor containingDeclaration = getDeclarationDescriptor().getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            return Collections.emptyList();
        }
        SmartList smartList = new SmartList();
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        smartList.add(classDescriptor.getDefaultType());
        ClassDescriptor companionObjectDescriptor = classDescriptor.getCompanionObjectDescriptor();
        if (z && companionObjectDescriptor != null) {
            smartList.add(companionObjectDescriptor.getDefaultType());
        }
        return smartList;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public KotlinType defaultSupertypeIfEmpty() {
        if (KotlinBuiltIns.isSpecialClassWithNoSupertypes(getDeclarationDescriptor())) {
            return null;
        }
        return getBuiltIns().getAnyType();
    }
}
