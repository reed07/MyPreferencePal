package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaVisibilities {
    @NotNull
    public static final Visibility PACKAGE_VISIBILITY = new Visibility("package", false) {
        @NotNull
        public String getDisplayName() {
            return "public/*package*/";
        }

        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            return JavaVisibilities.areInSamePackage(declarationDescriptorWithVisibility, declarationDescriptor);
        }

        /* access modifiers changed from: protected */
        public Integer compareTo(@NotNull Visibility visibility) {
            if (this == visibility) {
                return Integer.valueOf(0);
            }
            if (Visibilities.isPrivate(visibility)) {
                return Integer.valueOf(1);
            }
            return Integer.valueOf(-1);
        }

        @NotNull
        public Visibility normalize() {
            return Visibilities.PROTECTED;
        }
    };
    @NotNull
    public static final Visibility PROTECTED_AND_PACKAGE = new Visibility("protected_and_package", true) {
        @NotNull
        public String getDisplayName() {
            return "protected/*protected and package*/";
        }

        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            return JavaVisibilities.isVisibleForProtectedAndPackage(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
        }

        /* access modifiers changed from: protected */
        public Integer compareTo(@NotNull Visibility visibility) {
            if (this == visibility) {
                return Integer.valueOf(0);
            }
            if (visibility == Visibilities.INTERNAL) {
                return null;
            }
            if (Visibilities.isPrivate(visibility)) {
                return Integer.valueOf(1);
            }
            return Integer.valueOf(-1);
        }

        @NotNull
        public Visibility normalize() {
            return Visibilities.PROTECTED;
        }
    };
    @NotNull
    public static final Visibility PROTECTED_STATIC_VISIBILITY = new Visibility("protected_static", true) {
        @NotNull
        public String getDisplayName() {
            return "protected/*protected static*/";
        }

        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            return JavaVisibilities.isVisibleForProtectedAndPackage(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
        }

        @NotNull
        public Visibility normalize() {
            return Visibilities.PROTECTED;
        }
    };

    /* access modifiers changed from: private */
    public static boolean isVisibleForProtectedAndPackage(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
        if (areInSamePackage(DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility), declarationDescriptor)) {
            return true;
        }
        return Visibilities.PROTECTED.isVisible(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
    }

    /* access modifiers changed from: private */
    public static boolean areInSamePackage(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull DeclarationDescriptor declarationDescriptor2) {
        PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor, PackageFragmentDescriptor.class, false);
        PackageFragmentDescriptor packageFragmentDescriptor2 = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor2, PackageFragmentDescriptor.class, false);
        if (packageFragmentDescriptor2 == null || packageFragmentDescriptor == null || !packageFragmentDescriptor.getFqName().equals(packageFragmentDescriptor2.getFqName())) {
            return false;
        }
        return true;
    }
}
