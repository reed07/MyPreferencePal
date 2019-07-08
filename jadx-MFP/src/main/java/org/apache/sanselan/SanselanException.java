package org.apache.sanselan;

public class SanselanException extends Exception {
    static final long serialVersionUID = -1;

    public SanselanException(String str) {
        super(str);
    }

    public SanselanException(String str, Exception exc) {
        super(str, exc);
    }
}
