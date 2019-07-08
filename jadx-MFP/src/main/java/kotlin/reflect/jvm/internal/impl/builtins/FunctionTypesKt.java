package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory.Companion;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: functionTypes.kt */
public final class FunctionTypesKt {
    public static final boolean isFunctionType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor != null ? getFunctionalClassKind((DeclarationDescriptor) declarationDescriptor) : null) == Kind.Function;
    }

    public static final boolean isSuspendFunctionType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor != null ? getFunctionalClassKind((DeclarationDescriptor) declarationDescriptor) : null) == Kind.SuspendFunction;
    }

    public static final boolean isBuiltinFunctionalType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        Kind functionalClassKind = declarationDescriptor != null ? getFunctionalClassKind((DeclarationDescriptor) declarationDescriptor) : null;
        return functionalClassKind == Kind.Function || functionalClassKind == Kind.SuspendFunction;
    }

    public static final boolean isBuiltinExtensionFunctionalType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return isBuiltinFunctionalType(kotlinType) && isTypeAnnotatedWithExtensionFunctionType(kotlinType);
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(@NotNull KotlinType kotlinType) {
        Annotations annotations = kotlinType.getAnnotations();
        FqName fqName = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
        return annotations.findAnnotation(fqName) != null;
    }

    @Nullable
    public static final Kind getFunctionalClassKind(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "receiver$0");
        if ((declarationDescriptor instanceof ClassDescriptor) && KotlinBuiltIns.isUnderKotlinPackage(declarationDescriptor)) {
            return getFunctionalClassKind(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor));
        }
        return null;
    }

    private static final Kind getFunctionalClassKind(@NotNull FqNameUnsafe fqNameUnsafe) {
        if (!fqNameUnsafe.isSafe() || fqNameUnsafe.isRoot()) {
            return null;
        }
        Companion companion = BuiltInFictitiousFunctionClassFactory.Companion;
        String asString = fqNameUnsafe.shortName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "shortName().asString()");
        FqName parent = fqNameUnsafe.toSafe().parent();
        Intrinsics.checkExpressionValueIsNotNull(parent, "toSafe().parent()");
        return companion.getFunctionalClassKind(asString, parent);
    }

    @Nullable
    public static final KotlinType getReceiverTypeFromFunctionType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(kotlinType);
        if (_Assertions.ENABLED && !isBuiltinFunctionalType) {
            StringBuilder sb = new StringBuilder();
            sb.append("Not a function type: ");
            sb.append(kotlinType);
            throw new AssertionError(sb.toString());
        } else if (isTypeAnnotatedWithExtensionFunctionType(kotlinType)) {
            return ((TypeProjection) CollectionsKt.first(kotlinType.getArguments())).getType();
        } else {
            return null;
        }
    }

    @NotNull
    public static final KotlinType getReturnTypeFromFunctionType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(kotlinType);
        if (!_Assertions.ENABLED || isBuiltinFunctionalType) {
            KotlinType type = ((TypeProjection) CollectionsKt.last(kotlinType.getArguments())).getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "arguments.last().type");
            return type;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Not a function type: ");
        sb.append(kotlinType);
        throw new AssertionError(sb.toString());
    }

    @NotNull
    public static final List<TypeProjection> getValueParameterTypesFromFunctionType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(kotlinType);
        if (!_Assertions.ENABLED || isBuiltinFunctionalType) {
            List arguments = kotlinType.getArguments();
            int isBuiltinExtensionFunctionalType = isBuiltinExtensionFunctionalType(kotlinType);
            boolean z = true;
            int size = arguments.size() - 1;
            if (isBuiltinExtensionFunctionalType > size) {
                z = false;
            }
            if (!_Assertions.ENABLED || z) {
                return arguments.subList(isBuiltinExtensionFunctionalType, size);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Not an exact function type: ");
            sb.append(kotlinType);
            throw new AssertionError(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Not a function type: ");
        sb2.append(kotlinType);
        throw new AssertionError(sb2.toString());
    }

    @Nullable
    public static final Name extractParameterNameFromFunctionTypeArgument(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        Annotations annotations = kotlinType.getAnnotations();
        FqName fqName = KotlinBuiltIns.FQ_NAMES.parameterName;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.parameterName");
        AnnotationDescriptor findAnnotation = annotations.findAnnotation(fqName);
        if (findAnnotation == null) {
            return null;
        }
        Object singleOrNull = CollectionsKt.singleOrNull((Iterable<? extends T>) findAnnotation.getAllValueArguments().values());
        if (!(singleOrNull instanceof StringValue)) {
            singleOrNull = null;
        }
        StringValue stringValue = (StringValue) singleOrNull;
        if (stringValue != null) {
            String str = (String) stringValue.getValue();
            if (str != null) {
                if (!Name.isValidIdentifier(str)) {
                    str = null;
                }
                if (str != null) {
                    return Name.identifier(str);
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
        if (r2.isSpecial() == false) goto L_0x005b;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.List<kotlin.reflect.jvm.internal.impl.types.TypeProjection> getFunctionTypeArgumentProjections(@org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.types.KotlinType r10, @org.jetbrains.annotations.NotNull java.util.List<? extends kotlin.reflect.jvm.internal.impl.types.KotlinType> r11, @org.jetbrains.annotations.Nullable java.util.List<kotlin.reflect.jvm.internal.impl.name.Name> r12, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r13, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r14) {
        /*
            java.lang.String r0 = "parameterTypes"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "returnType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r0)
            java.lang.String r0 = "builtIns"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r11.size()
            r2 = 0
            r3 = 1
            if (r10 == 0) goto L_0x001b
            r4 = 1
            goto L_0x001c
        L_0x001b:
            r4 = 0
        L_0x001c:
            int r1 = r1 + r4
            int r1 = r1 + r3
            r0.<init>(r1)
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            r3 = 0
            if (r10 == 0) goto L_0x002c
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r10 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.asTypeProjection(r10)
            goto L_0x002d
        L_0x002c:
            r10 = r3
        L_0x002d:
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(r1, r10)
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Iterator r10 = r11.iterator()
        L_0x0036:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00a4
            java.lang.Object r11 = r10.next()
            int r4 = r2 + 1
            if (r2 >= 0) goto L_0x0047
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x0047:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r11 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r11
            if (r12 == 0) goto L_0x005a
            java.lang.Object r2 = r12.get(r2)
            kotlin.reflect.jvm.internal.impl.name.Name r2 = (kotlin.reflect.jvm.internal.impl.name.Name) r2
            if (r2 == 0) goto L_0x005a
            boolean r5 = r2.isSpecial()
            if (r5 != 0) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            r2 = r3
        L_0x005b:
            if (r2 == 0) goto L_0x009b
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor r5 = new kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns$FqNames r6 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.FQ_NAMES
            kotlin.reflect.jvm.internal.impl.name.FqName r6 = r6.parameterName
            java.lang.String r7 = "KotlinBuiltIns.FQ_NAMES.parameterName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            java.lang.String r7 = "name"
            kotlin.reflect.jvm.internal.impl.name.Name r7 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r7)
            kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue r8 = new kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue
            java.lang.String r2 = r2.asString()
            java.lang.String r9 = "name.asString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r9)
            r8.<init>(r2)
            kotlin.Pair r2 = kotlin.TuplesKt.to(r7, r8)
            java.util.Map r2 = kotlin.collections.MapsKt.mapOf(r2)
            r5.<init>(r14, r6, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r2 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = r11.getAnnotations()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.List r5 = kotlin.collections.CollectionsKt.plus(r6, r5)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r2 = r2.create(r5)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r11 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.replaceAnnotations(r11, r2)
        L_0x009b:
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r11 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.asTypeProjection(r11)
            r1.add(r11)
            r2 = r4
            goto L_0x0036
        L_0x00a4:
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r10 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.asTypeProjection(r13)
            r0.add(r10)
            java.util.List r0 = (java.util.List) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt.getFunctionTypeArgumentProjections(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.util.List, java.util.List, kotlin.reflect.jvm.internal.impl.types.KotlinType, kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns):java.util.List");
    }

    @NotNull
    @JvmOverloads
    public static /* synthetic */ SimpleType createFunctionType$default(KotlinBuiltIns kotlinBuiltIns, Annotations annotations, KotlinType kotlinType, List list, List list2, KotlinType kotlinType2, boolean z, int i, Object obj) {
        return createFunctionType(kotlinBuiltIns, annotations, kotlinType, list, list2, kotlinType2, (i & 64) != 0 ? false : z);
    }

    @NotNull
    @JvmOverloads
    public static final SimpleType createFunctionType(@NotNull KotlinBuiltIns kotlinBuiltIns, @NotNull Annotations annotations, @Nullable KotlinType kotlinType, @NotNull List<? extends KotlinType> list, @Nullable List<Name> list2, @NotNull KotlinType kotlinType2, boolean z) {
        Intrinsics.checkParameterIsNotNull(kotlinBuiltIns, "builtIns");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(list, "parameterTypes");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "returnType");
        List functionTypeArgumentProjections = getFunctionTypeArgumentProjections(kotlinType, list, list2, kotlinType2, kotlinBuiltIns);
        int size = list.size();
        if (kotlinType != null) {
            size++;
        }
        ClassDescriptor suspendFunction = z ? kotlinBuiltIns.getSuspendFunction(size) : kotlinBuiltIns.getFunction(size);
        Intrinsics.checkExpressionValueIsNotNull(suspendFunction, "if (suspendFunction) bui…tFunction(parameterCount)");
        if (kotlinType != null) {
            FqName fqName = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
            if (annotations.findAnnotation(fqName) == null) {
                Annotations.Companion companion = Annotations.Companion;
                Iterable iterable = annotations;
                FqName fqName2 = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
                Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
                annotations = companion.create(CollectionsKt.plus(iterable, new BuiltInAnnotationDescriptor(kotlinBuiltIns, fqName2, MapsKt.emptyMap())));
            }
        }
        return KotlinTypeFactory.simpleNotNullType(annotations, suspendFunction, functionTypeArgumentProjections);
    }
}
