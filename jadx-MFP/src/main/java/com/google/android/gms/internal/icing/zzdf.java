package com.google.android.gms.internal.icing;

import java.lang.reflect.Type;

public enum zzdf {
    DOUBLE(0, zzdh.SCALAR, zzdt.DOUBLE),
    FLOAT(1, zzdh.SCALAR, zzdt.FLOAT),
    INT64(2, zzdh.SCALAR, zzdt.LONG),
    UINT64(3, zzdh.SCALAR, zzdt.LONG),
    INT32(4, zzdh.SCALAR, zzdt.INT),
    FIXED64(5, zzdh.SCALAR, zzdt.LONG),
    FIXED32(6, zzdh.SCALAR, zzdt.INT),
    BOOL(7, zzdh.SCALAR, zzdt.BOOLEAN),
    STRING(8, zzdh.SCALAR, zzdt.STRING),
    MESSAGE(9, zzdh.SCALAR, zzdt.MESSAGE),
    BYTES(10, zzdh.SCALAR, zzdt.BYTE_STRING),
    UINT32(11, zzdh.SCALAR, zzdt.INT),
    ENUM(12, zzdh.SCALAR, zzdt.ENUM),
    SFIXED32(13, zzdh.SCALAR, zzdt.INT),
    SFIXED64(14, zzdh.SCALAR, zzdt.LONG),
    SINT32(15, zzdh.SCALAR, zzdt.INT),
    SINT64(16, zzdh.SCALAR, zzdt.LONG),
    GROUP(17, zzdh.SCALAR, zzdt.MESSAGE),
    DOUBLE_LIST(18, zzdh.VECTOR, zzdt.DOUBLE),
    FLOAT_LIST(19, zzdh.VECTOR, zzdt.FLOAT),
    INT64_LIST(20, zzdh.VECTOR, zzdt.LONG),
    UINT64_LIST(21, zzdh.VECTOR, zzdt.LONG),
    INT32_LIST(22, zzdh.VECTOR, zzdt.INT),
    FIXED64_LIST(23, zzdh.VECTOR, zzdt.LONG),
    FIXED32_LIST(24, zzdh.VECTOR, zzdt.INT),
    BOOL_LIST(25, zzdh.VECTOR, zzdt.BOOLEAN),
    STRING_LIST(26, zzdh.VECTOR, zzdt.STRING),
    MESSAGE_LIST(27, zzdh.VECTOR, zzdt.MESSAGE),
    BYTES_LIST(28, zzdh.VECTOR, zzdt.BYTE_STRING),
    UINT32_LIST(29, zzdh.VECTOR, zzdt.INT),
    ENUM_LIST(30, zzdh.VECTOR, zzdt.ENUM),
    SFIXED32_LIST(31, zzdh.VECTOR, zzdt.INT),
    SFIXED64_LIST(32, zzdh.VECTOR, zzdt.LONG),
    SINT32_LIST(33, zzdh.VECTOR, zzdt.INT),
    SINT64_LIST(34, zzdh.VECTOR, zzdt.LONG),
    DOUBLE_LIST_PACKED(35, zzdh.PACKED_VECTOR, zzdt.DOUBLE),
    FLOAT_LIST_PACKED(36, zzdh.PACKED_VECTOR, zzdt.FLOAT),
    INT64_LIST_PACKED(37, zzdh.PACKED_VECTOR, zzdt.LONG),
    UINT64_LIST_PACKED(38, zzdh.PACKED_VECTOR, zzdt.LONG),
    INT32_LIST_PACKED(39, zzdh.PACKED_VECTOR, zzdt.INT),
    FIXED64_LIST_PACKED(40, zzdh.PACKED_VECTOR, zzdt.LONG),
    FIXED32_LIST_PACKED(41, zzdh.PACKED_VECTOR, zzdt.INT),
    BOOL_LIST_PACKED(42, zzdh.PACKED_VECTOR, zzdt.BOOLEAN),
    UINT32_LIST_PACKED(43, zzdh.PACKED_VECTOR, zzdt.INT),
    ENUM_LIST_PACKED(44, zzdh.PACKED_VECTOR, zzdt.ENUM),
    SFIXED32_LIST_PACKED(45, zzdh.PACKED_VECTOR, zzdt.INT),
    SFIXED64_LIST_PACKED(46, zzdh.PACKED_VECTOR, zzdt.LONG),
    SINT32_LIST_PACKED(47, zzdh.PACKED_VECTOR, zzdt.INT),
    SINT64_LIST_PACKED(48, zzdh.PACKED_VECTOR, zzdt.LONG),
    GROUP_LIST(49, zzdh.VECTOR, zzdt.MESSAGE),
    MAP(50, zzdh.MAP, zzdt.VOID);
    
    private static final zzdf[] zzjh = null;
    private static final Type[] zzji = null;
    private final int id;
    private final zzdt zzjd;
    private final zzdh zzje;
    private final Class<?> zzjf;
    private final boolean zzjg;

    private zzdf(int i, zzdh zzdh, zzdt zzdt) {
        this.id = i;
        this.zzje = zzdh;
        this.zzjd = zzdt;
        switch (zzdg.zzjk[zzdh.ordinal()]) {
            case 1:
                this.zzjf = zzdt.zzcd();
                break;
            case 2:
                this.zzjf = zzdt.zzcd();
                break;
            default:
                this.zzjf = null;
                break;
        }
        boolean z = false;
        if (zzdh == zzdh.SCALAR) {
            switch (zzdg.zzjl[zzdt.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzjg = z;
    }

    public final int id() {
        return this.id;
    }

    static {
        int i;
        zzji = new Type[0];
        zzdf[] values = values();
        zzjh = new zzdf[values.length];
        for (zzdf zzdf : values) {
            zzjh[zzdf.id] = zzdf;
        }
    }
}
