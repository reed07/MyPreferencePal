package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: methodSignatureBuilding.kt */
final class SignatureBuildingComponents$jvmDescriptor$1 extends Lambda implements Function1<String, String> {
    public static final SignatureBuildingComponents$jvmDescriptor$1 INSTANCE = new SignatureBuildingComponents$jvmDescriptor$1();

    SignatureBuildingComponents$jvmDescriptor$1() {
        super(1);
    }

    @NotNull
    public final String invoke(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "it");
        return SignatureBuildingComponents.INSTANCE.escapeClassName(str);
    }
}
