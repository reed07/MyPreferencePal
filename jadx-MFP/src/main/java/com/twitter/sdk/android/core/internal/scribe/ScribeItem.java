package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ScribeItem implements Serializable {
    @SerializedName("card_event")
    public final CardEvent cardEvent;
    @SerializedName("description")
    public final String description;
    @SerializedName("id")
    public final Long id;
    @SerializedName("item_type")
    public final Integer itemType;
    @SerializedName("media_details")
    public final MediaDetails mediaDetails;

    public static class Builder {
    }

    public static class CardEvent implements Serializable {
        @SerializedName("promotion_card_type")
        final int promotionCardType;

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            if (this.promotionCardType != ((CardEvent) obj).promotionCardType) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return this.promotionCardType;
        }
    }

    public static class MediaDetails implements Serializable {
        @SerializedName("content_id")
        public final long contentId;
        @SerializedName("media_type")
        public final int mediaType;
        @SerializedName("publisher_id")
        public final long publisherId;

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MediaDetails mediaDetails = (MediaDetails) obj;
            if (this.contentId != mediaDetails.contentId || this.mediaType != mediaDetails.mediaType) {
                return false;
            }
            if (this.publisherId != mediaDetails.publisherId) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            long j = this.contentId;
            int i = ((((int) (j ^ (j >>> 32))) * 31) + this.mediaType) * 31;
            long j2 = this.publisherId;
            return i + ((int) (j2 ^ (j2 >>> 32)));
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScribeItem scribeItem = (ScribeItem) obj;
        Integer num = this.itemType;
        if (num == null ? scribeItem.itemType != null : !num.equals(scribeItem.itemType)) {
            return false;
        }
        Long l = this.id;
        if (l == null ? scribeItem.id != null : !l.equals(scribeItem.id)) {
            return false;
        }
        String str = this.description;
        if (str == null ? scribeItem.description != null : !str.equals(scribeItem.description)) {
            return false;
        }
        CardEvent cardEvent2 = this.cardEvent;
        if (cardEvent2 == null ? scribeItem.cardEvent != null : !cardEvent2.equals(scribeItem.cardEvent)) {
            return false;
        }
        MediaDetails mediaDetails2 = this.mediaDetails;
        if (mediaDetails2 == null ? scribeItem.mediaDetails != null : !mediaDetails2.equals(scribeItem.mediaDetails)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        Integer num = this.itemType;
        int i = 0;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        Long l = this.id;
        int hashCode2 = (hashCode + (l != null ? l.hashCode() : 0)) * 31;
        String str = this.description;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        CardEvent cardEvent2 = this.cardEvent;
        int hashCode4 = (hashCode3 + (cardEvent2 != null ? cardEvent2.hashCode() : 0)) * 31;
        MediaDetails mediaDetails2 = this.mediaDetails;
        if (mediaDetails2 != null) {
            i = mediaDetails2.hashCode();
        }
        return hashCode4 + i;
    }
}
