package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolverImpl;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionSpecificBehaviorKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts;
import kotlin.reflect.jvm.internal.impl.protobuf.ProtocolStringList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ModuleMapping.kt */
public final class ModuleMapping {
    @NotNull
    @JvmField
    public static final ModuleMapping CORRUPTED = new ModuleMapping(MapsKt.emptyMap(), new BinaryModuleData(CollectionsKt.emptyList()), "CORRUPTED");
    public static final Companion Companion = new Companion(null);
    @NotNull
    @JvmField
    public static final ModuleMapping EMPTY = new ModuleMapping(MapsKt.emptyMap(), new BinaryModuleData(CollectionsKt.emptyList()), "EMPTY");
    private final String debugName;
    @NotNull
    private final BinaryModuleData moduleData;
    @NotNull
    private final Map<String, PackageParts> packageFqName2Parts;

    /* compiled from: ModuleMapping.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ModuleMapping loadModuleMapping(@Nullable byte[] bArr, @NotNull String str, boolean z, boolean z2, @NotNull Function1<? super JvmMetadataVersion, Unit> function1) {
            String str2;
            byte[] bArr2 = bArr;
            String str3 = str;
            Function1<? super JvmMetadataVersion, Unit> function12 = function1;
            Intrinsics.checkParameterIsNotNull(str3, "debugName");
            Intrinsics.checkParameterIsNotNull(function12, "reportIncompatibleVersionError");
            if (bArr2 == null) {
                return ModuleMapping.EMPTY;
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr2));
            try {
                int[] iArr = new int[dataInputStream.readInt()];
                int length = iArr.length;
                for (int i = 0; i < length; i++) {
                    iArr[i] = dataInputStream.readInt();
                }
                JvmMetadataVersion jvmMetadataVersion = new JvmMetadataVersion(Arrays.copyOf(iArr, iArr.length));
                if (z || jvmMetadataVersion.isCompatible()) {
                    JvmMetadataVersion jvmMetadataVersion2 = new JvmMetadataVersion(iArr, ((VersionSpecificBehaviorKt.isKotlin1Dot4OrLater(jvmMetadataVersion) ? dataInputStream.readInt() : 0) & 1) != 0);
                    if (z || jvmMetadataVersion2.isCompatible()) {
                        Module parseFrom = Module.parseFrom(dataInputStream);
                        if (parseFrom == null) {
                            return ModuleMapping.EMPTY;
                        }
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        for (PackageParts packageParts : parseFrom.getPackagePartsList()) {
                            Intrinsics.checkExpressionValueIsNotNull(packageParts, "proto");
                            String packageFqName = packageParts.getPackageFqName();
                            Map map = linkedHashMap;
                            Intrinsics.checkExpressionValueIsNotNull(packageFqName, "packageFqName");
                            Object obj = map.get(packageFqName);
                            if (obj == null) {
                                obj = new PackageParts(packageFqName);
                                map.put(packageFqName, obj);
                            }
                            PackageParts packageParts2 = (PackageParts) obj;
                            ProtocolStringList<String> shortClassNameList = packageParts.getShortClassNameList();
                            Intrinsics.checkExpressionValueIsNotNull(shortClassNameList, "proto.shortClassNameList");
                            int i2 = 0;
                            for (String str4 : shortClassNameList) {
                                List multifileFacadeShortNameIdList = packageParts.getMultifileFacadeShortNameIdList();
                                Intrinsics.checkExpressionValueIsNotNull(multifileFacadeShortNameIdList, "proto.multifileFacadeShortNameIdList");
                                Integer num = (Integer) CollectionsKt.getOrNull(multifileFacadeShortNameIdList, i2);
                                Number valueOf = num != null ? Integer.valueOf(num.intValue() - 1) : null;
                                if (valueOf != null) {
                                    ProtocolStringList multifileFacadeShortNameList = packageParts.getMultifileFacadeShortNameList();
                                    Intrinsics.checkExpressionValueIsNotNull(multifileFacadeShortNameList, "proto.multifileFacadeShortNameList");
                                    str2 = (String) CollectionsKt.getOrNull(multifileFacadeShortNameList, valueOf.intValue());
                                } else {
                                    str2 = null;
                                }
                                String access$internalNameOf = str2 != null ? ModuleMappingKt.internalNameOf(packageFqName, str2) : null;
                                Intrinsics.checkExpressionValueIsNotNull(str4, "partShortName");
                                packageParts2.addPart(ModuleMappingKt.internalNameOf(packageFqName, str4), access$internalNameOf);
                                i2++;
                            }
                            if (z2) {
                                ProtocolStringList<String> classWithJvmPackageNameShortNameList = packageParts.getClassWithJvmPackageNameShortNameList();
                                Intrinsics.checkExpressionValueIsNotNull(classWithJvmPackageNameShortNameList, "proto.classWithJvmPackageNameShortNameList");
                                int i3 = 0;
                                for (String str5 : classWithJvmPackageNameShortNameList) {
                                    List classWithJvmPackageNamePackageIdList = packageParts.getClassWithJvmPackageNamePackageIdList();
                                    Intrinsics.checkExpressionValueIsNotNull(classWithJvmPackageNamePackageIdList, "proto.classWithJvmPackageNamePackageIdList");
                                    Integer num2 = (Integer) CollectionsKt.getOrNull(classWithJvmPackageNamePackageIdList, i3);
                                    if (num2 == null) {
                                        List classWithJvmPackageNamePackageIdList2 = packageParts.getClassWithJvmPackageNamePackageIdList();
                                        Intrinsics.checkExpressionValueIsNotNull(classWithJvmPackageNamePackageIdList2, "proto.classWithJvmPackageNamePackageIdList");
                                        num2 = (Integer) CollectionsKt.lastOrNull(classWithJvmPackageNamePackageIdList2);
                                    }
                                    if (num2 != null) {
                                        int intValue = num2.intValue();
                                        ProtocolStringList jvmPackageNameList = parseFrom.getJvmPackageNameList();
                                        Intrinsics.checkExpressionValueIsNotNull(jvmPackageNameList, "moduleProto.jvmPackageNameList");
                                        String str6 = (String) CollectionsKt.getOrNull(jvmPackageNameList, intValue);
                                        if (str6 != null) {
                                            Intrinsics.checkExpressionValueIsNotNull(str5, "partShortName");
                                            packageParts2.addPart(ModuleMappingKt.internalNameOf(str6, str5), null);
                                        }
                                    }
                                    i3++;
                                }
                            }
                        }
                        for (PackageParts packageParts3 : parseFrom.getMetadataPartsList()) {
                            Map map2 = linkedHashMap;
                            Intrinsics.checkExpressionValueIsNotNull(packageParts3, "proto");
                            String packageFqName2 = packageParts3.getPackageFqName();
                            Intrinsics.checkExpressionValueIsNotNull(packageFqName2, "proto.packageFqName");
                            Object obj2 = map2.get(packageFqName2);
                            if (obj2 == null) {
                                String packageFqName3 = packageParts3.getPackageFqName();
                                Intrinsics.checkExpressionValueIsNotNull(packageFqName3, "proto.packageFqName");
                                obj2 = new PackageParts(packageFqName3);
                                map2.put(packageFqName2, obj2);
                            }
                            PackageParts packageParts4 = (PackageParts) obj2;
                            ProtocolStringList<String> shortClassNameList2 = packageParts3.getShortClassNameList();
                            Intrinsics.checkExpressionValueIsNotNull(shortClassNameList2, "proto.shortClassNameList");
                            for (String addMetadataPart : shortClassNameList2) {
                                packageParts4.addMetadataPart(addMetadataPart);
                            }
                        }
                        StringTable stringTable = parseFrom.getStringTable();
                        Intrinsics.checkExpressionValueIsNotNull(stringTable, "moduleProto.stringTable");
                        QualifiedNameTable qualifiedNameTable = parseFrom.getQualifiedNameTable();
                        Intrinsics.checkExpressionValueIsNotNull(qualifiedNameTable, "moduleProto.qualifiedNameTable");
                        NameResolverImpl nameResolverImpl = new NameResolverImpl(stringTable, qualifiedNameTable);
                        List annotationList = parseFrom.getAnnotationList();
                        Intrinsics.checkExpressionValueIsNotNull(annotationList, "moduleProto.annotationList");
                        Iterable<Annotation> iterable = annotationList;
                        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                        for (Annotation annotation : iterable) {
                            Intrinsics.checkExpressionValueIsNotNull(annotation, "proto");
                            arrayList.add(nameResolverImpl.getQualifiedClassName(annotation.getId()));
                        }
                        return new ModuleMapping(linkedHashMap, new BinaryModuleData((List) arrayList), str3, null);
                    }
                    function12.invoke(jvmMetadataVersion2);
                    return ModuleMapping.EMPTY;
                }
                function12.invoke(jvmMetadataVersion);
                return ModuleMapping.EMPTY;
            } catch (IOException unused) {
                return ModuleMapping.CORRUPTED;
            }
        }
    }

    private ModuleMapping(Map<String, PackageParts> map, BinaryModuleData binaryModuleData, String str) {
        this.packageFqName2Parts = map;
        this.moduleData = binaryModuleData;
        this.debugName = str;
    }

    public /* synthetic */ ModuleMapping(@NotNull Map map, @NotNull BinaryModuleData binaryModuleData, @NotNull String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, binaryModuleData, str);
    }

    @NotNull
    public final Map<String, PackageParts> getPackageFqName2Parts() {
        return this.packageFqName2Parts;
    }

    @NotNull
    public String toString() {
        return this.debugName;
    }
}
