package io.uacf.configuration.ui.debug;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.DeviceInfo;
import io.uacf.configuration.R;
import io.uacf.configuration.sdk.UacfConfigurationSdk;
import io.uacf.configuration.sdk.UacfConfigurationSdkFactory;
import io.uacf.configuration.sdk.model.UacfConfiguration;
import io.uacf.configuration.sdk.model.UacfConfiguration.Builder;
import io.uacf.core.api.UacfApiException;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class UacfConfigurationDevToolActivity extends AppCompatActivity {
    private ConfigurationAdapter adapter;
    /* access modifiers changed from: private */
    public ListView configListView;
    /* access modifiers changed from: private */
    public UacfConfigurationSdk configurationSdk;
    private FloatingActionButton createFab;
    private List<UacfConfiguration> currentConfigurations;
    private TextView emptyState;
    private ForceFetchConfigurationTask forceFetchConfigurationTask;
    /* access modifiers changed from: private */
    public boolean forceFetchStarted;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout swipeRefreshLayout;
    private TextView updateByView;
    private TextView updatedAtView;
    private TextView versionTextView;

    private interface ClickConfigCallback {
        OnClickListener getOnClickConfigListener(UacfConfiguration uacfConfiguration);
    }

    private class ConfigurationAdapter extends ArrayAdapter<UacfConfiguration> {
        private List<UacfConfiguration> data;
        @LayoutRes
        private int resourceId;

        private ConfigurationAdapter(Context context, @NonNull int i, @LayoutRes List<UacfConfiguration> list) {
            super(context, i);
            this.resourceId = i;
            this.data = list;
        }

        @NonNull
        public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
            ConfigurationViewHolder configurationViewHolder;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(this.resourceId, viewGroup, false);
                view.setClickable(false);
                view.setOnClickListener(null);
                configurationViewHolder = new ConfigurationViewHolder(view);
                view.setTag(configurationViewHolder);
            } else {
                configurationViewHolder = (ConfigurationViewHolder) view.getTag();
            }
            configurationViewHolder.setData(getItem(i));
            return view;
        }

        @Nullable
        public UacfConfiguration getItem(int i) {
            return (UacfConfiguration) this.data.get(i);
        }

        public int getCount() {
            return this.data.size();
        }
    }

    private class ConfigurationViewHolder {
        private ImageView delete;
        private ImageView edit;
        private TextView key;
        private TextView value;
        private LinearLayout valuesLayout;

        public ConfigurationViewHolder(View view) {
            this.key = (TextView) view.findViewById(R.id.configuration_key);
            this.value = (TextView) view.findViewById(R.id.configuration_value_text);
            this.valuesLayout = (LinearLayout) view.findViewById(R.id.configuration_value_layout);
            this.edit = (ImageView) view.findViewById(R.id.configuration_edit);
            this.delete = (ImageView) view.findViewById(R.id.configuration_delete);
        }

        public void setData(final UacfConfiguration uacfConfiguration) {
            if (uacfConfiguration != null) {
                UacfConfigurationDevToolActivity.this.populateConfigValues(uacfConfiguration, this.key, this.value, this.valuesLayout);
                ImageView imageView = this.edit;
                if (imageView != null) {
                    imageView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            UacfConfigurationDevToolActivity.this.showEditConfigDialog(uacfConfiguration);
                        }
                    });
                }
                ImageView imageView2 = this.delete;
                if (imageView2 != null) {
                    imageView2.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            UacfConfigurationDevToolActivity.this.showDeleteConfigConfirmationDialog(uacfConfiguration);
                        }
                    });
                }
            }
        }
    }

    private static class ForceFetchConfigurationTask extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<UacfConfigurationDevToolActivity> activityWeakReference;

        private ForceFetchConfigurationTask(UacfConfigurationDevToolActivity uacfConfigurationDevToolActivity) {
            this.activityWeakReference = new WeakReference<>(uacfConfigurationDevToolActivity);
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... voidArr) {
            if (((UacfConfigurationDevToolActivity) this.activityWeakReference.get()).forceFetchStarted) {
                return Boolean.valueOf(false);
            }
            try {
                ((UacfConfigurationDevToolActivity) this.activityWeakReference.get()).configurationSdk.forceFetchConfiguration();
                return Boolean.valueOf(true);
            } catch (UacfApiException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            ((UacfConfigurationDevToolActivity) this.activityWeakReference.get()).configListView.setVisibility(0);
            if (bool.booleanValue()) {
                ((UacfConfigurationDevToolActivity) this.activityWeakReference.get()).loadMetaData();
                ((UacfConfigurationDevToolActivity) this.activityWeakReference.get()).loadConfigurationList();
            }
            ((UacfConfigurationDevToolActivity) this.activityWeakReference.get()).forceFetchStarted = false;
            ((UacfConfigurationDevToolActivity) this.activityWeakReference.get()).swipeRefreshLayout.setRefreshing(false);
        }
    }

    private interface NestedDialogCallback {
        void nestedValueSet(Class cls, String str, String str2);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_configuration);
        setUpActionBar();
        this.configurationSdk = new UacfConfigurationSdkFactory().newSdkInstance();
        this.configListView = (ListView) findViewById(R.id.configuration_list_view);
        this.versionTextView = (TextView) findViewById(R.id.configuration_version);
        this.updateByView = (TextView) findViewById(R.id.configuration_update_by);
        this.updatedAtView = (TextView) findViewById(R.id.configuration_update_at);
        this.emptyState = (TextView) findViewById(R.id.configuration_list_empty_state);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.configuration_list_swipe_container);
        this.createFab = (FloatingActionButton) findViewById(R.id.create_fab);
        this.createFab.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UacfConfigurationDevToolActivity.this.showCreateConfigDialog();
            }
        });
        this.swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                UacfConfigurationDevToolActivity.this.startForceFetchTask();
            }
        });
        loadMetaData();
        loadConfigurationList();
    }

    private void setUpActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(R.string.configuration_dev_tool);
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onResume() {
        super.onResume();
        if (this.forceFetchStarted) {
            startForceFetchTask();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.forceFetchStarted) {
            ForceFetchConfigurationTask forceFetchConfigurationTask2 = this.forceFetchConfigurationTask;
            if (forceFetchConfigurationTask2 != null) {
                forceFetchConfigurationTask2.cancel(true);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: private */
    public void startForceFetchTask() {
        this.configListView.setVisibility(8);
        this.emptyState.setVisibility(8);
        this.forceFetchConfigurationTask = new ForceFetchConfigurationTask();
        this.forceFetchConfigurationTask.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void loadMetaData() {
        Integer metaDataVersion = this.configurationSdk.getMetaDataVersion();
        if (metaDataVersion != null && metaDataVersion.intValue() > 0) {
            this.versionTextView.setText(String.valueOf(metaDataVersion));
        }
        String metaDataUpdateBy = this.configurationSdk.getMetaDataUpdateBy();
        if (metaDataUpdateBy != null) {
            this.updateByView.setText(metaDataUpdateBy);
        }
        String metaDataUpdateAt = this.configurationSdk.getMetaDataUpdateAt();
        if (metaDataUpdateAt != null) {
            try {
                this.updatedAtView.setText(DateFormat.getDateTimeInstance(1, 1).format(Format.newIso8601DateTimeFormatWithMs().parse(metaDataUpdateAt)));
            } catch (ParseException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to parse Updated At string - ");
                sb.append(metaDataUpdateAt);
                Log.d("ConfigDevToolActivity", sb.toString(), e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void loadConfigurationList() {
        ArrayList arrayList = new ArrayList(this.configurationSdk.getAllConfigurations().values());
        updateList(arrayList);
        int i = 0;
        this.emptyState.setVisibility(arrayList.size() == 0 ? 0 : 8);
        ListView listView = this.configListView;
        if (arrayList.size() == 0) {
            i = 8;
        }
        listView.setVisibility(i);
    }

    private void updateList(List<UacfConfiguration> list) {
        if (this.currentConfigurations == null) {
            this.currentConfigurations = new ArrayList();
        }
        this.currentConfigurations.clear();
        this.currentConfigurations.addAll(list);
        ConfigurationAdapter configurationAdapter = this.adapter;
        if (configurationAdapter == null) {
            ConfigurationAdapter configurationAdapter2 = new ConfigurationAdapter(this, R.layout.configuration_list_item, this.currentConfigurations);
            this.adapter = configurationAdapter2;
            this.configListView.setAdapter(this.adapter);
            return;
        }
        configurationAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void showCreateConfigDialog() {
        showEditConfigDialog(null);
    }

    /* access modifiers changed from: private */
    public void showEditConfigDialog(UacfConfiguration uacfConfiguration) {
        showEditConfigDialog(uacfConfiguration, null);
    }

    /* access modifiers changed from: private */
    public void showEditConfigDialog(final UacfConfiguration uacfConfiguration, NestedDialogCallback nestedDialogCallback) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.edit_configuration_dialog, null);
        EditText editText = (EditText) inflate.findViewById(R.id.configuration_key);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.configuration_type_spinner);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.configuration_value_text);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.configuration_value_layout);
        if (uacfConfiguration == null) {
            ArrayList arrayList = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.configuration_types_array)));
            arrayList.remove(3);
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, arrayList);
            arrayAdapter.setDropDownViewResource(17367049);
            spinner.setAdapter(arrayAdapter);
            spinner.setEnabled(true);
        }
        if (uacfConfiguration != null) {
            editText.setCursorVisible(false);
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false);
            editText.setClickable(false);
            spinner.setEnabled(false);
            if (uacfConfiguration.getStringValue() != null) {
                spinner.setSelection(0);
                editText2.setInputType(1);
            } else if (uacfConfiguration.getIntValue() != null) {
                spinner.setSelection(1);
                editText2.setInputType(2);
            } else if (uacfConfiguration.getFloatValue() != null) {
                spinner.setSelection(2);
                editText2.setInputType(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            } else if (uacfConfiguration.getMapValue() != null) {
                spinner.setSelection(3);
                editText2.setInputType(1);
            }
            populateConfigValues(uacfConfiguration, editText, editText2, linearLayout, new ClickConfigCallback() {
                public OnClickListener getOnClickConfigListener(final UacfConfiguration uacfConfiguration) {
                    return new OnClickListener() {
                        public void onClick(final View view) {
                            UacfConfigurationDevToolActivity.this.showEditConfigDialog(uacfConfiguration, new NestedDialogCallback() {
                                public void nestedValueSet(Class cls, String str, String str2) {
                                    if (cls == String.class) {
                                        uacfConfiguration.getMapValue().put(str, new Builder().setKey(str).setStringValue(str2).build());
                                    } else if (cls == Integer.class) {
                                        uacfConfiguration.getMapValue().put(str, new Builder().setKey(str).setIntValue(Integer.valueOf(Integer.parseInt(str2))).build());
                                    } else if (cls == Float.class) {
                                        uacfConfiguration.getMapValue().put(str, new Builder().setKey(str).setFloatValue(Float.valueOf(Float.parseFloat(str2))).build());
                                    }
                                    ((TextView) view.findViewById(R.id.configuration_value_text)).setText(str2);
                                }
                            });
                        }
                    };
                }
            });
        }
        editText2.setSelection(editText2.getText().length());
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                switch (i) {
                    case 0:
                        editText2.setInputType(1);
                        editText2.setTag(String.class);
                        return;
                    case 1:
                        editText2.setInputType(2);
                        editText2.setTag(Integer.class);
                        return;
                    case 2:
                        editText2.setInputType(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        editText2.setTag(Float.class);
                        return;
                    case 3:
                        editText2.setInputType(1);
                        editText2.setTag(Map.class);
                        return;
                    default:
                        return;
                }
            }
        });
        AlertDialog.Builder title = new AlertDialog.Builder(this).setView(inflate).setTitle((CharSequence) getString(R.string.create_configuration_dialog_title));
        String string = getString(R.string.create_configuration_dialog_save);
        final EditText editText3 = editText;
        final EditText editText4 = editText2;
        final NestedDialogCallback nestedDialogCallback2 = nestedDialogCallback;
        final Spinner spinner2 = spinner;
        final UacfConfiguration uacfConfiguration2 = uacfConfiguration;
        AnonymousClass6 r2 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText3.getText().toString();
                String obj2 = editText4.getText().toString();
                Class cls = (Class) editText4.getTag();
                NestedDialogCallback nestedDialogCallback = nestedDialogCallback2;
                if (nestedDialogCallback != null) {
                    nestedDialogCallback.nestedValueSet(cls, obj, obj2);
                } else if (TextUtils.isEmpty(obj)) {
                } else {
                    if (!TextUtils.isEmpty(obj2) || spinner2.getSelectedItemPosition() == 3) {
                        if (String.class.equals(cls)) {
                            UacfConfigurationDevToolActivity.this.configurationSdk.setStringForKey(obj, obj2);
                        } else if (Integer.class.equals(cls)) {
                            UacfConfigurationDevToolActivity.this.configurationSdk.setIntegerForKey(obj, Integer.valueOf(Integer.parseInt(obj2)));
                        } else if (Float.class.equals(cls)) {
                            UacfConfigurationDevToolActivity.this.configurationSdk.setFloatForKey(obj, Float.valueOf(Float.parseFloat(obj2)));
                        } else if (Map.class.equals(cls)) {
                            UacfConfigurationDevToolActivity.this.configurationSdk.setMapForKey(obj, uacfConfiguration2.getMapValue());
                        }
                        UacfConfigurationDevToolActivity.this.loadConfigurationList();
                    }
                }
            }
        };
        title.setPositiveButton((CharSequence) string, (DialogInterface.OnClickListener) r2).setNegativeButton((CharSequence) getString(R.string.create_configuration_dialog_cancel), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    /* access modifiers changed from: private */
    public void showDeleteConfigConfirmationDialog(final UacfConfiguration uacfConfiguration) {
        new AlertDialog.Builder(this).setMessage((CharSequence) getString(R.string.delete_configuration_dialog_text)).setPositiveButton((CharSequence) getString(R.string.delete_configuration_dialog_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                UacfConfigurationDevToolActivity.this.configurationSdk.deleteConfiguration(uacfConfiguration.getKey());
                UacfConfigurationDevToolActivity.this.loadConfigurationList();
            }
        }).show();
    }

    /* access modifiers changed from: private */
    public void populateConfigValues(UacfConfiguration uacfConfiguration, TextView textView, TextView textView2, LinearLayout linearLayout) {
        populateConfigValues(uacfConfiguration, textView, textView2, linearLayout, null);
    }

    private void populateConfigValues(UacfConfiguration uacfConfiguration, TextView textView, TextView textView2, LinearLayout linearLayout, ClickConfigCallback clickConfigCallback) {
        TextView textView3 = textView2;
        LinearLayout linearLayout2 = linearLayout;
        ClickConfigCallback clickConfigCallback2 = clickConfigCallback;
        textView.setText(uacfConfiguration.getKey());
        if (uacfConfiguration.getIntValue() != null) {
            textView3.setVisibility(0);
            linearLayout2.setVisibility(8);
            textView3.setText(String.valueOf(uacfConfiguration.getIntValue()));
            textView3.setTag(Integer.class);
        } else if (uacfConfiguration.getFloatValue() != null) {
            textView3.setVisibility(0);
            linearLayout2.setVisibility(8);
            textView3.setText(String.valueOf(uacfConfiguration.getFloatValue()));
            textView3.setTag(Float.class);
        } else if (uacfConfiguration.getStringValue() != null) {
            textView3.setVisibility(0);
            linearLayout2.setVisibility(8);
            textView3.setText(uacfConfiguration.getStringValue());
            textView3.setTag(String.class);
        } else if (uacfConfiguration.getMapValue() != null) {
            textView3.setVisibility(8);
            linearLayout2.setVisibility(0);
            linearLayout.removeAllViews();
            textView3.setTag(Map.class);
            DeviceInfo deviceInfo = new DeviceInfo(this);
            LayoutInflater from = LayoutInflater.from(this);
            int i = 0;
            for (Entry entry : uacfConfiguration.getMapValue().entrySet()) {
                View inflate = from.inflate(R.layout.configuration_item, linearLayout2, false);
                OnClickListener onClickListener = null;
                inflate.setBackground(null);
                inflate.setPadding(deviceInfo.toPixels(16), deviceInfo.toPixels(8), deviceInfo.toPixels(16), deviceInfo.toPixels(8));
                inflate.setClickable(false);
                if (clickConfigCallback2 != null) {
                    onClickListener = clickConfigCallback2.getOnClickConfigListener((UacfConfiguration) entry.getValue());
                }
                inflate.setOnClickListener(onClickListener);
                new ConfigurationViewHolder(inflate).setData((UacfConfiguration) entry.getValue());
                linearLayout2.addView(inflate);
                if (i < uacfConfiguration.getMapValue().size() - 1) {
                    View view = new View(this);
                    view.setLayoutParams(new LayoutParams(-1, deviceInfo.toPixels(1)));
                    view.setBackgroundColor(ContextCompat.getColor(this, R.color.ua_dark_gray));
                    linearLayout2.addView(view);
                }
                i++;
            }
        }
    }
}
