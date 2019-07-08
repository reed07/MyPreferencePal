package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmMemberSignature.kt */
public abstract class JvmMemberSignature {

    /* compiled from: JvmMemberSignature.kt */
    public static final class Field extends JvmMemberSignature {
        @NotNull
        private final String desc;
        @NotNull
        private final String name;

        @NotNull
        public final String component1() {
            return getName();
        }

        @NotNull
        public final String component2() {
            return getDesc();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0022, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) getDesc(), (java.lang.Object) r3.getDesc()) != false) goto L_0x0027;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0027
                boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field
                if (r0 == 0) goto L_0x0025
                kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature$Field r3 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field) r3
                java.lang.String r0 = r2.getName()
                java.lang.String r1 = r3.getName()
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0025
                java.lang.String r0 = r2.getDesc()
                java.lang.String r3 = r3.getDesc()
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r3 == 0) goto L_0x0025
                goto L_0x0027
            L_0x0025:
                r3 = 0
                return r3
            L_0x0027:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String name2 = getName();
            int i = 0;
            int hashCode = (name2 != null ? name2.hashCode() : 0) * 31;
            String desc2 = getDesc();
            if (desc2 != null) {
                i = desc2.hashCode();
            }
            return hashCode + i;
        }

        public Field(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "name");
            Intrinsics.checkParameterIsNotNull(str2, "desc");
            super(null);
            this.name = str;
            this.desc = str2;
        }

        @NotNull
        public String getDesc() {
            return this.desc;
        }

        @NotNull
        public String getName() {
            return this.name;
        }

        @NotNull
        public String asString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getName());
            sb.append(':');
            sb.append(getDesc());
            return sb.toString();
        }
    }

    /* compiled from: JvmMemberSignature.kt */
    public static final class Method extends JvmMemberSignature {
        @NotNull
        private final String desc;
        @NotNull
        private final String name;

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0022, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) getDesc(), (java.lang.Object) r3.getDesc()) != false) goto L_0x0027;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0027
                boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method
                if (r0 == 0) goto L_0x0025
                kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature$Method r3 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method) r3
                java.lang.String r0 = r2.getName()
                java.lang.String r1 = r3.getName()
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0025
                java.lang.String r0 = r2.getDesc()
                java.lang.String r3 = r3.getDesc()
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r3 == 0) goto L_0x0025
                goto L_0x0027
            L_0x0025:
                r3 = 0
                return r3
            L_0x0027:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String name2 = getName();
            int i = 0;
            int hashCode = (name2 != null ? name2.hashCode() : 0) * 31;
            String desc2 = getDesc();
            if (desc2 != null) {
                i = desc2.hashCode();
            }
            return hashCode + i;
        }

        public Method(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "name");
            Intrinsics.checkParameterIsNotNull(str2, "desc");
            super(null);
            this.name = str;
            this.desc = str2;
        }

        @NotNull
        public String getDesc() {
            return this.desc;
        }

        @NotNull
        public String getName() {
            return this.name;
        }

        @NotNull
        public String asString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getName());
            sb.append(getDesc());
            return sb.toString();
        }
    }

    @NotNull
    public abstract String asString();

    @NotNull
    public abstract String getDesc();

    @NotNull
    public abstract String getName();

    private JvmMemberSignature() {
    }

    public /* synthetic */ JvmMemberSignature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public final String toString() {
        return asString();
    }
}
