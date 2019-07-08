package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInsLoader.kt */
public interface BuiltInsLoader {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: BuiltInsLoader.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "Instance", "getInstance()Lorg/jetbrains/kotlin/builtins/BuiltInsLoader;"))};
        @NotNull
        private static final Lazy Instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<? extends T>) BuiltInsLoader$Companion$Instance$2.INSTANCE);

        @NotNull
        public final BuiltInsLoader getInstance() {
            Lazy lazy = Instance$delegate;
            KProperty kProperty = $$delegatedProperties[0];
            return (BuiltInsLoader) lazy.getValue();
        }

        private Companion() {
        }
    }

    @NotNull
    PackageFragmentProvider createPackageFragmentProvider(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor, @NotNull Iterable<? extends ClassDescriptorFactory> iterable, @NotNull PlatformDependentDeclarationFilter platformDependentDeclarationFilter, @NotNull AdditionalClassPartsProvider additionalClassPartsProvider);
}
