package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.serialization.SerializerExtensionProtocol;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Class;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationAndConstantLoaderImpl.kt */
public final class AnnotationAndConstantLoaderImpl implements AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> {
    private final AnnotationDeserializer deserializer;
    private final SerializerExtensionProtocol protocol;

    public AnnotationAndConstantLoaderImpl(@NotNull ModuleDescriptor moduleDescriptor, @NotNull NotFoundClasses notFoundClasses, @NotNull SerializerExtensionProtocol serializerExtensionProtocol) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(serializerExtensionProtocol, "protocol");
        this.protocol = serializerExtensionProtocol;
        this.deserializer = new AnnotationDeserializer(moduleDescriptor, notFoundClasses);
    }

    @NotNull
    public List<AnnotationDescriptor> loadClassAnnotations(@NotNull Class classR) {
        Intrinsics.checkParameterIsNotNull(classR, "container");
        List list = (List) classR.getClassProto().getExtension(this.protocol.getClassAnnotation());
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        Iterable<Annotation> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation deserializeAnnotation : iterable) {
            arrayList.add(this.deserializer.deserializeAnnotation(deserializeAnnotation, classR.getNameResolver()));
        }
        return (List) arrayList;
    }

    @NotNull
    public List<AnnotationDescriptor> loadCallableAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind) {
        List list;
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(messageLite, "proto");
        Intrinsics.checkParameterIsNotNull(annotatedCallableKind, "kind");
        if (messageLite instanceof Constructor) {
            list = (List) ((Constructor) messageLite).getExtension(this.protocol.getConstructorAnnotation());
        } else if (messageLite instanceof Function) {
            list = (List) ((Function) messageLite).getExtension(this.protocol.getFunctionAnnotation());
        } else if (messageLite instanceof Property) {
            list = (List) ((Property) messageLite).getExtension(this.protocol.getPropertyAnnotation());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown message: ");
            sb.append(messageLite);
            throw new IllegalStateException(sb.toString().toString());
        }
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        Iterable<Annotation> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation deserializeAnnotation : iterable) {
            arrayList.add(this.deserializer.deserializeAnnotation(deserializeAnnotation, protoContainer.getNameResolver()));
        }
        return (List) arrayList;
    }

    @NotNull
    public List<AnnotationDescriptor> loadPropertyBackingFieldAnnotations(@NotNull ProtoContainer protoContainer, @NotNull Property property) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(property, "proto");
        return CollectionsKt.emptyList();
    }

    @NotNull
    public List<AnnotationDescriptor> loadPropertyDelegateFieldAnnotations(@NotNull ProtoContainer protoContainer, @NotNull Property property) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(property, "proto");
        return CollectionsKt.emptyList();
    }

    @NotNull
    public List<AnnotationDescriptor> loadEnumEntryAnnotations(@NotNull ProtoContainer protoContainer, @NotNull EnumEntry enumEntry) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(enumEntry, "proto");
        List list = (List) enumEntry.getExtension(this.protocol.getEnumEntryAnnotation());
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        Iterable<Annotation> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation deserializeAnnotation : iterable) {
            arrayList.add(this.deserializer.deserializeAnnotation(deserializeAnnotation, protoContainer.getNameResolver()));
        }
        return (List) arrayList;
    }

    @NotNull
    public List<AnnotationDescriptor> loadValueParameterAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind, int i, @NotNull ValueParameter valueParameter) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(messageLite, "callableProto");
        Intrinsics.checkParameterIsNotNull(annotatedCallableKind, "kind");
        Intrinsics.checkParameterIsNotNull(valueParameter, "proto");
        List list = (List) valueParameter.getExtension(this.protocol.getParameterAnnotation());
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        Iterable<Annotation> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation deserializeAnnotation : iterable) {
            arrayList.add(this.deserializer.deserializeAnnotation(deserializeAnnotation, protoContainer.getNameResolver()));
        }
        return (List) arrayList;
    }

    @NotNull
    public List<AnnotationDescriptor> loadExtensionReceiverParameterAnnotations(@NotNull ProtoContainer protoContainer, @NotNull MessageLite messageLite, @NotNull AnnotatedCallableKind annotatedCallableKind) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(messageLite, "proto");
        Intrinsics.checkParameterIsNotNull(annotatedCallableKind, "kind");
        return CollectionsKt.emptyList();
    }

    @NotNull
    public List<AnnotationDescriptor> loadTypeAnnotations(@NotNull Type type, @NotNull NameResolver nameResolver) {
        Intrinsics.checkParameterIsNotNull(type, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        List list = (List) type.getExtension(this.protocol.getTypeAnnotation());
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        Iterable<Annotation> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation deserializeAnnotation : iterable) {
            arrayList.add(this.deserializer.deserializeAnnotation(deserializeAnnotation, nameResolver));
        }
        return (List) arrayList;
    }

    @NotNull
    public List<AnnotationDescriptor> loadTypeParameterAnnotations(@NotNull TypeParameter typeParameter, @NotNull NameResolver nameResolver) {
        Intrinsics.checkParameterIsNotNull(typeParameter, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        List list = (List) typeParameter.getExtension(this.protocol.getTypeParameterAnnotation());
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        Iterable<Annotation> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation deserializeAnnotation : iterable) {
            arrayList.add(this.deserializer.deserializeAnnotation(deserializeAnnotation, nameResolver));
        }
        return (List) arrayList;
    }

    @Nullable
    public ConstantValue<?> loadPropertyConstant(@NotNull ProtoContainer protoContainer, @NotNull Property property, @NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(protoContainer, "container");
        Intrinsics.checkParameterIsNotNull(property, "proto");
        Intrinsics.checkParameterIsNotNull(kotlinType, "expectedType");
        Value value = (Value) ProtoBufUtilKt.getExtensionOrNull(property, this.protocol.getCompileTimeValue());
        if (value != null) {
            return this.deserializer.resolveValue(kotlinType, value, protoContainer.getNameResolver());
        }
        return null;
    }
}
