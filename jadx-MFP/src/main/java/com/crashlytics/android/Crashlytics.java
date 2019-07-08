package com.crashlytics.android;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.beta.Beta;
import com.crashlytics.android.core.CrashlyticsCore;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitGroup;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Crashlytics extends Kit<Void> implements KitGroup {
    public final Answers answers;
    public final Beta beta;
    public final CrashlyticsCore core;
    public final Collection<? extends Kit> kits;

    public static class Builder {
        private Answers answers;
        private Beta beta;
        private CrashlyticsCore core;
        private com.crashlytics.android.core.CrashlyticsCore.Builder coreBuilder;

        public Builder core(CrashlyticsCore crashlyticsCore) {
            if (crashlyticsCore == null) {
                throw new NullPointerException("CrashlyticsCore Kit must not be null.");
            } else if (this.core == null) {
                this.core = crashlyticsCore;
                return this;
            } else {
                throw new IllegalStateException("CrashlyticsCore Kit already set.");
            }
        }

        public Crashlytics build() {
            com.crashlytics.android.core.CrashlyticsCore.Builder builder = this.coreBuilder;
            if (builder != null) {
                if (this.core == null) {
                    this.core = builder.build();
                } else {
                    throw new IllegalStateException("Must not use Deprecated methods delay(), disabled(), listener(), pinningInfoProvider() with core()");
                }
            }
            if (this.answers == null) {
                this.answers = new Answers();
            }
            if (this.beta == null) {
                this.beta = new Beta();
            }
            if (this.core == null) {
                this.core = new CrashlyticsCore();
            }
            return new Crashlytics(this.answers, this.beta, this.core);
        }
    }

    /* access modifiers changed from: protected */
    public Void doInBackground() {
        return null;
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:crashlytics";
    }

    public String getVersion() {
        return "2.9.2.24";
    }

    public Crashlytics() {
        this(new Answers(), new Beta(), new CrashlyticsCore());
    }

    Crashlytics(Answers answers2, Beta beta2, CrashlyticsCore crashlyticsCore) {
        this.answers = answers2;
        this.beta = beta2;
        this.core = crashlyticsCore;
        this.kits = Collections.unmodifiableCollection(Arrays.asList(new Kit[]{answers2, beta2, crashlyticsCore}));
    }

    public Collection<? extends Kit> getKits() {
        return this.kits;
    }

    public static Crashlytics getInstance() {
        return (Crashlytics) Fabric.getKit(Crashlytics.class);
    }

    public static void logException(Throwable th) {
        checkInitialized();
        getInstance().core.logException(th);
    }

    public static void log(String str) {
        checkInitialized();
        getInstance().core.log(str);
    }

    public static void setUserIdentifier(String str) {
        checkInitialized();
        getInstance().core.setUserIdentifier(str);
    }

    public static void setUserName(String str) {
        checkInitialized();
        getInstance().core.setUserName(str);
    }

    public static void setString(String str, String str2) {
        checkInitialized();
        getInstance().core.setString(str, str2);
    }

    private static void checkInitialized() {
        if (getInstance() == null) {
            throw new IllegalStateException("Crashlytics must be initialized by calling Fabric.with(Context) prior to calling Crashlytics.getInstance()");
        }
    }
}
