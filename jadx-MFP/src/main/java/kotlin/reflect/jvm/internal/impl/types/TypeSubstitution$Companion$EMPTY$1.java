package kotlin.reflect.jvm.internal.impl.types;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeSubstitution.kt */
public final class TypeSubstitution$Companion$EMPTY$1 extends TypeSubstitution {
    @Nullable
    public Void get(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, IpcUtil.KEY_CODE);
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    @NotNull
    public String toString() {
        return "Empty TypeSubstitution";
    }

    TypeSubstitution$Companion$EMPTY$1() {
    }
}
