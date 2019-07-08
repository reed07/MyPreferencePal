package io.requery.android.sqlite;

import android.database.Cursor;
import io.requery.meta.Attribute;
import io.requery.meta.Type;
import io.requery.sql.Configuration;
import io.requery.sql.SchemaModifier;
import io.requery.sql.TableCreationMode;
import io.requery.sql.TableModificationException;
import io.requery.util.function.Function;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class SchemaUpdater {
    private final Configuration configuration;
    private final TableCreationMode mode;
    private final Function<String, Cursor> queryFunction;

    public SchemaUpdater(Configuration configuration2, Function<String, Cursor> function, TableCreationMode tableCreationMode) {
        this.configuration = configuration2;
        this.queryFunction = function;
        if (tableCreationMode == null) {
            tableCreationMode = TableCreationMode.CREATE_NOT_EXISTS;
        }
        this.mode = tableCreationMode;
    }

    public void update() {
        Connection connection;
        Throwable th;
        SchemaModifier schemaModifier = new SchemaModifier(this.configuration);
        if (this.mode == TableCreationMode.DROP_CREATE) {
            schemaModifier.createTables(this.mode);
            return;
        }
        try {
            connection = schemaModifier.getConnection();
            connection.setAutoCommit(false);
            upgrade(connection, schemaModifier);
            connection.commit();
            if (connection != null) {
                connection.close();
                return;
            }
            return;
        } catch (SQLException e) {
            throw new TableModificationException(e);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private void upgrade(Connection connection, SchemaModifier schemaModifier) {
        schemaModifier.createTables(connection, this.mode, false);
        Function columnTransformer = this.configuration.getColumnTransformer();
        Function tableTransformer = this.configuration.getTableTransformer();
        ArrayList<Attribute> arrayList = new ArrayList<>();
        for (Type type : this.configuration.getModel().getTypes()) {
            if (!type.isView()) {
                String name = type.getName();
                if (tableTransformer != null) {
                    name = (String) tableTransformer.apply(name);
                }
                Function<String, Cursor> function = this.queryFunction;
                StringBuilder sb = new StringBuilder();
                sb.append("PRAGMA table_info(");
                sb.append(name);
                sb.append(")");
                Cursor cursor = (Cursor) function.apply(sb.toString());
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Attribute attribute : type.getAttributes()) {
                    if (!attribute.isAssociation() || attribute.isForeignKey()) {
                        if (columnTransformer == null) {
                            linkedHashMap.put(attribute.getName(), attribute);
                        } else {
                            linkedHashMap.put(columnTransformer.apply(attribute.getName()), attribute);
                        }
                    }
                }
                if (cursor.getCount() > 0) {
                    int columnIndex = cursor.getColumnIndex("name");
                    while (cursor.moveToNext()) {
                        linkedHashMap.remove(cursor.getString(columnIndex));
                    }
                }
                cursor.close();
                arrayList.addAll(linkedHashMap.values());
            }
        }
        Collections.sort(arrayList, new Comparator<Attribute>() {
            public int compare(Attribute attribute, Attribute attribute2) {
                if (!attribute.isForeignKey() || !attribute2.isForeignKey()) {
                    return attribute.isForeignKey() ? 1 : -1;
                }
                return 0;
            }
        });
        for (Attribute attribute2 : arrayList) {
            schemaModifier.addColumn(connection, attribute2, false);
            if (attribute2.isUnique() && !attribute2.isIndexed()) {
                schemaModifier.createIndex(connection, attribute2, this.mode);
            }
        }
        schemaModifier.createIndexes(connection, this.mode);
    }
}
