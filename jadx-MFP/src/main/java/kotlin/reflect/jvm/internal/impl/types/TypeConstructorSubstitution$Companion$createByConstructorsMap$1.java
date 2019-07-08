package kotlin.reflect.jvm.internal.impl.types;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeSubstitution.kt */
public final class TypeConstructorSubstitution$Companion$createByConstructorsMap$1 extends TypeConstructorSubstitution {
    final /* synthetic */ boolean $approximateCapturedTypes;
    final /* synthetic */ Map $map;

    TypeConstructorSubstitution$Companion$createByConstructorsMap$1(Map map, boolean z) {
        this.$map = map;
        this.$approximateCapturedTypes = z;
    }

    @Nullable
    public TypeProjection get(@NotNull TypeConstructor typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, IpcUtil.KEY_CODE);
        return (TypeProjection) this.$map.get(typeConstructor);
    }

    public boolean isEmpty() {
        return this.$map.isEmpty();
    }

    public boolean approximateCapturedTypes() {
        return this.$approximateCapturedTypes;
    }
}
