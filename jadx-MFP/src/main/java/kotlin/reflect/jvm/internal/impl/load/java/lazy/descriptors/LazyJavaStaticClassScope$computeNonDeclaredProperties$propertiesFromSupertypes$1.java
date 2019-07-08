package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaStaticClassScope.kt */
final class LazyJavaStaticClassScope$computeNonDeclaredProperties$propertiesFromSupertypes$1 extends Lambda implements Function1<MemberScope, Collection<? extends PropertyDescriptor>> {
    final /* synthetic */ Name $name;

    LazyJavaStaticClassScope$computeNonDeclaredProperties$propertiesFromSupertypes$1(Name name) {
        this.$name = name;
        super(1);
    }

    @NotNull
    public final Collection<PropertyDescriptor> invoke(@NotNull MemberScope memberScope) {
        Intrinsics.checkParameterIsNotNull(memberScope, "it");
        return memberScope.getContributedVariables(this.$name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS);
    }
}
