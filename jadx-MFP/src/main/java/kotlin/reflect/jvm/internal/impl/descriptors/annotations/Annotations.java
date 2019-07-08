package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Annotations.kt */
public interface Annotations extends Iterable<AnnotationDescriptor>, KMappedMarker {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: Annotations.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final Annotations EMPTY = new Annotations$Companion$EMPTY$1();

        private Companion() {
        }

        @NotNull
        public final Annotations getEMPTY() {
            return EMPTY;
        }

        @NotNull
        public final Annotations create(@NotNull List<? extends AnnotationDescriptor> list) {
            Intrinsics.checkParameterIsNotNull(list, "annotations");
            return list.isEmpty() ? EMPTY : new AnnotationsImpl(list);
        }
    }

    /* compiled from: Annotations.kt */
    public static final class DefaultImpls {
        @Nullable
        public static AnnotationDescriptor findAnnotation(Annotations annotations, @NotNull FqName fqName) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            Iterator it = annotations.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((AnnotationDescriptor) obj).getFqName(), (Object) fqName)) {
                    break;
                }
            }
            return (AnnotationDescriptor) obj;
        }

        public static boolean hasAnnotation(Annotations annotations, @NotNull FqName fqName) {
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            return annotations.findAnnotation(fqName) != null;
        }
    }

    @Nullable
    AnnotationDescriptor findAnnotation(@NotNull FqName fqName);

    boolean hasAnnotation(@NotNull FqName fqName);

    boolean isEmpty();
}
