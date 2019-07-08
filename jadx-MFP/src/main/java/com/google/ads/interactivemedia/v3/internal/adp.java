package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.lang.reflect.Type;

/* compiled from: IMASDK */
final class adp implements xg<CompanionAdSlot> {
    adp() {
    }

    public final /* synthetic */ wz a(Object obj, Type type, xf xfVar) {
        CompanionAdSlot companionAdSlot = (CompanionAdSlot) obj;
        int width = companionAdSlot.getWidth();
        int height = companionAdSlot.getHeight();
        StringBuilder sb = new StringBuilder(23);
        sb.append(width);
        sb.append(AvidJSONUtil.KEY_X);
        sb.append(height);
        return new xe(sb.toString());
    }
}
