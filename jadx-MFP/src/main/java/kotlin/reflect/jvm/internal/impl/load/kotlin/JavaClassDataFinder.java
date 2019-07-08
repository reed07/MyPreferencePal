package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaClassDataFinder.kt */
public final class JavaClassDataFinder implements ClassDataFinder {
    private final DeserializedDescriptorResolver deserializedDescriptorResolver;
    @NotNull
    private final KotlinClassFinder kotlinClassFinder;

    public JavaClassDataFinder(@NotNull KotlinClassFinder kotlinClassFinder2, @NotNull DeserializedDescriptorResolver deserializedDescriptorResolver2) {
        Intrinsics.checkParameterIsNotNull(kotlinClassFinder2, "kotlinClassFinder");
        Intrinsics.checkParameterIsNotNull(deserializedDescriptorResolver2, "deserializedDescriptorResolver");
        this.kotlinClassFinder = kotlinClassFinder2;
        this.deserializedDescriptorResolver = deserializedDescriptorResolver2;
    }

    @Nullable
    public ClassData findClassData(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        KotlinJvmBinaryClass findKotlinClass = this.kotlinClassFinder.findKotlinClass(classId);
        if (findKotlinClass == null) {
            return null;
        }
        boolean areEqual = Intrinsics.areEqual((Object) findKotlinClass.getClassId(), (Object) classId);
        if (!_Assertions.ENABLED || areEqual) {
            return this.deserializedDescriptorResolver.readClassData$descriptors_jvm(findKotlinClass);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Class with incorrect id found: expected ");
        sb.append(classId);
        sb.append(", actual ");
        sb.append(findKotlinClass.getClassId());
        throw new AssertionError(sb.toString());
    }
}
