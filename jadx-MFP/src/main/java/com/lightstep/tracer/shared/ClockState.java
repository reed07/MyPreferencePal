package com.lightstep.tracer.shared;

import java.util.Iterator;
import java.util.LinkedList;

class ClockState {
    private int currentOffsetAge = 8;
    private long currentOffsetMicros = 0;
    private final Object mutex = new Object();
    private LinkedList<Sample> samples = new LinkedList<>();

    static class NoopClockState extends ClockState {
        /* access modifiers changed from: 0000 */
        public int activeSampleCount() {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void addSample(long j, long j2, long j3, long j4) {
        }

        /* access modifiers changed from: 0000 */
        public boolean isReady() {
            return true;
        }

        /* access modifiers changed from: 0000 */
        public long offsetMicros() {
            return 0;
        }

        NoopClockState() {
        }
    }

    private class Sample {
        long delayMicros;
        long offsetMicros;

        Sample(long j, long j2) {
            this.delayMicros = j;
            this.offsetMicros = j2;
        }
    }

    ClockState() {
        update();
    }

    /* access modifiers changed from: 0000 */
    public void addSample(long j, long j2, long j3, long j4) {
        long j5;
        long j6;
        if (j <= 0 || j2 <= 0 || j3 <= 0 || j4 <= 0) {
            j6 = Long.MAX_VALUE;
            j5 = 0;
        } else {
            j5 = ((j2 - j) + (j3 - j4)) / 2;
            j6 = (j4 - j) - (j3 - j2);
        }
        synchronized (this.mutex) {
            if (this.samples.size() == 8) {
                this.samples.removeFirst();
            }
            LinkedList<Sample> linkedList = this.samples;
            Sample sample = new Sample(j6, j5);
            linkedList.push(sample);
            this.currentOffsetAge++;
            update();
        }
    }

    private void update() {
        Iterator it = this.samples.iterator();
        long j = 0;
        long j2 = Long.MAX_VALUE;
        long j3 = 0;
        while (it.hasNext()) {
            Sample sample = (Sample) it.next();
            if (sample.delayMicros < j2) {
                long j4 = sample.delayMicros;
                j3 = sample.offsetMicros;
                j2 = j4;
            }
        }
        if (j3 != this.currentOffsetMicros) {
            Iterator it2 = this.samples.iterator();
            while (it2.hasNext()) {
                j = (long) (((double) j) + Math.pow((double) (j3 - ((Sample) it2.next()).offsetMicros), 2.0d));
            }
            long sqrt = (long) Math.sqrt((double) (j / ((long) this.samples.size())));
            if (this.currentOffsetAge > 7 || Math.abs(this.currentOffsetMicros - j3) < sqrt * 3) {
                this.currentOffsetMicros = j3;
                this.currentOffsetAge = 0;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public long offsetMicros() {
        long j;
        synchronized (this.mutex) {
            j = this.currentOffsetMicros;
        }
        return j;
    }

    /* access modifiers changed from: 0000 */
    public boolean isReady() {
        boolean z;
        synchronized (this.mutex) {
            z = this.samples.size() > 3;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public int activeSampleCount() {
        int size;
        synchronized (this.mutex) {
            size = this.samples.size();
        }
        return size;
    }
}
