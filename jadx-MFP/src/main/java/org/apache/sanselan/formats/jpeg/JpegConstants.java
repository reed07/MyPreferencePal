package org.apache.sanselan.formats.jpeg;

public interface JpegConstants {
    public static final byte[] CONST_8BIM = {56, 66, 73, 77};
    public static final byte[] EOI = {-1, -39};
    public static final byte[] EXIF_IDENTIFIER_CODE = {69, Framer.EXIT_FRAME_PREFIX, 105, 102};
    public static final byte[] JFIF0_SIGNATURE = {74, 70, 73, 70, 0};
    public static final byte[] JFIF0_SIGNATURE_ALTERNATIVE = {74, 70, 73, 70, 32};
    public static final int[] MARKERS = {65498, 224, 65504, 65505, 65506, 65517, 65518, 65519, 65504, 65472, 65473, 65474, 65475, 65476, 65477, 65478, 65479, 65480, 65481, 65482, 65483, 65484, 65485, 65486, 65487};
    public static final byte[] PHOTOSHOP_IDENTIFICATION_STRING = {80, 104, 111, 116, 111, 115, 104, 111, 112, 32, 51, 46, 48, 0};
    public static final byte[] SOI = {-1, -40};
    public static final byte[] XMP_IDENTIFIER = {104, 116, 116, 112, 58, 47, 47, 110, 115, 46, 97, 100, 111, 98, 101, 46, 99, 111, 109, 47, Framer.EXIT_FRAME_PREFIX, 97, 112, 47, Framer.STDOUT_FRAME_PREFIX, 46, 48, 47, 0};
    public static final byte[] icc_profile_label = {73, 67, 67, Framer.STDIN_REQUEST_FRAME_PREFIX, 80, 82, 79, 70, 73, 76, 69, 0};
}
