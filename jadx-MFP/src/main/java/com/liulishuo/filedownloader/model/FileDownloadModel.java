package com.liulishuo.filedownloader.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.myfitnesspal.shared.db.table.ProfileImagesTable.Columns;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FileDownloadModel implements Parcelable {
    public static final Creator<FileDownloadModel> CREATOR = new Creator<FileDownloadModel>() {
        public FileDownloadModel createFromParcel(Parcel parcel) {
            return new FileDownloadModel(parcel);
        }

        public FileDownloadModel[] newArray(int i) {
            return new FileDownloadModel[i];
        }
    };
    private int connectionCount;
    private String eTag;
    private String errMsg;
    private String filename;
    private int id;
    private boolean isLargeFile;
    private String path;
    private boolean pathAsDirectory;
    private final AtomicLong soFar;
    private final AtomicInteger status;
    private long total;
    private String url;

    public int describeContents() {
        return 0;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setPath(String str, boolean z) {
        this.path = str;
        this.pathAsDirectory = z;
    }

    public void setStatus(byte b) {
        this.status.set(b);
    }

    public void setSoFar(long j) {
        this.soFar.set(j);
    }

    public void increaseSoFar(long j) {
        this.soFar.addAndGet(j);
    }

    public void setTotal(long j) {
        this.isLargeFile = j > 2147483647L;
        this.total = j;
    }

    public int getId() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getPath() {
        return this.path;
    }

    public String getTargetFilePath() {
        return FileDownloadUtils.getTargetFilePath(getPath(), isPathAsDirectory(), getFilename());
    }

    public String getTempFilePath() {
        if (getTargetFilePath() == null) {
            return null;
        }
        return FileDownloadUtils.getTempPath(getTargetFilePath());
    }

    public byte getStatus() {
        return (byte) this.status.get();
    }

    public long getSoFar() {
        return this.soFar.get();
    }

    public long getTotal() {
        return this.total;
    }

    public boolean isChunked() {
        return this.total == -1;
    }

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public boolean isPathAsDirectory() {
        return this.pathAsDirectory;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setConnectionCount(int i) {
        this.connectionCount = i;
    }

    public int getConnectionCount() {
        return this.connectionCount;
    }

    public void resetConnectionCount() {
        this.connectionCount = 1;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(getId()));
        contentValues.put("url", getUrl());
        contentValues.put("path", getPath());
        contentValues.put("status", Byte.valueOf(getStatus()));
        contentValues.put("sofar", Long.valueOf(getSoFar()));
        contentValues.put("total", Long.valueOf(getTotal()));
        contentValues.put("errMsg", getErrMsg());
        contentValues.put("etag", getETag());
        contentValues.put("connectionCount", Integer.valueOf(getConnectionCount()));
        contentValues.put("pathAsDirectory", Boolean.valueOf(isPathAsDirectory()));
        if (isPathAsDirectory() && getFilename() != null) {
            contentValues.put(Columns.FILENAME, getFilename());
        }
        return contentValues;
    }

    public boolean isLargeFile() {
        return this.isLargeFile;
    }

    public String toString() {
        return FileDownloadUtils.formatString("id[%d], url[%s], path[%s], status[%d], sofar[%s], total[%d], etag[%s], %s", Integer.valueOf(this.id), this.url, this.path, Integer.valueOf(this.status.get()), this.soFar, Long.valueOf(this.total), this.eTag, super.toString());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.url);
        parcel.writeString(this.path);
        parcel.writeByte(this.pathAsDirectory ? (byte) 1 : 0);
        parcel.writeString(this.filename);
        parcel.writeByte((byte) this.status.get());
        parcel.writeLong(this.soFar.get());
        parcel.writeLong(this.total);
        parcel.writeString(this.errMsg);
        parcel.writeString(this.eTag);
        parcel.writeInt(this.connectionCount);
        parcel.writeByte(this.isLargeFile ? (byte) 1 : 0);
    }

    public FileDownloadModel() {
        this.soFar = new AtomicLong();
        this.status = new AtomicInteger();
    }

    protected FileDownloadModel(Parcel parcel) {
        this.id = parcel.readInt();
        this.url = parcel.readString();
        this.path = parcel.readString();
        boolean z = true;
        this.pathAsDirectory = parcel.readByte() != 0;
        this.filename = parcel.readString();
        this.status = new AtomicInteger(parcel.readByte());
        this.soFar = new AtomicLong(parcel.readLong());
        this.total = parcel.readLong();
        this.errMsg = parcel.readString();
        this.eTag = parcel.readString();
        this.connectionCount = parcel.readInt();
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.isLargeFile = z;
    }
}
