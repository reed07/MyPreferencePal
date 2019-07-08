package kotlin.reflect.jvm.internal.components;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "version", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMetadataVersion;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: RuntimePackagePartProvider.kt */
final class RuntimePackagePartProvider$registerModule$1$mapping$1 extends Lambda implements Function1<JvmMetadataVersion, Unit> {
    public static final RuntimePackagePartProvider$registerModule$1$mapping$1 INSTANCE = new RuntimePackagePartProvider$registerModule$1$mapping$1();

    RuntimePackagePartProvider$registerModule$1$mapping$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((JvmMetadataVersion) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull JvmMetadataVersion jvmMetadataVersion) {
        Intrinsics.checkParameterIsNotNull(jvmMetadataVersion, "version");
        StringBuilder sb = new StringBuilder();
        sb.append("Module was compiled with an incompatible version of Kotlin. The binary version of its metadata is ");
        sb.append(jvmMetadataVersion);
        sb.append(", ");
        sb.append("expected version is ");
        sb.append(JvmMetadataVersion.INSTANCE);
        sb.append(". Please update Kotlin to the latest version");
        throw new UnsupportedOperationException(sb.toString());
    }
}
