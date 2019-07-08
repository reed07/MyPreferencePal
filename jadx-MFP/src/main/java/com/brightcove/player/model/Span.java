package com.brightcove.player.model;

public class Span extends StyledElement {
    private String text;

    public Span() {
    }

    public Span(StyledElement styledElement) {
        if (styledElement != null) {
            this.id = styledElement.getID();
            this.styleName = styledElement.getStyleName();
            this.color = styledElement.getColor();
            this.backgroundColor = styledElement.getBackgroundColor();
            this.fontSize = styledElement.getFontSize();
            this.fontStyle = styledElement.getFontStyle();
            this.fontFamily = styledElement.getFontFamily();
            return;
        }
        throw new IllegalArgumentException("must provide a StyledElement");
    }

    public Span(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
