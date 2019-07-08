package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbom;
import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

public final class zzbol<T_WRAPPER extends zzbom<T_ENGINE>, T_ENGINE> {
    private static final Logger logger = Logger.getLogger(zzbol.class.getName());
    private static final List<Provider> zzfjq;
    public static final zzbol<zzbon, Cipher> zzfjr = new zzbol<>(new zzbon());
    public static final zzbol<zzbor, Mac> zzfjs = new zzbol<>(new zzbor());
    private static final zzbol<zzbot, Signature> zzfjt = new zzbol<>(new zzbot());
    private static final zzbol<zzbos, MessageDigest> zzfju = new zzbol<>(new zzbos());
    public static final zzbol<zzboo, KeyAgreement> zzfjv = new zzbol<>(new zzboo());
    public static final zzbol<zzboq, KeyPairGenerator> zzfjw = new zzbol<>(new zzboq());
    public static final zzbol<zzbop, KeyFactory> zzfjx = new zzbol<>(new zzbop());
    private T_WRAPPER zzfjy;
    private List<Provider> zzfjz = zzfjq;
    private boolean zzfka = true;

    private zzbol(T_WRAPPER t_wrapper) {
        this.zzfjy = t_wrapper;
    }

    public final T_ENGINE zzfu(String str) throws GeneralSecurityException {
        for (Provider provider : this.zzfjz) {
            if (zza(str, provider)) {
                return this.zzfjy.zzb(str, provider);
            }
        }
        if (this.zzfka) {
            return this.zzfjy.zzb(str, null);
        }
        throw new GeneralSecurityException("No good Provider found.");
    }

    private final boolean zza(String str, Provider provider) {
        try {
            this.zzfjy.zzb(str, provider);
            return true;
        } catch (Exception e) {
            zzbpe.zze(e);
            return false;
        }
    }

    static {
        if (zzbpc.zzakd()) {
            String[] strArr = {ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL"};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                String str = strArr[i];
                Provider provider = Security.getProvider(str);
                if (provider != null) {
                    arrayList.add(provider);
                } else {
                    logger.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", new Object[]{str}));
                }
            }
            zzfjq = arrayList;
        } else {
            zzfjq = new ArrayList();
        }
    }
}
