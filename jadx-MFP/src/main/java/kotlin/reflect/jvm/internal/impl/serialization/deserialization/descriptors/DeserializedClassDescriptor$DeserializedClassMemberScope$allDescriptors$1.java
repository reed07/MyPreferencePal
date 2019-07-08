package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedClassDescriptor.kt */
final class DeserializedClassDescriptor$DeserializedClassMemberScope$allDescriptors$1 extends Lambda implements Function0<Collection<? extends DeclarationDescriptor>> {
    final /* synthetic */ DeserializedClassMemberScope this$0;

    DeserializedClassDescriptor$DeserializedClassMemberScope$allDescriptors$1(DeserializedClassMemberScope deserializedClassMemberScope) {
        this.this$0 = deserializedClassMemberScope;
        super(0);
    }

    @NotNull
    public final Collection<DeclarationDescriptor> invoke() {
        return this.this$0.computeDescriptors(DescriptorKindFilter.ALL, MemberScope.Companion.getALL_NAME_FILTER(), NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
    }
}
