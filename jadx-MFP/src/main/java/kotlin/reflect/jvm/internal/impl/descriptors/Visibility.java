package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Visibility.kt */
public abstract class Visibility {
    private final boolean isPublicAPI;
    @NotNull
    private final String name;

    public abstract boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor);

    @NotNull
    public Visibility normalize() {
        return this;
    }

    protected Visibility(@NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        this.name = str;
        this.isPublicAPI = z;
    }

    public final boolean isPublicAPI() {
        return this.isPublicAPI;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Integer compareTo(@NotNull Visibility visibility) {
        Intrinsics.checkParameterIsNotNull(visibility, "visibility");
        return Visibilities.compareLocal(this, visibility);
    }

    @NotNull
    public String getDisplayName() {
        return this.name;
    }

    @NotNull
    public final String toString() {
        return getDisplayName();
    }
}
