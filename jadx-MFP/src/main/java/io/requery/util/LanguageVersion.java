package io.requery.util;

public enum LanguageVersion {
    JAVA_1_5,
    JAVA_1_6,
    JAVA_1_7,
    JAVA_1_8,
    JAVA_1_9;
    
    private static LanguageVersion version;

    public static LanguageVersion current() {
        return version;
    }

    public boolean atLeast(LanguageVersion languageVersion) {
        return ordinal() >= languageVersion.ordinal();
    }
}
