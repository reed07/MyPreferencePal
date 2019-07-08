package org.apache.sanselan.formats.tiff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.sanselan.FormatCompliance;
import org.apache.sanselan.ImageFormat;
import org.apache.sanselan.ImageParser;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.common.byteSources.ByteSource;
import org.apache.sanselan.formats.tiff.TiffImageMetadata.Directory;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

public class TiffImageParser extends ImageParser implements TiffConstants {
    private static final String[] ACCEPTED_EXTENSIONS = {".tif", ".tiff"};

    /* access modifiers changed from: protected */
    public String[] getAcceptedExtensions() {
        return ACCEPTED_EXTENSIONS;
    }

    /* access modifiers changed from: protected */
    public ImageFormat[] getAcceptedTypes() {
        return new ImageFormat[]{ImageFormat.IMAGE_FORMAT_TIFF};
    }

    public IImageMetadata getMetadata(ByteSource byteSource, Map map) throws ImageReadException, IOException {
        TiffContents readContents = new TiffReader(isStrict(map)).readContents(byteSource, map, FormatCompliance.getDefault());
        ArrayList arrayList = readContents.directories;
        TiffImageMetadata tiffImageMetadata = new TiffImageMetadata(readContents);
        for (int i = 0; i < arrayList.size(); i++) {
            TiffDirectory tiffDirectory = (TiffDirectory) arrayList.get(i);
            Directory directory = new Directory(tiffDirectory);
            ArrayList directoryEntrys = tiffDirectory.getDirectoryEntrys();
            for (int i2 = 0; i2 < directoryEntrys.size(); i2++) {
                directory.add((TiffField) directoryEntrys.get(i2));
            }
            tiffImageMetadata.add(directory);
        }
        return tiffImageMetadata;
    }
}
