package com.google.firebase.iid;

import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public final class zza {
    public static KeyPair zzb() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance(GooglePlayConstants.BILLING_VERIFICATION_KEY_FACTORY_ALGORITHM);
            instance.initialize(2048);
            return instance.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
