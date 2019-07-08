package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractTypeConstructor.kt */
final class AbstractTypeConstructor$supertypes$1 extends Lambda implements Function0<Supertypes> {
    final /* synthetic */ AbstractTypeConstructor this$0;

    AbstractTypeConstructor$supertypes$1(AbstractTypeConstructor abstractTypeConstructor) {
        this.this$0 = abstractTypeConstructor;
        super(0);
    }

    @NotNull
    public final Supertypes invoke() {
        return new Supertypes(this.this$0.computeSupertypes());
    }
}
