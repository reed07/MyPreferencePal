package com.mopub.network;

import android.net.Uri;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.ClientMetadata;
import com.mopub.common.MoPub;
import com.mopub.common.privacy.AdvertisingId;
import com.mopub.volley.toolbox.HurlStack.UrlRewriter;

public class PlayServicesUrlRewriter implements UrlRewriter {
    public static final String DO_NOT_TRACK_TEMPLATE = "mp_tmpl_do_not_track";
    public static final String UDID_TEMPLATE = "mp_tmpl_advertising_id";

    public String rewriteUrl(String str) {
        if (!str.contains(UDID_TEMPLATE) && !str.contains(DO_NOT_TRACK_TEMPLATE)) {
            return str;
        }
        ClientMetadata instance = ClientMetadata.getInstance();
        if (instance == null) {
            return str;
        }
        AdvertisingId advertisingInfo = instance.getMoPubIdentifier().getAdvertisingInfo();
        return str.replace(UDID_TEMPLATE, Uri.encode(advertisingInfo.getIdWithPrefix(MoPub.canCollectPersonalInformation()))).replace(DO_NOT_TRACK_TEMPLATE, advertisingInfo.isDoNotTrack() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
    }
}
