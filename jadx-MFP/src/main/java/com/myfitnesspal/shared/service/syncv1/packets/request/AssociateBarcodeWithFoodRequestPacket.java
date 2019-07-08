package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;

public class AssociateBarcodeWithFoodRequestPacket extends ApiRequestPacketImpl {
    private String barcode;
    private long foodMasterId;
    private final String foodOriginalUid;
    private final String foodUid;

    public static final class ResultCodes {
        public static final int ERROR_BARCODE_ALREADY_ASSOCIATED = 261;
        public static final int ERROR_INVALID_BARCODE_CHECKSUM = 260;
        public static final int ERROR_MALFORMED_BARCODE = 259;
        public static final int ERROR_NO_MATCHES = 257;
        public static final int ERROR_OTHER = 262;
        public static final int SUCCESS = 0;
    }

    public AssociateBarcodeWithFoodRequestPacket(long j, String str, String str2, String str3) {
        super(110);
        this.foodMasterId = j;
        this.foodUid = str;
        this.foodOriginalUid = str2;
        this.barcode = str3;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String str) {
        this.barcode = str;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return (this.foodMasterId > 0 || (Strings.notEmpty(this.foodUid) && Strings.notEmpty(this.foodOriginalUid))) && Strings.notEmpty(this.barcode);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(this.foodMasterId);
        binaryEncoder.writeString(this.foodUid);
        binaryEncoder.writeString(this.foodOriginalUid);
        binaryEncoder.writeString(this.barcode);
    }
}
