package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;

public class BarcodeSearchRequestPacket extends ApiRequestPacketImpl {
    private String barcode;

    public static final class ResultCodes {
        public static final int ERROR_INVALID_BARCODE_CHECKSUM = 260;
        public static final int ERROR_MALFORMED_BARCODE = 259;
        public static final int ERROR_NO_MATCHES = 257;
        public static final int SUCCESS_EXACT_MATCH = 256;
        public static final int SUCCESS_MULTIPLE_MATCHES = 0;
    }

    public BarcodeSearchRequestPacket(String str) {
        super(109);
        this.barcode = str;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String str) {
        this.barcode = str;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.barcode);
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        int length = Strings.length(this.barcode);
        return length == 8 || length == 13;
    }
}
