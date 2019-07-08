package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider.None;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker.DO_NOTHING;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration.Default;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedClassDataFinder;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer.ThrowException;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInsLoaderImpl.kt */
public final class BuiltInsLoaderImpl implements BuiltInsLoader {
    private final BuiltInsResourceLoader resourceLoader = new BuiltInsResourceLoader();

    @NotNull
    public PackageFragmentProvider createPackageFragmentProvider(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor, @NotNull Iterable<? extends ClassDescriptorFactory> iterable, @NotNull PlatformDependentDeclarationFilter platformDependentDeclarationFilter, @NotNull AdditionalClassPartsProvider additionalClassPartsProvider) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "builtInsModule");
        Intrinsics.checkParameterIsNotNull(iterable, "classDescriptorFactories");
        Intrinsics.checkParameterIsNotNull(platformDependentDeclarationFilter, "platformDependentDeclarationFilter");
        Intrinsics.checkParameterIsNotNull(additionalClassPartsProvider, "additionalClassPartsProvider");
        Set<FqName> set = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAMES;
        Intrinsics.checkExpressionValueIsNotNull(set, "KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAMES");
        return createBuiltInPackageFragmentProvider(storageManager, moduleDescriptor, set, iterable, platformDependentDeclarationFilter, additionalClassPartsProvider, new BuiltInsLoaderImpl$createPackageFragmentProvider$1(this.resourceLoader));
    }

    @NotNull
    public static /* synthetic */ PackageFragmentProvider createBuiltInPackageFragmentProvider$default(BuiltInsLoaderImpl builtInsLoaderImpl, StorageManager storageManager, ModuleDescriptor moduleDescriptor, Set set, Iterable iterable, PlatformDependentDeclarationFilter platformDependentDeclarationFilter, AdditionalClassPartsProvider additionalClassPartsProvider, Function1 function1, int i, Object obj) {
        return builtInsLoaderImpl.createBuiltInPackageFragmentProvider(storageManager, moduleDescriptor, set, iterable, platformDependentDeclarationFilter, (i & 32) != 0 ? None.INSTANCE : additionalClassPartsProvider, function1);
    }

    @NotNull
    public final PackageFragmentProvider createBuiltInPackageFragmentProvider(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor, @NotNull Set<FqName> set, @NotNull Iterable<? extends ClassDescriptorFactory> iterable, @NotNull PlatformDependentDeclarationFilter platformDependentDeclarationFilter, @NotNull AdditionalClassPartsProvider additionalClassPartsProvider, @NotNull Function1<? super String, ? extends InputStream> function1) {
        StorageManager storageManager2 = storageManager;
        ModuleDescriptor moduleDescriptor2 = moduleDescriptor;
        Set<FqName> set2 = set;
        Function1<? super String, ? extends InputStream> function12 = function1;
        Intrinsics.checkParameterIsNotNull(storageManager2, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor2, "module");
        Intrinsics.checkParameterIsNotNull(set2, "packageFqNames");
        Intrinsics.checkParameterIsNotNull(iterable, "classDescriptorFactories");
        Intrinsics.checkParameterIsNotNull(platformDependentDeclarationFilter, "platformDependentDeclarationFilter");
        Intrinsics.checkParameterIsNotNull(additionalClassPartsProvider, "additionalClassPartsProvider");
        Intrinsics.checkParameterIsNotNull(function12, "loadResource");
        Iterable<FqName> iterable2 = set2;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (FqName fqName : iterable2) {
            String builtInsFilePath = BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(fqName);
            InputStream inputStream = (InputStream) function12.invoke(builtInsFilePath);
            if (inputStream != null) {
                arrayList.add(BuiltInsPackageFragmentImpl.Companion.create(fqName, storageManager2, moduleDescriptor2, inputStream));
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Resource not found in classpath: ");
                sb.append(builtInsFilePath);
                throw new IllegalStateException(sb.toString());
            }
        }
        List<BuiltInsPackageFragmentImpl> list = (List) arrayList;
        PackageFragmentProviderImpl packageFragmentProviderImpl = new PackageFragmentProviderImpl(list);
        NotFoundClasses notFoundClasses = r5;
        NotFoundClasses notFoundClasses2 = new NotFoundClasses(storageManager2, moduleDescriptor2);
        DeserializationConfiguration deserializationConfiguration = Default.INSTANCE;
        PackageFragmentProvider packageFragmentProvider = packageFragmentProviderImpl;
        PackageFragmentProvider packageFragmentProvider2 = packageFragmentProvider;
        ClassDataFinder deserializedClassDataFinder = new DeserializedClassDataFinder(packageFragmentProvider);
        AnnotationAndConstantLoader annotationAndConstantLoaderImpl = new AnnotationAndConstantLoaderImpl(moduleDescriptor2, notFoundClasses2, BuiltInSerializerProtocol.INSTANCE);
        LocalClassifierTypeSettings localClassifierTypeSettings = LocalClassifierTypeSettings.Default.INSTANCE;
        ErrorReporter errorReporter = ErrorReporter.DO_NOTHING;
        ErrorReporter errorReporter2 = errorReporter;
        Intrinsics.checkExpressionValueIsNotNull(errorReporter, "ErrorReporter.DO_NOTHING");
        PackageFragmentProvider packageFragmentProvider3 = packageFragmentProvider;
        DeserializationComponents deserializationComponents = r0;
        DeserializationComponents deserializationComponents2 = new DeserializationComponents(storageManager, moduleDescriptor, deserializationConfiguration, deserializedClassDataFinder, annotationAndConstantLoaderImpl, packageFragmentProvider2, localClassifierTypeSettings, errorReporter2, DO_NOTHING.INSTANCE, ThrowException.INSTANCE, iterable, notFoundClasses, ContractDeserializer.Companion.getDEFAULT(), additionalClassPartsProvider, platformDependentDeclarationFilter, BuiltInSerializerProtocol.INSTANCE.getExtensionRegistry());
        for (BuiltInsPackageFragmentImpl initialize : list) {
            initialize.initialize(deserializationComponents);
        }
        return packageFragmentProvider3;
    }
}
