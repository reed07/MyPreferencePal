package com.myfitnesspal.feature.challenges.ui.fragment;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.MfpWebView;
import com.myfitnesspal.shared.util.MfpWebViewChromeClientWithProgress;
import com.uacf.core.util.TextViewUtils;

public class JoinedChallengeDetailsFragment extends ChallengeTabFragmentBase {
    private static final String EXTRAS_HEADLINE = "headline";
    private static final String EXTRAS_URL = "url";
    @BindView(2131362307)
    MfpWebView challengeWebView;
    private String headline;
    @BindView(2131363908)
    TextView tvDetailsHeadline;
    private String url;

    static /* synthetic */ boolean lambda$onCreateView$0(View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$onCreateView$1(View view) {
        return true;
    }

    public static JoinedChallengeDetailsFragment newInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        JoinedChallengeDetailsFragment joinedChallengeDetailsFragment = new JoinedChallengeDetailsFragment();
        bundle.putString("url", str2);
        bundle.putString(EXTRAS_HEADLINE, str);
        joinedChallengeDetailsFragment.setArguments(bundle);
        return joinedChallengeDetailsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.url = getArguments().getString("url");
        this.headline = getArguments().getString(EXTRAS_HEADLINE);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.challenge_joined_details_fragment, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.challengeWebView.setWebChromeClient(new MfpWebViewChromeClientWithProgress((MfpActivity) getActivity()));
        this.challengeWebView.loadUrl(this.url);
        this.challengeWebView.setOnLongClickListener($$Lambda$JoinedChallengeDetailsFragment$ZQxcRHCJaqZ6Wq3ojm4gqKo6o.INSTANCE);
        if (VERSION.SDK_INT >= 23) {
            this.challengeWebView.setOnContextClickListener($$Lambda$JoinedChallengeDetailsFragment$yC4RsbolFp0eVeZTOzJgi7HZjmM.INSTANCE);
        }
        TextViewUtils.setText(this.tvDetailsHeadline, this.headline);
        return inflate;
    }
}
