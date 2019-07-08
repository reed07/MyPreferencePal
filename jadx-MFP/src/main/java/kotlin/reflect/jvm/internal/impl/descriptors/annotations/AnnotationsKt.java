package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Annotations.kt */
public final class AnnotationsKt {
    @NotNull
    public static final Annotations composeAnnotations(@NotNull Annotations annotations, @NotNull Annotations annotations2) {
        Intrinsics.checkParameterIsNotNull(annotations, "first");
        Intrinsics.checkParameterIsNotNull(annotations2, "second");
        if (annotations.isEmpty()) {
            return annotations2;
        }
        if (annotations2.isEmpty()) {
            return annotations;
        }
        return new CompositeAnnotations(annotations, annotations2);
    }
}
