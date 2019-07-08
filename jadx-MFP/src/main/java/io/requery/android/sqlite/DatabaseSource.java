package io.requery.android.sqlite;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import io.requery.android.DefaultMapping;
import io.requery.android.LoggingListener;
import io.requery.meta.EntityModel;
import io.requery.sql.Configuration;
import io.requery.sql.ConfigurationBuilder;
import io.requery.sql.Mapping;
import io.requery.sql.Platform;
import io.requery.sql.SchemaModifier;
import io.requery.sql.TableCreationMode;
import io.requery.sql.platform.SQLite;
import io.requery.util.function.Function;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

public class DatabaseSource extends SQLiteOpenHelper implements DatabaseProvider<SQLiteDatabase> {
    private Configuration configuration;
    private boolean configured;
    private SQLiteDatabase db;
    private boolean loggingEnabled;
    private Mapping mapping;
    private TableCreationMode mode;
    private final EntityModel model;
    private final Platform platform;

    public /* bridge */ /* synthetic */ Object getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public /* bridge */ /* synthetic */ Object getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public DatabaseSource(Context context, EntityModel entityModel, int i) {
        this(context, entityModel, getDefaultDatabaseName(context, entityModel), null, i);
    }

    public DatabaseSource(Context context, EntityModel entityModel, @Nullable String str, int i) {
        this(context, entityModel, str, null, i);
    }

    public DatabaseSource(Context context, EntityModel entityModel, @Nullable String str, @Nullable CursorFactory cursorFactory, int i) {
        this(context, entityModel, str, cursorFactory, i, new SQLite());
    }

    public DatabaseSource(Context context, EntityModel entityModel, @Nullable String str, @Nullable CursorFactory cursorFactory, int i, SQLite sQLite) {
        super(context, str, cursorFactory, i);
        if (entityModel != null) {
            this.platform = sQLite;
            this.model = entityModel;
            this.mode = TableCreationMode.CREATE_NOT_EXISTS;
            return;
        }
        throw new IllegalArgumentException("null model");
    }

    public void setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
    }

    public void setTableCreationMode(TableCreationMode tableCreationMode) {
        this.mode = tableCreationMode;
    }

    private static String getDefaultDatabaseName(Context context, EntityModel entityModel) {
        return TextUtils.isEmpty(entityModel.getName()) ? context.getPackageName() : entityModel.getName();
    }

    /* access modifiers changed from: protected */
    public Mapping onCreateMapping(Platform platform2) {
        return new DefaultMapping(platform2);
    }

    /* access modifiers changed from: protected */
    public void onConfigure(ConfigurationBuilder configurationBuilder) {
        if (this.loggingEnabled) {
            configurationBuilder.addStatementListener(new LoggingListener());
        }
    }

    private Connection getConnection(SQLiteDatabase sQLiteDatabase) throws SQLException {
        SqliteConnection sqliteConnection;
        synchronized (this) {
            if (sQLiteDatabase.isOpen()) {
                sqliteConnection = new SqliteConnection(sQLiteDatabase);
            } else {
                throw new SQLNonTransientConnectionException();
            }
        }
        return sqliteConnection;
    }

    public Configuration getConfiguration() {
        if (this.mapping == null) {
            this.mapping = onCreateMapping(this.platform);
        }
        if (this.mapping != null) {
            if (this.configuration == null) {
                ConfigurationBuilder batchUpdateSize = new ConfigurationBuilder(this, this.model).setMapping(this.mapping).setPlatform(this.platform).setBatchUpdateSize(1000);
                onConfigure(batchUpdateSize);
                this.configuration = batchUpdateSize.build();
            }
            return this.configuration;
        }
        throw new IllegalStateException();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
        new SchemaModifier(getConfiguration()).createTables(TableCreationMode.CREATE);
    }

    @TargetApi(16)
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        super.onConfigure(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
        }
    }

    public void onUpgrade(final SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.db = sQLiteDatabase;
        new SchemaUpdater(getConfiguration(), new Function<String, Cursor>() {
            public Cursor apply(String str) {
                return sQLiteDatabase.rawQuery(str, null);
            }
        }, this.mode).update();
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        synchronized (this) {
            if (this.db == null) {
                this.db = getWritableDatabase();
            }
            if (!this.configured && VERSION.SDK_INT < 16) {
                this.db.execSQL("PRAGMA foreign_keys = ON");
                if (this.db.getPageSize() == 1024) {
                    this.db.setPageSize(4096);
                }
                this.configured = true;
            }
            connection = getConnection(this.db);
        }
        return connection;
    }
}
