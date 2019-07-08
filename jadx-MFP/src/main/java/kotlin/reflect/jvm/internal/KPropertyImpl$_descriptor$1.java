package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "R", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KPropertyImpl.kt */
final class KPropertyImpl$_descriptor$1 extends Lambda implements Function0<PropertyDescriptor> {
    final /* synthetic */ KPropertyImpl this$0;

    KPropertyImpl$_descriptor$1(KPropertyImpl kPropertyImpl) {
        this.this$0 = kPropertyImpl;
        super(0);
    }

    @NotNull
    public final PropertyDescriptor invoke() {
        return this.this$0.getContainer().findPropertyDescriptor(this.this$0.getName(), this.this$0.getSignature());
    }
}
