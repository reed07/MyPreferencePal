package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
public final class JvmBuiltInsSettings$createMockJavaIoSerializableType$mockJavaIoPackageFragment$1 extends PackageFragmentDescriptorImpl {
    final /* synthetic */ JvmBuiltInsSettings this$0;

    JvmBuiltInsSettings$createMockJavaIoSerializableType$mockJavaIoPackageFragment$1(JvmBuiltInsSettings jvmBuiltInsSettings, ModuleDescriptor moduleDescriptor, FqName fqName) {
        this.this$0 = jvmBuiltInsSettings;
        super(moduleDescriptor, fqName);
    }

    @NotNull
    public Empty getMemberScope() {
        return Empty.INSTANCE;
    }
}
