package com.brightcove.player.controller;

public class NoSourceFoundException extends Exception {
    public NoSourceFoundException() {
    }

    public NoSourceFoundException(String str) {
        super(str);
    }

    public NoSourceFoundException(String str, Throwable th) {
        super(str, th);
    }
}
