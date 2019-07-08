package io.requery.sql;

import com.brightcove.player.network.DownloadStatus;
import com.myfitnesspal.shared.constants.SharedConstants.RequestCodes;
import io.requery.TransactionListenable;
import io.requery.meta.Attribute;
import io.requery.query.BaseResult;
import io.requery.query.Expression;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryWrapper;
import io.requery.sql.gen.DefaultOutput;
import io.requery.util.CloseableIterator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

class SelectResult<E> extends BaseResult<E> implements TransactionListenable, QueryWrapper {
    private boolean closeConnection = true;
    private final RuntimeConfiguration configuration;
    private final Integer limit;
    private final QueryElement<?> query;
    private final ResultReader<E> reader;
    private final int resultSetConcurrency = DownloadStatus.ERROR_DEVICE_NOT_FOUND;
    private final int resultSetType = RequestCodes.PICK_FROM_CAMERA;
    private final Set<? extends Expression<?>> selection;
    private String sql;

    SelectResult(RuntimeConfiguration runtimeConfiguration, QueryElement<?> queryElement, ResultReader<E> resultReader) {
        super(queryElement.getLimit());
        this.query = queryElement;
        this.configuration = runtimeConfiguration;
        this.reader = resultReader;
        this.selection = queryElement.getSelection();
        this.limit = queryElement.getLimit();
    }

    private Statement createStatement(boolean z) throws SQLException {
        Connection connection = this.configuration.getConnection();
        this.closeConnection = !(connection instanceof UncloseableConnection);
        if (!z) {
            return connection.createStatement(this.resultSetType, this.resultSetConcurrency);
        }
        return connection.prepareStatement(this.sql, this.resultSetType, this.resultSetConcurrency);
    }

    private BoundParameters createQuery(int i, int i2) {
        if (this.limit == null && i2 > 0 && i2 != Integer.MAX_VALUE) {
            this.query.limit(i2).offset(i);
        }
        DefaultOutput defaultOutput = new DefaultOutput(this.configuration, this.query);
        this.sql = defaultOutput.toSql();
        return defaultOutput.parameters();
    }

    public CloseableIterator<E> iterator(int i, int i2) {
        ResultSet resultSet;
        try {
            BoundParameters createQuery = createQuery(i, i2);
            int i3 = 0;
            Statement createStatement = createStatement(!createQuery.isEmpty());
            createStatement.setFetchSize(this.limit == null ? 0 : this.limit.intValue());
            StatementListener statementListener = this.configuration.getStatementListener();
            statementListener.beforeExecuteQuery(createStatement, this.sql, createQuery);
            if (createQuery.isEmpty()) {
                resultSet = createStatement.executeQuery(this.sql);
            } else {
                PreparedStatement preparedStatement = (PreparedStatement) createStatement;
                Mapping mapping = this.configuration.getMapping();
                while (i3 < createQuery.count()) {
                    Expression expressionAt = createQuery.expressionAt(i3);
                    Object valueAt = createQuery.valueAt(i3);
                    if (expressionAt instanceof Attribute) {
                        Attribute attribute = (Attribute) expressionAt;
                        if (attribute.isAssociation() && ((attribute.isForeignKey() || attribute.isKey()) && valueAt != null && expressionAt.getClassType().isAssignableFrom(valueAt.getClass()))) {
                            valueAt = Attributes.replaceKeyReference(valueAt, attribute);
                        }
                    }
                    i3++;
                    mapping.write(expressionAt, preparedStatement, i3, valueAt);
                }
                resultSet = preparedStatement.executeQuery();
            }
            statementListener.afterExecuteQuery(createStatement);
            ResultSetIterator resultSetIterator = new ResultSetIterator(this.reader, resultSet, this.selection, true, this.closeConnection);
            return resultSetIterator;
        } catch (Exception e) {
            throw StatementExecutionException.closing(null, e, this.sql);
        }
    }

    public QueryElement unwrapQuery() {
        return this.query;
    }
}
