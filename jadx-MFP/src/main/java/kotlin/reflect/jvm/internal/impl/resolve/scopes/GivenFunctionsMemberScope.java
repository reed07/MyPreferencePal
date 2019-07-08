package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import org.jetbrains.annotations.NotNull;

/* compiled from: GivenFunctionsMemberScope.kt */
public abstract class GivenFunctionsMemberScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(GivenFunctionsMemberScope.class), "allDescriptors", "getAllDescriptors()Ljava/util/List;"))};
    private final NotNullLazyValue allDescriptors$delegate;
    @NotNull
    private final ClassDescriptor containingClass;

    private final List<DeclarationDescriptor> getAllDescriptors() {
        return (List) StorageKt.getValue(this.allDescriptors$delegate, (Object) this, $$delegatedProperties[0]);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract List<FunctionDescriptor> computeDeclaredFunctions();

    /* access modifiers changed from: protected */
    @NotNull
    public final ClassDescriptor getContainingClass() {
        return this.containingClass;
    }

    public GivenFunctionsMemberScope(@NotNull StorageManager storageManager, @NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(classDescriptor, "containingClass");
        this.containingClass = classDescriptor;
        this.allDescriptors$delegate = storageManager.createLazyValue(new GivenFunctionsMemberScope$allDescriptors$2(this));
    }

    @NotNull
    public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        if (!descriptorKindFilter.acceptsKinds(DescriptorKindFilter.CALLABLES.getKindMask())) {
            return CollectionsKt.emptyList();
        }
        return getAllDescriptors();
    }

    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        Iterable allDescriptors = getAllDescriptors();
        Collection arrayList = new ArrayList();
        for (Object next : allDescriptors) {
            if (next instanceof SimpleFunctionDescriptor) {
                arrayList.add(next);
            }
        }
        Iterable iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Object next2 : iterable) {
            if (Intrinsics.areEqual((Object) ((SimpleFunctionDescriptor) next2).getName(), (Object) name)) {
                arrayList2.add(next2);
            }
        }
        return (List) arrayList2;
    }

    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        Iterable allDescriptors = getAllDescriptors();
        Collection arrayList = new ArrayList();
        for (Object next : allDescriptors) {
            if (next instanceof PropertyDescriptor) {
                arrayList.add(next);
            }
        }
        Iterable iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Object next2 : iterable) {
            if (Intrinsics.areEqual((Object) ((PropertyDescriptor) next2).getName(), (Object) name)) {
                arrayList2.add(next2);
            }
        }
        return (List) arrayList2;
    }

    /* access modifiers changed from: private */
    public final List<DeclarationDescriptor> createFakeOverrides(List<? extends FunctionDescriptor> list) {
        List list2;
        ArrayList arrayList = new ArrayList(3);
        TypeConstructor typeConstructor = this.containingClass.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "containingClass.typeConstructor");
        Collection supertypes = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes, "containingClass.typeConstructor.supertypes");
        Iterable<KotlinType> iterable = supertypes;
        Collection arrayList2 = new ArrayList();
        for (KotlinType memberScope : iterable) {
            CollectionsKt.addAll(arrayList2, (Iterable<? extends T>) DefaultImpls.getContributedDescriptors$default(memberScope.getMemberScope(), null, null, 3, null));
        }
        Iterable iterable2 = (List) arrayList2;
        Collection arrayList3 = new ArrayList();
        for (Object next : iterable2) {
            if (next instanceof CallableMemberDescriptor) {
                arrayList3.add(next);
            }
        }
        Iterable iterable3 = (List) arrayList3;
        Map linkedHashMap = new LinkedHashMap();
        for (Object next2 : iterable3) {
            Name name = ((CallableMemberDescriptor) next2).getName();
            Object obj = linkedHashMap.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(name, obj);
            }
            ((List) obj).add(next2);
        }
        for (Entry entry : linkedHashMap.entrySet()) {
            Name name2 = (Name) entry.getKey();
            Iterable iterable4 = (List) entry.getValue();
            Map linkedHashMap2 = new LinkedHashMap();
            for (Object next3 : iterable4) {
                Boolean valueOf = Boolean.valueOf(((CallableMemberDescriptor) next3) instanceof FunctionDescriptor);
                Object obj2 = linkedHashMap2.get(valueOf);
                if (obj2 == null) {
                    obj2 = new ArrayList();
                    linkedHashMap2.put(valueOf, obj2);
                }
                ((List) obj2).add(next3);
            }
            for (Entry entry2 : linkedHashMap2.entrySet()) {
                boolean booleanValue = ((Boolean) entry2.getKey()).booleanValue();
                Collection collection = (List) entry2.getValue();
                if (booleanValue) {
                    Iterable iterable5 = list;
                    Collection arrayList4 = new ArrayList();
                    for (Object next4 : iterable5) {
                        if (Intrinsics.areEqual((Object) ((FunctionDescriptor) next4).getName(), (Object) name2)) {
                            arrayList4.add(next4);
                        }
                    }
                    list2 = (List) arrayList4;
                } else {
                    list2 = CollectionsKt.emptyList();
                }
                OverridingUtil.generateOverridesInFunctionGroup(name2, collection, list2, this.containingClass, new GivenFunctionsMemberScope$createFakeOverrides$4(this, arrayList));
            }
        }
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList);
    }
}
