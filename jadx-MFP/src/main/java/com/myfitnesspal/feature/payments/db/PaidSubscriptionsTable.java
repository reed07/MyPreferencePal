package com.myfitnesspal.feature.payments.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableImpl;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class PaidSubscriptionsTable extends MfpDatabaseTableImpl {
    private static final String TABLE_NAME = "paid_subscription";
    private static final String TAG = "PaidSubscriptionsTable";
    private ApiJsonMapper jsonMapper = new ApiJsonMapper();

    private interface Columns {
        public static final String ID = "_id";
        public static final String JSON_DATA = "json_data";
        public static final String JSON_VERSION = "json_version";
        public static final String PAYMENT_PROVIDER = "payment_provider";
        public static final String PRODUCT_ID = "product_id";
        public static final String STATUS = "status";
        public static final String SUBSCRIPTION_ID = "subscription_id";
        public static final String USER = "user";
    }

    public void onUpgrade(int i, int i2) {
    }

    @Inject
    public PaidSubscriptionsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "paid_subscription");
    }

    public void onCreate() {
        createTable("_id integer primary key autoincrement", "user text not null", "subscription_id text unique not null", "status text not null", "product_id text not null", "payment_provider text not null", "json_version integer", "json_data text not null");
    }

    public boolean addOrUpdate(MfpPaidSubscription mfpPaidSubscription, Lazy<Session> lazy) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SUBSCRIPTION_ID, mfpPaidSubscription.getSubscriptionId());
        contentValues.put("user", ((Session) lazy.get()).getUser().getUsername());
        boolean z = true;
        contentValues.put(Columns.JSON_VERSION, Integer.valueOf(1));
        contentValues.put(Columns.JSON_DATA, this.jsonMapper.reverseMap((Object) mfpPaidSubscription));
        contentValues.put("status", mfpPaidSubscription.getSubscriptionStatus() != null ? mfpPaidSubscription.getSubscriptionStatus().value() : null);
        contentValues.put("product_id", mfpPaidSubscription.getPaymentDetails().getProductId());
        contentValues.put("payment_provider", (String) mfpPaidSubscription.getPaymentDetails().getPlatformDetails().getPaymentProviders().get(0));
        if (count("subscription_id=?", mfpPaidSubscription.getSubscriptionId()) == 0) {
            if (insertData(contentValues) == 0) {
                z = false;
            }
            return z;
        }
        if (updateData(contentValues, "subscription_id=?", mfpPaidSubscription.getSubscriptionId()) <= 0) {
            z = false;
        }
        return z;
    }

    public boolean deleteBySubscriptionId(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SUBSCRIPTION_ID, str);
        if (deleteData("subscription_id=?", contentValues) > 0) {
            return true;
        }
        return false;
    }

    public void reset(String str) {
        deleteData("user=?", str);
    }

    public List<MfpPaidSubscription> query(Lazy<Session> lazy) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        Cursor queryData = queryData(new String[]{"_id", Columns.JSON_VERSION, Columns.JSON_DATA}, "user=?", ((Session) lazy.get()).getUser().getUsername());
        if (queryData != null) {
            try {
                if (queryData.getCount() > 0) {
                    int columnIndexOrThrow = queryData.getColumnIndexOrThrow("_id");
                    int columnIndexOrThrow2 = queryData.getColumnIndexOrThrow(Columns.JSON_VERSION);
                    int columnIndexOrThrow3 = queryData.getColumnIndexOrThrow(Columns.JSON_DATA);
                    while (queryData.moveToNext()) {
                        if (queryData.getInt(columnIndexOrThrow2) != 1) {
                            z = true;
                        } else {
                            try {
                                arrayList.add((MfpPaidSubscription) this.jsonMapper.mapFrom(queryData.getString(columnIndexOrThrow3), MfpPaidSubscription.class));
                                z = false;
                            } catch (IOException unused) {
                                z = true;
                            }
                        }
                        if (z) {
                            long j = queryData.getLong(columnIndexOrThrow);
                            Ln.e("[%s]: failed to parse subscription, deleting entry with id=%d", TAG, Long.valueOf(j));
                            deleteData("_id=?", Long.valueOf(j));
                        }
                    }
                }
            } catch (Throwable th) {
                if (queryData != null) {
                    queryData.close();
                }
                throw th;
            }
        }
        if (queryData != null) {
            queryData.close();
        }
        return arrayList;
    }
}
