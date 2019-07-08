package com.uacf.core.database;

import android.database.DatabaseUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ReturningFunction0;
import com.uacf.core.util.Strings;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class DatabaseUtil {
    public static void ensureDatabaseTransaction(SQLiteDatabaseWrapper sQLiteDatabaseWrapper, Function0 function0) {
        ensureDatabaseTransaction(sQLiteDatabaseWrapper, function0, (Function0) null);
    }

    public static void ensureDatabaseTransaction(SQLiteDatabaseWrapper sQLiteDatabaseWrapper, final Function0 function0, Function0 function02) {
        ensureDatabaseTransaction(sQLiteDatabaseWrapper, (ReturningFunction0<Boolean>) new ReturningFunction0<Boolean>() {
            public Boolean execute() {
                FunctionUtils.invokeIfValid(function0);
                return Boolean.valueOf(true);
            }
        }, function02);
    }

    public static void ensureDatabaseTransaction(SQLiteDatabaseWrapper sQLiteDatabaseWrapper, ReturningFunction0<Boolean> returningFunction0, Function0 function0) {
        boolean z = !sQLiteDatabaseWrapper.inTransaction();
        if (z) {
            try {
                sQLiteDatabaseWrapper.beginTransaction();
            } catch (Throwable th) {
                FunctionUtils.invokeIfValid(function0);
                if (z) {
                    sQLiteDatabaseWrapper.endTransaction();
                }
                throw th;
            }
        }
        if (((Boolean) returningFunction0.execute()).booleanValue() && z) {
            sQLiteDatabaseWrapper.setTransactionSuccessful();
        }
        FunctionUtils.invokeIfValid(function0);
        if (z) {
            sQLiteDatabaseWrapper.endTransaction();
        }
    }

    public static <T> String getArgsForList(List<T> list) {
        int size = CollectionUtils.size((Collection<?>) list);
        if (size == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < size; i++) {
            sb.append(DatabaseUtils.sqlEscapeString(list.get(i).toString()));
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static String getColumnString(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(strArr[i]);
            if (i < length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static String getQuestionMarkString(int i) {
        String[] strArr = new String[i];
        Arrays.fill(strArr, "?");
        return Strings.join(",", (T[]) strArr);
    }
}
