package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.nio.ByteBuffer;

public final class zzgg {
    private static final int[] zzaay = {1, 2, 3, 6};
    private static final int[] zzaaz = {48000, 44100, 32000};
    private static final int[] zzaba = {24000, 22050, 16000};
    private static final int[] zzabb = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] zzabc = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, ModuleDescriptor.MODULE_VERSION, 384, 448, 512, 576, AmazonAdTask.DEFAULT_AD_WIDTH};
    private static final int[] zzabd = {69, 87, 104, PacketTypes.RetrieveFriendList, PacketTypes.CheckFriendsByEmailResponse, RequestCodes.JOIN_CHALLENGE, RequestCodes.PREMIUM_UPSELL, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static zzfs zza(zzpx zzpx, String str, String str2, zzhp zzhp) {
        int i = zzaaz[(zzpx.readUnsignedByte() & 192) >> 6];
        int readUnsignedByte = zzpx.readUnsignedByte();
        int i2 = zzabb[(readUnsignedByte & 56) >> 3];
        return zzfs.zza(str, MimeTypes.AUDIO_AC3, null, -1, -1, (readUnsignedByte & 4) != 0 ? i2 + 1 : i2, i, null, zzhp, 0, str2);
    }

    public static int zzcn() {
        return 1536;
    }

    public static zzfs zzb(zzpx zzpx, String str, String str2, zzhp zzhp) {
        zzpx zzpx2 = zzpx;
        zzpx.zzbl(2);
        int i = zzaaz[(zzpx.readUnsignedByte() & 192) >> 6];
        int readUnsignedByte = zzpx.readUnsignedByte();
        int i2 = zzabb[(readUnsignedByte & 14) >> 1];
        return zzfs.zza(str, MimeTypes.AUDIO_E_AC3, null, -1, -1, (readUnsignedByte & 1) != 0 ? i2 + 1 : i2, i, null, zzhp, 0, str2);
    }

    public static int zzh(ByteBuffer byteBuffer) {
        int i = 6;
        if (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3) {
            i = zzaay[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4];
        }
        return i * 256;
    }
}
