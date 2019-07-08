package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationMapper;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaAnnotations.kt */
final class LazyJavaAnnotations$annotationDescriptors$1 extends Lambda implements Function1<JavaAnnotation, AnnotationDescriptor> {
    final /* synthetic */ LazyJavaAnnotations this$0;

    LazyJavaAnnotations$annotationDescriptors$1(LazyJavaAnnotations lazyJavaAnnotations) {
        this.this$0 = lazyJavaAnnotations;
        super(1);
    }

    @Nullable
    public final AnnotationDescriptor invoke(@NotNull JavaAnnotation javaAnnotation) {
        Intrinsics.checkParameterIsNotNull(javaAnnotation, "annotation");
        return JavaAnnotationMapper.INSTANCE.mapOrResolveJavaAnnotation(javaAnnotation, this.this$0.c);
    }
}
