package com.brightcove.player.captioning;

import java.util.BitSet;

final class AutoParcel_BrightcoveCaptionStyle extends BrightcoveCaptionStyle {
    private final int backgroundColor;
    private final int backgroundOpacity;
    private final int edgeColor;
    private final int edgeType;
    private final String fontSize;
    private final int foregroundColor;
    private final int foregroundOpacity;
    private final int preset;
    private final String typeface;
    private final int windowColor;
    private final int windowOpacity;

    static final class Builder implements com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder {
        private int backgroundColor;
        private int backgroundOpacity;
        private int edgeColor;
        private int edgeType;
        private String fontSize;
        private int foregroundColor;
        private int foregroundOpacity;
        private int preset;
        private final BitSet set$ = new BitSet();
        private String typeface;
        private int windowColor;
        private int windowOpacity;

        Builder() {
        }

        Builder(BrightcoveCaptionStyle brightcoveCaptionStyle) {
            preset(brightcoveCaptionStyle.preset());
            fontSize(brightcoveCaptionStyle.fontSize());
            typeface(brightcoveCaptionStyle.typeface());
            foregroundColor(brightcoveCaptionStyle.foregroundColor());
            foregroundOpacity(brightcoveCaptionStyle.foregroundOpacity());
            edgeType(brightcoveCaptionStyle.edgeType());
            edgeColor(brightcoveCaptionStyle.edgeColor());
            backgroundColor(brightcoveCaptionStyle.backgroundColor());
            backgroundOpacity(brightcoveCaptionStyle.backgroundOpacity());
            windowColor(brightcoveCaptionStyle.windowColor());
            windowOpacity(brightcoveCaptionStyle.windowOpacity());
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder preset(int i) {
            this.preset = i;
            this.set$.set(0);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder fontSize(String str) {
            this.fontSize = str;
            this.set$.set(1);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder typeface(String str) {
            this.typeface = str;
            this.set$.set(2);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder foregroundColor(int i) {
            this.foregroundColor = i;
            this.set$.set(3);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder foregroundOpacity(int i) {
            this.foregroundOpacity = i;
            this.set$.set(4);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder edgeType(int i) {
            this.edgeType = i;
            this.set$.set(5);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder edgeColor(int i) {
            this.edgeColor = i;
            this.set$.set(6);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder backgroundColor(int i) {
            this.backgroundColor = i;
            this.set$.set(7);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder backgroundOpacity(int i) {
            this.backgroundOpacity = i;
            this.set$.set(8);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder windowColor(int i) {
            this.windowColor = i;
            this.set$.set(9);
            return this;
        }

        public com.brightcove.player.captioning.BrightcoveCaptionStyle.Builder windowOpacity(int i) {
            this.windowOpacity = i;
            this.set$.set(10);
            return this;
        }

        public BrightcoveCaptionStyle build() {
            if (this.set$.cardinality() < 11) {
                String[] strArr = {"preset", "fontSize", "typeface", "foregroundColor", "foregroundOpacity", "edgeType", "edgeColor", "backgroundColor", "backgroundOpacity", "windowColor", "windowOpacity"};
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 11; i++) {
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
            AutoParcel_BrightcoveCaptionStyle autoParcel_BrightcoveCaptionStyle = new AutoParcel_BrightcoveCaptionStyle(this.preset, this.fontSize, this.typeface, this.foregroundColor, this.foregroundOpacity, this.edgeType, this.edgeColor, this.backgroundColor, this.backgroundOpacity, this.windowColor, this.windowOpacity);
            autoParcel_BrightcoveCaptionStyle.validate();
            return autoParcel_BrightcoveCaptionStyle;
        }
    }

    private AutoParcel_BrightcoveCaptionStyle(int i, String str, String str2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.preset = i;
        if (str != null) {
            this.fontSize = str;
            if (str2 != null) {
                this.typeface = str2;
                this.foregroundColor = i2;
                this.foregroundOpacity = i3;
                this.edgeType = i4;
                this.edgeColor = i5;
                this.backgroundColor = i6;
                this.backgroundOpacity = i7;
                this.windowColor = i8;
                this.windowOpacity = i9;
                return;
            }
            throw new NullPointerException("Null typeface");
        }
        throw new NullPointerException("Null fontSize");
    }

    public int preset() {
        return this.preset;
    }

    public String fontSize() {
        return this.fontSize;
    }

    public String typeface() {
        return this.typeface;
    }

    public int foregroundColor() {
        return this.foregroundColor;
    }

    public int foregroundOpacity() {
        return this.foregroundOpacity;
    }

    public int edgeType() {
        return this.edgeType;
    }

    public int edgeColor() {
        return this.edgeColor;
    }

    public int backgroundColor() {
        return this.backgroundColor;
    }

    public int backgroundOpacity() {
        return this.backgroundOpacity;
    }

    public int windowColor() {
        return this.windowColor;
    }

    public int windowOpacity() {
        return this.windowOpacity;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BrightcoveCaptionStyle{preset=");
        sb.append(this.preset);
        sb.append(", ");
        sb.append("fontSize=");
        sb.append(this.fontSize);
        sb.append(", ");
        sb.append("typeface=");
        sb.append(this.typeface);
        sb.append(", ");
        sb.append("foregroundColor=");
        sb.append(this.foregroundColor);
        sb.append(", ");
        sb.append("foregroundOpacity=");
        sb.append(this.foregroundOpacity);
        sb.append(", ");
        sb.append("edgeType=");
        sb.append(this.edgeType);
        sb.append(", ");
        sb.append("edgeColor=");
        sb.append(this.edgeColor);
        sb.append(", ");
        sb.append("backgroundColor=");
        sb.append(this.backgroundColor);
        sb.append(", ");
        sb.append("backgroundOpacity=");
        sb.append(this.backgroundOpacity);
        sb.append(", ");
        sb.append("windowColor=");
        sb.append(this.windowColor);
        sb.append(", ");
        sb.append("windowOpacity=");
        sb.append(this.windowOpacity);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BrightcoveCaptionStyle)) {
            return false;
        }
        BrightcoveCaptionStyle brightcoveCaptionStyle = (BrightcoveCaptionStyle) obj;
        if (!(this.preset == brightcoveCaptionStyle.preset() && this.fontSize.equals(brightcoveCaptionStyle.fontSize()) && this.typeface.equals(brightcoveCaptionStyle.typeface()) && this.foregroundColor == brightcoveCaptionStyle.foregroundColor() && this.foregroundOpacity == brightcoveCaptionStyle.foregroundOpacity() && this.edgeType == brightcoveCaptionStyle.edgeType() && this.edgeColor == brightcoveCaptionStyle.edgeColor() && this.backgroundColor == brightcoveCaptionStyle.backgroundColor() && this.backgroundOpacity == brightcoveCaptionStyle.backgroundOpacity() && this.windowColor == brightcoveCaptionStyle.windowColor() && this.windowOpacity == brightcoveCaptionStyle.windowOpacity())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((((((((((((((this.preset ^ 1000003) * 1000003) ^ this.fontSize.hashCode()) * 1000003) ^ this.typeface.hashCode()) * 1000003) ^ this.foregroundColor) * 1000003) ^ this.foregroundOpacity) * 1000003) ^ this.edgeType) * 1000003) ^ this.edgeColor) * 1000003) ^ this.backgroundColor) * 1000003) ^ this.backgroundOpacity) * 1000003) ^ this.windowColor) * 1000003) ^ this.windowOpacity;
    }
}
