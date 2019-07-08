package kotlin.reflect.jvm.internal.impl.types;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractTypeConstructor.kt */
final class AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2 extends Lambda implements Function1<KotlinType, Unit> {
    final /* synthetic */ AbstractTypeConstructor$supertypes$3 this$0;

    AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2(AbstractTypeConstructor$supertypes$3 abstractTypeConstructor$supertypes$3) {
        this.this$0 = abstractTypeConstructor$supertypes$3;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((KotlinType) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "it");
        this.this$0.this$0.reportSupertypeLoopError(kotlinType);
    }
}
