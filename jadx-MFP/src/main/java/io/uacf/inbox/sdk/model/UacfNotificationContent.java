package io.uacf.inbox.sdk.model;

import com.google.gson.annotations.Expose;

public class UacfNotificationContent {
    @Expose
    private Body body;
    @Expose
    private Image primaryImage;
    @Expose
    private Image secondaryImage;

    public static class Body {
        @Expose
        private Image image;
        @Expose
        private Link link;
        @Expose
        private PlainText plainText;

        public Body(PlainText plainText2, Link link2, Image image2) {
            this.plainText = plainText2;
            this.link = link2;
            this.image = image2;
        }

        public PlainText getPlainText() {
            return this.plainText;
        }

        public Link getLink() {
            return this.link;
        }
    }

    public static class Image {
        @Expose
        private Link link;
        @Expose
        private String uri;

        public Image(String str, Link link2) {
            this.uri = str;
            this.link = link2;
        }

        public String getUri() {
            return this.uri;
        }

        public Link getLink() {
            return this.link;
        }
    }

    public static class Link {
        @Expose
        private String deeplink;

        public Link(String str) {
            this.deeplink = str;
        }

        public String getDeeplink() {
            return this.deeplink;
        }
    }

    public static class PlainText {
        @Expose
        private String text;

        public PlainText(String str) {
            this.text = str;
        }

        public String getText() {
            return this.text;
        }
    }

    public UacfNotificationContent(Body body2, Image image, Image image2) {
        this.body = body2;
        this.primaryImage = image;
        this.secondaryImage = image2;
    }

    public Body getBody() {
        return this.body;
    }

    public Image getPrimaryImage() {
        return this.primaryImage;
    }

    public Image getSecondaryImage() {
        return this.secondaryImage;
    }
}
