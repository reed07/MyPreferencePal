package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JWEDecrypter;

public class ECDHDecrypter extends ECDHCryptoProvider implements CriticalHeaderParamsAware, JWEDecrypter {
}
