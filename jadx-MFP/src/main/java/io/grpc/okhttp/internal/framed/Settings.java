package io.grpc.okhttp.internal.framed;

public final class Settings {
    private int persistValue;
    private int persisted;
    private int set;
    private final int[] values = new int[10];

    public Settings set(int i, int i2, int i3) {
        if (i >= this.values.length) {
            return this;
        }
        int i4 = 1 << i;
        this.set |= i4;
        if ((i2 & 1) != 0) {
            this.persistValue |= i4;
        } else {
            this.persistValue &= ~i4;
        }
        if ((i2 & 2) != 0) {
            this.persisted |= i4;
        } else {
            this.persisted &= ~i4;
        }
        this.values[i] = i3;
        return this;
    }

    public boolean isSet(int i) {
        return ((1 << i) & this.set) != 0;
    }

    public int get(int i) {
        return this.values[i];
    }

    /* access modifiers changed from: 0000 */
    public int size() {
        return Integer.bitCount(this.set);
    }

    /* access modifiers changed from: 0000 */
    public int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxFrameSize(int i) {
        return (this.set & 32) != 0 ? this.values[5] : i;
    }
}
