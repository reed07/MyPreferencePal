package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Class;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationAndConstantLoader.kt */
public interface AnnotationAndConstantLoader<A, C> {
    @NotNull
    List<A> loadCallableAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind);

    @NotNull
    List<A> loadClassAnnotations(@NotNull Class classR);

    @NotNull
    List<A> loadEnumEntryAnnotations(@NotNull ProtoContainer protoContainer, @NotNull EnumEntry enumEntry);

    @NotNull
    List<A> loadExtensionReceiverParameterAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind);

    @NotNull
    List<A> loadPropertyBackingFieldAnnotations(@NotNull ProtoContainer protoContainer, @NotNull Property property);

    @Nullable
    C loadPropertyConstant(@NotNull ProtoContainer protoContainer, @NotNull Property property, @NotNull KotlinType kotlinType);

    @NotNull
    List<A> loadPropertyDelegateFieldAnnotations(@NotNull ProtoContainer protoContainer, @NotNull Property property);

    @NotNull
    List<A> loadTypeAnnotations(@NotNull Type type, @NotNull NameResolver nameResolver);

    @NotNull
    List<A> loadTypeParameterAnnotations(@NotNull TypeParameter typeParameter, @NotNull NameResolver nameResolver);

    @NotNull
    List<A> loadValueParameterAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind, int i, @NotNull ValueParameter valueParameter);
}
