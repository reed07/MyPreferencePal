package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.recipes.model.IngredientMatchingAdapterItem;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MfpIngredientItem implements Parcelable, IngredientMatchingAdapterItem {
    public static final Creator<MfpIngredientItem> CREATOR = new Creator<MfpIngredientItem>() {
        public MfpIngredientItem createFromParcel(Parcel parcel) {
            return new MfpIngredientItem(parcel);
        }

        public MfpIngredientItem[] newArray(int i) {
            return new MfpIngredientItem[i];
        }
    };
    @Expose
    private MfpIngredient ingredient;
    @Expose
    private List<MfpIngredient> matches;
    @Expose
    private MfpNormalizedData normalizedData;
    private boolean wasManuallyAdded;

    public int describeContents() {
        return 0;
    }

    public MfpIngredientItem() {
        this.matches = new ArrayList();
    }

    public MfpIngredientItem(MfpIngredient mfpIngredient) {
        this(mfpIngredient, new MfpNormalizedData(), null);
    }

    public MfpIngredientItem(MfpIngredient mfpIngredient, MfpNormalizedData mfpNormalizedData, List<MfpIngredient> list) {
        this.matches = new ArrayList();
        this.ingredient = mfpIngredient;
        this.normalizedData = mfpNormalizedData;
        this.matches = list;
    }

    protected MfpIngredientItem(Parcel parcel) {
        this.matches = new ArrayList();
        this.ingredient = (MfpIngredient) parcel.readValue(MfpIngredient.class.getClassLoader());
        this.normalizedData = (MfpNormalizedData) parcel.readValue(MfpNormalizedData.class.getClassLoader());
        boolean z = true;
        if (parcel.readByte() == 1) {
            this.matches = new ArrayList();
            parcel.readList(this.matches, MfpIngredient.class.getClassLoader());
        } else {
            this.matches = null;
        }
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.wasManuallyAdded = z;
    }

    public MfpIngredient getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(MfpIngredient mfpIngredient) {
        this.ingredient = mfpIngredient;
    }

    public MfpNormalizedData getNormalizedData() {
        return this.normalizedData;
    }

    public void setNormalizedData(MfpNormalizedData mfpNormalizedData) {
        this.normalizedData = mfpNormalizedData;
    }

    public List<MfpIngredient> getMatches() {
        return this.matches;
    }

    public boolean hasMatches() {
        return CollectionUtils.size((Collection<?>) this.matches) > 0;
    }

    public MfpIngredient getPrimaryMatch() {
        return hasMatches() ? (MfpIngredient) this.matches.get(0) : this.ingredient;
    }

    public void setMatches(List<MfpIngredient> list) {
        this.matches = list;
    }

    public boolean wasManuallyAdded() {
        return this.wasManuallyAdded;
    }

    public void setWasManuallyAdded(boolean z) {
        this.wasManuallyAdded = z;
    }

    public MfpIngredient getIngredientForRecipeCreation(double d) {
        MfpIngredient primaryMatch = getPrimaryMatch();
        if (getIngredient() != null) {
            primaryMatch.setMatchType(getIngredient().getMatchType());
        }
        if (primaryMatch.getServingSize() == null) {
            MfpServingSize servingSize = ((MfpWeightOption) primaryMatch.getWeightOptions().get(0)).getServingSize();
            primaryMatch.setServings(Double.valueOf(servingSize.getValue().doubleValue() * d));
            primaryMatch.setServingSize(servingSize);
        }
        if (primaryMatch.getNormalizedData() == null) {
            primaryMatch.setNormalizedData(getNormalizedData());
        }
        if (primaryMatch.getRawText() == null) {
            primaryMatch.setRawText(getIngredient().getRawText());
        }
        return primaryMatch;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MfpIngredientItem mfpIngredientItem = (MfpIngredientItem) obj;
        MfpIngredient mfpIngredient = this.ingredient;
        if (mfpIngredient == null ? mfpIngredientItem.ingredient != null : !mfpIngredient.equals(mfpIngredientItem.ingredient)) {
            return false;
        }
        MfpNormalizedData mfpNormalizedData = this.normalizedData;
        if (mfpNormalizedData == null ? mfpIngredientItem.normalizedData != null : !mfpNormalizedData.equals(mfpIngredientItem.normalizedData)) {
            return false;
        }
        List<MfpIngredient> list = this.matches;
        if (list != null) {
            z = list.equals(mfpIngredientItem.matches);
        } else if (mfpIngredientItem.matches != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        MfpIngredient mfpIngredient = this.ingredient;
        int i = 0;
        int hashCode = (mfpIngredient != null ? mfpIngredient.hashCode() : 0) * 31;
        MfpNormalizedData mfpNormalizedData = this.normalizedData;
        int hashCode2 = (hashCode + (mfpNormalizedData != null ? mfpNormalizedData.hashCode() : 0)) * 31;
        List<MfpIngredient> list = this.matches;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.ingredient);
        parcel.writeValue(this.normalizedData);
        if (this.matches == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.matches);
        }
        parcel.writeByte(this.wasManuallyAdded ? (byte) 1 : 0);
    }

    public String toString() {
        return getNormalizedData() != null ? Strings.toString(getNormalizedData().getIngredient()) : "";
    }
}
