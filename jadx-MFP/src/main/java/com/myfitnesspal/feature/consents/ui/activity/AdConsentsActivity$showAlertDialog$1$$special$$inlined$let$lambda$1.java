package com.myfitnesspal.feature.consents.ui.activity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged", "com/myfitnesspal/feature/consents/ui/activity/AdConsentsActivity$showAlertDialog$1$1$1$1", "com/myfitnesspal/feature/consents/ui/activity/AdConsentsActivity$showAlertDialog$1$$special$$inlined$with$lambda$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: AdConsentsActivity.kt */
final class AdConsentsActivity$showAlertDialog$1$$special$$inlined$let$lambda$1 implements OnCheckedChangeListener {
    final /* synthetic */ AdConsentsActivity$showAlertDialog$1 this$0;

    AdConsentsActivity$showAlertDialog$1$$special$$inlined$let$lambda$1(AdConsentsActivity$showAlertDialog$1 adConsentsActivity$showAlertDialog$1) {
        this.this$0 = adConsentsActivity$showAlertDialog$1;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.this$0.this$0.settingsCheckboxAction.invoke(this.this$0.$consentId, this.this$0.$itemView, Boolean.valueOf(z));
    }
}
