package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Annotations.kt */
public final class CompositeAnnotations implements Annotations {
    private final List<Annotations> delegates;

    public CompositeAnnotations(@NotNull List<? extends Annotations> list) {
        Intrinsics.checkParameterIsNotNull(list, "delegates");
        this.delegates = list;
    }

    public CompositeAnnotations(@NotNull Annotations... annotationsArr) {
        Intrinsics.checkParameterIsNotNull(annotationsArr, "delegates");
        this(ArraysKt.toList((T[]) annotationsArr));
    }

    public boolean isEmpty() {
        Iterable<Annotations> iterable = this.delegates;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        for (Annotations isEmpty : iterable) {
            if (!isEmpty.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        for (Annotations hasAnnotation : CollectionsKt.asSequence(this.delegates)) {
            if (hasAnnotation.hasAnnotation(fqName)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public AnnotationDescriptor findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return (AnnotationDescriptor) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.delegates), new CompositeAnnotations$findAnnotation$1(fqName)));
    }

    @NotNull
    public Iterator<AnnotationDescriptor> iterator() {
        return SequencesKt.flatMap(CollectionsKt.asSequence(this.delegates), CompositeAnnotations$iterator$1.INSTANCE).iterator();
    }
}
