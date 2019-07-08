package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltIns.kt */
public final class JvmBuiltIns extends KotlinBuiltIns {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltIns.class), "settings", "getSettings()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltInsSettings;"))};
    /* access modifiers changed from: private */
    public boolean isAdditionalBuiltInsFeatureSupported;
    /* access modifiers changed from: private */
    public ModuleDescriptor ownerModuleDescriptor;
    @NotNull
    private final NotNullLazyValue settings$delegate;

    @NotNull
    public final JvmBuiltInsSettings getSettings() {
        return (JvmBuiltInsSettings) StorageKt.getValue(this.settings$delegate, (Object) this, $$delegatedProperties[0]);
    }

    @JvmOverloads
    public /* synthetic */ JvmBuiltIns(StorageManager storageManager, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            z = true;
        }
        this(storageManager, z);
    }

    @JvmOverloads
    public JvmBuiltIns(@NotNull StorageManager storageManager, boolean z) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        super(storageManager);
        this.isAdditionalBuiltInsFeatureSupported = true;
        this.settings$delegate = storageManager.createLazyValue(new JvmBuiltIns$settings$2(this, storageManager));
        if (z) {
            createBuiltInsModule();
        }
    }

    public final void initialize(@NotNull ModuleDescriptor moduleDescriptor, boolean z) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
        boolean z2 = this.ownerModuleDescriptor == null;
        if (!_Assertions.ENABLED || z2) {
            this.ownerModuleDescriptor = moduleDescriptor;
            this.isAdditionalBuiltInsFeatureSupported = z;
            return;
        }
        throw new AssertionError("JvmBuiltins repeated initialization");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return getSettings();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return getSettings();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<ClassDescriptorFactory> getClassDescriptorFactories() {
        Iterable classDescriptorFactories = super.getClassDescriptorFactories();
        Intrinsics.checkExpressionValueIsNotNull(classDescriptorFactories, "super.getClassDescriptorFactories()");
        StorageManager storageManager = getStorageManager();
        Intrinsics.checkExpressionValueIsNotNull(storageManager, "storageManager");
        ModuleDescriptorImpl builtInsModule = getBuiltInsModule();
        Intrinsics.checkExpressionValueIsNotNull(builtInsModule, "builtInsModule");
        JvmBuiltInClassDescriptorFactory jvmBuiltInClassDescriptorFactory = new JvmBuiltInClassDescriptorFactory(storageManager, builtInsModule, null, 4, null);
        return CollectionsKt.plus(classDescriptorFactories, jvmBuiltInClassDescriptorFactory);
    }
}
