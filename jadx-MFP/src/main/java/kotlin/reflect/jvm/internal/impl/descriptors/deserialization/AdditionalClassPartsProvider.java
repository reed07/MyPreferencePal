package kotlin.reflect.jvm.internal.impl.descriptors.deserialization;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: AdditionalClassPartsProvider.kt */
public interface AdditionalClassPartsProvider {

    /* compiled from: AdditionalClassPartsProvider.kt */
    public static final class None implements AdditionalClassPartsProvider {
        public static final None INSTANCE = new None();

        private None() {
        }

        @NotNull
        public Collection<KotlinType> getSupertypes(@NotNull ClassDescriptor classDescriptor) {
            Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
            return CollectionsKt.emptyList();
        }

        @NotNull
        public Collection<SimpleFunctionDescriptor> getFunctions(@NotNull Name name, @NotNull ClassDescriptor classDescriptor) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
            return CollectionsKt.emptyList();
        }

        @NotNull
        public Collection<Name> getFunctionsNames(@NotNull ClassDescriptor classDescriptor) {
            Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
            return CollectionsKt.emptyList();
        }

        @NotNull
        public Collection<ClassConstructorDescriptor> getConstructors(@NotNull ClassDescriptor classDescriptor) {
            Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
            return CollectionsKt.emptyList();
        }
    }

    @NotNull
    Collection<ClassConstructorDescriptor> getConstructors(@NotNull ClassDescriptor classDescriptor);

    @NotNull
    Collection<SimpleFunctionDescriptor> getFunctions(@NotNull Name name, @NotNull ClassDescriptor classDescriptor);

    @NotNull
    Collection<Name> getFunctionsNames(@NotNull ClassDescriptor classDescriptor);

    @NotNull
    Collection<KotlinType> getSupertypes(@NotNull ClassDescriptor classDescriptor);
}
