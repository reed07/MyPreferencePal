package com.google.android.gms.appindexing;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.fitness.FitnessActivities;

@Deprecated
@VisibleForTesting
public final class Action extends Thing {
    public static final String STATUS_TYPE_ACTIVE = "http://schema.org/ActiveActionStatus";
    public static final String STATUS_TYPE_COMPLETED = "http://schema.org/CompletedActionStatus";
    public static final String STATUS_TYPE_FAILED = "http://schema.org/FailedActionStatus";
    public static final String TYPE_ACTIVATE = "http://schema.org/ActivateAction";
    public static final String TYPE_ADD = "http://schema.org/AddAction";
    public static final String TYPE_BOOKMARK = "http://schema.org/BookmarkAction";
    public static final String TYPE_COMMUNICATE = "http://schema.org/CommunicateAction";
    public static final String TYPE_FILM = "http://schema.org/FilmAction";
    public static final String TYPE_LIKE = "http://schema.org/LikeAction";
    public static final String TYPE_LISTEN = "http://schema.org/ListenAction";
    public static final String TYPE_PHOTOGRAPH = "http://schema.org/PhotographAction";
    public static final String TYPE_RESERVE = "http://schema.org/ReserveAction";
    public static final String TYPE_SEARCH = "http://schema.org/SearchAction";
    public static final String TYPE_VIEW = "http://schema.org/ViewAction";
    public static final String TYPE_WANT = "http://schema.org/WantAction";
    public static final String TYPE_WATCH = "http://schema.org/WatchAction";

    @Deprecated
    public static final class Builder extends com.google.android.gms.appindexing.Thing.Builder {
        public Builder(String str) {
            Preconditions.checkNotNull(str);
            super.put("type", str);
        }

        public final Builder setName(String str) {
            return (Builder) super.put("name", str);
        }

        public final Builder setUrl(Uri uri) {
            if (uri != null) {
                super.put("url", uri.toString());
            }
            return this;
        }

        public final Builder put(String str, String str2) {
            return (Builder) super.put(str, str2);
        }

        public final Builder put(String str, String[] strArr) {
            return (Builder) super.put(str, strArr);
        }

        public final Builder put(String str, Thing thing) {
            return (Builder) super.put(str, thing);
        }

        public final Builder put(String str, Thing[] thingArr) {
            return (Builder) super.put(str, thingArr);
        }

        public final Builder put(String str, boolean z) {
            return (Builder) super.put(str, z);
        }

        public final Builder setObject(Thing thing) {
            Preconditions.checkNotNull(thing);
            return (Builder) super.put("object", thing);
        }

        public final Builder setActionStatus(String str) {
            Preconditions.checkNotNull(str);
            return (Builder) super.put(FitnessActivities.EXTRA_STATUS, str);
        }

        public final Action build() {
            Preconditions.checkNotNull(this.zzay.get("object"), "setObject is required before calling build().");
            Preconditions.checkNotNull(this.zzay.get("type"), "setType is required before calling build().");
            Bundle bundle = (Bundle) this.zzay.getParcelable("object");
            Preconditions.checkNotNull(bundle.get("name"), "Must call setObject() with a valid name. Example: setObject(new Thing.Builder().setName(name).setUrl(url))");
            Preconditions.checkNotNull(bundle.get("url"), "Must call setObject() with a valid app URI. Example: setObject(new Thing.Builder().setName(name).setUrl(url))");
            return new Action(this.zzay);
        }
    }

    private Action(Bundle bundle) {
        super(bundle);
    }

    public static Action newAction(String str, String str2, Uri uri, Uri uri2) {
        String str3;
        Builder builder = new Builder(str);
        com.google.android.gms.appindexing.Thing.Builder name = new com.google.android.gms.appindexing.Thing.Builder().setName(str2);
        if (uri == null) {
            str3 = null;
        } else {
            str3 = uri.toString();
        }
        return (Action) builder.setObject(name.setId(str3).setUrl(uri2).build()).build();
    }

    public static Action newAction(String str, String str2, Uri uri) {
        return newAction(str, str2, null, uri);
    }
}
