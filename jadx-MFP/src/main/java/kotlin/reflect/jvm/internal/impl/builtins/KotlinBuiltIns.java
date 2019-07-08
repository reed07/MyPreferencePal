package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider.None;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.NoPlatformDependent;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class KotlinBuiltIns {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    /* access modifiers changed from: private */
    public static final FqName ANNOTATION_PACKAGE_FQ_NAME = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("annotation"));
    public static final Name BUILTINS_MODULE_NAME = Name.special("<built-ins module>");
    public static final FqName BUILT_INS_PACKAGE_FQ_NAME = FqName.topLevel(BUILT_INS_PACKAGE_NAME);
    public static final Set<FqName> BUILT_INS_PACKAGE_FQ_NAMES = SetsKt.setOf(BUILT_INS_PACKAGE_FQ_NAME, COLLECTIONS_PACKAGE_FQ_NAME, RANGES_PACKAGE_FQ_NAME, ANNOTATION_PACKAGE_FQ_NAME, ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("internal")), DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE);
    public static final Name BUILT_INS_PACKAGE_NAME = Name.identifier("kotlin");
    public static final FqName COLLECTIONS_PACKAGE_FQ_NAME = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("collections"));
    public static final FqNames FQ_NAMES = new FqNames();
    public static final FqName RANGES_PACKAGE_FQ_NAME = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("ranges"));
    public static final FqName TEXT_PACKAGE_FQ_NAME = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("text"));
    private final MemoizedFunctionToNotNull<Name, ClassDescriptor> builtInClassesByName;
    /* access modifiers changed from: private */
    public ModuleDescriptorImpl builtInsModule;
    private final NotNullLazyValue<PackageFragments> packageFragments;
    private final NotNullLazyValue<Primitives> primitives;
    private final StorageManager storageManager;

    public static class FqNames {
        public final FqNameUnsafe _boolean = fqNameUnsafe("Boolean");
        public final FqNameUnsafe _byte = fqNameUnsafe("Byte");
        public final FqNameUnsafe _char = fqNameUnsafe("Char");
        public final FqNameUnsafe _double = fqNameUnsafe("Double");
        public final FqNameUnsafe _enum = fqNameUnsafe("Enum");
        public final FqNameUnsafe _float = fqNameUnsafe("Float");
        public final FqNameUnsafe _int = fqNameUnsafe("Int");
        public final FqNameUnsafe _long = fqNameUnsafe("Long");
        public final FqNameUnsafe _short = fqNameUnsafe("Short");
        public final FqName annotation = fqName("Annotation");
        public final FqName annotationRetention = annotationName("AnnotationRetention");
        public final FqName annotationTarget = annotationName("AnnotationTarget");
        public final FqNameUnsafe any = fqNameUnsafe("Any");
        public final FqNameUnsafe array = fqNameUnsafe("Array");
        public final Map<FqNameUnsafe, PrimitiveType> arrayClassFqNameToPrimitiveType = CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
        public final FqNameUnsafe charRange = rangesFqName("CharRange");
        public final FqNameUnsafe charSequence = fqNameUnsafe("CharSequence");
        public final FqNameUnsafe cloneable = fqNameUnsafe("Cloneable");
        public final FqName collection = collectionsFqName("Collection");
        public final FqName comparable = fqName("Comparable");
        public final FqName deprecated = fqName("Deprecated");
        public final FqName deprecationLevel = fqName("DeprecationLevel");
        public final FqName extensionFunctionType = fqName("ExtensionFunctionType");
        public final Map<FqNameUnsafe, PrimitiveType> fqNameToPrimitiveType = CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
        public final FqNameUnsafe functionSupertype = fqNameUnsafe("Function");
        public final FqNameUnsafe intRange = rangesFqName("IntRange");
        public final FqName iterable = collectionsFqName("Iterable");
        public final FqName iterator = collectionsFqName("Iterator");
        public final FqNameUnsafe kCallable = reflect("KCallable");
        public final FqNameUnsafe kClass = reflect("KClass");
        public final FqNameUnsafe kMutableProperty0 = reflect("KMutableProperty0");
        public final FqNameUnsafe kMutableProperty1 = reflect("KMutableProperty1");
        public final FqNameUnsafe kMutableProperty2 = reflect("KMutableProperty2");
        public final ClassId kProperty = ClassId.topLevel(reflect("KProperty").toSafe());
        public final FqNameUnsafe kProperty0 = reflect("KProperty0");
        public final FqNameUnsafe kProperty1 = reflect("KProperty1");
        public final FqNameUnsafe kProperty2 = reflect("KProperty2");
        public final FqName list = collectionsFqName("List");
        public final FqName listIterator = collectionsFqName("ListIterator");
        public final FqNameUnsafe longRange = rangesFqName("LongRange");
        public final FqName map = collectionsFqName("Map");
        public final FqName mapEntry = this.map.child(Name.identifier("Entry"));
        public final FqName mustBeDocumented = annotationName("MustBeDocumented");
        public final FqName mutableCollection = collectionsFqName("MutableCollection");
        public final FqName mutableIterable = collectionsFqName("MutableIterable");
        public final FqName mutableIterator = collectionsFqName("MutableIterator");
        public final FqName mutableList = collectionsFqName("MutableList");
        public final FqName mutableListIterator = collectionsFqName("MutableListIterator");
        public final FqName mutableMap = collectionsFqName("MutableMap");
        public final FqName mutableMapEntry = this.mutableMap.child(Name.identifier("MutableEntry"));
        public final FqName mutableSet = collectionsFqName("MutableSet");
        public final FqNameUnsafe nothing = fqNameUnsafe("Nothing");
        public final FqNameUnsafe number = fqNameUnsafe("Number");
        public final FqName parameterName = fqName("ParameterName");
        public final Set<Name> primitiveArrayTypeShortNames = CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
        public final Set<Name> primitiveTypeShortNames = CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
        public final FqName publishedApi = fqName("PublishedApi");
        public final FqName repeatable = annotationName("Repeatable");
        public final FqName replaceWith = fqName("ReplaceWith");
        public final FqName retention = annotationName("Retention");
        public final FqName set = collectionsFqName("Set");
        public final FqNameUnsafe string = fqNameUnsafe("String");
        public final FqName suppress = fqName("Suppress");
        public final FqName target = annotationName("Target");
        public final FqName throwable = fqName("Throwable");
        public final ClassId uByte = ClassId.topLevel(this.uByteFqName);
        public final FqName uByteFqName = fqName("UByte");
        public final ClassId uInt = ClassId.topLevel(this.uIntFqName);
        public final FqName uIntFqName = fqName("UInt");
        public final ClassId uLong = ClassId.topLevel(this.uLongFqName);
        public final FqName uLongFqName = fqName("ULong");
        public final ClassId uShort = ClassId.topLevel(this.uShortFqName);
        public final FqName uShortFqName = fqName("UShort");
        public final FqNameUnsafe unit = fqNameUnsafe("Unit");
        public final FqName unsafeVariance = fqName("UnsafeVariance");

        public FqNames() {
            PrimitiveType[] values;
            for (PrimitiveType primitiveType : PrimitiveType.values()) {
                this.primitiveTypeShortNames.add(primitiveType.getTypeName());
                this.primitiveArrayTypeShortNames.add(primitiveType.getArrayTypeName());
                this.fqNameToPrimitiveType.put(fqNameUnsafe(primitiveType.getTypeName().asString()), primitiveType);
                this.arrayClassFqNameToPrimitiveType.put(fqNameUnsafe(primitiveType.getArrayTypeName().asString()), primitiveType);
            }
        }

        @NotNull
        private static FqNameUnsafe fqNameUnsafe(@NotNull String str) {
            return fqName(str).toUnsafe();
        }

        @NotNull
        private static FqName fqName(@NotNull String str) {
            return KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }

        @NotNull
        private static FqName collectionsFqName(@NotNull String str) {
            return KotlinBuiltIns.COLLECTIONS_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }

        @NotNull
        private static FqNameUnsafe rangesFqName(@NotNull String str) {
            return KotlinBuiltIns.RANGES_PACKAGE_FQ_NAME.child(Name.identifier(str)).toUnsafe();
        }

        @NotNull
        private static FqNameUnsafe reflect(@NotNull String str) {
            return ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME().child(Name.identifier(str)).toUnsafe();
        }

        @NotNull
        private static FqName annotationName(@NotNull String str) {
            return KotlinBuiltIns.ANNOTATION_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }
    }

    private static class PackageFragments {
        public final Set<PackageFragmentDescriptor> allImportedByDefaultBuiltInsPackageFragments;
        public final PackageFragmentDescriptor annotationPackageFragment;
        public final PackageFragmentDescriptor builtInsPackageFragment;
        public final PackageFragmentDescriptor collectionsPackageFragment;

        private PackageFragments(@NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull PackageFragmentDescriptor packageFragmentDescriptor2, @NotNull PackageFragmentDescriptor packageFragmentDescriptor3, @NotNull Set<PackageFragmentDescriptor> set) {
            this.builtInsPackageFragment = packageFragmentDescriptor;
            this.collectionsPackageFragment = packageFragmentDescriptor2;
            this.annotationPackageFragment = packageFragmentDescriptor3;
            this.allImportedByDefaultBuiltInsPackageFragments = set;
        }
    }

    private static class Primitives {
        public final Map<SimpleType, SimpleType> kotlinArrayTypeToPrimitiveKotlinType;
        public final Map<KotlinType, SimpleType> primitiveKotlinTypeToKotlinArrayType;
        public final Map<PrimitiveType, SimpleType> primitiveTypeToArrayKotlinType;

        private Primitives(@NotNull Map<PrimitiveType, SimpleType> map, @NotNull Map<KotlinType, SimpleType> map2, @NotNull Map<SimpleType, SimpleType> map3) {
            this.primitiveTypeToArrayKotlinType = map;
            this.primitiveKotlinTypeToKotlinArrayType = map2;
            this.kotlinArrayTypeToPrimitiveKotlinType = map3;
        }
    }

    protected KotlinBuiltIns(@NotNull StorageManager storageManager2) {
        this.storageManager = storageManager2;
        this.packageFragments = storageManager2.createLazyValue(new Function0<PackageFragments>() {
            public PackageFragments invoke() {
                PackageFragmentProvider packageFragmentProvider = KotlinBuiltIns.this.builtInsModule.getPackageFragmentProvider();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                PackageFragmentDescriptor access$100 = KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME);
                KotlinBuiltIns.this.createPackage(packageFragmentProvider, null, DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE);
                PackageFragmentDescriptor access$1002 = KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.COLLECTIONS_PACKAGE_FQ_NAME);
                KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.RANGES_PACKAGE_FQ_NAME);
                PackageFragments packageFragments = new PackageFragments(access$100, access$1002, KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.ANNOTATION_PACKAGE_FQ_NAME), new LinkedHashSet(linkedHashMap.values()));
                return packageFragments;
            }
        });
        this.primitives = storageManager2.createLazyValue(new Function0<Primitives>() {
            public Primitives invoke() {
                PrimitiveType[] values;
                EnumMap enumMap = new EnumMap(PrimitiveType.class);
                HashMap hashMap = new HashMap();
                HashMap hashMap2 = new HashMap();
                for (PrimitiveType primitiveType : PrimitiveType.values()) {
                    SimpleType access$400 = KotlinBuiltIns.this.getBuiltInTypeByClassName(primitiveType.getTypeName().asString());
                    SimpleType access$4002 = KotlinBuiltIns.this.getBuiltInTypeByClassName(primitiveType.getArrayTypeName().asString());
                    enumMap.put(primitiveType, access$4002);
                    hashMap.put(access$400, access$4002);
                    hashMap2.put(access$4002, access$400);
                }
                return new Primitives(enumMap, hashMap, hashMap2);
            }
        });
        this.builtInClassesByName = storageManager2.createMemoizedFunction(new Function1<Name, ClassDescriptor>() {
            public ClassDescriptor invoke(Name name) {
                return KotlinBuiltIns.getBuiltInClassByName(name, KotlinBuiltIns.this.getBuiltInsPackageFragment());
            }
        });
    }

    /* access modifiers changed from: protected */
    public void createBuiltInsModule() {
        this.builtInsModule = new ModuleDescriptorImpl(BUILTINS_MODULE_NAME, this.storageManager, this, null);
        this.builtInsModule.initialize(BuiltInsLoader.Companion.getInstance().createPackageFragmentProvider(this.storageManager, this.builtInsModule, getClassDescriptorFactories(), getPlatformDependentDeclarationFilter(), getAdditionalClassPartsProvider()));
        ModuleDescriptorImpl moduleDescriptorImpl = this.builtInsModule;
        moduleDescriptorImpl.setDependencies(moduleDescriptorImpl);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return None.INSTANCE;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return NoPlatformDependent.INSTANCE;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Iterable<ClassDescriptorFactory> getClassDescriptorFactories() {
        return Collections.singletonList(new BuiltInFictitiousFunctionClassFactory(this.storageManager, this.builtInsModule));
    }

    /* JADX WARNING: type inference failed for: r7v7, types: [kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor] */
    /* JADX WARNING: type inference failed for: r7v8, types: [kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor createPackage(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider r7, @org.jetbrains.annotations.Nullable java.util.Map<kotlin.reflect.jvm.internal.impl.name.FqName, kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor> r8, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.FqName r9) {
        /*
            r6 = this;
            java.util.List r5 = r7.getPackageFragments(r9)
            boolean r7 = r5.isEmpty()
            if (r7 == 0) goto L_0x0012
            kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor r7 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor
            kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl r0 = r6.builtInsModule
            r7.<init>(r0, r9)
            goto L_0x002f
        L_0x0012:
            int r7 = r5.size()
            r0 = 1
            if (r7 != r0) goto L_0x0024
            java.util.Iterator r7 = r5.iterator()
            java.lang.Object r7 = r7.next()
            kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor r7 = (kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor) r7
            goto L_0x002f
        L_0x0024:
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns$5 r7 = new kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns$5
            kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl r2 = r6.builtInsModule
            r0 = r7
            r1 = r6
            r3 = r9
            r4 = r9
            r0.<init>(r2, r3, r4, r5)
        L_0x002f:
            if (r8 == 0) goto L_0x0034
            r8.put(r9, r7)
        L_0x0034:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.createPackage(kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider, java.util.Map, kotlin.reflect.jvm.internal.impl.name.FqName):kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public StorageManager getStorageManager() {
        return this.storageManager;
    }

    @NotNull
    public ModuleDescriptorImpl getBuiltInsModule() {
        return this.builtInsModule;
    }

    @NotNull
    public PackageFragmentDescriptor getBuiltInsPackageFragment() {
        return ((PackageFragments) this.packageFragments.invoke()).builtInsPackageFragment;
    }

    public static boolean isBuiltIn(@NotNull DeclarationDescriptor declarationDescriptor) {
        return DescriptorUtils.getParentOfType(declarationDescriptor, BuiltInsPackageFragment.class, false) != null;
    }

    public static boolean isUnderKotlinPackage(@NotNull DeclarationDescriptor declarationDescriptor) {
        while (declarationDescriptor != null) {
            if (declarationDescriptor instanceof PackageFragmentDescriptor) {
                return ((PackageFragmentDescriptor) declarationDescriptor).getFqName().startsWith(BUILT_INS_PACKAGE_NAME);
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return false;
    }

    @NotNull
    public ClassDescriptor getBuiltInClassByName(@NotNull Name name) {
        return (ClassDescriptor) this.builtInClassesByName.invoke(name);
    }

    /* access modifiers changed from: private */
    @NotNull
    public static ClassDescriptor getBuiltInClassByName(@NotNull Name name, @NotNull PackageFragmentDescriptor packageFragmentDescriptor) {
        ClassDescriptor builtInClassByNameNullable = getBuiltInClassByNameNullable(name, packageFragmentDescriptor);
        if (builtInClassByNameNullable != null) {
            return builtInClassByNameNullable;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Built-in class ");
        sb.append(packageFragmentDescriptor.getFqName().child(name).asString());
        sb.append(" is not found");
        throw new AssertionError(sb.toString());
    }

    @Nullable
    public ClassDescriptor getBuiltInClassByFqNameNullable(@NotNull FqName fqName) {
        return DescriptorUtilKt.resolveClassByFqName(this.builtInsModule, fqName, NoLookupLocation.FROM_BUILTINS);
    }

    @NotNull
    public ClassDescriptor getBuiltInClassByFqName(@NotNull FqName fqName) {
        return getBuiltInClassByFqNameNullable(fqName);
    }

    @Nullable
    private static ClassDescriptor getBuiltInClassByNameNullable(@NotNull Name name, @NotNull PackageFragmentDescriptor packageFragmentDescriptor) {
        return (ClassDescriptor) packageFragmentDescriptor.getMemberScope().getContributedClassifier(name, NoLookupLocation.FROM_BUILTINS);
    }

    @NotNull
    private ClassDescriptor getBuiltInClassByName(@NotNull String str) {
        return getBuiltInClassByName(Name.identifier(str));
    }

    @NotNull
    private static ClassDescriptor getBuiltInClassByName(@NotNull String str, PackageFragmentDescriptor packageFragmentDescriptor) {
        return getBuiltInClassByName(Name.identifier(str), packageFragmentDescriptor);
    }

    @NotNull
    public ClassDescriptor getAny() {
        return getBuiltInClassByName("Any");
    }

    @NotNull
    public ClassDescriptor getNothing() {
        return getBuiltInClassByName("Nothing");
    }

    @NotNull
    private ClassDescriptor getPrimitiveClassDescriptor(@NotNull PrimitiveType primitiveType) {
        return getBuiltInClassByName(primitiveType.getTypeName().asString());
    }

    @NotNull
    public ClassDescriptor getArray() {
        return getBuiltInClassByName("Array");
    }

    @NotNull
    public ClassDescriptor getNumber() {
        return getBuiltInClassByName("Number");
    }

    @NotNull
    public ClassDescriptor getUnit() {
        return getBuiltInClassByName("Unit");
    }

    @NotNull
    public static String getFunctionName(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Function");
        sb.append(i);
        return sb.toString();
    }

    @NotNull
    public static ClassId getFunctionClassId(int i) {
        return new ClassId(BUILT_INS_PACKAGE_FQ_NAME, Name.identifier(getFunctionName(i)));
    }

    @NotNull
    public ClassDescriptor getFunction(int i) {
        return getBuiltInClassByName(getFunctionName(i));
    }

    @NotNull
    public ClassDescriptor getSuspendFunction(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(Kind.SuspendFunction.getClassNamePrefix());
        sb.append(i);
        return getBuiltInClassByFqName(DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier(sb.toString())));
    }

    @NotNull
    public ClassDescriptor getString() {
        return getBuiltInClassByName("String");
    }

    @NotNull
    private ClassDescriptor getCollectionClassByName(@NotNull String str) {
        return getBuiltInClassByName(str, ((PackageFragments) this.packageFragments.invoke()).collectionsPackageFragment);
    }

    @NotNull
    public ClassDescriptor getCollection() {
        return getCollectionClassByName("Collection");
    }

    /* access modifiers changed from: private */
    @NotNull
    public SimpleType getBuiltInTypeByClassName(@NotNull String str) {
        return getBuiltInClassByName(str).getDefaultType();
    }

    @NotNull
    public SimpleType getNothingType() {
        return getNothing().getDefaultType();
    }

    @NotNull
    public SimpleType getNullableNothingType() {
        return getNothingType().makeNullableAsSpecified(true);
    }

    @NotNull
    public SimpleType getAnyType() {
        return getAny().getDefaultType();
    }

    @NotNull
    public SimpleType getNullableAnyType() {
        return getAnyType().makeNullableAsSpecified(true);
    }

    @NotNull
    public SimpleType getDefaultBound() {
        return getNullableAnyType();
    }

    @NotNull
    public SimpleType getPrimitiveKotlinType(@NotNull PrimitiveType primitiveType) {
        return getPrimitiveClassDescriptor(primitiveType).getDefaultType();
    }

    @NotNull
    public SimpleType getByteType() {
        return getPrimitiveKotlinType(PrimitiveType.BYTE);
    }

    @NotNull
    public SimpleType getShortType() {
        return getPrimitiveKotlinType(PrimitiveType.SHORT);
    }

    @NotNull
    public SimpleType getIntType() {
        return getPrimitiveKotlinType(PrimitiveType.INT);
    }

    @NotNull
    public SimpleType getLongType() {
        return getPrimitiveKotlinType(PrimitiveType.LONG);
    }

    @NotNull
    public SimpleType getFloatType() {
        return getPrimitiveKotlinType(PrimitiveType.FLOAT);
    }

    @NotNull
    public SimpleType getDoubleType() {
        return getPrimitiveKotlinType(PrimitiveType.DOUBLE);
    }

    @NotNull
    public SimpleType getCharType() {
        return getPrimitiveKotlinType(PrimitiveType.CHAR);
    }

    @NotNull
    public SimpleType getBooleanType() {
        return getPrimitiveKotlinType(PrimitiveType.BOOLEAN);
    }

    @NotNull
    public SimpleType getUnitType() {
        return getUnit().getDefaultType();
    }

    @NotNull
    public SimpleType getStringType() {
        return getString().getDefaultType();
    }

    @NotNull
    public KotlinType getArrayElementType(@NotNull KotlinType kotlinType) {
        if (!isArray(kotlinType)) {
            KotlinType makeNotNullable = TypeUtils.makeNotNullable(kotlinType);
            KotlinType kotlinType2 = (KotlinType) ((Primitives) this.primitives.invoke()).kotlinArrayTypeToPrimitiveKotlinType.get(makeNotNullable);
            if (kotlinType2 != null) {
                return kotlinType2;
            }
            ModuleDescriptor containingModuleOrNull = DescriptorUtils.getContainingModuleOrNull(makeNotNullable);
            if (containingModuleOrNull != null) {
                KotlinType elementTypeForUnsignedArray = getElementTypeForUnsignedArray(makeNotNullable, containingModuleOrNull);
                if (elementTypeForUnsignedArray != null) {
                    return elementTypeForUnsignedArray;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("not array: ");
            sb.append(kotlinType);
            throw new IllegalStateException(sb.toString());
        } else if (kotlinType.getArguments().size() == 1) {
            return ((TypeProjection) kotlinType.getArguments().get(0)).getType();
        } else {
            throw new IllegalStateException();
        }
    }

    @Nullable
    private static KotlinType getElementTypeForUnsignedArray(@NotNull KotlinType kotlinType, @NotNull ModuleDescriptor moduleDescriptor) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null || !UnsignedTypes.INSTANCE.isShortNameOfUnsignedArray(declarationDescriptor.getName())) {
            return null;
        }
        ClassId classId = DescriptorUtilsKt.getClassId(declarationDescriptor);
        if (classId == null) {
            return null;
        }
        ClassId unsignedClassIdByArrayClassId = UnsignedTypes.INSTANCE.getUnsignedClassIdByArrayClassId(classId);
        if (unsignedClassIdByArrayClassId == null) {
            return null;
        }
        ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor, unsignedClassIdByArrayClassId);
        if (findClassAcrossModuleDependencies == null) {
            return null;
        }
        return findClassAcrossModuleDependencies.getDefaultType();
    }

    @NotNull
    public SimpleType getPrimitiveArrayKotlinType(@NotNull PrimitiveType primitiveType) {
        return (SimpleType) ((Primitives) this.primitives.invoke()).primitiveTypeToArrayKotlinType.get(primitiveType);
    }

    @Nullable
    public SimpleType getPrimitiveArrayKotlinTypeByPrimitiveKotlinType(@NotNull KotlinType kotlinType) {
        SimpleType simpleType = (SimpleType) ((Primitives) this.primitives.invoke()).primitiveKotlinTypeToKotlinArrayType.get(kotlinType);
        if (simpleType != null) {
            return simpleType;
        }
        if (!UnsignedTypes.INSTANCE.isUnsignedType(kotlinType) || TypeUtils.isNullableType(kotlinType)) {
            return null;
        }
        ModuleDescriptor containingModuleOrNull = DescriptorUtils.getContainingModuleOrNull(kotlinType);
        if (containingModuleOrNull == null) {
            return null;
        }
        ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(containingModuleOrNull, UnsignedTypes.INSTANCE.getUnsignedArrayClassIdByUnsignedClassId(DescriptorUtilsKt.getClassId(kotlinType.getConstructor().getDeclarationDescriptor())));
        if (findClassAcrossModuleDependencies == null) {
            return null;
        }
        return findClassAcrossModuleDependencies.getDefaultType();
    }

    public static boolean isPrimitiveArray(@NotNull FqNameUnsafe fqNameUnsafe) {
        return FQ_NAMES.arrayClassFqNameToPrimitiveType.get(fqNameUnsafe) != null;
    }

    @Nullable
    public static PrimitiveType getPrimitiveType(@NotNull DeclarationDescriptor declarationDescriptor) {
        if (FQ_NAMES.primitiveTypeShortNames.contains(declarationDescriptor.getName())) {
            return (PrimitiveType) FQ_NAMES.fqNameToPrimitiveType.get(DescriptorUtils.getFqName(declarationDescriptor));
        }
        return null;
    }

    @Nullable
    public static PrimitiveType getPrimitiveArrayType(@NotNull DeclarationDescriptor declarationDescriptor) {
        if (FQ_NAMES.primitiveArrayTypeShortNames.contains(declarationDescriptor.getName())) {
            return (PrimitiveType) FQ_NAMES.arrayClassFqNameToPrimitiveType.get(DescriptorUtils.getFqName(declarationDescriptor));
        }
        return null;
    }

    @NotNull
    public SimpleType getArrayType(@NotNull Variance variance, @NotNull KotlinType kotlinType) {
        return KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), getArray(), Collections.singletonList(new TypeProjectionImpl(variance, kotlinType)));
    }

    public static boolean isArray(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.array);
    }

    public static boolean isArrayOrPrimitiveArray(@NotNull ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.array) || getPrimitiveArrayType(classDescriptor) != null;
    }

    public static boolean isPrimitiveArray(@NotNull KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor == null || getPrimitiveArrayType(declarationDescriptor) == null) ? false : true;
    }

    public static boolean isPrimitiveType(@NotNull KotlinType kotlinType) {
        return !kotlinType.isMarkedNullable() && isPrimitiveTypeOrNullablePrimitiveType(kotlinType);
    }

    public static boolean isPrimitiveTypeOrNullablePrimitiveType(@NotNull KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor instanceof ClassDescriptor) && isPrimitiveClass((ClassDescriptor) declarationDescriptor);
    }

    public static boolean isPrimitiveClass(@NotNull ClassDescriptor classDescriptor) {
        return getPrimitiveType(classDescriptor) != null;
    }

    public static boolean isConstructedFromGivenClass(@NotNull KotlinType kotlinType, @NotNull FqNameUnsafe fqNameUnsafe) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor instanceof ClassDescriptor) && classFqNameEquals(declarationDescriptor, fqNameUnsafe);
    }

    private static boolean classFqNameEquals(@NotNull ClassifierDescriptor classifierDescriptor, @NotNull FqNameUnsafe fqNameUnsafe) {
        return classifierDescriptor.getName().equals(fqNameUnsafe.shortName()) && fqNameUnsafe.equals(DescriptorUtils.getFqName(classifierDescriptor));
    }

    private static boolean isNotNullConstructedFromGivenClass(@NotNull KotlinType kotlinType, @NotNull FqNameUnsafe fqNameUnsafe) {
        return !kotlinType.isMarkedNullable() && isConstructedFromGivenClass(kotlinType, fqNameUnsafe);
    }

    public static boolean isSpecialClassWithNoSupertypes(@NotNull ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.any) || classFqNameEquals(classDescriptor, FQ_NAMES.nothing);
    }

    public static boolean isAny(@NotNull ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.any);
    }

    public static boolean isBoolean(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._boolean);
    }

    public static boolean isChar(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._char);
    }

    public static boolean isInt(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._int);
    }

    public static boolean isByte(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._byte);
    }

    public static boolean isLong(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._long);
    }

    public static boolean isShort(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._short);
    }

    public static boolean isFloat(@NotNull KotlinType kotlinType) {
        return isFloatOrNullableFloat(kotlinType) && !kotlinType.isMarkedNullable();
    }

    public static boolean isFloatOrNullableFloat(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES._float);
    }

    public static boolean isDouble(@NotNull KotlinType kotlinType) {
        return isDoubleOrNullableDouble(kotlinType) && !kotlinType.isMarkedNullable();
    }

    public static boolean isDoubleOrNullableDouble(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES._double);
    }

    private static boolean isConstructedFromGivenClassAndNotNullable(@NotNull KotlinType kotlinType, @NotNull FqNameUnsafe fqNameUnsafe) {
        return isConstructedFromGivenClass(kotlinType, fqNameUnsafe) && !kotlinType.isMarkedNullable();
    }

    public static boolean isNothing(@NotNull KotlinType kotlinType) {
        return isNothingOrNullableNothing(kotlinType) && !TypeUtils.isNullableType(kotlinType);
    }

    public static boolean isNothingOrNullableNothing(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.nothing);
    }

    public static boolean isAnyOrNullableAny(@NotNull KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.any);
    }

    public static boolean isNullableAny(@NotNull KotlinType kotlinType) {
        return isAnyOrNullableAny(kotlinType) && kotlinType.isMarkedNullable();
    }

    public static boolean isDefaultBound(@NotNull KotlinType kotlinType) {
        return isNullableAny(kotlinType);
    }

    public static boolean isUnit(@NotNull KotlinType kotlinType) {
        return isNotNullConstructedFromGivenClass(kotlinType, FQ_NAMES.unit);
    }

    public static boolean isString(@Nullable KotlinType kotlinType) {
        return kotlinType != null && isNotNullConstructedFromGivenClass(kotlinType, FQ_NAMES.string);
    }

    public static boolean isKClass(@NotNull ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.kClass);
    }

    public static boolean isDeprecated(@NotNull DeclarationDescriptor declarationDescriptor) {
        boolean z = true;
        if (declarationDescriptor.getOriginal().getAnnotations().hasAnnotation(FQ_NAMES.deprecated)) {
            return true;
        }
        if (!(declarationDescriptor instanceof PropertyDescriptor)) {
            return false;
        }
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) declarationDescriptor;
        boolean isVar = propertyDescriptor.isVar();
        PropertyGetterDescriptor getter = propertyDescriptor.getGetter();
        PropertySetterDescriptor setter = propertyDescriptor.getSetter();
        if (getter == null || !isDeprecated(getter) || (isVar && (setter == null || !isDeprecated(setter)))) {
            z = false;
        }
        return z;
    }

    public static FqName getPrimitiveFqName(@NotNull PrimitiveType primitiveType) {
        return BUILT_INS_PACKAGE_FQ_NAME.child(primitiveType.getTypeName());
    }
}
