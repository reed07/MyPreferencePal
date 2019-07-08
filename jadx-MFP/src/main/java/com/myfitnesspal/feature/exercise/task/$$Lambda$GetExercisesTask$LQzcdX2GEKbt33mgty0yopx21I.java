package com.myfitnesspal.feature.exercise.task;

import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import java.util.Comparator;

/* renamed from: com.myfitnesspal.feature.exercise.task.-$$Lambda$GetExercisesTask$LQzcdX2GEKbt33mgt-y0yopx21I reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$GetExercisesTask$LQzcdX2GEKbt33mgty0yopx21I implements Comparator {
    public static final /* synthetic */ $$Lambda$GetExercisesTask$LQzcdX2GEKbt33mgty0yopx21I INSTANCE = new $$Lambda$GetExercisesTask$LQzcdX2GEKbt33mgty0yopx21I();

    private /* synthetic */ $$Lambda$GetExercisesTask$LQzcdX2GEKbt33mgty0yopx21I() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((MfpExerciseEntry) obj2).getExercise().getDescription().compareTo(((MfpExerciseEntry) obj).getExercise().getDescription());
    }
}
