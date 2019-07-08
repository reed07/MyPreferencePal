package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: PackageViewDescriptor.kt */
public interface PackageViewDescriptor extends DeclarationDescriptor {

    /* compiled from: PackageViewDescriptor.kt */
    public static final class DefaultImpls {
        public static boolean isEmpty(PackageViewDescriptor packageViewDescriptor) {
            return packageViewDescriptor.getFragments().isEmpty();
        }
    }

    @NotNull
    FqName getFqName();

    @NotNull
    List<PackageFragmentDescriptor> getFragments();

    @NotNull
    MemberScope getMemberScope();

    @NotNull
    ModuleDescriptor getModule();

    boolean isEmpty();
}
