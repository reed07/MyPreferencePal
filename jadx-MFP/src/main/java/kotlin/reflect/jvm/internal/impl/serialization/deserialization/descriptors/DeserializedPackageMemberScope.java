package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedPackageMemberScope.kt */
public class DeserializedPackageMemberScope extends DeserializedMemberScope {
    private final PackageFragmentDescriptor packageDescriptor;
    private final FqName packageFqName = this.packageDescriptor.getFqName();

    /* access modifiers changed from: protected */
    public void addEnumEntryDescriptors(@NotNull Collection<DeclarationDescriptor> collection, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(collection, "result");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
    }

    public DeserializedPackageMemberScope(@NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull Package packageR, @NotNull NameResolver nameResolver, @NotNull BinaryVersion binaryVersion, @Nullable DeserializedContainerSource deserializedContainerSource, @NotNull DeserializationComponents deserializationComponents, @NotNull Function0<? extends Collection<Name>> function0) {
        PackageFragmentDescriptor packageFragmentDescriptor2 = packageFragmentDescriptor;
        Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor2, "packageDescriptor");
        Intrinsics.checkParameterIsNotNull(packageR, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(deserializationComponents, "components");
        Function0<? extends Collection<Name>> function02 = function0;
        Intrinsics.checkParameterIsNotNull(function02, "classNames");
        TypeTable typeTable = packageR.getTypeTable();
        Intrinsics.checkExpressionValueIsNotNull(typeTable, "proto.typeTable");
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable typeTable2 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(typeTable);
        Companion companion = VersionRequirementTable.Companion;
        ProtoBuf.VersionRequirementTable versionRequirementTable = packageR.getVersionRequirementTable();
        Intrinsics.checkExpressionValueIsNotNull(versionRequirementTable, "proto.versionRequirementTable");
        DeserializationContext createContext = deserializationComponents.createContext(packageFragmentDescriptor, nameResolver, typeTable2, companion.create(versionRequirementTable), binaryVersion, deserializedContainerSource);
        List functionList = packageR.getFunctionList();
        Intrinsics.checkExpressionValueIsNotNull(functionList, "proto.functionList");
        Collection collection = functionList;
        List propertyList = packageR.getPropertyList();
        Intrinsics.checkExpressionValueIsNotNull(propertyList, "proto.propertyList");
        Collection collection2 = propertyList;
        List typeAliasList = packageR.getTypeAliasList();
        Intrinsics.checkExpressionValueIsNotNull(typeAliasList, "proto.typeAliasList");
        super(createContext, collection, collection2, typeAliasList, function02);
        this.packageDescriptor = packageFragmentDescriptor2;
    }

    @NotNull
    public List<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        Collection computeDescriptors = computeDescriptors(descriptorKindFilter, function1, NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
        Iterable<ClassDescriptorFactory> fictitiousClassDescriptorFactories = getC().getComponents().getFictitiousClassDescriptorFactories();
        Collection arrayList = new ArrayList();
        for (ClassDescriptorFactory allContributedClassesIfPossible : fictitiousClassDescriptorFactories) {
            CollectionsKt.addAll(arrayList, (Iterable<? extends T>) allContributedClassesIfPossible.getAllContributedClassesIfPossible(this.packageFqName));
        }
        return CollectionsKt.plus(computeDescriptors, (Iterable<? extends T>) (List) arrayList);
    }

    /* access modifiers changed from: protected */
    public boolean hasClass(@NotNull Name name) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (super.hasClass(name)) {
            return true;
        }
        Iterable fictitiousClassDescriptorFactories = getC().getComponents().getFictitiousClassDescriptorFactories();
        if (!(fictitiousClassDescriptorFactories instanceof Collection) || !((Collection) fictitiousClassDescriptorFactories).isEmpty()) {
            Iterator it = fictitiousClassDescriptorFactories.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((ClassDescriptorFactory) it.next()).shouldCreateClass(this.packageFqName, name)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ClassId createClassId(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new ClassId(this.packageFqName, name);
    }

    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        recordLookup(name, lookupLocation);
        return super.getContributedClassifier(name, lookupLocation);
    }

    public void recordLookup(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        UtilsKt.record(getC().getComponents().getLookupTracker(), lookupLocation, this.packageDescriptor, name);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> getNonDeclaredFunctionNames() {
        return SetsKt.emptySet();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> getNonDeclaredVariableNames() {
        return SetsKt.emptySet();
    }
}
