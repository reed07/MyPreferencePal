package com.google.android.gms.fitness;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FitnessOptions implements GoogleSignInOptionsExtension, HasGoogleSignInAccountOptions {
    public static final int ACCESS_READ = 0;
    public static final int ACCESS_WRITE = 1;
    private final SparseArray<List<DataType>> zzk;
    private final Set<Scope> zzl;
    private final GoogleSignInAccount zzm;

    public static final class Builder {
        private GoogleSignInAccount zzm;
        private final SparseArray<List<DataType>> zzn;

        private Builder() {
            this.zzn = new SparseArray<>();
        }

        public final Builder addDataType(@NonNull DataType dataType) {
            return addDataType(dataType, 0);
        }

        public final Builder addDataType(@NonNull DataType dataType, int i) {
            boolean z = true;
            if (!(i == 0 || i == 1)) {
                z = false;
            }
            Preconditions.checkArgument(z, "valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            List list = (List) this.zzn.get(i);
            if (list == null) {
                list = new ArrayList();
                this.zzn.put(i, list);
            }
            list.add(dataType);
            return this;
        }

        /* access modifiers changed from: private */
        public final Builder zzb(@NonNull GoogleSignInAccount googleSignInAccount) {
            this.zzm = googleSignInAccount;
            return this;
        }

        public final FitnessOptions build() {
            return new FitnessOptions(this.zzn, this.zzm);
        }
    }

    private FitnessOptions(SparseArray<List<DataType>> sparseArray, GoogleSignInAccount googleSignInAccount) {
        this.zzk = sparseArray;
        this.zzm = googleSignInAccount;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            for (DataType dataType : (List) sparseArray.valueAt(i)) {
                if (keyAt == 0 && dataType.zzk() != null) {
                    arrayList.add(new Scope(dataType.zzk()));
                } else if (keyAt == 1 && dataType.zzl() != null) {
                    arrayList.add(new Scope(dataType.zzl()));
                }
            }
        }
        this.zzl = zzg.zza(arrayList);
    }

    public int getExtensionType() {
        return 3;
    }

    public GoogleSignInAccount getGoogleSignInAccount() {
        return this.zzm;
    }

    public Bundle toBundle() {
        return new Bundle();
    }

    @Nullable
    public List<Scope> getImpliedScopes() {
        return new ArrayList(this.zzl);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zza(GoogleSignInAccount googleSignInAccount) {
        if (googleSignInAccount != null) {
            return new Builder().zzb(googleSignInAccount);
        }
        return new Builder();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FitnessOptions fitnessOptions = (FitnessOptions) obj;
        return Objects.equal(this.zzk, fitnessOptions.zzk) && Objects.equal(this.zzm, fitnessOptions.zzm);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzk, this.zzm);
    }
}
