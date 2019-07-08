package com.google.android.exoplayer2.video.spherical;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.spherical.Projection.Mesh;
import com.google.android.exoplayer2.video.spherical.Projection.SubMesh;
import java.util.ArrayList;
import java.util.zip.Inflater;

public final class ProjectionDecoder {
    private static final int MAX_COORDINATE_COUNT = 10000;
    private static final int MAX_TRIANGLE_INDICES = 128000;
    private static final int MAX_VERTEX_COUNT = 32000;
    private static final int TYPE_DFL8 = Util.getIntegerCodeForString("dfl8");
    private static final int TYPE_MESH = Util.getIntegerCodeForString("mesh");
    private static final int TYPE_MSHP = Util.getIntegerCodeForString("mshp");
    private static final int TYPE_PROJ = Util.getIntegerCodeForString("proj");
    private static final int TYPE_RAW = Util.getIntegerCodeForString("raw ");
    private static final int TYPE_YTMP = Util.getIntegerCodeForString("ytmp");

    private static int decodeZigZag(int i) {
        return (-(i & 1)) ^ (i >> 1);
    }

    private ProjectionDecoder() {
    }

    @Nullable
    public static Projection decode(byte[] bArr, int i) {
        ArrayList arrayList;
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        try {
            arrayList = isProj(parsableByteArray) ? parseProj(parsableByteArray) : parseMshp(parsableByteArray);
        } catch (ArrayIndexOutOfBoundsException unused) {
            arrayList = null;
        }
        if (arrayList == null) {
            return null;
        }
        switch (arrayList.size()) {
            case 1:
                return new Projection((Mesh) arrayList.get(0), i);
            case 2:
                return new Projection((Mesh) arrayList.get(0), (Mesh) arrayList.get(1), i);
            default:
                return null;
        }
    }

    private static boolean isProj(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.setPosition(0);
        if (readInt == TYPE_PROJ) {
            return true;
        }
        return false;
    }

    @Nullable
    private static ArrayList<Mesh> parseProj(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(8);
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        while (position < limit) {
            int readInt = parsableByteArray.readInt() + position;
            if (readInt <= position || readInt > limit) {
                return null;
            }
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == TYPE_YTMP || readInt2 == TYPE_MSHP) {
                parsableByteArray.setLimit(readInt);
                return parseMshp(parsableByteArray);
            }
            parsableByteArray.setPosition(readInt);
            position = readInt;
        }
        return null;
    }

    @Nullable
    private static ArrayList<Mesh> parseMshp(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.readUnsignedByte() != 0) {
            return null;
        }
        parsableByteArray.skipBytes(7);
        int readInt = parsableByteArray.readInt();
        if (readInt == TYPE_DFL8) {
            ParsableByteArray parsableByteArray2 = new ParsableByteArray();
            Inflater inflater = new Inflater(true);
            try {
                if (!Util.inflate(parsableByteArray, parsableByteArray2, inflater)) {
                    return null;
                }
                inflater.end();
                parsableByteArray = parsableByteArray2;
            } finally {
                inflater.end();
            }
        } else if (readInt != TYPE_RAW) {
            return null;
        }
        return parseRawMshpData(parsableByteArray);
    }

    @Nullable
    private static ArrayList<Mesh> parseRawMshpData(ParsableByteArray parsableByteArray) {
        ArrayList<Mesh> arrayList = new ArrayList<>();
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        while (position < limit) {
            int readInt = parsableByteArray.readInt() + position;
            if (readInt <= position || readInt > limit) {
                return null;
            }
            if (parsableByteArray.readInt() == TYPE_MESH) {
                Mesh parseMesh = parseMesh(parsableByteArray);
                if (parseMesh == null) {
                    return null;
                }
                arrayList.add(parseMesh);
            }
            parsableByteArray.setPosition(readInt);
            position = readInt;
        }
        return arrayList;
    }

    @Nullable
    private static Mesh parseMesh(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (readInt > 10000) {
            return null;
        }
        float[] fArr = new float[readInt];
        for (int i = 0; i < readInt; i++) {
            fArr[i] = parsableByteArray.readFloat();
        }
        int readInt2 = parsableByteArray.readInt();
        if (readInt2 > MAX_VERTEX_COUNT) {
            return null;
        }
        double d = 2.0d;
        double log = Math.log(2.0d);
        int ceil = (int) Math.ceil(Math.log(((double) readInt) * 2.0d) / log);
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.data);
        int i2 = 8;
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        float[] fArr2 = new float[(readInt2 * 5)];
        int i3 = 5;
        int[] iArr = new int[5];
        int i4 = 0;
        int i5 = 0;
        while (i4 < readInt2) {
            int i6 = 0;
            while (i6 < i3) {
                int decodeZigZag = iArr[i6] + decodeZigZag(parsableBitArray.readBits(ceil));
                if (decodeZigZag >= readInt || decodeZigZag < 0) {
                    return null;
                }
                int i7 = i5 + 1;
                fArr2[i5] = fArr[decodeZigZag];
                iArr[i6] = decodeZigZag;
                i6++;
                i5 = i7;
                i3 = 5;
            }
            i4++;
            i3 = 5;
        }
        parsableBitArray.setPosition((parsableBitArray.getPosition() + 7) & -8);
        int i8 = 32;
        int readBits = parsableBitArray.readBits(32);
        SubMesh[] subMeshArr = new SubMesh[readBits];
        int i9 = 0;
        while (i9 < readBits) {
            int readBits2 = parsableBitArray.readBits(i2);
            int readBits3 = parsableBitArray.readBits(i2);
            int readBits4 = parsableBitArray.readBits(i8);
            if (readBits4 > MAX_TRIANGLE_INDICES) {
                return null;
            }
            int i10 = readBits2;
            int ceil2 = (int) Math.ceil(Math.log(((double) readInt2) * d) / log);
            float[] fArr3 = new float[(readBits4 * 3)];
            float[] fArr4 = new float[(readBits4 * 2)];
            int i11 = 0;
            for (int i12 = 0; i12 < readBits4; i12++) {
                i11 += decodeZigZag(parsableBitArray.readBits(ceil2));
                if (i11 < 0 || i11 >= readInt2) {
                    return null;
                }
                int i13 = i12 * 3;
                int i14 = i11 * 5;
                fArr3[i13] = fArr2[i14];
                fArr3[i13 + 1] = fArr2[i14 + 1];
                fArr3[i13 + 2] = fArr2[i14 + 2];
                int i15 = i12 * 2;
                fArr4[i15] = fArr2[i14 + 3];
                fArr4[i15 + 1] = fArr2[i14 + 4];
            }
            subMeshArr[i9] = new SubMesh(i10, fArr3, fArr4, readBits3);
            i9++;
            i8 = 32;
            d = 2.0d;
            i2 = 8;
        }
        return new Mesh(subMeshArr);
    }
}
