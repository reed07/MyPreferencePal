package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: predefinedEnhancementInfo.kt */
public final class PredefinedFunctionEnhancementInfo {
    @NotNull
    private final List<TypeEnhancementInfo> parametersInfo;
    @Nullable
    private final TypeEnhancementInfo returnTypeInfo;

    public PredefinedFunctionEnhancementInfo() {
        this(null, null, 3, null);
    }

    public PredefinedFunctionEnhancementInfo(@Nullable TypeEnhancementInfo typeEnhancementInfo, @NotNull List<TypeEnhancementInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "parametersInfo");
        this.returnTypeInfo = typeEnhancementInfo;
        this.parametersInfo = list;
    }

    public /* synthetic */ PredefinedFunctionEnhancementInfo(TypeEnhancementInfo typeEnhancementInfo, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            typeEnhancementInfo = null;
        }
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        this(typeEnhancementInfo, list);
    }

    @Nullable
    public final TypeEnhancementInfo getReturnTypeInfo() {
        return this.returnTypeInfo;
    }

    @NotNull
    public final List<TypeEnhancementInfo> getParametersInfo() {
        return this.parametersInfo;
    }
}
