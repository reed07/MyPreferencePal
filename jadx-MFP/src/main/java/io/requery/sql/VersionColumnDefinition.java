package io.requery.sql;

public interface VersionColumnDefinition {
    String columnName();

    boolean createColumn();
}
