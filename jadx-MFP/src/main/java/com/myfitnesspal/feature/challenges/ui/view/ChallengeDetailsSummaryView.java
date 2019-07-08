package com.myfitnesspal.feature.challenges.ui.view;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeMetric;
import com.myfitnesspal.feature.challenges.model.ChallengePrize;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeDetailsSummaryViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.Date;

public class ChallengeDetailsSummaryView {
    private Context context;
    private Lazy<ImageService> imageService;
    private ChallengeDetailsSummaryViewModel viewModel;

    static class ViewHolder {
        @BindView(2131363921)
        TextView tvMetricName;
        @BindView(2131363922)
        TextView tvMetricValue;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }

        public void bind(ChallengeMetric challengeMetric, Context context) {
            TextViewUtils.setText(this.tvMetricName, context.getString(ChallengesUtil.getVanityMetricStringBasedOnMetricType(challengeMetric.getType())));
            TextViewUtils.setText(this.tvMetricValue, NumberUtils.localeStringFromLongWithSeparators(challengeMetric.getValue()));
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.tvMetricName = (TextView) Utils.findRequiredViewAsType(view, R.id.tvMetricName, "field 'tvMetricName'", TextView.class);
            viewHolder.tvMetricValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tvMetricValue, "field 'tvMetricValue'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.tvMetricName = null;
                viewHolder.tvMetricValue = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ChallengeDetailsSummaryView(ChallengeDetailsSummaryViewModel challengeDetailsSummaryViewModel, Lazy<ImageService> lazy) {
        this.viewModel = challengeDetailsSummaryViewModel;
        this.imageService = lazy;
    }

    public void addView(Context context2, ViewGroup viewGroup, View view) {
        ViewGroup viewGroup2 = viewGroup;
        View view2 = view;
        this.context = context2.getApplicationContext();
        LayoutInflater from = LayoutInflater.from(this.context);
        ViewGroup viewGroup3 = (ViewGroup) from.inflate(R.layout.challenge_details_summary, viewGroup2, false);
        ChallengeBannerImageView challengeBannerImageView = (ChallengeBannerImageView) ViewUtils.findById(viewGroup3, R.id.challenge_banner);
        TextView textView = (TextView) ViewUtils.findById(viewGroup3, R.id.tvParticipantCount);
        TextView textView2 = (TextView) ViewUtils.findById(viewGroup3, R.id.tvDaysRemaining);
        TextView textView3 = (TextView) ViewUtils.findById(viewGroup3, R.id.tvDayCount);
        TextView textView4 = (TextView) ViewUtils.findById(viewGroup3, R.id.tvTotalDays);
        ProgressBar progressBar = (ProgressBar) ViewUtils.findById(viewGroup3, R.id.pbProgress);
        View findById = ViewUtils.findById(viewGroup3, R.id.llProgressContainer);
        ViewGroup viewGroup4 = (ViewGroup) ViewUtils.findById(viewGroup3, R.id.challenge_details_item1);
        View findById2 = ViewUtils.findById(viewGroup3, R.id.separator);
        TextView textView5 = (TextView) ViewUtils.findById(viewGroup3, R.id.tvPrizeName);
        TextView textView6 = (TextView) ViewUtils.findById(viewGroup3, R.id.tvPrizeDescription);
        ImageView imageView = (ImageView) ViewUtils.findById(viewGroup3, R.id.ivPrize);
        if (view2 != null) {
            viewGroup4.addView(view2);
        }
        ChallengePrize grandPrize = this.viewModel.getGrandPrize();
        ViewUtils.setVisible(findById2);
        TextViewUtils.setText(textView5, grandPrize.getTitle());
        TextViewUtils.setText(textView6, grandPrize.getDescription());
        ChallengesUtil.setImageToImageView(this.context, grandPrize.getIconImage(), imageView, this.imageService);
        textView.setText(this.viewModel.getParticipantCountAsString());
        challengeBannerImageView.setBannerImage(this.context, this.viewModel.getBannerImage(), this.imageService);
        if (this.viewModel.isChallengeActive()) {
            updateActiveChallengeProgress(this.context, textView2, textView3, textView4, progressBar);
        } else {
            updateExpiredChallengeProgress(this.context, textView2, progressBar, findById);
        }
        updateVanityMetrics(from, viewGroup3);
        viewGroup.addView(viewGroup3);
    }

    private void updateVanityMetrics(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (CollectionUtils.notEmpty((Collection<?>) this.viewModel.getVanityMetrics())) {
            ViewGroup viewGroup2 = (ViewGroup) ViewUtils.findById(viewGroup, R.id.llVanityMetricsContainer);
            ViewUtils.setVisible(true, viewGroup2);
            for (ChallengeMetric inflateMetricViewAndAdd : this.viewModel.getVanityMetrics()) {
                viewGroup2.addView(inflateMetricViewAndAdd(layoutInflater, viewGroup2, inflateMetricViewAndAdd));
            }
        }
    }

    private void updateExpiredChallengeProgress(Context context2, TextView textView, ProgressBar progressBar, View view) {
        ViewUtils.setVisible(false, view);
        ViewUtils.setVisible(false, progressBar);
        Date parse = DateTimeUtils.parse(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), this.viewModel.getEndAtAsString());
        TextViewUtils.setText(textView, context2.getResources().getString(R.string.challenge_ended, new Object[]{DateTimeUtils.getDateStringFromDate(parse, Challenges.ENDED_CHALLENGE_DATE_FORMAT)}));
    }

    private void updateActiveChallengeProgress(Context context2, TextView textView, TextView textView2, TextView textView3, ProgressBar progressBar) {
        textView.setText(this.viewModel.getTimeRemainingInChallenge(context2));
        long numberOfDaysBetween = DateTimeUtils.getNumberOfDaysBetween(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), this.viewModel.getStartAtAsString(), this.viewModel.getEndAtAsString());
        textView3.setText(Strings.toString(Long.valueOf(numberOfDaysBetween)));
        int numberOfDaysBetween2 = ((int) DateTimeUtils.getNumberOfDaysBetween(DateTimeUtils.parseGivenFormat(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), this.viewModel.getStartAtAsString()), new Date())) + 1;
        String string = context2.getString(R.string.day_x_of_challenge, new Object[]{Integer.valueOf(numberOfDaysBetween2)});
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(context2.getResources().getColor(R.color.black_text)), 0, string.indexOf(Strings.toString(Integer.valueOf(numberOfDaysBetween2))) + Strings.toString(Integer.valueOf(numberOfDaysBetween2)).length(), 33);
        textView2.setText(spannableString);
        progressBar.setProgress(numberOfDaysBetween2);
        progressBar.setMax((int) numberOfDaysBetween);
    }

    private View inflateMetricViewAndAdd(LayoutInflater layoutInflater, ViewGroup viewGroup, ChallengeMetric challengeMetric) {
        View inflate = layoutInflater.inflate(R.layout.challenge_metric_item, viewGroup, false);
        new ViewHolder(inflate).bind(challengeMetric, this.context);
        return inflate;
    }
}
