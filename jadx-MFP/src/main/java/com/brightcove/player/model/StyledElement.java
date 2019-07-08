package com.brightcove.player.model;

public class StyledElement extends Element {
    protected String backgroundColor;
    protected String color;
    protected String fontFamily;
    protected Length fontSize;
    protected FontStyle fontStyle;
    protected FontWeight fontWeight;
    protected String styleName;
    protected TextAlign textAlign;
    protected TextDecoration textDecoration;

    public enum FontStyle {
        UNDEFINED,
        NORMAL,
        ITALIC
    }

    public enum FontWeight {
        UNDEFINED,
        NORMAL,
        BOLD
    }

    public enum TextAlign {
        UNDEFINED,
        START,
        LEFT,
        CENTER,
        RIGHT,
        END
    }

    public enum TextDecoration {
        NONE,
        UNDERLINE,
        NOUNDERLINE,
        LINETHROUGH,
        NOLINETHROUGH,
        OVERLINE,
        NOOVERLINE
    }

    public enum Unit {
        UNDEFINED,
        EMS,
        PERCENT,
        PX,
        PT,
        LINE;

        public static Unit fromString(String str) {
            if (str == null) {
                return UNDEFINED;
            }
            if (str.equals("%")) {
                return PERCENT;
            }
            return valueOf(str);
        }
    }

    public String getStyleName() {
        return this.styleName;
    }

    public void setStyleName(String str) {
        this.styleName = str;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public Length getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(Length length) {
        this.fontSize = length;
    }

    public FontStyle getFontStyle() {
        return this.fontStyle;
    }

    public void setFontStyle(FontStyle fontStyle2) {
        this.fontStyle = fontStyle2;
    }

    public TextAlign getTextAlign() {
        return this.textAlign;
    }

    public void setTextAlign(TextAlign textAlign2) {
        this.textAlign = textAlign2;
    }

    public FontWeight getFontWeight() {
        return this.fontWeight;
    }

    public void setFontWeight(FontWeight fontWeight2) {
        this.fontWeight = fontWeight2;
    }

    public TextDecoration getTextDecoration() {
        return this.textDecoration;
    }

    public void setTextDecoration(TextDecoration textDecoration2) {
        this.textDecoration = textDecoration2;
    }

    public void setFontFamily(String str) {
        this.fontFamily = str;
    }

    public String getFontFamily() {
        return this.fontFamily;
    }
}
