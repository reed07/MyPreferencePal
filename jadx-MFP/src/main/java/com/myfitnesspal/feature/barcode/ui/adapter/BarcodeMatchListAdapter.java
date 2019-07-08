package com.myfitnesspal.feature.barcode.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.barcode.constants.BarcodeMatchDisplayMode;
import com.myfitnesspal.feature.barcode.ui.viewmodel.BarcodeMatchViewModel;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.uacf.core.util.CollectionUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;

public class BarcodeMatchListAdapter extends BaseAdapter {
    private final Activity context;
    private List<MfpFood> foods;
    private LayoutInflater inflater;
    private final Lazy<UserEnergyService> userEnergyService;
    private final BarcodeMatchViewModel viewModel;

    private static class ViewHolder {
        View checkbox;
        TextView subtitle;
        TextView title;

        ViewHolder(View view) {
            this.checkbox = view.findViewById(R.id.multiSelectCheckBox);
            this.title = (TextView) view.findViewById(R.id.text_primary);
            this.subtitle = (TextView) view.findViewById(R.id.text_secondary);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public BarcodeMatchListAdapter(Activity activity, Lazy<UserEnergyService> lazy, BarcodeMatchViewModel barcodeMatchViewModel, BarcodeMatchDisplayMode barcodeMatchDisplayMode) {
        this.context = activity;
        this.userEnergyService = lazy;
        this.viewModel = barcodeMatchViewModel;
        this.inflater = LayoutInflater.from(activity);
        setDisplayMode(barcodeMatchDisplayMode);
    }

    public void setDisplayMode(BarcodeMatchDisplayMode barcodeMatchDisplayMode) {
        List<MfpFood> matches = barcodeMatchDisplayMode == BarcodeMatchDisplayMode.MultiMatch ? this.viewModel.getMatches() : this.viewModel.getSearchResults();
        if (matches != this.foods) {
            this.foods = matches;
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return CollectionUtils.size((Collection<?>) this.foods);
    }

    public Object getItem(int i) {
        return this.foods.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(R.layout.generic_list_item_with_checkbox, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        MfpFood mfpFood = (MfpFood) this.foods.get(i);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.checkbox.setVisibility(8);
        viewHolder.title.setText(mfpFood.getDescription());
        if (mfpFood.getVerified()) {
            viewHolder.title.setCompoundDrawablesWithIntrinsicBounds(null, null, this.context.getResources().getDrawable(R.drawable.ic_verified_foods_small), null);
        }
        viewHolder.subtitle.setText(((UserEnergyService) this.userEnergyService.get()).getDescription(mfpFood));
        return view;
    }
}
