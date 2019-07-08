package com.myfitnesspal.shared.util;

public enum RichAttributeType {
    PLAIN_TEXT(0),
    EXTERNAL_LINK(1),
    TOPIC_LINK(2),
    PRIMARY_USERNAME_LINK(3),
    USERNAME_LINK(4),
    BLOG_LINK(5),
    DIARY_LINK(6),
    GENERIC_INTERNAL_LINK(7);
    
    private final int id;

    private RichAttributeType(int i) {
        this.id = i;
    }

    public int getValue() {
        return this.id;
    }

    public static RichAttributeType getType(int i) {
        switch (i) {
            case 0:
                return PLAIN_TEXT;
            case 1:
                return EXTERNAL_LINK;
            case 2:
                return TOPIC_LINK;
            case 3:
                return PRIMARY_USERNAME_LINK;
            case 4:
                return USERNAME_LINK;
            case 5:
                return BLOG_LINK;
            case 6:
                return DIARY_LINK;
            case 7:
                return GENERIC_INTERNAL_LINK;
            default:
                return PLAIN_TEXT;
        }
    }
}
