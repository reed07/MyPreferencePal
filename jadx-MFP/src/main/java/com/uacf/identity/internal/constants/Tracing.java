package com.uacf.identity.internal.constants;

import com.uacf.core.tracing.FSTracing;
import com.uacf.core.tracing.FSTracingSetting;

public final class Tracing extends FSTracing {
    public static FSTracingSetting TRACING_SETTING = FSTracingSetting.RELEASE_BUILDS;

    public interface Log extends FSLog {

        public interface Events extends com.uacf.core.tracing.FSTracing.FSLog.Events {
        }

        public interface Keys extends com.uacf.core.tracing.FSTracing.FSLog.Keys {
        }
    }

    public interface Tags extends FSTags {
    }
}
