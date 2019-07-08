package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.ParcelableUtil;
import java.util.List;

public class MfpNewsFeedActivityConversation implements Parcelable {
    public static final Creator<MfpNewsFeedActivityConversation> CREATOR = new Creator<MfpNewsFeedActivityConversation>() {
        public MfpNewsFeedActivityConversation createFromParcel(Parcel parcel) {
            return new MfpNewsFeedActivityConversation(parcel);
        }

        public MfpNewsFeedActivityConversation[] newArray(int i) {
            return new MfpNewsFeedActivityConversation[i];
        }
    };
    @Expose
    private List<MfpNewsFeedActivityComment> comments;
    @Expose
    private int count;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpNewsFeedActivityConversation> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpNewsFeedActivityConversation() {
    }

    private MfpNewsFeedActivityConversation(Parcel parcel) {
        this.count = parcel.readInt();
        this.comments = ParcelableUtil.readList(parcel, MfpNewsFeedActivityComment.class);
    }

    public MfpNewsFeedActivityConversation(int i, List<MfpNewsFeedActivityComment> list) {
        this.count = i;
        this.comments = list;
    }

    public List<MfpNewsFeedActivityComment> getComments() {
        return this.comments;
    }

    public void setComments(List<MfpNewsFeedActivityComment> list) {
        this.comments = list;
    }

    public void addCommentAndUpdateCount(MfpNewsFeedActivityComment mfpNewsFeedActivityComment) {
        this.comments.add(mfpNewsFeedActivityComment);
        this.count++;
    }

    public void removeCommentAndUpdateCount(int i) {
        this.comments.remove(i);
        this.count--;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.count);
        ParcelableUtil.writeList(parcel, this.comments);
    }
}
