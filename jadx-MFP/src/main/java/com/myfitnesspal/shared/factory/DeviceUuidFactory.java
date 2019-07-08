package com.myfitnesspal.shared.factory;

import android.content.Context;
import android.content.SharedPreferences;
import com.uacf.core.util.EncryptionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.UUID;
import javax.inject.Inject;

public class DeviceUuidFactory {
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static final String PREFS_ENCRYPTED_ID = "encrypted_id";
    protected static final String PREFS_FILE = "device_id.xml";
    protected static volatile String encryptedUuid;
    protected static volatile UUID uuid;

    @Inject
    public DeviceUuidFactory(Context context) {
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE, 0);
                if (uuid == null) {
                    String string = sharedPreferences.getString("device_id", null);
                    if (Strings.notEmpty(string)) {
                        try {
                            uuid = UUID.fromString(string);
                        } catch (IllegalArgumentException unused) {
                            logUuidAndAbort("failed to parse previously serialized UUID!", string);
                        }
                    }
                }
                if (uuid == null) {
                    uuid = UUID.randomUUID();
                    sharedPreferences.edit().putString("device_id", uuid.toString()).remove(PREFS_ENCRYPTED_ID).apply();
                }
                String string2 = sharedPreferences.getString(PREFS_ENCRYPTED_ID, null);
                if (Strings.notEmpty(string2)) {
                    encryptedUuid = string2;
                } else {
                    encryptedUuid = encryptUuid(context, uuid);
                    if (Strings.notEmpty(encryptedUuid)) {
                        sharedPreferences.edit().putString(PREFS_ENCRYPTED_ID, encryptedUuid).apply();
                    } else {
                        logUuidAndAbort("failed to encrypt UUID!", uuid.toString());
                    }
                }
                encryptedUuid = Strings.trimmed((Object) encryptedUuid);
            }
        }
    }

    public UUID getDeviceUuid() {
        return uuid;
    }

    public String getEncryptedUuid() {
        return encryptedUuid;
    }

    private static void logUuidAndAbort(String str, String str2) {
        String format = String.format("%s <%s>", new Object[]{str, str2});
        Ln.e(format, new Object[0]);
        throw new RuntimeException(format);
    }

    private static String encryptUuid(Context context, UUID uuid2) {
        return EncryptionUtils.encryptAndBase64Encode(context, createDeviceIdHash(uuid2).getBytes());
    }

    private static String createDeviceIdHash(UUID uuid2) {
        return EncryptionUtils.shaHex(uuid2.toString().getBytes());
    }
}
