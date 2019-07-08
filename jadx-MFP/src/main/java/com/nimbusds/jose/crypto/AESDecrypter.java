package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JWEDecrypter;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class AESDecrypter extends AESCryptoProvider implements CriticalHeaderParamsAware, JWEDecrypter {
}
