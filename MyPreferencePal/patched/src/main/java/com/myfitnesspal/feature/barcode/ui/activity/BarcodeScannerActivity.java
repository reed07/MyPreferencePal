package com.myfitnesspal.feature.barcode.ui.activity;
import lanchon.dexpatcher.annotation.*;

import com.myfitnesspal.shared.ui.activity.*;
import com.myfitnesspal.shared.service.analytics.*;
import javax.inject.*;
import dagger.*;
import com.myfitnesspal.feature.barcode.service.*;

import android.content.pm.ActivityInfo;
import android.media.*;
import com.myfitnesspal.feature.barcode.ui.fragment.*;
import android.support.annotation.*;
import butterknife.*;
import com.myfitnesspal.shared.model.mapper.impl.*;
import com.myfitnesspal.feature.barcode.ui.view.*;
import android.support.v4.app.*;
import com.myfitnesspal.feature.barcode.util.*;
import android.os.*;
import java.util.*;
import com.myfitnesspal.shared.model.v2.*;
import com.myfitnesspal.feature.diary.ui.activity.*;
import android.content.*;
import android.app.*;
import java.io.*;
import com.myfitnesspal.shared.api.*;
import com.squareup.otto.*;
import com.myfitnesspal.shared.util.*;
import com.visionsmarts.*;
import android.widget.*;
import android.view.*;
import com.uacf.core.util.*;
import com.myfitnesspal.framework.taskrunner.*;

@DexEdit(defaultAction = DexAction.IGNORE)
public class BarcodeScannerActivity extends MfpActivity implements OnBarcodeScannedListener
{
	@DexWrap
	public void onCreate(final Bundle bundle) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		onCreate(bundle);
	}

	@Override
	public void onBarcodeScanCompleted(String s, int i) {

	}

	@Override
	public void onBarcodeScanFailed(int i) {

	}
}