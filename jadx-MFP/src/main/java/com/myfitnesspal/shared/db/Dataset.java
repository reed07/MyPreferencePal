package com.myfitnesspal.shared.db;

import android.support.annotation.NonNull;
import java.util.ArrayList;

public class Dataset implements Comparable<Dataset> {
    public static final int kDatasetTypePurchased = 2;
    public static final int kDatasetTypeStock = 1;
    ArrayList<Integer> countryIds;
    int datasetId;
    String description;
    String identifier;
    int priority;
    int type;

    public int appropriateSortOrderCountryId() {
        return ((Integer) this.countryIds.get(0)).intValue();
    }

    public void setDatasetId(int i) {
        this.datasetId = i;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setCountryIds(ArrayList<Integer> arrayList) {
        this.countryIds = arrayList;
    }

    public int getPriority() {
        return this.priority;
    }

    public int compareTo(@NonNull Dataset dataset) {
        return Integer.compare(this.priority, dataset.priority);
    }
}
