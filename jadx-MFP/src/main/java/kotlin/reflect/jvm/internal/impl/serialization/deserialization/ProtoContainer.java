package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProtoContainer.kt */
public abstract class ProtoContainer {
    @NotNull
    private final NameResolver nameResolver;
    @Nullable
    private final SourceElement source;
    @NotNull
    private final TypeTable typeTable;

    /* compiled from: ProtoContainer.kt */
    public static final class Class extends ProtoContainer {
        @NotNull
        private final ClassId classId;
        @NotNull
        private final kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class classProto;
        private final boolean isInner;
        @NotNull
        private final Kind kind;
        @Nullable
        private final Class outerClass;

        @NotNull
        public final kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class getClassProto() {
            return this.classProto;
        }

        @Nullable
        public final Class getOuterClass() {
            return this.outerClass;
        }

        public Class(@NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class classR, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @Nullable SourceElement sourceElement, @Nullable Class classR2) {
            Intrinsics.checkParameterIsNotNull(classR, "classProto");
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
            super(nameResolver, typeTable, sourceElement, null);
            this.classProto = classR;
            this.outerClass = classR2;
            this.classId = NameResolverUtilKt.getClassId(nameResolver, this.classProto.getFqName());
            Kind kind2 = (Kind) Flags.CLASS_KIND.get(this.classProto.getFlags());
            if (kind2 == null) {
                kind2 = Kind.CLASS;
            }
            this.kind = kind2;
            Boolean bool = Flags.IS_INNER.get(this.classProto.getFlags());
            Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_INNER.get(classProto.flags)");
            this.isInner = bool.booleanValue();
        }

        @NotNull
        public final ClassId getClassId() {
            return this.classId;
        }

        @NotNull
        public final Kind getKind() {
            return this.kind;
        }

        public final boolean isInner() {
            return this.isInner;
        }

        @NotNull
        public FqName debugFqName() {
            FqName asSingleFqName = this.classId.asSingleFqName();
            Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "classId.asSingleFqName()");
            return asSingleFqName;
        }
    }

    /* compiled from: ProtoContainer.kt */
    public static final class Package extends ProtoContainer {
        @NotNull
        private final FqName fqName;

        public Package(@NotNull FqName fqName2, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @Nullable SourceElement sourceElement) {
            Intrinsics.checkParameterIsNotNull(fqName2, "fqName");
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
            super(nameResolver, typeTable, sourceElement, null);
            this.fqName = fqName2;
        }

        @NotNull
        public FqName debugFqName() {
            return this.fqName;
        }
    }

    @NotNull
    public abstract FqName debugFqName();

    private ProtoContainer(NameResolver nameResolver2, TypeTable typeTable2, SourceElement sourceElement) {
        this.nameResolver = nameResolver2;
        this.typeTable = typeTable2;
        this.source = sourceElement;
    }

    public /* synthetic */ ProtoContainer(@NotNull NameResolver nameResolver2, @NotNull TypeTable typeTable2, @Nullable SourceElement sourceElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(nameResolver2, typeTable2, sourceElement);
    }

    @NotNull
    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    @NotNull
    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    @Nullable
    public final SourceElement getSource() {
        return this.source;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(": ");
        sb.append(debugFqName());
        return sb.toString();
    }
}
