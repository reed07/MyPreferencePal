package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;

/* compiled from: IMASDK */
public final class adl implements ImaSdkSettings {
    private boolean autoPlayAdBreaks = true;
    private boolean debugMode = false;
    private transient String language = "en";
    private int numRedirects = 4;
    private String playerType;
    private String playerVersion;
    private String ppid;
    private transient boolean restrictToCustomPlayer;

    public final String getPpid() {
        return this.ppid;
    }

    public final void setPpid(String str) {
        this.ppid = str;
    }

    public final int getMaxRedirects() {
        return this.numRedirects;
    }

    public final void setMaxRedirects(int i) {
        this.numRedirects = i;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final void setLanguage(String str) {
        this.language = str;
    }

    public final boolean doesRestrictToCustomPlayer() {
        return this.restrictToCustomPlayer;
    }

    public final void setRestrictToCustomPlayer(boolean z) {
        this.restrictToCustomPlayer = z;
    }

    public final String getPlayerType() {
        return this.playerType;
    }

    public final void setPlayerType(String str) {
        this.playerType = str;
    }

    public final String getPlayerVersion() {
        return this.playerVersion;
    }

    public final void setPlayerVersion(String str) {
        this.playerVersion = str;
    }

    public final boolean getAutoPlayAdBreaks() {
        return this.autoPlayAdBreaks;
    }

    public final void setAutoPlayAdBreaks(boolean z) {
        this.autoPlayAdBreaks = z;
    }

    public final void setDebugMode(boolean z) {
        this.debugMode = z;
    }

    public final boolean isDebugMode() {
        return this.debugMode;
    }

    public final String toString() {
        String str = this.ppid;
        int i = this.numRedirects;
        String str2 = this.playerType;
        String str3 = this.playerVersion;
        String str4 = this.language;
        boolean z = this.restrictToCustomPlayer;
        boolean z2 = this.autoPlayAdBreaks;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 136 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
        sb.append("ImaSdkSettings [ppid=");
        sb.append(str);
        sb.append(", numRedirects=");
        sb.append(i);
        sb.append(", playerType=");
        sb.append(str2);
        sb.append(", playerVersion=");
        sb.append(str3);
        sb.append(", language=");
        sb.append(str4);
        sb.append(", restrictToCustom=");
        sb.append(z);
        sb.append(", autoPlayAdBreaks=");
        sb.append(z2);
        sb.append("]");
        return sb.toString();
    }
}
