package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Annotations.kt */
public final class FilteredAnnotations implements Annotations {
    private final Annotations delegate;
    private final Function1<FqName, Boolean> fqNameFilter;

    public FilteredAnnotations(@NotNull Annotations annotations, @NotNull Function1<? super FqName, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(annotations, "delegate");
        Intrinsics.checkParameterIsNotNull(function1, "fqNameFilter");
        this.delegate = annotations;
        this.fqNameFilter = function1;
    }

    public boolean hasAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        if (((Boolean) this.fqNameFilter.invoke(fqName)).booleanValue()) {
            return this.delegate.hasAnnotation(fqName);
        }
        return false;
    }

    @Nullable
    public AnnotationDescriptor findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        if (((Boolean) this.fqNameFilter.invoke(fqName)).booleanValue()) {
            return this.delegate.findAnnotation(fqName);
        }
        return null;
    }

    @NotNull
    public Iterator<AnnotationDescriptor> iterator() {
        Iterable iterable = this.delegate;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (shouldBeReturned((AnnotationDescriptor) next)) {
                arrayList.add(next);
            }
        }
        return ((List) arrayList).iterator();
    }

    public boolean isEmpty() {
        Iterable<AnnotationDescriptor> iterable = this.delegate;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (AnnotationDescriptor shouldBeReturned : iterable) {
            if (shouldBeReturned(shouldBeReturned)) {
                return true;
            }
        }
        return false;
    }

    private final boolean shouldBeReturned(AnnotationDescriptor annotationDescriptor) {
        FqName fqName = annotationDescriptor.getFqName();
        return fqName != null && ((Boolean) this.fqNameFilter.invoke(fqName)).booleanValue();
    }
}
