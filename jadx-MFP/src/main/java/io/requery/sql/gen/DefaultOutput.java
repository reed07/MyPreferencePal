package io.requery.sql.gen;

import com.brightcove.player.event.EventType;
import io.requery.meta.Attribute;
import io.requery.meta.QueryAttribute;
import io.requery.query.Aliasable;
import io.requery.query.Condition;
import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.query.NamedExpression;
import io.requery.query.NullOperand;
import io.requery.query.Operator;
import io.requery.query.RowExpression;
import io.requery.query.element.JoinConditionElement;
import io.requery.query.element.JoinOnElement;
import io.requery.query.element.LogicalElement;
import io.requery.query.element.LogicalOperator;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryWrapper;
import io.requery.query.function.Case;
import io.requery.query.function.Case.CaseCondition;
import io.requery.query.function.Function;
import io.requery.query.function.Function.Name;
import io.requery.sql.BoundParameters;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.sql.RuntimeConfiguration;
import io.requery.util.function.Supplier;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DefaultOutput implements Output {
    /* access modifiers changed from: private */
    public Aliases aliases;
    /* access modifiers changed from: private */
    public boolean autoAlias;
    private final RuntimeConfiguration configuration;
    private final Aliases inheritedAliases;
    private final boolean parameterize;
    private final BoundParameters parameters;
    private final QueryBuilder qb;
    private final QueryElement<?> query;
    private final StatementGenerator statementGenerator;

    private static class Aliases {
        private final Map<String, String> aliases;
        private final Set<String> appended;
        private char index;

        private Aliases() {
            this.aliases = new HashMap();
            this.appended = new HashSet();
            this.index = 'a';
        }

        private String alias(String str) {
            String str2 = (String) this.aliases.get(str);
            if (str2 != null) {
                return str2;
            }
            char c = this.index;
            if (c <= 'z') {
                Map<String, String> map = this.aliases;
                String valueOf = String.valueOf(c);
                map.put(str, valueOf);
                this.index = (char) (this.index + 1);
                return valueOf;
            }
            throw new IllegalStateException();
        }

        /* access modifiers changed from: 0000 */
        public void remove(String str) {
            String replaceAll = str.replaceAll("\"", "");
            if (this.appended.contains(replaceAll)) {
                this.aliases.remove(replaceAll);
            }
        }

        /* access modifiers changed from: 0000 */
        public void append(QueryBuilder queryBuilder, String str) {
            String replaceAll = str.replaceAll("\"", "");
            queryBuilder.tableName(str).value(alias(replaceAll));
            this.appended.add(replaceAll);
        }

        /* access modifiers changed from: 0000 */
        public void prefix(QueryBuilder queryBuilder, Attribute attribute) {
            queryBuilder.aliasAttribute(alias(attribute.getDeclaringType().getName()), attribute);
        }

        /* access modifiers changed from: 0000 */
        public void prefix(QueryBuilder queryBuilder, Expression expression) {
            Expression access$400 = DefaultOutput.unwrapExpression(expression);
            if (access$400.getExpressionType() == ExpressionType.ATTRIBUTE) {
                Attribute attribute = (Attribute) access$400;
                if (expression.getExpressionType() == ExpressionType.ALIAS) {
                    String name = attribute.getDeclaringType().getName();
                    StringBuilder sb = new StringBuilder();
                    sb.append(alias(name));
                    sb.append(".");
                    sb.append(expression.getName());
                    queryBuilder.append(sb.toString()).space();
                    return;
                }
                prefix(queryBuilder, attribute);
                return;
            }
            String alias = alias(access$400.getName());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(alias);
            sb2.append(".");
            sb2.append(expression.getName());
            queryBuilder.append(sb2.toString()).space();
        }
    }

    public DefaultOutput(RuntimeConfiguration runtimeConfiguration, QueryElement<?> queryElement) {
        this(runtimeConfiguration, queryElement, new QueryBuilder(runtimeConfiguration.getQueryBuilderOptions()), null, true);
    }

    public DefaultOutput(RuntimeConfiguration runtimeConfiguration, QueryElement<?> queryElement, QueryBuilder queryBuilder, Aliases aliases2, boolean z) {
        this.configuration = runtimeConfiguration;
        this.query = queryElement;
        this.qb = queryBuilder;
        this.inheritedAliases = aliases2;
        this.parameterize = z;
        this.statementGenerator = runtimeConfiguration.getStatementGenerator();
        this.parameters = z ? new BoundParameters() : null;
    }

    public QueryBuilder builder() {
        return this.qb;
    }

    public BoundParameters parameters() {
        return this.parameters;
    }

    public String toSql() {
        Aliases aliases2 = this.inheritedAliases;
        if (aliases2 == null) {
            aliases2 = new Aliases();
        }
        this.aliases = aliases2;
        Set fromExpressions = this.query.fromExpressions();
        Set joinElements = this.query.joinElements();
        boolean z = true;
        if (fromExpressions.size() <= 1 && (joinElements == null || joinElements.size() <= 0)) {
            z = false;
        }
        this.autoAlias = z;
        this.statementGenerator.write((Output) this, this.query);
        return this.qb.toString();
    }

    public void appendTables() {
        this.qb.commaSeparated((Iterable<? extends T>) this.query.fromExpressions(), (Appender<T>) new Appender<Expression<?>>() {
            public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                if (expression instanceof QueryWrapper) {
                    DefaultOutput.this.appendFromExpression(expression);
                } else if (DefaultOutput.this.autoAlias) {
                    DefaultOutput.this.aliases.append(queryBuilder, expression.getName());
                } else {
                    queryBuilder.tableName(expression.getName());
                }
            }
        });
        appendJoins();
    }

    private void appendJoins() {
        if (this.query.joinElements() != null && !this.query.joinElements().isEmpty()) {
            for (JoinOnElement appendJoin : this.query.joinElements()) {
                appendJoin(appendJoin);
            }
        }
    }

    /* access modifiers changed from: private */
    public void appendFromExpression(Expression expression) {
        if (expression.getExpressionType() == ExpressionType.QUERY) {
            QueryWrapper queryWrapper = (QueryWrapper) expression;
            String alias = queryWrapper.unwrapQuery().getAlias();
            if (alias != null) {
                this.qb.openParenthesis();
                appendQuery(queryWrapper);
                this.qb.closeParenthesis().space();
                this.qb.append(alias).space();
                return;
            }
            throw new IllegalStateException("query in 'from' expression must have an alias");
        }
        this.qb.append(expression.getName());
    }

    /* access modifiers changed from: private */
    public static Expression<?> unwrapExpression(Expression<?> expression) {
        return expression.getInnerExpression() != null ? expression.getInnerExpression() : expression;
    }

    private String findAlias(Expression<?> expression) {
        if (expression instanceof Aliasable) {
            return ((Aliasable) expression).getAlias();
        }
        return null;
    }

    public void appendColumn(Expression<?> expression) {
        String findAlias = findAlias(expression);
        if (expression instanceof Function) {
            appendFunction((Function) expression);
        } else if (this.autoAlias && findAlias == null && expression.getExpressionType() == ExpressionType.ATTRIBUTE) {
            this.aliases.prefix(this.qb, (Expression) expression);
        } else if (findAlias == null || findAlias.length() == 0) {
            appendColumnExpression(expression);
        } else {
            this.qb.append(findAlias).space();
        }
    }

    public void appendColumnForSelect(Expression<?> expression) {
        String findAlias = findAlias(expression);
        if (expression instanceof Function) {
            appendFunction((Function) expression);
        } else if (!this.autoAlias) {
            appendColumnExpression(expression);
        } else if (expression instanceof Attribute) {
            this.aliases.prefix(this.qb, (Attribute) expression);
        } else {
            this.aliases.prefix(this.qb, (Expression) expression);
        }
        if (findAlias != null && findAlias.length() > 0) {
            this.qb.keyword(Keyword.AS);
            this.qb.append(findAlias).space();
        }
    }

    private void appendColumnExpression(Expression expression) {
        if (AnonymousClass4.$SwitchMap$io$requery$query$ExpressionType[expression.getExpressionType().ordinal()] == 1) {
            this.qb.attribute((Attribute) expression);
        } else if (expression instanceof RowExpression) {
            RowExpression rowExpression = (RowExpression) expression;
            this.qb.openParenthesis();
            this.qb.commaSeparated((Iterable<? extends T>) rowExpression.getExpressions(), (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                    DefaultOutput.this.appendColumnForSelect(expression);
                }
            });
            this.qb.closeParenthesis().space();
        } else {
            this.qb.append(expression.getName()).space();
        }
    }

    private void appendFunction(Function function) {
        Object[] arguments;
        if (function instanceof Case) {
            appendCaseFunction((Case) function);
        } else {
            Name mapFunctionName = this.configuration.getMapping().mapFunctionName(function);
            this.qb.append(mapFunctionName.getName());
            if (function.arguments().length != 0 || !mapFunctionName.isConstant()) {
                this.qb.openParenthesis();
                int i = 0;
                for (Object obj : function.arguments()) {
                    if (i > 0) {
                        this.qb.comma();
                    }
                    if (obj instanceof Expression) {
                        Expression expression = (Expression) obj;
                        switch (expression.getExpressionType()) {
                            case ATTRIBUTE:
                                appendColumnForSelect(expression);
                                break;
                            case FUNCTION:
                                appendFunction((Function) obj);
                                break;
                            default:
                                this.qb.append(expression.getName());
                                break;
                        }
                    } else if (obj instanceof Class) {
                        this.qb.append(EventType.ANY);
                    } else {
                        appendConditionValue(function.expressionForArgument(i), obj);
                    }
                    i++;
                }
                this.qb.closeParenthesis().space();
            }
        }
    }

    private void appendCaseFunction(Case<?> caseR) {
        this.qb.keyword(Keyword.CASE);
        Iterator it = caseR.conditions().iterator();
        while (it.hasNext()) {
            CaseCondition caseCondition = (CaseCondition) it.next();
            this.qb.keyword(Keyword.WHEN);
            appendOperation(caseCondition.condition(), 0);
            this.qb.keyword(Keyword.THEN);
            if ((caseCondition.thenValue() instanceof CharSequence) || (caseCondition.thenValue() instanceof Number)) {
                appendConditionValue(caseR, caseCondition.thenValue(), false);
            } else {
                appendConditionValue(caseR, caseCondition.thenValue());
            }
        }
        if (caseR.elseValue() != null) {
            this.qb.keyword(Keyword.ELSE);
            appendConditionValue(caseR, caseR.elseValue());
        }
        this.qb.keyword(Keyword.END);
    }

    private void appendJoin(JoinOnElement<?> joinOnElement) {
        switch (joinOnElement.joinType()) {
            case INNER:
                this.qb.keyword(Keyword.INNER, Keyword.JOIN);
                break;
            case LEFT:
                this.qb.keyword(Keyword.LEFT, Keyword.JOIN);
                break;
            case RIGHT:
                this.qb.keyword(Keyword.RIGHT, Keyword.JOIN);
                break;
        }
        if (joinOnElement.tableName() != null) {
            if (this.autoAlias) {
                this.aliases.remove(joinOnElement.tableName());
                this.aliases.append(this.qb, joinOnElement.tableName());
            } else {
                this.qb.tableName(joinOnElement.tableName());
            }
        } else if (joinOnElement.subQuery() != null) {
            this.qb.openParenthesis();
            appendQuery((QueryWrapper) joinOnElement.subQuery());
            this.qb.closeParenthesis().space();
            if (joinOnElement.subQuery().getAlias() != null) {
                this.qb.append(joinOnElement.subQuery().getAlias()).space();
            }
        }
        this.qb.keyword(Keyword.ON);
        for (JoinConditionElement appendConditional : joinOnElement.conditions()) {
            appendConditional(appendConditional);
        }
    }

    private void appendOperation(Condition condition, int i) {
        Object leftOperand = condition.getLeftOperand();
        if (leftOperand instanceof Expression) {
            final Expression expression = (Expression) condition.getLeftOperand();
            appendColumn(expression);
            Object rightOperand = condition.getRightOperand();
            appendOperator(condition.getOperator());
            if ((rightOperand instanceof Collection) && (condition.getOperator() == Operator.IN || condition.getOperator() == Operator.NOT_IN)) {
                Collection collection = (Collection) rightOperand;
                this.qb.openParenthesis();
                this.qb.commaSeparated((Iterable<? extends T>) collection, (Appender<T>) new Appender() {
                    public void append(QueryBuilder queryBuilder, Object obj) {
                        DefaultOutput.this.appendConditionValue(expression, obj);
                    }
                });
                this.qb.closeParenthesis();
            } else if (rightOperand instanceof Object[]) {
                Object[] objArr = (Object[]) rightOperand;
                if (condition.getOperator() == Operator.BETWEEN) {
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    appendConditionValue(expression, obj);
                    this.qb.keyword(Keyword.AND);
                    appendConditionValue(expression, obj2);
                    return;
                }
                for (Object appendConditionValue : objArr) {
                    appendConditionValue(expression, appendConditionValue);
                }
            } else if (rightOperand instanceof QueryWrapper) {
                QueryWrapper queryWrapper = (QueryWrapper) rightOperand;
                this.qb.openParenthesis();
                appendQuery(queryWrapper);
                this.qb.closeParenthesis().space();
            } else if (rightOperand instanceof Condition) {
                appendOperation((Condition) rightOperand, i + 1);
            } else if (rightOperand != null) {
                appendConditionValue(expression, rightOperand);
            }
        } else if (!(leftOperand instanceof Condition)) {
            StringBuilder sb = new StringBuilder();
            sb.append("unknown start expression type ");
            sb.append(leftOperand);
            throw new IllegalStateException(sb.toString());
        } else if (condition.getRightOperand() instanceof NullOperand) {
            appendOperator(condition.getOperator());
            if (i > 0) {
                this.qb.openParenthesis();
            }
            appendOperation((Condition) leftOperand, i + 1);
            if (i > 0) {
                this.qb.closeParenthesis().space();
            }
        } else {
            if (i > 0) {
                this.qb.openParenthesis();
            }
            int i2 = i + 1;
            appendOperation((Condition) leftOperand, i2);
            appendOperator(condition.getOperator());
            Object rightOperand2 = condition.getRightOperand();
            if (rightOperand2 instanceof Condition) {
                appendOperation((Condition) rightOperand2, i2);
                if (i > 0) {
                    this.qb.closeParenthesis().space();
                    return;
                }
                return;
            }
            throw new IllegalStateException();
        }
    }

    public void appendConditionValue(Expression expression, Object obj) {
        appendConditionValue(expression, obj, true);
    }

    private void appendConditionValue(Expression expression, Object obj, boolean z) {
        if (obj instanceof QueryAttribute) {
            appendColumn((Expression) obj);
            return;
        }
        if (obj instanceof Supplier) {
            Supplier supplier = (Supplier) obj;
            if (supplier.get() instanceof QueryAttribute) {
                appendColumn((Expression) supplier.get());
                return;
            }
        }
        if (obj instanceof NamedExpression) {
            this.qb.append(((NamedExpression) obj).getName());
        } else if (obj instanceof Function) {
            appendFunction((Function) obj);
        } else if ((obj instanceof Collection) && expression.getExpressionType() == ExpressionType.ROW) {
            this.qb.openParenthesis();
            this.qb.commaSeparated((Collection) obj);
            this.qb.closeParenthesis();
        } else if (z) {
            BoundParameters boundParameters = this.parameters;
            if (boundParameters != null) {
                boundParameters.add(expression, obj);
            }
            this.qb.append("?").space();
        } else if (obj instanceof CharSequence) {
            this.qb.appendQuoted(obj.toString()).space();
        } else {
            this.qb.append(obj).space();
        }
    }

    public void appendConditional(LogicalElement logicalElement) {
        LogicalOperator operator = logicalElement.getOperator();
        if (operator != null) {
            switch (operator) {
                case AND:
                    this.qb.keyword(Keyword.AND);
                    break;
                case OR:
                    this.qb.keyword(Keyword.OR);
                    break;
            }
        }
        Condition condition = logicalElement.getCondition();
        boolean z = condition.getRightOperand() instanceof Condition;
        if (z) {
            this.qb.openParenthesis();
        }
        appendOperation(condition, 0);
        if (z) {
            this.qb.closeParenthesis().space();
        }
    }

    public void appendOperator(Operator operator) {
        switch (operator) {
            case EQUAL:
                this.qb.value("=");
                return;
            case NOT_EQUAL:
                this.qb.value("!=");
                return;
            case LESS_THAN:
                this.qb.value("<");
                return;
            case LESS_THAN_OR_EQUAL:
                this.qb.value("<=");
                return;
            case GREATER_THAN:
                this.qb.value(">");
                return;
            case GREATER_THAN_OR_EQUAL:
                this.qb.value(">=");
                return;
            case IN:
                this.qb.keyword(Keyword.IN);
                return;
            case NOT_IN:
                this.qb.keyword(Keyword.NOT, Keyword.IN);
                return;
            case LIKE:
                this.qb.keyword(Keyword.LIKE);
                return;
            case NOT_LIKE:
                this.qb.keyword(Keyword.NOT, Keyword.LIKE);
                return;
            case BETWEEN:
                this.qb.keyword(Keyword.BETWEEN);
                return;
            case IS_NULL:
                this.qb.keyword(Keyword.IS, Keyword.NULL);
                return;
            case NOT_NULL:
                this.qb.keyword(Keyword.IS, Keyword.NOT, Keyword.NULL);
                return;
            case AND:
                this.qb.keyword(Keyword.AND);
                return;
            case OR:
                this.qb.keyword(Keyword.OR);
                return;
            case NOT:
                this.qb.keyword(Keyword.NOT);
                return;
            default:
                return;
        }
    }

    public void appendQuery(QueryWrapper<?> queryWrapper) {
        DefaultOutput defaultOutput = new DefaultOutput(this.configuration, queryWrapper.unwrapQuery(), this.qb, this.aliases, this.parameterize);
        defaultOutput.toSql();
        BoundParameters boundParameters = this.parameters;
        if (boundParameters != null) {
            boundParameters.addAll(defaultOutput.parameters());
        }
    }
}
