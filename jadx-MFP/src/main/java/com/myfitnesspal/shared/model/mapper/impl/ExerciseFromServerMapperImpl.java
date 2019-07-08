package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v15.ExerciseObjectFromServer;
import com.uacf.core.util.Ln;
import java.io.IOException;

public class ExerciseFromServerMapperImpl implements ExerciseFromServerMapper {
    public Exercise reverseMap(ExerciseObjectFromServer exerciseObjectFromServer) {
        Exercise exercise = new Exercise();
        exercise.setLocalId(exerciseObjectFromServer.getLocalId());
        exercise.setMasterDatabaseId(exerciseObjectFromServer.getMasterId());
        exercise.setUid(exerciseObjectFromServer.getUid());
        exercise.setOriginalUid(exerciseObjectFromServer.getOriginalUid());
        exercise.setDescription(exerciseObjectFromServer.getDescription());
        exercise.setIsDeleted(exerciseObjectFromServer.isDeleted());
        exercise.setIsPublic(exerciseObjectFromServer.isPublic());
        exercise.setOwnerUserId(exerciseObjectFromServer.getOwnerLocalUserId());
        exercise.setOwnerUserMasterId(exerciseObjectFromServer.getOwnerMasterUserId());
        exercise.setExerciseType(exerciseObjectFromServer.getType());
        exercise.setMets(exerciseObjectFromServer.getMets());
        exercise.setCalorieAdjustmentExercise(exerciseObjectFromServer.isCalorieAdjustment());
        return exercise;
    }

    public ExerciseObjectFromServer mapFrom(Exercise exercise) throws IOException {
        ExerciseObjectFromServer exerciseObjectFromServer = new ExerciseObjectFromServer();
        exerciseObjectFromServer.setLocalId(exercise.getLocalId());
        exerciseObjectFromServer.setMasterId(exercise.getMasterDatabaseId());
        exerciseObjectFromServer.setUid(exercise.getUid());
        exerciseObjectFromServer.setOriginalUid(exercise.getOriginalUid());
        exerciseObjectFromServer.setDescription(exercise.getDescription());
        exerciseObjectFromServer.setIsDeleted(exercise.isDeleted());
        exerciseObjectFromServer.setIsPublic(exercise.isPublic());
        exerciseObjectFromServer.setOwnerLocalUserId(exercise.getOwnerUserId());
        exerciseObjectFromServer.setOwnerMasterUserId(exercise.getOwnerUserMasterId());
        exerciseObjectFromServer.setType(exercise.getExerciseType());
        exerciseObjectFromServer.setMets(exercise.getMets());
        exerciseObjectFromServer.setIsCalorieAdjustment(exercise.isCalorieAdjustmentExercise());
        return exerciseObjectFromServer;
    }

    public ExerciseObjectFromServer tryMapFrom(Exercise exercise) {
        try {
            return mapFrom(exercise);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }
}
