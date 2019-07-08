package kotlin.reflect.jvm.internal.impl.name;

import org.jetbrains.annotations.NotNull;

public final class Name implements Comparable<Name> {
    @NotNull
    private final String name;
    private final boolean special;

    private Name(@NotNull String str, boolean z) {
        this.name = str;
        this.special = z;
    }

    @NotNull
    public String asString() {
        return this.name;
    }

    @NotNull
    public String getIdentifier() {
        if (!this.special) {
            return asString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("not identifier: ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public boolean isSpecial() {
        return this.special;
    }

    public int compareTo(Name name2) {
        return this.name.compareTo(name2.name);
    }

    @NotNull
    public static Name identifier(@NotNull String str) {
        return new Name(str, false);
    }

    public static boolean isValidIdentifier(@NotNull String str) {
        if (str.isEmpty() || str.startsWith("<")) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '.' || charAt == '/' || charAt == '\\') {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static Name special(@NotNull String str) {
        if (str.startsWith("<")) {
            return new Name(str, true);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("special name must start with '<': ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    @NotNull
    public static Name guessByFirstCharacter(@NotNull String str) {
        if (str.startsWith("<")) {
            return special(str);
        }
        return identifier(str);
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Name)) {
            return false;
        }
        Name name2 = (Name) obj;
        return this.special == name2.special && this.name.equals(name2.name);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + (this.special ? 1 : 0);
    }
}
