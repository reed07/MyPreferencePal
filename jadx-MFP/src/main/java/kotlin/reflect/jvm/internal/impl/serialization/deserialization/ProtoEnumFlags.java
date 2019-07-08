package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Projection;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Variance;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProtoEnumFlags.kt */
public final class ProtoEnumFlags {
    public static final ProtoEnumFlags INSTANCE = new ProtoEnumFlags();

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Kind.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$3 = new int[Modality.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$6 = new int[ClassKind.values().length];

        static {
            $EnumSwitchMapping$0 = new int[MemberKind.values().length];
            $EnumSwitchMapping$0[MemberKind.DECLARATION.ordinal()] = 1;
            $EnumSwitchMapping$0[MemberKind.FAKE_OVERRIDE.ordinal()] = 2;
            $EnumSwitchMapping$0[MemberKind.DELEGATION.ordinal()] = 3;
            $EnumSwitchMapping$0[MemberKind.SYNTHESIZED.ordinal()] = 4;
            $EnumSwitchMapping$1[Kind.DECLARATION.ordinal()] = 1;
            $EnumSwitchMapping$1[Kind.FAKE_OVERRIDE.ordinal()] = 2;
            $EnumSwitchMapping$1[Kind.DELEGATION.ordinal()] = 3;
            $EnumSwitchMapping$1[Kind.SYNTHESIZED.ordinal()] = 4;
            $EnumSwitchMapping$2 = new int[ProtoBuf.Modality.values().length];
            $EnumSwitchMapping$2[ProtoBuf.Modality.FINAL.ordinal()] = 1;
            $EnumSwitchMapping$2[ProtoBuf.Modality.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$2[ProtoBuf.Modality.ABSTRACT.ordinal()] = 3;
            $EnumSwitchMapping$2[ProtoBuf.Modality.SEALED.ordinal()] = 4;
            $EnumSwitchMapping$3[Modality.FINAL.ordinal()] = 1;
            $EnumSwitchMapping$3[Modality.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$3[Modality.ABSTRACT.ordinal()] = 3;
            $EnumSwitchMapping$3[Modality.SEALED.ordinal()] = 4;
            $EnumSwitchMapping$4 = new int[Visibility.values().length];
            $EnumSwitchMapping$4[Visibility.INTERNAL.ordinal()] = 1;
            $EnumSwitchMapping$4[Visibility.PRIVATE.ordinal()] = 2;
            $EnumSwitchMapping$4[Visibility.PRIVATE_TO_THIS.ordinal()] = 3;
            $EnumSwitchMapping$4[Visibility.PROTECTED.ordinal()] = 4;
            $EnumSwitchMapping$4[Visibility.PUBLIC.ordinal()] = 5;
            $EnumSwitchMapping$4[Visibility.LOCAL.ordinal()] = 6;
            $EnumSwitchMapping$5 = new int[Class.Kind.values().length];
            $EnumSwitchMapping$5[Class.Kind.CLASS.ordinal()] = 1;
            $EnumSwitchMapping$5[Class.Kind.INTERFACE.ordinal()] = 2;
            $EnumSwitchMapping$5[Class.Kind.ENUM_CLASS.ordinal()] = 3;
            $EnumSwitchMapping$5[Class.Kind.ENUM_ENTRY.ordinal()] = 4;
            $EnumSwitchMapping$5[Class.Kind.ANNOTATION_CLASS.ordinal()] = 5;
            $EnumSwitchMapping$5[Class.Kind.OBJECT.ordinal()] = 6;
            $EnumSwitchMapping$5[Class.Kind.COMPANION_OBJECT.ordinal()] = 7;
            $EnumSwitchMapping$6[ClassKind.CLASS.ordinal()] = 1;
            $EnumSwitchMapping$6[ClassKind.INTERFACE.ordinal()] = 2;
            $EnumSwitchMapping$6[ClassKind.ENUM_CLASS.ordinal()] = 3;
            $EnumSwitchMapping$6[ClassKind.ENUM_ENTRY.ordinal()] = 4;
            $EnumSwitchMapping$6[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
            $EnumSwitchMapping$6[ClassKind.OBJECT.ordinal()] = 6;
            $EnumSwitchMapping$7 = new int[Variance.values().length];
            $EnumSwitchMapping$7[Variance.IN.ordinal()] = 1;
            $EnumSwitchMapping$7[Variance.OUT.ordinal()] = 2;
            $EnumSwitchMapping$7[Variance.INV.ordinal()] = 3;
            $EnumSwitchMapping$8 = new int[Projection.values().length];
            $EnumSwitchMapping$8[Projection.IN.ordinal()] = 1;
            $EnumSwitchMapping$8[Projection.OUT.ordinal()] = 2;
            $EnumSwitchMapping$8[Projection.INV.ordinal()] = 3;
            $EnumSwitchMapping$8[Projection.STAR.ordinal()] = 4;
        }
    }

    private ProtoEnumFlags() {
    }

    @NotNull
    public final Kind memberKind(@Nullable MemberKind memberKind) {
        if (memberKind != null) {
            switch (memberKind) {
                case DECLARATION:
                    return Kind.DECLARATION;
                case FAKE_OVERRIDE:
                    return Kind.FAKE_OVERRIDE;
                case DELEGATION:
                    return Kind.DELEGATION;
                case SYNTHESIZED:
                    return Kind.SYNTHESIZED;
            }
        }
        return Kind.DECLARATION;
    }

    @NotNull
    public final Modality modality(@Nullable ProtoBuf.Modality modality) {
        if (modality != null) {
            switch (modality) {
                case FINAL:
                    return Modality.FINAL;
                case OPEN:
                    return Modality.OPEN;
                case ABSTRACT:
                    return Modality.ABSTRACT;
                case SEALED:
                    return Modality.SEALED;
            }
        }
        return Modality.FINAL;
    }

    @NotNull
    public final kotlin.reflect.jvm.internal.impl.descriptors.Visibility visibility(@Nullable Visibility visibility) {
        kotlin.reflect.jvm.internal.impl.descriptors.Visibility visibility2;
        if (visibility != null) {
            switch (visibility) {
                case INTERNAL:
                    visibility2 = Visibilities.INTERNAL;
                    break;
                case PRIVATE:
                    visibility2 = Visibilities.PRIVATE;
                    break;
                case PRIVATE_TO_THIS:
                    visibility2 = Visibilities.PRIVATE_TO_THIS;
                    break;
                case PROTECTED:
                    visibility2 = Visibilities.PROTECTED;
                    break;
                case PUBLIC:
                    visibility2 = Visibilities.PUBLIC;
                    break;
                case LOCAL:
                    visibility2 = Visibilities.LOCAL;
                    break;
            }
        }
        visibility2 = Visibilities.PRIVATE;
        Intrinsics.checkExpressionValueIsNotNull(visibility2, "when (visibility) {\n    …isibilities.PRIVATE\n    }");
        return visibility2;
    }

    @NotNull
    public final ClassKind classKind(@Nullable Class.Kind kind) {
        if (kind != null) {
            switch (kind) {
                case CLASS:
                    return ClassKind.CLASS;
                case INTERFACE:
                    return ClassKind.INTERFACE;
                case ENUM_CLASS:
                    return ClassKind.ENUM_CLASS;
                case ENUM_ENTRY:
                    return ClassKind.ENUM_ENTRY;
                case ANNOTATION_CLASS:
                    return ClassKind.ANNOTATION_CLASS;
                case OBJECT:
                case COMPANION_OBJECT:
                    return ClassKind.OBJECT;
            }
        }
        return ClassKind.CLASS;
    }

    @NotNull
    public final kotlin.reflect.jvm.internal.impl.types.Variance variance(@NotNull Variance variance) {
        Intrinsics.checkParameterIsNotNull(variance, "variance");
        switch (variance) {
            case IN:
                return kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE;
            case OUT:
                return kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE;
            case INV:
                return kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @NotNull
    public final kotlin.reflect.jvm.internal.impl.types.Variance variance(@NotNull Projection projection) {
        Intrinsics.checkParameterIsNotNull(projection, "projection");
        switch (projection) {
            case IN:
                return kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE;
            case OUT:
                return kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE;
            case INV:
                return kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT;
            case STAR:
                StringBuilder sb = new StringBuilder();
                sb.append("Only IN, OUT and INV are supported. Actual argument: ");
                sb.append(projection);
                throw new IllegalArgumentException(sb.toString());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
