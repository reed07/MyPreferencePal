package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import org.jetbrains.annotations.NotNull;

/* compiled from: ClassData.kt */
public final class ClassData {
    @NotNull
    private final Class classProto;
    @NotNull
    private final BinaryVersion metadataVersion;
    @NotNull
    private final NameResolver nameResolver;
    @NotNull
    private final SourceElement sourceElement;

    @NotNull
    public final NameResolver component1() {
        return this.nameResolver;
    }

    @NotNull
    public final Class component2() {
        return this.classProto;
    }

    @NotNull
    public final BinaryVersion component3() {
        return this.metadataVersion;
    }

    @NotNull
    public final SourceElement component4() {
        return this.sourceElement;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.sourceElement, (java.lang.Object) r3.sourceElement) != false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0033
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData
            if (r0 == 0) goto L_0x0031
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData r3 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData) r3
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r0 = r2.nameResolver
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r1 = r3.nameResolver
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r0 = r2.classProto
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r1 = r3.classProto
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion r0 = r2.metadataVersion
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion r1 = r3.metadataVersion
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r0 = r2.sourceElement
            kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r3 = r3.sourceElement
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r3 = 0
            return r3
        L_0x0033:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        NameResolver nameResolver2 = this.nameResolver;
        int i = 0;
        int hashCode = (nameResolver2 != null ? nameResolver2.hashCode() : 0) * 31;
        Class classR = this.classProto;
        int hashCode2 = (hashCode + (classR != null ? classR.hashCode() : 0)) * 31;
        BinaryVersion binaryVersion = this.metadataVersion;
        int hashCode3 = (hashCode2 + (binaryVersion != null ? binaryVersion.hashCode() : 0)) * 31;
        SourceElement sourceElement2 = this.sourceElement;
        if (sourceElement2 != null) {
            i = sourceElement2.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClassData(nameResolver=");
        sb.append(this.nameResolver);
        sb.append(", classProto=");
        sb.append(this.classProto);
        sb.append(", metadataVersion=");
        sb.append(this.metadataVersion);
        sb.append(", sourceElement=");
        sb.append(this.sourceElement);
        sb.append(")");
        return sb.toString();
    }

    public ClassData(@NotNull NameResolver nameResolver2, @NotNull Class classR, @NotNull BinaryVersion binaryVersion, @NotNull SourceElement sourceElement2) {
        Intrinsics.checkParameterIsNotNull(nameResolver2, "nameResolver");
        Intrinsics.checkParameterIsNotNull(classR, "classProto");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(sourceElement2, "sourceElement");
        this.nameResolver = nameResolver2;
        this.classProto = classR;
        this.metadataVersion = binaryVersion;
        this.sourceElement = sourceElement2;
    }
}
