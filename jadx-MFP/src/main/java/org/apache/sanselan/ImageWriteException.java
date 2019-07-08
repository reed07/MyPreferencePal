package org.apache.sanselan;

public class ImageWriteException extends SanselanException {
    static final long serialVersionUID = -1;

    public ImageWriteException(String str) {
        super(str);
    }

    public ImageWriteException(String str, Exception exc) {
        super(str, exc);
    }
}
