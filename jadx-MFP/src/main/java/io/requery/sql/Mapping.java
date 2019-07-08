package io.requery.sql;

import io.requery.meta.Attribute;
import io.requery.query.Expression;
import io.requery.query.function.Function;
import io.requery.query.function.Function.Name;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapping {
    Mapping aliasFunction(Name name, Class<? extends Function> cls);

    FieldType mapAttribute(Attribute<?, ?> attribute);

    Name mapFunctionName(Function<?> function);

    <T> Mapping putType(Class<? super T> cls, FieldType<T> fieldType);

    <A> A read(Expression<A> expression, ResultSet resultSet, int i) throws SQLException;

    boolean readBoolean(ResultSet resultSet, int i) throws SQLException;

    byte readByte(ResultSet resultSet, int i) throws SQLException;

    double readDouble(ResultSet resultSet, int i) throws SQLException;

    float readFloat(ResultSet resultSet, int i) throws SQLException;

    int readInt(ResultSet resultSet, int i) throws SQLException;

    long readLong(ResultSet resultSet, int i) throws SQLException;

    short readShort(ResultSet resultSet, int i) throws SQLException;

    <T> Mapping replaceType(int i, FieldType<T> fieldType);

    <A> void write(Expression<A> expression, PreparedStatement preparedStatement, int i, A a) throws SQLException;

    void writeBoolean(PreparedStatement preparedStatement, int i, boolean z) throws SQLException;

    void writeByte(PreparedStatement preparedStatement, int i, byte b) throws SQLException;

    void writeDouble(PreparedStatement preparedStatement, int i, double d) throws SQLException;

    void writeFloat(PreparedStatement preparedStatement, int i, float f) throws SQLException;

    void writeInt(PreparedStatement preparedStatement, int i, int i2) throws SQLException;

    void writeLong(PreparedStatement preparedStatement, int i, long j) throws SQLException;

    void writeShort(PreparedStatement preparedStatement, int i, short s) throws SQLException;
}
