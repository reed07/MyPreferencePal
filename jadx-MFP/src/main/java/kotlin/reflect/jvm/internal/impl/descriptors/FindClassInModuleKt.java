package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: findClassInModule.kt */
public final class FindClassInModuleKt {
    @Nullable
    public static final ClassDescriptor findClassAcrossModuleDependencies(@NotNull ModuleDescriptor moduleDescriptor, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "receiver$0");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor = moduleDescriptor.getPackage(packageFqName);
        List pathSegments = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "classId.relativeClassName.pathSegments()");
        MemberScope memberScope = packageViewDescriptor.getMemberScope();
        Object first = CollectionsKt.first(pathSegments);
        Intrinsics.checkExpressionValueIsNotNull(first, "segments.first()");
        ClassifierDescriptor contributedClassifier = memberScope.getContributedClassifier((Name) first, NoLookupLocation.FROM_DESERIALIZATION);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor == null) {
            return null;
        }
        for (Name name : pathSegments.subList(1, pathSegments.size())) {
            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
            Intrinsics.checkExpressionValueIsNotNull(name, "name");
            ClassifierDescriptor contributedClassifier2 = unsubstitutedInnerClassesScope.getContributedClassifier(name, NoLookupLocation.FROM_DESERIALIZATION);
            if (!(contributedClassifier2 instanceof ClassDescriptor)) {
                contributedClassifier2 = null;
            }
            classDescriptor = (ClassDescriptor) contributedClassifier2;
            if (classDescriptor == null) {
                return null;
            }
        }
        return classDescriptor;
    }

    @NotNull
    public static final ClassDescriptor findNonGenericClassAcrossDependencies(@NotNull ModuleDescriptor moduleDescriptor, @NotNull ClassId classId, @NotNull NotFoundClasses notFoundClasses) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "receiver$0");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        ClassDescriptor findClassAcrossModuleDependencies = findClassAcrossModuleDependencies(moduleDescriptor, classId);
        if (findClassAcrossModuleDependencies != null) {
            return findClassAcrossModuleDependencies;
        }
        return notFoundClasses.getClass(classId, SequencesKt.toList(SequencesKt.map(SequencesKt.generateSequence((Object) classId, (Function1) FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$1.INSTANCE), FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2.INSTANCE)));
    }

    @Nullable
    public static final TypeAliasDescriptor findTypeAliasAcrossModuleDependencies(@NotNull ModuleDescriptor moduleDescriptor, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "receiver$0");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor = moduleDescriptor.getPackage(packageFqName);
        List pathSegments = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "classId.relativeClassName.pathSegments()");
        int size = pathSegments.size() - 1;
        MemberScope memberScope = packageViewDescriptor.getMemberScope();
        Object first = CollectionsKt.first(pathSegments);
        Intrinsics.checkExpressionValueIsNotNull(first, "segments.first()");
        ClassifierDescriptor contributedClassifier = memberScope.getContributedClassifier((Name) first, NoLookupLocation.FROM_DESERIALIZATION);
        if (size == 0) {
            if (!(contributedClassifier instanceof TypeAliasDescriptor)) {
                contributedClassifier = null;
            }
            return (TypeAliasDescriptor) contributedClassifier;
        }
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor == null) {
            return null;
        }
        for (Name name : pathSegments.subList(1, size)) {
            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
            Intrinsics.checkExpressionValueIsNotNull(name, "name");
            ClassifierDescriptor contributedClassifier2 = unsubstitutedInnerClassesScope.getContributedClassifier(name, NoLookupLocation.FROM_DESERIALIZATION);
            if (!(contributedClassifier2 instanceof ClassDescriptor)) {
                contributedClassifier2 = null;
            }
            classDescriptor = (ClassDescriptor) contributedClassifier2;
            if (classDescriptor == null) {
                return null;
            }
        }
        Name name2 = (Name) pathSegments.get(size);
        MemberScope unsubstitutedMemberScope = classDescriptor.getUnsubstitutedMemberScope();
        Intrinsics.checkExpressionValueIsNotNull(name2, "lastName");
        ClassifierDescriptor contributedClassifier3 = unsubstitutedMemberScope.getContributedClassifier(name2, NoLookupLocation.FROM_DESERIALIZATION);
        if (!(contributedClassifier3 instanceof TypeAliasDescriptor)) {
            contributedClassifier3 = null;
        }
        return (TypeAliasDescriptor) contributedClassifier3;
    }
}
