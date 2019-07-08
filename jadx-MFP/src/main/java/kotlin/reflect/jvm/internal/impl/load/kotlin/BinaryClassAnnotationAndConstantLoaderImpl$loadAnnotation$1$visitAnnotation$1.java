package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.ClassLiteralId;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BinaryClassAnnotationAndConstantLoaderImpl.kt */
public final class BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitAnnotation$1 implements AnnotationArgumentVisitor {
    private final /* synthetic */ AnnotationArgumentVisitor $$delegate_0;
    final /* synthetic */ ArrayList $list;
    final /* synthetic */ Name $name;
    final /* synthetic */ AnnotationArgumentVisitor $visitor;
    final /* synthetic */ BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1 this$0;

    public void visit(@Nullable Name name, @Nullable Object obj) {
        this.$$delegate_0.visit(name, obj);
    }

    @Nullable
    public AnnotationArgumentVisitor visitAnnotation(@NotNull Name name, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        return this.$$delegate_0.visitAnnotation(name, classId);
    }

    @Nullable
    public AnnotationArrayArgumentVisitor visitArray(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return this.$$delegate_0.visitArray(name);
    }

    public void visitClassLiteral(@NotNull Name name, @NotNull ClassLiteralId classLiteralId) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(classLiteralId, "classLiteralId");
        this.$$delegate_0.visitClassLiteral(name, classLiteralId);
    }

    public void visitEnum(@NotNull Name name, @NotNull ClassId classId, @NotNull Name name2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(classId, "enumClassId");
        Intrinsics.checkParameterIsNotNull(name2, "enumEntryName");
        this.$$delegate_0.visitEnum(name, classId, name2);
    }

    BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitAnnotation$1(BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1 binaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1, AnnotationArgumentVisitor annotationArgumentVisitor, Name name, ArrayList arrayList) {
        this.this$0 = binaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1;
        this.$visitor = annotationArgumentVisitor;
        this.$name = name;
        this.$list = arrayList;
        this.$$delegate_0 = annotationArgumentVisitor;
    }

    public void visitEnd() {
        this.$visitor.visitEnd();
        this.this$0.arguments.put(this.$name, new AnnotationValue((AnnotationDescriptor) CollectionsKt.single((List<? extends T>) this.$list)));
    }
}
