package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;

public final class GameRequestContent implements ShareModel {
    public static final Creator<GameRequestContent> CREATOR = new Creator<GameRequestContent>() {
        public GameRequestContent createFromParcel(Parcel parcel) {
            return new GameRequestContent(parcel);
        }

        public GameRequestContent[] newArray(int i) {
            return new GameRequestContent[i];
        }
    };
    private final ActionType actionType;
    private final String data;
    private final Filters filters;
    private final String message;
    private final String objectId;
    private final List<String> recipients;
    private final List<String> suggestions;
    private final String title;

    public enum ActionType {
        SEND,
        ASKFOR,
        TURN
    }

    public static class Builder implements ShareModelBuilder<GameRequestContent, Builder> {
        /* access modifiers changed from: private */
        public ActionType actionType;
        /* access modifiers changed from: private */
        public String data;
        /* access modifiers changed from: private */
        public Filters filters;
        /* access modifiers changed from: private */
        public String message;
        /* access modifiers changed from: private */
        public String objectId;
        /* access modifiers changed from: private */
        public List<String> recipients;
        /* access modifiers changed from: private */
        public List<String> suggestions;
        /* access modifiers changed from: private */
        public String title;

        public Builder setMessage(String str) {
            this.message = str;
            return this;
        }

        public Builder setTo(String str) {
            if (str != null) {
                this.recipients = Arrays.asList(str.split(","));
            }
            return this;
        }

        public Builder setRecipients(List<String> list) {
            this.recipients = list;
            return this;
        }

        public Builder setData(String str) {
            this.data = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setActionType(ActionType actionType2) {
            this.actionType = actionType2;
            return this;
        }

        public Builder setObjectId(String str) {
            this.objectId = str;
            return this;
        }

        public Builder setFilters(Filters filters2) {
            this.filters = filters2;
            return this;
        }

        public Builder setSuggestions(List<String> list) {
            this.suggestions = list;
            return this;
        }

        public GameRequestContent build() {
            return new GameRequestContent(this);
        }

        public Builder readFrom(GameRequestContent gameRequestContent) {
            if (gameRequestContent == null) {
                return this;
            }
            return setMessage(gameRequestContent.getMessage()).setRecipients(gameRequestContent.getRecipients()).setTitle(gameRequestContent.getTitle()).setData(gameRequestContent.getData()).setActionType(gameRequestContent.getActionType()).setObjectId(gameRequestContent.getObjectId()).setFilters(gameRequestContent.getFilters()).setSuggestions(gameRequestContent.getSuggestions());
        }

        /* access modifiers changed from: 0000 */
        public Builder readFrom(Parcel parcel) {
            return readFrom((GameRequestContent) parcel.readParcelable(GameRequestContent.class.getClassLoader()));
        }
    }

    public enum Filters {
        APP_USERS,
        APP_NON_USERS
    }

    public int describeContents() {
        return 0;
    }

    private GameRequestContent(Builder builder) {
        this.message = builder.message;
        this.recipients = builder.recipients;
        this.title = builder.title;
        this.data = builder.data;
        this.actionType = builder.actionType;
        this.objectId = builder.objectId;
        this.filters = builder.filters;
        this.suggestions = builder.suggestions;
    }

    GameRequestContent(Parcel parcel) {
        this.message = parcel.readString();
        this.recipients = parcel.createStringArrayList();
        this.title = parcel.readString();
        this.data = parcel.readString();
        this.actionType = (ActionType) parcel.readSerializable();
        this.objectId = parcel.readString();
        this.filters = (Filters) parcel.readSerializable();
        this.suggestions = parcel.createStringArrayList();
        parcel.readStringList(this.suggestions);
    }

    public String getMessage() {
        return this.message;
    }

    public String getTo() {
        if (getRecipients() != null) {
            return TextUtils.join(",", getRecipients());
        }
        return null;
    }

    public List<String> getRecipients() {
        return this.recipients;
    }

    public String getTitle() {
        return this.title;
    }

    public String getData() {
        return this.data;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public Filters getFilters() {
        return this.filters;
    }

    public List<String> getSuggestions() {
        return this.suggestions;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.message);
        parcel.writeStringList(this.recipients);
        parcel.writeString(this.title);
        parcel.writeString(this.data);
        parcel.writeSerializable(this.actionType);
        parcel.writeString(this.objectId);
        parcel.writeSerializable(this.filters);
        parcel.writeStringList(this.suggestions);
    }
}
