package com.myfitnesspal.android.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.btothefifth.patched.R;

import lanchon.dexpatcher.annotation.*;

@DexAdd
public class MyPreferencePalSettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypreferencepal_settings);
        SharedPreferences preferences = getSharedPreferences("mypreferencepal",MODE_PRIVATE);
        Boolean showNetCarbs = preferences.getBoolean("show_net_carbs", false);
        if (!showNetCarbs)
        {
            getNetCarbCheckBox().setChecked(false);
        }
        else {
            getNetCarbCheckBox().setChecked(true);
        }
        getNetCarbCheckBox().setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) showNetCarbsListener);
    }

    private CheckBox getNetCarbCheckBox()
    {
        return this.findViewById(R.id.netCarbCheckBox);
    }


    CompoundButton.OnCheckedChangeListener showNetCarbsListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            SharedPreferences preferences = getSharedPreferences("mypreferencepal",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("show_net_carbs", isChecked);
            editor.commit();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                editor.apply();
            }
        }
    };
}
