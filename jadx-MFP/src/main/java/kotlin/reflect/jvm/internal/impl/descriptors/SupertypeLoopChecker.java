package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import org.jetbrains.annotations.NotNull;

/* compiled from: SupertypeLoopChecker.kt */
public interface SupertypeLoopChecker {

    /* compiled from: SupertypeLoopChecker.kt */
    public static final class EMPTY implements SupertypeLoopChecker {
        public static final EMPTY INSTANCE = new EMPTY();

        @NotNull
        public Collection<KotlinType> findLoopsInSupertypesAndDisconnect(@NotNull TypeConstructor typeConstructor, @NotNull Collection<? extends KotlinType> collection, @NotNull Function1<? super TypeConstructor, ? extends Iterable<? extends KotlinType>> function1, @NotNull Function1<? super KotlinType, Unit> function12) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "currentTypeConstructor");
            Intrinsics.checkParameterIsNotNull(collection, "superTypes");
            Intrinsics.checkParameterIsNotNull(function1, "neighbors");
            Intrinsics.checkParameterIsNotNull(function12, "reportLoop");
            return collection;
        }

        private EMPTY() {
        }
    }

    @NotNull
    Collection<KotlinType> findLoopsInSupertypesAndDisconnect(@NotNull TypeConstructor typeConstructor, @NotNull Collection<? extends KotlinType> collection, @NotNull Function1<? super TypeConstructor, ? extends Iterable<? extends KotlinType>> function1, @NotNull Function1<? super KotlinType, Unit> function12);
}
