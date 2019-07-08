package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: StarProjectionImpl.kt */
final class StarProjectionImpl$_type$2 extends Lambda implements Function0<KotlinType> {
    final /* synthetic */ StarProjectionImpl this$0;

    StarProjectionImpl$_type$2(StarProjectionImpl starProjectionImpl) {
        this.this$0 = starProjectionImpl;
        super(0);
    }

    @NotNull
    public final KotlinType invoke() {
        return StarProjectionImplKt.starProjectionType(this.this$0.typeParameter);
    }
}
