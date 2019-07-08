package com.liulishuo.filedownloader.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.SparseArray;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadHelper.DatabaseCustomMaker;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.myfitnesspal.shared.db.table.ProfileImagesTable.Columns;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SqliteDatabaseImpl implements FileDownloadDatabase {
    /* access modifiers changed from: private */
    public final SQLiteDatabase db = new SqliteDatabaseOpenHelper(FileDownloadHelper.getAppContext()).getWritableDatabase();

    public class Maintainer implements com.liulishuo.filedownloader.database.FileDownloadDatabase.Maintainer {
        private final SparseArray<List<ConnectionModel>> connectionModelListMap;
        private MaintainerIterator currentIterator;
        private final SparseArray<FileDownloadModel> downloaderModelMap;
        private final SparseArray<FileDownloadModel> needChangeIdList;

        public void onRemovedInvalidData(FileDownloadModel fileDownloadModel) {
        }

        Maintainer(SqliteDatabaseImpl sqliteDatabaseImpl) {
            this(null, null);
        }

        Maintainer(SparseArray<FileDownloadModel> sparseArray, SparseArray<List<ConnectionModel>> sparseArray2) {
            this.needChangeIdList = new SparseArray<>();
            this.downloaderModelMap = sparseArray;
            this.connectionModelListMap = sparseArray2;
        }

        public Iterator<FileDownloadModel> iterator() {
            MaintainerIterator maintainerIterator = new MaintainerIterator();
            this.currentIterator = maintainerIterator;
            return maintainerIterator;
        }

        public void onFinishMaintain() {
            MaintainerIterator maintainerIterator = this.currentIterator;
            if (maintainerIterator != null) {
                maintainerIterator.onFinishMaintain();
            }
            int size = this.needChangeIdList.size();
            if (size >= 0) {
                SqliteDatabaseImpl.this.db.beginTransaction();
                int i = 0;
                while (i < size) {
                    try {
                        int keyAt = this.needChangeIdList.keyAt(i);
                        FileDownloadModel fileDownloadModel = (FileDownloadModel) this.needChangeIdList.get(keyAt);
                        SqliteDatabaseImpl.this.db.delete("filedownloader", "_id = ?", new String[]{String.valueOf(keyAt)});
                        SqliteDatabaseImpl.this.db.insert("filedownloader", null, fileDownloadModel.toContentValues());
                        if (fileDownloadModel.getConnectionCount() > 1) {
                            List<ConnectionModel> findConnectionModel = SqliteDatabaseImpl.this.findConnectionModel(keyAt);
                            if (findConnectionModel.size() > 0) {
                                SqliteDatabaseImpl.this.db.delete("filedownloaderConnection", "id = ?", new String[]{String.valueOf(keyAt)});
                                for (ConnectionModel connectionModel : findConnectionModel) {
                                    connectionModel.setId(fileDownloadModel.getId());
                                    SqliteDatabaseImpl.this.db.insert("filedownloaderConnection", null, connectionModel.toContentValues());
                                }
                            }
                        }
                        i++;
                    } catch (Throwable th) {
                        SqliteDatabaseImpl.this.db.endTransaction();
                        throw th;
                    }
                }
                if (!(this.downloaderModelMap == null || this.connectionModelListMap == null)) {
                    int size2 = this.downloaderModelMap.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        int id = ((FileDownloadModel) this.downloaderModelMap.valueAt(i2)).getId();
                        List findConnectionModel2 = SqliteDatabaseImpl.this.findConnectionModel(id);
                        if (findConnectionModel2 != null && findConnectionModel2.size() > 0) {
                            this.connectionModelListMap.put(id, findConnectionModel2);
                        }
                    }
                }
                SqliteDatabaseImpl.this.db.setTransactionSuccessful();
                SqliteDatabaseImpl.this.db.endTransaction();
            }
        }

        public void onRefreshedValidData(FileDownloadModel fileDownloadModel) {
            SparseArray<FileDownloadModel> sparseArray = this.downloaderModelMap;
            if (sparseArray != null) {
                sparseArray.put(fileDownloadModel.getId(), fileDownloadModel);
            }
        }

        public void changeFileDownloadModelId(int i, FileDownloadModel fileDownloadModel) {
            this.needChangeIdList.put(i, fileDownloadModel);
        }
    }

    class MaintainerIterator implements Iterator<FileDownloadModel> {
        private final Cursor c;
        private int currentId;
        private final List<Integer> needRemoveId = new ArrayList();

        MaintainerIterator() {
            this.c = SqliteDatabaseImpl.this.db.rawQuery("SELECT * FROM filedownloader", null);
        }

        public boolean hasNext() {
            return this.c.moveToNext();
        }

        public FileDownloadModel next() {
            FileDownloadModel access$100 = SqliteDatabaseImpl.createFromCursor(this.c);
            this.currentId = access$100.getId();
            return access$100;
        }

        public void remove() {
            this.needRemoveId.add(Integer.valueOf(this.currentId));
        }

        /* access modifiers changed from: 0000 */
        public void onFinishMaintain() {
            this.c.close();
            if (!this.needRemoveId.isEmpty()) {
                String join = TextUtils.join(", ", this.needRemoveId);
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "delete %s", join);
                }
                SqliteDatabaseImpl.this.db.execSQL(FileDownloadUtils.formatString("DELETE FROM %s WHERE %s IN (%s);", "filedownloader", "_id", join));
                SqliteDatabaseImpl.this.db.execSQL(FileDownloadUtils.formatString("DELETE FROM %s WHERE %s IN (%s);", "filedownloaderConnection", "id", join));
            }
        }
    }

    public static class Maker implements DatabaseCustomMaker {
        public FileDownloadDatabase customMake() {
            return new SqliteDatabaseImpl();
        }
    }

    public void onTaskStart(int i) {
    }

    public void updatePending(int i) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.liulishuo.filedownloader.model.FileDownloadModel find(int r9) {
        /*
            r8 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r8.db     // Catch:{ all -> 0x003d }
            java.lang.String r2 = "SELECT * FROM %s WHERE %s = ?"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x003d }
            java.lang.String r4 = "filedownloader"
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x003d }
            java.lang.String r4 = "_id"
            r6 = 1
            r3[r6] = r4     // Catch:{ all -> 0x003d }
            java.lang.String r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r2, r3)     // Catch:{ all -> 0x003d }
            java.lang.String[] r3 = new java.lang.String[r6]     // Catch:{ all -> 0x003d }
            java.lang.String r9 = java.lang.Integer.toString(r9)     // Catch:{ all -> 0x003d }
            r3[r5] = r9     // Catch:{ all -> 0x003d }
            android.database.Cursor r9 = r1.rawQuery(r2, r3)     // Catch:{ all -> 0x003d }
            boolean r1 = r9.moveToNext()     // Catch:{ all -> 0x0038 }
            if (r1 == 0) goto L_0x0032
            com.liulishuo.filedownloader.model.FileDownloadModel r0 = createFromCursor(r9)     // Catch:{ all -> 0x0038 }
            if (r9 == 0) goto L_0x0031
            r9.close()
        L_0x0031:
            return r0
        L_0x0032:
            if (r9 == 0) goto L_0x0037
            r9.close()
        L_0x0037:
            return r0
        L_0x0038:
            r0 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
            goto L_0x003e
        L_0x003d:
            r9 = move-exception
        L_0x003e:
            if (r0 == 0) goto L_0x0043
            r0.close()
        L_0x0043:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.database.SqliteDatabaseImpl.find(int):com.liulishuo.filedownloader.model.FileDownloadModel");
    }

    /* JADX INFO: finally extract failed */
    public List<ConnectionModel> findConnectionModel(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor rawQuery = this.db.rawQuery(FileDownloadUtils.formatString("SELECT * FROM %s WHERE %s = ?", "filedownloaderConnection", "id"), new String[]{Integer.toString(i)});
            while (rawQuery.moveToNext()) {
                ConnectionModel connectionModel = new ConnectionModel();
                connectionModel.setId(i);
                connectionModel.setIndex(rawQuery.getInt(rawQuery.getColumnIndex("connectionIndex")));
                connectionModel.setStartOffset(rawQuery.getLong(rawQuery.getColumnIndex("startOffset")));
                connectionModel.setCurrentOffset(rawQuery.getLong(rawQuery.getColumnIndex("currentOffset")));
                connectionModel.setEndOffset(rawQuery.getLong(rawQuery.getColumnIndex("endOffset")));
                arrayList.add(connectionModel);
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void removeConnections(int i) {
        SQLiteDatabase sQLiteDatabase = this.db;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM filedownloaderConnection WHERE id = ");
        sb.append(i);
        sQLiteDatabase.execSQL(sb.toString());
    }

    public void insertConnectionModel(ConnectionModel connectionModel) {
        this.db.insert("filedownloaderConnection", null, connectionModel.toContentValues());
    }

    public void updateConnectionModel(int i, int i2, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("currentOffset", Long.valueOf(j));
        this.db.update("filedownloaderConnection", contentValues, "id = ? AND connectionIndex = ?", new String[]{Integer.toString(i), Integer.toString(i2)});
    }

    public void updateConnectionCount(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("connectionCount", Integer.valueOf(i2));
        this.db.update("filedownloader", contentValues, "_id = ? ", new String[]{Integer.toString(i)});
    }

    public void insert(FileDownloadModel fileDownloadModel) {
        this.db.insert("filedownloader", null, fileDownloadModel.toContentValues());
    }

    public void update(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            FileDownloadLog.w(this, "update but model == null!", new Object[0]);
            return;
        }
        if (find(fileDownloadModel.getId()) != null) {
            this.db.update("filedownloader", fileDownloadModel.toContentValues(), "_id = ? ", new String[]{String.valueOf(fileDownloadModel.getId())});
        } else {
            insert(fileDownloadModel);
        }
    }

    public boolean remove(int i) {
        if (this.db.delete("filedownloader", "_id = ?", new String[]{String.valueOf(i)}) != 0) {
            return true;
        }
        return false;
    }

    public void clear() {
        this.db.delete("filedownloader", null, null);
        this.db.delete("filedownloaderConnection", null, null);
    }

    public void updateOldEtagOverdue(int i, String str, long j, long j2, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sofar", Long.valueOf(j));
        contentValues.put("total", Long.valueOf(j2));
        contentValues.put("etag", str);
        contentValues.put("connectionCount", Integer.valueOf(i2));
        update(i, contentValues);
    }

    public void updateConnected(int i, long j, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Byte.valueOf(2));
        contentValues.put("total", Long.valueOf(j));
        contentValues.put("etag", str);
        contentValues.put(Columns.FILENAME, str2);
        update(i, contentValues);
    }

    public void updateProgress(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Byte.valueOf(3));
        contentValues.put("sofar", Long.valueOf(j));
        update(i, contentValues);
    }

    public void updateError(int i, Throwable th, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("errMsg", th.toString());
        contentValues.put("status", Byte.valueOf(-1));
        contentValues.put("sofar", Long.valueOf(j));
        update(i, contentValues);
    }

    public void updateRetry(int i, Throwable th) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("errMsg", th.toString());
        contentValues.put("status", Byte.valueOf(5));
        update(i, contentValues);
    }

    public void updateCompleted(int i, long j) {
        remove(i);
    }

    public void updatePause(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Byte.valueOf(-2));
        contentValues.put("sofar", Long.valueOf(j));
        update(i, contentValues);
    }

    public com.liulishuo.filedownloader.database.FileDownloadDatabase.Maintainer maintainer() {
        return new Maintainer(this);
    }

    public com.liulishuo.filedownloader.database.FileDownloadDatabase.Maintainer maintainer(SparseArray<FileDownloadModel> sparseArray, SparseArray<List<ConnectionModel>> sparseArray2) {
        return new Maintainer(sparseArray, sparseArray2);
    }

    private void update(int i, ContentValues contentValues) {
        this.db.update("filedownloader", contentValues, "_id = ? ", new String[]{String.valueOf(i)});
    }

    /* access modifiers changed from: private */
    public static FileDownloadModel createFromCursor(Cursor cursor) {
        FileDownloadModel fileDownloadModel = new FileDownloadModel();
        fileDownloadModel.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        fileDownloadModel.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        String string = cursor.getString(cursor.getColumnIndex("path"));
        boolean z = true;
        if (cursor.getShort(cursor.getColumnIndex("pathAsDirectory")) != 1) {
            z = false;
        }
        fileDownloadModel.setPath(string, z);
        fileDownloadModel.setStatus((byte) cursor.getShort(cursor.getColumnIndex("status")));
        fileDownloadModel.setSoFar(cursor.getLong(cursor.getColumnIndex("sofar")));
        fileDownloadModel.setTotal(cursor.getLong(cursor.getColumnIndex("total")));
        fileDownloadModel.setErrMsg(cursor.getString(cursor.getColumnIndex("errMsg")));
        fileDownloadModel.setETag(cursor.getString(cursor.getColumnIndex("etag")));
        fileDownloadModel.setFilename(cursor.getString(cursor.getColumnIndex(Columns.FILENAME)));
        fileDownloadModel.setConnectionCount(cursor.getInt(cursor.getColumnIndex("connectionCount")));
        return fileDownloadModel;
    }
}
