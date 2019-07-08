package com.shinobicontrols.charts;

import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;

public class DateFrequency {
    int a = 1;
    Denomination b = Denomination.MINUTES;

    public enum Denomination {
        SECONDS(13, 1000),
        MINUTES(12, DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS),
        HOURS(10, 3600000),
        DAYS(7, 86400000),
        WEEKS(3, 604800000),
        MONTHS(2, 0),
        YEARS(1, 0);
        
        final int a;
        final long b;

        private Denomination(int i, long j) {
            this.a = i;
            this.b = j;
        }
    }

    public DateFrequency() {
    }

    public DateFrequency(int i, Denomination denomination) {
        this.a = i;
        this.b = denomination;
    }

    public final int getQuantity() {
        return this.a;
    }

    public final Denomination getDenomination() {
        return this.b;
    }

    public void setQuantity(int i) {
        this.a = i;
    }

    public void setDenomination(Denomination denomination) {
        this.b = denomination;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i, Denomination denomination) {
        this.a = i;
        this.b = denomination;
    }

    /* access modifiers changed from: 0000 */
    public boolean b(int i, Denomination denomination) {
        return this.a == i && this.b == denomination;
    }

    /* access modifiers changed from: 0000 */
    public long a() {
        switch (this.b) {
            case SECONDS:
                return ((long) this.a) * 1000;
            case MINUTES:
                return ((long) this.a) * DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS;
            case HOURS:
                return ((long) this.a) * 3600000;
            case DAYS:
                return ((long) this.a) * 86400000;
            case WEEKS:
                return ((long) (this.a * 7)) * 86400000;
            case MONTHS:
                return ((long) this.a) * 86400000 * 28;
            case YEARS:
                return ((long) this.a) * 86400000 * 365;
            default:
                return 1;
        }
    }

    public DateFrequency clone() {
        return new DateFrequency(this.a, this.b);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DateFrequency)) {
            return false;
        }
        DateFrequency dateFrequency = (DateFrequency) obj;
        if (this.a == dateFrequency.a) {
            Denomination denomination = this.b;
            if (denomination != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int i = (527 + this.a) * 31;
        Denomination denomination = this.b;
        return i + (denomination == null ? 0 : denomination.hashCode());
    }
}
