package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
public final class AbstractBinaryClassAnnotationAndConstantLoader$loadClassAnnotations$1 implements AnnotationVisitor {
    final /* synthetic */ ArrayList $result;
    final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader this$0;

    public void visitEnd() {
    }

    AbstractBinaryClassAnnotationAndConstantLoader$loadClassAnnotations$1(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, ArrayList arrayList) {
        this.this$0 = abstractBinaryClassAnnotationAndConstantLoader;
        this.$result = arrayList;
    }

    @Nullable
    public AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Intrinsics.checkParameterIsNotNull(sourceElement, "source");
        return this.this$0.loadAnnotationIfNotSpecial(classId, sourceElement, this.$result);
    }
}
