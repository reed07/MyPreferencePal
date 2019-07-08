package com.myfitnesspal.feature.challenges.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import dagger.Lazy;

public class ChallengeBannerImageView extends RelativeLayout {
    @BindView(2131362858)
    ResizableImageView bannerImage;
    @BindView(2131363319)
    ProgressBar progressBar;

    public ChallengeBannerImageView(Context context) {
        super(context);
        init(context);
    }

    public ChallengeBannerImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ChallengeBannerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public ChallengeBannerImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.challenge_banner_image, this, true);
        ButterKnife.bind((View) this);
    }

    public void setBannerImage(Context context, ChallengeImageOutput challengeImageOutput, Lazy<ImageService> lazy) {
        ChallengesUtil.setImageToImageView(context, challengeImageOutput, (ImageView) this.bannerImage, lazy, this.progressBar);
    }
}
