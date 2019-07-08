package com.brightcove.player.drm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import java.io.IOException;

public interface LicenseManager {
    public static final String CR_TOKEN_HEADER = "X-BC-CRT-CONFIG";

    @Nullable
    byte[] downloadLicense(String str, @NonNull CustomerRightsToken customerRightsToken) throws IOException, InterruptedException, DrmException;

    @Nullable
    Pair<Long, Long> getRemainingLicenseDuration(byte[] bArr) throws DrmException;

    void releaseLicense(@Nullable byte[] bArr) throws DrmException;

    void releaseResources();

    @Nullable
    byte[] renewLicense(byte[] bArr) throws DrmException;
}
