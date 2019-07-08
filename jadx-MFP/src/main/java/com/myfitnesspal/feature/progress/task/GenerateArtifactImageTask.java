package com.myfitnesspal.feature.progress.task;

import android.content.Context;
import android.view.View;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.util.ArtifactImageUtil;
import java.io.File;

public class GenerateArtifactImageTask extends EventedTaskBase<String, Exception> {
    private final String filename;
    private View view;

    public static class CompletedEvent extends TaskEventBase<String, Exception> {
    }

    public GenerateArtifactImageTask(String str, View view2) {
        super((TaskEventBase) new CompletedEvent());
        this.filename = str;
        this.view = view2;
    }

    public static String getOutputFilePath(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getOutputDirectory(context));
        sb.append(str);
        return sb.toString();
    }

    private static String getOutputDirectory(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalCacheDir());
        sb.append("/share/");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) throws Exception {
        String outputDirectory = getOutputDirectory(context);
        String outputFilePath = getOutputFilePath(context, this.filename);
        new File(outputDirectory).mkdirs();
        try {
            ArtifactImageUtil.renderMeasuredViewToFile(this.view, outputFilePath);
            return outputFilePath;
        } finally {
            this.view = null;
        }
    }
}
