package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryOutputStream;

public class TiffImageWriterLossy extends TiffImageWriterBase {
    public TiffImageWriterLossy() {
    }

    public TiffImageWriterLossy(int i) {
        super(i);
    }

    public void write(OutputStream outputStream, TiffOutputSet tiffOutputSet) throws IOException, ImageWriteException {
        TiffOutputSummary validateDirectories = validateDirectories(tiffOutputSet);
        List outputItems = tiffOutputSet.getOutputItems(validateDirectories);
        updateOffsetsStep(outputItems);
        validateDirectories.updateOffsets(this.byteOrder);
        writeStep(new BinaryOutputStream(outputStream, this.byteOrder), outputItems);
    }

    private void updateOffsetsStep(List list) throws IOException, ImageWriteException {
        int i = 8;
        for (int i2 = 0; i2 < list.size(); i2++) {
            TiffOutputItem tiffOutputItem = (TiffOutputItem) list.get(i2);
            tiffOutputItem.setOffset(i);
            int itemLength = tiffOutputItem.getItemLength();
            i = i + itemLength + imageDataPaddingLength(itemLength);
        }
    }

    private void writeStep(BinaryOutputStream binaryOutputStream, List list) throws IOException, ImageWriteException {
        writeImageFileHeader(binaryOutputStream);
        for (int i = 0; i < list.size(); i++) {
            TiffOutputItem tiffOutputItem = (TiffOutputItem) list.get(i);
            tiffOutputItem.writeItem(binaryOutputStream);
            int imageDataPaddingLength = imageDataPaddingLength(tiffOutputItem.getItemLength());
            for (int i2 = 0; i2 < imageDataPaddingLength; i2++) {
                binaryOutputStream.write(0);
            }
        }
    }
}
