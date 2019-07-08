package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DescriptorUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final FqName CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL = COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL.child(Name.identifier("Continuation"));
    public static final FqName CONTINUATION_INTERFACE_FQ_NAME_RELEASE = COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier("Continuation"));
    public static final FqName COROUTINES_INTRINSICS_PACKAGE_FQ_NAME_EXPERIMENTAL = COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL.child(Name.identifier("intrinsics"));
    public static final FqName COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL = COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier("experimental"));
    public static final FqName COROUTINES_PACKAGE_FQ_NAME_RELEASE = new FqName("kotlin.coroutines");
    public static final Name ENUM_VALUES = Name.identifier("values");
    public static final Name ENUM_VALUE_OF = Name.identifier("valueOf");
    public static final FqName JVM_NAME = new FqName("kotlin.jvm.JvmName");
    public static final FqName RESULT_FQ_NAME = new FqName("kotlin.Result");

    private DescriptorUtils() {
    }

    @Nullable
    public static ReceiverParameterDescriptor getDispatchReceiverParameterIfNeeded(@NotNull DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor instanceof ClassDescriptor) {
            return ((ClassDescriptor) declarationDescriptor).getThisAsReceiverParameter();
        }
        return null;
    }

    public static boolean isLocal(@NotNull DeclarationDescriptor declarationDescriptor) {
        while (declarationDescriptor != null) {
            if (isAnonymousObject(declarationDescriptor) || isDescriptorWithLocalVisibility(declarationDescriptor)) {
                return true;
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return false;
    }

    public static boolean isDescriptorWithLocalVisibility(DeclarationDescriptor declarationDescriptor) {
        return (declarationDescriptor instanceof DeclarationDescriptorWithVisibility) && ((DeclarationDescriptorWithVisibility) declarationDescriptor).getVisibility() == Visibilities.LOCAL;
    }

    @NotNull
    public static FqNameUnsafe getFqName(@NotNull DeclarationDescriptor declarationDescriptor) {
        FqName fqNameSafeIfPossible = getFqNameSafeIfPossible(declarationDescriptor);
        return fqNameSafeIfPossible != null ? fqNameSafeIfPossible.toUnsafe() : getFqNameUnsafe(declarationDescriptor);
    }

    @NotNull
    public static FqName getFqNameSafe(@NotNull DeclarationDescriptor declarationDescriptor) {
        FqName fqNameSafeIfPossible = getFqNameSafeIfPossible(declarationDescriptor);
        return fqNameSafeIfPossible != null ? fqNameSafeIfPossible : getFqNameUnsafe(declarationDescriptor).toSafe();
    }

    @Nullable
    private static FqName getFqNameSafeIfPossible(@NotNull DeclarationDescriptor declarationDescriptor) {
        if ((declarationDescriptor instanceof ModuleDescriptor) || ErrorUtils.isError(declarationDescriptor)) {
            return FqName.ROOT;
        }
        if (declarationDescriptor instanceof PackageViewDescriptor) {
            return ((PackageViewDescriptor) declarationDescriptor).getFqName();
        }
        if (declarationDescriptor instanceof PackageFragmentDescriptor) {
            return ((PackageFragmentDescriptor) declarationDescriptor).getFqName();
        }
        return null;
    }

    @NotNull
    private static FqNameUnsafe getFqNameUnsafe(@NotNull DeclarationDescriptor declarationDescriptor) {
        return getFqName(declarationDescriptor.getContainingDeclaration()).child(declarationDescriptor.getName());
    }

    public static boolean isTopLevelDeclaration(@Nullable DeclarationDescriptor declarationDescriptor) {
        return declarationDescriptor != null && (declarationDescriptor.getContainingDeclaration() instanceof PackageFragmentDescriptor);
    }

    public static boolean areInSameModule(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull DeclarationDescriptor declarationDescriptor2) {
        return getContainingModule(declarationDescriptor).equals(getContainingModule(declarationDescriptor2));
    }

    @Nullable
    public static <D extends DeclarationDescriptor> D getParentOfType(@Nullable DeclarationDescriptor declarationDescriptor, @NotNull Class<D> cls) {
        return getParentOfType(declarationDescriptor, cls, true);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, code=D, for r1v0, types: [kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <D extends kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor> D getParentOfType(@org.jetbrains.annotations.Nullable D r1, @org.jetbrains.annotations.NotNull java.lang.Class<D> r2, boolean r3) {
        /*
            r0 = 0
            if (r1 != 0) goto L_0x0004
            return r0
        L_0x0004:
            if (r3 == 0) goto L_0x000a
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r1.getContainingDeclaration()
        L_0x000a:
            if (r1 == 0) goto L_0x0018
            boolean r3 = r2.isInstance(r1)
            if (r3 == 0) goto L_0x0013
            return r1
        L_0x0013:
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r1.getContainingDeclaration()
            goto L_0x000a
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.getParentOfType(kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, java.lang.Class, boolean):kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor");
    }

    @Nullable
    public static ModuleDescriptor getContainingModuleOrNull(@NotNull KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            return null;
        }
        return getContainingModuleOrNull((DeclarationDescriptor) declarationDescriptor);
    }

    @NotNull
    public static ModuleDescriptor getContainingModule(@NotNull DeclarationDescriptor declarationDescriptor) {
        return getContainingModuleOrNull(declarationDescriptor);
    }

    @Nullable
    public static ModuleDescriptor getContainingModuleOrNull(@NotNull DeclarationDescriptor declarationDescriptor) {
        while (declarationDescriptor != null) {
            if (declarationDescriptor instanceof ModuleDescriptor) {
                return (ModuleDescriptor) declarationDescriptor;
            }
            if (declarationDescriptor instanceof PackageViewDescriptor) {
                return ((PackageViewDescriptor) declarationDescriptor).getModule();
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return null;
    }

    public static boolean isDirectSubclass(@NotNull ClassDescriptor classDescriptor, @NotNull ClassDescriptor classDescriptor2) {
        for (KotlinType isSameClass : classDescriptor.getTypeConstructor().getSupertypes()) {
            if (isSameClass(isSameClass, classDescriptor2.getOriginal())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSubclass(@NotNull ClassDescriptor classDescriptor, @NotNull ClassDescriptor classDescriptor2) {
        return isSubtypeOfClass(classDescriptor.getDefaultType(), classDescriptor2.getOriginal());
    }

    private static boolean isSameClass(@NotNull KotlinType kotlinType, @NotNull DeclarationDescriptor declarationDescriptor) {
        ClassifierDescriptor declarationDescriptor2 = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor2 != null) {
            DeclarationDescriptor original = declarationDescriptor2.getOriginal();
            if ((original instanceof ClassifierDescriptor) && (declarationDescriptor instanceof ClassifierDescriptor) && ((ClassifierDescriptor) declarationDescriptor).getTypeConstructor().equals(((ClassifierDescriptor) original).getTypeConstructor())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSubtypeOfClass(@NotNull KotlinType kotlinType, @NotNull DeclarationDescriptor declarationDescriptor) {
        if (isSameClass(kotlinType, declarationDescriptor)) {
            return true;
        }
        for (KotlinType isSubtypeOfClass : kotlinType.getConstructor().getSupertypes()) {
            if (isSubtypeOfClass(isSubtypeOfClass, declarationDescriptor)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCompanionObject(@Nullable DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.OBJECT) && ((ClassDescriptor) declarationDescriptor).isCompanionObject();
    }

    public static boolean isSealedClass(@Nullable DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.CLASS) && ((ClassDescriptor) declarationDescriptor).getModality() == Modality.SEALED;
    }

    public static boolean isAnonymousObject(@NotNull DeclarationDescriptor declarationDescriptor) {
        return isClass(declarationDescriptor) && declarationDescriptor.getName().equals(SpecialNames.NO_NAME_PROVIDED);
    }

    public static boolean isEnumEntry(@NotNull DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.ENUM_ENTRY);
    }

    public static boolean isEnumClass(@Nullable DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.ENUM_CLASS);
    }

    public static boolean isAnnotationClass(@Nullable DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.ANNOTATION_CLASS);
    }

    public static boolean isInterface(@Nullable DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.INTERFACE);
    }

    public static boolean isClass(@Nullable DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.CLASS);
    }

    public static boolean isClassOrEnumClass(@Nullable DeclarationDescriptor declarationDescriptor) {
        return isClass(declarationDescriptor) || isEnumClass(declarationDescriptor);
    }

    private static boolean isKindOf(@Nullable DeclarationDescriptor declarationDescriptor, @NotNull ClassKind classKind) {
        return (declarationDescriptor instanceof ClassDescriptor) && ((ClassDescriptor) declarationDescriptor).getKind() == classKind;
    }

    @Nullable
    public static ClassDescriptor getSuperClassDescriptor(@NotNull ClassDescriptor classDescriptor) {
        for (KotlinType classDescriptorForType : classDescriptor.getTypeConstructor().getSupertypes()) {
            ClassDescriptor classDescriptorForType2 = getClassDescriptorForType(classDescriptorForType);
            if (classDescriptorForType2.getKind() != ClassKind.INTERFACE) {
                return classDescriptorForType2;
            }
        }
        return null;
    }

    @NotNull
    public static ClassDescriptor getClassDescriptorForType(@NotNull KotlinType kotlinType) {
        return getClassDescriptorForTypeConstructor(kotlinType.getConstructor());
    }

    @NotNull
    public static ClassDescriptor getClassDescriptorForTypeConstructor(@NotNull TypeConstructor typeConstructor) {
        return (ClassDescriptor) typeConstructor.getDeclarationDescriptor();
    }

    @NotNull
    public static Visibility getDefaultConstructorVisibility(@NotNull ClassDescriptor classDescriptor) {
        ClassKind kind = classDescriptor.getKind();
        if (kind == ClassKind.ENUM_CLASS || kind.isSingleton() || isSealedClass(classDescriptor)) {
            return Visibilities.PRIVATE;
        }
        if (isAnonymousObject(classDescriptor)) {
            return Visibilities.DEFAULT_VISIBILITY;
        }
        return Visibilities.PUBLIC;
    }

    @NotNull
    public static <D extends CallableMemberDescriptor> D unwrapFakeOverride(@NotNull D d) {
        while (d.getKind() == Kind.FAKE_OVERRIDE) {
            Collection overriddenDescriptors = d.getOverriddenDescriptors();
            if (!overriddenDescriptors.isEmpty()) {
                d = (CallableMemberDescriptor) overriddenDescriptors.iterator().next();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Fake override should have at least one overridden descriptor: ");
                sb.append(d);
                throw new IllegalStateException(sb.toString());
            }
        }
        return d;
    }

    @NotNull
    public static <D extends DeclarationDescriptorWithVisibility> D unwrapFakeOverrideToAnyDeclaration(@NotNull D d) {
        return d instanceof CallableMemberDescriptor ? unwrapFakeOverride((CallableMemberDescriptor) d) : d;
    }

    public static boolean shouldRecordInitializerForProperty(@NotNull VariableDescriptor variableDescriptor, @NotNull KotlinType kotlinType) {
        boolean z = false;
        if (variableDescriptor.isVar() || KotlinTypeKt.isError(kotlinType)) {
            return false;
        }
        if (TypeUtils.acceptsNullable(kotlinType)) {
            return true;
        }
        KotlinBuiltIns builtIns = DescriptorUtilsKt.getBuiltIns(variableDescriptor);
        if (KotlinBuiltIns.isPrimitiveType(kotlinType) || KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getStringType(), kotlinType) || KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getNumber().getDefaultType(), kotlinType) || KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getAnyType(), kotlinType) || UnsignedTypes.INSTANCE.isUnsignedType(kotlinType)) {
            z = true;
        }
        return z;
    }

    @NotNull
    public static <D extends CallableDescriptor> Set<D> getAllOverriddenDescriptors(@NotNull D d) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectAllOverriddenDescriptors(d.getOriginal(), linkedHashSet);
        return linkedHashSet;
    }

    private static <D extends CallableDescriptor> void collectAllOverriddenDescriptors(@NotNull D d, @NotNull Set<D> set) {
        if (!set.contains(d)) {
            for (CallableDescriptor original : d.getOriginal().getOverriddenDescriptors()) {
                CallableDescriptor original2 = original.getOriginal();
                collectAllOverriddenDescriptors(original2, set);
                set.add(original2);
            }
        }
    }

    @NotNull
    public static SourceFile getContainingSourceFile(@NotNull DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor instanceof PropertySetterDescriptor) {
            declarationDescriptor = ((PropertySetterDescriptor) declarationDescriptor).getCorrespondingProperty();
        }
        if (declarationDescriptor instanceof DeclarationDescriptorWithSource) {
            return ((DeclarationDescriptorWithSource) declarationDescriptor).getSource().getContainingFile();
        }
        return SourceFile.NO_SOURCE_FILE;
    }

    @NotNull
    public static CallableMemberDescriptor getDirectMember(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        return callableMemberDescriptor instanceof PropertyAccessorDescriptor ? ((PropertyAccessorDescriptor) callableMemberDescriptor).getCorrespondingProperty() : callableMemberDescriptor;
    }
}
