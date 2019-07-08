package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KDeclarationContainerImpl.Data;
import kotlin.reflect.jvm.internal.components.RuntimeModuleData;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/components/RuntimeModuleData;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KDeclarationContainerImpl.kt */
final class KDeclarationContainerImpl$Data$moduleData$2 extends Lambda implements Function0<RuntimeModuleData> {
    final /* synthetic */ Data this$0;

    KDeclarationContainerImpl$Data$moduleData$2(Data data) {
        this.this$0 = data;
        super(0);
    }

    @NotNull
    public final RuntimeModuleData invoke() {
        return ModuleByClassLoaderKt.getOrCreateModule(KDeclarationContainerImpl.this.getJClass());
    }
}
