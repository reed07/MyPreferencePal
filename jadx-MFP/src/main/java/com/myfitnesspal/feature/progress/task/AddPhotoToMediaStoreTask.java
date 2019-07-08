package com.myfitnesspal.feature.progress.task;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.File;
import java.io.IOException;

public class AddPhotoToMediaStoreTask extends EventedTaskBase<String, IOException> {
    private static final String FILENAME_FORMAT = "MyFitnessPal-Progress-Photo-%d.jpg";
    private static final String RELATIVE_PATH = "/MyFitnessPal/";
    private final String filename;

    public static final class CompletedEvent extends TaskEventBase<String, IOException> {
    }

    public AddPhotoToMediaStoreTask(String str) {
        super(CompletedEvent.class);
        this.filename = str;
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) throws IOException {
        String outputDirectory = getOutputDirectory();
        if (!Strings.isEmpty(outputDirectory)) {
            String format = String.format(FILENAME_FORMAT, new Object[]{Long.valueOf(System.currentTimeMillis())});
            StringBuilder sb = new StringBuilder();
            sb.append("outputDir=");
            sb.append(outputDirectory);
            Ln.d(sb.toString(), new Object[0]);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("outputFilename=");
            sb2.append(format);
            Ln.d(sb2.toString(), new Object[0]);
            String copyFile = FileUtils.copyFile(context, this.filename, outputDirectory, format);
            MediaScannerConnection.scanFile(context, new String[]{copyFile}, null, null);
            return copyFile;
        }
        Ln.d("unable to find the external photos directory!", new Object[0]);
        throw new IOException("unable to find valid external storage directory");
    }

    private static String getOutputDirectory() {
        File createOutputDirectory = createOutputDirectory(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        if (createOutputDirectory == null) {
            createOutputDirectory = createOutputDirectory(Environment.getExternalStorageDirectory());
        }
        if (createOutputDirectory != null) {
            return createOutputDirectory.getAbsolutePath();
        }
        return null;
    }

    private static File createOutputDirectory(File file) {
        File file2 = null;
        if (file == null || !file.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        sb.append(RELATIVE_PATH);
        File file3 = new File(sb.toString());
        file3.mkdirs();
        if (file3.isDirectory() && file3.exists()) {
            file2 = file3;
        }
        return file2;
    }
}
