package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.sanselan.FormatCompliance;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryOutputStream;
import org.apache.sanselan.common.byteSources.ByteSourceArray;
import org.apache.sanselan.formats.tiff.JpegImageData;
import org.apache.sanselan.formats.tiff.TiffContents;
import org.apache.sanselan.formats.tiff.TiffDirectory;
import org.apache.sanselan.formats.tiff.TiffElement;
import org.apache.sanselan.formats.tiff.TiffElement.Stub;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.TiffReader;

public class TiffImageWriterLossless extends TiffImageWriterBase {
    private static final Comparator ELEMENT_SIZE_COMPARATOR = new Comparator() {
        public int compare(Object obj, Object obj2) {
            return ((TiffElement) obj).length - ((TiffElement) obj2).length;
        }
    };
    private static final Comparator ITEM_SIZE_COMPARATOR = new Comparator() {
        public int compare(Object obj, Object obj2) {
            return ((TiffOutputItem) obj).getItemLength() - ((TiffOutputItem) obj2).getItemLength();
        }
    };
    private final byte[] exifBytes;

    private static class BufferOutputStream extends OutputStream {
        private final byte[] buffer;
        private int index;

        public BufferOutputStream(byte[] bArr, int i) {
            this.buffer = bArr;
            this.index = i;
        }

        public void write(int i) throws IOException {
            int i2 = this.index;
            byte[] bArr = this.buffer;
            if (i2 < bArr.length) {
                this.index = i2 + 1;
                bArr[i2] = (byte) i;
                return;
            }
            throw new IOException("Buffer overflow.");
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.index;
            int i4 = i3 + i2;
            byte[] bArr2 = this.buffer;
            if (i4 <= bArr2.length) {
                System.arraycopy(bArr, i, bArr2, i3, i2);
                this.index += i2;
                return;
            }
            throw new IOException("Buffer overflow.");
        }
    }

    public TiffImageWriterLossless(int i, byte[] bArr) {
        super(i);
        this.exifBytes = bArr;
    }

