package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.BooleanFlagField;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmFieldSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JvmProtoBufUtil.kt */
public final class JvmProtoBufUtil {
    @NotNull
    private static final ExtensionRegistryLite EXTENSION_REGISTRY;
    public static final JvmProtoBufUtil INSTANCE = new JvmProtoBufUtil();

    static {
        ExtensionRegistryLite newInstance = ExtensionRegistryLite.newInstance();
        JvmProtoBuf.registerAllExtensions(newInstance);
        Intrinsics.checkExpressionValueIsNotNull(newInstance, "ExtensionRegistryLite.ne…f::registerAllExtensions)");
        EXTENSION_REGISTRY = newInstance;
    }

    private JvmProtoBufUtil() {
    }

    @NotNull
    public final ExtensionRegistryLite getEXTENSION_REGISTRY() {
        return EXTENSION_REGISTRY;
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Class> readClassDataFrom(@NotNull String[] strArr, @NotNull String[] strArr2) {
        Intrinsics.checkParameterIsNotNull(strArr, "data");
        Intrinsics.checkParameterIsNotNull(strArr2, "strings");
        byte[] decodeBytes = BitEncoding.decodeBytes(strArr);
        Intrinsics.checkExpressionValueIsNotNull(decodeBytes, "BitEncoding.decodeBytes(data)");
        return readClassDataFrom(decodeBytes, strArr2);
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Class> readClassDataFrom(@NotNull byte[] bArr, @NotNull String[] strArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        Intrinsics.checkParameterIsNotNull(strArr, "strings");
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strArr), Class.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Package> readPackageDataFrom(@NotNull String[] strArr, @NotNull String[] strArr2) {
        Intrinsics.checkParameterIsNotNull(strArr, "data");
        Intrinsics.checkParameterIsNotNull(strArr2, "strings");
        byte[] decodeBytes = BitEncoding.decodeBytes(strArr);
        Intrinsics.checkExpressionValueIsNotNull(decodeBytes, "BitEncoding.decodeBytes(data)");
        return readPackageDataFrom(decodeBytes, strArr2);
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Package> readPackageDataFrom(@NotNull byte[] bArr, @NotNull String[] strArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        Intrinsics.checkParameterIsNotNull(strArr, "strings");
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strArr), Package.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Function> readFunctionDataFrom(@NotNull String[] strArr, @NotNull String[] strArr2) {
        Intrinsics.checkParameterIsNotNull(strArr, "data");
        Intrinsics.checkParameterIsNotNull(strArr2, "strings");
        InputStream byteArrayInputStream = new ByteArrayInputStream(BitEncoding.decodeBytes(strArr));
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strArr2), Function.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    private final JvmNameResolver readNameResolver(@NotNull InputStream inputStream, String[] strArr) {
        StringTableTypes parseDelimitedFrom = StringTableTypes.parseDelimitedFrom(inputStream, EXTENSION_REGISTRY);
        Intrinsics.checkExpressionValueIsNotNull(parseDelimitedFrom, "JvmProtoBuf.StringTableT…this, EXTENSION_REGISTRY)");
        return new JvmNameResolver(parseDelimitedFrom, strArr);
    }

    @Nullable
    public final Method getJvmMethodSignature(@NotNull Function function, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable) {
        String str;
        Intrinsics.checkParameterIsNotNull(function, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        ExtendableMessage extendableMessage = function;
        GeneratedExtension<Function, JvmMethodSignature> generatedExtension = JvmProtoBuf.methodSignature;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "JvmProtoBuf.methodSignature");
        JvmMethodSignature jvmMethodSignature = (JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(extendableMessage, generatedExtension);
        int name = (jvmMethodSignature == null || !jvmMethodSignature.hasName()) ? function.getName() : jvmMethodSignature.getName();
        if (jvmMethodSignature == null || !jvmMethodSignature.hasDesc()) {
            Collection listOfNotNull = CollectionsKt.listOfNotNull(ProtoTypeTableUtilKt.receiverType(function, typeTable));
            List valueParameterList = function.getValueParameterList();
            Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
            Iterable<ValueParameter> iterable = valueParameterList;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ValueParameter valueParameter : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(valueParameter, "it");
                arrayList.add(ProtoTypeTableUtilKt.type(valueParameter, typeTable));
            }
            Iterable<Type> plus = CollectionsKt.plus(listOfNotNull, (Iterable<? extends T>) (List) arrayList);
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
            for (Type mapTypeDefault : plus) {
                String mapTypeDefault2 = INSTANCE.mapTypeDefault(mapTypeDefault, nameResolver);
                if (mapTypeDefault2 == null) {
                    return null;
                }
                arrayList2.add(mapTypeDefault2);
            }
            List list = (List) arrayList2;
            String mapTypeDefault3 = mapTypeDefault(ProtoTypeTableUtilKt.returnType(function, typeTable), nameResolver);
            if (mapTypeDefault3 == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(CollectionsKt.joinToString$default(list, "", "(", ")", 0, null, null, 56, null));
            sb.append(mapTypeDefault3);
            str = sb.toString();
        } else {
            str = nameResolver.getString(jvmMethodSignature.getDesc());
        }
        return new Method(nameResolver.getString(name), str);
    }

    @Nullable
    public final Method getJvmConstructorSignature(@NotNull Constructor constructor, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable) {
        String str;
        Intrinsics.checkParameterIsNotNull(constructor, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        ExtendableMessage extendableMessage = constructor;
        GeneratedExtension<Constructor, JvmMethodSignature> generatedExtension = JvmProtoBuf.constructorSignature;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "JvmProtoBuf.constructorSignature");
        JvmMethodSignature jvmMethodSignature = (JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(extendableMessage, generatedExtension);
        String string = (jvmMethodSignature == null || !jvmMethodSignature.hasName()) ? "<init>" : nameResolver.getString(jvmMethodSignature.getName());
        if (jvmMethodSignature == null || !jvmMethodSignature.hasDesc()) {
            List valueParameterList = constructor.getValueParameterList();
            Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
            Iterable<ValueParameter> iterable = valueParameterList;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ValueParameter valueParameter : iterable) {
                JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(valueParameter, "it");
                String mapTypeDefault = jvmProtoBufUtil.mapTypeDefault(ProtoTypeTableUtilKt.type(valueParameter, typeTable), nameResolver);
                if (mapTypeDefault == null) {
                    return null;
                }
                arrayList.add(mapTypeDefault);
            }
            str = CollectionsKt.joinToString$default((List) arrayList, "", "(", ")V", 0, null, null, 56, null);
        } else {
            str = nameResolver.getString(jvmMethodSignature.getDesc());
        }
        return new Method(string, str);
    }

    @Nullable
    public static /* synthetic */ Field getJvmFieldSignature$default(JvmProtoBufUtil jvmProtoBufUtil, Property property, NameResolver nameResolver, TypeTable typeTable, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return jvmProtoBufUtil.getJvmFieldSignature(property, nameResolver, typeTable, z);
    }

    @Nullable
    public final Field getJvmFieldSignature(@NotNull Property property, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, boolean z) {
        String str;
        Intrinsics.checkParameterIsNotNull(property, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        ExtendableMessage extendableMessage = property;
        GeneratedExtension<Property, JvmPropertySignature> generatedExtension = JvmProtoBuf.propertySignature;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "JvmProtoBuf.propertySignature");
        JvmPropertySignature jvmPropertySignature = (JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(extendableMessage, generatedExtension);
        if (jvmPropertySignature == null) {
            return null;
        }
        JvmFieldSignature field = jvmPropertySignature.hasField() ? jvmPropertySignature.getField() : null;
        if (field == null && z) {
            return null;
        }
        int name = (field == null || !field.hasName()) ? property.getName() : field.getName();
        if (field == null || !field.hasDesc()) {
            str = mapTypeDefault(ProtoTypeTableUtilKt.returnType(property, typeTable), nameResolver);
            if (str == null) {
                return null;
            }
        } else {
            str = nameResolver.getString(field.getDesc());
        }
        return new Field(nameResolver.getString(name), str);
    }

    private final String mapTypeDefault(Type type, NameResolver nameResolver) {
        if (type.hasClassName()) {
            return ClassMapperLite.mapClass(nameResolver.getQualifiedClassName(type.getClassName()));
        }
        return null;
    }

    @JvmStatic
    public static final boolean isMovedFromInterfaceCompanion(@NotNull Property property) {
        Intrinsics.checkParameterIsNotNull(property, "proto");
        BooleanFlagField is_moved_from_interface_companion = JvmFlags.INSTANCE.getIS_MOVED_FROM_INTERFACE_COMPANION();
        Object extension = property.getExtension(JvmProtoBuf.flags);
        Intrinsics.checkExpressionValueIsNotNull(extension, "proto.getExtension(JvmProtoBuf.flags)");
        Boolean bool = is_moved_from_interface_companion.get(((Number) extension).intValue());
        Intrinsics.checkExpressionValueIsNotNull(bool, "JvmFlags.IS_MOVED_FROM_I…nsion(JvmProtoBuf.flags))");
        return bool.booleanValue();
    }
}
