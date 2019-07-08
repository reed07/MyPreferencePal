package com.brightcove.player.edge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.Fields;
import com.brightcove.player.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class DashWidevineAuthorizer implements Authorizer {
    DashWidevineAuthorizer() {
    }

    @NonNull
    public Video configure(@NonNull Video video, @NonNull String str) {
        Map map;
        Objects.requireNonNull(video, "Video cannot be null");
        Objects.requireNonNull(str, "The Brightcove Authorization token cannot be null");
        SourceCollection sourceCollectionByDeliveryType = video.getSourceCollectionByDeliveryType(DeliveryType.DASH);
        if (sourceCollectionByDeliveryType != null && hasWidevineKeySystems(sourceCollectionByDeliveryType)) {
            Map properties = video.getProperties();
            Object obj = properties.get(Fields.WIDEVINE_HEADERS);
            if (obj instanceof Map) {
                map = (Map) obj;
            } else {
                map = new HashMap();
                properties.put(Fields.WIDEVINE_HEADERS, map);
            }
            map.put(Authorizer.BRIGHTCOVE_AUTHORIZATION_HEADER_KEY, str);
        }
        return video;
    }

    @Nullable
    public String findAuthorizationToken(@NonNull Video video) {
        Objects.requireNonNull(video, "Video cannot be null");
        Object obj = video.getProperties().get(Fields.WIDEVINE_HEADERS);
        if (!(obj instanceof Map)) {
            return null;
        }
        Object obj2 = ((Map) obj).get(Authorizer.BRIGHTCOVE_AUTHORIZATION_HEADER_KEY);
        if (obj2 == null) {
            return null;
        }
        return obj2.toString();
    }

    static boolean hasWidevineKeySystems(@NonNull SourceCollection sourceCollection) {
        Objects.requireNonNull(sourceCollection, "The SourceCollection cannot be null");
        boolean z = false;
        for (Source properties : sourceCollection.getSources()) {
            Map map = (Map) properties.getProperties().get(Source.Fields.KEY_SYSTEMS);
            if (map != null) {
                Iterator it = map.keySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().toString().equals(Source.Fields.WIDEVINE_KEY_SYSTEM)) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return z;
    }
}
