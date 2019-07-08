package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
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
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BinaryClassAnnotationAndConstantLoaderImpl.kt */
public final class BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitArray$1 implements AnnotationArrayArgumentVisitor {
    final /* synthetic */ Name $name;
    private final ArrayList<ConstantValue<?>> elements = new ArrayList<>();
    final /* synthetic */ BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1 this$0;

    BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitArray$1(BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1 binaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1, Name name) {
        this.this$0 = binaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1;
        this.$name = name;
    }

    public void visit(@Nullable Object obj) {
        this.elements.add(this.this$0.createConstant(this.$name, obj));
    }

    public void visitEnum(@NotNull ClassId classId, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(classId, "enumClassId");
        Intrinsics.checkParameterIsNotNull(name, "enumEntryName");
        this.elements.add(new EnumValue(classId, name));
    }

    public void visitClassLiteral(@NotNull ClassLiteralId classLiteralId) {
        ConstantValue constantValue;
        Intrinsics.checkParameterIsNotNull(classLiteralId, "classLiteralId");
        ArrayList<ConstantValue<?>> arrayList = this.elements;
        KClassValue access$toClassValue = this.this$0.this$0.toClassValue(classLiteralId);
        if (access$toClassValue != null) {
            constantValue = access$toClassValue;
        } else {
            Companion companion = ErrorValue.Companion;
            StringBuilder sb = new StringBuilder();
            sb.append("Error array element value of annotation argument: ");
            sb.append(this.$name);
            sb.append(": class ");
            sb.append(classLiteralId.getClassId().asSingleFqName());
            sb.append(" not found");
            constantValue = companion.create(sb.toString());
        }
        arrayList.add(constantValue);
    }

    public void visitEnd() {
        ValueParameterDescriptor annotationParameterByName = DescriptorResolverUtils.getAnnotationParameterByName(this.$name, this.this$0.$annotationClass);
        if (annotationParameterByName != null) {
            Map access$getArguments$p = this.this$0.arguments;
            Name name = this.$name;
            ConstantValueFactory constantValueFactory = ConstantValueFactory.INSTANCE;
            List compact = CollectionsKt.compact(this.elements);
            KotlinType type = annotationParameterByName.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "parameter.type");
            access$getArguments$p.put(name, constantValueFactory.createArrayValue(compact, type));
        }
    }
}
