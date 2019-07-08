package com.myfitnesspal.shared.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.Constants.Database;
import com.uacf.core.database.SQLiteDatabaseWrapperOpenHelper;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

public class StockDbSQLiteOpenHelper extends SQLiteDatabaseWrapperOpenHelper {
    private static final String COMPRESSED_DATABASE_NAME = "stock_data.jar";
    private final Lazy<AppSettings> appSettings;
    private final Context context;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Upgrading from version ");
        sb.append(i);
        sb.append(" to ");
        sb.append(i2);
        sb.append(".");
        Ln.w(sb.toString(), new Object[0]);
        attachDatabase();
    }

    public StockDbSQLiteOpenHelper(Context context2, Lazy<AppSettings> lazy) {
        super(context2, Database.STOCK_DATABASE_NAME, null, 3);
        this.context = context2;
        this.appSettings = lazy;
    }

    public void attachDatabase() {
        if (((AppSettings) this.appSettings.get()).getLastFreshStockDatabaseVersion() < 3 || !doesStockDatabaseFileExist(this.context)) {
            try {
                deleteStockDatabase(this.context);
                InputStream inputStream = null;
                try {
                    inputStream = this.context.getAssets().open(COMPRESSED_DATABASE_NAME);
                } catch (IOException unused) {
                    Ln.e("Stocks.db could not be found ", new Object[0]);
                }
                JarInputStream jarInputStream = new JarInputStream(inputStream);
                ZipEntry nextEntry = jarInputStream.getNextEntry();
                byte[] bArr = new byte[1024];
                while (nextEntry != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Processing stock data jar file entry:");
                    sb.append(nextEntry.getName());
                    Ln.i(sb.toString(), new Object[0]);
                    if (nextEntry.isDirectory()) {
                        jarInputStream.closeEntry();
                    } else {
                        File file = new File(getStockDatabasePath(this.context));
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        Ln.i("Unjar and copy stocks database to device, please wait.....", new Object[0]);
                        while (true) {
                            int read = jarInputStream.read(bArr, 0, bArr.length);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        Ln.i("Successfully copied stocks database to device.", new Object[0]);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        jarInputStream.closeEntry();
                        file.setLastModified(nextEntry.getTime());
                        nextEntry = jarInputStream.getNextEntry();
                    }
                }
                inputStream.close();
                ((AppSettings) this.appSettings.get()).setLastFreshStockDatabaseVersion(3);
            } catch (Exception e) {
                Ln.e(e, "An error was encountered while attempting to create stocks database", new Object[0]);
            }
        }
    }

    private static boolean deleteStockDatabase(Context context2) {
        if (!doesStockDatabaseFileExist(context2)) {
            return false;
        }
        try {
            return context2.deleteDatabase(Database.STOCK_DATABASE_NAME);
        } catch (Exception e) {
            Ln.e(e);
            return false;
        }
    }

    public static boolean doesStockDatabaseFileExist(Context context2) {
        return new File(getStockDatabasePath(context2)).exists();
    }

    private static String getStockDatabasePath(Context context2) {
        return context2.getDatabasePath(Database.STOCK_DATABASE_NAME).getAbsolutePath();
    }
}
