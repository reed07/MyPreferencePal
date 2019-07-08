package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import com.myfitnesspal.shared.db.Dataset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstalledDatasetsDBAdapter {
    private static final String INSTALLED_DATASETS_TABLE = "installed_datasets";
    private static final String KEY_DATASET_ID = "dataset_id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IDENTIFIER = "identifier";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_TYPE = "type";
    private final Context context;

    public InstalledDatasetsDBAdapter(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0082, code lost:
        if (r3 != null) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008d, code lost:
        if (r3 == null) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008f, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0092, code lost:
        installDatasetProperties(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0095, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.myfitnesspal.shared.db.Dataset> fetchInstalledDatasets() {
        /*
            r17 = this;
            r1 = r17
            java.util.ArrayList r2 = new java.util.ArrayList
            r0 = 3
            r2.<init>(r0)
            r3 = 0
            java.lang.String r4 = "dataset_id"
            java.lang.String r5 = "identifier"
            java.lang.String r6 = "type"
            java.lang.String r7 = "description"
            java.lang.String r8 = "priority"
            java.lang.String[] r11 = new java.lang.String[]{r4, r5, r6, r7, r8}     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            android.content.Context r4 = r1.context     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            com.uacf.core.database.SQLiteDatabaseWrapper r9 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r4)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            java.lang.String r10 = "installed_datasets"
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            android.database.Cursor r3 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            if (r3 == 0) goto L_0x0082
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            if (r4 == 0) goto L_0x0082
            r4 = 0
            r5 = 0
        L_0x0033:
            int r6 = r3.getCount()     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            if (r5 >= r6) goto L_0x0082
            com.myfitnesspal.shared.db.Dataset r6 = new com.myfitnesspal.shared.db.Dataset     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r6.<init>()     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            int r7 = r3.getInt(r4)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r6.setDatasetId(r7)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r7 = 1
            java.lang.String r8 = r3.getString(r7)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r6.setIdentifier(r8)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r8 = 2
            java.lang.String r9 = r3.getString(r8)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            java.lang.String r10 = "stock"
            boolean r10 = r9.equals(r10)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            if (r10 == 0) goto L_0x005e
            r6.setType(r7)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            goto L_0x006d
        L_0x005e:
            java.lang.String r7 = "purchased"
            boolean r7 = r9.equals(r7)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            if (r7 == 0) goto L_0x006a
            r6.setType(r8)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            goto L_0x006d
        L_0x006a:
            r6.setType(r8)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
        L_0x006d:
            java.lang.String r7 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r6.setDescription(r7)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r7 = 4
            int r7 = r3.getInt(r7)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r6.setPriority(r7)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            r2.add(r6)     // Catch:{ SQLiteException -> 0x0089, NumberFormatException -> 0x0087 }
            int r5 = r5 + 1
            goto L_0x0033
        L_0x0082:
            if (r3 == 0) goto L_0x0092
            goto L_0x008f
        L_0x0085:
            r0 = move-exception
            goto L_0x0096
        L_0x0087:
            r0 = move-exception
            goto L_0x008a
        L_0x0089:
            r0 = move-exception
        L_0x008a:
            com.uacf.core.util.Ln.e(r0)     // Catch:{ all -> 0x0085 }
            if (r3 == 0) goto L_0x0092
        L_0x008f:
            r3.close()
        L_0x0092:
            r1.installDatasetProperties(r2)
            return r2
        L_0x0096:
            if (r3 == 0) goto L_0x009b
            r3.close()
        L_0x009b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.InstalledDatasetsDBAdapter.fetchInstalledDatasets():java.util.ArrayList");
    }

    private void installDatasetProperties(List<Dataset> list) {
        ArrayList arrayList = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(254), Integer.valueOf(253), Integer.valueOf(14), Integer.valueOf(43)}));
        for (Dataset dataset : list) {
            dataset.setCountryIds(arrayList);
            dataset.setDescription("Stock Dataset");
            dataset.setPriority(1);
        }
    }
}
