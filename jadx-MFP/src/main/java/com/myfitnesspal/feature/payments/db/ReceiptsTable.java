package com.myfitnesspal.feature.payments.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.brightcove.player.event.EventType;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.PaymentReceipt;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableImpl;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceiptsTable extends MfpDatabaseTableImpl {
    private static final int LAST_PRE_RELEASE_DB_VERSION = 3;
    private static final int LAST_VERSION_WITHOUT_ORDER_ID = 4;
    private static final String TABLE_NAME = "receipt";
    private ApiJsonMapper jsonMapper = new ApiJsonMapper();
    private Lazy<Session> session;

    private interface Columns {
        public static final String COUNTRY_CODE = "country_code";
        public static final String COUPON = "coupon";
        public static final String CREATION_DATE = "creation_date";
        public static final String DELETED = "deleted";
        public static final String ID = "_id";
        public static final String ORDER_ID = "order_id";
        public static final String PAYMENT_PROVIDER = "payment_provider";
        public static final String PAYMENT_SESSION_ID = "payment_session_id";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_JSON = "product_json";
        public static final String PRODUCT_JSON_VERSION = "product_json_version";
        public static final String RECEIPT = "payload";
        public static final String USER = "user";
    }

    private static class InternalPaymentReceipt extends PaymentReceipt {
        private long id;

        InternalPaymentReceipt(long j, MfpProduct mfpProduct, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.id = j;
            this.product = mfpProduct;
            this.paymentSessionId = str;
            this.productId = str2;
            this.paymentProvider = str3;
            this.payload = str4;
            this.coupon = str6;
            this.orderId = str5;
            this.countryCode = str7;
        }

        public long getId() {
            return this.id;
        }
    }

    public ReceiptsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper, Lazy<Session> lazy) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
        this.session = lazy;
    }

    public void onCreate() {
        createTable("_id integer primary key autoincrement", "creation_date text", "payment_session_id text not null", "product_id text not null", "product_json text not null", "product_json_version integer", "payment_provider text not null", "payload text", "order_id text", "coupon text", "user text not null", "deleted integer", "country_code text not null");
    }

    public void onUpgrade(int i, int i2) {
        boolean z;
        if (i <= 3) {
            dropTable();
            onCreate();
            z = true;
        } else {
            z = false;
        }
        if (!z && i <= 4) {
            addColumn(Columns.ORDER_ID, "text");
        }
    }

    public List<PaymentReceipt> queryPending() {
        return parseReceiptsAndCloseCursor(super.queryData(new String[]{EventType.ANY}, String.format("%s=? AND %s=? AND coalesce(%s, '')!=''", new Object[]{"deleted", "user", "payload"}), "0", ((Session) this.session.get()).getUser().getUsername()));
    }

    public PaymentReceipt queryByPaymentSessionId(String str) {
        List parseReceiptsAndCloseCursor = parseReceiptsAndCloseCursor(super.queryData(new String[]{EventType.ANY}, String.format("%s=? AND %s=? AND %s=?", new Object[]{"deleted", "user", Columns.PAYMENT_SESSION_ID}), "0", ((Session) this.session.get()).getUser().getUsername(), str));
        if (parseReceiptsAndCloseCursor.size() > 0) {
            return (PaymentReceipt) parseReceiptsAndCloseCursor.get(0);
        }
        return null;
    }

    public int updatePaymentReceiptPayload(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("payload", str2);
        return updateData(contentValues, String.format("%s=? AND %s=?", new Object[]{"user", Columns.PAYMENT_SESSION_ID}), ((Session) this.session.get()).getUser().getUsername(), str);
    }

    private List<PaymentReceipt> parseReceiptsAndCloseCursor(Cursor cursor) {
        MfpProduct mfpProduct;
        Cursor cursor2 = cursor;
        ArrayList arrayList = new ArrayList();
        try {
            int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow2 = cursor2.getColumnIndexOrThrow(Columns.PAYMENT_SESSION_ID);
            int columnIndexOrThrow3 = cursor2.getColumnIndexOrThrow("product_id");
            int columnIndexOrThrow4 = cursor2.getColumnIndexOrThrow(Columns.PRODUCT_JSON);
            int columnIndexOrThrow5 = cursor2.getColumnIndexOrThrow(Columns.PRODUCT_JSON_VERSION);
            int columnIndexOrThrow6 = cursor2.getColumnIndexOrThrow("payment_provider");
            int columnIndexOrThrow7 = cursor2.getColumnIndexOrThrow("payload");
            int columnIndexOrThrow8 = cursor2.getColumnIndexOrThrow(Columns.ORDER_ID);
            int columnIndexOrThrow9 = cursor2.getColumnIndexOrThrow("coupon");
            int columnIndex = cursor2.getColumnIndex(Columns.COUNTRY_CODE);
            while (cursor.moveToNext()) {
                if (cursor2.getInt(columnIndexOrThrow5) == MfpProduct.getVersion()) {
                    try {
                        mfpProduct = (MfpProduct) this.jsonMapper.tryMapFrom(cursor2.getString(columnIndexOrThrow4), MfpProduct.class);
                    } catch (Throwable th) {
                        th = th;
                        cursor.close();
                        throw th;
                    }
                } else {
                    mfpProduct = null;
                }
                InternalPaymentReceipt internalPaymentReceipt = new InternalPaymentReceipt(cursor2.getLong(columnIndexOrThrow), mfpProduct, cursor2.getString(columnIndexOrThrow2), cursor2.getString(columnIndexOrThrow3), cursor2.getString(columnIndexOrThrow6), cursor2.getString(columnIndexOrThrow7), cursor2.getString(columnIndexOrThrow8), cursor2.getString(columnIndexOrThrow9), cursor2.getString(columnIndex));
                arrayList.add(internalPaymentReceipt);
            }
            cursor.close();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursor.close();
            throw th;
        }
    }

    public boolean insert(MfpProduct mfpProduct, String str, String str2, String str3, String str4, String str5, String str6) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.PRODUCT_JSON, this.jsonMapper.reverseMap((Object) mfpProduct));
        contentValues.put(Columns.PRODUCT_JSON_VERSION, Integer.valueOf(MfpProduct.getVersion()));
        contentValues.put(Columns.CREATION_DATE, DateTimeUtils.format(DATE_FORMAT, new Date()));
        contentValues.put("product_id", mfpProduct.getProductId());
        contentValues.put(Columns.PAYMENT_SESSION_ID, str);
        contentValues.put("payment_provider", str2);
        contentValues.put("payload", str4);
        contentValues.put(Columns.ORDER_ID, str5);
        contentValues.put("coupon", str6);
        contentValues.put("user", ((Session) this.session.get()).getUser().getUsername());
        contentValues.put(Columns.COUNTRY_CODE, str3);
        contentValues.put("deleted", Integer.valueOf(0));
        if (insertData(contentValues) != 0) {
            return true;
        }
        return false;
    }

    public boolean softDelete(PaymentReceipt paymentReceipt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", Integer.valueOf(1));
        if (updateData(contentValues, "_id=?", Long.valueOf(paymentReceipt.getId())) > 0) {
            return true;
        }
        return false;
    }
}
