package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaPropertyInitializerEvaluator.kt */
public interface JavaPropertyInitializerEvaluator {

    /* compiled from: JavaPropertyInitializerEvaluator.kt */
    public static final class DoNothing implements JavaPropertyInitializerEvaluator {
        public static final DoNothing INSTANCE = new DoNothing();

        @Nullable
        public Void getInitializerConstant(@NotNull JavaField javaField, @NotNull PropertyDescriptor propertyDescriptor) {
            Intrinsics.checkParameterIsNotNull(javaField, "field");
            Intrinsics.checkParameterIsNotNull(propertyDescriptor, "descriptor");
            return null;
        }

        private DoNothing() {
        }
    }

    @Nullable
    ConstantValue<?> getInitializerConstant(@NotNull JavaField javaField, @NotNull PropertyDescriptor propertyDescriptor);
}
