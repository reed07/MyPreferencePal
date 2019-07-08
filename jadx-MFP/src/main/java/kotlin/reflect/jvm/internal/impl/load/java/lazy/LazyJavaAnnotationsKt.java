package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaAnnotations.kt */
public final class LazyJavaAnnotationsKt {
    @NotNull
    public static final Annotations resolveAnnotations(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull JavaAnnotationOwner javaAnnotationOwner) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "receiver$0");
        Intrinsics.checkParameterIsNotNull(javaAnnotationOwner, "annotationsOwner");
        return new LazyJavaAnnotations(lazyJavaResolverContext, javaAnnotationOwner);
    }
}
