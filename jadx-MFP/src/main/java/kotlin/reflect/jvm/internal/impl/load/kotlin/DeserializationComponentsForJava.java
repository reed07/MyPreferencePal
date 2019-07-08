package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializationComponentsForJava.kt */
public final class DeserializationComponentsForJava {
    @NotNull
    private final DeserializationComponents components;

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0082, code lost:
        if (r3 != null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0073, code lost:
        if (r4 != null) goto L_0x0078;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeserializationComponentsForJava(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.storage.StorageManager r22, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r23, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration r24, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.load.kotlin.JavaClassDataFinder r25, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl r26, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider r27, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses r28, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter r29, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker r30, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer r31) {
        /*
            r21 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            java.lang.String r3 = "storageManager"
            r5 = r22
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r3)
            java.lang.String r3 = "moduleDescriptor"
            r6 = r23
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r3)
            java.lang.String r3 = "configuration"
            r7 = r24
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r3)
            java.lang.String r3 = "classDataFinder"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r3)
            java.lang.String r3 = "annotationAndConstantLoader"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r3)
            java.lang.String r3 = "packageFragmentProvider"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r3)
            java.lang.String r3 = "notFoundClasses"
            r15 = r28
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r3)
            java.lang.String r3 = "errorReporter"
            r12 = r29
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r3)
            java.lang.String r3 = "lookupTracker"
            r13 = r30
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r3)
            java.lang.String r3 = "contractDeserializer"
            r14 = r31
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r3)
            r21.<init>()
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r3 = r23.getBuiltIns()
            boolean r4 = r3 instanceof kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns
            if (r4 != 0) goto L_0x0052
            r3 = 0
        L_0x0052:
            kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns r3 = (kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns) r3
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r11 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents
            r8 = r0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder r8 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder) r8
            r9 = r1
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader r9 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader) r9
            r10 = r2
            kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider r10 = (kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider) r10
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings$Default r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings.Default.INSTANCE
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings r0 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings) r0
            kotlin.reflect.jvm.internal.impl.load.kotlin.JavaFlexibleTypeDeserializer r1 = kotlin.reflect.jvm.internal.impl.load.kotlin.JavaFlexibleTypeDeserializer.INSTANCE
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer r1 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer) r1
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            if (r3 == 0) goto L_0x0076
            kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings r4 = r3.getSettings()
            if (r4 == 0) goto L_0x0076
            goto L_0x0078
        L_0x0076:
            kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider$None r4 = kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider.None.INSTANCE
        L_0x0078:
            kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider r4 = (kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider) r4
            r18 = r4
            if (r3 == 0) goto L_0x0085
            kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings r3 = r3.getSettings()
            if (r3 == 0) goto L_0x0085
            goto L_0x0087
        L_0x0085:
            kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter$NoPlatformDependent r3 = kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.NoPlatformDependent.INSTANCE
        L_0x0087:
            kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter r3 = (kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter) r3
            r19 = r3
            kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil r3 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil.INSTANCE
            kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r20 = r3.getEXTENSION_REGISTRY()
            r4 = r11
            r5 = r22
            r6 = r23
            r7 = r24
            r3 = r11
            r11 = r0
            r12 = r29
            r13 = r30
            r14 = r1
            r15 = r2
            r16 = r28
            r17 = r31
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r0 = r21
            r0.components = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava.<init>(kotlin.reflect.jvm.internal.impl.storage.StorageManager, kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor, kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration, kotlin.reflect.jvm.internal.impl.load.kotlin.JavaClassDataFinder, kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider, kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses, kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter, kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker, kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer):void");
    }

    @NotNull
    public final DeserializationComponents getComponents() {
        return this.components;
    }
}
