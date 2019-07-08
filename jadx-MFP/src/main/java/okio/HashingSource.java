package okio;

import java.io.IOException;
import java.security.MessageDigest;
import javax.crypto.Mac;

public final class HashingSource extends ForwardingSource {
    private final Mac mac;
    private final MessageDigest messageDigest;

    public long read(Buffer buffer, long j) throws IOException {
        long read = super.read(buffer, j);
        if (read != -1) {
            long j2 = buffer.size - read;
            long j3 = buffer.size;
            Segment segment = buffer.head;
            while (j3 > j2) {
                segment = segment.prev;
                j3 -= (long) (segment.limit - segment.pos);
            }
            while (j3 < buffer.size) {
                int i = (int) ((((long) segment.pos) + j2) - j3);
                MessageDigest messageDigest2 = this.messageDigest;
                if (messageDigest2 != null) {
                    messageDigest2.update(segment.data, i, segment.limit - i);
                } else {
                    this.mac.update(segment.data, i, segment.limit - i);
                }
                j2 = ((long) (segment.limit - segment.pos)) + j3;
                segment = segment.next;
                j3 = j2;
            }
        }
        return read;
    }
}
