package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex.Empty;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaPackageScope.kt */
public final class LazyJavaPackageScope extends LazyJavaStaticScope {
    private final MemoizedFunctionToNullable<FindClassRequest, ClassDescriptor> classes;
    private final JavaPackage jPackage;
    private final NullableLazyValue<Set<String>> knownClassNamesInPackage;
    @NotNull
    private final LazyJavaPackageFragment ownerDescriptor;

    /* compiled from: LazyJavaPackageScope.kt */
    private static final class FindClassRequest {
        @Nullable
        private final JavaClass javaClass;
        @NotNull
        private final Name name;

        public FindClassRequest(@NotNull Name name2, @Nullable JavaClass javaClass2) {
            Intrinsics.checkParameterIsNotNull(name2, "name");
            this.name = name2;
            this.javaClass = javaClass2;
        }

        @Nullable
        public final JavaClass getJavaClass() {
            return this.javaClass;
        }

        @NotNull
        public final Name getName() {
            return this.name;
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof FindClassRequest) && Intrinsics.areEqual((Object) this.name, (Object) ((FindClassRequest) obj).name);
        }

        public int hashCode() {
            return this.name.hashCode();
        }
    }

    /* compiled from: LazyJavaPackageScope.kt */
    private static abstract class KotlinClassLookupResult {

        /* compiled from: LazyJavaPackageScope.kt */
        public static final class Found extends KotlinClassLookupResult {
            @NotNull
            private final ClassDescriptor descriptor;

            public Found(@NotNull ClassDescriptor classDescriptor) {
                Intrinsics.checkParameterIsNotNull(classDescriptor, "descriptor");
                super(null);
                this.descriptor = classDescriptor;
            }

            @NotNull
            public final ClassDescriptor getDescriptor() {
                return this.descriptor;
            }
        }

        /* compiled from: LazyJavaPackageScope.kt */
        public static final class NotFound extends KotlinClassLookupResult {
            public static final NotFound INSTANCE = new NotFound();

            private NotFound() {
                super(null);
            }
        }

        /* compiled from: LazyJavaPackageScope.kt */
        public static final class SyntheticClass extends KotlinClassLookupResult {
            public static final SyntheticClass INSTANCE = new SyntheticClass();

            private SyntheticClass() {
                super(null);
            }
        }

        private KotlinClassLookupResult() {
        }

        public /* synthetic */ KotlinClassLookupResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* access modifiers changed from: protected */
    public void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> collection, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(collection, "result");
        Intrinsics.checkParameterIsNotNull(name, "name");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public LazyJavaPackageFragment getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    public LazyJavaPackageScope(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull JavaPackage javaPackage, @NotNull LazyJavaPackageFragment lazyJavaPackageFragment) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        Intrinsics.checkParameterIsNotNull(javaPackage, "jPackage");
        Intrinsics.checkParameterIsNotNull(lazyJavaPackageFragment, "ownerDescriptor");
        super(lazyJavaResolverContext);
        this.jPackage = javaPackage;
        this.ownerDescriptor = lazyJavaPackageFragment;
        this.knownClassNamesInPackage = lazyJavaResolverContext.getStorageManager().createNullableLazyValue(new LazyJavaPackageScope$knownClassNamesInPackage$1(this, lazyJavaResolverContext));
        this.classes = lazyJavaResolverContext.getStorageManager().createMemoizedFunctionWithNullableValues(new LazyJavaPackageScope$classes$1(this, lazyJavaResolverContext));
    }

    /* access modifiers changed from: private */
    public final KotlinClassLookupResult resolveKotlinBinaryClass(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (kotlinJvmBinaryClass == null) {
            return NotFound.INSTANCE;
        }
        if (kotlinJvmBinaryClass.getClassHeader().getKind() != Kind.CLASS) {
            return SyntheticClass.INSTANCE;
        }
        ClassDescriptor resolveClass = getC().getComponents().getDeserializedDescriptorResolver().resolveClass(kotlinJvmBinaryClass);
        return resolveClass != null ? new Found(resolveClass) : NotFound.INSTANCE;
    }

    @Nullable
    public ClassDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return findClassifier(name, null);
    }

    private final ClassDescriptor findClassifier(Name name, JavaClass javaClass) {
        if (!SpecialNames.isSafeIdentifier(name)) {
            return null;
        }
        Set set = (Set) this.knownClassNamesInPackage.invoke();
        if (javaClass != null || set == null || set.contains(name.asString())) {
            return (ClassDescriptor) this.classes.invoke(new FindClassRequest(name, javaClass));
        }
        return null;
    }

    @Nullable
    public final ClassDescriptor findClassifierByJavaClass$descriptors_jvm(@NotNull JavaClass javaClass) {
        Intrinsics.checkParameterIsNotNull(javaClass, "javaClass");
        return findClassifier(javaClass.getName(), javaClass);
    }

    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return CollectionsKt.emptyList();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public DeclaredMemberIndex computeMemberIndex() {
        return Empty.INSTANCE;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> computeClassNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        if (!descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getNON_SINGLETON_CLASSIFIERS_MASK())) {
            return SetsKt.emptySet();
        }
        Set set = (Set) this.knownClassNamesInPackage.invoke();
        if (set != null) {
            Iterable<String> iterable = set;
            Collection hashSet = new HashSet();
            for (String identifier : iterable) {
                hashSet.add(Name.identifier(identifier));
            }
            return (Set) hashSet;
        }
        JavaPackage javaPackage = this.jPackage;
        if (function1 == null) {
            function1 = FunctionsKt.alwaysTrue();
        }
        Iterable<JavaClass> classes2 = javaPackage.getClasses(function1);
        Collection linkedHashSet = new LinkedHashSet();
        for (JavaClass javaClass : classes2) {
            Name name = javaClass.getLightClassOriginKind() == LightClassOriginKind.SOURCE ? null : javaClass.getName();
            if (name != null) {
                linkedHashSet.add(name);
            }
        }
        return (Set) linkedHashSet;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> computeFunctionNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        return SetsKt.emptySet();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> computePropertyNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        return SetsKt.emptySet();
    }

    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        return computeDescriptors(descriptorKindFilter, function1, NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
    }
}
