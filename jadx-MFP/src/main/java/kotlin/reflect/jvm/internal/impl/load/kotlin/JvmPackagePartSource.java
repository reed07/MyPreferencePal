package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JvmPackagePartSource.kt */
public final class JvmPackagePartSource implements DeserializedContainerSource {
    @NotNull
    private final JvmClassName className;
    @Nullable
    private final JvmClassName facadeClassName;
    @Nullable
    private final IncompatibleVersionErrorData<JvmMetadataVersion> incompatibility;
    private final boolean isPreReleaseInvisible;
    @Nullable
    private final KotlinJvmBinaryClass knownJvmBinaryClass;
    @NotNull
    private final String moduleName;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0037, code lost:
        if (r2 != null) goto L_0x003c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JvmPackagePartSource(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r2, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r3, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package r4, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r5, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData<kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion> r6, boolean r7, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass r8) {
        /*
            r1 = this;
            java.lang.String r0 = "className"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = "packageProto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            r1.<init>()
            r1.className = r2
            r1.facadeClassName = r3
            r1.incompatibility = r6
            r1.isPreReleaseInvisible = r7
            r1.knownJvmBinaryClass = r8
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableMessage r4 = (kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage) r4
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, java.lang.Integer> r2 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.packageModuleName
            java.lang.String r3 = "JvmProtoBuf.packageModuleName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.Object r2 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt.getExtensionOrNull(r4, r2)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L_0x003a
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.String r2 = r5.getString(r2)
            if (r2 == 0) goto L_0x003a
            goto L_0x003c
        L_0x003a:
            java.lang.String r2 = "main"
        L_0x003c:
            r1.moduleName = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource.<init>(kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName, kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver, kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData, boolean, kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass):void");
    }

    @NotNull
    public final JvmClassName getClassName() {
        return this.className;
    }

    @Nullable
    public final JvmClassName getFacadeClassName() {
        return this.facadeClassName;
    }

    @Nullable
    public final KotlinJvmBinaryClass getKnownJvmBinaryClass() {
        return this.knownJvmBinaryClass;
    }

    public JvmPackagePartSource(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass, @NotNull Package packageR, @NotNull NameResolver nameResolver, @Nullable IncompatibleVersionErrorData<JvmMetadataVersion> incompatibleVersionErrorData, boolean z) {
        Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinClass");
        Intrinsics.checkParameterIsNotNull(packageR, "packageProto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        JvmClassName byClassId = JvmClassName.byClassId(kotlinJvmBinaryClass.getClassId());
        Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(kotlinClass.classId)");
        String multifileClassName = kotlinJvmBinaryClass.getClassHeader().getMultifileClassName();
        JvmClassName jvmClassName = null;
        if (multifileClassName != null) {
            if (multifileClassName.length() > 0) {
                jvmClassName = JvmClassName.byInternalName(multifileClassName);
            }
        }
        JvmClassName jvmClassName2 = jvmClassName;
        this(byClassId, jvmClassName2, packageR, nameResolver, incompatibleVersionErrorData, z, kotlinJvmBinaryClass);
    }

    @NotNull
    public final Name getSimpleName() {
        String internalName = this.className.getInternalName();
        Intrinsics.checkExpressionValueIsNotNull(internalName, "className.internalName");
        Name identifier = Name.identifier(StringsKt.substringAfterLast$default(internalName, '/', (String) null, 2, (Object) null));
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(classNam….substringAfterLast('/'))");
        return identifier;
    }

    @NotNull
    public final ClassId getClassId() {
        return new ClassId(this.className.getPackageFqName(), getSimpleName());
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(": ");
        sb.append(this.className);
        return sb.toString();
    }

    @NotNull
    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
        return sourceFile;
    }
}
