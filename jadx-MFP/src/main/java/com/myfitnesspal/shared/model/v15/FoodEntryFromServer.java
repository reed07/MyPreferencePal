package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.Strings;

public class FoodEntryFromServer extends FoodEntryBaseObject {
    public static final BinaryCreator<FoodEntryFromServer> BINARY_CREATOR = $$Lambda$FoodEntryFromServer$woCWSCVFqWNM5ACMLFk1pnMHAmI.INSTANCE;
    private long masterIdOfWeight;

    static /* synthetic */ FoodEntryFromServer lambda$static$0(BinaryDecoder binaryDecoder) {
        FoodEntryFromServer foodEntryFromServer = new FoodEntryFromServer();
        foodEntryFromServer.readData(binaryDecoder);
        return foodEntryFromServer;
    }

    public long getMasterIdOfWeight() {
        return this.masterIdOfWeight;
    }

    public void setMasterIdOfWeight(long j) {
        this.masterIdOfWeight = j;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setMasterId(binaryDecoder.decode8ByteInt());
        setUid(binaryDecoder.decodeString());
        setFood((FoodObject) binaryDecoder.decodeObject(FoodObjectFromServer.BINARY_CREATOR));
        setDate(binaryDecoder.decodeDate());
        setMealName(binaryDecoder.decodeString());
        setQuantity(binaryDecoder.decodeFloat());
        this.masterIdOfWeight = binaryDecoder.decode4ByteInt();
        setMealFoodMasterId(binaryDecoder.decode4ByteInt());
        setMealFoodVersion(binaryDecoder.decodeString());
        setMealFoodUid(binaryDecoder.decodeString());
        String decodeString = binaryDecoder.decodeString();
        if (!Strings.isEmpty(decodeString)) {
            setEntryTime(DateTimeUtils.parse("HH:mm:ss", decodeString));
        }
        String decodeString2 = binaryDecoder.decodeString();
        if (!Strings.isEmpty(decodeString2)) {
            setLoggedAt(DateTimeUtils.parse(DateTimeUtils.FORMAT_ISO8601, decodeString2));
        }
    }
}
