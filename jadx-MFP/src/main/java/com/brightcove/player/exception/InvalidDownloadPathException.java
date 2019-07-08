package com.brightcove.player.exception;

public class InvalidDownloadPathException extends RuntimeException {
    public InvalidDownloadPathException(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Path: ");
        sb.append(str);
        super(sb.toString());
    }
}
