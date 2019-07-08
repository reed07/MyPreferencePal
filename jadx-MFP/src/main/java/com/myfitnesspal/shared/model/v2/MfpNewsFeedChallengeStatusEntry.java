package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import java.util.List;

public class MfpNewsFeedChallengeStatusEntry implements MfpNewsFeedActivityEntryData {
    @Expose
    private List<MfpNewsFeedChallengeLink> links;
    @Expose
    private String text;
    @Expose
    private String title;

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public List<MfpNewsFeedChallengeLink> getLinks() {
        return this.links;
    }

    public void setLinks(List<MfpNewsFeedChallengeLink> list) {
        this.links = list;
    }
}
