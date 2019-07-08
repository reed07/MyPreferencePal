package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl.Accessor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isNotNullProperty", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KPropertyImpl.kt */
final class KPropertyImplKt$computeCallerForAccessor$2 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ Accessor $this_computeCallerForAccessor;

    KPropertyImplKt$computeCallerForAccessor$2(Accessor accessor) {
        this.$this_computeCallerForAccessor = accessor;
        super(0);
    }

    public final boolean invoke() {
        return !TypeUtils.isNullableType(this.$this_computeCallerForAccessor.getProperty().getDescriptor().getType());
    }
}
