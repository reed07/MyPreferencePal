package com.myfitnesspal.feature.challenges.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.uacf.core.util.TextViewUtils;

public class UnjoinedChallengeDetailSubView extends LinearLayout {
    @BindView(2131363951)
    TextView challengeGoal;

    public UnjoinedChallengeDetailSubView(Context context) {
        super(context);
        init(context);
    }

    public UnjoinedChallengeDetailSubView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public UnjoinedChallengeDetailSubView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public UnjoinedChallengeDetailSubView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.unjoined_challenge_detail_sub_view, this, true);
        ButterKnife.bind((View) this);
    }

    public void setData(String str, String str2, String str3) {
        TextViewUtils.setText(this.challengeGoal, str3);
    }
}
