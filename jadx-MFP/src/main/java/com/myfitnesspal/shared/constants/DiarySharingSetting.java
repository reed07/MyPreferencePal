package com.myfitnesspal.shared.constants;

import com.uacf.core.util.Strings;

public enum DiarySharingSetting {
    Private,
    Public,
    FriendsOnly,
    Password;

    public String toString() {
        switch (this) {
            case Private:
                return "private";
            case Public:
                return "public";
            case FriendsOnly:
                return AttributeValue.PERMISSION_FRIENDS_ONLY;
            case Password:
                return "password";
            default:
                throw new IllegalStateException("Unknown value");
        }
    }

    public static DiarySharingSetting fromString(String str) {
        if (Strings.equalsIgnoreCase("private", str)) {
            return Private;
        }
        if (Strings.equalsIgnoreCase("public", str)) {
            return Public;
        }
        if (Strings.equalsIgnoreCase(AttributeValue.PERMISSION_FRIENDS_ONLY, str)) {
            return FriendsOnly;
        }
        if (Strings.equalsIgnoreCase("password", str)) {
            return Password;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown DiarySharingSetting: ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }
}
