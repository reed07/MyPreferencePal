package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.Nullable;

/* compiled from: MemberDeserializer.kt */
final class MemberDeserializer$loadProperty$3 extends Lambda implements Function0<ConstantValue<?>> {
    final /* synthetic */ DeserializedPropertyDescriptor $property;
    final /* synthetic */ Property $proto;
    final /* synthetic */ MemberDeserializer this$0;

    MemberDeserializer$loadProperty$3(MemberDeserializer memberDeserializer, Property property, DeserializedPropertyDescriptor deserializedPropertyDescriptor) {
        this.this$0 = memberDeserializer;
        this.$proto = property;
        this.$property = deserializedPropertyDescriptor;
        super(0);
    }

    @Nullable
    public final ConstantValue<?> invoke() {
        MemberDeserializer memberDeserializer = this.this$0;
        ProtoContainer access$asProtoContainer = memberDeserializer.asProtoContainer(memberDeserializer.c.getContainingDeclaration());
        if (access$asProtoContainer == null) {
            Intrinsics.throwNpe();
        }
        AnnotationAndConstantLoader annotationAndConstantLoader = this.this$0.c.getComponents().getAnnotationAndConstantLoader();
        Property property = this.$proto;
        KotlinType returnType = this.$property.getReturnType();
        Intrinsics.checkExpressionValueIsNotNull(returnType, "property.returnType");
        return (ConstantValue) annotationAndConstantLoader.loadPropertyConstant(access$asProtoContainer, property, returnType);
    }
}
