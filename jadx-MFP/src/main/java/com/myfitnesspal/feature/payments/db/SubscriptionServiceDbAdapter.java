package com.myfitnesspal.feature.payments.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription.SubscriptionStatus;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.MfpProductFeature;
import com.myfitnesspal.feature.payments.model.PaymentReceipt;
import com.myfitnesspal.shared.db.table.MfpDatabaseTable;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.database.SQLiteDatabaseWrapperFactory;
import com.uacf.core.database.SQLiteDatabaseWrapperOpenHelper;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function0;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionServiceDbAdapter {
    private SQLiteDatabaseWrapper db = this.dbHelper.getWritableDatabaseWrapper();
    private SQLiteDatabaseWrapperOpenHelper dbHelper;
    private PaidSubscriptionsTable paidSubscriptionsTable;
    private ReceiptsTable receiptsTable;
    private Lazy<Session> session;

    private static class DatabaseOpenHelper extends SQLiteDatabaseWrapperOpenHelper {
        private static final String FILENAME = "subscriptions.db";
        private static final int VERSION = 5;
        private Lazy<Session> session;

        private DatabaseOpenHelper(Context context, Lazy<Session> lazy) {
            super(context, FILENAME, null, 5);
            this.session = lazy;
        }

        private MfpDatabaseTable[] allTables(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
            return new MfpDatabaseTable[]{new ReceiptsTable(sQLiteDatabaseWrapper, this.session), new PaidSubscriptionsTable(sQLiteDatabaseWrapper)};
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            for (MfpDatabaseTable onCreate : allTables(SQLiteDatabaseWrapperFactory.wrap(sQLiteDatabase))) {
                onCreate.onCreate();
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            for (MfpDatabaseTable onUpgrade : allTables(SQLiteDatabaseWrapperFactory.wrap(sQLiteDatabase))) {
                onUpgrade.onUpgrade(i, i2);
            }
        }
    }

    public SubscriptionServiceDbAdapter(Context context, Lazy<Session> lazy) {
        this.dbHelper = new DatabaseOpenHelper(context, lazy);
        this.receiptsTable = new ReceiptsTable(this.db, lazy);
        this.paidSubscriptionsTable = new PaidSubscriptionsTable(this.db);
        this.session = lazy;
    }

    public List<MfpPaidSubscription> getSubscriptions() {
        return getSubscriptions(null);
    }

    public List<MfpPaidSubscription> getSubscriptions(ReturningFunction1<Boolean, MfpPaidSubscription> returningFunction1) {
        List<MfpPaidSubscription> queryAllSubscriptions = queryAllSubscriptions();
        return returningFunction1 != null ? (List) Enumerable.where((Collection<T>) queryAllSubscriptions, returningFunction1) : queryAllSubscriptions;
    }

    public Map<String, MfpProductFeature> getPaidFeatures() {
        HashMap hashMap = new HashMap();
        for (MfpPaidSubscription mfpPaidSubscription : getSubscriptions()) {
            if (mfpPaidSubscription.getSubscriptionStatus() == SubscriptionStatus.ACTIVE) {
                for (MfpProductFeature mfpProductFeature : mfpPaidSubscription.getSubscriptionFeatures()) {
                    hashMap.put(mfpProductFeature.getFeatureId(), mfpProductFeature);
                }
            }
        }
        for (PaymentReceipt paymentReceipt : getPendingReceipts()) {
            if (Strings.notEmpty(paymentReceipt.getPayload())) {
                MfpProduct product = paymentReceipt.getProduct();
                if (product != null) {
                    for (MfpProductFeature mfpProductFeature2 : product.getProductFeatures()) {
                        hashMap.put(mfpProductFeature2.getFeatureId(), mfpProductFeature2);
                    }
                }
            }
        }
        return hashMap;
    }

    public boolean addOrUpdateSubscription(MfpPaidSubscription mfpPaidSubscription) {
        return this.paidSubscriptionsTable.addOrUpdate(mfpPaidSubscription, this.session);
    }

    public boolean removeSubscription(String str) {
        return this.paidSubscriptionsTable.deleteBySubscriptionId(str);
    }

    public int setSubscriptions(String str, List<MfpPaidSubscription> list) {
        DatabaseUtil.ensureDatabaseTransaction(this.db, new Function0(str, list) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ List f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void execute() {
                SubscriptionServiceDbAdapter.lambda$setSubscriptions$0(SubscriptionServiceDbAdapter.this, this.f$1, this.f$2);
            }
        });
        return 0;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<com.myfitnesspal.feature.payments.model.MfpPaidSubscription>, for r3v0, types: [java.util.List, java.util.List<com.myfitnesspal.feature.payments.model.MfpPaidSubscription>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$setSubscriptions$0(com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter r1, java.lang.String r2, java.util.List<com.myfitnesspal.feature.payments.model.MfpPaidSubscription> r3) throws java.lang.RuntimeException {
        /*
            com.myfitnesspal.feature.payments.db.PaidSubscriptionsTable r0 = r1.paidSubscriptionsTable
            r0.reset(r2)
            java.util.Iterator r2 = r3.iterator()
        L_0x0009:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0019
            java.lang.Object r3 = r2.next()
            com.myfitnesspal.feature.payments.model.MfpPaidSubscription r3 = (com.myfitnesspal.feature.payments.model.MfpPaidSubscription) r3
            r1.addOrUpdateSubscription(r3)
            goto L_0x0009
        L_0x0019:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter.lambda$setSubscriptions$0(com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter, java.lang.String, java.util.List):void");
    }

    public boolean addReceipt(MfpProduct mfpProduct, String str, String str2, String str3, String str4, String str5) {
        return this.receiptsTable.insert(mfpProduct, str, str2, str3, str4, str5, null);
    }

    public boolean finalizeReceipt(PaymentReceipt paymentReceipt) {
        return this.receiptsTable.softDelete(paymentReceipt);
    }

    public List<PaymentReceipt> getPendingReceipts() {
        return this.receiptsTable.queryPending();
    }

    public PaymentReceipt findReceiptByPaymentSessionId(String str) {
        return this.receiptsTable.queryByPaymentSessionId(str);
    }

    public boolean updateReceiptPayload(String str, String str2) {
        return this.receiptsTable.updatePaymentReceiptPayload(str, str2) > 0;
    }

    public List<MfpPaidSubscription> queryAllSubscriptions() {
        return this.paidSubscriptionsTable.query(this.session);
    }

    /* access modifiers changed from: protected */
    public PaidSubscriptionsTable getDatabaseTable() {
        return this.paidSubscriptionsTable;
    }

    /* access modifiers changed from: protected */
    public Session getSession() {
        return (Session) this.session.get();
    }
}
