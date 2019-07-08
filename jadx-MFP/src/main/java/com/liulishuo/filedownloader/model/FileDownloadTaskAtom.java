package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class FileDownloadTaskAtom implements Parcelable {
    public static final Creator<FileDownloadTaskAtom> CREATOR = new Creator<FileDownloadTaskAtom>() {
        public FileDownloadTaskAtom createFromParcel(Parcel parcel) {
            return new FileDownloadTaskAtom(parcel);
        }

        public FileDownloadTaskAtom[] newArray(int i) {
            return new FileDownloadTaskAtom[i];
        }
    };
    private String path;
    private long totalBytes;
    private String url;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.path);
        parcel.writeLong(this.totalBytes);
    }

    protected FileDownloadTaskAtom(Parcel parcel) {
        this.url = parcel.readString();
        this.path = parcel.readString();
        this.totalBytes = parcel.readLong();
    }
}
