package com.myfitnesspal.feature.appgallery.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.model.OurOtherAppsItemViewModel;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.Collection;
import java.util.List;

public class OurAppsItemListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutId;
    private List<OurOtherAppsItemViewModel> list;
    /* access modifiers changed from: private */
    public OnCtaButtonClickedListener listener;

    private class AppsViewHolder {
        private Button cta;
        private MfpImageView image;
        private TextView name;
        private OnClickListener onCtaClickListener = new OnClickListener() {
            public void onClick(View view) {
                if (OurAppsItemListAdapter.this.listener != null) {
                    OurAppsItemListAdapter.this.listener.onClicked((OurOtherAppsItemViewModel) view.getTag());
                }
            }
        };

        AppsViewHolder(View view) {
            this.name = (TextView) ViewUtils.findById(view, R.id.title);
            this.image = (MfpImageView) ViewUtils.findById(view, R.id.image);
            this.cta = (Button) ViewUtils.findById(view, R.id.cta);
        }

        public void bind(OurOtherAppsItemViewModel ourOtherAppsItemViewModel) {
            this.name.setText(Strings.toString(ourOtherAppsItemViewModel.getName()));
            String iconUri = ourOtherAppsItemViewModel.getIconUri();
            this.image.setPlaceholderImageId(OurOtherAppsItemViewModel.getPlaceholderDrawable());
            this.image.setImageResource(OurOtherAppsItemViewModel.getPlaceholderColor());
            if (Strings.notEmpty(iconUri)) {
                this.image.setUrl(iconUri);
            }
            this.cta.setTag(ourOtherAppsItemViewModel);
            this.cta.setText(ourOtherAppsItemViewModel.getCtaText());
            this.cta.setOnClickListener(this.onCtaClickListener);
        }
    }

    public interface OnCtaButtonClickedListener {
        void onClicked(OurOtherAppsItemViewModel ourOtherAppsItemViewModel);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public OurAppsItemListAdapter(LayoutInflater layoutInflater, List<OurOtherAppsItemViewModel> list2, OnCtaButtonClickedListener onCtaButtonClickedListener, int i) {
        this.inflater = layoutInflater;
        this.list = list2;
        this.layoutId = i;
        this.listener = onCtaButtonClickedListener;
    }

    public int getCount() {
        return CollectionUtils.size((Collection<?>) this.list);
    }

    public OurOtherAppsItemViewModel getItem(int i) {
        return (OurOtherAppsItemViewModel) this.list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(this.layoutId, viewGroup, false);
            view.setTag(new AppsViewHolder(view));
        }
        ((AppsViewHolder) view.getTag()).bind((OurOtherAppsItemViewModel) this.list.get(i));
        return view;
    }
}
