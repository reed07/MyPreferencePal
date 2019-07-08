package io.requery.sql;

public class UserVersionColumnDefinition implements VersionColumnDefinition {
    public String columnName() {
        return null;
    }

    public boolean createColumn() {
        return true;
    }
}
