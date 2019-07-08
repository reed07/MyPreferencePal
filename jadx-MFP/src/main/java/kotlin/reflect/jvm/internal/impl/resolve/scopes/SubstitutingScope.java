package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Substitutable;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SubstitutingScope.kt */
public final class SubstitutingScope implements MemberScope {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SubstitutingScope.class), "_allDescriptors", "get_allDescriptors()Ljava/util/Collection;"))};
    private final Lazy _allDescriptors$delegate = LazyKt.lazy(new SubstitutingScope$_allDescriptors$2(this));
    private Map<DeclarationDescriptor, DeclarationDescriptor> substitutedDescriptors;
    private final TypeSubstitutor substitutor;
    /* access modifiers changed from: private */
    public final MemberScope workerScope;

    private final Collection<DeclarationDescriptor> get_allDescriptors() {
        Lazy lazy = this._allDescriptors$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (Collection) lazy.getValue();
    }

    public SubstitutingScope(@NotNull MemberScope memberScope, @NotNull TypeSubstitutor typeSubstitutor) {
        Intrinsics.checkParameterIsNotNull(memberScope, "workerScope");
        Intrinsics.checkParameterIsNotNull(typeSubstitutor, "givenSubstitutor");
        this.workerScope = memberScope;
        TypeSubstitution substitution = typeSubstitutor.getSubstitution();
        Intrinsics.checkExpressionValueIsNotNull(substitution, "givenSubstitutor.substitution");
        this.substitutor = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(substitution, false, 1, null).buildSubstitutor();
    }

    private final <D extends DeclarationDescriptor> D substitute(D d) {
        if (this.substitutor.isEmpty()) {
            return d;
        }
        if (this.substitutedDescriptors == null) {
            this.substitutedDescriptors = new HashMap();
        }
        Map<DeclarationDescriptor, DeclarationDescriptor> map = this.substitutedDescriptors;
        if (map == null) {
            Intrinsics.throwNpe();
        }
        D d2 = map.get(d);
        if (d2 == null) {
            if (d instanceof Substitutable) {
                D substitute = ((Substitutable) d).substitute(this.substitutor);
                if (substitute != null) {
                    d2 = (DeclarationDescriptor) substitute;
                    map.put(d, d2);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("We expect that no conflict should happen while substitution is guaranteed to generate invariant projection, ");
                    sb.append("but ");
                    sb.append(d);
                    sb.append(" substitution fails");
                    throw new AssertionError(sb.toString());
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unknown descriptor in scope: ");
                sb2.append(d);
                throw new IllegalStateException(sb2.toString().toString());
            }
        }
        D d3 = (DeclarationDescriptor) d2;
        if (d3 != null) {
            return d3;
        }
        throw new TypeCastException("null cannot be cast to non-null type D");
    }

    /* access modifiers changed from: private */
    public final <D extends DeclarationDescriptor> Collection<D> substitute(Collection<? extends D> collection) {
        if (this.substitutor.isEmpty() || collection.isEmpty()) {
            return collection;
        }
        LinkedHashSet newLinkedHashSetWithExpectedSize = CollectionsKt.newLinkedHashSetWithExpectedSize(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            newLinkedHashSetWithExpectedSize.add(substitute((D) (DeclarationDescriptor) it.next()));
        }
        return newLinkedHashSetWithExpectedSize;
    }

    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return substitute(this.workerScope.getContributedVariables(name, lookupLocation));
    }

    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        ClassifierDescriptor contributedClassifier = this.workerScope.getContributedClassifier(name, lookupLocation);
        if (contributedClassifier != null) {
            return (ClassifierDescriptor) substitute((D) contributedClassifier);
        }
        return null;
    }

    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        return substitute(this.workerScope.getContributedFunctions(name, lookupLocation));
    }

    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        return get_allDescriptors();
    }

    @NotNull
    public Set<Name> getFunctionNames() {
        return this.workerScope.getFunctionNames();
    }

    @NotNull
    public Set<Name> getVariableNames() {
        return this.workerScope.getVariableNames();
    }
}
