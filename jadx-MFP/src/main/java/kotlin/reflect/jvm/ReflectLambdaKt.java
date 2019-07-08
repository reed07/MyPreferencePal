package kotlin.reflect.jvm;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.internal.EmptyContainerForLocal;
import kotlin.reflect.jvm.internal.KFunctionImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0004"}, d2 = {"reflect", "Lkotlin/reflect/KFunction;", "R", "Lkotlin/Function;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* compiled from: reflectLambda.kt */
public final class ReflectLambdaKt {
    @Nullable
    public static final <R> KFunction<R> reflect(@NotNull Function<? extends R> function) {
        Intrinsics.checkParameterIsNotNull(function, "receiver$0");
        Metadata metadata = (Metadata) function.getClass().getAnnotation(Metadata.class);
        if (metadata == null) {
            return null;
        }
        String[] d1 = metadata.d1();
        boolean z = true;
        if (d1.length == 0) {
            d1 = null;
        }
        if (d1 == null) {
            return null;
        }
        Pair readFunctionDataFrom = JvmProtoBufUtil.readFunctionDataFrom(d1, metadata.d2());
        JvmNameResolver jvmNameResolver = (JvmNameResolver) readFunctionDataFrom.component1();
        ProtoBuf.Function function2 = (ProtoBuf.Function) readFunctionDataFrom.component2();
        int[] mv = metadata.mv();
        if ((metadata.xi() & 8) == 0) {
            z = false;
        }
        JvmMetadataVersion jvmMetadataVersion = new JvmMetadataVersion(mv, z);
        Class cls = function.getClass();
        MessageLite messageLite = function2;
        NameResolver nameResolver = jvmNameResolver;
        TypeTable typeTable = function2.getTypeTable();
        Intrinsics.checkExpressionValueIsNotNull(typeTable, "proto.typeTable");
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) UtilKt.deserializeToDescriptor(cls, messageLite, nameResolver, new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(typeTable), jvmMetadataVersion, ReflectLambdaKt$reflect$descriptor$1.INSTANCE);
        if (simpleFunctionDescriptor != null) {
            return new KFunctionImpl<>(EmptyContainerForLocal.INSTANCE, simpleFunctionDescriptor);
        }
        return null;
    }
}
