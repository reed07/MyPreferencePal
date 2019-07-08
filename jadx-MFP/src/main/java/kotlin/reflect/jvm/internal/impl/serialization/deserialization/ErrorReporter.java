package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.NotNull;

public interface ErrorReporter {
    public static final ErrorReporter DO_NOTHING = new ErrorReporter() {
        public void reportCannotInferVisibility(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        }

        public void reportIncompleteHierarchy(@NotNull ClassDescriptor classDescriptor, @NotNull List<String> list) {
        }
    };

    void reportCannotInferVisibility(@NotNull CallableMemberDescriptor callableMemberDescriptor);

    void reportIncompleteHierarchy(@NotNull ClassDescriptor classDescriptor, @NotNull List<String> list);
}
