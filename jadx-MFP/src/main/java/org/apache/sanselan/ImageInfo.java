package org.apache.sanselan;

import com.facebook.internal.AnalyticsEvents;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class ImageInfo {
    private final int bitsPerPixel;
    private final int colorType;
    private final ArrayList comments;
    private final String compressionAlgorithm;
    private final ImageFormat format;
    private final String formatDetails;
    private final String formatName;
    private final int height;
    private final boolean isProgressive;
    private final boolean isTransparent;
    private final String mimeType;
    private final int numberOfImages;
    private final int physicalHeightDpi;
    private final float physicalHeightInch;
    private final int physicalWidthDpi;
    private final float physicalWidthInch;
    private final boolean usesPalette;
    private final int width;

    public String getColorTypeDescription() {
        switch (this.colorType) {
            case -2:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            case -1:
                return "Other";
            case 0:
                return "Black and White";
            case 1:
                return "Grayscale";
            case 2:
                return "RGB";
            case 3:
                return "CMYK";
            default:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            toString(printWriter, "");
            printWriter.flush();
            return stringWriter.toString();
        } catch (Exception unused) {
            return "Image Data: Error";
        }
    }

    public void toString(PrintWriter printWriter, String str) throws ImageReadException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Format Details: ");
        sb.append(this.formatDetails);
        printWriter.println(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Bits Per Pixel: ");
        sb2.append(this.bitsPerPixel);
        printWriter.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Comments: ");
        sb3.append(this.comments.size());
        printWriter.println(sb3.toString());
        for (int i = 0; i < this.comments.size(); i++) {
            String str2 = (String) this.comments.get(i);
            StringBuilder sb4 = new StringBuilder();
            sb4.append("\t");
            sb4.append(i);
            sb4.append(": '");
            sb4.append(str2);
            sb4.append("'");
            printWriter.println(sb4.toString());
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Format: ");
        sb5.append(this.format.name);
        printWriter.println(sb5.toString());
        StringBuilder sb6 = new StringBuilder();
        sb6.append("Format Name: ");
        sb6.append(this.formatName);
        printWriter.println(sb6.toString());
        StringBuilder sb7 = new StringBuilder();
        sb7.append("Compression Algorithm: ");
        sb7.append(this.compressionAlgorithm);
        printWriter.println(sb7.toString());
        StringBuilder sb8 = new StringBuilder();
        sb8.append("Height: ");
        sb8.append(this.height);
        printWriter.println(sb8.toString());
        StringBuilder sb9 = new StringBuilder();
        sb9.append("MimeType: ");
        sb9.append(this.mimeType);
        printWriter.println(sb9.toString());
        StringBuilder sb10 = new StringBuilder();
        sb10.append("Number Of Images: ");
        sb10.append(this.numberOfImages);
        printWriter.println(sb10.toString());
        StringBuilder sb11 = new StringBuilder();
        sb11.append("Physical Height Dpi: ");
        sb11.append(this.physicalHeightDpi);
        printWriter.println(sb11.toString());
        StringBuilder sb12 = new StringBuilder();
        sb12.append("Physical Height Inch: ");
        sb12.append(this.physicalHeightInch);
        printWriter.println(sb12.toString());
        StringBuilder sb13 = new StringBuilder();
        sb13.append("Physical Width Dpi: ");
        sb13.append(this.physicalWidthDpi);
        printWriter.println(sb13.toString());
        StringBuilder sb14 = new StringBuilder();
        sb14.append("Physical Width Inch: ");
        sb14.append(this.physicalWidthInch);
        printWriter.println(sb14.toString());
        StringBuilder sb15 = new StringBuilder();
        sb15.append("Width: ");
        sb15.append(this.width);
        printWriter.println(sb15.toString());
        StringBuilder sb16 = new StringBuilder();
        sb16.append("Is Progressive: ");
        sb16.append(this.isProgressive);
        printWriter.println(sb16.toString());
        StringBuilder sb17 = new StringBuilder();
        sb17.append("Is Transparent: ");
        sb17.append(this.isTransparent);
        printWriter.println(sb17.toString());
        StringBuilder sb18 = new StringBuilder();
        sb18.append("Color Type: ");
        sb18.append(getColorTypeDescription());
        printWriter.println(sb18.toString());
        StringBuilder sb19 = new StringBuilder();
        sb19.append("Uses Palette: ");
        sb19.append(this.usesPalette);
        printWriter.println(sb19.toString());
        printWriter.flush();
    }
}
