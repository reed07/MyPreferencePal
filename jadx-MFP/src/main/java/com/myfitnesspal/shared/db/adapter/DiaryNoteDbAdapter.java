package com.myfitnesspal.shared.db.adapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.Ln;

public class DiaryNoteDbAdapter extends SessionDBAdapter {
    public void insertOrUpdateDiaryNote(DiaryNote diaryNote) {
        try {
            User user = getSession().getUser();
            SQLiteStatement preparedStatement = DbConnectionManager.preparedStatement(31);
            String encodeDate = Database.encodeDate(diaryNote.getEntryDate());
            preparedStatement.bindLong(1, user.getLocalId());
            preparedStatement.bindString(2, encodeDate);
            preparedStatement.bindLong(3, (long) diaryNote.getNoteType());
            preparedStatement.execute();
            preparedStatement.clearBindings();
            String encodeDate2 = Database.encodeDate(diaryNote.getEntryDate());
            SQLiteStatement preparedStatement2 = DbConnectionManager.preparedStatement(32);
            if (diaryNote.hasMasterDatabaseId()) {
                preparedStatement2.bindLong(1, diaryNote.getMasterDatabaseId());
            } else {
                preparedStatement2.bindNull(1);
            }
            if (diaryNote.hasUid()) {
                preparedStatement2.bindString(2, diaryNote.getUid());
            } else {
                preparedStatement2.bindNull(2);
            }
            preparedStatement2.bindLong(3, user.getLocalId());
            preparedStatement2.bindString(4, encodeDate2);
            preparedStatement2.bindLong(5, (long) diaryNote.getNoteType());
            preparedStatement2.bindString(6, diaryNote.getBody());
            diaryNote.setLocalId(preparedStatement2.executeInsert());
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void eraseDiaryNoteWithLocalId(long j) {
        try {
            SQLiteStatement preparedStatement = DbConnectionManager.preparedStatement(33);
            preparedStatement.bindLong(1, getSession().getUser().getLocalId());
            preparedStatement.bindLong(2, j);
            preparedStatement.execute();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r5v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0041  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.DiaryNote fetchDiaryNoteById(long r5) {
        /*
            r4 = this;
            r0 = 34
            r1 = 0
            java.lang.String r0 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r0)     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r3 = 0
            java.lang.String r5 = java.lang.Long.toString(r5)     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            r2[r3] = r5     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            com.uacf.core.database.SQLiteDatabaseWrapper r5 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r1)     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            android.database.Cursor r5 = r5.rawQuery(r0, r2)     // Catch:{ Exception -> 0x0032, all -> 0x0030 }
            boolean r6 = r5.moveToFirst()     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x0028
            com.myfitnesspal.shared.model.v1.DiaryNote r6 = new com.myfitnesspal.shared.model.v1.DiaryNote     // Catch:{ Exception -> 0x002e }
            r6.<init>()     // Catch:{ Exception -> 0x002e }
            com.myfitnesspal.shared.model.v1.DiaryNote r1 = r4.initDiaryNote(r6, r5)     // Catch:{ Exception -> 0x002e }
        L_0x0028:
            if (r5 == 0) goto L_0x002d
            r5.close()
        L_0x002d:
            return r1
        L_0x002e:
            r6 = move-exception
            goto L_0x0034
        L_0x0030:
            r6 = move-exception
            goto L_0x003f
        L_0x0032:
            r6 = move-exception
            r5 = r1
        L_0x0034:
            com.uacf.core.util.Ln.e(r6)     // Catch:{ all -> 0x003d }
            if (r5 == 0) goto L_0x003c
            r5.close()
        L_0x003c:
            return r1
        L_0x003d:
            r6 = move-exception
            r1 = r5
        L_0x003f:
            if (r1 == 0) goto L_0x0044
            r1.close()
        L_0x0044:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.DiaryNoteDbAdapter.fetchDiaryNoteById(long):com.myfitnesspal.shared.model.v1.DiaryNote");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.myfitnesspal.shared.model.v1.DiaryNote> fetchDiaryNotesOnDate(java.util.Date r7) {
        /*
            r6 = this;
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            r2 = 2
            r1.<init>(r2)     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            com.myfitnesspal.shared.service.session.Session r3 = r6.getSession()     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            com.myfitnesspal.shared.model.User r3 = r3.getUser()     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            long r3 = r3.getLocalId()     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            r4 = 0
            r2[r4] = r3     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            java.lang.String r7 = com.myfitnesspal.shared.util.Database.encodeDate(r7)     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            r3 = 1
            r2[r3] = r7     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            r7 = 35
            java.lang.String r7 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r7)     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            com.uacf.core.database.SQLiteDatabaseWrapper r3 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r0)     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            android.database.Cursor r7 = r3.rawQuery(r7, r2)     // Catch:{ Exception -> 0x0059, all -> 0x0057 }
            int r2 = r7.getCount()     // Catch:{ Exception -> 0x0055 }
            boolean r3 = r7.moveToFirst()     // Catch:{ Exception -> 0x0055 }
            if (r3 == 0) goto L_0x004f
        L_0x003b:
            if (r4 >= r2) goto L_0x004f
            com.myfitnesspal.shared.model.v1.DiaryNote r3 = new com.myfitnesspal.shared.model.v1.DiaryNote     // Catch:{ Exception -> 0x0055 }
            r3.<init>()     // Catch:{ Exception -> 0x0055 }
            com.myfitnesspal.shared.model.v1.DiaryNote r3 = r6.initDiaryNote(r3, r7)     // Catch:{ Exception -> 0x0055 }
            r1.add(r3)     // Catch:{ Exception -> 0x0055 }
            r7.moveToNext()     // Catch:{ Exception -> 0x0055 }
            int r4 = r4 + 1
            goto L_0x003b
        L_0x004f:
            if (r7 == 0) goto L_0x0054
            r7.close()
        L_0x0054:
            return r1
        L_0x0055:
            r1 = move-exception
            goto L_0x005b
        L_0x0057:
            r7 = move-exception
            goto L_0x0068
        L_0x0059:
            r1 = move-exception
            r7 = r0
        L_0x005b:
            com.uacf.core.util.Ln.e(r1)     // Catch:{ all -> 0x0064 }
            if (r7 == 0) goto L_0x0063
            r7.close()
        L_0x0063:
            return r0
        L_0x0064:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
        L_0x0068:
            if (r0 == 0) goto L_0x006d
            r0.close()
        L_0x006d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.DiaryNoteDbAdapter.fetchDiaryNotesOnDate(java.util.Date):java.util.ArrayList");
    }

    /* access modifiers changed from: 0000 */
    public DiaryNote initDiaryNote(DiaryNote diaryNote, Cursor cursor) {
        try {
            diaryNote.setLocalId(cursor.getLong(0));
            diaryNote.setMasterDatabaseId(cursor.getLong(2));
            diaryNote.setEntryDate(Database.decodeDateString(cursor.getString(3)));
            diaryNote.setNoteType(cursor.getInt(4));
            diaryNote.setBody(cursor.getString(5));
            return diaryNote;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }
}
