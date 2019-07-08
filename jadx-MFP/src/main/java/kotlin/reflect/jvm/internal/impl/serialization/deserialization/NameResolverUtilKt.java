package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: NameResolverUtil.kt */
public final class NameResolverUtilKt {
    @NotNull
    public static final ClassId getClassId(@NotNull NameResolver nameResolver, int i) {
        Intrinsics.checkParameterIsNotNull(nameResolver, "receiver$0");
        ClassId fromString = ClassId.fromString(nameResolver.getQualifiedClassName(i), nameResolver.isLocalClassName(i));
        Intrinsics.checkExpressionValueIsNotNull(fromString, "ClassId.fromString(getQu… isLocalClassName(index))");
        return fromString;
    }

    @NotNull
    public static final Name getName(@NotNull NameResolver nameResolver, int i) {
        Intrinsics.checkParameterIsNotNull(nameResolver, "receiver$0");
        Name guessByFirstCharacter = Name.guessByFirstCharacter(nameResolver.getString(i));
        Intrinsics.checkExpressionValueIsNotNull(guessByFirstCharacter, "Name.guessByFirstCharacter(getString(index))");
        return guessByFirstCharacter;
    }
}
