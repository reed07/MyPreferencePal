package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface JavaResolverCache {
    public static final JavaResolverCache EMPTY = new JavaResolverCache() {
        @Nullable
        public ClassDescriptor getClassResolvedFromSource(@NotNull FqName fqName) {
            return null;
        }

        public void recordClass(@NotNull JavaClass javaClass, @NotNull ClassDescriptor classDescriptor) {
        }

        public void recordConstructor(@NotNull JavaElement javaElement, @NotNull ConstructorDescriptor constructorDescriptor) {
        }

        public void recordField(@NotNull JavaField javaField, @NotNull PropertyDescriptor propertyDescriptor) {
        }

        public void recordMethod(@NotNull JavaMethod javaMethod, @NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        }
    };

    @Nullable
    ClassDescriptor getClassResolvedFromSource(@NotNull FqName fqName);

    void recordClass(@NotNull JavaClass javaClass, @NotNull ClassDescriptor classDescriptor);

    void recordConstructor(@NotNull JavaElement javaElement, @NotNull ConstructorDescriptor constructorDescriptor);

    void recordField(@NotNull JavaField javaField, @NotNull PropertyDescriptor propertyDescriptor);

    void recordMethod(@NotNull JavaMethod javaMethod, @NotNull SimpleFunctionDescriptor simpleFunctionDescriptor);
}
