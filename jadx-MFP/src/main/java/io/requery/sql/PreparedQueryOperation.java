package io.requery.sql;

import io.requery.meta.Attribute;
import io.requery.meta.EntityModel;
import io.requery.query.Expression;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class PreparedQueryOperation {
    final RuntimeConfiguration configuration;
    private final GeneratedResultReader generatedResultReader;
    private final EntityModel model;

    PreparedQueryOperation(RuntimeConfiguration runtimeConfiguration, GeneratedResultReader generatedResultReader2) {
        this.configuration = runtimeConfiguration;
        this.generatedResultReader = generatedResultReader2;
        this.model = runtimeConfiguration.getModel();
    }

    /* access modifiers changed from: 0000 */
    public PreparedStatement prepare(String str, Connection connection) throws SQLException {
        if (this.generatedResultReader == null) {
            return connection.prepareStatement(str, 2);
        }
        if (this.configuration.getPlatform().supportsGeneratedColumnsInPrepareStatement()) {
            return connection.prepareStatement(str, this.generatedResultReader.generatedColumns());
        }
        return connection.prepareStatement(str, 1);
    }

    /* access modifiers changed from: 0000 */
    public void mapParameters(PreparedStatement preparedStatement, BoundParameters boundParameters) throws SQLException {
        Class cls;
        int i = 0;
        while (i < boundParameters.count()) {
            Expression expressionAt = boundParameters.expressionAt(i);
            Object valueAt = boundParameters.valueAt(i);
            if (expressionAt instanceof Attribute) {
                Attribute attribute = (Attribute) expressionAt;
                if (attribute.isAssociation()) {
                    valueAt = Attributes.replaceKeyReference(valueAt, attribute);
                }
            }
            if (valueAt == null) {
                cls = null;
            } else {
                cls = valueAt.getClass();
            }
            if (cls != null && this.model.containsTypeOf(cls)) {
                Attribute singleKeyAttribute = this.model.typeOf(cls).getSingleKeyAttribute();
                if (singleKeyAttribute != null) {
                    valueAt = singleKeyAttribute.getProperty().get(valueAt);
                    expressionAt = (Expression) singleKeyAttribute;
                }
            }
            i++;
            this.configuration.getMapping().write(expressionAt, preparedStatement, i, valueAt);
        }
    }

    /* access modifiers changed from: 0000 */
    public void readGeneratedKeys(int i, Statement statement) throws SQLException {
        Throwable th;
        if (this.generatedResultReader != null) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            try {
                this.generatedResultReader.read(i, generatedKeys);
                if (generatedKeys != null) {
                    generatedKeys.close();
                    return;
                }
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            return;
        }
        throw th;
    }
}
