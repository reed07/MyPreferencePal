package com.brightcove.player.drm;

import java.util.Map;

public interface BrightcoveDrmSession {
    @Deprecated
    DrmException getDrmError();

    @Deprecated
    byte[] getOfflineLicenseKeySetId();

    byte[] getPropertyByteArray(String str);

    String getPropertyString(String str);

    @Deprecated
    Map<String, String> queryKeyStatus();

    void setPropertyByteArray(String str, byte[] bArr);

    void setPropertyString(String str, String str2);
}
