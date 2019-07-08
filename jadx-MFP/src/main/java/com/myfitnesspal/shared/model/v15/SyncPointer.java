package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class SyncPointer implements BinaryApiSerializable {
    public static final BinaryCreator<SyncPointer> BINARY_CREATOR = new BinaryCreator<SyncPointer>() {
        public SyncPointer create(BinaryDecoder binaryDecoder) {
            SyncPointer syncPointer = new SyncPointer();
            syncPointer.readData(binaryDecoder);
            return syncPointer;
        }
    };
    long cutoffId;
    String key;
    String lastSyncTime;

    public SyncPointer() {
    }

    public SyncPointer(String str, String str2, long j) {
        this.key = str;
        this.lastSyncTime = str2;
        this.cutoffId = j;
    }

    public String getKey() {
        return this.key;
    }

    public String getLastSyncTime() {
        return this.lastSyncTime;
    }

    public long getCutoffId() {
        return this.cutoffId;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.key);
        binaryEncoder.writeString(this.lastSyncTime);
        binaryEncoder.write8ByteInt(this.cutoffId);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.key = binaryDecoder.decodeString();
        this.lastSyncTime = binaryDecoder.decodeString();
        this.cutoffId = binaryDecoder.decode8ByteInt();
    }

    public String toString() {
        return String.format("%s %s", new Object[]{this.key, this.lastSyncTime});
    }
}
