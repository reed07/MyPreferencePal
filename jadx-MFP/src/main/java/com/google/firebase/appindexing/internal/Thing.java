package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.Indexable.Metadata;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

@Class(creator = "ThingCreator")
public final class Thing extends AbstractSafeParcelable implements ReflectedParcelable, Indexable {
    public static final Creator<Thing> CREATOR = new zzad();
    @Field(getter = "getType", id = 4)
    private final String type;
    @Field(getter = "getPropertyBundle", id = 1)
    private final Bundle zzax;
    @Field(getter = "getMetadata", id = 2)
    private final zza zzek;
    @Field(getter = "getVersionCode", id = 1000)
    private final int zzfm;
    @Field(getter = "getUrl", id = 3)
    private final String zzfn;

    @Class(creator = "MetadataCreator")
    @Reserved({1000})
    public static class zza extends AbstractSafeParcelable implements Metadata {
        public static final Creator<zza> CREATOR = new zzv();
        @Field(getter = "getScore", id = 2)
        private final int score;
        @Field(getter = "getPropertyBundle", id = 4)
        private final Bundle zzax;
        @Field(getter = "getWorksOffline", id = 1)
        private final boolean zzei;
        @Field(getter = "getAccountEmail", id = 3)
        private final String zzej;

        @Constructor
        public zza(@Param(id = 1) boolean z, @Param(id = 2) int i, @Param(id = 3) String str, @Param(id = 4) Bundle bundle) {
            this.zzei = z;
            this.score = i;
            this.zzej = str;
            if (bundle == null) {
                bundle = new Bundle();
            }
            this.zzax = bundle;
        }

        public final Bundle zze() {
            return this.zzax;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, this.zzei);
            SafeParcelWriter.writeInt(parcel, 2, this.score);
            SafeParcelWriter.writeString(parcel, 3, this.zzej, false);
            SafeParcelWriter.writeBundle(parcel, 4, this.zzax, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("worksOffline: ");
            sb.append(this.zzei);
            sb.append(", score: ");
            sb.append(this.score);
            if (!this.zzej.isEmpty()) {
                sb.append(", accountEmail: ");
                sb.append(this.zzej);
            }
            Bundle bundle = this.zzax;
            if (bundle != null && !bundle.isEmpty()) {
                sb.append(", Properties { ");
                Thing.zza(this.zzax, sb);
                sb.append("}");
            }
            return sb.toString();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return Objects.equal(Boolean.valueOf(this.zzei), Boolean.valueOf(zza.zzei)) && Objects.equal(Integer.valueOf(this.score), Integer.valueOf(zza.score)) && Objects.equal(this.zzej, zza.zzej) && Thing.zza(this.zzax, zza.zzax);
        }

        public final int hashCode() {
            return Objects.hashCode(Boolean.valueOf(this.zzei), Integer.valueOf(this.score), this.zzej, Integer.valueOf(Thing.zzb(this.zzax)));
        }
    }

    @Constructor
    public Thing(@Param(id = 1000) int i, @Param(id = 1) Bundle bundle, @Param(id = 2) zza zza2, @Param(id = 3) String str, @Param(id = 4) String str2) {
        this.zzfm = i;
        this.zzax = bundle;
        this.zzek = zza2;
        this.zzfn = str;
        this.type = str2;
        this.zzax.setClassLoader(getClass().getClassLoader());
    }

    public Thing(@NonNull Bundle bundle, @NonNull zza zza2, String str, @NonNull String str2) {
        this.zzfm = 10;
        this.zzax = bundle;
        this.zzek = zza2;
        this.zzfn = str;
        this.type = str2;
    }

    public final zza zzae() {
        return this.zzek;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzax, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzek, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzfn, false);
        SafeParcelWriter.writeString(parcel, 4, this.type, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzfm);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: private */
    public static void zza(@NonNull Bundle bundle, @NonNull StringBuilder sb) {
        try {
            Set keySet = bundle.keySet();
            String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
            Arrays.sort(strArr, zzac.zzfo);
            for (String str : strArr) {
                sb.append("{ key: '");
                sb.append(str);
                sb.append("' value: ");
                Object obj = bundle.get(str);
                if (obj == null) {
                    sb.append("<null>");
                } else if (obj.getClass().isArray()) {
                    sb.append("[ ");
                    for (int i = 0; i < Array.getLength(obj); i++) {
                        sb.append("'");
                        sb.append(Array.get(obj, i));
                        sb.append("' ");
                    }
                    sb.append("]");
                } else {
                    sb.append(obj.toString());
                }
                sb.append(" } ");
            }
        } catch (RuntimeException unused) {
            sb.append("<error>");
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.equals("Thing") ? "Indexable" : this.type);
        sb.append(" { { id: ");
        if (this.zzfn == null) {
            sb.append("<null>");
        } else {
            sb.append("'");
            sb.append(this.zzfn);
            sb.append("'");
        }
        sb.append(" } Properties { ");
        zza(this.zzax, sb);
        sb.append("} ");
        sb.append("Metadata { ");
        sb.append(this.zzek.toString());
        sb.append(" } ");
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Thing)) {
            return false;
        }
        Thing thing = (Thing) obj;
        return Objects.equal(Integer.valueOf(this.zzfm), Integer.valueOf(thing.zzfm)) && Objects.equal(this.zzfn, thing.zzfn) && Objects.equal(this.type, thing.type) && Objects.equal(this.zzek, thing.zzek) && zza(this.zzax, thing.zzax);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzfm), this.zzfn, this.type, Integer.valueOf(this.zzek.hashCode()), Integer.valueOf(zzb(this.zzax)));
    }

    /* access modifiers changed from: private */
    public static boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !zza((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null && (obj2 != null || !bundle2.containsKey(str))) {
                return false;
            }
            if (obj instanceof boolean[]) {
                if (!(obj2 instanceof boolean[]) || !Arrays.equals((boolean[]) obj, (boolean[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof long[]) {
                if (!(obj2 instanceof long[]) || !Arrays.equals((long[]) obj, (long[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof double[]) {
                if (!(obj2 instanceof double[]) || !Arrays.equals((double[]) obj, (double[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof byte[]) {
                if (!(obj2 instanceof byte[]) || !Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                    return false;
                }
            } else if ((obj instanceof Object[]) && (!(obj2 instanceof Object[]) || !Arrays.equals((Object[]) obj, (Object[]) obj2))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static int zzb(Bundle bundle) {
        ArrayList arrayList = new ArrayList(bundle.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = arrayList;
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList3.get(i);
            i++;
            Object obj2 = bundle.get((String) obj);
            if (obj2 instanceof boolean[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((boolean[]) obj2)));
            } else if (obj2 instanceof long[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((long[]) obj2)));
            } else if (obj2 instanceof double[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((double[]) obj2)));
            } else if (obj2 instanceof byte[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((byte[]) obj2)));
            } else if (obj2 instanceof Object[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((Object[]) obj2)));
            } else {
                arrayList2.add(Integer.valueOf(Objects.hashCode(obj2)));
            }
        }
        return Objects.hashCode(arrayList2.toArray());
    }

    static final /* synthetic */ int zzb(String str, String str2) {
        if (str == null) {
            return str2 == null ? 0 : -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }
}
