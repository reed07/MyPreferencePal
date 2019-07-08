package org.apache.sanselan;

import java.io.IOException;
import java.util.Map;
import org.apache.sanselan.common.BinaryFileParser;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.common.byteSources.ByteSource;
import org.apache.sanselan.common.byteSources.ByteSourceArray;
import org.apache.sanselan.formats.jpeg.JpegImageParser;
import org.apache.sanselan.formats.tiff.TiffImageParser;

public abstract class ImageParser extends BinaryFileParser implements SanselanConstants {
    /* access modifiers changed from: protected */
    public abstract String[] getAcceptedExtensions();

    /* access modifiers changed from: protected */
    public abstract ImageFormat[] getAcceptedTypes();

    public abstract IImageMetadata getMetadata(ByteSource byteSource, Map map) throws ImageReadException, IOException;

    public static final ImageParser[] getAllImageParsers() {
        return new ImageParser[]{new JpegImageParser(), new TiffImageParser()};
    }

    public final IImageMetadata getMetadata(byte[] bArr, Map map) throws ImageReadException, IOException {
        return getMetadata((ByteSource) new ByteSourceArray(bArr), map);
    }

    public boolean canAcceptType(ImageFormat imageFormat) {
        ImageFormat[] acceptedTypes = getAcceptedTypes();
        for (ImageFormat equals : acceptedTypes) {
            if (equals.equals(imageFormat)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean canAcceptExtension(String str) {
        String[] acceptedExtensions = getAcceptedExtensions();
        if (acceptedExtensions == null) {
            return true;
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String lowerCase = str.substring(lastIndexOf).toLowerCase();
            for (String lowerCase2 : acceptedExtensions) {
                if (lowerCase2.toLowerCase().equals(lowerCase)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isStrict(Map map) {
        if (map == null || !map.containsKey("STRICT")) {
            return false;
        }
        return ((Boolean) map.get("STRICT")).booleanValue();
    }
}
