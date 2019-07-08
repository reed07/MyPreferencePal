package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.PlatformMutabilityMapping;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeSignatureMapping.kt */
public final class TypeSignatureMappingKt {
    private static final <T> T boxTypeIfNeeded(@NotNull JvmTypeFactory<T> jvmTypeFactory, T t, boolean z) {
        return z ? jvmTypeFactory.boxType(t) : t;
    }

    @NotNull
    public static /* synthetic */ Object mapType$default(KotlinType kotlinType, JvmTypeFactory jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, Function3 function3, boolean z, int i, Object obj) {
        return mapType(kotlinType, jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, (i & 32) != 0 ? FunctionsKt.getDO_NOTHING_3() : function3, z);
    }

    @NotNull
    public static final <T> T mapType(@NotNull KotlinType kotlinType, @NotNull JvmTypeFactory<T> jvmTypeFactory, @NotNull TypeMappingMode typeMappingMode, @NotNull TypeMappingConfiguration<? extends T> typeMappingConfiguration, @Nullable JvmDescriptorTypeWriter<T> jvmDescriptorTypeWriter, @NotNull Function3<? super KotlinType, ? super T, ? super TypeMappingMode, Unit> function3, boolean z) {
        T t;
        Object obj;
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(jvmTypeFactory, "factory");
        Intrinsics.checkParameterIsNotNull(typeMappingMode, InternalAvidAdSessionContext.CONTEXT_MODE);
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        Intrinsics.checkParameterIsNotNull(function3, "writeGenericType");
        if (FunctionTypesKt.isSuspendFunctionType(kotlinType)) {
            return mapType(SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(kotlinType, typeMappingConfiguration.releaseCoroutines()), jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, function3, z);
        }
        Object mapBuiltInType = mapBuiltInType(kotlinType, jvmTypeFactory, typeMappingMode);
        if (mapBuiltInType != null) {
            T boxTypeIfNeeded = boxTypeIfNeeded(jvmTypeFactory, mapBuiltInType, typeMappingMode.getNeedPrimitiveBoxing());
            function3.invoke(kotlinType, boxTypeIfNeeded, typeMappingMode);
            return boxTypeIfNeeded;
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            Collection supertypes = ((IntersectionTypeConstructor) constructor).getSupertypes();
            Intrinsics.checkExpressionValueIsNotNull(supertypes, "constructor.supertypes");
            return mapType(TypeUtilsKt.replaceArgumentsWithStarProjections(typeMappingConfiguration.commonSupertype(supertypes)), jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, function3, z);
        }
        ClassifierDescriptor declarationDescriptor = constructor.getDeclarationDescriptor();
        if (declarationDescriptor != null) {
            Intrinsics.checkExpressionValueIsNotNull(declarationDescriptor, "constructor.declarationD…structor of $kotlinType\")");
            if (ErrorUtils.isError(declarationDescriptor)) {
                T createObjectType = jvmTypeFactory.createObjectType("error/NonExistentClass");
                if (declarationDescriptor != null) {
                    typeMappingConfiguration.processErrorType(kotlinType, (ClassDescriptor) declarationDescriptor);
                    if (jvmDescriptorTypeWriter != null) {
                        jvmDescriptorTypeWriter.writeClass(createObjectType);
                    }
                    return createObjectType;
                }
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            boolean z2 = declarationDescriptor instanceof ClassDescriptor;
            if (!z2 || !KotlinBuiltIns.isArray(kotlinType)) {
                if (z2) {
                    ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
                    if (classDescriptor.isInline() && !typeMappingMode.getNeedInlineClassWrapping()) {
                        KotlinType computeExpandedTypeForInlineClass = computeExpandedTypeForInlineClass(kotlinType);
                        if (computeExpandedTypeForInlineClass != null) {
                            return mapType(computeExpandedTypeForInlineClass, jvmTypeFactory, typeMappingMode.wrapInlineClassesMode(), typeMappingConfiguration, jvmDescriptorTypeWriter, function3, z);
                        }
                    }
                    if (!typeMappingMode.isForAnnotationParameter() || !KotlinBuiltIns.isKClass(classDescriptor)) {
                        ClassDescriptor original = classDescriptor.getOriginal();
                        Intrinsics.checkExpressionValueIsNotNull(original, "descriptor.original");
                        T predefinedTypeForClass = typeMappingConfiguration.getPredefinedTypeForClass(original);
                        if (predefinedTypeForClass != null) {
                            t = predefinedTypeForClass;
                        } else {
                            if (classDescriptor.getKind() == ClassKind.ENUM_ENTRY) {
                                DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
                                if (containingDeclaration != null) {
                                    classDescriptor = (ClassDescriptor) containingDeclaration;
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                                }
                            }
                            ClassDescriptor original2 = classDescriptor.getOriginal();
                            Intrinsics.checkExpressionValueIsNotNull(original2, "enumClassIfEnumEntry.original");
                            t = jvmTypeFactory.createObjectType(computeInternalName(original2, typeMappingConfiguration, z));
                        }
                    } else {
                        t = jvmTypeFactory.getJavaLangClassType();
                    }
                    function3.invoke(kotlinType, t, typeMappingMode);
                    return t;
                } else if (declarationDescriptor instanceof TypeParameterDescriptor) {
                    T mapType = mapType(getRepresentativeUpperBound((TypeParameterDescriptor) declarationDescriptor), jvmTypeFactory, typeMappingMode, typeMappingConfiguration, null, FunctionsKt.getDO_NOTHING_3(), z);
                    if (jvmDescriptorTypeWriter != null) {
                        Name name = declarationDescriptor.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.getName()");
                        jvmDescriptorTypeWriter.writeTypeVariable(name, mapType);
                    }
                    return mapType;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unknown type ");
                    sb.append(kotlinType);
                    throw new UnsupportedOperationException(sb.toString());
                }
            } else if (kotlinType.getArguments().size() == 1) {
                TypeProjection typeProjection = (TypeProjection) kotlinType.getArguments().get(0);
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "memberProjection.type");
                if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                    obj = jvmTypeFactory.createObjectType("java/lang/Object");
                    if (jvmDescriptorTypeWriter != null) {
                        jvmDescriptorTypeWriter.writeArrayType();
                        jvmDescriptorTypeWriter.writeClass(obj);
                        jvmDescriptorTypeWriter.writeArrayEnd();
                    }
                } else {
                    if (jvmDescriptorTypeWriter != null) {
                        jvmDescriptorTypeWriter.writeArrayType();
                    }
                    Variance projectionKind = typeProjection.getProjectionKind();
                    Intrinsics.checkExpressionValueIsNotNull(projectionKind, "memberProjection.projectionKind");
                    obj = mapType(type, jvmTypeFactory, typeMappingMode.toGenericArgumentMode(projectionKind), typeMappingConfiguration, jvmDescriptorTypeWriter, function3, z);
                    if (jvmDescriptorTypeWriter != null) {
                        jvmDescriptorTypeWriter.writeArrayEnd();
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("[");
                sb2.append(jvmTypeFactory.toString(obj));
                return jvmTypeFactory.createFromString(sb2.toString());
            } else {
                throw new UnsupportedOperationException("arrays must have one type argument");
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("no descriptor for type constructor of ");
            sb3.append(kotlinType);
            throw new UnsupportedOperationException(sb3.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        if ((r2 instanceof kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor) == false) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean hasVoidReturnType(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r2) {
        /*
            java.lang.String r0 = "descriptor"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            boolean r0 = r2 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
            r1 = 1
            if (r0 == 0) goto L_0x000b
            return r1
        L_0x000b:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r2.getReturnType()
            if (r0 != 0) goto L_0x0014
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0014:
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isUnit(r0)
            if (r0 == 0) goto L_0x002e
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r2.getReturnType()
            if (r0 != 0) goto L_0x0023
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0023:
            boolean r0 = kotlin.reflect.jvm.internal.impl.types.TypeUtils.isNullableType(r0)
            if (r0 != 0) goto L_0x002e
            boolean r2 = r2 instanceof kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor
            if (r2 != 0) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            r1 = 0
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.TypeSignatureMappingKt.hasVoidReturnType(kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor):boolean");
    }

    private static final String continuationInternalName(boolean z) {
        FqName fqName;
        if (z) {
            fqName = DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE;
        } else {
            fqName = DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL;
        }
        JvmClassName byClassId = JvmClassName.byClassId(ClassId.topLevel(fqName));
        Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(ClassId.topLevel(fqName))");
        String internalName = byClassId.getInternalName();
        Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(C…vel(fqName)).internalName");
        return internalName;
    }

    private static final <T> T mapBuiltInType(KotlinType kotlinType, JvmTypeFactory<T> jvmTypeFactory, TypeMappingMode typeMappingMode) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
        if (classDescriptor == null) {
            return null;
        }
        boolean z = false;
        if (classDescriptor == SuspendFunctionTypesKt.getFAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL()) {
            return jvmTypeFactory.createObjectType(continuationInternalName(false));
        }
        if (Intrinsics.areEqual((Object) classDescriptor, (Object) SuspendFunctionTypesKt.getFAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE())) {
            return jvmTypeFactory.createObjectType(continuationInternalName(true));
        }
        DeclarationDescriptor declarationDescriptor2 = classDescriptor;
        PrimitiveType primitiveType = KotlinBuiltIns.getPrimitiveType(declarationDescriptor2);
        if (primitiveType != null) {
            JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(primitiveType);
            Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType, "JvmPrimitiveType.get(primitiveType)");
            String desc = jvmPrimitiveType.getDesc();
            Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.get(primitiveType).desc");
            Object createFromString = jvmTypeFactory.createFromString(desc);
            if (TypeUtils.isNullableType(kotlinType) || TypeEnhancementKt.hasEnhancedNullability(kotlinType)) {
                z = true;
            }
            return boxTypeIfNeeded(jvmTypeFactory, createFromString, z);
        }
        PrimitiveType primitiveArrayType = KotlinBuiltIns.getPrimitiveArrayType(declarationDescriptor2);
        if (primitiveArrayType != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            JvmPrimitiveType jvmPrimitiveType2 = JvmPrimitiveType.get(primitiveArrayType);
            Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType2, "JvmPrimitiveType.get(arrayElementType)");
            sb.append(jvmPrimitiveType2.getDesc());
            return jvmTypeFactory.createFromString(sb.toString());
        }
        if (KotlinBuiltIns.isUnderKotlinPackage(declarationDescriptor2)) {
            ClassId mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor2));
            if (mapKotlinToJava != null) {
                if (!typeMappingMode.getKotlinCollectionsToJavaCollections()) {
                    Iterable mutabilityMappings = JavaToKotlinClassMap.INSTANCE.getMutabilityMappings();
                    if (!(mutabilityMappings instanceof Collection) || !((Collection) mutabilityMappings).isEmpty()) {
                        Iterator it = mutabilityMappings.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (Intrinsics.areEqual((Object) ((PlatformMutabilityMapping) it.next()).getJavaClass(), (Object) mapKotlinToJava)) {
                                    z = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    if (z) {
                        return null;
                    }
                }
                JvmClassName byClassId = JvmClassName.byClassId(mapKotlinToJava);
                Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(classId)");
                String internalName = byClassId.getInternalName();
                Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(classId).internalName");
                return jvmTypeFactory.createObjectType(internalName);
            }
        }
        return null;
    }

    @Nullable
    public static final KotlinType computeExpandedTypeForInlineClass(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "inlineClassType");
        return computeExpandedTypeInner(kotlinType, new HashSet());
    }

    @Nullable
    public static final KotlinType computeExpandedTypeInner(@NotNull KotlinType kotlinType, @NotNull HashSet<ClassifierDescriptor> hashSet) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(hashSet, "visitedClassifiers");
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor != null) {
            Intrinsics.checkExpressionValueIsNotNull(declarationDescriptor, "kotlinType.constructor.d…n expected: $kotlinType\")");
            if (!hashSet.add(declarationDescriptor)) {
                return null;
            }
            if (declarationDescriptor instanceof TypeParameterDescriptor) {
                KotlinType computeExpandedTypeInner = computeExpandedTypeInner(getRepresentativeUpperBound((TypeParameterDescriptor) declarationDescriptor), hashSet);
                kotlinType = computeExpandedTypeInner != null ? (KotlinTypeKt.isNullable(computeExpandedTypeInner) || !kotlinType.isMarkedNullable()) ? computeExpandedTypeInner : TypeUtilsKt.makeNullable(computeExpandedTypeInner) : null;
            } else if ((declarationDescriptor instanceof ClassDescriptor) && ((ClassDescriptor) declarationDescriptor).isInline()) {
                KotlinType substitutedUnderlyingType = InlineClassesUtilsKt.substitutedUnderlyingType(kotlinType);
                if (substitutedUnderlyingType == null) {
                    return null;
                }
                KotlinType computeExpandedTypeInner2 = computeExpandedTypeInner(substitutedUnderlyingType, hashSet);
                if (computeExpandedTypeInner2 == null) {
                    return null;
                }
                if (!kotlinType.isMarkedNullable()) {
                    kotlinType = computeExpandedTypeInner2;
                } else if (!KotlinTypeKt.isNullable(computeExpandedTypeInner2) && !KotlinBuiltIns.isPrimitiveType(computeExpandedTypeInner2)) {
                    kotlinType = TypeUtilsKt.makeNullable(computeExpandedTypeInner2);
                }
            }
            return kotlinType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Type with a declaration expected: ");
        sb.append(kotlinType);
        throw new AssertionError(sb.toString());
    }

    @NotNull
    public static /* synthetic */ String computeInternalName$default(ClassDescriptor classDescriptor, TypeMappingConfiguration typeMappingConfiguration, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingConfiguration = TypeMappingConfigurationImpl.INSTANCE;
        }
        return computeInternalName(classDescriptor, typeMappingConfiguration, z);
    }

    @NotNull
    public static final String computeInternalName(@NotNull ClassDescriptor classDescriptor, @NotNull TypeMappingConfiguration<?> typeMappingConfiguration, boolean z) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "klass");
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        DeclarationDescriptor container = z ? getContainer(classDescriptor.getContainingDeclaration()) : classDescriptor.getContainingDeclaration();
        Name safeIdentifier = SpecialNames.safeIdentifier(classDescriptor.getName());
        Intrinsics.checkExpressionValueIsNotNull(safeIdentifier, "SpecialNames.safeIdentifier(klass.name)");
        String identifier = safeIdentifier.getIdentifier();
        Intrinsics.checkExpressionValueIsNotNull(identifier, "SpecialNames.safeIdentifier(klass.name).identifier");
        if (container instanceof PackageFragmentDescriptor) {
            FqName fqName = ((PackageFragmentDescriptor) container).getFqName();
            if (!fqName.isRoot()) {
                StringBuilder sb = new StringBuilder();
                String asString = fqName.asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "fqName.asString()");
                sb.append(StringsKt.replace$default(asString, '.', '/', false, 4, (Object) null));
                sb.append('/');
                sb.append(identifier);
                identifier = sb.toString();
            }
            return identifier;
        }
        ClassDescriptor classDescriptor2 = (ClassDescriptor) (!(container instanceof ClassDescriptor) ? null : container);
        if (classDescriptor2 != null) {
            String predefinedInternalNameForClass = typeMappingConfiguration.getPredefinedInternalNameForClass(classDescriptor2);
            if (predefinedInternalNameForClass == null) {
                predefinedInternalNameForClass = computeInternalName(classDescriptor2, typeMappingConfiguration, z);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(predefinedInternalNameForClass);
            sb2.append(Typography.dollar);
            sb2.append(identifier);
            return sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Unexpected container: ");
        sb3.append(container);
        sb3.append(" for ");
        sb3.append(classDescriptor);
        throw new IllegalArgumentException(sb3.toString());
    }

    private static final DeclarationDescriptor getContainer(DeclarationDescriptor declarationDescriptor) {
        DeclarationDescriptor declarationDescriptor2 = (ClassDescriptor) (!(declarationDescriptor instanceof ClassDescriptor) ? null : declarationDescriptor);
        if (declarationDescriptor2 == null) {
            declarationDescriptor2 = (PackageFragmentDescriptor) (!(declarationDescriptor instanceof PackageFragmentDescriptor) ? null : declarationDescriptor);
        }
        DeclarationDescriptor declarationDescriptor3 = declarationDescriptor2;
        if (declarationDescriptor3 != null) {
            return declarationDescriptor3;
        }
        if (declarationDescriptor != null) {
            return getContainer(declarationDescriptor.getContainingDeclaration());
        }
        return null;
    }

    @NotNull
    public static final KotlinType getRepresentativeUpperBound(@NotNull TypeParameterDescriptor typeParameterDescriptor) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "descriptor");
        List upperBounds = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "descriptor.upperBounds");
        boolean z = !upperBounds.isEmpty();
        if (!_Assertions.ENABLED || z) {
            Iterator it = upperBounds.iterator();
            while (true) {
                ClassDescriptor classDescriptor = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                ClassifierDescriptor declarationDescriptor = ((KotlinType) obj).getConstructor().getDeclarationDescriptor();
                if (declarationDescriptor instanceof ClassDescriptor) {
                    classDescriptor = declarationDescriptor;
                }
                ClassDescriptor classDescriptor2 = classDescriptor;
                boolean z2 = false;
                if (!(classDescriptor2 == null || classDescriptor2.getKind() == ClassKind.INTERFACE || classDescriptor2.getKind() == ClassKind.ANNOTATION_CLASS)) {
                    z2 = true;
                    continue;
                }
                if (z2) {
                    break;
                }
            }
            KotlinType kotlinType = (KotlinType) obj;
            if (kotlinType != null) {
                return kotlinType;
            }
            Object first = CollectionsKt.first(upperBounds);
            Intrinsics.checkExpressionValueIsNotNull(first, "upperBounds.first()");
            return (KotlinType) first;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Upper bounds should not be empty: ");
        sb.append(typeParameterDescriptor);
        throw new AssertionError(sb.toString());
    }
}
