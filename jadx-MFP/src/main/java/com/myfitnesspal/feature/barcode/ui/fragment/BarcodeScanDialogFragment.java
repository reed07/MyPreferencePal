package com.myfitnesspal.feature.barcode.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class BarcodeScanDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_SEARCHING = "is_searching";
    @Inject
    Lazy<ConfigService> configService;
    private OnBarcodeScanDialogListener listener;
    private boolean searching;
    private int type;

    public interface OnBarcodeScanDialogListener {
        void onDismiss(BarcodeScanDialogFragment barcodeScanDialogFragment);

        void onErrorAcknowledged(BarcodeScanDialogFragment barcodeScanDialogFragment, int i);

        void onSearchFinished(BarcodeScanDialogFragment barcodeScanDialogFragment);

        void onSearchStarted(BarcodeScanDialogFragment barcodeScanDialogFragment);

        void onShow(BarcodeScanDialogFragment barcodeScanDialogFragment);
    }

    private int getMessage(int i) {
        if (i == 0) {
            return R.string.error_while_acquiring_camera;
        }
        switch (i) {
            case Dialogs.MALFORMED_BARCODE_DIALOG /*7610*/:
                return R.string.malformed_barcode;
            case Dialogs.INVALID_BARCODE_CHECKSUM_DIALOG /*7611*/:
                return R.string.invalid_barcode;
            case Dialogs.NONEXISTENT_FOOD_ID_DIALOG /*7612*/:
                return R.string.non_existent_food;
            case Dialogs.DEVICE_OFFLINE_DIALOG /*7613*/:
                return R.string.cannot_search_while_offline;
            default:
                switch (i) {
                    case Dialogs.ACQUIRE_CAMERA_FAILED /*7621*/:
                        return R.string.error_while_acquiring_camera;
                    case Dialogs.NO_MATCH_DIALOG /*7622*/:
                        return R.string.noMatchFound;
                    default:
                        return R.string.unknown_error;
                }
        }
    }

    public static BarcodeScanDialogFragment newInstance(int i, int i2) {
        BarcodeScanDialogFragment barcodeScanDialogFragment = new BarcodeScanDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        bundle.putInt("errorCode", i2);
        barcodeScanDialogFragment.setArguments(bundle);
        return barcodeScanDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public void onStop() {
        super.onStop();
        getActivity().setRequestedOrientation(-1);
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        OnBarcodeScanDialogListener onBarcodeScanDialogListener = this.listener;
        if (onBarcodeScanDialogListener == null) {
            return;
        }
        if (z) {
            onBarcodeScanDialogListener.onDismiss(this);
        } else {
            onBarcodeScanDialogListener.onShow(this);
        }
    }

    public void setOnBarcodeScanDialogListener(OnBarcodeScanDialogListener onBarcodeScanDialogListener) {
        this.listener = onBarcodeScanDialogListener;
    }

    public boolean isSearching() {
        return this.searching;
    }

    public int getType() {
        return this.type;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        LayoutInflater from = LayoutInflater.from(getDialogContextThemeWrapper());
        this.type = getArguments().getInt("type");
        this.searching = BundleUtils.getBoolean(bundle, EXTRA_SEARCHING, false);
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(getDialogContextThemeWrapper());
        if (this.type != 6007) {
            mfpAlertDialogBuilder.setNegativeButton((int) R.string.dismiss, (OnClickListener) new OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    BarcodeScanDialogFragment.lambda$onCreateDialog$1(BarcodeScanDialogFragment.this, dialogInterface, i);
                }
            }).setMessage((CharSequence) getString(getMessage(this.type))).setTitle((CharSequence) getResources().getString(R.string.error));
        } else {
            OnBarcodeScanDialogListener onBarcodeScanDialogListener = this.listener;
            if (onBarcodeScanDialogListener != null) {
                onBarcodeScanDialogListener.onSearchStarted(this);
            }
            this.searching = true;
            mfpAlertDialogBuilder.setOnDismissListener(new OnDismissListener() {
                public final void onDismiss(DialogInterface dialogInterface) {
                    BarcodeScanDialogFragment.lambda$onCreateDialog$0(BarcodeScanDialogFragment.this, dialogInterface);
                }
            }).setView(from.inflate(R.layout.progress, null));
        }
        mfpAlertDialogBuilder.setCancelable(false).setCanceledOnTouchOutside(false);
        AlertDialog create = mfpAlertDialogBuilder.create();
        create.setOnShowListener(new OnShowListener() {
            public final void onShow(DialogInterface dialogInterface) {
                BarcodeScanDialogFragment.lambda$onCreateDialog$2(BarcodeScanDialogFragment.this, dialogInterface);
            }
        });
        create.setOnDismissListener(new OnDismissListener() {
            public final void onDismiss(DialogInterface dialogInterface) {
                BarcodeScanDialogFragment.lambda$onCreateDialog$3(BarcodeScanDialogFragment.this, dialogInterface);
            }
        });
        return create;
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(BarcodeScanDialogFragment barcodeScanDialogFragment, DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        barcodeScanDialogFragment.searching = false;
        OnBarcodeScanDialogListener onBarcodeScanDialogListener = barcodeScanDialogFragment.listener;
        if (onBarcodeScanDialogListener != null) {
            onBarcodeScanDialogListener.onSearchFinished(barcodeScanDialogFragment);
        }
    }

    public static /* synthetic */ void lambda$onCreateDialog$1(BarcodeScanDialogFragment barcodeScanDialogFragment, DialogInterface dialogInterface, int i) {
        barcodeScanDialogFragment.dismiss();
        OnBarcodeScanDialogListener onBarcodeScanDialogListener = barcodeScanDialogFragment.listener;
        if (onBarcodeScanDialogListener != null) {
            onBarcodeScanDialogListener.onErrorAcknowledged(barcodeScanDialogFragment, BundleUtils.getInt(barcodeScanDialogFragment.getArguments(), "errorCode"));
        }
    }

    public static /* synthetic */ void lambda$onCreateDialog$2(BarcodeScanDialogFragment barcodeScanDialogFragment, DialogInterface dialogInterface) {
        OnBarcodeScanDialogListener onBarcodeScanDialogListener = barcodeScanDialogFragment.listener;
        if (onBarcodeScanDialogListener != null) {
            onBarcodeScanDialogListener.onShow(barcodeScanDialogFragment);
        }
    }

    public static /* synthetic */ void lambda$onCreateDialog$3(BarcodeScanDialogFragment barcodeScanDialogFragment, DialogInterface dialogInterface) {
        OnBarcodeScanDialogListener onBarcodeScanDialogListener = barcodeScanDialogFragment.listener;
        if (onBarcodeScanDialogListener != null) {
            onBarcodeScanDialogListener.onDismiss(barcodeScanDialogFragment);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_SEARCHING, this.searching);
    }
}
