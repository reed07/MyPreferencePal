package org.apache.sanselan.formats.tiff.write;

import java.util.ArrayList;
import java.util.List;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

public final class TiffOutputSet implements TiffConstants {
    private static final String newline = System.getProperty("line.separator");
    public final int byteOrder;
    private final ArrayList directories;

    public TiffOutputSet() {
        this(73);
    }

    public TiffOutputSet(int i) {
        this.directories = new ArrayList();
        this.byteOrder = i;
    }

    /* access modifiers changed from: protected */
    public List getOutputItems(TiffOutputSummary tiffOutputSummary) throws ImageWriteException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.directories.size(); i++) {
            arrayList.addAll(((TiffOutputDirectory) this.directories.get(i)).getOutputItems(tiffOutputSummary));
        }
        return arrayList;
    }

    public void addDirectory(TiffOutputDirectory tiffOutputDirectory) throws ImageWriteException {
        if (findDirectory(tiffOutputDirectory.type) == null) {
            this.directories.add(tiffOutputDirectory);
            return;
        }
        throw new ImageWriteException("Output set already contains a directory of that type.");
    }

    public List getDirectories() {
        return new ArrayList(this.directories);
    }

    public TiffOutputDirectory getRootDirectory() {
        return findDirectory(0);
    }

    public TiffOutputDirectory findDirectory(int i) {
        for (int i2 = 0; i2 < this.directories.size(); i2++) {
            TiffOutputDirectory tiffOutputDirectory = (TiffOutputDirectory) this.directories.get(i2);
            if (tiffOutputDirectory.type == i) {
                return tiffOutputDirectory;
            }
        }
        return null;
    }

    public TiffOutputDirectory addExifDirectory() throws ImageWriteException {
        TiffOutputDirectory tiffOutputDirectory = new TiffOutputDirectory(-2);
        addDirectory(tiffOutputDirectory);
        return tiffOutputDirectory;
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String str) {
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("TiffOutputSet {");
        stringBuffer.append(newline);
        stringBuffer.append(str);
        StringBuilder sb = new StringBuilder();
        sb.append("byteOrder: ");
        sb.append(this.byteOrder);
        stringBuffer.append(sb.toString());
        stringBuffer.append(newline);
        for (int i = 0; i < this.directories.size(); i++) {
            TiffOutputDirectory tiffOutputDirectory = (TiffOutputDirectory) this.directories.get(i);
            stringBuffer.append(str);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\tdirectory ");
            sb2.append(i);
            sb2.append(": ");
            sb2.append(tiffOutputDirectory.description());
            sb2.append(" (");
            sb2.append(tiffOutputDirectory.type);
            sb2.append(")");
            stringBuffer.append(sb2.toString());
            stringBuffer.append(newline);
            ArrayList fields = tiffOutputDirectory.getFields();
            for (int i2 = 0; i2 < fields.size(); i2++) {
                TiffOutputField tiffOutputField = (TiffOutputField) fields.get(i2);
                stringBuffer.append(str);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("\t\tfield ");
                sb3.append(i);
                sb3.append(": ");
                sb3.append(tiffOutputField.tagInfo);
                stringBuffer.append(sb3.toString());
                stringBuffer.append(newline);
            }
        }
        stringBuffer.append(str);
        stringBuffer.append("}");
        stringBuffer.append(newline);
        return stringBuffer.toString();
    }
}
