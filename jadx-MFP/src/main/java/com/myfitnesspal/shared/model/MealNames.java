package com.myfitnesspal.shared.model;

import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MealNames {
    public static final int EMPTY_MEAL_ID = -1;
    public static final String UNKNOWN_MEAL_NAME = "unknown_meal_name";
    private List<String> names;

    public MealNames(MealNames mealNames) {
        this.names = mealNames != null ? mealNames.getNames() : null;
    }

    public MealNames(List<String> list) {
        this.names = list;
    }

    public MealNames(String[] strArr) {
        this(strArr != null ? Arrays.asList(strArr) : null);
    }

    public List<String> getNames() {
        return this.names;
    }

    public String first() {
        if (notEmpty()) {
            return (String) this.names.get(0);
        }
        return null;
    }

    public int mealIdForName(String str) {
        if (!Strings.equalsIgnoreCase(str, "[All]") && CollectionUtils.notEmpty((Collection<?>) this.names)) {
            for (int i = 0; i < CollectionUtils.size((Collection<?>) this.names); i++) {
                if (Strings.equalsIgnoreCase(this.names.get(i), str)) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    public int mealIdForNameForReminders(String str) {
        int mealIdForName = mealIdForName(str);
        if (mealIdForName != 0 || Strings.equalsIgnoreCase(str, "[All]")) {
            return mealIdForName;
        }
        return -1;
    }

    public int mealIndexForName(String str) {
        if (CollectionUtils.notEmpty((Collection<?>) this.names)) {
            for (int i = 0; i < CollectionUtils.size((Collection<?>) this.names); i++) {
                if (Strings.equalsIgnoreCase(this.names.get(i), str)) {
                    return i;
                }
            }
        }
        return 0;
    }

    public String nameForId(long j) {
        return nameForId(j, this.names);
    }

    private String nameForId(long j, List<String> list) {
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            return (j < 1 || j > ((long) CollectionUtils.size((Collection<?>) list))) ? UNKNOWN_MEAL_NAME : (String) list.get(((int) j) - 1);
        }
        return null;
    }

    public String nameForIndex(int i) {
        if (CollectionUtils.notEmpty((Collection<?>) this.names)) {
            return (i < 0 || i >= CollectionUtils.size((Collection<?>) this.names)) ? UNKNOWN_MEAL_NAME : (String) this.names.get(i);
        }
        return null;
    }

    public int size() {
        return CollectionUtils.size((Collection<?>) this.names);
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty((Collection<?>) this.names);
    }

    public boolean notEmpty() {
        return CollectionUtils.notEmpty((Collection<?>) this.names);
    }

    public static List<String> getMealNameListWithOutEmptyNames(List<String> list) {
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (Strings.notEmpty(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
