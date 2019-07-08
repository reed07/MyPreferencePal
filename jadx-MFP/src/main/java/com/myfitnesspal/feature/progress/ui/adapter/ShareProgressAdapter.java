package com.myfitnesspal.feature.progress.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.ArtifactType;
import com.myfitnesspal.feature.progress.ui.viewmodel.ArtifactViewModel;
import com.squareup.otto.Bus;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareProgressAdapter extends PagerAdapter {
    private String customCaption;
    private BitmapDrawable image;
    private boolean isProgressPicsV2Enabled;
    private Bus messageBus;
    private List<ArtifactViewModel> models;
    private OnClickListener onEditClickListener = new OnClickListener() {
        public void onClick(View view) {
            ShareProgressAdapter.this.setCursorVisible(true);
        }
    };
    private TextWatcher onTextChangeListener = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ShareProgressAdapter.this.setCustomCaption(charSequence.toString());
        }
    };
    private Map<Integer, View> positionToView = new HashMap();
    private boolean showCustomCaption;

    public interface ShareViewHolder {
        ArtifactViewModel getModel();

        View getRoot();
    }

    public static class SummaryViewHolder implements ShareViewHolder {
        public final View artifactContainer;
        public final ImageView imageView;
        public final ArtifactViewModel model;
        public final View root;
        public final TextView stepsValue;
        public final TextView streakValue;
        public final TextView weightString;
        public final TextView weightValue;

        private SummaryViewHolder(View view, ArtifactViewModel artifactViewModel) {
            this.root = view;
            this.model = artifactViewModel;
            this.artifactContainer = view.findViewById(R.id.rlArtifactContainer);
            this.imageView = (ImageView) view.findViewById(R.id.image_view);
            this.weightValue = (TextView) view.findViewById(R.id.weight_value);
            this.weightString = (TextView) view.findViewById(R.id.weight_string);
            this.stepsValue = (TextView) view.findViewById(R.id.steps_value);
            this.streakValue = (TextView) view.findViewById(R.id.streak_value);
        }

        /* access modifiers changed from: private */
        public void bind(Context context, BitmapDrawable bitmapDrawable) {
            this.imageView.setScaleType(ScaleType.CENTER_CROP);
            this.imageView.setImageDrawable(bitmapDrawable);
            this.weightValue.setText(this.model.getDescription());
            this.weightString.setText(context.getString(this.model.getSummaryWeightStringId()));
            this.stepsValue.setText(this.model.getSummarySteps());
            this.streakValue.setText(this.model.getSummaryStreak());
        }

        public ArtifactViewModel getModel() {
            return this.model;
        }

        public View getRoot() {
            return this.artifactContainer;
        }
    }

    public static class ViewHolder implements ShareViewHolder {
        public final View artifactContainer;
        public final View bottomBar;
        public final View captionContainer;
        public final EditText captionText;
        public final ImageView imageView;
        public final TextView metricText;
        public final ArtifactViewModel model;
        public final View root;
        public final View smallLogo;

        private ViewHolder(View view, ArtifactViewModel artifactViewModel) {
            this.root = view;
            this.model = artifactViewModel;
            this.artifactContainer = view.findViewById(R.id.rlArtifactContainer);
            this.imageView = (ImageView) view.findViewById(R.id.image_view);
            this.metricText = (TextView) view.findViewById(R.id.metric_text);
            this.captionContainer = view.findViewById(R.id.custom_caption_container);
            this.captionText = (EditText) view.findViewById(R.id.custom_caption_edit);
            this.smallLogo = view.findViewById(R.id.small_logo);
            this.bottomBar = view.findViewById(R.id.bottom_bar);
        }

        public ArtifactViewModel getModel() {
            return this.model;
        }

        public View getRoot() {
            return this.artifactContainer;
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ShareProgressAdapter(Bitmap bitmap, List<ArtifactViewModel> list, Bus bus, boolean z) {
        this.image = new BitmapDrawable(bitmap);
        this.models = list;
        this.messageBus = bus;
        this.isProgressPicsV2Enabled = z;
    }

    public void setCustomCaptionVisible(boolean z) {
        String str;
        this.showCustomCaption = z;
        if (!z) {
            str = "";
        } else {
            str = this.customCaption;
        }
        this.customCaption = str;
        updateCustomCaptionViews();
    }

    public boolean isCustomCaptionVisible() {
        return this.showCustomCaption;
    }

    public String getCustomCaption() {
        return this.customCaption;
    }

    public void setCustomCaption(String str) {
        this.customCaption = str;
        updateCustomCaptionViews();
    }

    public void setCursorVisible(boolean z) {
        for (Integer intValue : this.positionToView.keySet()) {
            ShareViewHolder shareViewHolder = (ShareViewHolder) ((View) this.positionToView.get(Integer.valueOf(intValue.intValue()))).getTag();
            if (shareViewHolder instanceof ViewHolder) {
                ((ViewHolder) shareViewHolder).captionText.setCursorVisible(z);
            }
        }
    }

    public ShareViewHolder getViewAtPosition(int i) {
        return (ShareViewHolder) ((View) this.positionToView.get(Integer.valueOf(i))).getTag();
    }

    public int getCount() {
        return this.models.size();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = (View) obj;
        if (view.getTag() instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.captionText.setOnClickListener(null);
            viewHolder.captionText.removeTextChangedListener(this.onTextChangeListener);
        }
        viewGroup.removeView(view);
        this.positionToView.remove(Integer.valueOf(i));
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view;
        String str;
        Context context = viewGroup.getContext();
        LayoutInflater from = LayoutInflater.from(context);
        ArtifactViewModel artifactViewModel = (ArtifactViewModel) this.models.get(i);
        if (artifactViewModel.getArtifactType() == ArtifactType.Summary) {
            view = from.inflate(R.layout.progress_share_summary_artifact, viewGroup, false);
            SummaryViewHolder summaryViewHolder = new SummaryViewHolder(view, artifactViewModel);
            view.setTag(summaryViewHolder);
            summaryViewHolder.bind(context, this.image);
        } else {
            view = from.inflate(this.isProgressPicsV2Enabled ? R.layout.progress_share_artifact_v2 : R.layout.progress_share_artifact, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view, artifactViewModel);
            view.setTag(viewHolder);
            viewHolder.imageView.setScaleType(ScaleType.CENTER_CROP);
            viewHolder.imageView.setImageDrawable(this.image);
            TextView textView = viewHolder.metricText;
            if (this.isProgressPicsV2Enabled) {
                str = artifactViewModel.getDescription().toUpperCase();
            } else {
                str = artifactViewModel.getDescription();
            }
            textView.setText(str);
            viewHolder.captionText.setOnClickListener(this.onEditClickListener);
            viewHolder.captionText.addTextChangedListener(this.onTextChangeListener);
            updateViewWithCaption(viewHolder);
            if (artifactViewModel.getArtifactType() == ArtifactType.Blank) {
                layoutBlankCard(viewHolder, context);
            }
        }
        viewGroup.addView(view);
        this.positionToView.put(Integer.valueOf(i), view);
        return view;
    }

    private void layoutBlankCard(ViewHolder viewHolder, Context context) {
        viewHolder.metricText.setVisibility(8);
        LayoutParams layoutParams = (LayoutParams) ViewUtils.getLayoutParams(viewHolder.smallLogo);
        layoutParams.addRule(11, 0);
        layoutParams.addRule(14);
        if (this.isProgressPicsV2Enabled) {
            LayoutParams layoutParams2 = (LayoutParams) ViewUtils.getLayoutParams(viewHolder.bottomBar);
            layoutParams2.height = context.getResources().getDimensionPixelSize(R.dimen.progress_pics_v2_blank_card_margin);
            viewHolder.bottomBar.setLayoutParams(layoutParams2);
        }
        viewHolder.smallLogo.setLayoutParams(layoutParams);
    }

    private void updateCustomCaptionViews() {
        for (Integer intValue : this.positionToView.keySet()) {
            ViewHolder viewHolder = (ViewHolder) ((View) this.positionToView.get(Integer.valueOf(intValue.intValue()))).getTag();
            viewHolder.captionText.removeTextChangedListener(this.onTextChangeListener);
            updateViewWithCaption(viewHolder);
            viewHolder.captionText.addTextChangedListener(this.onTextChangeListener);
        }
    }

    private void updateViewWithCaption(ViewHolder viewHolder) {
        ViewUtils.setVisible(this.showCustomCaption, viewHolder.captionContainer);
        if (this.showCustomCaption && !Strings.equals(viewHolder.captionText.getText().toString(), this.customCaption)) {
            viewHolder.captionText.setText(this.customCaption);
        }
    }
}
