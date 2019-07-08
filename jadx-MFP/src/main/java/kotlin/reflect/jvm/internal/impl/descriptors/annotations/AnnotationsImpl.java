package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationsImpl.kt */
public final class AnnotationsImpl implements Annotations {
    private final List<AnnotationDescriptor> annotations;

    public AnnotationsImpl(@NotNull List<? extends AnnotationDescriptor> list) {
        Intrinsics.checkParameterIsNotNull(list, "annotations");
        this.annotations = list;
    }

    @Nullable
    public AnnotationDescriptor findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return DefaultImpls.findAnnotation(this, fqName);
    }

    public boolean hasAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return DefaultImpls.hasAnnotation(this, fqName);
    }

    public boolean isEmpty() {
        return this.annotations.isEmpty();
    }

    @NotNull
    public Iterator<AnnotationDescriptor> iterator() {
        return this.annotations.iterator();
    }

    @NotNull
    public String toString() {
        return this.annotations.toString();
    }
}
