package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.MemberComparator.NameAndTypeMemberComparator;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedMemberScope.kt */
public abstract class DeserializedMemberScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "variableNamesLazy", "getVariableNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "classNames", "getClassNames$deserialization()Ljava/util/Set;"))};
    /* access modifiers changed from: private */
    @NotNull
    public final DeserializationContext c;
    @NotNull
    private final NotNullLazyValue classNames$delegate;
    private final NotNullLazyValue functionNamesLazy$delegate;
    /* access modifiers changed from: private */
    public final Map<Name, byte[]> functionProtosBytes;
    private final MemoizedFunctionToNotNull<Name, Collection<SimpleFunctionDescriptor>> functions;
    private final MemoizedFunctionToNotNull<Name, Collection<PropertyDescriptor>> properties;
    /* access modifiers changed from: private */
    public final Map<Name, byte[]> propertyProtosBytes;
    private final MemoizedFunctionToNullable<Name, TypeAliasDescriptor> typeAliasByName;
    private final Map<Name, byte[]> typeAliasBytes;
    private final NotNullLazyValue variableNamesLazy$delegate;

    private final Set<Name> getFunctionNamesLazy() {
        return (Set) StorageKt.getValue(this.functionNamesLazy$delegate, (Object) this, $$delegatedProperties[0]);
    }

    private final Set<Name> getVariableNamesLazy() {
        return (Set) StorageKt.getValue(this.variableNamesLazy$delegate, (Object) this, $$delegatedProperties[1]);
    }

    /* access modifiers changed from: protected */
    public abstract void addEnumEntryDescriptors(@NotNull Collection<DeclarationDescriptor> collection, @NotNull Function1<? super Name, Boolean> function1);

    /* access modifiers changed from: protected */
    public void computeNonDeclaredFunctions(@NotNull Name name, @NotNull Collection<SimpleFunctionDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(collection, "functions");
    }

    /* access modifiers changed from: protected */
    public void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(collection, "descriptors");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract ClassId createClassId(@NotNull Name name);

    @NotNull
    public final Set<Name> getClassNames$deserialization() {
        return (Set) StorageKt.getValue(this.classNames$delegate, (Object) this, $$delegatedProperties[2]);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract Set<Name> getNonDeclaredFunctionNames();

    /* access modifiers changed from: protected */
    @NotNull
    public abstract Set<Name> getNonDeclaredVariableNames();

    /* access modifiers changed from: protected */
    @NotNull
    public final DeserializationContext getC() {
        return this.c;
    }

    protected DeserializedMemberScope(@NotNull DeserializationContext deserializationContext, @NotNull Collection<Function> collection, @NotNull Collection<Property> collection2, @NotNull Collection<TypeAlias> collection3, @NotNull Function0<? extends Collection<Name>> function0) {
        Map<Name, byte[]> map;
        Intrinsics.checkParameterIsNotNull(deserializationContext, "c");
        Intrinsics.checkParameterIsNotNull(collection, "functionList");
        Intrinsics.checkParameterIsNotNull(collection2, "propertyList");
        Intrinsics.checkParameterIsNotNull(collection3, "typeAliasList");
        Intrinsics.checkParameterIsNotNull(function0, "classNames");
        this.c = deserializationContext;
        Iterable iterable = collection;
        Map linkedHashMap = new LinkedHashMap();
        for (Object next : iterable) {
            Name name = NameResolverUtilKt.getName(this.c.getNameResolver(), ((Function) ((MessageLite) next)).getName());
            Object obj = linkedHashMap.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(name, obj);
            }
            ((List) obj).add(next);
        }
        this.functionProtosBytes = packToByteArray(linkedHashMap);
        Iterable iterable2 = collection2;
        Map linkedHashMap2 = new LinkedHashMap();
        for (Object next2 : iterable2) {
            Name name2 = NameResolverUtilKt.getName(this.c.getNameResolver(), ((Property) ((MessageLite) next2)).getName());
            Object obj2 = linkedHashMap2.get(name2);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap2.put(name2, obj2);
            }
            ((List) obj2).add(next2);
        }
        this.propertyProtosBytes = packToByteArray(linkedHashMap2);
        if (this.c.getComponents().getConfiguration().getTypeAliasesAllowed()) {
            Iterable iterable3 = collection3;
            Map linkedHashMap3 = new LinkedHashMap();
            for (Object next3 : iterable3) {
                Name name3 = NameResolverUtilKt.getName(this.c.getNameResolver(), ((TypeAlias) ((MessageLite) next3)).getName());
                Object obj3 = linkedHashMap3.get(name3);
                if (obj3 == null) {
                    obj3 = new ArrayList();
                    linkedHashMap3.put(name3, obj3);
                }
                ((List) obj3).add(next3);
            }
            map = packToByteArray(linkedHashMap3);
        } else {
            map = MapsKt.emptyMap();
        }
        this.typeAliasBytes = map;
        this.functions = this.c.getStorageManager().createMemoizedFunction(new DeserializedMemberScope$functions$1(this));
        this.properties = this.c.getStorageManager().createMemoizedFunction(new DeserializedMemberScope$properties$1(this));
        this.typeAliasByName = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new DeserializedMemberScope$typeAliasByName$1(this));
        this.functionNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new DeserializedMemberScope$functionNamesLazy$2(this));
        this.variableNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new DeserializedMemberScope$variableNamesLazy$2(this));
        this.classNames$delegate = this.c.getStorageManager().createLazyValue(new DeserializedMemberScope$classNames$2(function0));
    }

    private final Set<Name> getTypeAliasNames() {
        return this.typeAliasBytes.keySet();
    }

    @NotNull
    public Set<Name> getFunctionNames() {
        return getFunctionNamesLazy();
    }

    @NotNull
    public Set<Name> getVariableNames() {
        return getVariableNamesLazy();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0043 A[LOOP:0: B:7:0x003d->B:9:0x0043, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor> computeFunctions(kotlin.reflect.jvm.internal.impl.name.Name r6) {
        /*
            r5 = this;
            java.util.Map<kotlin.reflect.jvm.internal.impl.name.Name, byte[]> r0 = r5.functionProtosBytes
            kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function> r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.PARSER
            java.lang.String r2 = "ProtoBuf.Function.PARSER"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.Object r0 = r0.get(r6)
            byte[] r0 = (byte[]) r0
            if (r0 == 0) goto L_0x002a
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r0)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$1 r0 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$1
            r0.<init>(r2, r5, r1)
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.generateSequence(r0)
            java.util.List r0 = kotlin.sequences.SequencesKt.toList(r0)
            if (r0 == 0) goto L_0x002a
            java.util.Collection r0 = (java.util.Collection) r0
            goto L_0x0030
        L_0x002a:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            java.util.Collection r0 = (java.util.Collection) r0
        L_0x0030:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r0 = r0.iterator()
        L_0x003d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x005c
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function r2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function) r2
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r5.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r3 = r3.getMemberDeserializer()
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r2 = r3.loadFunction(r2)
            r1.add(r2)
            goto L_0x003d
        L_0x005c:
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
            r5.computeNonDeclaredFunctions(r6, r0)
            java.util.List r6 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(r1)
            java.util.Collection r6 = (java.util.Collection) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.computeFunctions(kotlin.reflect.jvm.internal.impl.name.Name):java.util.Collection");
    }

    @NotNull
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        if (!getFunctionNames().contains(name)) {
            return CollectionsKt.emptyList();
        }
        return (Collection) this.functions.invoke(name);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0043 A[LOOP:0: B:7:0x003d->B:9:0x0043, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor> computeProperties(kotlin.reflect.jvm.internal.impl.name.Name r6) {
        /*
            r5 = this;
            java.util.Map<kotlin.reflect.jvm.internal.impl.name.Name, byte[]> r0 = r5.propertyProtosBytes
            kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property> r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.PARSER
            java.lang.String r2 = "ProtoBuf.Property.PARSER"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.Object r0 = r0.get(r6)
            byte[] r0 = (byte[]) r0
            if (r0 == 0) goto L_0x002a
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r0)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$3 r0 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$3
            r0.<init>(r2, r5, r1)
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.generateSequence(r0)
            java.util.List r0 = kotlin.sequences.SequencesKt.toList(r0)
            if (r0 == 0) goto L_0x002a
            java.util.Collection r0 = (java.util.Collection) r0
            goto L_0x0030
        L_0x002a:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            java.util.Collection r0 = (java.util.Collection) r0
        L_0x0030:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r0 = r0.iterator()
        L_0x003d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x005c
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property r2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property) r2
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r5.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r3 = r3.getMemberDeserializer()
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r2 = r3.loadProperty(r2)
            r1.add(r2)
            goto L_0x003d
        L_0x005c:
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
            r5.computeNonDeclaredProperties(r6, r0)
            java.util.List r6 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(r1)
            java.util.Collection r6 = (java.util.Collection) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.computeProperties(kotlin.reflect.jvm.internal.impl.name.Name):java.util.Collection");
    }

    /* access modifiers changed from: private */
    public final TypeAliasDescriptor createTypeAlias(Name name) {
        byte[] bArr = (byte[]) this.typeAliasBytes.get(name);
        if (bArr == null) {
            return null;
        }
        TypeAlias parseDelimitedFrom = TypeAlias.parseDelimitedFrom(new ByteArrayInputStream(bArr), this.c.getComponents().getExtensionRegistryLite());
        if (parseDelimitedFrom != null) {
            return this.c.getMemberDeserializer().loadTypeAlias(parseDelimitedFrom);
        }
        return null;
    }

    @NotNull
    public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        if (!getVariableNames().contains(name)) {
            return CollectionsKt.emptyList();
        }
        return (Collection) this.properties.invoke(name);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final Collection<DeclarationDescriptor> computeDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        ArrayList arrayList = new ArrayList(0);
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getSINGLETON_CLASSIFIERS_MASK())) {
            addEnumEntryDescriptors(arrayList, function1);
        }
        Collection collection = arrayList;
        addFunctionsAndProperties(collection, descriptorKindFilter, function1, lookupLocation);
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK())) {
            for (Name name : getClassNames$deserialization()) {
                if (((Boolean) function1.invoke(name)).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection, deserializeClass(name));
                }
            }
        }
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getTYPE_ALIASES_MASK())) {
            for (Name name2 : getTypeAliasNames()) {
                if (((Boolean) function1.invoke(name2)).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection, this.typeAliasByName.invoke(name2));
                }
            }
        }
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList);
    }

    private final void addFunctionsAndProperties(Collection<DeclarationDescriptor> collection, DescriptorKindFilter descriptorKindFilter, Function1<? super Name, Boolean> function1, LookupLocation lookupLocation) {
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getVARIABLES_MASK())) {
            Collection<Name> variableNames = getVariableNames();
            ArrayList arrayList = new ArrayList();
            for (Name name : variableNames) {
                if (((Boolean) function1.invoke(name)).booleanValue()) {
                    arrayList.addAll(getContributedVariables(name, lookupLocation));
                }
            }
            List list = arrayList;
            NameAndTypeMemberComparator nameAndTypeMemberComparator = NameAndTypeMemberComparator.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(nameAndTypeMemberComparator, "MemberComparator.NameAnd…MemberComparator.INSTANCE");
            CollectionsKt.sortWith(list, nameAndTypeMemberComparator);
            collection.addAll(arrayList);
        }
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getFUNCTIONS_MASK())) {
            Collection<Name> functionNames = getFunctionNames();
            ArrayList arrayList2 = new ArrayList();
            for (Name name2 : functionNames) {
                if (((Boolean) function1.invoke(name2)).booleanValue()) {
                    arrayList2.addAll(getContributedFunctions(name2, lookupLocation));
                }
            }
            List list2 = arrayList2;
            NameAndTypeMemberComparator nameAndTypeMemberComparator2 = NameAndTypeMemberComparator.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(nameAndTypeMemberComparator2, "MemberComparator.NameAnd…MemberComparator.INSTANCE");
            CollectionsKt.sortWith(list2, nameAndTypeMemberComparator2);
            collection.addAll(arrayList2);
        }
    }

    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        if (hasClass(name)) {
            return deserializeClass(name);
        }
        if (getTypeAliasNames().contains(name)) {
            return (ClassifierDescriptor) this.typeAliasByName.invoke(name);
        }
        return null;
    }

    private final ClassDescriptor deserializeClass(Name name) {
        return this.c.getComponents().deserializeClass(createClassId(name));
    }

    /* access modifiers changed from: protected */
    public boolean hasClass(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return getClassNames$deserialization().contains(name);
    }

    private final Map<Name, byte[]> packToByteArray(@NotNull Map<Name, ? extends Collection<? extends AbstractMessageLite>> map) {
        Map<Name, byte[]> linkedHashMap = new LinkedHashMap<>(MapsKt.mapCapacity(map.size()));
        for (Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterable<AbstractMessageLite> iterable = (Iterable) entry.getValue();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (AbstractMessageLite writeDelimitedTo : iterable) {
                writeDelimitedTo.writeDelimitedTo(byteArrayOutputStream);
                arrayList.add(Unit.INSTANCE);
            }
            List list = (List) arrayList;
            linkedHashMap.put(key, byteArrayOutputStream.toByteArray());
        }
        return linkedHashMap;
    }
}
