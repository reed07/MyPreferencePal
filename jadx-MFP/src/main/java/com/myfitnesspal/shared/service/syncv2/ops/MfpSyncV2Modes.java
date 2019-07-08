package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.shared.constants.SyncResourceName;
import com.myfitnesspal.shared.db.table.ExercisesTable;
import com.myfitnesspal.shared.db.table.ImagesTable;
import com.uacf.sync.provider.sdk.model.SyncMode;
import com.uacf.sync.provider.sdk.model.SyncModeImport;
import java.util.Arrays;
import java.util.List;

public final class MfpSyncV2Modes {
    public static List<SyncMode> All = Arrays.asList(new SyncMode[]{ImportDefaultSet, ImportExercisesAndExerciseEntries, ImportImagesAndImageAssociations, ImportNotifications});
    public static final SyncMode ImportDefaultSet = new SyncModeImport(Arrays.asList(new String[]{"user", "nutrient_goal", SyncResourceName.PAID_SUBSCRIPTION, "user_application_settings"}));
    public static final SyncMode ImportExercisesAndExerciseEntries = new SyncModeImport(ExercisesTable.TABLE_NAME, Arrays.asList(new String[]{"exercise", "exercise_entry"}));
    public static final SyncMode ImportImagesAndImageAssociations = new SyncModeImport(ImagesTable.TABLE_NAME, Arrays.asList(new String[]{"image", SyncResourceName.IMAGE_ASSOCIATION}));
    public static final SyncMode ImportNotifications = new SyncModeImport("notifications", Arrays.asList(new String[]{"notification"}));
}
