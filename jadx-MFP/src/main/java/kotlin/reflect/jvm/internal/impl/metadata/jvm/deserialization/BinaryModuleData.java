package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: BinaryModuleData.kt */
public final class BinaryModuleData {
    @NotNull
    private final List<String> annotations;

    public BinaryModuleData(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "annotations");
        this.annotations = list;
    }
}
