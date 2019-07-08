package com.twitter.sdk.android.core.internal.scribe;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface EventsStorage {
    void add(byte[] bArr) throws IOException;

    boolean canWorkingFileStore(int i, int i2);

    void deleteFilesInRollOverDirectory(List<File> list);

    List<File> getAllFilesInRollOverDirectory();

    List<File> getBatchOfFilesToSend(int i);

    int getWorkingFileUsedSizeInBytes();

    boolean isWorkingFileEmpty();

    void rollOver(String str) throws IOException;
}
