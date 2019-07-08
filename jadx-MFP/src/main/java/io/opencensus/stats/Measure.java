package io.opencensus.stats;

import io.opencensus.internal.StringUtils;
import io.opencensus.internal.Utils;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Measure {

    @Immutable
    public static abstract class MeasureDouble extends Measure {
        public abstract String getDescription();

        public abstract String getName();

        public abstract String getUnit();

        MeasureDouble() {
            super();
        }

        public static MeasureDouble create(String str, String str2, String str3) {
            Utils.checkArgument(StringUtils.isPrintableString(str) && str.length() <= 255, "Name should be a ASCII string with a length no greater than 255 characters.");
            return new AutoValue_Measure_MeasureDouble(str, str2, str3);
        }
    }

    @Immutable
    public static abstract class MeasureLong extends Measure {
        public abstract String getDescription();

        public abstract String getName();

        public abstract String getUnit();

        MeasureLong() {
            super();
        }

        public static MeasureLong create(String str, String str2, String str3) {
            Utils.checkArgument(StringUtils.isPrintableString(str) && str.length() <= 255, "Name should be a ASCII string with a length no greater than 255 characters.");
            return new AutoValue_Measure_MeasureLong(str, str2, str3);
        }
    }

    private Measure() {
    }
}
