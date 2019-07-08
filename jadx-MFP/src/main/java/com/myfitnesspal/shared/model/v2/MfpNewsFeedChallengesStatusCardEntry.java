package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.home.model.MfpNewsFeedLinkDesc;
import java.util.List;

public class MfpNewsFeedChallengesStatusCardEntry implements MfpNewsFeedActivityEntryData {
    @Expose
    private MfpNewsFeedAssetDesc asset;
    @Expose
    private List<MfpNewsFeedLinkDesc> buttons;
    @Expose
    private String cardType;
    @Expose
    private String description;
    @Expose
    private boolean isDismissibleByUser;
    @Expose
    private List<MfpNewsFeedChallengeLink> links;
    @Expose
    private String title;

    public String getText() {
        return getDescription();
    }

    public String getAnalyticsTag() {
        return this.cardType;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String str) {
        this.cardType = str;
    }

    public boolean isDismissibleByUser() {
        return this.isDismissibleByUser;
    }

    public void setIsDismissibleByUser(boolean z) {
        this.isDismissibleByUser = z;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public List<MfpNewsFeedLinkDesc> getButtons() {
        return this.buttons;
    }

    public void setButtons(List<MfpNewsFeedLinkDesc> list) {
        this.buttons = list;
    }

    public MfpNewsFeedAssetDesc getAsset() {
        return this.asset;
    }

    public void setAsset(MfpNewsFeedAssetDesc mfpNewsFeedAssetDesc) {
        this.asset = mfpNewsFeedAssetDesc;
    }

    public List<MfpNewsFeedChallengeLink> getLinks() {
        return this.links;
    }

    public void setLinks(List<MfpNewsFeedChallengeLink> list) {
        this.links = list;
    }
}
