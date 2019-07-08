package com.brightcove.player.store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.logging.Log;
import io.requery.BlockingEntityStore;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.meta.EntityModel;
import io.requery.meta.QueryAttribute;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveResult;
import io.requery.reactivex.ReactiveScalar;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.EntityDataStore;
import io.requery.util.function.Function;
import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

public class BaseStore {
    private static final long LENGTH_UNSET = -1;
    public static final int MAX_SQL_EXPRESSIONS = 500;
    public static final int MAX_SQL_VARIABLES = 500;
    /* access modifiers changed from: private */
    public static final String TAG = "BaseStore";
    protected final Context context;
    /* access modifiers changed from: protected */
    public final ReactiveEntityStore<Persistable> dataStore;
    /* access modifiers changed from: private */
    public final String name;
    protected final Source source;

    private class Source extends DatabaseSource {
        public Source(Context context, EntityModel entityModel, String str, @NonNull int i) {
            super(context, entityModel, str, i);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            super.onCreate(sQLiteDatabase);
            BaseStore.this.onCreated(sQLiteDatabase.getVersion());
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            super.onUpgrade(sQLiteDatabase, i, i2);
            BaseStore.this.onUpgraded(i, i2);
        }
    }

    protected BaseStore(@NonNull Context context2, @NonNull EntityModel entityModel, int i) {
        this(context2, entityModel, getDefaultDatabaseName(context2, entityModel), i);
    }

    protected BaseStore(@NonNull Context context2, @NonNull EntityModel entityModel, @NonNull String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            this.context = context2.getApplicationContext();
            this.name = str;
            Source source2 = new Source(context2.getApplicationContext(), entityModel, str, i);
            this.source = source2;
            this.dataStore = ReactiveSupport.toReactiveStore(new EntityDataStore(this.source.getConfiguration()));
            return;
        }
        throw new IllegalArgumentException("Database name is empty!");
    }

    public long getFileSize() {
        File databasePath = this.context.getDatabasePath(this.name);
        try {
            return databasePath.length();
        } catch (Exception e) {
            Log.e(TAG, "Failed to size of %s", e, databasePath.getAbsolutePath());
            return -1;
        }
    }

    public boolean compact() {
        return ((Boolean) this.dataStore.runInTransaction(new Function<BlockingEntityStore<Persistable>, Boolean>() {
            public Boolean apply(BlockingEntityStore<Persistable> blockingEntityStore) {
                try {
                    BaseStore.this.source.getConnection().createStatement().execute("VACUUM;");
                    return Boolean.valueOf(true);
                } catch (SQLException e) {
                    Log.e(BaseStore.TAG, "Failed to compact %s", e, BaseStore.this.name);
                    return Boolean.valueOf(false);
                }
            }
        }).blockingGet()).booleanValue();
    }

    @Nullable
    public <E extends IdentifiableEntity> E refreshEntity(@Nullable E e) {
        if (e == null) {
            return null;
        }
        return (IdentifiableEntity) ((ReactiveResult) this.dataStore.select(e.getClass(), new QueryAttribute[0]).where(e.getIdentityCondition()).get()).firstOrNull();
    }

    /* access modifiers changed from: protected */
    public void onCreated(int i) {
        Log.v(TAG, "Created %s version %d", this.name, Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void onUpgraded(int i, int i2) {
        Log.v(TAG, "Updated %s from %d to version %d", this.name, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public <T extends IdentifiableEntity> T saveEntity(@NonNull T t) {
        if (t.getKey() == null) {
            return (IdentifiableEntity) this.dataStore.insert(t).blockingGet();
        }
        return (IdentifiableEntity) this.dataStore.update(t).blockingGet();
    }

    public boolean deleteEntity(@Nullable IdentifiableEntity identifiableEntity) {
        boolean z = false;
        if (identifiableEntity == null) {
            return false;
        }
        if (((Integer) ((ReactiveScalar) this.dataStore.delete(identifiableEntity.getClass()).where(identifiableEntity.getIdentityCondition()).get()).value()).intValue() > 0) {
            z = true;
        }
        return z;
    }

    protected static String getDefaultDatabaseName(Context context2, EntityModel entityModel) {
        return TextUtils.isEmpty(entityModel.getName()) ? context2.getPackageName() : entityModel.getName();
    }

    @Nullable
    @SuppressLint({"HardwareIds"})
    protected static String getDefaultDatabasePassword(Context context2, EntityModel entityModel) {
        StringBuilder sb = new StringBuilder();
        sb.append(Secure.getString(context2.getContentResolver(), "android_id"));
        sb.append(context2.getPackageName());
        sb.append(getDefaultDatabaseName(context2, entityModel));
        return UUID.nameUUIDFromBytes(sb.toString().getBytes()).toString();
    }
}
