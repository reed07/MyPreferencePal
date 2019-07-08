package io.opencensus.stats;

import io.opencensus.stats.View.AggregationWindow;
import io.opencensus.stats.View.Name;
import io.opencensus.tags.TagKey;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AutoValue_View extends View {
    private final Aggregation aggregation;
    private final List<TagKey> columns;
    private final String description;
    private final Measure measure;
    private final Name name;
    private final AggregationWindow window;

    AutoValue_View(Name name2, String str, Measure measure2, Aggregation aggregation2, List<TagKey> list, AggregationWindow aggregationWindow) {
        if (name2 != null) {
            this.name = name2;
            if (str != null) {
                this.description = str;
                if (measure2 != null) {
                    this.measure = measure2;
                    if (aggregation2 != null) {
                        this.aggregation = aggregation2;
                        if (list != null) {
                            this.columns = list;
                            if (aggregationWindow != null) {
                                this.window = aggregationWindow;
                                return;
                            }
                            throw new NullPointerException("Null window");
                        }
                        throw new NullPointerException("Null columns");
                    }
                    throw new NullPointerException("Null aggregation");
                }
                throw new NullPointerException("Null measure");
            }
            throw new NullPointerException("Null description");
        }
        throw new NullPointerException("Null name");
    }

    public Name getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Measure getMeasure() {
        return this.measure;
    }

    public Aggregation getAggregation() {
        return this.aggregation;
    }

    public List<TagKey> getColumns() {
        return this.columns;
    }

    @Deprecated
    public AggregationWindow getWindow() {
        return this.window;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("View{name=");
        sb.append(this.name);
        sb.append(", description=");
        sb.append(this.description);
        sb.append(", measure=");
        sb.append(this.measure);
        sb.append(", aggregation=");
        sb.append(this.aggregation);
        sb.append(", columns=");
        sb.append(this.columns);
        sb.append(", window=");
        sb.append(this.window);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof View)) {
            return false;
        }
        View view = (View) obj;
        if (!this.name.equals(view.getName()) || !this.description.equals(view.getDescription()) || !this.measure.equals(view.getMeasure()) || !this.aggregation.equals(view.getAggregation()) || !this.columns.equals(view.getColumns()) || !this.window.equals(view.getWindow())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode()) * 1000003) ^ this.measure.hashCode()) * 1000003) ^ this.aggregation.hashCode()) * 1000003) ^ this.columns.hashCode()) * 1000003) ^ this.window.hashCode();
    }
}
