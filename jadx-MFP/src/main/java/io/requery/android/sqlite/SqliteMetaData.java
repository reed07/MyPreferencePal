package io.requery.android.sqlite;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import com.myfitnesspal.shared.constants.SharedConstants.RequestCodes;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.sql.QueryBuilder.Options;
import io.requery.util.function.Function;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class SqliteMetaData implements DatabaseMetaData {
    private final BaseConnection connection;

    public boolean allProceduresAreCallable() throws SQLException {
        return false;
    }

    public boolean allTablesAreSelectable() throws SQLException {
        return true;
    }

    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        return false;
    }

    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        return false;
    }

    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        return false;
    }

    public boolean deletesAreDetected(int i) throws SQLException {
        return false;
    }

    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        return false;
    }

    public ResultSet getAttributes(String str, String str2, String str3, String str4) throws SQLException {
        return null;
    }

    public ResultSet getBestRowIdentifier(String str, String str2, String str3, int i, boolean z) throws SQLException {
        return null;
    }

    public String getCatalogSeparator() throws SQLException {
        return null;
    }

    public String getCatalogTerm() throws SQLException {
        return null;
    }

    public ResultSet getCatalogs() throws SQLException {
        return null;
    }

    public ResultSet getClientInfoProperties() throws SQLException {
        return null;
    }

    public ResultSet getColumnPrivileges(String str, String str2, String str3, String str4) throws SQLException {
        return null;
    }

    public ResultSet getColumns(String str, String str2, String str3, String str4) throws SQLException {
        return null;
    }

    public ResultSet getCrossReference(String str, String str2, String str3, String str4, String str5, String str6) throws SQLException {
        return null;
    }

    public String getDatabaseProductName() throws SQLException {
        return "SQLite";
    }

    public int getDefaultTransactionIsolation() throws SQLException {
        return 2;
    }

    public int getDriverMajorVersion() {
        return 1;
    }

    public int getDriverMinorVersion() {
        return 0;
    }

    public String getDriverName() throws SQLException {
        return "SQLite Android";
    }

    public String getDriverVersion() throws SQLException {
        return "1.0";
    }

    public ResultSet getExportedKeys(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public String getExtraNameCharacters() throws SQLException {
        return "";
    }

    public ResultSet getFunctionColumns(String str, String str2, String str3, String str4) throws SQLException {
        return null;
    }

    public ResultSet getFunctions(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public String getIdentifierQuoteString() throws SQLException {
        return "\"";
    }

    public ResultSet getImportedKeys(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public ResultSet getIndexInfo(String str, String str2, String str3, boolean z, boolean z2) throws SQLException {
        return null;
    }

    public int getJDBCMajorVersion() throws SQLException {
        return 1;
    }

    public int getJDBCMinorVersion() throws SQLException {
        return 0;
    }

    public int getMaxBinaryLiteralLength() throws SQLException {
        return 0;
    }

    public int getMaxCatalogNameLength() throws SQLException {
        return 0;
    }

    public int getMaxCharLiteralLength() throws SQLException {
        return 0;
    }

    public int getMaxColumnNameLength() throws SQLException {
        return 1000000000;
    }

    public int getMaxColumnsInGroupBy() throws SQLException {
        return 500;
    }

    public int getMaxColumnsInIndex() throws SQLException {
        return 500;
    }

    public int getMaxColumnsInOrderBy() throws SQLException {
        return 500;
    }

    public int getMaxColumnsInSelect() throws SQLException {
        return 500;
    }

    public int getMaxColumnsInTable() throws SQLException {
        return 2000;
    }

    public int getMaxConnections() throws SQLException {
        return 1;
    }

    public int getMaxCursorNameLength() throws SQLException {
        return 0;
    }

    public int getMaxIndexLength() throws SQLException {
        return 0;
    }

    public int getMaxProcedureNameLength() throws SQLException {
        return 0;
    }

    public int getMaxRowSize() throws SQLException {
        return 0;
    }

    public int getMaxSchemaNameLength() throws SQLException {
        return 1000000;
    }

    public int getMaxStatementLength() throws SQLException {
        return 1000000;
    }

    public int getMaxStatements() throws SQLException {
        return 0;
    }

    public int getMaxTableNameLength() throws SQLException {
        return 1000000;
    }

    public int getMaxTablesInSelect() throws SQLException {
        return 500;
    }

    public int getMaxUserNameLength() throws SQLException {
        return 0;
    }

    public String getNumericFunctions() throws SQLException {
        return "abs,hex,max,min,random";
    }

    public ResultSet getPrimaryKeys(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public ResultSet getProcedureColumns(String str, String str2, String str3, String str4) throws SQLException {
        return null;
    }

    public String getProcedureTerm() throws SQLException {
        return null;
    }

    public ResultSet getProcedures(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public int getResultSetHoldability() throws SQLException {
        return 1;
    }

    public String getSQLKeywords() throws SQLException {
        return "";
    }

    public int getSQLStateType() throws SQLException {
        return 2;
    }

    public String getSchemaTerm() throws SQLException {
        return null;
    }

    public ResultSet getSchemas() throws SQLException {
        return null;
    }

    public ResultSet getSchemas(String str, String str2) throws SQLException {
        return null;
    }

    public String getSearchStringEscape() throws SQLException {
        return null;
    }

    public String getStringFunctions() throws SQLException {
        return "";
    }

    public ResultSet getSuperTables(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public ResultSet getSuperTypes(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public String getSystemFunctions() throws SQLException {
        return "";
    }

    public ResultSet getTablePrivileges(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public String getTimeDateFunctions() throws SQLException {
        return null;
    }

    public ResultSet getTypeInfo() throws SQLException {
        return null;
    }

    public ResultSet getUDTs(String str, String str2, String str3, int[] iArr) throws SQLException {
        return null;
    }

    public String getURL() throws SQLException {
        return null;
    }

    public String getUserName() throws SQLException {
        return null;
    }

    public ResultSet getVersionColumns(String str, String str2, String str3) throws SQLException {
        return null;
    }

    public boolean insertsAreDetected(int i) throws SQLException {
        return false;
    }

    public boolean isCatalogAtStart() throws SQLException {
        return false;
    }

    public boolean isWrapperFor(Class<?> cls) throws SQLException {
        return false;
    }

    public boolean locatorsUpdateCopy() throws SQLException {
        return false;
    }

    public boolean nullPlusNonNullIsNull() throws SQLException {
        return true;
    }

    public boolean nullsAreSortedAtEnd() throws SQLException {
        return false;
    }

    public boolean nullsAreSortedAtStart() throws SQLException {
        return true;
    }

    public boolean nullsAreSortedHigh() throws SQLException {
        return false;
    }

    public boolean nullsAreSortedLow() throws SQLException {
        return false;
    }

    public boolean othersDeletesAreVisible(int i) throws SQLException {
        return false;
    }

    public boolean othersInsertsAreVisible(int i) throws SQLException {
        return false;
    }

    public boolean othersUpdatesAreVisible(int i) throws SQLException {
        return false;
    }

    public boolean ownDeletesAreVisible(int i) throws SQLException {
        return false;
    }

    public boolean ownInsertsAreVisible(int i) throws SQLException {
        return false;
    }

    public boolean ownUpdatesAreVisible(int i) throws SQLException {
        return false;
    }

    public boolean storesLowerCaseIdentifiers() throws SQLException {
        return false;
    }

    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    public boolean storesMixedCaseIdentifiers() throws SQLException {
        return false;
    }

    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    public boolean storesUpperCaseIdentifiers() throws SQLException {
        return true;
    }

    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        return true;
    }

    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        return false;
    }

    public boolean supportsANSI92FullSQL() throws SQLException {
        return false;
    }

    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        return false;
    }

    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        return true;
    }

    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        return false;
    }

    public boolean supportsBatchUpdates() throws SQLException {
        return false;
    }

    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        return false;
    }

    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        return false;
    }

    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        return false;
    }

    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        return false;
    }

    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        return false;
    }

    public boolean supportsColumnAliasing() throws SQLException {
        return true;
    }

    public boolean supportsConvert() throws SQLException {
        return false;
    }

    public boolean supportsConvert(int i, int i2) throws SQLException {
        return false;
    }

    public boolean supportsCoreSQLGrammar() throws SQLException {
        return false;
    }

    public boolean supportsCorrelatedSubqueries() throws SQLException {
        return false;
    }

    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        return false;
    }

    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        return false;
    }

    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        return false;
    }

    public boolean supportsExpressionsInOrderBy() throws SQLException {
        return false;
    }

    public boolean supportsExtendedSQLGrammar() throws SQLException {
        return false;
    }

    public boolean supportsFullOuterJoins() throws SQLException {
        return true;
    }

    public boolean supportsGetGeneratedKeys() throws SQLException {
        return true;
    }

    public boolean supportsGroupBy() throws SQLException {
        return true;
    }

    public boolean supportsGroupByBeyondSelect() throws SQLException {
        return true;
    }

    public boolean supportsGroupByUnrelated() throws SQLException {
        return true;
    }

    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        return false;
    }

    public boolean supportsLikeEscapeClause() throws SQLException {
        return true;
    }

    public boolean supportsLimitedOuterJoins() throws SQLException {
        return false;
    }

    public boolean supportsMinimumSQLGrammar() throws SQLException {
        return true;
    }

    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        return true;
    }

    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    public boolean supportsMultipleOpenResults() throws SQLException {
        return true;
    }

    public boolean supportsMultipleResultSets() throws SQLException {
        return false;
    }

    public boolean supportsMultipleTransactions() throws SQLException {
        return true;
    }

    public boolean supportsNamedParameters() throws SQLException {
        return true;
    }

    public boolean supportsNonNullableColumns() throws SQLException {
        return true;
    }

    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        return true;
    }

    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        return true;
    }

    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        return true;
    }

    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        return true;
    }

    public boolean supportsOrderByUnrelated() throws SQLException {
        return true;
    }

    public boolean supportsOuterJoins() throws SQLException {
        return true;
    }

    public boolean supportsPositionedDelete() throws SQLException {
        return false;
    }

    public boolean supportsPositionedUpdate() throws SQLException {
        return false;
    }

    public boolean supportsResultSetConcurrency(int i, int i2) throws SQLException {
        return i2 == 1007;
    }

    public boolean supportsResultSetHoldability(int i) throws SQLException {
        return i == 1;
    }

    public boolean supportsResultSetType(int i) throws SQLException {
        switch (i) {
            case RequestCodes.PICK_FROM_CAMERA /*1003*/:
            case 1004:
                return true;
            default:
                return false;
        }
    }

    public boolean supportsSavepoints() throws SQLException {
        return true;
    }

    public boolean supportsSchemasInDataManipulation() throws SQLException {
        return false;
    }

    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        return false;
    }

    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        return false;
    }

    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        return false;
    }

    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        return false;
    }

    public boolean supportsSelectForUpdate() throws SQLException {
        return false;
    }

    public boolean supportsStatementPooling() throws SQLException {
        return false;
    }

    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        return false;
    }

    public boolean supportsStoredProcedures() throws SQLException {
        return false;
    }

    public boolean supportsSubqueriesInComparisons() throws SQLException {
        return false;
    }

    public boolean supportsSubqueriesInExists() throws SQLException {
        return true;
    }

    public boolean supportsSubqueriesInIns() throws SQLException {
        return true;
    }

    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        return false;
    }

    public boolean supportsTableCorrelationNames() throws SQLException {
        return false;
    }

    public boolean supportsTransactionIsolationLevel(int i) throws SQLException {
        if (i != 8) {
            switch (i) {
                case 1:
                case 2:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public boolean supportsTransactions() throws SQLException {
        return true;
    }

    public boolean supportsUnion() throws SQLException {
        return true;
    }

    public boolean supportsUnionAll() throws SQLException {
        return true;
    }

    public <T> T unwrap(Class<T> cls) throws SQLException {
        return null;
    }

    public boolean updatesAreDetected(int i) throws SQLException {
        return false;
    }

    public boolean usesLocalFilePerTable() throws SQLException {
        return false;
    }

    public boolean usesLocalFiles() throws SQLException {
        return true;
    }

    protected SqliteMetaData(BaseConnection baseConnection) {
        this.connection = baseConnection;
    }

    /* access modifiers changed from: protected */
    public <R> R queryMemory(Function<Cursor, R> function, String str) throws SQLException {
        try {
            SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
            return function.apply(closeWithCursor(openOrCreateDatabase, openOrCreateDatabase.rawQuery(str, null)));
        } catch (android.database.SQLException e) {
            throw new SQLException(e);
        }
    }

    /* access modifiers changed from: protected */
    public CursorWrapper closeWithCursor(final Closeable closeable, Cursor cursor) {
        return new CursorWrapper(cursor) {
            public void close() {
                super.close();
                Closeable closeable = closeable;
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException unused) {
                    }
                }
            }
        };
    }

    public Connection getConnection() throws SQLException {
        return this.connection;
    }

    public int getDatabaseMajorVersion() throws SQLException {
        return Integer.parseInt(getDatabaseProductVersion().split(".")[0]);
    }

    public int getDatabaseMinorVersion() throws SQLException {
        return Integer.parseInt(getDatabaseProductVersion().split(".")[1]);
    }

    public String getDatabaseProductVersion() throws SQLException {
        return (String) queryMemory(new Function<Cursor, String>() {
            public String apply(Cursor cursor) {
                return cursor.moveToNext() ? cursor.getString(0) : "";
            }
        }, "select sqlite_version() AS sqlite_version");
    }

    public ResultSet getTables(String str, String str2, String str3, String[] strArr) throws SQLException {
        if (strArr == null) {
            strArr = new String[]{"TABLE", "VIEW"};
        }
        if (str3 == null) {
            str3 = "%";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("TABLE_CAT", null);
        linkedHashMap.put("TABLE_SCHEM", null);
        linkedHashMap.put("TABLE_NAME", "name");
        linkedHashMap.put("TABLE_TYPE", "type");
        linkedHashMap.put("REMARKS", null);
        linkedHashMap.put("TYPE_CAT", null);
        linkedHashMap.put("TYPE_SCHEM", null);
        linkedHashMap.put("TYPE_NAME", null);
        linkedHashMap.put("SELF_REFERENCING_COL_NAME", null);
        linkedHashMap.put("REF_GENERATION", null);
        Options options = new Options(getIdentifierQuoteString(), true, null, null, false, false);
        return (ResultSet) queryMemory(new Function<Cursor, ResultSet>() {
            public ResultSet apply(Cursor cursor) {
                return new CursorResultSet(null, cursor, true);
            }
        }, new QueryBuilder(options).keyword(Keyword.SELECT).commaSeparated((Iterable<? extends T>) linkedHashMap.entrySet(), (Appender<T>) new Appender<Entry<String, String>>() {
            public void append(QueryBuilder queryBuilder, Entry<String, String> entry) {
                queryBuilder.append(entry.getValue() == null ? "null" : (String) entry.getValue()).append(" as ").append(entry.getKey());
            }
        }).keyword(Keyword.FROM).openParenthesis().append("select name, type from sqlite_master").closeParenthesis().keyword(Keyword.WHERE).append(" TABLE_NAME like ").append(str3).append(" && TABLE_TYPE in ").openParenthesis().commaSeparated(Arrays.asList(strArr)).closeParenthesis().keyword(Keyword.ORDER, Keyword.BY).append(" TABLE_TYPE, TABLE_NAME").toString());
    }

    public ResultSet getTableTypes() throws SQLException {
        return (ResultSet) queryMemory(new Function<Cursor, ResultSet>() {
            public ResultSet apply(Cursor cursor) {
                return new CursorResultSet(null, cursor, true);
            }
        }, "select 'TABLE' as TABLE_TYPE, 'VIEW' as TABLE_TYPE");
    }

    public boolean isReadOnly() throws SQLException {
        return this.connection.isReadOnly();
    }

    public RowIdLifetime getRowIdLifetime() throws SQLException {
        return RowIdLifetime.ROWID_UNSUPPORTED;
    }
}
