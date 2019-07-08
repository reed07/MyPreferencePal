package com.myfitnesspal.feature.settings.ui.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.event.DismissDialogFragmentEvent;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Ln;

public class ProfileDialogFragment extends CustomLayoutBaseDialogFragment {
    private int id;

    public static ProfileDialogFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        ProfileDialogFragment profileDialogFragment = new ProfileDialogFragment();
        profileDialogFragment.setArguments(bundle);
        return profileDialogFragment;
    }

    private void hydrateFieldsFrom(Bundle bundle) {
        this.id = BundleUtils.getInt(bundle, "id");
    }

    public void onResume() {
        super.onResume();
        this.messageBus.register(this);
    }

    public void onPause() {
        super.onPause();
        this.messageBus.unregister(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        hydrateFieldsFrom(getArguments());
        try {
            if (this.id != 534) {
                return null;
            }
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle(getString(R.string.please_wait));
            progressDialog.setMessage(getString(R.string.removing_friend));
            return progressDialog;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    @Subscribe
    public void onDismissDialogFragmentEvent(DismissDialogFragmentEvent dismissDialogFragmentEvent) {
        getDialog().dismiss();
    }
}
