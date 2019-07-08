package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaStaticClassScope.kt */
final class LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1<N> implements Neighbors<N> {
    public static final LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1 INSTANCE = new LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1();

    LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1() {
    }

    @NotNull
    public final Iterable<ClassDescriptor> getNeighbors(ClassDescriptor classDescriptor) {
        Intrinsics.checkExpressionValueIsNotNull(classDescriptor, "it");
        TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "it.typeConstructor");
        Collection supertypes = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes, "it.typeConstructor.supertypes");
        return SequencesKt.asIterable(SequencesKt.mapNotNull(CollectionsKt.asSequence(supertypes), AnonymousClass1.INSTANCE));
    }
}
