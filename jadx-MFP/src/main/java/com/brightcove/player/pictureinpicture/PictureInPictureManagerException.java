package com.brightcove.player.pictureinpicture;

public class PictureInPictureManagerException extends RuntimeException {
    public PictureInPictureManagerException() {
    }

    public PictureInPictureManagerException(String str) {
        super(str);
    }

    public PictureInPictureManagerException(String str, Throwable th) {
        super(str, th);
    }

    public PictureInPictureManagerException(Throwable th) {
        super(th);
    }
}
