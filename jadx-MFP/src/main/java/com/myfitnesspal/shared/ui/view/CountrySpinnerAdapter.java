package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.service.install.CountryService;

public class CountrySpinnerAdapter extends ArrayAdapter<Country> {
    private final CountryService countryService;

    public CountrySpinnerAdapter(Context context, CountryService countryService2, int i, int i2) {
        super(context, i, countryService2.getAllSupportedCountries());
        this.countryService = countryService2;
        setDropDownViewResource(i2);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return setupView(i, super.getView(i, view, viewGroup));
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return setupView(i, super.getDropDownView(i, view, viewGroup));
    }

    private View setupView(int i, View view) {
        ((TextView) view).setText(this.countryService.getLocalizedLongCountryName((Country) getItem(i)));
        return view;
    }
}
