package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Annotations.kt */
public final class Annotations$Companion$EMPTY$1 implements Annotations {
    @Nullable
    public Void findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    @NotNull
    public String toString() {
        return "EMPTY";
    }

    Annotations$Companion$EMPTY$1() {
    }

    public boolean hasAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return DefaultImpls.hasAnnotation(this, fqName);
    }

    @NotNull
    public Iterator<AnnotationDescriptor> iterator() {
        return CollectionsKt.emptyList().iterator();
    }
}
