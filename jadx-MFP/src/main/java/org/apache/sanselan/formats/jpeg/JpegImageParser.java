package org.apache.sanselan.formats.jpeg;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.sanselan.ImageFormat;
import org.apache.sanselan.ImageParser;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.common.byteSources.ByteSource;
import org.apache.sanselan.formats.jpeg.JpegUtils.Visitor;
import org.apache.sanselan.formats.jpeg.segments.App2Segment;
import org.apache.sanselan.formats.jpeg.segments.GenericSegment;
import org.apache.sanselan.formats.jpeg.segments.JFIFSegment;
import org.apache.sanselan.formats.jpeg.segments.SOFNSegment;
import org.apache.sanselan.formats.jpeg.segments.UnknownSegment;
import org.apache.sanselan.formats.tiff.TiffImageMetadata;
import org.apache.sanselan.formats.tiff.TiffImageParser;
import org.apache.sanselan.formats.tiff.constants.TiffTagConstants;

public class JpegImageParser extends ImageParser implements JpegConstants, TiffTagConstants {
    public static final String[] AcceptedExtensions = {".jpg", ".jpeg"};

    public JpegImageParser() {
        setByteOrder(77);
    }

    /* access modifiers changed from: protected */
    public ImageFormat[] getAcceptedTypes() {
        return new ImageFormat[]{ImageFormat.IMAGE_FORMAT_JPEG};
    }

    /* access modifiers changed from: protected */
    public String[] getAcceptedExtensions() {
        return AcceptedExtensions;
    }

    /* access modifiers changed from: private */
    public boolean keepMarker(int i, int[] iArr) {
        if (iArr == null) {
            return true;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public ArrayList readSegments(ByteSource byteSource, final int[] iArr, final boolean z, boolean z2) throws ImageReadException, IOException {
        final ArrayList arrayList = new ArrayList();
        new JpegUtils().traverseJFIF(byteSource, new Visitor() {
            public boolean beginSOS() {
                return false;
            }

            public boolean visitSOS(int i, byte[] bArr, InputStream inputStream) {
                return false;
            }

            public boolean visitSegment(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException {
                if (i == 65497) {
                    return false;
                }
                if (!JpegImageParser.this.keepMarker(i, iArr)) {
                    return true;
                }
                if (i != 65517) {
                    if (i == 65506) {
                        arrayList.add(new App2Segment(i, bArr3));
                    } else if (i == 65504) {
                        arrayList.add(new JFIFSegment(i, bArr3));
                    } else if (i >= 65472 && i <= 65487) {
                        arrayList.add(new SOFNSegment(i, bArr3));
                    } else if (i >= 65505 && i <= 65519) {
                        arrayList.add(new UnknownSegment(i, bArr3));
                    }
                }
                return !z;
            }
        });
        return arrayList;
    }

    public ArrayList readSegments(ByteSource byteSource, int[] iArr, boolean z) throws ImageReadException, IOException {
        return readSegments(byteSource, iArr, z, false);
    }

    public IImageMetadata getMetadata(ByteSource byteSource, Map map) throws ImageReadException, IOException {
        TiffImageMetadata exifMetadata = getExifMetadata(byteSource, map);
        if (exifMetadata == null) {
            return null;
        }
        return new JpegImageMetadata(null, exifMetadata);
    }

    public static boolean isExifAPP1Segment(GenericSegment genericSegment) {
        return byteArrayHasPrefix(genericSegment.bytes, EXIF_IDENTIFIER_CODE);
    }

    private ArrayList filterAPP1Segments(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            GenericSegment genericSegment = (GenericSegment) arrayList.get(i);
            if (isExifAPP1Segment(genericSegment)) {
                arrayList2.add(genericSegment);
            }
        }
        return arrayList2;
    }

    public TiffImageMetadata getExifMetadata(ByteSource byteSource, Map map) throws ImageReadException, IOException {
        byte[] exifRawData = getExifRawData(byteSource);
        if (exifRawData == null) {
            return null;
        }
        if (map == null) {
            map = new HashMap();
        }
        if (!map.containsKey("READ_THUMBNAILS")) {
            map.put("READ_THUMBNAILS", Boolean.TRUE);
        }
        return (TiffImageMetadata) new TiffImageParser().getMetadata(exifRawData, map);
    }

    public byte[] getExifRawData(ByteSource byteSource) throws ImageReadException, IOException {
        ArrayList readSegments = readSegments(byteSource, new int[]{65505}, false);
        if (readSegments == null || readSegments.size() < 1) {
            return null;
        }
        ArrayList filterAPP1Segments = filterAPP1Segments(readSegments);
        if (this.debug) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("exif_segments.size: ");
            sb.append(filterAPP1Segments.size());
            printStream.println(sb.toString());
        }
        if (filterAPP1Segments.size() < 1) {
            return null;
        }
        if (filterAPP1Segments.size() <= 1) {
            return getByteArrayTail("trimmed exif bytes", ((GenericSegment) filterAPP1Segments.get(0)).bytes, 6);
        }
        throw new ImageReadException("Sanselan currently can't parse EXIF metadata split across multiple APP1 segments.  Please send this image to the Sanselan project.");
    }
}
