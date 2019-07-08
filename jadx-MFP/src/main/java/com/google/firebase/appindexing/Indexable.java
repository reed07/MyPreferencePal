package com.google.firebase.appindexing;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.icing.zzgs.zza;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.Thing;

public interface Indexable {
    public static final int MAX_BYTE_SIZE = 30000;
    public static final int MAX_INDEXABLES_TO_BE_UPDATED_IN_ONE_CALL = 1000;
    public static final int MAX_NESTING_DEPTH = 5;
    public static final int MAX_NUMBER_OF_FIELDS = 20;
    public static final int MAX_REPEATED_SIZE = 100;
    public static final int MAX_STRING_LENGTH = 20000;
    public static final int MAX_URL_LENGTH = 256;

    public static class Builder extends IndexableBuilder<Builder> {
        public Builder() {
            this("Thing");
        }

        public Builder(@NonNull String str) {
            super(str);
        }
    }

    public interface Metadata {

        public static final class Builder {
            private int score = zza.zzeb().getScore();
            private final Bundle zzax = new Bundle();
            private boolean zzei = zza.zzeb().zzdz();
            private String zzej = zza.zzeb().zzea();

            public final Builder setWorksOffline(boolean z) {
                this.zzei = z;
                return this;
            }

            public final Builder setScore(int i) {
                boolean z = i >= 0;
                StringBuilder sb = new StringBuilder(53);
                sb.append("Negative score values are invalid. Value: ");
                sb.append(i);
                Preconditions.checkArgument(z, sb.toString());
                this.score = i;
                return this;
            }

            public final Builder setScope(int i) {
                boolean z = i > 0 && i <= 3;
                StringBuilder sb = new StringBuilder(69);
                sb.append("The scope of this indexable is not valid, scope value is ");
                sb.append(i);
                sb.append(".");
                Preconditions.checkArgument(z, sb.toString());
                IndexableBuilder.zza(this.zzax, "scope", (long) i);
                return this;
            }

            public final Builder setSliceUri(@NonNull Uri uri) {
                Preconditions.checkNotNull(uri);
                boolean[] zArr = {true};
                IndexableBuilder.zza(this.zzax, "grantSlicePermission", zArr);
                String[] strArr = {uri.toString()};
                IndexableBuilder.zza(this.zzax, "sliceUri", strArr);
                return this;
            }

            public final Thing.zza zzac() {
                return new Thing.zza(this.zzei, this.score, this.zzej, this.zzax);
            }
        }
    }
}
