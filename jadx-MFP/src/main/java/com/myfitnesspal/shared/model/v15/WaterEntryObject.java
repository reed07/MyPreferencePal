package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.Date;

public class WaterEntryObject extends BaseObject {
    public static final BinaryCreator<WaterEntryObject> BINARY_CREATOR = new BinaryCreator<WaterEntryObject>() {
        public WaterEntryObject create(BinaryDecoder binaryDecoder) {
            WaterEntryObject waterEntryObject = new WaterEntryObject();
            waterEntryObject.readData(binaryDecoder);
            return waterEntryObject;
        }
    };
    float cups;
    Date entryDate;
    boolean isFromClient;
    float millileters;

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

    public float getCups() {
        return this.cups;
    }

    public void setCups(float f) {
        this.cups = f;
    }

    public float getMilliliters() {
        return this.millileters;
    }

    public void setMillileters(float f) {
        this.millileters = f;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(this.isFromClient ? getLocalId() : getMasterId());
        binaryEncoder.writeDate(this.entryDate);
        binaryEncoder.writeFloat(this.cups);
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
        this.cups = binaryDecoder.decodeFloat();
    }
}
