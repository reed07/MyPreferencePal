package com.myfitnesspal.shared.model.mapper;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;
import com.myfitnesspal.shared.service.syncv1.packets.PacketFactory;
import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacketList;
import com.uacf.core.util.Ln;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Inject;

public class ApiBinaryMapperBase implements BinaryMapper<ApiBinaryMapperBase> {
    private final PacketFactory packetFactory;

    public <TOutput> ApiBinaryMapperBase withType(Class<? extends TOutput> cls) {
        return null;
    }

    @Inject
    public ApiBinaryMapperBase(PacketFactory packetFactory2) {
        this.packetFactory = packetFactory2;
    }

    public <TOutput> byte[] reverseMap(TOutput toutput) {
        throw new UnsupportedOperationException();
    }

    public <TOutput> TOutput mapFrom(byte[] bArr) throws IOException {
        BinaryDecoder binaryDecoder = new BinaryDecoder();
        binaryDecoder.appendDataBuffer(bArr);
        return new ApiResponsePacketList(this.packetFactory).readData(binaryDecoder);
    }

    public <TOutput> TOutput tryMapFrom(byte[] bArr) {
        try {
            return mapFrom(bArr);
        } catch (MfpPacketException e) {
            Ln.e(e, "PACKET EXCEPTION: packet type = %s", Integer.valueOf(e.getType()));
            return new ArrayList();
        } catch (Exception e2) {
            Ln.e(e2);
            return new ArrayList();
        }
    }

    public <TOutput> TOutput mapFrom(byte[] bArr, Class<? extends TOutput> cls) throws IOException {
        return mapFrom(bArr);
    }

    public <TOutput> TOutput tryMapFrom(byte[] bArr, Class<? extends TOutput> cls) {
        return tryMapFrom(bArr);
    }
}
