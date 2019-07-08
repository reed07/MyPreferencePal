package com.nimbusds.jwt;

import com.nimbusds.jose.JWEObject;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class EncryptedJWT extends JWEObject implements JWT {
    private static final long serialVersionUID = 1;
}
