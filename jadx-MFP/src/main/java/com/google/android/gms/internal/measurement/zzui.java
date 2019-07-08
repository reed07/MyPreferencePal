package com.google.android.gms.internal.measurement;

import java.lang.reflect.Type;

public enum zzui {
    DOUBLE(0, zzuk.SCALAR, zzux.DOUBLE),
    FLOAT(1, zzuk.SCALAR, zzux.FLOAT),
    INT64(2, zzuk.SCALAR, zzux.LONG),
    UINT64(3, zzuk.SCALAR, zzux.LONG),
    INT32(4, zzuk.SCALAR, zzux.INT),
    FIXED64(5, zzuk.SCALAR, zzux.LONG),
    FIXED32(6, zzuk.SCALAR, zzux.INT),
    BOOL(7, zzuk.SCALAR, zzux.BOOLEAN),
    STRING(8, zzuk.SCALAR, zzux.STRING),
    MESSAGE(9, zzuk.SCALAR, zzux.MESSAGE),
    BYTES(10, zzuk.SCALAR, zzux.BYTE_STRING),
    UINT32(11, zzuk.SCALAR, zzux.INT),
    ENUM(12, zzuk.SCALAR, zzux.ENUM),
    SFIXED32(13, zzuk.SCALAR, zzux.INT),
    SFIXED64(14, zzuk.SCALAR, zzux.LONG),
    SINT32(15, zzuk.SCALAR, zzux.INT),
    SINT64(16, zzuk.SCALAR, zzux.LONG),
    GROUP(17, zzuk.SCALAR, zzux.MESSAGE),
    DOUBLE_LIST(18, zzuk.VECTOR, zzux.DOUBLE),
    FLOAT_LIST(19, zzuk.VECTOR, zzux.FLOAT),
    INT64_LIST(20, zzuk.VECTOR, zzux.LONG),
    UINT64_LIST(21, zzuk.VECTOR, zzux.LONG),
    INT32_LIST(22, zzuk.VECTOR, zzux.INT),
    FIXED64_LIST(23, zzuk.VECTOR, zzux.LONG),
    FIXED32_LIST(24, zzuk.VECTOR, zzux.INT),
    BOOL_LIST(25, zzuk.VECTOR, zzux.BOOLEAN),
    STRING_LIST(26, zzuk.VECTOR, zzux.STRING),
    MESSAGE_LIST(27, zzuk.VECTOR, zzux.MESSAGE),
    BYTES_LIST(28, zzuk.VECTOR, zzux.BYTE_STRING),
    UINT32_LIST(29, zzuk.VECTOR, zzux.INT),
    ENUM_LIST(30, zzuk.VECTOR, zzux.ENUM),
    SFIXED32_LIST(31, zzuk.VECTOR, zzux.INT),
    SFIXED64_LIST(32, zzuk.VECTOR, zzux.LONG),
    SINT32_LIST(33, zzuk.VECTOR, zzux.INT),
    SINT64_LIST(34, zzuk.VECTOR, zzux.LONG),
    DOUBLE_LIST_PACKED(35, zzuk.PACKED_VECTOR, zzux.DOUBLE),
    FLOAT_LIST_PACKED(36, zzuk.PACKED_VECTOR, zzux.FLOAT),
    INT64_LIST_PACKED(37, zzuk.PACKED_VECTOR, zzux.LONG),
    UINT64_LIST_PACKED(38, zzuk.PACKED_VECTOR, zzux.LONG),
    INT32_LIST_PACKED(39, zzuk.PACKED_VECTOR, zzux.INT),
    FIXED64_LIST_PACKED(40, zzuk.PACKED_VECTOR, zzux.LONG),
    FIXED32_LIST_PACKED(41, zzuk.PACKED_VECTOR, zzux.INT),
    BOOL_LIST_PACKED(42, zzuk.PACKED_VECTOR, zzux.BOOLEAN),
    UINT32_LIST_PACKED(43, zzuk.PACKED_VECTOR, zzux.INT),
    ENUM_LIST_PACKED(44, zzuk.PACKED_VECTOR, zzux.ENUM),
    SFIXED32_LIST_PACKED(45, zzuk.PACKED_VECTOR, zzux.INT),
    SFIXED64_LIST_PACKED(46, zzuk.PACKED_VECTOR, zzux.LONG),
    SINT32_LIST_PACKED(47, zzuk.PACKED_VECTOR, zzux.INT),
    SINT64_LIST_PACKED(48, zzuk.PACKED_VECTOR, zzux.LONG),
    GROUP_LIST(49, zzuk.VECTOR, zzux.MESSAGE),
    MAP(50, zzuk.MAP, zzux.VOID);
    
    private static final zzui[] zzbxq = null;
    private static final Type[] zzbxr = null;
    private final int id;
    private final zzux zzbxm;
    private final zzuk zzbxn;
    private final Class<?> zzbxo;
    private final boolean zzbxp;

    private zzui(int i, zzuk zzuk, zzux zzux) {
        this.id = i;
        this.zzbxn = zzuk;
        this.zzbxm = zzux;
        switch (zzuk) {
            case MAP:
                this.zzbxo = zzux.zzwy();
                break;
            case VECTOR:
                this.zzbxo = zzux.zzwy();
                break;
            default:
                this.zzbxo = null;
                break;
        }
        boolean z = false;
        if (zzuk == zzuk.SCALAR) {
            switch (zzux) {
                case BYTE_STRING:
                case MESSAGE:
                case STRING:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzbxp = z;
    }

    public final int id() {
        return this.id;
    }

    static {
        int i;
        zzbxr = new Type[0];
        zzui[] values = values();
        zzbxq = new zzui[values.length];
        for (zzui zzui : values) {
            zzbxq[zzui.id] = zzui;
        }
    }
}
