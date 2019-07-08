package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MemberScopeImpl.kt */
public abstract class MemberScopeImpl implements MemberScope {
    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return null;
    }

    public void recordLookup(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        DefaultImpls.recordLookup(this, name, lookupLocation);
    }

    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return CollectionsKt.emptyList();
    }

    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return CollectionsKt.emptyList();
    }

    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        return CollectionsKt.emptyList();
    }

    @NotNull
    public Set<Name> getFunctionNames() {
        Iterable contributedDescriptors = getContributedDescriptors(DescriptorKindFilter.FUNCTIONS, FunctionsKt.alwaysTrue());
        Collection arrayList = new ArrayList();
        for (Object next : contributedDescriptors) {
            if (next instanceof SimpleFunctionDescriptor) {
                arrayList.add(next);
            }
        }
        Iterable<SimpleFunctionDescriptor> iterable = (List) arrayList;
        Collection linkedHashSet = new LinkedHashSet();
        for (SimpleFunctionDescriptor name : iterable) {
            linkedHashSet.add(name.getName());
        }
        return (Set) linkedHashSet;
    }

    @NotNull
    public Set<Name> getVariableNames() {
        Iterable contributedDescriptors = getContributedDescriptors(DescriptorKindFilter.VARIABLES, FunctionsKt.alwaysTrue());
        Collection arrayList = new ArrayList();
        for (Object next : contributedDescriptors) {
            if (next instanceof VariableDescriptor) {
                arrayList.add(next);
            }
        }
        Iterable<VariableDescriptor> iterable = (List) arrayList;
        Collection linkedHashSet = new LinkedHashSet();
        for (VariableDescriptor name : iterable) {
            linkedHashSet.add(name.getName());
        }
        return (Set) linkedHashSet;
    }
}
