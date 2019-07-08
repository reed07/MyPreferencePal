package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaFlexibleTypeDeserializer.kt */
public final class JavaFlexibleTypeDeserializer implements FlexibleTypeDeserializer {
    public static final JavaFlexibleTypeDeserializer INSTANCE = new JavaFlexibleTypeDeserializer();

    private JavaFlexibleTypeDeserializer() {
    }

    @NotNull
    public KotlinType create(@NotNull Type type, @NotNull String str, @NotNull SimpleType simpleType, @NotNull SimpleType simpleType2) {
        Intrinsics.checkParameterIsNotNull(type, "proto");
        Intrinsics.checkParameterIsNotNull(str, "flexibleId");
        Intrinsics.checkParameterIsNotNull(simpleType, "lowerBound");
        Intrinsics.checkParameterIsNotNull(simpleType2, "upperBound");
        if (!Intrinsics.areEqual((Object) str, (Object) "kotlin.jvm.PlatformType")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error java flexible type with id: ");
            sb.append(str);
            sb.append(". (");
            sb.append(simpleType);
            sb.append("..");
            sb.append(simpleType2);
            sb.append(')');
            SimpleType createErrorType = ErrorUtils.createErrorType(sb.toString());
            Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorTy…owerBound..$upperBound)\")");
            return createErrorType;
        } else if (type.hasExtension(JvmProtoBuf.isRaw)) {
            return new RawTypeImpl(simpleType, simpleType2);
        } else {
            return KotlinTypeFactory.flexibleType(simpleType, simpleType2);
        }
    }
}
