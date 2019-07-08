package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryConstants;
import org.apache.sanselan.common.BinaryOutputStream;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

public abstract class TiffImageWriterBase implements BinaryConstants, TiffConstants {
    protected final int byteOrder;

    public abstract void write(OutputStream outputStream, TiffOutputSet tiffOutputSet) throws IOException, ImageWriteException;

    public TiffImageWriterBase() {
        this.byteOrder = 73;
    }

    public TiffImageWriterBase(int i) {
        this.byteOrder = i;
    }

    protected static final int imageDataPaddingLength(int i) {
        return (4 - (i % 4)) % 4;
    }

    /* access modifiers changed from: protected */
    public TiffOutputSummary validateDirectories(TiffOutputSet tiffOutputSet) throws ImageWriteException {
        List directories = tiffOutputSet.getDirectories();
        if (1 <= directories.size()) {
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            int i = 0;
            TiffOutputDirectory tiffOutputDirectory = null;
            TiffOutputDirectory tiffOutputDirectory2 = null;
            TiffOutputDirectory tiffOutputDirectory3 = null;
            TiffOutputField tiffOutputField = null;
            TiffOutputField tiffOutputField2 = null;
            TiffOutputField tiffOutputField3 = null;
            while (i < directories.size()) {
                TiffOutputDirectory tiffOutputDirectory4 = (TiffOutputDirectory) directories.get(i);
                int i2 = tiffOutputDirectory4.type;
                Integer num = new Integer(i2);
                hashMap.put(num, tiffOutputDirectory4);
                if (i2 < 0) {
                    switch (i2) {
                        case -4:
                            if (tiffOutputDirectory == null) {
                                tiffOutputDirectory = tiffOutputDirectory4;
                                break;
                            } else {
                                throw new ImageWriteException("More than one Interoperability directory.");
                            }
                        case -3:
                            if (tiffOutputDirectory2 == null) {
                                tiffOutputDirectory2 = tiffOutputDirectory4;
                                break;
                            } else {
                                throw new ImageWriteException("More than one GPS directory.");
                            }
                        case -2:
                            if (tiffOutputDirectory3 == null) {
                                tiffOutputDirectory3 = tiffOutputDirectory4;
                                break;
                            } else {
                                throw new ImageWriteException("More than one EXIF directory.");
                            }
                        default:
                            StringBuilder sb = new StringBuilder();
                            sb.append("Unknown directory: ");
                            sb.append(i2);
                            throw new ImageWriteException(sb.toString());
                    }
                } else if (!arrayList.contains(num)) {
                    arrayList.add(new Integer(i2));
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("More than one directory with index: ");
                    sb2.append(i2);
                    sb2.append(".");
                    throw new ImageWriteException(sb2.toString());
                }
                HashSet hashSet = new HashSet();
                ArrayList fields = tiffOutputDirectory4.getFields();
                TiffOutputField tiffOutputField4 = tiffOutputField3;
                TiffOutputField tiffOutputField5 = tiffOutputField2;
                TiffOutputField tiffOutputField6 = tiffOutputField;
                int i3 = 0;
                while (i3 < fields.size()) {
                    TiffOutputField tiffOutputField7 = (TiffOutputField) fields.get(i3);
                    List list = directories;
                    Integer num2 = new Integer(tiffOutputField7.tag);
                    if (!hashSet.contains(num2)) {
                        hashSet.add(num2);
                        if (tiffOutputField7.tag == EXIF_TAG_EXIF_OFFSET.tag) {
                            if (tiffOutputField5 == null) {
                                tiffOutputField5 = tiffOutputField7;
                            } else {
                                throw new ImageWriteException("More than one Exif directory offset field.");
                            }
                        } else if (tiffOutputField7.tag == EXIF_TAG_INTEROP_OFFSET.tag) {
                            if (tiffOutputField6 == null) {
                                tiffOutputField6 = tiffOutputField7;
                            } else {
                                throw new ImageWriteException("More than one Interoperability directory offset field.");
                            }
                        } else if (tiffOutputField7.tag != EXIF_TAG_GPSINFO.tag) {
                            continue;
                        } else if (tiffOutputField4 == null) {
                            tiffOutputField4 = tiffOutputField7;
                        } else {
                            throw new ImageWriteException("More than one GPS directory offset field.");
                        }
                        i3++;
                        directories = list;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Tag (");
                        sb3.append(tiffOutputField7.tagInfo.getDescription());
                        sb3.append(") appears twice in directory.");
                        throw new ImageWriteException(sb3.toString());
                    }
                }
                List list2 = directories;
                i++;
                tiffOutputField = tiffOutputField6;
                tiffOutputField2 = tiffOutputField5;
                tiffOutputField3 = tiffOutputField4;
            }
            if (arrayList.size() >= 1) {
                Collections.sort(arrayList);
                int i4 = 0;
                TiffOutputDirectory tiffOutputDirectory5 = null;
                while (i4 < arrayList.size()) {
                    Integer num3 = (Integer) arrayList.get(i4);
                    if (num3.intValue() == i4) {
                        TiffOutputDirectory tiffOutputDirectory6 = (TiffOutputDirectory) hashMap.get(num3);
                        if (tiffOutputDirectory5 != null) {
                            tiffOutputDirectory5.setNextDirectory(tiffOutputDirectory6);
                        }
                        i4++;
                        tiffOutputDirectory5 = tiffOutputDirectory6;
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Missing directory: ");
                        sb4.append(i4);
                        sb4.append(".");
                        throw new ImageWriteException(sb4.toString());
                    }
                }
                TiffOutputDirectory tiffOutputDirectory7 = (TiffOutputDirectory) hashMap.get(new Integer(0));
                TiffOutputSummary tiffOutputSummary = new TiffOutputSummary(this.byteOrder, tiffOutputDirectory7, hashMap);
                if (tiffOutputDirectory != null || tiffOutputField == null) {
                    if (tiffOutputDirectory != null) {
                        if (tiffOutputDirectory3 == null) {
                            tiffOutputDirectory3 = tiffOutputSet.addExifDirectory();
                        }
                        if (tiffOutputField == null) {
                            tiffOutputField = TiffOutputField.createOffsetField(EXIF_TAG_INTEROP_OFFSET, this.byteOrder);
                            tiffOutputDirectory3.add(tiffOutputField);
                        }
                        tiffOutputSummary.add(tiffOutputDirectory, tiffOutputField);
                    }
                    if (tiffOutputDirectory3 != null || tiffOutputField2 == null) {
                        if (tiffOutputDirectory3 != null) {
                            if (tiffOutputField2 == null) {
                                tiffOutputField2 = TiffOutputField.createOffsetField(EXIF_TAG_EXIF_OFFSET, this.byteOrder);
                                tiffOutputDirectory7.add(tiffOutputField2);
                            }
                            tiffOutputSummary.add(tiffOutputDirectory3, tiffOutputField2);
                        }
                        if (tiffOutputDirectory2 != null || tiffOutputField3 == null) {
                            if (tiffOutputDirectory2 != null) {
                                if (tiffOutputField3 == null) {
                                    tiffOutputField3 = TiffOutputField.createOffsetField(EXIF_TAG_GPSINFO, this.byteOrder);
                                    tiffOutputDirectory7.add(tiffOutputField3);
                                }
                                tiffOutputSummary.add(tiffOutputDirectory2, tiffOutputField3);
                            }
                            return tiffOutputSummary;
                        }
                        throw new ImageWriteException("Output set has GPS Directory Offset field, but no GPS Directory");
                    }
                    throw new ImageWriteException("Output set has Exif Directory Offset field, but no Exif Directory");
                }
                throw new ImageWriteException("Output set has Interoperability Directory Offset field, but no Interoperability Directory");
            }
            throw new ImageWriteException("Missing root directory.");
        }
        throw new ImageWriteException("No directories.");
    }

    /* access modifiers changed from: protected */
    public void writeImageFileHeader(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException {
        writeImageFileHeader(binaryOutputStream, 8);
    }

    /* access modifiers changed from: protected */
    public void writeImageFileHeader(BinaryOutputStream binaryOutputStream, int i) throws IOException, ImageWriteException {
        binaryOutputStream.write(this.byteOrder);
        binaryOutputStream.write(this.byteOrder);
        binaryOutputStream.write2Bytes(42);
        binaryOutputStream.write4Bytes(i);
    }
}
