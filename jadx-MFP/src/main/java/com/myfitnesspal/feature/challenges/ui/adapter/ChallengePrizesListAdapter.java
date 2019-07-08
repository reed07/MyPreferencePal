package com.myfitnesspal.feature.challenges.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengePrize;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengePrizesViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.List;

public class ChallengePrizesListAdapter<T> extends BaseAdapter {
    private Context context;
    private Lazy<ImageService> imageService;
    private LayoutInflater inflater;
    private int layoutId;
    private List<ChallengePrize> prizeList;

    private static class ViewHolder {
        private TextView description;
        private MfpImageView imageView;
        private TextView name;
        private View separator;

        public ViewHolder(View view) {
            this.name = (TextView) ViewUtils.findById(view, R.id.tvPrizeName);
            this.description = (TextView) ViewUtils.findById(view, R.id.tvPrizeDescription);
            this.imageView = (MfpImageView) ViewUtils.findById(view, R.id.ivPrize);
            this.separator = ViewUtils.findById(view, R.id.separator);
        }

        /* access modifiers changed from: 0000 */
        public void bind(Context context, Lazy<ImageService> lazy, ChallengePrize challengePrize) {
            ViewUtils.setVisible(true, this.separator);
            ChallengesUtil.setImageToImageView(context, challengePrize.getIconImage(), this.imageView, lazy);
            this.name.setText(challengePrize.getTitle());
            this.description.setText(challengePrize.getDescription());
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ChallengePrizesListAdapter(LayoutInflater layoutInflater, BaseViewModel baseViewModel, int i, Lazy<ImageService> lazy, Context context2) {
        this.inflater = layoutInflater;
        this.prizeList = ((ChallengePrizesViewModel) baseViewModel).getChallengePrizeList();
        this.layoutId = i;
        this.imageService = lazy;
        this.context = context2;
    }

    public int getCount() {
        return this.prizeList.size();
    }

    public Object getItem(int i) {
        return this.prizeList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(this.layoutId, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        ((ViewHolder) view.getTag()).bind(this.context, this.imageService, (ChallengePrize) this.prizeList.get(i));
        return view;
    }
}
