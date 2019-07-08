package com.brightcove.player.drm;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Pair;
import com.brightcove.player.dash.BrightcoveDashManifestParser;
import com.brightcove.player.util.Convert;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession.DrmSessionException;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.drm.MediaDrmCallback;
import com.google.android.exoplayer2.drm.OfflineLicenseHelper;
import com.google.android.exoplayer2.source.dash.DashUtil;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import java.io.IOException;
import java.util.HashMap;

public class OfflineLicenseManager<T extends ExoMediaCrypto> implements LicenseManager {
    private final MediaDrmCallback callback;
    private final OfflineLicenseHelper<T> delegate;

    public OfflineLicenseManager(ExoMediaDrm<T> exoMediaDrm, MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap) {
        this.delegate = new OfflineLicenseHelper<>(C.WIDEVINE_UUID, exoMediaDrm, mediaDrmCallback, hashMap);
        this.callback = mediaDrmCallback;
    }

    public void releaseResources() {
        this.delegate.release();
    }

    @Nullable
    public byte[] downloadLicense(String str, @NonNull CustomerRightsToken customerRightsToken) throws IOException, InterruptedException, DrmException {
        HttpDataSource createHttpDataSource = DrmUtil.createHttpDataSource();
        ParsingLoadable parsingLoadable = new ParsingLoadable((DataSource) createHttpDataSource, Uri.parse(str), 4, (Parser<? extends T>) new BrightcoveDashManifestParser<Object>());
        parsingLoadable.load();
        DrmInitData loadDrmInitData = DashUtil.loadDrmInitData(createHttpDataSource, ((DashManifest) parsingLoadable.getResult()).getPeriod(0));
        if (loadDrmInitData == null) {
            return null;
        }
        if (this.callback instanceof WidevineMediaDrmCallback) {
            HashMap hashMap = new HashMap();
            hashMap.put(LicenseManager.CR_TOKEN_HEADER, Base64.encodeToString(Convert.toJsonString(customerRightsToken).getBytes(), 2));
            ((WidevineMediaDrmCallback) this.callback).setOptionalHeaders(hashMap);
        }
        try {
            return this.delegate.downloadLicense(loadDrmInitData);
        } catch (DrmSessionException e) {
            throw new DrmException("Failed to download license", e);
        }
    }

    @Nullable
    public byte[] renewLicense(byte[] bArr) throws DrmException {
        try {
            return this.delegate.renewLicense(bArr);
        } catch (DrmSessionException e) {
            throw new DrmException("Failed to renew license", e);
        }
    }

    public void releaseLicense(@Nullable byte[] bArr) throws DrmException {
        if (bArr != null) {
            try {
                this.delegate.releaseLicense(bArr);
            } catch (DrmSessionException e) {
                throw new DrmException("Failed to release license", e);
            }
        }
    }

    @Nullable
    public Pair<Long, Long> getRemainingLicenseDuration(byte[] bArr) throws DrmException {
        try {
            return this.delegate.getLicenseDurationRemainingSec(bArr);
        } catch (DrmSessionException e) {
            throw new DrmException("Failed to read license duration", e);
        }
    }
}
