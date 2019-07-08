package com.myfitnesspal.feature.challenges.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengePrizesListAdapter;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class PrizesView {
    private Lazy<ImageService> imageService;
    private BaseViewModel viewModel;

    public PrizesView(BaseViewModel baseViewModel, Lazy<ImageService> lazy) {
        this.viewModel = baseViewModel;
        this.imageService = lazy;
    }

    public void addView(Context context, ViewGroup viewGroup) {
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup viewGroup2 = (ViewGroup) from.inflate(R.layout.challenge_prizes_prizes, viewGroup, false);
        LinearLayoutListAdapterView linearLayoutListAdapterView = (LinearLayoutListAdapterView) ViewUtils.findById(viewGroup2, R.id.lvPrizes);
        ((TextView) ViewUtils.findById(viewGroup2, R.id.tvTitle)).setText(context.getString(R.string.prizes));
        ChallengePrizesListAdapter challengePrizesListAdapter = new ChallengePrizesListAdapter(from, this.viewModel, R.layout.challenge_prize_summary_item, this.imageService, context);
        linearLayoutListAdapterView.setAdapter(challengePrizesListAdapter);
        viewGroup.addView(viewGroup2);
    }
}
