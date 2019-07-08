package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Tracestate {

    public static final class Builder {
        /* access modifiers changed from: private */
        public static final Tracestate EMPTY = Tracestate.create(Collections.emptyList());
        @Nullable
        private ArrayList<Entry> entries;
        private final Tracestate parent;

        private Builder(Tracestate tracestate) {
            Utils.checkNotNull(tracestate, "parent");
            this.parent = tracestate;
            this.entries = null;
        }

        public Tracestate build() {
            ArrayList<Entry> arrayList = this.entries;
            if (arrayList == null) {
                return this.parent;
            }
            return Tracestate.create(arrayList);
        }
    }

    @Immutable
    public static abstract class Entry {
        Entry() {
        }
    }

    public abstract List<Entry> getEntries();

    public static Builder builder() {
        return new Builder();
    }

    /* access modifiers changed from: private */
    public static Tracestate create(List<Entry> list) {
        Utils.checkState(list.size() <= 32, "Invalid size");
        return new AutoValue_Tracestate(Collections.unmodifiableList(list));
    }

    Tracestate() {
    }
}
