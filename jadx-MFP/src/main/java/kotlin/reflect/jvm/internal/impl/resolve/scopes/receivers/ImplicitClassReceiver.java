package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ImplicitClassReceiver.kt */
public class ImplicitClassReceiver implements ImplicitReceiver, ThisClassReceiver {
    @NotNull
    private final ClassDescriptor classDescriptor;
    @NotNull
    private final ClassDescriptor declarationDescriptor;
    private final ImplicitClassReceiver original;

    public ImplicitClassReceiver(@NotNull ClassDescriptor classDescriptor2, @Nullable ImplicitClassReceiver implicitClassReceiver) {
        Intrinsics.checkParameterIsNotNull(classDescriptor2, "classDescriptor");
        this.classDescriptor = classDescriptor2;
        if (implicitClassReceiver == null) {
            implicitClassReceiver = this;
        }
        this.original = implicitClassReceiver;
        this.declarationDescriptor = this.classDescriptor;
    }

    @NotNull
    public final ClassDescriptor getClassDescriptor() {
        return this.classDescriptor;
    }

    @NotNull
    public SimpleType getType() {
        SimpleType defaultType = this.classDescriptor.getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "classDescriptor.defaultType");
        return defaultType;
    }

    public boolean equals(@Nullable Object obj) {
        ClassDescriptor classDescriptor2 = this.classDescriptor;
        ClassDescriptor classDescriptor3 = null;
        if (!(obj instanceof ImplicitClassReceiver)) {
            obj = null;
        }
        ImplicitClassReceiver implicitClassReceiver = (ImplicitClassReceiver) obj;
        if (implicitClassReceiver != null) {
            classDescriptor3 = implicitClassReceiver.classDescriptor;
        }
        return Intrinsics.areEqual((Object) classDescriptor2, (Object) classDescriptor3);
    }

    public int hashCode() {
        return this.classDescriptor.hashCode();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Class{");
        sb.append(getType());
        sb.append('}');
        return sb.toString();
    }
}
