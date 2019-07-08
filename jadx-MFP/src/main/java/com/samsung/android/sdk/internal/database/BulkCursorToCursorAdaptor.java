package com.samsung.android.sdk.internal.database;

import android.database.AbstractWindowedCursor;
import android.database.StaleDataException;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

public final class BulkCursorToCursorAdaptor extends AbstractWindowedCursor {
    private IBulkCursor a;
    private String[] b;
    private boolean c;
    private int d;

    public final void initialize(BulkCursorDescriptor bulkCursorDescriptor) {
        this.a = bulkCursorDescriptor.cursor;
        this.b = bulkCursorDescriptor.columnNames;
        this.c = bulkCursorDescriptor.wantsAllOnMoveCalls;
        this.d = bulkCursorDescriptor.count;
        if (bulkCursorDescriptor.window != null) {
            setWindow(bulkCursorDescriptor.window);
        }
    }

    private void a() {
        if (this.a == null) {
            throw new StaleDataException("Attempted to access a cursor after it has been closed.");
        }
    }

    public final int getCount() {
        a();
        return this.d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onMove(int r3, int r4) {
        /*
            r2 = this;
            r2.a()
            r3 = 0
            android.database.CursorWindow r0 = r2.mWindow     // Catch:{ RemoteException -> 0x0039 }
            if (r0 == 0) goto L_0x002a
            android.database.CursorWindow r0 = r2.mWindow     // Catch:{ RemoteException -> 0x0039 }
            int r0 = r0.getStartPosition()     // Catch:{ RemoteException -> 0x0039 }
            if (r4 < r0) goto L_0x002a
            android.database.CursorWindow r0 = r2.mWindow     // Catch:{ RemoteException -> 0x0039 }
            int r0 = r0.getStartPosition()     // Catch:{ RemoteException -> 0x0039 }
            android.database.CursorWindow r1 = r2.mWindow     // Catch:{ RemoteException -> 0x0039 }
            int r1 = r1.getNumRows()     // Catch:{ RemoteException -> 0x0039 }
            int r0 = r0 + r1
            if (r4 < r0) goto L_0x0020
            goto L_0x002a
        L_0x0020:
            boolean r0 = r2.c     // Catch:{ RemoteException -> 0x0039 }
            if (r0 == 0) goto L_0x0033
            com.samsung.android.sdk.internal.database.IBulkCursor r0 = r2.a     // Catch:{ RemoteException -> 0x0039 }
            r0.onMove(r4)     // Catch:{ RemoteException -> 0x0039 }
            goto L_0x0033
        L_0x002a:
            com.samsung.android.sdk.internal.database.IBulkCursor r0 = r2.a     // Catch:{ RemoteException -> 0x0039 }
            android.database.CursorWindow r4 = r0.getWindow(r4)     // Catch:{ RemoteException -> 0x0039 }
            r2.setWindow(r4)     // Catch:{ RemoteException -> 0x0039 }
        L_0x0033:
            android.database.CursorWindow r4 = r2.mWindow
            if (r4 == 0) goto L_0x0038
            r3 = 1
        L_0x0038:
            return r3
        L_0x0039:
            java.lang.String r4 = "BulkCursor"
            java.lang.String r0 = "Unable to get window because the remote process is dead"
            android.util.Log.e(r4, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.internal.database.BulkCursorToCursorAdaptor.onMove(int, int):boolean");
    }

    public final void deactivate() {
        super.deactivate();
        IBulkCursor iBulkCursor = this.a;
        if (iBulkCursor != null) {
            try {
                iBulkCursor.deactivate();
            } catch (RemoteException unused) {
                Log.w("BulkCursor", "Remote process exception when deactivating");
            }
        }
    }

    public final void close() {
        super.close();
        IBulkCursor iBulkCursor = this.a;
        if (iBulkCursor != null) {
            try {
                iBulkCursor.close();
            } catch (RemoteException unused) {
                Log.w("BulkCursor", "Remote process exception when closing");
            } finally {
                this.a = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void closeWindow() {
        if (this.mWindow != null) {
            this.mWindow.close();
            this.mWindow = null;
        }
    }

    public final boolean requery() {
        a();
        try {
            this.d = this.a.requery();
            if (this.d != -1) {
                this.mPos = -1;
                closeWindow();
                super.requery();
                return true;
            }
            deactivate();
            return false;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Unable to requery because the remote process exception ");
            sb.append(e.getMessage());
            Log.e("BulkCursor", sb.toString());
            deactivate();
            return false;
        }
    }

    public final String[] getColumnNames() {
        a();
        return this.b;
    }

    public final Bundle getExtras() {
        a();
        try {
            return this.a.getExtras();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final Bundle respond(Bundle bundle) {
        a();
        try {
            return this.a.respond(bundle);
        } catch (RemoteException e) {
            Log.w("BulkCursor", "respond() threw RemoteException, returning an empty bundle.", e);
            return Bundle.EMPTY;
        }
    }

    public final int getColumnIndex(String str) {
        int i = 0;
        for (String equalsIgnoreCase : this.b) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
