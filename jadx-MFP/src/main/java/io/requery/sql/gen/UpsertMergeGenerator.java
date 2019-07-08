package io.requery.sql.gen;

import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class UpsertMergeGenerator implements Generator<Map<Expression<?>, Object>> {
    protected final String alias = "val";

    public void write(Output output, Map<Expression<?>, Object> map) {
        Type type;
        QueryBuilder builder = output.builder();
        Iterator it = map.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                type = null;
                break;
            }
            Expression expression = (Expression) it.next();
            if (expression.getExpressionType() == ExpressionType.ATTRIBUTE) {
                type = ((Attribute) expression).getDeclaringType();
                break;
            }
        }
        if (type != null) {
            builder.keyword(Keyword.MERGE).keyword(Keyword.INTO).tableName(type.getName()).keyword(Keyword.USING);
            appendUsing(output, map);
            builder.keyword(Keyword.ON).openParenthesis();
            Set<Attribute> keyAttributes = type.getKeyAttributes();
            if (keyAttributes.isEmpty()) {
                keyAttributes = type.getAttributes();
            }
            int i = 0;
            for (Attribute attribute : keyAttributes) {
                if (i > 0) {
                    builder.keyword(Keyword.AND);
                }
                builder.aliasAttribute(type.getName(), attribute);
                builder.append(" = ");
                builder.aliasAttribute("val", attribute);
                i++;
            }
            builder.closeParenthesis().space();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (Expression expression2 : map.keySet()) {
                if (expression2.getExpressionType() == ExpressionType.ATTRIBUTE) {
                    Attribute attribute2 = (Attribute) expression2;
                    if (!attribute2.isKey()) {
                        linkedHashSet.add(attribute2);
                    }
                }
            }
            builder.keyword(Keyword.WHEN, Keyword.MATCHED, Keyword.THEN, Keyword.UPDATE, Keyword.SET).commaSeparated((Iterable<? extends T>) linkedHashSet, (Appender<T>) new Appender<Attribute<?, ?>>() {
                public void append(QueryBuilder queryBuilder, Attribute<?, ?> attribute) {
                    queryBuilder.attribute(attribute);
                    StringBuilder sb = new StringBuilder();
                    sb.append(" = val.");
                    sb.append(attribute.getName());
                    queryBuilder.append(sb.toString());
                }
            }).space();
            builder.keyword(Keyword.WHEN, Keyword.NOT, Keyword.MATCHED, Keyword.THEN, Keyword.INSERT).openParenthesis().commaSeparatedExpressions(map.keySet()).closeParenthesis().space().keyword(Keyword.VALUES).openParenthesis().commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                    queryBuilder.aliasAttribute("val", (Attribute) expression);
                }
            }).closeParenthesis();
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: protected */
    public void appendUsing(final Output output, final Map<Expression<?>, Object> map) {
        output.builder().openParenthesis().keyword(Keyword.VALUES).openParenthesis().commaSeparated((Iterable<? extends T>) map.keySet(), (Appender<T>) new Appender<Expression>() {
            public void append(QueryBuilder queryBuilder, Expression expression) {
                queryBuilder.append("?");
                output.parameters().add(expression, map.get(expression));
            }
        }).closeParenthesis().closeParenthesis().space().keyword(Keyword.AS).append("val").openParenthesis().commaSeparatedExpressions(map.keySet()).closeParenthesis().space();
    }
}
