package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaDescriptorResolver.kt */
public final class JavaDescriptorResolver {
    private final JavaResolverCache javaResolverCache;
    @NotNull
    private final LazyJavaPackageFragmentProvider packageFragmentProvider;

    public JavaDescriptorResolver(@NotNull LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider, @NotNull JavaResolverCache javaResolverCache2) {
        Intrinsics.checkParameterIsNotNull(lazyJavaPackageFragmentProvider, "packageFragmentProvider");
        Intrinsics.checkParameterIsNotNull(javaResolverCache2, "javaResolverCache");
        this.packageFragmentProvider = lazyJavaPackageFragmentProvider;
        this.javaResolverCache = javaResolverCache2;
    }

    @NotNull
    public final LazyJavaPackageFragmentProvider getPackageFragmentProvider() {
        return this.packageFragmentProvider;
    }

    @Nullable
    public final ClassDescriptor resolveClass(@NotNull JavaClass javaClass) {
        Intrinsics.checkParameterIsNotNull(javaClass, "javaClass");
        FqName fqName = javaClass.getFqName();
        if (fqName != null && javaClass.getLightClassOriginKind() == LightClassOriginKind.SOURCE) {
            return this.javaResolverCache.getClassResolvedFromSource(fqName);
        }
        JavaClass outerClass = javaClass.getOuterClass();
        ClassDescriptor classDescriptor = null;
        if (outerClass != null) {
            ClassDescriptor resolveClass = resolveClass(outerClass);
            MemberScope unsubstitutedInnerClassesScope = resolveClass != null ? resolveClass.getUnsubstitutedInnerClassesScope() : null;
            ClassifierDescriptor contributedClassifier = unsubstitutedInnerClassesScope != null ? unsubstitutedInnerClassesScope.getContributedClassifier(javaClass.getName(), NoLookupLocation.FROM_JAVA_LOADER) : null;
            if (!(contributedClassifier instanceof ClassDescriptor)) {
                contributedClassifier = null;
            }
            return (ClassDescriptor) contributedClassifier;
        } else if (fqName == null) {
            return null;
        } else {
            LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider = this.packageFragmentProvider;
            FqName parent = fqName.parent();
            Intrinsics.checkExpressionValueIsNotNull(parent, "fqName.parent()");
            LazyJavaPackageFragment lazyJavaPackageFragment = (LazyJavaPackageFragment) CollectionsKt.firstOrNull(lazyJavaPackageFragmentProvider.getPackageFragments(parent));
            if (lazyJavaPackageFragment != null) {
                classDescriptor = lazyJavaPackageFragment.findClassifierByJavaClass$descriptors_jvm(javaClass);
            }
            return classDescriptor;
        }
    }
}
