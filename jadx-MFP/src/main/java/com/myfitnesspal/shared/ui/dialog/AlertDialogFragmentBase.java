package com.myfitnesspal.shared.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.StringRes;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase;
import com.uacf.core.util.Strings;

public abstract class AlertDialogFragmentBase<TDialog extends AlertDialogFragmentBase, TData> extends CustomLayoutBaseDialogFragment implements OnClickListener {
    private static final String EXTRA_MESSAGE = "message";
    private static final String EXTRA_MESSAGE_ID = "message_id";
    private static final String EXTRA_NEGATIVE_TEXT_ID = "negative_text_id";
    private static final String EXTRA_NEUTRAL_TEXT_ID = "neutral_text_id";
    private static final String EXTRA_POSITIVE_TEXT_ID = "positive_text_id";
    private static final String EXTRA_TITLE_ID = "title_id";
    private DialogCancelListener cancelListener;
    private String message;
    private int messageId;
    private DialogNegativeListener negativeListener;
    private int negativeTextId;
    private DialogNeutralListener neutralListener;
    private int neutralTextId;
    private DialogPositiveListener<TData> positiveListener;
    private int positiveTextId;
    private int titleId;

    public interface DialogCancelListener {
        void onClick();
    }

    public interface DialogNegativeListener {
        void onClick();
    }

    public interface DialogNeutralListener {
        void onClick();
    }

    public interface DialogPositiveListener<TData> {
        void onClick(TData tdata);
    }

    /* access modifiers changed from: protected */
    public void configureBuilder(MfpAlertDialogBuilder mfpAlertDialogBuilder) {
    }

    /* access modifiers changed from: protected */
    public TData getData() {
        return null;
    }

    public TDialog setTitle(int i) {
        this.titleId = i;
        return this;
    }

    public TDialog setPositiveText(@StringRes int i, DialogPositiveListener dialogPositiveListener) {
        this.positiveTextId = i;
        this.positiveListener = dialogPositiveListener;
        return this;
    }

    public TDialog setPositiveListener(DialogPositiveListener dialogPositiveListener) {
        this.positiveListener = dialogPositiveListener;
        return this;
    }

    public TDialog setNegativeText(@StringRes int i, DialogNegativeListener dialogNegativeListener) {
        this.negativeTextId = i;
        this.negativeListener = dialogNegativeListener;
        return this;
    }

    public TDialog setNegativeListener(DialogNegativeListener dialogNegativeListener) {
        this.negativeListener = dialogNegativeListener;
        return this;
    }

    public TDialog setNeutralText(@StringRes int i, DialogNeutralListener dialogNeutralListener) {
        this.neutralTextId = i;
        this.neutralListener = dialogNeutralListener;
        return this;
    }

    public TDialog setNeutralListener(DialogNeutralListener dialogNeutralListener) {
        this.neutralListener = dialogNeutralListener;
        return this;
    }

    public TDialog setCancelListener(DialogCancelListener dialogCancelListener) {
        this.cancelListener = dialogCancelListener;
        return this;
    }

    public TDialog setMessage(String str) {
        this.messageId = 0;
        this.message = str;
        return this;
    }

    public TDialog setMessage(@StringRes int i) {
        this.message = null;
        this.messageId = i;
        return this;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (bundle != null) {
            this.titleId = bundle.getInt(EXTRA_TITLE_ID, this.titleId);
            this.positiveTextId = bundle.getInt(EXTRA_POSITIVE_TEXT_ID, this.positiveTextId);
            this.negativeTextId = bundle.getInt(EXTRA_NEGATIVE_TEXT_ID, this.negativeTextId);
            this.neutralTextId = bundle.getInt(EXTRA_NEUTRAL_TEXT_ID, this.neutralTextId);
            this.message = bundle.getString("message", this.message);
            this.messageId = bundle.getInt("message_id", this.messageId);
        }
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(getDialogContextThemeWrapper());
        configureBuilder(mfpAlertDialogBuilder);
        int i = this.titleId;
        if (i > 0) {
            mfpAlertDialogBuilder.setTitle(i);
        }
        if (Strings.notEmpty(this.message)) {
            mfpAlertDialogBuilder.setMessage((CharSequence) this.message);
        } else {
            int i2 = this.messageId;
            if (i2 > 0) {
                mfpAlertDialogBuilder.setMessage(i2);
            }
        }
        int i3 = this.positiveTextId;
        if (i3 > 0) {
            mfpAlertDialogBuilder.setPositiveButton(i3, (OnClickListener) this);
        }
        int i4 = this.negativeTextId;
        if (i4 > 0) {
            mfpAlertDialogBuilder.setNegativeButton(i4, (OnClickListener) this);
        }
        int i5 = this.neutralTextId;
        if (i5 > 0) {
            mfpAlertDialogBuilder.setNeutralButton(i5, (OnClickListener) this);
        }
        int i6 = this.titleId;
        if (i6 > 0) {
            mfpAlertDialogBuilder.setTitle(i6);
        }
        return mfpAlertDialogBuilder.create();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(EXTRA_TITLE_ID, this.titleId);
        bundle.putInt(EXTRA_POSITIVE_TEXT_ID, this.positiveTextId);
        bundle.putInt(EXTRA_NEGATIVE_TEXT_ID, this.negativeTextId);
        bundle.putInt(EXTRA_NEUTRAL_TEXT_ID, this.neutralTextId);
        bundle.putString("message", this.message);
        bundle.putInt("message_id", this.messageId);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case -3:
                onDialogNeutral();
                return;
            case -2:
                onDialogNegative();
                return;
            case -1:
                onDialogPositive();
                return;
            default:
                return;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        onDialogCancel();
    }

    /* access modifiers changed from: protected */
    public void onDialogPositive() {
        DialogPositiveListener<TData> dialogPositiveListener = this.positiveListener;
        if (dialogPositiveListener != null) {
            dialogPositiveListener.onClick(getData());
        }
    }

    /* access modifiers changed from: protected */
    public void onDialogNegative() {
        DialogNegativeListener dialogNegativeListener = this.negativeListener;
        if (dialogNegativeListener != null) {
            dialogNegativeListener.onClick();
        }
    }

    /* access modifiers changed from: protected */
    public void onDialogNeutral() {
        DialogNeutralListener dialogNeutralListener = this.neutralListener;
        if (dialogNeutralListener != null) {
            dialogNeutralListener.onClick();
        }
    }

    /* access modifiers changed from: protected */
    public void onDialogCancel() {
        DialogCancelListener dialogCancelListener = this.cancelListener;
        if (dialogCancelListener != null) {
            dialogCancelListener.onClick();
        }
    }
}
