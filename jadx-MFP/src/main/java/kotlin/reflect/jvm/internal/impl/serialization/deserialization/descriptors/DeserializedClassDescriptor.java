package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.BooleanFlagField;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.StaticScopeForKotlinEnum;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedClassDescriptor.kt */
public final class DeserializedClassDescriptor extends AbstractClassDescriptor {
    @NotNull
    private final Annotations annotations;
    @NotNull
    private final DeserializationContext c;
    /* access modifiers changed from: private */
    public final ClassId classId;
    @NotNull
    private final Class classProto;
    private final NullableLazyValue<ClassDescriptor> companionObjectDescriptor;
    private final NotNullLazyValue<Collection<ClassConstructorDescriptor>> constructors;
    private final DeclarationDescriptor containingDeclaration;
    /* access modifiers changed from: private */
    public final EnumEntryClassDescriptors enumEntries;
    private final ClassKind kind = ProtoEnumFlags.INSTANCE.classKind((Kind) Flags.CLASS_KIND.get(this.classProto.getFlags()));
    private final DeserializedClassMemberScope memberScope;
    @NotNull
    private final BinaryVersion metadataVersion;
    private final Modality modality = ProtoEnumFlags.INSTANCE.modality((ProtoBuf.Modality) Flags.MODALITY.get(this.classProto.getFlags()));
    private final NullableLazyValue<ClassConstructorDescriptor> primaryConstructor;
    private final NotNullLazyValue<Collection<ClassDescriptor>> sealedSubclasses;
    private final SourceElement sourceElement;
    private final MemberScopeImpl staticScope;
    @NotNull
    private final ProtoContainer.Class thisAsProtoContainer;
    /* access modifiers changed from: private */
    public final DeserializedClassTypeConstructor typeConstructor;
    private final Visibility visibility = ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(this.classProto.getFlags()));

    /* compiled from: DeserializedClassDescriptor.kt */
    private final class DeserializedClassMemberScope extends DeserializedMemberScope {
        private final NotNullLazyValue<Collection<DeclarationDescriptor>> allDescriptors;

        public DeserializedClassMemberScope() {
            DeserializationContext c = DeserializedClassDescriptor.this.getC();
            List functionList = DeserializedClassDescriptor.this.getClassProto().getFunctionList();
            Intrinsics.checkExpressionValueIsNotNull(functionList, "classProto.functionList");
            Collection collection = functionList;
            List propertyList = DeserializedClassDescriptor.this.getClassProto().getPropertyList();
            Intrinsics.checkExpressionValueIsNotNull(propertyList, "classProto.propertyList");
            Collection collection2 = propertyList;
            List typeAliasList = DeserializedClassDescriptor.this.getClassProto().getTypeAliasList();
            Intrinsics.checkExpressionValueIsNotNull(typeAliasList, "classProto.typeAliasList");
            Collection collection3 = typeAliasList;
            List nestedClassNameList = DeserializedClassDescriptor.this.getClassProto().getNestedClassNameList();
            Intrinsics.checkExpressionValueIsNotNull(nestedClassNameList, "classProto.nestedClassNameList");
            Iterable<Number> iterable = nestedClassNameList;
            NameResolver nameResolver = DeserializedClassDescriptor.this.getC().getNameResolver();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Number intValue : iterable) {
                arrayList.add(NameResolverUtilKt.getName(nameResolver, intValue.intValue()));
            }
            super(c, collection, collection2, collection3, new DeserializedClassDescriptor$DeserializedClassMemberScope$2$1((List) arrayList));
            this.allDescriptors = getC().getStorageManager().createLazyValue(new DeserializedClassDescriptor$DeserializedClassMemberScope$allDescriptors$1(this));
        }

        private final DeserializedClassDescriptor getClassDescriptor() {
            return DeserializedClassDescriptor.this;
        }

        @NotNull
        public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
            Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
            Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
            return (Collection) this.allDescriptors.invoke();
        }

        @NotNull
        public Collection<SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
            recordLookup(name, lookupLocation);
            return super.getContributedFunctions(name, lookupLocation);
        }

        @NotNull
        public Collection<PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
            recordLookup(name, lookupLocation);
            return super.getContributedVariables(name, lookupLocation);
        }

        /* access modifiers changed from: protected */
        public void computeNonDeclaredFunctions(@NotNull Name name, @NotNull Collection<SimpleFunctionDescriptor> collection) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(collection, "functions");
            ArrayList arrayList = new ArrayList();
            for (KotlinType memberScope : getClassDescriptor().getTypeConstructor().getSupertypes()) {
                arrayList.addAll(memberScope.getMemberScope().getContributedFunctions(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            CollectionsKt.retainAll((Iterable<? extends T>) collection, (Function1<? super T, Boolean>) new DeserializedClassDescriptor$DeserializedClassMemberScope$computeNonDeclaredFunctions$1<Object,Boolean>(this));
            collection.addAll(getC().getComponents().getAdditionalClassPartsProvider().getFunctions(name, DeserializedClassDescriptor.this));
            generateFakeOverrides(name, arrayList, collection);
        }

        /* access modifiers changed from: protected */
        public void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> collection) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(collection, "descriptors");
            ArrayList arrayList = new ArrayList();
            for (KotlinType memberScope : getClassDescriptor().getTypeConstructor().getSupertypes()) {
                arrayList.addAll(memberScope.getMemberScope().getContributedVariables(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            generateFakeOverrides(name, arrayList, collection);
        }

        private final <D extends CallableMemberDescriptor> void generateFakeOverrides(Name name, Collection<? extends D> collection, Collection<D> collection2) {
            OverridingUtil.generateOverridesInFunctionGroup(name, collection, new ArrayList(collection2), getClassDescriptor(), new DeserializedClassDescriptor$DeserializedClassMemberScope$generateFakeOverrides$1(collection2));
        }

        /* access modifiers changed from: protected */
        @NotNull
        public Set<Name> getNonDeclaredFunctionNames() {
            Iterable<KotlinType> supertypes = getClassDescriptor().typeConstructor.getSupertypes();
            Collection linkedHashSet = new LinkedHashSet();
            for (KotlinType memberScope : supertypes) {
                CollectionsKt.addAll(linkedHashSet, (Iterable<? extends T>) memberScope.getMemberScope().getFunctionNames());
            }
            ((LinkedHashSet) linkedHashSet).addAll(getC().getComponents().getAdditionalClassPartsProvider().getFunctionsNames(DeserializedClassDescriptor.this));
            return (Set) linkedHashSet;
        }

        /* access modifiers changed from: protected */
        @NotNull
        public Set<Name> getNonDeclaredVariableNames() {
            Iterable<KotlinType> supertypes = getClassDescriptor().typeConstructor.getSupertypes();
            Collection linkedHashSet = new LinkedHashSet();
            for (KotlinType memberScope : supertypes) {
                CollectionsKt.addAll(linkedHashSet, (Iterable<? extends T>) memberScope.getMemberScope().getVariableNames());
            }
            return (Set) linkedHashSet;
        }

        @Nullable
        public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
            recordLookup(name, lookupLocation);
            EnumEntryClassDescriptors access$getEnumEntries$p = getClassDescriptor().enumEntries;
            if (access$getEnumEntries$p != null) {
                ClassDescriptor findEnumEntry = access$getEnumEntries$p.findEnumEntry(name);
                if (findEnumEntry != null) {
                    return findEnumEntry;
                }
            }
            return super.getContributedClassifier(name, lookupLocation);
        }

        /* access modifiers changed from: protected */
        @NotNull
        public ClassId createClassId(@NotNull Name name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            ClassId createNestedClassId = DeserializedClassDescriptor.this.classId.createNestedClassId(name);
            Intrinsics.checkExpressionValueIsNotNull(createNestedClassId, "classId.createNestedClassId(name)");
            return createNestedClassId;
        }

        /* access modifiers changed from: protected */
        public void addEnumEntryDescriptors(@NotNull Collection<DeclarationDescriptor> collection, @NotNull Function1<? super Name, Boolean> function1) {
            Intrinsics.checkParameterIsNotNull(collection, "result");
            Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
            EnumEntryClassDescriptors access$getEnumEntries$p = getClassDescriptor().enumEntries;
            Collection all = access$getEnumEntries$p != null ? access$getEnumEntries$p.all() : null;
            if (all == null) {
                all = CollectionsKt.emptyList();
            }
            collection.addAll(all);
        }

        public void recordLookup(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
            UtilsKt.record(getC().getComponents().getLookupTracker(), lookupLocation, (ClassDescriptor) getClassDescriptor(), name);
        }
    }

    /* compiled from: DeserializedClassDescriptor.kt */
    private final class DeserializedClassTypeConstructor extends AbstractClassTypeConstructor {
        private final NotNullLazyValue<List<TypeParameterDescriptor>> parameters;

        public boolean isDenotable() {
            return true;
        }

        public DeserializedClassTypeConstructor() {
            super(DeserializedClassDescriptor.this.getC().getStorageManager());
            this.parameters = DeserializedClassDescriptor.this.getC().getStorageManager().createLazyValue(new DeserializedClassDescriptor$DeserializedClassTypeConstructor$parameters$1(this));
        }

        /* access modifiers changed from: protected */
        @NotNull
        public Collection<KotlinType> computeSupertypes() {
            String str;
            Iterable<Type> supertypes = ProtoTypeTableUtilKt.supertypes(DeserializedClassDescriptor.this.getClassProto(), DeserializedClassDescriptor.this.getC().getTypeTable());
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypes, 10));
            for (Type type : supertypes) {
                arrayList.add(DeserializedClassDescriptor.this.getC().getTypeDeserializer().type(type));
            }
            Iterable<KotlinType> plus = CollectionsKt.plus((Collection<? extends T>) (List) arrayList, (Iterable<? extends T>) DeserializedClassDescriptor.this.getC().getComponents().getAdditionalClassPartsProvider().getSupertypes(DeserializedClassDescriptor.this));
            Collection arrayList2 = new ArrayList();
            for (KotlinType constructor : plus) {
                ClassifierDescriptor declarationDescriptor = constructor.getConstructor().getDeclarationDescriptor();
                if (!(declarationDescriptor instanceof MockClassDescriptor)) {
                    declarationDescriptor = null;
                }
                MockClassDescriptor mockClassDescriptor = (MockClassDescriptor) declarationDescriptor;
                if (mockClassDescriptor != null) {
                    arrayList2.add(mockClassDescriptor);
                }
            }
            List list = (List) arrayList2;
            if (!list.isEmpty()) {
                ErrorReporter errorReporter = DeserializedClassDescriptor.this.getC().getComponents().getErrorReporter();
                ClassDescriptor classDescriptor = DeserializedClassDescriptor.this;
                Iterable<MockClassDescriptor> iterable = list;
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (MockClassDescriptor mockClassDescriptor2 : iterable) {
                    ClassId classId = DescriptorUtilsKt.getClassId(mockClassDescriptor2);
                    if (classId != null) {
                        FqName asSingleFqName = classId.asSingleFqName();
                        if (asSingleFqName != null) {
                            str = asSingleFqName.asString();
                            if (str != null) {
                                arrayList3.add(str);
                            }
                        }
                    }
                    str = mockClassDescriptor2.getName().asString();
                    arrayList3.add(str);
                }
                errorReporter.reportIncompleteHierarchy(classDescriptor, (List) arrayList3);
            }
            return CollectionsKt.toList(plus);
        }

        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            return (List) this.parameters.invoke();
        }

        @NotNull
        public DeserializedClassDescriptor getDeclarationDescriptor() {
            return DeserializedClassDescriptor.this;
        }

        @NotNull
        public String toString() {
            String name = DeserializedClassDescriptor.this.getName().toString();
            Intrinsics.checkExpressionValueIsNotNull(name, "name.toString()");
            return name;
        }

        /* access modifiers changed from: protected */
        @NotNull
        public SupertypeLoopChecker getSupertypeLoopChecker() {
            return EMPTY.INSTANCE;
        }
    }

    /* compiled from: DeserializedClassDescriptor.kt */
    private final class EnumEntryClassDescriptors {
        private final MemoizedFunctionToNullable<Name, ClassDescriptor> enumEntryByName;
        /* access modifiers changed from: private */
        public final Map<Name, EnumEntry> enumEntryProtos;
        /* access modifiers changed from: private */
        public final NotNullLazyValue<Set<Name>> enumMemberNames;

        public EnumEntryClassDescriptors() {
            List enumEntryList = DeserializedClassDescriptor.this.getClassProto().getEnumEntryList();
            Intrinsics.checkExpressionValueIsNotNull(enumEntryList, "classProto.enumEntryList");
            Iterable iterable = enumEntryList;
            Map<Name, EnumEntry> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
            for (Object next : iterable) {
                EnumEntry enumEntry = (EnumEntry) next;
                NameResolver nameResolver = DeserializedClassDescriptor.this.getC().getNameResolver();
                Intrinsics.checkExpressionValueIsNotNull(enumEntry, "it");
                linkedHashMap.put(NameResolverUtilKt.getName(nameResolver, enumEntry.getName()), next);
            }
            this.enumEntryProtos = linkedHashMap;
            this.enumEntryByName = DeserializedClassDescriptor.this.getC().getStorageManager().createMemoizedFunctionWithNullableValues(new DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1(this));
            this.enumMemberNames = DeserializedClassDescriptor.this.getC().getStorageManager().createLazyValue(new DeserializedClassDescriptor$EnumEntryClassDescriptors$enumMemberNames$1(this));
        }

        @Nullable
        public final ClassDescriptor findEnumEntry(@NotNull Name name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return (ClassDescriptor) this.enumEntryByName.invoke(name);
        }

        /* access modifiers changed from: private */
        public final Set<Name> computeEnumMemberNames() {
            HashSet hashSet = new HashSet();
            for (KotlinType memberScope : DeserializedClassDescriptor.this.getTypeConstructor().getSupertypes()) {
                for (DeclarationDescriptor declarationDescriptor : DefaultImpls.getContributedDescriptors$default(memberScope.getMemberScope(), null, null, 3, null)) {
                    if ((declarationDescriptor instanceof SimpleFunctionDescriptor) || (declarationDescriptor instanceof PropertyDescriptor)) {
                        hashSet.add(declarationDescriptor.getName());
                    }
                }
            }
            List<Function> functionList = DeserializedClassDescriptor.this.getClassProto().getFunctionList();
            Intrinsics.checkExpressionValueIsNotNull(functionList, "classProto.functionList");
            for (Function function : functionList) {
                Collection collection = hashSet;
                NameResolver nameResolver = DeserializedClassDescriptor.this.getC().getNameResolver();
                Intrinsics.checkExpressionValueIsNotNull(function, "it");
                collection.add(NameResolverUtilKt.getName(nameResolver, function.getName()));
            }
            Collection collection2 = hashSet;
            Set set = (Set) collection2;
            List<Property> propertyList = DeserializedClassDescriptor.this.getClassProto().getPropertyList();
            Intrinsics.checkExpressionValueIsNotNull(propertyList, "classProto.propertyList");
            for (Property property : propertyList) {
                NameResolver nameResolver2 = DeserializedClassDescriptor.this.getC().getNameResolver();
                Intrinsics.checkExpressionValueIsNotNull(property, "it");
                collection2.add(NameResolverUtilKt.getName(nameResolver2, property.getName()));
            }
            return SetsKt.plus(set, (Iterable<? extends T>) collection2);
        }

        @NotNull
        public final Collection<ClassDescriptor> all() {
            Iterable<Name> keySet = this.enumEntryProtos.keySet();
            Collection arrayList = new ArrayList();
            for (Name findEnumEntry : keySet) {
                ClassDescriptor findEnumEntry2 = findEnumEntry(findEnumEntry);
                if (findEnumEntry2 != null) {
                    arrayList.add(findEnumEntry2);
                }
            }
            return (List) arrayList;
        }
    }

    public boolean isActual() {
        return false;
    }

    @NotNull
    public final Class getClassProto() {
        return this.classProto;
    }

    @NotNull
    public final BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public DeserializedClassDescriptor(@NotNull DeserializationContext deserializationContext, @NotNull Class classR, @NotNull NameResolver nameResolver, @NotNull BinaryVersion binaryVersion, @NotNull SourceElement sourceElement2) {
        Annotations annotations2;
        Intrinsics.checkParameterIsNotNull(deserializationContext, "outerContext");
        Intrinsics.checkParameterIsNotNull(classR, "classProto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(sourceElement2, "sourceElement");
        super(deserializationContext.getStorageManager(), NameResolverUtilKt.getClassId(nameResolver, classR.getFqName()).getShortClassName());
        this.classProto = classR;
        this.metadataVersion = binaryVersion;
        this.sourceElement = sourceElement2;
        this.classId = NameResolverUtilKt.getClassId(nameResolver, this.classProto.getFqName());
        DeclarationDescriptor declarationDescriptor = this;
        List typeParameterList = this.classProto.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "classProto.typeParameterList");
        TypeTable typeTable = this.classProto.getTypeTable();
        Intrinsics.checkExpressionValueIsNotNull(typeTable, "classProto.typeTable");
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable typeTable2 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(typeTable);
        Companion companion = VersionRequirementTable.Companion;
        ProtoBuf.VersionRequirementTable versionRequirementTable = this.classProto.getVersionRequirementTable();
        Intrinsics.checkExpressionValueIsNotNull(versionRequirementTable, "classProto.versionRequirementTable");
        this.c = deserializationContext.childContext(declarationDescriptor, typeParameterList, nameResolver, typeTable2, companion.create(versionRequirementTable), this.metadataVersion);
        this.staticScope = this.kind == ClassKind.ENUM_CLASS ? new StaticScopeForKotlinEnum(this.c.getStorageManager(), this) : Empty.INSTANCE;
        this.typeConstructor = new DeserializedClassTypeConstructor();
        this.memberScope = new DeserializedClassMemberScope();
        ProtoContainer.Class classR2 = null;
        this.enumEntries = this.kind == ClassKind.ENUM_CLASS ? new EnumEntryClassDescriptors() : null;
        this.containingDeclaration = deserializationContext.getContainingDeclaration();
        this.primaryConstructor = this.c.getStorageManager().createNullableLazyValue(new DeserializedClassDescriptor$primaryConstructor$1(this));
        this.constructors = this.c.getStorageManager().createLazyValue(new DeserializedClassDescriptor$constructors$1(this));
        this.companionObjectDescriptor = this.c.getStorageManager().createNullableLazyValue(new DeserializedClassDescriptor$companionObjectDescriptor$1(this));
        this.sealedSubclasses = this.c.getStorageManager().createLazyValue(new DeserializedClassDescriptor$sealedSubclasses$1(this));
        Class classR3 = this.classProto;
        NameResolver nameResolver2 = this.c.getNameResolver();
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable typeTable3 = this.c.getTypeTable();
        SourceElement sourceElement3 = this.sourceElement;
        DeclarationDescriptor declarationDescriptor2 = this.containingDeclaration;
        if (!(declarationDescriptor2 instanceof DeserializedClassDescriptor)) {
            declarationDescriptor2 = null;
        }
        DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) declarationDescriptor2;
        if (deserializedClassDescriptor != null) {
            classR2 = deserializedClassDescriptor.thisAsProtoContainer;
        }
        ProtoContainer.Class classR4 = new ProtoContainer.Class(classR3, nameResolver2, typeTable3, sourceElement3, classR2);
        this.thisAsProtoContainer = classR4;
        if (!Flags.HAS_ANNOTATIONS.get(this.classProto.getFlags()).booleanValue()) {
            annotations2 = Annotations.Companion.getEMPTY();
        } else {
            annotations2 = new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new DeserializedClassDescriptor$annotations$1(this));
        }
        this.annotations = annotations2;
    }

    @NotNull
    public final DeserializationContext getC() {
        return this.c;
    }

    @NotNull
    public final ProtoContainer.Class getThisAsProtoContainer$deserialization() {
        return this.thisAsProtoContainer;
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @NotNull
    public DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @NotNull
    public ClassKind getKind() {
        return this.kind;
    }

    @NotNull
    public Modality getModality() {
        return this.modality;
    }

    @NotNull
    public Visibility getVisibility() {
        return this.visibility;
    }

    public boolean isInner() {
        Boolean bool = Flags.IS_INNER.get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_INNER.get(classProto.flags)");
        return bool.booleanValue();
    }

    public boolean isData() {
        Boolean bool = Flags.IS_DATA.get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_DATA.get(classProto.flags)");
        return bool.booleanValue();
    }

    public boolean isInline() {
        Boolean bool = Flags.IS_INLINE_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_INLINE_CLASS.get(classProto.flags)");
        return bool.booleanValue();
    }

    public boolean isExpect() {
        Boolean bool = Flags.IS_EXPECT_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_EXPECT_CLASS.get(classProto.flags)");
        return bool.booleanValue();
    }

    public boolean isExternal() {
        Boolean bool = Flags.IS_EXTERNAL_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_EXTERNAL_CLASS.get(classProto.flags)");
        return bool.booleanValue();
    }

    @NotNull
    public MemberScope getUnsubstitutedMemberScope() {
        return this.memberScope;
    }

    @NotNull
    public MemberScopeImpl getStaticScope() {
        return this.staticScope;
    }

    public boolean isCompanionObject() {
        return ((Kind) Flags.CLASS_KIND.get(this.classProto.getFlags())) == Kind.COMPANION_OBJECT;
    }

    /* access modifiers changed from: private */
    public final ClassConstructorDescriptor computePrimaryConstructor() {
        ClassConstructorDescriptor classConstructorDescriptor;
        Object obj;
        if (this.kind.isSingleton()) {
            ClassConstructorDescriptorImpl createPrimaryConstructorForObject = DescriptorFactory.createPrimaryConstructorForObject(this, SourceElement.NO_SOURCE);
            createPrimaryConstructorForObject.setReturnType(getDefaultType());
            return createPrimaryConstructorForObject;
        }
        List constructorList = this.classProto.getConstructorList();
        Intrinsics.checkExpressionValueIsNotNull(constructorList, "classProto.constructorList");
        Iterator it = constructorList.iterator();
        while (true) {
            classConstructorDescriptor = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Constructor constructor = (Constructor) obj;
            BooleanFlagField booleanFlagField = Flags.IS_SECONDARY;
            Intrinsics.checkExpressionValueIsNotNull(constructor, "it");
            if (!booleanFlagField.get(constructor.getFlags()).booleanValue()) {
                break;
            }
        }
        Constructor constructor2 = (Constructor) obj;
        if (constructor2 != null) {
            classConstructorDescriptor = this.c.getMemberDeserializer().loadConstructor(constructor2, true);
        }
        return classConstructorDescriptor;
    }

    @Nullable
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return (ClassConstructorDescriptor) this.primaryConstructor.invoke();
    }

    /* access modifiers changed from: private */
    public final Collection<ClassConstructorDescriptor> computeConstructors() {
        return CollectionsKt.plus((Collection<? extends T>) CollectionsKt.plus((Collection<? extends T>) computeSecondaryConstructors(), (Iterable<? extends T>) CollectionsKt.listOfNotNull(getUnsubstitutedPrimaryConstructor())), (Iterable<? extends T>) this.c.getComponents().getAdditionalClassPartsProvider().getConstructors(this));
    }

    private final List<ClassConstructorDescriptor> computeSecondaryConstructors() {
        List constructorList = this.classProto.getConstructorList();
        Intrinsics.checkExpressionValueIsNotNull(constructorList, "classProto.constructorList");
        Iterable iterable = constructorList;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            Constructor constructor = (Constructor) next;
            BooleanFlagField booleanFlagField = Flags.IS_SECONDARY;
            Intrinsics.checkExpressionValueIsNotNull(constructor, "it");
            Boolean bool = booleanFlagField.get(constructor.getFlags());
            Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_SECONDARY.get(it.flags)");
            if (bool.booleanValue()) {
                arrayList.add(next);
            }
        }
        Iterable<Constructor> iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (Constructor constructor2 : iterable2) {
            MemberDeserializer memberDeserializer = this.c.getMemberDeserializer();
            Intrinsics.checkExpressionValueIsNotNull(constructor2, "it");
            arrayList2.add(memberDeserializer.loadConstructor(constructor2, false));
        }
        return (List) arrayList2;
    }

    @NotNull
    public Collection<ClassConstructorDescriptor> getConstructors() {
        return (Collection) this.constructors.invoke();
    }

    /* access modifiers changed from: private */
    public final ClassDescriptor computeCompanionObjectDescriptor() {
        if (!this.classProto.hasCompanionObjectName()) {
            return null;
        }
        ClassifierDescriptor contributedClassifier = this.memberScope.getContributedClassifier(NameResolverUtilKt.getName(this.c.getNameResolver(), this.classProto.getCompanionObjectName()), NoLookupLocation.FROM_DESERIALIZATION);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        return (ClassDescriptor) contributedClassifier;
    }

    @Nullable
    public ClassDescriptor getCompanionObjectDescriptor() {
        return (ClassDescriptor) this.companionObjectDescriptor.invoke();
    }

    public final boolean hasNestedClass$deserialization(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return this.memberScope.getClassNames$deserialization().contains(name);
    }

    /* access modifiers changed from: private */
    public final Collection<ClassDescriptor> computeSubclassesForSealedClass() {
        if (this.modality != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        List sealedSubclassFqNameList = this.classProto.getSealedSubclassFqNameList();
        Intrinsics.checkExpressionValueIsNotNull(sealedSubclassFqNameList, "fqNames");
        if (!(!sealedSubclassFqNameList.isEmpty())) {
            return DescriptorUtilsKt.computeSealedSubclasses(this);
        }
        Iterable<Integer> iterable = sealedSubclassFqNameList;
        Collection arrayList = new ArrayList();
        for (Integer num : iterable) {
            DeserializationComponents components = this.c.getComponents();
            NameResolver nameResolver = this.c.getNameResolver();
            Intrinsics.checkExpressionValueIsNotNull(num, "index");
            ClassDescriptor deserializeClass = components.deserializeClass(NameResolverUtilKt.getClassId(nameResolver, num.intValue()));
            if (deserializeClass != null) {
                arrayList.add(deserializeClass);
            }
        }
        return (List) arrayList;
    }

    @NotNull
    public Collection<ClassDescriptor> getSealedSubclasses() {
        return (Collection) this.sealedSubclasses.invoke();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("deserialized class ");
        sb.append(getName());
        return sb.toString();
    }

    @NotNull
    public SourceElement getSource() {
        return this.sourceElement;
    }

    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return this.c.getTypeDeserializer().getOwnTypeParameters();
    }
}
