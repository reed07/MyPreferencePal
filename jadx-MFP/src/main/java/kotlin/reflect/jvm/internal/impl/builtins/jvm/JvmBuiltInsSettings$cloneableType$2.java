package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
final class JvmBuiltInsSettings$cloneableType$2 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ StorageManager $storageManager;
    final /* synthetic */ JvmBuiltInsSettings this$0;

    JvmBuiltInsSettings$cloneableType$2(JvmBuiltInsSettings jvmBuiltInsSettings, StorageManager storageManager) {
        this.this$0 = jvmBuiltInsSettings;
        this.$storageManager = storageManager;
        super(0);
    }

    @NotNull
    public final SimpleType invoke() {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.this$0.getOwnerModuleDescriptor(), JvmBuiltInClassDescriptorFactory.Companion.getCLONEABLE_CLASS_ID(), new NotFoundClasses(this.$storageManager, this.this$0.getOwnerModuleDescriptor())).getDefaultType();
    }
}
