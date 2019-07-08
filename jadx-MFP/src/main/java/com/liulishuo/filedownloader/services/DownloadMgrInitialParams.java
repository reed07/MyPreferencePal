package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.connection.DefaultConnectionCountAdapter;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.database.RemitDatabase;
import com.liulishuo.filedownloader.stream.FileDownloadRandomAccessFile.Creator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCountAdapter;
import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCreator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.DatabaseCustomMaker;
import com.liulishuo.filedownloader.util.FileDownloadHelper.IdGenerator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.OutputStreamCreator;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class DownloadMgrInitialParams {
    private final InitCustomMaker mMaker = null;

    public static class InitCustomMaker {
        ConnectionCountAdapter mConnectionCountAdapter;
        ConnectionCreator mConnectionCreator;
        DatabaseCustomMaker mDatabaseCustomMaker;
        IdGenerator mIdGenerator;
        Integer mMaxNetworkThreadCount;
        OutputStreamCreator mOutputStreamCreator;

        public String toString() {
            return FileDownloadUtils.formatString("component: database[%s], maxNetworkCount[%s], outputStream[%s], connection[%s], connectionCountAdapter[%s]", this.mDatabaseCustomMaker, this.mMaxNetworkThreadCount, this.mOutputStreamCreator, this.mConnectionCreator, this.mConnectionCountAdapter);
        }
    }

    public int getMaxNetworkThreadCount() {
        InitCustomMaker initCustomMaker = this.mMaker;
        if (initCustomMaker == null) {
            return getDefaultMaxNetworkThreadCount();
        }
        Integer num = initCustomMaker.mMaxNetworkThreadCount;
        if (num == null) {
            return getDefaultMaxNetworkThreadCount();
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "initial FileDownloader manager with the customize maxNetworkThreadCount: %d", num);
        }
        return FileDownloadProperties.getValidNetworkThreadCount(num.intValue());
    }

    public FileDownloadDatabase createDatabase() {
        InitCustomMaker initCustomMaker = this.mMaker;
        if (initCustomMaker == null || initCustomMaker.mDatabaseCustomMaker == null) {
            return createDefaultDatabase();
        }
        FileDownloadDatabase customMake = this.mMaker.mDatabaseCustomMaker.customMake();
        if (customMake == null) {
            return createDefaultDatabase();
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "initial FileDownloader manager with the customize database: %s", customMake);
        }
        return customMake;
    }

    public OutputStreamCreator createOutputStreamCreator() {
        InitCustomMaker initCustomMaker = this.mMaker;
        if (initCustomMaker == null) {
            return createDefaultOutputStreamCreator();
        }
        OutputStreamCreator outputStreamCreator = initCustomMaker.mOutputStreamCreator;
        if (outputStreamCreator == null) {
            return createDefaultOutputStreamCreator();
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "initial FileDownloader manager with the customize output stream: %s", outputStreamCreator);
        }
        return outputStreamCreator;
    }

    public ConnectionCreator createConnectionCreator() {
        InitCustomMaker initCustomMaker = this.mMaker;
        if (initCustomMaker == null) {
            return createDefaultConnectionCreator();
        }
        ConnectionCreator connectionCreator = initCustomMaker.mConnectionCreator;
        if (connectionCreator == null) {
            return createDefaultConnectionCreator();
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "initial FileDownloader manager with the customize connection creator: %s", connectionCreator);
        }
        return connectionCreator;
    }

    public ConnectionCountAdapter createConnectionCountAdapter() {
        InitCustomMaker initCustomMaker = this.mMaker;
        if (initCustomMaker == null) {
            return createDefaultConnectionCountAdapter();
        }
        ConnectionCountAdapter connectionCountAdapter = initCustomMaker.mConnectionCountAdapter;
        if (connectionCountAdapter == null) {
            return createDefaultConnectionCountAdapter();
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "initial FileDownloader manager with the customize connection count adapter: %s", connectionCountAdapter);
        }
        return connectionCountAdapter;
    }

    public IdGenerator createIdGenerator() {
        InitCustomMaker initCustomMaker = this.mMaker;
        if (initCustomMaker == null) {
            return createDefaultIdGenerator();
        }
        IdGenerator idGenerator = initCustomMaker.mIdGenerator;
        if (idGenerator == null) {
            return createDefaultIdGenerator();
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "initial FileDownloader manager with the customize id generator: %s", idGenerator);
        }
        return idGenerator;
    }

    private IdGenerator createDefaultIdGenerator() {
        return new DefaultIdGenerator();
    }

    private int getDefaultMaxNetworkThreadCount() {
        return FileDownloadProperties.getImpl().downloadMaxNetworkThreadCount;
    }

    private FileDownloadDatabase createDefaultDatabase() {
        return new RemitDatabase();
    }

    private OutputStreamCreator createDefaultOutputStreamCreator() {
        return new Creator();
    }

    private ConnectionCreator createDefaultConnectionCreator() {
        return new FileDownloadUrlConnection.Creator();
    }

    private ConnectionCountAdapter createDefaultConnectionCountAdapter() {
        return new DefaultConnectionCountAdapter();
    }
}
