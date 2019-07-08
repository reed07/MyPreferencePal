package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: context.kt */
public final class DeserializationComponents {
    @NotNull
    private final AdditionalClassPartsProvider additionalClassPartsProvider;
    @NotNull
    private final AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> annotationAndConstantLoader;
    @NotNull
    private final ClassDataFinder classDataFinder;
    @NotNull
    private final ClassDeserializer classDeserializer = new ClassDeserializer(this);
    @NotNull
    private final DeserializationConfiguration configuration;
    @NotNull
    private final ContractDeserializer contractDeserializer;
    @NotNull
    private final ErrorReporter errorReporter;
    @NotNull
    private final ExtensionRegistryLite extensionRegistryLite;
    @NotNull
    private final Iterable<ClassDescriptorFactory> fictitiousClassDescriptorFactories;
    @NotNull
    private final FlexibleTypeDeserializer flexibleTypeDeserializer;
    @NotNull
    private final LocalClassifierTypeSettings localClassifierTypeSettings;
    @NotNull
    private final LookupTracker lookupTracker;
    @NotNull
    private final ModuleDescriptor moduleDescriptor;
    @NotNull
    private final NotFoundClasses notFoundClasses;
    @NotNull
    private final PackageFragmentProvider packageFragmentProvider;
    @NotNull
    private final PlatformDependentDeclarationFilter platformDependentDeclarationFilter;
    @NotNull
    private final StorageManager storageManager;

    public DeserializationComponents(@NotNull StorageManager storageManager2, @NotNull ModuleDescriptor moduleDescriptor2, @NotNull DeserializationConfiguration deserializationConfiguration, @NotNull ClassDataFinder classDataFinder2, @NotNull AnnotationAndConstantLoader<? extends AnnotationDescriptor, ? extends ConstantValue<?>> annotationAndConstantLoader2, @NotNull PackageFragmentProvider packageFragmentProvider2, @NotNull LocalClassifierTypeSettings localClassifierTypeSettings2, @NotNull ErrorReporter errorReporter2, @NotNull LookupTracker lookupTracker2, @NotNull FlexibleTypeDeserializer flexibleTypeDeserializer2, @NotNull Iterable<? extends ClassDescriptorFactory> iterable, @NotNull NotFoundClasses notFoundClasses2, @NotNull ContractDeserializer contractDeserializer2, @NotNull AdditionalClassPartsProvider additionalClassPartsProvider2, @NotNull PlatformDependentDeclarationFilter platformDependentDeclarationFilter2, @NotNull ExtensionRegistryLite extensionRegistryLite2) {
        StorageManager storageManager3 = storageManager2;
        ModuleDescriptor moduleDescriptor3 = moduleDescriptor2;
        DeserializationConfiguration deserializationConfiguration2 = deserializationConfiguration;
        ClassDataFinder classDataFinder3 = classDataFinder2;
        AnnotationAndConstantLoader<? extends AnnotationDescriptor, ? extends ConstantValue<?>> annotationAndConstantLoader3 = annotationAndConstantLoader2;
        PackageFragmentProvider packageFragmentProvider3 = packageFragmentProvider2;
        LocalClassifierTypeSettings localClassifierTypeSettings3 = localClassifierTypeSettings2;
        ErrorReporter errorReporter3 = errorReporter2;
        LookupTracker lookupTracker3 = lookupTracker2;
        FlexibleTypeDeserializer flexibleTypeDeserializer3 = flexibleTypeDeserializer2;
        Iterable<? extends ClassDescriptorFactory> iterable2 = iterable;
        NotFoundClasses notFoundClasses3 = notFoundClasses2;
        ContractDeserializer contractDeserializer3 = contractDeserializer2;
        AdditionalClassPartsProvider additionalClassPartsProvider3 = additionalClassPartsProvider2;
        PlatformDependentDeclarationFilter platformDependentDeclarationFilter3 = platformDependentDeclarationFilter2;
        Intrinsics.checkParameterIsNotNull(storageManager3, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor3, "moduleDescriptor");
        Intrinsics.checkParameterIsNotNull(deserializationConfiguration2, "configuration");
        Intrinsics.checkParameterIsNotNull(classDataFinder3, "classDataFinder");
        Intrinsics.checkParameterIsNotNull(annotationAndConstantLoader3, "annotationAndConstantLoader");
        Intrinsics.checkParameterIsNotNull(packageFragmentProvider3, "packageFragmentProvider");
        Intrinsics.checkParameterIsNotNull(localClassifierTypeSettings3, "localClassifierTypeSettings");
        Intrinsics.checkParameterIsNotNull(errorReporter3, "errorReporter");
        Intrinsics.checkParameterIsNotNull(lookupTracker3, "lookupTracker");
        Intrinsics.checkParameterIsNotNull(flexibleTypeDeserializer3, "flexibleTypeDeserializer");
        Intrinsics.checkParameterIsNotNull(iterable2, "fictitiousClassDescriptorFactories");
        Intrinsics.checkParameterIsNotNull(notFoundClasses3, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(contractDeserializer3, "contractDeserializer");
        Intrinsics.checkParameterIsNotNull(additionalClassPartsProvider3, "additionalClassPartsProvider");
        Intrinsics.checkParameterIsNotNull(platformDependentDeclarationFilter3, "platformDependentDeclarationFilter");
        ExtensionRegistryLite extensionRegistryLite3 = extensionRegistryLite2;
        Intrinsics.checkParameterIsNotNull(extensionRegistryLite3, "extensionRegistryLite");
        this.storageManager = storageManager3;
        this.moduleDescriptor = moduleDescriptor3;
        this.configuration = deserializationConfiguration2;
        this.classDataFinder = classDataFinder3;
        this.annotationAndConstantLoader = annotationAndConstantLoader3;
        this.packageFragmentProvider = packageFragmentProvider3;
        this.localClassifierTypeSettings = localClassifierTypeSettings3;
        this.errorReporter = errorReporter3;
        this.lookupTracker = lookupTracker3;
        this.flexibleTypeDeserializer = flexibleTypeDeserializer3;
        this.fictitiousClassDescriptorFactories = iterable2;
        this.notFoundClasses = notFoundClasses3;
        this.contractDeserializer = contractDeserializer3;
        this.additionalClassPartsProvider = additionalClassPartsProvider3;
        this.platformDependentDeclarationFilter = platformDependentDeclarationFilter2;
        this.extensionRegistryLite = extensionRegistryLite3;
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    @NotNull
    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    @NotNull
    public final DeserializationConfiguration getConfiguration() {
        return this.configuration;
    }

    @NotNull
    public final ClassDataFinder getClassDataFinder() {
        return this.classDataFinder;
    }

    @NotNull
    public final AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> getAnnotationAndConstantLoader() {
        return this.annotationAndConstantLoader;
    }

    @NotNull
    public final PackageFragmentProvider getPackageFragmentProvider() {
        return this.packageFragmentProvider;
    }

    @NotNull
    public final LocalClassifierTypeSettings getLocalClassifierTypeSettings() {
        return this.localClassifierTypeSettings;
    }

    @NotNull
    public final ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }

    @NotNull
    public final LookupTracker getLookupTracker() {
        return this.lookupTracker;
    }

    @NotNull
    public final FlexibleTypeDeserializer getFlexibleTypeDeserializer() {
        return this.flexibleTypeDeserializer;
    }

    @NotNull
    public final Iterable<ClassDescriptorFactory> getFictitiousClassDescriptorFactories() {
        return this.fictitiousClassDescriptorFactories;
    }

    @NotNull
    public final NotFoundClasses getNotFoundClasses() {
        return this.notFoundClasses;
    }

    @NotNull
    public final ContractDeserializer getContractDeserializer() {
        return this.contractDeserializer;
    }

    @NotNull
    public final AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return this.additionalClassPartsProvider;
    }

