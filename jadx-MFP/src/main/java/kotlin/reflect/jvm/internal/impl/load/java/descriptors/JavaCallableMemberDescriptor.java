package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface JavaCallableMemberDescriptor extends CallableMemberDescriptor {
    @NotNull
    JavaCallableMemberDescriptor enhance(@Nullable KotlinType kotlinType, @NotNull List<ValueParameterData> list, @NotNull KotlinType kotlinType2, @Nullable Pair<UserDataKey<?>, ?> pair);
}