package com.myfitnesspal.shared.service.syncv1;

import com.myfitnesspal.shared.model.v15.SyncPointer;
import java.util.List;

public interface SyncPointerService {
    List<SyncPointer> getLastSyncPointers();

    void setLastSyncPointers(List<SyncPointer> list);
}
