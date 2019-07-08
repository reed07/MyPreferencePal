package com.myfitnesspal.shared.extension;

import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.Map.Entry;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/myfitnesspal/shared/extension/SpannableUtil$setLinkSpan$1", "Landroid/text/style/ClickableSpan;", "onClick", "", "view", "Landroid/view/View;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SpannableExt.kt */
public final class SpannableUtil$setLinkSpan$1 extends ClickableSpan {
    final /* synthetic */ Entry $entry;

    SpannableUtil$setLinkSpan$1(Entry entry) {
        this.$entry = entry;
    }

    public void onClick(@Nullable View view) {
        ((OnClickListener) this.$entry.getValue()).onClick(view);
    }
}
