package com.brightcove.player.captioning;

import java.util.BitSet;

final class AutoParcel_BrightcoveCaptionFormat extends BrightcoveCaptionFormat {
    private final boolean hasInBandMetadataTrackDispatchType;
    private final boolean isDefault;
    private final String language;
    private final String type;

    static final class Builder implements com.brightcove.player.captioning.BrightcoveCaptionFormat.Builder {
        private boolean hasInBandMetadataTrackDispatchType;
        private boolean isDefault;
        private String language;
        private final BitSet set$ = new BitSet();
        private String type;

        Builder() {
        }

        Builder(BrightcoveCaptionFormat brightcoveCaptionFormat) {
            type(brightcoveCaptionFormat.type());
            language(brightcoveCaptionFormat.language());
            hasInBandMetadataTrackDispatchType(brightcoveCaptionFormat.hasInBandMetadataTrackDispatchType());
            isDefault(brightcoveCaptionFormat.isDefault());
        }

        public com.brightcove.player.captioning.BrightcoveCaptionFormat.Builder type(String str) {
            this.type = str;
            this.set$.set(0);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionFormat.Builder language(String str) {
            this.language = str;
            this.set$.set(1);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionFormat.Builder hasInBandMetadataTrackDispatchType(boolean z) {
            this.hasInBandMetadataTrackDispatchType = z;
            this.set$.set(2);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionFormat.Builder isDefault(boolean z) {
            this.isDefault = z;
            this.set$.set(3);
            return this;
        }

        public BrightcoveCaptionFormat build() {
            if (this.set$.cardinality() < 4) {
                String[] strArr = {"type", "language", "hasInBandMetadataTrackDispatchType", "isDefault"};
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    if (!this.set$.get(i)) {
                        sb.append(' ');
                        sb.append(strArr[i]);
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Missing required properties:");
                sb2.append(sb);
                throw new IllegalStateException(sb2.toString());
            }
            AutoParcel_BrightcoveCaptionFormat autoParcel_BrightcoveCaptionFormat = new AutoParcel_BrightcoveCaptionFormat(this.type, this.language, this.hasInBandMetadataTrackDispatchType, this.isDefault);
            autoParcel_BrightcoveCaptionFormat.validate();
            return autoParcel_BrightcoveCaptionFormat;
        }
    }

    private AutoParcel_BrightcoveCaptionFormat(String str, String str2, boolean z, boolean z2) {
        if (str != null) {
            this.type = str;
            if (str2 != null) {
                this.language = str2;
                this.hasInBandMetadataTrackDispatchType = z;
                this.isDefault = z2;
                return;
            }
            throw new NullPointerException("Null language");
        }
        throw new NullPointerException("Null type");
    }

    public String type() {
        return this.type;
    }

    public String language() {
        return this.language;
    }

    public boolean hasInBandMetadataTrackDispatchType() {
        return this.hasInBandMetadataTrackDispatchType;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BrightcoveCaptionFormat{type=");
        sb.append(this.type);
        sb.append(", ");
        sb.append("language=");
        sb.append(this.language);
        sb.append(", ");
        sb.append("hasInBandMetadataTrackDispatchType=");
        sb.append(this.hasInBandMetadataTrackDispatchType);
        sb.append(", ");
        sb.append("isDefault=");
        sb.append(this.isDefault);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BrightcoveCaptionFormat)) {
            return false;
        }
        BrightcoveCaptionFormat brightcoveCaptionFormat = (BrightcoveCaptionFormat) obj;
        if (!this.type.equals(brightcoveCaptionFormat.type()) || !this.language.equals(brightcoveCaptionFormat.language()) || this.hasInBandMetadataTrackDispatchType != brightcoveCaptionFormat.hasInBandMetadataTrackDispatchType() || this.isDefault != brightcoveCaptionFormat.isDefault()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 1231;
        int hashCode = (((((this.type.hashCode() ^ 1000003) * 1000003) ^ this.language.hashCode()) * 1000003) ^ (this.hasInBandMetadataTrackDispatchType ? 1231 : 1237)) * 1000003;
        if (!this.isDefault) {
            i = 1237;
        }
        return hashCode ^ i;
    }
}
