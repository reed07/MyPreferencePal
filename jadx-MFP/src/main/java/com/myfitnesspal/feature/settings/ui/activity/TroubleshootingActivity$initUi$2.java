package com.myfitnesspal.feature.settings.ui.activity;

import android.arch.lifecycle.Observer;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.model.Resource;
import com.myfitnesspal.feature.consents.model.Resource.Error;
import com.myfitnesspal.feature.consents.model.Resource.Loading;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/myfitnesspal/feature/consents/model/Resource;", "", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: TroubleshootingActivity.kt */
final class TroubleshootingActivity$initUi$2<T> implements Observer<Resource<? extends String>> {
    final /* synthetic */ TroubleshootingActivity this$0;

    TroubleshootingActivity$initUi$2(TroubleshootingActivity troubleshootingActivity) {
        this.this$0 = troubleshootingActivity;
    }

    public final void onChanged(@Nullable Resource<String> resource) {
        if (resource instanceof Success) {
            this.this$0.hideProgressDialog();
            TroubleshootingActivity troubleshootingActivity = this.this$0;
            Success success = (Success) resource;
            String str = (String) success.getData();
            if (str == null) {
                str = "";
            }
            troubleshootingActivity.showAlertDialog(str);
            TroubleshootingActivity troubleshootingActivity2 = this.this$0;
            String str2 = (String) success.getData();
            if (str2 == null) {
                str2 = "";
            }
            troubleshootingActivity2.responseString = str2;
            this.this$0.uploadedSuccessful = true;
        } else if (resource instanceof Loading) {
            this.this$0.showProgressDialog(R.string.please_wait);
        } else if (resource instanceof Error) {
            this.this$0.hideProgressDialog();
            this.this$0.showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage(this.this$0.getString(R.string.error_transferring_data_to_server))).setNegativeText(R.string.dismiss, null), "error_dialog_fragment");
        }
    }
}
