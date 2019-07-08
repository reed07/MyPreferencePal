package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: resolvers.kt */
public interface TypeParameterResolver {

    /* compiled from: resolvers.kt */
    public static final class EMPTY implements TypeParameterResolver {
        public static final EMPTY INSTANCE = new EMPTY();

        @Nullable
        public TypeParameterDescriptor resolveTypeParameter(@NotNull JavaTypeParameter javaTypeParameter) {
            Intrinsics.checkParameterIsNotNull(javaTypeParameter, "javaTypeParameter");
            return null;
        }

        private EMPTY() {
        }
    }

    @Nullable
    TypeParameterDescriptor resolveTypeParameter(@NotNull JavaTypeParameter javaTypeParameter);
}
