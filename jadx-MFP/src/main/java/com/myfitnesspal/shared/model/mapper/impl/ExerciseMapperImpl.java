package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExercise.ExerciseTypes;
import com.uacf.core.util.Ln;
import java.io.IOException;

public class ExerciseMapperImpl implements ExerciseMapper {
    public Exercise reverseMap(MfpExercise mfpExercise) {
        Exercise exercise = new Exercise();
        exercise.setLocalId(mfpExercise.getLocalId());
        exercise.setMasterDatabaseId(mfpExercise.getMasterId());
        exercise.setUid(mfpExercise.getVersion());
        exercise.setOriginalUid(mfpExercise.getId());
        exercise.setExerciseType(ExerciseTypes.toValue(mfpExercise.getType()));
        exercise.setDescription(mfpExercise.getDescription());
        exercise.setIsPublic(mfpExercise.isPublic().booleanValue());
        exercise.setMets(mfpExercise.getMetsDouble().floatValue());
        exercise.setIsDeleted(mfpExercise.isDeleted());
        exercise.setCalorieAdjustmentExercise(mfpExercise.isCalorieAdjustment());
        exercise.setOwnerUserId(mfpExercise.getOwnerUserLocalId());
        exercise.setOwnerUserMasterId(mfpExercise.getOwnerUserMasterId());
        return exercise;
    }

    public MfpExercise mapFrom(Exercise exercise) throws IOException {
        MfpExercise mfpExercise = new MfpExercise();
        mfpExercise.setLocalId(exercise.getLocalId());
        mfpExercise.setMasterId(exercise.getMasterDatabaseId());
        mfpExercise.setId(exercise.getOriginalUid());
        mfpExercise.setVersion(exercise.getUid());
        mfpExercise.setType(ExerciseTypes.toString(exercise.getExerciseType()));
        mfpExercise.setDescription(exercise.getDescription());
        mfpExercise.setIsPublic(Boolean.valueOf(exercise.isPublic()));
        mfpExercise.setMets(Double.valueOf((double) exercise.getMets()));
        mfpExercise.setMetsDouble(Double.valueOf((double) exercise.getMets()));
        mfpExercise.setDeleted(exercise.isDeleted());
        mfpExercise.setIsCalorieAdjustment(exercise.isCalorieAdjustmentExercise());
        mfpExercise.setOwnerUserLocalId(exercise.getOwnerUserId());
        mfpExercise.setOwnerUserMasterId(exercise.getOwnerUserMasterId());
        return mfpExercise;
    }

    public MfpExercise tryMapFrom(Exercise exercise) {
        try {
            return mapFrom(exercise);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }
}
