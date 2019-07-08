package okhttp3;

import java.nio.charset.Charset;
import javax.annotation.Nullable;

public final class Challenge {
    private final Charset charset;
    private final String realm;
    private final String scheme;

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Challenge) {
            Challenge challenge = (Challenge) obj;
            if (challenge.scheme.equals(this.scheme) && challenge.realm.equals(this.realm) && challenge.charset.equals(this.charset)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((899 + this.realm.hashCode()) * 31) + this.scheme.hashCode()) * 31) + this.charset.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.scheme);
        sb.append(" realm=\"");
        sb.append(this.realm);
        sb.append("\" charset=\"");
        sb.append(this.charset);
        sb.append("\"");
        return sb.toString();
    }
}
