package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;

public class JvmClassName {
    private FqName fqName;
    private final String internalName;

    @NotNull
    public static JvmClassName byInternalName(@NotNull String str) {
        return new JvmClassName(str);
    }

    @NotNull
    public static JvmClassName byClassId(@NotNull ClassId classId) {
        FqName packageFqName = classId.getPackageFqName();
        String replace = classId.getRelativeClassName().asString().replace('.', Typography.dollar);
        if (packageFqName.isRoot()) {
            return new JvmClassName(replace);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(packageFqName.asString().replace('.', '/'));
        sb.append("/");
        sb.append(replace);
        return new JvmClassName(sb.toString());
    }

    @NotNull
    public static JvmClassName byFqNameWithoutInnerClasses(@NotNull FqName fqName2) {
        JvmClassName jvmClassName = new JvmClassName(fqName2.asString().replace('.', '/'));
        jvmClassName.fqName = fqName2;
        return jvmClassName;
    }

    private JvmClassName(@NotNull String str) {
        this.internalName = str;
    }

    @NotNull
    public FqName getFqNameForTopLevelClassMaybeWithDollars() {
        return new FqName(this.internalName.replace('/', '.'));
    }

    @NotNull
    public FqName getPackageFqName() {
        int lastIndexOf = this.internalName.lastIndexOf("/");
        if (lastIndexOf == -1) {
            return FqName.ROOT;
        }
        return new FqName(this.internalName.substring(0, lastIndexOf).replace('/', '.'));
    }

    @NotNull
    public String getInternalName() {
        return this.internalName;
    }

    public String toString() {
        return this.internalName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.internalName.equals(((JvmClassName) obj).internalName);
    }

    public int hashCode() {
        return this.internalName.hashCode();
    }
}
