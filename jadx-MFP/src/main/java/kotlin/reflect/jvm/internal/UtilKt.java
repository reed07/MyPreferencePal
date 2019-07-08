package kotlin.reflect.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference;
import kotlin.reflect.KCallable;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt;
import kotlin.reflect.jvm.internal.components.ReflectAnnotationSource;
import kotlin.reflect.jvm.internal.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.components.RuntimeSourceElementFactory.RuntimeSourceElement;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinarySourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.structure.ReflectJavaAnnotation;
import kotlin.reflect.jvm.internal.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.structure.ReflectJavaElement;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001an\u0010\u0012\u001a\u0004\u0018\u0001H\u0013\"\b\b\u0000\u0010\u0014*\u00020\u0015\"\b\b\u0001\u0010\u0013*\u00020\u00062\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u0002H\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u001d\u0010\u001f\u001a\u0019\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00130 ¢\u0006\u0002\b\"H\u0000¢\u0006\u0002\u0010#\u001a&\u0010$\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00172\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0000\u001a\"\u0010)\u001a\u0002H*\"\u0004\b\u0000\u0010*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H*0,H\b¢\u0006\u0002\u0010-\u001a\u0014\u0010.\u001a\b\u0012\u0002\b\u0003\u0018\u00010/*\u0004\u0018\u000100H\u0000\u001a\u0010\u00101\u001a\u0004\u0018\u000102*\u0004\u0018\u000100H\u0000\u001a\u0014\u00103\u001a\b\u0012\u0002\b\u0003\u0018\u000104*\u0004\u0018\u000100H\u0000\u001a\u0012\u00105\u001a\b\u0012\u0004\u0012\u00020706*\u000208H\u0000\u001a\u000e\u00109\u001a\u0004\u0018\u000107*\u00020:H\u0002\u001a\u0012\u0010;\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0017*\u00020<H\u0000\u001a\u000e\u0010=\u001a\u0004\u0018\u00010>*\u00020?H\u0000\u001a\u001a\u0010@\u001a\u0004\u0018\u000100*\u0006\u0012\u0002\b\u00030A2\u0006\u0010%\u001a\u00020&H\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00068@X\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0018\u0010\t\u001a\u00020\n*\u00020\u000b8@X\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\f\"\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u000f8@X\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006B"}, d2 = {"JVM_STATIC", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "getJVM_STATIC", "()Lorg/jetbrains/kotlin/name/FqName;", "instanceReceiverParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "getInstanceReceiverParameter", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "isPublicInBytecode", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;)Z", "packageModuleName", "", "Lkotlin/reflect/jvm/internal/components/ReflectKotlinClass;", "getPackageModuleName", "(Lkotlin/reflect/jvm/internal/components/ReflectKotlinClass;)Ljava/lang/String;", "deserializeToDescriptor", "D", "M", "Lkotlin/reflect/jvm/internal/impl/protobuf/MessageLite;", "moduleAnchor", "Ljava/lang/Class;", "proto", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "metadataVersion", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion;", "createDescriptor", "Lkotlin/Function2;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Class;Lorg/jetbrains/kotlin/protobuf/MessageLite;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "loadClass", "classLoader", "Ljava/lang/ClassLoader;", "packageName", "className", "reflectionCall", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asKCallableImpl", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "", "asKFunctionImpl", "Lkotlin/reflect/jvm/internal/KFunctionImpl;", "asKPropertyImpl", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "computeAnnotations", "", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotated;", "toAnnotationInstance", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor;", "toJavaClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "toKVisibility", "Lkotlin/reflect/KVisibility;", "Lkotlin/reflect/jvm/internal/impl/descriptors/Visibility;", "toRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ConstantValue;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* compiled from: util.kt */
public final class UtilKt {
    @NotNull
    private static final FqName JVM_STATIC = new FqName("kotlin.jvm.JvmStatic");

    @NotNull
    public static final FqName getJVM_STATIC() {
        return JVM_STATIC;
    }

    @Nullable
    public static final Class<?> toJavaClass(@NotNull ClassDescriptor classDescriptor) {
        Class<?> cls;
        Intrinsics.checkParameterIsNotNull(classDescriptor, "receiver$0");
        SourceElement source = classDescriptor.getSource();
        Intrinsics.checkExpressionValueIsNotNull(source, "source");
        if (source instanceof KotlinJvmBinarySourceElement) {
            KotlinJvmBinaryClass binaryClass = ((KotlinJvmBinarySourceElement) source).getBinaryClass();
            if (binaryClass != null) {
                cls = ((ReflectKotlinClass) binaryClass).getKlass();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.components.ReflectKotlinClass");
            }
        } else if (source instanceof RuntimeSourceElement) {
            ReflectJavaElement javaElement = ((RuntimeSourceElement) source).getJavaElement();
            if (javaElement != null) {
                cls = ((ReflectJavaClass) javaElement).getElement();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.structure.ReflectJavaClass");
            }
        } else {
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            FqNameUnsafe fqName = DescriptorUtils.getFqName(classDescriptor);
            Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.getFqName(this)");
            ClassId mapKotlinToJava = javaToKotlinClassMap.mapKotlinToJava(fqName);
            if (mapKotlinToJava == null) {
                mapKotlinToJava = DescriptorUtilsKt.getClassId(classDescriptor);
            }
            if (mapKotlinToJava == null) {
                return null;
            }
            String asString = mapKotlinToJava.getPackageFqName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "classId.packageFqName.asString()");
            String asString2 = mapKotlinToJava.getRelativeClassName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString2, "classId.relativeClassName.asString()");
            cls = loadClass(ReflectClassUtilKt.getSafeClassLoader(classDescriptor.getClass()), asString, asString2);
        }
        return cls;
    }

    @Nullable
    public static final Class<?> loadClass(@NotNull ClassLoader classLoader, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(classLoader, "classLoader");
        Intrinsics.checkParameterIsNotNull(str, "packageName");
        Intrinsics.checkParameterIsNotNull(str2, "className");
        if (Intrinsics.areEqual((Object) str, (Object) "kotlin")) {
            switch (str2.hashCode()) {
                case -901856463:
                    if (str2.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (str2.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (str2.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (str2.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (str2.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (str2.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (str2.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (str2.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (str2.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append('.');
        sb.append(StringsKt.replace$default(str2, '.', (char) Typography.dollar, false, 4, (Object) null));
        return ReflectJavaClassFinderKt.tryLoadClass(classLoader, sb.toString());
    }

    @Nullable
    public static final KVisibility toKVisibility(@NotNull Visibility visibility) {
        Intrinsics.checkParameterIsNotNull(visibility, "receiver$0");
        if (Intrinsics.areEqual((Object) visibility, (Object) Visibilities.PUBLIC)) {
            return KVisibility.PUBLIC;
        }
        if (Intrinsics.areEqual((Object) visibility, (Object) Visibilities.PROTECTED)) {
            return KVisibility.PROTECTED;
        }
        if (Intrinsics.areEqual((Object) visibility, (Object) Visibilities.INTERNAL)) {
            return KVisibility.INTERNAL;
        }
        if (!Intrinsics.areEqual((Object) visibility, (Object) Visibilities.PRIVATE) && !Intrinsics.areEqual((Object) visibility, (Object) Visibilities.PRIVATE_TO_THIS)) {
            return null;
        }
        return KVisibility.PRIVATE;
    }

    @NotNull
    public static final List<Annotation> computeAnnotations(@NotNull Annotated annotated) {
        Intrinsics.checkParameterIsNotNull(annotated, "receiver$0");
        Iterable<AnnotationDescriptor> annotations = annotated.getAnnotations();
        Collection arrayList = new ArrayList();
        for (AnnotationDescriptor annotationDescriptor : annotations) {
            SourceElement source = annotationDescriptor.getSource();
            Annotation annotation = null;
            if (source instanceof ReflectAnnotationSource) {
                annotation = ((ReflectAnnotationSource) source).getAnnotation();
            } else if (source instanceof RuntimeSourceElement) {
                ReflectJavaElement javaElement = ((RuntimeSourceElement) source).getJavaElement();
                if (!(javaElement instanceof ReflectJavaAnnotation)) {
                    javaElement = null;
                }
                ReflectJavaAnnotation reflectJavaAnnotation = (ReflectJavaAnnotation) javaElement;
                if (reflectJavaAnnotation != null) {
                    annotation = reflectJavaAnnotation.getAnnotation();
                }
            } else {
                annotation = toAnnotationInstance(annotationDescriptor);
            }
            if (annotation != null) {
                arrayList.add(annotation);
            }
        }
        return (List) arrayList;
    }

    private static final Annotation toAnnotationInstance(@NotNull AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        Class javaClass = annotationClass != null ? toJavaClass(annotationClass) : null;
        if (!(javaClass instanceof Class)) {
            javaClass = null;
        }
        if (javaClass == null) {
            return null;
        }
        Iterable<Entry> entrySet = annotationDescriptor.getAllValueArguments().entrySet();
        Collection arrayList = new ArrayList();
        for (Entry entry : entrySet) {
            Name name = (Name) entry.getKey();
            ConstantValue constantValue = (ConstantValue) entry.getValue();
            ClassLoader classLoader = javaClass.getClassLoader();
            Intrinsics.checkExpressionValueIsNotNull(classLoader, "annotationClass.classLoader");
            Object runtimeValue = toRuntimeValue(constantValue, classLoader);
            Object obj = runtimeValue != null ? TuplesKt.to(name.asString(), runtimeValue) : null;
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return (Annotation) AnnotationConstructorCallerKt.createAnnotationInstance$default(javaClass, MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) (List) arrayList), null, 4, null);
    }

    private static final Object toRuntimeValue(@NotNull ConstantValue<?> constantValue, ClassLoader classLoader) {
        if (constantValue instanceof AnnotationValue) {
            return toAnnotationInstance((AnnotationDescriptor) ((AnnotationValue) constantValue).getValue());
        }
        if (constantValue instanceof ArrayValue) {
            Iterable<ConstantValue> iterable = (Iterable) ((ArrayValue) constantValue).getValue();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ConstantValue runtimeValue : iterable) {
                arrayList.add(toRuntimeValue(runtimeValue, classLoader));
            }
            Object[] array = ((List) arrayList).toArray(new Object[0]);
            if (array != null) {
                return array;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } else if (constantValue instanceof EnumValue) {
            Pair pair = (Pair) ((EnumValue) constantValue).getValue();
            ClassId classId = (ClassId) pair.component1();
            Name name = (Name) pair.component2();
            String asString = classId.getPackageFqName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "enumClassId.packageFqName.asString()");
            String asString2 = classId.getRelativeClassName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString2, "enumClassId.relativeClassName.asString()");
            Class loadClass = loadClass(classLoader, asString, asString2);
            if (loadClass == null) {
                return null;
            }
            if (loadClass != null) {
                return Util.getEnumConstantByName(loadClass, name.asString());
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<out kotlin.Enum<*>>");
        } else if (constantValue instanceof KClassValue) {
            ClassifierDescriptor declarationDescriptor = ((KClassValue) constantValue).getValue().getConstructor().getDeclarationDescriptor();
            if (!(declarationDescriptor instanceof ClassDescriptor)) {
                declarationDescriptor = null;
            }
            ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
            if (classDescriptor != null) {
                return toJavaClass(classDescriptor);
            }
            return null;
        } else if (!(constantValue instanceof ErrorValue) && !(constantValue instanceof NullValue)) {
            return constantValue.getValue();
        } else {
            return null;
        }
    }

    @Nullable
    public static final KFunctionImpl asKFunctionImpl(@Nullable Object obj) {
        KFunctionImpl kFunctionImpl = (KFunctionImpl) (!(obj instanceof KFunctionImpl) ? null : obj);
        if (kFunctionImpl != null) {
            return kFunctionImpl;
        }
        if (!(obj instanceof FunctionReference)) {
            obj = null;
        }
        FunctionReference functionReference = (FunctionReference) obj;
        KCallable compute = functionReference != null ? functionReference.compute() : null;
        if (!(compute instanceof KFunctionImpl)) {
            compute = null;
        }
        return (KFunctionImpl) compute;
    }

    @Nullable
    public static final KPropertyImpl<?> asKPropertyImpl(@Nullable Object obj) {
        KPropertyImpl<?> kPropertyImpl = (KPropertyImpl) (!(obj instanceof KPropertyImpl) ? null : obj);
        if (kPropertyImpl != null) {
            return kPropertyImpl;
        }
        if (!(obj instanceof PropertyReference)) {
            obj = null;
        }
        PropertyReference propertyReference = (PropertyReference) obj;
        KCallable compute = propertyReference != null ? propertyReference.compute() : null;
        if (!(compute instanceof KPropertyImpl)) {
            compute = null;
        }
        return (KPropertyImpl) compute;
    }

    @Nullable
    public static final KCallableImpl<?> asKCallableImpl(@Nullable Object obj) {
        KCallableImpl<?> kCallableImpl = (KCallableImpl) (!(obj instanceof KCallableImpl) ? null : obj);
        if (kCallableImpl == null) {
            kCallableImpl = asKFunctionImpl(obj);
        }
        return kCallableImpl != null ? kCallableImpl : asKPropertyImpl(obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x009a, code lost:
        if (r2 != null) goto L_0x009f;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getPackageModuleName(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.components.ReflectKotlinClass r9) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader r0 = r9.getClassHeader()
            kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion r1 = r0.getMetadataVersion()
            boolean r1 = r1.isCompatible()
            r2 = 0
            if (r1 != 0) goto L_0x0015
            return r2
        L_0x0015:
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader$Kind r1 = r0.getKind()
            int[] r3 = kotlin.reflect.jvm.internal.UtilKt.WhenMappings.$EnumSwitchMapping$0
            int r1 = r1.ordinal()
            r1 = r3[r1]
            switch(r1) {
                case 1: goto L_0x005d;
                case 2: goto L_0x005d;
                case 3: goto L_0x0026;
                default: goto L_0x0024;
            }
        L_0x0024:
            goto L_0x009f
        L_0x0026:
            java.util.List r0 = r0.getMultifilePartNames()
            java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r0)
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto L_0x005c
            kotlin.reflect.jvm.internal.components.ReflectKotlinClass$Factory r0 = kotlin.reflect.jvm.internal.components.ReflectKotlinClass.Factory
            java.lang.Class r9 = r9.getKlass()
            java.lang.ClassLoader r9 = r9.getClassLoader()
            r4 = 47
            r5 = 46
            r6 = 0
            r7 = 4
            r8 = 0
            java.lang.String r1 = kotlin.text.StringsKt.replace$default(r3, r4, r5, r6, r7, r8)
            java.lang.Class r9 = r9.loadClass(r1)
            java.lang.String r1 = "klass.classLoader.loadCl…rtName.replace('/', '.'))"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r1)
            kotlin.reflect.jvm.internal.components.ReflectKotlinClass r9 = r0.create(r9)
            if (r9 == 0) goto L_0x009f
            java.lang.String r2 = getPackageModuleName(r9)
            goto L_0x009f
        L_0x005c:
            return r2
        L_0x005d:
            java.lang.String[] r9 = r0.getData()
            if (r9 != 0) goto L_0x0066
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0066:
            java.lang.String[] r0 = r0.getStrings()
            if (r0 != 0) goto L_0x006f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x006f:
            kotlin.Pair r9 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil.readPackageDataFrom(r9, r0)
            java.lang.Object r0 = r9.component1()
            kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver r0 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver) r0
            java.lang.Object r9 = r9.component2()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package r9 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package) r9
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableMessage r9 = (kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage) r9
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, java.lang.Integer> r1 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.packageModuleName
            java.lang.String r2 = "JvmProtoBuf.packageModuleName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.Object r9 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt.getExtensionOrNull(r9, r1)
            java.lang.Integer r9 = (java.lang.Integer) r9
            if (r9 == 0) goto L_0x009d
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            java.lang.String r2 = r0.getString(r9)
            if (r2 == 0) goto L_0x009d
            goto L_0x009f
        L_0x009d:
            java.lang.String r2 = "main"
        L_0x009f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.UtilKt.getPackageModuleName(kotlin.reflect.jvm.internal.components.ReflectKotlinClass):java.lang.String");
    }

    public static final boolean isPublicInBytecode(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "receiver$0");
        Visibility visibility = callableMemberDescriptor.getVisibility();
        Intrinsics.checkExpressionValueIsNotNull(visibility, "visibility");
        return (Intrinsics.areEqual((Object) visibility, (Object) Visibilities.PUBLIC) || Intrinsics.areEqual((Object) visibility, (Object) Visibilities.INTERNAL)) && !AnnotationUtilKt.isEffectivelyInlineOnly(callableMemberDescriptor);
    }

    @Nullable
    public static final ReceiverParameterDescriptor getInstanceReceiverParameter(@NotNull CallableDescriptor callableDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "receiver$0");
        if (callableDescriptor.getDispatchReceiverParameter() == null) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = callableDescriptor.getContainingDeclaration();
        if (containingDeclaration != null) {
            return ((ClassDescriptor) containingDeclaration).getThisAsReceiverParameter();
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    @Nullable
    public static final <M extends MessageLite, D extends CallableDescriptor> D deserializeToDescriptor(@NotNull Class<?> cls, @NotNull M m, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @NotNull BinaryVersion binaryVersion, @NotNull Function2<? super MemberDeserializer, ? super M, ? extends D> function2) {
        List list;
        M m2 = m;
        Function2<? super MemberDeserializer, ? super M, ? extends D> function22 = function2;
        Class<?> cls2 = cls;
        Intrinsics.checkParameterIsNotNull(cls, "moduleAnchor");
        Intrinsics.checkParameterIsNotNull(m, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(function22, "createDescriptor");
        RuntimeModuleData orCreateModule = ModuleByClassLoaderKt.getOrCreateModule(cls);
        if (m2 instanceof Function) {
            list = ((Function) m2).getTypeParameterList();
        } else if (m2 instanceof Property) {
            list = ((Property) m2).getTypeParameterList();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported message: ");
            sb.append(m);
            throw new IllegalStateException(sb.toString().toString());
        }
        DeserializationComponents deserialization = orCreateModule.getDeserialization();
        DeclarationDescriptor module = orCreateModule.getModule();
        VersionRequirementTable empty = VersionRequirementTable.Companion.getEMPTY();
        Intrinsics.checkExpressionValueIsNotNull(list, "typeParameters");
        DeserializationContext deserializationContext = new DeserializationContext(deserialization, nameResolver, module, typeTable, empty, binaryVersion, null, null, list);
        return (CallableDescriptor) function22.invoke(new MemberDeserializer(deserializationContext), m);
    }
}
