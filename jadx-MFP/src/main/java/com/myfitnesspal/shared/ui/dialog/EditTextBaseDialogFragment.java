package com.myfitnesspal.shared.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.uacf.core.util.Strings;

public abstract class EditTextBaseDialogFragment extends CustomLayoutBaseDialogFragment {
    @BindView(2131362434)
    protected TextView editText;

    /* access modifiers changed from: protected */
    public abstract String getInitialText();

    /* access modifiers changed from: protected */
    public abstract String getTitle();

    /* access modifiers changed from: protected */
    public void onCancelClicked() {
    }

    /* access modifiers changed from: protected */
    public abstract void onOkClicked(String str);

    /* access modifiers changed from: protected */
    public abstract void setBuilderProperties(MfpAlertDialogBuilder mfpAlertDialogBuilder);

    /* access modifiers changed from: protected */
    public abstract void setEditTextProperties();

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        View inflate = LayoutInflater.from(activity).inflate(R.layout.dialog_edit_text, null);
        ButterKnife.bind((Object) this, inflate);
        this.editText.setText(getInitialText());
        showSoftInput();
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(activity);
        setBuilderProperties(mfpAlertDialogBuilder);
        AlertDialog create = mfpAlertDialogBuilder.setTitle((CharSequence) getTitle()).setView(inflate).setPositiveButton((int) R.string.ok, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                EditTextBaseDialogFragment.this.handleOkClicked(Strings.toString(EditTextBaseDialogFragment.this.editText.getText()));
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                EditTextBaseDialogFragment.this.handleCancelClicked();
            }
        }).create();
        setEditTextProperties();
        this.editText.setImeOptions(2);
        this.editText.setOnEditorActionListener(new OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return EditTextBaseDialogFragment.lambda$onCreateDialog$2(EditTextBaseDialogFragment.this, textView, i, keyEvent);
            }
        });
        return create;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r6.getAction() == 0) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ boolean lambda$onCreateDialog$2(com.myfitnesspal.shared.ui.dialog.EditTextBaseDialogFragment r3, android.widget.TextView r4, int r5, android.view.KeyEvent r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 1
            if (r5 == 0) goto L_0x000a
            r2 = 2
            if (r5 != r2) goto L_0x0023
        L_0x000a:
            int r5 = r6.getAction()
            if (r5 != r1) goto L_0x001c
            java.lang.CharSequence r4 = r4.getText()
            java.lang.String r4 = com.uacf.core.util.Strings.toString(r4)
            r3.handleOkClicked(r4)
            goto L_0x0024
        L_0x001c:
            int r4 = r6.getAction()
            if (r4 != 0) goto L_0x0023
            goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.dialog.EditTextBaseDialogFragment.lambda$onCreateDialog$2(com.myfitnesspal.shared.ui.dialog.EditTextBaseDialogFragment, android.widget.TextView, int, android.view.KeyEvent):boolean");
    }

    /* access modifiers changed from: private */
    public void handleOkClicked(String str) {
        onOkClicked(str);
        dismiss();
    }

    /* access modifiers changed from: private */
    public void handleCancelClicked() {
        onCancelClicked();
        dismiss();
    }

    /* access modifiers changed from: protected */
    public void hideSoftInput() {
        hideSoftInputFor(this.editText);
    }
}
