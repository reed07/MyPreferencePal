package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ChainedMemberScope.kt */
public final class ChainedMemberScope implements MemberScope {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String debugName;
    private final List<MemberScope> scopes;

    /* compiled from: ChainedMemberScope.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ChainedMemberScope(@NotNull String str, @NotNull List<? extends MemberScope> list) {
        Intrinsics.checkParameterIsNotNull(str, "debugName");
        Intrinsics.checkParameterIsNotNull(list, "scopes");
        this.debugName = str;
        this.scopes = list;
    }

    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        ClassifierDescriptor classifierDescriptor = null;
        for (MemberScope contributedClassifier : this.scopes) {
            ClassifierDescriptor contributedClassifier2 = contributedClassifier.getContributedClassifier(name, lookupLocation);
            if (contributedClassifier2 != null) {
                if (!(contributedClassifier2 instanceof ClassifierDescriptorWithTypeParameters) || !((ClassifierDescriptorWithTypeParameters) contributedClassifier2).isExpect()) {
                    return contributedClassifier2;
                }
                if (classifierDescriptor == null) {
                    classifierDescriptor = contributedClassifier2;
                }
            }
        }
        return classifierDescriptor;
    }

    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            return SetsKt.emptySet();
        }
        Collection collection = null;
        for (MemberScope contributedVariables : list) {
            collection = ScopeUtilsKt.concat(collection, contributedVariables.getContributedVariables(name, lookupLocation));
        }
        return collection != null ? collection : SetsKt.emptySet();
    }

    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            return SetsKt.emptySet();
        }
        Collection collection = null;
        for (MemberScope contributedFunctions : list) {
            collection = ScopeUtilsKt.concat(collection, contributedFunctions.getContributedFunctions(name, lookupLocation));
        }
        return collection != null ? collection : SetsKt.emptySet();
    }

    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            return SetsKt.emptySet();
        }
        Collection collection = null;
        for (MemberScope contributedDescriptors : list) {
            collection = ScopeUtilsKt.concat(collection, contributedDescriptors.getContributedDescriptors(descriptorKindFilter, function1));
        }
        return collection != null ? collection : SetsKt.emptySet();
    }

    @NotNull
    public Set<Name> getFunctionNames() {
        Iterable<MemberScope> iterable = this.scopes;
        Collection linkedHashSet = new LinkedHashSet();
        for (MemberScope functionNames : iterable) {
            CollectionsKt.addAll(linkedHashSet, (Iterable<? extends T>) functionNames.getFunctionNames());
        }
        return (Set) linkedHashSet;
    }

    @NotNull
    public Set<Name> getVariableNames() {
        Iterable<MemberScope> iterable = this.scopes;
        Collection linkedHashSet = new LinkedHashSet();
        for (MemberScope variableNames : iterable) {
            CollectionsKt.addAll(linkedHashSet, (Iterable<? extends T>) variableNames.getVariableNames());
        }
        return (Set) linkedHashSet;
    }

    @NotNull
    public String toString() {
        return this.debugName;
    }
}
