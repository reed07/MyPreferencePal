package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class EventMessageDecoder implements MetadataDecoder {
    private static final String TAG = "EventMessageDecoder";

    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        byte[] array = byteBuffer.array();
        int limit = byteBuffer.limit();
        ParsableByteArray parsableByteArray = new ParsableByteArray(array, limit);
        String str = (String) Assertions.checkNotNull(parsableByteArray.readNullTerminatedString());
        String str2 = (String) Assertions.checkNotNull(parsableByteArray.readNullTerminatedString());
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        if (readUnsignedInt2 != 0) {
            String str3 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring non-zero presentation_time_delta: ");
            sb.append(readUnsignedInt2);
            Log.w(str3, sb.toString());
        }
        EventMessage eventMessage = new EventMessage(str, str2, Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000, readUnsignedInt), parsableByteArray.readUnsignedInt(), Arrays.copyOfRange(array, parsableByteArray.getPosition(), limit));
        return new Metadata(eventMessage);
    }
}
