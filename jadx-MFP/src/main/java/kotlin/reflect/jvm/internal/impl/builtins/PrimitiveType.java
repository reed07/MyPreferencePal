package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

public enum PrimitiveType {
    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");
    
    public static final Set<PrimitiveType> NUMBER_TYPES = null;
    private FqName arrayTypeFqName;
    private final Name arrayTypeName;
    private FqName typeFqName;
    private final Name typeName;

    static {
        PrimitiveType primitiveType;
        PrimitiveType primitiveType2;
        PrimitiveType primitiveType3;
        PrimitiveType primitiveType4;
        PrimitiveType primitiveType5;
        PrimitiveType primitiveType6;
        PrimitiveType primitiveType7;
        NUMBER_TYPES = Collections.unmodifiableSet(EnumSet.of(primitiveType, new PrimitiveType[]{primitiveType2, primitiveType3, primitiveType4, primitiveType5, primitiveType6, primitiveType7}));
    }

    private PrimitiveType(String str) {
        this.typeFqName = null;
        this.arrayTypeFqName = null;
        this.typeName = Name.identifier(str);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("Array");
        this.arrayTypeName = Name.identifier(sb.toString());
    }

    @NotNull
    public Name getTypeName() {
        return this.typeName;
    }

    @NotNull
    public FqName getTypeFqName() {
        FqName fqName = this.typeFqName;
        if (fqName != null) {
            return fqName;
        }
        this.typeFqName = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(this.typeName);
        return this.typeFqName;
    }

    @NotNull
    public Name getArrayTypeName() {
        return this.arrayTypeName;
    }

    @NotNull
    public FqName getArrayTypeFqName() {
        FqName fqName = this.arrayTypeFqName;
        if (fqName != null) {
            return fqName;
        }
        this.arrayTypeFqName = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(this.arrayTypeName);
        return this.arrayTypeFqName;
    }
}
