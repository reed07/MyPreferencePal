package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.model.v15.DiaryNoteObject;
import com.uacf.core.util.Ln;
import java.io.IOException;

public class DiaryNoteMapperImpl implements DiaryNoteMapper {
    public DiaryNote reverseMap(DiaryNoteObject diaryNoteObject) {
        DiaryNote diaryNote = new DiaryNote();
        diaryNote.setLocalId(diaryNoteObject.getLocalId());
        diaryNote.setMasterDatabaseId(diaryNoteObject.getMasterId());
        diaryNote.setBody(diaryNoteObject.getNoteBody());
        diaryNote.setEntryDate(diaryNoteObject.getEntryDate());
        diaryNote.setNoteType(diaryNoteObject.getNoteType());
        return diaryNote;
    }

    public DiaryNoteObject mapFrom(DiaryNote diaryNote) throws IOException {
        DiaryNoteObject diaryNoteObject = new DiaryNoteObject();
        diaryNoteObject.setLocalId(diaryNote.getLocalId());
        diaryNoteObject.setMasterId(diaryNote.getMasterDatabaseId());
        diaryNoteObject.setNoteBody(diaryNote.getBody());
        diaryNoteObject.setEntryDate(diaryNote.getEntryDate());
        diaryNoteObject.setNoteType(diaryNote.getNoteType());
        return diaryNoteObject;
    }

    public DiaryNoteObject tryMapFrom(DiaryNote diaryNote) {
        try {
            return mapFrom(diaryNote);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }
}
