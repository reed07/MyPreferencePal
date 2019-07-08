package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;

public class FoodOrExercise extends DatabaseObject implements Parcelable, Comparable<FoodOrExercise> {
    public static final Creator<FoodOrExercise> CREATOR = new Creator<FoodOrExercise>() {
        public FoodOrExercise createFromParcel(Parcel parcel) {
            return new FoodOrExercise(parcel);
        }

        public FoodOrExercise[] newArray(int i) {
            return new FoodOrExercise[i];
        }
    };
    @Expose
    public String description;
    @Expose(serialize = false)
    public boolean isDeleted;
    @Expose(serialize = false)
    public boolean isPublic;
    @Expose
    public Long originalId;
    @Expose
    public Long originalMasterId;
    @Expose
    protected String originalUid;
    @Expose
    public long ownerUserId;
    @Expose
    public long ownerUserMasterId;
    @Expose
    public int sortPriority;

    public int describeContents() {
        return 0;
    }

    public boolean isExercise() {
        return false;
    }

    public boolean isFood() {
        return false;
    }

    public void lookupSortOrderByUsageCount(long j, DbConnectionManager dbConnectionManager) {
    }

    public FoodOrExercise() {
        setLocalId(0);
        this.originalId = Long.valueOf(0);
        this.originalMasterId = Long.valueOf(0);
        this.ownerUserId = 0;
        this.ownerUserMasterId = 0;
        this.sortPriority = 0;
        this.isDeleted = false;
        this.isPublic = false;
    }

    protected FoodOrExercise(Parcel parcel) {
        super(parcel);
        this.originalId = Long.valueOf(parcel.readLong());
        this.originalMasterId = Long.valueOf(parcel.readLong());
        this.originalUid = parcel.readString();
        this.ownerUserId = parcel.readLong();
        this.ownerUserMasterId = parcel.readLong();
        this.sortPriority = parcel.readInt();
        this.isDeleted = ParcelableUtil.readBoolean(parcel);
        this.isPublic = ParcelableUtil.readBoolean(parcel);
        this.description = parcel.readString();
    }

    public long getOriginalId() {
        return this.originalId.longValue();
    }

    public void setOriginalId(long j) {
        this.originalId = Long.valueOf(j);
    }

    public long getOriginalMasterId() {
        return this.originalMasterId.longValue();
    }

    public void setOriginalMasterId(long j) {
        this.originalMasterId = Long.valueOf(j);
    }

    public String getOriginalUid() {
        return this.originalUid;
    }

    public void setOriginalUid(String str) {
        this.originalUid = str;
    }

    public long getOwnerUserId() {
        return this.ownerUserId;
    }

    public void setOwnerUserId(long j) {
        this.ownerUserId = j;
    }

    public long getOwnerUserMasterId() {
        return this.ownerUserMasterId;
    }

