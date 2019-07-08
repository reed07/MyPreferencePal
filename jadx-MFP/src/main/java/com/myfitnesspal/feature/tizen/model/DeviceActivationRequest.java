package com.myfitnesspal.feature.tizen.model;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;
import com.uacf.gear.bridge.Message;
import org.json.JSONObject;

public class DeviceActivationRequest {
    private static final String MFP_DOMAIN = "mfp";
    @Expose
    private String advertisedName;
    @Expose
    private String bluetoothDeviceAddress;
    @Expose
    private final String domain = "mfp";
    @Expose
    private String firmwareVersion;
    @Expose
    private String hardwareVersion;
    @Expose
    private String macAddress;
    @Expose
    private String manufacturer;
    @Expose
    private String model;
    @Expose
    private String serialNumber;
    @Expose
    private String userId;

    public interface TizenMessageKey {
        public static final String BluetoothMacAddress = "bluetoothMacAddress";
        public static final String FirmwareVersion = "firmwareVersion";
        public static final String HardwareVersion = "hardwareVersion";
        public static final String Manufacturer = "manufacturer";
        public static final String Model = "model";
        public static final String WifiMacAddress = "wifiMacAddress";
    }

    public static String createDeviceId(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append(Strings.toString(message.getBody().optString(TizenMessageKey.BluetoothMacAddress)));
        sb.append(Strings.toString(message.getBody().optString(TizenMessageKey.WifiMacAddress)));
        String sb2 = sb.toString();
        if (Strings.notEmpty(sb2)) {
            return String.valueOf(sb2.hashCode());
        }
        return null;
    }

    public DeviceActivationRequest(String str, Message message) {
        if (!Strings.isEmpty(str)) {
            JSONObject body = message.getBody();
            this.userId = str;
            this.manufacturer = body.optString("manufacturer");
            this.model = body.optString("model");
            this.macAddress = body.optString(TizenMessageKey.WifiMacAddress);
            this.bluetoothDeviceAddress = body.optString(TizenMessageKey.BluetoothMacAddress);
            if (Strings.isEmpty(this.model) || Strings.isEmpty(this.manufacturer)) {
                throw new IllegalArgumentException("message must contain a model and manufacturer");
            } else if (!Strings.isEmpty(this.macAddress) || !Strings.isEmpty(this.bluetoothDeviceAddress)) {
                this.hardwareVersion = body.optString(TizenMessageKey.HardwareVersion);
                this.firmwareVersion = body.optString(TizenMessageKey.FirmwareVersion);
            } else {
                throw new IllegalArgumentException("message does not have a bluetooth or wifi mac address. at least one is required");
            }
        } else {
            throw new IllegalArgumentException("uacfUserId cannot be empty");
        }
    }
}
