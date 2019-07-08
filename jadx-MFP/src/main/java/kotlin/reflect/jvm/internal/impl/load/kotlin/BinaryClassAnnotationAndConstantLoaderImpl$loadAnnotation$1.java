package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.ClassLiteralId;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue.Companion;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BinaryClassAnnotationAndConstantLoaderImpl.kt */
public final class BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1 implements AnnotationArgumentVisitor {
    final /* synthetic */ ClassDescriptor $annotationClass;
    final /* synthetic */ List $result;
    final /* synthetic */ SourceElement $source;
    /* access modifiers changed from: private */
    public final HashMap<Name, ConstantValue<?>> arguments = new HashMap<>();
    final /* synthetic */ BinaryClassAnnotationAndConstantLoaderImpl this$0;

    BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1(BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl, ClassDescriptor classDescriptor, List list, SourceElement sourceElement) {
        this.this$0 = binaryClassAnnotationAndConstantLoaderImpl;
        this.$annotationClass = classDescriptor;
        this.$result = list;
        this.$source = sourceElement;
    }

    public void visit(@Nullable Name name, @Nullable Object obj) {
        if (name != null) {
            this.arguments.put(name, createConstant(name, obj));
        }
    }

    public void visitClassLiteral(@NotNull Name name, @NotNull ClassLiteralId classLiteralId) {
        ConstantValue constantValue;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(classLiteralId, "classLiteralId");
        Map map = this.arguments;
        KClassValue access$toClassValue = this.this$0.toClassValue(classLiteralId);
        if (access$toClassValue != null) {
            constantValue = access$toClassValue;
        } else {
            Companion companion = ErrorValue.Companion;
            StringBuilder sb = new StringBuilder();
            sb.append("Error value of annotation argument: ");
            sb.append(name);
            sb.append(": class ");
            sb.append(classLiteralId.getClassId().asSingleFqName());
            sb.append(" not found");
            constantValue = companion.create(sb.toString());
        }
        map.put(name, constantValue);
    }

    public void visitEnum(@NotNull Name name, @NotNull ClassId classId, @NotNull Name name2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(classId, "enumClassId");
        Intrinsics.checkParameterIsNotNull(name2, "enumEntryName");
        this.arguments.put(name, new EnumValue(classId, name2));
    }

    @Nullable
    public AnnotationArrayArgumentVisitor visitArray(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitArray$1(this, name);
    }

    @Nullable
    public AnnotationArgumentVisitor visitAnnotation(@NotNull Name name, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        ArrayList arrayList = new ArrayList();
        BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl = this.this$0;
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        AnnotationArgumentVisitor loadAnnotation = binaryClassAnnotationAndConstantLoaderImpl.loadAnnotation(classId, sourceElement, arrayList);
        if (loadAnnotation == null) {
            Intrinsics.throwNpe();
        }
        return new BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitAnnotation$1(this, loadAnnotation, name, arrayList);
    }

    public void visitEnd() {
        this.$result.add(new AnnotationDescriptorImpl(this.$annotationClass.getDefaultType(), this.arguments, this.$source));
    }

    /* access modifiers changed from: private */
    public final ConstantValue<?> createConstant(Name name, Object obj) {
        ConstantValue<?> createConstantValue = ConstantValueFactory.INSTANCE.createConstantValue(obj);
        if (createConstantValue != null) {
            return createConstantValue;
        }
        Companion companion = ErrorValue.Companion;
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported annotation argument: ");
        sb.append(name);
        return companion.create(sb.toString());
    }
}