    private List analyzeOldTiff() throws ImageWriteException, IOException {
        try {
            int i = 0;
            TiffElement tiffElement = null;
            TiffContents readContents = new TiffReader(false).readContents(new ByteSourceArray(this.exifBytes), null, FormatCompliance.getDefault());
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = readContents.directories;
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                TiffDirectory tiffDirectory = (TiffDirectory) arrayList2.get(i2);
                arrayList.add(tiffDirectory);
                ArrayList directoryEntrys = tiffDirectory.getDirectoryEntrys();
                for (int i3 = 0; i3 < directoryEntrys.size(); i3++) {
                    TiffElement oversizeValueElement = ((TiffField) directoryEntrys.get(i3)).getOversizeValueElement();
                    if (oversizeValueElement != null) {
                        arrayList.add(oversizeValueElement);
                    }
                }
                JpegImageData jpegImageData = tiffDirectory.getJpegImageData();
                if (jpegImageData != null) {
                    arrayList.add(jpegImageData);
                }
            }
            Collections.sort(arrayList, TiffElement.COMPARATOR);
            ArrayList arrayList3 = new ArrayList();
            int i4 = -1;
            while (i < arrayList.size()) {
                TiffElement tiffElement2 = (TiffElement) arrayList.get(i);
                int i5 = tiffElement2.offset + tiffElement2.length;
                if (tiffElement != null) {
                    if (tiffElement2.offset - i4 > 3) {
                        arrayList3.add(new Stub(tiffElement.offset, i4 - tiffElement.offset));
                    } else {
                        i++;
                        i4 = i5;
                    }
                }
                tiffElement = tiffElement2;
                i++;
                i4 = i5;
            }
            if (tiffElement != null) {
                arrayList3.add(new Stub(tiffElement.offset, i4 - tiffElement.offset));
            }
            return arrayList3;
        } catch (ImageReadException e) {
            throw new ImageWriteException(e.getMessage(), e);
        }
    }

    public void write(OutputStream outputStream, TiffOutputSet tiffOutputSet) throws IOException, ImageWriteException {
        List analyzeOldTiff = analyzeOldTiff();
        int length = this.exifBytes.length;
        if (analyzeOldTiff.size() >= 1) {
            if (analyzeOldTiff.size() == 1) {
                TiffElement tiffElement = (TiffElement) analyzeOldTiff.get(0);
                if (tiffElement.offset == 8 && tiffElement.offset + tiffElement.length + 8 == length) {
                    new TiffImageWriterLossy(this.byteOrder).write(outputStream, tiffOutputSet);
                    return;
                }
            }
            TiffOutputSummary validateDirectories = validateDirectories(tiffOutputSet);
            List outputItems = tiffOutputSet.getOutputItems(validateDirectories);
            int updateOffsetsStep = updateOffsetsStep(analyzeOldTiff, outputItems);
            validateDirectories.updateOffsets(this.byteOrder);
            writeStep(outputStream, tiffOutputSet, analyzeOldTiff, outputItems, updateOffsetsStep);
            return;
        }
        throw new ImageWriteException("Couldn't analyze old tiff data.");
    }

    private int updateOffsetsStep(List list, List list2) throws IOException, ImageWriteException {
        int length = this.exifBytes.length;
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, TiffElement.COMPARATOR);
        Collections.reverse(arrayList);
        while (arrayList.size() > 0) {
            TiffElement tiffElement = (TiffElement) arrayList.get(0);
            if (tiffElement.offset + tiffElement.length != length) {
                break;
            }
            length -= tiffElement.length;
            arrayList.remove(0);
        }
        Collections.sort(arrayList, ELEMENT_SIZE_COMPARATOR);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(list2);
        Collections.sort(arrayList2, ITEM_SIZE_COMPARATOR);
        Collections.reverse(arrayList2);
        while (arrayList2.size() > 0) {
            TiffOutputItem tiffOutputItem = (TiffOutputItem) arrayList2.remove(0);
            int itemLength = tiffOutputItem.getItemLength();
            TiffElement tiffElement2 = null;
            int i = 0;
            while (i < arrayList.size()) {
                TiffElement tiffElement3 = (TiffElement) arrayList.get(i);
                if (tiffElement3.length < itemLength) {
                    break;
                }
                i++;
                tiffElement2 = tiffElement3;
            }
            if (tiffElement2 == null) {
                tiffOutputItem.setOffset(length);
                length += itemLength;
            } else {
                tiffOutputItem.setOffset(tiffElement2.offset);
                arrayList.remove(tiffElement2);
                if (tiffElement2.length > itemLength) {
                    arrayList.add(new Stub(tiffElement2.offset + itemLength, tiffElement2.length - itemLength));
                    Collections.sort(arrayList, ELEMENT_SIZE_COMPARATOR);
                    Collections.reverse(arrayList);
                }
            }
        }
        return length;
    }

    private void writeStep(OutputStream outputStream, TiffOutputSet tiffOutputSet, List list, List list2, int i) throws IOException, ImageWriteException {
        TiffOutputDirectory rootDirectory = tiffOutputSet.getRootDirectory();
        byte[] bArr = new byte[i];
        byte[] bArr2 = this.exifBytes;
        System.arraycopy(bArr2, 0, bArr, 0, Math.min(bArr2.length, bArr.length));
        writeImageFileHeader(new BinaryOutputStream(new BufferOutputStream(bArr, 0), this.byteOrder), rootDirectory.getOffset());
        for (int i2 = 0; i2 < list.size(); i2++) {
            TiffElement tiffElement = (TiffElement) list.get(i2);
            for (int i3 = 0; i3 < tiffElement.length; i3++) {
                int i4 = tiffElement.offset + i3;
                if (i4 < bArr.length) {
                    bArr[i4] = 0;
                }
            }
        }
        for (int i5 = 0; i5 < list2.size(); i5++) {
            TiffOutputItem tiffOutputItem = (TiffOutputItem) list2.get(i5);
            tiffOutputItem.writeItem(new BinaryOutputStream(new BufferOutputStream(bArr, tiffOutputItem.getOffset()), this.byteOrder));
        }
        outputStream.write(bArr);
    }
}
