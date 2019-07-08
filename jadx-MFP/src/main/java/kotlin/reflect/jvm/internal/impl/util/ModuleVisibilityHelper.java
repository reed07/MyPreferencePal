package kotlin.reflect.jvm.internal.impl.util;

import com.myfitnesspal.shared.constants.SharedConstants.Params;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: ModuleVisibilityHelper.kt */
public interface ModuleVisibilityHelper {

    /* compiled from: ModuleVisibilityHelper.kt */
    public static final class EMPTY implements ModuleVisibilityHelper {
        public static final EMPTY INSTANCE = new EMPTY();

        public boolean isInFriendModule(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull DeclarationDescriptor declarationDescriptor2) {
            Intrinsics.checkParameterIsNotNull(declarationDescriptor, "what");
            Intrinsics.checkParameterIsNotNull(declarationDescriptor2, Params.FROM);
            return true;
        }

        private EMPTY() {
        }
    }

    boolean isInFriendModule(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull DeclarationDescriptor declarationDescriptor2);
}
