package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClassDeserializer.kt */
final class ClassDeserializer$classes$1 extends Lambda implements Function1<ClassKey, ClassDescriptor> {
    final /* synthetic */ ClassDeserializer this$0;

    ClassDeserializer$classes$1(ClassDeserializer classDeserializer) {
        this.this$0 = classDeserializer;
        super(1);
    }

    @Nullable
    public final ClassDescriptor invoke(@NotNull ClassKey classKey) {
        Intrinsics.checkParameterIsNotNull(classKey, IpcUtil.KEY_CODE);
        return this.this$0.createClass(classKey);
    }
}
