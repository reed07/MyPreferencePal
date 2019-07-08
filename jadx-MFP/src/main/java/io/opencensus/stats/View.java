package io.opencensus.stats;

import io.opencensus.common.Duration;
import io.opencensus.internal.StringUtils;
import io.opencensus.internal.Utils;
import io.opencensus.tags.TagKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class View {
    private static final Comparator<TagKey> TAG_KEY_COMPARATOR = new Comparator<TagKey>() {
        public int compare(TagKey tagKey, TagKey tagKey2) {
            return tagKey.getName().compareTo(tagKey2.getName());
        }
    };

    @Immutable
    @Deprecated
    public static abstract class AggregationWindow {

        @Immutable
        @Deprecated
        public static abstract class Cumulative extends AggregationWindow {
            private static final Cumulative CUMULATIVE = new AutoValue_View_AggregationWindow_Cumulative();

            Cumulative() {
                super();
            }

            public static Cumulative create() {
                return CUMULATIVE;
            }
        }

        @Immutable
        @Deprecated
        public static abstract class Interval extends AggregationWindow {
            private static final Duration ZERO = Duration.create(0, 0);

            public abstract Duration getDuration();

            Interval() {
                super();
            }

            public static Interval create(Duration duration) {
                Utils.checkArgument(duration.compareTo(ZERO) > 0, "Duration must be positive");
                return new AutoValue_View_AggregationWindow_Interval(duration);
            }
        }

        private AggregationWindow() {
        }
    }

    @Immutable
    public static abstract class Name {
        public abstract String asString();

        Name() {
        }

        public static Name create(String str) {
            Utils.checkArgument(StringUtils.isPrintableString(str) && str.length() <= 255, "Name should be a ASCII string with a length no greater than 255 characters.");
            return new AutoValue_View_Name(str);
        }
    }

    public abstract Aggregation getAggregation();

    public abstract List<TagKey> getColumns();

    public abstract String getDescription();

    public abstract Measure getMeasure();

    public abstract Name getName();

    @Deprecated
    public abstract AggregationWindow getWindow();

    View() {
    }

    @Deprecated
    public static View create(Name name, String str, Measure measure, Aggregation aggregation, List<TagKey> list, AggregationWindow aggregationWindow) {
        Utils.checkArgument(new HashSet(list).size() == list.size(), "Columns have duplicate.");
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, TAG_KEY_COMPARATOR);
        AutoValue_View autoValue_View = new AutoValue_View(name, str, measure, aggregation, Collections.unmodifiableList(arrayList), aggregationWindow);
        return autoValue_View;
    }

    public static View create(Name name, String str, Measure measure, Aggregation aggregation, List<TagKey> list) {
        Utils.checkArgument(new HashSet(list).size() == list.size(), "Columns have duplicate.");
        return create(name, str, measure, aggregation, list, Cumulative.create());
    }
}
