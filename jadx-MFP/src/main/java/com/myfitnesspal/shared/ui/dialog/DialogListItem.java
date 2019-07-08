package com.myfitnesspal.shared.ui.dialog;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/myfitnesspal/shared/ui/dialog/DialogListItem;", "", "imageResId", "", "getImageResId", "()I", "text", "", "getText", "()Ljava/lang/String;", "textResId", "getTextResId", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: DialogListItem.kt */
public interface DialogListItem {
    int getImageResId();

    @Nullable
    String getText();

    int getTextResId();
}
