package io.requery.sql;

import io.requery.meta.Attribute;

public class IdentityColumnDefinition implements GeneratedColumnDefinition {
    public boolean postFixPrimaryKey() {
        return false;
    }

    public boolean skipTypeIdentifier() {
        return false;
    }

    public void appendGeneratedSequence(QueryBuilder queryBuilder, Attribute attribute) {
        queryBuilder.keyword(Keyword.GENERATED, Keyword.ALWAYS, Keyword.AS, Keyword.IDENTITY);
        queryBuilder.openParenthesis().keyword(Keyword.START, Keyword.WITH).value(Integer.valueOf(1)).comma().keyword(Keyword.INCREMENT, Keyword.BY).value(Integer.valueOf(1)).closeParenthesis().space();
    }
}
