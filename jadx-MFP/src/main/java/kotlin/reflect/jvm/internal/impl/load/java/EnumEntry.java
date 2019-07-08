package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: utils.kt */
public final class EnumEntry extends JavaDefaultValue {
    @NotNull
    private final ClassDescriptor descriptor;

    public EnumEntry(@NotNull ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "descriptor");
        super(null);
        this.descriptor = classDescriptor;
    }
}
