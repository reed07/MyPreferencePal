package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.JvmFunctionSignature.KotlinFunction;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0004\u0005\u0006\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u0001\u0004\t\n\u000b\f¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "", "()V", "asString", "", "JavaField", "JavaMethodProperty", "KotlinProperty", "MappedKotlinProperty", "Lkotlin/reflect/jvm/internal/JvmPropertySignature$KotlinProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature$JavaMethodProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature$JavaField;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature$MappedKotlinProperty;", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
/* compiled from: RuntimeTypeMapper.kt */
public abstract class JvmPropertySignature {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature$JavaField;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "getField", "()Ljava/lang/reflect/Field;", "asString", "", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
    /* compiled from: RuntimeTypeMapper.kt */
    public static final class JavaField extends JvmPropertySignature {
        @NotNull
        private final Field field;

        public JavaField(@NotNull Field field2) {
            Intrinsics.checkParameterIsNotNull(field2, "field");
            super(null);
            this.field = field2;
        }

        @NotNull
        public final Field getField() {
            return this.field;
        }

        @NotNull
        public String asString() {
            StringBuilder sb = new StringBuilder();
            sb.append(JvmAbi.getterName(this.field.getName()));
            sb.append("()");
            Class type = this.field.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "field.type");
            sb.append(ReflectClassUtilKt.getDesc(type));
            return sb.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000b"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature$JavaMethodProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "getterMethod", "Ljava/lang/reflect/Method;", "setterMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getGetterMethod", "()Ljava/lang/reflect/Method;", "getSetterMethod", "asString", "", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
    /* compiled from: RuntimeTypeMapper.kt */
    public static final class JavaMethodProperty extends JvmPropertySignature {
        @NotNull
        private final Method getterMethod;
        @Nullable
        private final Method setterMethod;

        public JavaMethodProperty(@NotNull Method method, @Nullable Method method2) {
            Intrinsics.checkParameterIsNotNull(method, "getterMethod");
            super(null);
            this.getterMethod = method;
            this.setterMethod = method2;
        }

        @NotNull
        public final Method getGetterMethod() {
            return this.getterMethod;
        }

        @Nullable
        public final Method getSetterMethod() {
            return this.setterMethod;
        }

