package com.google.android.gms.internal.fitness;

import android.support.annotation.Nullable;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable.Columns;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences.MacroGoalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class zzk {
    private static final double zzel = (10.0d / ((double) TimeUnit.SECONDS.toNanos(1)));
    private static final double zzem = (1000.0d / ((double) TimeUnit.SECONDS.toNanos(1)));
    private static final double zzen = (2000.0d / ((double) TimeUnit.HOURS.toNanos(1)));
    private static final double zzeo = (100.0d / ((double) TimeUnit.SECONDS.toNanos(1)));
    public static final Set<String> zzep = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"altitude", "duration", "food_item", "meal_type", Columns.REPETITIONS, "resistance", "resistance_type", "debug_session", "google.android.fitness.SessionV2"})));
    private static final zzk zzes = new zzk();
    private final Map<String, Map<String, zzm>> zzeq;
    private final Map<String, zzm> zzer;

    private zzk() {
        HashMap hashMap = new HashMap();
        zzm zzm = new zzm(-90.0d, 90.0d);
        hashMap.put("latitude", zzm);
        zzm zzm2 = new zzm(-180.0d, 180.0d);
        hashMap.put("longitude", zzm2);
        zzm zzm3 = new zzm(0.0d, 10000.0d);
        hashMap.put("accuracy", zzm3);
        zzm zzm4 = new zzm(0.0d, 1000.0d);
        hashMap.put("bpm", zzm4);
        zzm zzm5 = new zzm(-100000.0d, 100000.0d);
        hashMap.put("altitude", zzm5);
        String str = MacroGoalFormat.PERCENTAGE;
        zzm zzm6 = new zzm(0.0d, 100.0d);
        hashMap.put(str, zzm6);
        zzm zzm7 = new zzm(0.0d, 100.0d);
        hashMap.put("confidence", zzm7);
        zzm zzm8 = new zzm(0.0d, 9.223372036854776E18d);
        hashMap.put("duration", zzm8);
        zzm zzm9 = new zzm(0.0d, 3.0d);
        hashMap.put("height", zzm9);
        zzm zzm10 = new zzm(0.0d, 1000.0d);
        hashMap.put("weight", zzm10);
        zzm zzm11 = new zzm(0.0d, 11000.0d);
        hashMap.put("speed", zzm11);
        this.zzer = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        zzm zzm12 = new zzm(0.0d, zzel);
        hashMap2.put("com.google.step_count.delta", zza((K) "steps", (V) zzm12));
        zzm zzm13 = new zzm(0.0d, zzem);
        hashMap2.put("com.google.calories.consumed", zza((K) "calories", (V) zzm13));
        zzm zzm14 = new zzm(0.0d, zzen);
        hashMap2.put("com.google.calories.expended", zza((K) "calories", (V) zzm14));
        zzm zzm15 = new zzm(0.0d, zzeo);
        hashMap2.put("com.google.distance.delta", zza((K) "distance", (V) zzm15));
        this.zzeq = Collections.unmodifiableMap(hashMap2);
    }

    private static <K, V> Map<K, V> zza(K k, V v) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    public final zzm zzk(String str) {
        return (zzm) this.zzer.get(str);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final zzm zza(String str, String str2) {
        Map map = (Map) this.zzeq.get(str);
        if (map != null) {
            return (zzm) map.get(str2);
        }
        return null;
    }

    public static zzk zzs() {
        return zzes;
    }
}
