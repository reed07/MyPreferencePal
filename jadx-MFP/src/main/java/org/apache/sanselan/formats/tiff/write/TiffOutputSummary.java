package org.apache.sanselan.formats.tiff.write;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

class TiffOutputSummary implements TiffConstants {
    public final int byteOrder;
    public final Map directoryTypeMap;
    private List imageDataItems = new ArrayList();
    private List offsetItems = new ArrayList();
    public final TiffOutputDirectory rootDirectory;

    private static class OffsetItem {
        public final TiffOutputItem item;
        public final TiffOutputField itemOffsetField;

        public OffsetItem(TiffOutputItem tiffOutputItem, TiffOutputField tiffOutputField) {
            this.itemOffsetField = tiffOutputField;
            this.item = tiffOutputItem;
        }
    }

    public TiffOutputSummary(int i, TiffOutputDirectory tiffOutputDirectory, Map map) {
        this.byteOrder = i;
        this.rootDirectory = tiffOutputDirectory;
        this.directoryTypeMap = map;
    }

    public void add(TiffOutputItem tiffOutputItem, TiffOutputField tiffOutputField) {
        this.offsetItems.add(new OffsetItem(tiffOutputItem, tiffOutputField));
    }

    public void updateOffsets(int i) throws ImageWriteException {
        for (int i2 = 0; i2 < this.offsetItems.size(); i2++) {
            OffsetItem offsetItem = (OffsetItem) this.offsetItems.get(i2);
            offsetItem.itemOffsetField.setData(FIELD_TYPE_LONG.writeData(new int[]{offsetItem.item.getOffset()}, i));
        }
        for (int i3 = 0; i3 < this.imageDataItems.size(); i3++) {
            ImageDataOffsets imageDataOffsets = (ImageDataOffsets) this.imageDataItems.get(i3);
            for (int i4 = 0; i4 < imageDataOffsets.outputItems.length; i4++) {
                imageDataOffsets.imageDataOffsets[i4] = imageDataOffsets.outputItems[i4].getOffset();
            }
            imageDataOffsets.imageDataOffsetsField.setData(FIELD_TYPE_LONG.writeData(imageDataOffsets.imageDataOffsets, i));
        }
    }

    public void addTiffImageData(ImageDataOffsets imageDataOffsets) {
        this.imageDataItems.add(imageDataOffsets);
    }
}
