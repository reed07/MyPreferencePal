package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaDescriptorUtil.kt */
public final class JavaDescriptorUtilKt {
    public static final boolean isJavaField(@NotNull PropertyDescriptor propertyDescriptor) {
        Intrinsics.checkParameterIsNotNull(propertyDescriptor, "receiver$0");
        return propertyDescriptor.getGetter() == null;
    }
}
