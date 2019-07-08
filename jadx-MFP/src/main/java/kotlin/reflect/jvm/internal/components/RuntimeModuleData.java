package kotlin.reflect.jvm.internal.components;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker.DO_NOTHING;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker.Default;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator.DoNothing;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.load.java.components.SamConversionResolver.Empty;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.SingleModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JavaClassDataFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JavaDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeModuleData;", "", "deserialization", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/DeserializationComponents;", "packagePartProvider", "Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider;", "(Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationComponents;Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider;)V", "getDeserialization", "()Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationComponents;", "module", "Lkotlin/reflect/jvm/internal/impl/descriptors/ModuleDescriptor;", "getModule", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getPackagePartProvider", "()Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider;", "Companion", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: RuntimeModuleData.kt */
public final class RuntimeModuleData {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final DeserializationComponents deserialization;
    @NotNull
    private final RuntimePackagePartProvider packagePartProvider;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeModuleData$Companion;", "", "()V", "create", "Lkotlin/reflect/jvm/internal/components/RuntimeModuleData;", "classLoader", "Ljava/lang/ClassLoader;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
    /* compiled from: RuntimeModuleData.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final RuntimeModuleData create(@NotNull ClassLoader classLoader) {
            ClassLoader classLoader2 = classLoader;
            Intrinsics.checkParameterIsNotNull(classLoader2, "classLoader");
            StorageManager lockBasedStorageManager = new LockBasedStorageManager();
            StorageManager storageManager = lockBasedStorageManager;
            JvmBuiltIns jvmBuiltIns = new JvmBuiltIns(lockBasedStorageManager, false, 2, null);
            StringBuilder sb = new StringBuilder();
            sb.append("<runtime module for ");
            sb.append(classLoader2);
            sb.append(Typography.greater);
            Name special = Name.special(sb.toString());
            Intrinsics.checkExpressionValueIsNotNull(special, "Name.special(\"<runtime module for $classLoader>\")");
            JvmBuiltIns jvmBuiltIns2 = jvmBuiltIns;
            ModuleDescriptorImpl moduleDescriptorImpl = r3;
            ModuleDescriptorImpl moduleDescriptorImpl2 = new ModuleDescriptorImpl(special, lockBasedStorageManager, jvmBuiltIns, null, null, null, 56, null);
            ReflectKotlinClassFinder reflectKotlinClassFinder = new ReflectKotlinClassFinder(classLoader2);
            DeserializedDescriptorResolver deserializedDescriptorResolver = r11;
            DeserializedDescriptorResolver deserializedDescriptorResolver2 = new DeserializedDescriptorResolver();
            SingleModuleClassResolver singleModuleClassResolver = new SingleModuleClassResolver();
            RuntimePackagePartProvider runtimePackagePartProvider = new RuntimePackagePartProvider(classLoader2);
            JavaResolverCache javaResolverCache = JavaResolverCache.EMPTY;
            JavaResolverCache javaResolverCache2 = javaResolverCache;
            ModuleDescriptor moduleDescriptor = moduleDescriptorImpl;
            ModuleDescriptor moduleDescriptor2 = moduleDescriptor;
            NotFoundClasses notFoundClasses = new NotFoundClasses(lockBasedStorageManager, moduleDescriptor);
            AnnotationTypeQualifierResolver annotationTypeQualifierResolver = r6;
            AnnotationTypeQualifierResolver annotationTypeQualifierResolver2 = new AnnotationTypeQualifierResolver(lockBasedStorageManager, Jsr305State.DISABLED);
            JavaClassFinder reflectJavaClassFinder = new ReflectJavaClassFinder(classLoader2);
            KotlinClassFinder kotlinClassFinder = reflectKotlinClassFinder;
            KotlinClassFinder kotlinClassFinder2 = kotlinClassFinder;
            SignaturePropagator signaturePropagator = SignaturePropagator.DO_NOTHING;
            SignaturePropagator signaturePropagator2 = signaturePropagator;
            ModuleDescriptorImpl moduleDescriptorImpl3 = moduleDescriptorImpl;
            Intrinsics.checkExpressionValueIsNotNull(signaturePropagator, "SignaturePropagator.DO_NOTHING");
            ErrorReporter errorReporter = RuntimeErrorReporter.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(javaResolverCache, "javaResolverCache");
            JavaPropertyInitializerEvaluator javaPropertyInitializerEvaluator = DoNothing.INSTANCE;
            SamConversionResolver samConversionResolver = Empty.INSTANCE;
            JavaSourceElementFactory javaSourceElementFactory = RuntimeSourceElementFactory.INSTANCE;
            ModuleClassResolver moduleClassResolver = singleModuleClassResolver;
            PackagePartProvider packagePartProvider = runtimePackagePartProvider;
            SupertypeLoopChecker supertypeLoopChecker = EMPTY.INSTANCE;
            LookupTracker lookupTracker = DO_NOTHING.INSTANCE;
            ReflectionTypes reflectionTypes = r0;
            ReflectionTypes reflectionTypes2 = new ReflectionTypes(moduleDescriptor, notFoundClasses);
            SignatureEnhancement signatureEnhancement = r0;
            SignatureEnhancement signatureEnhancement2 = new SignatureEnhancement(annotationTypeQualifierResolver2, Jsr305State.DISABLED);
            JavaResolverComponents javaResolverComponents = new JavaResolverComponents(storageManager, reflectJavaClassFinder, kotlinClassFinder2, deserializedDescriptorResolver, signaturePropagator2, errorReporter, javaResolverCache2, javaPropertyInitializerEvaluator, samConversionResolver, javaSourceElementFactory, moduleClassResolver, packagePartProvider, supertypeLoopChecker, lookupTracker, moduleDescriptor2, reflectionTypes, annotationTypeQualifierResolver, signatureEnhancement, Default.INSTANCE, JavaResolverSettings.Default.INSTANCE);
            LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider = new LazyJavaPackageFragmentProvider(javaResolverComponents);
            JvmBuiltIns jvmBuiltIns3 = jvmBuiltIns2;
            jvmBuiltIns3.initialize(moduleDescriptor, true);
            JavaDescriptorResolver javaDescriptorResolver = new JavaDescriptorResolver(lazyJavaPackageFragmentProvider, javaResolverCache);
            JavaClassDataFinder javaClassDataFinder = new JavaClassDataFinder(kotlinClassFinder, deserializedDescriptorResolver2);
            BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl = new BinaryClassAnnotationAndConstantLoaderImpl(moduleDescriptor, notFoundClasses, lockBasedStorageManager, kotlinClassFinder);
            StorageManager storageManager2 = lockBasedStorageManager;
            NotFoundClasses notFoundClasses2 = notFoundClasses;
            BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl2 = binaryClassAnnotationAndConstantLoaderImpl;
            RuntimePackagePartProvider runtimePackagePartProvider2 = runtimePackagePartProvider;
            LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider2 = lazyJavaPackageFragmentProvider;
            SingleModuleClassResolver singleModuleClassResolver2 = singleModuleClassResolver;
            NotFoundClasses notFoundClasses3 = notFoundClasses2;
            DeserializedDescriptorResolver deserializedDescriptorResolver3 = deserializedDescriptorResolver2;
            RuntimePackagePartProvider runtimePackagePartProvider3 = runtimePackagePartProvider2;
            DeserializationComponentsForJava deserializationComponentsForJava = r3;
            DeserializationComponentsForJava deserializationComponentsForJava2 = new DeserializationComponentsForJava(storageManager2, moduleDescriptor, DeserializationConfiguration.Default.INSTANCE, javaClassDataFinder, binaryClassAnnotationAndConstantLoaderImpl2, lazyJavaPackageFragmentProvider2, notFoundClasses3, RuntimeErrorReporter.INSTANCE, DO_NOTHING.INSTANCE, ContractDeserializer.Companion.getDEFAULT());
            singleModuleClassResolver2.setResolver(javaDescriptorResolver);
            deserializedDescriptorResolver3.setComponents(deserializationComponentsForJava);
            ModuleDescriptorImpl builtInsModule = jvmBuiltIns3.getBuiltInsModule();
            Intrinsics.checkExpressionValueIsNotNull(builtInsModule, "builtIns.builtInsModule");
            ModuleDescriptorImpl[] moduleDescriptorImplArr = {moduleDescriptorImpl3, builtInsModule};
            ModuleDescriptorImpl moduleDescriptorImpl4 = moduleDescriptorImpl3;
            moduleDescriptorImpl4.setDependencies(moduleDescriptorImplArr);
            moduleDescriptorImpl4.initialize(javaDescriptorResolver.getPackageFragmentProvider());
            return new RuntimeModuleData(deserializationComponentsForJava.getComponents(), runtimePackagePartProvider3, null);
        }
    }

    private RuntimeModuleData(DeserializationComponents deserializationComponents, RuntimePackagePartProvider runtimePackagePartProvider) {
        this.deserialization = deserializationComponents;
        this.packagePartProvider = runtimePackagePartProvider;
    }

    public /* synthetic */ RuntimeModuleData(@NotNull DeserializationComponents deserializationComponents, @NotNull RuntimePackagePartProvider runtimePackagePartProvider, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationComponents, runtimePackagePartProvider);
    }

    @NotNull
    public final DeserializationComponents getDeserialization() {
        return this.deserialization;
    }

    @NotNull
    public final RuntimePackagePartProvider getPackagePartProvider() {
        return this.packagePartProvider;
    }

    @NotNull
    public final ModuleDescriptor getModule() {
        return this.deserialization.getModuleDescriptor();
    }
}
