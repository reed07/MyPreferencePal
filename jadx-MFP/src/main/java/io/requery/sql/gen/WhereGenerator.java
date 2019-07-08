package io.requery.sql.gen;

import io.requery.query.element.ExistsElement;
import io.requery.query.element.QueryWrapper;
import io.requery.query.element.WhereConditionElement;
import io.requery.query.element.WhereElement;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;

class WhereGenerator implements Generator<WhereElement> {
    WhereGenerator() {
    }

    public void write(Output output, WhereElement whereElement) {
        QueryBuilder builder = output.builder();
        ExistsElement whereExistsElement = whereElement.getWhereExistsElement();
        if (whereExistsElement != null) {
            builder.keyword(Keyword.WHERE);
            if (whereExistsElement.isNotExists()) {
                builder.keyword(Keyword.NOT);
            }
            builder.keyword(Keyword.EXISTS);
            builder.openParenthesis();
            output.appendQuery((QueryWrapper) whereExistsElement.getQuery());
            builder.closeParenthesis().space();
        } else if (whereElement.getWhereElements() != null && whereElement.getWhereElements().size() > 0) {
            builder.keyword(Keyword.WHERE);
            for (WhereConditionElement appendConditional : whereElement.getWhereElements()) {
                output.appendConditional(appendConditional);
            }
        }
    }
}
