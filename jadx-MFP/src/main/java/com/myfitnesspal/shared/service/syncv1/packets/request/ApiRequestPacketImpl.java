package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.SyncConstants;
import com.myfitnesspal.shared.service.syncv1.ApiDeviceTokenProvider;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.util.ApiUtil;
import com.uacf.core.util.Strings;
import javax.inject.Inject;
import javax.inject.Named;

public abstract class ApiRequestPacketImpl implements ApiRequestPacket {
    @Inject
    ApiDeviceTokenProvider apiDeviceTokenProvider;
    private int apiVersion = ApiUtil.getBinaryApiVersion(this.appSettings);
    @Inject
    AppSettings appSettings;
    private int packetStartPosition;
    private final int packetType;
    @Inject
    @Named("deviceUUIDBytes")
    byte[] uuidBytes;
    @Inject
    @Named("appVersionCode")
    long versionCode;

    /* access modifiers changed from: protected */
    public void dump(StringBuilder sb) {
    }

    /* access modifiers changed from: protected */
    public abstract boolean validatePacketData();

    /* access modifiers changed from: protected */
    public abstract void writeDataInternal(BinaryEncoder binaryEncoder);

    protected ApiRequestPacketImpl(int i) {
        this.packetType = i;
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        if (_validatePacketData()) {
            this.packetStartPosition = binaryEncoder.currentPosition();
            _writeDataInternal(binaryEncoder);
            finishPacket(binaryEncoder);
            return;
        }
        throw new IllegalStateException("Packet data is invalid");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("API REQUEST PACKET: ");
        sb.append(this.packetType);
        sb.append(10);
        sb.append(Strings.tabify("packetStartPosition", Integer.valueOf(this.packetStartPosition), 20));
        dump(sb);
        return sb.toString();
    }

    private void finishPacket(BinaryEncoder binaryEncoder) {
        int currentPosition = binaryEncoder.currentPosition();
        int i = this.packetStartPosition;
        binaryEncoder.patchAtPosition(i + 2, currentPosition - i);
    }

    private void _writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(SyncConstants.MAGIC_NUMBER);
        binaryEncoder.write4ByteInt(0);
        binaryEncoder.write2ByteInt(1);
        binaryEncoder.write2ByteInt(this.packetType);
        writeDataInternal(binaryEncoder);
    }

    /* access modifiers changed from: protected */
    public void writeVersionInformation(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.apiVersion);
        binaryEncoder.write4ByteInt(this.versionCode);
    }

    public void writeCurrentUUID(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeUUID(this.uuidBytes);
    }

    /* access modifiers changed from: protected */
    public void writeCurrentDeviceToken(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.apiDeviceTokenProvider.get());
    }

    private boolean _validatePacketData() {
        return validatePacketData();
    }
}
