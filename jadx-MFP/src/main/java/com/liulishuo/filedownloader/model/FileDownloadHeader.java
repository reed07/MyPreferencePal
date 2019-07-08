package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileDownloadHeader implements Parcelable {
    public static final Creator<FileDownloadHeader> CREATOR = new Creator<FileDownloadHeader>() {
        public FileDownloadHeader createFromParcel(Parcel parcel) {
            return new FileDownloadHeader(parcel);
        }

        public FileDownloadHeader[] newArray(int i) {
            return new FileDownloadHeader[i];
        }
    };
    private HashMap<String, List<String>> mHeaderMap;

    public int describeContents() {
        return 0;
    }

    public void add(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else if (str2 != null) {
            if (this.mHeaderMap == null) {
                this.mHeaderMap = new HashMap<>();
            }
            List list = (List) this.mHeaderMap.get(str);
            if (list == null) {
                list = new ArrayList();
                this.mHeaderMap.put(str, list);
            }
            if (!list.contains(str2)) {
                list.add(str2);
            }
        } else {
            throw new NullPointerException("value == null");
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.mHeaderMap);
    }

    public HashMap<String, List<String>> getHeaders() {
        return this.mHeaderMap;
    }

    public FileDownloadHeader() {
    }

    protected FileDownloadHeader(Parcel parcel) {
        this.mHeaderMap = parcel.readHashMap(String.class.getClassLoader());
    }

    public String toString() {
        return this.mHeaderMap.toString();
    }
}
