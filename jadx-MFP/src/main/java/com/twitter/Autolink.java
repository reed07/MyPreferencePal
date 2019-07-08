package com.twitter;

public class Autolink {
    protected String cashtagClass;
    protected String cashtagUrlBase;
    private Extractor extractor;
    protected String hashtagClass;
    protected String hashtagUrlBase;
    protected String invisibleTagAttrs;
    protected LinkAttributeModifier linkAttributeModifier;
    protected LinkTextModifier linkTextModifier;
    protected String listClass;
    protected String listUrlBase;
    protected boolean noFollow;
    protected String symbolTag;
    protected String textWithSymbolTag;
    protected String urlClass;
    protected String urlTarget;
    protected String usernameClass;
    protected boolean usernameIncludeSymbol;
    protected String usernameUrlBase;

    public interface LinkAttributeModifier {
    }

    public interface LinkTextModifier {
    }

    public Autolink() {
        this.urlClass = null;
        this.noFollow = true;
        this.usernameIncludeSymbol = false;
        this.symbolTag = null;
        this.textWithSymbolTag = null;
        this.urlTarget = null;
        this.linkAttributeModifier = null;
        this.linkTextModifier = null;
        this.extractor = new Extractor();
        this.urlClass = null;
        this.listClass = "tweet-url list-slug";
        this.usernameClass = "tweet-url username";
        this.hashtagClass = "tweet-url hashtag";
        this.cashtagClass = "tweet-url cashtag";
        this.usernameUrlBase = "https://twitter.com/";
        this.listUrlBase = "https://twitter.com/";
        this.hashtagUrlBase = "https://twitter.com/#!/search?q=%23";
        this.cashtagUrlBase = "https://twitter.com/#!/search?q=%24";
        this.invisibleTagAttrs = "style='position:absolute;left:-9999px;'";
        this.extractor.setExtractURLWithoutProtocol(false);
    }
}
