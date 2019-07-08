package com.myfitnesspal.feature.settings.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import java.util.ArrayList;

public class CropOptionAdapter extends ArrayAdapter<CropOption> {
    private LayoutInflater mInflater;
    private ArrayList<CropOption> mOptions;

    public CropOptionAdapter(Context context, ArrayList<CropOption> arrayList) {
        super(context, R.layout.crop_selector, arrayList);
        this.mOptions = arrayList;
        this.mInflater = LayoutInflater.from(context);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(R.layout.crop_dialog_item, null);
        }
        CropOption cropOption = (CropOption) this.mOptions.get(i);
        if (cropOption == null) {
            return null;
        }
        ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(cropOption.icon);
        ((TextView) view.findViewById(R.id.text)).setText(cropOption.title);
        return view;
    }
}
