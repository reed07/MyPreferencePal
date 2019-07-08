package io.requery.sql.gen;

import io.requery.meta.Attribute;
import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.query.element.InsertType;
import io.requery.query.element.QueryElement;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import java.util.Map;
import java.util.Map.Entry;

class InsertGenerator implements Generator<QueryElement<?>> {

    /* renamed from: io.requery.sql.gen.InsertGenerator$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$io$requery$query$ExpressionType = new int[ExpressionType.values().length];

        static {
            try {
                $SwitchMap$io$requery$query$ExpressionType[ExpressionType.ATTRIBUTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    InsertGenerator() {
    }

    public void write(final Output output, QueryElement<?> queryElement) {
        Map updateValues = queryElement.updateValues();
        InsertType insertType = queryElement.insertType();
        QueryBuilder builder = output.builder();
        builder.keyword(Keyword.INSERT, Keyword.INTO);
        output.appendTables();
        if (!updateValues.isEmpty()) {
            builder.openParenthesis().commaSeparated((Iterable<? extends T>) updateValues.entrySet(), (Appender<T>) new Appender<Entry<Expression<?>, Object>>() {
                public void append(QueryBuilder queryBuilder, Entry<Expression<?>, Object> entry) {
                    Expression expression = (Expression) entry.getKey();
                    if (AnonymousClass3.$SwitchMap$io$requery$query$ExpressionType[expression.getExpressionType().ordinal()] != 1) {
                        queryBuilder.append(expression.getName()).space();
                        return;
                    }
                    Attribute attribute = (Attribute) expression;
                    if (!attribute.isGenerated()) {
                        queryBuilder.attribute(attribute);
                        return;
                    }
                    throw new IllegalStateException();
                }
            }).closeParenthesis().space();
            if (insertType == InsertType.VALUES) {
                builder.keyword(Keyword.VALUES).openParenthesis().commaSeparated((Iterable<? extends T>) updateValues.entrySet(), (Appender<T>) new Appender<Entry<Expression<?>, Object>>() {
                    public void append(QueryBuilder queryBuilder, Entry<Expression<?>, Object> entry) {
                        output.appendConditionValue((Expression) entry.getKey(), entry.getValue());
                    }
                }).closeParenthesis();
                return;
            }
            output.appendQuery(queryElement.subQuery());
        } else if (insertType == InsertType.VALUES) {
            builder.keyword(Keyword.DEFAULT, Keyword.VALUES);
        }
    }
}
