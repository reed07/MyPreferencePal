package com.myfitnesspal.shared.db;

public class SqlOp {
    public static String as(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        sb.append(" AS ");
        sb.append(str3);
        return sb.toString();
    }

    public static String as(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" AS ");
        sb.append(str2);
        return sb.toString();
    }

    public static String ljoin(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("=");
        sb.append(str2);
        return sb.toString();
    }

    public static String ljoin(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        sb.append("=");
        sb.append(str3);
        sb.append(".");
        sb.append(str4);
        return sb.toString();
    }

    public static String col(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        return sb.toString();
    }

    public static String bin(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        sb.append(" ");
        sb.append(str3);
        sb.append(")");
        return sb.toString();
    }

    public static String eq(String str, String str2) {
        return bin(str, "=", str2);
    }

    public static String neq(String str, String str2) {
        return bin(str, "!=", str2);
    }

    public static String or(String str, String str2) {
        return bin(str, "OR", str2);
    }

    public static String and(String str, String str2) {
        return bin(str, "AND", str2);
    }

    public static String asc(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ASC");
        return sb.toString();
    }

    public static String desc(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" DESC");
        return sb.toString();
    }
}
