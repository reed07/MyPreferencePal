package io.uacf.inbox.internal.model;

import com.google.gson.annotations.Expose;

public class NotificationContent {
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

        public static final class Builder {
        }

        public PlainText getPlainText() {
            return this.plainText;
        }

        public Link getLink() {
            return this.link;
        }

        public Image getImage() {
            return this.image;
        }
    }

    public static final class Builder {
    }

    public static class Image {
        @Expose
        private Link link;
        @Expose
        private String uri;

        public static final class Builder {
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

        public static final class Builder {
        }

        public String getDeeplink() {
            return this.deeplink;
        }
    }

    public static class PlainText {
        @Expose
        private String text;

        public static final class Builder {
        }

        public String getText() {
            return this.text;
        }
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
