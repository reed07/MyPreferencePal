package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import java.util.List;

public class MfpNewsFeedActivityEntryListContainer {
    @Expose
    private List<MfpNewsFeedActivityEntry> entries;
    @Expose
    private String link;

    public MfpNewsFeedActivityEntryListContainer() {
    }

    public MfpNewsFeedActivityEntryListContainer(List<MfpNewsFeedActivityEntry> list, String str) {
        this.entries = list;
        this.link = str;
    }

    public List<MfpNewsFeedActivityEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(List<MfpNewsFeedActivityEntry> list) {
        this.entries = list;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
