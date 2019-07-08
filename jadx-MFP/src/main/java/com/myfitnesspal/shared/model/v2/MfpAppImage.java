package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpAppImage implements Parcelable {
    public static Creator<MfpAppImage> CREATOR = new Creator<MfpAppImage>() {
        public MfpAppImage createFromParcel(Parcel parcel) {
            return new MfpAppImage(parcel);
        }

        public MfpAppImage[] newArray(int i) {
            return new MfpAppImage[i];
        }
    };
    @Expose
    private String createdAt;
    @Expose
    private String filename;
    @Expose
    private int height;
    @Expose
    private int id;
    @Expose
    private int imageType;
    @Expose
    private float pixelRatio;
    @Expose
    private int platformAppId;
    @Expose
    private int position;
    @Expose
    private String updatedAt;
    @Expose
    private int width;

    public int describeContents() {
        return 0;
    }

    public MfpAppImage() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public int getPlatformAppId() {
        return this.platformAppId;
    }

    public void setPlatformAppId(int i) {
        this.platformAppId = i;
    }

    public int getImageType() {
        return this.imageType;
    }

    public void setImageType(int i) {
        this.imageType = i;
    }

    public float getPixelRatio() {
        return this.pixelRatio;
    }

    public void setPixelRatio(float f) {
        this.pixelRatio = f;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.platformAppId);
        parcel.writeInt(this.imageType);
        parcel.writeFloat(this.pixelRatio);
        parcel.writeString(this.filename);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.position);
        parcel.writeString(this.createdAt);
        parcel.writeString(this.updatedAt);
    }

    private MfpAppImage(Parcel parcel) {
        this.id = parcel.readInt();
        this.platformAppId = parcel.readInt();
        this.imageType = parcel.readInt();
        this.pixelRatio = parcel.readFloat();
        this.filename = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.position = parcel.readInt();
        this.createdAt = parcel.readString();
        this.updatedAt = parcel.readString();
    }
}
