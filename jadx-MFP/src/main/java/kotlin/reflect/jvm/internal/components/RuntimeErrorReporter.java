package kotlin.reflect.jvm.internal.components;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeErrorReporter;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/ErrorReporter;", "()V", "reportCannotInferVisibility", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "reportIncompleteHierarchy", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "unresolvedSuperClasses", "", "", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* compiled from: RuntimeErrorReporter.kt */
public final class RuntimeErrorReporter implements ErrorReporter {
    public static final RuntimeErrorReporter INSTANCE = new RuntimeErrorReporter();

    private RuntimeErrorReporter() {
    }

    public void reportIncompleteHierarchy(@NotNull ClassDescriptor classDescriptor, @NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(list, "unresolvedSuperClasses");
        StringBuilder sb = new StringBuilder();
        sb.append("Incomplete hierarchy for class ");
        sb.append(classDescriptor.getName());
        sb.append(", unresolved classes ");
        sb.append(list);
        throw new IllegalStateException(sb.toString());
    }

    public void reportCannotInferVisibility(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "descriptor");
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot infer visibility for ");
        sb.append(callableMemberDescriptor);
        throw new IllegalStateException(sb.toString());
    }
}
