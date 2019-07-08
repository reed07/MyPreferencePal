package com.myfitnesspal.feature.restaurantlogging.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.model.MfpLocation;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.shared.util.Measurements;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.List;

public class VenueAdapter extends ArrayAdapter<Venue> {
    private final LayoutInflater layoutInflater;
    private final Length preferredLengthUnit;

    private static class ViewHolder {
        /* access modifiers changed from: private */
        public final TextView venueInfoTextView;
        /* access modifiers changed from: private */
        public final TextView venueNameTextView;

        private ViewHolder(View view) {
            this.venueNameTextView = (TextView) ViewUtils.findById(view, R.id.text_primary);
            this.venueInfoTextView = (TextView) ViewUtils.findById(view, R.id.text_secondary);
        }
    }

    public VenueAdapter(Context context, Length length) {
        super(context, 0);
        this.preferredLengthUnit = length;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.layoutInflater.inflate(R.layout.double_text_item_container, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        bindVenue(viewHolder, (Venue) getItem(i));
        return view;
    }

    private void bindVenue(ViewHolder viewHolder, Venue venue) {
        String str;
        viewHolder.venueNameTextView.setText(venue.getName());
        if (venue.hasMenu()) {
            str = getVenueInfoText(venue);
        } else {
            str = getContext().getString(R.string.request_menu);
        }
        viewHolder.venueInfoTextView.setText(str);
    }

    private String getVenueInfoText(Venue venue) {
        MfpLocation location = venue.getLocation();
        if (location == null) {
            return null;
        }
        String makeVenueAddressString = makeVenueAddressString(location);
        String makeVenueDistanceString = makeVenueDistanceString(location);
        if (Strings.isEmpty(makeVenueAddressString) && Strings.isEmpty(makeVenueDistanceString)) {
            makeVenueAddressString = "";
        } else if (!Strings.notEmpty(makeVenueAddressString) || !Strings.isEmpty(makeVenueDistanceString)) {
            if (Strings.isEmpty(makeVenueAddressString)) {
                makeVenueAddressString = makeVenueDistanceString;
            } else {
                makeVenueAddressString = String.format("%1s - %2s", new Object[]{makeVenueDistanceString, makeVenueAddressString});
            }
        }
        return makeVenueAddressString;
    }

    private String makeVenueDistanceString(MfpLocation mfpLocation) {
        float f;
        int i;
        float distanceFromUser = mfpLocation.getDistanceFromUser();
        if (distanceFromUser == BitmapDescriptorFactory.HUE_RED) {
            return null;
        }
        if (this.preferredLengthUnit == Length.MILES) {
            i = R.string.mile_short;
            f = Measurements.convertMetersToMiles(distanceFromUser);
        } else {
            i = R.string.kilometer_short;
            f = Measurements.convertMetersToKilometers(distanceFromUser);
        }
        return getContext().getString(i, new Object[]{NumberUtils.localeStringFromFloatWithMaxFractionDigits(f, 2)});
    }

    private String makeVenueAddressString(MfpLocation mfpLocation) {
        StringBuilder sb = new StringBuilder();
        String addressLine1 = mfpLocation.getAddressLine1();
        String addressLine2 = mfpLocation.getAddressLine2();
        boolean notEmpty = Strings.notEmpty(addressLine2);
        if (Strings.notEmpty(addressLine1)) {
            sb.append(addressLine1);
            sb.append(notEmpty ? ", " : "");
        }
        if (notEmpty) {
            sb.append(addressLine2);
        }
        return sb.toString();
    }

    public void setItems(List<Venue> list) {
        setNotifyOnChange(false);
        clear();
        addAll(list);
        notifyDataSetChanged();
    }
}
