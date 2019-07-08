package com.myfitnesspal.feature.debug.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.FileUploadUtil;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class DebugLogsFragment extends MfpFragment {
    @Inject
    Lazy<AppSettings> appSettings;
    /* access modifiers changed from: private */
    public ListView listView;
    @Inject
    @Named("logFile")
    File logsDir;
    private OnItemClickListener onListItemClick = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            switch (i) {
                case 0:
                    ((AppSettings) DebugLogsFragment.this.appSettings.get()).setPrivateFileLoggingEnabled(!((AppSettings) DebugLogsFragment.this.appSettings.get()).isPrivateFileLoggingEnabled());
                    ListViewUtils.notifyDataSetChanged(DebugLogsFragment.this.listView);
                    return;
                case 1:
                    Ln.clear();
                    return;
                case 2:
                    DebugLogsFragment.this.sendLogs();
                    return;
                default:
                    return;
            }
        }
    };

    public static class DebugLogsAdapter extends ArrayAdapter<String> {
        private final AppSettings appSettings;
        private final BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();

        public boolean areAllItemsEnabled() {
            return true;
        }

        public DebugLogsAdapter(AppSettings appSettings2, Context context, List<String> list) {
            super(context, R.layout.settings_item, list);
            this.appSettings = appSettings2;
        }

        public int getCount() {
            if (isDebugLoggingEnabled()) {
                return super.getCount();
            }
            return 1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.settings_item, null);
            }
            ((TextView) ViewUtils.findById(view, R.id.setting_name)).setText((CharSequence) getItem(i));
            CheckBox checkBox = (CheckBox) ViewUtils.findById(view, R.id.enabled);
            checkBox.setClickable(false);
            ViewUtils.setVisible(i == 0, checkBox);
            checkBox.setChecked(isDebugLoggingEnabled());
            checkBox.setEnabled(!this.buildConfiguration.isDebug());
            ((TextView) ViewUtils.findById(view, R.id.selected_setting)).setVisibility(8);
            return view;
        }

        public boolean isEnabled(int i) {
            boolean z = this.buildConfiguration.isDebug() || this.buildConfiguration.isQABuild();
            if (i != 0) {
                return isDebugLoggingEnabled();
            }
            if (!z) {
                return true;
            }
            return false;
        }

        private boolean isDebugLoggingEnabled() {
            return this.appSettings.isPrivateFileLoggingEnabled();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        this.listView.setAdapter(new DebugLogsAdapter((AppSettings) this.appSettings.get(), layoutInflater.getContext(), Arrays.asList(new String[]{getString(R.string.debugLogsEnable), getString(R.string.debugLogsClear), getString(R.string.debugLogsSend)})));
        this.listView.setOnItemClickListener(this.onListItemClick);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    /* access modifiers changed from: private */
    public void sendLogs() {
        ArrayList arrayList = (ArrayList) Enumerable.select((Collection<T>) FileUtils.getFilesSortedByModifiedTime(this.logsDir), (ReturningFunction1<U, T>) new ReturningFunction1<Uri, File>() {
            public Uri execute(File file) throws RuntimeException {
                try {
                    return FileUploadUtil.getCacheShareUriForFile(DebugLogsFragment.this.getContext(), file.getName());
                } catch (IOException e) {
                    Ln.e(e);
                    return null;
                }
            }
        });
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"androidlogs@myfitnesspal.com"});
        intent.putExtra("android.intent.extra.SUBJECT", "Debug Logs");
        startActivity(Intent.createChooser(intent, "Email:"));
    }
}
