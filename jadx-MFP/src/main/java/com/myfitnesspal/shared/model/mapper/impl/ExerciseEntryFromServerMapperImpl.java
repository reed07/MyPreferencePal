package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v15.ExerciseEntryFromServer;
import com.myfitnesspal.shared.model.v15.ExerciseObjectFromServer;
import com.uacf.core.util.Ln;
import java.io.IOException;

public class ExerciseEntryFromServerMapperImpl implements ExerciseEntryFromServerMapper {
    private final ExerciseFromServerMapper exerciseMapper;

    public ExerciseEntryFromServerMapperImpl(ExerciseFromServerMapper exerciseFromServerMapper) {
        this.exerciseMapper = exerciseFromServerMapper;
    }

    public ExerciseEntry reverseMap(ExerciseEntryFromServer exerciseEntryFromServer) {
        ExerciseEntry exerciseEntry = new ExerciseEntry();
        exerciseEntry.setLocalId(exerciseEntryFromServer.getLocalId());
        exerciseEntry.setMasterDatabaseId(exerciseEntryFromServer.getMasterId());
        exerciseEntry.setDate(exerciseEntryFromServer.getDate());
        exerciseEntry.setQuantity(exerciseEntryFromServer.getQuantity());
        exerciseEntry.setSets(exerciseEntryFromServer.getSets());
        exerciseEntry.setWeight(exerciseEntryFromServer.getWeight());
        exerciseEntry.setCalories(exerciseEntryFromServer.getCalories());
        exerciseEntry.setExtraProperties(exerciseEntryFromServer.getExtras());
        exerciseEntry.setExercise((Exercise) this.exerciseMapper.reverseMap((ExerciseObjectFromServer) exerciseEntryFromServer.getExercise()));
        return exerciseEntry;
    }

    public ExerciseEntryFromServer mapFrom(ExerciseEntry exerciseEntry) throws IOException {
        ExerciseEntryFromServer exerciseEntryFromServer = new ExerciseEntryFromServer();
        exerciseEntryFromServer.setLocalId(exerciseEntry.getLocalId());
        exerciseEntryFromServer.setMasterId(exerciseEntry.getMasterDatabaseId());
        exerciseEntryFromServer.setDate(exerciseEntry.getDate());
        exerciseEntryFromServer.setQuantity(exerciseEntry.getQuantity());
        exerciseEntryFromServer.setSets(exerciseEntry.getSets());
        exerciseEntryFromServer.setWeight(exerciseEntry.getWeight());
        exerciseEntryFromServer.setCalories(exerciseEntry.getCalories());
        exerciseEntryFromServer.setExtras(exerciseEntry.getExtraProperties());
        exerciseEntryFromServer.setExercise((ExerciseObjectFromServer) this.exerciseMapper.mapFrom(exerciseEntry.getExercise()));
        return exerciseEntryFromServer;
    }

    public ExerciseEntryFromServer tryMapFrom(ExerciseEntry exerciseEntry) {
        try {
            return mapFrom(exerciseEntry);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }
}
