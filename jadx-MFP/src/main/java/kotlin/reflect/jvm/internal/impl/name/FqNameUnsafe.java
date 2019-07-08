package kotlin.reflect.jvm.internal.impl.name;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

public final class FqNameUnsafe {
    private static final Name ROOT_NAME = Name.special("<root>");
    private static final Pattern SPLIT_BY_DOTS = Pattern.compile("\\.");
    private static final Function1<String, Name> STRING_TO_NAME = new Function1<String, Name>() {
        public Name invoke(String str) {
            return Name.guessByFirstCharacter(str);
        }
    };
    @NotNull
    private final String fqName;
    private transient FqNameUnsafe parent;
    private transient FqName safe;
    private transient Name shortName;

    FqNameUnsafe(@NotNull String str, @NotNull FqName fqName2) {
        this.fqName = str;
        this.safe = fqName2;
    }

    public FqNameUnsafe(@NotNull String str) {
        this.fqName = str;
    }

    private FqNameUnsafe(@NotNull String str, FqNameUnsafe fqNameUnsafe, Name name) {
        this.fqName = str;
        this.parent = fqNameUnsafe;
        this.shortName = name;
    }

    private void compute() {
        int lastIndexOf = this.fqName.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            this.shortName = Name.guessByFirstCharacter(this.fqName.substring(lastIndexOf + 1));
            this.parent = new FqNameUnsafe(this.fqName.substring(0, lastIndexOf));
            return;
        }
        this.shortName = Name.guessByFirstCharacter(this.fqName);
        this.parent = FqName.ROOT.toUnsafe();
    }

    @NotNull
    public String asString() {
        return this.fqName;
    }

    public boolean isSafe() {
        return this.safe != null || asString().indexOf(60) < 0;
    }

    @NotNull
    public FqName toSafe() {
        FqName fqName2 = this.safe;
        if (fqName2 != null) {
            return fqName2;
        }
        this.safe = new FqName(this);
        return this.safe;
    }

    public boolean isRoot() {
        return this.fqName.isEmpty();
    }

    @NotNull
    public FqNameUnsafe parent() {
        FqNameUnsafe fqNameUnsafe = this.parent;
        if (fqNameUnsafe != null) {
            return fqNameUnsafe;
        }
        if (!isRoot()) {
            compute();
            return this.parent;
        }
        throw new IllegalStateException("root");
    }

    @NotNull
    public FqNameUnsafe child(@NotNull Name name) {
        String str;
        if (isRoot()) {
            str = name.asString();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(this.fqName);
            sb.append(".");
            sb.append(name.asString());
            str = sb.toString();
        }
        return new FqNameUnsafe(str, this, name);
    }

    @NotNull
    public Name shortName() {
        Name name = this.shortName;
        if (name != null) {
            return name;
        }
        if (!isRoot()) {
            compute();
            return this.shortName;
        }
        throw new IllegalStateException("root");
    }

    @NotNull
    public Name shortNameOrSpecial() {
        if (isRoot()) {
            return ROOT_NAME;
        }
        return shortName();
    }

    @NotNull
    public List<Name> pathSegments() {
        return isRoot() ? Collections.emptyList() : ArraysKt.map((T[]) SPLIT_BY_DOTS.split(this.fqName), STRING_TO_NAME);
    }

    public boolean startsWith(@NotNull Name name) {
        int indexOf = this.fqName.indexOf(46);
        if (isRoot()) {
            return false;
        }
        String str = this.fqName;
        String asString = name.asString();
        if (indexOf == -1) {
            indexOf = this.fqName.length();
        }
        return str.regionMatches(0, asString, 0, indexOf);
    }

    @NotNull
    public static FqNameUnsafe topLevel(@NotNull Name name) {
        return new FqNameUnsafe(name.asString(), FqName.ROOT.toUnsafe(), name);
    }

    @NotNull
    public String toString() {
        return isRoot() ? ROOT_NAME.asString() : this.fqName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FqNameUnsafe)) {
            return false;
        }
        return this.fqName.equals(((FqNameUnsafe) obj).fqName);
    }

    public int hashCode() {
        return this.fqName.hashCode();
    }
}
