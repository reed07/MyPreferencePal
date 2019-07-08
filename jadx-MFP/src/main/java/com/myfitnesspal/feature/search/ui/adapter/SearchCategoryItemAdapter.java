package com.myfitnesspal.feature.search.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.Strings;
import java.util.List;
import javax.inject.Inject;

public class SearchCategoryItemAdapter extends ArrayAdapter<SearchCategory> {
    @Inject
    LocalizedStringsUtil localizedStringsUtil;
    int resource;
    @Inject
    UserEnergyService userEnergyService;

    public SearchCategoryItemAdapter(Context context, int i, List<SearchCategory> list) {
        super(context, i, list);
        MyFitnessPalApp.getInstance().component().inject(this);
        this.resource = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        SearchCategory searchCategory = (SearchCategory) getItem(i);
        if (view == null) {
            linearLayout = new LinearLayout(getContext());
            ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(this.resource, linearLayout, true);
        } else {
            linearLayout = (LinearLayout) view;
        }
        ((CheckedTextView) linearLayout.findViewById(R.id.checkText)).setText(Strings.equalsIgnoreCase(searchCategory.getType(), "exercise") ? this.localizedStringsUtil.getExerciseTypeNameString(searchCategory.getTitle()) : this.localizedStringsUtil.getMealNameString(searchCategory.getTitle(), this.userEnergyService));
        return linearLayout;
    }
}
