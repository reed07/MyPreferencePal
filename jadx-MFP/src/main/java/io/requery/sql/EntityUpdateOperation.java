package io.requery.sql;

import io.requery.query.BaseScalar;
import io.requery.query.Scalar;
import io.requery.query.element.QueryElement;
import io.requery.sql.gen.DefaultOutput;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class EntityUpdateOperation extends UpdateOperation {
    public abstract int bindParameters(PreparedStatement preparedStatement) throws SQLException;

    EntityUpdateOperation(RuntimeConfiguration runtimeConfiguration, GeneratedResultReader generatedResultReader) {
        super(runtimeConfiguration, generatedResultReader);
    }

    public Scalar<Integer> evaluate(final QueryElement<Scalar<Integer>> queryElement) {
        return new BaseScalar<Integer>(this.configuration.getWriteExecutor()) {
            public Integer evaluate() {
                Connection connection;
                Throwable th;
                Throwable th2;
                DefaultOutput defaultOutput = new DefaultOutput(EntityUpdateOperation.this.configuration, queryElement, new QueryBuilder(EntityUpdateOperation.this.configuration.getQueryBuilderOptions()), null, false);
                String sql = defaultOutput.toSql();
                try {
                    connection = EntityUpdateOperation.this.configuration.getConnection();
                    StatementListener statementListener = EntityUpdateOperation.this.configuration.getStatementListener();
                    PreparedStatement prepare = EntityUpdateOperation.this.prepare(sql, connection);
                    try {
                        EntityUpdateOperation.this.bindParameters(prepare);
                        statementListener.beforeExecuteUpdate(prepare, sql, null);
                        int executeUpdate = prepare.executeUpdate();
                        statementListener.afterExecuteUpdate(prepare, executeUpdate);
                        EntityUpdateOperation.this.readGeneratedKeys(0, prepare);
                        if (prepare != null) {
                            prepare.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                        return Integer.valueOf(executeUpdate);
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        th = r3;
                        th2 = th4;
                    }
                    if (prepare != null) {
                        if (th != null) {
                            try {
                                prepare.close();
                            } catch (Throwable th5) {
                                th.addSuppressed(th5);
                            }
                        } else {
                            prepare.close();
                        }
                    }
                    throw th2;
                    throw th2;
                    throw th;
                } catch (SQLException e) {
                    throw new StatementExecutionException(e, sql);
                } catch (Throwable th6) {
                    r2.addSuppressed(th6);
                }
            }
        };
    }
}
