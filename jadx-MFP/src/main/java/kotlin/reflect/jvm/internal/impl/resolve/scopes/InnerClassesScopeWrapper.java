package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: InnerClassesScopeWrapper.kt */
public final class InnerClassesScopeWrapper extends MemberScopeImpl {
    @NotNull
    private final MemberScope workerScope;

    public InnerClassesScopeWrapper(@NotNull MemberScope memberScope) {
        Intrinsics.checkParameterIsNotNull(memberScope, "workerScope");
        this.workerScope = memberScope;
    }

    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        ClassifierDescriptor contributedClassifier = this.workerScope.getContributedClassifier(name, lookupLocation);
        if (contributedClassifier == null) {
            return null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) (!(contributedClassifier instanceof ClassDescriptor) ? null : contributedClassifier);
        if (classDescriptor != null) {
            return classDescriptor;
        }
        if (!(contributedClassifier instanceof TypeAliasDescriptor)) {
            contributedClassifier = null;
        }
        return (TypeAliasDescriptor) contributedClassifier;
    }

    @NotNull
    public List<ClassifierDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        DescriptorKindFilter restrictedToKindsOrNull = descriptorKindFilter.restrictedToKindsOrNull(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK());
        if (restrictedToKindsOrNull == null) {
            return CollectionsKt.emptyList();
        }
        Iterable contributedDescriptors = this.workerScope.getContributedDescriptors(restrictedToKindsOrNull, function1);
        Collection arrayList = new ArrayList();
        for (Object next : contributedDescriptors) {
            if (next instanceof ClassifierDescriptorWithTypeParameters) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    @NotNull
    public Set<Name> getFunctionNames() {
        return this.workerScope.getFunctionNames();
    }

    @NotNull
    public Set<Name> getVariableNames() {
        return this.workerScope.getVariableNames();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Classes from ");
        sb.append(this.workerScope);
        return sb.toString();
    }
}
