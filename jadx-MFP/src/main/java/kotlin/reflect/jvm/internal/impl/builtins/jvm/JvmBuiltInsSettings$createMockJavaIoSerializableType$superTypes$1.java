package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
final class JvmBuiltInsSettings$createMockJavaIoSerializableType$superTypes$1 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ JvmBuiltInsSettings this$0;

    JvmBuiltInsSettings$createMockJavaIoSerializableType$superTypes$1(JvmBuiltInsSettings jvmBuiltInsSettings) {
        this.this$0 = jvmBuiltInsSettings;
        super(0);
    }

    @NotNull
    public final SimpleType invoke() {
        SimpleType anyType = this.this$0.moduleDescriptor.getBuiltIns().getAnyType();
        Intrinsics.checkExpressionValueIsNotNull(anyType, "moduleDescriptor.builtIns.anyType");
        return anyType;
    }
}
