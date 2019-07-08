package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
final class AbstractBinaryClassAnnotationAndConstantLoader$storage$1 extends Lambda implements Function1<KotlinJvmBinaryClass, Storage<? extends A, ? extends C>> {
    final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader this$0;

    AbstractBinaryClassAnnotationAndConstantLoader$storage$1(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader) {
        this.this$0 = abstractBinaryClassAnnotationAndConstantLoader;
        super(1);
    }

    @NotNull
    public final Storage<A, C> invoke(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinClass");
        return this.this$0.loadAnnotationsAndInitializers(kotlinJvmBinaryClass);
    }
}