    public void setOwnerUserMasterId(long j) {
        this.ownerUserMasterId = j;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(boolean z) {
        this.isDeleted = z;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setIsPublic(boolean z) {
        this.isPublic = z;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public boolean shadows(FoodOrExercise foodOrExercise) {
        long localId = foodOrExercise.getLocalId();
        long masterDatabaseId = foodOrExercise.getMasterDatabaseId();
        long originalId2 = foodOrExercise.getOriginalId();
        long originalMasterId2 = foodOrExercise.getOriginalMasterId();
        if (getLocalId() > 0 && localId > 0 && getLocalId() == localId) {
            return true;
        }
        if (getMasterDatabaseId() > 0 && masterDatabaseId > 0 && getMasterDatabaseId() == masterDatabaseId) {
            return true;
        }
        if (getOriginalId() > 0) {
            int i = (originalId2 > 0 ? 1 : (originalId2 == 0 ? 0 : -1));
            if (i > 0 && getOriginalId() == originalId2) {
                return true;
            }
            if (i == 0 && localId > 0 && getOriginalId() == localId) {
                return true;
            }
        }
        if (getOriginalMasterId() > 0) {
            int i2 = (masterDatabaseId > 0 ? 1 : (masterDatabaseId == 0 ? 0 : -1));
            if (i2 > 0 && getOriginalMasterId() == originalMasterId2) {
                return true;
            }
            if (i2 == 0 && getOriginalMasterId() == masterDatabaseId) {
                return true;
            }
        }
        return false;
    }

    public int getSortPriority() {
        return this.sortPriority;
    }

    public void setSortPriority(int i) {
        this.sortPriority = i;
    }

    public int compareTo(FoodOrExercise foodOrExercise) {
        if (!foodOrExercise.isFood()) {
            return 0;
        }
        Food food = (Food) this;
        Food food2 = (Food) foodOrExercise;
        int sortPriority2 = food2.getSortPriority();
        if (food.getSortPriority() > sortPriority2) {
            return -1;
        }
        if (food.getSortPriority() < sortPriority2) {
            return 1;
        }
        int sortOrder = food2.getSortOrder();
        if (food.getSortOrder() > sortOrder) {
            return -1;
        }
        if (food.getSortOrder() < sortOrder) {
            return 1;
        }
        return 0;
    }

    public boolean isPrivateItemOfAnotherUser(long j) {
        return !this.isPublic && getOwnerUserMasterId() > 0 && getOwnerUserMasterId() != j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.originalId.longValue());
        parcel.writeLong(this.originalMasterId.longValue());
        parcel.writeString(this.originalUid);
        parcel.writeLong(this.ownerUserId);
        parcel.writeLong(this.ownerUserMasterId);
        parcel.writeInt(this.sortPriority);
        ParcelableUtil.writeBoolean(parcel, this.isDeleted);
        ParcelableUtil.writeBoolean(parcel, this.isPublic);
        parcel.writeString(this.description);
    }

    public boolean hasOriginalUid() {
        return Strings.notEmpty(this.originalUid);
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Long l = this.originalId;
        int i = 0;
        int hashCode2 = (hashCode + (l != null ? l.hashCode() : 0)) * 31;
        Long l2 = this.originalMasterId;
        if (l2 != null) {
            i = l2.hashCode();
        }
        int hashCode3 = (((hashCode2 + i) * 31) + Strings.toString(this.originalUid).hashCode()) * 31;
        long j = this.ownerUserId;
        int i2 = (hashCode3 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.ownerUserMasterId;
        return ((((((((i2 + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.sortPriority) * 31) + (this.isDeleted ? 1 : 0)) * 31) + (this.isPublic ? 1 : 0)) * 31) + Strings.toString(this.description).hashCode();
    }

    public boolean equals(Object obj) {
        return equalsWithIds(obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0043, code lost:
        if (com.uacf.core.util.Strings.equals(r4.originalUid, r1.originalUid) != false) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean equalsWithIds(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.myfitnesspal.shared.model.v1.FoodOrExercise
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            r1 = r5
            com.myfitnesspal.shared.model.v1.FoodOrExercise r1 = (com.myfitnesspal.shared.model.v1.FoodOrExercise) r1
            boolean r3 = super.equals(r5)
            if (r3 == 0) goto L_0x0046
            boolean r5 = r4.equalsIgnoreId(r5)
            if (r5 == 0) goto L_0x0046
            java.lang.Long r5 = r4.originalId
            if (r5 != 0) goto L_0x0022
            java.lang.Long r5 = r1.originalId
            if (r5 != 0) goto L_0x0046
            goto L_0x002a
        L_0x0022:
            java.lang.Long r3 = r1.originalId
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x0046
        L_0x002a:
            java.lang.Long r5 = r4.originalMasterId
            if (r5 != 0) goto L_0x0033
            java.lang.Long r5 = r1.originalMasterId
            if (r5 != 0) goto L_0x0046
            goto L_0x003b
        L_0x0033:
            java.lang.Long r3 = r1.originalMasterId
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x0046
        L_0x003b:
            java.lang.String r5 = r4.originalUid
            java.lang.String r1 = r1.originalUid
            boolean r5 = com.uacf.core.util.Strings.equals(r5, r1)
            if (r5 == 0) goto L_0x0046
            goto L_0x0047
        L_0x0046:
            r0 = 0
        L_0x0047:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v1.FoodOrExercise.equalsWithIds(java.lang.Object):boolean");
    }

    public boolean equalsIgnoreId(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FoodOrExercise)) {
            return false;
        }
        FoodOrExercise foodOrExercise = (FoodOrExercise) obj;
        if (!(this.ownerUserId == foodOrExercise.ownerUserId && this.ownerUserMasterId == foodOrExercise.ownerUserMasterId && this.sortPriority == foodOrExercise.sortPriority && this.isDeleted == foodOrExercise.isDeleted && this.isPublic == foodOrExercise.isPublic && Strings.equals(this.description, foodOrExercise.description))) {
            z = false;
        }
        return z;
    }
}
