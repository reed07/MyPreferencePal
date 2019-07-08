package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.home.model.BlogEntry;
import com.myfitnesspal.feature.home.model.MfpBlogDailySummary;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.model.VideoEntry;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.MapOfStringObject;
import com.myfitnesspal.shared.model.MapOfStringString;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import java.util.Date;
import java.util.Map;

public class MfpNewsFeedActivityEntry implements Parcelable, NewsFeedItem {
    public static final Creator<MfpNewsFeedActivityEntry> CREATOR = new Creator<MfpNewsFeedActivityEntry>() {
        public MfpNewsFeedActivityEntry createFromParcel(Parcel parcel) {
            return new MfpNewsFeedActivityEntry(parcel);
        }

        public MfpNewsFeedActivityEntry[] newArray(int i) {
            return new MfpNewsFeedActivityEntry[i];
        }
    };
    private static final Map<String, Class<? extends MfpNewsFeedActivityEntryData>> TYPE_TO_CLASS_MAP = new Builder().put(DataTypes.DIARY_COMPLETE, MfpNewsFeedDiaryCompleteEntry.class).put("exercise", MfpNewsFeedExerciseEntry.class).put(DataTypes.LOG_IN_STREAK, MfpNewsFeedLogInStreakEntry.class).put("status_update", MfpNewsFeedStatusUpdateEntry.class).put(DataTypes.CHALLENGE_STATUS, MfpNewsFeedChallengeStatusEntry.class).put(DataTypes.WEIGHT_LOSS, MfpNewsFeedWeightLossEntry.class).put(DataTypes.BLOG_DAILY_SUMMARY, MfpBlogDailySummary.class).put(DataTypes.NEW_FRIEND, MfpNewsFeedStatusUpdateEntry.class).put(DataTypes.HERO_CARD, MfpNewsFeedHeroCardEntry.class).put(DataTypes.CHALLENGE_STATUS_CARD, MfpNewsFeedChallengesStatusCardEntry.class).put(DataTypes.BLOG_INDIVIDUAL_SUMMARY, BlogEntry.class).put("image_status_update", MfpNewsFeedImageStatusUpdateEntry.class).put(DataTypes.MEAL_STATUS_UPDATE, MfpNewsFeedMealPhotoUpdateEntry.class).put(DataTypes.LEGACY_HERO_CARD, LegacyMfpNewsFeedHeroCardEntry.class).put(DataTypes.VIDEO, VideoEntry.class).build();
    private ApiJsonMapper apiJsonMapper;
    @Expose
    private MfpNewsFeedActivityConversation conversation;
    @Expose
    private boolean conversationEnabled;
    @Expose
    private String correlationId;
    @Expose
    private Date createdAt;
    @Expose
    private Map<String, Object> data;
    private MfpNewsFeedActivityEntryData entryData;
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
    private MfpNewsFeedActivityParticipant owner;
    @Expose
    private String type;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpNewsFeedActivityEntry> {
    }

    public static final class DataTypes {
        public static final String BLOG_DAILY_SUMMARY = "blog_daily_summary";
        public static final String BLOG_INDIVIDUAL_SUMMARY = "blog_individual_summary";
        public static final String BLOG_POST = "blog_post";
        public static final String CHALLENGE_STATUS = "challenge_status";
        public static final String CHALLENGE_STATUS_CARD = "challenge_status_card";
        public static final String DIARY_COMPLETE = "diary_complete";
        public static final String EXERCISE = "exercise";
        public static final String HERO_CARD = "generic_hero_card";
        public static final String IMAGE_STATUS_UPDATE = "image_status_update";
        public static final String LEGACY_HERO_CARD = "hero_card";
        public static final String LOG_IN_STREAK = "log_in_streak";
        public static final String MEAL_STATUS_UPDATE = "meal_status_update";
        public static final String NEW_FRIEND = "new_friend";
        public static final String STATUS_UPDATE = "status_update";
        public static final String VIDEO = "blog_video_content";
        public static final String WEIGHT_LOSS = "weight_loss";
    }

    public int describeContents() {
        return 0;
    }

    public MfpNewsFeedActivityEntry() {
    }

    protected MfpNewsFeedActivityEntry(Parcel parcel) {
        this.id = parcel.readString();
        this.correlationId = parcel.readString();
        this.type = parcel.readString();
        this.owner = (MfpNewsFeedActivityParticipant) parcel.readParcelable(MfpNewsFeedActivityParticipant.class.getClassLoader());
        this.createdAt = ParcelableUtil.readDate(parcel);
        this.likesEnabled = ParcelableUtil.readBoolean(parcel);
        this.likes = (MfpNewsFeedLikeDetails) parcel.readParcelable(MfpNewsFeedLikeDetails.class.getClassLoader());
        this.conversationEnabled = ParcelableUtil.readBoolean(parcel);
        this.isRemovableByUser = ParcelableUtil.readBoolean(parcel);
        this.isCommentableByUser = ParcelableUtil.readBoolean(parcel);
        this.conversation = (MfpNewsFeedActivityConversation) parcel.readParcelable(MfpNewsFeedActivityConversation.class.getClassLoader());
        this.data = (Map) getApiJsonMapper().withType(MapOfStringObject.class).tryMapFrom(parcel.readString());
    }

