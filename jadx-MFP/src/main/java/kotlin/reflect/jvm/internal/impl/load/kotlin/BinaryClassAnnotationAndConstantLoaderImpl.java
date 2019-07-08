package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.myfitnesspal.shared.constants.Constants.ABTest.SmartWaterPhase1;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.ClassLiteralId;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationDeserializer;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BinaryClassAnnotationAndConstantLoaderImpl.kt */
public final class BinaryClassAnnotationAndConstantLoaderImpl extends AbstractBinaryClassAnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> {
    private final AnnotationDeserializer annotationDeserializer = new AnnotationDeserializer(this.module, this.notFoundClasses);
    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    public BinaryClassAnnotationAndConstantLoaderImpl(@NotNull ModuleDescriptor moduleDescriptor, @NotNull NotFoundClasses notFoundClasses2, @NotNull StorageManager storageManager, @NotNull KotlinClassFinder kotlinClassFinder) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses2, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(kotlinClassFinder, "kotlinClassFinder");
        super(storageManager, kotlinClassFinder);
        this.module = moduleDescriptor;
        this.notFoundClasses = notFoundClasses2;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public AnnotationDescriptor loadTypeAnnotation(@NotNull Annotation annotation, @NotNull NameResolver nameResolver) {
        Intrinsics.checkParameterIsNotNull(annotation, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        return this.annotationDeserializer.deserializeAnnotation(annotation, nameResolver);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ConstantValue<?> loadConstant(@NotNull String str, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(str, "desc");
        Intrinsics.checkParameterIsNotNull(obj, "initializer");
        boolean z = false;
        if (StringsKt.contains$default((CharSequence) "ZBCS", (CharSequence) str, false, 2, (Object) null)) {
            int intValue = ((Integer) obj).intValue();
            int hashCode = str.hashCode();
            if (hashCode != 83) {
                if (hashCode != 90) {
                    switch (hashCode) {
                        case 66:
                            if (str.equals(SmartWaterPhase1.SPONSORED_WATER_ON_VARIANT)) {
                                obj = Byte.valueOf((byte) intValue);
                                break;
                            }
                            break;
                        case 67:
                            if (str.equals("C")) {
                                obj = Character.valueOf((char) intValue);
                                break;
                            }
                            break;
                    }
                } else if (str.equals("Z")) {
                    if (intValue != 0) {
                        z = true;
                    }
                    obj = Boolean.valueOf(z);
                }
            } else if (str.equals("S")) {
                obj = Short.valueOf((short) intValue);
            }
            throw new AssertionError(str);
        }
        return ConstantValueFactory.INSTANCE.createConstantValue(obj);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ConstantValue<?> transformToUnsignedConstant(@NotNull ConstantValue<?> constantValue) {
        Intrinsics.checkParameterIsNotNull(constantValue, "constant");
        if (constantValue instanceof ByteValue) {
            return new UByteValue<>(((Number) ((ByteValue) constantValue).getValue()).byteValue());
        }
        if (constantValue instanceof ShortValue) {
            return new UShortValue<>(((Number) ((ShortValue) constantValue).getValue()).shortValue());
        }
        if (constantValue instanceof IntValue) {
            return new UIntValue<>(((Number) ((IntValue) constantValue).getValue()).intValue());
        }
        return constantValue instanceof LongValue ? new ULongValue<>(((Number) ((LongValue) constantValue).getValue()).longValue()) : constantValue;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public AnnotationArgumentVisitor loadAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement, @NotNull List<AnnotationDescriptor> list) {
        Intrinsics.checkParameterIsNotNull(classId, "annotationClassId");
        Intrinsics.checkParameterIsNotNull(sourceElement, "source");
        Intrinsics.checkParameterIsNotNull(list, "result");
        return new BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1(this, resolveClass(classId), list, sourceElement);
    }

    /* access modifiers changed from: private */
    public final KClassValue toClassValue(@NotNull ClassLiteralId classLiteralId) {
        ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(this.module, classLiteralId.getClassId());
        if (findClassAcrossModuleDependencies == null) {
            return null;
        }
        SimpleType defaultType = findClassAcrossModuleDependencies.getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "classDescriptor.defaultType");
        int i = 0;
        int arrayNestedness = classLiteralId.getArrayNestedness();
        while (i < arrayNestedness) {
            SimpleType primitiveArrayKotlinTypeByPrimitiveKotlinType = i == 0 ? this.module.getBuiltIns().getPrimitiveArrayKotlinTypeByPrimitiveKotlinType(defaultType) : null;
            if (primitiveArrayKotlinTypeByPrimitiveKotlinType != null) {
                defaultType = primitiveArrayKotlinTypeByPrimitiveKotlinType;
            } else {
                defaultType = this.module.getBuiltIns().getArrayType(Variance.INVARIANT, defaultType);
            }
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "(if (i == 0) module.buil…e.INVARIANT, currentType)");
            i++;
        }
        ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.kClass.toSafe());
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB…FQ_NAMES.kClass.toSafe())");
        return new KClassValue(KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), resolveClass(classId), CollectionsKt.listOf(new TypeProjectionImpl(defaultType))));
    }

    private final ClassDescriptor resolveClass(ClassId classId) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId, this.notFoundClasses);
    }
}
