package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedActivityEntryPostData {
    private static final String STATUS_UPDATE_TYPE = "status_update";
    @Expose
    private EntryDetails item;

    private class EntryData {
        @Expose
        private String text;

        public EntryData(String str) {
            this.text = str;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }
    }

    private class EntryDetails {
        @Expose
        private EntryData data;
        @Expose
        private OwnerDetails owner;
        @Expose
        private String type;

        public EntryDetails(String str, OwnerDetails ownerDetails, EntryData entryData) {
            this.type = str;
            this.owner = ownerDetails;
            this.data = entryData;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public OwnerDetails getOwner() {
            return this.owner;
        }

        public void setOwner(OwnerDetails ownerDetails) {
            this.owner = ownerDetails;
        }

        public EntryData getData() {
            return this.data;
        }

        public void setData(EntryData entryData) {
            this.data = entryData;
        }
    }

    private class OwnerDetails {
        @Expose
        private String userId;

        public OwnerDetails(String str) {
            this.userId = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }
    }

    public MfpNewsFeedActivityEntryPostData(String str, String str2) {
        this.item = new EntryDetails("status_update", new OwnerDetails(str2), new EntryData(str));
    }

    public EntryDetails getItem() {
        return this.item;
    }

    public void setItem(EntryDetails entryDetails) {
        this.item = entryDetails;
    }
}
