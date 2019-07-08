package com.nimbusds.jose;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class JWEObject extends JOSEObject {
    private static final long serialVersionUID = 1;

    public enum State {
        UNENCRYPTED,
        ENCRYPTED,
        DECRYPTED
    }
}
