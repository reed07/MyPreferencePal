package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import kotlin.reflect.jvm.internal.impl.serialization.SerializerExtensionProtocol;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInSerializerProtocol.kt */
public final class BuiltInSerializerProtocol extends SerializerExtensionProtocol {
    public static final BuiltInSerializerProtocol INSTANCE = new BuiltInSerializerProtocol();

    private BuiltInSerializerProtocol() {
        ExtensionRegistryLite newInstance = ExtensionRegistryLite.newInstance();
        BuiltInsProtoBuf.registerAllExtensions(newInstance);
        Intrinsics.checkExpressionValueIsNotNull(newInstance, "ExtensionRegistryLite.ne…sterAllExtensions(this) }");
        GeneratedExtension<Package, Integer> generatedExtension = BuiltInsProtoBuf.packageFqName;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "BuiltInsProtoBuf.packageFqName");
        GeneratedExtension<Constructor, List<Annotation>> generatedExtension2 = BuiltInsProtoBuf.constructorAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension2, "BuiltInsProtoBuf.constructorAnnotation");
        GeneratedExtension<Class, List<Annotation>> generatedExtension3 = BuiltInsProtoBuf.classAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension3, "BuiltInsProtoBuf.classAnnotation");
        GeneratedExtension<Function, List<Annotation>> generatedExtension4 = BuiltInsProtoBuf.functionAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension4, "BuiltInsProtoBuf.functionAnnotation");
        GeneratedExtension<Property, List<Annotation>> generatedExtension5 = BuiltInsProtoBuf.propertyAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension5, "BuiltInsProtoBuf.propertyAnnotation");
        GeneratedExtension<EnumEntry, List<Annotation>> generatedExtension6 = BuiltInsProtoBuf.enumEntryAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension6, "BuiltInsProtoBuf.enumEntryAnnotation");
        GeneratedExtension<Property, Value> generatedExtension7 = BuiltInsProtoBuf.compileTimeValue;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension7, "BuiltInsProtoBuf.compileTimeValue");
        GeneratedExtension<ValueParameter, List<Annotation>> generatedExtension8 = BuiltInsProtoBuf.parameterAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension8, "BuiltInsProtoBuf.parameterAnnotation");
        GeneratedExtension<Type, List<Annotation>> generatedExtension9 = BuiltInsProtoBuf.typeAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension9, "BuiltInsProtoBuf.typeAnnotation");
        GeneratedExtension<TypeParameter, List<Annotation>> generatedExtension10 = BuiltInsProtoBuf.typeParameterAnnotation;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension10, "BuiltInsProtoBuf.typeParameterAnnotation");
        super(newInstance, generatedExtension, generatedExtension2, generatedExtension3, generatedExtension4, generatedExtension5, generatedExtension6, generatedExtension7, generatedExtension8, generatedExtension9, generatedExtension10);
    }

    @NotNull
    public final String getBuiltInsFilePath(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        StringBuilder sb = new StringBuilder();
        String asString = fqName.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "fqName.asString()");
        sb.append(StringsKt.replace$default(asString, '.', '/', false, 4, (Object) null));
        sb.append("/");
        sb.append(getBuiltInsFileName(fqName));
        return sb.toString();
    }

    @NotNull
    public final String getBuiltInsFileName(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        StringBuilder sb = new StringBuilder();
        sb.append(shortName(fqName));
        sb.append(".");
        sb.append("kotlin_builtins");
        return sb.toString();
    }

    private final String shortName(FqName fqName) {
        if (fqName.isRoot()) {
            return "default-package";
        }
        String asString = fqName.shortName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "fqName.shortName().asString()");
        return asString;
    }
}
