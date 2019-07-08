package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.VersionKind;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement.Version;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Package;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor.CoroutinesCompatibilityMode;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberDeserializer.kt */
public final class MemberDeserializer {
    private final AnnotationDeserializer annotationDeserializer = new AnnotationDeserializer(this.c.getComponents().getModuleDescriptor(), this.c.getComponents().getNotFoundClasses());
    /* access modifiers changed from: private */
    public final DeserializationContext c;

    private final int loadOldFlags(int i) {
        return (i & 63) + ((i >> 8) << 6);
    }

    public MemberDeserializer(@NotNull DeserializationContext deserializationContext) {
        Intrinsics.checkParameterIsNotNull(deserializationContext, "c");
        this.c = deserializationContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0257  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x032f  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor loadProperty(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property r38) {
        /*
            r37 = this;
            r0 = r37
            r15 = r38
            java.lang.String r1 = "proto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r1)
            boolean r1 = r38.hasFlags()
            if (r1 == 0) goto L_0x0014
            int r1 = r38.getFlags()
            goto L_0x001c
        L_0x0014:
            int r1 = r38.getOldFlags()
            int r1 = r0.loadOldFlags(r1)
        L_0x001c:
            r14 = r1
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor r13 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor
            r1 = r13
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r0.c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r2 = r2.getContainingDeclaration()
            r3 = 0
            r12 = r15
            kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r12 = (kotlin.reflect.jvm.internal.impl.protobuf.MessageLite) r12
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r4 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r4 = r0.getAnnotations(r12, r14, r4)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r5 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality> r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY
            java.lang.Object r6 = r6.get(r14)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality r6 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r6
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r5 = r5.modality(r6)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            java.lang.Object r7 = r7.get(r14)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r7 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r7
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r6 = r6.visibility(r7)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r7 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_VAR
            java.lang.Boolean r7 = r7.get(r14)
            java.lang.String r8 = "Flags.IS_VAR.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            boolean r7 = r7.booleanValue()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r8 = r0.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r8 = r8.getNameResolver()
            int r9 = r38.getName()
            kotlin.reflect.jvm.internal.impl.name.Name r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r8, r9)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r9 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$MemberKind> r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MEMBER_KIND
            java.lang.Object r10 = r10.get(r14)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$MemberKind r10 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind) r10
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r9 = r9.memberKind(r10)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_LATEINIT
            java.lang.Boolean r10 = r10.get(r14)
            java.lang.String r11 = "Flags.IS_LATEINIT.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r11)
            boolean r10 = r10.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r11 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_CONST
            java.lang.Boolean r11 = r11.get(r14)
            java.lang.String r3 = "Flags.IS_CONST.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r3)
            boolean r11 = r11.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_PROPERTY
            java.lang.Boolean r3 = r3.get(r14)
            r16 = r12
            java.lang.String r12 = "Flags.IS_EXTERNAL_PROPERTY.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r12)
            boolean r12 = r3.booleanValue()
            r3 = r16
            r21 = r3
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_DELEGATED
            java.lang.Boolean r3 = r3.get(r14)
            r16 = r13
            java.lang.String r13 = "Flags.IS_DELEGATED.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r13)
            boolean r13 = r3.booleanValue()
            r3 = r16
            r22 = r3
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXPECT_PROPERTY
            java.lang.Boolean r3 = r3.get(r14)
            r16 = r14
            java.lang.String r14 = "Flags.IS_EXPECT_PROPERTY.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r14)
            boolean r14 = r3.booleanValue()
            r3 = r16
            r23 = r3
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r0.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r16 = r3.getNameResolver()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r0.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r17 = r3.getTypeTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r0.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r18 = r3.getVersionRequirementTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r0.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r19 = r3.getContainerSource()
            r3 = r15
            r15 = r38
            r26 = r21
            r25 = r22
            r24 = r23
            r3 = 0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r0.c
            r2 = r25
            r28 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r28 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r28
            java.util.List r3 = r38.getTypeParameterList()
            java.lang.String r4 = "proto.typeParameterList"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 60
            r35 = 0
            r27 = r1
            r29 = r3
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r27, r28, r29, r30, r31, r32, r33, r34, r35)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_GETTER
            r13 = r24
            java.lang.Boolean r3 = r3.get(r13)
            java.lang.String r4 = "Flags.HAS_GETTER.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x0140
            boolean r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.hasReceiver(r38)
            if (r4 == 0) goto L_0x0140
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r4 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_GETTER
            r14 = r26
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r4 = r0.getReceiverParameterAnnotations(r14, r4)
            goto L_0x0148
        L_0x0140:
            r14 = r26
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r4 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r4 = r4.getEMPTY()
        L_0x0148:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r5 = r1.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r6 = r0.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r6 = r6.getTypeTable()
            r15 = r38
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.returnType(r15, r6)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r5 = r5.type(r6)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r6 = r1.getTypeDeserializer()
            java.util.List r6 = r6.getOwnTypeParameters()
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r7 = r37.getDispatchReceiverParameter()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r8 = r0.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r8 = r8.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r8 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.receiverType(r15, r8)
            r9 = 0
            if (r8 == 0) goto L_0x0187
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r10 = r1.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r8 = r10.type(r8)
            if (r8 == 0) goto L_0x0187
            r10 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r10 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor) r10
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createExtensionReceiverParameterForCallable(r10, r8, r4)
            goto L_0x0188
        L_0x0187:
            r4 = r9
        L_0x0188:
            r2.setType(r5, r6, r7, r4)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_ANNOTATIONS
            java.lang.Boolean r4 = r4.get(r13)
            java.lang.String r5 = "Flags.HAS_ANNOTATIONS.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            boolean r16 = r4.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            java.lang.Object r4 = r4.get(r13)
            r17 = r4
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r17 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r17
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality> r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY
            java.lang.Object r4 = r4.get(r13)
            r18 = r4
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality r18 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r18
            r19 = 0
            r20 = 0
            r21 = 0
            int r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.getAccessorFlags(r16, r17, r18, r19, r20, r21)
            r12 = 1
            if (r3 == 0) goto L_0x0245
            boolean r3 = r38.hasGetterFlags()
            if (r3 == 0) goto L_0x01c6
            int r3 = r38.getGetterFlags()
            goto L_0x01c7
        L_0x01c6:
            r3 = r4
        L_0x01c7:
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_NOT_DEFAULT
            java.lang.Boolean r5 = r5.get(r3)
            java.lang.String r6 = "Flags.IS_NOT_DEFAULT.get(getterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            boolean r5 = r5.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_ACCESSOR
            java.lang.Boolean r6 = r6.get(r3)
            java.lang.String r7 = "Flags.IS_EXTERNAL_ACCESSOR.get(getterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            boolean r22 = r6.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_INLINE_ACCESSOR
            java.lang.Boolean r6 = r6.get(r3)
            java.lang.String r7 = "Flags.IS_INLINE_ACCESSOR.get(getterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            boolean r23 = r6.booleanValue()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_GETTER
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = r0.getAnnotations(r14, r3, r6)
            if (r5 == 0) goto L_0x0230
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl r7 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl
            r17 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r17 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r17
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality> r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY
            java.lang.Object r10 = r10.get(r3)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality r10 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r10
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r19 = r8.modality(r10)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r8 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r10 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            java.lang.Object r3 = r10.get(r3)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r3 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r3
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r20 = r8.visibility(r3)
            r21 = r5 ^ 1
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r24 = r2.getKind()
            r25 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r26 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE
            r16 = r7
            r18 = r6
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            goto L_0x023c
        L_0x0230:
            r3 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r3
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl r7 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createDefaultGetter(r3, r6)
            java.lang.String r3 = "DescriptorFactory.create…er(property, annotations)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r3)
        L_0x023c:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = r2.getReturnType()
            r7.initialize(r3)
            r3 = r7
            goto L_0x0246
        L_0x0245:
            r3 = r9
        L_0x0246:
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_SETTER
            java.lang.Boolean r5 = r5.get(r13)
            java.lang.String r6 = "Flags.HAS_SETTER.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x031b
            boolean r5 = r38.hasSetterFlags()
            if (r5 == 0) goto L_0x0261
            int r4 = r38.getSetterFlags()
        L_0x0261:
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_NOT_DEFAULT
            java.lang.Boolean r5 = r5.get(r4)
            java.lang.String r6 = "Flags.IS_NOT_DEFAULT.get(setterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            boolean r5 = r5.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_ACCESSOR
            java.lang.Boolean r6 = r6.get(r4)
            java.lang.String r7 = "Flags.IS_EXTERNAL_ACCESSOR.get(setterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            boolean r22 = r6.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r6 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_INLINE_ACCESSOR
            java.lang.Boolean r6 = r6.get(r4)
            java.lang.String r7 = "Flags.IS_INLINE_ACCESSOR.get(setterFlags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            boolean r23 = r6.booleanValue()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_SETTER
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = r0.getAnnotations(r14, r4, r6)
            if (r5 == 0) goto L_0x0305
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl r11 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl
            r17 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r17 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r17
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r7 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality> r8 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY
            java.lang.Object r8 = r8.get(r4)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality r8 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r8
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r19 = r7.modality(r8)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r7 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r8 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            java.lang.Object r4 = r8.get(r4)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r4 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r4
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r20 = r7.visibility(r4)
            r21 = r5 ^ 1
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r24 = r2.getKind()
            r25 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r26 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE
            r16 = r11
            r18 = r6
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            r5 = r11
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r5
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r16 = 60
            r17 = 0
            r4 = r1
            r36 = r11
            r11 = r16
            r16 = r3
            r3 = 1
            r12 = r17
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r4 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r4 = r4.getMemberDeserializer()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter r5 = r38.getSetterValueParameter()
            java.util.List r5 = kotlin.collections.CollectionsKt.listOf(r5)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_SETTER
            java.util.List r4 = r4.valueParameters(r5, r14, r6)
            java.lang.Object r4 = kotlin.collections.CollectionsKt.single(r4)
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r4
            r5 = r36
            r5.initialize(r4)
            r9 = r5
            goto L_0x031e
        L_0x0305:
            r16 = r3
            r3 = 1
            r4 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r4
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r5 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r5 = r5.getEMPTY()
            kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl r9 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createDefaultSetter(r4, r6, r5)
            java.lang.String r4 = "DescriptorFactory.create…ptor */\n                )"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r4)
            goto L_0x031e
        L_0x031b:
            r16 = r3
            r3 = 1
        L_0x031e:
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_CONSTANT
            java.lang.Boolean r4 = r4.get(r13)
            java.lang.String r5 = "Flags.HAS_CONSTANT.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0343
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r4 = r0.c
            kotlin.reflect.jvm.internal.impl.storage.StorageManager r4 = r4.getStorageManager()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$loadProperty$3 r5 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$loadProperty$3
            r5.<init>(r0, r15, r2)
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue r4 = r4.createNullableLazyValue(r5)
            r2.setCompileTimeInitializer(r4)
        L_0x0343:
            r4 = r9
            kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor) r4
            kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl r5 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl
            r6 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r6 = r0.getPropertyFieldAnnotations(r15, r6)
            r13 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r13 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r13
            r5.<init>(r6, r13)
            kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor) r5
            kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl r6 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r3 = r0.getPropertyFieldAnnotations(r15, r3)
            r6.<init>(r3, r13)
            kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor r6 = (kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor) r6
            r3 = r2
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor r3 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor) r3
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r1 = r1.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor$CoroutinesCompatibilityMode r7 = r0.checkExperimentalCoroutine(r3, r1)
            r1 = r2
            r2 = r16
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r1.initialize(r2, r3, r4, r5, r6)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.loadProperty(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property):kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor");
    }

    private final CoroutinesCompatibilityMode checkExperimentalCoroutine(@NotNull DeserializedMemberDescriptor deserializedMemberDescriptor, TypeDeserializer typeDeserializer) {
        CoroutinesCompatibilityMode coroutinesCompatibilityMode;
        if (!versionAndReleaseCoroutinesMismatch(deserializedMemberDescriptor)) {
            return CoroutinesCompatibilityMode.COMPATIBLE;
        }
        forceUpperBoundsComputation(typeDeserializer);
        if (typeDeserializer.getExperimentalSuspendFunctionTypeEncountered()) {
            coroutinesCompatibilityMode = CoroutinesCompatibilityMode.INCOMPATIBLE;
        } else {
            coroutinesCompatibilityMode = CoroutinesCompatibilityMode.COMPATIBLE;
        }
        return coroutinesCompatibilityMode;
    }

    private final void forceUpperBoundsComputation(TypeDeserializer typeDeserializer) {
        for (TypeParameterDescriptor upperBounds : typeDeserializer.getOwnTypeParameters()) {
            upperBounds.getUpperBounds();
        }
    }

    private final void initializeWithCoroutinesExperimentalityStatus(@NotNull DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor2, List<? extends TypeParameterDescriptor> list, List<? extends ValueParameterDescriptor> list2, KotlinType kotlinType, Modality modality, Visibility visibility, Map<? extends UserDataKey<?>, ?> map, boolean z) {
        KotlinType kotlinType2 = kotlinType;
        deserializedSimpleFunctionDescriptor.initialize(receiverParameterDescriptor, receiverParameterDescriptor2, list, list2, kotlinType2, modality, visibility, map, computeExperimentalityModeForFunctions(deserializedSimpleFunctionDescriptor, receiverParameterDescriptor, list2, list, kotlinType2, z));
    }

    private final CoroutinesCompatibilityMode computeExperimentalityModeForFunctions(@NotNull DeserializedCallableMemberDescriptor deserializedCallableMemberDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor, Collection<? extends ValueParameterDescriptor> collection, Collection<? extends TypeParameterDescriptor> collection2, KotlinType kotlinType, boolean z) {
        boolean z2;
        CoroutinesCompatibilityMode coroutinesCompatibilityMode;
        CoroutinesCompatibilityMode coroutinesCompatibilityMode2;
        boolean z3;
        boolean z4;
        if (!versionAndReleaseCoroutinesMismatch(deserializedCallableMemberDescriptor)) {
            return CoroutinesCompatibilityMode.COMPATIBLE;
        }
        if (Intrinsics.areEqual((Object) DescriptorUtilsKt.fqNameOrNull(deserializedCallableMemberDescriptor), (Object) SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            return CoroutinesCompatibilityMode.COMPATIBLE;
        }
        Iterable<ValueParameterDescriptor> iterable = collection;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ValueParameterDescriptor type : iterable) {
            arrayList.add(type.getType());
        }
        List plus = CollectionsKt.plus((List) arrayList, (Iterable<? extends T>) CollectionsKt.listOfNotNull(receiverParameterDescriptor != null ? receiverParameterDescriptor.getType() : null));
        if (kotlinType != null && containsSuspendFunctionType(kotlinType)) {
            return CoroutinesCompatibilityMode.INCOMPATIBLE;
        }
        Iterable iterable2 = collection2;
        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
            Iterator it = iterable2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                List upperBounds = ((TypeParameterDescriptor) it.next()).getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds, "typeParameter.upperBounds");
                Iterable iterable3 = upperBounds;
                if (!(iterable3 instanceof Collection) || !((Collection) iterable3).isEmpty()) {
                    Iterator it2 = iterable3.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z4 = false;
                            continue;
                            break;
                        }
                        KotlinType kotlinType2 = (KotlinType) it2.next();
                        Intrinsics.checkExpressionValueIsNotNull(kotlinType2, "it");
                        if (containsSuspendFunctionType(kotlinType2)) {
                            z4 = true;
                            continue;
                            break;
                        }
                    }
                } else {
                    z4 = false;
                    continue;
                }
                if (z4) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        if (z2) {
            return CoroutinesCompatibilityMode.INCOMPATIBLE;
        }
        Iterable<KotlinType> iterable4 = plus;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable4, 10));
        for (KotlinType kotlinType3 : iterable4) {
            Intrinsics.checkExpressionValueIsNotNull(kotlinType3, "type");
            if (FunctionTypesKt.isSuspendFunctionType(kotlinType3) && kotlinType3.getArguments().size() <= 3) {
                Iterable arguments = kotlinType3.getArguments();
                if (!(arguments instanceof Collection) || !((Collection) arguments).isEmpty()) {
                    Iterator it3 = arguments.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z3 = false;
                            break;
                        }
                        KotlinType type2 = ((TypeProjection) it3.next()).getType();
                        Intrinsics.checkExpressionValueIsNotNull(type2, "it.type");
                        if (containsSuspendFunctionType(type2)) {
                            z3 = true;
                            break;
                        }
                    }
                } else {
                    z3 = false;
                }
                if (z3) {
                    coroutinesCompatibilityMode2 = CoroutinesCompatibilityMode.INCOMPATIBLE;
                } else {
                    coroutinesCompatibilityMode2 = CoroutinesCompatibilityMode.NEEDS_WRAPPER;
                }
            } else if (containsSuspendFunctionType(kotlinType3)) {
                coroutinesCompatibilityMode2 = CoroutinesCompatibilityMode.INCOMPATIBLE;
            } else {
                coroutinesCompatibilityMode2 = CoroutinesCompatibilityMode.COMPATIBLE;
            }
            arrayList2.add(coroutinesCompatibilityMode2);
        }
        CoroutinesCompatibilityMode coroutinesCompatibilityMode3 = (CoroutinesCompatibilityMode) CollectionsKt.max((Iterable<? extends T>) (List) arrayList2);
        if (coroutinesCompatibilityMode3 == null) {
            coroutinesCompatibilityMode3 = CoroutinesCompatibilityMode.COMPATIBLE;
        }
        if (z) {
            coroutinesCompatibilityMode = CoroutinesCompatibilityMode.NEEDS_WRAPPER;
        } else {
            coroutinesCompatibilityMode = CoroutinesCompatibilityMode.COMPATIBLE;
        }
        return (CoroutinesCompatibilityMode) ComparisonsKt.maxOf(coroutinesCompatibilityMode, coroutinesCompatibilityMode3);
    }

    private final boolean containsSuspendFunctionType(@NotNull KotlinType kotlinType) {
        return TypeUtilsKt.contains(kotlinType, MemberDeserializer$containsSuspendFunctionType$1.INSTANCE);
    }

    private final boolean versionAndReleaseCoroutinesMismatch(@NotNull DeserializedMemberDescriptor deserializedMemberDescriptor) {
        boolean z;
        boolean z2;
        if (!this.c.getComponents().getConfiguration().getReleaseCoroutines()) {
            return false;
        }
        Iterable versionRequirements = deserializedMemberDescriptor.getVersionRequirements();
        if (!(versionRequirements instanceof Collection) || !((Collection) versionRequirements).isEmpty()) {
            Iterator it = versionRequirements.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                VersionRequirement versionRequirement = (VersionRequirement) it.next();
                Version version = versionRequirement.getVersion();
                Version version2 = new Version(1, 3, 0, 4, null);
                if (!Intrinsics.areEqual((Object) version, (Object) version2) || versionRequirement.getKind() != VersionKind.LANGUAGE_VERSION) {
                    z2 = false;
                    continue;
                } else {
                    z2 = true;
                    continue;
                }
                if (z2) {
                    z = false;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0203  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor loadFunction(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function r36) {
        /*
            r35 = this;
            r11 = r35
            r10 = r36
            java.lang.String r0 = "proto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            boolean r0 = r36.hasFlags()
            if (r0 == 0) goto L_0x0014
            int r0 = r36.getFlags()
            goto L_0x001c
        L_0x0014:
            int r0 = r36.getOldFlags()
            int r0 = r11.loadOldFlags(r0)
        L_0x001c:
            r9 = r0
            r0 = r10
            kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r0 = (kotlin.reflect.jvm.internal.impl.protobuf.MessageLite) r0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.FUNCTION
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r15 = r11.getAnnotations(r0, r9, r1)
            boolean r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.hasReceiver(r36)
            if (r1 == 0) goto L_0x0033
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.FUNCTION
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r1 = r11.getReceiverParameterAnnotations(r0, r1)
            goto L_0x0039
        L_0x0033:
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r1 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r1 = r1.getEMPTY()
        L_0x0039:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r2 = r2.getContainingDeclaration()
            kotlin.reflect.jvm.internal.impl.name.FqName r2 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getFqNameSafe(r2)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r11.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r3 = r3.getNameResolver()
            int r4 = r36.getName()
            kotlin.reflect.jvm.internal.impl.name.Name r3 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r3, r4)
            kotlin.reflect.jvm.internal.impl.name.FqName r2 = r2.child(r3)
            kotlin.reflect.jvm.internal.impl.name.FqName r3 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 == 0) goto L_0x0066
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable$Companion r2 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r2 = r2.getEMPTY()
            r21 = r2
            goto L_0x006e
        L_0x0066:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r2 = r2.getVersionRequirementTable()
            r21 = r2
        L_0x006e:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor r8 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r13 = r2.getContainingDeclaration()
            r14 = 0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r2 = r2.getNameResolver()
            int r3 = r36.getName()
            kotlin.reflect.jvm.internal.impl.name.Name r16 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r2, r3)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r2 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$MemberKind> r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MEMBER_KIND
            java.lang.Object r3 = r3.get(r9)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$MemberKind r3 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind) r3
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r17 = r2.memberKind(r3)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r19 = r2.getNameResolver()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r20 = r2.getTypeTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r22 = r2.getContainerSource()
            r23 = 0
            r24 = 1024(0x400, float:1.435E-42)
            r25 = 0
            r12 = r8
            r18 = r36
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            r27 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r27 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r27
            java.util.List r3 = r36.getTypeParameterList()
            java.lang.String r4 = "proto.typeParameterList"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 60
            r34 = 0
            r26 = r2
            r28 = r3
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r26, r27, r28, r29, r30, r31, r32, r33, r34)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r11.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r3 = r3.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r3 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.receiverType(r10, r3)
            if (r3 == 0) goto L_0x00f2
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r4 = r2.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = r4.type(r3)
            if (r3 == 0) goto L_0x00f2
            r4 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor) r4
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r1 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory.createExtensionReceiverParameterForCallable(r4, r3, r1)
            goto L_0x00f3
        L_0x00f2:
            r1 = 0
        L_0x00f3:
            r3 = r1
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r4 = r35.getDispatchReceiverParameter()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r1 = r2.getTypeDeserializer()
            java.util.List r5 = r1.getOwnTypeParameters()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r1 = r2.getMemberDeserializer()
            java.util.List r6 = r36.getValueParameterList()
            java.lang.String r7 = "proto.valueParameterList"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r7 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.FUNCTION
            java.util.List r6 = r1.valueParameters(r6, r0, r7)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r0 = r2.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r11.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r1 = r1.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.returnType(r10, r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r7 = r0.type(r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality> r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.MODALITY
            java.lang.Object r1 = r1.get(r9)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality r1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality) r1
            kotlin.reflect.jvm.internal.impl.descriptors.Modality r12 = r0.modality(r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            java.lang.Object r1 = r1.get(r9)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r1
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r13 = r0.visibility(r1)
            java.util.Map r14 = kotlin.collections.MapsKt.emptyMap()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_SUSPEND
            java.lang.Boolean r0 = r0.get(r9)
            java.lang.String r1 = "Flags.IS_SUSPEND.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r15 = r0.booleanValue()
            r0 = r35
            r1 = r8
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r12
            r12 = r8
            r8 = r13
            r13 = r9
            r9 = r14
            r14 = r10
            r10 = r15
            r0.initializeWithCoroutinesExperimentalityStatus(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_OPERATOR
            java.lang.Boolean r0 = r0.get(r13)
            java.lang.String r1 = "Flags.IS_OPERATOR.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            r12.setOperator(r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_INFIX
            java.lang.Boolean r0 = r0.get(r13)
            java.lang.String r1 = "Flags.IS_INFIX.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            r12.setInfix(r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXTERNAL_FUNCTION
            java.lang.Boolean r0 = r0.get(r13)
            java.lang.String r1 = "Flags.IS_EXTERNAL_FUNCTION.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            r12.setExternal(r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_INLINE
            java.lang.Boolean r0 = r0.get(r13)
            java.lang.String r1 = "Flags.IS_INLINE.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            r12.setInline(r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_TAILREC
            java.lang.Boolean r0 = r0.get(r13)
            java.lang.String r1 = "Flags.IS_TAILREC.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            r12.setTailrec(r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_SUSPEND
            java.lang.Boolean r0 = r0.get(r13)
            java.lang.String r1 = "Flags.IS_SUSPEND.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            r12.setSuspend(r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_EXPECT_FUNCTION
            java.lang.Boolean r0 = r0.get(r13)
            java.lang.String r1 = "Flags.IS_EXPECT_FUNCTION.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            r12.setExpect(r0)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r11.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r0 = r0.getComponents()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer r0 = r0.getContractDeserializer()
            r8 = r12
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r8 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r8
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r11.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r1 = r1.getTypeTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r11.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r2 = r2.getTypeDeserializer()
            kotlin.Pair r0 = r0.deserializeContractFromFunction(r14, r8, r1, r2)
            if (r0 == 0) goto L_0x0210
            java.lang.Object r1 = r0.getFirst()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor$UserDataKey r1 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey) r1
            java.lang.Object r0 = r0.getSecond()
            r12.putInUserDataMap(r1, r0)
        L_0x0210:
            r8 = r12
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r8 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.loadFunction(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function):kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor");
    }

    @NotNull
    public final TypeAliasDescriptor loadTypeAlias(@NotNull TypeAlias typeAlias) {
        TypeAlias typeAlias2 = typeAlias;
        Intrinsics.checkParameterIsNotNull(typeAlias2, "proto");
        Companion companion = Annotations.Companion;
        List annotationList = typeAlias.getAnnotationList();
        Intrinsics.checkExpressionValueIsNotNull(annotationList, "proto.annotationList");
        Iterable<Annotation> iterable = annotationList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Annotation annotation : iterable) {
            AnnotationDeserializer annotationDeserializer2 = this.annotationDeserializer;
            Intrinsics.checkExpressionValueIsNotNull(annotation, "it");
            arrayList.add(annotationDeserializer2.deserializeAnnotation(annotation, this.c.getNameResolver()));
        }
        DeserializedTypeAliasDescriptor deserializedTypeAliasDescriptor = new DeserializedTypeAliasDescriptor(this.c.getStorageManager(), this.c.getContainingDeclaration(), companion.create((List) arrayList), NameResolverUtilKt.getName(this.c.getNameResolver(), typeAlias.getName()), ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(typeAlias.getFlags())), typeAlias, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource());
        DeserializationContext deserializationContext = this.c;
        DeclarationDescriptor declarationDescriptor = deserializedTypeAliasDescriptor;
        List typeParameterList = typeAlias.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "proto.typeParameterList");
        DeserializationContext childContext$default = DeserializationContext.childContext$default(deserializationContext, declarationDescriptor, typeParameterList, null, null, null, null, 60, null);
        deserializedTypeAliasDescriptor.initialize(childContext$default.getTypeDeserializer().getOwnTypeParameters(), childContext$default.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.underlyingType(typeAlias2, this.c.getTypeTable())), childContext$default.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.expandedType(typeAlias2, this.c.getTypeTable())), checkExperimentalCoroutine(deserializedTypeAliasDescriptor, childContext$default.getTypeDeserializer()));
        return deserializedTypeAliasDescriptor;
    }

    private final ReceiverParameterDescriptor getDispatchReceiverParameter() {
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        if (classDescriptor != null) {
            return classDescriptor.getThisAsReceiverParameter();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00c2, code lost:
        if (versionAndReleaseCoroutinesMismatch(r8) != false) goto L_0x00c6;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor loadConstructor(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor r32, boolean r33) {
        /*
            r31 = this;
            r7 = r31
            r0 = r32
            java.lang.String r1 = "proto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r7.c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r1.getContainingDeclaration()
            if (r1 == 0) goto L_0x00fc
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r1
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor r6 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor
            r10 = 0
            r2 = r0
            kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r2 = (kotlin.reflect.jvm.internal.impl.protobuf.MessageLite) r2
            int r3 = r32.getFlags()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r4 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.FUNCTION
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r11 = r7.getAnnotations(r2, r3, r4)
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind r13 = kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind.DECLARATION
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r7.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r15 = r3.getNameResolver()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r7.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r16 = r3.getTypeTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r7.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r17 = r3.getVersionRequirementTable()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r7.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r18 = r3.getContainerSource()
            r19 = 0
            r20 = 1024(0x400, float:1.435E-42)
            r21 = 0
            r8 = r6
            r9 = r1
            r12 = r33
            r14 = r32
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r7.c
            r23 = r6
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r23 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r23
            java.util.List r24 = kotlin.collections.CollectionsKt.emptyList()
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 60
            r30 = 0
            r22 = r3
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.childContext$default(r22, r23, r24, r25, r26, r27, r28, r29, r30)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r3 = r3.getMemberDeserializer()
            java.util.List r4 = r32.getValueParameterList()
            java.lang.String r5 = "proto.valueParameterList"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r5 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.FUNCTION
            java.util.List r2 = r3.valueParameters(r4, r2, r5)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags r3 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags.INSTANCE
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility> r4 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.VISIBILITY
            int r0 = r32.getFlags()
            java.lang.Object r0 = r4.get(r0)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility r0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility) r0
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r0 = r3.visibility(r0)
            r6.initialize(r2, r0)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = r1.getDefaultType()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
            r6.setReturnType(r0)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r7.c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getContainingDeclaration()
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor
            if (r1 != 0) goto L_0x00a4
            r0 = 0
        L_0x00a4:
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor r0 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor) r0
            r1 = 1
            if (r0 == 0) goto L_0x00c5
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r0.getC()
            if (r0 == 0) goto L_0x00c5
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r0 = r0.getTypeDeserializer()
            if (r0 == 0) goto L_0x00c5
            boolean r0 = r0.getExperimentalSuspendFunctionTypeEncountered()
            if (r0 != r1) goto L_0x00c5
            r0 = r6
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor) r0
            boolean r0 = r7.versionAndReleaseCoroutinesMismatch(r0)
            if (r0 == 0) goto L_0x00c5
            goto L_0x00c6
        L_0x00c5:
            r1 = 0
        L_0x00c6:
            if (r1 == 0) goto L_0x00cc
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor$CoroutinesCompatibilityMode r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE
            r9 = r6
            goto L_0x00f5
        L_0x00cc:
            r1 = r6
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor r1 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor) r1
            r2 = 0
            java.util.List r0 = r6.getValueParameters()
            java.lang.String r3 = "descriptor.valueParameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)
            r3 = r0
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.List r0 = r6.getTypeParameters()
            java.lang.String r4 = "descriptor.typeParameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            kotlin.reflect.jvm.internal.impl.types.KotlinType r5 = r6.getReturnType()
            r8 = 0
            r0 = r31
            r9 = r6
            r6 = r8
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor$CoroutinesCompatibilityMode r0 = r0.computeExperimentalityModeForFunctions(r1, r2, r3, r4, r5, r6)
        L_0x00f5:
            r9.setCoroutinesExperimentalCompatibilityMode$deserialization(r0)
            r6 = r9
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r6 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r6
            return r6
        L_0x00fc:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.loadConstructor(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Constructor, boolean):kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor");
    }

    private final Annotations getAnnotations(MessageLite messageLite, int i, AnnotatedCallableKind annotatedCallableKind) {
        if (!Flags.HAS_ANNOTATIONS.get(i).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getAnnotations$1(this, messageLite, annotatedCallableKind));
    }

    private final Annotations getPropertyFieldAnnotations(Property property, boolean z) {
        if (!Flags.HAS_ANNOTATIONS.get(property.getFlags()).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getPropertyFieldAnnotations$1(this, z, property));
    }

    private final Annotations getReceiverParameterAnnotations(MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        return new DeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getReceiverParameterAnnotations$1(this, messageLite, annotatedCallableKind));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor> valueParameters(java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter> r27, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r28, kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r29) {
        /*
            r26 = this;
            r8 = r26
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r8.c
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getContainingDeclaration()
            if (r0 == 0) goto L_0x0122
            r21 = r0
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r21 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor) r21
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r21.getContainingDeclaration()
            java.lang.String r1 = "callableDescriptor.containingDeclaration"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer r22 = r8.asProtoContainer(r0)
            r0 = r27
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r2)
            r1.<init>(r2)
            r15 = r1
            java.util.Collection r15 = (java.util.Collection) r15
            java.util.Iterator r23 = r0.iterator()
            r24 = 0
            r12 = 0
        L_0x0034:
            boolean r0 = r23.hasNext()
            if (r0 == 0) goto L_0x0117
            java.lang.Object r0 = r23.next()
            int r25 = r12 + 1
            if (r12 >= 0) goto L_0x0045
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x0045:
            r9 = r0
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter r9 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter) r9
            boolean r0 = r9.hasFlags()
            if (r0 == 0) goto L_0x0054
            int r0 = r9.getFlags()
            r10 = r0
            goto L_0x0055
        L_0x0054:
            r10 = 0
        L_0x0055:
            if (r22 == 0) goto L_0x008b
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.HAS_ANNOTATIONS
            java.lang.Boolean r0 = r0.get(r10)
            java.lang.String r1 = "Flags.HAS_ANNOTATIONS.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x008b
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations r11 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r8.c
            kotlin.reflect.jvm.internal.impl.storage.StorageManager r13 = r0.getStorageManager()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1 r14 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1
            r0 = r14
            r1 = r12
            r2 = r9
            r3 = r26
            r4 = r22
            r5 = r28
            r6 = r29
            r7 = r21
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function0 r14 = (kotlin.jvm.functions.Function0) r14
            r11.<init>(r13, r14)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r11 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations) r11
            r13 = r11
            goto L_0x0092
        L_0x008b:
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion r0 = kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r0 = r0.getEMPTY()
            r13 = r0
        L_0x0092:
            r11 = 0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r8.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r0 = r0.getNameResolver()
            int r1 = r9.getName()
            kotlin.reflect.jvm.internal.impl.name.Name r14 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r0, r1)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r8.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r0 = r0.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r8.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r1 = r1.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.type(r9, r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.type(r1)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.DECLARES_DEFAULT_VALUE
            java.lang.Boolean r1 = r1.get(r10)
            java.lang.String r2 = "Flags.DECLARES_DEFAULT_VALUE.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            boolean r16 = r1.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_CROSSINLINE
            java.lang.Boolean r1 = r1.get(r10)
            java.lang.String r2 = "Flags.IS_CROSSINLINE.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            boolean r17 = r1.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_NOINLINE
            java.lang.Boolean r1 = r1.get(r10)
            java.lang.String r2 = "Flags.IS_NOINLINE.get(flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            boolean r18 = r1.booleanValue()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r1 = r8.c
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r1 = r1.getTypeTable()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r1 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.varargElementType(r9, r1)
            if (r1 == 0) goto L_0x00f9
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r8.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r2 = r2.getTypeDeserializer()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r2.type(r1)
            goto L_0x00fa
        L_0x00f9:
            r1 = 0
        L_0x00fa:
            r19 = r1
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r1 = kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.NO_SOURCE
            java.lang.String r2 = "SourceElement.NO_SOURCE"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl r2 = new kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl
            r9 = r2
            r10 = r21
            r3 = r15
            r15 = r0
            r20 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r3.add(r2)
            r15 = r3
            r12 = r25
            goto L_0x0034
        L_0x0117:
            r3 = r15
            r15 = r3
            java.util.List r15 = (java.util.List) r15
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.List r0 = kotlin.collections.CollectionsKt.toList(r15)
            return r0
        L_0x0122:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.valueParameters(java.util.List, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite, kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind):java.util.List");
    }

    /* access modifiers changed from: private */
    public final ProtoContainer asProtoContainer(@NotNull DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor instanceof PackageFragmentDescriptor) {
            return new Package(((PackageFragmentDescriptor) declarationDescriptor).getFqName(), this.c.getNameResolver(), this.c.getTypeTable(), this.c.getContainerSource());
        }
        if (declarationDescriptor instanceof DeserializedClassDescriptor) {
            return ((DeserializedClassDescriptor) declarationDescriptor).getThisAsProtoContainer$deserialization();
        }
        return null;
    }
}
