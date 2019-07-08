package com.brightcove.player.model;

import java.util.Locale;

public class Region extends StyledElement {
    private DisplayAlign displayAlign;
    private Length extentX;
    private Length extentY;
    private Length originX;
    private Length originY;

    public enum DisplayAlign {
        BEFORE,
        CENTER,
        AFTER;

        public static DisplayAlign fromString(String str) {
            return valueOf(str.toUpperCase(Locale.US));
        }
    }

    public Length getOriginX() {
        return this.originX;
    }

    public void setOriginX(Length length) {
        this.originX = length;
    }

    public Length getOriginY() {
        return this.originY;
    }

    public void setOriginY(Length length) {
        this.originY = length;
    }

    public Length getExtentX() {
        return this.extentX;
    }

    public void setExtentX(Length length) {
        this.extentX = length;
    }

    public Length getExtentY() {
        return this.extentY;
    }

    public void setExtentY(Length length) {
        this.extentY = length;
    }

    public DisplayAlign getDisplayAlign() {
        return this.displayAlign;
    }

    public void setDisplayAlign(DisplayAlign displayAlign2) {
        this.displayAlign = displayAlign2;
    }
}
