package com.samsung.android.sdk.internal.database;

import android.database.CrossProcessCursor;
import android.database.CrossProcessCursorWrapper;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.StaleDataException;
import android.os.Bundle;

public final class CursorToBulkCursorAdaptor extends BulkCursorNative {
    private final Object a = new Object();
    private final String b;
    private CrossProcessCursor c;
    private CursorWindow d;

    public CursorToBulkCursorAdaptor(Cursor cursor, String str) {
        if (cursor instanceof CrossProcessCursor) {
            this.c = (CrossProcessCursor) cursor;
        } else {
            this.c = new CrossProcessCursorWrapper(cursor);
        }
        this.b = str;
    }

    private void a() {
        CursorWindow cursorWindow = this.d;
        if (cursorWindow != null) {
            cursorWindow.close();
            this.d = null;
        }
        CrossProcessCursor crossProcessCursor = this.c;
        if (crossProcessCursor != null) {
            CursorWindow window = crossProcessCursor.getWindow();
            if (window != null) {
                window.close();
            }
        }
    }

    private void b() {
        if (this.c == null) {
            throw new StaleDataException("Attempted to access a cursor after it has been closed.");
        }
    }

    public final BulkCursorDescriptor getBulkCursorDescriptor() {
        BulkCursorDescriptor bulkCursorDescriptor;
        synchronized (this.a) {
            b();
            bulkCursorDescriptor = new BulkCursorDescriptor();
            bulkCursorDescriptor.cursor = this;
            bulkCursorDescriptor.columnNames = this.c.getColumnNames();
            bulkCursorDescriptor.wantsAllOnMoveCalls = this.c.getWantsAllOnMoveCalls();
            bulkCursorDescriptor.count = this.c.getCount();
            bulkCursorDescriptor.window = this.c.getWindow();
            if (bulkCursorDescriptor.window != null) {
                bulkCursorDescriptor.window.acquireReference();
            }
        }
        return bulkCursorDescriptor;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.database.CursorWindow getWindow(int r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.a
            monitor-enter(r0)
            r4.b()     // Catch:{ all -> 0x004c }
            android.database.CrossProcessCursor r1 = r4.c     // Catch:{ all -> 0x004c }
            boolean r1 = r1.moveToPosition(r5)     // Catch:{ all -> 0x004c }
            if (r1 != 0) goto L_0x0014
            r4.a()     // Catch:{ all -> 0x004c }
            r5 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            return r5
        L_0x0014:
            android.database.CrossProcessCursor r1 = r4.c     // Catch:{ all -> 0x004c }
            android.database.CursorWindow r1 = r1.getWindow()     // Catch:{ all -> 0x004c }
            if (r1 != 0) goto L_0x0045
            android.database.CursorWindow r1 = r4.d     // Catch:{ all -> 0x004c }
            if (r1 != 0) goto L_0x002c
            android.database.CursorWindow r1 = new android.database.CursorWindow     // Catch:{ all -> 0x004c }
            java.lang.String r2 = r4.b     // Catch:{ all -> 0x004c }
            r1.<init>(r2)     // Catch:{ all -> 0x004c }
            r4.d = r1     // Catch:{ all -> 0x004c }
            android.database.CursorWindow r1 = r4.d     // Catch:{ all -> 0x004c }
            goto L_0x0040
        L_0x002c:
            int r2 = r1.getStartPosition()     // Catch:{ all -> 0x004c }
            if (r5 < r2) goto L_0x003d
            int r2 = r1.getStartPosition()     // Catch:{ all -> 0x004c }
            int r3 = r1.getNumRows()     // Catch:{ all -> 0x004c }
            int r2 = r2 + r3
            if (r5 < r2) goto L_0x0040
        L_0x003d:
            r1.clear()     // Catch:{ all -> 0x004c }
        L_0x0040:
            android.database.CrossProcessCursor r2 = r4.c     // Catch:{ all -> 0x004c }
            r2.fillWindow(r5, r1)     // Catch:{ all -> 0x004c }
        L_0x0045:
            if (r1 == 0) goto L_0x004a
            r1.acquireReference()     // Catch:{ all -> 0x004c }
        L_0x004a:
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            return r1
        L_0x004c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.internal.database.CursorToBulkCursorAdaptor.getWindow(int):android.database.CursorWindow");
    }

    public final void onMove(int i) {
        synchronized (this.a) {
            b();
            this.c.onMove(this.c.getPosition(), i);
        }
    }

    public final void deactivate() {
        synchronized (this.a) {
            if (this.c != null) {
                this.c.deactivate();
            }
            a();
        }
    }

    public final void close() {
        synchronized (this.a) {
            a();
            if (this.c != null) {
                this.c.close();
                this.c = null;
            }
        }
    }

    public final int requery() {
        synchronized (this.a) {
            b();
            a();
            try {
                if (!this.c.requery()) {
                    return -1;
                }
                int count = this.c.getCount();
                return count;
            } catch (IllegalStateException e) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.b);
                sb.append(" Requery misuse db, mCursor isClosed:");
                sb.append(this.c.isClosed());
                throw new IllegalStateException(sb.toString(), e);
            }
        }
    }

    public final Bundle getExtras() {
        Bundle extras;
        synchronized (this.a) {
            b();
            extras = this.c.getExtras();
        }
        return extras;
    }

    public final Bundle respond(Bundle bundle) {
        Bundle respond;
        synchronized (this.a) {
            b();
            respond = this.c.respond(bundle);
        }
        return respond;
    }
}
