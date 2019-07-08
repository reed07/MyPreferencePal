package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationAsAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassObjectAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaLiteralAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaAnnotationDescriptor.kt */
public final class LazyJavaAnnotationDescriptor implements AnnotationDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "fqName", "getFqName()Lorg/jetbrains/kotlin/name/FqName;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    @NotNull
    private final NotNullLazyValue allValueArguments$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaAnnotationDescriptor$allValueArguments$2(this));
    /* access modifiers changed from: private */
    public final LazyJavaResolverContext c;
    @Nullable
    private final NullableLazyValue fqName$delegate = this.c.getStorageManager().createNullableLazyValue(new LazyJavaAnnotationDescriptor$fqName$2(this));
    /* access modifiers changed from: private */
    public final JavaAnnotation javaAnnotation;
    @NotNull
    private final JavaSourceElement source = this.c.getComponents().getSourceElementFactory().source(this.javaAnnotation);
    @NotNull
    private final NotNullLazyValue type$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaAnnotationDescriptor$type$2(this));

    @NotNull
    public Map<Name, ConstantValue<?>> getAllValueArguments() {
        return (Map) StorageKt.getValue(this.allValueArguments$delegate, (Object) this, $$delegatedProperties[2]);
    }

    @Nullable
    public FqName getFqName() {
        return (FqName) StorageKt.getValue(this.fqName$delegate, (Object) this, $$delegatedProperties[0]);
    }

    @NotNull
    public SimpleType getType() {
        return (SimpleType) StorageKt.getValue(this.type$delegate, (Object) this, $$delegatedProperties[1]);
    }

    public LazyJavaAnnotationDescriptor(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull JavaAnnotation javaAnnotation2) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        Intrinsics.checkParameterIsNotNull(javaAnnotation2, "javaAnnotation");
        this.c = lazyJavaResolverContext;
        this.javaAnnotation = javaAnnotation2;
    }

    @NotNull
    public JavaSourceElement getSource() {
        return this.source;
    }

    /* access modifiers changed from: private */
    public final ConstantValue<?> resolveAnnotationArgument(JavaAnnotationArgument javaAnnotationArgument) {
        if (javaAnnotationArgument instanceof JavaLiteralAnnotationArgument) {
            return ConstantValueFactory.INSTANCE.createConstantValue(((JavaLiteralAnnotationArgument) javaAnnotationArgument).getValue());
        }
        if (javaAnnotationArgument instanceof JavaEnumValueAnnotationArgument) {
            JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument = (JavaEnumValueAnnotationArgument) javaAnnotationArgument;
            return resolveFromEnumValue(javaEnumValueAnnotationArgument.getEnumClassId(), javaEnumValueAnnotationArgument.getEntryName());
        } else if (javaAnnotationArgument instanceof JavaArrayAnnotationArgument) {
            Name name = javaAnnotationArgument.getName();
            if (name == null) {
                name = JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME;
                Intrinsics.checkExpressionValueIsNotNull(name, "DEFAULT_ANNOTATION_MEMBER_NAME");
            }
            return resolveFromArray(name, ((JavaArrayAnnotationArgument) javaAnnotationArgument).getElements());
        } else if (javaAnnotationArgument instanceof JavaAnnotationAsAnnotationArgument) {
            return resolveFromAnnotation(((JavaAnnotationAsAnnotationArgument) javaAnnotationArgument).getAnnotation());
        } else {
            if (javaAnnotationArgument instanceof JavaClassObjectAnnotationArgument) {
                return resolveFromJavaClassObjectType(((JavaClassObjectAnnotationArgument) javaAnnotationArgument).getReferencedType());
            }
            return null;
        }
    }

    private final ConstantValue<?> resolveFromAnnotation(JavaAnnotation javaAnnotation2) {
        return new AnnotationValue<>(new LazyJavaAnnotationDescriptor(this.c, javaAnnotation2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r3 != null) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue<?> resolveFromArray(kotlin.reflect.jvm.internal.impl.name.Name r3, java.util.List<? extends kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument> r4) {
        /*
            r2 = this;
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = r2.getType()
            java.lang.String r1 = "type"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
            boolean r0 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt.isError(r0)
            if (r0 == 0) goto L_0x0013
            r3 = 0
            return r3
        L_0x0013:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r0 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getAnnotationClass(r2)
            if (r0 != 0) goto L_0x001c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x001c:
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r3 = kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.getAnnotationParameterByName(r3, r0)
            if (r3 == 0) goto L_0x0029
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = r3.getType()
            if (r3 == 0) goto L_0x0029
            goto L_0x0047
        L_0x0029:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r3 = r2.c
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r3 = r3.getComponents()
            kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r3 = r3.getModule()
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r3 = r3.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.Variance r0 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
            java.lang.String r1 = "Unknown array element type"
            kotlin.reflect.jvm.internal.impl.types.SimpleType r1 = kotlin.reflect.jvm.internal.impl.types.ErrorUtils.createErrorType(r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
            kotlin.reflect.jvm.internal.impl.types.SimpleType r3 = r3.getArrayType(r0, r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r3
        L_0x0047:
            java.lang.String r0 = "DescriptorResolverUtils.…e\")\n                    )"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r1)
            r0.<init>(r1)
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r4 = r4.iterator()
        L_0x005f:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x007d
            java.lang.Object r1 = r4.next()
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument r1 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument) r1
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r1 = r2.resolveAnnotationArgument(r1)
            if (r1 == 0) goto L_0x0072
            goto L_0x0079
        L_0x0072:
            kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue r1 = new kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue
            r1.<init>()
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r1 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r1
        L_0x0079:
            r0.add(r1)
            goto L_0x005f
        L_0x007d:
            java.util.List r0 = (java.util.List) r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory r4 = kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory.INSTANCE
            kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue r3 = r4.createArrayValue(r0, r3)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r3 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor.resolveFromArray(kotlin.reflect.jvm.internal.impl.name.Name, java.util.List):kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue");
    }

    private final ConstantValue<?> resolveFromEnumValue(ClassId classId, Name name) {
        if (classId == null || name == null) {
            return null;
        }
        return new EnumValue<>(classId, name);
    }

    private final ConstantValue<?> resolveFromJavaClassObjectType(JavaType javaType) {
        KotlinType makeNotNullable = TypeUtils.makeNotNullable(this.c.getTypeResolver().transformJavaType(javaType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null)));
        Intrinsics.checkExpressionValueIsNotNull(makeNotNullable, "TypeUtils.makeNotNullabl…toAttributes())\n        )");
        ClassDescriptor resolveTopLevelClass = DescriptorUtilsKt.resolveTopLevelClass(this.c.getModule(), new FqName("java.lang.Class"), NoLookupLocation.FOR_NON_TRACKED_SCOPE);
        if (resolveTopLevelClass == null) {
            return null;
        }
        return new KClassValue<>(KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), resolveTopLevelClass, CollectionsKt.listOf(new TypeProjectionImpl(makeNotNullable))));
    }

    @NotNull
    public String toString() {
        return DescriptorRenderer.renderAnnotation$default(DescriptorRenderer.FQ_NAMES_IN_TYPES, this, null, 2, null);
    }

    /* access modifiers changed from: private */
    public final ClassDescriptor createTypeForMissingDependencies(FqName fqName) {
        ModuleDescriptor module = this.c.getModule();
        ClassId classId = ClassId.topLevel(fqName);
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(fqName)");
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(module, classId, this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses());
    }
}
