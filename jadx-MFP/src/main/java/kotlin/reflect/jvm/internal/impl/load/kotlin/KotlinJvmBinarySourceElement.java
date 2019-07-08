package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinJvmBinarySourceElement.kt */
public final class KotlinJvmBinarySourceElement implements DeserializedContainerSource {
    @NotNull
    private final KotlinJvmBinaryClass binaryClass;
    @Nullable
    private final IncompatibleVersionErrorData<JvmMetadataVersion> incompatibility;
    private final boolean isPreReleaseInvisible;

    public KotlinJvmBinarySourceElement(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass, @Nullable IncompatibleVersionErrorData<JvmMetadataVersion> incompatibleVersionErrorData, boolean z) {
        Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "binaryClass");
        this.binaryClass = kotlinJvmBinaryClass;
        this.incompatibility = incompatibleVersionErrorData;
        this.isPreReleaseInvisible = z;
    }

    @NotNull
    public final KotlinJvmBinaryClass getBinaryClass() {
        return this.binaryClass;
    }

    @NotNull
    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
        return sourceFile;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(": ");
        sb.append(this.binaryClass);
        return sb.toString();
    }
}
