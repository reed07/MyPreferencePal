package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzark
public final class zzwe {
    public static final zzwe zzckj = new zzwe();

    @VisibleForTesting
    protected zzwe() {
    }

    public static zzwb zza(Context context, zzyx zzyx) {
        List list;
        Context context2;
        String str;
        zzyx zzyx2 = zzyx;
        Date birthday = zzyx.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = zzyx.getContentUrl();
        int gender = zzyx.getGender();
        Set keywords = zzyx.getKeywords();
        if (!keywords.isEmpty()) {
            list = Collections.unmodifiableList(new ArrayList(keywords));
            context2 = context;
        } else {
            context2 = context;
            list = null;
        }
        boolean isTestDevice = zzyx2.isTestDevice(context2);
        int zzqm = zzyx.zzqm();
        Location location = zzyx.getLocation();
        Bundle networkExtrasBundle = zzyx2.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = zzyx.getManualImpressionsEnabled();
        String publisherProvidedId = zzyx.getPublisherProvidedId();
        SearchAdRequest zzqj = zzyx.zzqj();
        zzzs zzzs = zzqj != null ? new zzzs(zzqj) : null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzwu.zzpv();
            str = zzbat.zza(Thread.currentThread().getStackTrace(), packageName);
        } else {
            str = null;
        }
        zzwb zzwb = new zzwb(8, time, networkExtrasBundle, gender, list, isTestDevice, zzqm, manualImpressionsEnabled, publisherProvidedId, zzzs, location, contentUrl, zzyx.zzql(), zzyx.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzyx.zzqn())), zzyx.zzqi(), str, zzyx.isDesignedForFamilies(), null, zzyx.zzqo(), zzyx.zzqp());
        return zzwb;
    }
}
