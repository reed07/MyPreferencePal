package org.apache.sanselan;

import com.myfitnesspal.shared.constants.Constants.Analytics;

public class ImageFormat {
    public static final ImageFormat IMAGE_FORMAT_BMP = new ImageFormat("BMP");
    public static final ImageFormat IMAGE_FORMAT_GIF = new ImageFormat("GIF");
    public static final ImageFormat IMAGE_FORMAT_ICO = new ImageFormat("ICO");
    public static final ImageFormat IMAGE_FORMAT_JBIG2 = new ImageFormat("JBig2");
    public static final ImageFormat IMAGE_FORMAT_JPEG = new ImageFormat("JPEG");
    public static final ImageFormat IMAGE_FORMAT_PBM = new ImageFormat("PBM");
    public static final ImageFormat IMAGE_FORMAT_PGM = new ImageFormat("PGM");
    public static final ImageFormat IMAGE_FORMAT_PNG = new ImageFormat("PNG");
    public static final ImageFormat IMAGE_FORMAT_PNM = new ImageFormat("PNM");
    public static final ImageFormat IMAGE_FORMAT_PPM = new ImageFormat("PPM");
    public static final ImageFormat IMAGE_FORMAT_PSD = new ImageFormat("PSD");
    public static final ImageFormat IMAGE_FORMAT_TGA = new ImageFormat("TGA");
    public static final ImageFormat IMAGE_FORMAT_TIFF = new ImageFormat("TIFF");
    public static final ImageFormat IMAGE_FORMAT_UNKNOWN = new ImageFormat(Analytics.UNKNOWN, false);
    public final boolean actual;
    public final String extension;
    public final String name;

    private ImageFormat(String str, boolean z) {
        this.name = str;
        this.extension = str;
        this.actual = z;
    }

    private ImageFormat(String str) {
        this.name = str;
        this.extension = str;
        this.actual = true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ImageFormat)) {
            return false;
        }
        return ((ImageFormat) obj).name.equals(this.name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(this.name);
        sb.append(": ");
        sb.append(this.extension);
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}
