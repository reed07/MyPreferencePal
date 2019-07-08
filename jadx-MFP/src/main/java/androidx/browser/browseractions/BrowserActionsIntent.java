package androidx.browser.browseractions;

import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BrowserActionsIntent {

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserActionsItemId {
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserActionsUrlType {
    }

    public static final class Builder {
    }
}
