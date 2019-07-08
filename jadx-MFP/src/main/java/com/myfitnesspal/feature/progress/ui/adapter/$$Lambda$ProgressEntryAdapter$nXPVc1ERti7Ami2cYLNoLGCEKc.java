package com.myfitnesspal.feature.progress.ui.adapter;

import com.myfitnesspal.feature.progress.model.ProgressEntryItem;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.uacf.core.util.ReturningFunction1;

/* renamed from: com.myfitnesspal.feature.progress.ui.adapter.-$$Lambda$ProgressEntryAdapter$nXPVc-1ERti7Ami2cYLNoLGCEKc reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ProgressEntryAdapter$nXPVc1ERti7Ami2cYLNoLGCEKc implements ReturningFunction1 {
    public static final /* synthetic */ $$Lambda$ProgressEntryAdapter$nXPVc1ERti7Ami2cYLNoLGCEKc INSTANCE = new $$Lambda$ProgressEntryAdapter$nXPVc1ERti7Ami2cYLNoLGCEKc();

    private /* synthetic */ $$Lambda$ProgressEntryAdapter$nXPVc1ERti7Ami2cYLNoLGCEKc() {
    }

    public final Object execute(Object obj) {
        return Boolean.valueOf(((ProgressEntryViewModel) ((ProgressEntryItem) obj)).isShowInList());
    }
}
