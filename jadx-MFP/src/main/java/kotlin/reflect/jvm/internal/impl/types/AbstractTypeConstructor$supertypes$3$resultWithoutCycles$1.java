package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractTypeConstructor.kt */
final class AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1 extends Lambda implements Function1<TypeConstructor, Collection<? extends KotlinType>> {
    final /* synthetic */ AbstractTypeConstructor$supertypes$3 this$0;

    AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1(AbstractTypeConstructor$supertypes$3 abstractTypeConstructor$supertypes$3) {
        this.this$0 = abstractTypeConstructor$supertypes$3;
        super(1);
    }

    @NotNull
    public final Collection<KotlinType> invoke(@NotNull TypeConstructor typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "it");
        return this.this$0.this$0.computeNeighbours(typeConstructor, false);
    }
}
