package com.facebook.ads.internal.j;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthExerciseType.SamsungExercise;

@VisibleForTesting
public abstract class f<T> {
    private a a;

    public enum a {
        UNKNOWN(9000, "An unknown error has occurred."),
        DATABASE_SELECT(3001, "Failed to read from database."),
        DATABASE_INSERT(SamsungExercise.BILLIARDS, "Failed to insert row into database."),
        DATABASE_UPDATE(SamsungExercise.BOWLING, "Failed to update row in database."),
        DATABASE_DELETE(3004, "Failed to delete row from database.");
        
        private final int f;
        private final String g;

        private a(int i, String str) {
            this.f = i;
            this.g = str;
        }

        public int a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }
    }

    /* access modifiers changed from: protected */
    public void a(a aVar) {
        this.a = aVar;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public abstract T b();

    public a c() {
        return this.a;
    }
}
