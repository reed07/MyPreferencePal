package org.apache.sanselan;

public class ImageReadException extends SanselanException {
    static final long serialVersionUID = -1;

    public ImageReadException(String str) {
        super(str);
    }

    public ImageReadException(String str, Exception exc) {
        super(str, exc);
    }
}
