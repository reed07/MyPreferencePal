package com.google.ads.interactivemedia.v3.internal;

import java.util.Comparator;

/* compiled from: IMASDK */
public final class ahg {
    private int a = 0;

    public final ahg a(Object obj, Object obj2) {
        return a(obj, obj2, null);
    }

    private final ahg a(Object obj, Object obj2, Comparator<?> comparator) {
        if (this.a != 0 || obj == obj2) {
            return this;
        }
        int i = -1;
        if (obj == null) {
            this.a = -1;
            return this;
        } else if (obj2 == null) {
            this.a = 1;
            return this;
        } else {
            if (obj.getClass().isArray()) {
                int i2 = 0;
                if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    long[] jArr2 = (long[]) obj2;
                    if (this.a == 0 && jArr != jArr2) {
                        if (jArr != null) {
                            if (jArr2 != null) {
                                if (jArr.length == jArr2.length) {
                                    while (i2 < jArr.length) {
                                        int i3 = this.a;
                                        if (i3 != 0) {
                                            break;
                                        }
                                        long j = jArr[i2];
                                        long j2 = jArr2[i2];
                                        if (i3 == 0) {
                                            this.a = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                                        }
                                        i2++;
                                    }
                                } else {
                                    if (jArr.length >= jArr2.length) {
                                        i = 1;
                                    }
                                    this.a = i;
                                }
                            } else {
                                this.a = 1;
                            }
                        } else {
                            this.a = -1;
                        }
                    }
                } else if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    int[] iArr2 = (int[]) obj2;
                    if (this.a == 0 && iArr != iArr2) {
                        if (iArr != null) {
                            if (iArr2 != null) {
                                if (iArr.length == iArr2.length) {
                                    while (i2 < iArr.length) {
                                        int i4 = this.a;
                                        if (i4 != 0) {
                                            break;
                                        }
                                        int i5 = iArr[i2];
                                        int i6 = iArr2[i2];
                                        if (i4 == 0) {
                                            this.a = Integer.compare(i5, i6);
                                        }
                                        i2++;
                                    }
                                } else {
                                    if (iArr.length >= iArr2.length) {
                                        i = 1;
                                    }
                                    this.a = i;
                                }
                            } else {
                                this.a = 1;
                            }
                        } else {
                            this.a = -1;
                        }
                    }
                } else if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    short[] sArr2 = (short[]) obj2;
                    if (this.a == 0 && sArr != sArr2) {
                        if (sArr != null) {
                            if (sArr2 != null) {
                                if (sArr.length == sArr2.length) {
                                    while (i2 < sArr.length) {
                                        int i7 = this.a;
                                        if (i7 != 0) {
                                            break;
                                        }
                                        short s = sArr[i2];
                                        short s2 = sArr2[i2];
                                        if (i7 == 0) {
                                            this.a = Short.compare(s, s2);
                                        }
                                        i2++;
                                    }
                                } else {
                                    if (sArr.length >= sArr2.length) {
                                        i = 1;
                                    }
                                    this.a = i;
                                }
                            } else {
                                this.a = 1;
                            }
                        } else {
                            this.a = -1;
                        }
                    }
                } else if (obj instanceof char[]) {
                    char[] cArr = (char[]) obj;
                    char[] cArr2 = (char[]) obj2;
                    if (this.a == 0 && cArr != cArr2) {
                        if (cArr != null) {
                            if (cArr2 != null) {
                                if (cArr.length == cArr2.length) {
                                    while (i2 < cArr.length) {
                                        int i8 = this.a;
                                        if (i8 != 0) {
                                            break;
                                        }
                                        char c = cArr[i2];
                                        char c2 = cArr2[i2];
                                        if (i8 == 0) {
                                            this.a = Character.compare(c, c2);
                                        }
                                        i2++;
                                    }
                                } else {
                                    if (cArr.length >= cArr2.length) {
                                        i = 1;
                                    }
                                    this.a = i;
                                }
                            } else {
                                this.a = 1;
                            }
                        } else {
                            this.a = -1;
                        }
                    }
                } else if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    byte[] bArr2 = (byte[]) obj2;
                    if (this.a == 0 && bArr != bArr2) {
                        if (bArr != null) {
                            if (bArr2 != null) {
                                if (bArr.length == bArr2.length) {
                                    while (i2 < bArr.length) {
                                        int i9 = this.a;
                                        if (i9 != 0) {
                                            break;
                                        }
                                        byte b = bArr[i2];
                                        byte b2 = bArr2[i2];
                                        if (i9 == 0) {
                                            this.a = Byte.compare(b, b2);
                                        }
                                        i2++;
                                    }
                                } else {
                                    if (bArr.length >= bArr2.length) {
                                        i = 1;
                                    }
                                    this.a = i;
                                }
                            } else {
                                this.a = 1;
                            }
                        } else {
                            this.a = -1;
                        }
                    }
                } else if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    double[] dArr2 = (double[]) obj2;
                    if (this.a == 0 && dArr != dArr2) {
                        if (dArr != null) {
                            if (dArr2 != null) {
                                if (dArr.length == dArr2.length) {
                                    while (i2 < dArr.length) {
                                        int i10 = this.a;
                                        if (i10 != 0) {
                                            break;
                                        }
                                        double d = dArr[i2];
                                        double d2 = dArr2[i2];
                                        if (i10 == 0) {
                                            this.a = Double.compare(d, d2);
                                        }
                                        i2++;
                                    }
                                } else {
                                    if (dArr.length >= dArr2.length) {
                                        i = 1;
                                    }
                                    this.a = i;
                                }
                            } else {
                                this.a = 1;
                            }
                        } else {
                            this.a = -1;
                        }
                    }
                } else if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    float[] fArr2 = (float[]) obj2;
                    if (this.a == 0 && fArr != fArr2) {
                        if (fArr != null) {
                            if (fArr2 != null) {
                                if (fArr.length == fArr2.length) {
                                    while (i2 < fArr.length) {
                                        int i11 = this.a;
                                        if (i11 != 0) {
                                            break;
                                        }
                                        float f = fArr[i2];
                                        float f2 = fArr2[i2];
                                        if (i11 == 0) {
                                            this.a = Float.compare(f, f2);
                                        }
                                        i2++;
                                    }
                                } else {
                                    if (fArr.length >= fArr2.length) {
                                        i = 1;
                                    }
                                    this.a = i;
                                }
                            } else {
                                this.a = 1;
                            }
                        } else {
                            this.a = -1;
                        }
                    }
                } else if (obj instanceof boolean[]) {
                    boolean[] zArr = (boolean[]) obj;
                    boolean[] zArr2 = (boolean[]) obj2;
                    if (this.a == 0 && zArr != zArr2) {
                        if (zArr != null) {
                            if (zArr2 != null) {
                                if (zArr.length == zArr2.length) {
                                    while (i2 < zArr.length) {
                                        int i12 = this.a;
                                        if (i12 != 0) {
                                            break;
                                        }
                                        boolean z = zArr[i2];
                                        boolean z2 = zArr2[i2];
                                        if (i12 == 0 && z != z2) {
                                            if (z) {
                                                this.a = 1;
                                            } else {
                                                this.a = -1;
                                            }
                                        }
                                        i2++;
                                    }
                                } else {
                                    if (zArr.length >= zArr2.length) {
                                        i = 1;
                                    }
                                    this.a = i;
                                }
                            } else {
                                this.a = 1;
                            }
                        } else {
                            this.a = -1;
                        }
                    }
                } else {
                    Object[] objArr = (Object[]) obj;
                    Object[] objArr2 = (Object[]) obj2;
                    if (this.a == 0 && objArr != objArr2) {
                        if (objArr == null) {
                            this.a = -1;
                        } else if (objArr2 == null) {
                            this.a = 1;
                        } else if (objArr.length != objArr2.length) {
                            if (objArr.length >= objArr2.length) {
                                i = 1;
                            }
                            this.a = i;
                        } else {
                            while (i2 < objArr.length && this.a == 0) {
                                a(objArr[i2], objArr2[i2], comparator);
                                i2++;
                            }
                        }
                    }
                }
            } else if (comparator == null) {
                this.a = ((Comparable) obj).compareTo(obj2);
            } else {
                this.a = comparator.compare(obj, obj2);
            }
            return this;
        }
    }

    public final int a() {
        return this.a;
    }
}
