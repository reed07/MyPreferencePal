package bolts;

import java.util.Locale;

public class CancellationToken {
    private final CancellationTokenSource tokenSource;

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(this.tokenSource.isCancellationRequested())});
    }
}