        @NotNull
        public String asString() {
            return RuntimeTypeMapperKt.getSignature(this.getterMethod);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0019\u001a\u00020\u0016H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature$KotlinProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "proto", "Lkotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Property;", "signature", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/JvmProtoBuf$JvmPropertySignature;", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;Lorg/jetbrains/kotlin/metadata/jvm/JvmProtoBuf$JvmPropertySignature;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getNameResolver", "()Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "getProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "getSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/JvmProtoBuf$JvmPropertySignature;", "string", "", "getTypeTable", "()Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "asString", "getManglingSuffix", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
    /* compiled from: RuntimeTypeMapper.kt */
    public static final class KotlinProperty extends JvmPropertySignature {
        @NotNull
        private final PropertyDescriptor descriptor;
        @NotNull
        private final NameResolver nameResolver;
        @NotNull
        private final Property proto;
        @NotNull
        private final kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature signature;
        private final String string;
        @NotNull
        private final TypeTable typeTable;

        @NotNull
        public final PropertyDescriptor getDescriptor() {
            return this.descriptor;
        }

        @NotNull
        public final Property getProto() {
            return this.proto;
        }

        @NotNull
        public final kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature getSignature() {
            return this.signature;
        }

        @NotNull
        public final NameResolver getNameResolver() {
            return this.nameResolver;
        }

        @NotNull
        public final TypeTable getTypeTable() {
            return this.typeTable;
        }

        public KotlinProperty(@NotNull PropertyDescriptor propertyDescriptor, @NotNull Property property, @NotNull kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature jvmPropertySignature, @NotNull NameResolver nameResolver2, @NotNull TypeTable typeTable2) {
            String str;
            Intrinsics.checkParameterIsNotNull(propertyDescriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(property, "proto");
            Intrinsics.checkParameterIsNotNull(jvmPropertySignature, "signature");
            Intrinsics.checkParameterIsNotNull(nameResolver2, "nameResolver");
            Intrinsics.checkParameterIsNotNull(typeTable2, "typeTable");
            super(null);
            this.descriptor = propertyDescriptor;
            this.proto = property;
            this.signature = jvmPropertySignature;
            this.nameResolver = nameResolver2;
            this.typeTable = typeTable2;
            if (this.signature.hasGetter()) {
                StringBuilder sb = new StringBuilder();
                NameResolver nameResolver3 = this.nameResolver;
                JvmMethodSignature getter = this.signature.getGetter();
                Intrinsics.checkExpressionValueIsNotNull(getter, "signature.getter");
                sb.append(nameResolver3.getString(getter.getName()));
                NameResolver nameResolver4 = this.nameResolver;
                JvmMethodSignature getter2 = this.signature.getGetter();
                Intrinsics.checkExpressionValueIsNotNull(getter2, "signature.getter");
                sb.append(nameResolver4.getString(getter2.getDesc()));
                str = sb.toString();
            } else {
                JvmMemberSignature.Field jvmFieldSignature$default = JvmProtoBufUtil.getJvmFieldSignature$default(JvmProtoBufUtil.INSTANCE, this.proto, this.nameResolver, this.typeTable, false, 8, null);
                if (jvmFieldSignature$default != null) {
                    String component1 = jvmFieldSignature$default.component1();
                    String component2 = jvmFieldSignature$default.component2();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(JvmAbi.getterName(component1));
                    sb2.append(getManglingSuffix());
                    sb2.append("()");
                    sb2.append(component2);
                    str = sb2.toString();
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("No field signature for property: ");
                    sb3.append(this.descriptor);
                    throw new KotlinReflectionInternalError(sb3.toString());
                }
            }
            this.string = str;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0040, code lost:
            if (r0 != null) goto L_0x0045;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final java.lang.String getManglingSuffix() {
            /*
                r3 = this;
                kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r0 = r3.descriptor
                kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getContainingDeclaration()
                java.lang.String r1 = "descriptor.containingDeclaration"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r1 = r3.descriptor
                kotlin.reflect.jvm.internal.impl.descriptors.Visibility r1 = r1.getVisibility()
                kotlin.reflect.jvm.internal.impl.descriptors.Visibility r2 = kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.INTERNAL
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
                if (r1 == 0) goto L_0x005b
                boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor
                if (r1 == 0) goto L_0x005b
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor r0 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor) r0
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r0 = r0.getClassProto()
                kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableMessage r0 = (kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage) r0
                kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class, java.lang.Integer> r1 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.classModuleName
                java.lang.String r2 = "JvmProtoBuf.classModuleName"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                java.lang.Object r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt.getExtensionOrNull(r0, r1)
                java.lang.Integer r0 = (java.lang.Integer) r0
                if (r0 == 0) goto L_0x0043
                kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r1 = r3.nameResolver
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                java.lang.String r0 = r1.getString(r0)
                if (r0 == 0) goto L_0x0043
                goto L_0x0045
            L_0x0043:
                java.lang.String r0 = "main"
            L_0x0045:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "$"
                r1.append(r2)
                java.lang.String r0 = kotlin.reflect.jvm.internal.impl.name.NameUtils.sanitizeAsJavaIdentifier(r0)
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                return r0
            L_0x005b:
                kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r1 = r3.descriptor
                kotlin.reflect.jvm.internal.impl.descriptors.Visibility r1 = r1.getVisibility()
                kotlin.reflect.jvm.internal.impl.descriptors.Visibility r2 = kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.PRIVATE
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
                if (r1 == 0) goto L_0x00a5
                boolean r0 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
                if (r0 == 0) goto L_0x00a5
                kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r0 = r3.descriptor
                if (r0 == 0) goto L_0x009d
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor r0 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor) r0
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r0 = r0.getContainerSource()
                boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource
                if (r1 == 0) goto L_0x00a5
                kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource r0 = (kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource) r0
                kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r1 = r0.getFacadeClassName()
                if (r1 == 0) goto L_0x00a5
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "$"
                r1.append(r2)
                kotlin.reflect.jvm.internal.impl.name.Name r0 = r0.getSimpleName()
                java.lang.String r0 = r0.asString()
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                return r0
            L_0x009d:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException
                java.lang.String r1 = "null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedPropertyDescriptor"
                r0.<init>(r1)
                throw r0
            L_0x00a5:
                java.lang.String r0 = ""
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.JvmPropertySignature.KotlinProperty.getManglingSuffix():java.lang.String");
        }

        @NotNull
        public String asString() {
            return this.string;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000b"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature$MappedKotlinProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "getterSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "setterSignature", "(Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;)V", "getGetterSignature", "()Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "getSetterSignature", "asString", "", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
    /* compiled from: RuntimeTypeMapper.kt */
    public static final class MappedKotlinProperty extends JvmPropertySignature {
        @NotNull
        private final KotlinFunction getterSignature;
        @Nullable
        private final KotlinFunction setterSignature;

        @NotNull
        public final KotlinFunction getGetterSignature() {
            return this.getterSignature;
        }

        @Nullable
        public final KotlinFunction getSetterSignature() {
            return this.setterSignature;
        }

        public MappedKotlinProperty(@NotNull KotlinFunction kotlinFunction, @Nullable KotlinFunction kotlinFunction2) {
            Intrinsics.checkParameterIsNotNull(kotlinFunction, "getterSignature");
            super(null);
            this.getterSignature = kotlinFunction;
            this.setterSignature = kotlinFunction2;
        }

        @NotNull
        public String asString() {
            return this.getterSignature.asString();
        }
    }

    @NotNull
    public abstract String asString();

    private JvmPropertySignature() {
    }

    public /* synthetic */ JvmPropertySignature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
