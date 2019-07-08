package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmPackageScope.kt */
final class JvmPackageScope$kotlinScopes$2 extends Lambda implements Function0<List<? extends MemberScope>> {
    final /* synthetic */ JvmPackageScope this$0;

    JvmPackageScope$kotlinScopes$2(JvmPackageScope jvmPackageScope) {
        this.this$0 = jvmPackageScope;
        super(0);
    }

    @NotNull
    public final List<MemberScope> invoke() {
        Iterable<KotlinJvmBinaryClass> values = this.this$0.packageFragment.getBinaryClasses$descriptors_jvm().values();
        Collection arrayList = new ArrayList();
        for (KotlinJvmBinaryClass createKotlinPackagePartScope : values) {
            MemberScope createKotlinPackagePartScope2 = this.this$0.c.getComponents().getDeserializedDescriptorResolver().createKotlinPackagePartScope(this.this$0.packageFragment, createKotlinPackagePartScope);
            if (createKotlinPackagePartScope2 != null) {
                arrayList.add(createKotlinPackagePartScope2);
            }
        }
        return CollectionsKt.toList((List) arrayList);
    }
}