    @NotNull
    public final PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return this.platformDependentDeclarationFilter;
    }

    @NotNull
    public final ExtensionRegistryLite getExtensionRegistryLite() {
        return this.extensionRegistryLite;
    }

    @NotNull
    public final ClassDeserializer getClassDeserializer() {
        return this.classDeserializer;
    }

    @Nullable
    public final ClassDescriptor deserializeClass(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        return ClassDeserializer.deserializeClass$default(this.classDeserializer, classId, null, 2, null);
    }

    @NotNull
    public final DeserializationContext createContext(@NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @NotNull VersionRequirementTable versionRequirementTable, @NotNull BinaryVersion binaryVersion, @Nullable DeserializedContainerSource deserializedContainerSource) {
        PackageFragmentDescriptor packageFragmentDescriptor2 = packageFragmentDescriptor;
        Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor, "descriptor");
        NameResolver nameResolver2 = nameResolver;
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        TypeTable typeTable2 = typeTable;
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        VersionRequirementTable versionRequirementTable2 = versionRequirementTable;
        Intrinsics.checkParameterIsNotNull(versionRequirementTable2, "versionRequirementTable");
        BinaryVersion binaryVersion2 = binaryVersion;
        Intrinsics.checkParameterIsNotNull(binaryVersion2, "metadataVersion");
        DeserializationContext deserializationContext = new DeserializationContext(this, nameResolver2, packageFragmentDescriptor2, typeTable2, versionRequirementTable2, binaryVersion2, deserializedContainerSource, null, CollectionsKt.emptyList());
        return deserializationContext;
    }
}
