package com.brightcove.player.edge;

public class VideoParseException extends Exception {
    public VideoParseException() {
    }

    public VideoParseException(String str) {
        super(str);
    }

    public VideoParseException(String str, Throwable th) {
        super(str, th);
    }
}
