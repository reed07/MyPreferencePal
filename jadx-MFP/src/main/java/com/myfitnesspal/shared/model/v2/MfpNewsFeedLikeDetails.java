package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.ParcelableUtil;
import java.util.List;

public class MfpNewsFeedLikeDetails implements Parcelable {
    public static final Creator<MfpNewsFeedLikeDetails> CREATOR = new Creator<MfpNewsFeedLikeDetails>() {
        public MfpNewsFeedLikeDetails createFromParcel(Parcel parcel) {
            return new MfpNewsFeedLikeDetails(parcel);
        }

        public MfpNewsFeedLikeDetails[] newArray(int i) {
            return new MfpNewsFeedLikeDetails[i];
        }
    };
    @Expose
    private int count;
    @Expose
    private List<MfpNewsFeedActivityParticipant> participants;
    @Expose
    private boolean userLiked;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpNewsFeedLikeDetails> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpNewsFeedLikeDetails() {
    }

    private MfpNewsFeedLikeDetails(Parcel parcel) {
        this.count = parcel.readInt();
        this.userLiked = parcel.readByte() != 0;
        this.participants = ParcelableUtil.readList(parcel, MfpNewsFeedActivityParticipant.class);
    }

    public int getCount() {
        return this.count;
    }

    public MfpNewsFeedLikeDetails setCount(int i) {
        this.count = i;
        return this;
    }

    public List<MfpNewsFeedActivityParticipant> getParticipants() {
        return this.participants;
    }

    public MfpNewsFeedLikeDetails setParticipants(List<MfpNewsFeedActivityParticipant> list) {
        this.participants = list;
        return this;
    }

    public boolean isUserLiked() {
        return this.userLiked;
    }

    public MfpNewsFeedLikeDetails setUserLiked(boolean z) {
        this.userLiked = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.count);
        parcel.writeByte(this.userLiked ? (byte) 1 : 0);
        ParcelableUtil.writeList(parcel, this.participants);
    }
}
