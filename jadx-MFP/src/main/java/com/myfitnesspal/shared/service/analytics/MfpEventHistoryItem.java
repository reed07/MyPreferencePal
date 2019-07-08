package com.myfitnesspal.shared.service.analytics;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.util.Database;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MfpEventHistoryItem implements Parcelable {
    public static final Creator<MfpEventHistoryItem> CREATOR = new Creator<MfpEventHistoryItem>() {
        public MfpEventHistoryItem createFromParcel(Parcel parcel) {
            return new MfpEventHistoryItem(parcel);
        }

        public MfpEventHistoryItem[] newArray(int i) {
            return new MfpEventHistoryItem[i];
        }
    };
    @Expose
    private String abTestName;
    @Expose
    private Map<String, String> attributes;
    @Expose
    private String eventName;
    @Expose
    private int sampleRate;
    @Expose
    private Date timestamp;

    public int describeContents() {
        return 0;
    }

    public MfpEventHistoryItem() {
    }

    public MfpEventHistoryItem(String str, Date date, Map<String, String> map, String str2, int i) {
        this.eventName = str;
        setTimestamp(date);
        this.attributes = map;
        this.abTestName = str2;
        this.sampleRate = i;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String str) {
        this.eventName = str;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date date) {
        if (date == null) {
            date = new Date();
        }
        this.timestamp = date;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public String getAbTestName() {
        return this.abTestName;
    }

    public void setAbTestName(String str) {
        this.abTestName = str;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(int i) {
        this.sampleRate = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.eventName);
        parcel.writeString(Database.encodeDateAndTime(this.timestamp));
        parcel.writeMap(this.attributes);
        parcel.writeString(this.abTestName);
        parcel.writeInt(this.sampleRate);
    }

    private void readFromParcel(Parcel parcel) {
        this.eventName = parcel.readString();
        this.timestamp = Database.decodeDateAndTimeString(parcel.readString());
        this.attributes = new HashMap();
        parcel.readMap(this.attributes, String.class.getClassLoader());
        this.abTestName = parcel.readString();
        this.sampleRate = parcel.readInt();
    }

    private MfpEventHistoryItem(Parcel parcel) {
        readFromParcel(parcel);
    }
}
