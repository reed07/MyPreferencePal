package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.Collection;
import java.util.LinkedHashSet;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DescriptorUtils.kt */
public final class DescriptorUtilsKt {
    private static final Name RETENTION_PARAMETER_NAME;

    static {
        Name identifier = Name.identifier("value");
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"value\")");
        RETENTION_PARAMETER_NAME = identifier;
    }

    @NotNull
    public static final FqNameUnsafe getFqNameUnsafe(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        FqNameUnsafe fqName = DescriptorUtils.getFqName(declarationDescriptor);
        Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.getFqName(this)");
        return fqName;
    }

    @NotNull
    public static final FqName getFqNameSafe(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        FqName fqNameSafe = DescriptorUtils.getFqNameSafe(declarationDescriptor);
        Intrinsics.checkExpressionValueIsNotNull(fqNameSafe, "DescriptorUtils.getFqNameSafe(this)");
        return fqNameSafe;
    }

    @NotNull
    public static final ModuleDescriptor getModule(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        ModuleDescriptor containingModule = DescriptorUtils.getContainingModule(declarationDescriptor);
        Intrinsics.checkExpressionValueIsNotNull(containingModule, "DescriptorUtils.getContainingModule(this)");
        return containingModule;
    }

    @Nullable
    public static final ClassDescriptor resolveTopLevelClass(@NotNull ModuleDescriptor moduleDescriptor, @NotNull FqName fqName, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "receiver$0");
        Intrinsics.checkParameterIsNotNull(fqName, "topLevelClassFqName");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        boolean z = !fqName.isRoot();
        if (!_Assertions.ENABLED || z) {
            FqName parent = fqName.parent();
            Intrinsics.checkExpressionValueIsNotNull(parent, "topLevelClassFqName.parent()");
            MemberScope memberScope = moduleDescriptor.getPackage(parent).getMemberScope();
            Name shortName = fqName.shortName();
            Intrinsics.checkExpressionValueIsNotNull(shortName, "topLevelClassFqName.shortName()");
            ClassifierDescriptor contributedClassifier = memberScope.getContributedClassifier(shortName, lookupLocation);
            if (!(contributedClassifier instanceof ClassDescriptor)) {
                contributedClassifier = null;
            }
            return (ClassDescriptor) contributedClassifier;
        }
        throw new AssertionError("Assertion failed");
    }

    @Nullable
    public static final ClassId getClassId(@Nullable ClassifierDescriptor classifierDescriptor) {
        if (classifierDescriptor == null) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = classifierDescriptor.getContainingDeclaration();
        if (containingDeclaration == null) {
            return null;
        }
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            return new ClassId(((PackageFragmentDescriptor) containingDeclaration).getFqName(), classifierDescriptor.getName());
        }
        if (!(containingDeclaration instanceof ClassifierDescriptorWithTypeParameters)) {
            return null;
        }
        ClassId classId = getClassId((ClassifierDescriptor) containingDeclaration);
        if (classId != null) {
            return classId.createNestedClassId(classifierDescriptor.getName());
        }
        return null;
    }

    @Nullable
    public static final ClassDescriptor getSuperClassNotAny(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "receiver$0");
        for (KotlinType kotlinType : classDescriptor.getDefaultType().getConstructor().getSupertypes()) {
            if (!KotlinBuiltIns.isAnyOrNullableAny(kotlinType)) {
                ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
                if (DescriptorUtils.isClassOrEnumClass(declarationDescriptor)) {
                    if (declarationDescriptor != null) {
                        return (ClassDescriptor) declarationDescriptor;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
            }
        }
        return null;
    }

    @NotNull
    public static final KotlinBuiltIns getBuiltIns(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        return getModule(declarationDescriptor).getBuiltIns();
    }

    public static final boolean declaresOrInheritsDefaultValue(@NotNull ValueParameterDescriptor valueParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(valueParameterDescriptor, "receiver$0");
        Boolean ifAny = DFS.ifAny(CollectionsKt.listOf(valueParameterDescriptor), DescriptorUtilsKt$declaresOrInheritsDefaultValue$1.INSTANCE, DescriptorUtilsKt$declaresOrInheritsDefaultValue$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(ifAny, "DFS.ifAny(\n        listO…eclaresDefaultValue\n    )");
        return ifAny.booleanValue();
    }

    @NotNull
    public static final Sequence<DeclarationDescriptor> getParentsWithSelf(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        return SequencesKt.generateSequence((Object) declarationDescriptor, (Function1) DescriptorUtilsKt$parentsWithSelf$1.INSTANCE);
    }

    @NotNull
    public static final Sequence<DeclarationDescriptor> getParents(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        return SequencesKt.drop(getParentsWithSelf(declarationDescriptor), 1);
    }

    @NotNull
    public static final CallableMemberDescriptor getPropertyIfAccessor(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "receiver$0");
        if (!(callableMemberDescriptor instanceof PropertyAccessorDescriptor)) {
            return callableMemberDescriptor;
        }
        PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) callableMemberDescriptor).getCorrespondingProperty();
        Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "correspondingProperty");
        return correspondingProperty;
    }

    @Nullable
    public static final FqName fqNameOrNull(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        FqNameUnsafe fqNameUnsafe = getFqNameUnsafe(declarationDescriptor);
        if (!fqNameUnsafe.isSafe()) {
            fqNameUnsafe = null;
        }
        if (fqNameUnsafe != null) {
            return fqNameUnsafe.toSafe();
        }
        return null;
    }

    @Nullable
    public static /* synthetic */ CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return firstOverridden(callableMemberDescriptor, z, function1);
    }

    @Nullable
    public static final CallableMemberDescriptor firstOverridden(@NotNull CallableMemberDescriptor callableMemberDescriptor, boolean z, @NotNull Function1<? super CallableMemberDescriptor, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = (CallableMemberDescriptor) null;
        return (CallableMemberDescriptor) DFS.dfs(CollectionsKt.listOf(callableMemberDescriptor), new DescriptorUtilsKt$firstOverridden$1(z), new DescriptorUtilsKt$firstOverridden$2(objectRef, function1));
    }

    @NotNull
    public static final Collection<ClassDescriptor> computeSealedSubclasses(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "sealedClass");
        if (classDescriptor.getModality() != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        DescriptorUtilsKt$computeSealedSubclasses$1 descriptorUtilsKt$computeSealedSubclasses$1 = new DescriptorUtilsKt$computeSealedSubclasses$1(classDescriptor, linkedHashSet);
        DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "sealedClass.containingDeclaration");
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            descriptorUtilsKt$computeSealedSubclasses$1.invoke(((PackageFragmentDescriptor) containingDeclaration).getMemberScope(), false);
        }
        MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
        Intrinsics.checkExpressionValueIsNotNull(unsubstitutedInnerClassesScope, "sealedClass.unsubstitutedInnerClassesScope");
        descriptorUtilsKt$computeSealedSubclasses$1.invoke(unsubstitutedInnerClassesScope, true);
        return linkedHashSet;
    }

    @Nullable
    public static final ClassDescriptor getAnnotationClass(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "receiver$0");
        ClassifierDescriptor declarationDescriptor = annotationDescriptor.getType().getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        return (ClassDescriptor) declarationDescriptor;
    }

    @Nullable
    public static final ConstantValue<?> firstArgument(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "receiver$0");
        return (ConstantValue) CollectionsKt.firstOrNull((Iterable<? extends T>) annotationDescriptor.getAllValueArguments().values());
    }
}
