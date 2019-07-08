package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.Companion;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ModuleMappingUtil.kt */
public final class ModuleMappingUtilKt {
    @NotNull
    public static final ModuleMapping loadModuleMapping(@NotNull Companion companion, @Nullable byte[] bArr, @NotNull String str, @NotNull DeserializationConfiguration deserializationConfiguration, @NotNull Function1<? super JvmMetadataVersion, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(companion, "receiver$0");
        Intrinsics.checkParameterIsNotNull(str, "debugName");
        Intrinsics.checkParameterIsNotNull(deserializationConfiguration, "configuration");
        Intrinsics.checkParameterIsNotNull(function1, "reportIncompatibleVersionError");
        return companion.loadModuleMapping(bArr, str, deserializationConfiguration.getSkipMetadataVersionCheck(), deserializationConfiguration.isJvmPackageNameSupported(), function1);
    }
}
