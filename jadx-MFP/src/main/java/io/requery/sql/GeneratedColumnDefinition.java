package io.requery.sql;

import io.requery.meta.Attribute;

public interface GeneratedColumnDefinition {
    void appendGeneratedSequence(QueryBuilder queryBuilder, Attribute attribute);

    boolean postFixPrimaryKey();

    boolean skipTypeIdentifier();
}
