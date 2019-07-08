package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinJvmBinaryPackageSourceElement.kt */
public final class KotlinJvmBinaryPackageSourceElement implements SourceElement {
    private final LazyJavaPackageFragment packageFragment;

    public KotlinJvmBinaryPackageSourceElement(@NotNull LazyJavaPackageFragment lazyJavaPackageFragment) {
        Intrinsics.checkParameterIsNotNull(lazyJavaPackageFragment, "packageFragment");
        this.packageFragment = lazyJavaPackageFragment;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.packageFragment);
        sb.append(": ");
        sb.append(this.packageFragment.getBinaryClasses$descriptors_jvm().keySet());
        return sb.toString();
    }

    @NotNull
    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
        return sourceFile;
    }

    @Nullable
    public final KotlinJvmBinaryClass getContainingBinaryClass(@NotNull DeserializedMemberDescriptor deserializedMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(deserializedMemberDescriptor, "descriptor");
        JvmClassName implClassNameForDeserialized = UtilKt.getImplClassNameForDeserialized(deserializedMemberDescriptor);
        if (implClassNameForDeserialized != null) {
            return (KotlinJvmBinaryClass) this.packageFragment.getBinaryClasses$descriptors_jvm().get(implClassNameForDeserialized.getInternalName());
        }
        return null;
    }
}
