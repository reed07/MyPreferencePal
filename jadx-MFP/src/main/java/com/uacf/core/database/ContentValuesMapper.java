package com.uacf.core.database;

import android.content.ContentValues;
import java.util.ArrayList;
import java.util.List;

public class ContentValuesMapper extends ColumnMapper<ContentValuesMapper> {
    private final ContentValues contentValues = new ContentValues();
    private final List<String> exclusions = new ArrayList();

    public interface Mappable {
        void toContentValues(ContentValuesMapper contentValuesMapper);
    }

    public ContentValuesMapper exclude(String str) {
        this.exclusions.add(str);
        return this;
    }

    public ContentValuesMapper exclude(String str, boolean z) {
        if (z) {
            this.exclusions.add(str);
        } else {
            this.exclusions.remove(str);
        }
        return this;
    }

    public ContentValuesMapper put(Mappable mappable) {
        mappable.toContentValues(this);
        return this;
    }

    public ContentValues get() {
        for (String remove : this.exclusions) {
            this.contentValues.remove(remove);
        }
        return this.contentValues;
    }

    public ContentValuesMapper clear() {
        this.contentValues.clear();
        return this;
    }

    public ContentValuesMapper put(String str, Long l) {
        this.contentValues.put(getActualColumnName(str), l);
        return this;
    }

    public ContentValuesMapper put(String str, Integer num) {
        this.contentValues.put(getActualColumnName(str), num);
        return this;
    }

    public ContentValuesMapper put(String str, String str2) {
        this.contentValues.put(getActualColumnName(str), str2);
        return this;
    }

    public ContentValuesMapper put(String str, Boolean bool) {
        this.contentValues.put(getActualColumnName(str), bool != null ? Integer.valueOf(bool.booleanValue() ? 1 : 0) : null);
        return this;
    }

    public ContentValuesMapper put(String str, Float f) {
        this.contentValues.put(getActualColumnName(str), f);
        return this;
    }

    public ContentValuesMapper put(String str, Double d) {
        this.contentValues.put(getActualColumnName(str), d);
        return this;
    }
}
