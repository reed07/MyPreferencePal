package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.Date;

public class DiaryNoteObject extends BaseObject {
    public static final BinaryCreator<DiaryNoteObject> BINARY_CREATOR = new BinaryCreator<DiaryNoteObject>() {
        public DiaryNoteObject create(BinaryDecoder binaryDecoder) {
            DiaryNoteObject diaryNoteObject = new DiaryNoteObject();
            diaryNoteObject.readData(binaryDecoder);
            return diaryNoteObject;
        }
    };
    private Date entryDate;
    private boolean isFromClient;
    private String noteBody;
    private int noteType;

    public static final class NoteType {
        public static final int EXERCISE = 1;
        public static final int FOOD = 0;
    }

    public boolean isFromClient() {
        return this.isFromClient;
    }

    public void setFromClient(boolean z) {
        this.isFromClient = z;
    }

    public Date getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(Date date) {
        this.entryDate = date;
    }

    public int getNoteType() {
        return this.noteType;
    }

    public void setNoteType(int i) {
        this.noteType = i;
    }

    public String getNoteBody() {
        return this.noteBody;
    }

    public void setNoteBody(String str) {
        this.noteBody = str;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(this.isFromClient ? getLocalId() : getMasterId());
        binaryEncoder.writeString(getUid());
        binaryEncoder.writeDate(this.entryDate);
        binaryEncoder.write2ByteInt(this.noteType);
        binaryEncoder.writeString(this.noteBody);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        long decode8ByteInt = binaryDecoder.decode8ByteInt();
        if (this.isFromClient) {
            setLocalId(decode8ByteInt);
        } else {
            setMasterId(decode8ByteInt);
        }
        setUid(binaryDecoder.decodeString());
        this.entryDate = binaryDecoder.decodeDate();
        this.noteType = binaryDecoder.decode2ByteInt();
        this.noteBody = binaryDecoder.decodeString();
    }
}
