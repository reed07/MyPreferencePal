package okio;

import java.io.IOException;
import java.security.MessageDigest;
import javax.annotation.Nullable;
import javax.crypto.Mac;

public final class HashingSink extends ForwardingSink {
    @Nullable
    private final Mac mac;
    @Nullable
    private final MessageDigest messageDigest;

    public void write(Buffer buffer, long j) throws IOException {
        Util.checkOffsetAndCount(buffer.size, 0, j);
        Segment segment = buffer.head;
        long j2 = 0;
        while (j2 < j) {
            int min = (int) Math.min(j - j2, (long) (segment.limit - segment.pos));
            MessageDigest messageDigest2 = this.messageDigest;
            if (messageDigest2 != null) {
                messageDigest2.update(segment.data, segment.pos, min);
            } else {
                this.mac.update(segment.data, segment.pos, min);
            }
            j2 += (long) min;
            segment = segment.next;
        }
        super.write(buffer, j);
    }
}
