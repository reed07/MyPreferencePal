package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: methodSignatureMapping.kt */
public abstract class JvmType {

    /* compiled from: methodSignatureMapping.kt */
    public static final class Array extends JvmType {
        @NotNull
        private final JvmType elementType;

        public Array(@NotNull JvmType jvmType) {
            Intrinsics.checkParameterIsNotNull(jvmType, "elementType");
            super(null);
            this.elementType = jvmType;
        }

        @NotNull
        public final JvmType getElementType() {
            return this.elementType;
        }
    }

    /* compiled from: methodSignatureMapping.kt */
    public static final class Object extends JvmType {
        @NotNull
        private final String internalName;

        public Object(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "internalName");
            super(null);
            this.internalName = str;
        }

        @NotNull
        public final String getInternalName() {
            return this.internalName;
        }
    }

    /* compiled from: methodSignatureMapping.kt */
    public static final class Primitive extends JvmType {
        @Nullable
        private final JvmPrimitiveType jvmPrimitiveType;

        public Primitive(@Nullable JvmPrimitiveType jvmPrimitiveType2) {
            super(null);
            this.jvmPrimitiveType = jvmPrimitiveType2;
        }

        @Nullable
        public final JvmPrimitiveType getJvmPrimitiveType() {
            return this.jvmPrimitiveType;
        }
    }

    private JvmType() {
    }

    public /* synthetic */ JvmType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public String toString() {
        return JvmTypeFactoryImpl.INSTANCE.toString(this);
    }
}
