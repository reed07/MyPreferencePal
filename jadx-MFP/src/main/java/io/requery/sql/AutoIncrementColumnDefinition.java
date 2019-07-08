package io.requery.sql;

import io.requery.meta.Attribute;

public class AutoIncrementColumnDefinition implements GeneratedColumnDefinition {
    private final String autoincrementKeyword;

    public boolean postFixPrimaryKey() {
        return true;
    }

    public boolean skipTypeIdentifier() {
        return false;
    }

    public AutoIncrementColumnDefinition() {
        this("auto_increment");
    }

    public AutoIncrementColumnDefinition(String str) {
        this.autoincrementKeyword = str;
    }

    public void appendGeneratedSequence(QueryBuilder queryBuilder, Attribute attribute) {
        queryBuilder.append(this.autoincrementKeyword);
    }
}
