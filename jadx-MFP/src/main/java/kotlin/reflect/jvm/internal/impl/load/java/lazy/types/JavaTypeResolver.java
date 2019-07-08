package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaTypeResolver.kt */
public final class JavaTypeResolver {
    private final LazyJavaResolverContext c;
    private final TypeParameterResolver typeParameterResolver;

    public JavaTypeResolver(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull TypeParameterResolver typeParameterResolver2) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        Intrinsics.checkParameterIsNotNull(typeParameterResolver2, "typeParameterResolver");
        this.c = lazyJavaResolverContext;
        this.typeParameterResolver = typeParameterResolver2;
    }

    @NotNull
    public final KotlinType transformJavaType(@NotNull JavaType javaType, @NotNull JavaTypeAttributes javaTypeAttributes) {
        SimpleType simpleType;
        Intrinsics.checkParameterIsNotNull(javaType, "javaType");
        Intrinsics.checkParameterIsNotNull(javaTypeAttributes, "attr");
        if (javaType instanceof JavaPrimitiveType) {
            PrimitiveType type = ((JavaPrimitiveType) javaType).getType();
            if (type != null) {
                simpleType = this.c.getModule().getBuiltIns().getPrimitiveKotlinType(type);
            } else {
                simpleType = this.c.getModule().getBuiltIns().getUnitType();
            }
            Intrinsics.checkExpressionValueIsNotNull(simpleType, "if (primitiveType != nul….module.builtIns.unitType");
            return simpleType;
        } else if (javaType instanceof JavaClassifierType) {
            return transformJavaClassifierType((JavaClassifierType) javaType, javaTypeAttributes);
        } else {
            if (javaType instanceof JavaArrayType) {
                return transformArrayType$default(this, (JavaArrayType) javaType, javaTypeAttributes, false, 4, null);
            } else if (javaType instanceof JavaWildcardType) {
                JavaType bound = ((JavaWildcardType) javaType).getBound();
                if (bound != null) {
                    KotlinType transformJavaType = transformJavaType(bound, javaTypeAttributes);
                    if (transformJavaType != null) {
                        return transformJavaType;
                    }
                }
                SimpleType defaultBound = this.c.getModule().getBuiltIns().getDefaultBound();
                Intrinsics.checkExpressionValueIsNotNull(defaultBound, "c.module.builtIns.defaultBound");
                return defaultBound;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported type: ");
                sb.append(javaType);
                throw new UnsupportedOperationException(sb.toString());
            }
        }
    }

    @NotNull
    public static /* synthetic */ KotlinType transformArrayType$default(JavaTypeResolver javaTypeResolver, JavaArrayType javaArrayType, JavaTypeAttributes javaTypeAttributes, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return javaTypeResolver.transformArrayType(javaArrayType, javaTypeAttributes, z);
    }

    @NotNull
    public final KotlinType transformArrayType(@NotNull JavaArrayType javaArrayType, @NotNull JavaTypeAttributes javaTypeAttributes, boolean z) {
        KotlinType kotlinType;
        Intrinsics.checkParameterIsNotNull(javaArrayType, "arrayType");
        Intrinsics.checkParameterIsNotNull(javaTypeAttributes, "attr");
        JavaType componentType = javaArrayType.getComponentType();
        JavaPrimitiveType javaPrimitiveType = (JavaPrimitiveType) (!(componentType instanceof JavaPrimitiveType) ? null : componentType);
        PrimitiveType type = javaPrimitiveType != null ? javaPrimitiveType.getType() : null;
        if (type != null) {
            SimpleType primitiveArrayKotlinType = this.c.getModule().getBuiltIns().getPrimitiveArrayKotlinType(type);
            Intrinsics.checkExpressionValueIsNotNull(primitiveArrayKotlinType, "c.module.builtIns.getPri…KotlinType(primitiveType)");
            if (javaTypeAttributes.isForAnnotationParameter()) {
                kotlinType = primitiveArrayKotlinType;
            } else {
                kotlinType = KotlinTypeFactory.flexibleType(primitiveArrayKotlinType, primitiveArrayKotlinType.makeNullableAsSpecified(true));
            }
            return kotlinType;
        }
        KotlinType transformJavaType = transformJavaType(componentType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, javaTypeAttributes.isForAnnotationParameter(), null, 2, null));
        if (javaTypeAttributes.isForAnnotationParameter()) {
            SimpleType arrayType = this.c.getModule().getBuiltIns().getArrayType(z ? Variance.OUT_VARIANCE : Variance.INVARIANT, transformJavaType);
            Intrinsics.checkExpressionValueIsNotNull(arrayType, "c.module.builtIns.getArr…ctionKind, componentType)");
            return arrayType;
        }
        SimpleType arrayType2 = this.c.getModule().getBuiltIns().getArrayType(Variance.INVARIANT, transformJavaType);
        Intrinsics.checkExpressionValueIsNotNull(arrayType2, "c.module.builtIns.getArr…INVARIANT, componentType)");
        return KotlinTypeFactory.flexibleType(arrayType2, this.c.getModule().getBuiltIns().getArrayType(Variance.OUT_VARIANCE, transformJavaType).makeNullableAsSpecified(true));
    }

    private final KotlinType transformJavaClassifierType(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        KotlinType kotlinType;
        JavaTypeResolver$transformJavaClassifierType$1 javaTypeResolver$transformJavaClassifierType$1 = new JavaTypeResolver$transformJavaClassifierType$1(javaClassifierType);
        boolean z = !javaTypeAttributes.isForAnnotationParameter() && javaTypeAttributes.getHowThisTypeIsUsed() != TypeUsage.SUPERTYPE;
        boolean isRaw = javaClassifierType.isRaw();
        if (isRaw || z) {
            SimpleType computeSimpleJavaClassifierType = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND), null);
            if (computeSimpleJavaClassifierType == null) {
                return javaTypeResolver$transformJavaClassifierType$1.invoke();
            }
            SimpleType computeSimpleJavaClassifierType2 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND), computeSimpleJavaClassifierType);
            if (computeSimpleJavaClassifierType2 == null) {
                return javaTypeResolver$transformJavaClassifierType$1.invoke();
            }
            if (isRaw) {
                kotlinType = new RawTypeImpl(computeSimpleJavaClassifierType, computeSimpleJavaClassifierType2);
            } else {
                kotlinType = KotlinTypeFactory.flexibleType(computeSimpleJavaClassifierType, computeSimpleJavaClassifierType2);
            }
            return kotlinType;
        }
        SimpleType computeSimpleJavaClassifierType3 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes, null);
        if (computeSimpleJavaClassifierType3 == null) {
            computeSimpleJavaClassifierType3 = javaTypeResolver$transformJavaClassifierType$1.invoke();
        }
        return computeSimpleJavaClassifierType3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        if (r0 != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.types.SimpleType computeSimpleJavaClassifierType(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType r5, kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes r6, kotlin.reflect.jvm.internal.impl.types.SimpleType r7) {
        /*
            r4 = this;
            if (r7 == 0) goto L_0x0009
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r7.getAnnotations()
            if (r0 == 0) goto L_0x0009
            goto L_0x0015
        L_0x0009:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations r0 = new kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r1 = r4.c
            r2 = r5
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner r2 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner) r2
            r0.<init>(r1, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations) r0
        L_0x0015:
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r1 = r4.computeTypeConstructor(r5, r6)
            r2 = 0
            if (r1 == 0) goto L_0x0043
            boolean r3 = r4.isNullable(r6)
            if (r7 == 0) goto L_0x0026
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r2 = r7.getConstructor()
        L_0x0026:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r1)
            if (r2 == 0) goto L_0x003a
            boolean r2 = r5.isRaw()
            if (r2 != 0) goto L_0x003a
            if (r3 == 0) goto L_0x003a
            r5 = 1
            kotlin.reflect.jvm.internal.impl.types.SimpleType r5 = r7.makeNullableAsSpecified(r5)
            return r5
        L_0x003a:
            java.util.List r5 = r4.computeArguments(r5, r6, r1)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r5 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleType(r0, r1, r5, r3)
            return r5
        L_0x0043:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.computeSimpleJavaClassifierType(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType, kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes, kotlin.reflect.jvm.internal.impl.types.SimpleType):kotlin.reflect.jvm.internal.impl.types.SimpleType");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r5 != null) goto L_0x0063;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.types.TypeConstructor computeTypeConstructor(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType r4, kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes r5) {
        /*
            r3 = this;
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier r0 = r4.getClassifier()
            if (r0 == 0) goto L_0x007d
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
            if (r1 == 0) goto L_0x004f
            r1 = r0
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass r1 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass) r1
            kotlin.reflect.jvm.internal.impl.name.FqName r2 = r1.getFqName()
            if (r2 == 0) goto L_0x0036
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r5 = r3.mapKotlinClass(r4, r5, r2)
            if (r5 == 0) goto L_0x001a
            goto L_0x0028
        L_0x001a:
            kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r5 = r3.c
            kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r5 = r5.getComponents()
            kotlin.reflect.jvm.internal.impl.load.java.lazy.ModuleClassResolver r5 = r5.getModuleClassResolver()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r5 = r5.resolveClass(r1)
        L_0x0028:
            if (r5 == 0) goto L_0x0031
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r5 = r5.getTypeConstructor()
            if (r5 == 0) goto L_0x0031
            goto L_0x0063
        L_0x0031:
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r5 = r3.createNotFoundClass(r4)
            goto L_0x0063
        L_0x0036:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Class type should have a FQ name: "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>(r4)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        L_0x004f:
            boolean r4 = r0 instanceof kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter
            if (r4 == 0) goto L_0x0064
            kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver r4 = r3.typeParameterResolver
            kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter r0 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter) r0
            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r4 = r4.resolveTypeParameter(r0)
            if (r4 == 0) goto L_0x0062
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r5 = r4.getTypeConstructor()
            goto L_0x0063
        L_0x0062:
            r5 = 0
        L_0x0063:
            return r5
        L_0x0064:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "Unknown classifier kind: "
            r5.append(r1)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        L_0x007d:
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r4 = r3.createNotFoundClass(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.computeTypeConstructor(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType, kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes):kotlin.reflect.jvm.internal.impl.types.TypeConstructor");
    }

    private final TypeConstructor createNotFoundClass(JavaClassifierType javaClassifierType) {
        ClassId classId = ClassId.topLevel(new FqName(javaClassifierType.getClassifierQualifiedName()));
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(FqName(…classifierQualifiedName))");
        TypeConstructor typeConstructor = this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses().getClass(classId, CollectionsKt.listOf(Integer.valueOf(0))).getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "c.components.deserialize…istOf(0)).typeConstructor");
        return typeConstructor;
    }

    private final ClassDescriptor mapKotlinClass(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, FqName fqName) {
        if (javaTypeAttributes.isForAnnotationParameter() && Intrinsics.areEqual((Object) fqName, (Object) JavaTypeResolverKt.JAVA_LANG_CLASS_FQ_NAME)) {
            return this.c.getComponents().getReflectionTypes().getKClass();
        }
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        ClassDescriptor mapJavaToKotlin$default = JavaToKotlinClassMap.mapJavaToKotlin$default(javaToKotlinClassMap, fqName, this.c.getModule().getBuiltIns(), null, 4, null);
        if (mapJavaToKotlin$default != null) {
            return (!javaToKotlinClassMap.isReadOnly(mapJavaToKotlin$default) || !(javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE || argumentsMakeSenseOnlyForMutableContainer(javaClassifierType, mapJavaToKotlin$default))) ? mapJavaToKotlin$default : javaToKotlinClassMap.convertReadOnlyToMutable(mapJavaToKotlin$default);
        }
        return null;
    }

    private final boolean argumentsMakeSenseOnlyForMutableContainer(@NotNull JavaClassifierType javaClassifierType, ClassDescriptor classDescriptor) {
        boolean z = false;
        if (!JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1.INSTANCE.invoke((JavaType) CollectionsKt.lastOrNull(javaClassifierType.getTypeArguments()))) {
            return false;
        }
        TypeConstructor typeConstructor = JavaToKotlinClassMap.INSTANCE.convertReadOnlyToMutable(classDescriptor).getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "JavaToKotlinClassMap.con…         .typeConstructor");
        List parameters = typeConstructor.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "JavaToKotlinClassMap.con…ypeConstructor.parameters");
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) CollectionsKt.lastOrNull(parameters);
        if (typeParameterDescriptor != null) {
            Variance variance = typeParameterDescriptor.getVariance();
            if (variance != null) {
                Intrinsics.checkExpressionValueIsNotNull(variance, "JavaToKotlinClassMap.con….variance ?: return false");
                if (variance != Variance.OUT_VARIANCE) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    private final List<TypeProjection> computeArguments(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, TypeConstructor typeConstructor) {
        JavaTypeAttributes javaTypeAttributes2;
        boolean isRaw = javaClassifierType.isRaw();
        boolean z = isRaw || (javaClassifierType.getTypeArguments().isEmpty() && !typeConstructor.getParameters().isEmpty());
        List parameters = typeConstructor.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
        if (z) {
            Iterable<TypeParameterDescriptor> iterable = parameters;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (TypeParameterDescriptor typeParameterDescriptor : iterable) {
                StorageManager storageManager = this.c.getStorageManager();
                JavaTypeResolver$computeArguments$$inlined$map$lambda$1 javaTypeResolver$computeArguments$$inlined$map$lambda$1 = new JavaTypeResolver$computeArguments$$inlined$map$lambda$1(typeParameterDescriptor, this, javaTypeAttributes, typeConstructor, isRaw);
                LazyWrappedType lazyWrappedType = new LazyWrappedType(storageManager, javaTypeResolver$computeArguments$$inlined$map$lambda$1);
                RawSubstitution rawSubstitution = RawSubstitution.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);
                if (isRaw) {
                    javaTypeAttributes2 = javaTypeAttributes;
                } else {
                    javaTypeAttributes2 = javaTypeAttributes.withFlexibility(JavaTypeFlexibility.INFLEXIBLE);
                }
                arrayList.add(rawSubstitution.computeProjection(typeParameterDescriptor, javaTypeAttributes2, lazyWrappedType));
            }
            return CollectionsKt.toList((List) arrayList);
        } else if (parameters.size() != javaClassifierType.getTypeArguments().size()) {
            Iterable<TypeParameterDescriptor> iterable2 = parameters;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (TypeParameterDescriptor typeParameterDescriptor2 : iterable2) {
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor2, "p");
                arrayList2.add(new TypeProjectionImpl(ErrorUtils.createErrorType(typeParameterDescriptor2.getName().asString())));
            }
            return CollectionsKt.toList((List) arrayList2);
        } else {
            Iterable<IndexedValue> withIndex = CollectionsKt.withIndex(javaClassifierType.getTypeArguments());
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(withIndex, 10));
            for (IndexedValue indexedValue : withIndex) {
                int component1 = indexedValue.component1();
                JavaType javaType = (JavaType) indexedValue.component2();
                boolean z2 = component1 < parameters.size();
                if (!_Assertions.ENABLED || z2) {
                    TypeParameterDescriptor typeParameterDescriptor3 = (TypeParameterDescriptor) parameters.get(component1);
                    JavaTypeAttributes attributes$default = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null);
                    Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor3, MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);
                    arrayList3.add(transformToTypeProjection(javaType, attributes$default, typeParameterDescriptor3));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Argument index should be less then type parameters count, but ");
                    sb.append(component1);
                    sb.append(" > ");
                    sb.append(parameters.size());
                    throw new AssertionError(sb.toString());
                }
            }
            return CollectionsKt.toList((List) arrayList3);
        }
    }

    private final TypeProjection transformToTypeProjection(JavaType javaType, JavaTypeAttributes javaTypeAttributes, TypeParameterDescriptor typeParameterDescriptor) {
        if (!(javaType instanceof JavaWildcardType)) {
            return new TypeProjectionImpl(Variance.INVARIANT, transformJavaType(javaType, javaTypeAttributes));
        }
        JavaWildcardType javaWildcardType = (JavaWildcardType) javaType;
        JavaType bound = javaWildcardType.getBound();
        Variance variance = javaWildcardType.isExtends() ? Variance.OUT_VARIANCE : Variance.IN_VARIANCE;
        if (bound == null || isConflictingArgumentFor(variance, typeParameterDescriptor)) {
            return JavaTypeResolverKt.makeStarProjection(typeParameterDescriptor, javaTypeAttributes);
        }
        return TypeUtilsKt.createProjection(transformJavaType(bound, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null)), variance, typeParameterDescriptor);
    }

    private final boolean isConflictingArgumentFor(@NotNull Variance variance, TypeParameterDescriptor typeParameterDescriptor) {
        boolean z = false;
        if (typeParameterDescriptor.getVariance() == Variance.INVARIANT) {
            return false;
        }
        if (variance != typeParameterDescriptor.getVariance()) {
            z = true;
        }
        return z;
    }

    private final boolean isNullable(@NotNull JavaTypeAttributes javaTypeAttributes) {
        boolean z = false;
        if (javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND) {
            return false;
        }
        if (!javaTypeAttributes.isForAnnotationParameter() && javaTypeAttributes.getHowThisTypeIsUsed() != TypeUsage.SUPERTYPE) {
            z = true;
        }
        return z;
    }
}
