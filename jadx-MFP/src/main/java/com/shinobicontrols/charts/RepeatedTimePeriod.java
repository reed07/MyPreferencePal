package com.shinobicontrols.charts;

import java.util.Date;

public class RepeatedTimePeriod {
    final Date a;
    final DateFrequency b;
    final DateFrequency c;

    public RepeatedTimePeriod(Date date, DateFrequency dateFrequency, DateFrequency dateFrequency2) {
        if (date == null) {
            throw new IllegalArgumentException("TimePeriod cannot have null start date.");
        } else if (dateFrequency == null) {
            throw new IllegalArgumentException("TimePeriod cannot have null lenth.");
        } else if (dateFrequency2 != null) {
            this.a = date;
            this.b = dateFrequency;
            this.c = dateFrequency2;
        } else {
            throw new IllegalArgumentException("RepeatedTimePeriod cannot have null frequency.");
        }
    }

    public Date getStart() {
        return this.a;
    }

    public DateFrequency getLength() {
        return this.b;
    }

    public DateFrequency getFrequency() {
        return this.c;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RepeatedTimePeriod)) {
            return false;
        }
        RepeatedTimePeriod repeatedTimePeriod = (RepeatedTimePeriod) obj;
        if (!this.a.equals(repeatedTimePeriod.a) || !this.b.equals(repeatedTimePeriod.b) || !this.c.equals(repeatedTimePeriod.c)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((527 + this.a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
