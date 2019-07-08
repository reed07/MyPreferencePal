package com.google.firebase.appindexing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.zza;
import com.google.firebase.appindexing.internal.zzb;
import com.google.firebase.appindexing.internal.zzu;
import java.util.Arrays;

public interface Action {

    public static class Builder {
        public static final String ACTIVATE_ACTION = "ActivateAction";
        public static final String ADD_ACTION = "AddAction";
        public static final String BOOKMARK_ACTION = "BookmarkAction";
        public static final String COMMENT_ACTION = "CommentAction";
        public static final String LIKE_ACTION = "LikeAction";
        public static final String LISTEN_ACTION = "ListenAction";
        public static final String SEND_ACTION = "SendAction";
        public static final String SHARE_ACTION = "ShareAction";
        public static final String STATUS_TYPE_ACTIVE = "http://schema.org/ActiveActionStatus";
        public static final String STATUS_TYPE_COMPLETED = "http://schema.org/CompletedActionStatus";
        public static final String STATUS_TYPE_FAILED = "http://schema.org/FailedActionStatus";
        public static final String VIEW_ACTION = "ViewAction";
        public static final String WATCH_ACTION = "WatchAction";
        private final String zzar;
        private final Bundle zzax = new Bundle();
        private String zzea;
        private String zzeb;
        private String zzec;
        private zzb zzed;
        private String zzee;

        public Builder(@NonNull String str) {
            this.zzar = str;
        }

        public Builder put(@NonNull String str, @NonNull String... strArr) {
            IndexableBuilder.zza(this.zzax, str, strArr);
            return this;
        }

        public Builder put(@NonNull String str, @NonNull Indexable... indexableArr) throws FirebaseAppIndexingInvalidArgumentException {
            IndexableBuilder.zza(this.zzax, str, indexableArr);
            return this;
        }

        public Builder put(@NonNull String str, @NonNull boolean... zArr) {
            IndexableBuilder.zza(this.zzax, str, zArr);
            return this;
        }

        public Builder put(@NonNull String str, @NonNull long... jArr) {
            IndexableBuilder.zza(this.zzax, str, jArr);
            return this;
        }

        public Builder put(@NonNull String str, @NonNull double... dArr) {
            Bundle bundle = this.zzax;
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(dArr);
            if (dArr.length > 0) {
                if (dArr.length >= 100) {
                    zzu.zzr("Input Array of elements is too big, cutting off.");
                    dArr = Arrays.copyOf(dArr, 100);
                }
                bundle.putDoubleArray(str, dArr);
            } else {
                zzu.zzr("Double array is empty and is ignored by put method.");
            }
            return this;
        }

        public final Builder setUrl(@NonNull String str) {
            Preconditions.checkNotNull(str);
            this.zzeb = str;
            return put("url", str);
        }

        public Builder setObject(@NonNull String str, @NonNull String str2) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(str2);
            this.zzea = str;
            this.zzeb = str2;
            return this;
        }

        public Builder setObject(@NonNull String str, @NonNull String str2, @NonNull String str3) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(str2);
            Preconditions.checkNotNull(str3);
            this.zzea = str;
            this.zzeb = str2;
            this.zzec = str3;
            return this;
        }

        public final Builder setName(@NonNull String str) {
            Preconditions.checkNotNull(str);
            this.zzea = str;
            return put("name", str);
        }

        public Builder setMetadata(@NonNull Builder builder) {
            Preconditions.checkNotNull(builder);
            this.zzed = builder.zzab();
            return this;
        }

        public Builder setActionStatus(@NonNull String str) {
            Preconditions.checkNotNull(str);
            this.zzee = str;
            return this;
        }

        public Action build() {
            Preconditions.checkNotNull(this.zzea, "setObject is required before calling build().");
            Preconditions.checkNotNull(this.zzeb, "setObject is required before calling build().");
            String str = this.zzar;
            String str2 = this.zzea;
            String str3 = this.zzeb;
            String str4 = this.zzec;
            zzb zzb = this.zzed;
            if (zzb == null) {
                zzb = new Builder().zzab();
            }
            zza zza = new zza(str, str2, str3, str4, zzb, this.zzee, this.zzax);
            return zza;
        }
    }

    public interface Metadata {

        public static class Builder {
            private boolean zzef = true;
            private boolean zzeg = false;

            public Builder setUpload(boolean z) {
                this.zzef = z;
                return this;
            }

            public final zzb zzab() {
                zzb zzb = new zzb(this.zzef, null, null, null, false);
                return zzb;
            }
        }
    }
}