    public static boolean isSupportedType(String str) {
        return TYPE_TO_CLASS_MAP.containsKey(str);
    }

    public MfpNewsFeedActivityConversation getConversation() {
        return this.conversation;
    }

    public void setConversation(MfpNewsFeedActivityConversation mfpNewsFeedActivityConversation) {
        this.conversation = mfpNewsFeedActivityConversation;
    }

    public boolean isConversationEnabled() {
        return this.conversationEnabled;
    }

    public void setConversationEnabled(boolean z) {
        this.conversationEnabled = z;
    }

    public boolean isIsRemovableByUser() {
        return this.isRemovableByUser;
    }

    public void setIsRemovableByUser(boolean z) {
        this.isRemovableByUser = z;
    }

    public boolean isCommentableByUser() {
        return this.isCommentableByUser;
    }

    public void setIsCommentableByUser(boolean z) {
        this.isCommentableByUser = z;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setCorrelationId(String str) {
        this.correlationId = str;
    }

    public String getCorrelationId() {
        return this.correlationId;
    }

    public MfpNewsFeedLikeDetails getLikes() {
        return this.likes;
    }

    public void setLikes(MfpNewsFeedLikeDetails mfpNewsFeedLikeDetails) {
        this.likes = mfpNewsFeedLikeDetails;
    }

    public boolean isLikesEnabled() {
        return this.likesEnabled;
    }

    public void setLikesEnabled(boolean z) {
        this.likesEnabled = z;
    }

    public MfpNewsFeedActivityParticipant getOwner() {
        return this.owner;
    }

    public void setOwner(MfpNewsFeedActivityParticipant mfpNewsFeedActivityParticipant) {
        this.owner = mfpNewsFeedActivityParticipant;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Map<String, Object> mapDataFromEntryData(MfpNewsFeedActivityEntryData mfpNewsFeedActivityEntryData) {
        Map<String, Object> map = this.data;
        if (map != null) {
            return map;
        }
        if (mfpNewsFeedActivityEntryData == null || !Strings.notEmpty(this.type) || !TYPE_TO_CLASS_MAP.containsKey(this.type)) {
            return null;
        }
        ApiJsonMapper apiJsonMapper2 = new ApiJsonMapper();
        String reverseMap = apiJsonMapper2.reverseMap((Object) mfpNewsFeedActivityEntryData);
        apiJsonMapper2.withType(MapOfStringString.class);
        return (Map) apiJsonMapper2.tryMapFrom(reverseMap);
    }

    public void setData(Map<String, Object> map) {
        this.data = map;
    }

    public <T extends MfpNewsFeedActivityEntryData> T getEntryData() {
        if (this.entryData == null && this.data != null && Strings.notEmpty(this.type) && TYPE_TO_CLASS_MAP.containsKey(this.type)) {
            Class cls = (Class) TYPE_TO_CLASS_MAP.get(this.type);
            ApiJsonMapper apiJsonMapper2 = new ApiJsonMapper();
            String reverseMap = apiJsonMapper2.reverseMap((Object) this.data);
            apiJsonMapper2.withType(cls);
            this.entryData = (MfpNewsFeedActivityEntryData) apiJsonMapper2.tryMapFrom(reverseMap);
        }
        return this.entryData;
    }

    public <T extends MfpNewsFeedActivityEntryData> void setEntryData(T t) {
        this.entryData = t;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MfpNewsFeedActivityEntry) && (obj == this || Strings.equalsIgnoreCase(((MfpNewsFeedActivityEntry) obj).getId(), getId()));
    }

    public int hashCode() {
        return Strings.toString(getId()).hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.correlationId);
        parcel.writeString(this.type);
        parcel.writeParcelable(this.owner, i);
        ParcelableUtil.writeDate(parcel, this.createdAt);
        ParcelableUtil.writeBoolean(parcel, this.likesEnabled);
        parcel.writeParcelable(this.likes, i);
        ParcelableUtil.writeBoolean(parcel, this.conversationEnabled);
        ParcelableUtil.writeBoolean(parcel, this.isRemovableByUser);
        ParcelableUtil.writeBoolean(parcel, this.isCommentableByUser);
        parcel.writeParcelable(this.conversation, i);
        parcel.writeString(getApiJsonMapper().reverseMap((Object) this.data));
    }

    private ApiJsonMapper getApiJsonMapper() {
        if (this.apiJsonMapper == null) {
            this.apiJsonMapper = new ApiJsonMapper();
        }
        return this.apiJsonMapper;
    }
}
