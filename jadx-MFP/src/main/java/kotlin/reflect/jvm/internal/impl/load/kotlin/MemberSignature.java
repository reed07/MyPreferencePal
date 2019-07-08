package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberSignature.kt */
public final class MemberSignature {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String signature;

    /* compiled from: MemberSignature.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromMethod(@NotNull NameResolver nameResolver, @NotNull JvmMethodSignature jvmMethodSignature) {
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(jvmMethodSignature, "signature");
            return fromMethodNameAndDesc(nameResolver.getString(jvmMethodSignature.getName()), nameResolver.getString(jvmMethodSignature.getDesc()));
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromMethodNameAndDesc(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "name");
            Intrinsics.checkParameterIsNotNull(str2, "desc");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            return new MemberSignature(sb.toString(), null);
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromFieldNameAndDesc(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "name");
            Intrinsics.checkParameterIsNotNull(str2, "desc");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("#");
            sb.append(str2);
            return new MemberSignature(sb.toString(), null);
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromJvmMemberSignature(@NotNull JvmMemberSignature jvmMemberSignature) {
            Intrinsics.checkParameterIsNotNull(jvmMemberSignature, "signature");
            if (jvmMemberSignature instanceof Method) {
                return fromMethodNameAndDesc(jvmMemberSignature.getName(), jvmMemberSignature.getDesc());
            }
            if (jvmMemberSignature instanceof Field) {
                return fromFieldNameAndDesc(jvmMemberSignature.getName(), jvmMemberSignature.getDesc());
            }
            throw new NoWhenBranchMatchedException();
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromMethodSignatureAndParameterIndex(@NotNull MemberSignature memberSignature, int i) {
            Intrinsics.checkParameterIsNotNull(memberSignature, "signature");
            StringBuilder sb = new StringBuilder();
            sb.append(memberSignature.getSignature$descriptors_jvm());
            sb.append("@");
            sb.append(i);
            return new MemberSignature(sb.toString(), null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.signature, (java.lang.Object) ((kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature) r2).signature) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature
            if (r0 == 0) goto L_0x0013
            kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature r2 = (kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature) r2
            java.lang.String r0 = r1.signature
            java.lang.String r2 = r2.signature
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.signature;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MemberSignature(signature=");
        sb.append(this.signature);
        sb.append(")");
        return sb.toString();
    }

    private MemberSignature(String str) {
        this.signature = str;
    }

    public /* synthetic */ MemberSignature(@NotNull String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @NotNull
    public final String getSignature$descriptors_jvm() {
        return this.signature;
    }
}
