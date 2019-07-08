package com.uacf.core.util;

import android.database.Cursor;

public final class CursorUtils {
    public static long getLong(Cursor cursor, String str) {
        return cursor.getLong(cursor.getColumnIndex(str));
    }

    public static int getInt(Cursor cursor, String str) {
        return cursor.getInt(cursor.getColumnIndex(str));
    }

    public static float getFloat(Cursor cursor, String str) {
        return cursor.getFloat(cursor.getColumnIndex(str));
    }

    public static String getString(Cursor cursor, String str) {
        return cursor.getString(cursor.getColumnIndex(str));
    }

    public static byte[] getBlob(Cursor cursor, String str) {
        return cursor.getBlob(cursor.getColumnIndex(str));
    }

    public static boolean getBoolean(Cursor cursor, int i) {
        return cursor.getInt(i) != 0;
    }

    public static boolean getBoolean(Cursor cursor, String str) {
        return getBoolean(cursor, cursor.getColumnIndex(str));
    }

    public static double getDouble(Cursor cursor, String str) {
        return cursor.getDouble(cursor.getColumnIndex(str));
    }

    public static long readLongAndClose(Cursor cursor, long j) {
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    long j2 = cursor.getLong(0);
                    close(cursor);
                    return j2;
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                close(cursor);
                throw th;
            }
        }
        close(cursor);
        return j;
    }

    public static int readIntAndClose(Cursor cursor, int i) {
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int i2 = cursor.getInt(0);
                    close(cursor);
                    return i2;
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                close(cursor);
                throw th;
            }
        }
        close(cursor);
        return i;
    }

    public static float readFloatAndClose(Cursor cursor, float f) {
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    float f2 = cursor.getFloat(0);
                    close(cursor);
                    return f2;
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                close(cursor);
                throw th;
            }
        }
        close(cursor);
        return f;
    }

    public static void close(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    public static boolean isValid(Cursor cursor) {
        return cursor != null && !cursor.isClosed() && !cursor.isBeforeFirst() && !cursor.isAfterLast();
    }
}
