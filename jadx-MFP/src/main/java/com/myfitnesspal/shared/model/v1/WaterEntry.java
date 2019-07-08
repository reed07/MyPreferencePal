package com.myfitnesspal.shared.model.v1;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import java.util.Date;

public class WaterEntry extends DatabaseObject {
    private float cups;
    private Date entryDate;
    private float milliliters;

    public int itemType() {
        return 7;
    }

    public WaterEntry() {
        this.cups = BitmapDescriptorFactory.HUE_RED;
        this.milliliters = BitmapDescriptorFactory.HUE_RED;
        this.localId = 0;
        this.masterDatabaseId = 0;
        this.entryDate = null;
    }

    public WaterEntry(LocalizedFluid localizedFluid) {
        this.cups = (float) localizedFluid.getValue(Water.CUPS);
        this.milliliters = (float) localizedFluid.getValue(Water.MILLILITERS);
        this.localId = 0;
        this.masterDatabaseId = 0;
        this.entryDate = null;
    }

    public void setEntryDate(Date date) {
        this.entryDate = date;
    }

    public void setCups(float f) {
        this.cups = f;
    }

    public Date getEntryDate() {
        return this.entryDate;
    }

    public float getCups() {
        return this.cups;
    }

    public float getMilliliters() {
        return this.milliliters;
    }

    public void setMilliliters(float f) {
        this.milliliters = f;
    }

    public void setMilliliters(double d) {
        this.milliliters = (float) d;
    }
}
