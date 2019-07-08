package io.requery.query.element;

import io.requery.meta.Attribute;
import io.requery.meta.EntityModel;
import io.requery.meta.Type;
import io.requery.query.Aliasable;
import io.requery.query.AliasedExpression;
import io.requery.query.Condition;
import io.requery.query.Deletion;
import io.requery.query.DistinctSelection;
import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.query.InsertInto;
import io.requery.query.Insertion;
import io.requery.query.JoinOn;
import io.requery.query.JoinWhereGroupByOrderBy;
import io.requery.query.Limit;
import io.requery.query.Offset;
import io.requery.query.Return;
import io.requery.query.Selectable;
import io.requery.query.Selection;
import io.requery.query.SetGroupByOrderByLimit;
import io.requery.query.SetHavingOrderByLimit;
import io.requery.query.Update;
import io.requery.query.WhereAndOr;
import io.requery.query.function.Function;
import io.requery.util.Objects;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class QueryElement<E> implements Aliasable<Return<E>>, Deletion<E>, DistinctSelection<E>, Expression<QueryElement>, InsertInto<E>, Insertion<E>, JoinWhereGroupByOrderBy<E>, Offset<E>, Selectable<E>, Selection<E>, SetGroupByOrderByLimit<E>, SetHavingOrderByLimit<E>, Update<E>, GroupByElement, LimitedElement, OrderByElement, QueryWrapper<E>, SelectionElement, SetOperationElement, WhereElement {
    private String aliasName;
    private Set<Expression<?>> from;
    private Set<Expression<?>> groupBy;
    private Set<HavingConditionElement<E>> having;
    private InsertType insertType;
    private Set<JoinOnElement<E>> joins;
    private Integer limit;
    private final EntityModel model;
    private Integer offset;
    private QueryOperation<E> operator;
    private Set<Expression<?>> orderBy;
    private QueryElement<E> parent;
    private final QueryType queryType;
    private boolean selectDistinct;
    private Set<? extends Expression<?>> selection;
    private SetOperator setOperator;
    private QueryElement<E> setQuery;
    private QueryElement<?> subQuery;
    private Set<Type<?>> types;
    private Map<Expression<?>, Object> updates;
    private Set<WhereConditionElement<E>> where = new LinkedHashSet();
    private ExistsElement<?> whereSubQuery;

    public Expression<QueryElement> getInnerExpression() {
        return null;
    }

    public String getName() {
        return "";
    }

    public QueryElement<E> unwrapQuery() {
        return this;
    }

    public QueryElement(QueryType queryType2, EntityModel entityModel, QueryOperation<E> queryOperation) {
        this.queryType = (QueryType) Objects.requireNotNull(queryType2);
        this.model = entityModel;
        this.operator = queryOperation;
    }

    public QueryType queryType() {
        return this.queryType;
    }

    public InsertType insertType() {
        return this.insertType;
    }

    public QueryElement<?> subQuery() {
        return this.subQuery;
    }

    public Set<? extends Expression<?>> getSelection() {
        return this.selection;
    }

    public boolean isDistinct() {
        return this.selectDistinct;
    }

    public Map<Expression<?>, Object> updateValues() {
        Map<Expression<?>, Object> map = this.updates;
        return map == null ? Collections.emptyMap() : map;
    }

    public Set<WhereConditionElement<?>> getWhereElements() {
        return this.where;
    }

    public ExistsElement<?> getWhereExistsElement() {
        return this.whereSubQuery;
    }

    public Set<JoinOnElement<E>> joinElements() {
        return this.joins;
    }

    public SetOperator getOperator() {
        return this.setOperator;
    }

    public Set<Expression<?>> getOrderByExpressions() {
        return this.orderBy;
    }

    public Set<Expression<?>> getGroupByExpressions() {
        return this.groupBy;
    }

    public Set<HavingConditionElement<?>> getHavingElements() {
        return this.having;
    }

    public QueryElement<E> getInnerSetQuery() {
        return this.setQuery;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public Set<Type<?>> entityTypes() {
        return this.types;
    }

    public Set<Expression<?>> fromExpressions() {
        Set<Expression> set;
        Object[] arguments;
        if (this.from == null) {
            this.types = new LinkedHashSet();
            switch (this.queryType) {
                case SELECT:
                    set = getSelection();
                    break;
                case INSERT:
                case UPDATE:
                case UPSERT:
                    set = this.updates.keySet();
                    break;
                default:
                    set = Collections.emptySet();
                    break;
            }
            for (Expression expression : set) {
                if (expression instanceof AliasedExpression) {
                    expression = ((AliasedExpression) expression).getInnerExpression();
                }
                if (expression instanceof Attribute) {
                    this.types.add(((Attribute) expression).getDeclaringType());
                } else if (expression instanceof Function) {
                    for (Object obj : ((Function) expression).arguments()) {
                        Type type = null;
                        if (obj instanceof Attribute) {
                            type = ((Attribute) obj).getDeclaringType();
                            this.types.add(type);
                        } else if (obj instanceof Class) {
                            type = this.model.typeOf((Class) obj);
                        }
                        if (type != null) {
                            this.types.add(type);
                        }
                    }
                }
            }
            if (this.from == null) {
                this.from = new LinkedHashSet();
            }
            if (!this.types.isEmpty()) {
                this.from.addAll(this.types);
            }
        }
        return this.from;
    }

    public Class<QueryElement> getClassType() {
        return QueryElement.class;
    }

    public ExpressionType getExpressionType() {
        return ExpressionType.QUERY;
    }

    public Return<E> as(String str) {
        this.aliasName = str;
        return this;
    }

    public String getAlias() {
        return this.aliasName;
    }

    public QueryElement<E> select(Expression<?>... expressionArr) {
        Set<? extends Expression<?>> set;
        if (expressionArr == null) {
            set = null;
        } else {
            set = new LinkedHashSet<>(Arrays.asList(expressionArr));
        }
        this.selection = set;
        return this;
    }

    public QueryElement<E> select(Set<? extends Expression<?>> set) {
        this.selection = set;
        return this;
    }

    public DistinctSelection<E> distinct() {
        this.selectDistinct = true;
        return this;
    }

    public QueryElement<E> from(Class<?>... clsArr) {
        this.types = new LinkedHashSet();
        for (Class<?> typeOf : clsArr) {
            this.types.add(this.model.typeOf(typeOf));
        }
        if (this.from == null) {
            this.from = new LinkedHashSet();
        }
        this.from.addAll(this.types);
        return this;
    }

    public E get() {
        QueryOperation<E> queryOperation = this.operator;
        QueryElement queryElement = this.parent;
        if (queryElement == null) {
            queryElement = this;
        }
        return queryOperation.evaluate(queryElement);
    }

    public <F extends E> QueryElement<F> extend(io.requery.util.function.Function<E, F> function) {
        this.operator = new ExtendQueryOperation(function, this.operator);
        return this;
    }

    private void addJoinElement(JoinOnElement<E> joinOnElement) {
        if (this.joins == null) {
            this.joins = new LinkedHashSet();
        }
        this.joins.add(joinOnElement);
    }

    private <J> JoinOn<E> createJoin(Class<J> cls, JoinType joinType) {
        JoinOnElement joinOnElement = new JoinOnElement(this, this.model.typeOf(cls).getName(), joinType);
        addJoinElement(joinOnElement);
        return joinOnElement;
    }

    public <J> JoinOn<E> join(Class<J> cls) {
        return createJoin(cls, JoinType.INNER);
    }

    public <V> Limit<E> orderBy(Expression<V> expression) {
        if (this.orderBy == null) {
            this.orderBy = new LinkedHashSet();
        }
        this.orderBy.add(expression);
        return this;
    }

    public Limit<E> orderBy(Expression<?>... expressionArr) {
        if (this.orderBy == null) {
            this.orderBy = new LinkedHashSet();
        }
        this.orderBy.addAll(Arrays.asList(expressionArr));
        return this;
    }

    public <V> WhereAndOr<E> where(Condition<V, ?> condition) {
        if (this.where == null) {
            this.where = new LinkedHashSet();
        }
        WhereConditionElement whereConditionElement = new WhereConditionElement(this, this.where, condition, this.where.size() > 0 ? LogicalOperator.AND : null);
        this.where.add(whereConditionElement);
        return whereConditionElement;
    }

    public Offset<E> limit(int i) {
        this.limit = Integer.valueOf(i);
        return this;
    }

    public Return<E> offset(int i) {
        this.offset = Integer.valueOf(i);
        return this;
    }

    public <V> Update<E> set(Expression<V> expression, V v) {
        value(expression, v);
        return this;
    }

    public <V> Insertion<E> value(Expression<V> expression, V v) {
        Objects.requireNotNull(expression);
        if (this.updates == null) {
            this.updates = new LinkedHashMap();
        }
        this.updates.put(expression, v);
        this.insertType = InsertType.VALUES;
        return this;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QueryElement)) {
            return false;
        }
        QueryElement queryElement = (QueryElement) obj;
        if (this.queryType != queryElement.queryType || this.selectDistinct != queryElement.selectDistinct || !Objects.equals(this.selection, queryElement.selection) || !Objects.equals(this.updates, queryElement.updates) || !Objects.equals(this.joins, queryElement.joins) || !Objects.equals(this.where, queryElement.where) || !Objects.equals(this.orderBy, queryElement.orderBy) || !Objects.equals(this.groupBy, queryElement.groupBy) || !Objects.equals(this.having, queryElement.having) || !Objects.equals(this.setQuery, queryElement.setQuery) || !Objects.equals(this.setOperator, queryElement.setOperator) || !Objects.equals(this.limit, queryElement.limit) || !Objects.equals(this.offset, queryElement.offset)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.queryType, Boolean.valueOf(this.selectDistinct), this.selection, this.updates, this.joins, this.where, this.orderBy, this.groupBy, this.having, this.limit, this.offset);
    }
}
