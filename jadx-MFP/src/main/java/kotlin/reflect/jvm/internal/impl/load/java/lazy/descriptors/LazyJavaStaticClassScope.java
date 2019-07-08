package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaStaticClassScope.kt */
public final class LazyJavaStaticClassScope extends LazyJavaStaticScope {
    private final JavaClass jClass;
    @NotNull
    private final LazyJavaClassDescriptor ownerDescriptor;

    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return null;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public LazyJavaClassDescriptor getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    public LazyJavaStaticClassScope(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull JavaClass javaClass, @NotNull LazyJavaClassDescriptor lazyJavaClassDescriptor) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        Intrinsics.checkParameterIsNotNull(javaClass, "jClass");
        Intrinsics.checkParameterIsNotNull(lazyJavaClassDescriptor, "ownerDescriptor");
        super(lazyJavaResolverContext);
        this.jClass = javaClass;
        this.ownerDescriptor = lazyJavaClassDescriptor;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ClassDeclaredMemberIndex computeMemberIndex() {
        return new ClassDeclaredMemberIndex(this.jClass, LazyJavaStaticClassScope$computeMemberIndex$1.INSTANCE);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> computeFunctionNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Set<Name> mutableSet = CollectionsKt.toMutableSet(((DeclaredMemberIndex) getDeclaredMemberIndex().invoke()).getMethodNames());
        LazyJavaStaticClassScope parentJavaStaticClassScope = UtilKt.getParentJavaStaticClassScope(getOwnerDescriptor());
        Set functionNames = parentJavaStaticClassScope != null ? parentJavaStaticClassScope.getFunctionNames() : null;
        if (functionNames == null) {
            functionNames = SetsKt.emptySet();
        }
        mutableSet.addAll(functionNames);
        if (this.jClass.isEnum()) {
            mutableSet.addAll(CollectionsKt.listOf(DescriptorUtils.ENUM_VALUE_OF, DescriptorUtils.ENUM_VALUES));
        }
        return mutableSet;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> computePropertyNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Set<Name> mutableSet = CollectionsKt.toMutableSet(((DeclaredMemberIndex) getDeclaredMemberIndex().invoke()).getFieldNames());
        flatMapJavaStaticSupertypesScopes(getOwnerDescriptor(), mutableSet, LazyJavaStaticClassScope$computePropertyNames$1$1.INSTANCE);
        return mutableSet;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> computeClassNames(@NotNull DescriptorKindFilter descriptorKindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        return SetsKt.emptySet();
    }

    /* access modifiers changed from: protected */
    public void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> collection, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(collection, "result");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Collection resolveOverridesForStaticMembers = DescriptorResolverUtils.resolveOverridesForStaticMembers(name, getStaticFunctionsFromJavaSuperClasses(name, getOwnerDescriptor()), collection, getOwnerDescriptor(), getC().getComponents().getErrorReporter());
        Intrinsics.checkExpressionValueIsNotNull(resolveOverridesForStaticMembers, "resolveOverridesForStati…components.errorReporter)");
        collection.addAll(resolveOverridesForStaticMembers);
        if (!this.jClass.isEnum()) {
            return;
        }
        if (Intrinsics.areEqual((Object) name, (Object) DescriptorUtils.ENUM_VALUE_OF)) {
            SimpleFunctionDescriptor createEnumValueOfMethod = DescriptorFactory.createEnumValueOfMethod(getOwnerDescriptor());
            Intrinsics.checkExpressionValueIsNotNull(createEnumValueOfMethod, "createEnumValueOfMethod(ownerDescriptor)");
            collection.add(createEnumValueOfMethod);
        } else if (Intrinsics.areEqual((Object) name, (Object) DescriptorUtils.ENUM_VALUES)) {
            SimpleFunctionDescriptor createEnumValuesMethod = DescriptorFactory.createEnumValuesMethod(getOwnerDescriptor());
            Intrinsics.checkExpressionValueIsNotNull(createEnumValuesMethod, "createEnumValuesMethod(ownerDescriptor)");
            collection.add(createEnumValuesMethod);
        }
    }

    /* access modifiers changed from: protected */
    public void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(collection, "result");
        Set flatMapJavaStaticSupertypesScopes = flatMapJavaStaticSupertypesScopes(getOwnerDescriptor(), new LinkedHashSet(), new LazyJavaStaticClassScope$computeNonDeclaredProperties$propertiesFromSupertypes$1(name));
        if (!collection.isEmpty()) {
            Collection resolveOverridesForStaticMembers = DescriptorResolverUtils.resolveOverridesForStaticMembers(name, flatMapJavaStaticSupertypesScopes, collection, getOwnerDescriptor(), getC().getComponents().getErrorReporter());
            Intrinsics.checkExpressionValueIsNotNull(resolveOverridesForStaticMembers, "resolveOverridesForStati…rorReporter\n            )");
            collection.addAll(resolveOverridesForStaticMembers);
            return;
        }
        Iterable iterable = flatMapJavaStaticSupertypesScopes;
        Map linkedHashMap = new LinkedHashMap();
        for (Object next : iterable) {
            PropertyDescriptor realOriginal = getRealOriginal((PropertyDescriptor) next);
            Object obj = linkedHashMap.get(realOriginal);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(realOriginal, obj);
            }
            ((List) obj).add(next);
        }
        Collection arrayList = new ArrayList();
        for (Entry value : linkedHashMap.entrySet()) {
            CollectionsKt.addAll(arrayList, (Iterable<? extends T>) DescriptorResolverUtils.resolveOverridesForStaticMembers(name, (Collection) value.getValue(), collection, getOwnerDescriptor(), getC().getComponents().getErrorReporter()));
        }
        collection.addAll((List) arrayList);
    }

    private final Set<SimpleFunctionDescriptor> getStaticFunctionsFromJavaSuperClasses(Name name, ClassDescriptor classDescriptor) {
        LazyJavaStaticClassScope parentJavaStaticClassScope = UtilKt.getParentJavaStaticClassScope(classDescriptor);
        if (parentJavaStaticClassScope != null) {
            return CollectionsKt.toSet(parentJavaStaticClassScope.getContributedFunctions(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS));
        }
        return SetsKt.emptySet();
    }

    private final <R> Set<R> flatMapJavaStaticSupertypesScopes(ClassDescriptor classDescriptor, Set<R> set, Function1<? super MemberScope, ? extends Collection<? extends R>> function1) {
        DFS.dfs(CollectionsKt.listOf(classDescriptor), LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1.INSTANCE, new LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$2(classDescriptor, set, function1));
        return set;
    }

    private final PropertyDescriptor getRealOriginal(@NotNull PropertyDescriptor propertyDescriptor) {
        Kind kind = propertyDescriptor.getKind();
        Intrinsics.checkExpressionValueIsNotNull(kind, "this.kind");
        if (kind.isReal()) {
            return propertyDescriptor;
        }
        Collection overriddenDescriptors = propertyDescriptor.getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "this.overriddenDescriptors");
        Iterable<PropertyDescriptor> iterable = overriddenDescriptors;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (PropertyDescriptor propertyDescriptor2 : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(propertyDescriptor2, "it");
            arrayList.add(getRealOriginal(propertyDescriptor2));
        }
        return (PropertyDescriptor) CollectionsKt.single(CollectionsKt.distinct((List) arrayList));
    }
}
