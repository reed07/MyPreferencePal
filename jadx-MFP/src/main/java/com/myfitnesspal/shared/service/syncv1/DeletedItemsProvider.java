package com.myfitnesspal.shared.service.syncv1;

import com.myfitnesspal.shared.model.v2.MfpDatabaseObjectV2;
import java.util.List;

public interface DeletedItemsProvider {
    void clearDeletedItems(String str);

    List<MfpDatabaseObjectV2> getDeletedItems(String str);
}
