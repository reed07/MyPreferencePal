package com.myfitnesspal.shared.model.v1;

import android.graphics.drawable.Drawable;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.DateTimeUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MealEntries extends DatabaseObject implements DiaryEntryCellModel {
    private ArrayList<DiaryEntryCellModel> entries;
    private Set entriesSet;
    private Date entryDate;
    private int mealId;
    private String mealName;

    public Drawable image() {
        return null;
    }

    public boolean isExercise() {
        return false;
    }

    public boolean isExerciseEntry() {
        return false;
    }

    public boolean isFood() {
        return false;
    }

    public boolean isFoodEntry() {
        return false;
    }

    public boolean isMealEntries() {
        return true;
    }

    public int itemType() {
        return 0;
    }

    public MealEntries(Date date, int i, ArrayList<DiaryEntryCellModel> arrayList, Lazy<Session> lazy) {
        this.entryDate = date;
        this.mealId = i;
        this.mealName = ((Session) lazy.get()).getUser().getMealNames().nameForId((long) i);
        this.entries = arrayList;
        this.entriesSet = new HashSet(arrayList);
    }

    public int totalCalories() {
        Iterator it = this.entries.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += Math.round(((FoodEntry) it.next()).getCaloriesValue());
        }
        return i;
    }

    public String summaryLine1() {
        String concat = this.mealName.substring(0, 1).toUpperCase().concat(this.mealName.substring(1));
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.add(6, -1);
        Calendar calendar2 = (Calendar) Calendar.getInstance().clone();
        calendar2.setTime(this.entryDate);
        if (calendar2.get(1) == calendar.get(1) && calendar2.get(6) == calendar.get(6)) {
            return "Yesterday's ".concat(concat);
        }
        return concat.concat(" from ").concat(DateTimeUtils.format("MMM d, yyyy", calendar2.getTime()));
    }

    public boolean isEqual(Object obj) {
        if (!(obj instanceof MealEntries)) {
            return false;
        }
        MealEntries mealEntries = (MealEntries) obj;
        if (this.mealId == mealEntries.mealId && this.entryDate.compareTo(mealEntries.entryDate) == 0) {
            return compareEntriesSet(mealEntries.getEntriesSet());
        }
        return false;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<com.myfitnesspal.shared.model.v1.FoodEntry>, for r2v0, types: [java.util.Set<com.myfitnesspal.shared.model.v1.FoodEntry>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean compareEntriesSet(java.util.Set<com.myfitnesspal.shared.model.v1.FoodEntry> r2) {
        /*
            r1 = this;
            java.util.Iterator r2 = r2.iterator()
        L_0x0004:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0018
            java.lang.Object r0 = r2.next()
            com.myfitnesspal.shared.model.v1.FoodEntry r0 = (com.myfitnesspal.shared.model.v1.FoodEntry) r0
            boolean r0 = r1.entriesSetContain(r0)
            if (r0 != 0) goto L_0x0004
            r2 = 0
            return r2
        L_0x0018:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v1.MealEntries.compareEntriesSet(java.util.Set):boolean");
    }

    private boolean entriesSetContain(FoodEntry foodEntry) {
        for (FoodEntry foodEntry2 : this.entriesSet) {
            if (foodEntry2.masterDatabaseId == foodEntry.masterDatabaseId && foodEntry2.localId == foodEntry.localId) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        return isEqual(obj);
    }

    public int getMealId() {
        return this.mealId;
    }

    public void setMealId(int i) {
        this.mealId = i;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String str) {
        this.mealName = str;
    }

    public ArrayList<DiaryEntryCellModel> getEntries() {
        return this.entries;
    }

    public Set getEntriesSet() {
        return this.entriesSet;
    }

    public Date getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(Date date) {
        this.entryDate = date;
    }
}
