package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

/* compiled from: Delegates.kt */
public final class DescriptorRendererOptionsImpl$property$$inlined$vetoable$1 extends ObservableProperty<T> {
    final /* synthetic */ Object $initialValue;
    final /* synthetic */ DescriptorRendererOptionsImpl this$0;

    public DescriptorRendererOptionsImpl$property$$inlined$vetoable$1(Object obj, Object obj2, DescriptorRendererOptionsImpl descriptorRendererOptionsImpl) {
        this.$initialValue = obj;
        this.this$0 = descriptorRendererOptionsImpl;
        super(obj2);
    }

    /* access modifiers changed from: protected */
    public boolean beforeChange(@NotNull KProperty<?> kProperty, T t, T t2) {
        Intrinsics.checkParameterIsNotNull(kProperty, "property");
        if (!this.this$0.isLocked()) {
            return true;
        }
        throw new IllegalStateException("Cannot modify readonly DescriptorRendererOptions");
    }
}
