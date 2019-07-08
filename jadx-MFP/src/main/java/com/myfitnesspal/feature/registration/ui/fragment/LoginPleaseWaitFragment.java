package com.myfitnesspal.feature.registration.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public class LoginPleaseWaitFragment extends MfpFragment {
    private static final long SHOW_PROGRESS_VIEW_DELAY_MILLIS = 500;
    private String currentStatus = "";
    private Handler handler = new Handler();
    @BindView(2131363284)
    View progressView;
    @BindView(2131363283)
    TextView textView;

    public static LoginPleaseWaitFragment newInstance() {
        return new LoginPleaseWaitFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.login_please_wait, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        updateOverlayText();
        return inflate;
    }

    public void setCurrentStatus(String str) {
        this.currentStatus = str;
        updateOverlayText();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(Extras.CURRENT_STATUS, this.currentStatus);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                ViewUtils.setVisible(LoginPleaseWaitFragment.this.progressView);
            }
        }, SHOW_PROGRESS_VIEW_DELAY_MILLIS);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            setCurrentStatus(bundle.getString(Extras.CURRENT_STATUS));
        }
    }

    private void updateOverlayText() {
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.setText(Strings.notEmpty(this.currentStatus) ? this.currentStatus : getActivity().getString(R.string.please_wait));
        }
    }
}
