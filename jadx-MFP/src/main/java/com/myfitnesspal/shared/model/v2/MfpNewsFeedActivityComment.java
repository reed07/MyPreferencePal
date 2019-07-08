package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.ParcelableUtil;
import java.util.Date;

public class MfpNewsFeedActivityComment implements Parcelable, NewsFeedItem {
    public static final Creator<MfpNewsFeedActivityComment> CREATOR = new Creator<MfpNewsFeedActivityComment>() {
        public MfpNewsFeedActivityComment createFromParcel(Parcel parcel) {
            return new MfpNewsFeedActivityComment(parcel);
        }

        public MfpNewsFeedActivityComment[] newArray(int i) {
            return new MfpNewsFeedActivityComment[i];
        }
    };
    @Expose
    private boolean conversationEnabled;
    @Expose
    private String correlationId;
    @Expose
    private Date createdAt;
    @Expose
    private String id;
    @Expose
    private boolean isCommentableByUser;
    @Expose
    private boolean isRemovableByUser;
    @Expose
    private MfpNewsFeedLikeDetails likes;
    @Expose
    private boolean likesEnabled;
    @Expose
    private MfpNewsFeedActivityParticipant sourceUser;
    @Expose
    private String text;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpNewsFeedActivityComment> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpNewsFeedActivityComment() {
    }

    private MfpNewsFeedActivityComment(Parcel parcel) {
        this.id = parcel.readString();
        this.text = parcel.readString();
        this.correlationId = parcel.readString();
        this.sourceUser = (MfpNewsFeedActivityParticipant) parcel.readParcelable(MfpNewsFeedActivityParticipant.class.getClassLoader());
        this.likesEnabled = ParcelableUtil.readBoolean(parcel);
        this.createdAt = ParcelableUtil.readDate(parcel);
        this.conversationEnabled = ParcelableUtil.readBoolean(parcel);
        this.isRemovableByUser = ParcelableUtil.readBoolean(parcel);
        this.isCommentableByUser = ParcelableUtil.readBoolean(parcel);
        this.likes = (MfpNewsFeedLikeDetails) parcel.readParcelable(MfpNewsFeedLikeDetails.class.getClassLoader());
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public MfpNewsFeedActivityComment setCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public MfpNewsFeedActivityComment setId(String str) {
        this.id = str;
        return this;
    }

    public String getCorrelationId() {
        return this.correlationId;
    }

    public MfpNewsFeedActivityComment setCorrelationId(String str) {
        this.correlationId = str;
        return this;
    }

    public boolean isConversationEnabled() {
        return this.conversationEnabled;
    }

    public MfpNewsFeedActivityComment setConversationEnabled(boolean z) {
        this.conversationEnabled = z;
        return this;
    }

    public boolean isIsRemovableByUser() {
        return this.isRemovableByUser;
    }

    public MfpNewsFeedActivityComment setIsRemovableByUser(boolean z) {
        this.isRemovableByUser = z;
        return this;
    }

    public boolean isIsCommentableByUser() {
        return this.isCommentableByUser;
    }

    public MfpNewsFeedActivityComment setIsCommentableByUser(boolean z) {
        this.isCommentableByUser = z;
        return this;
    }

    public MfpNewsFeedLikeDetails getLikes() {
        return this.likes;
    }

    public MfpNewsFeedActivityComment setLikes(MfpNewsFeedLikeDetails mfpNewsFeedLikeDetails) {
        this.likes = mfpNewsFeedLikeDetails;
        return this;
    }

    public boolean isLikesEnabled() {
        return this.likesEnabled;
    }

    public MfpNewsFeedActivityComment setLikesEnabled(boolean z) {
        this.likesEnabled = z;
        return this;
    }

    public MfpNewsFeedActivityParticipant getSourceUser() {
        return this.sourceUser;
    }

    public MfpNewsFeedActivityComment setSourceUser(MfpNewsFeedActivityParticipant mfpNewsFeedActivityParticipant) {
        this.sourceUser = mfpNewsFeedActivityParticipant;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public MfpNewsFeedActivityComment setText(String str) {
        this.text = str;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.text);
        parcel.writeString(this.correlationId);
        parcel.writeParcelable(this.sourceUser, i);
        ParcelableUtil.writeBoolean(parcel, this.likesEnabled);
        ParcelableUtil.writeDate(parcel, this.createdAt);
        ParcelableUtil.writeBoolean(parcel, this.conversationEnabled);
        ParcelableUtil.writeBoolean(parcel, this.isRemovableByUser);
        ParcelableUtil.writeBoolean(parcel, this.isCommentableByUser);
        parcel.writeParcelable(this.likes, i);
    }
}
