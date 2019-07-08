package com.myfitnesspal.shared.util;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.Collection;

public class MultiAddExerciseSelection extends MultiAddSelection {
    private static MultiAddExerciseSelection currentMultiAddExerciseSelection;

    public static MultiAddExerciseSelection current() {
        return currentMultiAddExerciseSelection;
    }

    public static boolean isActive() {
        return currentMultiAddExerciseSelection != null;
    }

    public static void activate() {
        if (currentMultiAddExerciseSelection == null) {
            currentMultiAddExerciseSelection = new MultiAddExerciseSelection();
        }
    }

    public static void reset() {
        if (currentMultiAddExerciseSelection != null) {
            currentMultiAddExerciseSelection = null;
        }
    }

    public void selectOrUpdateExerciseEntry(MfpExerciseEntry mfpExerciseEntry) {
        if (hasSelectedItem(mfpExerciseEntry)) {
            updateExerciseEntry(mfpExerciseEntry);
        } else {
            selectExerciseEntry(mfpExerciseEntry);
        }
    }

    public void addAllExerciseEntriesToDiary(DiaryService diaryService) {
        DiaryDay diaryDayForActiveDateSync = diaryService.getDiaryDayForActiveDateSync();
        diaryDayForActiveDateSync.setIsPerformingMultiAdd(true);
        for (MfpExerciseEntry addExerciseEntry : getEntries()) {
            diaryDayForActiveDateSync.addExerciseEntry(addExerciseEntry);
        }
        diaryDayForActiveDateSync.setIsPerformingMultiAdd(false);
        diaryDayForActiveDateSync.setJustAddedExerciseEntry(null);
        diaryDayForActiveDateSync.setJustAddedMultipleItems(true);
    }

    public int getMinutesPerformedForSelectedExercises() {
        int i = 0;
        if (CollectionUtils.notEmpty((Collection<?>) getEntries())) {
            for (MfpExerciseEntry mfpExerciseEntry : getEntries()) {
                if (Strings.equals(mfpExerciseEntry.getExercise().getType(), "cardio")) {
                    i += mfpExerciseEntry.getDuration() / 60;
                }
            }
        }
        return i;
    }

    private void updateExerciseEntry(MfpExerciseEntry mfpExerciseEntry) {
        MfpExerciseEntry exerciseEntryForExercise = getExerciseEntryForExercise(mfpExerciseEntry.getExercise());
        if (exerciseEntryForExercise != null) {
            exerciseEntryForExercise.setExercise(mfpExerciseEntry.getExercise());
            exerciseEntryForExercise.setDuration(mfpExerciseEntry.getDuration());
            exerciseEntryForExercise.setEnergy(mfpExerciseEntry.getEnergy());
            exerciseEntryForExercise.setSets(mfpExerciseEntry.getSets());
            exerciseEntryForExercise.setRepsPerSet(mfpExerciseEntry.getRepsPerSet());
            exerciseEntryForExercise.setWeightPerSet(mfpExerciseEntry.getWeightPerSet());
        }
    }
}
