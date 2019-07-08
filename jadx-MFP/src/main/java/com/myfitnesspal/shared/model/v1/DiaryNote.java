package com.myfitnesspal.shared.model.v1;

import com.myfitnesspal.android.R;
import java.util.Date;

public class DiaryNote extends DatabaseObject {
    public static final int kDiaryNoteTypeExercise = 1;
    public static final int kDiaryNoteTypeFood = 0;
    private String body;
    private Date entryDate;
    private int noteType;

    public int itemType() {
        return 10;
    }

    public int getNoteType() {
        return this.noteType;
    }

    public void setNoteType(int i) {
        this.noteType = i;
    }

    public Date getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(Date date) {
        this.entryDate = date;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public int typeDescription() {
        switch (this.noteType) {
            case 0:
                return R.string.food;
            case 1:
                return R.string.exercise;
            default:
                return R.string.unknown;
        }
    }

    public final int getNoteTitle() {
        switch (this.noteType) {
            case 0:
                return R.string.food_notes;
            case 1:
                return R.string.exercise_notes;
            default:
                return R.string.notes;
        }
    }

    public String bodyTruncatedTo(int i) {
        if (this.body.length() <= i) {
            return this.body;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.body.substring(0, i - 3));
        sb.append("...");
        return sb.toString();
    }
}
