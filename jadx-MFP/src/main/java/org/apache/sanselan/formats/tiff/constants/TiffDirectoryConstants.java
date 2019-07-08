package org.apache.sanselan.formats.tiff.constants;

public interface TiffDirectoryConstants {
    public static final ExifDirectoryType[] EXIF_DIRECTORIES = {TIFF_DIRECTORY_ROOT, EXIF_DIRECTORY_EXIF_IFD, TIFF_DIRECTORY_IFD0, EXIF_DIRECTORY_IFD0, TIFF_DIRECTORY_IFD1, EXIF_DIRECTORY_IFD1, TIFF_DIRECTORY_IFD2, EXIF_DIRECTORY_IFD2, EXIF_DIRECTORY_INTEROP_IFD, EXIF_DIRECTORY_MAKER_NOTES, EXIF_DIRECTORY_SUB_IFD, EXIF_DIRECTORY_SUB_IFD1, EXIF_DIRECTORY_SUB_IFD2, EXIF_DIRECTORY_GPS};
    public static final ExifDirectoryType EXIF_DIRECTORY_EXIF_IFD = new Special(-2, "Exif IFD");
    public static final ExifDirectoryType EXIF_DIRECTORY_GPS = new Special(-3, "GPS IFD");
    public static final ExifDirectoryType EXIF_DIRECTORY_IFD0;
    public static final ExifDirectoryType EXIF_DIRECTORY_IFD1 = TIFF_DIRECTORY_IFD1;
    public static final ExifDirectoryType EXIF_DIRECTORY_IFD2 = TIFF_DIRECTORY_IFD2;
    public static final ExifDirectoryType EXIF_DIRECTORY_IFD3;
    public static final ExifDirectoryType EXIF_DIRECTORY_INTEROP_IFD = new Special(-4, "Interop IFD");
    public static final ExifDirectoryType EXIF_DIRECTORY_MAKER_NOTES = new Special(-5, "Maker Notes");
    public static final ExifDirectoryType EXIF_DIRECTORY_SUB_IFD = TIFF_DIRECTORY_IFD1;
    public static final ExifDirectoryType EXIF_DIRECTORY_SUB_IFD1 = TIFF_DIRECTORY_IFD2;
    public static final ExifDirectoryType EXIF_DIRECTORY_SUB_IFD2;
    public static final ExifDirectoryType EXIF_DIRECTORY_UNKNOWN = null;
    public static final ExifDirectoryType TIFF_DIRECTORY_IFD0 = new Image(0, "IFD0");
    public static final ExifDirectoryType TIFF_DIRECTORY_IFD1 = new Image(1, "IFD1");
    public static final ExifDirectoryType TIFF_DIRECTORY_IFD2 = new Image(2, "IFD2");
    public static final ExifDirectoryType TIFF_DIRECTORY_IFD3 = new Image(3, "IFD3");
    public static final ExifDirectoryType TIFF_DIRECTORY_ROOT;

    public static abstract class ExifDirectoryType {
        public final int directoryType;
        public final String name;

        public static class Image extends ExifDirectoryType {
            public boolean isImageDirectory() {
                return true;
            }

            public Image(int i, String str) {
                super(i, str);
            }
        }

        public static class Special extends ExifDirectoryType {
            public boolean isImageDirectory() {
                return false;
            }

            public Special(int i, String str) {
                super(i, str);
            }
        }

        public abstract boolean isImageDirectory();

        public ExifDirectoryType(int i, String str) {
            this.directoryType = i;
            this.name = str;
        }
    }

    static {
        ExifDirectoryType exifDirectoryType = TIFF_DIRECTORY_IFD0;
        EXIF_DIRECTORY_IFD0 = exifDirectoryType;
        TIFF_DIRECTORY_ROOT = exifDirectoryType;
        ExifDirectoryType exifDirectoryType2 = TIFF_DIRECTORY_IFD3;
        EXIF_DIRECTORY_IFD3 = exifDirectoryType2;
        EXIF_DIRECTORY_SUB_IFD2 = exifDirectoryType2;
    }
}
