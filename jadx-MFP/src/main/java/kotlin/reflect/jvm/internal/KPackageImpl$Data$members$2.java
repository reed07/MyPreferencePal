package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryPackageSourceElement;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: KPackageImpl.kt */
final class KPackageImpl$Data$members$2 extends Lambda implements Function0<List<? extends KCallableImpl<?>>> {
    final /* synthetic */ Data this$0;

    KPackageImpl$Data$members$2(Data data) {
        this.this$0 = data;
        super(0);
    }

    @NotNull
    public final List<KCallableImpl<?>> invoke() {
        Iterable members = KPackageImpl.this.getMembers(KPackageImpl.this.getScope(), MemberBelonginess.DECLARED);
        Collection arrayList = new ArrayList();
        for (Object next : members) {
            CallableMemberDescriptor descriptor = ((KCallableImpl) next).getDescriptor();
            if (descriptor != null) {
                DeserializedCallableMemberDescriptor deserializedCallableMemberDescriptor = (DeserializedCallableMemberDescriptor) descriptor;
                DeclarationDescriptor containingDeclaration = deserializedCallableMemberDescriptor.getContainingDeclaration();
                if (containingDeclaration != null) {
                    PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) containingDeclaration;
                    Class cls = null;
                    if (!(packageFragmentDescriptor instanceof LazyJavaPackageFragment)) {
                        packageFragmentDescriptor = null;
                    }
                    LazyJavaPackageFragment lazyJavaPackageFragment = (LazyJavaPackageFragment) packageFragmentDescriptor;
                    SourceElement source = lazyJavaPackageFragment != null ? lazyJavaPackageFragment.getSource() : null;
                    if (!(source instanceof KotlinJvmBinaryPackageSourceElement)) {
                        source = null;
                    }
                    KotlinJvmBinaryPackageSourceElement kotlinJvmBinaryPackageSourceElement = (KotlinJvmBinaryPackageSourceElement) source;
                    KotlinJvmBinaryClass containingBinaryClass = kotlinJvmBinaryPackageSourceElement != null ? kotlinJvmBinaryPackageSourceElement.getContainingBinaryClass(deserializedCallableMemberDescriptor) : null;
                    if (!(containingBinaryClass instanceof ReflectKotlinClass)) {
                        containingBinaryClass = null;
                    }
                    ReflectKotlinClass reflectKotlinClass = (ReflectKotlinClass) containingBinaryClass;
                    if (reflectKotlinClass != null) {
                        cls = reflectKotlinClass.getKlass();
                    }
                    if (Intrinsics.areEqual((Object) cls, (Object) KPackageImpl.this.getJClass())) {
                        arrayList.add(next);
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor");
            }
        }
        return (List) arrayList;
    }
}
