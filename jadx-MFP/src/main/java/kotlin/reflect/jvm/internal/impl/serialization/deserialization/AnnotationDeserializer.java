package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Type;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnnotationDeserializer.kt */
public final class AnnotationDeserializer {
    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    public AnnotationDeserializer(@NotNull ModuleDescriptor moduleDescriptor, @NotNull NotFoundClasses notFoundClasses2) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses2, "notFoundClasses");
        this.module = moduleDescriptor;
        this.notFoundClasses = notFoundClasses2;
    }

    private final KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    @NotNull
    public final AnnotationDescriptor deserializeAnnotation(@NotNull Annotation annotation, @NotNull NameResolver nameResolver) {
        Intrinsics.checkParameterIsNotNull(annotation, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        ClassDescriptor resolveClass = resolveClass(NameResolverUtilKt.getClassId(nameResolver, annotation.getId()));
        Map emptyMap = MapsKt.emptyMap();
        if (annotation.getArgumentCount() != 0) {
            DeclarationDescriptor declarationDescriptor = resolveClass;
            if (!ErrorUtils.isError(declarationDescriptor) && DescriptorUtils.isAnnotationClass(declarationDescriptor)) {
                Collection constructors = resolveClass.getConstructors();
                Intrinsics.checkExpressionValueIsNotNull(constructors, "annotationClass.constructors");
                ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) CollectionsKt.singleOrNull((Iterable<? extends T>) constructors);
                if (classConstructorDescriptor != null) {
                    List valueParameters = classConstructorDescriptor.getValueParameters();
                    Intrinsics.checkExpressionValueIsNotNull(valueParameters, "constructor.valueParameters");
                    Iterable iterable = valueParameters;
                    Map linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
                    for (Object next : iterable) {
                        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) next;
                        Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "it");
                        linkedHashMap.put(valueParameterDescriptor.getName(), next);
                    }
                    List argumentList = annotation.getArgumentList();
                    Intrinsics.checkExpressionValueIsNotNull(argumentList, "proto.argumentList");
                    Iterable<Argument> iterable2 = argumentList;
                    Collection arrayList = new ArrayList();
                    for (Argument argument : iterable2) {
                        Intrinsics.checkExpressionValueIsNotNull(argument, "it");
                        Pair resolveArgument = resolveArgument(argument, linkedHashMap, nameResolver);
                        if (resolveArgument != null) {
                            arrayList.add(resolveArgument);
                        }
                    }
                    emptyMap = MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) (List) arrayList);
                }
            }
        }
        return new AnnotationDescriptorImpl(resolveClass.getDefaultType(), emptyMap, SourceElement.NO_SOURCE);
    }

    private final Pair<Name, ConstantValue<?>> resolveArgument(Argument argument, Map<Name, ? extends ValueParameterDescriptor> map, NameResolver nameResolver) {
        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) map.get(NameResolverUtilKt.getName(nameResolver, argument.getNameId()));
        if (valueParameterDescriptor == null) {
            return null;
        }
        Name name = NameResolverUtilKt.getName(nameResolver, argument.getNameId());
        KotlinType type = valueParameterDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "parameter.type");
        Value value = argument.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "proto.value");
        return new Pair<>(name, resolveValue(type, value, nameResolver));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01e4, code lost:
        if (kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isSubtypeOf(r11.getType(r8.module), r9) == false) goto L_0x01e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01e7, code lost:
        r0 = kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue.Companion;
        r1 = new java.lang.StringBuilder();
        r1.append("Unexpected argument value: type ");
        r1.append(r11.getType(r8.module));
        r1.append(" is not a subtype of ");
        r1.append(r9);
        r1.append(" (value.type = ");
        r1.append(r10.getType());
        r1.append(')');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return r0.create(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        return r11;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue<?> resolveValue(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r9, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value r10, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r11) {
        /*
            r8 = this;
            java.lang.String r0 = "expectedType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$BooleanFlagField r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.IS_UNSIGNED
            int r1 = r10.getFlags()
            java.lang.Boolean r0 = r0.get(r1)
            java.lang.String r1 = "Flags.IS_UNSIGNED.get(value.flags)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            boolean r0 = r0.booleanValue()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value$Type r1 = r10.getType()
            r2 = 41
            if (r1 == 0) goto L_0x021f
            int[] r3 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationDeserializer.WhenMappings.$EnumSwitchMapping$0
            int r1 = r1.ordinal()
            r1 = r3[r1]
            r3 = 0
            r4 = 1
            switch(r1) {
                case 1: goto L_0x01c4;
                case 2: goto L_0x01b6;
                case 3: goto L_0x019f;
                case 4: goto L_0x0189;
                case 5: goto L_0x0175;
                case 6: goto L_0x0169;
                case 7: goto L_0x015c;
                case 8: goto L_0x0148;
                case 9: goto L_0x0136;
                case 10: goto L_0x0124;
                case 11: goto L_0x010a;
                case 12: goto L_0x00f3;
                case 13: goto L_0x0039;
                default: goto L_0x0037;
            }
        L_0x0037:
            goto L_0x021f
        L_0x0039:
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isArray(r9)
            if (r0 != 0) goto L_0x0045
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isPrimitiveArray(r9)
            if (r0 == 0) goto L_0x0046
        L_0x0045:
            r3 = 1
        L_0x0046:
            java.util.List r0 = r10.getArrayElementList()
            java.lang.String r1 = "arrayElements"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r4
            if (r1 == 0) goto L_0x008a
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first(r0)
            java.lang.String r4 = "arrayElements.first()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value r1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value) r1
            kotlin.reflect.jvm.internal.impl.types.SimpleType r1 = r8.resolveArrayElementType(r1, r11)
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r4 = r8.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
            kotlin.reflect.jvm.internal.impl.types.SimpleType r4 = r4.getPrimitiveArrayKotlinTypeByPrimitiveKotlinType(r1)
            if (r4 == 0) goto L_0x0078
            r1 = r4
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
            goto L_0x00a9
        L_0x0078:
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r4 = r8.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.Variance r5 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
            kotlin.reflect.jvm.internal.impl.types.SimpleType r1 = r4.getArrayType(r5, r1)
            java.lang.String r4 = "builtIns.getArrayType(Va…RIANT, actualElementType)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
            goto L_0x00a9
        L_0x008a:
            if (r3 == 0) goto L_0x008e
            r1 = r9
            goto L_0x00a9
        L_0x008e:
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r1 = r8.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.Variance r4 = kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r5 = r8.getBuiltIns()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r5 = r5.getAnyType()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r5 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r5
            kotlin.reflect.jvm.internal.impl.types.SimpleType r1 = r1.getArrayType(r4, r5)
            java.lang.String r4 = "builtIns.getArrayType(Va…ARIANT, builtIns.anyType)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
        L_0x00a9:
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r4 = r8.getBuiltIns()
            if (r3 == 0) goto L_0x00b1
            r3 = r9
            goto L_0x00b2
        L_0x00b1:
            r3 = r1
        L_0x00b2:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = r4.getArrayElementType(r3)
            java.lang.String r4 = "builtIns.getArrayElement…ype else actualArrayType)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory r4 = kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory.INSTANCE
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r6)
            r5.<init>(r6)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r0 = r0.iterator()
        L_0x00d0:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x00e9
            java.lang.Object r6 = r0.next()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value r6 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value) r6
            java.lang.String r7 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r6 = r8.resolveValue(r3, r6, r11)
            r5.add(r6)
            goto L_0x00d0
        L_0x00e9:
            java.util.List r5 = (java.util.List) r5
            kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue r11 = r4.createArrayValue(r5, r1)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x00f3:
            kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation r1 = r10.getAnnotation()
            java.lang.String r3 = "value.annotation"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r11 = r8.deserializeAnnotation(r1, r11)
            r0.<init>(r11)
            r11 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x010a:
            kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue
            int r1 = r10.getClassId()
            kotlin.reflect.jvm.internal.impl.name.ClassId r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getClassId(r11, r1)
            int r3 = r10.getEnumValueId()
            kotlin.reflect.jvm.internal.impl.name.Name r11 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r11, r3)
            r0.<init>(r1, r11)
            r11 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x0124:
            int r0 = r10.getClassId()
            kotlin.reflect.jvm.internal.impl.name.ClassId r11 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getClassId(r11, r0)
            int r0 = r10.getArrayDimensionCount()
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = r8.resolveClassLiteralValue(r11, r0)
            goto L_0x01da
        L_0x0136:
            kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue
            int r1 = r10.getStringValue()
            java.lang.String r11 = r11.getString(r1)
            r0.<init>(r11)
            r11 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x0148:
            kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue r11 = new kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue
            long r0 = r10.getIntValue()
            r5 = 0
            int r7 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0155
            r3 = 1
        L_0x0155:
            r11.<init>(r3)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x015c:
            kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue r11 = new kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue
            double r0 = r10.getDoubleValue()
            r11.<init>(r0)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x0169:
            kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue r11 = new kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue
            float r0 = r10.getFloatValue()
            r11.<init>(r0)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x0175:
            long r3 = r10.getIntValue()
            if (r0 == 0) goto L_0x0181
            kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue r11 = new kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue
            r11.<init>(r3)
            goto L_0x0186
        L_0x0181:
            kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue r11 = new kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue
            r11.<init>(r3)
        L_0x0186:
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x0189:
            long r3 = r10.getIntValue()
            int r11 = (int) r3
            if (r0 == 0) goto L_0x0196
            kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue
            r0.<init>(r11)
            goto L_0x019b
        L_0x0196:
            kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue
            r0.<init>(r11)
        L_0x019b:
            r11 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x019f:
            long r3 = r10.getIntValue()
            int r11 = (int) r3
            short r11 = (short) r11
            if (r0 == 0) goto L_0x01ad
            kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue
            r0.<init>(r11)
            goto L_0x01b2
        L_0x01ad:
            kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue
            r0.<init>(r11)
        L_0x01b2:
            r11 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x01b6:
            kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue r11 = new kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue
            long r0 = r10.getIntValue()
            int r1 = (int) r0
            char r0 = (char) r1
            r11.<init>(r0)
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
            goto L_0x01da
        L_0x01c4:
            long r3 = r10.getIntValue()
            int r11 = (int) r3
            byte r11 = (byte) r11
            if (r0 == 0) goto L_0x01d2
            kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue
            r0.<init>(r11)
            goto L_0x01d7
        L_0x01d2:
            kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue r0 = new kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue
            r0.<init>(r11)
        L_0x01d7:
            r11 = r0
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
        L_0x01da:
            kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r0 = r8.module
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r11.getType(r0)
            boolean r0 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isSubtypeOf(r0, r9)
            if (r0 == 0) goto L_0x01e7
            goto L_0x021e
        L_0x01e7:
            kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue$Companion r0 = kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue.Companion
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Unexpected argument value: type "
            r1.append(r3)
            kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r3 = r8.module
            kotlin.reflect.jvm.internal.impl.types.KotlinType r11 = r11.getType(r3)
            r1.append(r11)
            java.lang.String r11 = " is not a subtype of "
            r1.append(r11)
            r1.append(r9)
            java.lang.String r9 = " (value.type = "
            r1.append(r9)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value$Type r9 = r10.getType()
            r1.append(r9)
            r1.append(r2)
            java.lang.String r9 = r1.toString()
            kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue r9 = r0.create(r9)
            r11 = r9
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r11 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r11
        L_0x021e:
            return r11
        L_0x021f:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "Unsupported annotation argument type: "
            r11.append(r0)
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value$Type r10 = r10.getType()
            r11.append(r10)
            java.lang.String r10 = " (expected "
            r11.append(r10)
            r11.append(r9)
            r11.append(r2)
            java.lang.String r9 = r11.toString()
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r9 = r9.toString()
            r10.<init>(r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationDeserializer.resolveValue(kotlin.reflect.jvm.internal.impl.types.KotlinType, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver):kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue");
    }

    private final ConstantValue<?> resolveClassLiteralValue(ClassId classId, int i) {
        SimpleType defaultType = resolveClass(classId).getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "resolveClass(classId).defaultType");
        KotlinType replaceArgumentsWithStarProjections = TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
        for (int i2 = 0; i2 < i; i2++) {
            SimpleType arrayType = getBuiltIns().getArrayType(Variance.INVARIANT, replaceArgumentsWithStarProjections);
            Intrinsics.checkExpressionValueIsNotNull(arrayType, "builtIns.getArrayType(Variance.INVARIANT, type)");
            replaceArgumentsWithStarProjections = arrayType;
        }
        ClassId classId2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.kClass.toSafe());
        Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(KotlinB…FQ_NAMES.kClass.toSafe())");
        return new KClassValue<>(KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), resolveClass(classId2), CollectionsKt.listOf(new TypeProjectionImpl(replaceArgumentsWithStarProjections))));
    }

    private final SimpleType resolveArrayElementType(Value value, NameResolver nameResolver) {
        KotlinBuiltIns builtIns = getBuiltIns();
        Type type = value.getType();
        if (type != null) {
            switch (type) {
                case BYTE:
                    SimpleType byteType = builtIns.getByteType();
                    Intrinsics.checkExpressionValueIsNotNull(byteType, "byteType");
                    return byteType;
                case CHAR:
                    SimpleType charType = builtIns.getCharType();
                    Intrinsics.checkExpressionValueIsNotNull(charType, "charType");
                    return charType;
                case SHORT:
                    SimpleType shortType = builtIns.getShortType();
                    Intrinsics.checkExpressionValueIsNotNull(shortType, "shortType");
                    return shortType;
                case INT:
                    SimpleType intType = builtIns.getIntType();
                    Intrinsics.checkExpressionValueIsNotNull(intType, "intType");
                    return intType;
                case LONG:
                    SimpleType longType = builtIns.getLongType();
                    Intrinsics.checkExpressionValueIsNotNull(longType, "longType");
                    return longType;
                case FLOAT:
                    SimpleType floatType = builtIns.getFloatType();
                    Intrinsics.checkExpressionValueIsNotNull(floatType, "floatType");
                    return floatType;
                case DOUBLE:
                    SimpleType doubleType = builtIns.getDoubleType();
                    Intrinsics.checkExpressionValueIsNotNull(doubleType, "doubleType");
                    return doubleType;
                case BOOLEAN:
                    SimpleType booleanType = builtIns.getBooleanType();
                    Intrinsics.checkExpressionValueIsNotNull(booleanType, "booleanType");
                    return booleanType;
                case STRING:
                    SimpleType stringType = builtIns.getStringType();
                    Intrinsics.checkExpressionValueIsNotNull(stringType, "stringType");
                    return stringType;
                case CLASS:
                    throw new IllegalStateException("Arrays of class literals are not supported yet".toString());
                case ENUM:
                    SimpleType defaultType = resolveClass(NameResolverUtilKt.getClassId(nameResolver, value.getClassId())).getDefaultType();
                    Intrinsics.checkExpressionValueIsNotNull(defaultType, "resolveClass(nameResolve…lue.classId)).defaultType");
                    return defaultType;
                case ANNOTATION:
                    Annotation annotation = value.getAnnotation();
                    Intrinsics.checkExpressionValueIsNotNull(annotation, "value.annotation");
                    SimpleType defaultType2 = resolveClass(NameResolverUtilKt.getClassId(nameResolver, annotation.getId())).getDefaultType();
                    Intrinsics.checkExpressionValueIsNotNull(defaultType2, "resolveClass(nameResolve…notation.id)).defaultType");
                    return defaultType2;
                case ARRAY:
                    throw new IllegalStateException("Array of arrays is impossible".toString());
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown type: ");
        sb.append(value.getType());
        throw new IllegalStateException(sb.toString().toString());
    }

    private final ClassDescriptor resolveClass(ClassId classId) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId, this.notFoundClasses);
    }
}
